package fr.alleretretour.lotr.entity.npc;

import fr.alleretretour.lotr.fac.LOTRFaction;
import net.minecraft.entity.EntityType;
import net.minecraft.world.World;

/** PORT de LOTREntityAngmarWargBombardier : bombardier de la faction ANGMAR. */
public class LOTREntityAngmarWargBombardier extends LOTREntityWargBombardier {

    public LOTREntityAngmarWargBombardier(EntityType<? extends LOTREntityAngmarWargBombardier> type, World world) {
        super(type, world);
    }

    @Override
    public LOTRFaction getFaction() {
        return LOTRFaction.ANGMAR;
    }
}
