package fr.alleretretour.lotr.entity.npc;

import fr.alleretretour.lotr.fac.LOTRFaction;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.ai.attributes.AttributeModifierMap;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

/** GENERE (mega-lot Phase 4) - port de lotr.common.entity.npc.LOTREntityDorwinionGuard */
public class LOTREntityDorwinionGuard extends LOTREntityNPC {

    public LOTREntityDorwinionGuard(EntityType<? extends LOTREntityDorwinionGuard> type, World world) {
        super(type, world);
    }

    @Override
    public LOTRFaction getFaction() {
        return LOTRFaction.DORWINION;
    }

    @Override
    protected String getSpeechBank() {
        return "dorwinion/warrior";
    }

    public static AttributeModifierMap.MutableAttribute createAttributes() {
        return createNPCAttributes()
                .add(Attributes.MAX_HEALTH, 20.0)
                .add(Attributes.ATTACK_DAMAGE, 2.0);
    }
}
