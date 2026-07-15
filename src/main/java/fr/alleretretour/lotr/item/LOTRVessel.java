package fr.alleretretour.lotr.item;

/**
 * Port de LOTRItemMug.Vessel : les 12 recipients du mod original.
 * canPlace : posable au sol (bloc chope, Phase 2). extraPrice : surcout marchand (Phase 4).
 */
public enum LOTRVessel {
    MUG("mug", true, 0),
    MUG_CLAY("clay", true, 1),
    GOBLET_GOLD("goblet_gold", true, 10),
    GOBLET_SILVER("goblet_silver", true, 8),
    GOBLET_COPPER("goblet_copper", true, 5),
    GOBLET_WOOD("goblet_wood", true, 0),
    SKULL("skull", true, 3),
    GLASS("glass", true, 3),
    BOTTLE("bottle", true, 2),
    SKIN("skin", false, 0),
    HORN("horn", true, 5),
    HORN_GOLD("horn_gold", true, 8);

    public final String vesselName;
    public final boolean canPlace;
    public final int extraPrice;

    LOTRVessel(String name, boolean canPlace, int extraPrice) {
        this.vesselName = name;
        this.canPlace = canPlace;
        this.extraPrice = extraPrice;
    }

    public static LOTRVessel byName(String s) {
        for (LOTRVessel v : values()) {
            if (v.vesselName.equals(s)) {
                return v;
            }
        }
        return MUG;
    }
}
