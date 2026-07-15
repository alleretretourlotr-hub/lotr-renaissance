package fr.alleretretour.lotr.item;

import fr.alleretretour.lotr.init.LOTRCreativeTabs;
import fr.alleretretour.lotr.init.LOTRItems;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.UseAction;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.potion.Effect;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.util.ActionResult;
import net.minecraft.util.DamageSource;
import net.minecraft.util.Hand;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.World;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;

/**
 * Port de lotr.common.item.LOTRItemMug - le systeme de boissons du mod.
 * En 1.7.10 la puissance et le recipient etaient dans les metadata ;
 * en 1.16.5 ils vivent dans le NBT (LOTRStrength 0-4, LOTRVessel nom).
 *
 * Puissances : weak x0.25, light x0.5, moderate x1, strong x2, potent x3
 * (multiplie la duree des effets et l'alcool ; la nourriture suit foodStrengths).
 *
 * TODO Phase 2 : pose des chopes au sol (LOTRBlockMug) + brassage au tonneau.
 * TODO Phase 3 : tolerance a l'alcool par joueur (LOTRPlayerData) + effet "ivresse"
 * complet (tremblements, messages alteres). Pour l'instant : nausee proportionnelle.
 */
public class LOTRItemMug extends Item {

    public static final String[] STRENGTH_NAMES = {"weak", "light", "moderate", "strong", "potent"};
    public static final float[] STRENGTHS = {0.25f, 0.5f, 1.0f, 2.0f, 3.0f};
    public static final float[] FOOD_STRENGTHS = {0.5f, 0.75f, 1.0f, 1.25f, 1.5f};

    private final boolean fullMug;
    private final boolean brewable;
    private final float alcoholicity;
    private int foodHeal;
    private float foodSaturation;
    private final List<PendingEffect> effects = new ArrayList<>();
    private int damageAmount;
    private boolean curesEffects;

    /** Recipient vide (chope, gobelet...). */
    public LOTRItemMug() {
        super(new Properties().tab(LOTRCreativeTabs.TAB_FOOD).stacksTo(64));
        this.fullMug = false;
        this.brewable = false;
        this.alcoholicity = 0.0f;
    }

    /** Boisson non brassable (eau, lait, jus...). */
    public LOTRItemMug(boolean food) {
        super(new Properties().tab(LOTRCreativeTabs.TAB_FOOD).stacksTo(1));
        this.fullMug = true;
        this.brewable = false;
        this.alcoholicity = 0.0f;
    }

    /** Boisson brassable avec taux d'alcool (biere, hydromel...). */
    public LOTRItemMug(float alcoholicity) {
        super(new Properties().tab(LOTRCreativeTabs.TAB_FOOD).stacksTo(1));
        this.fullMug = true;
        this.brewable = true;
        this.alcoholicity = alcoholicity;
    }

    // --- Builder (equivalents des setters chaines de l'original) ---

    public LOTRItemMug setDrinkStats(int heal, float saturation) {
        this.foodHeal = heal;
        this.foodSaturation = saturation;
        return this;
    }

    public LOTRItemMug addEffect(Supplier<Effect> effect, int seconds) {
        this.effects.add(new PendingEffect(effect, seconds));
        return this;
    }

    public LOTRItemMug setDamageAmount(int damage) {
        this.damageAmount = damage;
        return this;
    }

    public LOTRItemMug setCuresEffects() {
        this.curesEffects = true;
        return this;
    }

    // --- NBT : puissance et recipient ---

    public static int getStrength(ItemStack stack) {
        CompoundNBT nbt = stack.getTag();
        return nbt != null ? Math.min(Math.max(nbt.getInt("LOTRStrength"), 0), 4) : 2;
    }

    public static void setStrength(ItemStack stack, int strength) {
        stack.getOrCreateTag().putInt("LOTRStrength", strength);
    }

    public static LOTRVessel getVessel(ItemStack stack) {
        CompoundNBT nbt = stack.getTag();
        return nbt != null && nbt.contains("LOTRVessel")
                ? LOTRVessel.byName(nbt.getString("LOTRVessel")) : LOTRVessel.MUG;
    }

    public static void setVessel(ItemStack stack, LOTRVessel vessel) {
        stack.getOrCreateTag().putString("LOTRVessel", vessel.vesselName);
    }

    public boolean isBrewable() {
        return brewable;
    }

    public float getAlcoholicity() {
        return alcoholicity;
    }

    // --- Consommation ---

    @Override
    public UseAction getUseAnimation(ItemStack stack) {
        return fullMug ? UseAction.DRINK : UseAction.NONE;
    }

    @Override
    public int getUseDuration(ItemStack stack) {
        return fullMug ? 32 : 0;
    }

    @Override
    public ActionResult<ItemStack> use(World world, PlayerEntity player, Hand hand) {
        if (!fullMug) {
            return ActionResult.pass(player.getItemInHand(hand));
        }
        player.startUsingItem(hand);
        return ActionResult.consume(player.getItemInHand(hand));
    }

    @Override
    public ItemStack finishUsingItem(ItemStack stack, World world, LivingEntity entity) {
        if (!(entity instanceof PlayerEntity) || world.isClientSide) {
            return stack;
        }
        PlayerEntity player = (PlayerEntity) entity;
        float strength = brewable ? STRENGTHS[getStrength(stack)] : 1.0f;
        float foodStrength = brewable ? FOOD_STRENGTHS[getStrength(stack)] : 1.0f;

        if (foodHeal > 0 || foodSaturation > 0.0f) {
            player.getFoodData().eat(Math.round(foodHeal * foodStrength), foodSaturation * foodStrength);
        }
        for (PendingEffect pe : effects) {
            int duration = Math.round(pe.seconds * 20 * strength);
            player.addEffect(new EffectInstance(pe.effect.get(), duration, 0));
        }
        if (damageAmount > 0) {
            player.hurt(DamageSource.MAGIC, damageAmount);
        }
        if (curesEffects) {
            player.removeAllEffects();
        }
        if (alcoholicity > 0.0f) {
            // TODO Phase 3 : tolerance par joueur + effet ivresse complet
            int drunkTicks = Math.round(alcoholicity * strength * 60.0f * 20.0f);
            if (drunkTicks > 0) {
                player.addEffect(new EffectInstance(Effects.CONFUSION, Math.min(drunkTicks, 20 * 90), 0));
            }
        }

        if (player.abilities.instabuild) {
            return stack;
        }
        return LOTRItems.emptyVesselFor(getVessel(stack));
    }

    @Override
    public void appendHoverText(ItemStack stack, @Nullable World world, List<ITextComponent> tooltip, ITooltipFlag flag) {
        if (brewable) {
            tooltip.add(new TranslationTextComponent("lotr.drink." + STRENGTH_NAMES[getStrength(stack)]));
            if (alcoholicity > 0.0f) {
                // formule et paliers de couleur identiques a l'original
                float f = alcoholicity * STRENGTHS[getStrength(stack)] * 10.0f;
                net.minecraft.util.text.TextFormatting c =
                        f < 2.0f ? net.minecraft.util.text.TextFormatting.GREEN
                        : f < 5.0f ? net.minecraft.util.text.TextFormatting.YELLOW
                        : f < 10.0f ? net.minecraft.util.text.TextFormatting.GOLD
                        : f < 20.0f ? net.minecraft.util.text.TextFormatting.RED
                        : net.minecraft.util.text.TextFormatting.DARK_RED;
                tooltip.add(new TranslationTextComponent("lotr.drink.alcoholicity")
                        .append(": " + String.format("%.2f", f) + "%").withStyle(c));
            }
        }
    }

    private static class PendingEffect {
        final Supplier<Effect> effect;
        final int seconds;

        PendingEffect(Supplier<Effect> effect, int seconds) {
            this.effect = effect;
            this.seconds = seconds;
        }
    }
}
