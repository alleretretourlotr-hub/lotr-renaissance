package fr.alleretretour.lotr.init;

import fr.alleretretour.lotr.LOTRMod;
import net.minecraft.util.RegistryKey;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.BiomeAmbience;
import net.minecraft.world.biome.BiomeGenerationSettings;
import net.minecraft.world.biome.MobSpawnInfo;
import net.minecraft.world.gen.GenerationStage;
import net.minecraft.world.gen.surfacebuilders.ConfiguredSurfaceBuilders;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * PORT des 169 biomes de la Terre du Milieu (lotr.common.world.biome.LOTRBiome).
 * Temperature, pluviometrie, profondeur et variation reprises telles quelles ;
 * MAP_COLORS reproduit la table couleur -> biome utilisee par map.png.
 * La decoration (arbres, minerais, structures) viendra dans les lots suivants.
 */
public final class LOTRBiomes {

    public static final DeferredRegister<Biome> BIOMES =
            DeferredRegister.create(ForgeRegistries.BIOMES, LOTRMod.MOD_ID);

    /** couleur de la carte (RGB) -> biome, PORT de colorsToBiomeIDs. */
    public static final Map<Integer, RegistryObject<Biome>> MAP_COLORS = new LinkedHashMap<>();

    private LOTRBiomes() {
    }

    public static void register(IEventBus modBus) {
        BIOMES.register(modBus);
    }

    /** Surface par defaut (herbe/terre), comme la majorite des biomes du Legacy. */
    private static final java.util.function.Supplier<net.minecraft.world.gen.surfacebuilders.ConfiguredSurfaceBuilder<
            net.minecraft.world.gen.surfacebuilders.SurfaceBuilderConfig>> GRASS_SURFACE =
            () -> ConfiguredSurfaceBuilders.GRASS;

    /**
     * PORT des champs topBlock / fillerBlock du biome Legacy.
     * Les blocs propres au mod sont resolus par nom : si le bloc n'est pas
     * encore porte, on retombe sur l'equivalent vanilla indique.
     */
    private static java.util.function.Supplier<net.minecraft.world.gen.surfacebuilders.ConfiguredSurfaceBuilder<
            net.minecraft.world.gen.surfacebuilders.SurfaceBuilderConfig>> surface(
            String topId, String topFallback, String fillerId, String fillerFallback) {
        return () -> new net.minecraft.world.gen.surfacebuilders.ConfiguredSurfaceBuilder<>(
                net.minecraft.world.gen.surfacebuilders.SurfaceBuilder.DEFAULT,
                new net.minecraft.world.gen.surfacebuilders.SurfaceBuilderConfig(
                        state(topId, topFallback), state(fillerId, fillerFallback),
                        state(fillerId, fillerFallback)));
    }

    private static net.minecraft.block.BlockState state(String id, String fallback) {
        net.minecraft.block.Block block = ForgeRegistries.BLOCKS
                .getValue(new ResourceLocation(id));
        if (block == null || block == net.minecraft.block.Blocks.AIR) {
            block = ForgeRegistries.BLOCKS.getValue(new ResourceLocation(fallback));
        }
        return block == null ? net.minecraft.block.Blocks.STONE.defaultBlockState()
                : block.defaultBlockState();
    }

    private static RegistryObject<Biome> register(String name, float depth, float scale,
                                                  float temperature, float downfall,
                                                  Biome.Category category, int mapColor,
                                                  java.util.function.Supplier<
                                                          net.minecraft.world.gen.surfacebuilders.ConfiguredSurfaceBuilder<
                                                                  net.minecraft.world.gen.surfacebuilders.SurfaceBuilderConfig>> surfaceBuilder,
                                                  int[] colors) {
        RegistryObject<Biome> ro = BIOMES.register(name, () -> new Biome.Builder()
                .precipitation(temperature <= 0.15f ? Biome.RainType.SNOW : Biome.RainType.RAIN)
                .biomeCategory(category)
                .depth(depth)
                .scale(scale)
                .temperature(temperature)
                .downfall(downfall)
                .specialEffects(ambience(temperature, colors))
                .mobSpawnSettings(spawns(name))
                .generationSettings(generation(name, surfaceBuilder.get()))
                .build());
        return ro;
    }

    /** PNJ pouvant apparaitre dans ce biome (PORT des npcSpawnList). */
    private static MobSpawnInfo spawns(String name) {
        MobSpawnInfo.Builder b = new MobSpawnInfo.Builder();
        LOTRBiomeSpawns.apply(name, b);
        return b.build();
    }

    /** Reglages de generation : surface + decoration Legacy du biome. */
    private static BiomeGenerationSettings generation(String name,
            net.minecraft.world.gen.surfacebuilders.ConfiguredSurfaceBuilder<
                    net.minecraft.world.gen.surfacebuilders.SurfaceBuilderConfig> sb) {
        BiomeGenerationSettings.Builder b = new BiomeGenerationSettings.Builder()
                .surfaceBuilder(sb);
        LOTRBiomeCommon.addBaseFeatures(b);   // grottes / lacs / sources
        LOTRBiomeOres.apply(name, b);          // minerais aux taux du Legacy
        // les routes sont ajoutees au chargement des biomes (LOTRBiomeEvents) :
        // les features configurees n'existent pas encore a l'enregistrement
        LOTRBiomeDecoration.apply(name, b);
        return b.build();
    }

    /**
     * PORT de LOTRBiomeColors : herbe, feuillage, eau, ciel et brouillard
     * surcharges par certains biomes ({grass, foliage, water, sky, fog},
     * -1 = valeur par defaut).
     */
    private static BiomeAmbience ambience(float temperature, int[] colors) {
        BiomeAmbience.Builder b = new BiomeAmbience.Builder()
                .waterColor(colors != null && colors[2] >= 0 ? colors[2] : 0x3F76E4)
                .waterFogColor(colors != null && colors[2] >= 0 ? colors[2] : 0x050533)
                .fogColor(colors != null && colors[4] >= 0 ? colors[4] : 0xC0D8FF)
                .skyColor(colors != null && colors[3] >= 0 ? colors[3] : skyColor(temperature));
        if (colors != null && colors[0] >= 0) {
            b.grassColorOverride(colors[0]);
        }
        if (colors != null && colors[1] >= 0) {
            b.foliageColorOverride(colors[1]);
        }
        return b.build();
    }

    /** Formule vanilla de la couleur de ciel selon la temperature. */
    private static int skyColor(float temperature) {
        float f = net.minecraft.util.math.MathHelper.clamp(temperature / 3.0f, -1.0f, 1.0f);
        return net.minecraft.util.math.MathHelper.hsvToRgb(0.62222224f - f * 0.05f, 0.5f + f * 0.1f, 1.0f);
    }

    public static RegistryKey<Biome> key(RegistryObject<Biome> ro) {
        return RegistryKey.create(Registry.BIOME_REGISTRY, ro.getId());
    }

    /** Biome par defaut si une couleur de la carte est inconnue. */
    public static RegistryObject<Biome> fallback() {
        return SHIRE;
    }

    public static final RegistryObject<Biome> RIVER = register("river",
            -0.3f, 0.0f, 0.8f, 0.4f, Biome.Category.RIVER,
            0x367CB5, GRASS_SURFACE, null);
    public static final RegistryObject<Biome> ROHAN = register("rohan",
            0.4f, 0.075f, 0.8f, 0.8f, Biome.Category.PLAINS,
            0x70AD45, GRASS_SURFACE, null);
    public static final RegistryObject<Biome> MISTY_MOUNTAINS = register("misty_mountains",
            2.2f, 1.0f, 0.2f, 0.5f, Biome.Category.EXTREME_HILLS,
            0xE8E7E1, GRASS_SURFACE, new int[]{-1, -1, -1, 0xBACBD1, -1});
    public static final RegistryObject<Biome> SHIRE = register("shire",
            0.35f, 0.15f, 0.8f, 0.9f, Biome.Category.PLAINS,
            0x67AD35, GRASS_SURFACE, new int[]{0x7BC421, -1, -1, -1, -1});
    public static final RegistryObject<Biome> SHIRE_WOODLANDS = register("shire_woodlands",
            0.5f, 0.25f, 0.8f, 0.9f, Biome.Category.FOREST,
            0x447736, GRASS_SURFACE, null);
    public static final RegistryObject<Biome> MORDOR = register("mordor",
            0.5f, 0.25f, 2.0f, 0.0f, Biome.Category.PLAINS,
            0x11100E, surface("lotr:rock", "minecraft:stone", "lotr:rock", "minecraft:stone"), new int[]{0x5B412B, 0x634F2D, 0x26211D, 0x663E33, 0x302317});
    public static final RegistryObject<Biome> MORDOR_MOUNTAINS = register("mordor_mountains",
            2.2f, 1.5f, 2.0f, 0.0f, Biome.Category.EXTREME_HILLS,
            0x514D48, GRASS_SURFACE, null);
    public static final RegistryObject<Biome> GONDOR = register("gondor",
            0.3f, 0.075f, 0.8f, 0.8f, Biome.Category.PLAINS,
            0x88B445, GRASS_SURFACE, null);
    public static final RegistryObject<Biome> WHITE_MOUNTAINS = register("white_mountains",
            1.7f, 1.0f, 0.6f, 0.8f, Biome.Category.EXTREME_HILLS,
            0xE5E5E8, GRASS_SURFACE, null);
    public static final RegistryObject<Biome> LOTHLORIEN = register("lothlorien",
            0.3f, 0.15f, 0.9f, 1.0f, Biome.Category.FOREST,
            0xFBD83F, GRASS_SURFACE, new int[]{0xAFE51B, -1, -1, -1, 0xFFE664});
    public static final RegistryObject<Biome> CELEBRANT = register("celebrant",
            0.3f, 0.025f, 1.1f, 1.0f, Biome.Category.PLAINS,
            0x74AF46, GRASS_SURFACE, new int[]{0x94CD2C, -1, -1, -1, -1});
    public static final RegistryObject<Biome> IRON_HILLS = register("iron_hills",
            0.5f, 0.7f, 0.27f, 0.4f, Biome.Category.PLAINS,
            0x8B7F4D, GRASS_SURFACE, null);
    public static final RegistryObject<Biome> DEAD_MARSHES = register("dead_marshes",
            0.2f, 0.05f, 0.4f, 1.0f, Biome.Category.SWAMP,
            0x6F733F, GRASS_SURFACE, new int[]{0x7F644F, -1, 0x14160F, 0x565332, 0x404024});
    public static final RegistryObject<Biome> TROLLSHAWS = register("trollshaws",
            0.35f, 0.5f, 0.6f, 0.8f, Biome.Category.PLAINS,
            0x587C2F, GRASS_SURFACE, null);
    public static final RegistryObject<Biome> WOODLAND_REALM = register("woodland_realm",
            0.4f, 0.15f, 0.8f, 0.9f, Biome.Category.FOREST,
            0x3E6526, GRASS_SURFACE, null);
    public static final RegistryObject<Biome> MIRKWOOD_CORRUPTED = register("mirkwood_corrupted",
            0.4f, 0.2f, 0.6f, 0.8f, Biome.Category.FOREST,
            0x2E441B, GRASS_SURFACE, new int[]{0x2B5B25, 0x263325, 0x1A1326, -1, 0x32647D});
    public static final RegistryObject<Biome> ROHAN_URUK_HIGHLANDS = register("rohan_uruk_highlands",
            1.0f, 0.15f, 0.7f, 0.4f, Biome.Category.PLAINS,
            0x7E935A, GRASS_SURFACE, null);
    public static final RegistryObject<Biome> EMYN_MUIL = register("emyn_muil",
            0.4f, 0.4f, 0.5f, 0.9f, Biome.Category.PLAINS,
            0x968C72, surface("minecraft:stone", "minecraft:stone", "minecraft:stone", "minecraft:stone"), new int[]{0x919161, -1, -1, 0x989994, -1});
    public static final RegistryObject<Biome> ITHILIEN = register("ithilien",
            0.35f, 0.25f, 0.9f, 0.9f, Biome.Category.PLAINS,
            0x75A734, GRASS_SURFACE, null);
    public static final RegistryObject<Biome> PELARGIR = register("pelargir",
            0.28f, 0.1f, 1.0f, 1.0f, Biome.Category.PLAINS,
            0xABC151, surface("lotr:white_sand", "minecraft:sand", "lotr:white_sand", "minecraft:sand"), null);
    public static final RegistryObject<Biome> LONE_LANDS = register("lone_lands",
            0.35f, 0.2f, 0.6f, 0.5f, Biome.Category.PLAINS,
            0x82A84A, GRASS_SURFACE, new int[]{0xC4BA64, -1, -1, -1, -1});
    public static final RegistryObject<Biome> LONE_LANDS_HILLS = register("lone_lands_hills",
            0.8f, 0.4f, 0.6f, 0.5f, Biome.Category.PLAINS,
            0x848E4E, GRASS_SURFACE, null);
    public static final RegistryObject<Biome> DUNLAND = register("dunland",
            0.5f, 0.25f, 0.4f, 0.7f, Biome.Category.PLAINS,
            0x69994C, GRASS_SURFACE, null);
    public static final RegistryObject<Biome> FANGORN = register("fangorn",
            0.4f, 0.2f, 0.7f, 0.8f, Biome.Category.FOREST,
            0x427519, GRASS_SURFACE, new int[]{-1, -1, -1, 0x76A072, 0x327D4B});
    public static final RegistryObject<Biome> ANGLE = register("angle",
            0.35f, 0.15f, 0.6f, 0.8f, Biome.Category.PLAINS,
            0x8FAF4F, GRASS_SURFACE, null);
    public static final RegistryObject<Biome> ETTENMOORS = register("ettenmoors",
            0.7f, 0.3f, 0.2f, 0.6f, Biome.Category.PLAINS,
            0x7C895A, GRASS_SURFACE, null);
    public static final RegistryObject<Biome> OLD_FOREST = register("old_forest",
            0.4f, 0.15f, 0.5f, 1.0f, Biome.Category.FOREST,
            0x45753B, GRASS_SURFACE, new int[]{0x47823E, 0x30682A, -1, -1, 0x193219});
    public static final RegistryObject<Biome> HARONDOR = register("harondor",
            0.4f, 0.15f, 1.0f, 0.6f, Biome.Category.PLAINS,
            0xA2B546, surface("minecraft:sand", "minecraft:sand", "minecraft:sand", "minecraft:sand"), null);
    public static final RegistryObject<Biome> ERIADOR = register("eriador",
            0.3f, 0.2f, 0.9f, 0.8f, Biome.Category.PLAINS,
            0x6BA644, GRASS_SURFACE, null);
    public static final RegistryObject<Biome> ERIADOR_DOWNS = register("eriador_downs",
            0.7f, 0.25f, 0.6f, 0.7f, Biome.Category.PLAINS,
            0x748C47, GRASS_SURFACE, null);
    public static final RegistryObject<Biome> ERYN_VORN = register("eryn_vorn",
            0.3f, 0.2f, 0.8f, 0.9f, Biome.Category.PLAINS,
            0x427F4D, GRASS_SURFACE, null);
    public static final RegistryObject<Biome> GREY_MOUNTAINS = register("grey_mountains",
            2.0f, 1.0f, 0.28f, 0.2f, Biome.Category.EXTREME_HILLS,
            0xCACCC1, GRASS_SURFACE, new int[]{-1, -1, -1, 0xA5C0CE, -1});
    public static final RegistryObject<Biome> MIDGEWATER = register("midgewater",
            0.2f, 0.05f, 0.6f, 1.0f, Biome.Category.PLAINS,
            0x5B9357, GRASS_SURFACE, new int[]{0x82824C, 0x826A3B, 0x564F3C, -1, -1});
    public static final RegistryObject<Biome> BROWN_LANDS = register("brown_lands",
            0.4f, 0.1f, 1.0f, 0.2f, Biome.Category.PLAINS,
            0x827E50, surface("minecraft:dirt", "minecraft:dirt", "minecraft:dirt", "minecraft:dirt"), new int[]{0xAD8B69, -1, -1, 0x877962, -1});
    public static final RegistryObject<Biome> OCEAN = register("ocean",
            -0.8f, 0.15f, 0.8f, 0.8f, Biome.Category.OCEAN,
            0x02598D, GRASS_SURFACE, null);
    public static final RegistryObject<Biome> ANDUIN_HILLS = register("anduin_hills",
            0.8f, 0.2f, 0.7f, 0.7f, Biome.Category.PLAINS,
            0x6BB25C, GRASS_SURFACE, null);
    public static final RegistryObject<Biome> MENELTARMA = register("meneltarma",
            0.3f, 0.1f, 0.9f, 0.8f, Biome.Category.PLAINS,
            0x91B75A, surface("minecraft:stone", "minecraft:stone", "minecraft:stone", "minecraft:stone"), null);
    public static final RegistryObject<Biome> GLADDEN_FIELDS = register("gladden_fields",
            0.2f, 0.05f, 0.6f, 1.0f, Biome.Category.PLAINS,
            0x4C9B59, GRASS_SURFACE, null);
    public static final RegistryObject<Biome> LOTHLORIEN_EDGE = register("lothlorien_edge",
            0.3f, 0.1f, 0.9f, 1.0f, Biome.Category.FOREST,
            0xD4C643, GRASS_SURFACE, null);
    public static final RegistryObject<Biome> FORODWAITH = register("forodwaith",
            0.3f, 0.05f, 0.0f, 0.2f, Biome.Category.TAIGA,
            0xD8D8D2, surface("minecraft:snow_block", "minecraft:snow_block", "minecraft:snow_block", "minecraft:snow_block"), new int[]{-1, -1, -1, 0x99A4A8, -1});
    public static final RegistryObject<Biome> ENEDWAITH = register("enedwaith",
            0.4f, 0.15f, 0.6f, 0.8f, Biome.Category.PLAINS,
            0x7AA84F, GRASS_SURFACE, null);
    public static final RegistryObject<Biome> ANGMAR = register("angmar",
            0.4f, 0.3f, 0.2f, 0.2f, Biome.Category.PLAINS,
            0x54472F, surface("minecraft:stone", "minecraft:stone", "minecraft:stone", "minecraft:stone"), new int[]{0x787C57, -1, -1, 0x513F33, 0x191919});
    public static final RegistryObject<Biome> EREGION = register("eregion",
            0.4f, 0.15f, 0.6f, 0.7f, Biome.Category.PLAINS,
            0x659048, GRASS_SURFACE, null);
    public static final RegistryObject<Biome> LINDON = register("lindon",
            0.35f, 0.1f, 0.9f, 0.9f, Biome.Category.PLAINS,
            0x74AD45, GRASS_SURFACE, null);
    public static final RegistryObject<Biome> LINDON_WOODLANDS = register("lindon_woodlands",
            0.4f, 0.25f, 0.9f, 1.0f, Biome.Category.FOREST,
            0x1E772F, GRASS_SURFACE, null);
    public static final RegistryObject<Biome> EAST_BIGHT = register("east_bight",
            0.35f, 0.025f, 0.8f, 0.3f, Biome.Category.PLAINS,
            0x8A955D, surface("minecraft:dirt", "minecraft:dirt", "minecraft:dirt", "minecraft:dirt"), null);
    public static final RegistryObject<Biome> BLUE_MOUNTAINS = register("blue_mountains",
            1.2f, 1.25f, 0.22f, 0.8f, Biome.Category.EXTREME_HILLS,
            0xC9DAE2, GRASS_SURFACE, new int[]{-1, -1, -1, 0x7289F9, -1});
    public static final RegistryObject<Biome> MIRKWOOD_MOUNTAINS = register("mirkwood_mountains",
            1.4f, 0.75f, 0.28f, 0.9f, Biome.Category.EXTREME_HILLS,
            0x282D1D, GRASS_SURFACE, null);
    public static final RegistryObject<Biome> WILDERLAND = register("wilderland",
            0.4f, 0.2f, 0.9f, 0.4f, Biome.Category.PLAINS,
            0x92AC50, GRASS_SURFACE, null);
    public static final RegistryObject<Biome> DAGORLAD = register("dagorlad",
            0.3f, 0.025f, 1.0f, 0.2f, Biome.Category.PLAINS,
            0x6B5F45, surface("lotr:mordor_dirt", "minecraft:coarse_dirt", "lotr:mordor_dirt", "minecraft:coarse_dirt"), new int[]{-1, -1, 0x26211D, 0x54493D, 0x666666});
    public static final RegistryObject<Biome> NURN = register("nurn",
            0.3f, 0.1f, 0.9f, 0.4f, Biome.Category.PLAINS,
            0x28241B, surface("minecraft:grass_block", "minecraft:grass_block", "minecraft:dirt", "minecraft:dirt"), new int[]{0x99993D, 0x6B773A, 0x877465, 0xA09D92, -1});
    public static final RegistryObject<Biome> NURNEN = register("nurnen",
            -0.8f, 0.15f, 0.9f, 0.4f, Biome.Category.PLAINS,
            0x0E3656, GRASS_SURFACE, null);
    public static final RegistryObject<Biome> NURN_MARSHES = register("nurn_marshes",
            0.2f, 0.05f, 0.9f, 0.4f, Biome.Category.SWAMP,
            0x3D3B2B, GRASS_SURFACE, null);
    public static final RegistryObject<Biome> ADORNLAND = register("adornland",
            0.4f, 0.1f, 0.7f, 0.6f, Biome.Category.PLAINS,
            0x779B4F, GRASS_SURFACE, null);
    public static final RegistryObject<Biome> ANGMAR_MOUNTAINS = register("angmar_mountains",
            1.8f, 0.75f, 0.25f, 0.1f, Biome.Category.EXTREME_HILLS,
            0xCFCFCB, GRASS_SURFACE, null);
    public static final RegistryObject<Biome> ANDUIN_MOUTH = register("anduin_mouth",
            0.2f, 0.05f, 0.9f, 1.0f, Biome.Category.PLAINS,
            0x4DA853, GRASS_SURFACE, null);
    public static final RegistryObject<Biome> ENTWASH_MOUTH = register("entwash_mouth",
            0.2f, 0.05f, 0.5f, 1.0f, Biome.Category.PLAINS,
            0x55A346, GRASS_SURFACE, null);
    public static final RegistryObject<Biome> DOR_EN_ERNIL = register("dor_en_ernil",
            0.27f, 0.1f, 0.9f, 0.9f, Biome.Category.PLAINS,
            0x8EBF45, GRASS_SURFACE, null);
    public static final RegistryObject<Biome> DOR_EN_ERNIL_HILLS = register("dor_en_ernil_hills",
            0.7f, 0.25f, 0.8f, 0.7f, Biome.Category.PLAINS,
            0x82A043, GRASS_SURFACE, null);
    public static final RegistryObject<Biome> FANGORN_WASTELAND = register("fangorn_wasteland",
            0.4f, 0.2f, 0.7f, 0.4f, Biome.Category.FOREST,
            0x677C4C, GRASS_SURFACE, null);
    public static final RegistryObject<Biome> ROHAN_WOODLANDS = register("rohan_woodlands",
            0.4f, 0.2f, 0.9f, 0.9f, Biome.Category.FOREST,
            0x578736, GRASS_SURFACE, null);
    public static final RegistryObject<Biome> GONDOR_WOODLANDS = register("gondor_woodlands",
            0.4f, 0.1f, 0.8f, 0.9f, Biome.Category.FOREST,
            0x59872B, GRASS_SURFACE, null);
    public static final RegistryObject<Biome> LAKE = register("lake",
            0.3f, 0.1f, 0.8f, 0.4f, Biome.Category.PLAINS,
            0x34649E, GRASS_SURFACE, null);
    public static final RegistryObject<Biome> LINDON_COAST = register("lindon_coast",
            0.2f, 0.25f, 0.9f, 0.9f, Biome.Category.PLAINS,
            0x8D9596, surface("minecraft:stone", "minecraft:stone", "minecraft:stone", "minecraft:stone"), null);
    public static final RegistryObject<Biome> BARROW_DOWNS = register("barrow_downs",
            0.5f, 0.2f, 0.6f, 0.7f, Biome.Category.PLAINS,
            0x7B8E52, GRASS_SURFACE, null);
    public static final RegistryObject<Biome> LONG_MARSHES = register("long_marshes",
            0.2f, 0.05f, 0.6f, 0.9f, Biome.Category.SWAMP,
            0x6D8746, GRASS_SURFACE, new int[]{-1, -1, 0x7C9E89, 0xC9E2E2, 0xB8D1C5});
    public static final RegistryObject<Biome> FANGORN_CLEARING = register("fangorn_clearing",
            0.4f, 0.05f, 0.7f, 0.8f, Biome.Category.FOREST,
            0x59AD3A, GRASS_SURFACE, null);
    public static final RegistryObject<Biome> ITHILIEN_HILLS = register("ithilien_hills",
            0.8f, 0.3f, 0.7f, 0.7f, Biome.Category.PLAINS,
            0x6A9840, GRASS_SURFACE, null);
    public static final RegistryObject<Biome> ITHILIEN_WASTELAND = register("ithilien_wasteland",
            0.35f, 0.1f, 0.6f, 0.6f, Biome.Category.PLAINS,
            0x7A874F, GRASS_SURFACE, null);
    public static final RegistryObject<Biome> NINDALF = register("nindalf",
            0.2f, 0.05f, 0.4f, 1.0f, Biome.Category.PLAINS,
            0x6C8446, GRASS_SURFACE, new int[]{0x6C704B, 0x6B7045, 0x303526, 0x898758, 0x646458});
    public static final RegistryObject<Biome> COLDFELLS = register("coldfells",
            0.6f, 0.4f, 0.25f, 0.8f, Biome.Category.PLAINS,
            0x7E9652, GRASS_SURFACE, null);
    public static final RegistryObject<Biome> NAN_CURUNIR = register("nan_curunir",
            0.4f, 0.05f, 0.6f, 0.4f, Biome.Category.PLAINS,
            0x6C7C52, surface("minecraft:dirt", "minecraft:dirt", "minecraft:dirt", "minecraft:dirt"), new int[]{-1, -1, -1, 0x848078, -1});
    public static final RegistryObject<Biome> WHITE_DOWNS = register("white_downs",
            0.8f, 0.3f, 0.6f, 0.7f, Biome.Category.PLAINS,
            0x9BCE79, surface("lotr:rock", "minecraft:stone", "lotr:rock", "minecraft:stone"), null);
    public static final RegistryObject<Biome> SWANFLEET = register("swanfleet",
            0.2f, 0.05f, 0.8f, 1.0f, Biome.Category.PLAINS,
            0x5F9C59, GRASS_SURFACE, null);
    public static final RegistryObject<Biome> PELENNOR = register("pelennor",
            0.3f, 0.01f, 0.9f, 0.9f, Biome.Category.PLAINS,
            0xABCC4B, GRASS_SURFACE, null);
    public static final RegistryObject<Biome> MINHIRIATH = register("minhiriath",
            0.3f, 0.1f, 0.7f, 0.4f, Biome.Category.PLAINS,
            0x709E46, GRASS_SURFACE, null);
    public static final RegistryObject<Biome> EREBOR = register("erebor",
            0.6f, 0.3f, 0.6f, 0.7f, Biome.Category.PLAINS,
            0x726D55, GRASS_SURFACE, null);
    public static final RegistryObject<Biome> MIRKWOOD_NORTH = register("mirkwood_north",
            0.4f, 0.2f, 0.7f, 0.7f, Biome.Category.FOREST,
            0x3A5223, GRASS_SURFACE, null);
    public static final RegistryObject<Biome> WOODLAND_REALM_HILLS = register("woodland_realm_hills",
            1.1f, 0.35f, 0.8f, 0.6f, Biome.Category.FOREST,
            0x37501F, GRASS_SURFACE, null);
    public static final RegistryObject<Biome> NAN_UNGOL = register("nan_ungol",
            0.3f, 0.2f, 2.0f, 0.0f, Biome.Category.PLAINS,
            0x0A0501, GRASS_SURFACE, null);
    public static final RegistryObject<Biome> PINNATH_GELIN = register("pinnath_gelin",
            0.7f, 0.25f, 0.8f, 0.8f, Biome.Category.PLAINS,
            0x97C645, GRASS_SURFACE, new int[]{0xA6C926, -1, -1, -1, -1});
    public static final RegistryObject<Biome> ISLAND = register("island",
            0.2f, 0.15f, 0.9f, 0.8f, Biome.Category.PLAINS,
            0x9AB553, GRASS_SURFACE, null);
    public static final RegistryObject<Biome> FORODWAITH_MOUNTAINS = register("forodwaith_mountains",
            2.2f, 1.0f, 0.0f, 0.2f, Biome.Category.EXTREME_HILLS,
            0xEDEDEE, GRASS_SURFACE, null);
    public static final RegistryObject<Biome> MISTY_MOUNTAINS_FOOTHILLS = register("misty_mountains_foothills",
            0.9f, 0.45f, 0.25f, 0.6f, Biome.Category.EXTREME_HILLS,
            0xBEC1B6, GRASS_SURFACE, null);
    public static final RegistryObject<Biome> GREY_MOUNTAINS_FOOTHILLS = register("grey_mountains_foothills",
            0.7f, 0.45f, 0.5f, 0.7f, Biome.Category.EXTREME_HILLS,
            0x8B9660, GRASS_SURFACE, null);
    public static final RegistryObject<Biome> BLUE_MOUNTAINS_FOOTHILLS = register("blue_mountains_foothills",
            0.7f, 0.45f, 0.5f, 0.8f, Biome.Category.EXTREME_HILLS,
            0xABB5B2, GRASS_SURFACE, null);
    public static final RegistryObject<Biome> TUNDRA = register("tundra",
            0.3f, 0.1f, 0.1f, 0.3f, Biome.Category.PLAINS,
            0xBCB296, surface("minecraft:stone", "minecraft:stone", "minecraft:stone", "minecraft:stone"), null);
    public static final RegistryObject<Biome> TAIGA = register("taiga",
            0.3f, 0.25f, 0.1f, 0.7f, Biome.Category.TAIGA,
            0x63964F, GRASS_SURFACE, null);
    public static final RegistryObject<Biome> BREELAND = register("breeland",
            0.3f, 0.1f, 0.8f, 0.7f, Biome.Category.PLAINS,
            0x68B339, GRASS_SURFACE, null);
    public static final RegistryObject<Biome> CHETWOOD = register("chetwood",
            0.4f, 0.2f, 0.8f, 0.9f, Biome.Category.FOREST,
            0x43831D, GRASS_SURFACE, null);
    public static final RegistryObject<Biome> FORODWAITH_GLACIER = register("forodwaith_glacier",
            1.2f, 0.05f, 0.0f, 0.1f, Biome.Category.TAIGA,
            0x8FCCE0, surface("minecraft:ice", "minecraft:ice", "minecraft:ice", "minecraft:ice"), null);
    public static final RegistryObject<Biome> WHITE_MOUNTAINS_FOOTHILLS = register("white_mountains_foothills",
            0.7f, 0.45f, 0.6f, 0.7f, Biome.Category.EXTREME_HILLS,
            0xC0CDB7, GRASS_SURFACE, null);
    public static final RegistryObject<Biome> BEACH = register("beach",
            0.3f, 0.1f, 0.8f, 0.4f, Biome.Category.BEACH,
            0xDBCA97, GRASS_SURFACE, null);
    public static final RegistryObject<Biome> BEACH_GRAVEL = register("beach_gravel",
            0.3f, 0.1f, 0.8f, 0.4f, Biome.Category.BEACH,
            0x9695A0, GRASS_SURFACE, null);
    public static final RegistryObject<Biome> NEAR_HARAD = register("near_harad",
            0.4f, 0.05f, 1.5f, 0.1f, Biome.Category.DESERT,
            0xD8C377, surface("minecraft:sand", "minecraft:sand", "minecraft:sand", "minecraft:sand"), new int[]{-1, -1, -1, -1, 0xF6E5C9});
    public static final RegistryObject<Biome> FAR_HARAD = register("far_harad",
            0.3f, 0.05f, 1.2f, 0.2f, Biome.Category.DESERT,
            0x94A041, GRASS_SURFACE, null);
    public static final RegistryObject<Biome> HARAD_MOUNTAINS = register("harad_mountains",
            2.0f, 1.0f, 0.9f, 0.5f, Biome.Category.EXTREME_HILLS,
            0x969075, GRASS_SURFACE, null);
    public static final RegistryObject<Biome> UMBAR = register("umbar",
            0.3f, 0.1f, 0.9f, 0.6f, Biome.Category.PLAINS,
            0x919C54, surface("minecraft:sand", "minecraft:sand", "minecraft:sand", "minecraft:sand"), new int[]{0xB5CE35, -1, -1, -1, -1});
    public static final RegistryObject<Biome> FAR_HARAD_JUNGLE = register("far_harad_jungle",
            0.4f, 0.2f, 1.2f, 0.9f, Biome.Category.DESERT,
            0x4B7423, surface("lotr:mud_grass", "minecraft:grass_block", "lotr:mud", "minecraft:dirt"), new int[]{0xA1DB3D, 0x7FD13C, 0x3EA077, 0xB6C4B4, 0xABBC9A});
    public static final RegistryObject<Biome> UMBAR_HILLS = register("umbar_hills",
            1.4f, 0.4f, 0.8f, 0.5f, Biome.Category.PLAINS,
            0x7D864A, surface("minecraft:sand", "minecraft:sand", "minecraft:sand", "minecraft:sand"), new int[]{0xB5CE35, -1, -1, -1, -1});
    public static final RegistryObject<Biome> NEAR_HARAD_HILLS = register("near_harad_hills",
            0.7f, 0.4f, 1.2f, 0.3f, Biome.Category.DESERT,
            0xB9A762, surface("minecraft:stone", "minecraft:stone", "minecraft:stone", "minecraft:stone"), null);
    public static final RegistryObject<Biome> FAR_HARAD_JUNGLE_LAKE = register("far_harad_jungle_lake",
            -0.3f, 0.1f, 1.2f, 0.9f, Biome.Category.DESERT,
            0x22AACC, GRASS_SURFACE, null);
    public static final RegistryObject<Biome> LOSTLADEN = register("lostladen",
            0.4f, 0.05f, 1.2f, 0.2f, Biome.Category.PLAINS,
            0xA2A365, surface("minecraft:gravel", "minecraft:gravel", "minecraft:gravel", "minecraft:gravel"), new int[]{-1, -1, -1, 0xEDECE6, -1});
    public static final RegistryObject<Biome> FAR_HARAD_FOREST = register("far_harad_forest",
            0.5f, 0.2f, 1.0f, 1.0f, Biome.Category.FOREST,
            0x38821D, GRASS_SURFACE, new int[]{0xB1EA48, 0x7FD13C, -1, -1, -1});
    public static final RegistryObject<Biome> NEAR_HARAD_FERTILE = register("near_harad_fertile",
            0.4f, 0.05f, 1.2f, 0.7f, Biome.Category.DESERT,
            0x9EAA4E, surface("minecraft:sand", "minecraft:sand", "minecraft:sand", "minecraft:sand"), new int[]{0xB5CE35, -1, -1, -1, -1});
    public static final RegistryObject<Biome> PERTOROGWAITH = register("pertorogwaith",
            0.4f, 0.25f, 0.7f, 0.1f, Biome.Category.PLAINS,
            0x877E5A, GRASS_SURFACE, new int[]{-1, -1, 0x8A8E77, 0x827C72, 0x727271});
    public static final RegistryObject<Biome> UMBAR_FOREST = register("umbar_forest",
            0.4f, 0.15f, 0.8f, 0.8f, Biome.Category.FOREST,
            0x6D873A, GRASS_SURFACE, null);
    public static final RegistryObject<Biome> FAR_HARAD_JUNGLE_EDGE = register("far_harad_jungle_edge",
            0.4f, 0.1f, 1.2f, 0.8f, Biome.Category.DESERT,
            0x71882E, GRASS_SURFACE, null);
    public static final RegistryObject<Biome> TAUREDAIN_CLEARING = register("tauredain_clearing",
            0.4f, 0.1f, 1.2f, 0.8f, Biome.Category.PLAINS,
            0xA4BC45, GRASS_SURFACE, new int[]{-1, -1, -1, 0xB0D9E5, 0xC1DDDB});
    public static final RegistryObject<Biome> GULF_HARAD = register("gulf_harad",
            0.35f, 0.05f, 1.0f, 0.5f, Biome.Category.DESERT,
            0x8BA850, surface("minecraft:sand", "minecraft:sand", "minecraft:sand", "minecraft:sand"), null);
    public static final RegistryObject<Biome> DORWINION_HILLS = register("dorwinion_hills",
            1.0f, 0.4f, 0.9f, 0.8f, Biome.Category.PLAINS,
            0xCBD3A9, surface("lotr:rock", "minecraft:stone", "lotr:rock", "minecraft:stone"), null);
    public static final RegistryObject<Biome> TOLFALAS = register("tolfalas",
            0.5f, 0.5f, 0.8f, 0.4f, Biome.Category.PLAINS,
            0x9BA06D, GRASS_SURFACE, null);
    public static final RegistryObject<Biome> LEBENNIN = register("lebennin",
            0.3f, 0.15f, 1.0f, 0.9f, Biome.Category.PLAINS,
            0x77B62A, GRASS_SURFACE, null);
    public static final RegistryObject<Biome> RHUN = register("rhun",
            0.5f, 0.0f, 0.9f, 0.3f, Biome.Category.PLAINS,
            0x9FB258, GRASS_SURFACE, new int[]{0xBECE58, -1, -1, -1, -1});
    public static final RegistryObject<Biome> RHUN_FOREST = register("rhun_forest",
            0.5f, 0.25f, 0.8f, 0.9f, Biome.Category.FOREST,
            0x72873B, GRASS_SURFACE, null);
    public static final RegistryObject<Biome> RED_MOUNTAINS = register("red_mountains",
            1.7f, 1.0f, 0.3f, 0.4f, Biome.Category.EXTREME_HILLS,
            0x93714C, GRASS_SURFACE, new int[]{-1, -1, -1, 0xCEA092, -1});
    public static final RegistryObject<Biome> RED_MOUNTAINS_FOOTHILLS = register("red_mountains_foothills",
            0.7f, 0.45f, 0.7f, 0.4f, Biome.Category.EXTREME_HILLS,
            0x999452, GRASS_SURFACE, null);
    public static final RegistryObject<Biome> DOL_GULDUR = register("dol_guldur",
            0.4f, 0.25f, 0.6f, 0.8f, Biome.Category.PLAINS,
            0x242F0F, GRASS_SURFACE, new int[]{0x2E4431, 0x2E4431, -1, 0x424751, -1});
    public static final RegistryObject<Biome> NEAR_HARAD_SEMI_DESERT = register("near_harad_semi_desert",
            0.4f, 0.05f, 1.5f, 0.2f, Biome.Category.DESERT,
            0xBDBB6A, surface("minecraft:dirt", "minecraft:dirt", "minecraft:dirt", "minecraft:dirt"), null);
    public static final RegistryObject<Biome> FAR_HARAD_ARID = register("far_harad_arid",
            0.4f, 0.075f, 1.5f, 0.3f, Biome.Category.DESERT,
            0xAAAE55, surface("minecraft:sand", "minecraft:sand", "minecraft:sand", "minecraft:sand"), new int[]{0xD6BF5C, -1, -1, -1, -1});
    public static final RegistryObject<Biome> FAR_HARAD_ARID_HILLS = register("far_harad_arid_hills",
            1.2f, 0.3f, 1.5f, 0.3f, Biome.Category.DESERT,
            0x998D5B, surface("minecraft:sand", "minecraft:sand", "minecraft:sand", "minecraft:sand"), new int[]{0xD6BF5C, -1, -1, -1, -1});
    public static final RegistryObject<Biome> FAR_HARAD_SWAMP = register("far_harad_swamp",
            0.2f, 0.05f, 0.8f, 1.0f, Biome.Category.DESERT,
            0x55934B, GRASS_SURFACE, new int[]{-1, -1, 0x558E7E, -1, -1});
    public static final RegistryObject<Biome> FAR_HARAD_CLOUD_FOREST = register("far_harad_cloud_forest",
            0.9f, 0.2f, 1.2f, 1.0f, Biome.Category.FOREST,
            0x2E7B40, GRASS_SURFACE, new int[]{0x1EA054, 0x068932, -1, 0xAEC1BB, -1});
    public static final RegistryObject<Biome> FAR_HARAD_BUSHLAND = register("far_harad_bushland",
            0.4f, 0.05f, 1.0f, 0.4f, Biome.Category.DESERT,
            0x99913E, GRASS_SURFACE, new int[]{0xCCB257, -1, -1, -1, -1});
    public static final RegistryObject<Biome> FAR_HARAD_BUSHLAND_HILLS = register("far_harad_bushland_hills",
            1.0f, 0.4f, 0.8f, 0.4f, Biome.Category.DESERT,
            0x7F7934, GRASS_SURFACE, new int[]{0xCCB257, -1, -1, -1, -1});
    public static final RegistryObject<Biome> FAR_HARAD_MANGROVE = register("far_harad_mangrove",
            0.15f, 0.025f, 1.0f, 0.9f, Biome.Category.DESERT,
            0x878E4D, surface("minecraft:sand", "minecraft:sand", "minecraft:sand", "minecraft:sand"), new int[]{0x9FB577, 0x667746, 0x5B533D, -1, -1});
    public static final RegistryObject<Biome> NEAR_HARAD_FERTILE_FOREST = register("near_harad_fertile_forest",
            0.4f, 0.2f, 1.2f, 1.0f, Biome.Category.FOREST,
            0x698432, GRASS_SURFACE, null);
    public static final RegistryObject<Biome> ANDUIN_VALE = register("anduin_vale",
            0.25f, 0.025f, 0.9f, 1.0f, Biome.Category.PLAINS,
            0x71A548, GRASS_SURFACE, null);
    public static final RegistryObject<Biome> WOLD = register("wold",
            0.6f, 0.15f, 0.9f, 0.1f, Biome.Category.PLAINS,
            0x90B54F, surface("minecraft:dirt", "minecraft:dirt", "minecraft:dirt", "minecraft:dirt"), null);
    public static final RegistryObject<Biome> SHIRE_MOORS = register("shire_moors",
            0.6f, 0.3f, 0.6f, 1.0f, Biome.Category.PLAINS,
            0x699B4C, GRASS_SURFACE, null);
    public static final RegistryObject<Biome> SHIRE_MARSHES = register("shire_marshes",
            0.2f, 0.05f, 0.8f, 1.0f, Biome.Category.SWAMP,
            0x3DA05F, GRASS_SURFACE, null);
    public static final RegistryObject<Biome> NEAR_HARAD_RED_DESERT = register("near_harad_red_desert",
            0.4f, 0.0f, 1.5f, 0.1f, Biome.Category.DESERT,
            0xC9934F, surface("minecraft:sand", "minecraft:sand", "minecraft:sand", "minecraft:sand"), null);
    public static final RegistryObject<Biome> FAR_HARAD_VOLCANO = register("far_harad_volcano",
            0.8f, 0.6f, 1.5f, 0.0f, Biome.Category.DESERT,
            0x685734, surface("minecraft:stone", "minecraft:stone", "minecraft:stone", "minecraft:stone"), new int[]{-1, -1, 0x3D2F1F, 0x5B5A58, 0x666666});
    public static final RegistryObject<Biome> UDUN = register("udun",
            0.4f, 0.35f, 1.5f, 0.0f, Biome.Category.PLAINS,
            0x010000, GRASS_SURFACE, new int[]{-1, -1, -1, 0x68544F, 0x4C3D3A});
    public static final RegistryObject<Biome> GORGOROTH = register("gorgoroth",
            0.8f, 0.1f, 2.0f, 0.0f, Biome.Category.PLAINS,
            0x211D1D, GRASS_SURFACE, new int[]{-1, -1, -1, 0x592A1C, -1});
    public static final RegistryObject<Biome> MORGUL_VALE = register("morgul_vale",
            0.4f, 0.05f, 1.0f, 0.0f, Biome.Category.PLAINS,
            0x152D19, surface("minecraft:grass_block", "minecraft:grass_block", "minecraft:dirt", "minecraft:dirt"), new int[]{0x5C634D, 0x444C32, 0x36604E, 0x778E86, 0x606B66});
    public static final RegistryObject<Biome> EASTERN_DESOLATION = register("eastern_desolation",
            0.4f, 0.1f, 1.0f, 0.3f, Biome.Category.PLAINS,
            0x5C5C47, surface("lotr:mordor_dirt", "minecraft:coarse_dirt", "lotr:mordor_dirt", "minecraft:coarse_dirt"), new int[]{-1, -1, -1, 0x918B7F, -1});
    public static final RegistryObject<Biome> DALE = register("dale",
            0.3f, 0.1f, 0.8f, 0.7f, Biome.Category.PLAINS,
            0x7DA34F, GRASS_SURFACE, null);
    public static final RegistryObject<Biome> DORWINION = register("dorwinion",
            0.3f, 0.15f, 0.9f, 0.9f, Biome.Category.PLAINS,
            0x6CA545, GRASS_SURFACE, new int[]{0xA0CE2D, -1, -1, -1, -1});
    public static final RegistryObject<Biome> TOWER_HILLS = register("tower_hills",
            0.7f, 0.25f, 0.8f, 0.8f, Biome.Category.PLAINS,
            0x689641, GRASS_SURFACE, null);
    public static final RegistryObject<Biome> GULF_HARAD_FOREST = register("gulf_harad_forest",
            0.4f, 0.2f, 1.0f, 1.0f, Biome.Category.FOREST,
            0x598C2E, GRASS_SURFACE, null);
    public static final RegistryObject<Biome> WILDERLAND_NORTH = register("wilderland_north",
            0.4f, 0.25f, 0.6f, 0.6f, Biome.Category.PLAINS,
            0x93A66C, GRASS_SURFACE, null);
    public static final RegistryObject<Biome> FORODWAITH_COAST = register("forodwaith_coast",
            0.2f, 0.25f, 0.0f, 0.4f, Biome.Category.TAIGA,
            0x8C9AAD, surface("minecraft:stone", "minecraft:stone", "minecraft:stone", "minecraft:stone"), null);
    public static final RegistryObject<Biome> FAR_HARAD_COAST = register("far_harad_coast",
            0.2f, 0.25f, 1.2f, 0.8f, Biome.Category.DESERT,
            0x7F8278, surface("minecraft:stone", "minecraft:stone", "minecraft:stone", "minecraft:stone"), null);
    public static final RegistryObject<Biome> NEAR_HARAD_RIVERBANK = register("near_harad_riverbank",
            0.3f, 0.05f, 1.2f, 0.8f, Biome.Category.RIVER,
            0x6D9E50, GRASS_SURFACE, null);
    public static final RegistryObject<Biome> LOSSARNACH = register("lossarnach",
            0.3f, 0.1f, 1.0f, 1.0f, Biome.Category.PLAINS,
            0x80C52E, GRASS_SURFACE, null);
    public static final RegistryObject<Biome> IMLOTH_MELUI = register("imloth_melui",
            0.3f, 0.1f, 1.0f, 1.0f, Biome.Category.PLAINS,
            0xDD8568, GRASS_SURFACE, null);
    public static final RegistryObject<Biome> NEAR_HARAD_OASIS = register("near_harad_oasis",
            0.3f, 0.05f, 1.2f, 0.8f, Biome.Category.DESERT,
            0x0CB500, GRASS_SURFACE, null);
    public static final RegistryObject<Biome> BEACH_WHITE = register("beach_white",
            0.3f, 0.1f, 0.8f, 0.4f, Biome.Category.BEACH,
            0xEDEDED, GRASS_SURFACE, null);
    public static final RegistryObject<Biome> HARNEDOR = register("harnedor",
            0.3f, 0.15f, 1.0f, 0.3f, Biome.Category.PLAINS,
            0xAEB355, surface("minecraft:sand", "minecraft:sand", "minecraft:sand", "minecraft:sand"), new int[]{0xDDD566, -1, -1, -1, -1});
    public static final RegistryObject<Biome> LAMEDON = register("lamedon",
            0.4f, 0.1f, 0.9f, 0.5f, Biome.Category.PLAINS,
            0xA6BD64, surface("minecraft:dirt", "minecraft:dirt", "minecraft:dirt", "minecraft:dirt"), new int[]{0xB1B54F, -1, -1, -1, -1});
    public static final RegistryObject<Biome> LAMEDON_HILLS = register("lamedon_hills",
            0.8f, 0.45f, 0.6f, 0.4f, Biome.Category.PLAINS,
            0xCED6A9, GRASS_SURFACE, null);
    public static final RegistryObject<Biome> BLACKROOT_VALE = register("blackroot_vale",
            0.4f, 0.06f, 0.8f, 0.9f, Biome.Category.PLAINS,
            0x6D9E31, GRASS_SURFACE, new int[]{0x75912E, -1, -1, -1, -1});
    public static final RegistryObject<Biome> ANDRAST = register("andrast",
            0.4f, 0.1f, 0.8f, 0.8f, Biome.Category.PLAINS,
            0x879660, surface("minecraft:stone", "minecraft:stone", "minecraft:stone", "minecraft:stone"), new int[]{0x9BAD66, -1, -1, -1, -1});
    public static final RegistryObject<Biome> PUKEL = register("pukel",
            0.4f, 0.2f, 0.7f, 0.7f, Biome.Category.PLAINS,
            0x567A42, surface("minecraft:stone", "minecraft:stone", "minecraft:stone", "minecraft:stone"), new int[]{0x667738, -1, -1, 0xA6BCB8, -1});
    public static final RegistryObject<Biome> RHUN_LAND = register("rhun_land",
            0.3f, 0.15f, 1.0f, 0.8f, Biome.Category.PLAINS,
            0xADAB4F, GRASS_SURFACE, new int[]{0xDDD539, -1, -1, -1, -1});
    public static final RegistryObject<Biome> RHUN_LAND_STEPPE = register("rhun_land_steppe",
            0.4f, 0.025f, 1.0f, 0.3f, Biome.Category.PLAINS,
            0xB2B762, GRASS_SURFACE, new int[]{0xD4CF4E, -1, -1, -1, -1});
    public static final RegistryObject<Biome> RHUN_LAND_HILLS = register("rhun_land_hills",
            0.8f, 0.4f, 1.0f, 0.5f, Biome.Category.PLAINS,
            0x8E8D4E, surface("minecraft:sand", "minecraft:sand", "minecraft:sand", "minecraft:sand"), null);
    public static final RegistryObject<Biome> RHUN_RED_FOREST = register("rhun_red_forest",
            0.3f, 0.15f, 0.9f, 1.0f, Biome.Category.FOREST,
            0x916C3E, GRASS_SURFACE, new int[]{0x88963C, -1, -1, -1, 0xABCCB7});
    public static final RegistryObject<Biome> RHUN_ISLAND = register("rhun_island",
            0.3f, 0.2f, 1.0f, 0.8f, Biome.Category.PLAINS,
            0xA5B157, GRASS_SURFACE, null);
    public static final RegistryObject<Biome> RHUN_ISLAND_FOREST = register("rhun_island_forest",
            0.3f, 0.2f, 0.9f, 1.0f, Biome.Category.FOREST,
            0x91793E, GRASS_SURFACE, new int[]{-1, -1, -1, -1, 0x5D916E});
    public static final RegistryObject<Biome> LAST_DESERT = register("last_desert",
            0.4f, 0.025f, 0.7f, 0.0f, Biome.Category.DESERT,
            0xD3C387, surface("minecraft:sand", "minecraft:sand", "minecraft:sand", "minecraft:sand"), new int[]{0xFFDB8E, -1, -1, 0xE0DCCC, 0xDBD2AF});
    public static final RegistryObject<Biome> WIND_MOUNTAINS = register("wind_mountains",
            2.2f, 1.0f, 0.28f, 0.2f, Biome.Category.EXTREME_HILLS,
            0xD3D3D3, GRASS_SURFACE, new int[]{-1, -1, -1, 0xB1D2E2, -1});
    public static final RegistryObject<Biome> WIND_MOUNTAINS_FOOTHILLS = register("wind_mountains_foothills",
            0.7f, 0.3f, 0.4f, 0.6f, Biome.Category.EXTREME_HILLS,
            0x9A9F6A, GRASS_SURFACE, null);
    public static final RegistryObject<Biome> RIVENDELL = register("rivendell",
            0.35f, 0.15f, 0.9f, 1.0f, Biome.Category.PLAINS,
            0x86B72A, GRASS_SURFACE, null);
    public static final RegistryObject<Biome> RIVENDELL_HILLS = register("rivendell_hills",
            2.2f, 0.25f, 0.7f, 0.8f, Biome.Category.PLAINS,
            0xD8D5B1, surface("lotr:rock", "minecraft:stone", "lotr:rock", "minecraft:stone"), null);
    public static final RegistryObject<Biome> FAR_HARAD_JUNGLE_MOUNTAINS = register("far_harad_jungle_mountains",
            2.0f, 0.75f, 1.0f, 1.0f, Biome.Category.EXTREME_HILLS,
            0x635A46, GRASS_SURFACE, new int[]{-1, -1, -1, 0xA2A89A, 0x959E8B});
    public static final RegistryObject<Biome> HALF_TROLL_FOREST = register("half_troll_forest",
            0.5f, 0.2f, 0.8f, 0.2f, Biome.Category.FOREST,
            0x5B7034, GRASS_SURFACE, new int[]{-1, -1, 0xA6AD82, 0xC1C1AC, 0x879385});
    public static final RegistryObject<Biome> FAR_HARAD_KANUKA = register("far_harad_kanuka",
            0.5f, 0.25f, 1.0f, 1.0f, Biome.Category.DESERT,
            0x4E7818, surface("lotr:dirt_path", "minecraft:coarse_dirt", "lotr:dirt_path", "minecraft:coarse_dirt"), new int[]{0xB5D12B, -1, -1, -1, -1});

    static {
        MAP_COLORS.put(0x367CB5, RIVER);
        MAP_COLORS.put(0x70AD45, ROHAN);
        MAP_COLORS.put(0xE8E7E1, MISTY_MOUNTAINS);
        MAP_COLORS.put(0x67AD35, SHIRE);
        MAP_COLORS.put(0x447736, SHIRE_WOODLANDS);
        MAP_COLORS.put(0x11100E, MORDOR);
        MAP_COLORS.put(0x514D48, MORDOR_MOUNTAINS);
        MAP_COLORS.put(0x88B445, GONDOR);
        MAP_COLORS.put(0xE5E5E8, WHITE_MOUNTAINS);
        MAP_COLORS.put(0xFBD83F, LOTHLORIEN);
        MAP_COLORS.put(0x74AF46, CELEBRANT);
        MAP_COLORS.put(0x8B7F4D, IRON_HILLS);
        MAP_COLORS.put(0x6F733F, DEAD_MARSHES);
        MAP_COLORS.put(0x587C2F, TROLLSHAWS);
        MAP_COLORS.put(0x3E6526, WOODLAND_REALM);
        MAP_COLORS.put(0x2E441B, MIRKWOOD_CORRUPTED);
        MAP_COLORS.put(0x7E935A, ROHAN_URUK_HIGHLANDS);
        MAP_COLORS.put(0x968C72, EMYN_MUIL);
        MAP_COLORS.put(0x75A734, ITHILIEN);
        MAP_COLORS.put(0xABC151, PELARGIR);
        MAP_COLORS.put(0x82A84A, LONE_LANDS);
        MAP_COLORS.put(0x848E4E, LONE_LANDS_HILLS);
        MAP_COLORS.put(0x69994C, DUNLAND);
        MAP_COLORS.put(0x427519, FANGORN);
        MAP_COLORS.put(0x8FAF4F, ANGLE);
        MAP_COLORS.put(0x7C895A, ETTENMOORS);
        MAP_COLORS.put(0x45753B, OLD_FOREST);
        MAP_COLORS.put(0xA2B546, HARONDOR);
        MAP_COLORS.put(0x6BA644, ERIADOR);
        MAP_COLORS.put(0x748C47, ERIADOR_DOWNS);
        MAP_COLORS.put(0x427F4D, ERYN_VORN);
        MAP_COLORS.put(0xCACCC1, GREY_MOUNTAINS);
        MAP_COLORS.put(0x5B9357, MIDGEWATER);
        MAP_COLORS.put(0x827E50, BROWN_LANDS);
        MAP_COLORS.put(0x02598D, OCEAN);
        MAP_COLORS.put(0x6BB25C, ANDUIN_HILLS);
        MAP_COLORS.put(0x91B75A, MENELTARMA);
        MAP_COLORS.put(0x4C9B59, GLADDEN_FIELDS);
        MAP_COLORS.put(0xD4C643, LOTHLORIEN_EDGE);
        MAP_COLORS.put(0xD8D8D2, FORODWAITH);
        MAP_COLORS.put(0x7AA84F, ENEDWAITH);
        MAP_COLORS.put(0x54472F, ANGMAR);
        MAP_COLORS.put(0x659048, EREGION);
        MAP_COLORS.put(0x74AD45, LINDON);
        MAP_COLORS.put(0x1E772F, LINDON_WOODLANDS);
        MAP_COLORS.put(0x8A955D, EAST_BIGHT);
        MAP_COLORS.put(0xC9DAE2, BLUE_MOUNTAINS);
        MAP_COLORS.put(0x282D1D, MIRKWOOD_MOUNTAINS);
        MAP_COLORS.put(0x92AC50, WILDERLAND);
        MAP_COLORS.put(0x6B5F45, DAGORLAD);
        MAP_COLORS.put(0x28241B, NURN);
        MAP_COLORS.put(0x0E3656, NURNEN);
        MAP_COLORS.put(0x3D3B2B, NURN_MARSHES);
        MAP_COLORS.put(0x779B4F, ADORNLAND);
        MAP_COLORS.put(0xCFCFCB, ANGMAR_MOUNTAINS);
        MAP_COLORS.put(0x4DA853, ANDUIN_MOUTH);
        MAP_COLORS.put(0x55A346, ENTWASH_MOUTH);
        MAP_COLORS.put(0x8EBF45, DOR_EN_ERNIL);
        MAP_COLORS.put(0x82A043, DOR_EN_ERNIL_HILLS);
        MAP_COLORS.put(0x677C4C, FANGORN_WASTELAND);
        MAP_COLORS.put(0x578736, ROHAN_WOODLANDS);
        MAP_COLORS.put(0x59872B, GONDOR_WOODLANDS);
        MAP_COLORS.put(0x34649E, LAKE);
        MAP_COLORS.put(0x8D9596, LINDON_COAST);
        MAP_COLORS.put(0x7B8E52, BARROW_DOWNS);
        MAP_COLORS.put(0x6D8746, LONG_MARSHES);
        MAP_COLORS.put(0x59AD3A, FANGORN_CLEARING);
        MAP_COLORS.put(0x6A9840, ITHILIEN_HILLS);
        MAP_COLORS.put(0x7A874F, ITHILIEN_WASTELAND);
        MAP_COLORS.put(0x6C8446, NINDALF);
        MAP_COLORS.put(0x7E9652, COLDFELLS);
        MAP_COLORS.put(0x6C7C52, NAN_CURUNIR);
        MAP_COLORS.put(0x9BCE79, WHITE_DOWNS);
        MAP_COLORS.put(0x5F9C59, SWANFLEET);
        MAP_COLORS.put(0xABCC4B, PELENNOR);
        MAP_COLORS.put(0x709E46, MINHIRIATH);
        MAP_COLORS.put(0x726D55, EREBOR);
        MAP_COLORS.put(0x3A5223, MIRKWOOD_NORTH);
        MAP_COLORS.put(0x37501F, WOODLAND_REALM_HILLS);
        MAP_COLORS.put(0x0A0501, NAN_UNGOL);
        MAP_COLORS.put(0x97C645, PINNATH_GELIN);
        MAP_COLORS.put(0x9AB553, ISLAND);
        MAP_COLORS.put(0xEDEDEE, FORODWAITH_MOUNTAINS);
        MAP_COLORS.put(0xBEC1B6, MISTY_MOUNTAINS_FOOTHILLS);
        MAP_COLORS.put(0x8B9660, GREY_MOUNTAINS_FOOTHILLS);
        MAP_COLORS.put(0xABB5B2, BLUE_MOUNTAINS_FOOTHILLS);
        MAP_COLORS.put(0xBCB296, TUNDRA);
        MAP_COLORS.put(0x63964F, TAIGA);
        MAP_COLORS.put(0x68B339, BREELAND);
        MAP_COLORS.put(0x43831D, CHETWOOD);
        MAP_COLORS.put(0x8FCCE0, FORODWAITH_GLACIER);
        MAP_COLORS.put(0xC0CDB7, WHITE_MOUNTAINS_FOOTHILLS);
        MAP_COLORS.put(0xDBCA97, BEACH);
        MAP_COLORS.put(0x9695A0, BEACH_GRAVEL);
        MAP_COLORS.put(0xD8C377, NEAR_HARAD);
        MAP_COLORS.put(0x94A041, FAR_HARAD);
        MAP_COLORS.put(0x969075, HARAD_MOUNTAINS);
        MAP_COLORS.put(0x919C54, UMBAR);
        MAP_COLORS.put(0x4B7423, FAR_HARAD_JUNGLE);
        MAP_COLORS.put(0x7D864A, UMBAR_HILLS);
        MAP_COLORS.put(0xB9A762, NEAR_HARAD_HILLS);
        MAP_COLORS.put(0x22AACC, FAR_HARAD_JUNGLE_LAKE);
        MAP_COLORS.put(0xA2A365, LOSTLADEN);
        MAP_COLORS.put(0x38821D, FAR_HARAD_FOREST);
        MAP_COLORS.put(0x9EAA4E, NEAR_HARAD_FERTILE);
        MAP_COLORS.put(0x877E5A, PERTOROGWAITH);
        MAP_COLORS.put(0x6D873A, UMBAR_FOREST);
        MAP_COLORS.put(0x71882E, FAR_HARAD_JUNGLE_EDGE);
        MAP_COLORS.put(0xA4BC45, TAUREDAIN_CLEARING);
        MAP_COLORS.put(0x8BA850, GULF_HARAD);
        MAP_COLORS.put(0xCBD3A9, DORWINION_HILLS);
        MAP_COLORS.put(0x9BA06D, TOLFALAS);
        MAP_COLORS.put(0x77B62A, LEBENNIN);
        MAP_COLORS.put(0x9FB258, RHUN);
        MAP_COLORS.put(0x72873B, RHUN_FOREST);
        MAP_COLORS.put(0x93714C, RED_MOUNTAINS);
        MAP_COLORS.put(0x999452, RED_MOUNTAINS_FOOTHILLS);
        MAP_COLORS.put(0x242F0F, DOL_GULDUR);
        MAP_COLORS.put(0xBDBB6A, NEAR_HARAD_SEMI_DESERT);
        MAP_COLORS.put(0xAAAE55, FAR_HARAD_ARID);
        MAP_COLORS.put(0x998D5B, FAR_HARAD_ARID_HILLS);
        MAP_COLORS.put(0x55934B, FAR_HARAD_SWAMP);
        MAP_COLORS.put(0x2E7B40, FAR_HARAD_CLOUD_FOREST);
        MAP_COLORS.put(0x99913E, FAR_HARAD_BUSHLAND);
        MAP_COLORS.put(0x7F7934, FAR_HARAD_BUSHLAND_HILLS);
        MAP_COLORS.put(0x878E4D, FAR_HARAD_MANGROVE);
        MAP_COLORS.put(0x698432, NEAR_HARAD_FERTILE_FOREST);
        MAP_COLORS.put(0x71A548, ANDUIN_VALE);
        MAP_COLORS.put(0x90B54F, WOLD);
        MAP_COLORS.put(0x699B4C, SHIRE_MOORS);
        MAP_COLORS.put(0x3DA05F, SHIRE_MARSHES);
        MAP_COLORS.put(0xC9934F, NEAR_HARAD_RED_DESERT);
        MAP_COLORS.put(0x685734, FAR_HARAD_VOLCANO);
        MAP_COLORS.put(0x010000, UDUN);
        MAP_COLORS.put(0x211D1D, GORGOROTH);
        MAP_COLORS.put(0x152D19, MORGUL_VALE);
        MAP_COLORS.put(0x5C5C47, EASTERN_DESOLATION);
        MAP_COLORS.put(0x7DA34F, DALE);
        MAP_COLORS.put(0x6CA545, DORWINION);
        MAP_COLORS.put(0x689641, TOWER_HILLS);
        MAP_COLORS.put(0x598C2E, GULF_HARAD_FOREST);
        MAP_COLORS.put(0x93A66C, WILDERLAND_NORTH);
        MAP_COLORS.put(0x8C9AAD, FORODWAITH_COAST);
        MAP_COLORS.put(0x7F8278, FAR_HARAD_COAST);
        MAP_COLORS.put(0x6D9E50, NEAR_HARAD_RIVERBANK);
        MAP_COLORS.put(0x80C52E, LOSSARNACH);
        MAP_COLORS.put(0xDD8568, IMLOTH_MELUI);
        MAP_COLORS.put(0x0CB500, NEAR_HARAD_OASIS);
        MAP_COLORS.put(0xEDEDED, BEACH_WHITE);
        MAP_COLORS.put(0xAEB355, HARNEDOR);
        MAP_COLORS.put(0xA6BD64, LAMEDON);
        MAP_COLORS.put(0xCED6A9, LAMEDON_HILLS);
        MAP_COLORS.put(0x6D9E31, BLACKROOT_VALE);
        MAP_COLORS.put(0x879660, ANDRAST);
        MAP_COLORS.put(0x567A42, PUKEL);
        MAP_COLORS.put(0xADAB4F, RHUN_LAND);
        MAP_COLORS.put(0xB2B762, RHUN_LAND_STEPPE);
        MAP_COLORS.put(0x8E8D4E, RHUN_LAND_HILLS);
        MAP_COLORS.put(0x916C3E, RHUN_RED_FOREST);
        MAP_COLORS.put(0xA5B157, RHUN_ISLAND);
        MAP_COLORS.put(0x91793E, RHUN_ISLAND_FOREST);
        MAP_COLORS.put(0xD3C387, LAST_DESERT);
        MAP_COLORS.put(0xD3D3D3, WIND_MOUNTAINS);
        MAP_COLORS.put(0x9A9F6A, WIND_MOUNTAINS_FOOTHILLS);
        MAP_COLORS.put(0x86B72A, RIVENDELL);
        MAP_COLORS.put(0xD8D5B1, RIVENDELL_HILLS);
        MAP_COLORS.put(0x635A46, FAR_HARAD_JUNGLE_MOUNTAINS);
        MAP_COLORS.put(0x5B7034, HALF_TROLL_FOREST);
        MAP_COLORS.put(0x4E7818, FAR_HARAD_KANUKA);
    }
}
