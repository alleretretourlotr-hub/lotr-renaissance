package fr.alleretretour.lotr.fac;

/**
 * PORT de lotr.common.fac.LOTRFactionRank : un rang de faction,
 * seuil d'alignement + cle de nom traduite (lotr.faction.FACTION.rank.nom).
 */
public class LOTRFactionRank implements Comparable<LOTRFactionRank> {

    public static final LOTRFactionRank RANK_NEUTRAL = new LOTRFactionRank(null, 0.0f, "neutral", false);
    public static final LOTRFactionRank RANK_ENEMY = new LOTRFactionRank(null, 0.0f, "enemy", false);

    public final LOTRFaction faction;
    public final float alignment;
    public final String name;
    public final boolean isPledgeRank;

    public LOTRFactionRank(LOTRFaction faction, float alignment, String name, boolean pledge) {
        this.faction = faction;
        this.alignment = alignment;
        this.name = name;
        this.isPledgeRank = pledge;
    }

    public boolean isDummyRank() {
        return faction == null;
    }

    public String getTranslationKey() {
        if (faction == null) {
            return "lotr.faction.rank." + name;
        }
        return "lotr.faction." + faction.name() + ".rank." + name;
    }

    @Override
    public int compareTo(LOTRFactionRank o) {
        return Float.compare(alignment, o.alignment);
    }
}
