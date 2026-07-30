package fr.alleretretour.lotr.entity.npc;

import net.minecraft.entity.EntityType;
import net.minecraft.world.World;

/** PORT de LOTREntityWoodElfBannerBearer (Legacy) : WoodElfWarrior portant sa banniere. */
public class LOTREntityWoodElfBannerBearer extends LOTREntityWoodElfWarrior implements LOTRBannerBearer {

    public LOTREntityWoodElfBannerBearer(EntityType<? extends LOTREntityWoodElfBannerBearer> type, World world) {
        super((EntityType) type, world);
    }

    @Override
    public String getBannerItemId() {
        return "lotr:wood_elf_banner";
    }
}
