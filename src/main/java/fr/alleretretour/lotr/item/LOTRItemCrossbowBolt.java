package fr.alleretretour.lotr.item;

import fr.alleretretour.lotr.init.LOTRItems;
import net.minecraft.item.Item;

/** Port de lotr.common.item.LOTRItemCrossbowBolt (munition d'arbalete). */
public class LOTRItemCrossbowBolt extends Item {

    public LOTRItemCrossbowBolt() {
        super(new Properties().tab(fr.alleretretour.lotr.init.LOTRCreativeTabs.TAB_COMBAT));
    }
}
