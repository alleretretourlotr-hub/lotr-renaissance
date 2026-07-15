package fr.alleretretour.lotr.init;

import fr.alleretretour.lotr.item.LOTRItemArmor;
import fr.alleretretour.lotr.item.LOTRItemBattleaxe;
import fr.alleretretour.lotr.item.LOTRItemDagger;
import fr.alleretretour.lotr.item.LOTRItemHammer;
import fr.alleretretour.lotr.item.LOTRItemLance;
import fr.alleretretour.lotr.item.LOTRItemPike;
import fr.alleretretour.lotr.item.LOTRItemPolearm;
import fr.alleretretour.lotr.item.LOTRItemPolearmLong;
import fr.alleretretour.lotr.item.LOTRItemSword;
import fr.alleretretour.lotr.item.LOTRMaterial;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.Item;
import net.minecraftforge.fml.RegistryObject;

/**
 * GENERE AUTOMATIQUEMENT depuis LOTRMod.java (Legacy 1.7.10) - ne pas editer a la main.
 * Toutes les epees, dagues et pieces d'armure du mod original.
 */
public class LOTRItemsCombat {

    /** Force le chargement de la classe (les champs statiques s'enregistrent). */
    public static void init() {
    }

    public static final RegistryObject<Item> BRONZE_SWORD = LOTRItems.ITEMS.register("bronze_sword",
            () -> new LOTRItemSword(LOTRMaterial.BRONZE));
    public static final RegistryObject<Item> BRONZE_HELMET = LOTRItems.ITEMS.register("bronze_helmet",
            () -> new LOTRItemArmor(LOTRMaterial.BRONZE, EquipmentSlotType.HEAD));
    public static final RegistryObject<Item> BRONZE_CHESTPLATE = LOTRItems.ITEMS.register("bronze_chestplate",
            () -> new LOTRItemArmor(LOTRMaterial.BRONZE, EquipmentSlotType.CHEST));
    public static final RegistryObject<Item> BRONZE_LEGGINGS = LOTRItems.ITEMS.register("bronze_leggings",
            () -> new LOTRItemArmor(LOTRMaterial.BRONZE, EquipmentSlotType.LEGS));
    public static final RegistryObject<Item> BRONZE_BOOTS = LOTRItems.ITEMS.register("bronze_boots",
            () -> new LOTRItemArmor(LOTRMaterial.BRONZE, EquipmentSlotType.FEET));
    public static final RegistryObject<Item> ORC_SCIMITAR = LOTRItems.ITEMS.register("orc_scimitar",
            () -> new LOTRItemSword(LOTRMaterial.MORDOR));
    public static final RegistryObject<Item> ORC_HELMET = LOTRItems.ITEMS.register("orc_helmet",
            () -> new LOTRItemArmor(LOTRMaterial.MORDOR, EquipmentSlotType.HEAD));
    public static final RegistryObject<Item> ORC_CHESTPLATE = LOTRItems.ITEMS.register("orc_chestplate",
            () -> new LOTRItemArmor(LOTRMaterial.MORDOR, EquipmentSlotType.CHEST));
    public static final RegistryObject<Item> ORC_LEGGINGS = LOTRItems.ITEMS.register("orc_leggings",
            () -> new LOTRItemArmor(LOTRMaterial.MORDOR, EquipmentSlotType.LEGS));
    public static final RegistryObject<Item> ORC_BOOTS = LOTRItems.ITEMS.register("orc_boots",
            () -> new LOTRItemArmor(LOTRMaterial.MORDOR, EquipmentSlotType.FEET));
    public static final RegistryObject<Item> ORC_BATTLEAXE = LOTRItems.ITEMS.register("orc_battleaxe",
            () -> new LOTRItemBattleaxe(LOTRMaterial.MORDOR));
    public static final RegistryObject<Item> ORC_DAGGER = LOTRItems.ITEMS.register("orc_dagger",
            () -> new LOTRItemDagger(LOTRMaterial.MORDOR));
    public static final RegistryObject<Item> ORC_POISONED_DAGGER = LOTRItems.ITEMS.register("orc_poisoned_dagger",
            () -> new LOTRItemDagger(LOTRMaterial.MORDOR, LOTRItemDagger.DaggerEffect.POISON));
    public static final RegistryObject<Item> MITHRIL_SWORD = LOTRItems.ITEMS.register("mithril_sword",
            () -> new LOTRItemSword(LOTRMaterial.MITHRIL));
    public static final RegistryObject<Item> GONDOR_SWORD = LOTRItems.ITEMS.register("gondor_sword",
            () -> new LOTRItemSword(LOTRMaterial.GONDOR));
    public static final RegistryObject<Item> GONDOR_HELMET = LOTRItems.ITEMS.register("gondor_helmet",
            () -> new LOTRItemArmor(LOTRMaterial.GONDOR, EquipmentSlotType.HEAD, "helmet"));
    public static final RegistryObject<Item> GONDOR_CHESTPLATE = LOTRItems.ITEMS.register("gondor_chestplate",
            () -> new LOTRItemArmor(LOTRMaterial.GONDOR, EquipmentSlotType.CHEST));
    public static final RegistryObject<Item> GONDOR_LEGGINGS = LOTRItems.ITEMS.register("gondor_leggings",
            () -> new LOTRItemArmor(LOTRMaterial.GONDOR, EquipmentSlotType.LEGS));
    public static final RegistryObject<Item> GONDOR_BOOTS = LOTRItems.ITEMS.register("gondor_boots",
            () -> new LOTRItemArmor(LOTRMaterial.GONDOR, EquipmentSlotType.FEET));
    public static final RegistryObject<Item> MITHRIL_HELMET = LOTRItems.ITEMS.register("mithril_helmet",
            () -> new LOTRItemArmor(LOTRMaterial.MITHRIL, EquipmentSlotType.HEAD));
    public static final RegistryObject<Item> MITHRIL_CHESTPLATE = LOTRItems.ITEMS.register("mithril_chestplate",
            () -> new LOTRItemArmor(LOTRMaterial.MITHRIL, EquipmentSlotType.CHEST));
    public static final RegistryObject<Item> MITHRIL_LEGGINGS = LOTRItems.ITEMS.register("mithril_leggings",
            () -> new LOTRItemArmor(LOTRMaterial.MITHRIL, EquipmentSlotType.LEGS));
    public static final RegistryObject<Item> MITHRIL_BOOTS = LOTRItems.ITEMS.register("mithril_boots",
            () -> new LOTRItemArmor(LOTRMaterial.MITHRIL, EquipmentSlotType.FEET));
    public static final RegistryObject<Item> MALLORN_SWORD = LOTRItems.ITEMS.register("mallorn_sword",
            () -> new LOTRItemSword(LOTRMaterial.MALLORN));
    public static final RegistryObject<Item> ELVEN_SWORD = LOTRItems.ITEMS.register("elven_sword",
            () -> new LOTRItemSword(LOTRMaterial.GALADHRIM, true));
    public static final RegistryObject<Item> ELVEN_HELMET = LOTRItems.ITEMS.register("elven_helmet",
            () -> new LOTRItemArmor(LOTRMaterial.GALADHRIM, EquipmentSlotType.HEAD, "helmet"));
    public static final RegistryObject<Item> ELVEN_CHESTPLATE = LOTRItems.ITEMS.register("elven_chestplate",
            () -> new LOTRItemArmor(LOTRMaterial.GALADHRIM, EquipmentSlotType.CHEST));
    public static final RegistryObject<Item> ELVEN_LEGGINGS = LOTRItems.ITEMS.register("elven_leggings",
            () -> new LOTRItemArmor(LOTRMaterial.GALADHRIM, EquipmentSlotType.LEGS));
    public static final RegistryObject<Item> ELVEN_BOOTS = LOTRItems.ITEMS.register("elven_boots",
            () -> new LOTRItemArmor(LOTRMaterial.GALADHRIM, EquipmentSlotType.FEET));
    public static final RegistryObject<Item> WARG_HELMET = LOTRItems.ITEMS.register("warg_helmet",
            () -> new LOTRItemArmor(LOTRMaterial.FUR, EquipmentSlotType.HEAD));
    public static final RegistryObject<Item> WARG_CHESTPLATE = LOTRItems.ITEMS.register("warg_chestplate",
            () -> new LOTRItemArmor(LOTRMaterial.FUR, EquipmentSlotType.CHEST));
    public static final RegistryObject<Item> WARG_LEGGINGS = LOTRItems.ITEMS.register("warg_leggings",
            () -> new LOTRItemArmor(LOTRMaterial.FUR, EquipmentSlotType.LEGS));
    public static final RegistryObject<Item> WARG_BOOTS = LOTRItems.ITEMS.register("warg_boots",
            () -> new LOTRItemArmor(LOTRMaterial.FUR, EquipmentSlotType.FEET));
    public static final RegistryObject<Item> HAMMER_BLACKSMITH = LOTRItems.ITEMS.register("hammer_blacksmith",
            () -> new LOTRItemHammer(LOTRMaterial.GONDOR));
    public static final RegistryObject<Item> GONDOR_DAGGER = LOTRItems.ITEMS.register("gondor_dagger",
            () -> new LOTRItemDagger(LOTRMaterial.GONDOR));
    public static final RegistryObject<Item> ELVEN_DAGGER = LOTRItems.ITEMS.register("elven_dagger",
            () -> new LOTRItemDagger(LOTRMaterial.GALADHRIM));
    public static final RegistryObject<Item> DWARVEN_SWORD = LOTRItems.ITEMS.register("dwarven_sword",
            () -> new LOTRItemSword(LOTRMaterial.DWARVEN));
    public static final RegistryObject<Item> DWARVEN_DAGGER = LOTRItems.ITEMS.register("dwarven_dagger",
            () -> new LOTRItemDagger(LOTRMaterial.DWARVEN));
    public static final RegistryObject<Item> DWARVEN_BATTLEAXE = LOTRItems.ITEMS.register("dwarven_battleaxe",
            () -> new LOTRItemBattleaxe(LOTRMaterial.DWARVEN));
    public static final RegistryObject<Item> DWARVEN_HAMMER = LOTRItems.ITEMS.register("dwarven_hammer",
            () -> new LOTRItemHammer(LOTRMaterial.DWARVEN));
    public static final RegistryObject<Item> ORC_HAMMER = LOTRItems.ITEMS.register("orc_hammer",
            () -> new LOTRItemHammer(LOTRMaterial.MORDOR));
    public static final RegistryObject<Item> DWARVEN_HELMET = LOTRItems.ITEMS.register("dwarven_helmet",
            () -> new LOTRItemArmor(LOTRMaterial.DWARVEN, EquipmentSlotType.HEAD));
    public static final RegistryObject<Item> DWARVEN_CHESTPLATE = LOTRItems.ITEMS.register("dwarven_chestplate",
            () -> new LOTRItemArmor(LOTRMaterial.DWARVEN, EquipmentSlotType.CHEST));
    public static final RegistryObject<Item> DWARVEN_LEGGINGS = LOTRItems.ITEMS.register("dwarven_leggings",
            () -> new LOTRItemArmor(LOTRMaterial.DWARVEN, EquipmentSlotType.LEGS));
    public static final RegistryObject<Item> DWARVEN_BOOTS = LOTRItems.ITEMS.register("dwarven_boots",
            () -> new LOTRItemArmor(LOTRMaterial.DWARVEN, EquipmentSlotType.FEET));
    public static final RegistryObject<Item> GALVORN_HELMET = LOTRItems.ITEMS.register("galvorn_helmet",
            () -> new LOTRItemArmor(LOTRMaterial.GALVORN, EquipmentSlotType.HEAD));
    public static final RegistryObject<Item> GALVORN_CHESTPLATE = LOTRItems.ITEMS.register("galvorn_chestplate",
            () -> new LOTRItemArmor(LOTRMaterial.GALVORN, EquipmentSlotType.CHEST));
    public static final RegistryObject<Item> GALVORN_LEGGINGS = LOTRItems.ITEMS.register("galvorn_leggings",
            () -> new LOTRItemArmor(LOTRMaterial.GALVORN, EquipmentSlotType.LEGS));
    public static final RegistryObject<Item> GALVORN_BOOTS = LOTRItems.ITEMS.register("galvorn_boots",
            () -> new LOTRItemArmor(LOTRMaterial.GALVORN, EquipmentSlotType.FEET));
    public static final RegistryObject<Item> BRONZE_DAGGER = LOTRItems.ITEMS.register("bronze_dagger",
            () -> new LOTRItemDagger(LOTRMaterial.BRONZE));
    public static final RegistryObject<Item> MITHRIL_DAGGER = LOTRItems.ITEMS.register("mithril_dagger",
            () -> new LOTRItemDagger(LOTRMaterial.MITHRIL));
    public static final RegistryObject<Item> MITHRIL_BATTLEAXE = LOTRItems.ITEMS.register("mithril_battleaxe",
            () -> new LOTRItemBattleaxe(LOTRMaterial.MITHRIL));
    public static final RegistryObject<Item> MITHRIL_HAMMER = LOTRItems.ITEMS.register("mithril_hammer",
            () -> new LOTRItemHammer(LOTRMaterial.MITHRIL));
    public static final RegistryObject<Item> GONDOR_HAMMER = LOTRItems.ITEMS.register("gondor_hammer",
            () -> new LOTRItemHammer(LOTRMaterial.GONDOR));
    public static final RegistryObject<Item> URUK_SCIMITAR = LOTRItems.ITEMS.register("uruk_scimitar",
            () -> new LOTRItemSword(LOTRMaterial.URUK));
    public static final RegistryObject<Item> URUK_DAGGER = LOTRItems.ITEMS.register("uruk_dagger",
            () -> new LOTRItemDagger(LOTRMaterial.URUK));
    public static final RegistryObject<Item> URUK_POISONED_DAGGER = LOTRItems.ITEMS.register("uruk_poisoned_dagger",
            () -> new LOTRItemDagger(LOTRMaterial.URUK, LOTRItemDagger.DaggerEffect.POISON));
    public static final RegistryObject<Item> URUK_BATTLEAXE = LOTRItems.ITEMS.register("uruk_battleaxe",
            () -> new LOTRItemBattleaxe(LOTRMaterial.URUK));
    public static final RegistryObject<Item> URUK_HAMMER = LOTRItems.ITEMS.register("uruk_hammer",
            () -> new LOTRItemHammer(LOTRMaterial.URUK));
    public static final RegistryObject<Item> URUK_HELMET = LOTRItems.ITEMS.register("uruk_helmet",
            () -> new LOTRItemArmor(LOTRMaterial.URUK, EquipmentSlotType.HEAD, "helmet"));
    public static final RegistryObject<Item> URUK_CHESTPLATE = LOTRItems.ITEMS.register("uruk_chestplate",
            () -> new LOTRItemArmor(LOTRMaterial.URUK, EquipmentSlotType.CHEST));
    public static final RegistryObject<Item> URUK_LEGGINGS = LOTRItems.ITEMS.register("uruk_leggings",
            () -> new LOTRItemArmor(LOTRMaterial.URUK, EquipmentSlotType.LEGS));
    public static final RegistryObject<Item> URUK_BOOTS = LOTRItems.ITEMS.register("uruk_boots",
            () -> new LOTRItemArmor(LOTRMaterial.URUK, EquipmentSlotType.FEET));
    public static final RegistryObject<Item> WOOD_ELVEN_SCOUT_HELMET = LOTRItems.ITEMS.register("wood_elven_scout_helmet",
            () -> new LOTRItemArmor(LOTRMaterial.WOOD_ELVEN_SCOUT, EquipmentSlotType.HEAD));
    public static final RegistryObject<Item> WOOD_ELVEN_SCOUT_CHESTPLATE = LOTRItems.ITEMS.register("wood_elven_scout_chestplate",
            () -> new LOTRItemArmor(LOTRMaterial.WOOD_ELVEN_SCOUT, EquipmentSlotType.CHEST));
    public static final RegistryObject<Item> WOOD_ELVEN_SCOUT_LEGGINGS = LOTRItems.ITEMS.register("wood_elven_scout_leggings",
            () -> new LOTRItemArmor(LOTRMaterial.WOOD_ELVEN_SCOUT, EquipmentSlotType.LEGS));
    public static final RegistryObject<Item> WOOD_ELVEN_SCOUT_BOOTS = LOTRItems.ITEMS.register("wood_elven_scout_boots",
            () -> new LOTRItemArmor(LOTRMaterial.WOOD_ELVEN_SCOUT, EquipmentSlotType.FEET));
    public static final RegistryObject<Item> ROHAN_SWORD = LOTRItems.ITEMS.register("rohan_sword",
            () -> new LOTRItemSword(LOTRMaterial.ROHAN));
    public static final RegistryObject<Item> ROHAN_DAGGER = LOTRItems.ITEMS.register("rohan_dagger",
            () -> new LOTRItemDagger(LOTRMaterial.ROHAN));
    public static final RegistryObject<Item> ROHAN_HELMET = LOTRItems.ITEMS.register("rohan_helmet",
            () -> new LOTRItemArmor(LOTRMaterial.ROHAN, EquipmentSlotType.HEAD));
    public static final RegistryObject<Item> ROHAN_CHESTPLATE = LOTRItems.ITEMS.register("rohan_chestplate",
            () -> new LOTRItemArmor(LOTRMaterial.ROHAN, EquipmentSlotType.CHEST));
    public static final RegistryObject<Item> ROHAN_LEGGINGS = LOTRItems.ITEMS.register("rohan_leggings",
            () -> new LOTRItemArmor(LOTRMaterial.ROHAN, EquipmentSlotType.LEGS));
    public static final RegistryObject<Item> ROHAN_BOOTS = LOTRItems.ITEMS.register("rohan_boots",
            () -> new LOTRItemArmor(LOTRMaterial.ROHAN, EquipmentSlotType.FEET));
    public static final RegistryObject<Item> GONDOR_WINGED_HELMET = LOTRItems.ITEMS.register("gondor_winged_helmet",
            () -> new LOTRItemArmor(LOTRMaterial.GONDOR, EquipmentSlotType.HEAD, "wingedHelmet"));
    public static final RegistryObject<Item> RANGER_HELMET = LOTRItems.ITEMS.register("ranger_helmet",
            () -> new LOTRItemArmor(LOTRMaterial.RANGER, EquipmentSlotType.HEAD));
    public static final RegistryObject<Item> RANGER_CHESTPLATE = LOTRItems.ITEMS.register("ranger_chestplate",
            () -> new LOTRItemArmor(LOTRMaterial.RANGER, EquipmentSlotType.CHEST));
    public static final RegistryObject<Item> RANGER_LEGGINGS = LOTRItems.ITEMS.register("ranger_leggings",
            () -> new LOTRItemArmor(LOTRMaterial.RANGER, EquipmentSlotType.LEGS));
    public static final RegistryObject<Item> RANGER_BOOTS = LOTRItems.ITEMS.register("ranger_boots",
            () -> new LOTRItemArmor(LOTRMaterial.RANGER, EquipmentSlotType.FEET));
    public static final RegistryObject<Item> DUNLENDING_HELMET = LOTRItems.ITEMS.register("dunlending_helmet",
            () -> new LOTRItemArmor(LOTRMaterial.DUNLENDING, EquipmentSlotType.HEAD));
    public static final RegistryObject<Item> DUNLENDING_CHESTPLATE = LOTRItems.ITEMS.register("dunlending_chestplate",
            () -> new LOTRItemArmor(LOTRMaterial.DUNLENDING, EquipmentSlotType.CHEST));
    public static final RegistryObject<Item> DUNLENDING_LEGGINGS = LOTRItems.ITEMS.register("dunlending_leggings",
            () -> new LOTRItemArmor(LOTRMaterial.DUNLENDING, EquipmentSlotType.LEGS));
    public static final RegistryObject<Item> DUNLENDING_BOOTS = LOTRItems.ITEMS.register("dunlending_boots",
            () -> new LOTRItemArmor(LOTRMaterial.DUNLENDING, EquipmentSlotType.FEET));
    public static final RegistryObject<Item> BLADE_MORGUL = LOTRItems.ITEMS.register("blade_morgul",
            () -> new LOTRItemSword(LOTRMaterial.MORGUL));
    public static final RegistryObject<Item> MORGUL_HELMET = LOTRItems.ITEMS.register("morgul_helmet",
            () -> new LOTRItemArmor(LOTRMaterial.MORGUL, EquipmentSlotType.HEAD, "helmet"));
    public static final RegistryObject<Item> MORGUL_CHESTPLATE = LOTRItems.ITEMS.register("morgul_chestplate",
            () -> new LOTRItemArmor(LOTRMaterial.MORGUL, EquipmentSlotType.CHEST));
    public static final RegistryObject<Item> MORGUL_LEGGINGS = LOTRItems.ITEMS.register("morgul_leggings",
            () -> new LOTRItemArmor(LOTRMaterial.MORGUL, EquipmentSlotType.LEGS));
    public static final RegistryObject<Item> MORGUL_BOOTS = LOTRItems.ITEMS.register("morgul_boots",
            () -> new LOTRItemArmor(LOTRMaterial.MORGUL, EquipmentSlotType.FEET));
    public static final RegistryObject<Item> WOOD_ELVEN_SWORD = LOTRItems.ITEMS.register("wood_elven_sword",
            () -> new LOTRItemSword(LOTRMaterial.WOOD_ELVEN, true));
    public static final RegistryObject<Item> WOOD_ELVEN_DAGGER = LOTRItems.ITEMS.register("wood_elven_dagger",
            () -> new LOTRItemDagger(LOTRMaterial.WOOD_ELVEN));
    public static final RegistryObject<Item> WOOD_ELVEN_HELMET = LOTRItems.ITEMS.register("wood_elven_helmet",
            () -> new LOTRItemArmor(LOTRMaterial.WOOD_ELVEN, EquipmentSlotType.HEAD));
    public static final RegistryObject<Item> WOOD_ELVEN_CHESTPLATE = LOTRItems.ITEMS.register("wood_elven_chestplate",
            () -> new LOTRItemArmor(LOTRMaterial.WOOD_ELVEN, EquipmentSlotType.CHEST));
    public static final RegistryObject<Item> WOOD_ELVEN_LEGGINGS = LOTRItems.ITEMS.register("wood_elven_leggings",
            () -> new LOTRItemArmor(LOTRMaterial.WOOD_ELVEN, EquipmentSlotType.LEGS));
    public static final RegistryObject<Item> WOOD_ELVEN_BOOTS = LOTRItems.ITEMS.register("wood_elven_boots",
            () -> new LOTRItemArmor(LOTRMaterial.WOOD_ELVEN, EquipmentSlotType.FEET));
    public static final RegistryObject<Item> BRONZE_POISONED_DAGGER = LOTRItems.ITEMS.register("bronze_poisoned_dagger",
            () -> new LOTRItemDagger(LOTRMaterial.BRONZE, LOTRItemDagger.DaggerEffect.POISON));
    public static final RegistryObject<Item> MITHRIL_POISONED_DAGGER = LOTRItems.ITEMS.register("mithril_poisoned_dagger",
            () -> new LOTRItemDagger(LOTRMaterial.MITHRIL, LOTRItemDagger.DaggerEffect.POISON));
    public static final RegistryObject<Item> GONDOR_POISONED_DAGGER = LOTRItems.ITEMS.register("gondor_poisoned_dagger",
            () -> new LOTRItemDagger(LOTRMaterial.GONDOR, LOTRItemDagger.DaggerEffect.POISON));
    public static final RegistryObject<Item> ELVEN_POISONED_DAGGER = LOTRItems.ITEMS.register("elven_poisoned_dagger",
            () -> new LOTRItemDagger(LOTRMaterial.GALADHRIM, LOTRItemDagger.DaggerEffect.POISON));
    public static final RegistryObject<Item> DWARVEN_POISONED_DAGGER = LOTRItems.ITEMS.register("dwarven_poisoned_dagger",
            () -> new LOTRItemDagger(LOTRMaterial.DWARVEN, LOTRItemDagger.DaggerEffect.POISON));
    public static final RegistryObject<Item> ROHAN_POISONED_DAGGER = LOTRItems.ITEMS.register("rohan_poisoned_dagger",
            () -> new LOTRItemDagger(LOTRMaterial.ROHAN, LOTRItemDagger.DaggerEffect.POISON));
    public static final RegistryObject<Item> WOOD_ELVEN_POISONED_DAGGER = LOTRItems.ITEMS.register("wood_elven_poisoned_dagger",
            () -> new LOTRItemDagger(LOTRMaterial.WOOD_ELVEN, LOTRItemDagger.DaggerEffect.POISON));
    public static final RegistryObject<Item> ANGMAR_SWORD = LOTRItems.ITEMS.register("angmar_sword",
            () -> new LOTRItemSword(LOTRMaterial.ANGMAR));
    public static final RegistryObject<Item> ANGMAR_DAGGER = LOTRItems.ITEMS.register("angmar_dagger",
            () -> new LOTRItemDagger(LOTRMaterial.ANGMAR));
    public static final RegistryObject<Item> ANGMAR_POISONED_DAGGER = LOTRItems.ITEMS.register("angmar_poisoned_dagger",
            () -> new LOTRItemDagger(LOTRMaterial.ANGMAR, LOTRItemDagger.DaggerEffect.POISON));
    public static final RegistryObject<Item> ANGMAR_BATTLEAXE = LOTRItems.ITEMS.register("angmar_battleaxe",
            () -> new LOTRItemBattleaxe(LOTRMaterial.ANGMAR));
    public static final RegistryObject<Item> ANGMAR_HAMMER = LOTRItems.ITEMS.register("angmar_hammer",
            () -> new LOTRItemHammer(LOTRMaterial.ANGMAR));
    public static final RegistryObject<Item> ANGMAR_HELMET = LOTRItems.ITEMS.register("angmar_helmet",
            () -> new LOTRItemArmor(LOTRMaterial.ANGMAR, EquipmentSlotType.HEAD));
    public static final RegistryObject<Item> ANGMAR_CHESTPLATE = LOTRItems.ITEMS.register("angmar_chestplate",
            () -> new LOTRItemArmor(LOTRMaterial.ANGMAR, EquipmentSlotType.CHEST));
    public static final RegistryObject<Item> ANGMAR_LEGGINGS = LOTRItems.ITEMS.register("angmar_leggings",
            () -> new LOTRItemArmor(LOTRMaterial.ANGMAR, EquipmentSlotType.LEGS));
    public static final RegistryObject<Item> ANGMAR_BOOTS = LOTRItems.ITEMS.register("angmar_boots",
            () -> new LOTRItemArmor(LOTRMaterial.ANGMAR, EquipmentSlotType.FEET));
    public static final RegistryObject<Item> ROHAN_BATTLEAXE = LOTRItems.ITEMS.register("rohan_battleaxe",
            () -> new LOTRItemBattleaxe(LOTRMaterial.ROHAN));
    public static final RegistryObject<Item> NEAR_HARAD_SCIMITAR = LOTRItems.ITEMS.register("near_harad_scimitar",
            () -> new LOTRItemSword(LOTRMaterial.UMBAR));
    public static final RegistryObject<Item> NEAR_HARAD_HELMET = LOTRItems.ITEMS.register("near_harad_helmet",
            () -> new LOTRItemArmor(LOTRMaterial.NEAR_HARAD, EquipmentSlotType.HEAD));
    public static final RegistryObject<Item> NEAR_HARAD_CHESTPLATE = LOTRItems.ITEMS.register("near_harad_chestplate",
            () -> new LOTRItemArmor(LOTRMaterial.NEAR_HARAD, EquipmentSlotType.CHEST));
    public static final RegistryObject<Item> NEAR_HARAD_LEGGINGS = LOTRItems.ITEMS.register("near_harad_leggings",
            () -> new LOTRItemArmor(LOTRMaterial.NEAR_HARAD, EquipmentSlotType.LEGS));
    public static final RegistryObject<Item> NEAR_HARAD_BOOTS = LOTRItems.ITEMS.register("near_harad_boots",
            () -> new LOTRItemArmor(LOTRMaterial.NEAR_HARAD, EquipmentSlotType.FEET));
    public static final RegistryObject<Item> GEMSBOK_HELMET = LOTRItems.ITEMS.register("gemsbok_helmet",
            () -> new LOTRItemArmor(LOTRMaterial.GEMSBOK, EquipmentSlotType.HEAD));
    public static final RegistryObject<Item> GEMSBOK_CHESTPLATE = LOTRItems.ITEMS.register("gemsbok_chestplate",
            () -> new LOTRItemArmor(LOTRMaterial.GEMSBOK, EquipmentSlotType.CHEST));
    public static final RegistryObject<Item> GEMSBOK_LEGGINGS = LOTRItems.ITEMS.register("gemsbok_leggings",
            () -> new LOTRItemArmor(LOTRMaterial.GEMSBOK, EquipmentSlotType.LEGS));
    public static final RegistryObject<Item> GEMSBOK_BOOTS = LOTRItems.ITEMS.register("gemsbok_boots",
            () -> new LOTRItemArmor(LOTRMaterial.GEMSBOK, EquipmentSlotType.FEET));
    public static final RegistryObject<Item> HIGH_ELVEN_HELMET = LOTRItems.ITEMS.register("high_elven_helmet",
            () -> new LOTRItemArmor(LOTRMaterial.HIGH_ELVEN, EquipmentSlotType.HEAD));
    public static final RegistryObject<Item> HIGH_ELVEN_CHESTPLATE = LOTRItems.ITEMS.register("high_elven_chestplate",
            () -> new LOTRItemArmor(LOTRMaterial.HIGH_ELVEN, EquipmentSlotType.CHEST));
    public static final RegistryObject<Item> HIGH_ELVEN_LEGGINGS = LOTRItems.ITEMS.register("high_elven_leggings",
            () -> new LOTRItemArmor(LOTRMaterial.HIGH_ELVEN, EquipmentSlotType.LEGS));
    public static final RegistryObject<Item> HIGH_ELVEN_BOOTS = LOTRItems.ITEMS.register("high_elven_boots",
            () -> new LOTRItemArmor(LOTRMaterial.HIGH_ELVEN, EquipmentSlotType.FEET));
    public static final RegistryObject<Item> HIGH_ELVEN_SWORD = LOTRItems.ITEMS.register("high_elven_sword",
            () -> new LOTRItemSword(LOTRMaterial.HIGH_ELVEN, true));
    public static final RegistryObject<Item> HIGH_ELVEN_DAGGER = LOTRItems.ITEMS.register("high_elven_dagger",
            () -> new LOTRItemDagger(LOTRMaterial.HIGH_ELVEN));
    public static final RegistryObject<Item> HIGH_ELVEN_POISONED_DAGGER = LOTRItems.ITEMS.register("high_elven_poisoned_dagger",
            () -> new LOTRItemDagger(LOTRMaterial.HIGH_ELVEN, LOTRItemDagger.DaggerEffect.POISON));
    public static final RegistryObject<Item> NEAR_HARAD_DAGGER = LOTRItems.ITEMS.register("near_harad_dagger",
            () -> new LOTRItemDagger(LOTRMaterial.UMBAR));
    public static final RegistryObject<Item> NEAR_HARAD_POISONED_DAGGER = LOTRItems.ITEMS.register("near_harad_poisoned_dagger",
            () -> new LOTRItemDagger(LOTRMaterial.UMBAR, LOTRItemDagger.DaggerEffect.POISON));
    public static final RegistryObject<Item> BLUE_DWARVEN_SWORD = LOTRItems.ITEMS.register("blue_dwarven_sword",
            () -> new LOTRItemSword(LOTRMaterial.BLUE_DWARVEN));
    public static final RegistryObject<Item> BLUE_DWARVEN_DAGGER = LOTRItems.ITEMS.register("blue_dwarven_dagger",
            () -> new LOTRItemDagger(LOTRMaterial.BLUE_DWARVEN));
    public static final RegistryObject<Item> BLUE_DWARVEN_POISONED_DAGGER = LOTRItems.ITEMS.register("blue_dwarven_poisoned_dagger",
            () -> new LOTRItemDagger(LOTRMaterial.BLUE_DWARVEN, LOTRItemDagger.DaggerEffect.POISON));
    public static final RegistryObject<Item> BLUE_DWARVEN_BATTLEAXE = LOTRItems.ITEMS.register("blue_dwarven_battleaxe",
            () -> new LOTRItemBattleaxe(LOTRMaterial.BLUE_DWARVEN));
    public static final RegistryObject<Item> BLUE_DWARVEN_HAMMER = LOTRItems.ITEMS.register("blue_dwarven_hammer",
            () -> new LOTRItemHammer(LOTRMaterial.BLUE_DWARVEN));
    public static final RegistryObject<Item> BLUE_DWARVEN_HELMET = LOTRItems.ITEMS.register("blue_dwarven_helmet",
            () -> new LOTRItemArmor(LOTRMaterial.BLUE_DWARVEN, EquipmentSlotType.HEAD));
    public static final RegistryObject<Item> BLUE_DWARVEN_CHESTPLATE = LOTRItems.ITEMS.register("blue_dwarven_chestplate",
            () -> new LOTRItemArmor(LOTRMaterial.BLUE_DWARVEN, EquipmentSlotType.CHEST));
    public static final RegistryObject<Item> BLUE_DWARVEN_LEGGINGS = LOTRItems.ITEMS.register("blue_dwarven_leggings",
            () -> new LOTRItemArmor(LOTRMaterial.BLUE_DWARVEN, EquipmentSlotType.LEGS));
    public static final RegistryObject<Item> BLUE_DWARVEN_BOOTS = LOTRItems.ITEMS.register("blue_dwarven_boots",
            () -> new LOTRItemArmor(LOTRMaterial.BLUE_DWARVEN, EquipmentSlotType.FEET));
    public static final RegistryObject<Item> DOL_GULDUR_SWORD = LOTRItems.ITEMS.register("dol_guldur_sword",
            () -> new LOTRItemSword(LOTRMaterial.DOL_GULDUR));
    public static final RegistryObject<Item> DOL_GULDUR_DAGGER = LOTRItems.ITEMS.register("dol_guldur_dagger",
            () -> new LOTRItemDagger(LOTRMaterial.DOL_GULDUR));
    public static final RegistryObject<Item> DOL_GULDUR_POISONED_DAGGER = LOTRItems.ITEMS.register("dol_guldur_poisoned_dagger",
            () -> new LOTRItemDagger(LOTRMaterial.DOL_GULDUR, LOTRItemDagger.DaggerEffect.POISON));
    public static final RegistryObject<Item> DOL_GULDUR_BATTLEAXE = LOTRItems.ITEMS.register("dol_guldur_battleaxe",
            () -> new LOTRItemBattleaxe(LOTRMaterial.DOL_GULDUR));
    public static final RegistryObject<Item> DOL_GULDUR_HAMMER = LOTRItems.ITEMS.register("dol_guldur_hammer",
            () -> new LOTRItemHammer(LOTRMaterial.DOL_GULDUR));
    public static final RegistryObject<Item> DOL_GULDUR_HELMET = LOTRItems.ITEMS.register("dol_guldur_helmet",
            () -> new LOTRItemArmor(LOTRMaterial.DOL_GULDUR, EquipmentSlotType.HEAD));
    public static final RegistryObject<Item> DOL_GULDUR_CHESTPLATE = LOTRItems.ITEMS.register("dol_guldur_chestplate",
            () -> new LOTRItemArmor(LOTRMaterial.DOL_GULDUR, EquipmentSlotType.CHEST));
    public static final RegistryObject<Item> DOL_GULDUR_LEGGINGS = LOTRItems.ITEMS.register("dol_guldur_leggings",
            () -> new LOTRItemArmor(LOTRMaterial.DOL_GULDUR, EquipmentSlotType.LEGS));
    public static final RegistryObject<Item> DOL_GULDUR_BOOTS = LOTRItems.ITEMS.register("dol_guldur_boots",
            () -> new LOTRItemArmor(LOTRMaterial.DOL_GULDUR, EquipmentSlotType.FEET));
    public static final RegistryObject<Item> UTUMNO_HELMET = LOTRItems.ITEMS.register("utumno_helmet",
            () -> new LOTRItemArmor(LOTRMaterial.UTUMNO, EquipmentSlotType.HEAD));
    public static final RegistryObject<Item> UTUMNO_CHESTPLATE = LOTRItems.ITEMS.register("utumno_chestplate",
            () -> new LOTRItemArmor(LOTRMaterial.UTUMNO, EquipmentSlotType.CHEST));
    public static final RegistryObject<Item> UTUMNO_LEGGINGS = LOTRItems.ITEMS.register("utumno_leggings",
            () -> new LOTRItemArmor(LOTRMaterial.UTUMNO, EquipmentSlotType.LEGS));
    public static final RegistryObject<Item> UTUMNO_BOOTS = LOTRItems.ITEMS.register("utumno_boots",
            () -> new LOTRItemArmor(LOTRMaterial.UTUMNO, EquipmentSlotType.FEET));
    public static final RegistryObject<Item> BLACK_URUK_SCIMITAR = LOTRItems.ITEMS.register("black_uruk_scimitar",
            () -> new LOTRItemSword(LOTRMaterial.BLACK_URUK));
    public static final RegistryObject<Item> BLACK_URUK_DAGGER = LOTRItems.ITEMS.register("black_uruk_dagger",
            () -> new LOTRItemDagger(LOTRMaterial.BLACK_URUK));
    public static final RegistryObject<Item> BLACK_URUK_POISONED_DAGGER = LOTRItems.ITEMS.register("black_uruk_poisoned_dagger",
            () -> new LOTRItemDagger(LOTRMaterial.BLACK_URUK, LOTRItemDagger.DaggerEffect.POISON));
    public static final RegistryObject<Item> BLACK_URUK_BATTLEAXE = LOTRItems.ITEMS.register("black_uruk_battleaxe",
            () -> new LOTRItemBattleaxe(LOTRMaterial.BLACK_URUK));
    public static final RegistryObject<Item> BLACK_URUK_HAMMER = LOTRItems.ITEMS.register("black_uruk_hammer",
            () -> new LOTRItemHammer(LOTRMaterial.BLACK_URUK));
    public static final RegistryObject<Item> BLACK_URUK_HELMET = LOTRItems.ITEMS.register("black_uruk_helmet",
            () -> new LOTRItemArmor(LOTRMaterial.BLACK_URUK, EquipmentSlotType.HEAD));
    public static final RegistryObject<Item> BLACK_URUK_CHESTPLATE = LOTRItems.ITEMS.register("black_uruk_chestplate",
            () -> new LOTRItemArmor(LOTRMaterial.BLACK_URUK, EquipmentSlotType.CHEST));
    public static final RegistryObject<Item> BLACK_URUK_LEGGINGS = LOTRItems.ITEMS.register("black_uruk_leggings",
            () -> new LOTRItemArmor(LOTRMaterial.BLACK_URUK, EquipmentSlotType.LEGS));
    public static final RegistryObject<Item> BLACK_URUK_BOOTS = LOTRItems.ITEMS.register("black_uruk_boots",
            () -> new LOTRItemArmor(LOTRMaterial.BLACK_URUK, EquipmentSlotType.FEET));
    public static final RegistryObject<Item> NEAR_HARAD_WARLORD_HELMET = LOTRItems.ITEMS.register("near_harad_warlord_helmet",
            () -> new LOTRItemArmor(LOTRMaterial.NEAR_HARAD, EquipmentSlotType.HEAD, "warlordHelmet"));
    public static final RegistryObject<Item> UTUMNO_SWORD = LOTRItems.ITEMS.register("utumno_sword",
            () -> new LOTRItemSword(LOTRMaterial.UTUMNO));
    public static final RegistryObject<Item> UTUMNO_DAGGER = LOTRItems.ITEMS.register("utumno_dagger",
            () -> new LOTRItemDagger(LOTRMaterial.UTUMNO));
    public static final RegistryObject<Item> UTUMNO_POISONED_DAGGER = LOTRItems.ITEMS.register("utumno_poisoned_dagger",
            () -> new LOTRItemDagger(LOTRMaterial.UTUMNO, LOTRItemDagger.DaggerEffect.POISON));
    public static final RegistryObject<Item> UTUMNO_BATTLEAXE = LOTRItems.ITEMS.register("utumno_battleaxe",
            () -> new LOTRItemBattleaxe(LOTRMaterial.UTUMNO));
    public static final RegistryObject<Item> UTUMNO_HAMMER = LOTRItems.ITEMS.register("utumno_hammer",
            () -> new LOTRItemHammer(LOTRMaterial.UTUMNO));
    public static final RegistryObject<Item> BRONZE_BATTLEAXE = LOTRItems.ITEMS.register("bronze_battleaxe",
            () -> new LOTRItemBattleaxe(LOTRMaterial.BRONZE));
    public static final RegistryObject<Item> HALF_TROLL_HELMET = LOTRItems.ITEMS.register("half_troll_helmet",
            () -> new LOTRItemArmor(LOTRMaterial.HALF_TROLL, EquipmentSlotType.HEAD));
    public static final RegistryObject<Item> HALF_TROLL_CHESTPLATE = LOTRItems.ITEMS.register("half_troll_chestplate",
            () -> new LOTRItemArmor(LOTRMaterial.HALF_TROLL, EquipmentSlotType.CHEST));
    public static final RegistryObject<Item> HALF_TROLL_LEGGINGS = LOTRItems.ITEMS.register("half_troll_leggings",
            () -> new LOTRItemArmor(LOTRMaterial.HALF_TROLL, EquipmentSlotType.LEGS));
    public static final RegistryObject<Item> HALF_TROLL_BOOTS = LOTRItems.ITEMS.register("half_troll_boots",
            () -> new LOTRItemArmor(LOTRMaterial.HALF_TROLL, EquipmentSlotType.FEET));
    public static final RegistryObject<Item> HALF_TROLL_BATTLEAXE = LOTRItems.ITEMS.register("half_troll_battleaxe",
            () -> new LOTRItemBattleaxe(LOTRMaterial.HALF_TROLL));
    public static final RegistryObject<Item> HALF_TROLL_HAMMER = LOTRItems.ITEMS.register("half_troll_hammer",
            () -> new LOTRItemHammer(LOTRMaterial.HALF_TROLL));
    public static final RegistryObject<Item> HALF_TROLL_MACE = LOTRItems.ITEMS.register("half_troll_mace",
            () -> new LOTRItemHammer(LOTRMaterial.HALF_TROLL));
    public static final RegistryObject<Item> HALF_TROLL_SCIMITAR = LOTRItems.ITEMS.register("half_troll_scimitar",
            () -> new LOTRItemSword(LOTRMaterial.HALF_TROLL));
    public static final RegistryObject<Item> HALF_TROLL_DAGGER = LOTRItems.ITEMS.register("half_troll_dagger",
            () -> new LOTRItemDagger(LOTRMaterial.HALF_TROLL));
    public static final RegistryObject<Item> HALF_TROLL_POISONED_DAGGER = LOTRItems.ITEMS.register("half_troll_poisoned_dagger",
            () -> new LOTRItemDagger(LOTRMaterial.HALF_TROLL, LOTRItemDagger.DaggerEffect.POISON));
    public static final RegistryObject<Item> DWARVEN_SILVER_HELMET = LOTRItems.ITEMS.register("dwarven_silver_helmet",
            () -> new LOTRItemArmor(LOTRMaterial.DWARVEN, EquipmentSlotType.HEAD, "silver_1"));
    public static final RegistryObject<Item> DWARVEN_SILVER_CHESTPLATE = LOTRItems.ITEMS.register("dwarven_silver_chestplate",
            () -> new LOTRItemArmor(LOTRMaterial.DWARVEN, EquipmentSlotType.CHEST, "silver_1"));
    public static final RegistryObject<Item> DWARVEN_SILVER_LEGGINGS = LOTRItems.ITEMS.register("dwarven_silver_leggings",
            () -> new LOTRItemArmor(LOTRMaterial.DWARVEN, EquipmentSlotType.LEGS, "silver_2"));
    public static final RegistryObject<Item> DWARVEN_SILVER_BOOTS = LOTRItems.ITEMS.register("dwarven_silver_boots",
            () -> new LOTRItemArmor(LOTRMaterial.DWARVEN, EquipmentSlotType.FEET, "silver_1"));
    public static final RegistryObject<Item> DWARVEN_GOLD_HELMET = LOTRItems.ITEMS.register("dwarven_gold_helmet",
            () -> new LOTRItemArmor(LOTRMaterial.DWARVEN, EquipmentSlotType.HEAD, "gold_1"));
    public static final RegistryObject<Item> DWARVEN_GOLD_CHESTPLATE = LOTRItems.ITEMS.register("dwarven_gold_chestplate",
            () -> new LOTRItemArmor(LOTRMaterial.DWARVEN, EquipmentSlotType.CHEST, "gold_1"));
    public static final RegistryObject<Item> DWARVEN_GOLD_LEGGINGS = LOTRItems.ITEMS.register("dwarven_gold_leggings",
            () -> new LOTRItemArmor(LOTRMaterial.DWARVEN, EquipmentSlotType.LEGS, "gold_2"));
    public static final RegistryObject<Item> DWARVEN_GOLD_BOOTS = LOTRItems.ITEMS.register("dwarven_gold_boots",
            () -> new LOTRItemArmor(LOTRMaterial.DWARVEN, EquipmentSlotType.FEET, "gold_1"));
    public static final RegistryObject<Item> DWARVEN_MITHRIL_HELMET = LOTRItems.ITEMS.register("dwarven_mithril_helmet",
            () -> new LOTRItemArmor(LOTRMaterial.DWARVEN, EquipmentSlotType.HEAD, "mithril_1"));
    public static final RegistryObject<Item> DWARVEN_MITHRIL_CHESTPLATE = LOTRItems.ITEMS.register("dwarven_mithril_chestplate",
            () -> new LOTRItemArmor(LOTRMaterial.DWARVEN, EquipmentSlotType.CHEST, "mithril_1"));
    public static final RegistryObject<Item> DWARVEN_MITHRIL_LEGGINGS = LOTRItems.ITEMS.register("dwarven_mithril_leggings",
            () -> new LOTRItemArmor(LOTRMaterial.DWARVEN, EquipmentSlotType.LEGS, "mithril_2"));
    public static final RegistryObject<Item> DWARVEN_MITHRIL_BOOTS = LOTRItems.ITEMS.register("dwarven_mithril_boots",
            () -> new LOTRItemArmor(LOTRMaterial.DWARVEN, EquipmentSlotType.FEET, "mithril_1"));
    public static final RegistryObject<Item> DOL_AMROTH_SWORD = LOTRItems.ITEMS.register("dol_amroth_sword",
            () -> new LOTRItemSword(LOTRMaterial.DOL_AMROTH));
    public static final RegistryObject<Item> DOL_AMROTH_HELMET = LOTRItems.ITEMS.register("dol_amroth_helmet",
            () -> new LOTRItemArmor(LOTRMaterial.DOL_AMROTH, EquipmentSlotType.HEAD, "wingedHelmet"));
    public static final RegistryObject<Item> DOL_AMROTH_CHESTPLATE = LOTRItems.ITEMS.register("dol_amroth_chestplate",
            () -> new LOTRItemArmor(LOTRMaterial.DOL_AMROTH, EquipmentSlotType.CHEST, "wingedBody"));
    public static final RegistryObject<Item> DOL_AMROTH_LEGGINGS = LOTRItems.ITEMS.register("dol_amroth_leggings",
            () -> new LOTRItemArmor(LOTRMaterial.DOL_AMROTH, EquipmentSlotType.LEGS));
    public static final RegistryObject<Item> DOL_AMROTH_BOOTS = LOTRItems.ITEMS.register("dol_amroth_boots",
            () -> new LOTRItemArmor(LOTRMaterial.DOL_AMROTH, EquipmentSlotType.FEET));
    public static final RegistryObject<Item> MOREDAIN_DAGGER = LOTRItems.ITEMS.register("moredain_dagger",
            () -> new LOTRItemDagger(LOTRMaterial.MOREDAIN));
    public static final RegistryObject<Item> MOREDAIN_POISONED_DAGGER = LOTRItems.ITEMS.register("moredain_poisoned_dagger",
            () -> new LOTRItemDagger(LOTRMaterial.MOREDAIN, LOTRItemDagger.DaggerEffect.POISON));
    public static final RegistryObject<Item> MOREDAIN_BATTLEAXE = LOTRItems.ITEMS.register("moredain_battleaxe",
            () -> new LOTRItemBattleaxe(LOTRMaterial.MOREDAIN));
    public static final RegistryObject<Item> MOREDAIN_HELMET = LOTRItems.ITEMS.register("moredain_helmet",
            () -> new LOTRItemArmor(LOTRMaterial.MOREDAIN, EquipmentSlotType.HEAD));
    public static final RegistryObject<Item> MOREDAIN_CHESTPLATE = LOTRItems.ITEMS.register("moredain_chestplate",
            () -> new LOTRItemArmor(LOTRMaterial.MOREDAIN, EquipmentSlotType.CHEST));
    public static final RegistryObject<Item> MOREDAIN_LEGGINGS = LOTRItems.ITEMS.register("moredain_leggings",
            () -> new LOTRItemArmor(LOTRMaterial.MOREDAIN, EquipmentSlotType.LEGS));
    public static final RegistryObject<Item> MOREDAIN_BOOTS = LOTRItems.ITEMS.register("moredain_boots",
            () -> new LOTRItemArmor(LOTRMaterial.MOREDAIN, EquipmentSlotType.FEET));
    public static final RegistryObject<Item> MOREDAIN_LION_HELMET = LOTRItems.ITEMS.register("moredain_lion_helmet",
            () -> new LOTRItemArmor(LOTRMaterial.MOREDAIN_LION_ARMOR, EquipmentSlotType.HEAD, "helmet"));
    public static final RegistryObject<Item> MOREDAIN_LION_CHESTPLATE = LOTRItems.ITEMS.register("moredain_lion_chestplate",
            () -> new LOTRItemArmor(LOTRMaterial.MOREDAIN_LION_ARMOR, EquipmentSlotType.CHEST));
    public static final RegistryObject<Item> MOREDAIN_LION_LEGGINGS = LOTRItems.ITEMS.register("moredain_lion_leggings",
            () -> new LOTRItemArmor(LOTRMaterial.MOREDAIN_LION_ARMOR, EquipmentSlotType.LEGS));
    public static final RegistryObject<Item> MOREDAIN_LION_BOOTS = LOTRItems.ITEMS.register("moredain_lion_boots",
            () -> new LOTRItemArmor(LOTRMaterial.MOREDAIN_LION_ARMOR, EquipmentSlotType.FEET));
    public static final RegistryObject<Item> BONE_HELMET = LOTRItems.ITEMS.register("bone_helmet",
            () -> new LOTRItemArmor(LOTRMaterial.BONE, EquipmentSlotType.HEAD));
    public static final RegistryObject<Item> BONE_CHESTPLATE = LOTRItems.ITEMS.register("bone_chestplate",
            () -> new LOTRItemArmor(LOTRMaterial.BONE, EquipmentSlotType.CHEST));
    public static final RegistryObject<Item> BONE_LEGGINGS = LOTRItems.ITEMS.register("bone_leggings",
            () -> new LOTRItemArmor(LOTRMaterial.BONE, EquipmentSlotType.LEGS));
    public static final RegistryObject<Item> BONE_BOOTS = LOTRItems.ITEMS.register("bone_boots",
            () -> new LOTRItemArmor(LOTRMaterial.BONE, EquipmentSlotType.FEET));
    public static final RegistryObject<Item> GONDOLIN_SWORD = LOTRItems.ITEMS.register("gondolin_sword",
            () -> new LOTRItemSword(LOTRMaterial.GONDOLIN, true));
    public static final RegistryObject<Item> MALLORN_CHARRED_MACE = LOTRItems.ITEMS.register("mallorn_charred_mace",
            () -> new LOTRItemHammer(LOTRMaterial.MALLORN_MACE));
    public static final RegistryObject<Item> GONDOLIN_HELMET = LOTRItems.ITEMS.register("gondolin_helmet",
            () -> new LOTRItemArmor(LOTRMaterial.GONDOLIN, EquipmentSlotType.HEAD, "helmet"));
    public static final RegistryObject<Item> GONDOLIN_CHESTPLATE = LOTRItems.ITEMS.register("gondolin_chestplate",
            () -> new LOTRItemArmor(LOTRMaterial.GONDOLIN, EquipmentSlotType.CHEST));
    public static final RegistryObject<Item> GONDOLIN_LEGGINGS = LOTRItems.ITEMS.register("gondolin_leggings",
            () -> new LOTRItemArmor(LOTRMaterial.GONDOLIN, EquipmentSlotType.LEGS));
    public static final RegistryObject<Item> GONDOLIN_BOOTS = LOTRItems.ITEMS.register("gondolin_boots",
            () -> new LOTRItemArmor(LOTRMaterial.GONDOLIN, EquipmentSlotType.FEET));
    public static final RegistryObject<Item> ROHAN_MARSHAL_HELMET = LOTRItems.ITEMS.register("rohan_marshal_helmet",
            () -> new LOTRItemArmor(LOTRMaterial.ROHAN_MARSHAL, EquipmentSlotType.HEAD, "helmet"));
    public static final RegistryObject<Item> ROHAN_MARSHAL_CHESTPLATE = LOTRItems.ITEMS.register("rohan_marshal_chestplate",
            () -> new LOTRItemArmor(LOTRMaterial.ROHAN_MARSHAL, EquipmentSlotType.CHEST));
    public static final RegistryObject<Item> ROHAN_MARSHAL_LEGGINGS = LOTRItems.ITEMS.register("rohan_marshal_leggings",
            () -> new LOTRItemArmor(LOTRMaterial.ROHAN_MARSHAL, EquipmentSlotType.LEGS));
    public static final RegistryObject<Item> ROHAN_MARSHAL_BOOTS = LOTRItems.ITEMS.register("rohan_marshal_boots",
            () -> new LOTRItemArmor(LOTRMaterial.ROHAN_MARSHAL, EquipmentSlotType.FEET));
    public static final RegistryObject<Item> TAUREDAIN_DAGGER = LOTRItems.ITEMS.register("tauredain_dagger",
            () -> new LOTRItemDagger(LOTRMaterial.TAUREDAIN));
    public static final RegistryObject<Item> TAUREDAIN_POISONED_DAGGER = LOTRItems.ITEMS.register("tauredain_poisoned_dagger",
            () -> new LOTRItemDagger(LOTRMaterial.TAUREDAIN, LOTRItemDagger.DaggerEffect.POISON));
    public static final RegistryObject<Item> TAUREDAIN_SWORD = LOTRItems.ITEMS.register("tauredain_sword",
            () -> new LOTRItemSword(LOTRMaterial.TAUREDAIN));
    public static final RegistryObject<Item> TAUREDAIN_HELMET = LOTRItems.ITEMS.register("tauredain_helmet",
            () -> new LOTRItemArmor(LOTRMaterial.TAUREDAIN, EquipmentSlotType.HEAD));
    public static final RegistryObject<Item> TAUREDAIN_CHESTPLATE = LOTRItems.ITEMS.register("tauredain_chestplate",
            () -> new LOTRItemArmor(LOTRMaterial.TAUREDAIN, EquipmentSlotType.CHEST));
    public static final RegistryObject<Item> TAUREDAIN_LEGGINGS = LOTRItems.ITEMS.register("tauredain_leggings",
            () -> new LOTRItemArmor(LOTRMaterial.TAUREDAIN, EquipmentSlotType.LEGS));
    public static final RegistryObject<Item> TAUREDAIN_BOOTS = LOTRItems.ITEMS.register("tauredain_boots",
            () -> new LOTRItemArmor(LOTRMaterial.TAUREDAIN, EquipmentSlotType.FEET));
    public static final RegistryObject<Item> TAUREDAIN_CHIEFTAIN_HELMET = LOTRItems.ITEMS.register("tauredain_chieftain_helmet",
            () -> new LOTRItemArmor(LOTRMaterial.TAUREDAIN, EquipmentSlotType.HEAD, "chieftainHelmet"));
    public static final RegistryObject<Item> NEAR_HARAD_POLEAXE = LOTRItems.ITEMS.register("near_harad_poleaxe",
            () -> new LOTRItemPolearm(LOTRMaterial.UMBAR));
    public static final RegistryObject<Item> URUK_PIKE = LOTRItems.ITEMS.register("uruk_pike",
            () -> new LOTRItemPike(LOTRMaterial.URUK));
    public static final RegistryObject<Item> ORC_POLEARM = LOTRItems.ITEMS.register("orc_polearm",
            () -> new LOTRItemPolearm(LOTRMaterial.MORDOR));
    public static final RegistryObject<Item> DOL_AMROTH_LANCE = LOTRItems.ITEMS.register("dol_amroth_lance",
            () -> new LOTRItemLance(LOTRMaterial.DOL_AMROTH));
    public static final RegistryObject<Item> HIGH_ELVEN_POLEARM = LOTRItems.ITEMS.register("high_elven_polearm",
            () -> new LOTRItemPolearm(LOTRMaterial.HIGH_ELVEN));
    public static final RegistryObject<Item> ELVEN_POLEARM = LOTRItems.ITEMS.register("elven_polearm",
            () -> new LOTRItemPolearm(LOTRMaterial.GALADHRIM));
    public static final RegistryObject<Item> WOOD_ELVEN_POLEARM = LOTRItems.ITEMS.register("wood_elven_polearm",
            () -> new LOTRItemPolearm(LOTRMaterial.WOOD_ELVEN));
    public static final RegistryObject<Item> BARROW_DAGGER = LOTRItems.ITEMS.register("barrow_dagger",
            () -> new LOTRItemDagger(LOTRMaterial.BARROW));
    public static final RegistryObject<Item> BARROW_POISONED_DAGGER = LOTRItems.ITEMS.register("barrow_poisoned_dagger",
            () -> new LOTRItemDagger(LOTRMaterial.BARROW, LOTRItemDagger.DaggerEffect.POISON));
    public static final RegistryObject<Item> NEAR_HARAD_MACE = LOTRItems.ITEMS.register("near_harad_mace",
            () -> new LOTRItemHammer(LOTRMaterial.UMBAR));
    public static final RegistryObject<Item> HITHLAIN_HELMET = LOTRItems.ITEMS.register("hithlain_helmet",
            () -> new LOTRItemArmor(LOTRMaterial.HITHLAIN, EquipmentSlotType.HEAD));
    public static final RegistryObject<Item> HITHLAIN_CHESTPLATE = LOTRItems.ITEMS.register("hithlain_chestplate",
            () -> new LOTRItemArmor(LOTRMaterial.HITHLAIN, EquipmentSlotType.CHEST));
    public static final RegistryObject<Item> HITHLAIN_LEGGINGS = LOTRItems.ITEMS.register("hithlain_leggings",
            () -> new LOTRItemArmor(LOTRMaterial.HITHLAIN, EquipmentSlotType.LEGS));
    public static final RegistryObject<Item> HITHLAIN_BOOTS = LOTRItems.ITEMS.register("hithlain_boots",
            () -> new LOTRItemArmor(LOTRMaterial.HITHLAIN, EquipmentSlotType.FEET));
    public static final RegistryObject<Item> HALF_TROLL_PIKE = LOTRItems.ITEMS.register("half_troll_pike",
            () -> new LOTRItemPike(LOTRMaterial.HALF_TROLL));
    public static final RegistryObject<Item> TAUREDAIN_GOLD_HELMET = LOTRItems.ITEMS.register("tauredain_gold_helmet",
            () -> new LOTRItemArmor(LOTRMaterial.TAUREDAIN_GOLD, EquipmentSlotType.HEAD, "helmet"));
    public static final RegistryObject<Item> TAUREDAIN_GOLD_CHESTPLATE = LOTRItems.ITEMS.register("tauredain_gold_chestplate",
            () -> new LOTRItemArmor(LOTRMaterial.TAUREDAIN_GOLD, EquipmentSlotType.CHEST));
    public static final RegistryObject<Item> TAUREDAIN_GOLD_LEGGINGS = LOTRItems.ITEMS.register("tauredain_gold_leggings",
            () -> new LOTRItemArmor(LOTRMaterial.TAUREDAIN_GOLD, EquipmentSlotType.LEGS));
    public static final RegistryObject<Item> TAUREDAIN_GOLD_BOOTS = LOTRItems.ITEMS.register("tauredain_gold_boots",
            () -> new LOTRItemArmor(LOTRMaterial.TAUREDAIN_GOLD, EquipmentSlotType.FEET));
    public static final RegistryObject<Item> DWARVEN_PIKE = LOTRItems.ITEMS.register("dwarven_pike",
            () -> new LOTRItemPike(LOTRMaterial.DWARVEN));
    public static final RegistryObject<Item> BLUE_DWARVEN_PIKE = LOTRItems.ITEMS.register("blue_dwarven_pike",
            () -> new LOTRItemPike(LOTRMaterial.BLUE_DWARVEN));
    public static final RegistryObject<Item> DOL_AMROTH_DAGGER = LOTRItems.ITEMS.register("dol_amroth_dagger",
            () -> new LOTRItemDagger(LOTRMaterial.DOL_AMROTH));
    public static final RegistryObject<Item> DOL_AMROTH_POISONED_DAGGER = LOTRItems.ITEMS.register("dol_amroth_poisoned_dagger",
            () -> new LOTRItemDagger(LOTRMaterial.DOL_AMROTH, LOTRItemDagger.DaggerEffect.POISON));
    public static final RegistryObject<Item> GUNDABAD_URUK_HELMET = LOTRItems.ITEMS.register("gundabad_uruk_helmet",
            () -> new LOTRItemArmor(LOTRMaterial.GUNDABAD_URUK, EquipmentSlotType.HEAD, "helmet"));
    public static final RegistryObject<Item> GUNDABAD_URUK_CHESTPLATE = LOTRItems.ITEMS.register("gundabad_uruk_chestplate",
            () -> new LOTRItemArmor(LOTRMaterial.GUNDABAD_URUK, EquipmentSlotType.CHEST));
    public static final RegistryObject<Item> GUNDABAD_URUK_LEGGINGS = LOTRItems.ITEMS.register("gundabad_uruk_leggings",
            () -> new LOTRItemArmor(LOTRMaterial.GUNDABAD_URUK, EquipmentSlotType.LEGS));
    public static final RegistryObject<Item> GUNDABAD_URUK_BOOTS = LOTRItems.ITEMS.register("gundabad_uruk_boots",
            () -> new LOTRItemArmor(LOTRMaterial.GUNDABAD_URUK, EquipmentSlotType.FEET));
    public static final RegistryObject<Item> GONDOR_LANCE = LOTRItems.ITEMS.register("gondor_lance",
            () -> new LOTRItemLance(LOTRMaterial.GONDOR));
    public static final RegistryObject<Item> GUNDABAD_URUK_SWORD = LOTRItems.ITEMS.register("gundabad_uruk_sword",
            () -> new LOTRItemSword(LOTRMaterial.GUNDABAD_URUK));
    public static final RegistryObject<Item> GUNDABAD_URUK_BATTLEAXE = LOTRItems.ITEMS.register("gundabad_uruk_battleaxe",
            () -> new LOTRItemBattleaxe(LOTRMaterial.GUNDABAD_URUK));
    public static final RegistryObject<Item> GUNDABAD_URUK_HAMMER = LOTRItems.ITEMS.register("gundabad_uruk_hammer",
            () -> new LOTRItemHammer(LOTRMaterial.GUNDABAD_URUK));
    public static final RegistryObject<Item> URUK_BERSERKER_HELMET = LOTRItems.ITEMS.register("uruk_berserker_helmet",
            () -> new LOTRItemArmor(LOTRMaterial.URUK, EquipmentSlotType.HEAD, "helmet_berserker"));
    public static final RegistryObject<Item> URUK_BERSERKER_SCIMITAR = LOTRItems.ITEMS.register("uruk_berserker_scimitar",
            () -> new LOTRItemSword(LOTRMaterial.URUK));
    public static final RegistryObject<Item> ROHAN_LANCE = LOTRItems.ITEMS.register("rohan_lance",
            () -> new LOTRItemLance(LOTRMaterial.ROHAN));
    public static final RegistryObject<Item> ELVEN_LONGSPEAR = LOTRItems.ITEMS.register("elven_longspear",
            () -> new LOTRItemPolearmLong(LOTRMaterial.GALADHRIM));
    public static final RegistryObject<Item> HIGH_ELVEN_LONGSPEAR = LOTRItems.ITEMS.register("high_elven_longspear",
            () -> new LOTRItemPolearmLong(LOTRMaterial.HIGH_ELVEN));
    public static final RegistryObject<Item> WOOD_ELVEN_LONGSPEAR = LOTRItems.ITEMS.register("wood_elven_longspear",
            () -> new LOTRItemPolearmLong(LOTRMaterial.WOOD_ELVEN));
    public static final RegistryObject<Item> MITHRIL_HALBERD = LOTRItems.ITEMS.register("mithril_halberd",
            () -> new LOTRItemPolearmLong(LOTRMaterial.MITHRIL));
    public static final RegistryObject<Item> DALE_SWORD = LOTRItems.ITEMS.register("dale_sword",
            () -> new LOTRItemSword(LOTRMaterial.DALE));
    public static final RegistryObject<Item> DALE_DAGGER = LOTRItems.ITEMS.register("dale_dagger",
            () -> new LOTRItemDagger(LOTRMaterial.DALE));
    public static final RegistryObject<Item> DALE_POISONED_DAGGER = LOTRItems.ITEMS.register("dale_poisoned_dagger",
            () -> new LOTRItemDagger(LOTRMaterial.DALE, LOTRItemDagger.DaggerEffect.POISON));
    public static final RegistryObject<Item> DALE_BATTLEAXE = LOTRItems.ITEMS.register("dale_battleaxe",
            () -> new LOTRItemBattleaxe(LOTRMaterial.DALE));
    public static final RegistryObject<Item> DALE_HELMET = LOTRItems.ITEMS.register("dale_helmet",
            () -> new LOTRItemArmor(LOTRMaterial.DALE, EquipmentSlotType.HEAD));
    public static final RegistryObject<Item> DALE_CHESTPLATE = LOTRItems.ITEMS.register("dale_chestplate",
            () -> new LOTRItemArmor(LOTRMaterial.DALE, EquipmentSlotType.CHEST));
    public static final RegistryObject<Item> DALE_LEGGINGS = LOTRItems.ITEMS.register("dale_leggings",
            () -> new LOTRItemArmor(LOTRMaterial.DALE, EquipmentSlotType.LEGS));
    public static final RegistryObject<Item> DALE_BOOTS = LOTRItems.ITEMS.register("dale_boots",
            () -> new LOTRItemArmor(LOTRMaterial.DALE, EquipmentSlotType.FEET));
    public static final RegistryObject<Item> DORWINION_HELMET = LOTRItems.ITEMS.register("dorwinion_helmet",
            () -> new LOTRItemArmor(LOTRMaterial.DORWINION, EquipmentSlotType.HEAD));
    public static final RegistryObject<Item> DORWINION_CHESTPLATE = LOTRItems.ITEMS.register("dorwinion_chestplate",
            () -> new LOTRItemArmor(LOTRMaterial.DORWINION, EquipmentSlotType.CHEST));
    public static final RegistryObject<Item> DORWINION_LEGGINGS = LOTRItems.ITEMS.register("dorwinion_leggings",
            () -> new LOTRItemArmor(LOTRMaterial.DORWINION, EquipmentSlotType.LEGS));
    public static final RegistryObject<Item> DORWINION_BOOTS = LOTRItems.ITEMS.register("dorwinion_boots",
            () -> new LOTRItemArmor(LOTRMaterial.DORWINION, EquipmentSlotType.FEET));
    public static final RegistryObject<Item> DORWINION_ELF_HELMET = LOTRItems.ITEMS.register("dorwinion_elf_helmet",
            () -> new LOTRItemArmor(LOTRMaterial.DORWINION_ELF, EquipmentSlotType.HEAD, "helmet"));
    public static final RegistryObject<Item> DORWINION_ELF_CHESTPLATE = LOTRItems.ITEMS.register("dorwinion_elf_chestplate",
            () -> new LOTRItemArmor(LOTRMaterial.DORWINION_ELF, EquipmentSlotType.CHEST));
    public static final RegistryObject<Item> DORWINION_ELF_LEGGINGS = LOTRItems.ITEMS.register("dorwinion_elf_leggings",
            () -> new LOTRItemArmor(LOTRMaterial.DORWINION_ELF, EquipmentSlotType.LEGS));
    public static final RegistryObject<Item> DORWINION_ELF_BOOTS = LOTRItems.ITEMS.register("dorwinion_elf_boots",
            () -> new LOTRItemArmor(LOTRMaterial.DORWINION_ELF, EquipmentSlotType.FEET));
    public static final RegistryObject<Item> RANGER_ITHILIEN_HELMET = LOTRItems.ITEMS.register("ranger_ithilien_helmet",
            () -> new LOTRItemArmor(LOTRMaterial.RANGER_ITHILIEN, EquipmentSlotType.HEAD));
    public static final RegistryObject<Item> RANGER_ITHILIEN_CHESTPLATE = LOTRItems.ITEMS.register("ranger_ithilien_chestplate",
            () -> new LOTRItemArmor(LOTRMaterial.RANGER_ITHILIEN, EquipmentSlotType.CHEST));
    public static final RegistryObject<Item> RANGER_ITHILIEN_LEGGINGS = LOTRItems.ITEMS.register("ranger_ithilien_leggings",
            () -> new LOTRItemArmor(LOTRMaterial.RANGER_ITHILIEN, EquipmentSlotType.LEGS));
    public static final RegistryObject<Item> RANGER_ITHILIEN_BOOTS = LOTRItems.ITEMS.register("ranger_ithilien_boots",
            () -> new LOTRItemArmor(LOTRMaterial.RANGER_ITHILIEN, EquipmentSlotType.FEET));
    public static final RegistryObject<Item> GUNDABAD_URUK_DAGGER = LOTRItems.ITEMS.register("gundabad_uruk_dagger",
            () -> new LOTRItemDagger(LOTRMaterial.GUNDABAD_URUK));
    public static final RegistryObject<Item> GUNDABAD_URUK_POISONED_DAGGER = LOTRItems.ITEMS.register("gundabad_uruk_poisoned_dagger",
            () -> new LOTRItemDagger(LOTRMaterial.GUNDABAD_URUK, LOTRItemDagger.DaggerEffect.POISON));
    public static final RegistryObject<Item> GUNDABAD_URUK_PIKE = LOTRItems.ITEMS.register("gundabad_uruk_pike",
            () -> new LOTRItemPike(LOTRMaterial.GUNDABAD_URUK));
    public static final RegistryObject<Item> TAUREDAIN_HAMMER = LOTRItems.ITEMS.register("tauredain_hammer",
            () -> new LOTRItemHammer(LOTRMaterial.TAUREDAIN));
    public static final RegistryObject<Item> DORWINION_ELF_SWORD = LOTRItems.ITEMS.register("dorwinion_elf_sword",
            () -> new LOTRItemSword(LOTRMaterial.DORWINION_ELF, true));
    public static final RegistryObject<Item> DORWINION_ELF_DAGGER = LOTRItems.ITEMS.register("dorwinion_elf_dagger",
            () -> new LOTRItemDagger(LOTRMaterial.DORWINION_ELF));
    public static final RegistryObject<Item> DORWINION_ELF_POISONED_DAGGER = LOTRItems.ITEMS.register("dorwinion_elf_poisoned_dagger",
            () -> new LOTRItemDagger(LOTRMaterial.DORWINION_ELF, LOTRItemDagger.DaggerEffect.POISON));
    public static final RegistryObject<Item> TAUREDAIN_BATTLEAXE = LOTRItems.ITEMS.register("tauredain_battleaxe",
            () -> new LOTRItemBattleaxe(LOTRMaterial.TAUREDAIN));
    public static final RegistryObject<Item> TAUREDAIN_PIKE = LOTRItems.ITEMS.register("tauredain_pike",
            () -> new LOTRItemPike(LOTRMaterial.TAUREDAIN));
    public static final RegistryObject<Item> MOREDAIN_CLUB = LOTRItems.ITEMS.register("moredain_club",
            () -> new LOTRItemHammer(LOTRMaterial.MOREDAIN_WOOD));
    public static final RegistryObject<Item> DALE_PIKE = LOTRItems.ITEMS.register("dale_pike",
            () -> new LOTRItemPike(LOTRMaterial.DALE));
    public static final RegistryObject<Item> ANGMAR_POLEARM = LOTRItems.ITEMS.register("angmar_polearm",
            () -> new LOTRItemPolearm(LOTRMaterial.ANGMAR));
    public static final RegistryObject<Item> DOL_GULDUR_PIKE = LOTRItems.ITEMS.register("dol_guldur_pike",
            () -> new LOTRItemPike(LOTRMaterial.DOL_GULDUR));
    public static final RegistryObject<Item> NEAR_HARAD_PIKE = LOTRItems.ITEMS.register("near_harad_pike",
            () -> new LOTRItemPike(LOTRMaterial.UMBAR));
    public static final RegistryObject<Item> LOSSARNACH_HELMET = LOTRItems.ITEMS.register("lossarnach_helmet",
            () -> new LOTRItemArmor(LOTRMaterial.LOSSARNACH, EquipmentSlotType.HEAD));
    public static final RegistryObject<Item> LOSSARNACH_CHESTPLATE = LOTRItems.ITEMS.register("lossarnach_chestplate",
            () -> new LOTRItemArmor(LOTRMaterial.LOSSARNACH, EquipmentSlotType.CHEST));
    public static final RegistryObject<Item> LOSSARNACH_LEGGINGS = LOTRItems.ITEMS.register("lossarnach_leggings",
            () -> new LOTRItemArmor(LOTRMaterial.LOSSARNACH, EquipmentSlotType.LEGS));
    public static final RegistryObject<Item> LOSSARNACH_BOOTS = LOTRItems.ITEMS.register("lossarnach_boots",
            () -> new LOTRItemArmor(LOTRMaterial.LOSSARNACH, EquipmentSlotType.FEET));
    public static final RegistryObject<Item> PELARGIR_HELMET = LOTRItems.ITEMS.register("pelargir_helmet",
            () -> new LOTRItemArmor(LOTRMaterial.PELARGIR, EquipmentSlotType.HEAD));
    public static final RegistryObject<Item> PELARGIR_CHESTPLATE = LOTRItems.ITEMS.register("pelargir_chestplate",
            () -> new LOTRItemArmor(LOTRMaterial.PELARGIR, EquipmentSlotType.CHEST));
    public static final RegistryObject<Item> PELARGIR_LEGGINGS = LOTRItems.ITEMS.register("pelargir_leggings",
            () -> new LOTRItemArmor(LOTRMaterial.PELARGIR, EquipmentSlotType.LEGS));
    public static final RegistryObject<Item> PELARGIR_BOOTS = LOTRItems.ITEMS.register("pelargir_boots",
            () -> new LOTRItemArmor(LOTRMaterial.PELARGIR, EquipmentSlotType.FEET));
    public static final RegistryObject<Item> PINNATH_GELIN_HELMET = LOTRItems.ITEMS.register("pinnath_gelin_helmet",
            () -> new LOTRItemArmor(LOTRMaterial.PINNATH_GELIN, EquipmentSlotType.HEAD));
    public static final RegistryObject<Item> PINNATH_GELIN_CHESTPLATE = LOTRItems.ITEMS.register("pinnath_gelin_chestplate",
            () -> new LOTRItemArmor(LOTRMaterial.PINNATH_GELIN, EquipmentSlotType.CHEST));
    public static final RegistryObject<Item> PINNATH_GELIN_LEGGINGS = LOTRItems.ITEMS.register("pinnath_gelin_leggings",
            () -> new LOTRItemArmor(LOTRMaterial.PINNATH_GELIN, EquipmentSlotType.LEGS));
    public static final RegistryObject<Item> PINNATH_GELIN_BOOTS = LOTRItems.ITEMS.register("pinnath_gelin_boots",
            () -> new LOTRItemArmor(LOTRMaterial.PINNATH_GELIN, EquipmentSlotType.FEET));
    public static final RegistryObject<Item> LOSSARNACH_BATTLEAXE = LOTRItems.ITEMS.register("lossarnach_battleaxe",
            () -> new LOTRItemBattleaxe(LOTRMaterial.LOSSARNACH));
    public static final RegistryObject<Item> PELARGIR_SWORD = LOTRItems.ITEMS.register("pelargir_sword",
            () -> new LOTRItemSword(LOTRMaterial.PELARGIR));
    public static final RegistryObject<Item> BLACKROOT_HELMET = LOTRItems.ITEMS.register("blackroot_helmet",
            () -> new LOTRItemArmor(LOTRMaterial.BLACKROOT, EquipmentSlotType.HEAD));
    public static final RegistryObject<Item> BLACKROOT_CHESTPLATE = LOTRItems.ITEMS.register("blackroot_chestplate",
            () -> new LOTRItemArmor(LOTRMaterial.BLACKROOT, EquipmentSlotType.CHEST));
    public static final RegistryObject<Item> BLACKROOT_LEGGINGS = LOTRItems.ITEMS.register("blackroot_leggings",
            () -> new LOTRItemArmor(LOTRMaterial.BLACKROOT, EquipmentSlotType.LEGS));
    public static final RegistryObject<Item> BLACKROOT_BOOTS = LOTRItems.ITEMS.register("blackroot_boots",
            () -> new LOTRItemArmor(LOTRMaterial.BLACKROOT, EquipmentSlotType.FEET));
    public static final RegistryObject<Item> GONDOR_PIKE = LOTRItems.ITEMS.register("gondor_pike",
            () -> new LOTRItemPike(LOTRMaterial.GONDOR));
    public static final RegistryObject<Item> DOL_AMROTH_GAMBESON_CHESTPLATE = LOTRItems.ITEMS.register("dol_amroth_gambeson_chestplate",
            () -> new LOTRItemArmor(LOTRMaterial.GAMBESON, EquipmentSlotType.CHEST, "dolAmroth"));
    public static final RegistryObject<Item> DOL_AMROTH_GAMBESON_LEGGINGS = LOTRItems.ITEMS.register("dol_amroth_gambeson_leggings",
            () -> new LOTRItemArmor(LOTRMaterial.GAMBESON, EquipmentSlotType.LEGS, "dolAmrothLegs"));
    public static final RegistryObject<Item> DOL_AMROTH_LONGSPEAR = LOTRItems.ITEMS.register("dol_amroth_longspear",
            () -> new LOTRItemPolearmLong(LOTRMaterial.DOL_AMROTH));
    public static final RegistryObject<Item> GONDOR_GAMBESON_CHESTPLATE = LOTRItems.ITEMS.register("gondor_gambeson_chestplate",
            () -> new LOTRItemArmor(LOTRMaterial.GAMBESON, EquipmentSlotType.CHEST, "gondor"));
    public static final RegistryObject<Item> LEBENNIN_GAMBESON_CHESTPLATE = LOTRItems.ITEMS.register("lebennin_gambeson_chestplate",
            () -> new LOTRItemArmor(LOTRMaterial.GAMBESON, EquipmentSlotType.CHEST, "lebennin"));
    public static final RegistryObject<Item> LAMEDON_HELMET = LOTRItems.ITEMS.register("lamedon_helmet",
            () -> new LOTRItemArmor(LOTRMaterial.LAMEDON, EquipmentSlotType.HEAD));
    public static final RegistryObject<Item> LAMEDON_CHESTPLATE = LOTRItems.ITEMS.register("lamedon_chestplate",
            () -> new LOTRItemArmor(LOTRMaterial.LAMEDON, EquipmentSlotType.CHEST));
    public static final RegistryObject<Item> LAMEDON_LEGGINGS = LOTRItems.ITEMS.register("lamedon_leggings",
            () -> new LOTRItemArmor(LOTRMaterial.LAMEDON, EquipmentSlotType.LEGS));
    public static final RegistryObject<Item> LAMEDON_BOOTS = LOTRItems.ITEMS.register("lamedon_boots",
            () -> new LOTRItemArmor(LOTRMaterial.LAMEDON, EquipmentSlotType.FEET));
    public static final RegistryObject<Item> LAMEDON_JACKET_CHESTPLATE = LOTRItems.ITEMS.register("lamedon_jacket_chestplate",
            () -> new LOTRItemArmor(LOTRMaterial.JACKET, EquipmentSlotType.CHEST, "lamedon"));
    public static final RegistryObject<Item> DALE_GAMBESON_CHESTPLATE = LOTRItems.ITEMS.register("dale_gambeson_chestplate",
            () -> new LOTRItemArmor(LOTRMaterial.GAMBESON, EquipmentSlotType.CHEST, "dale"));
    public static final RegistryObject<Item> ARNOR_HELMET = LOTRItems.ITEMS.register("arnor_helmet",
            () -> new LOTRItemArmor(LOTRMaterial.ARNOR, EquipmentSlotType.HEAD, "helmet"));
    public static final RegistryObject<Item> ARNOR_CHESTPLATE = LOTRItems.ITEMS.register("arnor_chestplate",
            () -> new LOTRItemArmor(LOTRMaterial.ARNOR, EquipmentSlotType.CHEST));
    public static final RegistryObject<Item> ARNOR_LEGGINGS = LOTRItems.ITEMS.register("arnor_leggings",
            () -> new LOTRItemArmor(LOTRMaterial.ARNOR, EquipmentSlotType.LEGS));
    public static final RegistryObject<Item> ARNOR_BOOTS = LOTRItems.ITEMS.register("arnor_boots",
            () -> new LOTRItemArmor(LOTRMaterial.ARNOR, EquipmentSlotType.FEET));
    public static final RegistryObject<Item> RHUN_SWORD = LOTRItems.ITEMS.register("rhun_sword",
            () -> new LOTRItemSword(LOTRMaterial.RHUN));
    public static final RegistryObject<Item> RHUN_DAGGER = LOTRItems.ITEMS.register("rhun_dagger",
            () -> new LOTRItemDagger(LOTRMaterial.RHUN));
    public static final RegistryObject<Item> RHUN_POISONED_DAGGER = LOTRItems.ITEMS.register("rhun_poisoned_dagger",
            () -> new LOTRItemDagger(LOTRMaterial.RHUN, LOTRItemDagger.DaggerEffect.POISON));
    public static final RegistryObject<Item> RHUN_POLEARM = LOTRItems.ITEMS.register("rhun_polearm",
            () -> new LOTRItemPolearm(LOTRMaterial.RHUN));
    public static final RegistryObject<Item> RHUN_PIKE = LOTRItems.ITEMS.register("rhun_pike",
            () -> new LOTRItemPike(LOTRMaterial.RHUN));
    public static final RegistryObject<Item> RHUN_HELMET = LOTRItems.ITEMS.register("rhun_helmet",
            () -> new LOTRItemArmor(LOTRMaterial.RHUN, EquipmentSlotType.HEAD));
    public static final RegistryObject<Item> RHUN_CHESTPLATE = LOTRItems.ITEMS.register("rhun_chestplate",
            () -> new LOTRItemArmor(LOTRMaterial.RHUN, EquipmentSlotType.CHEST));
    public static final RegistryObject<Item> RHUN_LEGGINGS = LOTRItems.ITEMS.register("rhun_leggings",
            () -> new LOTRItemArmor(LOTRMaterial.RHUN, EquipmentSlotType.LEGS));
    public static final RegistryObject<Item> RHUN_BOOTS = LOTRItems.ITEMS.register("rhun_boots",
            () -> new LOTRItemArmor(LOTRMaterial.RHUN, EquipmentSlotType.FEET));
    public static final RegistryObject<Item> RHUN_GOLD_HELMET = LOTRItems.ITEMS.register("rhun_gold_helmet",
            () -> new LOTRItemArmor(LOTRMaterial.RHUN_GOLD, EquipmentSlotType.HEAD, "helmet"));
    public static final RegistryObject<Item> RHUN_GOLD_CHESTPLATE = LOTRItems.ITEMS.register("rhun_gold_chestplate",
            () -> new LOTRItemArmor(LOTRMaterial.RHUN_GOLD, EquipmentSlotType.CHEST));
    public static final RegistryObject<Item> RHUN_GOLD_LEGGINGS = LOTRItems.ITEMS.register("rhun_gold_leggings",
            () -> new LOTRItemArmor(LOTRMaterial.RHUN_GOLD, EquipmentSlotType.LEGS));
    public static final RegistryObject<Item> RHUN_GOLD_BOOTS = LOTRItems.ITEMS.register("rhun_gold_boots",
            () -> new LOTRItemArmor(LOTRMaterial.RHUN_GOLD, EquipmentSlotType.FEET));
    public static final RegistryObject<Item> RHUN_WARLORD_HELMET = LOTRItems.ITEMS.register("rhun_warlord_helmet",
            () -> new LOTRItemArmor(LOTRMaterial.RHUN_GOLD, EquipmentSlotType.HEAD, "warlordHelmet"));
    public static final RegistryObject<Item> RHUN_BATTLEAXE = LOTRItems.ITEMS.register("rhun_battleaxe",
            () -> new LOTRItemBattleaxe(LOTRMaterial.RHUN));
    public static final RegistryObject<Item> RIVENDELL_SWORD = LOTRItems.ITEMS.register("rivendell_sword",
            () -> new LOTRItemSword(LOTRMaterial.RIVENDELL, true));
    public static final RegistryObject<Item> RIVENDELL_DAGGER = LOTRItems.ITEMS.register("rivendell_dagger",
            () -> new LOTRItemDagger(LOTRMaterial.RIVENDELL));
    public static final RegistryObject<Item> RIVENDELL_POISONED_DAGGER = LOTRItems.ITEMS.register("rivendell_poisoned_dagger",
            () -> new LOTRItemDagger(LOTRMaterial.RIVENDELL, LOTRItemDagger.DaggerEffect.POISON));
    public static final RegistryObject<Item> RIVENDELL_HELMET = LOTRItems.ITEMS.register("rivendell_helmet",
            () -> new LOTRItemArmor(LOTRMaterial.RIVENDELL, EquipmentSlotType.HEAD));
    public static final RegistryObject<Item> RIVENDELL_CHESTPLATE = LOTRItems.ITEMS.register("rivendell_chestplate",
            () -> new LOTRItemArmor(LOTRMaterial.RIVENDELL, EquipmentSlotType.CHEST));
    public static final RegistryObject<Item> RIVENDELL_LEGGINGS = LOTRItems.ITEMS.register("rivendell_leggings",
            () -> new LOTRItemArmor(LOTRMaterial.RIVENDELL, EquipmentSlotType.LEGS));
    public static final RegistryObject<Item> RIVENDELL_BOOTS = LOTRItems.ITEMS.register("rivendell_boots",
            () -> new LOTRItemArmor(LOTRMaterial.RIVENDELL, EquipmentSlotType.FEET));
    public static final RegistryObject<Item> RIVENDELL_POLEARM = LOTRItems.ITEMS.register("rivendell_polearm",
            () -> new LOTRItemPolearm(LOTRMaterial.RIVENDELL));
    public static final RegistryObject<Item> RIVENDELL_LONGSPEAR = LOTRItems.ITEMS.register("rivendell_longspear",
            () -> new LOTRItemPolearmLong(LOTRMaterial.RIVENDELL));
    public static final RegistryObject<Item> ARNOR_SWORD = LOTRItems.ITEMS.register("arnor_sword",
            () -> new LOTRItemSword(LOTRMaterial.ARNOR));
    public static final RegistryObject<Item> ARNOR_DAGGER = LOTRItems.ITEMS.register("arnor_dagger",
            () -> new LOTRItemDagger(LOTRMaterial.ARNOR));
    public static final RegistryObject<Item> ARNOR_POISONED_DAGGER = LOTRItems.ITEMS.register("arnor_poisoned_dagger",
            () -> new LOTRItemDagger(LOTRMaterial.ARNOR, LOTRItemDagger.DaggerEffect.POISON));
    public static final RegistryObject<Item> MOREDAIN_SWORD = LOTRItems.ITEMS.register("moredain_sword",
            () -> new LOTRItemSword(LOTRMaterial.MOREDAIN_BRONZE));
    public static final RegistryObject<Item> GULF_HARAD_HELMET = LOTRItems.ITEMS.register("gulf_harad_helmet",
            () -> new LOTRItemArmor(LOTRMaterial.GULF_HARAD, EquipmentSlotType.HEAD));
    public static final RegistryObject<Item> GULF_HARAD_CHESTPLATE = LOTRItems.ITEMS.register("gulf_harad_chestplate",
            () -> new LOTRItemArmor(LOTRMaterial.GULF_HARAD, EquipmentSlotType.CHEST, "body"));
    public static final RegistryObject<Item> GULF_HARAD_LEGGINGS = LOTRItems.ITEMS.register("gulf_harad_leggings",
            () -> new LOTRItemArmor(LOTRMaterial.GULF_HARAD, EquipmentSlotType.LEGS));
    public static final RegistryObject<Item> GULF_HARAD_BOOTS = LOTRItems.ITEMS.register("gulf_harad_boots",
            () -> new LOTRItemArmor(LOTRMaterial.GULF_HARAD, EquipmentSlotType.FEET));
    public static final RegistryObject<Item> CORSAIR_HELMET = LOTRItems.ITEMS.register("corsair_helmet",
            () -> new LOTRItemArmor(LOTRMaterial.CORSAIR, EquipmentSlotType.HEAD));
    public static final RegistryObject<Item> CORSAIR_CHESTPLATE = LOTRItems.ITEMS.register("corsair_chestplate",
            () -> new LOTRItemArmor(LOTRMaterial.CORSAIR, EquipmentSlotType.CHEST));
    public static final RegistryObject<Item> CORSAIR_LEGGINGS = LOTRItems.ITEMS.register("corsair_leggings",
            () -> new LOTRItemArmor(LOTRMaterial.CORSAIR, EquipmentSlotType.LEGS));
    public static final RegistryObject<Item> CORSAIR_BOOTS = LOTRItems.ITEMS.register("corsair_boots",
            () -> new LOTRItemArmor(LOTRMaterial.CORSAIR, EquipmentSlotType.FEET));
    public static final RegistryObject<Item> CORSAIR_SWORD = LOTRItems.ITEMS.register("corsair_sword",
            () -> new LOTRItemSword(LOTRMaterial.CORSAIR));
    public static final RegistryObject<Item> CORSAIR_DAGGER = LOTRItems.ITEMS.register("corsair_dagger",
            () -> new LOTRItemDagger(LOTRMaterial.CORSAIR));
    public static final RegistryObject<Item> CORSAIR_POISONED_DAGGER = LOTRItems.ITEMS.register("corsair_poisoned_dagger",
            () -> new LOTRItemDagger(LOTRMaterial.CORSAIR, LOTRItemDagger.DaggerEffect.POISON));
    public static final RegistryObject<Item> CORSAIR_BATTLEAXE = LOTRItems.ITEMS.register("corsair_battleaxe",
            () -> new LOTRItemBattleaxe(LOTRMaterial.CORSAIR));
    public static final RegistryObject<Item> UMBAR_HELMET = LOTRItems.ITEMS.register("umbar_helmet",
            () -> new LOTRItemArmor(LOTRMaterial.UMBAR, EquipmentSlotType.HEAD, "helmet"));
    public static final RegistryObject<Item> UMBAR_CHESTPLATE = LOTRItems.ITEMS.register("umbar_chestplate",
            () -> new LOTRItemArmor(LOTRMaterial.UMBAR, EquipmentSlotType.CHEST));
    public static final RegistryObject<Item> UMBAR_LEGGINGS = LOTRItems.ITEMS.register("umbar_leggings",
            () -> new LOTRItemArmor(LOTRMaterial.UMBAR, EquipmentSlotType.LEGS));
    public static final RegistryObject<Item> UMBAR_BOOTS = LOTRItems.ITEMS.register("umbar_boots",
            () -> new LOTRItemArmor(LOTRMaterial.UMBAR, EquipmentSlotType.FEET));
    public static final RegistryObject<Item> HARNEDOR_HELMET = LOTRItems.ITEMS.register("harnedor_helmet",
            () -> new LOTRItemArmor(LOTRMaterial.HARNEDOR, EquipmentSlotType.HEAD, "helmet"));
    public static final RegistryObject<Item> HARNEDOR_CHESTPLATE = LOTRItems.ITEMS.register("harnedor_chestplate",
            () -> new LOTRItemArmor(LOTRMaterial.HARNEDOR, EquipmentSlotType.CHEST, "body"));
    public static final RegistryObject<Item> HARNEDOR_LEGGINGS = LOTRItems.ITEMS.register("harnedor_leggings",
            () -> new LOTRItemArmor(LOTRMaterial.HARNEDOR, EquipmentSlotType.LEGS));
    public static final RegistryObject<Item> HARNEDOR_BOOTS = LOTRItems.ITEMS.register("harnedor_boots",
            () -> new LOTRItemArmor(LOTRMaterial.HARNEDOR, EquipmentSlotType.FEET));
    public static final RegistryObject<Item> HARAD_SWORD = LOTRItems.ITEMS.register("harad_sword",
            () -> new LOTRItemSword(LOTRMaterial.NEAR_HARAD));
    public static final RegistryObject<Item> HARAD_DAGGER = LOTRItems.ITEMS.register("harad_dagger",
            () -> new LOTRItemDagger(LOTRMaterial.NEAR_HARAD));
    public static final RegistryObject<Item> HARAD_POISONED_DAGGER = LOTRItems.ITEMS.register("harad_poisoned_dagger",
            () -> new LOTRItemDagger(LOTRMaterial.NEAR_HARAD, LOTRItemDagger.DaggerEffect.POISON));
    public static final RegistryObject<Item> HARAD_PIKE = LOTRItems.ITEMS.register("harad_pike",
            () -> new LOTRItemPike(LOTRMaterial.NEAR_HARAD));
    public static final RegistryObject<Item> GULF_HARAD_SWORD = LOTRItems.ITEMS.register("gulf_harad_sword",
            () -> new LOTRItemSword(LOTRMaterial.GULF_HARAD));
    public static final RegistryObject<Item> NOMAD_HELMET = LOTRItems.ITEMS.register("nomad_helmet",
            () -> new LOTRItemArmor(LOTRMaterial.HARAD_NOMAD, EquipmentSlotType.HEAD));
    public static final RegistryObject<Item> NOMAD_CHESTPLATE = LOTRItems.ITEMS.register("nomad_chestplate",
            () -> new LOTRItemArmor(LOTRMaterial.HARAD_NOMAD, EquipmentSlotType.CHEST));
    public static final RegistryObject<Item> NOMAD_LEGGINGS = LOTRItems.ITEMS.register("nomad_leggings",
            () -> new LOTRItemArmor(LOTRMaterial.HARAD_NOMAD, EquipmentSlotType.LEGS));
    public static final RegistryObject<Item> NOMAD_BOOTS = LOTRItems.ITEMS.register("nomad_boots",
            () -> new LOTRItemArmor(LOTRMaterial.HARAD_NOMAD, EquipmentSlotType.FEET));
    public static final RegistryObject<Item> ANCIENT_HARAD_DAGGER = LOTRItems.ITEMS.register("ancient_harad_dagger",
            () -> new LOTRItemDagger(LOTRMaterial.ANCIENT_HARAD));
    public static final RegistryObject<Item> BLACK_NUMENOREAN_HELMET = LOTRItems.ITEMS.register("black_numenorean_helmet",
            () -> new LOTRItemArmor(LOTRMaterial.BLACK_NUMENOREAN, EquipmentSlotType.HEAD, "helmet"));
    public static final RegistryObject<Item> BLACK_NUMENOREAN_CHESTPLATE = LOTRItems.ITEMS.register("black_numenorean_chestplate",
            () -> new LOTRItemArmor(LOTRMaterial.BLACK_NUMENOREAN, EquipmentSlotType.CHEST));
    public static final RegistryObject<Item> BLACK_NUMENOREAN_LEGGINGS = LOTRItems.ITEMS.register("black_numenorean_leggings",
            () -> new LOTRItemArmor(LOTRMaterial.BLACK_NUMENOREAN, EquipmentSlotType.LEGS));
    public static final RegistryObject<Item> BLACK_NUMENOREAN_BOOTS = LOTRItems.ITEMS.register("black_numenorean_boots",
            () -> new LOTRItemArmor(LOTRMaterial.BLACK_NUMENOREAN, EquipmentSlotType.FEET));
    public static final RegistryObject<Item> BLACK_NUMENOREAN_SWORD = LOTRItems.ITEMS.register("black_numenorean_sword",
            () -> new LOTRItemSword(LOTRMaterial.BLACK_NUMENOREAN));
    public static final RegistryObject<Item> BLACK_NUMENOREAN_DAGGER = LOTRItems.ITEMS.register("black_numenorean_dagger",
            () -> new LOTRItemDagger(LOTRMaterial.BLACK_NUMENOREAN));
    public static final RegistryObject<Item> BLACK_NUMENOREAN_POISONED_DAGGER = LOTRItems.ITEMS.register("black_numenorean_poisoned_dagger",
            () -> new LOTRItemDagger(LOTRMaterial.BLACK_NUMENOREAN, LOTRItemDagger.DaggerEffect.POISON));
    public static final RegistryObject<Item> BLACK_NUMENOREAN_MACE = LOTRItems.ITEMS.register("black_numenorean_mace",
            () -> new LOTRItemHammer(LOTRMaterial.BLACK_NUMENOREAN));
}
