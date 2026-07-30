package fr.alleretretour.lotr.world.feature;

import com.mojang.serialization.Codec;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
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
 * PORT de LOTRWorldGenBanana : le bananier.
 *
 * Algorithme d'origine conserve tel quel :
 *   - tronc de 1 bloc de large, hauteur 2-4, plante sur height + 2 blocs ;
 *   - quatre bouquets de feuilles, un par direction cardinale : chacun monte
 *     de 1 a 3 blocs (tirage independant par direction) a une case du tronc,
 *     puis deux blocs de feuilles a deux cases, au sommet du bouquet ;
 *   - la generation est annulee si l'un de ces emplacements est occupe (le
 *     Legacy verifie TOUT avant de poser quoi que ce soit).
 * Les regimes de bananes (bloc oriente du Legacy) attendent le portage du bloc.
 */
public class LOTRFeatureBanana extends Feature<NoFeatureConfig> {

    private static final Direction[] CARDINALS = {
            Direction.NORTH, Direction.SOUTH, Direction.WEST, Direction.EAST};

    public LOTRFeatureBanana(Codec<NoFeatureConfig> codec) {
        super(codec);
    }

    @Override
    public boolean place(ISeedReader world, ChunkGenerator generator, Random random,
                         BlockPos pos, NoFeatureConfig config) {
        int i = pos.getX();
        int j = pos.getY();
        int k = pos.getZ();
        int height = 2 + random.nextInt(3);          // PORT
        int[] leaves = new int[4];
        for (int l = 0; l < 4; l++) {
            leaves[l] = 1 + random.nextInt(3);       // PORT : un tirage par direction
        }
        if (j < 1 || j + height + 5 > world.getHeight() || !replaceable(world, pos)) {
            return false;
        }
        BlockPos soil = new BlockPos(i, j - 1, k);
        if (!world.getBlockState(soil).canSustainPlant(world, soil, Direction.UP,
                (net.minecraftforge.common.IPlantable) Blocks.JUNGLE_SAPLING)) {
            return false;
        }
        for (int l = 0; l < height + 2; l++) {
            if (!replaceable(world, new BlockPos(i, j + l, k))) {
                return false;
            }
        }
        // PORT : tout l'espace des bouquets est verifie AVANT la pose
        for (int l = 0; l < 4; l++) {
            Direction dir = CARDINALS[l];
            for (int l1 = -1; l1 < leaves[l]; l1++) {
                if (!replaceable(world, new BlockPos(
                        i + dir.getStepX(), j + height + l1, k + dir.getStepZ()))) {
                    return false;
                }
            }
            for (int l1 = -1; l1 < 1; l1++) {
                if (!replaceable(world, new BlockPos(
                        i + dir.getStepX() * 2, j + height + leaves[l] + l1,
                        k + dir.getStepZ() * 2))) {
                    return false;
                }
            }
        }

        BlockState log = log();
        BlockState leaf = leaves();
        for (int l = 0; l < height + 2; l++) {
            setBlock(world, new BlockPos(i, j + l, k), log);
        }
        for (int l = 0; l < 4; l++) {
            Direction dir = CARDINALS[l];
            for (int l1 = 0; l1 < leaves[l]; l1++) {
                setBlock(world, new BlockPos(
                        i + dir.getStepX(), j + height + l1, k + dir.getStepZ()), leaf);
            }
            for (int l1 = -1; l1 < 1; l1++) {
                setBlock(world, new BlockPos(
                        i + dir.getStepX() * 2, j + height + leaves[l] + l1,
                        k + dir.getStepZ() * 2), leaf);
            }
        }
        return true;
    }

    private static boolean replaceable(ISeedReader world, BlockPos pos) {
        BlockState state = world.getBlockState(pos);
        return state.isAir(world, pos) || state.getMaterial().isReplaceable()
                || state.getBlock() instanceof net.minecraft.block.LeavesBlock;
    }

    private static BlockState log() {
        return state("lotr:banana_log", Blocks.JUNGLE_LOG);
    }

    private static BlockState leaves() {
        return state("lotr:banana_leaves", Blocks.JUNGLE_LEAVES);
    }

    private static BlockState state(String id, Block fallback) {
        Block block = ForgeRegistries.BLOCKS.getValue(new ResourceLocation(id));
        return (block == null || block == Blocks.AIR ? fallback : block).defaultBlockState();
    }
}
