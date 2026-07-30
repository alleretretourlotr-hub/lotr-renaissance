package fr.alleretretour.lotr.entity.npc;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.ai.attributes.AttributeModifierMap;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.world.World;

import javax.annotation.Nullable;

/**
 * PORT de lotr.common.entity.npc.LOTREntityWarg : monture des Hommes des
 * collines d'Angmar et des orques. Types de pelage et tirage exacts du
 * Legacy (1/500 blanc, 1/20 noir, 1/3 gris, sinon brun).
 */
public abstract class LOTREntityWarg extends LOTREntityNPC
        implements fr.alleretretour.lotr.entity.animal.LOTRMountArmored {

    /** PORT de LOTREntityWarg.WargType. */
    public enum WargType {
        BROWN, GREY, BLACK, WHITE;

        public String textureName() {
            return name().toLowerCase(java.util.Locale.ROOT);
        }
    }

    private static final DataParameter<Byte> TYPE =
            EntityDataManager.defineId(LOTREntityWarg.class, DataSerializers.BYTE);

    public LOTREntityWarg(EntityType<? extends LOTREntityWarg> type, World world) {
        super(type, world);
    }

    @Override
    protected void defineSynchedData() {
        super.defineSynchedData();
        entityData.define(MOUNT_ARMOR, net.minecraft.item.ItemStack.EMPTY);
        entityData.define(TYPE, (byte) 0);
    }

    public static AttributeModifierMap.MutableAttribute createAttributes() {
        return createNPCAttributes()
                .add(Attributes.MAX_HEALTH, 26.0)
                .add(Attributes.MOVEMENT_SPEED, 0.22)
                .add(Attributes.ATTACK_DAMAGE, 4.0)
                .add(Attributes.ARMOR, 0.0)
                .add(Attributes.FOLLOW_RANGE, 32.0);
    }

    @Override
    protected void registerGoals() {
        super.registerGoals();  // socle NPC : factions, embauche, defense
        goalSelector.addGoal(1, new fr.alleretretour.lotr.entity.ai.LOTRMountGoal(this, 1.4));
    }

    @Nullable
    @Override
    public net.minecraft.entity.ILivingEntityData finalizeSpawn(
            net.minecraft.world.IServerWorld world,
            net.minecraft.world.DifficultyInstance difficulty,
            net.minecraft.entity.SpawnReason reason,
            @Nullable net.minecraft.entity.ILivingEntityData data,
            @Nullable CompoundNBT nbt) {
        // PORT exact du tirage Legacy
        if (random.nextInt(500) == 0) {
            setWargType(WargType.WHITE);
        } else if (random.nextInt(20) == 0) {
            setWargType(WargType.BLACK);
        } else if (random.nextInt(3) == 0) {
            setWargType(WargType.GREY);
        } else {
            setWargType(WargType.BROWN);
        }
        // PV du Legacy : aleatoires entre 20 et 32
        double hp = 20 + random.nextInt(13);
        getAttribute(Attributes.MAX_HEALTH).setBaseValue(hp);
        setHealth((float) hp);
        getAttribute(Attributes.ATTACK_DAMAGE).setBaseValue(3 + random.nextInt(3));
        return super.finalizeSpawn(world, difficulty, reason, data, nbt);
    }

    public void setWargType(WargType type) {
        entityData.set(TYPE, (byte) type.ordinal());
    }

    public WargType getWargType() {
        byte b = entityData.get(TYPE);
        WargType[] values = WargType.values();
        return b >= 0 && b < values.length ? values[b] : WargType.BROWN;
    }

    /** PORT exact : la queue s'abaisse quand le warg est blesse. */
    public float getTailRotation() {
        float f = (getMaxHealth() - getHealth()) / getMaxHealth();
        return f * -1.2f;
    }

    public void setupAsUnitMount() {
        setPersistenceRequired();
    }

    @Override
    public void addAdditionalSaveData(CompoundNBT nbt) {
        super.addAdditionalSaveData(nbt);
        if (!getMountArmor().isEmpty()) {
            nbt.put("MountArmor", getMountArmor().save(new CompoundNBT()));
        }
        nbt.putByte("WargType", entityData.get(TYPE));
    }

    @Override
    public void readAdditionalSaveData(CompoundNBT nbt) {
        super.readAdditionalSaveData(nbt);
        if (nbt.contains("MountArmor")) {
            setMountArmor(net.minecraft.item.ItemStack.of(nbt.getCompound("MountArmor")));
        }
        entityData.set(TYPE, nbt.getByte("WargType"));
    }


    // ===== ARMURE DE MONTURE (PORT LOTRNPCMount) =====
    private static final net.minecraft.network.datasync.DataParameter<net.minecraft.item.ItemStack>
            MOUNT_ARMOR = net.minecraft.network.datasync.EntityDataManager.defineId(
            LOTREntityWarg.class, net.minecraft.network.datasync.DataSerializers.ITEM_STACK);

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
