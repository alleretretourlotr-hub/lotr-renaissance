package fr.alleretretour.lotr.entity.npc;

import net.minecraft.entity.EntityType;
import net.minecraft.world.World;

/** PORT de LOTREntityGondorBannerBearer (Legacy) : GondorSoldier + banniere en main gauche. */
public class LOTREntityGondorBannerBearer extends LOTREntityGondorSoldier implements LOTRBannerBearer {

    public LOTREntityGondorBannerBearer(EntityType<? extends LOTREntityGondorBannerBearer> type, World world) {
        super((EntityType) type, world);
    }

    @Override
    public String getBannerItemId() {
        return "lotr:gondor_banner";
    }
}
