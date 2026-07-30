package fr.alleretretour.lotr.entity.npc;

import net.minecraft.entity.EntityType;
import net.minecraft.world.World;

/** PORT de LOTREntityEsgarothBannerBearer (Legacy) : DaleSoldier portant sa banniere. */
public class LOTREntityEsgarothBannerBearer extends LOTREntityDaleSoldier implements LOTRBannerBearer {

    public LOTREntityEsgarothBannerBearer(EntityType<? extends LOTREntityEsgarothBannerBearer> type, World world) {
        super((EntityType) type, world);
    }

    @Override
    public String getBannerItemId() {
        return "lotr:esgaroth_banner";
    }
}
