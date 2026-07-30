#!/usr/bin/env python3
# -*- coding: utf-8 -*-
"""Lot marchands : extraction des tables Legacy + 10 PNJ marchands."""
import json, os, re
from PIL import Image

LEG = "/home/claude/lotr-legacy/src/main/java/lotr/common/entity/npc/LOTRTradeEntries.java"
REN = "/home/claude/lotr-renaissance"
MEGA = "/home/claude/megalot"
RANGED = "/home/claude/lot-ranged"
OUT = "/home/claude/lot-traders"
PKG = f"{OUT}/src/main/java/fr/alleretretour/lotr"
RES = f"{OUT}/src/main/resources/assets/lotr"
for d in (f"{PKG}/entity/npc", f"{PKG}/init", f"{PKG}/client", f"{PKG}/trade",
          f"{RES}/models/item", f"{RES}/lang"):
    os.makedirs(d, exist_ok=True)

ids = set(json.load(open("/home/claude/item_ids.json")))
legacy_src = open(LEG, encoding="utf-8").read()

# ---------- mapping champ Legacy -> id 1.16.5 ----------
TYPES = {  # prefixe camel Legacy -> suffixe snake
    "throwingAxe": "throwing_axe", "poisonedDagger": "poisoned_dagger",
    "sword": "sword", "spear": "spear", "battleaxe": "battleaxe", "hammer": "hammer",
    "pike": "pike", "dagger": "dagger", "axe": "axe", "pickaxe": "pickaxe",
    "shovel": "shovel", "mattock": "mattock", "scimitar": "scimitar", "club": "club",
    "mace": "mace", "bow": "bow", "crossbow": "crossbow", "polearm": "polearm",
    "poleaxe": "poleaxe", "lance": "lance", "longspear": "longspear", "hoe": "hoe",
    "helmet": "helmet", "body": "chestplate", "legs": "leggings", "boots": "boots",
    "blowgun": "blowgun", "dart": "dart",
}
def snake(s):
    return re.sub(r"(?<=[a-z0-9])(?=[A-Z])", "_", s).lower()

def map_field(field):
    """LOTRMod.swordDwarven -> lotr:dwarven_sword (None si introuvable)."""
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
    if cand in ids:
        return "lotr:" + cand
    return None

VANILLA = {"iron_ingot", "gold_ingot", "coal", "string", "leather", "bread", "wheat",
           "carrot", "potato", "apple", "bone", "rotten_flesh", "porkchop", "beef",
           "chicken", "mutton", "egg", "sugar", "book", "paper", "iron_bars", "flint",
           "stick", "wheat_seeds", "pumpkin", "melon_slice", "mushroom_stew", "cooked_beef",
           "cooked_porkchop", "cooked_chicken", "cooked_mutton", "baked_potato", "gold_nugget",
           "iron_nugget", "cobblestone", "stone", "clay_ball", "brick", "spider_eye"}

def map_ref(ns, field, ):
    if ns == "LOTRMod":
        return map_field(field)
    v = snake(field)
    return "minecraft:" + v if v in VANILLA else None

def extract(table):
    m = re.search(re.escape(table) + r"\s*=\s*new LOTRTradeEntries\((.*?)\)\s*(?:\.setVessels[^;]*)?;",
                  legacy_src, re.S)
    if not m:
        return []
    entries = []
    for ns, field, qty, price in re.findall(
            r"new LOTRTradeEntry\(new ItemStack\((LOTRMod|Items|Blocks)\.(\w+)(?:,\s*(\d+))?\),\s*(\d+)\)",
            m.group(1)):
        mapped = map_ref(ns, field)
        if mapped:
            entries.append((mapped, int(qty or 1), int(price)))
    return entries

# ---------- roster des marchands ----------
# cls, reg, faction, table Legacy, skin, variants, scale, model, speech, fr, en
T = [
 ("DwarfSmith","dwarf_smith","DURINS_FOLK","DWARF_SMITH","dwarf_male",3,0.75,"npc","dwarf/warrior","Forgeron Nain","Dwarf Smith"),
 ("BlueMountainsSmith","blue_mountains_smith","BLUE_MOUNTAINS","BLUE_DWARF_SMITH","blue_mountains_male",3,0.75,"npc","blue_mountains/warrior","Forgeron des Montagnes Bleues","Blue Mountains Smith"),
 ("HobbitBartender","hobbit_bartender","HOBBIT","HOBBIT_BARTENDER","hobbit_male",13,0.62,"npc","hobbit/civilian","Tavernier Hobbit","Hobbit Bartender"),
 ("BreeInnkeeper","bree_innkeeper","BREE","BREE_INNKEEPER","bree_male",30,None,"npc","bree/civilian","Aubergiste de Bree","Bree Innkeeper"),
 ("DaleBlacksmith","dale_blacksmith","DALE","DALE_BLACKSMITH","dale_male",3,None,"npc","dale/warrior","Forgeron de Dale","Dale Blacksmith"),
 ("EasterlingBlacksmith","easterling_blacksmith","RHUDEL","RHUN_BLACKSMITH","easterling_male",5,None,"npc","rhun/warrior","Forgeron Oriental","Easterling Blacksmith"),
 ("NearHaradMerchant","near_harad_merchant","NEAR_HARAD","NEAR_HARAD_MERCHANT","haradrim_male",5,None,"npc","near_harad/warrior","Marchand du Proche-Harad","Near Harad Merchant"),
 ("MoredainTrader","moredain_trader","MORWAITH","MOREDAIN_HUNTSMAN","moredain_male",5,None,"npc","moredain/warrior","Marchand Moredain","Moredain Trader"),
 ("AngmarOrcTrader","angmar_orc_trader","ANGMAR","ANGMAR_TRADER","orc",8,None,"orc","angmar/orc","Troqueur Orque d'Angmar","Angmar Orc Trader"),
 ("DolGuldurOrcTrader","dol_guldur_orc_trader","DOL_GULDUR","DOL_GULDUR_TRADER","orc",8,None,"orc","dol_guldur/orc","Troqueur Orque de Dol Guldur","Dol Guldur Orc Trader"),
]

# ---------- 1. LOTRTradeTables complet ----------
tables_src = open(f"{REN}/src/main/java/fr/alleretretour/lotr/trade/LOTRTradeTables.java",
                  encoding="utf-8").read()
methods = ["\n    // ===== LOT MARCHANDS =====\n"]
stats = {}
def mname(reg, kind):
    parts = reg.split("_")
    return parts[0] + "".join(p.capitalize() for p in parts[1:]) + kind

for row in T:
    reg, table = row[1], row[3]
    for kind, suffix in (("Buy", "_BUY"), ("Sell", "_SELL")):
        entries = extract(table + suffix)
        stats[f"{reg}{suffix}"] = len(entries)
        body = "\n".join(f'            add(list, "{i}", {q}, {p});' for i, q, p in entries)
        methods.append(f"""    public static List<Entry> {mname(reg, kind)}() {{
        List<Entry> list = new ArrayList<>();
{body}
        return list;
    }}
""")
tables_src = tables_src.rstrip()[:-1].rstrip() + "\n\n" + "\n".join(methods) + "\n}\n"
open(f"{PKG}/trade/LOTRTradeTables.java", "w", encoding="utf-8").write(tables_src)

# ---------- 2. classes marchands ----------
for cls, reg, fac, table, skin, n, sc, model, speech, fr, en in T:
    java = f"""package fr.alleretretour.lotr.entity.npc;

import fr.alleretretour.lotr.fac.LOTRFaction;
import fr.alleretretour.lotr.inventory.LOTRContainerTrade;
import fr.alleretretour.lotr.trade.LOTRTradeTables;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.entity.ai.attributes.AttributeModifierMap;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.inventory.container.INamedContainerProvider;
import net.minecraft.inventory.container.Container;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.world.World;

import javax.annotation.Nullable;

/** GENERE (lot marchands) - table Legacy {table} */
public class LOTREntity{cls} extends LOTREntityNPC {{

    public LOTREntity{cls}(EntityType<? extends LOTREntity{cls}> type, World world) {{
        super(type, world);
    }}

    @Override
    public LOTRFaction getFaction() {{
        return LOTRFaction.{fac};
    }}

    @Override
    protected String getSpeechBank() {{
        return "{speech}";
    }}

    public static AttributeModifierMap.MutableAttribute createAttributes() {{
        return createNPCAttributes()
                .add(Attributes.MAX_HEALTH, 20.0)
                .add(Attributes.ATTACK_DAMAGE, 2.0);
    }}

    @Override
    protected ActionResultType mobInteract(PlayerEntity player, Hand hand) {{
        if (hand != Hand.MAIN_HAND) {{
            return super.mobInteract(player, hand);
        }}
        float alignment = fr.alleretretour.lotr.fac.LOTRPlayerDataProvider
                .get(player).getAlignment(getFaction());
        if (alignment < 0.0f) {{
            return super.mobInteract(player, hand);
        }}
        if (!level.isClientSide && player instanceof ServerPlayerEntity) {{
            player.openMenu(new INamedContainerProvider() {{
                @Override
                public ITextComponent getDisplayName() {{
                    return getName();
                }}

                @Nullable
                @Override
                public Container createMenu(int id, net.minecraft.entity.player.PlayerInventory inv,
                                            PlayerEntity p) {{
                    return new LOTRContainerTrade(id, inv,
                            LOTRTradeTables.{mname(reg, "Buy")}(),
                            LOTRTradeTables.{mname(reg, "Sell")}());
                }}
            }});
        }}
        return ActionResultType.CONSUME;
    }}
}}
"""
    open(f"{PKG}/entity/npc/LOTREntity{cls}.java", "w", encoding="utf-8").write(java)

# ---------- 3. carrefours (bases lot-ranged) ----------
NPCP = "fr.alleretretour.lotr.entity.npc"
ent = open(f"{RANGED}/src/main/java/fr/alleretretour/lotr/init/LOTREntities.java").read()
blocks = ["\n    // ===== LOT MARCHANDS =====\n"]
for row in T:
    cls, reg = row[0], row[1]
    w, h = (0.55, 1.7) if row[7] == "orc" else (0.55, 1.45) if "warf" in cls or "Mountains" in cls else (0.5, 1.2) if "Hobbit" in cls else (0.6, 1.8)
    blocks.append(f"""    public static final RegistryObject<EntityType<{NPCP}.LOTREntity{cls}>>
            {reg.upper()} = ENTITIES.register("{reg}",
            () -> EntityType.Builder.of(
                    {NPCP}.LOTREntity{cls}::new,
                    net.minecraft.entity.EntityClassification.CREATURE)
                    .sized({w}f, {h}f).build("{reg}"));
""")
ent = ent.rstrip()[:-1].rstrip() + "\n" + "\n".join(blocks) + "\n}\n"
open(f"{PKG}/init/LOTREntities.java", "w").write(ent)

mod = open(f"{RANGED}/src/main/java/fr/alleretretour/lotr/LOTRMod.java").read()
anchor = "LOTREntityUrukHaiCrossbower.createAttributes().build());"
assert anchor in mod
puts = "\n".join(f"        event.put(fr.alleretretour.lotr.init.LOTREntities.{r[1].upper()}.get(),\n                {NPCP}.LOTREntity{r[0]}.createAttributes().build());" for r in T)
mod = mod.replace(anchor, anchor + "\n" + puts, 1)
open(f"{PKG}/LOTRMod.java", "w").write(mod)

def egg_colors(dest):
    img = Image.open(f"{MEGA}/src/main/resources/assets/lotr/textures/entity/{dest}/0.png").convert("RGBA")
    px = img.load()
    def avg(y0, y1):
        r=g=b=n=0
        for y in range(y0,y1):
            for x in range(img.width):
                p=px[x,y]
                if p[3]>128: r+=p[0]; g+=p[1]; b+=p[2]; n+=1
        n=max(n,1); return (r//n<<16)|(g//n<<8)|(b//n)
    return avg(8,16), avg(20,32)

eggs = open(f"{RANGED}/src/main/java/fr/alleretretour/lotr/init/LOTRSpawnEggs.java").read()
eb = ["\n    // ===== LOT MARCHANDS =====\n"]
for row in T:
    reg, skin = row[1], row[4]
    p, s = egg_colors(skin)
    eb.append(f"""    public static final RegistryObject<Item> {reg.upper()}_EGG =
            LOTRItems.ITEMS.register("{reg}_spawn_egg",
                    () -> new LOTRSpawnEgg(LOTREntities.{reg.upper()}, 0x{p:06X}, 0x{s:06X}));
""")
anchor = "    public static void init() {"
eggs = eggs.replace(anchor, "\n".join(eb) + "\n" + anchor, 1)
open(f"{PKG}/init/LOTRSpawnEggs.java", "w").write(eggs)

cs = open(f"{RANGED}/src/main/java/fr/alleretretour/lotr/client/ClientSetup.java").read()
last = '''def("uruk_hai_crossbower", fr.alleretretour.lotr.init.LOTREntities.URUK_HAI_CROSSBOWER,
            m -> new fr.alleretretour.lotr.client.render.LOTRRenderOrc<>(m, "uruk_hai", 3));'''
assert last in cs
defs = ["\n        // --- LOT MARCHANDS ---"]
for row in T:
    reg, skin, n, sc, model = row[1], row[4], row[5], row[6], row[7]
    render = "LOTRRenderOrc" if model == "orc" else "LOTRRenderNPC"
    args = f'"{skin}", {n}' + (f", {sc}f" if sc else "")
    defs.append(f'''        def("{reg}", fr.alleretretour.lotr.init.LOTREntities.{reg.upper()},
            m -> new fr.alleretretour.lotr.client.render.{render}<>(m, {args}));''')
cs = cs.replace(last, last + "\n" + "\n".join(defs), 1)
open(f"{PKG}/client/ClientSetup.java", "w").write(cs)

# ---------- 4. oeufs + lang ----------
for row in T:
    open(f"{RES}/models/item/{row[1]}_spawn_egg.json", "w").write(
        '{\n  "parent": "minecraft:item/template_spawn_egg"\n}\n')
for lang, idx in (("fr_fr", 9), ("en_us", 10)):
    data = json.load(open("/home/claude/lot-ranged/src/main/resources/assets/lotr/lang/%s.json" % lang, encoding="utf-8"))
    for row in T:
        name = row[idx]
        data[f"entity.lotr.{row[1]}"] = name
        data[f"item.lotr.{row[1]}_spawn_egg"] = (f"Oeuf d'apparition {name}" if lang == "fr_fr" else f"{name} Spawn Egg")
    json.dump(data, open(f"{RES}/lang/{lang}.json", "w", encoding="utf-8"),
              ensure_ascii=False, indent=2, sort_keys=True)

print("Tables extraites (entrees conservees) :")
for k, v in stats.items():
    print(f"  {k:35s} {v}")
