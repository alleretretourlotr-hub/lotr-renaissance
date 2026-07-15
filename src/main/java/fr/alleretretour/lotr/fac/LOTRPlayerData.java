package fr.alleretretour.lotr.fac;

import net.minecraft.nbt.CompoundNBT;

import java.util.EnumMap;
import java.util.Map;

/**
 * Port du coeur de lotr.common.LOTRPlayerData : les alignements par faction.
 * Stocke en Capability Forge (LOTRPlayerDataProvider), persiste en NBT,
 * survit a la mort, se synchronise au client via LOTRPacketAlignment.
 *
 * Systemes a venir dans les prochains lots : serments (pledge), rangs,
 * recompenses d'alignement, regions, cache d'alignement par zone.
 */
public class LOTRPlayerData {

    private final Map<LOTRFaction, Float> alignments = new EnumMap<>(LOTRFaction.class);
    private boolean hideAlignment;
    private LOTRFaction pledgeFaction;
    private String titleRank = "";
    private LOTRFaction titleFaction;

    public LOTRFaction getTitleFaction() {
        return titleFaction;
    }

    public String getTitleRank() {
        return titleRank;
    }

    /** Choisit un titre parmi les rangs atteints (verifie cote serveur). */
    public boolean setTitle(LOTRFaction faction, String rankName) {
        if (faction == null) {
            titleFaction = null;
            titleRank = "";
            return true;
        }
        LOTRFactionRanks.Rank current = LOTRFactionRanks.getRank(faction, getAlignment(faction));
        // le rang demande doit etre atteint (lui ou un rang superieur du meme arbre)
        for (LOTRFactionRanks.Rank r : LOTRFactionRanks.ranksOf(faction)) {
            if (r.name.equals(rankName)
                    && current != null && current.alignment >= r.alignment) {
                titleFaction = faction;
                titleRank = rankName;
                return true;
            }
        }
        return false;
    }

    public LOTRFaction getPledgeFaction() {
        return pledgeFaction;
    }

    public boolean isPledgedTo(LOTRFaction faction) {
        return pledgeFaction == faction;
    }

    /** Serment possible : rang de serment atteint et pas deja engage ailleurs. */
    public boolean canPledgeTo(LOTRFaction faction) {
        LOTRFactionRanks.Rank required = LOTRFactionRanks.getPledgeRank(faction);
        return required != null && getAlignment(faction) >= required.alignment;
    }

    public void setPledgeFaction(LOTRFaction faction) {
        this.pledgeFaction = faction;
    }

    /** Rupture automatique si l'alignement retombe sous le rang de serment. */
    public boolean checkPledgeBroken() {
        if (pledgeFaction != null && !canPledgeTo(pledgeFaction)) {
            pledgeFaction = null;
            return true;
        }
        return false;
    }

    public float getAlignment(LOTRFaction faction) {
        return alignments.getOrDefault(faction, 0.0f);
    }

    public void setAlignment(LOTRFaction faction, float value) {
        alignments.put(faction, value);
    }

    /**
     * Ajoute de l'alignement avec propagation diplomatique fidele a l'original :
     * un gain pour une faction impacte ses allies (+50%), amis (+25%),
     * ennemis (-100%) et ennemis mortels (-200%... plafonne raisonnablement).
     */
    public void addAlignment(LOTRFaction faction, float amount) {
        setAlignment(faction, getAlignment(faction) + amount);
        if (amount <= 0.0f) {
            return;
        }
        for (LOTRFaction other : LOTRFaction.values()) {
            if (other == faction || other == LOTRFaction.HOSTILE || other == LOTRFaction.UNALIGNED) {
                continue;
            }
            switch (LOTRFaction.getRelation(faction, other)) {
                case ALLY:
                    setAlignment(other, getAlignment(other) + amount * 0.5f);
                    break;
                case FRIEND:
                    setAlignment(other, getAlignment(other) + amount * 0.25f);
                    break;
                case ENEMY:
                    setAlignment(other, getAlignment(other) - amount);
                    break;
                case MORTAL_ENEMY:
                    setAlignment(other, getAlignment(other) - amount * 2.0f);
                    break;
                default:
                    break;
            }
        }
    }

    public boolean isHideAlignment() {
        return hideAlignment;
    }

    public void setHideAlignment(boolean hide) {
        this.hideAlignment = hide;
    }

    public CompoundNBT save() {
        CompoundNBT nbt = new CompoundNBT();
        CompoundNBT alignTag = new CompoundNBT();
        for (Map.Entry<LOTRFaction, Float> e : alignments.entrySet()) {
            if (e.getValue() != 0.0f) {
                alignTag.putFloat(e.getKey().name(), e.getValue());
            }
        }
        nbt.put("Alignments", alignTag);
        nbt.putBoolean("HideAlignment", hideAlignment);
        if (pledgeFaction != null) {
            nbt.putString("PledgeFaction", pledgeFaction.name());
        }
        if (titleFaction != null) {
            nbt.putString("TitleFaction", titleFaction.name());
            nbt.putString("TitleRank", titleRank);
        }
        return nbt;
    }

    public void load(CompoundNBT nbt) {
        alignments.clear();
        CompoundNBT alignTag = nbt.getCompound("Alignments");
        for (String key : alignTag.getAllKeys()) {
            LOTRFaction f = LOTRFaction.byName(key);
            if (f != null) {
                alignments.put(f, alignTag.getFloat(key));
            }
        }
        hideAlignment = nbt.getBoolean("HideAlignment");
        pledgeFaction = nbt.contains("PledgeFaction")
                ? LOTRFaction.byName(nbt.getString("PledgeFaction")) : null;
        titleFaction = nbt.contains("TitleFaction")
                ? LOTRFaction.byName(nbt.getString("TitleFaction")) : null;
        titleRank = nbt.getString("TitleRank");
    }

    public void copyFrom(LOTRPlayerData other) {
        load(other.save());
    }
}
