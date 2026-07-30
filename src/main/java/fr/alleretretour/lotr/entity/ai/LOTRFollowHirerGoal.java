package fr.alleretretour.lotr.entity.ai;

import fr.alleretretour.lotr.entity.npc.LOTREntityNPC;
import net.minecraft.entity.ai.goal.Goal;
import net.minecraft.entity.player.PlayerEntity;

import java.util.EnumSet;

/**
 * Suivi du commanditaire (adapte de FollowOwnerGoal, sans TameableEntity).
 * Actif seulement si engage, pas en position tenue, et sans cible de combat.
 * Teleporte aupres du commanditaire au-dela de 24 blocs.
 */
public class LOTRFollowHirerGoal extends Goal {

    private final LOTREntityNPC mob;
    private final double speed;
    private final float startDist;
    private final float stopDist;
    private PlayerEntity hirer;
    private int recalcTicks;

    public LOTRFollowHirerGoal(LOTREntityNPC mob, double speed, float startDist, float stopDist) {
        this.mob = mob;
        this.speed = speed;
        this.startDist = startDist;
        this.stopDist = stopDist;
        setFlags(EnumSet.of(Goal.Flag.MOVE, Goal.Flag.LOOK));
    }

    @Override
    public boolean canUse() {
        if (!mob.isHired() || mob.isHalted() || mob.getTarget() != null) {
            return false;
        }
        PlayerEntity p = mob.getHirer();
        if (p == null || p.isSpectator() || mob.distanceToSqr(p) < startDist * startDist) {
            return false;
        }
        this.hirer = p;
        return true;
    }

    @Override
    public boolean canContinueToUse() {
        return mob.isHired() && !mob.isHalted() && mob.getTarget() == null
                && hirer != null && hirer.isAlive()
                && mob.distanceToSqr(hirer) > stopDist * stopDist;
    }

    @Override
    public void stop() {
        hirer = null;
        mob.getNavigation().stop();
    }

    @Override
    public void tick() {
        mob.getLookControl().setLookAt(hirer, 10.0f, mob.getMaxHeadXRot());
        if (--recalcTicks > 0) {
            return;
        }
        recalcTicks = 10;
        if (mob.distanceToSqr(hirer) >= 24.0 * 24.0) {
            // teleportation aupres du commanditaire
            mob.randomTeleport(hirer.getX() + (mob.getRandom().nextInt(5) - 2),
                    hirer.getY(), hirer.getZ() + (mob.getRandom().nextInt(5) - 2), false);
        } else {
            mob.getNavigation().moveTo(hirer, speed);
        }
    }
}
