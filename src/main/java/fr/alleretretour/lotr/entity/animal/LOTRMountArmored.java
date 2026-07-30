package fr.alleretretour.lotr.entity.animal;

import net.minecraft.item.ItemStack;

/** Monture pouvant porter une armure (montures non-chevalines du Legacy). */
public interface LOTRMountArmored {

    void setMountArmor(ItemStack stack);

    ItemStack getMountArmor();
}
