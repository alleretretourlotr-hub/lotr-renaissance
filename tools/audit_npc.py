#!/usr/bin/env python3
# -*- coding: utf-8 -*-
"""Audit + regeneration fidele des PNJ : PV, pools d'armes, armures du Legacy."""
import json, os, re

LEG = "/home/claude/lotr-legacy/src/main/java/lotr/common/entity/npc"
ids = set(json.load(open("/home/claude/item_ids.json")))

TYPES = {
    "throwingAxe": "throwing_axe", "sword": "sword", "spear": "spear",
    "battleaxe": "battleaxe", "hammer": "hammer", "pike": "pike", "dagger": "dagger",
    "axe": "axe", "pickaxe": "pickaxe", "scimitar": "scimitar", "club": "club",
    "mace": "mace", "bow": "bow", "crossbow": "crossbow", "polearm": "polearm",
    "poleaxe": "poleaxe", "lance": "lance", "longspear": "longspear",
    "helmet": "helmet", "body": "chestplate", "legs": "leggings", "boots": "boots",
    "blowgun": "blowgun",
}
VANILLA = {"ironSword": "sword_iron_hint"}

def snake(s):
    return re.sub(r"(?<=[a-z0-9])(?=[A-Z])", "_", s).lower()

def map_field(ns, field):
    if ns == "Items":
        v = snake(field)  # iron_sword etc. -> vanilla
        return "minecraft:" + v
    poisoned = field.endswith("Poisoned")
    if poisoned:
        field = field[:-8]
    for camel, suf in sorted(TYPES.items(), key=lambda kv: -len(kv[0])):
        if field.startswith(camel) and len(field) > len(camel):
            fac = snake(field[len(camel):])
            cand = f"{fac}_poisoned_{suf}" if poisoned else f"{fac}_{suf}"
            if cand in ids:
                return "lotr:" + cand
            return None
    cand = snake(field)
    return "lotr:" + cand if cand in ids else None

def read_class(name):
    p = f"{LEG}/LOTREntity{name}.java"
    return open(p, encoding="utf-8").read() if os.path.exists(p) else None

def parent_of(src):
    m = re.search(r"extends LOTREntity(\w+)", src)
    return m.group(1) if m else None

def find_health(name):
    """Remonte la chaine de classes jusqu'au setBaseValue(maxHealth)."""
    cur = name
    for _ in range(8):
        src = read_class(cur)
        if src is None:
            return None, cur
        m = re.search(r"maxHealth\)\.setBaseValue\(([\d.]+)", src)
        if m:
            return float(m.group(1)), cur
        cur = parent_of(src)
        if cur is None:
            return None, None
    return None, None

ARMOR_SUFFIX = {"helmet": "HEAD", "chestplate": "CHEST", "leggings": "LEGS", "boots": "FEET"}

def method_body(src, method):
    i = src.find(method)
    if i < 0:
        return ""
    j = src.find("{", i)
    depth = 0
    for k in range(j, len(src)):
        if src[k] == "{":
            depth += 1
        elif src[k] == "}":
            depth -= 1
            if depth == 0:
                return src[j:k]
    return ""

def gear_of(name):
    """Pool d'armes + armures depuis onSpawnWithEgg, en remontant la chaine."""
    weapons, ranged, armor = [], [], {}
    cur = name
    for _ in range(8):
        src = read_class(cur)
        if src is None:
            break
        body = method_body(src, "onSpawnWithEgg")
        for line in body.splitlines():
            for ns, field in re.findall(r"new ItemStack\((LOTRMod|Items)\.(\w+)", line):
                mapped = map_field(ns, field)
                if mapped is None:
                    continue
                suffix = mapped.rsplit("_", 1)[-1]
                if suffix in ARMOR_SUFFIX:
                    armor.setdefault(ARMOR_SUFFIX[suffix], mapped)
                elif "RangedWeapon" in line or suffix in ("bow", "crossbow", "blowgun"):
                    if mapped not in ranged:
                        ranged.append(mapped)
                elif "MeleeWeapon" in line or "IdleItem" not in line:
                    if mapped not in weapons:
                        weapons.append(mapped)
        cur = parent_of(src)
    return weapons, ranged, armor

# reg de notre port -> classe Legacy
NPCS = {
 "gondor_soldier": "GondorSoldier", "gondor_archer": "GondorArcher",
 "gondor_tower_guard": "GondorTowerGuard", "gondorian_captain": "GondorianCaptain",
 "gondor_levyman": "GondorLevyman", "gondor_blacksmith": "GondorBlacksmith",
 "mordor_orc": "MordorOrc", "mordor_orc_archer": "MordorOrcArcher", "uruk_hai": "UrukHai",
 "rohirrim_warrior": "RohirrimWarrior", "rohirrim_archer": "RohirrimArcher",
 "rohirrim_marshal": "RohirrimMarshal", "rohan_shieldmaiden": "RohanShieldmaiden",
 "galadhrim_warrior": "GaladhrimWarrior", "galadhrim_warden": "GaladhrimWarden",
 "galadhrim_lord": "GaladhrimLord", "high_elf_warrior": "HighElfWarrior",
 "high_elf_lord": "HighElfLord", "wood_elf_warrior": "WoodElfWarrior",
 "wood_elf_scout": "WoodElfScout", "wood_elf_captain": "WoodElfCaptain",
 "rivendell_warrior": "RivendellWarrior", "rivendell_lord": "RivendellLord",
 "dwarf": "Dwarf", "dwarf_warrior": "DwarfWarrior", "dwarf_commander": "DwarfCommander",
 "dwarf_axe_thrower": "DwarfAxeThrower", "blue_dwarf_warrior": "BlueDwarfWarrior",
 "blue_dwarf_axe_thrower": "BlueDwarfAxeThrower",
 "hobbit": "Hobbit", "hobbit_bounder": "HobbitBounder",
 "bree_man": "BreeMan", "bree_guard": "BreeGuard",
 "ranger_north": "RangerNorth", "ranger_north_captain": "RangerNorthCaptain",
 "dale_soldier": "DaleSoldier", "dale_archer": "DaleArcher", "dale_levyman": "DaleLevyman",
 "dorwinion_guard": "DorwinionGuard", "dorwinion_elf_warrior": "DorwinionElfWarrior",
 "dorwinion_elf_archer": "DorwinionElfArcher", "dorwinion_crossbower": "DorwinionCrossbower",
 "angmar_orc": "AngmarOrc", "angmar_orc_archer": "AngmarOrcArcher",
 "angmar_hillman_warrior": "AngmarHillmanWarrior",
 "gundabad_orc": "GundabadOrc", "gundabad_orc_archer": "GundabadOrcArcher",
 "gundabad_uruk": "GundabadUruk",
 "dol_guldur_orc": "DolGuldurOrc", "dol_guldur_orc_archer": "DolGuldurOrcArcher",
 "dunlending": "Dunlending", "dunlending_warrior": "DunlendingWarrior",
 "dunlending_berserker": "DunlendingBerserker",
 "near_haradrim_warrior": "NearHaradrimWarrior", "near_haradrim_archer": "NearHaradrimArcher",
 "corsair": "Corsair", "umbar_warrior": "UmbarWarrior", "umbar_archer": "UmbarArcher",
 "easterling_warrior": "EasterlingWarrior", "easterling_archer": "EasterlingArcher",
 "easterling_gold_warrior": "EasterlingGoldWarrior",
 "moredain_warrior": "MoredainWarrior", "moredain_huntsman": "MoredainHuntsman",
 "tauredain_warrior": "TauredainWarrior", "tauredain_blowgunner": "TauredainBlowgunner",
 "half_troll": "HalfTroll", "half_troll_warrior": "HalfTrollWarrior",
 "uruk_hai_crossbower": "UrukHaiCrossbower",
}

result = {}
for reg, legacy in NPCS.items():
    hp, hp_src = find_health(legacy)
    weapons, ranged, armor = gear_of(legacy)
    result[reg] = {"legacy": legacy, "hp": hp, "hp_from": hp_src,
                   "weapons": weapons, "ranged": ranged, "armor": armor}

json.dump(result, open("/home/claude/npc_fidelity.json", "w"), indent=1)
missing = [r for r, d in result.items() if d["hp"] is None]
print(f"{len(result)} PNJ analyses ; sans PV Legacy trouves : {missing}")
for reg in ["dunlending_warrior", "gondor_soldier", "dwarf_warrior", "half_troll_warrior"]:
    d = result[reg]
    print(f"\n{reg} <- {d['legacy']} : PV {d['hp']} (de {d['hp_from']})")
    print("  armes:", d["weapons"][:8])
    print("  tir:", d["ranged"], " armure:", d["armor"])
