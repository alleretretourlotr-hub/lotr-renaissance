#!/usr/bin/env python3
# -*- coding: utf-8 -*-
"""USINE v2 : troupes (via usine) + RECRUTEURS avec rosters extraits de
LOTRUnitTradeEntries. Montures supportees : horse/boar (le reste en TODO)."""
import json, os, re, sys
sys.path.insert(0, "/home/claude")
import audit_npc as A
import usine as U

TRADES = open(f"{A.LEG}/LOTRUnitTradeEntries.java", encoding="utf-8").read()

# classe Legacy -> (REG_CONST chez nous) : construit depuis nos lots
KNOWN = {}
for reg in list(A.NPCS) + ["galadhrim_elf", "high_elf", "rivendell_elf",
                           "gondor_banner_bearer", "rohan_banner_bearer", "dwarf_banner_bearer",
                           "blue_dwarf_banner_bearer", "high_elf_banner_bearer",
                           "rivendell_banner_bearer", "galadhrim_banner_bearer",
                           "ranger_north_banner_bearer"]:
    camel = "".join(w.capitalize() for w in reg.split("_"))
    KNOWN[camel] = reg
KNOWN.update({"GaladhrimElf": "galadhrim_elf", "HighElf": "high_elf",
              "RivendellElf": "rivendell_elf", "RangerNorth": "ranger_north",
              "Dwarf": "dwarf", "Hobbit": "hobbit", "HalfTroll": "half_troll",
              "Dunlending": "dunlending", "Corsair": "corsair", "BreeMan": "bree_man"})
MOUNTS = {"LOTREntityHorse": "horse", "LOTREntityWildBoar": "boar"}
PLEDGES = {"ANY_DWARF": "PledgeType.ANY_DWARF", "ANY_ELF": "PledgeType.ANY_ELF",
           "EXCLUSIVE": "PledgeType.EXCLUSIVE"}

import json as _json, os as _os
if _os.path.exists("/tmp/known.json"):
    KNOWN.update(_json.load(open("/tmp/known.json")))


def extract_unit_table(table):
    """-> (base_alignment, [(reg, cost, align, pledge|None, mount|None)], skipped)."""
    m = re.search(re.escape(table) + r"\s*=\s*new LOTRUnitTradeEntries\(([\d.]+)f,(.*?)\);\n",
                  TRADES, re.S)
    assert m, table
    base = int(float(m.group(1)))
    entries, skipped = [], []
    pattern = (r"new LOTRUnitTradeEntry\(LOTREntity(\w+)\.class"
               r"(?:,\s*(LOTREntity\w+)\.class,\s*\"[^\"]*\")?"
               r"(?:[^,)]*)?,\s*(\d+),\s*([\d.]+)f\)"
               r"((?:\.set\w+\([^)]*\))*)")
    for cls, mountcls, cost, align, mods in re.findall(pattern, m.group(2)):
        mount = MOUNTS.get(mountcls) if mountcls else None
        if mountcls and mount is None:
            skipped.append(cls + "+" + mountcls)
            continue
        reg = KNOWN.get(cls)
        if reg is None:
            skipped.append(cls)
            continue
        pledge = None
        pm = re.search(r"PledgeType\.(\w+)", mods)
        if pm:
            pledge = PLEDGES.get(pm.group(1))
        entries.append((reg, int(cost), int(float(align)), pledge, mount))
    return base, entries, skipped

def run(manifest_path, outdir):
    man = json.load(open(manifest_path, encoding="utf-8"))
    # les troupes du lot courant deviennent referencables par les recruteurs
    for n in man.get("npcs", []):
        KNOWN[n["legacy"]] = n["reg"]
    U.run(manifest_path, outdir)  # troupes + carrefours de base
    PKG = f"{outdir}/src/main/java/fr/alleretretour/lotr"

    ro = open("/home/claude/lot-final6/src/main/java/fr/alleretretour/lotr/hire/LOTRHireRosters.java").read()
    roster_methods, recruiter_report = [], []
    ent_add, mod_add, egg_add, cs_add = [], [], [], []
    NPCP = "fr.alleretretour.lotr.entity.npc"

    for r in man.get("recruiters", []):
        legacy, reg, table = r["legacy"], r["reg"], r["table"]
        cls = legacy
        base, entries, skipped = extract_unit_table(table)
        hp, _ = A.find_health(legacy)
        weapons, ranged, armor = U.gear_of_v2(legacy)
        method = r["roster_method"]
        lines = []
        for ereg, cost, align, pledge, mount in entries:
            args = f"LOTREntities.{ereg.upper()}, {cost}, {align}"
            if pledge:
                args += f", {pledge}"
            e = f"new Entry({args})"
            if mount:
                e += f'.mounted("{mount}")'
            lines.append("                " + e)
        todo = f"    // TODO (non portes) : {', '.join(skipped)}\n" if skipped else ""
        roster_methods.append(f"""
    // Legacy : {table}, base {base}
{todo}    public static final int {method.upper()}_BASE = {base};

    public static List<Entry> {method}() {{
        return new ArrayList<>(Arrays.asList(
{',\n'.join(lines)}));
    }}
""")
        gear_lines = []
        if weapons:
            pool = ", ".join(f'"{w}"' for w in weapons)
            gear_lines += [f"        String[] pool = {{{pool}}};",
                "        setItemSlot(net.minecraft.inventory.EquipmentSlotType.MAINHAND,",
                "                stackOf(pool[getRandom().nextInt(pool.length)]));"]
        for slot in ("HEAD", "CHEST", "LEGS", "FEET"):
            if slot in armor:
                gear_lines.append(f'        setItemSlot(net.minecraft.inventory.EquipmentSlotType.{slot}, stackOf("{armor[slot]}"));')
        fr = U.FR.get(f"entity.lotr.{legacy}.name") or r.get("fr") or reg
        en = U.EN.get(f"entity.lotr.{legacy}.name") or r.get("en") or reg
        java = f'''package fr.alleretretour.lotr.entity.npc;

import fr.alleretretour.lotr.fac.LOTRFaction;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.ai.attributes.AttributeModifierMap;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.world.World;

/** GENERE PAR L'USINE v2 - recruteur, port de LOTREntity{legacy} (table {table}) */
public class LOTREntity{cls} extends LOTREntityNPC {{

    public LOTREntity{cls}(EntityType<? extends LOTREntity{cls}> type, World world) {{
        super(type, world);
    }}

    @Override
    public LOTRFaction getFaction() {{
        return LOTRFaction.{r["faction"]};
    }}

    @Override
    protected String getSpeechBank() {{
        return "{r["speech"]}";
    }}

    @Override
    protected net.minecraft.util.ActionResultType mobInteract(
            net.minecraft.entity.player.PlayerEntity player, net.minecraft.util.Hand hand) {{
        if (hand == net.minecraft.util.Hand.MAIN_HAND && !player.isShiftKeyDown()) {{
            float alignment = fr.alleretretour.lotr.fac.LOTRPlayerDataProvider
                    .get(player).getAlignment(getFaction());
            if (alignment < fr.alleretretour.lotr.hire.LOTRHireRosters.{method.upper()}_BASE) {{
                return super.mobInteract(player, hand);
            }}
            if (!level.isClientSide
                    && player instanceof net.minecraft.entity.player.ServerPlayerEntity) {{
                final LOTREntity{cls} self = this;
                player.openMenu(new net.minecraft.inventory.container.INamedContainerProvider() {{
                    @Override
                    public net.minecraft.util.text.ITextComponent getDisplayName() {{
                        return getName();
                    }}

                    @Override
                    public net.minecraft.inventory.container.Container createMenu(
                            int id, net.minecraft.entity.player.PlayerInventory inv,
                            net.minecraft.entity.player.PlayerEntity p) {{
                        return new fr.alleretretour.lotr.inventory.LOTRContainerHire(id, inv,
                                fr.alleretretour.lotr.hire.LOTRHireRosters.{method}(), self);
                    }}
                }});
            }}
            return net.minecraft.util.ActionResultType.CONSUME;
        }}
        return super.mobInteract(player, hand);
    }}

    public static AttributeModifierMap.MutableAttribute createAttributes() {{
        return createNPCAttributes()
                .add(Attributes.MAX_HEALTH, {hp})
                .add(Attributes.ATTACK_DAMAGE, 2.0);
    }}
{f"""
    private static net.minecraft.item.ItemStack stackOf(String id) {{
        net.minecraft.item.Item item = net.minecraftforge.registries.ForgeRegistries.ITEMS
                .getValue(new net.minecraft.util.ResourceLocation(id));
        return item == null ? net.minecraft.item.ItemStack.EMPTY
                : new net.minecraft.item.ItemStack(item);
    }}

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
""" if gear_lines else ""}}}
'''
        path = f"{PKG}/entity/npc/LOTREntity{cls}.java"
        open(path, "w", encoding="utf-8").write(java)
        U.lint(path)
        variants = U.convert_skins(r["skin_src"], r["skin_dest"],
                                   f"{outdir}/src/main/resources/assets/lotr/textures/entity")
        w, h = r.get("size", [0.6, 1.8])
        ent_add.append(f"""    public static final RegistryObject<EntityType<{NPCP}.LOTREntity{cls}>>
            {reg.upper()} = ENTITIES.register("{reg}",
            () -> EntityType.Builder.of(
                    {NPCP}.LOTREntity{cls}::new,
                    net.minecraft.entity.EntityClassification.CREATURE)
                    .sized({w}f, {h}f).build("{reg}"));
""")
        mod_add.append(f"        event.put(fr.alleretretour.lotr.init.LOTREntities.{reg.upper()}.get(),\n"
                       f"                {NPCP}.LOTREntity{cls}.createAttributes().build());")
        p, s = U.egg_colors(f"{outdir}/src/main/resources/assets/lotr/textures/entity/{r['skin_dest']}/0.png")
        egg_add.append(f"""    public static final RegistryObject<Item> {reg.upper()}_EGG =
            LOTRItems.ITEMS.register("{reg}_spawn_egg",
                    () -> new LOTRSpawnEgg(LOTREntities.{reg.upper()}, 0x{p:06X}, 0x{s:06X}));
""")
        render = "LOTRRenderOrc" if r.get("model") == "orc" else "LOTRRenderNPC"
        args = f'"{r["skin_dest"]}", {variants}' + (f', {r["scale"]}f' if r.get("scale") else "")
        cs_add.append(f'''        def("{reg}", fr.alleretretour.lotr.init.LOTREntities.{reg.upper()},
            m -> new fr.alleretretour.lotr.client.render.{render}<>(m, {args}));''')
        open(f"{outdir}/src/main/resources/assets/lotr/models/item/{reg}_spawn_egg.json", "w").write(
            '{\n  "parent": "minecraft:item/template_spawn_egg"\n}\n')
        for loc, name in (("fr_fr", fr), ("en_us", en)):
            lp = f"{outdir}/src/main/resources/assets/lotr/lang/{loc}.json"
            d = json.load(open(lp, encoding="utf-8"))
            d[f"entity.lotr.{reg}"] = name
            d[f"item.lotr.{reg}_spawn_egg"] = (f"Oeuf d'apparition {name}" if loc == "fr_fr"
                                               else f"{name} Spawn Egg")
            json.dump(d, open(lp, "w", encoding="utf-8"), ensure_ascii=False, indent=2, sort_keys=True)
        recruiter_report.append(f"  {reg}: base {base}, {len(entries)} unites, ignorees: {skipped}")

    # rosters : methodes ajoutees avant la fermeture de classe
    if roster_methods:
        os.makedirs(f"{PKG}/hire", exist_ok=True)
        ro = ro.rstrip()[:-1].rstrip() + "\n" + "".join(roster_methods) + "\n}\n"
        open(f"{PKG}/hire/LOTRHireRosters.java", "w").write(ro)

    # carrefours : re-injection dans les fichiers deja produits par usine v1
    for name, adds in (("init/LOTREntities.java", ent_add),
                      ("init/LOTRSpawnEggs.java", egg_add)):
        p = f"{PKG}/{name}"
        src = open(p).read()
        if "LOTRSpawnEggs" in name:
            src = src.replace("    public static void init() {",
                              "".join(adds) + "\n    public static void init() {", 1)
        else:
            src = src.rstrip()[:-1].rstrip() + "\n\n" + "".join(adds) + "\n}\n"
        open(p, "w").write(src)
    p = f"{PKG}/LOTRMod.java"
    txt = open(p).read()
    open(p, "w").write(U.insert_before_method_end(
            txt, "private void onEntityAttributes", "\n" + "\n".join(mod_add)))
    p = f"{PKG}/client/ClientSetup.java"
    txt = open(p).read()
    open(p, "w").write(U.insert_before_method_end(
            txt, "private static void defineRenderers()", "\n" + "\n".join(cs_add)))
    print("RECRUTEURS :")
    for line in recruiter_report:
        print(line)

if __name__ == "__main__":
    run(sys.argv[1], sys.argv[2])
