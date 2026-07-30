package fr.alleretretour.lotr.entity.npc;

import fr.alleretretour.lotr.fac.LOTRFaction;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.ai.attributes.AttributeModifierMap;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.world.World;

/** GENERE PAR L'USINE (mega-lot 2) - port de lotr.common.entity.npc.LOTREntityRuffianSpy */
public class LOTREntityRuffianSpy extends LOTREntityNPC {

    public LOTREntityRuffianSpy(EntityType<? extends LOTREntityRuffianSpy> type, World world) {
        super(type, world);
    }

    @Override
    public LOTRFaction getFaction() {
        return LOTRFaction.RUFFIAN;
    }

    @Override
    protected String getSpeechBank() {
        return "bree/civilian";
    }

    public static AttributeModifierMap.MutableAttribute createAttributes() {
        return createNPCAttributes()
                .add(Attributes.MAX_HEALTH, 20.0)
                .add(Attributes.ATTACK_DAMAGE, 2.0);
    }
}
