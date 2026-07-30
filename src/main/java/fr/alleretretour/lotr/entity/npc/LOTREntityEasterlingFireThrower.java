package fr.alleretretour.lotr.entity.npc;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.util.SoundEvents;
import net.minecraft.world.World;

/**
 * PORT de LOTREntityEasterlingFireThrower : guerrier oriental lanceur de pots
 * de feu. Herite du guerrier (PV, armure, alignement) et remplace l'attaque a
 * distance par le jet de pot de feu, comme dans le Legacy.
 */
public class LOTREntityEasterlingFireThrower extends LOTREntityEasterlingWarrior {

    public LOTREntityEasterlingFireThrower(
            EntityType<? extends LOTREntityEasterlingFireThrower> type, World world) {
        super(type, world);
    }

    @Override
    protected boolean isRangedNPC() {
        return true;
    }

    @Override
    public void performRangedAttack(LivingEntity target, float power) {
        fr.alleretretour.lotr.entity.projectile.LOTREntityFirePot pot =
                new fr.alleretretour.lotr.entity.projectile.LOTREntityFirePot(level, this);
        double dx = target.getX() - getX();
        double dy = target.getY(0.3333333333333333) - pot.getY();
        double dz = target.getZ() - getZ();
        double dist = Math.sqrt(dx * dx + dz * dz);
        pot.shoot(dx, dy + dist * 0.2, dz, 1.0f, 8.0f);
        playSound(SoundEvents.SNOWBALL_THROW, 1.0f,
                1.0f / (getRandom().nextFloat() * 0.4f + 0.8f));
        level.addFreshEntity(pot);
    }

    @javax.annotation.Nullable
    @Override
    public net.minecraft.entity.ILivingEntityData finalizeSpawn(
            net.minecraft.world.IServerWorld world,
            net.minecraft.world.DifficultyInstance difficulty,
            net.minecraft.entity.SpawnReason reason,
            @javax.annotation.Nullable net.minecraft.entity.ILivingEntityData data,
            @javax.annotation.Nullable net.minecraft.nbt.CompoundNBT nbt) {
        net.minecraft.entity.ILivingEntityData result =
                super.finalizeSpawn(world, difficulty, reason, data, nbt);
        // PORT : npcItemsInv.setRangedWeapon(rhunFirePot)
        setItemSlot(net.minecraft.inventory.EquipmentSlotType.MAINHAND,
                new net.minecraft.item.ItemStack(
                        fr.alleretretour.lotr.init.LOTRItemsRhun.RHUN_FIRE_POT.get()));
        return result;
    }
}
