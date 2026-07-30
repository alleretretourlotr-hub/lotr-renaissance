package fr.alleretretour.lotr.entity.npc;

import net.minecraft.entity.EntityType;
import net.minecraft.world.World;

/** PORT de LOTREntityUmbarBannerBearer (Legacy) : UmbarWarrior portant sa banniere. */
public class LOTREntityUmbarBannerBearer extends LOTREntityUmbarWarrior implements LOTRBannerBearer {

    public LOTREntityUmbarBannerBearer(EntityType<? extends LOTREntityUmbarBannerBearer> type, World world) {
        super((EntityType) type, world);
    }

    @Override
    public String getBannerItemId() {
        return "lotr:umbar_banner";
    }
}
