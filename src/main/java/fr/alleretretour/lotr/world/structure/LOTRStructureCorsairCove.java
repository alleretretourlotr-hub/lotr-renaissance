package fr.alleretretour.lotr.world.structure;

import com.mojang.serialization.Codec;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.SlabBlock;
import net.minecraft.state.properties.SlabType;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.ISeedReader;
import net.minecraft.world.gen.feature.NoFeatureConfig;

import java.util.Random;

/**
 * PORT de LOTRWorldGenCorsairCove : la crique des corsaires.
 *
 * Le trace vient du scan "corsair_cove". Materiaux d'UMBAR
 * (LOTRWorldGenCorsairStructure) : brique d'Umbar, piliers et bois de
 * paletuvier - le bois des cotes du Sud.
 *
 * Conditions du Legacy conservees :
 *   - emprise ASYMETRIQUE (-15..9 en X, -1..12 en Z) : la crique s'ouvre
 *     d'un cote, vers la mer ;
 *   - le sol doit etre naturel, de pierre ou de gres, avec un denivele
 *     maximal de 8 ;
 *   - l'espace du bassin (-14..4 x 0..7, hauteurs 1 a 9) est degage avant
 *     la pose : c'est le mouillage des navires.
 *
 * Le butin et l'equipage attendent le portage des TileEntity.
 */
public class LOTRStructureCorsairCove extends LOTRStructureScanned {

    public LOTRStructureCorsairCove(Codec<NoFeatureConfig> codec) {
        super(codec, "corsair_cove");

        alias("BRICK", "lotr:umbar_brick", Blocks.STONE_BRICKS);
        alias("BRICK_SLAB", "lotr:umbar_brick_slab", Blocks.STONE_BRICK_SLAB);
        alias("BRICK_SLAB_INV", modBlock("lotr:umbar_brick_slab", Blocks.STONE_BRICK_SLAB)
                .setValue(SlabBlock.TYPE, SlabType.TOP));
        alias("BRICK_STAIR", "lotr:umbar_brick_stairs", Blocks.STONE_BRICK_STAIRS);
        alias("BRICK_WALL", "lotr:umbar_brick_wall", Blocks.STONE_BRICK_WALL);
        alias("PILLAR", "lotr:umbar_pillar", Blocks.STONE_BRICKS);
        alias("PILLAR_SLAB", "lotr:umbar_pillar_slab", Blocks.STONE_BRICK_SLAB);

        // bois de paletuvier, celui des cotes du Sud
        alias("PLANK", "lotr:mangrove_planks", Blocks.JUNGLE_PLANKS);
        alias("PLANK_SLAB", "lotr:mangrove_slab", Blocks.JUNGLE_SLAB);
        alias("PLANK_SLAB_INV", modBlock("lotr:mangrove_slab", Blocks.JUNGLE_SLAB)
                .setValue(SlabBlock.TYPE, SlabType.TOP));
        alias("PLANK_STAIR", "lotr:mangrove_stairs", Blocks.JUNGLE_STAIRS);
        alias("FENCE", "lotr:mangrove_fence", Blocks.JUNGLE_FENCE);
        alias("FENCE_GATE", "lotr:mangrove_fence_gate", Blocks.JUNGLE_FENCE_GATE);
        alias("WOOD", "lotr:mangrove_log", Blocks.JUNGLE_LOG);
        alias("TRAPDOOR", "lotr:mangrove_trapdoor", Blocks.JUNGLE_TRAPDOOR);
        // TODO : les portes du mod ne sont pas encore portees
        alias("DOOR", Blocks.JUNGLE_DOOR.defaultBlockState());
    }

    @Override
    protected boolean canPlace(ISeedReader world, Random random, BlockPos base, int rotation) {
        int min = 0;
        int max = 0;
        // PORT : emprise asymetrique, la crique s'ouvre vers la mer
        for (int i = -15; i <= 9; i++) {
            for (int k = -1; k <= 12; k++) {
                int y = groundAt(world, base, rotation, i, k) - 1 - base.getY();
                if (!isSurfaceLocal(world, base, rotation, i, y, k)) {
                    Block b = world.getBlockState(rotate(base, rotation, i, y, k)).getBlock();
                    if (b != Blocks.STONE && b != Blocks.SANDSTONE) {
                        return false;
                    }
                }
                min = Math.min(min, y);
                max = Math.max(max, y);
                if (max - min > 8) {
                    return false;
                }
            }
        }
        return true;
    }

    @Override
    protected boolean generateWithRotation(ISeedReader world, Random random, BlockPos origin,
                                           int rotation) {
        // PORT : degagement du bassin avant la pose
        BlockState air = Blocks.AIR.defaultBlockState();
        for (int i = -14; i <= 4; i++) {
            for (int k = 0; k <= 7; k++) {
                for (int j = 1; j <= 9; j++) {
                    setBlockRotated(world, origin, rotation, i, j, k, air);
                }
            }
        }
        return super.generateWithRotation(world, random, origin, rotation);
    }
}
