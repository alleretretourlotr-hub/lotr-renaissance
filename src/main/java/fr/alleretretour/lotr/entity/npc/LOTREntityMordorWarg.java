package fr.alleretretour.lotr.entity.npc;

import fr.alleretretour.lotr.fac.LOTRFaction;
import net.minecraft.entity.EntityType;
import net.minecraft.world.World;

/** PORT de LOTREntityMordorWarg : warg de la faction MORDOR. */
public class LOTREntityMordorWarg extends LOTREntityWarg {

    public LOTREntityMordorWarg(EntityType<? extends LOTREntityMordorWarg> type, World world) {
        super(type, world);
    }

    @Override
    public LOTRFaction getFaction() {
        return LOTRFaction.MORDOR;
    }
}
