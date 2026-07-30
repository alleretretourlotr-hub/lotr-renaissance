#!/usr/bin/env python3
# -*- coding: utf-8 -*-
"""PORT de LOTRChestContents vers les tables de butin 1.16.5 (JSON).

Chaque LOTRChestContents(min, max, entrees) devient une loot table :
  - rolls : min..max (nombre de piles tirees, valeur exacte du Legacy) ;
  - chaque entree : poids et quantite min/max repris tels quels.
Les objets non encore portes sont ignores (jamais remplaces).
"""
import json, os, re, sys
sys.path.insert(0, "/home/claude")
import audit_npc as A

SRC = "/home/claude/lotr-legacy/src/main/java/lotr/common/world/structure/LOTRChestContents.java"
OUT = "/home/claude/lot39/src/main/resources/data/lotr/loot_tables/chests"
os.makedirs(OUT, exist_ok=True)

VANILLA = {  # champ Legacy -> id vanilla 1.16.5 (renommages inclus)
    "cooked_fished": "cooked_cod", "dye": "ink_sac", "coal": "coal",
    "record_13": "music_disc_13", "melon": "melon_slice",
}
def vanilla_id(field):
    if field in VANILLA:
        return "minecraft:" + VANILLA[field]
    return "minecraft:" + re.sub(r"(?<=[a-z0-9])(?=[A-Z])", "_", field).lower()

src = open(SRC, encoding="utf-8").read()
tables, skipped = {}, set()
pattern = re.compile(
    r"public static LOTRChestContents (\w+) = new LOTRChestContents\((\d+), (\d+), "
    r"new WeightedRandomChestContent\[\]\{(.*?)\}\)", re.S)
entry_re = re.compile(
    r"new WeightedRandomChestContent\(new ItemStack\((LOTRMod|Items|Blocks)\.(\w+)"
    r"(?:,\s*\d+(?:,\s*\d+)?)?\),\s*(\d+),\s*(\d+),\s*(\d+)\)")

for m in pattern.finditer(src):
    name, rolls_min, rolls_max, body = m.group(1), int(m.group(2)), int(m.group(3)), m.group(4)
    entries = []
    for e in entry_re.finditer(body):
        ns, field, cmin, cmax, weight = e.groups()
        if ns == "LOTRMod":
            item = A.map_field("LOTRMod", field)
        else:
            item = vanilla_id(field)
        if item is None:
            skipped.add(field)
            continue
        entries.append({
            "type": "minecraft:item",
            "name": item,
            "weight": int(weight),
            "functions": [{
                "function": "minecraft:set_count",
                "count": {"min": int(cmin), "max": int(cmax)}
            }]
        })
    if not entries:
        continue
    table = {
        "type": "minecraft:chest",
        "pools": [{
            "rolls": {"min": rolls_min, "max": rolls_max},
            "entries": entries
        }]
    }
    reg = name.lower()
    json.dump(table, open(f"{OUT}/{reg}.json", "w"), indent=2)
    tables[reg] = len(entries)

print(f"{len(tables)} tables de butin generees, "
      f"{sum(tables.values())} entrees au total")
for k in sorted(tables)[:8]:
    print(f"  {k}: {tables[k]} entrees")
print(f"objets non portes ignores : {len(skipped)}")
