package fr.alleretretour.lotr.world.structure;

import com.mojang.serialization.Codec;
import net.minecraft.block.Blocks;
import net.minecraft.world.gen.feature.NoFeatureConfig;

/**
 * PORT de LOTRWorldGenRottenHouse : la maison pourrie.
 *
 * Meme trace que la maison en ruine ; seul le bois change - il est POURRI
 * (PORT du constructeur du Legacy). On la trouve dans les marais et les
 * terres humides.
 */
public class LOTRStructureRottenHouse extends LOTRStructureRuinedHouse {

    public LOTRStructureRottenHouse(Codec<NoFeatureConfig> codec) {
        super(codec);
        wood = modBlock("lotr:rotten_log", Blocks.OAK_LOG);
        plank = modBlock("lotr:rotten_planks", Blocks.OAK_PLANKS);
        fence = modBlock("lotr:rotten_fence", Blocks.OAK_FENCE);
        stairs = modBlock("lotr:rotten_stairs", Blocks.OAK_STAIRS);
    }
}
