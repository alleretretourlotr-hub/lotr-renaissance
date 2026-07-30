package fr.alleretretour.lotr.init;

import net.minecraft.item.Item;
import net.minecraftforge.fml.RegistryObject;

/** PORT de LOTRMod.rhunFirePot : pot de feu du Rhun (arme de jet incendiaire). */
public final class LOTRItemsRhun {

    public static final RegistryObject<Item> RHUN_FIRE_POT =
            LOTRItems.ITEMS.register("rhun_fire_pot",
                    () -> new fr.alleretretour.lotr.item.LOTRItemFirePot(
                            new Item.Properties().tab(LOTRItems.TAB_LOTR).stacksTo(16)));

    private LOTRItemsRhun() {
    }

    public static void init() {
    }
}
