package fr.alleretretour.lotr.init;

import fr.alleretretour.lotr.item.LOTRItemMug;
import fr.alleretretour.lotr.item.LOTRItemVessel;
import net.minecraft.item.Item;
import net.minecraft.potion.Effects;
import net.minecraftforge.fml.RegistryObject;

/**
 * GENERE AUTOMATIQUEMENT depuis LOTRMod.java (Legacy 1.7.10) - ne pas editer.
 * Le systeme de boissons : chopes pleines et recipients vides.
 */
public class LOTRItemsDrinks {

    public static void init() {
    }

    public static final RegistryObject<Item> MUG = LOTRItems.ITEMS.register("mug",
            () -> new LOTRItemVessel());
    public static final RegistryObject<Item> MUG_WATER = LOTRItems.ITEMS.register("mug_water",
            () -> new LOTRItemMug(true));
    public static final RegistryObject<Item> MUG_MILK = LOTRItems.ITEMS.register("mug_milk",
            () -> new LOTRItemMug(true).setCuresEffects());
    public static final RegistryObject<Item> MUG_ALE = LOTRItems.ITEMS.register("mug_ale",
            () -> new LOTRItemMug(0.3f).setDrinkStats(3, 0.3f));
    public static final RegistryObject<Item> MUG_CHOCOLATE = LOTRItems.ITEMS.register("mug_chocolate",
            () -> new LOTRItemMug(true).setDrinkStats(6, 0.6f));
    public static final RegistryObject<Item> MUG_MIRUVOR = LOTRItems.ITEMS.register("mug_miruvor",
            () -> new LOTRItemMug(0.0f).setDrinkStats(8, 0.8f).addEffect(() -> Effects.DAMAGE_BOOST, 40).addEffect(() -> Effects.MOVEMENT_SPEED, 40));
    public static final RegistryObject<Item> MUG_ORC_DRAUGHT = LOTRItems.ITEMS.register("mug_orc_draught",
            () -> new LOTRItemMug(0.0f).setDrinkStats(6, 0.6f).addEffect(() -> Effects.DAMAGE_BOOST, 60).addEffect(() -> Effects.MOVEMENT_SPEED, 60).setDamageAmount(2));
    public static final RegistryObject<Item> MUG_MEAD = LOTRItems.ITEMS.register("mug_mead",
            () -> new LOTRItemMug(0.6f).setDrinkStats(4, 0.4f));
    public static final RegistryObject<Item> MUG_RED_WINE = LOTRItems.ITEMS.register("mug_red_wine",
            () -> new LOTRItemMug(1.0f).setDrinkStats(4, 0.4f));
    public static final RegistryObject<Item> MUG_CIDER = LOTRItems.ITEMS.register("mug_cider",
            () -> new LOTRItemMug(0.3f).setDrinkStats(4, 0.4f));
    public static final RegistryObject<Item> MUG_PERRY = LOTRItems.ITEMS.register("mug_perry",
            () -> new LOTRItemMug(0.3f).setDrinkStats(4, 0.4f));
    public static final RegistryObject<Item> MUG_CHERRY_LIQUEUR = LOTRItems.ITEMS.register("mug_cherry_liqueur",
            () -> new LOTRItemMug(1.0f).setDrinkStats(3, 0.3f));
    public static final RegistryObject<Item> MUG_RUM = LOTRItems.ITEMS.register("mug_rum",
            () -> new LOTRItemMug(1.5f).setDrinkStats(3, 0.3f));
    public static final RegistryObject<Item> MUG_ATHELAS_BREW = LOTRItems.ITEMS.register("mug_athelas_brew",
            () -> new LOTRItemMug(0.0f).setDrinkStats(6, 0.6f).addEffect(() -> Effects.DAMAGE_BOOST, 120).addEffect(() -> Effects.DAMAGE_RESISTANCE, 60)); // EQUILIBRAGE serveur
    public static final RegistryObject<Item> MUG_DWARVEN_TONIC = LOTRItems.ITEMS.register("mug_dwarven_tonic",
            () -> new LOTRItemMug(0.2f).setDrinkStats(4, 0.4f).addEffect(() -> Effects.NIGHT_VISION, 240));
    public static final RegistryObject<Item> MUG_DWARVEN_ALE = LOTRItems.ITEMS.register("mug_dwarven_ale",
            () -> new LOTRItemMug(0.4f).setDrinkStats(3, 0.3f));
    public static final RegistryObject<Item> MUG_VODKA = LOTRItems.ITEMS.register("mug_vodka",
            () -> new LOTRItemMug(1.75f).setDrinkStats(3, 0.3f));
    public static final RegistryObject<Item> MUG_MANGO_JUICE = LOTRItems.ITEMS.register("mug_mango_juice",
            () -> new LOTRItemMug(true).setDrinkStats(6, 0.6f));
    public static final RegistryObject<Item> MUG_MAPLE_BEER = LOTRItems.ITEMS.register("mug_maple_beer",
            () -> new LOTRItemMug(0.4f).setDrinkStats(4, 0.6f));
    public static final RegistryObject<Item> MUG_ARAQ = LOTRItems.ITEMS.register("mug_araq",
            () -> new LOTRItemMug(1.4f).setDrinkStats(4, 0.4f));
    public static final RegistryObject<Item> MUG_CARROT_WINE = LOTRItems.ITEMS.register("mug_carrot_wine",
            () -> new LOTRItemMug(0.8f).setDrinkStats(3, 0.4f));
    public static final RegistryObject<Item> MUG_BANANA_BEER = LOTRItems.ITEMS.register("mug_banana_beer",
            () -> new LOTRItemMug(0.5f).setDrinkStats(4, 0.6f));
    public static final RegistryObject<Item> MUG_MELON_LIQUEUR = LOTRItems.ITEMS.register("mug_melon_liqueur",
            () -> new LOTRItemMug(1.0f).setDrinkStats(3, 0.3f));
    public static final RegistryObject<Item> MUG_CACTUS_LIQUEUR = LOTRItems.ITEMS.register("mug_cactus_liqueur",
            () -> new LOTRItemMug(0.8f).setDrinkStats(2, 0.3f));
    public static final RegistryObject<Item> MUG_TOROG_DRAUGHT = LOTRItems.ITEMS.register("mug_torog_draught",
            () -> new LOTRItemMug(0.6f).setDrinkStats(6, 0.6f).addEffect(() -> Effects.DAMAGE_BOOST, 90));
    public static final RegistryObject<Item> MUG_BLUEBERRY_JUICE = LOTRItems.ITEMS.register("mug_blueberry_juice",
            () -> new LOTRItemMug(true).setDrinkStats(5, 0.5f));
    public static final RegistryObject<Item> MUG_BLACKBERRY_JUICE = LOTRItems.ITEMS.register("mug_blackberry_juice",
            () -> new LOTRItemMug(true).setDrinkStats(5, 0.5f));
    public static final RegistryObject<Item> MUG_RASPBERRY_JUICE = LOTRItems.ITEMS.register("mug_raspberry_juice",
            () -> new LOTRItemMug(true).setDrinkStats(5, 0.5f));
    public static final RegistryObject<Item> MUG_CRANBERRY_JUICE = LOTRItems.ITEMS.register("mug_cranberry_juice",
            () -> new LOTRItemMug(true).setDrinkStats(5, 0.5f));
    public static final RegistryObject<Item> MUG_ELDERBERRY_JUICE = LOTRItems.ITEMS.register("mug_elderberry_juice",
            () -> new LOTRItemMug(true).setDrinkStats(5, 0.5f));
    public static final RegistryObject<Item> MUG_ORANGE_JUICE = LOTRItems.ITEMS.register("mug_orange_juice",
            () -> new LOTRItemMug(true).setDrinkStats(6, 0.6f));
    public static final RegistryObject<Item> MUG_LEMON_LIQUEUR = LOTRItems.ITEMS.register("mug_lemon_liqueur",
            () -> new LOTRItemMug(1.0f).setDrinkStats(3, 0.3f));
    public static final RegistryObject<Item> MUG_LEMONADE = LOTRItems.ITEMS.register("mug_lemonade",
            () -> new LOTRItemMug(true).setDrinkStats(5, 0.3f));
    public static final RegistryObject<Item> MUG_LIME_LIQUEUR = LOTRItems.ITEMS.register("mug_lime_liqueur",
            () -> new LOTRItemMug(1.0f).setDrinkStats(3, 0.3f));
    public static final RegistryObject<Item> MUG_TAUREDAIN_COCOA = LOTRItems.ITEMS.register("mug_tauredain_cocoa",
            () -> new LOTRItemMug(0.0f).setDrinkStats(6, 0.6f).addEffect(() -> Effects.DAMAGE_BOOST, 40).addEffect(() -> Effects.MOVEMENT_SPEED, 40));
    public static final RegistryObject<Item> MUG_CORN_LIQUOR = LOTRItems.ITEMS.register("mug_corn_liquor",
            () -> new LOTRItemMug(1.0f).setDrinkStats(3, 0.3f));
    public static final RegistryObject<Item> MUG_APPLE_JUICE = LOTRItems.ITEMS.register("mug_apple_juice",
            () -> new LOTRItemMug(true).setDrinkStats(6, 0.6f));
    public static final RegistryObject<Item> MUG_WHITE_WINE = LOTRItems.ITEMS.register("mug_white_wine",
            () -> new LOTRItemMug(0.9f).setDrinkStats(4, 0.4f));
    public static final RegistryObject<Item> MUG_RED_GRAPE_JUICE = LOTRItems.ITEMS.register("mug_red_grape_juice",
            () -> new LOTRItemMug(true).setDrinkStats(5, 0.5f));
    public static final RegistryObject<Item> MUG_WHITE_GRAPE_JUICE = LOTRItems.ITEMS.register("mug_white_grape_juice",
            () -> new LOTRItemMug(true).setDrinkStats(5, 0.5f));
    public static final RegistryObject<Item> CERAMIC_MUG = LOTRItems.ITEMS.register("ceramic_mug",
            () -> new LOTRItemVessel());
    public static final RegistryObject<Item> MUG_PLUM_KVASS = LOTRItems.ITEMS.register("mug_plum_kvass",
            () -> new LOTRItemMug(0.2f).setDrinkStats(4, 0.4f));
    public static final RegistryObject<Item> GOBLET_GOLD = LOTRItems.ITEMS.register("goblet_gold",
            () -> new LOTRItemVessel());
    public static final RegistryObject<Item> GOBLET_SILVER = LOTRItems.ITEMS.register("goblet_silver",
            () -> new LOTRItemVessel());
    public static final RegistryObject<Item> GOBLET_COPPER = LOTRItems.ITEMS.register("goblet_copper",
            () -> new LOTRItemVessel());
    public static final RegistryObject<Item> GOBLET_WOOD = LOTRItems.ITEMS.register("goblet_wood",
            () -> new LOTRItemVessel());
    public static final RegistryObject<Item> SKULL_CUP = LOTRItems.ITEMS.register("skull_cup",
            () -> new LOTRItemVessel());
    public static final RegistryObject<Item> WINE_GLASS = LOTRItems.ITEMS.register("wine_glass",
            () -> new LOTRItemVessel());
    public static final RegistryObject<Item> WATERSKIN = LOTRItems.ITEMS.register("waterskin",
            () -> new LOTRItemVessel());
    public static final RegistryObject<Item> ALE_HORN = LOTRItems.ITEMS.register("ale_horn",
            () -> new LOTRItemVessel());
    public static final RegistryObject<Item> ALE_HORN_GOLD = LOTRItems.ITEMS.register("ale_horn_gold",
            () -> new LOTRItemVessel());
    public static final RegistryObject<Item> MUG_SOUR_MILK = LOTRItems.ITEMS.register("mug_sour_milk",
            () -> new LOTRItemMug(0.2f).setDrinkStats(5, 0.5f));
    public static final RegistryObject<Item> MUG_POMEGRANATE_JUICE = LOTRItems.ITEMS.register("mug_pomegranate_juice",
            () -> new LOTRItemMug(true).setDrinkStats(6, 0.6f));
    public static final RegistryObject<Item> MUG_POMEGRANATE_WINE = LOTRItems.ITEMS.register("mug_pomegranate_wine",
            () -> new LOTRItemMug(0.9f).setDrinkStats(4, 0.4f));
}
