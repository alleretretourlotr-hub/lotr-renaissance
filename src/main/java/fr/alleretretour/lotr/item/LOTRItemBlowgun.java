package fr.alleretretour.lotr.item;

import fr.alleretretour.lotr.entity.projectile.LOTREntityDart;
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
 * Port de lotr.common.item.LOTRItemBlowgun (sarbacane Tauredain).
 * Tir rapide (10 ticks), degats faibles, mais les flechettes empoisonnees
 * infligent 8 s de poison.
 */
public class LOTRItemBlowgun extends Item {

    public LOTRItemBlowgun(LOTRMaterial material) {
        super(new Properties()
                .durability((int) (material.toItemTier().getUses() * 1.5f))
                .tab(fr.alleretretour.lotr.init.LOTRCreativeTabs.TAB_COMBAT));
    }

    public int getMaxDrawTime() {
        return 10;
    }

    @Override
    public UseAction getUseAnimation(ItemStack stack) {
        return UseAction.BOW;
    }

    @Override
    public int getUseDuration(ItemStack stack) {
        return 72000;
    }

    private ItemStack findDart(PlayerEntity player) {
        for (int i = 0; i < player.inventory.getContainerSize(); i++) {
            ItemStack s = player.inventory.getItem(i);
            if (s.getItem() instanceof LOTRItemDart) {
                return s;
            }
        }
        return ItemStack.EMPTY;
    }

    @Override
    public ActionResult<ItemStack> use(World world, PlayerEntity player, Hand hand) {
        ItemStack stack = player.getItemInHand(hand);
        if (findDart(player).isEmpty() && !player.abilities.instabuild) {
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
        ItemStack dart = findDart(player);
        boolean creative = player.abilities.instabuild;
        if (dart.isEmpty() && !creative) {
            return;
        }
        int useTicks = getUseDuration(stack) - timeLeft;
        float charge = Math.min((float) useTicks / getMaxDrawTime(), 1.0f);
        if (charge < 0.5f) {
            return;
        }
        boolean poisoned = !dart.isEmpty() && dart.getItem() == LOTRItemsRanged.TAUREDAIN_DART_POISONED.get();
        if (!world.isClientSide) {
            LOTREntityDart entityDart = new LOTREntityDart(world, player, 1.5, poisoned);
            entityDart.shootFromRotation(player, player.xRot, player.yRot, 0.0f, charge * 2.5f, 1.0f);
            stack.hurtAndBreak(1, player, p -> p.broadcastBreakEvent(player.getUsedItemHand()));
            world.addFreshEntity(entityDart);
        }
        world.playSound(null, player.getX(), player.getY(), player.getZ(),
                SoundEvents.SNOWBALL_THROW, SoundCategory.PLAYERS, 1.0f, 1.4f);
        if (!creative && !dart.isEmpty()) {
            dart.shrink(1);
        }
    }
}
