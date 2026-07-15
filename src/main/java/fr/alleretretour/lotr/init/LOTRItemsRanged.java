package fr.alleretretour.lotr.init;

import fr.alleretretour.lotr.item.LOTRItemBlowgun;
import fr.alleretretour.lotr.item.LOTRItemBow;
import fr.alleretretour.lotr.item.LOTRItemCrossbow;
import fr.alleretretour.lotr.item.LOTRItemCrossbowBolt;
import fr.alleretretour.lotr.item.LOTRItemDart;
import fr.alleretretour.lotr.item.LOTRItemSpear;
import fr.alleretretour.lotr.item.LOTRItemThrowingAxe;
import fr.alleretretour.lotr.item.LOTRMaterial;
import net.minecraft.item.Item;
import net.minecraftforge.fml.RegistryObject;

/**
 * GENERE AUTOMATIQUEMENT depuis LOTRMod.java (Legacy 1.7.10) - ne pas editer.
 * Armes a distance : arcs, arbaletes, lances, haches de jet, sarbacane, munitions.
 */
public class LOTRItemsRanged {

    public static void init() {
    }

    public static final RegistryObject<Item> GONDOR_SPEAR = LOTRItems.ITEMS.register("gondor_spear",
            () -> new LOTRItemSpear(LOTRMaterial.GONDOR));
    public static final RegistryObject<Item> ORC_SPEAR = LOTRItems.ITEMS.register("orc_spear",
            () -> new LOTRItemSpear(LOTRMaterial.MORDOR));
    public static final RegistryObject<Item> BRONZE_SPEAR = LOTRItems.ITEMS.register("bronze_spear",
            () -> new LOTRItemSpear(LOTRMaterial.BRONZE));
    public static final RegistryObject<Item> IRON_SPEAR = LOTRItems.ITEMS.register("iron_spear",
            () -> new LOTRItemSpear(LOTRMaterial.IRON));
    public static final RegistryObject<Item> MITHRIL_SPEAR = LOTRItems.ITEMS.register("mithril_spear",
            () -> new LOTRItemSpear(LOTRMaterial.MITHRIL));
    public static final RegistryObject<Item> ELVEN_SPEAR = LOTRItems.ITEMS.register("elven_spear",
            () -> new LOTRItemSpear(LOTRMaterial.GALADHRIM));
    public static final RegistryObject<Item> MALLORN_BOW = LOTRItems.ITEMS.register("mallorn_bow",
            () -> new LOTRItemBow(LOTRMaterial.MALLORN));
    public static final RegistryObject<Item> ELVEN_BOW = LOTRItems.ITEMS.register("elven_bow",
            () -> new LOTRItemBow(LOTRMaterial.GALADHRIM, 1.25));
    public static final RegistryObject<Item> ORC_BOW = LOTRItems.ITEMS.register("orc_bow",
            () -> new LOTRItemBow(LOTRMaterial.MORDOR, 1.125));
    public static final RegistryObject<Item> DWARVEN_THROWING_AXE = LOTRItems.ITEMS.register("dwarven_throwing_axe",
            () -> new LOTRItemThrowingAxe(LOTRMaterial.DWARVEN));
    public static final RegistryObject<Item> URUK_SPEAR = LOTRItems.ITEMS.register("uruk_spear",
            () -> new LOTRItemSpear(LOTRMaterial.URUK));
    public static final RegistryObject<Item> CROSSBOW_BOLT = LOTRItems.ITEMS.register("crossbow_bolt",
            () -> new LOTRItemCrossbowBolt());
    public static final RegistryObject<Item> URUK_CROSSBOW = LOTRItems.ITEMS.register("uruk_crossbow",
            () -> new LOTRItemCrossbow(LOTRMaterial.URUK));
    public static final RegistryObject<Item> IRON_CROSSBOW = LOTRItems.ITEMS.register("iron_crossbow",
            () -> new LOTRItemCrossbow(LOTRMaterial.IRON));
    public static final RegistryObject<Item> MITHRIL_CROSSBOW = LOTRItems.ITEMS.register("mithril_crossbow",
            () -> new LOTRItemCrossbow(LOTRMaterial.MITHRIL));
    public static final RegistryObject<Item> MIRKWOOD_BOW = LOTRItems.ITEMS.register("mirkwood_bow",
            () -> new LOTRItemBow(LOTRMaterial.WOOD_ELVEN));
    public static final RegistryObject<Item> ROHAN_SPEAR = LOTRItems.ITEMS.register("rohan_spear",
            () -> new LOTRItemSpear(LOTRMaterial.ROHAN));
    public static final RegistryObject<Item> WOOD_ELVEN_SPEAR = LOTRItems.ITEMS.register("wood_elven_spear",
            () -> new LOTRItemSpear(LOTRMaterial.WOOD_ELVEN));
    public static final RegistryObject<Item> ANGMAR_SPEAR = LOTRItems.ITEMS.register("angmar_spear",
            () -> new LOTRItemSpear(LOTRMaterial.ANGMAR));
    public static final RegistryObject<Item> HIGH_ELVEN_SPEAR = LOTRItems.ITEMS.register("high_elven_spear",
            () -> new LOTRItemSpear(LOTRMaterial.HIGH_ELVEN));
    public static final RegistryObject<Item> NEAR_HARAD_SPEAR = LOTRItems.ITEMS.register("near_harad_spear",
            () -> new LOTRItemSpear(LOTRMaterial.UMBAR));
    public static final RegistryObject<Item> NEAR_HARAD_BOW = LOTRItems.ITEMS.register("near_harad_bow",
            () -> new LOTRItemBow(LOTRMaterial.NEAR_HARAD, 1.1));
    public static final RegistryObject<Item> BLUE_DWARVEN_THROWING_AXE = LOTRItems.ITEMS.register("blue_dwarven_throwing_axe",
            () -> new LOTRItemThrowingAxe(LOTRMaterial.BLUE_DWARVEN));
    public static final RegistryObject<Item> DWARVEN_SPEAR = LOTRItems.ITEMS.register("dwarven_spear",
            () -> new LOTRItemSpear(LOTRMaterial.DWARVEN));
    public static final RegistryObject<Item> BLUE_DWARVEN_SPEAR = LOTRItems.ITEMS.register("blue_dwarven_spear",
            () -> new LOTRItemSpear(LOTRMaterial.BLUE_DWARVEN));
    public static final RegistryObject<Item> DOL_GULDUR_SPEAR = LOTRItems.ITEMS.register("dol_guldur_spear",
            () -> new LOTRItemSpear(LOTRMaterial.DOL_GULDUR));
    public static final RegistryObject<Item> BLACK_URUK_SPEAR = LOTRItems.ITEMS.register("black_uruk_spear",
            () -> new LOTRItemSpear(LOTRMaterial.BLACK_URUK));
    public static final RegistryObject<Item> BLACK_URUK_BOW = LOTRItems.ITEMS.register("black_uruk_bow",
            () -> new LOTRItemBow(LOTRMaterial.BLACK_URUK, 1.25));
    public static final RegistryObject<Item> UTUMNO_SPEAR = LOTRItems.ITEMS.register("utumno_spear",
            () -> new LOTRItemSpear(LOTRMaterial.UTUMNO));
    public static final RegistryObject<Item> UTUMNO_BOW = LOTRItems.ITEMS.register("utumno_bow",
            () -> new LOTRItemBow(LOTRMaterial.UTUMNO, 1.25));
    public static final RegistryObject<Item> ROHAN_BOW = LOTRItems.ITEMS.register("rohan_bow",
            () -> new LOTRItemBow(LOTRMaterial.ROHAN));
    public static final RegistryObject<Item> GONDOR_BOW = LOTRItems.ITEMS.register("gondor_bow",
            () -> new LOTRItemBow(LOTRMaterial.GONDOR, 1.125));
    public static final RegistryObject<Item> HIGH_ELVEN_BOW = LOTRItems.ITEMS.register("high_elven_bow",
            () -> new LOTRItemBow(LOTRMaterial.HIGH_ELVEN, 1.25));
    public static final RegistryObject<Item> BRONZE_CROSSBOW = LOTRItems.ITEMS.register("bronze_crossbow",
            () -> new LOTRItemCrossbow(LOTRMaterial.BRONZE));
    public static final RegistryObject<Item> MOREDAIN_SPEAR = LOTRItems.ITEMS.register("moredain_spear",
            () -> new LOTRItemSpear(LOTRMaterial.MOREDAIN_SPEAR));
    public static final RegistryObject<Item> BRONZE_THROWING_AXE = LOTRItems.ITEMS.register("bronze_throwing_axe",
            () -> new LOTRItemThrowingAxe(LOTRMaterial.BRONZE));
    public static final RegistryObject<Item> IRON_THROWING_AXE = LOTRItems.ITEMS.register("iron_throwing_axe",
            () -> new LOTRItemThrowingAxe(LOTRMaterial.IRON));
    public static final RegistryObject<Item> TAUREDAIN_SPEAR = LOTRItems.ITEMS.register("tauredain_spear",
            () -> new LOTRItemSpear(LOTRMaterial.TAUREDAIN));
    public static final RegistryObject<Item> TAUREDAIN_BLOWGUN = LOTRItems.ITEMS.register("tauredain_blowgun",
            () -> new LOTRItemBlowgun(LOTRMaterial.TAUREDAIN));
    public static final RegistryObject<Item> TAUREDAIN_DART = LOTRItems.ITEMS.register("tauredain_dart",
            () -> new LOTRItemDart());
    public static final RegistryObject<Item> TAUREDAIN_DART_POISONED = LOTRItems.ITEMS.register("tauredain_dart_poisoned",
            () -> new LOTRItemDart());
    public static final RegistryObject<Item> DALE_SPEAR = LOTRItems.ITEMS.register("dale_spear",
            () -> new LOTRItemSpear(LOTRMaterial.DALE));
    public static final RegistryObject<Item> BLADORTHIN_SPEAR = LOTRItems.ITEMS.register("bladorthin_spear",
            () -> new LOTRItemSpear(LOTRMaterial.BLADORTHIN));
    public static final RegistryObject<Item> DALE_BOW = LOTRItems.ITEMS.register("dale_bow",
            () -> new LOTRItemBow(LOTRMaterial.DALE, 1.25));
    public static final RegistryObject<Item> GUNDABAD_URUK_SPEAR = LOTRItems.ITEMS.register("gundabad_uruk_spear",
            () -> new LOTRItemSpear(LOTRMaterial.GUNDABAD_URUK));
    public static final RegistryObject<Item> GUNDABAD_URUK_BOW = LOTRItems.ITEMS.register("gundabad_uruk_bow",
            () -> new LOTRItemBow(LOTRMaterial.GUNDABAD_URUK, 1.2));
    public static final RegistryObject<Item> LOSSARNACH_THROWING_AXE = LOTRItems.ITEMS.register("lossarnach_throwing_axe",
            () -> new LOTRItemThrowingAxe(LOTRMaterial.LOSSARNACH));
    public static final RegistryObject<Item> BLACKROOT_BOW = LOTRItems.ITEMS.register("blackroot_bow",
            () -> new LOTRItemBow(LOTRMaterial.BLACKROOT));
    public static final RegistryObject<Item> STONE_SPEAR = LOTRItems.ITEMS.register("stone_spear",
            () -> new LOTRItemSpear(LOTRMaterial.STONE));
    public static final RegistryObject<Item> RANGER_BOW = LOTRItems.ITEMS.register("ranger_bow",
            () -> new LOTRItemBow(LOTRMaterial.RANGER));
    public static final RegistryObject<Item> RHUN_SPEAR = LOTRItems.ITEMS.register("rhun_spear",
            () -> new LOTRItemSpear(LOTRMaterial.RHUN));
    public static final RegistryObject<Item> RHUN_BOW = LOTRItems.ITEMS.register("rhun_bow",
            () -> new LOTRItemBow(LOTRMaterial.RHUN));
    public static final RegistryObject<Item> DORWINION_ELF_BOW = LOTRItems.ITEMS.register("dorwinion_elf_bow",
            () -> new LOTRItemBow(LOTRMaterial.DORWINION_ELF, 1.2));
    public static final RegistryObject<Item> RIVENDELL_SPEAR = LOTRItems.ITEMS.register("rivendell_spear",
            () -> new LOTRItemSpear(LOTRMaterial.RIVENDELL));
    public static final RegistryObject<Item> ARNOR_SPEAR = LOTRItems.ITEMS.register("arnor_spear",
            () -> new LOTRItemSpear(LOTRMaterial.ARNOR));
    public static final RegistryObject<Item> RIVENDELL_BOW = LOTRItems.ITEMS.register("rivendell_bow",
            () -> new LOTRItemBow(LOTRMaterial.RIVENDELL, 1.25));
    public static final RegistryObject<Item> CROSSBOW_BOLT_POISONED = LOTRItems.ITEMS.register("crossbow_bolt_poisoned",
            () -> new LOTRItemCrossbowBolt());
    public static final RegistryObject<Item> CORSAIR_SPEAR = LOTRItems.ITEMS.register("corsair_spear",
            () -> new LOTRItemSpear(LOTRMaterial.CORSAIR));
    public static final RegistryObject<Item> HARAD_SPEAR = LOTRItems.ITEMS.register("harad_spear",
            () -> new LOTRItemSpear(LOTRMaterial.NEAR_HARAD));
    public static final RegistryObject<Item> BLACK_NUMENOREAN_SPEAR = LOTRItems.ITEMS.register("black_numenorean_spear",
            () -> new LOTRItemSpear(LOTRMaterial.BLACK_NUMENOREAN));
}
