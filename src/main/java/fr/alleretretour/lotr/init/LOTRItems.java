package fr.alleretretour.lotr.init;

import fr.alleretretour.lotr.LOTRMod;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

/**
 * Registre differe des items.
 * Lot 1 : equipement du Gondor.
 * Lot 2 : materiaux bruts (lingots, aciers, peaux) servant de base de craft/reparation.
 */
public class LOTRItems {

    public static final DeferredRegister<Item> ITEMS =
            DeferredRegister.create(ForgeRegistries.ITEMS, LOTRMod.MOD_ID);

    // Onglet creatif - icone : epee du Gondor
    public static final ItemGroup TAB_LOTR = new ItemGroup("lotr") {
        @Override
        public ItemStack makeIcon() {
            return new ItemStack(LOTRItemsCombat.GONDOR_SWORD.get());
        }
    };

    /** Recipient vide rendu apres avoir bu, selon le vessel NBT de la boisson. */
    public static net.minecraft.item.ItemStack emptyVesselFor(fr.alleretretour.lotr.item.LOTRVessel vessel) {
        switch (vessel) {
            case MUG_CLAY: return new net.minecraft.item.ItemStack(LOTRItemsDrinks.CERAMIC_MUG.get());
            case GOBLET_GOLD: return new net.minecraft.item.ItemStack(LOTRItemsDrinks.GOBLET_GOLD.get());
            case GOBLET_SILVER: return new net.minecraft.item.ItemStack(LOTRItemsDrinks.GOBLET_SILVER.get());
            case GOBLET_COPPER: return new net.minecraft.item.ItemStack(LOTRItemsDrinks.GOBLET_COPPER.get());
            case GOBLET_WOOD: return new net.minecraft.item.ItemStack(LOTRItemsDrinks.GOBLET_WOOD.get());
            case SKULL: return new net.minecraft.item.ItemStack(LOTRItemsDrinks.SKULL_CUP.get());
            case GLASS: return new net.minecraft.item.ItemStack(LOTRItemsDrinks.WINE_GLASS.get());
            case BOTTLE: return new net.minecraft.item.ItemStack(net.minecraft.item.Items.GLASS_BOTTLE);
            case SKIN: return new net.minecraft.item.ItemStack(LOTRItemsDrinks.WATERSKIN.get());
            case HORN: return new net.minecraft.item.ItemStack(LOTRItemsDrinks.ALE_HORN.get());
            case HORN_GOLD: return new net.minecraft.item.ItemStack(LOTRItemsDrinks.ALE_HORN_GOLD.get());
            case MUG:
            default: return new net.minecraft.item.ItemStack(LOTRItemsDrinks.MUG.get());
        }
    }

    public static final RegistryObject<Item> SILVER_COIN = ITEMS.register("silver_coin",
            () -> new fr.alleretretour.lotr.item.LOTRItemCoin(
                    new Item.Properties().stacksTo(64).tab(LOTRCreativeTabs.TAB_MISC)));

    private static RegistryObject<Item> basic(String name) {
        return ITEMS.register(name, () -> new Item(new Item.Properties().tab(TAB_LOTR)));
    }

    // ===== LOT 2 : MATERIAUX =====
    public static final RegistryObject<Item> BRONZE = basic("bronze");
    public static final RegistryObject<Item> MITHRIL = basic("mithril");
    public static final RegistryObject<Item> MITHRIL_MAIL = basic("mithril_mail");
    public static final RegistryObject<Item> ORC_STEEL = basic("orc_steel");
    public static final RegistryObject<Item> URUK_STEEL = basic("uruk_steel");
    public static final RegistryObject<Item> MORGUL_STEEL = basic("morgul_steel");
    public static final RegistryObject<Item> BLACK_URUK_STEEL = basic("black_uruk_steel");
    public static final RegistryObject<Item> ELF_STEEL = basic("elf_steel");
    public static final RegistryObject<Item> DWARF_STEEL = basic("dwarf_steel");
    public static final RegistryObject<Item> BLUE_DWARF_STEEL = basic("blue_dwarf_steel");
    public static final RegistryObject<Item> GALVORN = basic("galvorn");
    public static final RegistryObject<Item> GILDED_IRON = basic("gilded_iron");
    public static final RegistryObject<Item> HITHLAIN = basic("hithlain");
    public static final RegistryObject<Item> FUR = basic("fur");
    public static final RegistryObject<Item> GEMSBOK_HIDE = basic("gemsbok_hide");
    public static final RegistryObject<Item> GEMSBOK_HORN = basic("gemsbok_horn");
    public static final RegistryObject<Item> RHINO_HORN = basic("rhino_horn");
    public static final RegistryObject<Item> LION_FUR = basic("lion_fur");
    public static final RegistryObject<Item> OBSIDIAN_SHARD = basic("obsidian_shard");
}
