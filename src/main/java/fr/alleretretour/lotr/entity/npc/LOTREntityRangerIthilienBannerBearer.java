package fr.alleretretour.lotr.entity.npc;

import net.minecraft.entity.EntityType;
import net.minecraft.world.World;

/** PORT de LOTREntityRangerIthilienBannerBearer (Legacy) : RangerIthilien portant sa banniere. */
public class LOTREntityRangerIthilienBannerBearer extends LOTREntityRangerIthilien implements LOTRBannerBearer {

    public LOTREntityRangerIthilienBannerBearer(EntityType<? extends LOTREntityRangerIthilienBannerBearer> type, World world) {
        super((EntityType) type, world);
    }

    @Override
    public String getBannerItemId() {
        return "lotr:ithilien_banner";
    }
}
