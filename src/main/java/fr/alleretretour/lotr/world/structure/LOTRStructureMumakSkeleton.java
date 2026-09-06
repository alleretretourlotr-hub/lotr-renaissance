package fr.alleretretour.lotr.world.structure;

import com.mojang.serialization.Codec;
import net.minecraft.block.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.ISeedReader;
import net.minecraft.world.gen.feature.NoFeatureConfig;

import java.util.Random;

/**
 * PORT de LOTRWorldGenMumakSkeleton : la carcasse de mumak dans le desert.
 *
 * Le trace vient du scan "mumak_skeleton" (123 poses d'os). Conditions du
 * Legacy conservees :
 *   - l'aire -3..3 x -3..17 doit etre PLATE : aucune colonne ne doit depasser
 *     le niveau de reference ;
 *   - la carcasse est ensuite enfoncee de 0 a 5 blocs dans le sable, ce qui
 *     donne ces squelettes a demi ensevelis.
 */
public class LOTRStructureMumakSkeleton extends LOTRStructureScanned {

    public LOTRStructureMumakSkeleton(Codec<NoFeatureConfig> codec) {
        super(codec, "mumak_skeleton");
        alias("BONE", "lotr:bone_block", Blocks.BONE_BLOCK);
    }

    @Override
    protected boolean canPlace(ISeedReader world, Random random, BlockPos base, int rotation) {
        for (int i = -3; i <= 3; i++) {
            for (int k = -3; k <= 17; k++) {
                if (groundAt(world, base, rotation, i, k) - 1 - base.getY() < -2) {
                    return false;
                }
            }
        }
        // PORT : la carcasse s'enfonce de 0 a 5 blocs
        yOffset = -random.nextInt(6);
        return true;
    }
}
