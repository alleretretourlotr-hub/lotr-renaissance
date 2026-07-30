package fr.alleretretour.lotr.entity.npc;

import net.minecraft.entity.EntityType;
import net.minecraft.world.World;

/** PORT de LOTREntityUrukHaiBannerBearer (Legacy) : UrukHai portant sa banniere. */
public class LOTREntityUrukHaiBannerBearer extends LOTREntityUrukHai implements LOTRBannerBearer {

    public LOTREntityUrukHaiBannerBearer(EntityType<? extends LOTREntityUrukHaiBannerBearer> type, World world) {
        super((EntityType) type, world);
    }

    @Override
    public String getBannerItemId() {
        return "lotr:isengard_banner";
    }
}
