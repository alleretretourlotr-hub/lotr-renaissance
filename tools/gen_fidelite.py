#!/usr/bin/env python3
# -*- coding: utf-8 -*-
"""Regeneration fidele des 68 classes PNJ depuis npc_fidelity.json."""
import json, os, re

DATA = json.load(open("/home/claude/npc_fidelity.json"))
SOURCES = [
    "/home/claude/lot-hire-fidele/src/main/java/fr/alleretretour/lotr/entity/npc",
    "/home/claude/lot-speech/src/main/java/fr/alleretretour/lotr/entity/npc",
    "/home/claude/lot-ranged/src/main/java/fr/alleretretour/lotr/entity/npc",
    "/home/claude/megalot/src/main/java/fr/alleretretour/lotr/entity/npc",
    "/home/claude/lotr-renaissance/src/main/java/fr/alleretretour/lotr/entity/npc",
]
OUT = "/home/claude/lot-fidelite-pnj/src/main/java/fr/alleretretour/lotr/entity/npc"
os.makedirs(OUT, exist_ok=True)

def camel(reg):
    return "".join(w.capitalize() for w in reg.split("_"))

def find_source(cls):
    for base in SOURCES:
        p = f"{base}/LOTREntity{cls}.java"
        if os.path.exists(p):
            return p
    return None

def remove_methods_containing(src, token):
    """Supprime CHAQUE methode dont la signature contient token, quel que soit
    le style (types importes ou pleinement qualifies), annotations comprises."""
    while True:
        i = src.find(token)
        if i < 0:
            return src
        j = src.find("{", i)
        if j < 0:
            return src
        depth = 0
        end = None
        for k in range(j, len(src)):
            if src[k] == "{":
                depth += 1
            elif src[k] == "}":
                depth -= 1
                if depth == 0:
                    end = k
                    break
        if end is None:
            return src
        start = src.rfind("\n\n", 0, i)
        start = 0 if start < 0 else start
        src = src[:start] + src[end + 1:]

def gear_block(reg, d, is_ranged_class):
    weapons = d["weapons"]
    ranged = d["ranged"]
    armor = d["armor"]
    if not weapons and not ranged and not armor:
        return "", False
    lines = []
    needs_ranged = False
    if ranged:
        lines.append(f'        setItemSlot(net.minecraft.inventory.EquipmentSlotType.MAINHAND, stackOf("{ranged[0]}"));')
        needs_ranged = True
    elif weapons:
        throwing = [w for w in weapons if "throwing" in w]
        if is_ranged_class and throwing:
            lines.append(f'        setItemSlot(net.minecraft.inventory.EquipmentSlotType.MAINHAND, stackOf("{throwing[0]}"));')
            needs_ranged = True
        else:
            pool = ", ".join(f'"{w}"' for w in weapons)
            lines.append(f"        String[] pool = {{{pool}}};")
            lines.append("        setItemSlot(net.minecraft.inventory.EquipmentSlotType.MAINHAND,")
            lines.append("                stackOf(pool[getRandom().nextInt(pool.length)]));")
    for slot in ("HEAD", "CHEST", "LEGS", "FEET"):
        if slot in armor:
            lines.append(f'        setItemSlot(net.minecraft.inventory.EquipmentSlotType.{slot}, stackOf("{armor[slot]}"));')
    body = "\n".join(lines)
    block = f'''
    private static net.minecraft.item.ItemStack stackOf(String id) {{
        net.minecraft.item.Item item = net.minecraftforge.registries.ForgeRegistries.ITEMS
                .getValue(new net.minecraft.util.ResourceLocation(id));
        return item == null ? net.minecraft.item.ItemStack.EMPTY
                : new net.minecraft.item.ItemStack(item);
    }}

    /** PORT de onSpawnWithEgg (Legacy LOTREntity{d["legacy"]}) : pool d'armes + armure exacts. */
    @javax.annotation.Nullable
    @Override
    public net.minecraft.entity.ILivingEntityData finalizeSpawn(
            net.minecraft.world.IServerWorld world, net.minecraft.world.DifficultyInstance difficulty,
            net.minecraft.entity.SpawnReason reason,
            @javax.annotation.Nullable net.minecraft.entity.ILivingEntityData data,
            @javax.annotation.Nullable net.minecraft.nbt.CompoundNBT nbt) {{
{body}
        for (net.minecraft.inventory.EquipmentSlotType slot
                : net.minecraft.inventory.EquipmentSlotType.values()) {{
            setDropChance(slot, 0.05f);
        }}
        return super.finalizeSpawn(world, difficulty, reason, data, nbt);
    }}
'''
    return block, needs_ranged

stats = {"patched": 0, "gear": 0, "ranged_added": 0}
report = []
for reg, d in DATA.items():
    cls = camel(reg)
    path = find_source(cls)
    assert path, cls
    src = open(path, encoding="utf-8").read()

    # 1. PV exacts + degats de base 2.0 (l'arme porte le reste, comme en 1.7.10)
    old_hp = re.search(r"MAX_HEALTH, ([\d.]+)", src)
    src = re.sub(r"\.add\(Attributes\.MAX_HEALTH, [\d.]+\)",
                 f".add(Attributes.MAX_HEALTH, {d['hp']})", src)
    src = re.sub(r"\.add\(Attributes\.ATTACK_DAMAGE, [\d.]+\)",
                 ".add(Attributes.ATTACK_DAMAGE, 2.0)", src)
    if old_hp and float(old_hp.group(1)) != d["hp"]:
        report.append(f"{reg}: PV {old_hp.group(1)} -> {d['hp']}")

    # 2. purge de l'ancien equipement puis reinjection fidele
    src = remove_methods_containing(src, "Item itemOf(")
    src = remove_methods_containing(src, "finalizeSpawn(")
    block, needs_ranged = gear_block(reg, d, "isRangedNPC" in src)
    if block:
        src = src.rstrip()
        assert src.endswith("}")
        src = src[:-1].rstrip() + "\n" + block + "}\n"
        stats["gear"] += 1

    # 3. IA de tir si l'arme principale l'exige
    if needs_ranged and "isRangedNPC" not in src:
        anchor = "    public static AttributeModifierMap.MutableAttribute createAttributes() {"
        src = src.replace(anchor,
            "    @Override\n    protected boolean isRangedNPC() {\n        return true;\n    }\n\n" + anchor, 1)
        stats["ranged_added"] += 1
        report.append(f"{reg}: IA de tir ajoutee (arme Legacy a distance)")

    open(f"{OUT}/LOTREntity{cls}.java", "w", encoding="utf-8").write(src)
    stats["patched"] += 1

print(stats)
print(f"{len(report)} corrections notables :")
for r in report[:30]:
    print(" ", r)
