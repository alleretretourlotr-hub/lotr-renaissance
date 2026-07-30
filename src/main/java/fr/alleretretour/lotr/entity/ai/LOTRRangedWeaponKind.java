package fr.alleretretour.lotr.entity.ai;

import net.minecraft.item.BowItem;
import net.minecraft.item.ItemStack;

/**
 * Classification des armes a distance tenues par un PNJ.
 * BOW      : arcs (cycle de tir avec bandage, fleches vanilla)
 * CROSSBOW : arbalètes (carreau LOTREntityCrossbowBolt, tir tendu)
 * BLOWGUN  : sarbacane (flechette LOTREntityDart, empoisonnee)
 * THROWN   : lances et haches de jet (LOTREntityThrownWeapon, l'arme vole)
 */
public enum LOTRRangedWeaponKind {
    BOW, CROSSBOW, BLOWGUN, THROWN, NONE;

    public static LOTRRangedWeaponKind of(ItemStack stack) {
        if (stack.isEmpty()) {
            return NONE;
        }
        if (stack.getItem() instanceof BowItem) {
            return BOW;
        }
        if (stack.getItem() instanceof fr.alleretretour.lotr.item.LOTRItemCrossbow) {
            return CROSSBOW;
        }
        if (stack.getItem() instanceof fr.alleretretour.lotr.item.LOTRItemBlowgun) {
            return BLOWGUN;
        }
        if (stack.getItem() instanceof fr.alleretretour.lotr.item.LOTRItemSpear) {
            return THROWN;  // couvre LOTRItemThrowingAxe (sous-classe)
        }
        return NONE;
    }
}
