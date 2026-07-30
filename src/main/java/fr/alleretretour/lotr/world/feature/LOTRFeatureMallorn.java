package fr.alleretretour.lotr.world.feature;

import com.mojang.serialization.Codec;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.util.Direction;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.ISeedReader;
import net.minecraft.world.gen.ChunkGenerator;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.NoFeatureConfig;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.Random;

/**
 * PORT de LOTRWorldGenMallorn : le mallorn de la Lothlorien.
 *
 * Algorithme d'origine conserve tel quel :
 *   - hauteur 10-14 (variante haute : 20-30), feuillage a partir de 60 % ;
 *   - branches : de haut en bas jusqu'a 60 % de la hauteur, 1 ou 2 par niveau,
 *     l'angle avancant de 50 a 119 degres a chaque branche (deg cumulatif),
 *     angle vertical tire jusqu'a 50 degres, longueur 4-9 ; la branche avance
 *     d'une case des que la composante correspondante franchit un entier ;
 *   - chaque branche se termine par un HOUPPIER (growLeafCanopy) : 4 couches,
 *     rayon maximal 3-4, decalages {-2, 0, -1, -2}, filtre spherique
 *     (distance carree) ET distance de Manhattan <= 4, avec un bloc sur quatre
 *     omis sur l'avant-derniere couronne ;
 *   - racines : quatre cotes, descendant jusqu'a rencontrer un obstacle.
 */
public class LOTRFeatureMallorn extends Feature<NoFeatureConfig> {

    private final int minHeight;
    private final int maxHeight;

    public LOTRFeatureMallorn(Codec<NoFeatureConfig> codec, int minHeight, int maxHeight) {
        super(codec);
        this.minHeight = minHeight;
        this.maxHeight = maxHeight;
    }

    @Override
    public boolean place(ISeedReader world, ChunkGenerator generator, Random random,
                         BlockPos pos, NoFeatureConfig config) {
        int i = pos.getX();
        int j = pos.getY();
        int k = pos.getZ();
        int height = MathHelper.nextInt(random, minHeight, maxHeight);
        int leafMin = j + (int) (height * 0.6f);          // PORT
        if (j < 1 || j + height + 1 > world.getHeight()) {
            return false;
        }
        for (int j1 = j; j1 <= j + height + 1; j1++) {
            int range = 1;
            if (j1 == j) {
                range = 0;
            }
            if (j1 >= leafMin) {
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
        if (!world.getBlockState(soil).canSustainPlant(world, soil, Direction.UP,
                (net.minecraftforge.common.IPlantable) Blocks.OAK_SAPLING)) {
            return false;
        }

        BlockState log = log();
        BlockState leaf = leaves();

        int deg = 0;
        for (int j1 = j + height; j1 >= leafMin; j1--) {
            int branches = 1 + random.nextInt(2);         // PORT
            for (int b = 0; b < branches; b++) {
                deg += 50 + random.nextInt(70);           // PORT : angle cumulatif
                float angle = (float) Math.toRadians(deg);
                float cos = MathHelper.cos(angle);
                float sin = MathHelper.sin(angle);
                float angleY = random.nextFloat() * 0.8726646259971648f;  // 50 degres
                float sinY = MathHelper.sin(angleY);
                int length = 4 + random.nextInt(6);       // PORT
                int i1 = i;
                int k1 = k;
                int j2 = j1;
                for (int l = 0; l < length; l++) {
                    if (Math.floor(cos * l) != Math.floor(cos * (l - 1))) {
                        i1 += (int) Math.signum(cos);
                    }
                    if (Math.floor(sin * l) != Math.floor(sin * (l - 1))) {
                        k1 += (int) Math.signum(sin);
                    }
                    if (Math.floor(sinY * l) != Math.floor(sinY * (l - 1))) {
                        j2 += (int) Math.signum(sinY);
                    }
                    BlockPos p = new BlockPos(i1, j2, k1);
                    BlockState state = world.getBlockState(p);
                    if (!state.getMaterial().isReplaceable() && !isLeaves(state)
                            && state.getBlock() != log.getBlock()) {
                        break;
                    }
                    setBlock(world, p, log);
                }
                growLeafCanopy(world, random, i1, j2, k1, leaf);
            }
        }

        for (int j1 = j; j1 < j + height; j1++) {
            setBlock(world, new BlockPos(i, j1, k), log);
        }

        // racines
        for (int i1 = i - 1; i1 <= i + 1; i1++) {
            for (int k1 = k - 1; k1 <= k + 1; k1++) {
                if (Math.abs(i1 - i) == Math.abs(k1 - k)) {
                    continue;   // PORT : ni le centre ni les diagonales
                }
                int rootY = j + random.nextInt(2);
                while (rootY > 0 && replaceable(world, new BlockPos(i1, rootY, k1))) {
                    setBlock(world, new BlockPos(i1, rootY, k1), log);
                    rootY--;
                }
            }
        }
        return true;
    }

    /** PORT de growLeafCanopy : houppier au bout de chaque branche. */
    private void growLeafCanopy(ISeedReader world, Random random, int i, int j, int k,
                                BlockState leaf) {
        int leafStart = j - 1;
        int leafTop = j + 2;
        int maxRange = 3 + random.nextInt(2);
        int[] ranges = {-2, 0, -1, -2};                   // PORT exact
        for (int j1 = leafStart; j1 <= leafTop; j1++) {
            int leafRange = maxRange + ranges[j1 - leafStart];
            int leafRangeSq = leafRange * leafRange;
            for (int i1 = i - leafRange; i1 <= i + leafRange; i1++) {
                for (int k1 = k - leafRange; k1 <= k + leafRange; k1++) {
                    int i2 = Math.abs(i1 - i);
                    int k2 = Math.abs(k1 - k);
                    int j2 = Math.abs(j1 - j);
                    boolean grow = i2 * i2 + k2 * k2 < leafRangeSq && i2 + j2 + k2 <= 4;
                    if (i2 == leafRange - 1 || k2 == leafRange - 1) {
                        grow = grow && random.nextInt(4) != 0;
                    }
                    BlockPos p = new BlockPos(i1, j1, k1);
                    if (!grow || !replaceable(world, p)) {
                        continue;
                    }
                    setBlock(world, p, leaf);
                }
            }
        }
    }

    private static boolean isLeaves(BlockState state) {
        return state.getBlock() instanceof net.minecraft.block.LeavesBlock;
    }

    private static boolean replaceable(ISeedReader world, BlockPos pos) {
        BlockState state = world.getBlockState(pos);
        return state.isAir(world, pos) || state.getMaterial().isReplaceable()
                || state.getBlock() instanceof net.minecraft.block.LeavesBlock;
    }

    private static BlockState log() {
        return state("lotr:mallorn_log", Blocks.BIRCH_LOG);
    }

    private static BlockState leaves() {
        return state("lotr:mallorn_leaves", Blocks.BIRCH_LEAVES);
    }

    private static BlockState state(String id, Block fallback) {
        Block block = ForgeRegistries.BLOCKS.getValue(new ResourceLocation(id));
        return (block == null || block == Blocks.AIR ? fallback : block).defaultBlockState();
    }
}
