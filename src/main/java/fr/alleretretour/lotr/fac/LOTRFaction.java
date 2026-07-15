package fr.alleretretour.lotr.fac;

import java.util.EnumMap;
import java.util.Map;

/**
 * PORT GENERE de lotr.common.fac.LOTRFaction (Legacy 1.7.10).
 * Couleurs et relations diplomatiques par defaut fideles a l'original.
 * Les regions de carte et zones de controle viendront avec la Phase 6.
 */
public enum LOTRFaction {

    HOBBIT(0x59CE4E),
    BREE(0xAD8B72),
    RANGER_NORTH(0x3A5642),
    BLUE_MOUNTAINS(0x5D91CC),
    HIGH_ELF(0xC6E5FF),
    GUNDABAD(0x966C54),
    ANGMAR(0x779177),
    WOOD_ELF(0x39964E),
    DOL_GULDUR(0x353B44),
    DALE(0xCE875F),
    DURINS_FOLK(0x4B6182),
    LOTHLORIEN(0xEFD158),
    DUNLAND(0xA8948F),
    ISENGARD(0x333833),
    FANGORN(0x49B752),
    ROHAN(0x358727),
    GONDOR(0xF9F9F9),
    MORDOR(0x351F1F),
    DORWINION(0x6D3068),
    RHUDEL(0xC49227),
    NEAR_HARAD(0xB51B1B),
    MORWAITH(0xD9B05A),
    TAURETHRIM(0x2E6342),
    HALF_TROLL(0x9E8373),
    DARK_HUORN(0x000000),
    RUFFIAN(0x000000),
    UTUMNO(0x330500),
    HOSTILE(0x000000),
    UNALIGNED(0xFFFFFF);

    public final int color;

    LOTRFaction(int color) {
        this.color = color;
    }

    public enum Relation {
        ALLY, FRIEND, NEUTRAL, ENEMY, MORTAL_ENEMY
    }

    private static final Map<LOTRFaction, Map<LOTRFaction, Relation>> RELATIONS =
            new EnumMap<>(LOTRFaction.class);

    private static void setRelation(LOTRFaction a, LOTRFaction b, Relation r) {
        RELATIONS.computeIfAbsent(a, k -> new EnumMap<>(LOTRFaction.class)).put(b, r);
        RELATIONS.computeIfAbsent(b, k -> new EnumMap<>(LOTRFaction.class)).put(a, r);
    }

    public static Relation getRelation(LOTRFaction a, LOTRFaction b) {
        if (a == b) {
            return Relation.ALLY;
        }
        return RELATIONS.getOrDefault(a, java.util.Collections.emptyMap())
                .getOrDefault(b, Relation.NEUTRAL);
    }

    public String getTranslationKey() {
        return "lotr.faction." + name() + ".name";
    }

    public String codeName() {
        String[] parts = name().toLowerCase().split("_");
        StringBuilder sb = new StringBuilder(parts[0]);
        for (int i = 1; i < parts.length; i++) {
            sb.append(Character.toUpperCase(parts[i].charAt(0))).append(parts[i].substring(1));
        }
        return sb.toString();
    }


    private final java.util.List<LOTRFactionRank> ranks = new java.util.ArrayList<>();

    private LOTRFactionRank addRank(float alignment, String name) {
        return addRank(alignment, name, false);
    }

    private LOTRFactionRank addRank(float alignment, String name, boolean pledge) {
        LOTRFactionRank rank = new LOTRFactionRank(this, alignment, name, pledge);
        ranks.add(rank);
        java.util.Collections.sort(ranks);
        return rank;
    }

    /** Le rang atteint pour cette valeur d'alignement (ou rang neutre/ennemi factice). */
    public LOTRFactionRank getRank(float alignment) {
        if (alignment < 0.0f) {
            return LOTRFactionRank.RANK_ENEMY;
        }
        LOTRFactionRank result = null;
        for (LOTRFactionRank r : ranks) {
            if (alignment >= r.alignment) {
                result = r;
            }
        }
        return result != null ? result : LOTRFactionRank.RANK_NEUTRAL;
    }

    public LOTRFactionRank getFirstRank() {
        return ranks.isEmpty() ? LOTRFactionRank.RANK_NEUTRAL : ranks.get(0);
    }

    public LOTRFactionRank getRankAbove(LOTRFactionRank rank) {
        int i = ranks.indexOf(rank);
        return i >= 0 && i + 1 < ranks.size() ? ranks.get(i + 1) : null;
    }

    public LOTRFactionRank getPledgeRank() {
        for (LOTRFactionRank r : ranks) {
            if (r.isPledgeRank) {
                return r;
            }
        }
        return null;
    }

    public static LOTRFaction byName(String s) {
        try {
            return valueOf(s.toUpperCase());
        } catch (IllegalArgumentException e) {
            return null;
        }
    }

    static {
        setRelation(HOBBIT, BREE, Relation.ALLY);
        setRelation(HOBBIT, RANGER_NORTH, Relation.ALLY);
        setRelation(HOBBIT, BLUE_MOUNTAINS, Relation.ALLY);
        setRelation(HOBBIT, HIGH_ELF, Relation.FRIEND);
        setRelation(HOBBIT, WOOD_ELF, Relation.FRIEND);
        setRelation(HOBBIT, DALE, Relation.ALLY);
        setRelation(HOBBIT, DURINS_FOLK, Relation.ALLY);
        setRelation(HOBBIT, LOTHLORIEN, Relation.FRIEND);
        setRelation(HOBBIT, ROHAN, Relation.FRIEND);
        setRelation(HOBBIT, GONDOR, Relation.FRIEND);
        setRelation(BREE, RANGER_NORTH, Relation.FRIEND);
        setRelation(BREE, BLUE_MOUNTAINS, Relation.FRIEND);
        setRelation(BREE, HIGH_ELF, Relation.FRIEND);
        setRelation(BREE, WOOD_ELF, Relation.FRIEND);
        setRelation(BREE, DALE, Relation.FRIEND);
        setRelation(BREE, DURINS_FOLK, Relation.FRIEND);
        setRelation(BREE, LOTHLORIEN, Relation.FRIEND);
        setRelation(RANGER_NORTH, HIGH_ELF, Relation.ALLY);
        setRelation(RANGER_NORTH, WOOD_ELF, Relation.FRIEND);
        setRelation(RANGER_NORTH, LOTHLORIEN, Relation.ALLY);
        setRelation(RANGER_NORTH, ROHAN, Relation.FRIEND);
        setRelation(RANGER_NORTH, GONDOR, Relation.ALLY);
        setRelation(BLUE_MOUNTAINS, DURINS_FOLK, Relation.ALLY);
        setRelation(HIGH_ELF, WOOD_ELF, Relation.FRIEND);
        setRelation(HIGH_ELF, LOTHLORIEN, Relation.ALLY);
        setRelation(HIGH_ELF, FANGORN, Relation.ALLY);
        setRelation(HIGH_ELF, GONDOR, Relation.FRIEND);
        setRelation(GUNDABAD, ANGMAR, Relation.FRIEND);
        setRelation(GUNDABAD, DOL_GULDUR, Relation.FRIEND);
        setRelation(GUNDABAD, MORDOR, Relation.FRIEND);
        setRelation(ANGMAR, DOL_GULDUR, Relation.ALLY);
        setRelation(ANGMAR, MORDOR, Relation.ALLY);
        setRelation(WOOD_ELF, LOTHLORIEN, Relation.ALLY);
        setRelation(WOOD_ELF, FANGORN, Relation.ALLY);
        setRelation(WOOD_ELF, DORWINION, Relation.ALLY);
        setRelation(DOL_GULDUR, MORDOR, Relation.ALLY);
        setRelation(DALE, DURINS_FOLK, Relation.ALLY);
        setRelation(DALE, ROHAN, Relation.FRIEND);
        setRelation(DALE, GONDOR, Relation.FRIEND);
        setRelation(DURINS_FOLK, DUNLAND, Relation.FRIEND);
        setRelation(LOTHLORIEN, FANGORN, Relation.ALLY);
        setRelation(DUNLAND, ISENGARD, Relation.FRIEND);
        setRelation(ISENGARD, HALF_TROLL, Relation.FRIEND);
        setRelation(FANGORN, TAURETHRIM, Relation.ALLY);
        setRelation(ROHAN, GONDOR, Relation.ALLY);
        setRelation(MORDOR, RHUDEL, Relation.FRIEND);
        setRelation(MORDOR, NEAR_HARAD, Relation.FRIEND);
        setRelation(MORDOR, MORWAITH, Relation.FRIEND);
        setRelation(MORDOR, HALF_TROLL, Relation.ALLY);
        setRelation(NEAR_HARAD, MORWAITH, Relation.FRIEND);
        setRelation(NEAR_HARAD, HALF_TROLL, Relation.FRIEND);
        setRelation(HOBBIT, GUNDABAD, Relation.MORTAL_ENEMY);
        setRelation(HOBBIT, ANGMAR, Relation.MORTAL_ENEMY);
        setRelation(HOBBIT, DOL_GULDUR, Relation.MORTAL_ENEMY);
        setRelation(HOBBIT, ISENGARD, Relation.MORTAL_ENEMY);
        setRelation(HOBBIT, MORDOR, Relation.MORTAL_ENEMY);
        setRelation(HOBBIT, HALF_TROLL, Relation.MORTAL_ENEMY);
        setRelation(HOBBIT, DARK_HUORN, Relation.MORTAL_ENEMY);
        setRelation(BREE, GUNDABAD, Relation.MORTAL_ENEMY);
        setRelation(BREE, ANGMAR, Relation.MORTAL_ENEMY);
        setRelation(BREE, DOL_GULDUR, Relation.MORTAL_ENEMY);
        setRelation(BREE, ISENGARD, Relation.MORTAL_ENEMY);
        setRelation(BREE, MORDOR, Relation.MORTAL_ENEMY);
        setRelation(BREE, HALF_TROLL, Relation.MORTAL_ENEMY);
        setRelation(BREE, DARK_HUORN, Relation.MORTAL_ENEMY);
        setRelation(RANGER_NORTH, GUNDABAD, Relation.MORTAL_ENEMY);
        setRelation(RANGER_NORTH, ANGMAR, Relation.MORTAL_ENEMY);
        setRelation(RANGER_NORTH, DOL_GULDUR, Relation.MORTAL_ENEMY);
        setRelation(RANGER_NORTH, DUNLAND, Relation.ENEMY);
        setRelation(RANGER_NORTH, ISENGARD, Relation.MORTAL_ENEMY);
        setRelation(RANGER_NORTH, MORDOR, Relation.MORTAL_ENEMY);
        setRelation(RANGER_NORTH, RHUDEL, Relation.ENEMY);
        setRelation(RANGER_NORTH, NEAR_HARAD, Relation.ENEMY);
        setRelation(RANGER_NORTH, MORWAITH, Relation.ENEMY);
        setRelation(RANGER_NORTH, HALF_TROLL, Relation.MORTAL_ENEMY);
        setRelation(RANGER_NORTH, DARK_HUORN, Relation.MORTAL_ENEMY);
        setRelation(BLUE_MOUNTAINS, GUNDABAD, Relation.MORTAL_ENEMY);
        setRelation(BLUE_MOUNTAINS, ANGMAR, Relation.MORTAL_ENEMY);
        setRelation(BLUE_MOUNTAINS, DOL_GULDUR, Relation.MORTAL_ENEMY);
        setRelation(BLUE_MOUNTAINS, ISENGARD, Relation.MORTAL_ENEMY);
        setRelation(BLUE_MOUNTAINS, MORDOR, Relation.MORTAL_ENEMY);
        setRelation(BLUE_MOUNTAINS, HALF_TROLL, Relation.MORTAL_ENEMY);
        setRelation(HIGH_ELF, GUNDABAD, Relation.MORTAL_ENEMY);
        setRelation(HIGH_ELF, ANGMAR, Relation.MORTAL_ENEMY);
        setRelation(HIGH_ELF, DOL_GULDUR, Relation.MORTAL_ENEMY);
        setRelation(HIGH_ELF, ISENGARD, Relation.MORTAL_ENEMY);
        setRelation(HIGH_ELF, MORDOR, Relation.MORTAL_ENEMY);
        setRelation(HIGH_ELF, RHUDEL, Relation.ENEMY);
        setRelation(HIGH_ELF, NEAR_HARAD, Relation.ENEMY);
        setRelation(HIGH_ELF, HALF_TROLL, Relation.MORTAL_ENEMY);
        setRelation(GUNDABAD, WOOD_ELF, Relation.MORTAL_ENEMY);
        setRelation(GUNDABAD, DALE, Relation.MORTAL_ENEMY);
        setRelation(GUNDABAD, DURINS_FOLK, Relation.MORTAL_ENEMY);
        setRelation(GUNDABAD, LOTHLORIEN, Relation.MORTAL_ENEMY);
        setRelation(GUNDABAD, FANGORN, Relation.MORTAL_ENEMY);
        setRelation(GUNDABAD, ROHAN, Relation.MORTAL_ENEMY);
        setRelation(GUNDABAD, GONDOR, Relation.MORTAL_ENEMY);
        setRelation(GUNDABAD, DORWINION, Relation.MORTAL_ENEMY);
        setRelation(ANGMAR, WOOD_ELF, Relation.MORTAL_ENEMY);
        setRelation(ANGMAR, DALE, Relation.MORTAL_ENEMY);
        setRelation(ANGMAR, DURINS_FOLK, Relation.MORTAL_ENEMY);
        setRelation(ANGMAR, LOTHLORIEN, Relation.MORTAL_ENEMY);
        setRelation(ANGMAR, FANGORN, Relation.MORTAL_ENEMY);
        setRelation(ANGMAR, ROHAN, Relation.MORTAL_ENEMY);
        setRelation(ANGMAR, GONDOR, Relation.MORTAL_ENEMY);
        setRelation(ANGMAR, DORWINION, Relation.MORTAL_ENEMY);
        setRelation(WOOD_ELF, DOL_GULDUR, Relation.MORTAL_ENEMY);
        setRelation(WOOD_ELF, ISENGARD, Relation.MORTAL_ENEMY);
        setRelation(WOOD_ELF, MORDOR, Relation.MORTAL_ENEMY);
        setRelation(WOOD_ELF, RHUDEL, Relation.ENEMY);
        setRelation(WOOD_ELF, NEAR_HARAD, Relation.ENEMY);
        setRelation(WOOD_ELF, HALF_TROLL, Relation.MORTAL_ENEMY);
        setRelation(DOL_GULDUR, DALE, Relation.MORTAL_ENEMY);
        setRelation(DOL_GULDUR, DURINS_FOLK, Relation.MORTAL_ENEMY);
        setRelation(DOL_GULDUR, LOTHLORIEN, Relation.MORTAL_ENEMY);
        setRelation(DOL_GULDUR, FANGORN, Relation.MORTAL_ENEMY);
        setRelation(DOL_GULDUR, ROHAN, Relation.MORTAL_ENEMY);
        setRelation(DOL_GULDUR, GONDOR, Relation.MORTAL_ENEMY);
        setRelation(DOL_GULDUR, DORWINION, Relation.MORTAL_ENEMY);
        setRelation(DALE, ISENGARD, Relation.MORTAL_ENEMY);
        setRelation(DALE, MORDOR, Relation.MORTAL_ENEMY);
        setRelation(DALE, RHUDEL, Relation.ENEMY);
        setRelation(DALE, HALF_TROLL, Relation.MORTAL_ENEMY);
        setRelation(DURINS_FOLK, ISENGARD, Relation.MORTAL_ENEMY);
        setRelation(DURINS_FOLK, MORDOR, Relation.MORTAL_ENEMY);
        setRelation(DURINS_FOLK, HALF_TROLL, Relation.MORTAL_ENEMY);
        setRelation(LOTHLORIEN, ISENGARD, Relation.MORTAL_ENEMY);
        setRelation(LOTHLORIEN, MORDOR, Relation.MORTAL_ENEMY);
        setRelation(LOTHLORIEN, RHUDEL, Relation.ENEMY);
        setRelation(LOTHLORIEN, NEAR_HARAD, Relation.ENEMY);
        setRelation(LOTHLORIEN, HALF_TROLL, Relation.MORTAL_ENEMY);
        setRelation(DUNLAND, ROHAN, Relation.MORTAL_ENEMY);
        setRelation(DUNLAND, GONDOR, Relation.ENEMY);
        setRelation(ISENGARD, FANGORN, Relation.MORTAL_ENEMY);
        setRelation(ISENGARD, ROHAN, Relation.MORTAL_ENEMY);
        setRelation(ISENGARD, GONDOR, Relation.MORTAL_ENEMY);
        setRelation(ISENGARD, DORWINION, Relation.MORTAL_ENEMY);
        setRelation(FANGORN, MORDOR, Relation.MORTAL_ENEMY);
        setRelation(FANGORN, HALF_TROLL, Relation.MORTAL_ENEMY);
        setRelation(ROHAN, MORDOR, Relation.MORTAL_ENEMY);
        setRelation(ROHAN, RHUDEL, Relation.MORTAL_ENEMY);
        setRelation(ROHAN, NEAR_HARAD, Relation.ENEMY);
        setRelation(ROHAN, MORWAITH, Relation.ENEMY);
        setRelation(ROHAN, HALF_TROLL, Relation.MORTAL_ENEMY);
        setRelation(GONDOR, MORDOR, Relation.MORTAL_ENEMY);
        setRelation(GONDOR, RHUDEL, Relation.MORTAL_ENEMY);
        setRelation(GONDOR, NEAR_HARAD, Relation.MORTAL_ENEMY);
        setRelation(GONDOR, MORWAITH, Relation.ENEMY);
        setRelation(GONDOR, HALF_TROLL, Relation.MORTAL_ENEMY);
        setRelation(MORDOR, DORWINION, Relation.MORTAL_ENEMY);
        setRelation(MORDOR, TAURETHRIM, Relation.MORTAL_ENEMY);
        setRelation(DORWINION, HALF_TROLL, Relation.MORTAL_ENEMY);
        setRelation(NEAR_HARAD, TAURETHRIM, Relation.ENEMY);
        setRelation(MORWAITH, TAURETHRIM, Relation.MORTAL_ENEMY);
        setRelation(TAURETHRIM, HALF_TROLL, Relation.MORTAL_ENEMY);
        // fidele au Legacy : Utumno est l ennemi mortel de toutes les factions
        for (LOTRFaction f : values()) {
            if (f != UTUMNO && f != HOSTILE && f != UNALIGNED) {
                setRelation(f, UTUMNO, Relation.MORTAL_ENEMY);
            }
        }

        // rangs de faction (176, generes depuis le Legacy)
        HOBBIT.addRank(10.0f, "guest");
        HOBBIT.addRank(100.0f, "friend", true);
        HOBBIT.addRank(250.0f, "hayward");
        HOBBIT.addRank(500.0f, "bounder");
        HOBBIT.addRank(1000.0f, "shirriff");
        HOBBIT.addRank(2000.0f, "chief");
        HOBBIT.addRank(3000.0f, "thain");
        BREE.addRank(10.0f, "guest");
        BREE.addRank(50.0f, "friend");
        BREE.addRank(100.0f, "townsman", true);
        BREE.addRank(200.0f, "trustee");
        BREE.addRank(500.0f, "champion");
        BREE.addRank(1000.0f, "captain");
        BREE.addRank(2000.0f, "master");
        RANGER_NORTH.addRank(10.0f, "friend");
        RANGER_NORTH.addRank(50.0f, "warden");
        RANGER_NORTH.addRank(100.0f, "ranger", true);
        RANGER_NORTH.addRank(200.0f, "ohtar");
        RANGER_NORTH.addRank(500.0f, "roquen");
        RANGER_NORTH.addRank(1000.0f, "champion");
        RANGER_NORTH.addRank(2000.0f, "captain");
        BLUE_MOUNTAINS.addRank(10.0f, "guest");
        BLUE_MOUNTAINS.addRank(50.0f, "friend");
        BLUE_MOUNTAINS.addRank(100.0f, "warden", true);
        BLUE_MOUNTAINS.addRank(200.0f, "axebearer");
        BLUE_MOUNTAINS.addRank(500.0f, "champion");
        BLUE_MOUNTAINS.addRank(1000.0f, "captain");
        BLUE_MOUNTAINS.addRank(1500.0f, "noble");
        HIGH_ELF.addRank(10.0f, "guest");
        HIGH_ELF.addRank(50.0f, "friend");
        HIGH_ELF.addRank(100.0f, "warrior", true);
        HIGH_ELF.addRank(200.0f, "herald");
        HIGH_ELF.addRank(500.0f, "captain");
        HIGH_ELF.addRank(1000.0f, "noble");
        HIGH_ELF.addRank(2000.0f, "commander");
        GUNDABAD.addRank(10.0f, "thrall");
        GUNDABAD.addRank(50.0f, "snaga");
        GUNDABAD.addRank(100.0f, "raider", true);
        GUNDABAD.addRank(200.0f, "ravager");
        GUNDABAD.addRank(500.0f, "scourge");
        GUNDABAD.addRank(1000.0f, "warlord");
        GUNDABAD.addRank(2000.0f, "chieftain");
        ANGMAR.addRank(10.0f, "thrall");
        ANGMAR.addRank(50.0f, "servant");
        ANGMAR.addRank(100.0f, "kinsman", true);
        ANGMAR.addRank(200.0f, "warrior");
        ANGMAR.addRank(500.0f, "champion");
        ANGMAR.addRank(1000.0f, "warlord");
        ANGMAR.addRank(2000.0f, "chieftain");
        WOOD_ELF.addRank(50.0f, "guest");
        WOOD_ELF.addRank(100.0f, "friend", true);
        WOOD_ELF.addRank(200.0f, "guard");
        WOOD_ELF.addRank(500.0f, "herald");
        WOOD_ELF.addRank(1000.0f, "captain");
        WOOD_ELF.addRank(2000.0f, "noble");
        DOL_GULDUR.addRank(10.0f, "thrall");
        DOL_GULDUR.addRank(50.0f, "servant");
        DOL_GULDUR.addRank(100.0f, "brigand", true);
        DOL_GULDUR.addRank(200.0f, "torchbearer");
        DOL_GULDUR.addRank(500.0f, "despoiler");
        DOL_GULDUR.addRank(1000.0f, "captain");
        DOL_GULDUR.addRank(2000.0f, "lieutenant");
        DALE.addRank(10.0f, "guest");
        DALE.addRank(50.0f, "friend");
        DALE.addRank(100.0f, "soldier", true);
        DALE.addRank(200.0f, "herald");
        DALE.addRank(500.0f, "captain");
        DALE.addRank(1000.0f, "marshal");
        DURINS_FOLK.addRank(10.0f, "guest");
        DURINS_FOLK.addRank(50.0f, "friend");
        DURINS_FOLK.addRank(100.0f, "oathfriend", true);
        DURINS_FOLK.addRank(200.0f, "axebearer");
        DURINS_FOLK.addRank(500.0f, "champion");
        DURINS_FOLK.addRank(1000.0f, "commander");
        LOTHLORIEN.addRank(10.0f, "guest");
        LOTHLORIEN.addRank(50.0f, "friend");
        LOTHLORIEN.addRank(100.0f, "warden", true);
        LOTHLORIEN.addRank(200.0f, "warrior");
        LOTHLORIEN.addRank(1000.0f, "captain");
        LOTHLORIEN.addRank(2000.0f, "noble");
        DUNLAND.addRank(10.0f, "guest");
        DUNLAND.addRank(50.0f, "kinsman");
        DUNLAND.addRank(100.0f, "warrior", true);
        DUNLAND.addRank(200.0f, "bearer");
        DUNLAND.addRank(500.0f, "avenger");
        DUNLAND.addRank(1000.0f, "warlord");
        DUNLAND.addRank(2000.0f, "chieftain");
        ISENGARD.addRank(10.0f, "thrall");
        ISENGARD.addRank(50.0f, "snaga");
        ISENGARD.addRank(100.0f, "soldier", true);
        ISENGARD.addRank(200.0f, "treefeller");
        ISENGARD.addRank(500.0f, "berserker");
        ISENGARD.addRank(1000.0f, "corporal");
        ISENGARD.addRank(1500.0f, "hand");
        ISENGARD.addRank(3000.0f, "captain");
        FANGORN.addRank(10.0f, "newcomer");
        FANGORN.addRank(50.0f, "friend");
        FANGORN.addRank(100.0f, "treeherd", true);
        FANGORN.addRank(250.0f, "master");
        FANGORN.addRank(500.0f, "elder");
        ROHAN.addRank(10.0f, "guest");
        ROHAN.addRank(50.0f, "footman");
        ROHAN.addRank(100.0f, "atarms", true);
        ROHAN.addRank(250.0f, "rider");
        ROHAN.addRank(500.0f, "esquire");
        ROHAN.addRank(1000.0f, "captain");
        ROHAN.addRank(2000.0f, "marshal");
        GONDOR.addRank(10.0f, "guest");
        GONDOR.addRank(50.0f, "friend");
        GONDOR.addRank(100.0f, "atarms", true);
        GONDOR.addRank(200.0f, "soldier");
        GONDOR.addRank(500.0f, "knight");
        GONDOR.addRank(1000.0f, "champion");
        GONDOR.addRank(1500.0f, "captain");
        MORDOR.addRank(10.0f, "thrall");
        MORDOR.addRank(50.0f, "snaga");
        MORDOR.addRank(100.0f, "brigand", true);
        MORDOR.addRank(200.0f, "slavedriver");
        MORDOR.addRank(500.0f, "despoiler");
        MORDOR.addRank(1000.0f, "captain");
        MORDOR.addRank(1500.0f, "lieutenant");
        MORDOR.addRank(3000.0f, "commander");
        DORWINION.addRank(10.0f, "guest");
        DORWINION.addRank(50.0f, "vinehand");
        DORWINION.addRank(100.0f, "merchant", true);
        DORWINION.addRank(200.0f, "guard");
        DORWINION.addRank(500.0f, "captain");
        DORWINION.addRank(1000.0f, "master");
        DORWINION.addRank(1500.0f, "chief");
        RHUDEL.addRank(10.0f, "bondsman");
        RHUDEL.addRank(50.0f, "levyman");
        RHUDEL.addRank(100.0f, "clansman", true);
        RHUDEL.addRank(200.0f, "warrior");
        RHUDEL.addRank(500.0f, "champion");
        RHUDEL.addRank(1000.0f, "golden");
        RHUDEL.addRank(1500.0f, "warlord");
        RHUDEL.addRank(3000.0f, "chieftain");
        NEAR_HARAD.addRank(10.0f, "guest");
        NEAR_HARAD.addRank(50.0f, "friend");
        NEAR_HARAD.addRank(100.0f, "kinsman", true);
        NEAR_HARAD.addRank(200.0f, "warrior");
        NEAR_HARAD.addRank(500.0f, "champion");
        NEAR_HARAD.addRank(1000.0f, "serpentguard");
        NEAR_HARAD.addRank(1500.0f, "warlord");
        MORWAITH.addRank(10.0f, "guest");
        MORWAITH.addRank(50.0f, "friend");
        MORWAITH.addRank(100.0f, "kinsman", true);
        MORWAITH.addRank(250.0f, "hunter");
        MORWAITH.addRank(500.0f, "warrior");
        MORWAITH.addRank(1000.0f, "chief");
        MORWAITH.addRank(3000.0f, "greatchief");
        TAURETHRIM.addRank(10.0f, "guest");
        TAURETHRIM.addRank(50.0f, "friend");
        TAURETHRIM.addRank(100.0f, "forestman", true);
        TAURETHRIM.addRank(200.0f, "warrior");
        TAURETHRIM.addRank(500.0f, "champion");
        TAURETHRIM.addRank(1000.0f, "warlord");
        TAURETHRIM.addRank(3000.0f, "splendour");
        HALF_TROLL.addRank(10.0f, "guest");
        HALF_TROLL.addRank(50.0f, "scavenger");
        HALF_TROLL.addRank(100.0f, "kin", true);
        HALF_TROLL.addRank(200.0f, "warrior");
        HALF_TROLL.addRank(500.0f, "raider");
        HALF_TROLL.addRank(1000.0f, "warlord");
        HALF_TROLL.addRank(2000.0f, "chieftain");

    }
}
