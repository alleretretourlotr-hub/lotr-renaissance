package fr.alleretretour.lotr.world.feature;

import com.mojang.serialization.Codec;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.ISeedReader;
import net.minecraft.world.gen.ChunkGenerator;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.NoFeatureConfig;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.Random;

/**
 * PORT de LOTRWorldGenHolly : le houx (HOLLY et HOLLY_LARGE).
 *
 * Algorithme d'origine conserve tel quel :
 *   - hauteur 9-14 ; la variante large (setLarge -> extraTrunkWidth = 1)
 *     ajoute 10-13 blocs ET un tronc de 2x2 ;
 *   - le feuillage descend du sommet jusqu'a leafStop (2-3) :
 *       - au sommet : le carre du tronc seulement,
 *       - sur les 3 niveaux du haut et la couche du bas : rayon 1, coins
 *         retires au niveau height - 1,
 *       - ailleurs : rayon 3, les coins (2,2) omis un niveau sur deux et les
 *         bords a 3 filtres selon la parite - d'ou le feuillage en etages ;
 *   - le tronc (1x1 ou 2x2) est pose en dernier.
 */
public class LOTRFeatureHolly extends Feature<NoFeatureConfig> {

    private final int extraTrunkWidth;

    public LOTRFeatureHolly(Codec<NoFeatureConfig> codec, boolean large) {
        super(codec);
        this.extraTrunkWidth = large ? 1 : 0;   // PORT de setLarge()
    }

    @Override
    public boolean place(ISeedReader world, ChunkGenerator generator, Random random,
                         BlockPos pos, NoFeatureConfig config) {
        int i = pos.getX();
        int j = pos.getY();
        int k = pos.getZ();
        int height = 9 + random.nextInt(6);              // PORT
        if (extraTrunkWidth > 0) {
            height += 10 + random.nextInt(4);            // PORT
        }
        if (j < 1 || j + height + 1 > world.getHeight()) {
            return false;
        }
        // verification de l'espace (rayons progressifs du Legacy)
        for (int j1 = j; j1 <= j + 1 + height; j1++) {
            int range = 1;
            if (j1 == j) {
                range = 0;
            }
            if (j1 > j + 2 && j1 < j + height - 2) {
                range = 2;
            }
            for (int i1 = i - range; i1 <= i + range + extraTrunkWidth; i1++) {
                for (int k1 = k - range; k1 <= k + range + extraTrunkWidth; k1++) {
                    if (!replaceable(world, new BlockPos(i1, j1, k1))) {
                        return false;
                    }
                }
            }
        }
        // sol capable de porter une pousse
        for (int i1 = i; i1 <= i + extraTrunkWidth; i1++) {
            for (int k1 = k; k1 <= k + extraTrunkWidth; k1++) {
                BlockPos soil = new BlockPos(i1, j - 1, k1);
                if (!world.getBlockState(soil).canSustainPlant(world, soil,
                        net.minecraft.util.Direction.UP,
                        (net.minecraftforge.common.IPlantable) Blocks.OAK_SAPLING)) {
                    return false;
                }
            }
        }

        BlockState leaf = leaves();
        int leafStop = 2 + random.nextInt(2);            // PORT
        for (int j1 = height; j1 > leafStop; j1--) {
            if (j1 == height) {
                for (int i13 = 0; i13 <= extraTrunkWidth; i13++) {
                    for (int k13 = 0; k13 <= extraTrunkWidth; k13++) {
                        growLeaves(world, i + i13, j + j1, k + k13, leaf);
                    }
                }
                continue;
            }
            if (j1 > height - 3 || j1 == leafStop + 1) {
                for (int i13 = -1; i13 <= 1 + extraTrunkWidth; i13++) {
                    for (int k13 = -1; k13 <= 1 + extraTrunkWidth; k13++) {
                        int i2 = i13 > 0 ? i13 - extraTrunkWidth : i13;
                        int k2 = k13 > 0 ? k13 - extraTrunkWidth : k13;
                        if (j1 == height - 1 && Math.abs(i2) == 1 && Math.abs(k2) == 1) {
                            continue;
                        }
                        growLeaves(world, i + i13, j + j1, k + k13, leaf);
                    }
                }
                continue;
            }
            for (int i13 = -3; i13 <= 3 + extraTrunkWidth; i13++) {
                for (int k13 = -3; k13 <= 3 + extraTrunkWidth; k13++) {
                    int i2 = i13 > 0 ? i13 - extraTrunkWidth : i13;
                    int k2 = k13 > 0 ? k13 - extraTrunkWidth : k13;
                    // PORT exact du filtre de bord
                    if (j1 % 2 != 0 && Math.abs(i2) == 2 && Math.abs(k2) == 2
                            || (Math.abs(i2) >= 3 || Math.abs(k2) >= 3)
                            && (extraTrunkWidth <= 0 || j1 % 2 != 0 || i2 != 0 && k2 != 0)) {
                        continue;
                    }
                    growLeaves(world, i + i13, j + j1, k + k13, leaf);
                }
            }
        }

        BlockState log = log();
        for (int j1 = 0; j1 < height; j1++) {
            for (int i13 = 0; i13 <= extraTrunkWidth; i13++) {
                for (int k13 = 0; k13 <= extraTrunkWidth; k13++) {
                    BlockPos p = new BlockPos(i + i13, j + j1, k + k13);
                    if (!replaceable(world, p)) {
                        continue;
                    }
                    setBlock(world, p, log);
                }
            }
        }
        return true;
    }

    /** PORT de growLeaves. */
    private void growLeaves(ISeedReader world, int i, int j, int k, BlockState leaf) {
        BlockPos p = new BlockPos(i, j, k);
        if (replaceable(world, p)) {
            setBlock(world, p, leaf);
        }
    }

    private static boolean replaceable(ISeedReader world, BlockPos pos) {
        BlockState state = world.getBlockState(pos);
        return state.isAir(world, pos) || state.getMaterial().isReplaceable()
                || state.getBlock() instanceof net.minecraft.block.LeavesBlock;
    }

    private static BlockState log() {
        return state("lotr:holly_log", Blocks.OAK_LOG);
    }

    private static BlockState leaves() {
        return state("lotr:holly_leaves", Blocks.OAK_LEAVES);
    }

    private static BlockState state(String id, Block fallback) {
        Block block = ForgeRegistries.BLOCKS.getValue(new ResourceLocation(id));
        return (block == null || block == Blocks.AIR ? fallback : block).defaultBlockState();
    }
}
