package fr.alleretretour.lotr.world.structure;

import com.mojang.serialization.Codec;
import net.minecraft.block.Blocks;
import net.minecraft.block.SlabBlock;
import net.minecraft.state.properties.SlabType;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.ISeedReader;
import net.minecraft.world.gen.feature.NoFeatureConfig;

import java.util.Random;

/**
 * PORT de LOTRWorldGenBlackUrukFort : la forteresse des Uruks noirs.
 *
 * Le trace vient du scan "black_uruk_fort" ; les materiaux sont ceux du
 * Mordor (LOTRWorldGenMordorStructure) : brique noire, pilier, pierre lisse
 * et tuile teintee.
 *
 * L'alias GROUND est pondere comme dans le Legacy - 6 roches pour 2 terres
 * et 2 graviers du Mordor - ce qui donne ces cours au sol inegal.
 *
 * La garnison d'uruks et les coffres attendent le portage des TileEntity.
 */
public class LOTRStructureBlackUrukFort extends LOTRStructureScanned {

    public LOTRStructureBlackUrukFort(Codec<NoFeatureConfig> codec) {
        super(codec, "black_uruk_fort");

        alias("BRICK", "lotr:mordor_brick", Blocks.STONE_BRICKS);
        alias("BRICK_SLAB", "lotr:mordor_brick_slab", Blocks.STONE_BRICK_SLAB);
        alias("BRICK_SLAB_INV", modBlock("lotr:mordor_brick_slab", Blocks.STONE_BRICK_SLAB)
                .setValue(SlabBlock.TYPE, SlabType.TOP));
        alias("BRICK_STAIR", "lotr:mordor_brick_stairs", Blocks.STONE_BRICK_STAIRS);
        alias("BRICK_WALL", "lotr:mordor_brick_wall", Blocks.STONE_BRICK_WALL);
        alias("BRICK_CARVED", "lotr:mordor_carved_brick", Blocks.CHISELED_STONE_BRICKS);
        alias("PILLAR", "lotr:mordor_pillar", Blocks.STONE_BRICKS);

        alias("SMOOTH", Blocks.SMOOTH_STONE.defaultBlockState());
        alias("SMOOTH_SLAB", Blocks.SMOOTH_STONE_SLAB.defaultBlockState());

        alias("TILE", "lotr:clay_tile", Blocks.BLACK_TERRACOTTA);
        alias("TILE_SLAB", "lotr:clay_tile", Blocks.BLACK_TERRACOTTA);
        alias("TILE_SLAB_INV", "lotr:clay_tile", Blocks.BLACK_TERRACOTTA);
        alias("TILE_STAIR", "lotr:clay_tile", Blocks.BLACK_TERRACOTTA);

        alias("PLANK", "lotr:charred_planks", Blocks.DARK_OAK_PLANKS);
        alias("PLANK_SLAB", "lotr:charred_slab", Blocks.DARK_OAK_SLAB);
        alias("PLANK_SLAB_INV", modBlock("lotr:charred_slab", Blocks.DARK_OAK_SLAB)
                .setValue(SlabBlock.TYPE, SlabType.TOP));
        alias("PLANK_STAIR", "lotr:charred_stairs", Blocks.DARK_OAK_STAIRS);
        alias("FENCE", "lotr:charred_fence", Blocks.DARK_OAK_FENCE);
        alias("TRAPDOOR", "lotr:charred_trapdoor", Blocks.DARK_OAK_TRAPDOOR);
        alias("BEAM", "lotr:charred_log", Blocks.DARK_OAK_LOG);
        alias("BEAM|4", "lotr:charred_log", Blocks.DARK_OAK_LOG);
        alias("BEAM|8", "lotr:charred_log", Blocks.DARK_OAK_LOG);
        alias("GATE_IRON", Blocks.IRON_BARS.defaultBlockState());

        // sol des cours : 6 roches, 2 terres, 2 graviers (PORT)
        aliasOption("GROUND", 6, "lotr:mordor_rock", Blocks.STONE);
        aliasOption("GROUND", 2, "lotr:mordor_dirt", Blocks.COARSE_DIRT);
        aliasOption("GROUND", 2, "lotr:mordor_gravel", Blocks.GRAVEL);
    }

    @Override
    protected boolean canPlace(ISeedReader world, Random random, BlockPos base, int rotation) {
        int min = 0;
        int max = 0;
        for (int i = -16; i <= 16; i++) {
            for (int k = -16; k <= 16; k++) {
                int y = groundAt(world, base, rotation, i, k) - 1 - base.getY();
                if (!isSurfaceLocal(world, base, rotation, i, y, k)) {
                    return false;
                }
                min = Math.min(min, y);
                max = Math.max(max, y);
                if (max - min > 6) {
                    return false;
                }
            }
        }
        return true;
    }
}
