package fr.alleretretour.lotr.item;

import fr.alleretretour.lotr.init.LOTRCreativeTabs;
import fr.alleretretour.lotr.init.LOTRItems;
import net.minecraft.item.AxeItem;

/** Port de lotr.common.item.LOTRItemAxe (hache-outil, stats vanilla fer). */
public class LOTRItemAxe extends AxeItem {

    public LOTRItemAxe(LOTRMaterial material) {
        super(material.toItemTier(), 6.0f, -3.1f,
                new Properties().tab(LOTRCreativeTabs.TAB_TOOLS));
    }
}
