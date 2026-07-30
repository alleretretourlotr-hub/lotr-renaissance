package fr.alleretretour.lotr.world.map;

import java.util.ArrayList;
import java.util.List;

/**
 * PORT de lotr.common.world.map.LOTRRoads : 57 routes de la Terre du
 * Milieu, definies par leurs points de passage successifs (coordonnees carte).
 * Le trace exact du Legacy est conserve, y compris les points intermediaires.
 */
public final class LOTRRoads {

    public static final List<Road> ROADS = new ArrayList<>();

    public static final class Point {
        public final double x;
        public final double z;

        Point(double x, double z) {
            this.x = x;
            this.z = z;
        }
    }

    public static final class Road {
        public final String name;
        public final List<Point> points;

        Road(String name, List<Point> points) {
            this.name = name;
            this.points = points;
        }
    }

    private LOTRRoads() {
    }

    private static Point wp(LOTRWaypoint w) {
        return new Point(w.getMapX(), w.getMapZ());
    }

    private static Point pt(double x, double z) {
        return new Point(x, z);
    }

    private static void road(String name, Point... points) {
        List<Point> list = new ArrayList<>();
        java.util.Collections.addAll(list, points);
        ROADS.add(new Road(name, list));
    }

    static {
        road("EredLuin", wp(LOTRWaypoint.NOGROD), wp(LOTRWaypoint.BELEGOST));
        road("NogrodForlond", wp(LOTRWaypoint.NOGROD), wp(LOTRWaypoint.FORLOND));
        road("NogrodMithlond", wp(LOTRWaypoint.NOGROD), pt(654.0, 650.0), wp(LOTRWaypoint.MITHLOND_NORTH));
        road("Mithlond", wp(LOTRWaypoint.HARLOND), pt(658.0, 755.0), wp(LOTRWaypoint.MITHLOND_SOUTH), pt(690.0, 711.0), pt(681.0, 705.0), wp(LOTRWaypoint.MITHLOND_NORTH), pt(644.0, 733.0), pt(603.0, 733.0), pt(554.0, 715.0), wp(LOTRWaypoint.FORLOND));
        road("WestEast", wp(LOTRWaypoint.MITHLOND_SOUTH), wp(LOTRWaypoint.TOWER_HILLS), wp(LOTRWaypoint.GREENHOLM), wp(LOTRWaypoint.MICHEL_DELVING), wp(LOTRWaypoint.WAYMEET), wp(LOTRWaypoint.BYWATER), wp(LOTRWaypoint.FROGMORTON), wp(LOTRWaypoint.WHITFURROWS), wp(LOTRWaypoint.BRANDYWINE_BRIDGE), pt(870.0, 718.0), pt(902.0, 729.0), wp(LOTRWaypoint.BREE));
        road("WestEast", wp(LOTRWaypoint.FORSAKEN_INN), wp(LOTRWaypoint.LAST_BRIDGE), pt(1132.0, 723.0), pt(1178.0, 704.0), wp(LOTRWaypoint.HIGH_PASS), wp(LOTRWaypoint.OLD_FORD), wp(LOTRWaypoint.RIVER_GATE), wp(LOTRWaypoint.DALE_CROSSROADS), wp(LOTRWaypoint.REDWATER_FORD), pt(1785.0, 775.0), wp(LOTRWaypoint.RHUN_NORTH_FORD), wp(LOTRWaypoint.RHUN_NORTHEAST), wp(LOTRWaypoint.RHUN_ROAD_WAY), wp(LOTRWaypoint.BARAZ_DUM));
        road("BywaterRoad", wp(LOTRWaypoint.BYWATER), wp(LOTRWaypoint.HOBBITON));
        road("Overhill", wp(LOTRWaypoint.HOBBITON), wp(LOTRWaypoint.OVERHILL));
        road("BucklandRoad", wp(LOTRWaypoint.HAY_GATE), wp(LOTRWaypoint.BUCKLEBURY), wp(LOTRWaypoint.HAYSEND));
        road("Chetroad", wp(LOTRWaypoint.STADDLE), wp(LOTRWaypoint.COMBE), wp(LOTRWaypoint.ARCHET));
        road("ElfPath", wp(LOTRWaypoint.FOREST_GATE), wp(LOTRWaypoint.ENCHANTED_RIVER), wp(LOTRWaypoint.THRANDUIL_HALLS));
        road("EreborRoad", wp(LOTRWaypoint.LONG_LAKE), wp(LOTRWaypoint.DALE_CITY), wp(LOTRWaypoint.EREBOR));
        road("DalePortRoad", wp(LOTRWaypoint.DALE_CITY), wp(LOTRWaypoint.DALE_CROSSROADS), wp(LOTRWaypoint.DALE_PORT));
        road("DaleSouthRoad", wp(LOTRWaypoint.EAST_RHOVANION_ROAD), wp(LOTRWaypoint.OLD_RHOVANION), wp(LOTRWaypoint.RUNNING_FORD), wp(LOTRWaypoint.DALE_CROSSROADS), wp(LOTRWaypoint.WEST_PEAK));
        road("IronHills", wp(LOTRWaypoint.WEST_PEAK), pt(1652.0, 621.0), wp(LOTRWaypoint.EAST_PEAK));
        road("DorwinionSouthRoad", wp(LOTRWaypoint.DALE_PORT), wp(LOTRWaypoint.DORWINION_CROSSROADS), wp(LOTRWaypoint.DORWINION_COURT), wp(LOTRWaypoint.DORWINION_FORD));
        road("DorwinionEastRoad", wp(LOTRWaypoint.OLD_RHOVANION), wp(LOTRWaypoint.DORWINION_CROSSROADS), wp(LOTRWaypoint.DORWINION_PORT));
        road("RhunRoad", wp(LOTRWaypoint.DORWINION_FORD), wp(LOTRWaypoint.BORDER_TOWN), wp(LOTRWaypoint.RHUN_SEA_CITY), wp(LOTRWaypoint.RHUN_CAPITAL), pt(1888.0, 958.0), wp(LOTRWaypoint.RHUN_NORTH_CITY), wp(LOTRWaypoint.BAZYLAN), wp(LOTRWaypoint.RHUN_NORTHEAST));
        road("RhunEastRoad", wp(LOTRWaypoint.RHUN_NORTH_CITY), wp(LOTRWaypoint.RHUN_EAST_TOWN), wp(LOTRWaypoint.RHUN_EAST_CITY));
        road("Nobottle", wp(LOTRWaypoint.TIGHFIELD), wp(LOTRWaypoint.LITTLE_DELVING), wp(LOTRWaypoint.NOBOTTLE), wp(LOTRWaypoint.NEEDLEHOLE));
        road("Oatbarton", wp(LOTRWaypoint.OATBARTON), wp(LOTRWaypoint.FROGMORTON));
        road("Stock", wp(LOTRWaypoint.TUCKBOROUGH), wp(LOTRWaypoint.STOCK));
        road("Deephallow", wp(LOTRWaypoint.SCARY), wp(LOTRWaypoint.WHITFURROWS), wp(LOTRWaypoint.STOCK), wp(LOTRWaypoint.DEEPHALLOW));
        road("Willowbottom", wp(LOTRWaypoint.WILLOWBOTTOM), wp(LOTRWaypoint.DEEPHALLOW));
        road("ArnorRoad", wp(LOTRWaypoint.ANNUMINAS), wp(LOTRWaypoint.FORNOST));
        road("Greenway", wp(LOTRWaypoint.FORNOST), wp(LOTRWaypoint.BREE), wp(LOTRWaypoint.GREENWAY_CROSSROADS));
        road("ElvenWay", wp(LOTRWaypoint.WEST_GATE), pt(1133.0, 867.0), pt(1124.0, 868.0), wp(LOTRWaypoint.OST_IN_EDHIL), pt(1073.0, 864.0), wp(LOTRWaypoint.OLD_ELF_WAY), pt(1002.0, 849.0), pt(992.0, 860.0), wp(LOTRWaypoint.THARBAD), pt(959.0, 889.0), pt(926.0, 913.0), pt(902.0, 942.0), wp(LOTRWaypoint.LOND_DAER));
        road("BruinenPath", wp(LOTRWaypoint.FORD_BRUINEN), wp(LOTRWaypoint.RIVENDELL));
        road("NimrodelRoad", wp(LOTRWaypoint.DIMRILL_DALE), wp(LOTRWaypoint.NIMRODEL));
        road("AnduinRoad", wp(LOTRWaypoint.MORANNON), pt(1428.0, 1066.0), wp(LOTRWaypoint.EAST_RHOVANION_ROAD), wp(LOTRWaypoint.ANDUIN_CROSSROADS), pt(1325.0, 820.0), pt(1318.0, 735.0), wp(LOTRWaypoint.FOREST_GATE));
        road("DolGuldurRoad", wp(LOTRWaypoint.ANDUIN_CROSSROADS), wp(LOTRWaypoint.DOL_GULDUR));
        road("Framsburg", wp(LOTRWaypoint.FOREST_GATE), pt(1278.0, 605.0), wp(LOTRWaypoint.FRAMSBURG), pt(1260.0, 565.0), wp(LOTRWaypoint.DAINS_HALLS));
        road("NorthSouth", wp(LOTRWaypoint.LITTLE_DELVING), wp(LOTRWaypoint.WAYMEET), wp(LOTRWaypoint.LONGBOTTOM), wp(LOTRWaypoint.SARN_FORD), wp(LOTRWaypoint.GREENWAY_CROSSROADS), wp(LOTRWaypoint.THARBAD), wp(LOTRWaypoint.ENEDWAITH_ROAD), wp(LOTRWaypoint.FORDS_OF_ISEN), wp(LOTRWaypoint.HELMS_CROSSROADS), wp(LOTRWaypoint.GRIMSLADE), wp(LOTRWaypoint.EDORAS), wp(LOTRWaypoint.ALDBURG), wp(LOTRWaypoint.MERING_STREAM), wp(LOTRWaypoint.AMON_DIN));
        road("TirithRoad", wp(LOTRWaypoint.AMON_DIN), wp(LOTRWaypoint.MINAS_TIRITH));
        road("OsgiliathRoad", wp(LOTRWaypoint.MINAS_TIRITH), wp(LOTRWaypoint.OSGILIATH_WEST));
        road("OsgiliathCrossing", wp(LOTRWaypoint.OSGILIATH_WEST), wp(LOTRWaypoint.OSGILIATH_EAST));
        road("OsgiliathMorgulRoad", wp(LOTRWaypoint.OSGILIATH_EAST), wp(LOTRWaypoint.CROSSROADS_ITHILIEN), wp(LOTRWaypoint.MINAS_MORGUL));
        road("GondorSouthRoad", wp(LOTRWaypoint.MINAS_TIRITH), wp(LOTRWaypoint.CROSSINGS_ERUI), pt(1408.0, 1291.0), wp(LOTRWaypoint.PELARGIR), wp(LOTRWaypoint.LINHIR), pt(1266.0, 1301.0), wp(LOTRWaypoint.ETHRING), wp(LOTRWaypoint.CALEMBEL), wp(LOTRWaypoint.TARLANG), wp(LOTRWaypoint.ERECH));
        road("IsengardRoad", wp(LOTRWaypoint.FORDS_OF_ISEN), wp(LOTRWaypoint.ISENGARD));
        road("HelmRoad", wp(LOTRWaypoint.HELMS_CROSSROADS), wp(LOTRWaypoint.HELMS_DEEP));
        road("WoldRoad", wp(LOTRWaypoint.EDORAS), wp(LOTRWaypoint.ENTWADE), pt(1260.0, 1060.0), wp(LOTRWaypoint.WOLD));
        road("DolAmroth", pt(1266.0, 1301.0), wp(LOTRWaypoint.TARNOST), wp(LOTRWaypoint.EDHELLOND), pt(1185.0, 1325.0), wp(LOTRWaypoint.DOL_AMROTH));
        road("Pelargir", wp(LOTRWaypoint.PELARGIR), pt(1394.0, 1352.0));
        road("Poros", pt(1397.0, 1355.0), wp(LOTRWaypoint.CROSSINGS_OF_POROS));
        road("CairAndros", wp(LOTRWaypoint.AMON_DIN), wp(LOTRWaypoint.CAIR_ANDROS), wp(LOTRWaypoint.NORTH_ITHILIEN));
        road("SauronRoad", wp(LOTRWaypoint.MINAS_MORGUL), wp(LOTRWaypoint.MOUNT_DOOM), wp(LOTRWaypoint.BARAD_DUR), wp(LOTRWaypoint.SEREGOST), pt(1742.0, 1209.0), pt(1809.0, 1172.0), wp(LOTRWaypoint.EASTERN_GUARD), wp(LOTRWaypoint.MORDOR_FORD), wp(LOTRWaypoint.RHUN_SOUTH_PASS), pt(1875.0, 1003.0), pt(1867.0, 996.0), wp(LOTRWaypoint.RHUN_CAPITAL));
        road("MorannonRoad", wp(LOTRWaypoint.MORANNON), wp(LOTRWaypoint.UDUN));
        road("MorannonRhunRoad", wp(LOTRWaypoint.MORANNON), pt(1520.0, 1130.0), pt(1658.0, 1140.0), pt(1780.0, 1115.0), wp(LOTRWaypoint.MORDOR_FORD), wp(LOTRWaypoint.RHUN_SOUTHEAST), wp(LOTRWaypoint.KHAND_NORTH_ROAD), wp(LOTRWaypoint.KHAND_FORD), wp(LOTRWaypoint.HARNEN_BLACK_TOWN), wp(LOTRWaypoint.CROSSINGS_OF_LITHNEN), wp(LOTRWaypoint.HARNEN_ROAD_TOWN), wp(LOTRWaypoint.HARNEN_RIVER_TOWN), wp(LOTRWaypoint.HARNEN_SEA_TOWN), wp(LOTRWaypoint.COAST_FORTRESS), wp(LOTRWaypoint.GATE_FUINUR), wp(LOTRWaypoint.UMBAR_CITY), wp(LOTRWaypoint.GATE_HERUMOR));
        road("GorgorothRoad", wp(LOTRWaypoint.UDUN), wp(LOTRWaypoint.CARACH_ANGREN), wp(LOTRWaypoint.BARAD_DUR), wp(LOTRWaypoint.THAURBAND));
        road("HaradRoad", wp(LOTRWaypoint.MORANNON), wp(LOTRWaypoint.NORTH_ITHILIEN), wp(LOTRWaypoint.CROSSROADS_ITHILIEN), wp(LOTRWaypoint.CROSSINGS_OF_POROS), pt(1429.0, 1394.0), pt(1408.0, 1432.0), pt(1428.0, 1470.0), pt(1435.0, 1526.0), wp(LOTRWaypoint.CROSSINGS_OF_HARAD), wp(LOTRWaypoint.HARNEN_ROAD_TOWN), wp(LOTRWaypoint.DESERT_TOWN));
        road("UmbarRoad", wp(LOTRWaypoint.UMBAR_CITY), wp(LOTRWaypoint.UMBAR_GATE), wp(LOTRWaypoint.AIN_AL_HARAD), wp(LOTRWaypoint.GARDENS_BERUTHIEL), wp(LOTRWaypoint.FERTILE_VALLEY), wp(LOTRWaypoint.SOUTH_DESERT_TOWN));
        road("GulfRoad", wp(LOTRWaypoint.TOWN_BONES), pt(1794.0, 2110.0), wp(LOTRWaypoint.GULF_FORD), wp(LOTRWaypoint.GULF_TRADE_TOWN), wp(LOTRWaypoint.GULF_CITY), wp(LOTRWaypoint.GULF_NORTH_TOWN), pt(1702.0, 1940.0), wp(LOTRWaypoint.GULF_OF_HARAD), pt(1775.0, 2002.0), wp(LOTRWaypoint.GULF_EAST_PORT));
        road("JungleNorthRoad", wp(LOTRWaypoint.JUNGLE_CITY_TRADE), wp(LOTRWaypoint.JUNGLE_CITY_OLD), wp(LOTRWaypoint.JUNGLE_CITY_NORTH));
        road("JungleMangroveRoad", wp(LOTRWaypoint.JUNGLE_CITY_NORTH), wp(LOTRWaypoint.JUNGLE_CITY_EAST), wp(LOTRWaypoint.HARADUIN_MOUTH));
        road("JungleDeepRoad", wp(LOTRWaypoint.JUNGLE_CITY_NORTH), wp(LOTRWaypoint.JUNGLE_CITY_CAPITAL), wp(LOTRWaypoint.JUNGLE_CITY_CAVES), wp(LOTRWaypoint.JUNGLE_CITY_DEEP));
        road("JungleWestEastRoad", wp(LOTRWaypoint.JUNGLE_CITY_OLD), wp(LOTRWaypoint.JUNGLE_CITY_STONE), wp(LOTRWaypoint.JUNGLE_CITY_CAPITAL), wp(LOTRWaypoint.JUNGLE_LAKES), wp(LOTRWaypoint.JUNGLE_CITY_WATCH));
        road("JungleLakeRoad", wp(LOTRWaypoint.JUNGLE_LAKES), wp(LOTRWaypoint.JUNGLE_CITY_EAST), wp(LOTRWaypoint.HARADUIN_BRIDGE), wp(LOTRWaypoint.OLD_JUNGLE_RUIN));
    }
}
