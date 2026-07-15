package fr.alleretretour.lotr.item;

import fr.alleretretour.lotr.init.LOTRItems;
import net.minecraft.item.SwordItem;

/**
 * Port de LOTRItemBattleaxe : degats epee + 2, frappe lente.
 * (SwordItem et non AxeItem : comme l'original, c'est une arme, pas un outil.)
 */
public class LOTRItemBattleaxe extends SwordItem {

    public LOTRItemBattleaxe(LOTRMaterial material) {
        super(material.toItemTier(), 5, -3.0f,
                new Properties().tab(fr.alleretretour.lotr.init.LOTRCreativeTabs.TAB_COMBAT));
    }
}
