#!/usr/bin/env python3
# -*- coding: utf-8 -*-
"""Lot porte-bannieres : 8 items de banniere + 8 entites + rosters exacts."""
import json, os, shutil
from PIL import Image

LEGB = "/home/claude/lotr-legacy/src/main/resources/assets/lotr/item/banner"
REN = "/home/claude/lotr-renaissance"
OUT = "/home/claude/lot-bannieres"
PKG = f"{OUT}/src/main/java/fr/alleretretour/lotr"
RES = f"{OUT}/src/main/resources/assets/lotr"
for d in (f"{PKG}/entity/npc", f"{PKG}/init", f"{PKG}/client", f"{PKG}/hire",
          f"{RES}/textures/item", f"{RES}/models/item", f"{RES}/lang"):
    os.makedirs(d, exist_ok=True)

# (ClasseBearer, reg, parent classe, item_id, candidats texture Legacy,
#  skin, variants, scale, fr, en)
B = [
 ("GondorBannerBearer","gondor_banner_bearer","GondorSoldier","gondor_banner",
  ["banner_gondor"],"gondor_male",10,None,"Porte-Banniere du Gondor","Gondor Banner Bearer"),
 ("RohanBannerBearer","rohan_banner_bearer","RohirrimWarrior","rohan_banner",
  ["banner_rohan"],"rohan_male",6,None,"Porte-Banniere du Rohan","Rohan Banner Bearer"),
 ("DwarfBannerBearer","dwarf_banner_bearer","DwarfWarrior","dwarf_banner",
  ["banner_dwarf","banner_durin"],"dwarf_male",3,0.75,"Porte-Banniere Nain","Dwarf Banner Bearer"),
 ("BlueDwarfBannerBearer","blue_dwarf_banner_bearer","BlueDwarfWarrior","blue_mountains_banner",
  ["banner_blueMountains"],"blue_mountains_male",3,0.75,"Porte-Banniere des Montagnes Bleues","Blue Mountains Banner Bearer"),
 ("HighElfBannerBearer","high_elf_banner_bearer","HighElfWarrior","high_elf_banner",
  ["banner_highElf"],"high_elf_male",18,None,"Porte-Banniere Haut Elfe","High Elven Banner Bearer"),
 ("RivendellBannerBearer","rivendell_banner_bearer","RivendellWarrior","rivendell_banner",
  ["banner_rivendell"],"high_elf_male",18,None,"Porte-Banniere de Fondcombe","Rivendell Banner Bearer"),
 ("GaladhrimBannerBearer","galadhrim_banner_bearer","GaladhrimWarrior","galadhrim_banner",
  ["banner_galadhrim","banner_lorien","banner_lothlorien"],"galadhrim_male",4,None,"Porte-Banniere Galadhrim","Galadhrim Banner Bearer"),
 ("RangerNorthBannerBearer","ranger_north_banner_bearer","RangerNorth","ranger_banner",
  ["banner_ranger"],"ranger_male",6,None,"Porte-Banniere Rodeur","Ranger Banner Bearer"),
]

# ---------- 1. textures + modeles + items ----------
BANNER_DISPLAY = '''  "display": {
    "thirdperson_righthand": {"rotation": [0, -90, 55], "translation": [0, 6, 0.5], "scale": [1.6, 1.6, 1.6]},
    "thirdperson_lefthand": {"rotation": [0, 90, -55], "translation": [0, 6, 0.5], "scale": [1.6, 1.6, 1.6]},
    "firstperson_righthand": {"rotation": [0, -90, 25], "translation": [1.13, 3.2, 1.13], "scale": [0.8, 0.8, 0.8]},
    "firstperson_lefthand": {"rotation": [0, 90, -25], "translation": [1.13, 3.2, 1.13], "scale": [0.8, 0.8, 0.8]}
  }'''
items_java = []
for cls, reg, parent, item_id, candidates, skin, n, sc, fr, en in B:
    src = None
    for c in candidates:
        p = f"{LEGB}/{c}.png"
        if os.path.exists(p):
            src = p
            break
    assert src, f"texture Legacy introuvable pour {item_id} : {candidates}"
    shutil.copy(src, f"{RES}/textures/item/{item_id}.png")
    open(f"{RES}/models/item/{item_id}.json", "w").write(
        '{\n  "parent": "minecraft:item/handheld",\n  "textures": {\n    "layer0": "lotr:item/'
        + item_id + '"\n  },\n' + BANNER_DISPLAY + '\n}\n')
    items_java.append(f'''    public static final RegistryObject<Item> {item_id.upper()} =
            LOTRItems.ITEMS.register("{item_id}",
                    () -> new Item(new Item.Properties().tab(LOTRItems.TAB_LOTR).stacksTo(1)));
''')

open(f"{PKG}/init/LOTRItemsBanners.java", "w").write(f'''package fr.alleretretour.lotr.init;

import net.minecraft.item.Item;
import net.minecraftforge.fml.RegistryObject;

/**
 * PORT de LOTRItemBanner (Legacy) - premiere tranche : les 8 bannieres des
 * porte-bannieres embauchables. Textures officielles item/banner/ du Legacy.
 * Les autres types de bannieres (et la banniere posable) viendront avec le
 * lot protection de terrain.
 */
public final class LOTRItemsBanners {{

{"".join(items_java)}
    private LOTRItemsBanners() {{
    }}

    /** Declenche l'enregistrement des champs statiques. */
    public static void init() {{
    }}
}}
''')

# ---------- 2. interface + entites ----------
open(f"{PKG}/entity/npc/LOTRBannerBearer.java", "w").write('''package fr.alleretretour.lotr.entity.npc;

/** PORT de lotr.common.entity.npc.LOTRBannerBearer. */
public interface LOTRBannerBearer {

    /** Id de l'item de banniere porte en main gauche (ex. "lotr:gondor_banner"). */
    String getBannerItemId();
}
''')
for cls, reg, parent, item_id, candidates, skin, n, sc, fr, en in B:
    open(f"{PKG}/entity/npc/LOTREntity{cls}.java", "w").write(f'''package fr.alleretretour.lotr.entity.npc;

import net.minecraft.entity.EntityType;
import net.minecraft.world.World;

/** PORT de LOTREntity{cls} (Legacy) : {parent} + banniere en main gauche. */
public class LOTREntity{cls} extends LOTREntity{parent} implements LOTRBannerBearer {{

    public LOTREntity{cls}(EntityType<? extends LOTREntity{cls}> type, World world) {{
        super((EntityType) type, world);
    }}

    @Override
    public String getBannerItemId() {{
        return "lotr:{item_id}";
    }}

    @javax.annotation.Nullable
    @Override
    public net.minecraft.entity.ILivingEntityData finalizeSpawn(
            net.minecraft.world.IServerWorld world, net.minecraft.world.DifficultyInstance difficulty,
            net.minecraft.entity.SpawnReason reason,
            @javax.annotation.Nullable net.minecraft.entity.ILivingEntityData data,
            @javax.annotation.Nullable net.minecraft.nbt.CompoundNBT nbt) {{
        net.minecraft.entity.ILivingEntityData result =
                super.finalizeSpawn(world, difficulty, reason, data, nbt);
        // fidele au Legacy : la banniere se porte en main gauche (getHeldItemLeft)
        net.minecraft.item.Item banner = net.minecraftforge.registries.ForgeRegistries.ITEMS
                .getValue(new net.minecraft.util.ResourceLocation(getBannerItemId()));
        if (banner != null) {{
            setItemSlot(net.minecraft.inventory.EquipmentSlotType.OFFHAND,
                    new net.minecraft.item.ItemStack(banner));
            setDropChance(net.minecraft.inventory.EquipmentSlotType.OFFHAND, 0.05f);
        }}
        return result;
    }}
}}
''')

# ---------- 3. carrefours ----------
NPCP = "fr.alleretretour.lotr.entity.npc"
SIZES = {"Dwarf": (0.55, 1.45), "BlueDwarf": (0.55, 1.45)}
ent = open("/home/claude/lot-traders/src/main/java/fr/alleretretour/lotr/init/LOTREntities.java").read()
blocks = ["\n    // ===== LOT PORTE-BANNIERES =====\n"]
for cls, reg, parent, *_ in B:
    w, h = (0.55, 1.45) if "Dwarf" in cls else (0.6, 1.8)
    blocks.append(f"""    public static final RegistryObject<EntityType<{NPCP}.LOTREntity{cls}>>
            {reg.upper()} = ENTITIES.register("{reg}",
            () -> EntityType.Builder.of(
                    {NPCP}.LOTREntity{cls}::new,
                    net.minecraft.entity.EntityClassification.CREATURE)
                    .sized({w}f, {h}f).build("{reg}"));
""")
ent = ent.rstrip()[:-1].rstrip() + "\n" + "\n".join(blocks) + "\n}\n"
open(f"{PKG}/init/LOTREntities.java", "w").write(ent)

mod = open("/home/claude/lot-traders/src/main/java/fr/alleretretour/lotr/LOTRMod.java").read()
anchor = "fr.alleretretour.lotr.init.LOTRItemsRanged.init();"
assert anchor in mod
mod = mod.replace(anchor, anchor + "\n        fr.alleretretour.lotr.init.LOTRItemsBanners.init();", 1)
attr_anchor = "LOTREntityDolGuldurOrcTrader.createAttributes().build());"
assert attr_anchor in mod
puts = "\n".join(f"        event.put(fr.alleretretour.lotr.init.LOTREntities.{r[1].upper()}.get(),\n                {NPCP}.LOTREntity{r[0]}.createAttributes().build());" for r in B)
mod = mod.replace(attr_anchor, attr_anchor + "\n" + puts, 1)
open(f"{PKG}/LOTRMod.java", "w").write(mod)

def egg_colors(dest):
    img = Image.open(f"/home/claude/megalot/src/main/resources/assets/lotr/textures/entity/{dest}/0.png").convert("RGBA")
    px = img.load()
    def avg(y0, y1):
        r=g=b=n=0
        for y in range(y0,y1):
            for x in range(img.width):
                p=px[x,y]
                if p[3]>128: r+=p[0]; g+=p[1]; b+=p[2]; n+=1
        n=max(n,1); return (r//n<<16)|(g//n<<8)|(b//n)
    return avg(8,16), avg(20,32)

eggs = open("/home/claude/lot-traders/src/main/java/fr/alleretretour/lotr/init/LOTRSpawnEggs.java").read()
eb = ["\n    // ===== LOT PORTE-BANNIERES =====\n"]
for row in B:
    reg, skin = row[1], row[5]
    p, s = egg_colors(skin)
    eb.append(f"""    public static final RegistryObject<Item> {reg.upper()}_EGG =
            LOTRItems.ITEMS.register("{reg}_spawn_egg",
                    () -> new LOTRSpawnEgg(LOTREntities.{reg.upper()}, 0x{p:06X}, 0x{s:06X}));
""")
anchor = "    public static void init() {"
eggs = eggs.replace(anchor, "\n".join(eb) + "\n" + anchor, 1)
open(f"{PKG}/init/LOTRSpawnEggs.java", "w").write(eggs)

cs = open("/home/claude/lot-hire-gui/src/main/java/fr/alleretretour/lotr/client/ClientSetup.java").read()
last = '''def("dol_guldur_orc_trader", fr.alleretretour.lotr.init.LOTREntities.DOL_GULDUR_ORC_TRADER,
            m -> new fr.alleretretour.lotr.client.render.LOTRRenderOrc<>(m, "orc", 8));'''
assert last in cs
defs = ["\n        // --- LOT PORTE-BANNIERES ---"]
for cls, reg, parent, item_id, candidates, skin, n, sc, fr, en in B:
    args = f'"{skin}", {n}' + (f", {sc}f" if sc else "")
    defs.append(f'''        def("{reg}", fr.alleretretour.lotr.init.LOTREntities.{reg.upper()},
            m -> new fr.alleretretour.lotr.client.render.LOTRRenderNPC<>(m, {args}));''')
cs = cs.replace(last, last + "\n" + "\n".join(defs), 1)
open(f"{PKG}/client/ClientSetup.java", "w").write(cs)

# ---------- 4. rosters : activer les entrees banniere exactes du Legacy ----------
ro = open("/home/claude/lot-hire-fidele/src/main/java/fr/alleretretour/lotr/hire/LOTRHireRosters.java").read()
ADD = {
 "GONDOR_TOWER_GUARD, 50, 250, PledgeType.EXCLUSIVE)":
   "GONDOR_TOWER_GUARD, 50, 250, PledgeType.EXCLUSIVE),\n                new Entry(LOTREntities.GONDOR_BANNER_BEARER, 50, 200)",
 "ROHIRRIM_ARCHER, 50, 50)":
   "ROHIRRIM_ARCHER, 50, 50),\n                new Entry(LOTREntities.ROHAN_BANNER_BEARER, 50, 150)",
 "GALADHRIM_WARRIOR, 50, 100, PledgeType.ANY_ELF)":
   "GALADHRIM_WARRIOR, 50, 100, PledgeType.ANY_ELF),\n                new Entry(LOTREntities.GALADHRIM_BANNER_BEARER, 70, 250, PledgeType.ANY_ELF)",
 "HIGH_ELF_WARRIOR, 50, 100, PledgeType.ANY_ELF)":
   "HIGH_ELF_WARRIOR, 50, 100, PledgeType.ANY_ELF),\n                new Entry(LOTREntities.HIGH_ELF_BANNER_BEARER, 70, 250, PledgeType.ANY_ELF)",
 "RIVENDELL_WARRIOR, 50, 100, PledgeType.ANY_ELF)":
   "RIVENDELL_WARRIOR, 50, 100, PledgeType.ANY_ELF),\n                new Entry(LOTREntities.RIVENDELL_BANNER_BEARER, 70, 250, PledgeType.ANY_ELF)",
 "DWARF_AXE_THROWER, 50, 100, PledgeType.ANY_DWARF)":
   "DWARF_AXE_THROWER, 50, 100, PledgeType.ANY_DWARF),\n                new Entry(LOTREntities.DWARF_BANNER_BEARER, 50, 200, PledgeType.ANY_DWARF)",
 "RANGER_NORTH, 50, 0)":
   "RANGER_NORTH, 50, 0),\n                new Entry(LOTREntities.RANGER_NORTH_BANNER_BEARER, 70, 150)",
}
for old, new in ADD.items():
    assert old in ro, old
    ro = ro.replace(old, new, 1)
open(f"{PKG}/hire/LOTRHireRosters.java", "w").write(ro)

# ---------- 5. oeufs + lang ----------
for row in B:
    open(f"{RES}/models/item/{row[1]}_spawn_egg.json", "w").write(
        '{\n  "parent": "minecraft:item/template_spawn_egg"\n}\n')
for lang, idx in (("fr_fr", 8), ("en_us", 9)):
    data = json.load(open(f"/home/claude/lot-traders/src/main/resources/assets/lotr/lang/{lang}.json", encoding="utf-8"))
    for row in B:
        name = row[idx]
        data[f"entity.lotr.{row[1]}"] = name
        data[f"item.lotr.{row[1]}_spawn_egg"] = (f"Oeuf d'apparition {name}" if lang == "fr_fr" else f"{name} Spawn Egg")
        banner_fr = "Banniere " + name.replace("Porte-Banniere ", "").replace("Porte-Banniere", "").strip()
        data[f"item.lotr.{row[3]}"] = (banner_fr if lang == "fr_fr"
                                       else name.replace(" Banner Bearer", "") + " Banner")
    json.dump(data, open(f"{RES}/lang/{lang}.json", "w", encoding="utf-8"),
              ensure_ascii=False, indent=2, sort_keys=True)

print(f"{len(B)} porte-bannieres generes (items, entites, carrefours, rosters)")
