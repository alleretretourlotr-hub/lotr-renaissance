package fr.alleretretour.lotr.item;

/**
 * Port de LOTRItemLance : lance de cavalerie.
 * L'original applique un malus de vitesse au porteur a pied et un bonus
 * de degats monte - a porter en Phase 7 (event handler equipement).
 */
public class LOTRItemLance extends LOTRItemPolearm {

    public LOTRItemLance(LOTRMaterial material) {
        super(material.toItemTier());
    }
}
