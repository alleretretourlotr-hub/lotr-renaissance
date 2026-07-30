package fr.alleretretour.lotr.entity.npc;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvents;
import net.minecraft.world.Explosion;
import net.minecraft.world.World;

/**
 * PORT de LOTREntityWargBombardier : warg porteur de bombe. Valeurs exactes du
 * Legacy - meche de 35 ticks (decompte a l'approche, remontee a la fuite),
 * explosion de (niveau + 1) x 4 de puissance, warg detruit.
 * Ne peut pas etre monte.
 */
public abstract class LOTREntityWargBombardier extends LOTREntityWarg {

    private static final DataParameter<Byte> BOMB_FUSE =
            EntityDataManager.defineId(LOTREntityWargBombardier.class, DataSerializers.BYTE);
    private static final DataParameter<Byte> BOMB_STRENGTH =
            EntityDataManager.defineId(LOTREntityWargBombardier.class, DataSerializers.BYTE);

    protected LOTREntityWargBombardier(EntityType<? extends LOTREntityWargBombardier> type,
                                       World world) {
        super(type, world);
    }

    @Override
    protected void defineSynchedData() {
        super.defineSynchedData();
        entityData.define(BOMB_FUSE, (byte) 35);
        entityData.define(BOMB_STRENGTH, (byte) 1);
    }

    public int getBombFuse() {
        return entityData.get(BOMB_FUSE);
    }

    public void setBombFuse(int i) {
        entityData.set(BOMB_FUSE, (byte) i);
    }

    public int getBombStrengthLevel() {
        return entityData.get(BOMB_STRENGTH);
    }

    public void setBombStrengthLevel(int i) {
        entityData.set(BOMB_STRENGTH, (byte) i);
    }

    @Override
    public void setTarget(@javax.annotation.Nullable LivingEntity target) {
        LivingEntity previous = getTarget();
        super.setTarget(target);
        if (target != null && previous == null && !level.isClientSide) {
            level.playSound(null, getX(), getY(), getZ(), SoundEvents.TNT_PRIMED,
                    SoundCategory.HOSTILE, 1.0f, 1.0f);
        }
    }

    @Override
    public void aiStep() {
        super.aiStep();
        if (level.isClientSide) {
            if (getBombFuse() < 35) {
                level.addParticle(ParticleTypes.SMOKE, getX(), getY() + 2.2, getZ(),
                        0.0, 0.0, 0.0);
            }
            return;
        }
        LivingEntity target = getTarget();
        if (target != null && target.isAlive() && distanceToSqr(target) < 9.0) {
            // PORT : la meche brule par paliers de 10 au-dessus de 20, puis 1 par tick
            int fuse = getBombFuse();
            if (fuse > 20) {
                setBombFuse(Math.max(fuse - 10, 20));
            } else if (fuse > 0) {
                setBombFuse(fuse - 1);
            } else {
                level.explode(this, getX(), getY(), getZ(),
                        (getBombStrengthLevel() + 1) * 4.0f, Explosion.Mode.NONE);
                remove();
            }
        } else if (getBombFuse() <= 20) {
            setBombFuse(Math.min(getBombFuse() + 10, 35));
        }
    }

    /** PORT : un bombardier ne se monte pas. */
    @Override
    public void setupAsUnitMount() {
        setPersistenceRequired();
    }

    @Override
    public void addAdditionalSaveData(CompoundNBT nbt) {
        super.addAdditionalSaveData(nbt);
        nbt.putByte("BombFuse", (byte) getBombFuse());
        nbt.putByte("BombStrengthLevel", (byte) getBombStrengthLevel());
    }

    @Override
    public void readAdditionalSaveData(CompoundNBT nbt) {
        super.readAdditionalSaveData(nbt);
        setBombFuse(nbt.getByte("BombFuse"));
        setBombStrengthLevel(nbt.getByte("BombStrengthLevel"));
    }
}
