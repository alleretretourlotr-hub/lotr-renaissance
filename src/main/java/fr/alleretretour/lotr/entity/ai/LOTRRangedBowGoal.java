package fr.alleretretour.lotr.entity.ai;

import fr.alleretretour.lotr.entity.npc.LOTREntityNPC;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.goal.Goal;
import net.minecraft.item.BowItem;
import net.minecraft.item.Items;
import net.minecraft.util.Hand;

import java.util.EnumSet;

/**
 * Copie fidele de RangedBowAttackGoal, mais bornee a LOTREntityNPC
 * (le vanilla exige MonsterEntity ; nos PNJ sont des CreatureEntity).
 */
public class LOTRRangedBowGoal extends Goal {

    private final LOTREntityNPC mob;
    private final double speedModifier;
    private int attackIntervalMin;
    private final float attackRadiusSqr;
    private int attackTime = -1;
    private int seeTime;
    private boolean strafingClockwise;
    private boolean strafingBackwards;
    private int strafingTime = -1;

    public LOTRRangedBowGoal(LOTREntityNPC mob, double speed, int attackInterval, float attackRadius) {
        this.mob = mob;
        this.speedModifier = speed;
        this.attackIntervalMin = attackInterval;
        this.attackRadiusSqr = attackRadius * attackRadius;
        setFlags(EnumSet.of(Goal.Flag.MOVE, Goal.Flag.LOOK));
    }

    @Override
    public boolean canUse() {
        return mob.getTarget() != null && isHoldingBow();
    }

    protected boolean isHoldingBow() {
        return mob.isHolding(item -> item instanceof BowItem);
    }

    @Override
    public boolean canContinueToUse() {
        return (canUse() || !mob.getNavigation().isDone()) && isHoldingBow();
    }

    @Override
    public void start() {
        super.start();
        mob.setAggressive(true);
    }

    @Override
    public void stop() {
        super.stop();
        mob.setAggressive(false);
        seeTime = 0;
        attackTime = -1;
        mob.stopUsingItem();
    }

    @Override
    public void tick() {
        LivingEntity target = mob.getTarget();
        if (target == null) {
            return;
        }
        double distSqr = mob.distanceToSqr(target.getX(), target.getY(), target.getZ());
        boolean canSee = mob.getSensing().canSee(target);
        boolean seen = seeTime > 0;
        if (canSee != seen) {
            seeTime = 0;
        }
        seeTime += canSee ? 1 : -1;

        if (distSqr <= attackRadiusSqr && seeTime >= 20) {
            mob.getNavigation().stop();
            strafingTime++;
        } else {
            mob.getNavigation().moveTo(target, speedModifier);
            strafingTime = -1;
        }
        if (strafingTime >= 20) {
            if (mob.getRandom().nextFloat() < 0.3) {
                strafingClockwise = !strafingClockwise;
            }
            if (mob.getRandom().nextFloat() < 0.3) {
                strafingBackwards = !strafingBackwards;
            }
            strafingTime = 0;
        }
        if (strafingTime > -1) {
            if (distSqr > attackRadiusSqr * 0.75f) {
                strafingBackwards = false;
            } else if (distSqr < attackRadiusSqr * 0.25f) {
                strafingBackwards = true;
            }
            mob.getMoveControl().strafe(strafingBackwards ? -0.5f : 0.5f,
                    strafingClockwise ? 0.5f : -0.5f);
            mob.lookAt(target, 30.0f, 30.0f);
        } else {
            mob.getLookControl().setLookAt(target, 30.0f, 30.0f);
        }

        if (mob.isUsingItem()) {
            if (!canSee && seeTime < -60) {
                mob.stopUsingItem();
            } else if (canSee) {
                int useTicks = mob.getTicksUsingItem();
                if (useTicks >= 20) {
                    mob.stopUsingItem();
                    mob.performRangedAttack(target, BowItem.getPowerForTime(useTicks));
                    attackTime = attackIntervalMin;
                }
            }
        } else if (--attackTime <= 0 && seeTime >= -60) {
            mob.startUsingItem(net.minecraft.util.Hand.MAIN_HAND);
        }
    }
}
