package fr.alleretretour.lotr.entity.npc;

import net.minecraft.entity.EntityType;
import net.minecraft.world.World;

/** PORT de LOTREntityGaladhrimBannerBearer (Legacy) : GaladhrimWarrior + banniere en main gauche. */
public class LOTREntityGaladhrimBannerBearer extends LOTREntityGaladhrimWarrior implements LOTRBannerBearer {

    public LOTREntityGaladhrimBannerBearer(EntityType<? extends LOTREntityGaladhrimBannerBearer> type, World world) {
        super((EntityType) type, world);
    }

    @Override
    public String getBannerItemId() {
        return "lotr:galadhrim_banner";
    }
}
