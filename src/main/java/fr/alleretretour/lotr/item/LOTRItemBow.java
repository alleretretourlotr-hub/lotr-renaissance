package fr.alleretretour.lotr.item;

import fr.alleretretour.lotr.init.LOTRItems;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.AbstractArrowEntity;
import net.minecraft.item.ArrowItem;
import net.minecraft.item.BowItem;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvents;
import net.minecraft.world.World;

/**
 * Port de lotr.common.item.LOTRItemBow.
 * - durabilite = uses du materiau * 1.5 (comme l'original)
 * - facteur de degats des fleches (arcs elfiques 1.25, orques 1.125...)
 * - temps de bande configurable (bowPullTime, 20 par defaut)
 */
public class LOTRItemBow extends BowItem {

    public static final float MIN_BOW_DRAW = 0.65f;

    private final double arrowDamageFactor;
    private final int bowPullTime;

    public LOTRItemBow(LOTRMaterial material) {
        this(material, 1.0);
    }

    public LOTRItemBow(LOTRMaterial material, double damageFactor) {
        this(material, damageFactor, 20);
    }

    public LOTRItemBow(LOTRMaterial material, double damageFactor, int pullTime) {
        super(new Properties()
                .durability((int) (material.toItemTier().getUses() * 1.5f))
                .tab(fr.alleretretour.lotr.init.LOTRCreativeTabs.TAB_COMBAT));
        this.arrowDamageFactor = damageFactor;
        this.bowPullTime = pullTime;
    }

    public int getBowPullTime() {
        return bowPullTime;
    }

    public float getPowerForTimeCustom(int useTicks) {
        float f = (float) useTicks / bowPullTime;
        f = (f * f + f * 2.0f) / 3.0f;
        return Math.min(f, 1.0f);
    }

    @Override
    public void releaseUsing(ItemStack stack, World world, LivingEntity entity, int timeLeft) {
        if (!(entity instanceof PlayerEntity)) {
            return;
        }
        PlayerEntity player = (PlayerEntity) entity;
        boolean infinite = player.abilities.instabuild
                || EnchantmentHelper.getItemEnchantmentLevel(Enchantments.INFINITY_ARROWS, stack) > 0;
        ItemStack ammo = player.getProjectile(stack);
        if (ammo.isEmpty() && !infinite) {
            return;
        }
        if (ammo.isEmpty()) {
            ammo = new ItemStack(Items.ARROW);
        }
        int useTicks = getUseDuration(stack) - timeLeft;
        float power = getPowerForTimeCustom(useTicks);
        if (power < 0.1f) {
            return;
        }
        if (!world.isClientSide) {
            ArrowItem arrowItem = (ArrowItem) (ammo.getItem() instanceof ArrowItem ? ammo.getItem() : Items.ARROW);
            AbstractArrowEntity arrow = arrowItem.createArrow(world, ammo, player);
            arrow.shootFromRotation(player, player.xRot, player.yRot, 0.0f, power * 3.0f, 1.0f);
            arrow.setBaseDamage(arrow.getBaseDamage() * arrowDamageFactor);
            if (power == 1.0f) {
                arrow.setCritArrow(true);
            }
            int powerEnch = EnchantmentHelper.getItemEnchantmentLevel(Enchantments.POWER_ARROWS, stack);
            if (powerEnch > 0) {
                arrow.setBaseDamage(arrow.getBaseDamage() + powerEnch * 0.5 + 0.5);
            }
            int punch = EnchantmentHelper.getItemEnchantmentLevel(Enchantments.PUNCH_ARROWS, stack);
            if (punch > 0) {
                arrow.setKnockback(punch);
            }
            if (EnchantmentHelper.getItemEnchantmentLevel(Enchantments.FLAMING_ARROWS, stack) > 0) {
                arrow.setSecondsOnFire(100);
            }
            stack.hurtAndBreak(1, player, p -> p.broadcastBreakEvent(player.getUsedItemHand()));
            if (infinite && ammo.getItem() == Items.ARROW) {
                arrow.pickup = AbstractArrowEntity.PickupStatus.CREATIVE_ONLY;
            }
            world.addFreshEntity(arrow);
        }
        world.playSound(null, player.getX(), player.getY(), player.getZ(),
                SoundEvents.ARROW_SHOOT, SoundCategory.PLAYERS,
                1.0f, 1.0f / (random.nextFloat() * 0.4f + 1.2f) + power * 0.5f);
        if (!infinite && !player.abilities.instabuild) {
            ammo.shrink(1);
        }
        player.awardStat(net.minecraft.stats.Stats.ITEM_USED.get(this));
    }
}
