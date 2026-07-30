package fr.alleretretour.lotr.entity.npc;

import net.minecraft.entity.EntityType;
import net.minecraft.world.World;

/** PORT de LOTREntityHalfTrollBannerBearer (Legacy) : HalfTrollWarrior portant sa banniere. */
public class LOTREntityHalfTrollBannerBearer extends LOTREntityHalfTrollWarrior implements LOTRBannerBearer {

    public LOTREntityHalfTrollBannerBearer(EntityType<? extends LOTREntityHalfTrollBannerBearer> type, World world) {
        super((EntityType) type, world);
    }

    @Override
    public String getBannerItemId() {
        return "lotr:half_troll_banner";
    }
}
