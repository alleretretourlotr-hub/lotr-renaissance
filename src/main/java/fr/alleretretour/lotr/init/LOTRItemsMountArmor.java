package fr.alleretretour.lotr.init;

import fr.alleretretour.lotr.item.LOTRItemMountArmor;
import net.minecraft.item.Item;
import net.minecraftforge.fml.RegistryObject;

/** PORT des armures de monture du Legacy (valeurs de reduction exactes). */
public final class LOTRItemsMountArmor {

    public static final RegistryObject<Item> HORSE_ARMOR_GONDOR =
            LOTRItems.ITEMS.register("horse_armor_gondor",
                    () -> new LOTRItemMountArmor(LOTRItemMountArmor.Mount.HORSE, 11,
                            "horse_gondor", new Item.Properties()
                            .tab(LOTRItems.TAB_LOTR).stacksTo(1)));
    public static final RegistryObject<Item> HORSE_ARMOR_ROHAN =
            LOTRItems.ITEMS.register("horse_armor_rohan",
                    () -> new LOTRItemMountArmor(LOTRItemMountArmor.Mount.HORSE, 9,
                            "horse_rohan", new Item.Properties()
                            .tab(LOTRItems.TAB_LOTR).stacksTo(1)));
    public static final RegistryObject<Item> HORSE_ARMOR_GALADHRIM =
            LOTRItems.ITEMS.register("horse_armor_galadhrim",
                    () -> new LOTRItemMountArmor(LOTRItemMountArmor.Mount.HORSE, 11,
                            "horse_galadhrim", new Item.Properties()
                            .tab(LOTRItems.TAB_LOTR).stacksTo(1)));
    public static final RegistryObject<Item> HORSE_ARMOR_HIGH_ELVEN =
            LOTRItems.ITEMS.register("horse_armor_high_elven",
                    () -> new LOTRItemMountArmor(LOTRItemMountArmor.Mount.HORSE, 11,
                            "horse_high_elven", new Item.Properties()
                            .tab(LOTRItems.TAB_LOTR).stacksTo(1)));
    public static final RegistryObject<Item> HORSE_ARMOR_RIVENDELL =
            LOTRItems.ITEMS.register("horse_armor_rivendell",
                    () -> new LOTRItemMountArmor(LOTRItemMountArmor.Mount.HORSE, 11,
                            "horse_rivendell", new Item.Properties()
                            .tab(LOTRItems.TAB_LOTR).stacksTo(1)));
    public static final RegistryObject<Item> HORSE_ARMOR_NEAR_HARAD =
            LOTRItems.ITEMS.register("horse_armor_near_harad",
                    () -> new LOTRItemMountArmor(LOTRItemMountArmor.Mount.HORSE, 9,
                            "horse_near_harad", new Item.Properties()
                            .tab(LOTRItems.TAB_LOTR).stacksTo(1)));
    public static final RegistryObject<Item> HORSE_ARMOR_RHUN_GOLD =
            LOTRItems.ITEMS.register("horse_armor_rhun_gold",
                    () -> new LOTRItemMountArmor(LOTRItemMountArmor.Mount.HORSE, 11,
                            "horse_rhun_gold", new Item.Properties()
                            .tab(LOTRItems.TAB_LOTR).stacksTo(1)));
    public static final RegistryObject<Item> HORSE_ARMOR_UMBAR =
            LOTRItems.ITEMS.register("horse_armor_umbar",
                    () -> new LOTRItemMountArmor(LOTRItemMountArmor.Mount.HORSE, 11,
                            "horse_umbar", new Item.Properties()
                            .tab(LOTRItems.TAB_LOTR).stacksTo(1)));
    public static final RegistryObject<Item> HORSE_ARMOR_DALE =
            LOTRItems.ITEMS.register("horse_armor_dale",
                    () -> new LOTRItemMountArmor(LOTRItemMountArmor.Mount.HORSE, 11,
                            "horse_dale", new Item.Properties()
                            .tab(LOTRItems.TAB_LOTR).stacksTo(1)));
    public static final RegistryObject<Item> HORSE_ARMOR_LAMEDON =
            LOTRItems.ITEMS.register("horse_armor_lamedon",
                    () -> new LOTRItemMountArmor(LOTRItemMountArmor.Mount.HORSE, 9,
                            "horse_lamedon", new Item.Properties()
                            .tab(LOTRItems.TAB_LOTR).stacksTo(1)));
    public static final RegistryObject<Item> HORSE_ARMOR_DOL_AMROTH =
            LOTRItems.ITEMS.register("horse_armor_dol_amroth",
                    () -> new LOTRItemMountArmor(LOTRItemMountArmor.Mount.HORSE, 11,
                            "horse_dol_amroth", new Item.Properties()
                            .tab(LOTRItems.TAB_LOTR).stacksTo(1)));
    public static final RegistryObject<Item> HORSE_ARMOR_MORGUL =
            LOTRItems.ITEMS.register("horse_armor_morgul",
                    () -> new LOTRItemMountArmor(LOTRItemMountArmor.Mount.HORSE, 11,
                            "horse_morgul", new Item.Properties()
                            .tab(LOTRItems.TAB_LOTR).stacksTo(1)));
    public static final RegistryObject<Item> HORSE_ARMOR_MITHRIL =
            LOTRItems.ITEMS.register("horse_armor_mithril",
                    () -> new LOTRItemMountArmor(LOTRItemMountArmor.Mount.HORSE, 14,
                            "horse_mithril", new Item.Properties()
                            .tab(LOTRItems.TAB_LOTR).stacksTo(1)));
    public static final RegistryObject<Item> BOAR_ARMOR_DWARVEN =
            LOTRItems.ITEMS.register("boar_armor_dwarven",
                    () -> new LOTRItemMountArmor(LOTRItemMountArmor.Mount.BOAR, 13,
                            "boar_dwarven", new Item.Properties()
                            .tab(LOTRItems.TAB_LOTR).stacksTo(1)));
    public static final RegistryObject<Item> BOAR_ARMOR_BLUE_DWARVEN =
            LOTRItems.ITEMS.register("boar_armor_blue_dwarven",
                    () -> new LOTRItemMountArmor(LOTRItemMountArmor.Mount.BOAR, 13,
                            "boar_blue_dwarven", new Item.Properties()
                            .tab(LOTRItems.TAB_LOTR).stacksTo(1)));
    public static final RegistryObject<Item> WARG_ARMOR_ANGMAR =
            LOTRItems.ITEMS.register("warg_armor_angmar",
                    () -> new LOTRItemMountArmor(LOTRItemMountArmor.Mount.WARG, 11,
                            "warg_angmar", new Item.Properties()
                            .tab(LOTRItems.TAB_LOTR).stacksTo(1)));
    public static final RegistryObject<Item> WARG_ARMOR_MORDOR =
            LOTRItems.ITEMS.register("warg_armor_mordor",
                    () -> new LOTRItemMountArmor(LOTRItemMountArmor.Mount.WARG, 11,
                            "warg_mordor", new Item.Properties()
                            .tab(LOTRItems.TAB_LOTR).stacksTo(1)));
    public static final RegistryObject<Item> WARG_ARMOR_URUK =
            LOTRItems.ITEMS.register("warg_armor_uruk",
                    () -> new LOTRItemMountArmor(LOTRItemMountArmor.Mount.WARG, 13,
                            "warg_uruk", new Item.Properties()
                            .tab(LOTRItems.TAB_LOTR).stacksTo(1)));
    public static final RegistryObject<Item> RHINO_ARMOR_HALF_TROLL =
            LOTRItems.ITEMS.register("rhino_armor_half_troll",
                    () -> new LOTRItemMountArmor(LOTRItemMountArmor.Mount.RHINO, 9,
                            "rhino_half_troll", new Item.Properties()
                            .tab(LOTRItems.TAB_LOTR).stacksTo(1)));

    public static final RegistryObject<Item> ELK_ARMOR_WOOD_ELVEN =
            LOTRItems.ITEMS.register("elk_armor_wood_elven",
                    () -> new LOTRItemMountArmor(LOTRItemMountArmor.Mount.ELK, 11,
                            "elk_wood_elven", new Item.Properties()
                            .tab(LOTRItems.TAB_LOTR).stacksTo(1)));

    private LOTRItemsMountArmor() {
    }

    public static void init() {
    }
}
