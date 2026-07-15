package fr.alleretretour.lotr.item;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

/**
 * PORT de LOTRItemCoin : la piece d'argent, monnaie du mod.
 * L'apparence change selon la taille de la pile (1 / 10+ / 100+),
 * via 3 modeles override (coin, coin_10, coin_100).
 */
public class LOTRItemCoin extends Item {

    public LOTRItemCoin(Properties props) {
        super(props);
    }

    public static int coinTier(ItemStack stack) {
        int n = stack.getCount();
        if (n >= 100) {
            return 2;
        }
        if (n >= 10) {
            return 1;
        }
        return 0;
    }
}
