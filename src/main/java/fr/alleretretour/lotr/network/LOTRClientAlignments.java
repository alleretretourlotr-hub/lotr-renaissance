package fr.alleretretour.lotr.network;

import fr.alleretretour.lotr.fac.LOTRFaction;

import java.util.EnumMap;
import java.util.Map;

/**
 * Cache client des alignements du joueur local (alimente par LOTRPacketAlignment)
 * + la faction actuellement suivie dans le HUD (preference locale).
 * Aucune dependance client-only : chargeable sans risque sur Mohist.
 */
public class LOTRClientAlignments {

    private static final Map<LOTRFaction, Float> VALUES = new EnumMap<>(LOTRFaction.class);
    private static LOTRFaction current = LOTRFaction.GONDOR;
    private static LOTRFaction pledge;

    public static LOTRFaction getPledge() {
        return pledge;
    }

    public static void setPledge(LOTRFaction faction) {
        pledge = faction;
    }

    public static void set(LOTRFaction faction, float value) {
        VALUES.put(faction, value);
    }

    public static float get(LOTRFaction faction) {
        return VALUES.getOrDefault(faction, 0.0f);
    }

    public static LOTRFaction getCurrent() {
        return current;
    }

    public static void setCurrent(LOTRFaction faction) {
        current = faction;
    }
}
