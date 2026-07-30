#!/usr/bin/env python3
# -*- coding: utf-8 -*-
"""PHASE 5 / lot 1 : biomes de la Terre du Milieu.

Extraction FIDELE de lotr.common.world.biome.LOTRBiome :
  - temperature / pluviometrie : setTemperatureRainfall(t, r)
  - profondeur / variation : setMinMaxHeight(f, f1)
      Legacy : rootHeight = f - 2.0 + 0.2 ; heightVariation = f1 / 2
      1.16.5 : depth = rootHeight + 2.0 (repere vanilla), scale = heightVariation
  - couleur de carte : setColor(...) -> table couleur -> biome pour la carte map.png
"""
import json, os, re

ROWS = json.load(open("/tmp/biomes_legacy.json"))
SURFACES = json.load(open("/tmp/surfaces_mapped.json"))
COLORS = json.load(open("/tmp/biome_colors.json"))
OUT = "/home/claude/lot13"
PKG = f"{OUT}/src/main/java/fr/alleretretour/lotr"
os.makedirs(f"{PKG}/world/biome", exist_ok=True)
os.makedirs(f"{PKG}/init", exist_ok=True)

def snake(s):
    return re.sub(r"(?<=[a-z0-9])(?=[A-Z])", "_", s).lower()

# categorie vanilla la plus proche (rendu du ciel/brouillard, pas de generation)
def category(name):
    n = name.lower()
    if "ocean" in n or "sea" in n:
        return "OCEAN"
    if "river" in n:
        return "RIVER"
    if "beach" in n or "shore" in n:
        return "BEACH"
    if "mountain" in n or "misty" in n or "ered" in n:
        return "EXTREME_HILLS"
    if "desert" in n or "harad" in n and "forest" not in n:
        return "DESERT"
    if "forest" in n or "wood" in n or "mirk" in n or "fangorn" in n or "lorien" in n:
        return "FOREST"
    if "swamp" in n or "marsh" in n or "fen" in n:
        return "SWAMP"
    if "taiga" in n or "snow" in n or "forodwaith" in n:
        return "TAIGA"
    if "plateau" in n or "wold" in n or "steppe" in n or "plains" in n or "field" in n:
        return "PLAINS"
    return "PLAINS"

entries, colors, count = [], [], 0
for r in ROWS:
    reg = snake(r["name"])
    # PORT : rootHeight = hmin - 1.8 ; on ramene au repere vanilla (depth 0.1 = plaine)
    depth = round(r["hmin"] - 1.8 + 2.0, 3)
    scale = round(r["hmax"] / 2.0, 3)
    temp = r["temp"]
    rain = min(max(r["rain"], 0.0), 1.0)  # 1.16.5 borne la pluviometrie a 1
    sf = SURFACES.get(r["cls"])
    if sf and (sf["top"] or sf["filler"]):
        t = sf["top"] or sf["filler"]
        f_ = sf["filler"] or sf["top"]
        surface = (f'''surface("{t[0]}", "{t[1]}", "{f_[0]}", "{f_[1]}")''')
    else:
        surface = "GRASS_SURFACE"
    cl = COLORS.get(r["cls"], {})
    extra = ""
    for kind in ("grass", "foliage", "water", "sky", "fog"):
        if kind in cl:
            extra += f', .{kind}(0x{cl[kind] & 0xFFFFFF:06X})'
    extra = (", new int[]{" + ", ".join(
        (f'0x{cl[k] & 0xFFFFFF:06X}' if k in cl else "-1") for k in ("grass","foliage","water","sky","fog")) + "}") if cl else ", null"
    entries.append(f'''    public static final RegistryObject<Biome> {reg.upper()} = register("{reg}",
            {depth}f, {scale}f, {temp}f, {rain}f, Biome.Category.{category(r["name"])},
            0x{r["color"] & 0xFFFFFF:06X}, {surface}{extra});
''')
    colors.append(f'        MAP_COLORS.put(0x{r["color"] & 0xFFFFFF:06X}, {reg.upper()});')
    count += 1

java = f'''package fr.alleretretour.lotr.init;

import fr.alleretretour.lotr.LOTRMod;
import net.minecraft.util.RegistryKey;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.BiomeAmbience;
import net.minecraft.world.biome.BiomeGenerationSettings;
import net.minecraft.world.biome.MobSpawnInfo;
import net.minecraft.world.gen.surfacebuilders.ConfiguredSurfaceBuilders;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * PORT des {count} biomes de la Terre du Milieu (lotr.common.world.biome.LOTRBiome).
 * Temperature, pluviometrie, profondeur et variation reprises telles quelles ;
 * MAP_COLORS reproduit la table couleur -> biome utilisee par map.png.
 * La decoration (arbres, minerais, structures) viendra dans les lots suivants.
 */
public final class LOTRBiomes {{

    public static final DeferredRegister<Biome> BIOMES =
            DeferredRegister.create(ForgeRegistries.BIOMES, LOTRMod.MOD_ID);

    /** couleur de la carte (RGB) -> biome, PORT de colorsToBiomeIDs. */
    public static final Map<Integer, RegistryObject<Biome>> MAP_COLORS = new LinkedHashMap<>();

    private LOTRBiomes() {{
    }}

    public static void register(IEventBus modBus) {{
        BIOMES.register(modBus);
    }}

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
            String topId, String topFallback, String fillerId, String fillerFallback) {{
        return () -> new net.minecraft.world.gen.surfacebuilders.ConfiguredSurfaceBuilder<>(
                net.minecraft.world.gen.surfacebuilders.SurfaceBuilder.DEFAULT,
                new net.minecraft.world.gen.surfacebuilders.SurfaceBuilderConfig(
                        state(topId, topFallback), state(fillerId, fillerFallback),
                        state(fillerId, fillerFallback)));
    }}

    private static net.minecraft.block.BlockState state(String id, String fallback) {{
        net.minecraft.block.Block block = ForgeRegistries.BLOCKS
                .getValue(new ResourceLocation(id));
        if (block == null || block == net.minecraft.block.Blocks.AIR) {{
            block = ForgeRegistries.BLOCKS.getValue(new ResourceLocation(fallback));
        }}
        return block == null ? net.minecraft.block.Blocks.STONE.defaultBlockState()
                : block.defaultBlockState();
    }}

    private static RegistryObject<Biome> register(String name, float depth, float scale,
                                                  float temperature, float downfall,
                                                  Biome.Category category, int mapColor,
                                                  java.util.function.Supplier<
                                                          net.minecraft.world.gen.surfacebuilders.ConfiguredSurfaceBuilder<
                                                                  net.minecraft.world.gen.surfacebuilders.SurfaceBuilderConfig>> surfaceBuilder,
                                                  int[] colors) {{
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
    }}

    /** PNJ pouvant apparaitre dans ce biome (PORT des npcSpawnList). */
    private static MobSpawnInfo spawns(String name) {{
        MobSpawnInfo.Builder b = new MobSpawnInfo.Builder();
        LOTRBiomeSpawns.apply(name, b);
        return b.build();
    }}

    /** Reglages de generation : surface + decoration Legacy du biome. */
    private static BiomeGenerationSettings generation(String name,
            net.minecraft.world.gen.surfacebuilders.ConfiguredSurfaceBuilder<
                    net.minecraft.world.gen.surfacebuilders.SurfaceBuilderConfig> sb) {{
        BiomeGenerationSettings.Builder b = new BiomeGenerationSettings.Builder()
                .surfaceBuilder(sb);
        LOTRBiomeCommon.addBaseFeatures(b);   // grottes / lacs / sources (voir la classe)
        LOTRBiomeOres.apply(name, b);          // minerais aux taux du Legacy
        LOTRBiomeDecoration.apply(name, b);
        return b.build();
    }}

    /**
     * PORT de LOTRBiomeColors : herbe, feuillage, eau, ciel et brouillard
     * surcharges par certains biomes ({{grass, foliage, water, sky, fog}},
     * -1 = valeur par defaut).
     */
    private static BiomeAmbience ambience(float temperature, int[] colors) {{
        BiomeAmbience.Builder b = new BiomeAmbience.Builder()
                .waterColor(colors != null && colors[2] >= 0 ? colors[2] : 0x3F76E4)
                .waterFogColor(colors != null && colors[2] >= 0 ? colors[2] : 0x050533)
                .fogColor(colors != null && colors[4] >= 0 ? colors[4] : 0xC0D8FF)
                .skyColor(colors != null && colors[3] >= 0 ? colors[3] : skyColor(temperature));
        if (colors != null && colors[0] >= 0) {{
            b.grassColorOverride(colors[0]);
        }}
        if (colors != null && colors[1] >= 0) {{
            b.foliageColorOverride(colors[1]);
        }}
        return b.build();
    }}

    /** Formule vanilla de la couleur de ciel selon la temperature. */
    private static int skyColor(float temperature) {{
        float f = net.minecraft.util.math.MathHelper.clamp(temperature / 3.0f, -1.0f, 1.0f);
        return net.minecraft.util.math.MathHelper.hsvToRgb(0.62222224f - f * 0.05f, 0.5f + f * 0.1f, 1.0f);
    }}

    public static RegistryKey<Biome> key(RegistryObject<Biome> ro) {{
        return RegistryKey.create(Registry.BIOME_REGISTRY, ro.getId());
    }}

    /** Biome par defaut si une couleur de la carte est inconnue. */
    public static RegistryObject<Biome> fallback() {{
        return SHIRE;
    }}

{"".join(entries)}
    static {{
{chr(10).join(colors)}
    }}
}}
'''
open(f"{PKG}/init/LOTRBiomes.java", "w", encoding="utf-8").write(java)
print(f"{count} biomes generes")
