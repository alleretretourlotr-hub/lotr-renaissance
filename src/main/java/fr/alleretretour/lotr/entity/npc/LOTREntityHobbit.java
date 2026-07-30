package fr.alleretretour.lotr.entity.npc;

import fr.alleretretour.lotr.fac.LOTRFaction;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.ai.attributes.AttributeModifierMap;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.world.World;

/** GENERE (mega-lot Phase 4) - port de lotr.common.entity.npc.LOTREntityHobbit */
public class LOTREntityHobbit extends LOTREntityNPC {

    public LOTREntityHobbit(EntityType<? extends LOTREntityHobbit> type, World world) {
        super(type, world);
    }

    @Override
    public LOTRFaction getFaction() {
        return LOTRFaction.HOBBIT;
    }

    @Override
    protected String getSpeechBank() {
        return "hobbit/civilian";
    }

    public static AttributeModifierMap.MutableAttribute createAttributes() {
        return createNPCAttributes()
                .add(Attributes.MAX_HEALTH, 16.0)
                .add(Attributes.ATTACK_DAMAGE, 2.0);
    }
}
