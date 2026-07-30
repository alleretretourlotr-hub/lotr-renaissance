package fr.alleretretour.lotr.entity.npc;

import fr.alleretretour.lotr.fac.LOTRFaction;
import net.minecraft.entity.EntityType;
import net.minecraft.world.World;

/** PORT de LOTREntityUrukWarg : warg de la faction ISENGARD. */
public class LOTREntityUrukWarg extends LOTREntityWarg {

    public LOTREntityUrukWarg(EntityType<? extends LOTREntityUrukWarg> type, World world) {
        super(type, world);
    }

    @Override
    public LOTRFaction getFaction() {
        return LOTRFaction.ISENGARD;
    }
}
