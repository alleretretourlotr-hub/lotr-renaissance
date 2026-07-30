package fr.alleretretour.lotr.entity.npc;

import fr.alleretretour.lotr.fac.LOTRFaction;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.ai.attributes.AttributeModifierMap;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.world.World;

/** GENERE PAR L'USINE (lot 8 ombre) - port de lotr.common.entity.npc.LOTREntityIsengardSnaga */
public class LOTREntityIsengardSnaga extends LOTREntityNPC {

    public LOTREntityIsengardSnaga(EntityType<? extends LOTREntityIsengardSnaga> type, World world) {
        super(type, world);
    }

    @Override
    public LOTRFaction getFaction() {
        return LOTRFaction.ISENGARD;
    }

    @Override
    protected String getSpeechBank() {
        return "isengard/orc";
    }

    public static AttributeModifierMap.MutableAttribute createAttributes() {
        return createNPCAttributes()
                .add(Attributes.MAX_HEALTH, 18.0)
                .add(Attributes.ATTACK_DAMAGE, 2.0);
    }
}
