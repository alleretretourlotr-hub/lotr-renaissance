package fr.alleretretour.lotr.item;

/** Port de LOTRItemPike : pique, comportement de polearm longue. */
public class LOTRItemPike extends LOTRItemPolearm {

    public LOTRItemPike(LOTRMaterial material) {
        super(material.toItemTier());
    }
}
