package fr.alleretretour.lotr.trade;

import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.ArrayList;
import java.util.List;

/** GENERE depuis LOTRTradeEntries (Legacy). Tables d'echange par metier. */
public class LOTRTradeTables {

    public static class Entry {
        public final ItemStack stack;
        public final int cost;

        public Entry(ItemStack stack, int cost) {
            this.stack = stack;
            this.cost = cost;
        }
    }

    private static void add(List<Entry> list, String id, int qty, int cost) {
        net.minecraft.item.Item item = ForgeRegistries.ITEMS.getValue(new ResourceLocation(id));
        if (item != null) {
            list.add(new Entry(new ItemStack(item, qty), cost));
        }
    }

    public static List<Entry> gondorBlacksmithBuy() {
        List<Entry> list = new ArrayList<>();
            add(list, "lotr:gondor_sword", 1, 15);
            add(list, "lotr:gondor_dagger", 1, 9);
            add(list, "lotr:gondor_spear", 1, 16);
            add(list, "lotr:gondor_helmet", 1, 20);
            add(list, "lotr:gondor_chestplate", 1, 32);
            add(list, "lotr:gondor_leggings", 1, 26);
            add(list, "lotr:gondor_boots", 1, 17);
            add(list, "lotr:hammer_blacksmith", 1, 18);
            add(list, "minecraft:iron_bars", 8, 20);
            add(list, "lotr:gondor_hammer", 1, 18);
            add(list, "lotr:crossbow_bolt", 4, 3);
            add(list, "lotr:iron_crossbow", 1, 15);
        return list;
    }

    public static List<Entry> gondorBlacksmithSell() {
        List<Entry> list = new ArrayList<>();
            add(list, "minecraft:iron_ingot", 1, 3);
            add(list, "minecraft:coal", 2, 1);
            add(list, "minecraft:gold_ingot", 1, 15);
            add(list, "lotr:copper", 1, 3);
            add(list, "lotr:tin", 1, 3);
            add(list, "minecraft:string", 3, 1);
            add(list, "minecraft:leather", 1, 2);
        return list;
    }
}
