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
 * PORT de LOTRWorldGenMallornExtreme : les mallorns geants de Caras Galadhon.
 *
 * Constantes et algorithme d'origine, conserves tels quels :
 *   HEIGHT 35-70, tronc 5x5 (trunkWidth 2) ;
 *   RAMURES (boughs) : tour complet par pas de 10 a 30 degres ; chacune longue
 *     de 15 a 25, EPAISSE (facteur 0.03, l'epaisseur diminuant de moitie vers
 *     la pointe), demarrant a 90-100 % de la hauteur et montant de 7 a 10 ;
 *   au bout de chaque ramure : 8 a 16 branches, longues de 8 a 10, montant de
 *     6 a 8, terminees par un bouquet spherique de rayon 3 ;
 *   BRANCHES BASSES : de 90 % a 67 % de la hauteur, par pas de 1 a 3 niveaux,
 *     1 a 5 branches longues de 10 a 20, suivies de trois couches de feuilles
 *     (rayons 2, 3 et 1) ;
 *   RACINES : 6 a 10, partant des flancs, descendant jusqu'au sol dur.
 *
 * Les maisons elfiques (HOUSE_CHANCE 0.7 / HOUSE_ELFLORD_CHANCE 0.15) sont des
 * STRUCTURES : elles viendront avec le portage des structures, aucune n'a ete
 * inventee ici.
 */
public class LOTRFeatureMallornExtreme extends Feature<NoFeatureConfig> {

    private static final int HEIGHT_MIN = 35;
    private static final int HEIGHT_MAX = 70;
    private static final int BOUGH_ANGLE_INTERVAL_MIN = 10;
    private static final int BOUGH_ANGLE_INTERVAL_MAX = 30;
    private static final int BOUGH_LENGTH_MIN = 15;
    private static final int BOUGH_LENGTH_MAX = 25;
    private static final float BOUGH_THICKNESS_FACTOR = 0.03f;
    private static final float BOUGH_BASE_HEIGHT_MIN = 0.9f;
    private static final float BOUGH_BASE_HEIGHT_MAX = 1.0f;
    private static final int BOUGH_HEIGHT_MIN = 7;
    private static final int BOUGH_HEIGHT_MAX = 10;
    private static final int BRANCH_LENGTH_MIN = 8;
    private static final int BRANCH_LENGTH_MAX = 10;
    private static final int BRANCH_HEIGHT_MIN = 6;
    private static final int BRANCH_HEIGHT_MAX = 8;
    private static final int TRUNK_WIDTH = 2;

    public LOTRFeatureMallornExtreme(Codec<NoFeatureConfig> codec) {
        super(codec);
    }

    @Override
    public boolean place(ISeedReader world, ChunkGenerator generator, Random random,
                         BlockPos pos, NoFeatureConfig config) {
        int i = pos.getX();
        int j = pos.getY();
        int k = pos.getZ();
        int height = MathHelper.nextInt(random, HEIGHT_MIN, HEIGHT_MAX);
        if (j < 1 || j + height + 5 > world.getHeight()) {
            return false;
        }
        for (int j1 = j; j1 <= j + 1 + height; j1++) {
            int range = TRUNK_WIDTH;
            if (j1 == j) {
                range = 0;
            }
            if (j1 >= j + 1 + height - 2) {
                range = TRUNK_WIDTH + 1;
            }
            for (int i1 = i - range; i1 <= i + range; i1++) {
                for (int k1 = k - range; k1 <= k + range; k1++) {
                    if (!replaceable(world, new BlockPos(i1, j1, k1))) {
                        return false;
                    }
                }
            }
        }
        for (int i1 = i - TRUNK_WIDTH; i1 <= i + TRUNK_WIDTH; i1++) {
            for (int k1 = k - TRUNK_WIDTH; k1 <= k + TRUNK_WIDTH; k1++) {
                BlockPos soil = new BlockPos(i1, j - 1, k1);
                if (!world.getBlockState(soil).canSustainPlant(world, soil, Direction.UP,
                        (net.minecraftforge.common.IPlantable) Blocks.OAK_SAPLING)) {
                    return false;
                }
            }
        }

        BlockState log = log();
        BlockState leaf = leaves();

        // tronc 5x5
        for (int i1 = i - TRUNK_WIDTH; i1 <= i + TRUNK_WIDTH; i1++) {
            for (int k1 = k - TRUNK_WIDTH; k1 <= k + TRUNK_WIDTH; k1++) {
                for (int j1 = 0; j1 < height; j1++) {
                    BlockPos p = new BlockPos(i1, j + j1, k1);
                    if (replaceable(world, p)) {
                        setBlock(world, p, log);
                    }
                }
            }
        }

        // ramures epaisses
        int angle = 0;
        while (angle < 360) {
            angle += MathHelper.nextInt(random, BOUGH_ANGLE_INTERVAL_MIN, BOUGH_ANGLE_INTERVAL_MAX);
            float angleR = (float) Math.toRadians(angle);
            float sin = MathHelper.sin(angleR);
            float cos = MathHelper.cos(angleR);
            int boughLength = MathHelper.nextInt(random, BOUGH_LENGTH_MIN, BOUGH_LENGTH_MAX);
            int boughThickness = Math.round(boughLength * BOUGH_THICKNESS_FACTOR);
            int boughBaseHeight = j + MathHelper.floor(height
                    * MathHelper.nextFloat(random, BOUGH_BASE_HEIGHT_MIN, BOUGH_BASE_HEIGHT_MAX));
            int boughHeight = MathHelper.nextInt(random, BOUGH_HEIGHT_MIN, BOUGH_HEIGHT_MAX);
            for (int l = 0; l < boughLength; l++) {
                int bi = i + Math.round(sin * l);
                int bk = k + Math.round(cos * l);
                int bj = boughBaseHeight + Math.round((float) l / boughLength * boughHeight);
                int range = boughThickness
                        - Math.round((float) l / boughLength * boughThickness * 0.5f);
                for (int i2 = bi - range; i2 <= bi + range; i2++) {
                    for (int j2 = bj - range; j2 <= bj + range; j2++) {
                        for (int k2 = bk - range; k2 <= bk + range; k2++) {
                            BlockPos p = new BlockPos(i2, j2, k2);
                            if (replaceable(world, p)) {
                                setBlock(world, p, log);
                            }
                        }
                    }
                }
                if (l != boughLength - 1) {
                    continue;
                }
                // bouquet de branches au bout de la ramure
                int branches = MathHelper.nextInt(random, 8, 16);
                for (int b = 0; b < branches; b++) {
                    float branchAngle = random.nextFloat() * 2.0f * (float) Math.PI;
                    float bSin = MathHelper.sin(branchAngle);
                    float bCos = MathHelper.cos(branchAngle);
                    int branchLength = MathHelper.nextInt(random, BRANCH_LENGTH_MIN, BRANCH_LENGTH_MAX);
                    int branchHeight = MathHelper.nextInt(random, BRANCH_HEIGHT_MIN, BRANCH_HEIGHT_MAX);
                    for (int b1 = 0; b1 < branchLength; b1++) {
                        int i2 = bi + Math.round(bSin * b1);
                        int k2 = bk + Math.round(bCos * b1);
                        int j2 = bj + Math.round((float) b1 / branchLength * branchHeight);
                        BlockPos p = new BlockPos(i2, j2, k2);
                        if (replaceable(world, p)) {
                            setBlock(world, p, log);
                        }
                        if (b1 == branchLength - 1) {
                            spawnLeafCluster(world, random, i2, j2, k2, 3, leaf);
                        }
                    }
                }
            }
        }

        // branches basses
        for (int j1 = j + (int) (height * BOUGH_BASE_HEIGHT_MIN);
             j1 > j + (int) (height * 0.67f); j1 -= 1 + random.nextInt(3)) {
            int branches = 1 + random.nextInt(5);
            for (int b = 0; b < branches; b++) {
                float branchAngle = random.nextFloat() * (float) Math.PI * 2.0f;
                int length = MathHelper.nextInt(random, 10, 20);
                int bi = i;
                int bk = k;
                int bj = j1;
                for (int l = 0; l < length; l++) {
                    bi = i + (int) (1.5f + MathHelper.cos(branchAngle) * l);
                    bj = j1 - 3 + l / 2;
                    bk = k + (int) (1.5f + MathHelper.sin(branchAngle) * l);
                    BlockPos p = new BlockPos(bi, bj, bk);
                    if (!replaceable(world, p)) {
                        break;
                    }
                    setBlock(world, p, log);
                }
                spawnLeafLayer(world, bi, bj + 1, bk, 2, leaf);
                spawnLeafLayer(world, bi, bj, bk, 3, leaf);
                spawnLeafLayer(world, bi, bj - 1, bk, 1, leaf);
            }
        }

        // racines
        int roots = MathHelper.nextInt(random, 6, 10);
        for (int l = 0; l < roots; l++) {
            int i1 = i;
            int j1 = j + 1 + random.nextInt(5);
            int k1 = k;
            int xDirection = 0;
            int zDirection = 0;
            int rootLength = 1 + random.nextInt(4);
            if (random.nextBoolean()) {
                if (random.nextBoolean()) {
                    i1 -= TRUNK_WIDTH + 1;
                    xDirection = -1;
                } else {
                    i1 += TRUNK_WIDTH + 1;
                    xDirection = 1;
                }
                k1 = k - TRUNK_WIDTH - 1 + random.nextInt(TRUNK_WIDTH * 2 + 2);
            } else {
                if (random.nextBoolean()) {
                    k1 -= TRUNK_WIDTH + 1;
                    zDirection = -1;
                } else {
                    k1 += TRUNK_WIDTH + 1;
                    zDirection = 1;
                }
                i1 = i - TRUNK_WIDTH - 1 + random.nextInt(TRUNK_WIDTH * 2 + 2);
            }
            for (int l1 = 0; l1 < rootLength; l1++) {
                int rootBlocks = 0;
                int j2 = j1;
                while (j2 > 0 && !world.getBlockState(new BlockPos(i1, j2, k1))
                        .isSolidRender(world, new BlockPos(i1, j2, k1))) {
                    setBlock(world, new BlockPos(i1, j2, k1), log);
                    rootBlocks++;
                    if (rootBlocks > 5) {
                        break;
                    }
                    j2--;
                }
                j1--;
                if (!random.nextBoolean()) {
                    continue;
                }
                if (xDirection == -1) {
                    i1--;
                } else if (xDirection == 1) {
                    i1++;
                } else if (zDirection == -1) {
                    k1--;
                } else {
                    k1++;
                }
            }
        }
        return true;
    }

    /** PORT de spawnLeafCluster : boule de feuilles, bord effrite. */
    private void spawnLeafCluster(ISeedReader world, Random random, int i, int j, int k,
                                  int leafRange, BlockState leaf) {
        int leafRangeSq = leafRange * leafRange;
        int leafRangeSqLess = (int) ((leafRange - 0.5) * (leafRange - 0.5));
        for (int i1 = i - leafRange; i1 <= i + leafRange; i1++) {
            for (int j1 = j - leafRange; j1 <= j + leafRange; j1++) {
                for (int k1 = k - leafRange; k1 <= k + leafRange; k1++) {
                    int i2 = i1 - i;
                    int j2 = j1 - j;
                    int k2 = k1 - k;
                    int dist = i2 * i2 + j2 * j2 + k2 * k2;
                    BlockPos p = new BlockPos(i1, j1, k1);
                    if (dist >= leafRangeSqLess && (dist >= leafRangeSq || random.nextInt(3) != 0)
                            || !replaceable(world, p)) {
                        continue;
                    }
                    setBlock(world, p, leaf);
                }
            }
        }
    }

    /** PORT de spawnLeafLayer : disque de feuilles. */
    private void spawnLeafLayer(ISeedReader world, int i, int j, int k, int leafRange,
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
