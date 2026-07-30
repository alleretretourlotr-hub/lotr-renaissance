package fr.alleretretour.lotr.init;

import net.minecraft.item.Item;
import net.minecraftforge.fml.RegistryObject;

/**
 * PORT de LOTRItemBanner (Legacy) - premiere tranche : les 8 bannieres des
 * porte-bannieres embauchables. Textures officielles item/banner/ du Legacy.
 * Les autres types de bannieres (et la banniere posable) viendront avec le
 * lot protection de terrain.
 */
public final class LOTRItemsBanners {

    public static final RegistryObject<Item> GONDOR_BANNER =
            LOTRItems.ITEMS.register("gondor_banner",
                    () -> new fr.alleretretour.lotr.item.LOTRItemBanner(
                            new Item.Properties().tab(LOTRItems.TAB_LOTR).stacksTo(1)));
    public static final RegistryObject<Item> ROHAN_BANNER =
            LOTRItems.ITEMS.register("rohan_banner",
                    () -> new fr.alleretretour.lotr.item.LOTRItemBanner(
                            new Item.Properties().tab(LOTRItems.TAB_LOTR).stacksTo(1)));
    public static final RegistryObject<Item> DWARF_BANNER =
            LOTRItems.ITEMS.register("dwarf_banner",
                    () -> new fr.alleretretour.lotr.item.LOTRItemBanner(
                            new Item.Properties().tab(LOTRItems.TAB_LOTR).stacksTo(1)));
    public static final RegistryObject<Item> BLUE_MOUNTAINS_BANNER =
            LOTRItems.ITEMS.register("blue_mountains_banner",
                    () -> new fr.alleretretour.lotr.item.LOTRItemBanner(
                            new Item.Properties().tab(LOTRItems.TAB_LOTR).stacksTo(1)));
    public static final RegistryObject<Item> HIGH_ELF_BANNER =
            LOTRItems.ITEMS.register("high_elf_banner",
                    () -> new fr.alleretretour.lotr.item.LOTRItemBanner(
                            new Item.Properties().tab(LOTRItems.TAB_LOTR).stacksTo(1)));
    public static final RegistryObject<Item> RIVENDELL_BANNER =
            LOTRItems.ITEMS.register("rivendell_banner",
                    () -> new fr.alleretretour.lotr.item.LOTRItemBanner(
                            new Item.Properties().tab(LOTRItems.TAB_LOTR).stacksTo(1)));
    public static final RegistryObject<Item> GALADHRIM_BANNER =
            LOTRItems.ITEMS.register("galadhrim_banner",
                    () -> new fr.alleretretour.lotr.item.LOTRItemBanner(
                            new Item.Properties().tab(LOTRItems.TAB_LOTR).stacksTo(1)));
    public static final RegistryObject<Item> RANGER_BANNER =
            LOTRItems.ITEMS.register("ranger_banner",
                    () -> new fr.alleretretour.lotr.item.LOTRItemBanner(
                            new Item.Properties().tab(LOTRItems.TAB_LOTR).stacksTo(1)));

    // ===== bannieres des factions du mega-lot 2 =====
    public static final RegistryObject<Item> RHUDAUR_BANNER =
            LOTRItems.ITEMS.register("rhudaur_banner",
                    () -> new fr.alleretretour.lotr.item.LOTRItemBanner(
                            new Item.Properties().tab(LOTRItems.TAB_LOTR).stacksTo(1)));
    public static final RegistryObject<Item> DOL_GULDUR_BANNER =
            LOTRItems.ITEMS.register("dol_guldur_banner",
                    () -> new fr.alleretretour.lotr.item.LOTRItemBanner(
                            new Item.Properties().tab(LOTRItems.TAB_LOTR).stacksTo(1)));
    public static final RegistryObject<Item> DUNLAND_BANNER =
            LOTRItems.ITEMS.register("dunland_banner",
                    () -> new fr.alleretretour.lotr.item.LOTRItemBanner(
                            new Item.Properties().tab(LOTRItems.TAB_LOTR).stacksTo(1)));
    public static final RegistryObject<Item> RHUN_BANNER =
            LOTRItems.ITEMS.register("rhun_banner",
                    () -> new fr.alleretretour.lotr.item.LOTRItemBanner(
                            new Item.Properties().tab(LOTRItems.TAB_LOTR).stacksTo(1)));
    public static final RegistryObject<Item> HALF_TROLL_BANNER =
            LOTRItems.ITEMS.register("half_troll_banner",
                    () -> new fr.alleretretour.lotr.item.LOTRItemBanner(
                            new Item.Properties().tab(LOTRItems.TAB_LOTR).stacksTo(1)));
    public static final RegistryObject<Item> NEAR_HARAD_BANNER =
            LOTRItems.ITEMS.register("near_harad_banner",
                    () -> new fr.alleretretour.lotr.item.LOTRItemBanner(
                            new Item.Properties().tab(LOTRItems.TAB_LOTR).stacksTo(1)));
    public static final RegistryObject<Item> MOREDAIN_BANNER =
            LOTRItems.ITEMS.register("moredain_banner",
                    () -> new fr.alleretretour.lotr.item.LOTRItemBanner(
                            new Item.Properties().tab(LOTRItems.TAB_LOTR).stacksTo(1)));
    public static final RegistryObject<Item> TAUREDAIN_BANNER =
            LOTRItems.ITEMS.register("tauredain_banner",
                    () -> new fr.alleretretour.lotr.item.LOTRItemBanner(
                            new Item.Properties().tab(LOTRItems.TAB_LOTR).stacksTo(1)));

    // ===== bannieres (lot 9) =====
    public static final RegistryObject<Item> MORDOR_BANNER =
            LOTRItems.ITEMS.register("mordor_banner",
                    () -> new fr.alleretretour.lotr.item.LOTRItemBanner(
                            new Item.Properties().tab(LOTRItems.TAB_LOTR).stacksTo(1)));
    public static final RegistryObject<Item> MINAS_MORGUL_BANNER =
            LOTRItems.ITEMS.register("minas_morgul_banner",
                    () -> new fr.alleretretour.lotr.item.LOTRItemBanner(
                            new Item.Properties().tab(LOTRItems.TAB_LOTR).stacksTo(1)));
    public static final RegistryObject<Item> ISENGARD_BANNER =
            LOTRItems.ITEMS.register("isengard_banner",
                    () -> new fr.alleretretour.lotr.item.LOTRItemBanner(
                            new Item.Properties().tab(LOTRItems.TAB_LOTR).stacksTo(1)));
    public static final RegistryObject<Item> GUNDABAD_BANNER =
            LOTRItems.ITEMS.register("gundabad_banner",
                    () -> new fr.alleretretour.lotr.item.LOTRItemBanner(
                            new Item.Properties().tab(LOTRItems.TAB_LOTR).stacksTo(1)));
    public static final RegistryObject<Item> ANGMAR_BANNER =
            LOTRItems.ITEMS.register("angmar_banner",
                    () -> new fr.alleretretour.lotr.item.LOTRItemBanner(
                            new Item.Properties().tab(LOTRItems.TAB_LOTR).stacksTo(1)));
    public static final RegistryObject<Item> WOOD_ELF_BANNER =
            LOTRItems.ITEMS.register("wood_elf_banner",
                    () -> new fr.alleretretour.lotr.item.LOTRItemBanner(
                            new Item.Properties().tab(LOTRItems.TAB_LOTR).stacksTo(1)));
    public static final RegistryObject<Item> DALE_BANNER =
            LOTRItems.ITEMS.register("dale_banner",
                    () -> new fr.alleretretour.lotr.item.LOTRItemBanner(
                            new Item.Properties().tab(LOTRItems.TAB_LOTR).stacksTo(1)));
    public static final RegistryObject<Item> ESGAROTH_BANNER =
            LOTRItems.ITEMS.register("esgaroth_banner",
                    () -> new fr.alleretretour.lotr.item.LOTRItemBanner(
                            new Item.Properties().tab(LOTRItems.TAB_LOTR).stacksTo(1)));
    public static final RegistryObject<Item> UMBAR_BANNER =
            LOTRItems.ITEMS.register("umbar_banner",
                    () -> new fr.alleretretour.lotr.item.LOTRItemBanner(
                            new Item.Properties().tab(LOTRItems.TAB_LOTR).stacksTo(1)));
    public static final RegistryObject<Item> BREE_BANNER =
            LOTRItems.ITEMS.register("bree_banner",
                    () -> new fr.alleretretour.lotr.item.LOTRItemBanner(
                            new Item.Properties().tab(LOTRItems.TAB_LOTR).stacksTo(1)));
    public static final RegistryObject<Item> DORWINION_BANNER =
            LOTRItems.ITEMS.register("dorwinion_banner",
                    () -> new fr.alleretretour.lotr.item.LOTRItemBanner(
                            new Item.Properties().tab(LOTRItems.TAB_LOTR).stacksTo(1)));
    public static final RegistryObject<Item> ITHILIEN_BANNER =
            LOTRItems.ITEMS.register("ithilien_banner",
                    () -> new fr.alleretretour.lotr.item.LOTRItemBanner(
                            new Item.Properties().tab(LOTRItems.TAB_LOTR).stacksTo(1)));

    private LOTRItemsBanners() {
    }

    /** Declenche l'enregistrement des champs statiques. */
    public static void init() {
    }
}
