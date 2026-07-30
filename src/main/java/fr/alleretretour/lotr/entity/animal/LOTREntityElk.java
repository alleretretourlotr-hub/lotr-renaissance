package fr.alleretretour.lotr.entity.animal;

import net.minecraft.entity.EntityType;
import net.minecraft.world.World;

/**
 * PORT de LOTREntityElk : monture des Elfes sylvains. Comme dans le Legacy,
 * l'elan etend le cheval ; PV multiplies par 1.0-1.5 a l'apparition.
 */
public class LOTREntityElk extends LOTREntityHorse {

    public LOTREntityElk(EntityType<? extends LOTREntityElk> type, World world) {
        super(type, world);
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
        double hp = getAttribute(net.minecraft.entity.ai.attributes.Attributes.MAX_HEALTH)
                .getBaseValue() * (1.0 + random.nextFloat() * 0.5);
        getAttribute(net.minecraft.entity.ai.attributes.Attributes.MAX_HEALTH).setBaseValue(hp);
        setHealth((float) hp);
        return result;
    }
}
