package fr.alleretretour.lotr.world.structure;

import com.mojang.serialization.Codec;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.world.gen.feature.NoFeatureConfig;

/**
 * PORT de LOTRWorldGenRangerCamp : le campement des Rodeurs du Nord.
 *
 * Meme disposition que tous les camps (LOTRStructureCampBase) ; seules
 * changent la couleur de la toile et la pierre du foyer, aux valeurs exactes
 * du Legacy.
 */
public class LOTRStructureRangerCamp extends LOTRStructureCampBase {

    public LOTRStructureRangerCamp(Codec<NoFeatureConfig> codec) {
        super(codec);
        tentWool = new BlockState[]{
                Blocks.GREEN_WOOL.defaultBlockState(),
                Blocks.BROWN_WOOL.defaultBlockState(),
                Blocks.LIGHT_GRAY_WOOL.defaultBlockState()};

    }
}
