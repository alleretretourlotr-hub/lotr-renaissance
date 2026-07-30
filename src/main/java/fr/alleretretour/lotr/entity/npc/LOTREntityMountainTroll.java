package fr.alleretretour.lotr.entity.npc;

import fr.alleretretour.lotr.fac.LOTRFaction;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.ai.attributes.AttributeModifierMap;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.world.World;

/** PORT de LOTREntityMountainTroll : PV 70, degats 7, portee 24 (valeurs Legacy). */
public class LOTREntityMountainTroll extends LOTREntityTroll {

    public LOTREntityMountainTroll(EntityType<? extends LOTREntityMountainTroll> type, World world) {
        super((EntityType) type, world);
    }

    @Override
    public LOTRFaction getFaction() {
        return LOTRFaction.ANGMAR;
    }

    public static AttributeModifierMap.MutableAttribute createAttributes() {
        return createNPCAttributes()
                .add(Attributes.MAX_HEALTH, 70.0)
                .add(Attributes.MOVEMENT_SPEED, 0.2)
                .add(Attributes.ATTACK_DAMAGE, 7.0)
                .add(Attributes.FOLLOW_RANGE, 24.0)
                .add(Attributes.KNOCKBACK_RESISTANCE, 0.8);
    }

    /** PORT : le troll projette sa cible (knockback = 0.25 x degats). */
    @Override
    public boolean doHurtTarget(net.minecraft.entity.Entity target) {
        if (!super.doHurtTarget(target)) {
            return false;
        }
        if (target instanceof net.minecraft.entity.LivingEntity) {
            float knockback = 0.25f * (float) getAttributeValue(Attributes.ATTACK_DAMAGE);
            ((net.minecraft.entity.LivingEntity) target).knockback(knockback,
                    net.minecraft.util.math.MathHelper.sin(yRot * 0.017453292f),
                    -net.minecraft.util.math.MathHelper.cos(yRot * 0.017453292f));
        }
        return true;
    }
}
