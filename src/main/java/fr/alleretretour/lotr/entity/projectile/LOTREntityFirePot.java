package fr.alleretretour.lotr.entity.projectile;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.projectile.ProjectileItemEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.network.IPacket;
import net.minecraft.particles.IParticleData;
import net.minecraft.particles.ItemParticleData;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.util.DamageSource;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.EntityRayTraceResult;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;
import net.minecraftforge.fml.network.NetworkHooks;

import java.util.List;

/**
 * PORT de lotr.common.entity.projectile.LOTREntityFirePot : pot de feu du
 * Rhun. Valeurs exactes du Legacy : gravite 0.04, rayon 3.0, 3 degats sur la
 * cible touchee et 1 sur les autres, feu 2+rand(3) (+2+rand(3) sur la cible).
 */
public class LOTREntityFirePot extends ProjectileItemEntity {

    public LOTREntityFirePot(EntityType<? extends LOTREntityFirePot> type, World world) {
        super(type, world);
    }

    public LOTREntityFirePot(World world, LivingEntity thrower) {
        super(fr.alleretretour.lotr.init.LOTREntities.FIRE_POT.get(), thrower, world);
    }

    @Override
    protected Item getDefaultItem() {
        return fr.alleretretour.lotr.init.LOTRItemsRhun.RHUN_FIRE_POT.get();
    }

    @Override
    protected float getGravity() {
        return 0.04f;  // PORT exact
    }

    @Override
    protected void onHit(RayTraceResult result) {
        super.onHit(result);
        if (level.isClientSide) {
            return;
        }
        net.minecraft.entity.Entity hit = result instanceof EntityRayTraceResult
                ? ((EntityRayTraceResult) result).getEntity() : null;
        double range = 3.0;
        List<LivingEntity> around = level.getEntitiesOfClass(LivingEntity.class,
                getBoundingBox().inflate(range, range, range));
        if (hit instanceof LivingEntity && !around.contains(hit)) {
            around.add((LivingEntity) hit);
        }
        for (LivingEntity entity : around) {
            float damage = entity == hit ? 3.0f : 1.0f;
            if (!entity.hurt(DamageSource.thrown(this, getOwner()), damage)) {
                continue;
            }
            int fire = 2 + random.nextInt(3);
            if (entity == hit) {
                fire += 2 + random.nextInt(3);
            }
            entity.setSecondsOnFire(fire);
        }
        level.playSound(null, getX(), getY(), getZ(), SoundEvents.GLASS_BREAK,
                SoundCategory.NEUTRAL, 1.0f,
                (random.nextFloat() - random.nextFloat()) * 0.2f + 1.0f);
        level.broadcastEntityEvent(this, (byte) 3);
        remove();
    }

    /** Particules de bris (equivalent des blockcrack du Legacy). */
    @Override
    public void handleEntityEvent(byte id) {
        if (id == 3) {
            IParticleData particle = new ItemParticleData(ParticleTypes.ITEM, getItem());
            for (int i = 0; i < 8; i++) {
                level.addParticle(particle, getX(), getY(), getZ(),
                        (random.nextDouble() - 0.5) * 0.2,
                        random.nextDouble() * 0.2,
                        (random.nextDouble() - 0.5) * 0.2);
            }
        }
    }

    @Override
    public IPacket<?> getAddEntityPacket() {
        return NetworkHooks.getEntitySpawningPacket(this);
    }
}
