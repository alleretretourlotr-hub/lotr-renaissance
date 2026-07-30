package fr.alleretretour.lotr.entity.npc;

import net.minecraft.entity.EntityType;
import net.minecraft.world.World;

/** PORT de LOTREntityDorwinionBannerBearer (Legacy) : DorwinionGuard portant sa banniere. */
public class LOTREntityDorwinionBannerBearer extends LOTREntityDorwinionGuard implements LOTRBannerBearer {

    public LOTREntityDorwinionBannerBearer(EntityType<? extends LOTREntityDorwinionBannerBearer> type, World world) {
        super((EntityType) type, world);
    }

    @Override
    public String getBannerItemId() {
        return "lotr:dorwinion_banner";
    }
}
