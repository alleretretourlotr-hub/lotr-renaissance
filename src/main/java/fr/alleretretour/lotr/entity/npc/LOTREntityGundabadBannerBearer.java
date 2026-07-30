package fr.alleretretour.lotr.entity.npc;

import net.minecraft.entity.EntityType;
import net.minecraft.world.World;

/** PORT de LOTREntityGundabadBannerBearer (Legacy) : GundabadOrc portant sa banniere. */
public class LOTREntityGundabadBannerBearer extends LOTREntityGundabadOrc implements LOTRBannerBearer {

    public LOTREntityGundabadBannerBearer(EntityType<? extends LOTREntityGundabadBannerBearer> type, World world) {
        super((EntityType) type, world);
    }

    @Override
    public String getBannerItemId() {
        return "lotr:gundabad_banner";
    }
}
