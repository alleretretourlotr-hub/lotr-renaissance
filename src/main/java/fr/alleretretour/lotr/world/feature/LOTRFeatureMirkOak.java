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
 * PORT de LOTRWorldGenMirkOak : les chenes de la Foret Noire (noirs et rouges).
 *
 * Algorithme d'origine conserve tel quel :
 *   - hauteur et largeur de tronc parametrees comme dans LOTRTreeType :
 *       MIRK_OAK        4-7,  tronc 1x1
 *       MIRK_OAK_LARGE  12-16, tronc 3x3
 *       RED_OAK         6-9,  tronc 1x1
 *       RED_OAK_LARGE   12-17, tronc 3x3
 *   - tronc plein de (2 x trunkWidth + 1) de cote ;
 *   - si le tronc est large : branches en spirale, l'angle avancant de
 *     (40 + rand(30)) / trunkWidth degres jusqu'au tour complet ; angle
 *     vertical jusqu'a 40 degres, longueur (3 + rand(6)) x (1 + rand(width)),
 *     depart entre height - 5 et height - 1 ; chaque branche se termine par un
 *     houppier. Sinon, un seul houppier au sommet ;
 *   - houppier : 4 couches au-dessus du point, rayon 3 decroissant, filtre
 *     spherique, un bloc sur quatre omis sur l'avant-derniere couronne ;
 *   - racines : 4 + rand(5 x width + 1) racines partant des cotes du tronc,
 *     descendant jusqu'a un bloc opaque (5 blocs maximum par palier) et
 *     s'ecartant une fois sur deux.
 *
 * Les plateformes elfiques (LOTRWorldGenWoodElfPlatform, tronc 3x3 seulement)
 * attendent le portage de ce generateur : elles sont ignorees ici.
 */
public class LOTRFeatureMirkOak extends Feature<NoFeatureConfig> {

    private final int minHeight;
    private final int maxHeight;
    private final int trunkWidth;
    private final String species;

    public LOTRFeatureMirkOak(Codec<NoFeatureConfig> codec, int minHeight, int maxHeight,
                              int trunkWidth, String species) {
        super(codec);
        this.minHeight = minHeight;
        this.maxHeight = maxHeight;
        this.trunkWidth = trunkWidth;
        this.species = species;
    }

    @Override
    public boolean place(ISeedReader world, ChunkGenerator generator, Random random,
                         BlockPos pos, NoFeatureConfig config) {
        int i = pos.getX();
        int j = pos.getY();
        int k = pos.getZ();
        int height = MathHelper.nextInt(random, minHeight, maxHeight);
        if (j < 1 || j + height + 5 > world.getHeight()) {
            return false;
        }
        for (int j1 = j; j1 <= j + height + 5; j1++) {
            int range = trunkWidth + 1;
            if (j1 == j) {
                range = trunkWidth;
            }
            if (j1 >= j + height + 2) {
                range = trunkWidth + 2;
            }
            for (int i1 = i - range; i1 <= i + range; i1++) {
                for (int k1 = k - range; k1 <= k + range; k1++) {
                    if (!replaceable(world, new BlockPos(i1, j1, k1))) {
                        return false;
                    }
                }
            }
        }
        for (int i1 = i - trunkWidth; i1 <= i + trunkWidth; i1++) {
            for (int k1 = k - trunkWidth; k1 <= k + trunkWidth; k1++) {
                BlockPos soil = new BlockPos(i1, j - 1, k1);
                if (!world.getBlockState(soil).canSustainPlant(world, soil, Direction.UP,
                        (net.minecraftforge.common.IPlantable) Blocks.OAK_SAPLING)) {
                    return false;
                }
            }
        }

        BlockState log = log();
        BlockState leaf = leaves();

        for (int j1 = 0; j1 < height; j1++) {
            for (int i1 = i - trunkWidth; i1 <= i + trunkWidth; i1++) {
                for (int k1 = k - trunkWidth; k1 <= k + trunkWidth; k1++) {
                    setBlock(world, new BlockPos(i1, j + j1, k1), log);
                }
            }
        }

        if (trunkWidth >= 1) {
            int deg = 0;
            while (deg < 360) {
                deg += (40 + random.nextInt(30)) / trunkWidth;   // PORT
                float angle = (float) Math.toRadians(deg);
                float cos = MathHelper.cos(angle);
                float sin = MathHelper.sin(angle);
                float angleY = random.nextFloat() * 0.6981317007977318f;  // 40 degres
                float sinY = MathHelper.sin(angleY);
                int length = (3 + random.nextInt(6)) * (1 + random.nextInt(trunkWidth));
                int i1 = i;
                int k1 = k;
                int j1 = j + height - 1 - random.nextInt(5);
                for (int l = 0; l < length; l++) {
                    if (Math.floor(cos * l) != Math.floor(cos * (l - 1))) {
                        i1 += (int) Math.signum(cos);
                    }
                    if (Math.floor(sin * l) != Math.floor(sin * (l - 1))) {
                        k1 += (int) Math.signum(sin);
                    }
                    if (Math.floor(sinY * l) != Math.floor(sinY * (l - 1))) {
                        j1 += (int) Math.signum(sinY);
                    }
                    BlockPos p = new BlockPos(i1, j1, k1);
                    BlockState state = world.getBlockState(p);
                    if (!state.getMaterial().isReplaceable() && !isLeaves(state)
                            && state.getBlock() != log.getBlock()) {
                        break;
                    }
                    setBlock(world, p, log);
                }
                growLeafCanopy(world, random, i1, j1, k1, leaf);
            }
        } else {
            growLeafCanopy(world, random, i, j + height - 1, k, leaf);
        }

        // racines
        int roots = 4 + random.nextInt(5 * trunkWidth + 1);
        for (int l = 0; l < roots; l++) {
            int i1 = i;
            int j1 = j + 1 + random.nextInt(trunkWidth * 2 + 1);
            int k1 = k;
            int xDirection = 0;
            int zDirection = 0;
            int rootLength = 1 + random.nextInt(4);
            if (random.nextBoolean()) {
                if (random.nextBoolean()) {
                    i1 -= trunkWidth + 1;
                    xDirection = -1;
                } else {
                    i1 += trunkWidth + 1;
                    xDirection = 1;
                }
                k1 = k - trunkWidth - 1 + random.nextInt(trunkWidth * 2 + 2);
            } else {
                if (random.nextBoolean()) {
                    k1 -= trunkWidth + 1;
                    zDirection = -1;
                } else {
                    k1 += trunkWidth + 1;
                    zDirection = 1;
                }
                i1 = i - trunkWidth - 1 + random.nextInt(trunkWidth * 2 + 2);
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

    /** PORT de growLeafCanopy. */
    private void growLeafCanopy(ISeedReader world, Random random, int i, int j, int k,
                                BlockState leaf) {
        int leafStart = j + 2;
        int leafTop = j + 5;
        int maxRange = 3;
        for (int j1 = leafStart; j1 <= leafTop; j1++) {
            int leafRange = maxRange - (j1 - leafTop);
            int leafRangeSq = leafRange * leafRange;
            for (int i1 = i - leafRange; i1 <= i + leafRange; i1++) {
                for (int k1 = k - leafRange; k1 <= k + leafRange; k1++) {
                    int i2 = Math.abs(i1 - i);
                    int k2 = Math.abs(k1 - k);
                    boolean grow = i2 * i2 + k2 * k2 < leafRangeSq;
                    if (i2 == leafRange - 1 || k2 == leafRange - 1) {
                        grow = grow && random.nextInt(4) > 0;
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

    private BlockState log() {
        return state("lotr:" + species + "_log", Blocks.DARK_OAK_LOG);
    }

    private BlockState leaves() {
        // le chene rouge utilise les feuilles rouges du mod
        String id = "red_oak".equals(species) ? "lotr:mirk_oak_red_leaves"
                : "lotr:" + species + "_leaves";
        return state(id, Blocks.DARK_OAK_LEAVES);
    }

    private static BlockState state(String id, Block fallback) {
        Block block = ForgeRegistries.BLOCKS.getValue(new ResourceLocation(id));
        return (block == null || block == Blocks.AIR ? fallback : block).defaultBlockState();
    }
}
