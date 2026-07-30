package fr.alleretretour.lotr.init;

import net.minecraft.world.biome.BiomeGenerationSettings;
import net.minecraft.world.biome.DefaultBiomeFeatures;

/**
 * Elements communs a tous les biomes : grottes, ravins, lacs, sources et gel
 * de surface (equivalents vanilla de ce que LOTRChunkProvider generait).
 * Les minerais, eux, sont portes aux valeurs du Legacy dans LOTRBiomeOres.
 */
public final class LOTRBiomeCommon {

    private LOTRBiomeCommon() {
    }

    public static void addBaseFeatures(BiomeGenerationSettings.Builder b) {
        DefaultBiomeFeatures.addDefaultCarvers(b);
        DefaultBiomeFeatures.addDefaultLakes(b);
        DefaultBiomeFeatures.addDefaultSprings(b);
        DefaultBiomeFeatures.addSurfaceFreezing(b);
    }
}
