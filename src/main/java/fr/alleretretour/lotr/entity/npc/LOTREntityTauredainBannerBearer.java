package fr.alleretretour.lotr.entity.npc;

import net.minecraft.entity.EntityType;
import net.minecraft.world.World;

/** PORT de LOTREntityTauredainBannerBearer (Legacy) : TauredainWarrior portant sa banniere. */
public class LOTREntityTauredainBannerBearer extends LOTREntityTauredainWarrior implements LOTRBannerBearer {

    public LOTREntityTauredainBannerBearer(EntityType<? extends LOTREntityTauredainBannerBearer> type, World world) {
        super((EntityType) type, world);
    }

    @Override
    public String getBannerItemId() {
        return "lotr:tauredain_banner";
    }
}
