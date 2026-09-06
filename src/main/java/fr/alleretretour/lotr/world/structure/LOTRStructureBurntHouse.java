package fr.alleretretour.lotr.world.structure;

import com.mojang.serialization.Codec;
import net.minecraft.block.Blocks;
import net.minecraft.world.gen.feature.NoFeatureConfig;

/**
 * PORT de LOTRWorldGenBurntHouse : la maison brulee.
 *
 * Meme trace que la maison en ruine, mais tout est CALCINE : bois carbonise
 * et pierre roussie. Les terres devastees du Mordor et de l'Isengard en sont
 * parsemees.
 */
public class LOTRStructureBurntHouse extends LOTRStructureRuinedHouse {

    public LOTRStructureBurntHouse(Codec<NoFeatureConfig> codec) {
        super(codec);
        wood = modBlock("lotr:charred_log", Blocks.DARK_OAK_LOG);
        plank = modBlock("lotr:charred_planks", Blocks.DARK_OAK_PLANKS);
        fence = modBlock("lotr:charred_fence", Blocks.DARK_OAK_FENCE);
        stairs = modBlock("lotr:charred_stairs", Blocks.DARK_OAK_STAIRS);
        stone = modBlock("lotr:scorched_stone", Blocks.BLACKSTONE);
        stoneVariant = modBlock("lotr:scorched_stone", Blocks.BLACKSTONE);
    }
}
