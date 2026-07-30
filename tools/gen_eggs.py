#!/usr/bin/env python3
# -*- coding: utf-8 -*-
"""Regenere LOTRSpawnEggs : couleurs EXACTES du Legacy (registerCreature),
oeufs regroupes et ordonnes par faction, onglet creatif dedie (tabSpawn)."""
import json, re, os

SRC = '/home/claude/lot-final10/src/main/java/fr/alleretretour/lotr'
OUT = '/home/claude/lot11/src/main/java/fr/alleretretour/lotr'
os.makedirs(f'{OUT}/init', exist_ok=True)
LEGACY = json.load(open('/tmp/eggs_legacy.json'))

# ordre des factions = enum du depot
fac_src = open('/home/claude/lotr-renaissance/src/main/java/fr/alleretretour/lotr/fac/LOTRFaction.java').read()
m = re.search(r'public enum LOTRFaction \{\s*(.*?);', fac_src, re.S)
FACTIONS = [f.strip().split('(')[0].strip() for f in re.split(r',(?![^(]*\))', m.group(1)) if f.strip()]

# corpus complet des classes d'entites (tous les lots + depot)
ROOTS = ['/home/claude/lotr-renaissance/src/main/java/fr/alleretretour/lotr/entity']
import glob as _g
for d in sorted(_g.glob('/home/claude/lot*/src/main/java/fr/alleretretour/lotr/entity')) + \
         sorted(_g.glob('/home/claude/megalot/src/main/java/fr/alleretretour/lotr/entity')) + \
         sorted(_g.glob('/home/claude/fix*/src/main/java/fr/alleretretour/lotr/entity')):
    ROOTS.append(d)
classes = {}
for R in ROOTS:
    for root, _, files in os.walk(R):
        for f in files:
            if f.startswith('LOTREntity') and f.endswith('.java'):
                classes[f[:-5]] = open(os.path.join(root, f), encoding='utf-8').read()

def faction_of(cls, depth=0):
    src = classes.get(cls if cls.startswith("LOTREntity") else "LOTREntity" + cls)
    if src is None or depth > 8:
        return None
    m = re.search(r'return\s+(?:fr\.alleretretour\.lotr\.fac\.)?LOTRFaction\.(\w+)', src)
    if m:
        return m.group(1)
    p = re.search(r'extends\s+(LOTREntity\w+)', src)
    return faction_of(p.group(1), depth + 1) if p else None


# oeufs existants : reg -> classe
eggs_src = open(f'{SRC}/init/LOTRSpawnEggs.java').read()
entries = re.findall(r'RegistryObject<Item> ([A-Z0-9_]+)_EGG =\s*\n\s*LOTRItems\.ITEMS\.register\("([a-z0-9_]+)_spawn_egg",\s*\n\s*\(\) -> new LOTRSpawnEgg\(LOTREntities\.([A-Z0-9_]+),', eggs_src)
ent_src = open(f'{SRC}/init/LOTREntities.java').read()
reg_to_class = dict(re.findall(r'LOTREntity(\w+)>>\s*\n\s*([A-Z0-9_]+) = ENTITIES\.register', ent_src))
reg_to_class = {v: k for k, v in reg_to_class.items()}  # CONST -> classe

def legacy_colors(cls):
    if cls in LEGACY:
        return LEGACY[cls]
    # variantes de nommage du port
    # noms differents entre le Legacy et notre port (valeurs Legacy conservees)
    alt = {"BreeRuffian": "RuffianBrute",        # meme couleur que les vauriens
           "MoredainTrader": "MoredainHuntsman"  # table du chasseur (cf. lot marchands)
           }.get(cls)
    if alt and alt in LEGACY:
        return LEGACY[alt]
    return None

grouped, missing = {}, []
for const, reg, ent_const in entries:
    cls = reg_to_class.get(ent_const)
    fac = faction_of(cls) if cls else None
    col = legacy_colors(cls) if cls else None
    if col is None:
        # couleur non trouvee : on conserve celle du port
        mcol = re.search(r'LOTREntities\.' + ent_const + r',\s*(0x[0-9A-Fa-f]{6}),\s*(0x[0-9A-Fa-f]{6})',
                         eggs_src)
        col = (int(mcol.group(1), 16), int(mcol.group(2), 16)) if mcol else (0x999999, 0x333333)
        missing.append(cls or reg)
    grouped.setdefault(fac or "UNALIGNED", []).append((const, reg, ent_const, col))

order = [f for f in FACTIONS if f in grouped] + [f for f in grouped if f not in FACTIONS]
body, total = [], 0
for fac in order:
    body.append(f"    // ===== {fac} =====\n")
    for const, reg, ent_const, (p, s) in sorted(grouped[fac], key=lambda e: e[1]):
        body.append(f'''    public static final RegistryObject<Item> {const}_EGG =
            LOTRItems.ITEMS.register("{reg}_spawn_egg",
                    () -> new LOTRSpawnEgg(LOTREntities.{ent_const}, 0x{p:06X}, 0x{s:06X}));

''')
        total += 1

java = f'''package fr.alleretretour.lotr.init;

import net.minecraft.entity.EntityType;
import net.minecraft.item.Item;
import net.minecraft.item.SpawnEggItem;
import net.minecraft.nbt.CompoundNBT;
import net.minecraftforge.fml.RegistryObject;

import javax.annotation.Nullable;

/**
 * Oeufs d'apparition des PNJ - onglet creatif dedie (PORT de
 * LOTRCreativeTabs.tabSpawn), regroupes et ordonnes PAR FACTION.
 * Couleurs EXACTES du Legacy (LOTREntities.registerCreature).
 */
public class LOTRSpawnEggs {{

{"".join(body)}    private LOTRSpawnEggs() {{
    }}

    /** Declenche l'enregistrement des champs statiques. */
    public static void init() {{
    }}

    static class LOTRSpawnEgg extends SpawnEggItem {{

        private final RegistryObject<? extends EntityType<?>> typeRO;

        @SuppressWarnings("ConstantConditions")
        LOTRSpawnEgg(RegistryObject<? extends EntityType<?>> type, int primary, int secondary) {{
            super(null, primary, secondary,
                    new Item.Properties().tab(LOTRCreativeTabs.TAB_SPAWN));
            this.typeRO = type;
        }}

        @Override
        public EntityType<?> getType(@Nullable CompoundNBT nbt) {{
            if (nbt != null && nbt.contains("EntityTag", 10)) {{
                EntityType<?> fromNbt = super.getType(nbt);
                if (fromNbt != null) {{
                    return fromNbt;
                }}
            }}
            return typeRO.get();
        }}
    }}
}}
'''
open(f'{OUT}/init/LOTRSpawnEggs.java', 'w').write(java)
print(f"{total} oeufs, {len(order)} factions")
print("couleurs Legacy trouvees :", total - len(missing), "| conservees :", len(missing))
if missing:
    print("  sans couleur Legacy :", sorted(missing)[:12])
