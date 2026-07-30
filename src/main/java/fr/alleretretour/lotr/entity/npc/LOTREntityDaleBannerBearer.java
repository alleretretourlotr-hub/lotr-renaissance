package fr.alleretretour.lotr.entity.npc;

import net.minecraft.entity.EntityType;
import net.minecraft.world.World;

/** PORT de LOTREntityDaleBannerBearer (Legacy) : DaleSoldier portant sa banniere. */
public class LOTREntityDaleBannerBearer extends LOTREntityDaleSoldier implements LOTRBannerBearer {

    public LOTREntityDaleBannerBearer(EntityType<? extends LOTREntityDaleBannerBearer> type, World world) {
        super((EntityType) type, world);
    }

    @Override
    public String getBannerItemId() {
        return "lotr:dale_banner";
    }
}
