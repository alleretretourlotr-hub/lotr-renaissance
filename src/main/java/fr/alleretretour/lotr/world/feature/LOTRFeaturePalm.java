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
 * PORT de LOTRWorldGenPalm : le palmier-dattier.
 *
 * Algorithme d'origine conserve tel quel :
 *   - hauteur 5-8, tronc d'un bloc de large qui SERPENTE : direction tiree une
 *     fois (angle), puis jusqu'a 3 decalages d'une case, uniquement entre le
 *     tiers et l'avant-dernier niveau, et seulement si la composante depasse
 *     un seuil tire entre 0,25 et 0,5 ;
 *   - palmes : on tourne autour du tronc par pas de 15 a 29 degres jusqu'a
 *     360 ; chaque palme part du sommet, longue de 5, monte selon un angle
 *     vertical tire entre 0 et 30 degres, avance d'une case en X ou en Z selon
 *     laquelle des deux composantes progresse le plus ; le premier bloc est du
 *     bois, les suivants des feuilles.
 * Les dattes (bloc a orientation du Legacy) attendent le portage du bloc.
 */
public class LOTRFeaturePalm extends Feature<NoFeatureConfig> {

    private static final int MIN_HEIGHT = 5;   // PORT
    private static final int MAX_HEIGHT = 8;   // PORT

    public LOTRFeaturePalm(Codec<NoFeatureConfig> codec) {
        super(codec);
    }

    @Override
    public boolean place(ISeedReader world, ChunkGenerator generator, Random random,
                         BlockPos pos, NoFeatureConfig config) {
        int i = pos.getX();
        int j = pos.getY();
        int k = pos.getZ();
        int height = MathHelper.nextInt(random, MIN_HEIGHT, MAX_HEIGHT);
        if (j < 1 || j + height + 2 > world.getHeight() || !replaceable(world, pos)) {
            return false;
        }
        BlockPos soil = new BlockPos(i, j - 1, k);
        if (!world.getBlockState(soil).canSustainPlant(world, soil,
                net.minecraft.util.Direction.UP,
                (net.minecraftforge.common.IPlantable) Blocks.OAK_SAPLING)) {
            return false;
        }
        for (int l = 1; l < height + 2; l++) {
            for (int i1 = i - 1; i1 <= i + 1; i1++) {
                for (int k1 = k - 1; k1 <= k + 1; k1++) {
                    if (!replaceable(world, new BlockPos(i1, j + l, k1))) {
                        return false;
                    }
                }
            }
        }

        BlockState log = log();
        BlockState leaf = leaves();

        float trunkAngle = 6.2831855f * random.nextFloat();
        float trunkSin = MathHelper.sin(trunkAngle);
        float trunkCos = MathHelper.cos(trunkAngle);
        int trunkX = i;
        int trunkZ = k;
        int trunkSwitches = 0;
        int trunkSwitchesMax = MathHelper.nextInt(random, 0, 3);   // PORT
        for (int l = 0; l < height; l++) {
            setBlock(world, new BlockPos(trunkX, j + l, trunkZ), log);
            if (l <= height / 3 || l >= height - 1 || trunkSwitches >= trunkSwitchesMax
                    || !random.nextBoolean()) {
                continue;
            }
            trunkSwitches++;
            if (Math.abs(trunkCos) >= nextDouble(random, 0.25, 0.5)) {
                trunkX += (int) Math.signum(trunkCos);
            }
            if (Math.abs(trunkSin) < nextDouble(random, 0.25, 0.5)) {
                continue;
            }
            trunkZ += (int) Math.signum(trunkSin);
        }

        // palmes : tour complet par pas de 15 a 29 degres
        int leafAngle = 0;
        while (leafAngle < 360) {
            leafAngle += 15 + random.nextInt(15);
            float angleR = (float) Math.toRadians(leafAngle);
            float sin = MathHelper.sin(angleR);
            float cos = MathHelper.cos(angleR);
            float angleY = random.nextFloat() * 0.5235987755982988f;   // PORT : 30 degres
            float sinY = MathHelper.sin(angleY);
            int i1 = trunkX;
            int j1 = j + height - 1;
            int k1 = trunkZ;
            for (int l = 1; l <= 5; l++) {
                if (Math.floor(sinY * l) == Math.floor(sinY * (l - 1))) {
                    double dCos = Math.abs(Math.floor(Math.abs(cos * l))
                            - Math.floor(Math.abs(cos * (l - 1))));
                    double dSin = Math.abs(Math.floor(Math.abs(sin * l))
                            - Math.floor(Math.abs(sin * (l - 1))));
                    boolean cosOrSin = dCos == dSin ? random.nextBoolean() : dCos > dSin;
                    if (cosOrSin) {
                        i1 += (int) Math.signum(cos);
                    } else {
                        k1 += (int) Math.signum(sin);
                    }
                } else {
                    j1 += (int) Math.signum(sinY);
                }
                BlockPos p = new BlockPos(i1, j1, k1);
                BlockState state = world.getBlockState(p);
                boolean replacingWood = state.getBlock() == log.getBlock();
                if (!state.getMaterial().isReplaceable() && !isLeaves(state) && !replacingWood) {
                    break;
                }
                if (l == 1) {
                    setBlock(world, p, log);
                } else if (!replacingWood) {
                    setBlock(world, p, leaf);
                }
            }
        }
        return true;
    }

    private static double nextDouble(Random random, double min, double max) {
        return min + random.nextDouble() * (max - min);
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
        return state("lotr:date_palm_log", Blocks.JUNGLE_LOG);
    }

    private static BlockState leaves() {
        return state("lotr:date_palm_leaves", Blocks.JUNGLE_LEAVES);
    }

    private static BlockState state(String id, Block fallback) {
        Block block = ForgeRegistries.BLOCKS.getValue(new ResourceLocation(id));
        return (block == null || block == Blocks.AIR ? fallback : block).defaultBlockState();
    }
}
