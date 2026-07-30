package fr.alleretretour.lotr.entity.npc;

import fr.alleretretour.lotr.fac.LOTRFaction;
import net.minecraft.entity.EntityType;
import net.minecraft.world.World;

/** PORT de LOTREntityUrukWargBombardier : bombardier de la faction ISENGARD. */
public class LOTREntityUrukWargBombardier extends LOTREntityWargBombardier {

    public LOTREntityUrukWargBombardier(EntityType<? extends LOTREntityUrukWargBombardier> type, World world) {
        super(type, world);
    }

    @Override
    public LOTRFaction getFaction() {
        return LOTRFaction.ISENGARD;
    }
}
