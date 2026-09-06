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
 * PORT de LOTRWorldGenHobbitBurrow : le trou de hobbit.
 *
 * Le trace vient du scan "hobbit_burrow". Comme dans le Legacy, les
 * materiaux sont tires a chaque construction (setupRandomBlocks de
 * LOTRWorldGenHobbitStructure) :
 *
 *   BOIS  : cinq essences equiprobables - pin du Comte, chene, bouleau,
 *           cedre et pommier ;
 *   SOL   : brique, pierre taillee ou pierre de taille ;
 *   BRIQUE: brique cuite, la facade des terriers du Comte ;
 *   TAPIS : quatre teintes possibles.
 *
 * Le garde-manger (LOTRChestContents.HOBBIT_HOLE_LARDER), le lit de paille
 * et les habitants attendent le portage des TileEntity : le terrier est
 * creuse, son mobilier viendra.
 */
public class LOTRStructureHobbitBurrow extends LOTRStructureScanned {

    public LOTRStructureHobbitBurrow(Codec<NoFeatureConfig> codec) {
        super(codec, "hobbit_burrow");

        // cinq essences, comme le Legacy
        aliasOption("PLANK", 1, "lotr:shire_pine_planks", Blocks.OAK_PLANKS);
        aliasOption("PLANK", 1, Blocks.OAK_PLANKS.defaultBlockState());
        aliasOption("PLANK", 1, Blocks.BIRCH_PLANKS.defaultBlockState());
        aliasOption("PLANK", 1, "lotr:cedar_planks", Blocks.OAK_PLANKS);
        aliasOption("PLANK", 1, "lotr:apple_planks", Blocks.OAK_PLANKS);

        aliasOption("PLANK_SLAB", 1, "lotr:shire_pine_slab", Blocks.OAK_SLAB);
        aliasOption("PLANK_SLAB", 1, Blocks.OAK_SLAB.defaultBlockState());
        aliasOption("PLANK_SLAB", 1, Blocks.BIRCH_SLAB.defaultBlockState());
        aliasOption("PLANK_SLAB_INV", 1, Blocks.OAK_SLAB.defaultBlockState()
                .setValue(SlabBlock.TYPE, SlabType.TOP));
        aliasOption("PLANK_SLAB_INV", 1, Blocks.BIRCH_SLAB.defaultBlockState()
                .setValue(SlabBlock.TYPE, SlabType.TOP));
        aliasOption("PLANK_STAIR", 1, "lotr:shire_pine_stairs", Blocks.OAK_STAIRS);
        aliasOption("PLANK_STAIR", 1, Blocks.OAK_STAIRS.defaultBlockState());
        aliasOption("PLANK_STAIR", 1, Blocks.BIRCH_STAIRS.defaultBlockState());
        aliasOption("FENCE", 1, "lotr:shire_pine_fence", Blocks.OAK_FENCE);
        aliasOption("FENCE", 1, Blocks.OAK_FENCE.defaultBlockState());
        aliasOption("FENCE_GATE", 1, "lotr:shire_pine_fence_gate", Blocks.OAK_FENCE_GATE);
        aliasOption("FENCE_GATE", 1, Blocks.OAK_FENCE_GATE.defaultBlockState());
        // TODO : les portes du mod ne sont pas encore portees
        alias("DOOR", Blocks.OAK_DOOR.defaultBlockState());

        // poutres : le Legacy a des blocs de poutre dedies, non portes
        alias("BEAM", Blocks.OAK_WOOD.defaultBlockState());
        alias("BEAM|4", Blocks.OAK_WOOD.defaultBlockState());
        alias("BEAM|8", Blocks.OAK_WOOD.defaultBlockState());

        // sol et facade
        aliasOption("FLOOR", 1, Blocks.BRICKS.defaultBlockState());
        aliasOption("FLOOR", 1, Blocks.COBBLESTONE.defaultBlockState());
        aliasOption("FLOOR", 1, Blocks.STONE_BRICKS.defaultBlockState());
        alias("BRICK", Blocks.BRICKS.defaultBlockState());
        alias("COBBLE_WALL", Blocks.COBBLESTONE_WALL.defaultBlockState());
        alias("LEAF", Blocks.OAK_LEAVES.defaultBlockState());

        // mobilier
        alias("TABLE", Blocks.CRAFTING_TABLE.defaultBlockState());
        aliasOption("CARPET", 1, Blocks.RED_CARPET.defaultBlockState());
        aliasOption("CARPET", 1, Blocks.YELLOW_CARPET.defaultBlockState());
        aliasOption("CARPET", 1, Blocks.GREEN_CARPET.defaultBlockState());
        aliasOption("CARPET", 1, Blocks.BROWN_CARPET.defaultBlockState());
    }

    @Override
    protected boolean canPlace(ISeedReader world, Random random, BlockPos base, int rotation) {
        // le terrier se creuse dans une butte : terrain herbeux et regulier
        int min = 0;
        int max = 0;
        for (int i = -6; i <= 6; i++) {
            for (int k = -6; k <= 6; k++) {
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
