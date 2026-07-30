package fr.alleretretour.lotr.entity.ai;

import fr.alleretretour.lotr.entity.npc.LOTREntityNPC;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.MobEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.ai.goal.Goal;

import java.util.EnumSet;

/**
 * IA de monture d'unite engagee (adaptation du principe LOTRNPCMount du
 * Legacy) : la monture porte le mouvement - vers la cible de combat du
 * cavalier s'il en a une, sinon a la suite du commanditaire.
 */
public class LOTRMountGoal extends Goal {

    private final MobEntity mount;
    private final double speed;
    private int recalc;

    public LOTRMountGoal(MobEntity mount, double speed) {
        this.mount = mount;
        this.speed = speed;
        setFlags(EnumSet.of(Goal.Flag.MOVE, Goal.Flag.LOOK));
    }

    private LOTREntityNPC rider() {
        return mount.getPassengers().isEmpty()
                || !(mount.getPassengers().get(0) instanceof LOTREntityNPC)
                ? null : (LOTREntityNPC) mount.getPassengers().get(0);
    }

    private LivingEntity destination() {
        LOTREntityNPC rider = rider();
        if (rider == null) {
            return null;
        }
        if (rider.getTarget() != null && rider.getTarget().isAlive()) {
            return rider.getTarget();
        }
        if (rider.isHired() && !rider.isHalted()) {
            PlayerEntity hirer = rider.getHirer();
            if (hirer != null && mount.distanceToSqr(hirer) > 6.0 * 6.0) {
                return hirer;
            }
        }
        return null;
    }

    @Override
    public boolean canUse() {
        return destination() != null;
    }

    @Override
    public void tick() {
        LivingEntity dest = destination();
        if (dest == null) {
            return;
        }
        mount.getLookControl().setLookAt(dest, 30.0f, 30.0f);
        if (--recalc <= 0) {
            recalc = 10;
            mount.getNavigation().moveTo(dest, speed);
        }
    }

    @Override
    public void stop() {
        mount.getNavigation().stop();
    }
}
