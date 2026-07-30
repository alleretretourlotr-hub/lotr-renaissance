package fr.alleretretour.lotr.entity.npc;

import net.minecraft.entity.EntityType;
import net.minecraft.world.World;

/** PORT de LOTREntityMordorBannerBearer (Legacy) : MordorOrc portant sa banniere. */
public class LOTREntityMordorBannerBearer extends LOTREntityMordorOrc implements LOTRBannerBearer {

    public LOTREntityMordorBannerBearer(EntityType<? extends LOTREntityMordorBannerBearer> type, World world) {
        super((EntityType) type, world);
    }

    @Override
    public String getBannerItemId() {
        return "lotr:mordor_banner";
    }
}
