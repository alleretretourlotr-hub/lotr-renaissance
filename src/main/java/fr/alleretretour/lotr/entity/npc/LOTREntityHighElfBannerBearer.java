package fr.alleretretour.lotr.entity.npc;

import net.minecraft.entity.EntityType;
import net.minecraft.world.World;

/** PORT de LOTREntityHighElfBannerBearer (Legacy) : HighElfWarrior + banniere en main gauche. */
public class LOTREntityHighElfBannerBearer extends LOTREntityHighElfWarrior implements LOTRBannerBearer {

    public LOTREntityHighElfBannerBearer(EntityType<? extends LOTREntityHighElfBannerBearer> type, World world) {
        super((EntityType) type, world);
    }

    @Override
    public String getBannerItemId() {
        return "lotr:high_elf_banner";
    }
}
