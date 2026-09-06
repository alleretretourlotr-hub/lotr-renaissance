package fr.alleretretour.lotr.world.structure;

import com.mojang.serialization.Codec;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.ISeedReader;
import net.minecraft.world.gen.feature.NoFeatureConfig;

import java.util.Random;

/**
 * PORT de LOTRWorldGenDunlandHillFort : le fort de colline du Dun.
 *
 * Memes materiaux que la maison et la taverne (le Dun batit tout de la meme
 * facon), trace du scan "dunland_fort". Sa vaste emprise tolere un denivele
 * plus important : c'est une forteresse de crete.
 *
 * La garnison et les coffres attendent le portage des TileEntity.
 */
public class LOTRStructureDunlandHillFort extends LOTRStructureDunlendingHouse {

    public LOTRStructureDunlandHillFort(Codec<NoFeatureConfig> codec) {
        super(codec, "dunland_fort");
    }

    @Override
    protected boolean canPlace(ISeedReader world, Random random, BlockPos base, int rotation) {
        int min = 0;
        int max = 0;
        for (int i = -14; i <= 14; i++) {
            for (int k = -14; k <= 14; k++) {
                int y = groundAt(world, base, rotation, i, k) - 1 - base.getY();
                if (!isSurfaceLocal(world, base, rotation, i, y, k)) {
                    return false;
                }
                min = Math.min(min, y);
                max = Math.max(max, y);
                if (max - min > 8) {
                    return false;   // fort de crete : denivele tolere
                }
            }
        }
        return true;
    }
}
