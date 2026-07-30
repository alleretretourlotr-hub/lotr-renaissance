package fr.alleretretour.lotr.entity.npc;

import net.minecraft.entity.EntityType;
import net.minecraft.world.World;

/** PORT de LOTREntityEasterlingBannerBearer (Legacy) : EasterlingWarrior portant sa banniere. */
public class LOTREntityEasterlingBannerBearer extends LOTREntityEasterlingWarrior implements LOTRBannerBearer {

    public LOTREntityEasterlingBannerBearer(EntityType<? extends LOTREntityEasterlingBannerBearer> type, World world) {
        super((EntityType) type, world);
    }

    @Override
    public String getBannerItemId() {
        return "lotr:rhun_banner";
    }
}
