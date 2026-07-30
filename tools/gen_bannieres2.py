#!/usr/bin/env python3
# -*- coding: utf-8 -*-
"""Porte-bannieres des factions du mega-lot 2 : items + entites heritant de
leur guerrier + rendu clone du parent (LOTRLayerBanner s'occupe de l'affichage).
Toutes les valeurs (parent, type de banniere, nom) viennent du Legacy."""
import json, os, re, shutil, sys
sys.path.insert(0, "/home/claude")
import usine as U

LEGB = "/home/claude/lotr-legacy/src/main/resources/assets/lotr/item/banner"
LEGL = "/home/claude/lotr-legacy/src/main/resources/assets/lotr/lang/fr_FR.lang"
BASE = "/home/claude/lot-troupes3/src/main/java/fr/alleretretour/lotr"
BASE_RES = "/home/claude/lot-troupes3/src/main/resources/assets/lotr"
ITEMS_BASE = "/home/claude/lot-bannieres-v2/src/main/java/fr/alleretretour/lotr/init/LOTRItemsBanners.java"
OUT = "/home/claude/lot-bannieres3"
PKG = f"{OUT}/src/main/java/fr/alleretretour/lotr"
RES = f"{OUT}/src/main/resources/assets/lotr"
for d in (f"{PKG}/entity/npc", f"{PKG}/init", f"{PKG}/client", f"{PKG}/hire",
          f"{RES}/textures/item", f"{RES}/models/item", f"{RES}/lang"):
    os.makedirs(d, exist_ok=True)

# (classe Legacy, reg, parent reg, parent classe, item banniere, texture Legacy)
B = [
 ("AngmarHillmanBannerBearer", "angmar_hillman_banner_bearer", "angmar_hillman_warrior",
  "AngmarHillmanWarrior", "rhudaur_banner", "banner_rhudaur"),
 ("DolGuldurBannerBearer", "dol_guldur_banner_bearer", "dol_guldur_orc",
  "DolGuldurOrc", "dol_guldur_banner", "banner_dolGuldur"),
 ("DunlendingBannerBearer", "dunlending_banner_bearer", "dunlending_warrior",
  "DunlendingWarrior", "dunland_banner", "banner_dunland"),
 ("EasterlingBannerBearer", "easterling_banner_bearer", "easterling_warrior",
  "EasterlingWarrior", "rhun_banner", "banner_rhun"),
 ("HalfTrollBannerBearer", "half_troll_banner_bearer", "half_troll_warrior",
  "HalfTrollWarrior", "half_troll_banner", "banner_halfTroll"),
 ("HarnedorBannerBearer", "harnedor_banner_bearer", "harnedor_warrior",
  "HarnedorWarrior", "near_harad_banner", "banner_nearHarad"),
 ("NearHaradBannerBearer", "near_harad_banner_bearer", "near_haradrim_warrior",
  "NearHaradrimWarrior", "near_harad_banner", "banner_nearHarad"),
 ("MoredainBannerBearer", "moredain_banner_bearer", "moredain_warrior",
  "MoredainWarrior", "moredain_banner", "banner_moredain"),
 ("TauredainBannerBearer", "tauredain_banner_bearer", "tauredain_warrior",
  "TauredainWarrior", "tauredain_banner", "banner_tauredain"),
]

FR = {}
for line in open(LEGL, encoding="utf-8"):
    if "=" in line:
        k, v = line.split("=", 1)
        FR[k.strip()] = v.strip()

DISPLAY = {
 "thirdperson_righthand": {"rotation": [0, -90, 0], "translation": [0, 7, 0.5], "scale": [1.8, 1.8, 1.8]},
 "thirdperson_lefthand": {"rotation": [0, 90, 0], "translation": [0, 7, 0.5], "scale": [1.8, 1.8, 1.8]},
 "firstperson_righthand": {"rotation": [0, -90, 0], "translation": [1.13, 4.5, 1.13], "scale": [0.8, 0.8, 0.8]},
 "firstperson_lefthand": {"rotation": [0, 90, 0], "translation": [1.13, 4.5, 1.13], "scale": [0.8, 0.8, 0.8]},
}

# ---------- 1. items de banniere (dedoublonnes) ----------
items, seen = [], set()
for cls, reg, preg, pcls, item_id, tex in B:
    if item_id in seen:
        continue
    seen.add(item_id)
    src = f"{LEGB}/{tex}.png"
    assert os.path.exists(src), src
    shutil.copy(src, f"{RES}/textures/item/{item_id}.png")
    json.dump({"parent": "minecraft:item/handheld",
               "textures": {"layer0": f"lotr:item/{item_id}"},
               "display": DISPLAY},
              open(f"{RES}/models/item/{item_id}.json", "w"), indent=2)
    items.append(f'''    public static final RegistryObject<Item> {item_id.upper()} =
            LOTRItems.ITEMS.register("{item_id}",
                    () -> new fr.alleretretour.lotr.item.LOTRItemBanner(
                            new Item.Properties().tab(LOTRItems.TAB_LOTR).stacksTo(1)));
''')
src = open(ITEMS_BASE, encoding="utf-8").read()
src = src.replace("    private LOTRItemsBanners() {",
                  "    // ===== bannieres des factions du mega-lot 2 =====\n"
                  + "".join(items) + "\n    private LOTRItemsBanners() {", 1)
open(f"{PKG}/init/LOTRItemsBanners.java", "w").write(src)

# ---------- 2. entites ----------
for cls, reg, preg, pcls, item_id, tex in B:
    open(f"{PKG}/entity/npc/LOTREntity{cls}.java", "w").write(f'''package fr.alleretretour.lotr.entity.npc;

import net.minecraft.entity.EntityType;
import net.minecraft.world.World;

/** PORT de LOTREntity{cls} (Legacy) : {pcls} portant sa banniere. */
public class LOTREntity{cls} extends LOTREntity{pcls} implements LOTRBannerBearer {{

    public LOTREntity{cls}(EntityType<? extends LOTREntity{cls}> type, World world) {{
        super((EntityType) type, world);
    }}

    @Override
    public String getBannerItemId() {{
        return "lotr:{item_id}";
    }}
}}
''')

# ---------- 3. carrefours ----------
NPCP = "fr.alleretretour.lotr.entity.npc"
ent = open(f"{BASE}/init/LOTREntities.java").read()
cs = open(f"{BASE}/client/ClientSetup.java").read()
mod = open(f"{BASE}/LOTRMod.java").read()
eggs = open(f"{BASE}/init/LOTRSpawnEggs.java").read()

ent_add, mod_add, egg_add, cs_add = [], [], [], []
for cls, reg, preg, pcls, item_id, tex in B:
    # taille et rendu : clones exacts du parent
    m = re.search(r'\.sized\(([\d.]+)f, ([\d.]+)f\)\.build\("' + preg + r'"\)', ent)
    assert m, preg
    w, h = m.group(1), m.group(2)
    ent_add.append(f"""    public static final RegistryObject<EntityType<{NPCP}.LOTREntity{cls}>>
            {reg.upper()} = ENTITIES.register("{reg}",
            () -> EntityType.Builder.of(
                    {NPCP}.LOTREntity{cls}::new,
                    net.minecraft.entity.EntityClassification.CREATURE)
                    .sized({w}f, {h}f).build("{reg}"));
""")
    mod_add.append(f"        event.put(fr.alleretretour.lotr.init.LOTREntities.{reg.upper()}.get(),\n"
                   f"                {NPCP}.LOTREntity{cls}.createAttributes().build());")
    md = re.search(r'def\("' + preg + r'", [^;]*?;\n', cs, re.S)
    assert md, preg
    clone = md.group(0).replace(f'def("{preg}"', f'def("{reg}"', 1)
    clone = clone.replace(f"LOTREntities.{preg.upper()}", f"LOTREntities.{reg.upper()}", 1)
    cs_add.append("        " + clone.strip())
    me = re.search(r'RegistryObject<Item> ' + preg.upper() + r'_EGG =\n.*?0x([0-9A-F]{6}), 0x([0-9A-F]{6})\)\);', eggs, re.S)
    p, s = (me.group(1), me.group(2)) if me else ("6B5A45", "2A2320")
    egg_add.append(f"""    public static final RegistryObject<Item> {reg.upper()}_EGG =
            LOTRItems.ITEMS.register("{reg}_spawn_egg",
                    () -> new LOTRSpawnEgg(LOTREntities.{reg.upper()}, 0x{p}, 0x{s}));
""")
    open(f"{RES}/models/item/{reg}_spawn_egg.json", "w").write(
        '{\n  "parent": "minecraft:item/template_spawn_egg"\n}\n')

open(f"{PKG}/init/LOTREntities.java", "w").write(
    ent.rstrip()[:-1].rstrip() + "\n\n    // ===== PORTE-BANNIERES (mega-lot 2) =====\n"
    + "\n".join(ent_add) + "\n}\n")
open(f"{PKG}/LOTRMod.java", "w").write(
    U.insert_before_method_end(mod, "private void onEntityAttributes", "\n" + "\n".join(mod_add)))
open(f"{PKG}/client/ClientSetup.java", "w").write(
    U.insert_before_method_end(cs, "private static void defineRenderers()",
                               "\n        // --- porte-bannieres (mega-lot 2) ---\n" + "\n".join(cs_add)))
open(f"{PKG}/init/LOTRSpawnEggs.java", "w").write(
    eggs.replace("    public static void init() {", "".join(egg_add) + "\n    public static void init() {", 1))

# ---------- 4. lang ----------
BANNER_KEY = {"rhudaur_banner": "rhudaur", "dol_guldur_banner": "dolGuldur",
              "dunland_banner": "dunland", "rhun_banner": "rhun",
              "half_troll_banner": "halfTroll", "near_harad_banner": "nearHarad",
              "moredain_banner": "moredain", "tauredain_banner": "tauredain"}
for loc in ("fr_fr", "en_us"):
    d = json.load(open(f"{BASE_RES}/lang/{loc}.json", encoding="utf-8"))
    for cls, reg, preg, pcls, item_id, tex in B:
        name = FR.get(f"entity.lotr.{cls}.name") if loc == "fr_fr" else None
        name = name or U.EN.get(f"entity.lotr.{cls}.name") or reg
        d[f"entity.lotr.{reg}"] = name
        d[f"item.lotr.{reg}_spawn_egg"] = (f"Oeuf d'apparition {name}" if loc == "fr_fr"
                                           else f"{name} Spawn Egg")
        bn = (FR.get(f"item.lotr:banner.{BANNER_KEY[item_id]}.name") if loc == "fr_fr"
              else U.EN.get(f"item.lotr:banner.{BANNER_KEY[item_id]}.name"))
        d[f"item.lotr.{item_id}"] = bn or item_id
    json.dump(d, open(f"{RES}/lang/{loc}.json", "w", encoding="utf-8"),
              ensure_ascii=False, indent=2, sort_keys=True)

print(f"{len(B)} porte-bannieres, {len(seen)} items de banniere")
for cls, reg, *_ in B:
    print(f"  {reg}: {FR.get(f'entity.lotr.{cls}.name', '?')}")
