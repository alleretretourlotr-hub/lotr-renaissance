package fr.alleretretour.lotr.entity.animal;

import net.minecraft.entity.EntityType;
import net.minecraft.world.World;

/** PORT de LOTREntityZebra : le Legacy etend le cheval, idem ici. */
public class LOTREntityZebra extends LOTREntityHorse {

    public LOTREntityZebra(EntityType<? extends LOTREntityZebra> type, World world) {
        super(type, world);
    }
}
