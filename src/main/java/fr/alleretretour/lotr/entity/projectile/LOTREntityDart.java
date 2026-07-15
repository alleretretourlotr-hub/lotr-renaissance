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
 * Port de lotr.common.entity.projectile.LOTREntityDart (sarbacane Tauredain).
 */
public class LOTREntityDart extends AbstractArrowEntity {

    private boolean poisoned;

    public LOTREntityDart(EntityType<? extends LOTREntityDart> type, World world) {
        super(type, world);
    }

    public LOTREntityDart(World world, LivingEntity shooter, double damage, boolean poisoned) {
        super(LOTREntities.DART.get(), shooter, world);
        setBaseDamage(damage);
        this.poisoned = poisoned;
    }

    @Override
    protected void doPostHurtEffects(LivingEntity target) {
        super.doPostHurtEffects(target);
        if (poisoned && !level.isClientSide) {
            target.addEffect(new EffectInstance(Effects.POISON, 160, 0)); // 8 s comme l'original
        }
    }

    @Override
    protected ItemStack getPickupItem() {
        return new ItemStack(poisoned
                ? LOTRItemsRanged.TAUREDAIN_DART_POISONED.get()
                : LOTRItemsRanged.TAUREDAIN_DART.get());
    }

    @Override
    public IPacket<?> getAddEntityPacket() {
        return NetworkHooks.getEntitySpawningPacket(this);
    }
}
