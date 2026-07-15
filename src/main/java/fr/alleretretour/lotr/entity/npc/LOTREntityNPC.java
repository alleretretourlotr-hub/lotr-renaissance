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
import net.minecraft.world.World;

/**
 * PORT du socle lotr.common.entity.npc.LOTREntityNPC (1664 lignes -> essentiel).
 * Un PNJ a une FACTION ; il attaque les joueurs dont l'alignement avec sa
 * faction est negatif (hostiles), et les PNJ des factions ennemies.
 * Le kill rapporte l'alignement via LOTRAlignmentBonuses (bonus par type).
 *
 * A venir dans les lots suivants : montures, tir a l'arc, discours,
 * marchands, unites engagees, mini-quetes.
 */
public abstract class LOTREntityNPC extends CreatureEntity
        implements net.minecraft.entity.IRangedAttackMob {

    protected LOTREntityNPC(EntityType<? extends LOTREntityNPC> type, World world) {
        super(type, world);
    }

    /** La faction de ce PNJ - pilote l'IA de ciblage et l'alignement. */
    public abstract LOTRFaction getFaction();

    /**
     * Banque de discours du PNJ (chemin sous assets/lotr/speech/), null = muet.
     * Le suffixe friendly/neutral/hostile est choisi selon l'alignement.
     */
    protected String getSpeechBank() {
        return null;
    }

    @Override
    protected net.minecraft.util.ActionResultType mobInteract(
            net.minecraft.entity.player.PlayerEntity player, net.minecraft.util.Hand hand) {
        String bank = getSpeechBank();
        if (bank != null && !level.isClientSide && hand == net.minecraft.util.Hand.MAIN_HAND) {
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

    /** true pour les archers : IA de tir a l'arc au lieu de la melee. */
    protected boolean isRangedNPC() {
        return false;
    }

    @Override
    public void performRangedAttack(net.minecraft.entity.LivingEntity target, float power) {
        net.minecraft.entity.projectile.ArrowEntity arrow =
                new net.minecraft.entity.projectile.ArrowEntity(level, this);
        double dx = target.getX() - getX();
        double dy = target.getY(0.3333333333333333) - arrow.getY();
        double dz = target.getZ() - getZ();
        double dist = Math.sqrt(dx * dx + dz * dz);
        arrow.shoot(dx, dy + dist * 0.2, dz, 1.6f,
                (float) (14 - level.getDifficulty().getId() * 4));
        arrow.setBaseDamage(2.0 + power * 2.0);
        playSound(net.minecraft.util.SoundEvents.SKELETON_SHOOT, 1.0f,
                1.0f / (getRandom().nextFloat() * 0.4f + 0.8f));
        level.addFreshEntity(arrow);
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
        if (isRangedNPC()) {
            goalSelector.addGoal(2, new fr.alleretretour.lotr.entity.ai.LOTRRangedBowGoal(
                    this, 1.0, 20, 18.0f));
        } else {
            goalSelector.addGoal(2, new MeleeAttackGoal(this, 1.3, false));
        }
        goalSelector.addGoal(6, new WaterAvoidingRandomWalkingGoal(this, 0.75));
        goalSelector.addGoal(7, new LookAtGoal(this, PlayerEntity.class, 8.0f));
        goalSelector.addGoal(8, new LookRandomlyGoal(this));

        targetSelector.addGoal(1, new HurtByTargetGoal(this));
        // joueurs hostiles a la faction (alignement negatif)
        targetSelector.addGoal(2, new NearestAttackableTargetGoal<>(this, PlayerEntity.class,
                10, true, false, this::isPlayerHostile));
        // PNJ des factions ennemies
        targetSelector.addGoal(3, new NearestAttackableTargetGoal<>(this, LOTREntityNPC.class,
                10, true, false, e -> e instanceof LOTREntityNPC
                        && isFactionHostile(((LOTREntityNPC) e).getFaction())));
    }

    protected boolean isPlayerHostile(LivingEntity entity) {
        if (!(entity instanceof PlayerEntity) || entity.isSpectator()
                || ((PlayerEntity) entity).isCreative()) {
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
