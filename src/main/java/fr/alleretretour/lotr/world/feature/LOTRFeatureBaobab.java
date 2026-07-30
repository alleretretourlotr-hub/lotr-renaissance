package fr.alleretretour.lotr.world.feature;

import com.mojang.serialization.Codec;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
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
 * PORT de LOTRWorldGenBaobab : le baobab du Harad.
 *
 * Algorithme d'origine conserve tel quel :
 *   - hauteur 16-25, tronc CIRCULAIRE de rayon 4 (filtre i^2 + k^2 <= r^2) ;
 *   - le tronc s'incline : tous les xSlope (5-14) blocs de hauteur, le centre
 *     se decale d'une case en X, idem en Z avec zSlope, signe tire au hasard ;
 *   - branches : sur le dernier quart de la hauteur, 2 a 4 branches par niveau,
 *     angle aleatoire, longueur 4-6, montant d'un bloc tous les deux pas
 *     (j = niveau - 3 + pas / 2), terminees par 1-2 couches de feuilles ;
 *   - couronne : une colonne sur cinq du disque du tronc monte de 2-4 blocs
 *     puis recoit deux couches de feuilles.
 */
public class LOTRFeatureBaobab extends Feature<NoFeatureConfig> {

    private static final int TRUNK_RADIUS = 4;   // PORT : trunkCircleWidth

    public LOTRFeatureBaobab(Codec<NoFeatureConfig> codec) {
        super(codec);
    }

    @Override
    public boolean place(ISeedReader world, ChunkGenerator generator, Random random,
                         BlockPos pos, NoFeatureConfig config) {
        int i = pos.getX();
        int j = pos.getY();
        int k = pos.getZ();
        int height = 16 + random.nextInt(10);            // PORT
        int xSlope = 5 + random.nextInt(10);             // PORT
        if (random.nextBoolean()) {
            xSlope *= -1;
        }
        int zSlope = 5 + random.nextInt(10);             // PORT
        if (random.nextBoolean()) {
            zSlope *= -1;
        }
        if (j < 1 || j + height + 5 > world.getHeight()) {
            return false;
        }
        // place libre sur tout le disque du tronc
        for (int i1 = i - TRUNK_RADIUS - 1; i1 <= i + TRUNK_RADIUS + 1; i1++) {
            for (int k1 = k - TRUNK_RADIUS - 1; k1 <= k + TRUNK_RADIUS + 1; k1++) {
                int i2 = Math.abs(i1 - i);
                int k2 = Math.abs(k1 - k);
                if (i2 * i2 + k2 * k2 > TRUNK_RADIUS * TRUNK_RADIUS) {
                    continue;
                }
                for (int j1 = j; j1 <= j + 1 + height; j1++) {
                    if (!replaceable(world, new BlockPos(i1, j1, k1))) {
                        return false;
                    }
                }
            }
        }

        BlockState log = log();
        BlockState leaf = leaves();
        int centerX = i;
        int centerZ = k;
        // tronc circulaire incline
        for (int j1 = 0; j1 < height; j1++) {
            for (int i1 = centerX - TRUNK_RADIUS - 1; i1 <= centerX + TRUNK_RADIUS + 1; i1++) {
                for (int k1 = centerZ - TRUNK_RADIUS - 1; k1 <= centerZ + TRUNK_RADIUS + 1; k1++) {
                    int i2 = Math.abs(i1 - centerX);
                    int k2 = Math.abs(k1 - centerZ);
                    if (i2 * i2 + k2 * k2 > TRUNK_RADIUS * TRUNK_RADIUS) {
                        continue;
                    }
                    setBlock(world, new BlockPos(i1, j + j1, k1), log);
                }
            }
            if (j1 % xSlope == 0) {
                centerX += xSlope > 0 ? 1 : -1;
            }
            if (j1 % zSlope == 0) {
                centerZ += zSlope > 0 ? 1 : -1;
            }
        }

        // branches sur le dernier quart de la hauteur
        for (int j1 = j + height - 1; j1 > j + (int) (height * 0.75f); j1--) {
            int branches = 2 + random.nextInt(3);        // PORT
            for (int l = 0; l < branches; l++) {
                float angle = random.nextFloat() * (float) Math.PI * 2.0f;
                int bx = centerX;
                int bz = centerZ;
                int by = j1;
                int length = MathHelper.nextInt(random, 4, 6);   // PORT
                for (int l1 = TRUNK_RADIUS; l1 < TRUNK_RADIUS + length; l1++) {
                    bx = centerX + (int) (1.5f + MathHelper.cos(angle) * l1);
                    by = j1 - 3 + l1 / 2;
                    bz = centerZ + (int) (1.5f + MathHelper.sin(angle) * l1);
                    if (!replaceable(world, new BlockPos(bx, by, bz))) {
                        break;
                    }
                    setBlock(world, new BlockPos(bx, by, bz), log);
                }
                int leafMin = 1 + random.nextInt(2);     // PORT
                for (int j3 = by - leafMin; j3 <= by; j3++) {
                    spawnLeaves(world, bx, j3, bz, 1 - (j3 - by), leaf);
                }
            }
        }

        // couronne : une colonne sur cinq du disque
        for (int i1 = centerX - TRUNK_RADIUS - 1; i1 <= centerX + TRUNK_RADIUS + 1; i1++) {
            for (int k1 = centerZ - TRUNK_RADIUS - 1; k1 <= centerZ + TRUNK_RADIUS + 1; k1++) {
                int i2 = Math.abs(i1 - centerX);
                int k2 = Math.abs(k1 - centerZ);
                if (i2 * i2 + k2 * k2 > TRUNK_RADIUS * TRUNK_RADIUS || random.nextInt(5) != 0) {
                    continue;
                }
                int j1 = j + height;
                int topHeight = 2 + random.nextInt(3);   // PORT
                for (int l = 0; l < topHeight; l++) {
                    setBlock(world, new BlockPos(i1, j1, k1), log);
                    j1++;
                }
                for (int j2 = j1 - 2; j2 <= j1; j2++) {
                    spawnLeaves(world, i1, j2, k1, 1 - (j2 - j1), leaf);
                }
            }
        }
        return true;
    }

    /** PORT de spawnLeaves : disque de feuilles de rayon donne. */
    private void spawnLeaves(ISeedReader world, int i, int j, int k, int leafRange,
                             BlockState leaf) {
        int leafRangeSq = leafRange * leafRange;
        for (int i1 = i - leafRange; i1 <= i + leafRange; i1++) {
            for (int k1 = k - leafRange; k1 <= k + leafRange; k1++) {
                int i2 = i1 - i;
                int k2 = k1 - k;
                BlockPos p = new BlockPos(i1, j, k1);
                if (i2 * i2 + k2 * k2 > leafRangeSq || !replaceable(world, p)) {
                    continue;
                }
                setBlock(world, p, leaf);
            }
        }
    }

    private static boolean replaceable(ISeedReader world, BlockPos pos) {
        BlockState state = world.getBlockState(pos);
        return state.isAir(world, pos) || state.getMaterial().isReplaceable()
                || state.getBlock() instanceof net.minecraft.block.LeavesBlock;
    }

    private static BlockState log() {
        return state("lotr:baobab_log", Blocks.ACACIA_LOG);
    }

    private static BlockState leaves() {
        return state("lotr:baobab_leaves", Blocks.ACACIA_LEAVES);
    }

    private static BlockState state(String id, Block fallback) {
        Block block = ForgeRegistries.BLOCKS.getValue(new ResourceLocation(id));
        return (block == null || block == Blocks.AIR ? fallback : block).defaultBlockState();
    }
}
