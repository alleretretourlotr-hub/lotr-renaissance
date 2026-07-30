package fr.alleretretour.lotr.entity.animal;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.passive.horse.HorseEntity;
import net.minecraft.world.World;

/**
 * PORT (premiere tranche) de LOTREntityHorse : cheval des unites montees.
 * Le Legacy etend le cheval vanilla ; idem ici, avec l'IA de monture
 * engagee. Elevage/armures de monture viendront ensuite.
 */
public class LOTREntityHorse extends HorseEntity {

    public LOTREntityHorse(EntityType<? extends LOTREntityHorse> type, World world) {
        super(type, world);
    }

    @Override
    protected void registerGoals() {
        super.registerGoals();
        goalSelector.addGoal(0, new fr.alleretretour.lotr.entity.ai.LOTRMountGoal(this, 1.4));
    }

    /** Monture d'unite : apprivoisee, jamais de despawn. */
    public void setupAsUnitMount() {
        setTamed(true);
        setPersistenceRequired();
    }
}
