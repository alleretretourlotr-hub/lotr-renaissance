package fr.alleretretour.lotr.entity.npc;

import fr.alleretretour.lotr.fac.LOTRFaction;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.ai.attributes.AttributeModifierMap;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.world.World;

/** GENERE PAR L'USINE (lot troupes 3) - port de lotr.common.entity.npc.LOTREntityEasterlingLevyman */
public class LOTREntityEasterlingLevyman extends LOTREntityNPC {

    public LOTREntityEasterlingLevyman(EntityType<? extends LOTREntityEasterlingLevyman> type, World world) {
        super(type, world);
    }

    @Override
    public LOTRFaction getFaction() {
        return LOTRFaction.RHUDEL;
    }

    @Override
    protected String getSpeechBank() {
        return "rhun/warrior";
    }

    public static AttributeModifierMap.MutableAttribute createAttributes() {
        return createNPCAttributes()
                .add(Attributes.MAX_HEALTH, 20.0)
                .add(Attributes.ATTACK_DAMAGE, 2.0);
    }
}
