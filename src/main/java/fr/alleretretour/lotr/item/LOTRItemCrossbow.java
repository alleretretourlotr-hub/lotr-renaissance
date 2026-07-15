package fr.alleretretour.lotr.item;

import fr.alleretretour.lotr.entity.projectile.LOTREntityCrossbowBolt;
import fr.alleretretour.lotr.init.LOTRItems;
import fr.alleretretour.lotr.init.LOTRItemsRanged;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.UseAction;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvents;
import net.minecraft.world.World;

/**
 * Port de lotr.common.item.LOTRItemCrossbow.
 * Facteur de degats du carreau = 1 + max(0, (degats materiau - 2) * 0.1) (original).
 * Munitions : carreaux normaux ou empoisonnes. Durabilite = uses * 1.5.
 */
public class LOTRItemCrossbow extends Item {

    private final double boltDamageFactor;

    public LOTRItemCrossbow(LOTRMaterial material) {
        super(new Properties()
                .durability((int) (material.toItemTier().getUses() * 1.5f))
                .tab(fr.alleretretour.lotr.init.LOTRCreativeTabs.TAB_COMBAT));
        this.boltDamageFactor = 1.0 + Math.max(0.0, (material.toItemTier().getAttackDamageBonus() - 2.0f) * 0.1);
    }

    public int getMaxDrawTime() {
        return 15;
    }

    @Override
    public UseAction getUseAnimation(ItemStack stack) {
        return UseAction.BOW;
    }

    @Override
    public int getUseDuration(ItemStack stack) {
        return 72000;
    }

    private ItemStack findBolt(PlayerEntity player) {
        for (int i = 0; i < player.inventory.getContainerSize(); i++) {
            ItemStack s = player.inventory.getItem(i);
            if (s.getItem() instanceof LOTRItemCrossbowBolt) {
                return s;
            }
        }
        return ItemStack.EMPTY;
    }

    @Override
    public ActionResult<ItemStack> use(World world, PlayerEntity player, Hand hand) {
        ItemStack stack = player.getItemInHand(hand);
        if (findBolt(player).isEmpty() && !player.abilities.instabuild) {
            return ActionResult.fail(stack);
        }
        player.startUsingItem(hand);
        return ActionResult.consume(stack);
    }

    @Override
    public void releaseUsing(ItemStack stack, World world, LivingEntity entity, int timeLeft) {
        if (!(entity instanceof PlayerEntity)) {
            return;
        }
        PlayerEntity player = (PlayerEntity) entity;
        ItemStack bolt = findBolt(player);
        boolean creative = player.abilities.instabuild;
        if (bolt.isEmpty() && !creative) {
            return;
        }
        int useTicks = getUseDuration(stack) - timeLeft;
        float charge = (float) useTicks / getMaxDrawTime();
        charge = (charge * charge + charge * 2.0f) / 3.0f;
        charge = Math.min(charge, 1.0f);
        if (charge < 0.65f) {
            return; // une arbalete doit etre armee a fond, comme l'original
        }
        boolean poisoned = !bolt.isEmpty() && bolt.getItem() == LOTRItemsRanged.CROSSBOW_BOLT_POISONED.get();
        if (!world.isClientSide) {
            double damage = 2.0 * boltDamageFactor;
            LOTREntityCrossbowBolt entityBolt = new LOTREntityCrossbowBolt(world, player, damage, poisoned);
            entityBolt.shootFromRotation(player, player.xRot, player.yRot, 0.0f, charge * 4.0f, 0.5f);
            if (charge >= 1.0f) {
                entityBolt.setCritArrow(true);
            }
            stack.hurtAndBreak(1, player, p -> p.broadcastBreakEvent(player.getUsedItemHand()));
            world.addFreshEntity(entityBolt);
        }
        world.playSound(null, player.getX(), player.getY(), player.getZ(),
                SoundEvents.CROSSBOW_SHOOT, SoundCategory.PLAYERS, 1.0f,
                1.0f / (random.nextFloat() * 0.4f + 1.2f));
        if (!creative && !bolt.isEmpty()) {
            bolt.shrink(1);
        }
    }
}
