package fr.alleretretour.lotr.item;

import fr.alleretretour.lotr.init.LOTRItems;
import net.minecraft.item.IItemTier;
import net.minecraft.item.SwordItem;

/**
 * Port de LOTRItemPolearm (hallebardes courtes).
 * L'allonge etendue du mod original (portee de frappe accrue pour les armes
 * d'hast) sera portee en Phase 7 via un event handler dedie - note au backlog.
 */
public class LOTRItemPolearm extends SwordItem {

    public LOTRItemPolearm(LOTRMaterial material) {
        this(material.toItemTier());
    }

    protected LOTRItemPolearm(IItemTier tier) {
        super(tier, 3, -2.7f, new Properties().tab(fr.alleretretour.lotr.init.LOTRCreativeTabs.TAB_COMBAT));
    }
}
