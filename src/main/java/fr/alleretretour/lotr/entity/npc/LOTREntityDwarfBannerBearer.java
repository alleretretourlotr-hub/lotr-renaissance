package fr.alleretretour.lotr.entity.npc;

import net.minecraft.entity.EntityType;
import net.minecraft.world.World;

/** PORT de LOTREntityDwarfBannerBearer (Legacy) : DwarfWarrior + banniere en main gauche. */
public class LOTREntityDwarfBannerBearer extends LOTREntityDwarfWarrior implements LOTRBannerBearer {

    public LOTREntityDwarfBannerBearer(EntityType<? extends LOTREntityDwarfBannerBearer> type, World world) {
        super((EntityType) type, world);
    }

    @Override
    public String getBannerItemId() {
        return "lotr:dwarf_banner";
    }
}
