package fr.alleretretour.lotr.item;

import fr.alleretretour.lotr.init.LOTRCreativeTabs;
import fr.alleretretour.lotr.init.LOTRItems;
import net.minecraft.item.PickaxeItem;

/** Port de lotr.common.item.LOTRItemPickaxe (stats vanilla). */
public class LOTRItemPickaxe extends PickaxeItem {

    public LOTRItemPickaxe(LOTRMaterial material) {
        super(material.toItemTier(), 1, -2.8f,
                new Properties().tab(LOTRCreativeTabs.TAB_TOOLS));
    }
}
