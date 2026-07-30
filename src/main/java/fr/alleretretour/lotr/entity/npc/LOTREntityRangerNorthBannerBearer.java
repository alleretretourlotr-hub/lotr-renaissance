package fr.alleretretour.lotr.entity.npc;

import net.minecraft.entity.EntityType;
import net.minecraft.world.World;

/** PORT de LOTREntityRangerNorthBannerBearer (Legacy) : RangerNorth + banniere en main gauche. */
public class LOTREntityRangerNorthBannerBearer extends LOTREntityRangerNorth implements LOTRBannerBearer {

    public LOTREntityRangerNorthBannerBearer(EntityType<? extends LOTREntityRangerNorthBannerBearer> type, World world) {
        super((EntityType) type, world);
    }

    @Override
    public String getBannerItemId() {
        return "lotr:ranger_banner";
    }
}
