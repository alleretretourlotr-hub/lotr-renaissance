package fr.alleretretour.lotr.hire;

import fr.alleretretour.lotr.entity.npc.LOTREntityNPC;
import fr.alleretretour.lotr.fac.LOTRFaction;
import fr.alleretretour.lotr.init.LOTREntities;
import fr.alleretretour.lotr.init.LOTRItemsMountArmor;
import net.minecraft.entity.EntityType;
import net.minecraft.item.Item;
import net.minecraftforge.fml.RegistryObject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * PORT FIDELE de LOTRUnitTradeEntries (Legacy) : couts et alignements exacts.
 * Les unites a monture et les porte-bannieres du Legacy seront ajoutes a
 * leurs rosters quand ces entites seront portees (TODO lots suivants) ;
 * idem pour les civils elfes (GaladhrimElf, HighElf, RivendellElf).
 */
public final class LOTRHireRosters {

    /** PORT de LOTRUnitTradeEntry.PledgeType. */
    public enum PledgeType {
        NONE, ANY_DWARF, ANY_ELF, EXCLUSIVE;

        public boolean isMet(LOTRFaction pledged, LOTRFaction recruiterFaction) {
            switch (this) {
                case ANY_DWARF:
                    return pledged == LOTRFaction.DURINS_FOLK
                            || pledged == LOTRFaction.BLUE_MOUNTAINS;
                case ANY_ELF:
                    return pledged == LOTRFaction.HIGH_ELF
                            || pledged == LOTRFaction.WOOD_ELF
                            || pledged == LOTRFaction.LOTHLORIEN;
                case EXCLUSIVE:
                    return pledged == recruiterFaction;
                default:
                    return true;
            }
        }

        public String requirementText() {
            switch (this) {
                case ANY_DWARF:
                    return "Reserve aux jures d'une faction naine";
                case ANY_ELF:
                    return "Reserve aux jures d'une faction elfique";
                case EXCLUSIVE:
                    return "Reserve aux jures de cette faction";
                default:
                    return null;
            }
        }
    }

    public static final class Entry {
        public final RegistryObject<? extends EntityType<? extends LOTREntityNPC>> type;
        public final int initialCost;
        public final int minAlignment;
        public final PledgeType pledgeType;

        public Entry(RegistryObject<? extends EntityType<? extends LOTREntityNPC>> type,
                     int initialCost, int minAlignment) {
            this(type, initialCost, minAlignment, PledgeType.NONE);
        }

        /** "horse", "boar" ou null : monture de l'unite (Legacy XxxYyy_Horse). */
        @javax.annotation.Nullable
        public String mount;

        public Entry(RegistryObject<? extends EntityType<? extends LOTREntityNPC>> type,
                     int initialCost, int minAlignment, PledgeType pledgeType) {
            this.type = type;
            this.initialCost = initialCost;
            this.minAlignment = minAlignment;
            this.pledgeType = pledgeType;
        }

        /** Armure equipee a la monture (PORT de setMountArmor). */
        @javax.annotation.Nullable
        public RegistryObject<Item> mountArmor;

        public Entry mounted(String mountType) {
            this.mount = mountType;
            return this;
        }

        public Entry mountArmor(RegistryObject<Item> armor) {
            this.mountArmor = armor;
            return this;
        }
    }

    private LOTRHireRosters() {
    }

    // Legacy : GONDORIAN_CAPTAIN, base 200
    // TODO : GondorSoldier_Horse 50/150, GondorBannerBearer 50/200
    public static final int GONDOR_CAPTAIN_BASE = 200;

    public static List<Entry> gondorCaptain() {
        return new ArrayList<>(Arrays.asList(
                new Entry(LOTREntities.GONDOR_LEVYMAN, 20, 0),
                new Entry(LOTREntities.GONDOR_SOLDIER, 30, 50),
                new Entry(LOTREntities.GONDOR_ARCHER, 50, 100),
                new Entry(LOTREntities.GONDOR_SOLDIER, 50, 150).mounted("horse").mountArmor(LOTRItemsMountArmor.HORSE_ARMOR_GONDOR),
                new Entry(LOTREntities.GONDOR_TOWER_GUARD, 50, 250, PledgeType.EXCLUSIVE),
                new Entry(LOTREntities.GONDOR_BANNER_BEARER, 50, 200)));
    }

    // Legacy : ROHAN_MARSHAL, base 150
    // TODO : Rohirrim_Horse 50/100, RohirrimArcher_Horse 70/150, RohanBannerBearer 50/150
    public static final int ROHAN_MARSHAL_BASE = 150;

    public static List<Entry> rohirrimMarshal() {
        return new ArrayList<>(Arrays.asList(
                new Entry(LOTREntities.ROHIRRIM_WARRIOR, 30, 0),
                new Entry(LOTREntities.ROHIRRIM_ARCHER, 50, 50),
                new Entry(LOTREntities.ROHIRRIM_WARRIOR, 50, 100).mounted("horse").mountArmor(LOTRItemsMountArmor.HORSE_ARMOR_ROHAN),
                new Entry(LOTREntities.ROHIRRIM_ARCHER, 70, 150).mounted("horse").mountArmor(LOTRItemsMountArmor.HORSE_ARMOR_ROHAN),
                new Entry(LOTREntities.ROHAN_BANNER_BEARER, 50, 150)));
    }

    // Legacy : GALADHRIM_ELF_LORD, base 300
    // TODO : GaladhrimElf (civil) 30/0, GaladhrimWarrior_Horse 70/200, banniere
    public static final int GALADHRIM_LORD_BASE = 300;

    public static List<Entry> galadhrimLord() {
        return new ArrayList<>(Arrays.asList(
                new Entry(LOTREntities.GALADHRIM_ELF, 30, 0),
                new Entry(LOTREntities.GALADHRIM_WARDEN, 40, 50, PledgeType.ANY_ELF),
                new Entry(LOTREntities.GALADHRIM_WARRIOR, 50, 100, PledgeType.ANY_ELF),
                new Entry(LOTREntities.GALADHRIM_WARRIOR, 70, 200, PledgeType.ANY_ELF).mounted("horse").mountArmor(LOTRItemsMountArmor.HORSE_ARMOR_GALADHRIM),
                new Entry(LOTREntities.GALADHRIM_BANNER_BEARER, 70, 250, PledgeType.ANY_ELF)));
    }

    // Legacy : HIGH_ELF_LORD, base 300 - TODO : HighElf (civil) 30/0, monture, banniere
    public static final int HIGH_ELF_LORD_BASE = 300;

    public static List<Entry> highElfLord() {
        return new ArrayList<>(Arrays.asList(
                new Entry(LOTREntities.HIGH_ELF, 30, 0),
                new Entry(LOTREntities.HIGH_ELF_WARRIOR, 50, 100, PledgeType.ANY_ELF),
                new Entry(LOTREntities.HIGH_ELF_WARRIOR, 70, 200, PledgeType.ANY_ELF).mounted("horse").mountArmor(LOTRItemsMountArmor.HORSE_ARMOR_HIGH_ELVEN),
                new Entry(LOTREntities.HIGH_ELF_BANNER_BEARER, 70, 250, PledgeType.ANY_ELF)));
    }

    // Legacy : RIVENDELL_LORD, base 300 - TODO : RivendellElf (civil) 30/0, monture, banniere
    public static final int RIVENDELL_LORD_BASE = 300;

    public static List<Entry> rivendellLord() {
        return new ArrayList<>(Arrays.asList(
                new Entry(LOTREntities.RIVENDELL_ELF, 30, 0),
                new Entry(LOTREntities.RIVENDELL_WARRIOR, 50, 100, PledgeType.ANY_ELF),
                new Entry(LOTREntities.RIVENDELL_WARRIOR, 70, 200, PledgeType.ANY_ELF).mounted("horse").mountArmor(LOTRItemsMountArmor.HORSE_ARMOR_RIVENDELL),
                new Entry(LOTREntities.RIVENDELL_BANNER_BEARER, 70, 250, PledgeType.ANY_ELF)));
    }

    // Legacy : DWARF_COMMANDER, base 200
    // TODO : montures sangliers 50/150 et 70/200, DwarfBannerBearer 50/200
    public static final int DWARF_COMMANDER_BASE = 200;

    public static List<Entry> dwarfCommander() {
        return new ArrayList<>(Arrays.asList(
                new Entry(LOTREntities.DWARF, 20, 0),
                new Entry(LOTREntities.DWARF_WARRIOR, 30, 50, PledgeType.ANY_DWARF),
                new Entry(LOTREntities.DWARF_AXE_THROWER, 50, 100, PledgeType.ANY_DWARF),
                new Entry(LOTREntities.DWARF_WARRIOR, 50, 150, PledgeType.ANY_DWARF).mounted("boar").mountArmor(LOTRItemsMountArmor.BOAR_ARMOR_DWARVEN),
                new Entry(LOTREntities.DWARF_AXE_THROWER, 70, 200, PledgeType.ANY_DWARF).mounted("boar").mountArmor(LOTRItemsMountArmor.BOAR_ARMOR_DWARVEN),
                new Entry(LOTREntities.DWARF_BANNER_BEARER, 50, 200, PledgeType.ANY_DWARF)));
    }

    // Legacy : RANGER_NORTH_CAPTAIN, base 300
    // TODO : RangerNorth_Horse 70/100, RangerNorthBannerBearer 70/150
    public static final int RANGER_CAPTAIN_BASE = 300;

    public static List<Entry> rangerCaptain() {
        return new ArrayList<>(Arrays.asList(
                new Entry(LOTREntities.RANGER_NORTH, 50, 0),
                new Entry(LOTREntities.RANGER_NORTH, 70, 100).mounted("horse"),
                new Entry(LOTREntities.RANGER_NORTH_BANNER_BEARER, 70, 150)));
    }

    // Legacy : ANGMAR_HILLMAN_CHIEFTAIN, base 100
    public static final int ANGMARHILLMANCHIEFTAIN_BASE = 100;

    public static List<Entry> angmarHillmanChieftain() {
        return new ArrayList<>(Arrays.asList(
                new Entry(LOTREntities.ANGMAR_HILLMAN, 15, 0),
                new Entry(LOTREntities.ANGMAR_HILLMAN_WARRIOR, 30, 50),
                new Entry(LOTREntities.ANGMAR_HILLMAN_AXE_THROWER, 50, 100),
                new Entry(LOTREntities.ANGMAR_HILLMAN, 35, 100).mounted("warg"),
                new Entry(LOTREntities.ANGMAR_HILLMAN_WARRIOR, 50, 150).mounted("warg"),
                new Entry(LOTREntities.ANGMAR_HILLMAN_AXE_THROWER, 70, 200).mounted("warg"),
                new Entry(LOTREntities.ANGMAR_HILLMAN_BANNER_BEARER, 50, 200)));
    }

    // Legacy : DOL_GULDUR_CAPTAIN, base 150
    public static final int DOLGULDURCHIEFTAIN_BASE = 150;

    public static List<Entry> dolGuldurChieftain() {
        return new ArrayList<>(Arrays.asList(
                new Entry(LOTREntities.DOL_GULDUR_ORC, 20, 0),
                new Entry(LOTREntities.DOL_GULDUR_ORC_ARCHER, 40, 50),
                new Entry(LOTREntities.MIRKWOOD_SPIDER, 20, 0),
                new Entry(LOTREntities.DOL_GULDUR_ORC, 40, 100).mounted("spider"),
                new Entry(LOTREntities.DOL_GULDUR_ORC_ARCHER, 60, 150).mounted("spider"),
                new Entry(LOTREntities.MIRK_TROLL, 100, 350),
                new Entry(LOTREntities.DOL_GULDUR_BANNER_BEARER, 40, 0)));
    }

    // Legacy : DUNLENDING_WARLORD, base 100
    public static final int DUNLENDINGWARLORD_BASE = 100;

    public static List<Entry> dunlendingWarlord() {
        return new ArrayList<>(Arrays.asList(
                new Entry(LOTREntities.DUNLENDING, 15, 0),
                new Entry(LOTREntities.DUNLENDING_WARRIOR, 30, 50),
                new Entry(LOTREntities.DUNLENDING_ARCHER, 50, 100),
                new Entry(LOTREntities.DUNLENDING_AXE_THROWER, 50, 100),
                new Entry(LOTREntities.DUNLENDING_BERSERKER, 50, 200),
                new Entry(LOTREntities.DUNLENDING_BANNER_BEARER, 50, 200)));
    }

    // Legacy : EASTERLING_WARLORD, base 150
    public static final int EASTERLINGWARLORD_BASE = 150;

    public static List<Entry> easterlingWarlord() {
        return new ArrayList<>(Arrays.asList(
                new Entry(LOTREntities.EASTERLING_LEVYMAN, 20, 0),
                new Entry(LOTREntities.EASTERLING_WARRIOR, 30, 50),
                new Entry(LOTREntities.EASTERLING_ARCHER, 50, 100),
                new Entry(LOTREntities.EASTERLING_GOLD_WARRIOR, 50, 200),
                new Entry(LOTREntities.EASTERLING_WARRIOR, 50, 150).mounted("horse"),
                new Entry(LOTREntities.EASTERLING_ARCHER, 70, 200).mounted("horse"),
                new Entry(LOTREntities.EASTERLING_GOLD_WARRIOR, 70, 300).mounted("horse"),
                new Entry(LOTREntities.EASTERLING_FIRE_THROWER, 60, 150),
                new Entry(LOTREntities.EASTERLING_BANNER_BEARER, 50, 200)));
    }

    // Legacy : HALF_TROLL_WARLORD, base 200
    public static final int HALFTROLLWARLORD_BASE = 200;

    public static List<Entry> halfTrollWarlord() {
        return new ArrayList<>(Arrays.asList(
                new Entry(LOTREntities.HALF_TROLL, 30, 0),
                new Entry(LOTREntities.HALF_TROLL_WARRIOR, 50, 100),
                new Entry(LOTREntities.HALF_TROLL_WARRIOR, 70, 200).mounted("rhino"),
                new Entry(LOTREntities.HALF_TROLL_BANNER_BEARER, 70, 150)));
    }

    // Legacy : HARNEDOR_WARLORD, base 150
    public static final int HARNEDORWARLORD_BASE = 150;

    public static List<Entry> harnedorWarlord() {
        return new ArrayList<>(Arrays.asList(
                new Entry(LOTREntities.HARNEDOR_WARRIOR, 20, 0),
                new Entry(LOTREntities.HARNEDOR_ARCHER, 40, 50),
                new Entry(LOTREntities.HARNEDOR_WARRIOR, 40, 100).mounted("horse"),
                new Entry(LOTREntities.HARNEDOR_ARCHER, 60, 150).mounted("horse"),
                new Entry(LOTREntities.HARNEDOR_BANNER_BEARER, 40, 150)));
    }

    // Legacy : NEAR_HARADRIM_WARLORD, base 150
    public static final int NEARHARADRIMWARLORD_BASE = 150;

    public static List<Entry> nearHaradrimWarlord() {
        return new ArrayList<>(Arrays.asList(
                new Entry(LOTREntities.NEAR_HARADRIM_WARRIOR, 30, 0),
                new Entry(LOTREntities.NEAR_HARADRIM_ARCHER, 50, 50),
                new Entry(LOTREntities.SOUTHRON_CHAMPION, 60, 100).mounted("horse").mountArmor(LOTRItemsMountArmor.HORSE_ARMOR_NEAR_HARAD),
                new Entry(LOTREntities.NEAR_HARAD_BANNER_BEARER, 50, 150)));
    }

    // Legacy : MOREDAIN_CHIEFTAIN, base 150
    public static final int MOREDAINCHIEFTAIN_BASE = 150;

    public static List<Entry> moredainChieftain() {
        return new ArrayList<>(Arrays.asList(
                new Entry(LOTREntities.MOREDAIN_WARRIOR, 20, 0),
                new Entry(LOTREntities.MOREDAIN_WARRIOR, 40, 100).mounted("zebra"),
                new Entry(LOTREntities.MOREDAIN_BANNER_BEARER, 40, 150)));
    }

    // Legacy : TAUREDAIN_CHIEFTAIN, base 200
    public static final int TAUREDAINCHIEFTAIN_BASE = 200;

    public static List<Entry> tauredainChieftain() {
        return new ArrayList<>(Arrays.asList(
                new Entry(LOTREntities.TAUREDAIN_WARRIOR, 30, 0),
                new Entry(LOTREntities.TAUREDAIN_BLOWGUNNER, 50, 50),
                new Entry(LOTREntities.TAUREDAIN_BANNER_BEARER, 50, 150)));
    }

    // Legacy : WOOD_ELF_CAPTAIN, base 250
    public static final int WOODELFCAPTAIN_BASE = 250;

    public static List<Entry> woodElfCaptain() {
        return new ArrayList<>(Arrays.asList(
                new Entry(LOTREntities.WOOD_ELF, 30, 0),
                new Entry(LOTREntities.WOOD_ELF_SCOUT, 40, 50, PledgeType.ANY_ELF),
                new Entry(LOTREntities.WOOD_ELF_WARRIOR, 50, 100, PledgeType.ANY_ELF),
                new Entry(LOTREntities.WOOD_ELF_WARRIOR, 70, 200, PledgeType.ANY_ELF).mounted("elk").mountArmor(LOTRItemsMountArmor.ELK_ARMOR_WOOD_ELVEN),
                new Entry(LOTREntities.WOOD_ELF_BANNER_BEARER, 70, 250, PledgeType.ANY_ELF)));
    }

    // Legacy : DALE_CAPTAIN, base 100
    public static final int DALECAPTAIN_BASE = 100;

    public static List<Entry> daleCaptain() {
        return new ArrayList<>(Arrays.asList(
                new Entry(LOTREntities.DALE_LEVYMAN, 20, 0),
                new Entry(LOTREntities.DALE_SOLDIER, 30, 50),
                new Entry(LOTREntities.DALE_ARCHER, 50, 100),
                new Entry(LOTREntities.DALE_SOLDIER, 50, 150).mounted("horse").mountArmor(LOTRItemsMountArmor.HORSE_ARMOR_DALE),
                new Entry(LOTREntities.DALE_BANNER_BEARER, 50, 200),
                new Entry(LOTREntities.ESGAROTH_BANNER_BEARER, 50, 200)));
    }

    // Legacy : BLUE_DWARF_COMMANDER, base 200
    public static final int BLUEDWARFCOMMANDER_BASE = 200;

    public static List<Entry> blueDwarfCommander() {
        return new ArrayList<>(Arrays.asList(
                new Entry(LOTREntities.BLUE_DWARF_WARRIOR, 20, 0),
                new Entry(LOTREntities.BLUE_DWARF_WARRIOR, 30, 50, PledgeType.ANY_DWARF),
                new Entry(LOTREntities.BLUE_DWARF_AXE_THROWER, 50, 100, PledgeType.ANY_DWARF),
                new Entry(LOTREntities.BLUE_DWARF_WARRIOR, 50, 150, PledgeType.ANY_DWARF).mounted("boar").mountArmor(LOTRItemsMountArmor.BOAR_ARMOR_BLUE_DWARVEN),
                new Entry(LOTREntities.BLUE_DWARF_AXE_THROWER, 70, 200, PledgeType.ANY_DWARF).mounted("boar").mountArmor(LOTRItemsMountArmor.BOAR_ARMOR_BLUE_DWARVEN),
                new Entry(LOTREntities.BLUE_DWARF_BANNER_BEARER, 50, 200, PledgeType.ANY_DWARF)));
    }

    // Legacy : DORWINION_CAPTAIN, base 150
    public static final int DORWINIONCAPTAIN_BASE = 150;

    public static List<Entry> dorwinionCaptain() {
        return new ArrayList<>(Arrays.asList(
                new Entry(LOTREntities.DORWINION_GUARD, 40, 0),
                new Entry(LOTREntities.DORWINION_CROSSBOWER, 60, 50),
                new Entry(LOTREntities.DORWINION_BANNER_BEARER, 60, 150)));
    }

    // Legacy : UMBAR_CAPTAIN, base 150
    public static final int UMBARCAPTAIN_BASE = 150;

    public static List<Entry> umbarCaptain() {
        return new ArrayList<>(Arrays.asList(
                new Entry(LOTREntities.UMBAR_WARRIOR, 30, 0),
                new Entry(LOTREntities.UMBAR_ARCHER, 50, 50),
                new Entry(LOTREntities.UMBAR_WARRIOR, 50, 100).mounted("horse").mountArmor(LOTRItemsMountArmor.HORSE_ARMOR_UMBAR),
                new Entry(LOTREntities.UMBAR_BANNER_BEARER, 50, 150)));
    }

    // Legacy : CORSAIR_CAPTAIN, base 150
    public static final int CORSAIRCAPTAIN_BASE = 150;

    public static List<Entry> corsairCaptain() {
        return new ArrayList<>(Arrays.asList(
                new Entry(LOTREntities.CORSAIR, 20, 0)));
    }

    // Legacy : BREE_CAPTAIN, base 100
    public static final int BREECAPTAIN_BASE = 100;

    public static List<Entry> breeCaptain() {
        return new ArrayList<>(Arrays.asList(
                new Entry(LOTREntities.BREE_GUARD, 20, 0),
                new Entry(LOTREntities.BREE_BANNER_BEARER, 40, 150)));
    }

    // Legacy : RANGER_ITHILIEN_CAPTAIN, base 300
    public static final int RANGERITHILIENCAPTAIN_BASE = 300;

    public static List<Entry> rangerIthilienCaptain() {
        return new ArrayList<>(Arrays.asList(
                new Entry(LOTREntities.RANGER_ITHILIEN, 50, 0),
                new Entry(LOTREntities.RANGER_ITHILIEN_BANNER_BEARER, 70, 150)));
    }

    // Legacy : MORDOR_ORC_MERCENARY_CAPTAIN, base 150
    public static final int MORDORMERCENARYCAPTAIN_BASE = 150;

    public static List<Entry> mordorMercenaryCaptain() {
        return new ArrayList<>(Arrays.asList(
                new Entry(LOTREntities.MORDOR_ORC, 20, 0),
                new Entry(LOTREntities.MORDOR_ORC_ARCHER, 40, 50),
                new Entry(LOTREntities.MORDOR_ORC_BOMBARDIER, 50, 100),
                new Entry(LOTREntities.MORDOR_WARG, 20, 0),
                new Entry(LOTREntities.MORDOR_ORC, 40, 100).mounted("warg"),
                new Entry(LOTREntities.MORDOR_ORC_ARCHER, 60, 150).mounted("warg"),
                new Entry(LOTREntities.MORDOR_WARG_BOMBARDIER, 50, 250),
                new Entry(LOTREntities.OLOG_HAI, 120, 350),
                new Entry(LOTREntities.MORDOR_BANNER_BEARER, 40, 150),
                new Entry(LOTREntities.MINAS_MORGUL_BANNER_BEARER, 40, 150)));
    }

    // Legacy : URUK_HAI_MERCENARY_CAPTAIN, base 150
    public static final int URUKHAIMERCENARYCAPTAIN_BASE = 150;

    public static List<Entry> urukHaiMercenaryCaptain() {
        return new ArrayList<>(Arrays.asList(
                new Entry(LOTREntities.ISENGARD_SNAGA, 20, 0),
                new Entry(LOTREntities.ISENGARD_SNAGA_ARCHER, 40, 50),
                new Entry(LOTREntities.URUK_HAI, 40, 0),
                new Entry(LOTREntities.URUK_HAI_CROSSBOWER, 60, 50),
                new Entry(LOTREntities.URUK_HAI_SAPPER, 70, 100),
                new Entry(LOTREntities.URUK_HAI_BERSERKER, 60, 150),
                new Entry(LOTREntities.URUK_WARG, 20, 0),
                new Entry(LOTREntities.ISENGARD_SNAGA, 40, 100).mounted("warg"),
                new Entry(LOTREntities.ISENGARD_SNAGA_ARCHER, 60, 150).mounted("warg"),
                new Entry(LOTREntities.URUK_WARG_BOMBARDIER, 50, 250),
                new Entry(LOTREntities.URUK_HAI_BANNER_BEARER, 60, 150)));
    }

    // Legacy : GUNDABAD_ORC_MERCENARY_CAPTAIN, base 100
    public static final int GUNDABADMERCENARYCAPTAIN_BASE = 100;

    public static List<Entry> gundabadMercenaryCaptain() {
        return new ArrayList<>(Arrays.asList(
                new Entry(LOTREntities.GUNDABAD_ORC, 15, 0),
                new Entry(LOTREntities.GUNDABAD_ORC_ARCHER, 35, 50),
                new Entry(LOTREntities.GUNDABAD_WARG, 20, 50),
                new Entry(LOTREntities.GUNDABAD_ORC, 35, 100).mounted("warg"),
                new Entry(LOTREntities.GUNDABAD_ORC_ARCHER, 55, 150).mounted("warg"),
                new Entry(LOTREntities.GUNDABAD_URUK, 40, 250),
                new Entry(LOTREntities.GUNDABAD_URUK_ARCHER, 60, 300),
                new Entry(LOTREntities.GUNDABAD_BANNER_BEARER, 35, 150)));
    }

    // Legacy : ANGMAR_ORC_MERCENARY_CAPTAIN, base 150
    public static final int ANGMARMERCENARYCAPTAIN_BASE = 150;

    public static List<Entry> angmarMercenaryCaptain() {
        return new ArrayList<>(Arrays.asList(
                new Entry(LOTREntities.ANGMAR_ORC, 20, 0),
                new Entry(LOTREntities.ANGMAR_ORC_ARCHER, 40, 50),
                new Entry(LOTREntities.ANGMAR_ORC_BOMBARDIER, 50, 100),
                new Entry(LOTREntities.ANGMAR_WARG, 20, 0),
                new Entry(LOTREntities.ANGMAR_ORC, 40, 100).mounted("warg"),
                new Entry(LOTREntities.ANGMAR_ORC_ARCHER, 60, 150).mounted("warg"),
                new Entry(LOTREntities.ANGMAR_WARG_BOMBARDIER, 50, 250),
                new Entry(LOTREntities.TROLL, 100, 250),
                new Entry(LOTREntities.MOUNTAIN_TROLL, 120, 350),
                new Entry(LOTREntities.ANGMAR_BANNER_BEARER, 40, 150)));
    }

}
