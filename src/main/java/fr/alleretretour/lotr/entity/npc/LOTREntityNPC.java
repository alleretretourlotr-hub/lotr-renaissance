package fr.alleretretour.lotr.entity.npc;

import fr.alleretretour.lotr.fac.LOTRFaction;
import net.minecraft.entity.CreatureEntity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.MobEntity;
import net.minecraft.entity.ai.attributes.AttributeModifierMap;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.ai.goal.HurtByTargetGoal;
import net.minecraft.entity.ai.goal.LookAtGoal;
import net.minecraft.entity.ai.goal.LookRandomlyGoal;
import net.minecraft.entity.ai.goal.MeleeAttackGoal;
import net.minecraft.entity.ai.goal.NearestAttackableTargetGoal;
import net.minecraft.entity.ai.goal.SwimGoal;
import net.minecraft.entity.ai.goal.WaterAvoidingRandomWalkingGoal;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.world.World;

import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;

import javax.annotation.Nullable;
import java.util.Optional;
import java.util.UUID;

/**
 * PORT du socle lotr.common.entity.npc.LOTREntityNPC (1664 lignes -> essentiel).
 * Un PNJ a une FACTION ; il attaque les joueurs dont l'alignement avec sa
 * faction est negatif (hostiles), et les PNJ des factions ennemies.
 * Le kill rapporte l'alignement via LOTRAlignmentBonuses (bonus par type).
 *
 * SYSTEME D'EMBAUCHE (v1) :
 * - un PNJ peut etre engage par un joueur (hirerUUID, persiste en NBT) ;
 * - il suit son commanditaire (LOTRFollowHirerGoal), defend et venge celui-ci
 *   (LOTRHirerDefenseGoal), ne le cible jamais, ni les autres unites du meme
 *   commanditaire ;
 * - clic du commanditaire : alterne suivre / tenir la position ;
 * - il ne despawn jamais (setPersistenceRequired).
 *
 * A venir dans les lots suivants : montures, marchands ambulants, mini-quetes.
 */
public abstract class LOTREntityNPC extends CreatureEntity
        implements net.minecraft.entity.IRangedAttackMob {

    private static final DataParameter<Boolean> HIRED =
            EntityDataManager.defineId(LOTREntityNPC.class, DataSerializers.BOOLEAN);
    private static final DataParameter<Boolean> HALTED =
            EntityDataManager.defineId(LOTREntityNPC.class, DataSerializers.BOOLEAN);
    private static final DataParameter<Optional<UUID>> HIRER =
            EntityDataManager.defineId(LOTREntityNPC.class, DataSerializers.OPTIONAL_UUID);

    @Nullable
    private UUID hirerUUID;

    @Override
    protected void defineSynchedData() {
        super.defineSynchedData();
        entityData.define(HIRED, false);
        entityData.define(HALTED, false);
        entityData.define(HIRER, Optional.empty());
    }

    protected LOTREntityNPC(EntityType<? extends LOTREntityNPC> type, World world) {
        super(type, world);
    }

    /** La faction de ce PNJ - pilote l'IA de ciblage et l'alignement. */
    public abstract LOTRFaction getFaction();

    // ==================== EMBAUCHE ====================

    public boolean isHired() {
        return entityData.get(HIRED);
    }

    @Nullable
    public UUID getHirerUUID() {
        return hirerUUID;
    }

    /** Le commanditaire s'il est en ligne et dans ce monde, sinon null. */
    @Nullable
    public PlayerEntity getHirer() {
        return hirerUUID == null ? null : level.getPlayerByUUID(hirerUUID);
    }

    public void hireBy(PlayerEntity player) {
        this.hirerUUID = player.getUUID();
        entityData.set(HIRED, true);
        entityData.set(HALTED, false);
        entityData.set(HIRER, Optional.of(player.getUUID()));
        setPersistenceRequired();
    }

    /** Rend l'unite a la vie civile (elle redevient un PNJ de faction normal). */
    public void dismiss() {
        this.hirerUUID = null;
        entityData.set(HIRED, false);
        entityData.set(HALTED, false);
        entityData.set(HIRER, Optional.empty());
        setTarget(null);
    }

    public boolean isHalted() {
        return entityData.get(HALTED);
    }

    public void setHalted(boolean value) {
        entityData.set(HALTED, value);
        if (value) {
            getNavigation().stop();
        }
    }

    /** Cote client : le commanditaire synchronise (pour ouvrir l'interface). */
    public boolean isHiredByClient(PlayerEntity player) {
        return entityData.get(HIRER).map(u -> u.equals(player.getUUID())).orElse(false);
    }

    /** Fait parler l'unite depuis sa banque hired (via l'interface de gestion). */
    public void speakHiredTo(PlayerEntity player) {
        String bank = getSpeechBank();
        if (bank != null && !level.isClientSide) {
            fr.alleretretour.lotr.fac.LOTRSpeech.speak(this, bank + "/hired", player,
                    getFaction().color);
        }
    }

    public boolean isHiredBy(LivingEntity entity) {
        return hirerUUID != null && hirerUUID.equals(entity.getUUID());
    }

    /** true si l'autre PNJ est engage par le meme commanditaire. */
    public boolean isAlliedHiredUnit(LOTREntityNPC other) {
        return hirerUUID != null && hirerUUID.equals(other.hirerUUID);
    }

    @Override
    public void addAdditionalSaveData(CompoundNBT nbt) {
        super.addAdditionalSaveData(nbt);
        if (hirerUUID != null) {
            nbt.putUUID("LOTRHirer", hirerUUID);
        }
        nbt.putBoolean("LOTRHalted", isHalted());
    }

    @Override
    public void readAdditionalSaveData(CompoundNBT nbt) {
        super.readAdditionalSaveData(nbt);
        if (nbt.hasUUID("LOTRHirer")) {
            hirerUUID = nbt.getUUID("LOTRHirer");
            entityData.set(HIRED, true);
            entityData.set(HIRER, Optional.of(hirerUUID));
        }
        entityData.set(HALTED, nbt.getBoolean("LOTRHalted"));
    }

    // ==================== DISCOURS / INTERACTION ====================

    /**
     * Banque de discours du PNJ (chemin sous assets/lotr/speech/), null = muet.
     * Le suffixe friendly/neutral/hostile/hired est choisi selon le contexte.
     */
    protected String getSpeechBank() {
        return null;
    }

    @Override
    protected net.minecraft.util.ActionResultType mobInteract(
            PlayerEntity player, net.minecraft.util.Hand hand) {
        if (hand != net.minecraft.util.Hand.MAIN_HAND) {
            return super.mobInteract(player, hand);
        }
        // Commanditaire : ouvrir l'interface de gestion (cote client)
        if (level.isClientSide ? isHiredByClient(player) : isHiredBy(player)) {
            if (level.isClientSide) {
                net.minecraftforge.fml.DistExecutor.unsafeRunWhenOn(
                        net.minecraftforge.api.distmarker.Dist.CLIENT,
                        () -> () -> fr.alleretretour.lotr.client.LOTRClientHooks
                                .openHiredScreen(this));
                return net.minecraft.util.ActionResultType.SUCCESS;
            }
            return net.minecraft.util.ActionResultType.CONSUME;
        }
        String bank = getSpeechBank();
        if (bank != null && !level.isClientSide) {
            float alignment = fr.alleretretour.lotr.fac.LOTRPlayerDataProvider
                    .get(player).getAlignment(getFaction());
            String suffix;
            if (alignment < 0.0f) {
                suffix = "hostile";
            } else if (alignment >= 100.0f) {
                suffix = "friendly";
            } else {
                suffix = "neutral";
            }
            // repli : neutral absent -> friendly (banques a 2 fichiers)
            fr.alleretretour.lotr.fac.LOTRSpeech.speak(this, bank + "/" + suffix, player,
                    getFaction().color);
            return net.minecraft.util.ActionResultType.CONSUME;
        }
        return super.mobInteract(player, hand);
    }

    // ==================== COMBAT ====================

    /** true pour les archers : IA de tir a l'arc au lieu de la melee. */
    protected boolean isRangedNPC() {
        return false;
    }

    @Override
    public void performRangedAttack(net.minecraft.entity.LivingEntity target, float power) {
        net.minecraft.item.ItemStack held = getMainHandItem();
        fr.alleretretour.lotr.entity.ai.LOTRRangedWeaponKind kind =
                fr.alleretretour.lotr.entity.ai.LOTRRangedWeaponKind.of(held);
        net.minecraft.entity.projectile.AbstractArrowEntity projectile;
        float velocity;
        net.minecraft.util.SoundEvent sound;
        switch (kind) {
            case CROSSBOW:
                projectile = new fr.alleretretour.lotr.entity.projectile.LOTREntityCrossbowBolt(
                        level, this, 4.0, false);
                velocity = 3.0f;
                sound = net.minecraft.util.SoundEvents.CROSSBOW_SHOOT;
                break;
            case BLOWGUN:
                projectile = new fr.alleretretour.lotr.entity.projectile.LOTREntityDart(
                        level, this, 2.5, true);
                velocity = 1.8f;
                sound = net.minecraft.util.SoundEvents.LLAMA_SPIT;
                break;
            case THROWN:
                projectile = new fr.alleretretour.lotr.entity.projectile.LOTREntityThrownWeapon(
                        fr.alleretretour.lotr.init.LOTREntities.THROWN_WEAPON.get(),
                        level, this, held, 5.0);
                velocity = 1.5f;
                sound = net.minecraft.util.SoundEvents.TRIDENT_THROW;
                break;
            default:
                net.minecraft.entity.projectile.ArrowEntity arrow =
                        new net.minecraft.entity.projectile.ArrowEntity(level, this);
                arrow.setBaseDamage(2.0 + power * 2.0);
                projectile = arrow;
                velocity = 1.6f;
                sound = net.minecraft.util.SoundEvents.SKELETON_SHOOT;
                break;
        }
        double dx = target.getX() - getX();
        double dy = target.getY(0.3333333333333333) - projectile.getY();
        double dz = target.getZ() - getZ();
        double dist = Math.sqrt(dx * dx + dz * dz);
        projectile.shoot(dx, dy + dist * 0.2, dz, velocity,
                (float) (14 - level.getDifficulty().getId() * 4));
        playSound(sound, 1.0f, 1.0f / (getRandom().nextFloat() * 0.4f + 0.8f));
        level.addFreshEntity(projectile);
    }

    public static AttributeModifierMap.MutableAttribute createNPCAttributes() {
        return MobEntity.createMobAttributes()
                .add(Attributes.MAX_HEALTH, 20.0)
                .add(Attributes.MOVEMENT_SPEED, 0.3)
                .add(Attributes.ATTACK_DAMAGE, 2.0)
                .add(Attributes.FOLLOW_RANGE, 24.0);
    }

    @Override
    protected void registerGoals() {
        goalSelector.addGoal(0, new SwimGoal(this));
        goalSelector.addGoal(1, new fr.alleretretour.lotr.entity.ai.LOTRFollowHirerGoal(
                this, 1.15, 8.0f, 4.0f));
        if (isRangedNPC()) {
            goalSelector.addGoal(2, new fr.alleretretour.lotr.entity.ai.LOTRRangedBowGoal(
                    this, 1.0, 20, 18.0f));
        } else {
            goalSelector.addGoal(2, new MeleeAttackGoal(this, 1.3, false));
        }
        goalSelector.addGoal(6, new WaterAvoidingRandomWalkingGoal(this, 0.75));
        goalSelector.addGoal(7, new LookAtGoal(this, PlayerEntity.class, 8.0f));
        goalSelector.addGoal(8, new LookRandomlyGoal(this));

        targetSelector.addGoal(0, new fr.alleretretour.lotr.entity.ai.LOTRHirerDefenseGoal(this));
        targetSelector.addGoal(1, new HurtByTargetGoal(this));
        // joueurs hostiles a la faction (alignement negatif)
        targetSelector.addGoal(2, new NearestAttackableTargetGoal<>(this, PlayerEntity.class,
                10, true, false, this::isPlayerHostile));
        // PNJ des factions ennemies (jamais les unites du meme commanditaire)
        targetSelector.addGoal(3, new NearestAttackableTargetGoal<>(this, LOTREntityNPC.class,
                10, true, false, e -> e instanceof LOTREntityNPC
                        && !isAlliedHiredUnit((LOTREntityNPC) e)
                        && isFactionHostile(((LOTREntityNPC) e).getFaction())));
    }

    @Override
    public boolean canAttack(LivingEntity target) {
        // jamais le commanditaire, jamais une unite du meme commanditaire
        if (isHiredBy(target)) {
            return false;
        }
        if (target instanceof LOTREntityNPC && isAlliedHiredUnit((LOTREntityNPC) target)) {
            return false;
        }
        return super.canAttack(target);
    }

    protected boolean isPlayerHostile(LivingEntity entity) {
        if (!(entity instanceof PlayerEntity) || entity.isSpectator()
                || ((PlayerEntity) entity).isCreative()) {
            return false;
        }
        if (isHiredBy(entity)) {
            return false;
        }
        if (entity instanceof ServerPlayerEntity) {
            // acces via LazyOptional : jamais d'exception si la capability
            // n'est pas encore prete (chargement de monde)
            return ((PlayerEntity) entity).getCapability(
                    fr.alleretretour.lotr.fac.LOTRPlayerDataProvider.CAPABILITY)
                    .map(data -> data.getAlignment(getFaction()) < 0.0f)
                    .orElse(false);
        }
        return false;
    }

    protected boolean isFactionHostile(LOTRFaction other) {
        LOTRFaction.Relation rel = LOTRFaction.getRelation(getFaction(), other);
        return rel == LOTRFaction.Relation.ENEMY || rel == LOTRFaction.Relation.MORTAL_ENEMY;
    }
}
