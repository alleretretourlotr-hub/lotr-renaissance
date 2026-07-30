package fr.alleretretour.lotr.entity.npc;

import net.minecraft.entity.EntityType;
import net.minecraft.world.World;

/** PORT de LOTREntityAngmarBannerBearer (Legacy) : AngmarOrc portant sa banniere. */
public class LOTREntityAngmarBannerBearer extends LOTREntityAngmarOrc implements LOTRBannerBearer {

    public LOTREntityAngmarBannerBearer(EntityType<? extends LOTREntityAngmarBannerBearer> type, World world) {
        super((EntityType) type, world);
    }

    @Override
    public String getBannerItemId() {
        return "lotr:angmar_banner";
    }
}
