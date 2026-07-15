package fr.alleretretour.lotr.item;

import fr.alleretretour.lotr.init.LOTRCreativeTabs;
import fr.alleretretour.lotr.init.LOTRItems;
import net.minecraft.item.ShovelItem;

/** Port de lotr.common.item.LOTRItemShovel. */
public class LOTRItemShovel extends ShovelItem {

    public LOTRItemShovel(LOTRMaterial material) {
        super(material.toItemTier(), 1.5f, -3.0f,
                new Properties().tab(LOTRCreativeTabs.TAB_TOOLS));
    }
}
