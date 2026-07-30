package fr.alleretretour.lotr.world;

import fr.alleretretour.lotr.world.biome.LOTRBiomeSource;
import net.minecraft.util.RegistryKey;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.World;

/**
 * PORT de LOTRDimension.MIDDLE_EARTH : la dimension de la Terre du Milieu.
 * Le type de dimension et le "level stem" sont declares en JSON
 * (data/lotr/dimension_type/middle_earth.json et data/lotr/dimension/...),
 * comme l'exige 1.16.5 ; cette classe enregistre le codec de la source de
 * biomes et expose la cle du monde.
 */
public final class LOTRDimensions {

    public static final ResourceLocation MIDDLE_EARTH_ID =
            new ResourceLocation("lotr", "middle_earth");

    public static final RegistryKey<World> MIDDLE_EARTH =
            RegistryKey.create(Registry.DIMENSION_REGISTRY, MIDDLE_EARTH_ID);

    private LOTRDimensions() {
    }

    /** A appeler au FMLCommonSetupEvent (les registres de codecs sont vanilla). */
    public static void registerCodecs() {
        Registry.register(Registry.BIOME_SOURCE, MIDDLE_EARTH_ID, LOTRBiomeSource.CODEC);
    }
}
