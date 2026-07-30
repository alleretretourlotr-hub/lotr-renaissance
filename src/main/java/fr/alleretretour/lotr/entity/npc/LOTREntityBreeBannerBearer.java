package fr.alleretretour.lotr.entity.npc;

import net.minecraft.entity.EntityType;
import net.minecraft.world.World;

/** PORT de LOTREntityBreeBannerBearer (Legacy) : BreeGuard portant sa banniere. */
public class LOTREntityBreeBannerBearer extends LOTREntityBreeGuard implements LOTRBannerBearer {

    public LOTREntityBreeBannerBearer(EntityType<? extends LOTREntityBreeBannerBearer> type, World world) {
        super((EntityType) type, world);
    }

    @Override
    public String getBannerItemId() {
        return "lotr:bree_banner";
    }
}
