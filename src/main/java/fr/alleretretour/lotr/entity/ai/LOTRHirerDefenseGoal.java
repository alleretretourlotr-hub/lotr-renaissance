package fr.alleretretour.lotr.entity.ai;

import fr.alleretretour.lotr.entity.npc.LOTREntityNPC;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.goal.Goal;
import net.minecraft.entity.ai.goal.TargetGoal;
import net.minecraft.entity.player.PlayerEntity;

import java.util.EnumSet;

/**
 * Defense du commanditaire (fusion de OwnerHurtByTargetGoal et
 * OwnerHurtTargetGoal, sans TameableEntity) : cible l'agresseur du
 * commanditaire, ou la cible que celui-ci attaque.
 */
public class LOTRHirerDefenseGoal extends TargetGoal {

    private final LOTREntityNPC npc;
    private LivingEntity toAttack;
    private int lastHurtByTimestamp;
    private int lastHurtTimestamp;

    public LOTRHirerDefenseGoal(LOTREntityNPC npc) {
        super(npc, false);
        this.npc = npc;
        setFlags(EnumSet.of(Goal.Flag.TARGET));
    }

    @Override
    public boolean canUse() {
        if (!npc.isHired()) {
            return false;
        }
        PlayerEntity hirer = npc.getHirer();
        if (hirer == null) {
            return false;
        }
        LivingEntity attacker = hirer.getLastHurtByMob();
        if (attacker != null && hirer.getLastHurtByMobTimestamp() != lastHurtByTimestamp
                && isValid(attacker)) {
            toAttack = attacker;
            lastHurtByTimestamp = hirer.getLastHurtByMobTimestamp();
            return true;
        }
        LivingEntity victim = hirer.getLastHurtMob();
        if (victim != null && hirer.getLastHurtMobTimestamp() != lastHurtTimestamp
                && isValid(victim)) {
            toAttack = victim;
            lastHurtTimestamp = hirer.getLastHurtMobTimestamp();
            return true;
        }
        return false;
    }

    private boolean isValid(LivingEntity target) {
        if (target == npc || !target.isAlive() || npc.isHiredBy(target)) {
            return false;
        }
        if (target instanceof LOTREntityNPC && npc.isAlliedHiredUnit((LOTREntityNPC) target)) {
            return false;
        }
        return canAttack(target, net.minecraft.entity.EntityPredicate.DEFAULT);
    }

    @Override
    public void start() {
        mob.setTarget(toAttack);
        super.start();
    }
}
