package fr.alleretretour.lotr.world.biome;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import fr.alleretretour.lotr.init.LOTRBiomes;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.provider.BiomeProvider;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * PORT de LOTRGenLayerWorld : les biomes sont lus dans assets/lotr/map/map.png.
 * Constantes EXACTES du Legacy : origine (810, 730) en pixels, echelle
 * 2^7 = 128 blocs par pixel. Hors carte, on retombe sur les bords (comme le
 * Legacy qui borne les coordonnees a l'image).
 */
public class LOTRBiomeSource extends BiomeProvider {

    public static final Codec<LOTRBiomeSource> CODEC = RecordCodecBuilder.create(instance ->
            instance.group(
                    Codec.LONG.fieldOf("seed").stable().forGetter(source -> source.seed),
                    net.minecraft.util.registry.RegistryLookupCodec.create(Registry.BIOME_REGISTRY).forGetter(source -> source.registry))
                    .apply(instance, instance.stable(LOTRBiomeSource::new)));

    /** PORT : LOTRGenLayerWorld.originX / originZ / scale. */
    public static final int ORIGIN_X = 810;
    public static final int ORIGIN_Z = 730;
    public static final int SCALE = 128;

    private static int[] mapPixels;
    private static int mapWidth;
    private static int mapHeight;

    private final long seed;
    private final Registry<Biome> registry;
    private final Map<Integer, Biome> colorToBiome = new HashMap<>();
    private final Biome fallback;

    public LOTRBiomeSource(long seed, Registry<Biome> registry) {
        super(biomeList(registry));
        this.seed = seed;
        this.registry = registry;
        loadMap();
        LOTRBiomes.MAP_COLORS.forEach((color, ro) -> {
            Biome biome = registry.get(ro.getId());
            if (biome != null) {
                colorToBiome.put(color, biome);
            }
        });
        Biome shire = registry.get(LOTRBiomes.fallback().getId());
        this.fallback = shire != null ? shire : registry.iterator().next();
    }

    private static List<Biome> biomeList(Registry<Biome> registry) {
        List<Biome> list = new ArrayList<>();
        LOTRBiomes.MAP_COLORS.values().forEach(ro -> {
            Biome b = registry.get(ro.getId());
            if (b != null) {
                list.add(b);
            }
        });
        if (list.isEmpty()) {
            registry.forEach(list::add);
        }
        return list;
    }

    private static synchronized void loadMap() {
        if (mapPixels != null) {
            return;
        }
        try (InputStream in = LOTRBiomeSource.class.getResourceAsStream("/assets/lotr/map/map.png")) {
            BufferedImage image = ImageIO.read(in);
            mapWidth = image.getWidth();
            mapHeight = image.getHeight();
            mapPixels = image.getRGB(0, 0, mapWidth, mapHeight, null, 0, mapWidth);
        } catch (Exception e) {
            throw new IllegalStateException("Impossible de charger assets/lotr/map/map.png", e);
        }
    }

    /** PORT du calcul de LOTRGenLayerWorld : bloc -> pixel de la carte. */
    private int colorAt(int blockX, int blockZ) {
        int px = Math.floorDiv(blockX, SCALE) + ORIGIN_X;
        int pz = Math.floorDiv(blockZ, SCALE) + ORIGIN_Z;
        px = net.minecraft.util.math.MathHelper.clamp(px, 0, mapWidth - 1);
        pz = net.minecraft.util.math.MathHelper.clamp(pz, 0, mapHeight - 1);
        return mapPixels[pz * mapWidth + px] & 0xFFFFFF;
    }

    @Override
    protected Codec<? extends BiomeProvider> codec() {
        return CODEC;
    }

    @Override
    public BiomeProvider withSeed(long newSeed) {
        return new LOTRBiomeSource(newSeed, registry);
    }

    @Override
    public Biome getNoiseBiome(int quartX, int quartY, int quartZ) {
        // les coordonnees "quart" valent 4 blocs
        return colorToBiome.getOrDefault(colorAt(quartX << 2, quartZ << 2), fallback);
    }
}
