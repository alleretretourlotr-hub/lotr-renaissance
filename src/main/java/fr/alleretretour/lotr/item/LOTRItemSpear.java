package fr.alleretretour.lotr.item;

import fr.alleretretour.lotr.entity.projectile.LOTREntityThrownWeapon;
import fr.alleretretour.lotr.init.LOTREntities;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.UseAction;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvents;
import net.minecraft.world.World;
import net.minecraft.entity.LivingEntity;

/**
 * Port de lotr.common.item.LOTRItemSpear.
 * Melee : epee -1 degat. Jet : clic droit maintenu (20 ticks de charge),
 * degats de jet = degats de l'arme * 0.7, critique a pleine charge,
 * l'objet est consomme (recuperable en le ramassant), comme l'original.
 */
public class LOTRItemSpear extends LOTRItemSword {

    public LOTRItemSpear(LOTRMaterial material) {
        super(material, 2, -2.6f, false); // epee (3) - 1
    }

    public int getMaxDrawTime() {
        return 20;
    }

    public float getThrownDamage(LOTRMaterial ignored, ItemStack stack) {
        // degats de base de l'arme * 0.7 (les enchantements sont appliques par l'entite)
        return (getDamage() + 1.0f) * 0.7f;
    }

    @Override
    public UseAction getUseAnimation(ItemStack stack) {
        return UseAction.BOW;
    }

    @Override
    public int getUseDuration(ItemStack stack) {
        return 72000;
    }

    @Override
    public ActionResult<ItemStack> use(World world, PlayerEntity player, Hand hand) {
        player.startUsingItem(hand);
        return ActionResult.consume(player.getItemInHand(hand));
    }

    @Override
    public void releaseUsing(ItemStack stack, World world, LivingEntity entity, int timeLeft) {
        if (!(entity instanceof PlayerEntity)) {
            return;
        }
        PlayerEntity player = (PlayerEntity) entity;
        int useTicks = getUseDuration(stack) - timeLeft;
        float charge = (float) useTicks / getMaxDrawTime();
        if (charge < 0.1f) {
            return;
        }
        charge = (charge * charge + charge * 2.0f) / 3.0f;
        charge = Math.min(charge, 1.0f);

        if (!world.isClientSide) {
            LOTREntityThrownWeapon spear = new LOTREntityThrownWeapon(
                    LOTREntities.THROWN_WEAPON.get(), world, player, stack.copy(), getThrownDamage(null, stack));
            spear.shootFromRotation(player, player.xRot, player.yRot, 0.0f, charge * 2.0f, 1.0f);
            if (charge >= 1.0f) {
                spear.setCritArrow(true);
            }
            if (player.abilities.instabuild) {
                spear.pickup = LOTREntityThrownWeapon.PickupStatus.CREATIVE_ONLY;
            }
            world.addFreshEntity(spear);
        }
        world.playSound(null, player.getX(), player.getY(), player.getZ(),
                SoundEvents.ARROW_SHOOT, SoundCategory.PLAYERS,
                1.0f, 1.0f / (random.nextFloat() * 0.4f + 1.2f) + charge * 0.5f);
        if (!player.abilities.instabuild) {
            stack.shrink(1);
        }
    }
}
