package fr.alleretretour.lotr.item;

import fr.alleretretour.lotr.init.LOTRCreativeTabs;
import fr.alleretretour.lotr.init.LOTRItems;
import net.minecraft.item.HoeItem;

/** Port de lotr.common.item.LOTRItemHoe. */
public class LOTRItemHoe extends HoeItem {

    public LOTRItemHoe(LOTRMaterial material) {
        super(material.toItemTier(), -Math.min(2, material.toItemTier().getLevel()), -1.0f,
                new Properties().tab(LOTRCreativeTabs.TAB_TOOLS));
    }
}
