package fr.alleretretour.lotr.item;

/** Port de LOTRItemPolearmLong (armes d'hast longues : plus d'allonge en Phase 7). */
public class LOTRItemPolearmLong extends LOTRItemPolearm {

    public LOTRItemPolearmLong(LOTRMaterial material) {
        super(material.toItemTier());
    }
}
