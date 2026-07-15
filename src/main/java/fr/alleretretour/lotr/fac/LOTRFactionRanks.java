package fr.alleretretour.lotr.fac;

import java.util.ArrayList;
import java.util.EnumMap;
import java.util.List;
import java.util.Map;

/**
 * PORT GENERE des rangs et regions de lotr.common.fac.LOTRFaction.
 * Rangs : seuils d'alignement -> cle de traduction lotr.faction.X.rank.Y
 * Regions : groupes de l'ecran des factions (fleches < >), comme l'original.
 */
public class LOTRFactionRanks {

    public enum Region {
        WEST, EAST, SOUTH
    }

    public static class Rank {
        public final float alignment;
        public final String name;
        public final LOTRFaction faction;
        public boolean pledgeRank;

        Rank(LOTRFaction faction, float alignment, String name) {
            this.faction = faction;
            this.alignment = alignment;
            this.name = name;
        }

        public String getTranslationKey() {
            return "lotr.faction." + faction.name() + ".rank." + name;
        }
    }

    private static final Map<LOTRFaction, List<Rank>> RANKS = new EnumMap<>(LOTRFaction.class);
    private static final Map<LOTRFaction, Region> REGIONS = new EnumMap<>(LOTRFaction.class);

    private static Rank addRank(LOTRFaction f, float alignment, String name) {
        Rank r = new Rank(f, alignment, name);
        RANKS.computeIfAbsent(f, k -> new ArrayList<>()).add(r);
        return r;
    }

    /** Tous les rangs d'une faction (ordre croissant). */
    public static List<Rank> ranksOf(LOTRFaction faction) {
        return RANKS.getOrDefault(faction, java.util.Collections.emptyList());
    }

    /** Rang requis pour preter serment a cette faction (null si non serment-able). */
    public static Rank getPledgeRank(LOTRFaction faction) {
        List<Rank> list = RANKS.get(faction);
        if (list != null) {
            for (Rank r : list) {
                if (r.pledgeRank) {
                    return r;
                }
            }
        }
        return null;
    }

    /** Rang atteint pour cette valeur, ou null si en-dessous du premier (Etranger/Ennemi). */
    public static Rank getRank(LOTRFaction faction, float alignment) {
        List<Rank> list = RANKS.get(faction);
        Rank result = null;
        if (list != null) {
            for (Rank r : list) {
                if (alignment >= r.alignment) {
                    result = r;
                }
            }
        }
        return result;
    }

    public static Rank getRankAbove(LOTRFaction faction, float alignment) {
        List<Rank> list = RANKS.get(faction);
        if (list != null) {
            for (Rank r : list) {
                if (alignment < r.alignment) {
                    return r;
                }
            }
        }
        return null;
    }

    public static Rank getFirstRank(LOTRFaction faction) {
        List<Rank> list = RANKS.get(faction);
        return list == null || list.isEmpty() ? null : list.get(0);
    }

    public static Region getRegion(LOTRFaction faction) {
        return REGIONS.get(faction);
    }

    public static List<LOTRFaction> factionsOf(Region region) {
        List<LOTRFaction> out = new ArrayList<>();
        for (LOTRFaction f : LOTRFaction.values()) {
            if (REGIONS.get(f) == region) {
                out.add(f);
            }
        }
        return out;
    }

    static {
        addRank(LOTRFaction.ANGMAR, 10.0f, "thrall");
        addRank(LOTRFaction.ANGMAR, 50.0f, "servant");
        addRank(LOTRFaction.ANGMAR, 100.0f, "kinsman").pledgeRank = true;
        addRank(LOTRFaction.ANGMAR, 200.0f, "warrior");
        addRank(LOTRFaction.ANGMAR, 500.0f, "champion");
        addRank(LOTRFaction.ANGMAR, 1000.0f, "warlord");
        addRank(LOTRFaction.ANGMAR, 2000.0f, "chieftain");
        addRank(LOTRFaction.BLUE_MOUNTAINS, 10.0f, "guest");
        addRank(LOTRFaction.BLUE_MOUNTAINS, 50.0f, "friend");
        addRank(LOTRFaction.BLUE_MOUNTAINS, 100.0f, "warden").pledgeRank = true;
        addRank(LOTRFaction.BLUE_MOUNTAINS, 200.0f, "axebearer");
        addRank(LOTRFaction.BLUE_MOUNTAINS, 500.0f, "champion");
        addRank(LOTRFaction.BLUE_MOUNTAINS, 1000.0f, "captain");
        addRank(LOTRFaction.BLUE_MOUNTAINS, 1500.0f, "noble");
        addRank(LOTRFaction.BREE, 10.0f, "guest");
        addRank(LOTRFaction.BREE, 50.0f, "friend");
        addRank(LOTRFaction.BREE, 100.0f, "townsman").pledgeRank = true;
        addRank(LOTRFaction.BREE, 200.0f, "trustee");
        addRank(LOTRFaction.BREE, 500.0f, "champion");
        addRank(LOTRFaction.BREE, 1000.0f, "captain");
        addRank(LOTRFaction.BREE, 2000.0f, "master");
        addRank(LOTRFaction.DALE, 10.0f, "guest");
        addRank(LOTRFaction.DALE, 50.0f, "friend");
        addRank(LOTRFaction.DALE, 100.0f, "soldier").pledgeRank = true;
        addRank(LOTRFaction.DALE, 200.0f, "herald");
        addRank(LOTRFaction.DALE, 500.0f, "captain");
        addRank(LOTRFaction.DALE, 1000.0f, "marshal");
        addRank(LOTRFaction.DOL_GULDUR, 10.0f, "thrall");
        addRank(LOTRFaction.DOL_GULDUR, 50.0f, "servant");
        addRank(LOTRFaction.DOL_GULDUR, 100.0f, "brigand").pledgeRank = true;
        addRank(LOTRFaction.DOL_GULDUR, 200.0f, "torchbearer");
        addRank(LOTRFaction.DOL_GULDUR, 500.0f, "despoiler");
        addRank(LOTRFaction.DOL_GULDUR, 1000.0f, "captain");
        addRank(LOTRFaction.DOL_GULDUR, 2000.0f, "lieutenant");
        addRank(LOTRFaction.DORWINION, 10.0f, "guest");
        addRank(LOTRFaction.DORWINION, 50.0f, "vinehand");
        addRank(LOTRFaction.DORWINION, 100.0f, "merchant").pledgeRank = true;
        addRank(LOTRFaction.DORWINION, 200.0f, "guard");
        addRank(LOTRFaction.DORWINION, 500.0f, "captain");
        addRank(LOTRFaction.DORWINION, 1000.0f, "master");
        addRank(LOTRFaction.DORWINION, 1500.0f, "chief");
        addRank(LOTRFaction.DUNLAND, 10.0f, "guest");
        addRank(LOTRFaction.DUNLAND, 50.0f, "kinsman");
        addRank(LOTRFaction.DUNLAND, 100.0f, "warrior").pledgeRank = true;
        addRank(LOTRFaction.DUNLAND, 200.0f, "bearer");
        addRank(LOTRFaction.DUNLAND, 500.0f, "avenger");
        addRank(LOTRFaction.DUNLAND, 1000.0f, "warlord");
        addRank(LOTRFaction.DUNLAND, 2000.0f, "chieftain");
        addRank(LOTRFaction.DURINS_FOLK, 10.0f, "guest");
        addRank(LOTRFaction.DURINS_FOLK, 50.0f, "friend");
        addRank(LOTRFaction.DURINS_FOLK, 100.0f, "oathfriend").pledgeRank = true;
        addRank(LOTRFaction.DURINS_FOLK, 200.0f, "axebearer");
        addRank(LOTRFaction.DURINS_FOLK, 500.0f, "champion");
        addRank(LOTRFaction.DURINS_FOLK, 1000.0f, "commander");
        addRank(LOTRFaction.FANGORN, 10.0f, "newcomer");
        addRank(LOTRFaction.FANGORN, 50.0f, "friend");
        addRank(LOTRFaction.FANGORN, 100.0f, "treeherd").pledgeRank = true;
        addRank(LOTRFaction.FANGORN, 250.0f, "master");
        addRank(LOTRFaction.FANGORN, 500.0f, "elder");
        addRank(LOTRFaction.GONDOR, 10.0f, "guest");
        addRank(LOTRFaction.GONDOR, 50.0f, "friend");
        addRank(LOTRFaction.GONDOR, 100.0f, "atarms").pledgeRank = true;
        addRank(LOTRFaction.GONDOR, 200.0f, "soldier");
        addRank(LOTRFaction.GONDOR, 500.0f, "knight");
        addRank(LOTRFaction.GONDOR, 1000.0f, "champion");
        addRank(LOTRFaction.GONDOR, 1500.0f, "captain");
        addRank(LOTRFaction.GUNDABAD, 10.0f, "thrall");
        addRank(LOTRFaction.GUNDABAD, 50.0f, "snaga");
        addRank(LOTRFaction.GUNDABAD, 100.0f, "raider").pledgeRank = true;
        addRank(LOTRFaction.GUNDABAD, 200.0f, "ravager");
        addRank(LOTRFaction.GUNDABAD, 500.0f, "scourge");
        addRank(LOTRFaction.GUNDABAD, 1000.0f, "warlord");
        addRank(LOTRFaction.GUNDABAD, 2000.0f, "chieftain");
        addRank(LOTRFaction.HALF_TROLL, 10.0f, "guest");
        addRank(LOTRFaction.HALF_TROLL, 50.0f, "scavenger");
        addRank(LOTRFaction.HALF_TROLL, 100.0f, "kin").pledgeRank = true;
        addRank(LOTRFaction.HALF_TROLL, 200.0f, "warrior");
        addRank(LOTRFaction.HALF_TROLL, 500.0f, "raider");
        addRank(LOTRFaction.HALF_TROLL, 1000.0f, "warlord");
        addRank(LOTRFaction.HALF_TROLL, 2000.0f, "chieftain");
        addRank(LOTRFaction.HIGH_ELF, 10.0f, "guest");
        addRank(LOTRFaction.HIGH_ELF, 50.0f, "friend");
        addRank(LOTRFaction.HIGH_ELF, 100.0f, "warrior").pledgeRank = true;
        addRank(LOTRFaction.HIGH_ELF, 200.0f, "herald");
        addRank(LOTRFaction.HIGH_ELF, 500.0f, "captain");
        addRank(LOTRFaction.HIGH_ELF, 1000.0f, "noble");
        addRank(LOTRFaction.HIGH_ELF, 2000.0f, "commander");
        addRank(LOTRFaction.HOBBIT, 10.0f, "guest");
        addRank(LOTRFaction.HOBBIT, 100.0f, "friend").pledgeRank = true;
        addRank(LOTRFaction.HOBBIT, 250.0f, "hayward");
        addRank(LOTRFaction.HOBBIT, 500.0f, "bounder");
        addRank(LOTRFaction.HOBBIT, 1000.0f, "shirriff");
        addRank(LOTRFaction.HOBBIT, 2000.0f, "chief");
        addRank(LOTRFaction.HOBBIT, 3000.0f, "thain");
        addRank(LOTRFaction.ISENGARD, 10.0f, "thrall");
        addRank(LOTRFaction.ISENGARD, 50.0f, "snaga");
        addRank(LOTRFaction.ISENGARD, 100.0f, "soldier").pledgeRank = true;
        addRank(LOTRFaction.ISENGARD, 200.0f, "treefeller");
        addRank(LOTRFaction.ISENGARD, 500.0f, "berserker");
        addRank(LOTRFaction.ISENGARD, 1000.0f, "corporal");
        addRank(LOTRFaction.ISENGARD, 1500.0f, "hand");
        addRank(LOTRFaction.ISENGARD, 3000.0f, "captain");
        addRank(LOTRFaction.LOTHLORIEN, 10.0f, "guest");
        addRank(LOTRFaction.LOTHLORIEN, 50.0f, "friend");
        addRank(LOTRFaction.LOTHLORIEN, 100.0f, "warden").pledgeRank = true;
        addRank(LOTRFaction.LOTHLORIEN, 200.0f, "warrior");
        addRank(LOTRFaction.LOTHLORIEN, 1000.0f, "captain");
        addRank(LOTRFaction.LOTHLORIEN, 2000.0f, "noble");
        addRank(LOTRFaction.MORDOR, 10.0f, "thrall");
        addRank(LOTRFaction.MORDOR, 50.0f, "snaga");
        addRank(LOTRFaction.MORDOR, 100.0f, "brigand").pledgeRank = true;
        addRank(LOTRFaction.MORDOR, 200.0f, "slavedriver");
        addRank(LOTRFaction.MORDOR, 500.0f, "despoiler");
        addRank(LOTRFaction.MORDOR, 1000.0f, "captain");
        addRank(LOTRFaction.MORDOR, 1500.0f, "lieutenant");
        addRank(LOTRFaction.MORDOR, 3000.0f, "commander");
        addRank(LOTRFaction.MORWAITH, 10.0f, "guest");
        addRank(LOTRFaction.MORWAITH, 50.0f, "friend");
        addRank(LOTRFaction.MORWAITH, 100.0f, "kinsman").pledgeRank = true;
        addRank(LOTRFaction.MORWAITH, 250.0f, "hunter");
        addRank(LOTRFaction.MORWAITH, 500.0f, "warrior");
        addRank(LOTRFaction.MORWAITH, 1000.0f, "chief");
        addRank(LOTRFaction.MORWAITH, 3000.0f, "greatchief");
        addRank(LOTRFaction.NEAR_HARAD, 10.0f, "guest");
        addRank(LOTRFaction.NEAR_HARAD, 50.0f, "friend");
        addRank(LOTRFaction.NEAR_HARAD, 100.0f, "kinsman").pledgeRank = true;
        addRank(LOTRFaction.NEAR_HARAD, 200.0f, "warrior");
        addRank(LOTRFaction.NEAR_HARAD, 500.0f, "champion");
        addRank(LOTRFaction.NEAR_HARAD, 1000.0f, "serpentguard");
        addRank(LOTRFaction.NEAR_HARAD, 1500.0f, "warlord");
        addRank(LOTRFaction.RANGER_NORTH, 10.0f, "friend");
        addRank(LOTRFaction.RANGER_NORTH, 50.0f, "warden");
        addRank(LOTRFaction.RANGER_NORTH, 100.0f, "ranger").pledgeRank = true;
        addRank(LOTRFaction.RANGER_NORTH, 200.0f, "ohtar");
        addRank(LOTRFaction.RANGER_NORTH, 500.0f, "roquen");
        addRank(LOTRFaction.RANGER_NORTH, 1000.0f, "champion");
        addRank(LOTRFaction.RANGER_NORTH, 2000.0f, "captain");
        addRank(LOTRFaction.RHUDEL, 10.0f, "bondsman");
        addRank(LOTRFaction.RHUDEL, 50.0f, "levyman");
        addRank(LOTRFaction.RHUDEL, 100.0f, "clansman").pledgeRank = true;
        addRank(LOTRFaction.RHUDEL, 200.0f, "warrior");
        addRank(LOTRFaction.RHUDEL, 500.0f, "champion");
        addRank(LOTRFaction.RHUDEL, 1000.0f, "golden");
        addRank(LOTRFaction.RHUDEL, 1500.0f, "warlord");
        addRank(LOTRFaction.RHUDEL, 3000.0f, "chieftain");
        addRank(LOTRFaction.ROHAN, 10.0f, "guest");
        addRank(LOTRFaction.ROHAN, 50.0f, "footman");
        addRank(LOTRFaction.ROHAN, 100.0f, "atarms").pledgeRank = true;
        addRank(LOTRFaction.ROHAN, 250.0f, "rider");
        addRank(LOTRFaction.ROHAN, 500.0f, "esquire");
        addRank(LOTRFaction.ROHAN, 1000.0f, "captain");
        addRank(LOTRFaction.ROHAN, 2000.0f, "marshal");
        addRank(LOTRFaction.TAURETHRIM, 10.0f, "guest");
        addRank(LOTRFaction.TAURETHRIM, 50.0f, "friend");
        addRank(LOTRFaction.TAURETHRIM, 100.0f, "forestman").pledgeRank = true;
        addRank(LOTRFaction.TAURETHRIM, 200.0f, "warrior");
        addRank(LOTRFaction.TAURETHRIM, 500.0f, "champion");
        addRank(LOTRFaction.TAURETHRIM, 1000.0f, "warlord");
        addRank(LOTRFaction.TAURETHRIM, 3000.0f, "splendour");
        addRank(LOTRFaction.WOOD_ELF, 50.0f, "guest");
        addRank(LOTRFaction.WOOD_ELF, 100.0f, "friend").pledgeRank = true;
        addRank(LOTRFaction.WOOD_ELF, 200.0f, "guard");
        addRank(LOTRFaction.WOOD_ELF, 500.0f, "herald");
        addRank(LOTRFaction.WOOD_ELF, 1000.0f, "captain");
        addRank(LOTRFaction.WOOD_ELF, 2000.0f, "noble");

        REGIONS.put(LOTRFaction.HOBBIT, Region.WEST);
        REGIONS.put(LOTRFaction.BREE, Region.WEST);
        REGIONS.put(LOTRFaction.RANGER_NORTH, Region.WEST);
        REGIONS.put(LOTRFaction.BLUE_MOUNTAINS, Region.WEST);
        REGIONS.put(LOTRFaction.HIGH_ELF, Region.WEST);
        REGIONS.put(LOTRFaction.GUNDABAD, Region.WEST);
        REGIONS.put(LOTRFaction.ANGMAR, Region.WEST);
        REGIONS.put(LOTRFaction.WOOD_ELF, Region.WEST);
        REGIONS.put(LOTRFaction.DOL_GULDUR, Region.WEST);
        REGIONS.put(LOTRFaction.DALE, Region.WEST);
        REGIONS.put(LOTRFaction.DURINS_FOLK, Region.WEST);
        REGIONS.put(LOTRFaction.LOTHLORIEN, Region.WEST);
        REGIONS.put(LOTRFaction.DUNLAND, Region.WEST);
        REGIONS.put(LOTRFaction.ISENGARD, Region.WEST);
        REGIONS.put(LOTRFaction.FANGORN, Region.WEST);
        REGIONS.put(LOTRFaction.ROHAN, Region.WEST);
        REGIONS.put(LOTRFaction.GONDOR, Region.WEST);
        REGIONS.put(LOTRFaction.MORDOR, Region.WEST);
        REGIONS.put(LOTRFaction.DORWINION, Region.EAST);
        REGIONS.put(LOTRFaction.RHUDEL, Region.EAST);
        REGIONS.put(LOTRFaction.NEAR_HARAD, Region.SOUTH);
        REGIONS.put(LOTRFaction.MORWAITH, Region.SOUTH);
        REGIONS.put(LOTRFaction.TAURETHRIM, Region.SOUTH);
        REGIONS.put(LOTRFaction.HALF_TROLL, Region.SOUTH);
    }
}
