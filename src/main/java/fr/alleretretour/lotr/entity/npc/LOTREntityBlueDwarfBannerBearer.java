package fr.alleretretour.lotr.entity.npc;

import net.minecraft.entity.EntityType;
import net.minecraft.world.World;

/** PORT de LOTREntityBlueDwarfBannerBearer (Legacy) : BlueDwarfWarrior + banniere en main gauche. */
public class LOTREntityBlueDwarfBannerBearer extends LOTREntityBlueDwarfWarrior implements LOTRBannerBearer {

    public LOTREntityBlueDwarfBannerBearer(EntityType<? extends LOTREntityBlueDwarfBannerBearer> type, World world) {
        super((EntityType) type, world);
    }

    @Override
    public String getBannerItemId() {
        return "lotr:blue_mountains_banner";
    }
}
