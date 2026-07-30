#!/usr/bin/env python3
# -*- coding: utf-8 -*-
"""Porte-bannieres restants : items + entites heritant de leur guerrier."""
import json, os, re, shutil, sys
sys.path.insert(0, "/home/claude")
import usine as U

LEGB = "/home/claude/lotr-legacy/src/main/resources/assets/lotr/item/banner"
LEGL = "/home/claude/lotr-legacy/src/main/resources/assets/lotr/lang/fr_FR.lang"
B = "/home/claude/lot-final8/src/main/java/fr/alleretretour/lotr"
BRES = "/home/claude/lot-final8/src/main/resources/assets/lotr"
OUT = "/home/claude/lot-bannieres4"
PKG = f"{OUT}/src/main/java/fr/alleretretour/lotr"
RES = f"{OUT}/src/main/resources/assets/lotr"
for d in (f"{PKG}/entity/npc", f"{PKG}/init", f"{PKG}/client", f"{PKG}/hire",
          f"{RES}/textures/item", f"{RES}/models/item", f"{RES}/lang"):
    os.makedirs(d, exist_ok=True)

# (classe, reg, reg parent, classe parent, item banniere, texture Legacy, modele)
B_LIST = [
 ("MordorBannerBearer","mordor_banner_bearer","mordor_orc","MordorOrc","mordor_banner","banner_mordor","orc"),
 ("MinasMorgulBannerBearer","minas_morgul_banner_bearer","mordor_orc","MordorOrc","minas_morgul_banner","banner_minasMorgul","orc"),
 ("UrukHaiBannerBearer","uruk_hai_banner_bearer","uruk_hai","UrukHai","isengard_banner","banner_isengard","orc"),
 ("GundabadBannerBearer","gundabad_banner_bearer","gundabad_orc","GundabadOrc","gundabad_banner","banner_gundabad","orc"),
 ("AngmarBannerBearer","angmar_banner_bearer","angmar_orc","AngmarOrc","angmar_banner","banner_angmar","orc"),
 ("WoodElfBannerBearer","wood_elf_banner_bearer","wood_elf_warrior","WoodElfWarrior","wood_elf_banner","banner_mirkwood",None),
 ("DaleBannerBearer","dale_banner_bearer","dale_soldier","DaleSoldier","dale_banner","banner_dale",None),
 ("EsgarothBannerBearer","esgaroth_banner_bearer","dale_soldier","DaleSoldier","esgaroth_banner","banner_esgaroth",None),
 ("UmbarBannerBearer","umbar_banner_bearer","umbar_warrior","UmbarWarrior","umbar_banner","banner_umbar",None),
 ("BreeBannerBearer","bree_banner_bearer","bree_guard","BreeGuard","bree_banner","banner_bree",None),
 ("DorwinionBannerBearer","dorwinion_banner_bearer","dorwinion_guard","DorwinionGuard","dorwinion_banner","banner_dorwinion",None),
 ("RangerIthilienBannerBearer","ranger_ithilien_banner_bearer","ranger_ithilien","RangerIthilien","ithilien_banner","banner_ithilien",None),
]
BANNER_KEY = {"mordor_banner":"mordor","minas_morgul_banner":"minasMorgul","isengard_banner":"isengard",
 "gundabad_banner":"gundabad","angmar_banner":"angmar","wood_elf_banner":"mirkwood","dale_banner":"dale",
 "esgaroth_banner":"esgaroth","umbar_banner":"umbar","bree_banner":"bree","dorwinion_banner":"dorwinion",
 "ithilien_banner":"ithilien"}

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

# 1. items
items, seen = [], set()
for cls, reg, preg, pcls, item_id, tex, model in B_LIST:
    if item_id in seen:
        continue
    seen.add(item_id)
    shutil.copy(f"{LEGB}/{tex}.png", f"{RES}/textures/item/{item_id}.png")
    json.dump({"parent": "minecraft:item/handheld",
               "textures": {"layer0": f"lotr:item/{item_id}"}, "display": DISPLAY},
              open(f"{RES}/models/item/{item_id}.json", "w"), indent=2)
    items.append(f'''    public static final RegistryObject<Item> {item_id.upper()} =
            LOTRItems.ITEMS.register("{item_id}",
                    () -> new fr.alleretretour.lotr.item.LOTRItemBanner(
                            new Item.Properties().tab(LOTRItems.TAB_LOTR).stacksTo(1)));
''')
src = open(f"{B}/init/LOTRItemsBanners.java", encoding="utf-8").read()
src = src.replace("    private LOTRItemsBanners() {",
                  "    // ===== bannieres (lot 9) =====\n" + "".join(items)
                  + "\n    private LOTRItemsBanners() {", 1)
open(f"{PKG}/init/LOTRItemsBanners.java", "w").write(src)

# 2. entites
for cls, reg, preg, pcls, item_id, tex, model in B_LIST:
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

# 3. carrefours (clones du parent)
NPCP = "fr.alleretretour.lotr.entity.npc"
ent = open(f"{B}/init/LOTREntities.java").read()
cs = open(f"{B}/client/ClientSetup.java").read()
mod = open(f"{B}/LOTRMod.java").read()
eggs = open(f"{B}/init/LOTRSpawnEggs.java").read()
ent_add, mod_add, egg_add, cs_add = [], [], [], []
for cls, reg, preg, pcls, item_id, tex, model in B_LIST:
    m = re.search(r'\.sized\(([\d.]+)f, ([\d.]+)f\)\.build\("' + preg + r'"\)', ent)
    assert m, preg
    ent_add.append(f"""    public static final RegistryObject<EntityType<{NPCP}.LOTREntity{cls}>>
            {reg.upper()} = ENTITIES.register("{reg}",
            () -> EntityType.Builder.of(
                    {NPCP}.LOTREntity{cls}::new,
                    net.minecraft.entity.EntityClassification.CREATURE)
                    .sized({m.group(1)}f, {m.group(2)}f).build("{reg}"));
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
    ent.rstrip()[:-1].rstrip() + "\n\n    // ===== PORTE-BANNIERES (lot 9) =====\n" + "".join(ent_add) + "}\n")
open(f"{PKG}/LOTRMod.java", "w").write(
    U.insert_before_method_end(mod, "private void onEntityAttributes", "\n" + "\n".join(mod_add)))
open(f"{PKG}/client/ClientSetup.java", "w").write(
    U.insert_before_method_end(cs, "private static void defineRenderers()",
                               "\n        // --- porte-bannieres (lot 9) ---\n" + "\n".join(cs_add)))
open(f"{PKG}/init/LOTRSpawnEggs.java", "w").write(
    eggs.replace("    public static void init() {", "".join(egg_add) + "\n    public static void init() {", 1))

# 4. lang
for loc in ("fr_fr", "en_us"):
    d = json.load(open(f"{BRES}/lang/{loc}.json", encoding="utf-8"))
    for cls, reg, preg, pcls, item_id, tex, model in B_LIST:
        name = (FR.get(f"entity.lotr.{cls}.name") if loc == "fr_fr"
                else U.EN.get(f"entity.lotr.{cls}.name")) or reg
        d[f"entity.lotr.{reg}"] = name
        d[f"item.lotr.{reg}_spawn_egg"] = (f"Oeuf d'apparition {name}" if loc == "fr_fr"
                                           else f"{name} Spawn Egg")
        bn = (FR.get(f"item.lotr:banner.{BANNER_KEY[item_id]}.name") if loc == "fr_fr"
              else U.EN.get(f"item.lotr:banner.{BANNER_KEY[item_id]}.name"))
        d[f"item.lotr.{item_id}"] = bn or item_id
    json.dump(d, open(f"{RES}/lang/{loc}.json", "w", encoding="utf-8"),
              ensure_ascii=False, indent=2, sort_keys=True)

print(f"{len(B_LIST)} porte-bannieres, {len(seen)} bannieres")
for cls, reg, *_ in B_LIST:
    print(f"  {reg}: {FR.get(f'entity.lotr.{cls}.name', '?')}")
