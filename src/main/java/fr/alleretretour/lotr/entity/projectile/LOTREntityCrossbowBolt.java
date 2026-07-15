package fr.alleretretour.lotr.entity.projectile;

import fr.alleretretour.lotr.init.LOTREntities;
import fr.alleretretour.lotr.init.LOTRItemsRanged;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.projectile.AbstractArrowEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.network.IPacket;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.world.World;
import net.minecraftforge.fml.network.NetworkHooks;

/**
 * Port de lotr.common.entity.projectile.LOTREntityCrossbowBolt.
 * Le carreau vole plus vite et plus droit qu'une fleche.
 */
public class LOTREntityCrossbowBolt extends AbstractArrowEntity {

    private boolean poisoned;

    public LOTREntityCrossbowBolt(EntityType<? extends LOTREntityCrossbowBolt> type, World world) {
        super(type, world);
    }

    public LOTREntityCrossbowBolt(World world, LivingEntity shooter, double damage, boolean poisoned) {
        super(LOTREntities.CROSSBOW_BOLT.get(), shooter, world);
        setBaseDamage(damage);
        this.poisoned = poisoned;
    }

    @Override
    protected void doPostHurtEffects(LivingEntity target) {
        super.doPostHurtEffects(target);
        if (poisoned && !level.isClientSide) {
            target.addEffect(new EffectInstance(Effects.POISON, 100, 0));
        }
    }

    @Override
    protected ItemStack getPickupItem() {
        return new ItemStack(poisoned
                ? LOTRItemsRanged.CROSSBOW_BOLT_POISONED.get()
                : LOTRItemsRanged.CROSSBOW_BOLT.get());
    }

    @Override
    public IPacket<?> getAddEntityPacket() {
        return NetworkHooks.getEntitySpawningPacket(this);
    }
}
