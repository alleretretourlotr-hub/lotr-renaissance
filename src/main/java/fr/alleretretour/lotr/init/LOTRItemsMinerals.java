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
    public static final RegistryObject<Item> TOPAZ = LOTRItems.ITEMS.register("topaz",
            () -> new Item(new Item.Properties().tab(LOTRCreativeTabs.TAB_MISC)));
    public static final RegistryObject<Item> AMETHYST = LOTRItems.ITEMS.register("amethyst",
            () -> new Item(new Item.Properties().tab(LOTRCreativeTabs.TAB_MISC)));
    public static final RegistryObject<Item> SAPPHIRE = LOTRItems.ITEMS.register("sapphire",
            () -> new Item(new Item.Properties().tab(LOTRCreativeTabs.TAB_MISC)));
    public static final RegistryObject<Item> RUBY = LOTRItems.ITEMS.register("ruby",
            () -> new Item(new Item.Properties().tab(LOTRCreativeTabs.TAB_MISC)));
    public static final RegistryObject<Item> AMBER = LOTRItems.ITEMS.register("amber",
            () -> new Item(new Item.Properties().tab(LOTRCreativeTabs.TAB_MISC)));
    public static final RegistryObject<Item> OPAL = LOTRItems.ITEMS.register("opal",
            () -> new Item(new Item.Properties().tab(LOTRCreativeTabs.TAB_MISC)));
    public static final RegistryObject<Item> SALT = LOTRItems.ITEMS.register("salt",
            () -> new Item(new Item.Properties().tab(LOTRCreativeTabs.TAB_MISC)));
}
