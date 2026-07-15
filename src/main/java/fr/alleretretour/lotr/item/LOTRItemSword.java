package fr.alleretretour.lotr.item;

import fr.alleretretour.lotr.init.LOTRItems;
import net.minecraft.item.SwordItem;

/**
 * Port de lotr.common.item.LOTRItemSword.
 * attackDamageIn = 3 reproduit l'equilibrage vanilla actuel ;
 * les sous-classes (hache, marteau...) ajustent degats et vitesse.
 */
public class LOTRItemSword extends SwordItem {

    private final boolean elvenBlade;

    public LOTRItemSword(LOTRMaterial material) {
        this(material, false);
    }

    public LOTRItemSword(LOTRMaterial material, boolean elvenBlade) {
        this(material, 3, -2.4f, elvenBlade);
    }

    protected LOTRItemSword(LOTRMaterial material, int attackDamage, float attackSpeed, boolean elvenBlade) {
        super(material.toItemTier(), attackDamage, attackSpeed,
                new Properties().tab(fr.alleretretour.lotr.init.LOTRCreativeTabs.TAB_COMBAT));
        this.elvenBlade = elvenBlade;
    }

    /** Lames elfiques : brilleront pres des orques (effet porte en Phase 4 avec les NPC). */
    public boolean isElvenBlade() {
        return elvenBlade;
    }
}
