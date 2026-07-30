package fr.alleretretour.lotr.entity.npc;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.attributes.AttributeModifierMap;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.world.World;

import javax.annotation.Nullable;

/**
 * PORT de LOTREntityMirkwoodSpider (+ LOTREntitySpiderBase) : araignee de la
 * Foret Noire, monture des orques de Dol Guldur.
 * Valeurs exactes du Legacy : taille 0-2 (PV 12 + taille*6, vitesse
 * 0.35 - taille*0.03, degats 2 + taille, echelle 0.5 + taille/2),
 * venin tire selon getRandomSpiderType (aucun / lenteur / poison).
 */
public class LOTREntityMirkwoodSpider extends LOTREntityNPC {

    public static final int VENOM_NONE = 0;
    public static final int VENOM_SLOWNESS = 1;
    public static final int VENOM_POISON = 2;

    private static final DataParameter<Byte> SCALE =
            EntityDataManager.defineId(LOTREntityMirkwoodSpider.class, DataSerializers.BYTE);
    private static final DataParameter<Byte> VENOM =
            EntityDataManager.defineId(LOTREntityMirkwoodSpider.class, DataSerializers.BYTE);

    public LOTREntityMirkwoodSpider(EntityType<? extends LOTREntityMirkwoodSpider> type,
                                    World world) {
        super(type, world);
    }

    @Override
    protected void defineSynchedData() {
        super.defineSynchedData();
        entityData.define(SCALE, (byte) 0);
        entityData.define(VENOM, (byte) 0);
    }

    @Override
    public fr.alleretretour.lotr.fac.LOTRFaction getFaction() {
        return fr.alleretretour.lotr.fac.LOTRFaction.DOL_GULDUR;  // PORT exact
    }

    public static AttributeModifierMap.MutableAttribute createAttributes() {
        return createNPCAttributes()
                .add(Attributes.MAX_HEALTH, 12.0)
                .add(Attributes.MOVEMENT_SPEED, 0.35)
                .add(Attributes.ATTACK_DAMAGE, 2.0);
    }

    @Override
    protected void registerGoals() {
        super.registerGoals();  // socle NPC : factions, embauche, defense
        goalSelector.addGoal(1, new fr.alleretretour.lotr.entity.ai.LOTRMountGoal(this, 1.3));
    }

    @Nullable
    @Override
    public net.minecraft.entity.ILivingEntityData finalizeSpawn(
            net.minecraft.world.IServerWorld world,
            net.minecraft.world.DifficultyInstance difficulty,
            net.minecraft.entity.SpawnReason reason,
            @Nullable net.minecraft.entity.ILivingEntityData data,
            @Nullable CompoundNBT nbt) {
        // PORT : getRandomSpiderScale = rand.nextInt(3)
        setSpiderScale(random.nextInt(3));
        // PORT : getRandomSpiderType = nextBoolean() ? 0 : 1 + nextInt(2)
        entityData.set(VENOM, (byte) (random.nextBoolean() ? 0 : 1 + random.nextInt(2)));
        return super.finalizeSpawn(world, difficulty, reason, data, nbt);
    }

    public void setSpiderScale(int scale) {
        entityData.set(SCALE, (byte) scale);
        getAttribute(Attributes.MAX_HEALTH).setBaseValue(12.0 + scale * 6.0);
        getAttribute(Attributes.MOVEMENT_SPEED).setBaseValue(0.35 - scale * 0.03);
        getAttribute(Attributes.ATTACK_DAMAGE).setBaseValue(2.0 + scale);
        setHealth(getMaxHealth());
        refreshDimensions();
    }

    public int getSpiderScale() {
        return entityData.get(SCALE);
    }

    /** PORT exact : 0.5 + taille / 2. */
    public float getSpiderScaleAmount() {
        return 0.5f + getSpiderScale() / 2.0f;
    }

    public int getSpiderType() {
        return entityData.get(VENOM);
    }

    @Override
    public boolean doHurtTarget(net.minecraft.entity.Entity entity) {
        if (!super.doHurtTarget(entity)) {
            return false;
        }
        if (entity instanceof LivingEntity) {
            int id = level.getDifficulty().getId();
            int duration = id * (id + 5) / 2;
            if (duration > 0) {
                if (getSpiderType() == VENOM_SLOWNESS) {
                    ((LivingEntity) entity).addEffect(
                            new EffectInstance(Effects.MOVEMENT_SLOWDOWN, duration * 20, 0));
                } else if (getSpiderType() == VENOM_POISON) {
                    ((LivingEntity) entity).addEffect(
                            new EffectInstance(Effects.POISON, duration * 20, 0));
                }
            }
        }
        return true;
    }

    /** Les araignees grimpent aux murs (comme la vanilla). */
    @Override
    public boolean onClimbable() {
        return horizontalCollision;
    }

    public void setupAsUnitMount() {
        setPersistenceRequired();
    }

    @Override
    public void addAdditionalSaveData(CompoundNBT nbt) {
        super.addAdditionalSaveData(nbt);
        nbt.putByte("SpiderScale", entityData.get(SCALE));
        nbt.putByte("SpiderVenom", entityData.get(VENOM));
    }

    @Override
    public void readAdditionalSaveData(CompoundNBT nbt) {
        super.readAdditionalSaveData(nbt);
        entityData.set(VENOM, nbt.getByte("SpiderVenom"));
        setSpiderScale(nbt.getByte("SpiderScale"));
    }

}
