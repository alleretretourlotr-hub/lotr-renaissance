#!/usr/bin/env python3
# -*- coding: utf-8 -*-
"""Lot armures de monture : 20 items (valeurs et textures du Legacy),
support sur les montures non-chevalines, equipement a l'embauche."""
import json, os, re, shutil, sys
sys.path.insert(0, "/home/claude")
import usine as U

LEG = "/home/claude/lotr-legacy/src/main/resources/assets/lotr"
B = "/home/claude/lot-final5/src/main/java/fr/alleretretour/lotr"
BRES = "/home/claude/lot-final5/src/main/resources/assets/lotr"
OUT = "/home/claude/lot-mountarmor"
PKG = f"{OUT}/src/main/java/fr/alleretretour/lotr"
RES = f"{OUT}/src/main/resources/assets/lotr"
for d in (f"{PKG}/item", f"{PKG}/init", f"{PKG}/client/render", f"{PKG}/entity/animal",
          f"{PKG}/hire", f"{PKG}/fac", f"{RES}/textures/item", f"{RES}/textures/armor/mount",
          f"{RES}/models/item", f"{RES}/lang"):
    os.makedirs(d, exist_ok=True)

# (id, texture Legacy item, texture Legacy armure, monture, reduction, nom FR, nom EN)
ARMORS = [
 ("horse_armor_gondor", "horseArmorGondor", "horse_gondor", "HORSE", 11,
  "Armure de cheval du Gondor", "Gondor Horse Armour"),
 ("horse_armor_rohan", "horseArmorRohan", "horse_rohan", "HORSE", 9,
  "Armure de cheval du Rohan", "Rohan Horse Armour"),
 ("horse_armor_galadhrim", "horseArmorGaladhrim", "horse_galadhrim", "HORSE", 11,
  "Armure de cheval galadhrim", "Galadhrim Horse Armour"),
 ("horse_armor_high_elven", "horseArmorHighElven", "horse_high_elven", "HORSE", 11,
  "Armure de cheval haut-elfique", "High-elven Horse Armour"),
 ("horse_armor_rivendell", "horseArmorRivendell", "horse_rivendell", "HORSE", 11,
  "Armure de cheval de Fondcombe", "Rivendell Horse Armour"),
 ("horse_armor_near_harad", "horseArmorNearHarad", "horse_near_harad", "HORSE", 9,
  "Armure de cheval du Proche-Harad", "Near-Haradric Horse Armour"),
 ("horse_armor_rhun_gold", "horseArmorRhunGold", "horse_rhun_gold", "HORSE", 11,
  "Armure de cheval d'or du Rhun", "Rhun Gold Horse Armour"),
 ("horse_armor_umbar", "horseArmorUmbar", "horse_umbar", "HORSE", 11,
  "Armure de cheval d'Umbar", "Umbar Horse Armour"),
 ("horse_armor_dale", "horseArmorDale", "horse_dale", "HORSE", 11,
  "Armure de cheval de Dale", "Dale Horse Armour"),
 ("horse_armor_lamedon", "horseArmorLamedon", "horse_lamedon", "HORSE", 9,
  "Armure de cheval de Lamedon", "Lamedon Horse Armour"),
 ("horse_armor_dol_amroth", "horseArmorDolAmroth", "horse_dol_amroth", "HORSE", 11,
  "Armure de cheval de Dol Amroth", "Dol Amroth Horse Armour"),
 ("horse_armor_morgul", "horseArmorMorgul", "horse_morgul", "HORSE", 11,
  "Armure de cheval de Morgul", "Morgul Horse Armour"),
 ("horse_armor_mithril", "horseArmorMithril", "horse_mithril", "HORSE", 14,
  "Armure de cheval en mithril", "Mithril Horse Armour"),
 ("boar_armor_dwarven", "boarArmorDwarven", "boar_dwarven", "BOAR", 13,
  "Armure de sanglier naine", "Dwarven Boar Armour"),
 ("boar_armor_blue_dwarven", "boarArmorBlueDwarven", "boar_blue_dwarven", "BOAR", 13,
  "Armure de sanglier des Montagnes Bleues", "Blue Dwarven Boar Armour"),
 ("warg_armor_angmar", "wargArmorAngmar", "warg_angmar", "WARG", 11,
  "Armure de warg d'Angmar", "Angmar Warg Armour"),
 ("warg_armor_mordor", "wargArmorMordor", "warg_mordor", "WARG", 11,
  "Armure de warg du Mordor", "Mordor Warg Armour"),
 ("warg_armor_uruk", "wargArmorUruk", "warg_uruk", "WARG", 13,
  "Armure de warg uruk", "Uruk Warg Armour"),
 ("rhino_armor_half_troll", "rhinoArmorHalfTroll", "rhino_half_troll", "RHINO", 9,
  "Armure de rhinoceros semi-troll", "Half-troll Rhino Armour"),
]

# ---------- textures ----------
for reg, item_tex, armor_tex, mount, prot, fr, en in ARMORS:
    shutil.copy(f"{LEG}/textures/items/{item_tex}.png", f"{RES}/textures/item/{reg}.png")
    shutil.copy(f"{LEG}/armor/mount/{armor_tex}.png", f"{RES}/textures/armor/mount/{armor_tex}.png")
    json.dump({"parent": "minecraft:item/generated",
               "textures": {"layer0": f"lotr:item/{reg}"}},
              open(f"{RES}/models/item/{reg}.json", "w"), indent=2)

# ---------- classe d'item ----------
open(f"{PKG}/item/LOTRItemMountArmor.java", "w").write('''package fr.alleretretour.lotr.item;

import net.minecraft.item.HorseArmorItem;
import net.minecraft.util.ResourceLocation;

/**
 * PORT de lotr.common.item.LOTRItemMountArmor : armure de monture.
 * damageReduceAmount = protection du plastron + des jambieres du materiau
 * (formule exacte du Legacy), texture armor/mount/<monture>_<materiau>.png.
 * Herite de HorseArmorItem : les chevaux (et zebres) l'acceptent nativement ;
 * les autres montures passent par LOTRMountArmored.
 */
public class LOTRItemMountArmor extends HorseArmorItem {

    /** PORT de LOTRItemMountArmor.Mount. */
    public enum Mount {
        HORSE, BOAR, WARG, RHINO, ELK, CAMEL, GIRAFFE
    }

    private final Mount mountType;
    private final ResourceLocation armorTexture;

    public LOTRItemMountArmor(Mount mount, int protection, String textureName,
                              Properties properties) {
        super(protection, new ResourceLocation("lotr", "textures/armor/mount/"
                + textureName + ".png"), properties);
        this.mountType = mount;
        this.armorTexture = new ResourceLocation("lotr", "textures/armor/mount/"
                + textureName + ".png");
    }

    public Mount getMountType() {
        return mountType;
    }

    public ResourceLocation getArmorTexture() {
        return armorTexture;
    }

    public boolean fits(Mount mount) {
        return mountType == mount;
    }
}
''')

# ---------- registre ----------
items = []
for reg, item_tex, armor_tex, mount, prot, fr, en in ARMORS:
    items.append(f'''    public static final RegistryObject<Item> {reg.upper()} =
            LOTRItems.ITEMS.register("{reg}",
                    () -> new LOTRItemMountArmor(LOTRItemMountArmor.Mount.{mount}, {prot},
                            "{armor_tex}", new Item.Properties()
                            .tab(LOTRItems.TAB_LOTR).stacksTo(1)));
''')
open(f"{PKG}/init/LOTRItemsMountArmor.java", "w").write(f'''package fr.alleretretour.lotr.init;

import fr.alleretretour.lotr.item.LOTRItemMountArmor;
import net.minecraft.item.Item;
import net.minecraftforge.fml.RegistryObject;

/** PORT des armures de monture du Legacy (valeurs de reduction exactes). */
public final class LOTRItemsMountArmor {{

{"".join(items)}
    private LOTRItemsMountArmor() {{
    }}

    public static void init() {{
    }}
}}
''')

# ---------- interface + support sur les montures non-chevalines ----------
open(f"{PKG}/entity/animal/LOTRMountArmored.java", "w").write('''package fr.alleretretour.lotr.entity.animal;

import net.minecraft.item.ItemStack;

/** Monture pouvant porter une armure (montures non-chevalines du Legacy). */
public interface LOTRMountArmored {

    void setMountArmor(ItemStack stack);

    ItemStack getMountArmor();
}
''')

ARMOR_SUPPORT = '''
    // ===== ARMURE DE MONTURE (PORT LOTRNPCMount) =====
    private static final net.minecraft.network.datasync.DataParameter<net.minecraft.item.ItemStack>
            MOUNT_ARMOR = net.minecraft.network.datasync.EntityDataManager.defineId(
            %(cls)s.class, net.minecraft.network.datasync.DataSerializers.ITEM_STACK);

    @Override
    public void setMountArmor(net.minecraft.item.ItemStack stack) {
        entityData.set(MOUNT_ARMOR, stack.copy());
        net.minecraft.entity.ai.attributes.ModifiableAttributeInstance attr =
                getAttribute(net.minecraft.entity.ai.attributes.Attributes.ARMOR);
        if (attr != null) {
            attr.setBaseValue(stack.getItem() instanceof fr.alleretretour.lotr.item.LOTRItemMountArmor
                    ? ((fr.alleretretour.lotr.item.LOTRItemMountArmor) stack.getItem())
                            .getProtection()
                    : 0.0);
        }
    }

    @Override
    public net.minecraft.item.ItemStack getMountArmor() {
        return entityData.get(MOUNT_ARMOR);
    }
'''

SRC = {"LOTREntityWildBoar": "/home/claude/lot-montures/src/main/java/fr/alleretretour/lotr/entity/animal/LOTREntityWildBoar.java",
       "LOTREntityWarg": "/home/claude/lot-creatures/src/main/java/fr/alleretretour/lotr/entity/animal/LOTREntityWarg.java",
       "LOTREntityRhino": "/home/claude/lot-creatures/src/main/java/fr/alleretretour/lotr/entity/animal/LOTREntityRhino.java"}
for cls, path in SRC.items():
    s = open(path, encoding="utf-8").read()
    s = s.replace(f"public class {cls} extends AnimalEntity {{",
                  f"public class {cls} extends AnimalEntity implements LOTRMountArmored {{", 1)
    # defineSynchedData : creer si absent
    if "protected void defineSynchedData()" in s:
        i = s.index("super.defineSynchedData();")
        s = s[:i] + "super.defineSynchedData();\n        entityData.define(MOUNT_ARMOR, net.minecraft.item.ItemStack.EMPTY);" + s[i + len("super.defineSynchedData();"):]
    else:
        anchor = "    public static AttributeModifierMap.MutableAttribute createAttributes() {"
        s = s.replace(anchor, '''    @Override
    protected void defineSynchedData() {
        super.defineSynchedData();
        entityData.define(MOUNT_ARMOR, net.minecraft.item.ItemStack.EMPTY);
    }

''' + anchor, 1)
    # attribut ARMOR
    s = re.sub(r"(\.add\(Attributes\.ATTACK_DAMAGE, [\d.]+\))",
               r"\1\n                .add(Attributes.ARMOR, 0.0)", s, count=1)
    # NBT
    if "addAdditionalSaveData" in s:
        i = s.index("super.addAdditionalSaveData(nbt);")
        s = s[:i] + "super.addAdditionalSaveData(nbt);\n        if (!getMountArmor().isEmpty()) {\n            nbt.put(\"MountArmor\", getMountArmor().save(new CompoundNBT()));\n        }" + s[i + len("super.addAdditionalSaveData(nbt);"):]
        i = s.index("super.readAdditionalSaveData(nbt);")
        s = s[:i] + "super.readAdditionalSaveData(nbt);\n        if (nbt.contains(\"MountArmor\")) {\n            setMountArmor(net.minecraft.item.ItemStack.of(nbt.getCompound(\"MountArmor\")));\n        }" + s[i + len("super.readAdditionalSaveData(nbt);"):]
    else:
        s = s.rstrip()[:-1].rstrip() + '''

    @Override
    public void addAdditionalSaveData(net.minecraft.nbt.CompoundNBT nbt) {
        super.addAdditionalSaveData(nbt);
        if (!getMountArmor().isEmpty()) {
            nbt.put("MountArmor", getMountArmor().save(new net.minecraft.nbt.CompoundNBT()));
        }
    }

    @Override
    public void readAdditionalSaveData(net.minecraft.nbt.CompoundNBT nbt) {
        super.readAdditionalSaveData(nbt);
        if (nbt.contains("MountArmor")) {
            setMountArmor(net.minecraft.item.ItemStack.of(nbt.getCompound("MountArmor")));
        }
    }
}
'''
    s = s.rstrip()[:-1].rstrip() + "\n" + (ARMOR_SUPPORT % {"cls": cls}) + "}\n"
    open(f"{PKG}/entity/animal/{cls}.java", "w", encoding="utf-8").write(s)

print(f"{len(ARMORS)} armures, 3 montures equipables")
