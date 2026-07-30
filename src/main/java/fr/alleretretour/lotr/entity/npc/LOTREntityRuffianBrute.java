package fr.alleretretour.lotr.entity.npc;

import fr.alleretretour.lotr.fac.LOTRFaction;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.ai.attributes.AttributeModifierMap;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.world.World;

/** GENERE PAR L'USINE (mega-lot 2) - port de lotr.common.entity.npc.LOTREntityRuffianBrute */
public class LOTREntityRuffianBrute extends LOTREntityNPC {

    public LOTREntityRuffianBrute(EntityType<? extends LOTREntityRuffianBrute> type, World world) {
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
                .add(Attributes.MAX_HEALTH, 30.0)
                .add(Attributes.ATTACK_DAMAGE, 2.0);
    }
}
