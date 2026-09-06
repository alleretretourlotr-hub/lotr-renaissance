package fr.alleretretour.lotr.world.structure;

import com.mojang.serialization.Codec;
import net.minecraft.block.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.ISeedReader;
import net.minecraft.world.gen.feature.NoFeatureConfig;

import java.util.Random;

/**
 * PORT de LOTRWorldGenDunlendingTavern : la taverne du Dun.
 *
 * Meme jeu de materiaux que la maison (LOTRWorldGenDunlandStructure) : sol,
 * bois et barreaux tires a chaque construction, toit de chaume. Seul le
 * trace change, il vient du scan "dunland_tavern".
 *
 * L'alias WOOD|4 (bois pose en travers) s'ajoute a ceux de la maison.
 *
 * Le tavernier et ses barriques attendent le portage des TileEntity.
 */
public class LOTRStructureDunlendingTavern extends LOTRStructureDunlendingHouse {

    public LOTRStructureDunlendingTavern(Codec<NoFeatureConfig> codec) {
        super(codec, "dunland_tavern");
        // PORT : la taverne utilise en plus le bois couche
        aliasOption("WOOD|4", 4, Blocks.SPRUCE_WOOD.defaultBlockState());
        aliasOption("WOOD|4", 2, Blocks.OAK_WOOD.defaultBlockState());
        alias("FENCE_GATE", Blocks.SPRUCE_FENCE_GATE.defaultBlockState());
    }

    @Override
    protected boolean canPlace(ISeedReader world, Random random, BlockPos base, int rotation) {
        // emprise plus large que la maison
        int min = 0;
        int max = 0;
        for (int i = -8; i <= 8; i++) {
            for (int k = -8; k <= 8; k++) {
                int y = groundAt(world, base, rotation, i, k) - 1 - base.getY();
                if (!isSurfaceLocal(world, base, rotation, i, y, k)) {
                    return false;
                }
                min = Math.min(min, y);
                max = Math.max(max, y);
                if (max - min > 4) {
                    return false;
                }
            }
        }
        return true;
    }
}
