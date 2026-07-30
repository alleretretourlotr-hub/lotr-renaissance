package fr.alleretretour.lotr.entity.npc;

import fr.alleretretour.lotr.fac.LOTRFaction;
import net.minecraft.entity.EntityType;
import net.minecraft.world.World;

/** PORT de LOTREntityGundabadWarg : warg de la faction GUNDABAD. */
public class LOTREntityGundabadWarg extends LOTREntityWarg {

    public LOTREntityGundabadWarg(EntityType<? extends LOTREntityGundabadWarg> type, World world) {
        super(type, world);
    }

    @Override
    public LOTRFaction getFaction() {
        return LOTRFaction.GUNDABAD;
    }
}
