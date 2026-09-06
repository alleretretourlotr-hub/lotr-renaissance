#!/usr/bin/env python3
# -*- coding: utf-8 -*-
"""Cable une structure dans LOTRFeatures + LOTRBiomeEvents du depot."""
import re, sys

REPO = '/home/claude/lotr-renaissance/src/main/java/fr/alleretretour/lotr'

def camel(reg):
    p = reg.split('_')
    return p[0] + ''.join(w.capitalize() for w in p[1:])

def wire(reg, java_class, biomes):
    CONST = reg.upper()
    C = ''.join(w.capitalize() for w in reg.split('_'))
    getter = camel(reg)

    p = f'{REPO}/init/LOTRFeatures.java'
    s = open(p, encoding='utf-8').read()
    if CONST in s:
        print(f"  {reg} : deja cable")
    else:
        anchor = '    private static ConfiguredFeature<?, ?> configuredRoads;'
        assert anchor in s
        s = s.replace(anchor, f'''    public static final RegistryObject<Feature<NoFeatureConfig>> {CONST} =
            FEATURES.register("{reg}", () -> new {java_class}(NoFeatureConfig.CODEC));

''' + anchor, 1)
        s = s.replace(anchor, anchor + f'\n    private static ConfiguredFeature<?, ?> configured{C};', 1)
        m = re.search(r'(\n        Registry\.register\(WorldGenRegistries\.CONFIGURED_FEATURE,\n[^;]*;\n)', s)
        assert m
        s = s[:m.end(1)] + f'''        configured{C} = {CONST}.get().configured(IFeatureConfig.NONE);
        Registry.register(WorldGenRegistries.CONFIGURED_FEATURE,
                new net.minecraft.util.ResourceLocation("lotr", "{reg}"), configured{C});
''' + s[m.end(1):]
        i = s.rindex('}')
        s = s[:i] + f'''
    public static ConfiguredFeature<?, ?> {getter}() {{
        return configured{C};
    }}
''' + s[i:]
        open(p, 'w', encoding='utf-8').write(s)
        print(f"  {reg} : LOTRFeatures cable")

    p = f'{REPO}/world/LOTRBiomeEvents.java'
    s = open(p, encoding='utf-8').read()
    if f'LOTRFeatures.{getter}()' in s:
        print(f"  {reg} : biomes deja cables")
        return
    lines = '\n'.join(f'''        if (name.equals("{b}")) {{
            structure(event, LOTRFeatures.{getter}(), {c});
        }}''' for b, c in sorted(biomes.items()))
    marker = '        // structures (frequences du Legacy : 1 chance sur N par chunk)'
    assert marker in s, "marqueur des structures introuvable"
    s = s.replace(marker, marker + '\n' + lines, 1)
    open(p, 'w', encoding='utf-8').write(s)
    print(f"  {reg} : {len(biomes)} biome(s) cable(s)")

if __name__ == '__main__':
    wire('angmar_shrine',
         'fr.alleretretour.lotr.world.structure.LOTRStructureAngmarShrine',
         {'angmar': 200})
