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
 * PORT de LOTRWorldGenHaradRuinedFort : le fort en ruine du Proche-Harad.
 *
 * Le trace vient du scan "harad_ruined_fort". Memes ponderations que la
 * pyramide : trois briques lisses pour une fissuree, a chaque pose - ce qui
 * mele les deux teintes et donne l'aspect ronge par le desert.
 *
 * Les coffres a butin attendent le portage des TileEntity.
 */
public class LOTRStructureHaradRuinedFort extends LOTRStructureScanned {

    public LOTRStructureHaradRuinedFort(Codec<NoFeatureConfig> codec) {
        super(codec, "harad_ruined_fort");

        aliasOption("BRICK", 3, "lotr:near_harad_brick", Blocks.SANDSTONE);
        aliasOption("BRICK", 1, "lotr:near_harad_cracked_brick", Blocks.SANDSTONE);
        aliasOption("BRICK_SLAB", 3, "lotr:near_harad_cracked_brick_slab",
                Blocks.SANDSTONE_SLAB);
        aliasOption("BRICK_SLAB", 1, "lotr:near_harad_red_brick_slab", Blocks.SANDSTONE_SLAB);
        aliasOption("BRICK_SLAB_INV", 3,
                modBlock("lotr:near_harad_cracked_brick_slab", Blocks.SANDSTONE_SLAB)
                        .setValue(SlabBlock.TYPE, SlabType.TOP));
        aliasOption("BRICK_SLAB_INV", 1,
                modBlock("lotr:near_harad_red_brick_slab", Blocks.SANDSTONE_SLAB)
                        .setValue(SlabBlock.TYPE, SlabType.TOP));
        aliasOption("BRICK_STAIR", 3, "lotr:near_harad_brick_stairs", Blocks.SANDSTONE_STAIRS);
        aliasOption("BRICK_STAIR", 1, "lotr:near_harad_cracked_brick_stairs",
                Blocks.SANDSTONE_STAIRS);
        aliasOption("BRICK_WALL", 3, "lotr:near_harad_brick_wall", Blocks.SANDSTONE_WALL);
        aliasOption("BRICK_WALL", 1, "lotr:near_harad_cracked_brick_wall", Blocks.SANDSTONE_WALL);
        aliasOption("PILLAR", 4, "lotr:near_harad_pillar", Blocks.SANDSTONE);
    }

    @Override
    protected boolean canPlace(ISeedReader world, Random random, BlockPos base, int rotation) {
        int min = 0;
        int max = 0;
        for (int i = -10; i <= 10; i++) {
            for (int k = -10; k <= 10; k++) {
                int y = groundAt(world, base, rotation, i, k) - 1 - base.getY();
                if (!isSurfaceLocal(world, base, rotation, i, y, k)) {
                    return false;
                }
                min = Math.min(min, y);
                max = Math.max(max, y);
                if (max - min > 5) {
                    return false;
                }
            }
        }
        return true;
    }
}
