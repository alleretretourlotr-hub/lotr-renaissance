package fr.alleretretour.lotr.entity.npc;

import net.minecraft.entity.EntityType;
import net.minecraft.world.World;

/** PORT de LOTREntityNearHaradBannerBearer (Legacy) : NearHaradrimWarrior portant sa banniere. */
public class LOTREntityNearHaradBannerBearer extends LOTREntityNearHaradrimWarrior implements LOTRBannerBearer {

    public LOTREntityNearHaradBannerBearer(EntityType<? extends LOTREntityNearHaradBannerBearer> type, World world) {
        super((EntityType) type, world);
    }

    @Override
    public String getBannerItemId() {
        return "lotr:near_harad_banner";
    }
}
