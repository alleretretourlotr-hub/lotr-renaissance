package fr.alleretretour.lotr.item;

import fr.alleretretour.lotr.entity.projectile.LOTREntityFirePot;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.stats.Stats;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvents;
import net.minecraft.world.World;

/** PORT de LOTRItemFirePot : se lance comme une boule de neige. */
public class LOTRItemFirePot extends Item {

    public LOTRItemFirePot(Properties properties) {
        super(properties);
    }

    @Override
    public ActionResult<ItemStack> use(World world, PlayerEntity player, Hand hand) {
        ItemStack stack = player.getItemInHand(hand);
        world.playSound(null, player.getX(), player.getY(), player.getZ(),
                SoundEvents.SNOWBALL_THROW, SoundCategory.NEUTRAL, 0.5f,
                0.4f / (world.random.nextFloat() * 0.4f + 0.8f));
        if (!world.isClientSide) {
            LOTREntityFirePot pot = new LOTREntityFirePot(world, player);
            pot.setItem(stack);
            pot.shootFromRotation(player, player.xRot, player.yRot, 0.0f, 1.5f, 1.0f);
            world.addFreshEntity(pot);
        }
        player.awardStat(Stats.ITEM_USED.get(this));
        if (!player.abilities.instabuild) {
            stack.shrink(1);
        }
        return ActionResult.sidedSuccess(stack, world.isClientSide());
    }
}
