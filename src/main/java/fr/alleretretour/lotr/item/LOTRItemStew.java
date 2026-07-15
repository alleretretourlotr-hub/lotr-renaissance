package fr.alleretretour.lotr.item;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.world.World;

/**
 * Port de lotr.common.item.LOTRItemStew : ragouts et soupes,
 * pile de 1, rendent le bol apres consommation.
 */
public class LOTRItemStew extends LOTRItemFood {

    public LOTRItemStew(int heal, float saturation, boolean wolfFood) {
        super(buildProps(heal, saturation, wolfFood).stacksTo(1));
    }

    @Override
    public ItemStack finishUsingItem(ItemStack stack, World world, LivingEntity entity) {
        ItemStack result = super.finishUsingItem(stack, world, entity);
        if (entity instanceof PlayerEntity && ((PlayerEntity) entity).abilities.instabuild) {
            return result;
        }
        return result.isEmpty() ? new ItemStack(Items.BOWL) : result;
    }
}
