package fr.alleretretour.lotr.item;

import fr.alleretretour.lotr.init.LOTRItems;
import net.minecraft.item.Item;

/** Port de lotr.common.item.LOTRItemDart (flechette de sarbacane Tauredain). */
public class LOTRItemDart extends Item {

    public LOTRItemDart() {
        super(new Properties().tab(fr.alleretretour.lotr.init.LOTRCreativeTabs.TAB_COMBAT));
    }
}
