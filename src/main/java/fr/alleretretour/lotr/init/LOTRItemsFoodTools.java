package fr.alleretretour.lotr.init;

import fr.alleretretour.lotr.item.LOTRItemAxe;
import fr.alleretretour.lotr.item.LOTRItemBerry;
import fr.alleretretour.lotr.item.LOTRItemFood;
import fr.alleretretour.lotr.item.LOTRItemHoe;
import fr.alleretretour.lotr.item.LOTRItemMattock;
import fr.alleretretour.lotr.item.LOTRItemPickaxe;
import fr.alleretretour.lotr.item.LOTRItemShovel;
import fr.alleretretour.lotr.item.LOTRItemStew;
import fr.alleretretour.lotr.item.LOTRMaterial;
import net.minecraft.item.Item;
import net.minecraftforge.fml.RegistryObject;

/**
 * GENERE AUTOMATIQUEMENT depuis LOTRMod.java (Legacy 1.7.10) - ne pas editer.
 * Nourriture (aliments, baies, ragouts) et outils (pioches, haches, pelles, houes, mattocks).
 */
public class LOTRItemsFoodTools {

    public static void init() {
    }

    public static final RegistryObject<Item> BRONZE_SHOVEL = LOTRItems.ITEMS.register("bronze_shovel",
            () -> new LOTRItemShovel(LOTRMaterial.BRONZE));
    public static final RegistryObject<Item> BRONZE_PICKAXE = LOTRItems.ITEMS.register("bronze_pickaxe",
            () -> new LOTRItemPickaxe(LOTRMaterial.BRONZE));
    public static final RegistryObject<Item> BRONZE_AXE = LOTRItems.ITEMS.register("bronze_axe",
            () -> new LOTRItemAxe(LOTRMaterial.BRONZE));
    public static final RegistryObject<Item> BRONZE_HOE = LOTRItems.ITEMS.register("bronze_hoe",
            () -> new LOTRItemHoe(LOTRMaterial.BRONZE));
    public static final RegistryObject<Item> LEMBAS = LOTRItems.ITEMS.register("lembas",
            () -> new LOTRItemFood(20, 2.0f, false));
    public static final RegistryObject<Item> MITHRIL_SHOVEL = LOTRItems.ITEMS.register("mithril_shovel",
            () -> new LOTRItemShovel(LOTRMaterial.MITHRIL));
    public static final RegistryObject<Item> MITHRIL_PICKAXE = LOTRItems.ITEMS.register("mithril_pickaxe",
            () -> new LOTRItemPickaxe(LOTRMaterial.MITHRIL));
    public static final RegistryObject<Item> MITHRIL_AXE = LOTRItems.ITEMS.register("mithril_axe",
            () -> new LOTRItemAxe(LOTRMaterial.MITHRIL));
    public static final RegistryObject<Item> MITHRIL_HOE = LOTRItems.ITEMS.register("mithril_hoe",
            () -> new LOTRItemHoe(LOTRMaterial.MITHRIL));
    public static final RegistryObject<Item> MALLORN_SHOVEL = LOTRItems.ITEMS.register("mallorn_shovel",
            () -> new LOTRItemShovel(LOTRMaterial.MALLORN));
    public static final RegistryObject<Item> MALLORN_PICKAXE = LOTRItems.ITEMS.register("mallorn_pickaxe",
            () -> new LOTRItemPickaxe(LOTRMaterial.MALLORN));
    public static final RegistryObject<Item> MALLORN_AXE = LOTRItems.ITEMS.register("mallorn_axe",
            () -> new LOTRItemAxe(LOTRMaterial.MALLORN));
    public static final RegistryObject<Item> MALLORN_HOE = LOTRItems.ITEMS.register("mallorn_hoe",
            () -> new LOTRItemHoe(LOTRMaterial.MALLORN));
    public static final RegistryObject<Item> ELVEN_SHOVEL = LOTRItems.ITEMS.register("elven_shovel",
            () -> new LOTRItemShovel(LOTRMaterial.GALADHRIM));
    public static final RegistryObject<Item> ELVEN_PICKAXE = LOTRItems.ITEMS.register("elven_pickaxe",
            () -> new LOTRItemPickaxe(LOTRMaterial.GALADHRIM));
    public static final RegistryObject<Item> ELVEN_AXE = LOTRItems.ITEMS.register("elven_axe",
            () -> new LOTRItemAxe(LOTRMaterial.GALADHRIM));
    public static final RegistryObject<Item> ELVEN_HOE = LOTRItems.ITEMS.register("elven_hoe",
            () -> new LOTRItemHoe(LOTRMaterial.GALADHRIM));
    public static final RegistryObject<Item> GAMMON = LOTRItems.ITEMS.register("gammon",
            () -> new LOTRItemFood(8, 0.8f, true));
    public static final RegistryObject<Item> APPLE_GREEN = LOTRItems.ITEMS.register("apple_green",
            () -> new LOTRItemFood(4, 0.3f, false));
    public static final RegistryObject<Item> PEAR = LOTRItems.ITEMS.register("pear",
            () -> new LOTRItemFood(4, 0.3f, false));
    public static final RegistryObject<Item> CHERRY = LOTRItems.ITEMS.register("cherry",
            () -> new LOTRItemFood(2, 0.2f, false));
    public static final RegistryObject<Item> DWARVEN_SHOVEL = LOTRItems.ITEMS.register("dwarven_shovel",
            () -> new LOTRItemShovel(LOTRMaterial.DWARVEN));
    public static final RegistryObject<Item> DWARVEN_PICKAXE = LOTRItems.ITEMS.register("dwarven_pickaxe",
            () -> new LOTRItemPickaxe(LOTRMaterial.DWARVEN));
    public static final RegistryObject<Item> DWARVEN_AXE = LOTRItems.ITEMS.register("dwarven_axe",
            () -> new LOTRItemAxe(LOTRMaterial.DWARVEN));
    public static final RegistryObject<Item> DWARVEN_HOE = LOTRItems.ITEMS.register("dwarven_hoe",
            () -> new LOTRItemHoe(LOTRMaterial.DWARVEN));
    public static final RegistryObject<Item> ORC_SHOVEL = LOTRItems.ITEMS.register("orc_shovel",
            () -> new LOTRItemShovel(LOTRMaterial.MORDOR));
    public static final RegistryObject<Item> ORC_PICKAXE = LOTRItems.ITEMS.register("orc_pickaxe",
            () -> new LOTRItemPickaxe(LOTRMaterial.MORDOR));
    public static final RegistryObject<Item> ORC_AXE = LOTRItems.ITEMS.register("orc_axe",
            () -> new LOTRItemAxe(LOTRMaterial.MORDOR));
    public static final RegistryObject<Item> ORC_HOE = LOTRItems.ITEMS.register("orc_hoe",
            () -> new LOTRItemHoe(LOTRMaterial.MORDOR));
    public static final RegistryObject<Item> URUK_SHOVEL = LOTRItems.ITEMS.register("uruk_shovel",
            () -> new LOTRItemShovel(LOTRMaterial.URUK));
    public static final RegistryObject<Item> URUK_PICKAXE = LOTRItems.ITEMS.register("uruk_pickaxe",
            () -> new LOTRItemPickaxe(LOTRMaterial.URUK));
    public static final RegistryObject<Item> URUK_AXE = LOTRItems.ITEMS.register("uruk_axe",
            () -> new LOTRItemAxe(LOTRMaterial.URUK));
    public static final RegistryObject<Item> URUK_HOE = LOTRItems.ITEMS.register("uruk_hoe",
            () -> new LOTRItemHoe(LOTRMaterial.URUK));
    public static final RegistryObject<Item> MALLORN_NUT = LOTRItems.ITEMS.register("mallorn_nut",
            () -> new LOTRItemFood(4, 0.4f, false));
    public static final RegistryObject<Item> MAGGOTY_BREAD = LOTRItems.ITEMS.register("maggoty_bread",
            () -> new LOTRItemFood(4, 0.5f, false));
    public static final RegistryObject<Item> DWARVEN_MATTOCK = LOTRItems.ITEMS.register("dwarven_mattock",
            () -> new LOTRItemMattock(LOTRMaterial.DWARVEN));
    public static final RegistryObject<Item> WOOD_ELVEN_SHOVEL = LOTRItems.ITEMS.register("wood_elven_shovel",
            () -> new LOTRItemShovel(LOTRMaterial.WOOD_ELVEN));
    public static final RegistryObject<Item> WOOD_ELVEN_PICKAXE = LOTRItems.ITEMS.register("wood_elven_pickaxe",
            () -> new LOTRItemPickaxe(LOTRMaterial.WOOD_ELVEN));
    public static final RegistryObject<Item> WOOD_ELVEN_AXE = LOTRItems.ITEMS.register("wood_elven_axe",
            () -> new LOTRItemAxe(LOTRMaterial.WOOD_ELVEN));
    public static final RegistryObject<Item> WOOD_ELVEN_HOE = LOTRItems.ITEMS.register("wood_elven_hoe",
            () -> new LOTRItemHoe(LOTRMaterial.WOOD_ELVEN));
    public static final RegistryObject<Item> RABBIT_RAW = LOTRItems.ITEMS.register("rabbit_raw",
            () -> new LOTRItemFood(2, 0.3f, true));
    public static final RegistryObject<Item> RABBIT_COOKED = LOTRItems.ITEMS.register("rabbit_cooked",
            () -> new LOTRItemFood(6, 0.6f, true));
    public static final RegistryObject<Item> RABBIT_STEW = LOTRItems.ITEMS.register("rabbit_stew",
            () -> new LOTRItemStew(10, 0.8f, true));
    public static final RegistryObject<Item> HOBBIT_PANCAKE = LOTRItems.ITEMS.register("hobbit_pancake",
            () -> new LOTRItemFood(4, 0.6f, false));
    public static final RegistryObject<Item> ANGMAR_SHOVEL = LOTRItems.ITEMS.register("angmar_shovel",
            () -> new LOTRItemShovel(LOTRMaterial.ANGMAR));
    public static final RegistryObject<Item> ANGMAR_PICKAXE = LOTRItems.ITEMS.register("angmar_pickaxe",
            () -> new LOTRItemPickaxe(LOTRMaterial.ANGMAR));
    public static final RegistryObject<Item> ANGMAR_AXE = LOTRItems.ITEMS.register("angmar_axe",
            () -> new LOTRItemAxe(LOTRMaterial.ANGMAR));
    public static final RegistryObject<Item> ANGMAR_HOE = LOTRItems.ITEMS.register("angmar_hoe",
            () -> new LOTRItemHoe(LOTRMaterial.ANGMAR));
    public static final RegistryObject<Item> MANGO = LOTRItems.ITEMS.register("mango",
            () -> new LOTRItemFood(4, 0.3f, false));
    public static final RegistryObject<Item> BANANA_BREAD = LOTRItems.ITEMS.register("banana_bread",
            () -> new LOTRItemFood(5, 0.6f, false));
    public static final RegistryObject<Item> LION_RAW = LOTRItems.ITEMS.register("lion_raw",
            () -> new LOTRItemFood(3, 0.3f, true));
    public static final RegistryObject<Item> LION_COOKED = LOTRItems.ITEMS.register("lion_cooked",
            () -> new LOTRItemFood(8, 0.8f, true));
    public static final RegistryObject<Item> ZEBRA_RAW = LOTRItems.ITEMS.register("zebra_raw",
            () -> new LOTRItemFood(2, 0.1f, true));
    public static final RegistryObject<Item> ZEBRA_COOKED = LOTRItems.ITEMS.register("zebra_cooked",
            () -> new LOTRItemFood(6, 0.6f, true));
    public static final RegistryObject<Item> RHINO_RAW = LOTRItems.ITEMS.register("rhino_raw",
            () -> new LOTRItemFood(2, 0.1f, true));
    public static final RegistryObject<Item> RHINO_COOKED = LOTRItems.ITEMS.register("rhino_cooked",
            () -> new LOTRItemFood(7, 0.4f, true));
    public static final RegistryObject<Item> MAPLE_SYRUP = LOTRItems.ITEMS.register("maple_syrup",
            () -> new LOTRItemStew(2, 0.1f, false));
    public static final RegistryObject<Item> HOBBIT_PANCAKE_MAPLE_SYRUP = LOTRItems.ITEMS.register("hobbit_pancake_maple_syrup",
            () -> new LOTRItemFood(5, 0.6f, false));
    public static final RegistryObject<Item> HIGH_ELVEN_SHOVEL = LOTRItems.ITEMS.register("high_elven_shovel",
            () -> new LOTRItemShovel(LOTRMaterial.HIGH_ELVEN));
    public static final RegistryObject<Item> HIGH_ELVEN_PICKAXE = LOTRItems.ITEMS.register("high_elven_pickaxe",
            () -> new LOTRItemPickaxe(LOTRMaterial.HIGH_ELVEN));
    public static final RegistryObject<Item> HIGH_ELVEN_AXE = LOTRItems.ITEMS.register("high_elven_axe",
            () -> new LOTRItemAxe(LOTRMaterial.HIGH_ELVEN));
    public static final RegistryObject<Item> HIGH_ELVEN_HOE = LOTRItems.ITEMS.register("high_elven_hoe",
            () -> new LOTRItemHoe(LOTRMaterial.HIGH_ELVEN));
    public static final RegistryObject<Item> BLUE_DWARVEN_SHOVEL = LOTRItems.ITEMS.register("blue_dwarven_shovel",
            () -> new LOTRItemShovel(LOTRMaterial.BLUE_DWARVEN));
    public static final RegistryObject<Item> BLUE_DWARVEN_PICKAXE = LOTRItems.ITEMS.register("blue_dwarven_pickaxe",
            () -> new LOTRItemPickaxe(LOTRMaterial.BLUE_DWARVEN));
    public static final RegistryObject<Item> BLUE_DWARVEN_AXE = LOTRItems.ITEMS.register("blue_dwarven_axe",
            () -> new LOTRItemAxe(LOTRMaterial.BLUE_DWARVEN));
    public static final RegistryObject<Item> BLUE_DWARVEN_HOE = LOTRItems.ITEMS.register("blue_dwarven_hoe",
            () -> new LOTRItemHoe(LOTRMaterial.BLUE_DWARVEN));
    public static final RegistryObject<Item> BLUE_DWARVEN_MATTOCK = LOTRItems.ITEMS.register("blue_dwarven_mattock",
            () -> new LOTRItemMattock(LOTRMaterial.BLUE_DWARVEN));
    public static final RegistryObject<Item> DOL_GULDUR_SHOVEL = LOTRItems.ITEMS.register("dol_guldur_shovel",
            () -> new LOTRItemShovel(LOTRMaterial.DOL_GULDUR));
    public static final RegistryObject<Item> DOL_GULDUR_AXE = LOTRItems.ITEMS.register("dol_guldur_axe",
            () -> new LOTRItemAxe(LOTRMaterial.DOL_GULDUR));
    public static final RegistryObject<Item> DOL_GULDUR_PICKAXE = LOTRItems.ITEMS.register("dol_guldur_pickaxe",
            () -> new LOTRItemPickaxe(LOTRMaterial.DOL_GULDUR));
    public static final RegistryObject<Item> DOL_GULDUR_HOE = LOTRItems.ITEMS.register("dol_guldur_hoe",
            () -> new LOTRItemHoe(LOTRMaterial.DOL_GULDUR));
    public static final RegistryObject<Item> BLUEBERRY = LOTRItems.ITEMS.register("blueberry",
            () -> new LOTRItemBerry());
    public static final RegistryObject<Item> BLACKBERRY = LOTRItems.ITEMS.register("blackberry",
            () -> new LOTRItemBerry());
    public static final RegistryObject<Item> RASPBERRY = LOTRItems.ITEMS.register("raspberry",
            () -> new LOTRItemBerry());
    public static final RegistryObject<Item> CRANBERRY = LOTRItems.ITEMS.register("cranberry",
            () -> new LOTRItemBerry());
    public static final RegistryObject<Item> ELDERBERRY = LOTRItems.ITEMS.register("elderberry",
            () -> new LOTRItemBerry());
    public static final RegistryObject<Item> CHESTNUT_ROAST = LOTRItems.ITEMS.register("chestnut_roast",
            () -> new LOTRItemFood(2, 0.2f, false));
    public static final RegistryObject<Item> UTUMNO_PICKAXE = LOTRItems.ITEMS.register("utumno_pickaxe",
            () -> new LOTRItemPickaxe(LOTRMaterial.UTUMNO));
    public static final RegistryObject<Item> TOROG_STEW = LOTRItems.ITEMS.register("torog_stew",
            () -> new LOTRItemStew(8, 0.6f, true));
    public static final RegistryObject<Item> CRAM = LOTRItems.ITEMS.register("cram",
            () -> new LOTRItemFood(8, 1.0f, false));
    public static final RegistryObject<Item> LEMON = LOTRItems.ITEMS.register("lemon",
            () -> new LOTRItemFood(2, 0.2f, false));
    public static final RegistryObject<Item> ORANGE = LOTRItems.ITEMS.register("orange",
            () -> new LOTRItemFood(4, 0.3f, false));
    public static final RegistryObject<Item> LIME = LOTRItems.ITEMS.register("lime",
            () -> new LOTRItemFood(2, 0.2f, false));
    public static final RegistryObject<Item> TAUREDAIN_SHOVEL = LOTRItems.ITEMS.register("tauredain_shovel",
            () -> new LOTRItemShovel(LOTRMaterial.TAUREDAIN));
    public static final RegistryObject<Item> TAUREDAIN_PICKAXE = LOTRItems.ITEMS.register("tauredain_pickaxe",
            () -> new LOTRItemPickaxe(LOTRMaterial.TAUREDAIN));
    public static final RegistryObject<Item> TAUREDAIN_AXE = LOTRItems.ITEMS.register("tauredain_axe",
            () -> new LOTRItemAxe(LOTRMaterial.TAUREDAIN));
    public static final RegistryObject<Item> TAUREDAIN_HOE = LOTRItems.ITEMS.register("tauredain_hoe",
            () -> new LOTRItemHoe(LOTRMaterial.TAUREDAIN));
    public static final RegistryObject<Item> MUTTON_RAW = LOTRItems.ITEMS.register("mutton_raw",
            () -> new LOTRItemFood(3, 0.3f, true));
    public static final RegistryObject<Item> MUTTON_COOKED = LOTRItems.ITEMS.register("mutton_cooked",
            () -> new LOTRItemFood(8, 0.8f, true));
    public static final RegistryObject<Item> CORN = LOTRItems.ITEMS.register("corn",
            () -> new LOTRItemFood(2, 0.3f, false));
    public static final RegistryObject<Item> DEER_RAW = LOTRItems.ITEMS.register("deer_raw",
            () -> new LOTRItemFood(3, 0.3f, true));
    public static final RegistryObject<Item> DEER_COOKED = LOTRItems.ITEMS.register("deer_cooked",
            () -> new LOTRItemFood(8, 0.8f, true));
    public static final RegistryObject<Item> CORN_COOKED = LOTRItems.ITEMS.register("corn_cooked",
            () -> new LOTRItemFood(4, 0.4f, false));
    public static final RegistryObject<Item> LEEK_SOUP = LOTRItems.ITEMS.register("leek_soup",
            () -> new LOTRItemStew(8, 0.8f, false));
    public static final RegistryObject<Item> CAMEL_RAW = LOTRItems.ITEMS.register("camel_raw",
            () -> new LOTRItemFood(2, 0.2f, true));
    public static final RegistryObject<Item> CAMEL_COOKED = LOTRItems.ITEMS.register("camel_cooked",
            () -> new LOTRItemFood(6, 0.6f, true));
    public static final RegistryObject<Item> OLIVE = LOTRItems.ITEMS.register("olive",
            () -> new LOTRItemFood(1, 0.1f, false));
    public static final RegistryObject<Item> OLIVE_BREAD = LOTRItems.ITEMS.register("olive_bread",
            () -> new LOTRItemFood(5, 0.6f, false));
    public static final RegistryObject<Item> GRAPE_RED = LOTRItems.ITEMS.register("grape_red",
            () -> new LOTRItemFood(2, 0.2f, false));
    public static final RegistryObject<Item> GRAPE_WHITE = LOTRItems.ITEMS.register("grape_white",
            () -> new LOTRItemFood(2, 0.2f, false));
    public static final RegistryObject<Item> TURNIP_COOKED = LOTRItems.ITEMS.register("turnip_cooked",
            () -> new LOTRItemFood(6, 0.6f, false));
    public static final RegistryObject<Item> MELON_SOUP = LOTRItems.ITEMS.register("melon_soup",
            () -> new LOTRItemStew(5, 0.5f, false));
    public static final RegistryObject<Item> ALMOND = LOTRItems.ITEMS.register("almond",
            () -> new LOTRItemFood(2, 0.2f, false));
    public static final RegistryObject<Item> WILDBERRY = LOTRItems.ITEMS.register("wildberry",
            () -> new LOTRItemBerry());
    public static final RegistryObject<Item> PLUM = LOTRItems.ITEMS.register("plum",
            () -> new LOTRItemFood(4, 0.3f, false));
    public static final RegistryObject<Item> MARZIPAN = LOTRItems.ITEMS.register("marzipan",
            () -> new LOTRItemFood(6, 0.6f, false));
    public static final RegistryObject<Item> MARZIPAN_CHOCOLATE = LOTRItems.ITEMS.register("marzipan_chocolate",
            () -> new LOTRItemFood(8, 0.8f, false));
    public static final RegistryObject<Item> YAM_ROAST = LOTRItems.ITEMS.register("yam_roast",
            () -> new LOTRItemFood(6, 0.6f, false));
    public static final RegistryObject<Item> POMEGRANATE = LOTRItems.ITEMS.register("pomegranate",
            () -> new LOTRItemFood(4, 0.3f, false));
    public static final RegistryObject<Item> MITHRIL_MATTOCK = LOTRItems.ITEMS.register("mithril_mattock",
            () -> new LOTRItemMattock(LOTRMaterial.MITHRIL));
    public static final RegistryObject<Item> SALTED_FLESH = LOTRItems.ITEMS.register("salted_flesh",
            () -> new LOTRItemFood(6, 0.6f, true));
    public static final RegistryObject<Item> CORN_BREAD = LOTRItems.ITEMS.register("corn_bread",
            () -> new LOTRItemFood(5, 0.6f, false));
    public static final RegistryObject<Item> RAISINS = LOTRItems.ITEMS.register("raisins",
            () -> new LOTRItemFood(1, 0.1f, false));
    public static final RegistryObject<Item> RIVENDELL_SHOVEL = LOTRItems.ITEMS.register("rivendell_shovel",
            () -> new LOTRItemShovel(LOTRMaterial.RIVENDELL));
    public static final RegistryObject<Item> RIVENDELL_PICKAXE = LOTRItems.ITEMS.register("rivendell_pickaxe",
            () -> new LOTRItemPickaxe(LOTRMaterial.RIVENDELL));
    public static final RegistryObject<Item> RIVENDELL_AXE = LOTRItems.ITEMS.register("rivendell_axe",
            () -> new LOTRItemAxe(LOTRMaterial.RIVENDELL));
    public static final RegistryObject<Item> RIVENDELL_HOE = LOTRItems.ITEMS.register("rivendell_hoe",
            () -> new LOTRItemHoe(LOTRMaterial.RIVENDELL));
    public static final RegistryObject<Item> MUSHROOM_PIE = LOTRItems.ITEMS.register("mushroom_pie",
            () -> new LOTRItemFood(8, 0.3f, false));
}
