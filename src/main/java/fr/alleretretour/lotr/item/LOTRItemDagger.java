package fr.alleretretour.lotr.item;

import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.SwordItem;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;

import fr.alleretretour.lotr.init.LOTRItems;

/**
 * Port de lotr.common.item.LOTRItemDagger.
 * Original : degats epee - 3, avec effet optionnel (poison / fleau des tombes).
 * Adaptation 1.9+ : vitesse d'attaque plus rapide (-1.5) pour compenser,
 * fidele a l'esprit "arme legere".
 */
public class LOTRItemDagger extends SwordItem {

    public enum DaggerEffect {
        NONE, POISON, BARROW
    }

    private final DaggerEffect effect;

    public LOTRItemDagger(LOTRMaterial material) {
        this(material, DaggerEffect.NONE);
    }

    public LOTRItemDagger(LOTRMaterial material, DaggerEffect effect) {
        super(material.toItemTier(), 0, -1.5f,
                new Properties().tab(fr.alleretretour.lotr.init.LOTRCreativeTabs.TAB_COMBAT));
        this.effect = effect;
    }

    @Override
    public boolean hurtEnemy(ItemStack stack, LivingEntity target, LivingEntity attacker) {
        boolean result = super.hurtEnemy(stack, target, attacker);
        if (result && !target.level.isClientSide) {
            if (effect == DaggerEffect.POISON) {
                target.addEffect(new EffectInstance(Effects.POISON, 100, 0)); // 5 s, comme l'original
            } else if (effect == DaggerEffect.BARROW) {
                target.addEffect(new EffectInstance(Effects.WITHER, 100, 0));
            }
        }
        return result;
    }
}
