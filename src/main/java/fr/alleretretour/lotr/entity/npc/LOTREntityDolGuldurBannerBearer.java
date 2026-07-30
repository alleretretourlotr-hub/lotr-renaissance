package fr.alleretretour.lotr.entity.npc;

import net.minecraft.entity.EntityType;
import net.minecraft.world.World;

/** PORT de LOTREntityDolGuldurBannerBearer (Legacy) : DolGuldurOrc portant sa banniere. */
public class LOTREntityDolGuldurBannerBearer extends LOTREntityDolGuldurOrc implements LOTRBannerBearer {

    public LOTREntityDolGuldurBannerBearer(EntityType<? extends LOTREntityDolGuldurBannerBearer> type, World world) {
        super((EntityType) type, world);
    }

    @Override
    public String getBannerItemId() {
        return "lotr:dol_guldur_banner";
    }
}
