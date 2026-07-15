package fr.alleretretour.lotr.item;

import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

/** Port de lotr.common.item.LOTRItemBerry : petite baie (2 faim), mangee rapidement. */
public class LOTRItemBerry extends LOTRItemFood {

    public LOTRItemBerry() {
        super(2, 0.2f, false);
    }

    @Override
    public int getUseDuration(ItemStack stack) {
        return 16; // moitie du temps normal, comme les baies vanilla
    }
}
