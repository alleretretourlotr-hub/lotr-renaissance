package fr.alleretretour.lotr.entity.npc;

import net.minecraft.entity.EntityType;
import net.minecraft.world.World;

/** PORT de LOTREntityAngmarHillmanBannerBearer (Legacy) : AngmarHillmanWarrior portant sa banniere. */
public class LOTREntityAngmarHillmanBannerBearer extends LOTREntityAngmarHillmanWarrior implements LOTRBannerBearer {

    public LOTREntityAngmarHillmanBannerBearer(EntityType<? extends LOTREntityAngmarHillmanBannerBearer> type, World world) {
        super((EntityType) type, world);
    }

    @Override
    public String getBannerItemId() {
        return "lotr:rhudaur_banner";
    }
}
