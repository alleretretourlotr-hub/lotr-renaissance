package fr.alleretretour.lotr.entity.npc;

import net.minecraft.entity.EntityType;
import net.minecraft.world.World;

/** PORT de LOTREntityHarnedorBannerBearer (Legacy) : HarnedorWarrior portant sa banniere. */
public class LOTREntityHarnedorBannerBearer extends LOTREntityHarnedorWarrior implements LOTRBannerBearer {

    public LOTREntityHarnedorBannerBearer(EntityType<? extends LOTREntityHarnedorBannerBearer> type, World world) {
        super((EntityType) type, world);
    }

    @Override
    public String getBannerItemId() {
        return "lotr:near_harad_banner";
    }
}
