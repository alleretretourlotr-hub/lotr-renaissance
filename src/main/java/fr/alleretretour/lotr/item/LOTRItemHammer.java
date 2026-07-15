package fr.alleretretour.lotr.item;

import fr.alleretretour.lotr.init.LOTRItems;
import net.minecraft.item.SwordItem;

/**
 * Port de LOTRItemHammer : degats epee + 2, frappe lente (arme lourde).
 */
public class LOTRItemHammer extends SwordItem {

    public LOTRItemHammer(LOTRMaterial material) {
        super(material.toItemTier(), 5, -3.1f,
                new Properties().tab(fr.alleretretour.lotr.init.LOTRCreativeTabs.TAB_COMBAT));
    }
}
