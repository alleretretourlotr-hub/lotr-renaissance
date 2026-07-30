package fr.alleretretour.lotr.entity.npc;

import net.minecraft.entity.EntityType;
import net.minecraft.world.World;

/** PORT de LOTREntityRivendellBannerBearer (Legacy) : RivendellWarrior + banniere en main gauche. */
public class LOTREntityRivendellBannerBearer extends LOTREntityRivendellWarrior implements LOTRBannerBearer {

    public LOTREntityRivendellBannerBearer(EntityType<? extends LOTREntityRivendellBannerBearer> type, World world) {
        super((EntityType) type, world);
    }

    @Override
    public String getBannerItemId() {
        return "lotr:rivendell_banner";
    }
}
