#!/usr/bin/env python3
# -*- coding: utf-8 -*-
"""Mega-lot Phase 4 : generation de 42 PNJ depuis le Legacy."""
import json, os, re, shutil, sys
from PIL import Image

REN = "/home/claude/lotr-renaissance"
LEG = "/home/claude/lotr-legacy/src/main/resources/assets/lotr/mob"
OUT = "/home/claude/megalot"
PKG = f"{OUT}/src/main/java/fr/alleretretour/lotr"
RES = f"{OUT}/src/main/resources/assets/lotr"

ITEM_IDS = set(json.load(open("/home/claude/item_ids.json")))

MELEE_PREF = ["sword", "scimitar", "battleaxe", "mace", "club", "hammer",
              "spear", "axe", "pike", "polearm", "dagger"]

def resolve_weapon(prefix):
    if not prefix:
        return None
    for suf in MELEE_PREF:
        wid = f"{prefix}_{suf}"
        if wid in ITEM_IDS:
            return wid
    raise SystemExit(f"ERREUR : aucune arme trouvee pour le prefixe '{prefix}'")

def resolve_armor(prefix):
    if not prefix:
        return None
    pieces = [f"{prefix}_helmet", f"{prefix}_chestplate", f"{prefix}_leggings", f"{prefix}_boots"]
    missing = [p for p in pieces if p not in ITEM_IDS]
    if missing:
        raise SystemExit(f"ERREUR : armure incomplete pour '{prefix}' : {missing}")
    return pieces

def check_item(iid):
    if iid and iid not in ITEM_IDS:
        raise SystemExit(f"ERREUR : item inconnu '{iid}'")
    return iid

# (Classe, reg_name, faction, skin_source_legacy|None, skin_dest, hp, dmg,
#  arme: prefixe|('=', id exact)|None, bow_id|None, armure prefixe|None,
#  modele 'npc'|'orc', (larg, haut), nom_fr, nom_en)
R = [
 # --- Peuples libres ---
 ("Dwarf","dwarf","DURINS_FOLK","dwarf/dwarf_male","dwarf_male",22,2.5,None,None,None,"npc",(0.55,1.45),"Nain","Dwarf"),
 ("DwarfWarrior","dwarf_warrior","DURINS_FOLK","dwarf/dwarf_male","dwarf_male",25,4.0,"dwarven",None,"dwarven","npc",(0.55,1.45),"Guerrier Nain","Dwarf Warrior"),
 ("DwarfCommander","dwarf_commander","DURINS_FOLK","dwarf/dwarf_male","dwarf_male",30,5.0,"dwarven",None,"dwarven_gold","npc",(0.55,1.45),"Commandant Nain","Dwarf Commander"),
 ("BlueDwarfWarrior","blue_dwarf_warrior","BLUE_MOUNTAINS","dwarf/blueMountains_male","blue_mountains_male",25,4.0,"blue_dwarven",None,"blue_dwarven","npc",(0.55,1.45),"Guerrier des Montagnes Bleues","Blue Mountains Warrior"),
 ("Hobbit","hobbit","HOBBIT","hobbit/hobbit_male","hobbit_male",16,1.5,None,None,None,"npc",(0.5,1.2),"Hobbit","Hobbit"),
 ("HobbitBounder","hobbit_bounder","HOBBIT","hobbit/hobbit_male","hobbit_male",20,2.5,None,None,None,"npc",(0.5,1.2),"Frontalier Hobbit","Hobbit Bounder"),
 ("BreeMan","bree_man","BREE","bree/bree_male","bree_male",20,2.0,None,None,None,"npc",(0.6,1.8),"Homme de Bree","Bree-man"),
 ("BreeGuard","bree_guard","BREE","bree/bree_male","bree_male",25,3.5,"bronze",None,"bronze","npc",(0.6,1.8),"Garde de Bree","Bree Guard"),
 ("RangerNorth","ranger_north","RANGER_NORTH","ranger/ranger_male","ranger_male",25,3.5,"arnor","ranger_bow","ranger","npc",(0.6,1.8),"Rodeur du Nord","Ranger of the North"),
 ("RangerNorthCaptain","ranger_north_captain","RANGER_NORTH","ranger/ranger_male","ranger_male",30,5.0,"arnor",None,"ranger","npc",(0.6,1.8),"Capitaine Rodeur du Nord","Ranger Captain of the North"),
 ("DaleSoldier","dale_soldier","DALE","dale/dale_soldier","dale_soldier",25,4.0,"dale",None,"dale","npc",(0.6,1.8),"Soldat de Dale","Dale Soldier"),
 ("DaleArcher","dale_archer","DALE","dale/dale_soldier","dale_soldier",25,3.5,None,"dale_bow","dale","npc",(0.6,1.8),"Archer de Dale","Dale Archer"),
 ("DaleLevyman","dale_levyman","DALE","dale/dale_male","dale_male",22,3.0,"dale",None,None,"npc",(0.6,1.8),"Recrue de Dale","Dale Levyman"),
 ("DorwinionGuard","dorwinion_guard","DORWINION","dorwinion/dorwinion_male","dorwinion_male",25,3.5,"bronze",None,"dorwinion","npc",(0.6,1.8),"Garde du Dorwinion","Dorwinion Guard"),
 ("DorwinionElfWarrior","dorwinion_elf_warrior","DORWINION","dorwinion/dorwinion_male","dorwinion_male",25,4.0,"dorwinion_elf",None,"dorwinion_elf","npc",(0.6,1.8),"Guerrier Elfe du Dorwinion","Dorwinion Elf Warrior"),
 ("DorwinionElfArcher","dorwinion_elf_archer","DORWINION","dorwinion/dorwinion_male","dorwinion_male",25,3.5,None,"dorwinion_elf_bow","dorwinion_elf","npc",(0.6,1.8),"Archer Elfe du Dorwinion","Dorwinion Elf Archer"),
 # --- Serviteurs de l'Ombre ---
 ("AngmarOrc","angmar_orc","ANGMAR","","orc",22,3.0,"angmar",None,"angmar","orc",(0.55,1.7),"Orque d'Angmar","Angmar Orc"),
 ("AngmarOrcArcher","angmar_orc_archer","ANGMAR","","orc",22,3.0,None,"orc_bow","angmar","orc",(0.55,1.7),"Orque Archer d'Angmar","Angmar Orc Archer"),
 ("AngmarHillmanWarrior","angmar_hillman_warrior","ANGMAR","hillman/hillman_male","hillman_male",25,4.0,"angmar",None,None,"npc",(0.6,1.8),"Guerrier des Collines d'Angmar","Angmar Hillman Warrior"),
 ("GundabadOrc","gundabad_orc","GUNDABAD","","orc",22,3.0,"orc",None,"orc","orc",(0.55,1.7),"Orque de Gundabad","Gundabad Orc"),
 ("GundabadOrcArcher","gundabad_orc_archer","GUNDABAD","","orc",22,3.0,None,"orc_bow","orc","orc",(0.55,1.7),"Orque Archer de Gundabad","Gundabad Orc Archer"),
 ("GundabadUruk","gundabad_uruk","GUNDABAD","","uruk_hai",28,4.5,"gundabad_uruk",None,"gundabad_uruk","orc",(0.6,1.85),"Uruk de Gundabad","Gundabad Uruk"),
 ("DolGuldurOrc","dol_guldur_orc","DOL_GULDUR","","orc",22,3.0,"dol_guldur",None,"dol_guldur","orc",(0.55,1.7),"Orque de Dol Guldur","Dol Guldur Orc"),
 ("DolGuldurOrcArcher","dol_guldur_orc_archer","DOL_GULDUR","","orc",22,3.0,None,"orc_bow","dol_guldur","orc",(0.55,1.7),"Orque Archer de Dol Guldur","Dol Guldur Orc Archer"),
 ("Dunlending","dunlending","DUNLAND","dunland/dunlending_male","dunlending_male",20,2.5,None,None,None,"npc",(0.6,1.8),"Dunlending","Dunlending"),
 ("DunlendingWarrior","dunlending_warrior","DUNLAND","dunland/dunlending_male","dunlending_male",25,4.0,"bronze",None,"bronze","npc",(0.6,1.8),"Guerrier Dunlending","Dunlending Warrior"),
 ("DunlendingBerserker","dunlending_berserker","DUNLAND","dunland/berserker","dunland_berserker",30,6.0,"bronze",None,None,"npc",(0.6,1.8),"Berserker Dunlending","Dunlending Berserker"),
 ("NearHaradrimWarrior","near_haradrim_warrior","NEAR_HARAD","nearHarad/warrior","near_harad_warrior",25,4.0,"near_harad",None,"near_harad","npc",(0.6,1.8),"Guerrier du Proche-Harad","Near Haradrim Warrior"),
 ("NearHaradrimArcher","near_haradrim_archer","NEAR_HARAD","nearHarad/warrior","near_harad_warrior",25,3.5,None,"near_harad_bow","near_harad","npc",(0.6,1.8),"Archer du Proche-Harad","Near Haradrim Archer"),
 ("Corsair","corsair","NEAR_HARAD","nearHarad/haradrim_male","haradrim_male",24,4.0,"corsair",None,"corsair","npc",(0.6,1.8),"Corsaire d'Umbar","Corsair of Umbar"),
 ("UmbarWarrior","umbar_warrior","NEAR_HARAD","nearHarad/haradrim_male","haradrim_male",25,4.0,"corsair",None,"umbar","npc",(0.6,1.8),"Guerrier d'Umbar","Umbar Warrior"),
 ("UmbarArcher","umbar_archer","NEAR_HARAD","nearHarad/haradrim_male","haradrim_male",25,3.5,None,"near_harad_bow","umbar","npc",(0.6,1.8),"Archer d'Umbar","Umbar Archer"),
 ("EasterlingWarrior","easterling_warrior","RHUDEL","rhun/easterling_male","easterling_male",25,4.0,"rhun",None,"rhun","npc",(0.6,1.8),"Guerrier Oriental","Easterling Warrior"),
 ("EasterlingArcher","easterling_archer","RHUDEL","rhun/easterling_male","easterling_male",25,3.5,None,"rhun_bow","rhun","npc",(0.6,1.8),"Archer Oriental","Easterling Archer"),
 ("EasterlingGoldWarrior","easterling_gold_warrior","RHUDEL","rhun/easterling_male","easterling_male",28,4.5,"rhun",None,"rhun_gold","npc",(0.6,1.8),"Guerrier d'Or Oriental","Easterling Gold Warrior"),
 ("MoredainWarrior","moredain_warrior","MORWAITH","moredain/moredain_male","moredain_male",25,4.0,"moredain",None,"moredain","npc",(0.6,1.8),"Guerrier Moredain","Moredain Warrior"),
 ("MoredainHuntsman","moredain_huntsman","MORWAITH","moredain/moredain_male","moredain_male",24,3.5,"moredain",None,None,"npc",(0.6,1.8),"Chasseur Moredain","Moredain Huntsman"),
 ("TauredainWarrior","tauredain_warrior","TAURETHRIM","tauredain/tauredain_male","tauredain_male",25,4.0,"tauredain",None,"tauredain","npc",(0.6,1.8),"Guerrier Taured\u00e2in","Tauredain Warrior"),
 ("TauredainBlowgunner","tauredain_blowgunner","TAURETHRIM","tauredain/tauredain_male","tauredain_male",22,3.0,("=","tauredain_dagger"),None,None,"npc",(0.6,1.8),"Sarbacanier Taured\u00e2in","Tauredain Blowgunner"),
 ("HalfTroll","half_troll","HALF_TROLL","halfTroll/halfTroll","half_troll",30,4.0,None,None,None,"npc",(0.7,2.0),"Semi-Troll","Half-troll"),
 ("HalfTrollWarrior","half_troll_warrior","HALF_TROLL","halfTroll/halfTroll","half_troll",35,6.0,"half_troll",None,"half_troll","npc",(0.7,2.0),"Guerrier Semi-Troll","Half-troll Warrior"),
]


def convert_legacy_skin(img):
    """Conversion Mojang 64x32 -> 64x64 : synthese des membres gauches en miroir."""
    out = img.copy()
    def cp(x, y, dx, dy, w, h):
        region = img.crop((x, y, x + w, y + h)).transpose(Image.FLIP_LEFT_RIGHT)
        out.paste(region, (x + dx, y + dy))
    cp(4, 16, 16, 32, 4, 4);  cp(8, 16, 16, 32, 4, 4)      # jambe G : dessus/dessous
    cp(0, 20, 24, 32, 4, 12); cp(4, 20, 16, 32, 4, 12)     # jambe G : faces
    cp(8, 20, 8, 32, 4, 12);  cp(12, 20, 16, 32, 4, 12)
    cp(44, 16, -8, 32, 4, 4); cp(48, 16, -8, 32, 4, 4)     # bras G : dessus/dessous
    cp(40, 20, 0, 32, 4, 12); cp(44, 20, -8, 32, 4, 12)    # bras G : faces
    cp(48, 20, -16, 32, 4, 12); cp(52, 20, -8, 32, 4, 12)
    return out

def left_limbs_missing(img):
    for box in ((32, 48, 48, 64), (16, 48, 32, 64)):
        d = list(img.crop(box).getdata())
        if sum(1 for p in d if p[3] > 128) / len(d) < 0.3:
            return True
    return False


SCALES = {}
for _r in R:
    _reg = _r[1]
    if _reg.startswith(("dwarf", "blue_dwarf")):
        SCALES[_reg] = 0.75
    elif _reg.startswith("hobbit"):
        SCALES[_reg] = 0.62
    elif _reg.startswith("half_troll"):
        SCALES[_reg] = 1.2

def camel(reg):
    return "".join(w.capitalize() for w in reg.split("_"))

os.makedirs(f"{PKG}/entity/npc", exist_ok=True)
os.makedirs(f"{PKG}/init", exist_ok=True)
os.makedirs(f"{PKG}/client", exist_ok=True)
os.makedirs(f"{RES}/textures/entity", exist_ok=True)
os.makedirs(f"{RES}/models/item", exist_ok=True)
os.makedirs(f"{RES}/lang", exist_ok=True)

# ---------- 1. Textures ----------
skin_variants = {}
existing_entity_dir = f"{REN}/src/main/resources/assets/lotr/textures/entity"
for row in R:
    src, dest = row[3], row[4]
    if dest in skin_variants:
        continue
    if not src:  # reutilise un dossier deja present dans le projet
        n = len([f for f in os.listdir(f"{existing_entity_dir}/{dest}") if f.endswith(".png")])
        skin_variants[dest] = n
        continue
    sp = f"{LEG}/{src}"
    if not os.path.isdir(sp):
        raise SystemExit(f"ERREUR : dossier de peaux Legacy introuvable : {sp}")
    files = sorted([f for f in os.listdir(sp) if re.fullmatch(r"\d+\.png", f)],
                   key=lambda f: int(f.split(".")[0]))
    if not files:
        raise SystemExit(f"ERREUR : aucune peau numerotee dans {sp}")
    dp = f"{RES}/textures/entity/{dest}"
    os.makedirs(dp, exist_ok=True)
    for i, f in enumerate(files):
        img = Image.open(f"{sp}/{f}").convert("RGBA")
        if img.size != (64, 64):
            canvas = Image.new("RGBA", (64, 64), (0, 0, 0, 0))
            canvas.paste(img, (0, 0))
            img = canvas
        if left_limbs_missing(img):
            img = convert_legacy_skin(img)
        img.save(f"{dp}/{i}.png")
    skin_variants[dest] = len(files)

# ---------- 2. Couleurs d'oeufs depuis la peau 0 ----------
def egg_colors(dest):
    for base in (f"{RES}/textures/entity/{dest}/0.png",
                 f"{existing_entity_dir}/{dest}/0.png"):
        if os.path.exists(base):
            img = Image.open(base).convert("RGBA")
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
            return avg(8, 16), avg(20, 32)  # visage / torse
    return 0x888888, 0x333333

# ---------- 3. Classes d'entites ----------
def gear_lines(row):
    wep, bow, armor = row[7], row[8], row[9]
    lines = []
    if bow:
        lines.append((u"MAINHAND", check_item(bow)))
    elif wep:
        wid = check_item(wep[1]) if isinstance(wep, tuple) else resolve_weapon(wep)
        lines.append((u"MAINHAND", wid))
    if armor:
        pieces = resolve_armor(armor)
        for slot, iid in zip(["HEAD", "CHEST", "LEGS", "FEET"], pieces):
            lines.append((slot, iid))
    return lines

for row in R:
    cls, reg, fac = row[0], row[1], row[2]
    hp, dmg, bow = row[5], row[6], row[8]
    gear = gear_lines(row)
    ranged = "\n    @Override\n    protected boolean isRangedNPC() {\n        return true;\n    }\n" if bow else ""
    if gear:
        sets = "\n".join(
            f'        setItemSlot(EquipmentSlotType.{slot}, new ItemStack(itemOf("{iid}")));'
            for slot, iid in gear)
        spawn = f"""
    private static net.minecraft.item.Item itemOf(String id) {{
        return net.minecraftforge.registries.ForgeRegistries.ITEMS.getValue(
                new net.minecraft.util.ResourceLocation("lotr", id));
    }}

    @javax.annotation.Nullable
    @Override
    public net.minecraft.entity.ILivingEntityData finalizeSpawn(
            net.minecraft.world.IServerWorld world, net.minecraft.world.DifficultyInstance difficulty,
            net.minecraft.entity.SpawnReason reason,
            @javax.annotation.Nullable net.minecraft.entity.ILivingEntityData data,
            @javax.annotation.Nullable net.minecraft.nbt.CompoundNBT nbt) {{
{sets}
        for (EquipmentSlotType slot : EquipmentSlotType.values()) {{
            setDropChance(slot, 0.05f);
        }}
        return super.finalizeSpawn(world, difficulty, reason, data, nbt);
    }}
"""
        imports = "import net.minecraft.inventory.EquipmentSlotType;\nimport net.minecraft.item.ItemStack;\n"
    else:
        spawn, imports = "", ""
    java = f"""package fr.alleretretour.lotr.entity.npc;

import fr.alleretretour.lotr.fac.LOTRFaction;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.ai.attributes.AttributeModifierMap;
import net.minecraft.entity.ai.attributes.Attributes;
{imports}import net.minecraft.world.World;

/** GENERE (mega-lot Phase 4) - port de lotr.common.entity.npc.LOTREntity{cls} */
public class LOTREntity{cls} extends LOTREntityNPC {{

    public LOTREntity{cls}(EntityType<? extends LOTREntity{cls}> type, World world) {{
        super(type, world);
    }}

    @Override
    public LOTRFaction getFaction() {{
        return LOTRFaction.{fac};
    }}
{ranged}
    public static AttributeModifierMap.MutableAttribute createAttributes() {{
        return createNPCAttributes()
                .add(Attributes.MAX_HEALTH, {hp})
                .add(Attributes.ATTACK_DAMAGE, {dmg});
    }}
{spawn}}}
"""
    open(f"{PKG}/entity/npc/LOTREntity{cls}.java", "w").write(java)

# ---------- 4. Carrefours ----------
NPCP = "fr.alleretretour.lotr.entity.npc"

ent = open(f"{REN}/src/main/java/fr/alleretretour/lotr/init/LOTREntities.java").read()
blocks = ["\n    // ===== MEGA-LOT Phase 4 =====\n"]
for row in R:
    cls, reg, (w, h) = row[0], row[1], row[11]
    blocks.append(f"""    public static final RegistryObject<EntityType<{NPCP}.LOTREntity{cls}>>
            {reg.upper()} = ENTITIES.register("{reg}",
            () -> EntityType.Builder.of(
                    {NPCP}.LOTREntity{cls}::new,
                    net.minecraft.entity.EntityClassification.CREATURE)
                    .sized({w}f, {h}f).build("{reg}"));
""")
ent = ent.rstrip()[:-1].rstrip() + "\n" + "\n".join(blocks) + "\n}\n"
open(f"{PKG}/init/LOTREntities.java", "w").write(ent)

mod = open(f"{REN}/src/main/java/fr/alleretretour/lotr/LOTRMod.java").read()
attr_anchor = "LOTREntityRivendellLord.createAttributes().build());"
puts = "\n".join(
    f"        event.put(fr.alleretretour.lotr.init.LOTREntities.{row[1].upper()}.get(),\n"
    f"                {NPCP}.LOTREntity{row[0]}.createAttributes().build());"
    for row in R)
assert attr_anchor in mod
mod = mod.replace(attr_anchor, attr_anchor + "\n" + puts, 1)
open(f"{PKG}/LOTRMod.java", "w").write(mod)

eggs = open(f"{REN}/src/main/java/fr/alleretretour/lotr/init/LOTRSpawnEggs.java").read()
egg_blocks = ["\n    // ===== MEGA-LOT Phase 4 =====\n"]
for row in R:
    reg = row[1]
    p, s = egg_colors(row[4])
    egg_blocks.append(f"""    public static final RegistryObject<Item> {reg.upper()}_EGG =
            LOTRItems.ITEMS.register("{reg}_spawn_egg",
                    () -> new LOTRSpawnEgg(LOTREntities.{reg.upper()}, 0x{p:06X}, 0x{s:06X}));
""")
anchor = "    public static void init() {"
assert anchor in eggs
eggs = eggs.replace(anchor, "\n".join(egg_blocks) + "\n" + anchor, 1)
open(f"{PKG}/init/LOTRSpawnEggs.java", "w").write(eggs)

cs = open("/home/claude/fix-elves/src/main/java/fr/alleretretour/lotr/client/ClientSetup.java").read()
def_anchor = '''def("rivendell_lord", fr.alleretretour.lotr.init.LOTREntities.RIVENDELL_LORD,
            m -> new fr.alleretretour.lotr.client.render.LOTRRenderNPC<>(m, "high_elf_male", 18));'''
assert def_anchor in cs
defs = ["\n        // --- MEGA-LOT Phase 4 ---"]
for row in R:
    reg, dest, model = row[1], row[4], row[10]
    n = skin_variants[dest]
    render = "LOTRRenderOrc" if model == "orc" else "LOTRRenderNPC"
    sc = SCALES.get(reg)
    args = f'"{dest}", {n}' + (f", {sc}f" if sc else "")
    defs.append(f'''        def("{reg}", fr.alleretretour.lotr.init.LOTREntities.{reg.upper()},
            m -> new fr.alleretretour.lotr.client.render.{render}<>(m, {args}));''')
cs = cs.replace(def_anchor, def_anchor + "\n" + "\n".join(defs), 1)
open(f"{PKG}/client/ClientSetup.java", "w").write(cs)

# ---------- 5. Modeles d'items des oeufs (nouveaux + anciens) ----------
all_eggs = re.findall(r'register\("([a-z0-9_]+_spawn_egg)"', eggs)
for e in all_eggs:
    open(f"{RES}/models/item/{e}.json", "w").write(
        '{\n  "parent": "minecraft:item/template_spawn_egg"\n}\n')

# ---------- 6. Lang FR/EN fusionnes ----------
for lang, idx in (("fr_fr", 12), ("en_us", 13)):
    data = json.load(open(f"{REN}/src/main/resources/assets/lotr/lang/{lang}.json", encoding="utf-8"))
    for row in R:
        name = row[idx]
        data[f"entity.lotr.{row[1]}"] = name
        data[f"item.lotr.{row[1]}_spawn_egg"] = (f"Oeuf d'apparition {name}" if lang == "fr_fr"
                                                 else f"{name} Spawn Egg")
    json.dump(data, open(f"{RES}/lang/{lang}.json", "w", encoding="utf-8"),
              ensure_ascii=False, indent=2, sort_keys=True)

print(f"OK : {len(R)} PNJ generes, {len(all_eggs)} modeles d'oeufs, "
      f"{len(skin_variants)} dossiers de peaux ({sum(skin_variants.values())} variantes)")
