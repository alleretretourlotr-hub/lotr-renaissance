#!/usr/bin/env python3
# -*- coding: utf-8 -*-
"""USINE A LOTS : manifest JSON -> lot complet (entites, peaux, carrefours,
oeufs, lang officiel fr_FR/en_US du Legacy, defs renderers, lint).
Ne touche jamais aux modeles Blockbench/GeckoLib existants du projet.
Usage : python3 usine.py <manifest.json> <dossier_sortie>"""
import json, os, re, shutil, sys
sys.path.insert(0, "/home/claude")
import audit_npc as A
from PIL import Image

LEG = "/home/claude/lotr-legacy/src/main"
LEGL = f"{LEG}/resources/assets/lotr/lang"
MOB = f"{LEG}/resources/assets/lotr/mob"

# bases carrefours = versions les plus recentes de chaque fichier
BASES = {
 "LOTREntities": "/home/claude/lot-final7/src/main/java/fr/alleretretour/lotr/init/LOTREntities.java",
 "ClientSetup": "/home/claude/lot-final7/src/main/java/fr/alleretretour/lotr/client/ClientSetup.java",
 "LOTRMod": "/home/claude/lot-final7/src/main/java/fr/alleretretour/lotr/LOTRMod.java",
 "LOTRSpawnEggs": "/home/claude/lot-final7/src/main/java/fr/alleretretour/lotr/init/LOTRSpawnEggs.java",
 "lang": "/home/claude/lot-final7/src/main/resources/assets/lotr/lang",
 "skins": "/home/claude/megalot/src/main/resources/assets/lotr/textures/entity",
}

def lang_of(locale):
    d = {}
    for line in open(f"{LEGL}/{locale}.lang", encoding="utf-8"):
        if "=" in line and not line.startswith("#"):
            k, v = line.split("=", 1)
            d[k.strip()] = v.strip()
    return d

FR = lang_of("fr_FR")
EN = lang_of("en_US")

def gear_of_v2(name):
    weapons, ranged, armor = [], [], {}
    cur = name
    for _ in range(8):
        src = A.read_class(cur)
        if src is None:
            break
        body = A.method_body(src, "onSpawnWithEgg")
        w_here, r_here = [], []
        for line in body.splitlines():
            for ns, field in re.findall(r"new ItemStack\((LOTRMod|Items)\.(\w+)", line):
                mapped = A.map_field(ns, field)
                if mapped is None:
                    continue
                key = mapped.split(":")[-1]
                suffix = key.rsplit("_", 1)[-1]
                if suffix in A.ARMOR_SUFFIX:
                    armor.setdefault(A.ARMOR_SUFFIX[suffix], mapped)
                elif suffix in ("bow", "crossbow", "blowgun", "dart") \
                        or key.endswith("throwing_axe"):
                    r_here.append(mapped) if mapped not in r_here else None
                elif "MeleeWeapon" in line or "Weapon" in line:
                    w_here.append(mapped) if mapped not in w_here else None
        weapons = weapons or w_here
        ranged = ranged or r_here
        cur = A.parent_of(src)
    return weapons, ranged, armor

def convert_skins(src_rel, dest, out_entity_dir):
    sp = f"{MOB}/{src_rel}"
    if not os.path.isdir(sp) and os.path.exists(sp + ".png"):
        # peau unique (Legacy : warlord.png etc.)
        sp, files = os.path.dirname(sp), [os.path.basename(sp) + ".png"]
    else:
        files = sorted([f for f in os.listdir(sp) if re.fullmatch(r"\d+\.png", f)],
                       key=lambda f: int(f.split(".")[0]))
    dp = f"{out_entity_dir}/{dest}"
    os.makedirs(dp, exist_ok=True)
    sys.path.insert(0, "/home/claude/megalot/tools") if False else None
    from fix_skins_weapons_lib import convert_legacy_skin, limb_missing  # noqa
    for i, f in enumerate(files):
        img = Image.open(f"{sp}/{f}").convert("RGBA")
        if img.size != (64, 64):
            c = Image.new("RGBA", (64, 64), (0, 0, 0, 0))
            c.paste(img, (0, 0))
            img = c
        if limb_missing(img):
            img = convert_legacy_skin(img)
        img.save(f"{dp}/{i}.png")
    return len(files)

def egg_colors(path0):
    img = Image.open(path0).convert("RGBA")
    px = img.load()
    def avg(y0, y1):
        r = g = b = n = 0
        for y in range(y0, y1):
            for x in range(img.width):
                p = px[x, y]
                if p[3] > 128:
                    r += p[0]; g += p[1]; b += p[2]; n += 1
        n = max(n, 1)
        return (r // n << 16) | (g // n << 8) | (b // n)
    return avg(8, 16), avg(20, 32)

def insert_before_method_end(src, method_sig, addition):
    i = src.index(method_sig)
    j = src.index("{", i)
    depth = 0
    for k in range(j, len(src)):
        if src[k] == "{":
            depth += 1
        elif src[k] == "}":
            depth -= 1
            if depth == 0:
                return src[:k] + addition + "\n    " + src[k:]
    raise AssertionError(method_sig)

def lint(path):
    src = open(path, encoding="utf-8").read()
    assert src.count("{") == src.count("}"), f"accolades desequilibrees : {path}"
    imported = set(re.findall(r"import (?:static )?[\w.]+\.([A-Z]\w+);", src))
    declared = set(re.findall(r"(?:class|enum|interface) (\w+)", src))
    java_ok = {"String", "Math", "Override", "Nullable", "SuppressWarnings", "Optional",
               "UUID", "List", "ArrayList", "Arrays", "Collections", "HashMap", "Map",
               "Object", "Boolean", "Integer", "Float", "Double", "AssertionError"}
    stripped = re.sub(r"//.*|/\*.*?\*/", "", src, flags=re.S)
    stripped = re.sub(r'"[^"\n]*"', '""', stripped)
    stripped = re.sub(r"[\w$][\w.$]*\.([A-Z]\w+)", "", stripped)  # qualifies
    used = set(re.findall(r"(?<![\w.])([A-Z][A-Za-z0-9]+)(?=[\s(<.{])", stripped))
    missing = used - imported - declared - java_ok
    missing = {m for m in missing if not m.isupper()}  # constantes
    missing = {m for m in missing if not m.startswith("LOTREntity")}  # meme package
    assert not missing, f"symboles sans import dans {path} : {sorted(missing)}"

def run(manifest_path, outdir):
    man = json.load(open(manifest_path, encoding="utf-8"))
    lot = man["lot"]
    PKG = f"{outdir}/src/main/java/fr/alleretretour/lotr"
    RES = f"{outdir}/src/main/resources/assets/lotr"
    for d in (f"{PKG}/entity/npc", f"{PKG}/init", f"{PKG}/client",
              f"{RES}/textures/entity", f"{RES}/models/item", f"{RES}/lang"):
        os.makedirs(d, exist_ok=True)

    ent = open(BASES["LOTREntities"]).read()
    mod = open(BASES["LOTRMod"]).read()
    eggs = open(BASES["LOTRSpawnEggs"]).read()
    cs = open(BASES["ClientSetup"]).read()
    langs = {loc: json.load(open(f"{BASES['lang']}/{loc}.json", encoding="utf-8"))
             for loc in ("fr_fr", "en_us")}
    NPCP = "fr.alleretretour.lotr.entity.npc"
    ent_add, mod_add, egg_add, cs_add = [], [], [], []

    for n in man["npcs"]:
        legacy, reg = n["legacy"], n["reg"]
        cls = n.get("cls") or legacy
        hp, _ = A.find_health(legacy)
        assert hp is not None, legacy
        weapons, ranged, armor = gear_of_v2(legacy)
        variants = convert_skins(n["skin_src"], n["skin_dest"], f"{RES}/textures/entity")
        fr = FR.get(f"entity.lotr.{legacy}.name") or n.get("fr") or reg
        en = EN.get(f"entity.lotr.{legacy}.name") or n.get("en") or reg

        gear_lines = []
        if ranged:
            gear_lines.append(f'        setItemSlot(net.minecraft.inventory.EquipmentSlotType.MAINHAND, stackOf("{ranged[0]}"));')
        elif weapons:
            pool = ", ".join(f'"{w}"' for w in weapons)
            gear_lines += [f"        String[] pool = {{{pool}}};",
                "        setItemSlot(net.minecraft.inventory.EquipmentSlotType.MAINHAND,",
                "                stackOf(pool[getRandom().nextInt(pool.length)]));"]
        for slot in ("HEAD", "CHEST", "LEGS", "FEET"):
            if slot in armor:
                gear_lines.append(f'        setItemSlot(net.minecraft.inventory.EquipmentSlotType.{slot}, stackOf("{armor[slot]}"));')
        gear_java = ""
        if gear_lines:
            gear_java = f'''
    private static net.minecraft.item.ItemStack stackOf(String id) {{
        net.minecraft.item.Item item = net.minecraftforge.registries.ForgeRegistries.ITEMS
                .getValue(new net.minecraft.util.ResourceLocation(id));
        return item == null ? net.minecraft.item.ItemStack.EMPTY
                : new net.minecraft.item.ItemStack(item);
    }}

    /** PORT de onSpawnWithEgg (Legacy LOTREntity{legacy}). */
    @javax.annotation.Nullable
    @Override
    public net.minecraft.entity.ILivingEntityData finalizeSpawn(
            net.minecraft.world.IServerWorld world, net.minecraft.world.DifficultyInstance difficulty,
            net.minecraft.entity.SpawnReason reason,
            @javax.annotation.Nullable net.minecraft.entity.ILivingEntityData data,
            @javax.annotation.Nullable net.minecraft.nbt.CompoundNBT nbt) {{
{chr(10).join(gear_lines)}
        for (net.minecraft.inventory.EquipmentSlotType slot
                : net.minecraft.inventory.EquipmentSlotType.values()) {{
            setDropChance(slot, 0.05f);
        }}
        return super.finalizeSpawn(world, difficulty, reason, data, nbt);
    }}
'''
        ranged_java = ("\n    @Override\n    protected boolean isRangedNPC() {\n"
                       "        return true;\n    }\n") if ranged else ""
        speech = f'\n    @Override\n    protected String getSpeechBank() {{\n        return "{n["speech"]}";\n    }}\n' if n.get("speech") else ""
        java = f'''package fr.alleretretour.lotr.entity.npc;

import fr.alleretretour.lotr.fac.LOTRFaction;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.ai.attributes.AttributeModifierMap;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.world.World;

/** GENERE PAR L'USINE ({lot}) - port de lotr.common.entity.npc.LOTREntity{legacy} */
public class LOTREntity{cls} extends LOTREntityNPC {{

    public LOTREntity{cls}(EntityType<? extends LOTREntity{cls}> type, World world) {{
        super(type, world);
    }}

    @Override
    public LOTRFaction getFaction() {{
        return LOTRFaction.{n["faction"]};
    }}
{speech}{ranged_java}
    public static AttributeModifierMap.MutableAttribute createAttributes() {{
        return createNPCAttributes()
                .add(Attributes.MAX_HEALTH, {hp})
                .add(Attributes.ATTACK_DAMAGE, 2.0);
    }}
{gear_java}}}
'''
        path = f"{PKG}/entity/npc/LOTREntity{cls}.java"
        open(path, "w", encoding="utf-8").write(java)
        lint(path)

        w, h = n.get("size", [0.6, 1.8])
        ent_add.append(f"""    public static final RegistryObject<EntityType<{NPCP}.LOTREntity{cls}>>
            {reg.upper()} = ENTITIES.register("{reg}",
            () -> EntityType.Builder.of(
                    {NPCP}.LOTREntity{cls}::new,
                    net.minecraft.entity.EntityClassification.CREATURE)
                    .sized({w}f, {h}f).build("{reg}"));
""")
        mod_add.append(f"        event.put(fr.alleretretour.lotr.init.LOTREntities.{reg.upper()}.get(),\n"
                       f"                {NPCP}.LOTREntity{cls}.createAttributes().build());")
        p, s = egg_colors(f"{RES}/textures/entity/{n['skin_dest']}/0.png")
        egg_add.append(f"""    public static final RegistryObject<Item> {reg.upper()}_EGG =
            LOTRItems.ITEMS.register("{reg}_spawn_egg",
                    () -> new LOTRSpawnEgg(LOTREntities.{reg.upper()}, 0x{p:06X}, 0x{s:06X}));
""")
        render = "LOTRRenderOrc" if n.get("model") == "orc" else "LOTRRenderNPC"
        args = f'"{n["skin_dest"]}", {variants}' + (f', {n["scale"]}f' if n.get("scale") else "")
        cs_add.append(f'''        def("{reg}", fr.alleretretour.lotr.init.LOTREntities.{reg.upper()},
            m -> new fr.alleretretour.lotr.client.render.{render}<>(m, {args}));''')
        open(f"{RES}/models/item/{reg}_spawn_egg.json", "w").write(
            '{\n  "parent": "minecraft:item/template_spawn_egg"\n}\n')
        for loc, name in (("fr_fr", fr), ("en_us", en)):
            langs[loc][f"entity.lotr.{reg}"] = name
            langs[loc][f"item.lotr.{reg}_spawn_egg"] = (
                f"Oeuf d'apparition {name}" if loc == "fr_fr" else f"{name} Spawn Egg")
        print(f"  {reg}: PV {hp}, {len(weapons)} armes, tir={bool(ranged)}, "
              f"{variants} peaux, FR='{fr}'")

    marker = f"\n    // ===== {lot.upper()} =====\n"
    ent = ent.rstrip()[:-1].rstrip() + marker + "\n".join(ent_add) + "\n}\n"
    eggs = eggs.replace("    public static void init() {",
                        marker + "\n".join(egg_add) + "\n    public static void init() {", 1)
    mod = insert_before_method_end(mod, "private void onEntityAttributes",
                                   "\n" + "\n".join(mod_add))
    cs = insert_before_method_end(cs, "private static void defineRenderers()",
                                  "\n        // --- " + lot + " ---\n" + "\n".join(cs_add))
    open(f"{PKG}/init/LOTREntities.java", "w").write(ent)
    open(f"{PKG}/init/LOTRSpawnEggs.java", "w").write(eggs)
    open(f"{PKG}/LOTRMod.java", "w").write(mod)
    open(f"{PKG}/client/ClientSetup.java", "w").write(cs)
    for loc in langs:
        json.dump(langs[loc], open(f"{RES}/lang/{loc}.json", "w", encoding="utf-8"),
                  ensure_ascii=False, indent=2, sort_keys=True)
    for f in ("LOTREntities", "LOTRSpawnEggs", "LOTRMod", "ClientSetup"):
        pass
    print(f"USINE : lot '{lot}' genere dans {outdir}")

if __name__ == "__main__":
    run(sys.argv[1], sys.argv[2])
