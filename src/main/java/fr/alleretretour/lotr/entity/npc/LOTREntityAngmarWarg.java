package fr.alleretretour.lotr.entity.npc;

import fr.alleretretour.lotr.fac.LOTRFaction;
import net.minecraft.entity.EntityType;
import net.minecraft.world.World;

/** PORT de LOTREntityAngmarWarg : warg de la faction ANGMAR. */
public class LOTREntityAngmarWarg extends LOTREntityWarg {

    public LOTREntityAngmarWarg(EntityType<? extends LOTREntityAngmarWarg> type, World world) {
        super(type, world);
    }

    @Override
    public LOTRFaction getFaction() {
        return LOTRFaction.ANGMAR;
    }
}
