package fr.alleretretour.lotr.entity.npc;

import net.minecraft.entity.EntityType;
import net.minecraft.world.World;

/** PORT de LOTREntityRohanBannerBearer (Legacy) : RohirrimWarrior + banniere en main gauche. */
public class LOTREntityRohanBannerBearer extends LOTREntityRohirrimWarrior implements LOTRBannerBearer {

    public LOTREntityRohanBannerBearer(EntityType<? extends LOTREntityRohanBannerBearer> type, World world) {
        super((EntityType) type, world);
    }

    @Override
    public String getBannerItemId() {
        return "lotr:rohan_banner";
    }
}
