package fr.alleretretour.lotr.world.feature;

import com.mojang.serialization.Codec;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.VineBlock;
import net.minecraft.util.Direction;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.ISeedReader;
import net.minecraft.world.gen.ChunkGenerator;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.NoFeatureConfig;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.Random;

/**
 * PORT de LOTRWorldGenMangrove : le paletuvier.
 *
 * Algorithme d'origine conserve tel quel :
 *   - hauteur 6-10 ; pousse aussi sur les sols a buisson mort (mangrove) ;
 *   - cime : 4 couches depuis le sommet, rayon = 1 - (niveau relatif / 2), les
 *     coins etant omis une fois sur deux (toujours au niveau du sommet) ;
 *   - lianes : une chance sur huit par face libre de chaque bloc de feuilles,
 *     retombant de 2 a 4 blocs ;
 *   - racines echasses : partant des quatre cotes (jamais des diagonales),
 *     d'une hauteur de 1 a 3, descendant en s'ecartant deux fois sur trois,
 *     jusqu'a rencontrer un obstacle - la signature du paletuvier.
 */
public class LOTRFeatureMangrove extends Feature<NoFeatureConfig> {

    public LOTRFeatureMangrove(Codec<NoFeatureConfig> codec) {
        super(codec);
    }

    @Override
    public boolean place(ISeedReader world, ChunkGenerator generator, Random random,
                         BlockPos pos, NoFeatureConfig config) {
        int i = pos.getX();
        int j = pos.getY();
        int k = pos.getZ();
        int height = 6 + random.nextInt(5);          // PORT
        if (j < 1 || j + height + 1 > world.getHeight()) {
            return false;
        }
        for (int j1 = j; j1 <= j + 1 + height; j1++) {
            int range = 1;
            if (j1 == j) {
                range = 0;
            }
            if (j1 >= j + 1 + height - 2) {
                range = 2;
            }
            for (int i1 = i - range; i1 <= i + range; i1++) {
                for (int k1 = k - range; k1 <= k + range; k1++) {
                    if (!replaceable(world, new BlockPos(i1, j1, k1))) {
                        return false;
                    }
                }
            }
        }
        BlockPos soil = new BlockPos(i, j - 1, k);
        BlockState below = world.getBlockState(soil);
        boolean canGrow = below.canSustainPlant(world, soil, Direction.UP,
                (net.minecraftforge.common.IPlantable) Blocks.OAK_SAPLING)
                || below.canSustainPlant(world, soil, Direction.UP,
                (net.minecraftforge.common.IPlantable) Blocks.DEAD_BUSH);
        if (!canGrow) {
            return false;
        }

        BlockState leaf = leaves();
        BlockState log = log();

        // cime : 4 couches depuis le sommet
        for (int j1 = j - 3 + height; j1 <= j + height; j1++) {
            int j2 = j1 - (j + height);
            int leafRange = 1 - j2 / 2;              // PORT : leafRangeMin 0, facteur 2
            for (int i1 = i - leafRange; i1 <= i + leafRange; i1++) {
                int i2 = i1 - i;
                for (int k1 = k - leafRange; k1 <= k + leafRange; k1++) {
                    int k2 = k1 - k;
                    BlockPos p = new BlockPos(i1, j1, k1);
                    if (Math.abs(i2) == leafRange && Math.abs(k2) == leafRange
                            && (random.nextInt(2) == 0 || j2 == 0)) {
                        continue;
                    }
                    if (!replaceable(world, p)) {
                        continue;
                    }
                    setBlock(world, p, leaf);
                    // lianes retombantes (PORT : 1 chance sur 8 par face)
                    tryVines(world, random, new BlockPos(i1 - 1, j1, k1), Direction.EAST);
                    tryVines(world, random, new BlockPos(i1 + 1, j1, k1), Direction.WEST);
                    tryVines(world, random, new BlockPos(i1, j1, k1 - 1), Direction.SOUTH);
                    tryVines(world, random, new BlockPos(i1, j1, k1 + 1), Direction.NORTH);
                }
            }
        }

        for (int j1 = 0; j1 < height; j1++) {
            BlockPos p = new BlockPos(i, j + j1, k);
            if (!replaceable(world, p)) {
                continue;
            }
            setBlock(world, p, log);
        }

        // racines echasses
        for (int i1 = i - 1; i1 <= i + 1; i1++) {
            for (int k1 = k - 1; k1 <= k + 1; k1++) {
                int i2 = i1 - i;
                int k2 = k1 - k;
                if (Math.abs(i2) == Math.abs(k2)) {
                    continue;   // PORT : ni le centre ni les diagonales
                }
                int rootX = i1;
                int rootY = j + 1 + random.nextInt(3);
                int rootZ = k1;
                int xWay = Integer.signum(i2);
                int zWay = Integer.signum(k2);
                while (replaceable(world, new BlockPos(rootX, rootY, rootZ))) {
                    setBlock(world, new BlockPos(rootX, rootY, rootZ), log);
                    rootY--;
                    if (random.nextInt(3) > 0) {
                        rootX += xWay;
                        rootZ += zWay;
                    }
                    if (rootY <= 0) {
                        break;
                    }
                }
            }
        }
        return true;
    }

    /** PORT de growVines : liane sur la face, retombant de 2 a 4 blocs. */
    private void tryVines(ISeedReader world, Random random, BlockPos pos, Direction face) {
        if (random.nextInt(8) != 0 || !world.isEmptyBlock(pos)) {
            return;
        }
        BlockState vine = Blocks.VINE.defaultBlockState()
                .setValue(VineBlock.getPropertyForFace(face), Boolean.TRUE);
        setBlock(world, pos, vine);
        BlockPos below = pos.below();
        int vines = 0;
        int max = 2 + random.nextInt(3);
        while (world.isEmptyBlock(below) && vines < max) {
            setBlock(world, below, vine);
            below = below.below();
            vines++;
        }
    }

    private static boolean replaceable(ISeedReader world, BlockPos pos) {
        BlockState state = world.getBlockState(pos);
        return state.isAir(world, pos) || state.getMaterial().isReplaceable()
                || state.getBlock() instanceof net.minecraft.block.LeavesBlock;
    }

    private static BlockState log() {
        return state("lotr:mangrove_log", Blocks.JUNGLE_LOG);
    }

    private static BlockState leaves() {
        return state("lotr:mangrove_leaves", Blocks.JUNGLE_LEAVES);
    }

    private static BlockState state(String id, Block fallback) {
        Block block = ForgeRegistries.BLOCKS.getValue(new ResourceLocation(id));
        return (block == null || block == Blocks.AIR ? fallback : block).defaultBlockState();
    }
}
