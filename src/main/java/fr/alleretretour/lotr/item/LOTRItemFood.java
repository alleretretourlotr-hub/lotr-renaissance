package fr.alleretretour.lotr.item;

import fr.alleretretour.lotr.init.LOTRCreativeTabs;
import fr.alleretretour.lotr.init.LOTRItems;
import net.minecraft.item.Food;
import net.minecraft.item.Item;

/**
 * Port de lotr.common.item.LOTRItemFood : (faim, saturation, mangeable par les loups).
 * Note 1.16.5 : la saturation vanilla = heal * satModifier * 2, identique a 1.7.10.
 */
public class LOTRItemFood extends Item {

    public LOTRItemFood(int heal, float saturation, boolean wolfFood) {
        super(buildProps(heal, saturation, wolfFood));
    }

    protected LOTRItemFood(Properties props) {
        super(props);
    }

    protected static Properties buildProps(int heal, float saturation, boolean wolfFood) {
        Food.Builder food = new Food.Builder().nutrition(heal).saturationMod(saturation);
        if (wolfFood) {
            food.meat();
        }
        return new Properties().tab(LOTRCreativeTabs.TAB_FOOD).food(food.build());
    }
}
