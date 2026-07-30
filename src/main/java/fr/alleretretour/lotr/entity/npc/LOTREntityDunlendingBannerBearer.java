package fr.alleretretour.lotr.entity.npc;

import net.minecraft.entity.EntityType;
import net.minecraft.world.World;

/** PORT de LOTREntityDunlendingBannerBearer (Legacy) : DunlendingWarrior portant sa banniere. */
public class LOTREntityDunlendingBannerBearer extends LOTREntityDunlendingWarrior implements LOTRBannerBearer {

    public LOTREntityDunlendingBannerBearer(EntityType<? extends LOTREntityDunlendingBannerBearer> type, World world) {
        super((EntityType) type, world);
    }

    @Override
    public String getBannerItemId() {
        return "lotr:dunland_banner";
    }
}
