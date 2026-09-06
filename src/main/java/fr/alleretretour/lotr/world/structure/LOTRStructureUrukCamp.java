package fr.alleretretour.lotr.world.structure;

import com.mojang.serialization.Codec;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.world.gen.feature.NoFeatureConfig;

/**
 * PORT de LOTRWorldGenUrukCamp : le campement des Uruk-hai d'Isengard.
 *
 * Meme disposition que tous les camps (LOTRStructureCampBase) ; seules
 * changent la couleur de la toile et la pierre du foyer, aux valeurs exactes
 * du Legacy.
 */
public class LOTRStructureUrukCamp extends LOTRStructureCampBase {

    public LOTRStructureUrukCamp(Codec<NoFeatureConfig> codec) {
        super(codec);
        tentWool = new BlockState[]{
                Blocks.BLACK_WOOL.defaultBlockState(),
                Blocks.BROWN_WOOL.defaultBlockState(),
                Blocks.LIGHT_GRAY_WOOL.defaultBlockState()};
        hearthBlock = modBlock("lotr:uruk_brick", Blocks.COBBLESTONE);
        hearthSlab = modBlock("lotr:uruk_brick_slab", Blocks.COBBLESTONE_SLAB);
    }
}
