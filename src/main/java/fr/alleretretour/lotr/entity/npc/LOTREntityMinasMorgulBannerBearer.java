package fr.alleretretour.lotr.entity.npc;

import net.minecraft.entity.EntityType;
import net.minecraft.world.World;

/** PORT de LOTREntityMinasMorgulBannerBearer (Legacy) : MordorOrc portant sa banniere. */
public class LOTREntityMinasMorgulBannerBearer extends LOTREntityMordorOrc implements LOTRBannerBearer {

    public LOTREntityMinasMorgulBannerBearer(EntityType<? extends LOTREntityMinasMorgulBannerBearer> type, World world) {
        super((EntityType) type, world);
    }

    @Override
    public String getBannerItemId() {
        return "lotr:minas_morgul_banner";
    }
}
