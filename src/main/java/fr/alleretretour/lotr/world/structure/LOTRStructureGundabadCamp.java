package fr.alleretretour.lotr.world.structure;

import com.mojang.serialization.Codec;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.world.gen.feature.NoFeatureConfig;

/**
 * PORT de LOTRWorldGenGundabadCamp : le campement des orques de Gundabad.
 *
 * Meme disposition que tous les camps (LOTRStructureCampBase) ; seules
 * changent la couleur de la toile et la pierre du foyer, aux valeurs exactes
 * du Legacy.
 */
public class LOTRStructureGundabadCamp extends LOTRStructureCampBase {

    public LOTRStructureGundabadCamp(Codec<NoFeatureConfig> codec) {
        super(codec);
        tentWool = new BlockState[]{
                Blocks.BLACK_WOOL.defaultBlockState(),
                Blocks.BROWN_WOOL.defaultBlockState(),
                Blocks.LIGHT_GRAY_WOOL.defaultBlockState()};
        hearthBlock = modBlock("lotr:mordor_brick", Blocks.COBBLESTONE);
        hearthSlab = modBlock("lotr:mordor_brick_slab", Blocks.COBBLESTONE_SLAB);
    }
}
