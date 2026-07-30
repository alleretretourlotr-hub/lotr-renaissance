package fr.alleretretour.lotr.entity.animal;

import net.minecraft.entity.AgeableEntity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.ai.attributes.AttributeModifierMap;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.ai.goal.HurtByTargetGoal;
import net.minecraft.entity.ai.goal.LookAtGoal;
import net.minecraft.entity.ai.goal.MeleeAttackGoal;
import net.minecraft.entity.ai.goal.SwimGoal;
import net.minecraft.entity.ai.goal.WaterAvoidingRandomWalkingGoal;
import net.minecraft.entity.passive.AnimalEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.world.World;

import javax.annotation.Nullable;

/** PORT de LOTREntityRhino : monture des semi-trolls (PV 40, degats 4.0). */
public class LOTREntityRhino extends AnimalEntity implements LOTRMountArmored {

    public LOTREntityRhino(EntityType<? extends LOTREntityRhino> type, World world) {
        super(type, world);
    }

    @Override
    protected void defineSynchedData() {
        super.defineSynchedData();
        entityData.define(MOUNT_ARMOR, net.minecraft.item.ItemStack.EMPTY);
    }

    public static AttributeModifierMap.MutableAttribute createAttributes() {
        return AnimalEntity.createMobAttributes()
                .add(Attributes.MAX_HEALTH, 40.0)
                .add(Attributes.MOVEMENT_SPEED, 0.24)
                .add(Attributes.ATTACK_DAMAGE, 4.0)
                .add(Attributes.ARMOR, 0.0)
                .add(Attributes.KNOCKBACK_RESISTANCE, 0.6);
    }

    @Override
    protected void registerGoals() {
        goalSelector.addGoal(0, new SwimGoal(this));
        goalSelector.addGoal(1, new fr.alleretretour.lotr.entity.ai.LOTRMountGoal(this, 1.3));
        goalSelector.addGoal(2, new MeleeAttackGoal(this, 1.2, false));
        goalSelector.addGoal(5, new WaterAvoidingRandomWalkingGoal(this, 0.7));
        goalSelector.addGoal(6, new LookAtGoal(this, PlayerEntity.class, 8.0f));
        targetSelector.addGoal(1, new HurtByTargetGoal(this));
    }

    public void setupAsUnitMount() {
        setPersistenceRequired();
    }

    @Nullable
    @Override
    public AgeableEntity getBreedOffspring(net.minecraft.world.server.ServerWorld world,
                                           AgeableEntity partner) {
        return fr.alleretretour.lotr.init.LOTREntities.RHINO.get().create(world);
    }

    @Override
    public void addAdditionalSaveData(net.minecraft.nbt.CompoundNBT nbt) {
        super.addAdditionalSaveData(nbt);
        if (!getMountArmor().isEmpty()) {
            nbt.put("MountArmor", getMountArmor().save(new net.minecraft.nbt.CompoundNBT()));
        }
    }

    @Override
    public void readAdditionalSaveData(net.minecraft.nbt.CompoundNBT nbt) {
        super.readAdditionalSaveData(nbt);
        if (nbt.contains("MountArmor")) {
            setMountArmor(net.minecraft.item.ItemStack.of(nbt.getCompound("MountArmor")));
        }
    }

    // ===== ARMURE DE MONTURE (PORT LOTRNPCMount) =====
    private static final net.minecraft.network.datasync.DataParameter<net.minecraft.item.ItemStack>
            MOUNT_ARMOR = net.minecraft.network.datasync.EntityDataManager.defineId(
            LOTREntityRhino.class, net.minecraft.network.datasync.DataSerializers.ITEM_STACK);

    @Override
    public void setMountArmor(net.minecraft.item.ItemStack stack) {
        entityData.set(MOUNT_ARMOR, stack.copy());
        net.minecraft.entity.ai.attributes.ModifiableAttributeInstance attr =
                getAttribute(net.minecraft.entity.ai.attributes.Attributes.ARMOR);
        if (attr != null) {
            attr.setBaseValue(stack.getItem() instanceof fr.alleretretour.lotr.item.LOTRItemMountArmor
                    ? ((fr.alleretretour.lotr.item.LOTRItemMountArmor) stack.getItem())
                            .getProtection()
                    : 0.0);
        }
    }

    @Override
    public net.minecraft.item.ItemStack getMountArmor() {
        return entityData.get(MOUNT_ARMOR);
    }
}
