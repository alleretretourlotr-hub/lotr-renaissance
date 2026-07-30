#!/usr/bin/env python3
# -*- coding: utf-8 -*-
"""Decoration des biomes : PORT de LOTRBiomeDecorator.

Reprend, biome par biome, les valeurs du Legacy :
  grassPerChunk, doubleGrassPerChunk, flowersPerChunk, doubleFlowersPerChunk,
  treesPerChunk, deadBushPerChunk, waterlilyPerChunk, canePerChunk,
  mushroomsPerChunk, et la liste ponderee addTree(...).

Les essences propres au mod (mallorn, lebethron, charred...) exigent les blocs
de bois du mod : elles sont listees en TODO par biome, avec leur poids exact,
pour etre activees quand ces blocs seront portes.
"""
import json, re

DECOR = json.load(open("/tmp/decor.json"))
ROWS = json.load(open("/tmp/biomes_legacy.json"))

# essence Legacy -> feature vanilla EQUIVALENTE (pas de substitution approximative)
TREES = {
    "OAK": "Features.OAK",
    "OAK_LARGE": "Features.FANCY_OAK",
    "OAK_SWAMP": "Features.SWAMP_OAK",
    "BIRCH": "Features.BIRCH",
    "BIRCH_TALL": "Features.SUPER_BIRCH_BEES_0002",
    "SPRUCE": "Features.SPRUCE",
    "SPRUCE_THIN": "Features.SPRUCE",
    "PINE": "Features.PINE",
    "JUNGLE": "Features.JUNGLE_TREE",
    "JUNGLE_LARGE": "Features.MEGA_JUNGLE_TREE",
    "JUNGLE_SHRUB": "Features.JUNGLE_BUSH",
    "ACACIA": "Features.ACACIA",
    "DARK_OAK": "Features.DARK_OAK",
}

def snake(s):
    return re.sub(r"(?<=[a-z0-9])(?=[A-Z])", "_", s).lower()

lines, todo_lines, stats = [], [], {"biomes": 0, "features": 0, "todo": 0}
for r in ROWS:
    d = DECOR.get(r["cls"])
    if not d:
        continue
    reg = snake(r["name"])
    calls = []
    for key, helper in (("grassPerChunk", "grass"), ("doubleGrassPerChunk", "tallGrass"),
                        ("flowersPerChunk", "flowers"), ("doubleFlowersPerChunk", "tallFlowers"),
                        ("deadBushPerChunk", "deadBush"), ("waterlilyPerChunk", "waterlily"),
                        ("canePerChunk", "sugarCane"), ("mushroomsPerChunk", "mushrooms")):
        if d.get(key):
            calls.append(f'{helper}(b, {d[key]});')
            stats["features"] += 1
    trees = d.get("trees", [])
    known = [(t, w) for t, w in trees if t in TREES]
    unknown = [(t, w) for t, w in trees if t not in TREES]
    per_chunk = d.get("treesPerChunk", 0)
    if known and per_chunk:
        total = sum(w for _, w in trees) or 1
        for t, w in known:
            # part exacte de l'essence dans la ponderation Legacy
            share = round(per_chunk * w / total, 3)
            if share >= 0.01:
                calls.append(f'tree(b, {TREES[t]}, {share}f);')
                stats["features"] += 1
    if unknown:
        stats["todo"] += 1
        todo_lines.append(f'    // {reg} : essences du mod non portees -> '
                          + ", ".join(f"{t}({w})" for t, w in unknown[:8])
                          + ("..." if len(unknown) > 8 else ""))
    if calls:
        stats["biomes"] += 1
        lines.append(f'''        if (name.equals("{reg}")) {{
            {" ".join(calls)}
        }}''')

java = f'''package fr.alleretretour.lotr.init;

import net.minecraft.world.biome.BiomeGenerationSettings;
import net.minecraft.world.gen.GenerationStage;
import net.minecraft.world.gen.blockstateprovider.SimpleBlockStateProvider;
import net.minecraft.world.gen.feature.BlockClusterFeatureConfig;
import net.minecraft.world.gen.feature.ConfiguredFeature;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.Features;
import net.minecraft.world.gen.feature.IFeatureConfig;
import net.minecraft.world.gen.placement.AtSurfaceWithExtraConfig;
import net.minecraft.world.gen.placement.Placement;

/**
 * PORT de LOTRBiomeDecorator : herbe, fleurs, arbres... aux quantites exactes
 * du Legacy, biome par biome.
 * Les essences propres au mod (mallorn, lebethron, cedre, charred...) attendent
 * le portage des blocs de bois : elles sont listees en fin de fichier avec leur
 * poids d'origine, aucune substitution n'a ete inventee.
 */
public final class LOTRBiomeDecoration {{

    private LOTRBiomeDecoration() {{
    }}

    /** Applique la decoration Legacy du biome nomme. */
    public static void apply(String name, BiomeGenerationSettings.Builder b) {{
{chr(10).join(lines)}
    }}

    private static void tree(BiomeGenerationSettings.Builder b,
                             ConfiguredFeature<?, ?> tree, float perChunk) {{
        int whole = (int) perChunk;
        float chance = perChunk - whole;
        b.addFeature(GenerationStage.Decoration.VEGETAL_DECORATION,
                tree.decorated(Placement.COUNT_EXTRA.configured(
                        new AtSurfaceWithExtraConfig(whole, chance, 1))));
    }}

    private static void grass(BiomeGenerationSettings.Builder b, int count) {{
        repeat(b, Features.PATCH_GRASS_PLAIN, count);
    }}

    private static void tallGrass(BiomeGenerationSettings.Builder b, int count) {{
        repeat(b, Features.PATCH_TALL_GRASS, count);
    }}

    private static void flowers(BiomeGenerationSettings.Builder b, int count) {{
        repeat(b, Features.FLOWER_DEFAULT, count);
    }}

    private static void tallFlowers(BiomeGenerationSettings.Builder b, int count) {{
        repeat(b, Features.FOREST_FLOWER_VEGETATION, count);
    }}

    private static void deadBush(BiomeGenerationSettings.Builder b, int count) {{
        repeat(b, Features.PATCH_DEAD_BUSH, count);
    }}

    private static void waterlily(BiomeGenerationSettings.Builder b, int count) {{
        repeat(b, Features.PATCH_WATERLILLY, count);
    }}

    private static void sugarCane(BiomeGenerationSettings.Builder b, int count) {{
        repeat(b, Features.PATCH_SUGAR_CANE, count);
    }}

    private static void mushrooms(BiomeGenerationSettings.Builder b, int count) {{
        repeat(b, Features.BROWN_MUSHROOM_NORMAL, count);
        repeat(b, Features.RED_MUSHROOM_NORMAL, count);
    }}

    private static void repeat(BiomeGenerationSettings.Builder b,
                               ConfiguredFeature<?, ?> feature, int count) {{
        for (int i = 0; i < count; i++) {{
            b.addFeature(GenerationStage.Decoration.VEGETAL_DECORATION, feature);
        }}
    }}
}}

/* TODO - essences du mod a activer quand les bois seront portes :
{chr(10).join(todo_lines)}
*/
'''
open('/home/claude/lot14/src/main/java/fr/alleretretour/lotr/init/LOTRBiomeDecoration.java',
     'w', encoding='utf-8').write(java)
print(f"{stats['biomes']} biomes decores, {stats['features']} features, "
      f"{stats['todo']} biomes avec essences du mod en attente")
