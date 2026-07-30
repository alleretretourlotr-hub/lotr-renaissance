package fr.alleretretour.lotr.entity.npc;

import net.minecraft.entity.EntityType;
import net.minecraft.world.World;

/** PORT de LOTREntityMoredainBannerBearer (Legacy) : MoredainWarrior portant sa banniere. */
public class LOTREntityMoredainBannerBearer extends LOTREntityMoredainWarrior implements LOTRBannerBearer {

    public LOTREntityMoredainBannerBearer(EntityType<? extends LOTREntityMoredainBannerBearer> type, World world) {
        super((EntityType) type, world);
    }

    @Override
    public String getBannerItemId() {
        return "lotr:moredain_banner";
    }
}
