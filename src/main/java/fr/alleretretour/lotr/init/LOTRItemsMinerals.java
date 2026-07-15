package fr.alleretretour.lotr.init;

import net.minecraft.item.Item;
import net.minecraftforge.fml.RegistryObject;

/** GENERE AUTOMATIQUEMENT - gemmes et pepites des minerais. */
public class LOTRItemsMinerals {

    public static void init() {
    }

    public static final RegistryObject<Item> COPPER = LOTRItems.ITEMS.register("copper",
            () -> new Item(new Item.Properties().tab(LOTRCreativeTabs.TAB_MISC)));
    public static final RegistryObject<Item> TIN = LOTRItems.ITEMS.register("tin",
            () -> new Item(new Item.Properties().tab(LOTRCreativeTabs.TAB_MISC)));
    public static final RegistryObject<Item> SILVER_NUGGET = LOTRItems.ITEMS.register("silver_nugget",
            () -> new Item(new Item.Properties().tab(LOTRCreativeTabs.TAB_MISC)));
    public static final RegistryObject<Item> MITHRIL_NUGGET = LOTRItems.ITEMS.register("mithril_nugget",
            () -> new Item(new Item.Properties().tab(LOTRCreativeTabs.TAB_MISC)));
    public static final RegistryObject<Item> NAURITE_GEM = LOTRItems.ITEMS.register("naurite_gem",
            () -> new Item(new Item.Properties().tab(LOTRCreativeTabs.TAB_MISC)));
    public static final RegistryObject<Item> QUENDITE_CRYSTAL = LOTRItems.ITEMS.register("quendite_crystal",
            () -> new Item(new Item.Properties().tab(LOTRCreativeTabs.TAB_MISC)));
    public static final RegistryObject<Item> GULDURIL_CRYSTAL = LOTRItems.ITEMS.register("gulduril_crystal",
            () -> new Item(new Item.Properties().tab(LOTRCreativeTabs.TAB_MISC)));
    public static final RegistryObject<Item> SULFUR = LOTRItems.ITEMS.register("sulfur",
            () -> new Item(new Item.Properties().tab(LOTRCreativeTabs.TAB_MISC)));
    public static final RegistryObject<Item> SALTPETER = LOTRItems.ITEMS.register("saltpeter",
            () -> new Item(new Item.Properties().tab(LOTRCreativeTabs.TAB_MISC)));
}
