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


    // ===== LOT MARCHANDS =====

    public static List<Entry> dwarfSmithBuy() {
        List<Entry> list = new ArrayList<>();
            add(list, "lotr:dwarven_sword", 1, 16);
            add(list, "lotr:dwarven_spear", 1, 18);
            add(list, "lotr:dwarven_battleaxe", 1, 18);
            add(list, "lotr:dwarven_hammer", 1, 18);
            add(list, "lotr:dwarven_pike", 1, 18);
            add(list, "lotr:dwarven_dagger", 1, 13);
            add(list, "lotr:dwarven_axe", 1, 15);
            add(list, "lotr:dwarven_pickaxe", 1, 14);
            add(list, "lotr:dwarven_shovel", 1, 12);
            add(list, "lotr:dwarven_mattock", 1, 18);
            add(list, "lotr:dwarven_throwing_axe", 1, 15);
            add(list, "lotr:dwarven_helmet", 1, 25);
            add(list, "lotr:dwarven_chestplate", 1, 36);
            add(list, "lotr:dwarven_leggings", 1, 30);
            add(list, "lotr:dwarven_boots", 1, 22);
            add(list, "lotr:dwarven_silver_helmet", 1, 50);
            add(list, "lotr:dwarven_silver_chestplate", 1, 60);
            add(list, "lotr:dwarven_silver_leggings", 1, 55);
            add(list, "lotr:dwarven_silver_boots", 1, 50);
            add(list, "lotr:dwarven_gold_helmet", 1, 70);
            add(list, "lotr:dwarven_gold_chestplate", 1, 80);
            add(list, "lotr:dwarven_gold_leggings", 1, 75);
            add(list, "lotr:dwarven_gold_boots", 1, 70);
        return list;
    }

    public static List<Entry> dwarfSmithSell() {
        List<Entry> list = new ArrayList<>();
            add(list, "minecraft:iron_ingot", 1, 3);
            add(list, "minecraft:gold_ingot", 1, 15);
            add(list, "minecraft:string", 3, 1);
            add(list, "minecraft:cooked_beef", 1, 3);
            add(list, "minecraft:cooked_porkchop", 1, 3);
            add(list, "lotr:mutton_cooked", 1, 3);
            add(list, "minecraft:cooked_chicken", 1, 3);
            add(list, "lotr:gammon", 1, 3);
            add(list, "lotr:rabbit_cooked", 1, 3);
            add(list, "lotr:deer_cooked", 1, 3);
            add(list, "minecraft:bread", 1, 2);
        return list;
    }

    public static List<Entry> blueMountainsSmithBuy() {
        List<Entry> list = new ArrayList<>();
            add(list, "lotr:blue_dwarven_sword", 1, 16);
            add(list, "lotr:blue_dwarven_spear", 1, 18);
            add(list, "lotr:blue_dwarven_battleaxe", 1, 18);
            add(list, "lotr:blue_dwarven_hammer", 1, 18);
            add(list, "lotr:blue_dwarven_pike", 1, 18);
            add(list, "lotr:blue_dwarven_dagger", 1, 13);
            add(list, "lotr:blue_dwarven_axe", 1, 15);
            add(list, "lotr:blue_dwarven_pickaxe", 1, 14);
            add(list, "lotr:blue_dwarven_shovel", 1, 12);
            add(list, "lotr:blue_dwarven_mattock", 1, 18);
            add(list, "lotr:blue_dwarven_throwing_axe", 1, 15);
            add(list, "lotr:blue_dwarven_helmet", 1, 25);
            add(list, "lotr:blue_dwarven_chestplate", 1, 36);
            add(list, "lotr:blue_dwarven_leggings", 1, 30);
            add(list, "lotr:blue_dwarven_boots", 1, 22);
        return list;
    }

    public static List<Entry> blueMountainsSmithSell() {
        List<Entry> list = new ArrayList<>();
            add(list, "minecraft:iron_ingot", 1, 3);
            add(list, "minecraft:gold_ingot", 1, 15);
            add(list, "minecraft:string", 3, 1);
            add(list, "minecraft:cooked_beef", 1, 3);
            add(list, "minecraft:cooked_porkchop", 1, 3);
            add(list, "lotr:mutton_cooked", 1, 3);
            add(list, "minecraft:cooked_chicken", 1, 3);
            add(list, "lotr:gammon", 1, 3);
            add(list, "lotr:rabbit_cooked", 1, 3);
            add(list, "lotr:deer_cooked", 1, 3);
            add(list, "minecraft:bread", 1, 2);
        return list;
    }

    public static List<Entry> hobbitBartenderBuy() {
        List<Entry> list = new ArrayList<>();
            add(list, "minecraft:cooked_beef", 1, 7);
            add(list, "minecraft:cooked_porkchop", 1, 7);
            add(list, "lotr:mutton_cooked", 1, 7);
            add(list, "minecraft:cooked_chicken", 1, 6);
            add(list, "minecraft:bread", 1, 5);
            add(list, "minecraft:baked_potato", 2, 7);
            add(list, "lotr:gammon", 1, 7);
            add(list, "lotr:deer_cooked", 1, 7);
            add(list, "lotr:rabbit_cooked", 1, 6);
            add(list, "lotr:rabbit_stew", 1, 10);
            add(list, "minecraft:mushroom_stew", 1, 10);
            add(list, "lotr:mushroom_pie", 1, 10);
            add(list, "lotr:leek_soup", 1, 10);
            add(list, "lotr:hobbit_pancake", 1, 10);
        return list;
    }

    public static List<Entry> hobbitBartenderSell() {
        List<Entry> list = new ArrayList<>();
            add(list, "minecraft:beef", 1, 3);
            add(list, "minecraft:porkchop", 1, 3);
            add(list, "lotr:mutton_raw", 1, 3);
            add(list, "minecraft:chicken", 1, 3);
            add(list, "lotr:rabbit_raw", 1, 3);
            add(list, "lotr:deer_raw", 1, 3);
            add(list, "minecraft:potato", 2, 1);
        return list;
    }

    public static List<Entry> breeInnkeeperBuy() {
        List<Entry> list = new ArrayList<>();
            add(list, "minecraft:cooked_beef", 1, 7);
            add(list, "minecraft:cooked_porkchop", 1, 7);
            add(list, "lotr:mutton_cooked", 1, 7);
            add(list, "minecraft:cooked_chicken", 1, 6);
            add(list, "lotr:deer_cooked", 1, 7);
            add(list, "lotr:rabbit_cooked", 1, 6);
            add(list, "lotr:rabbit_stew", 1, 10);
            add(list, "minecraft:bread", 1, 5);
            add(list, "minecraft:baked_potato", 2, 7);
            add(list, "lotr:gammon", 1, 7);
            add(list, "minecraft:mushroom_stew", 1, 10);
            add(list, "lotr:mushroom_pie", 1, 10);
            add(list, "lotr:leek_soup", 1, 10);
        return list;
    }

    public static List<Entry> breeInnkeeperSell() {
        List<Entry> list = new ArrayList<>();
            add(list, "minecraft:beef", 1, 3);
            add(list, "minecraft:porkchop", 1, 3);
            add(list, "lotr:mutton_raw", 1, 3);
            add(list, "minecraft:chicken", 1, 3);
            add(list, "lotr:rabbit_raw", 1, 3);
            add(list, "lotr:deer_raw", 1, 3);
            add(list, "minecraft:potato", 2, 1);
            add(list, "minecraft:wheat", 2, 1);
        return list;
    }

    public static List<Entry> daleBlacksmithBuy() {
        List<Entry> list = new ArrayList<>();
            add(list, "lotr:dale_sword", 1, 15);
            add(list, "lotr:dale_battleaxe", 1, 18);
            add(list, "lotr:dale_dagger", 1, 9);
            add(list, "lotr:dale_spear", 1, 16);
            add(list, "lotr:dale_pike", 1, 16);
            add(list, "lotr:dale_helmet", 1, 18);
            add(list, "lotr:dale_chestplate", 1, 28);
            add(list, "lotr:dale_leggings", 1, 24);
            add(list, "lotr:dale_boots", 1, 16);
            add(list, "minecraft:iron_bars", 8, 20);
            add(list, "lotr:iron_crossbow", 1, 15);
        return list;
    }

    public static List<Entry> daleBlacksmithSell() {
        List<Entry> list = new ArrayList<>();
            add(list, "minecraft:iron_ingot", 1, 3);
            add(list, "minecraft:gold_ingot", 1, 15);
            add(list, "minecraft:string", 3, 1);
            add(list, "minecraft:leather", 1, 2);
        return list;
    }

    public static List<Entry> easterlingBlacksmithBuy() {
        List<Entry> list = new ArrayList<>();
            add(list, "lotr:rhun_sword", 1, 15);
            add(list, "lotr:rhun_dagger", 1, 9);
            add(list, "lotr:rhun_spear", 1, 16);
            add(list, "lotr:rhun_pike", 1, 16);
            add(list, "lotr:rhun_polearm", 1, 16);
            add(list, "lotr:rhun_battleaxe", 1, 18);
            add(list, "lotr:rhun_helmet", 1, 20);
            add(list, "lotr:rhun_chestplate", 1, 32);
            add(list, "lotr:rhun_leggings", 1, 26);
            add(list, "lotr:rhun_boots", 1, 17);
            add(list, "lotr:rhun_gold_helmet", 1, 30);
            add(list, "lotr:rhun_gold_chestplate", 1, 45);
            add(list, "lotr:rhun_gold_leggings", 1, 35);
            add(list, "lotr:rhun_gold_boots", 1, 25);
            add(list, "minecraft:iron_bars", 8, 20);
            add(list, "lotr:iron_crossbow", 1, 15);
            add(list, "lotr:bronze_crossbow", 1, 12);
        return list;
    }

    public static List<Entry> easterlingBlacksmithSell() {
        List<Entry> list = new ArrayList<>();
            add(list, "minecraft:iron_ingot", 1, 3);
            add(list, "minecraft:gold_ingot", 1, 15);
            add(list, "minecraft:string", 3, 1);
            add(list, "minecraft:leather", 1, 2);
        return list;
    }

    public static List<Entry> nearHaradMerchantBuy() {
        List<Entry> list = new ArrayList<>();
            add(list, "lotr:near_harad_scimitar", 1, 20);
            add(list, "lotr:near_harad_spear", 1, 20);
            add(list, "lotr:near_harad_poleaxe", 1, 20);
            add(list, "lotr:near_harad_mace", 1, 20);
            add(list, "lotr:near_harad_pike", 1, 20);
            add(list, "lotr:near_harad_dagger", 1, 13);
            add(list, "lotr:near_harad_poisoned_dagger", 1, 16);
            add(list, "lotr:harad_sword", 1, 18);
            add(list, "lotr:harad_spear", 1, 18);
            add(list, "lotr:harad_pike", 1, 18);
            add(list, "lotr:harad_dagger", 1, 12);
            add(list, "lotr:harad_poisoned_dagger", 1, 15);
            add(list, "lotr:gulf_harad_sword", 1, 25);
            add(list, "lotr:near_harad_helmet", 1, 25);
            add(list, "lotr:near_harad_chestplate", 1, 36);
            add(list, "lotr:near_harad_leggings", 1, 30);
            add(list, "lotr:near_harad_boots", 1, 22);
            add(list, "lotr:harnedor_helmet", 1, 22);
            add(list, "lotr:harnedor_chestplate", 1, 34);
            add(list, "lotr:harnedor_leggings", 1, 28);
            add(list, "lotr:harnedor_boots", 1, 20);
            add(list, "lotr:umbar_helmet", 1, 28);
            add(list, "lotr:umbar_chestplate", 1, 40);
            add(list, "lotr:umbar_leggings", 1, 32);
            add(list, "lotr:umbar_boots", 1, 25);
            add(list, "lotr:gulf_harad_helmet", 1, 25);
            add(list, "lotr:gulf_harad_chestplate", 1, 36);
            add(list, "lotr:gulf_harad_leggings", 1, 30);
            add(list, "lotr:gulf_harad_boots", 1, 22);
            add(list, "lotr:near_harad_bow", 1, 20);
            add(list, "lotr:moredain_sword", 1, 18);
            add(list, "lotr:lemon", 1, 5);
            add(list, "lotr:orange", 1, 5);
            add(list, "lotr:lime", 1, 5);
            add(list, "lotr:plum", 1, 5);
            add(list, "lotr:olive", 1, 5);
            add(list, "lotr:almond", 1, 5);
            add(list, "lotr:marzipan", 1, 10);
            add(list, "lotr:marzipan_chocolate", 1, 15);
            add(list, "lotr:mango", 1, 5);
            add(list, "lotr:lion_cooked", 1, 8);
            add(list, "lotr:rhino_cooked", 1, 8);
            add(list, "lotr:camel_cooked", 1, 8);
        return list;
    }

    public static List<Entry> nearHaradMerchantSell() {
        List<Entry> list = new ArrayList<>();
            add(list, "minecraft:iron_ingot", 1, 3);
            add(list, "minecraft:gold_ingot", 1, 15);
            add(list, "minecraft:bread", 1, 2);
            add(list, "minecraft:wheat", 2, 2);
            add(list, "minecraft:sugar", 2, 1);
        return list;
    }

    public static List<Entry> moredainTraderBuy() {
        List<Entry> list = new ArrayList<>();
            add(list, "lotr:lion_raw", 1, 6);
            add(list, "lotr:zebra_raw", 1, 4);
            add(list, "lotr:rhino_raw", 1, 6);
            add(list, "minecraft:rotten_flesh", 1, 3);
        return list;
    }

    public static List<Entry> moredainTraderSell() {
        List<Entry> list = new ArrayList<>();
            add(list, "minecraft:bread", 1, 2);
            add(list, "lotr:yam_roast", 1, 2);
            add(list, "minecraft:stick", 8, 1);
            add(list, "minecraft:string", 3, 1);
            add(list, "lotr:mango", 1, 2);
            add(list, "lotr:moredain_spear", 1, 10);
            add(list, "minecraft:gold_nugget", 1, 2);
        return list;
    }

    public static List<Entry> angmarOrcTraderBuy() {
        List<Entry> list = new ArrayList<>();
            add(list, "lotr:angmar_helmet", 1, 20);
            add(list, "lotr:angmar_chestplate", 1, 30);
            add(list, "lotr:angmar_leggings", 1, 26);
            add(list, "lotr:angmar_boots", 1, 18);
            add(list, "lotr:angmar_sword", 1, 14);
            add(list, "lotr:angmar_spear", 1, 15);
            add(list, "lotr:angmar_dagger", 1, 8);
            add(list, "lotr:angmar_poisoned_dagger", 1, 10);
            add(list, "lotr:angmar_battleaxe", 1, 18);
            add(list, "lotr:angmar_polearm", 1, 16);
            add(list, "lotr:orc_bow", 1, 16);
            add(list, "lotr:angmar_pickaxe", 1, 14);
            add(list, "lotr:angmar_axe", 1, 14);
            add(list, "lotr:angmar_hammer", 1, 16);
            add(list, "lotr:maggoty_bread", 1, 4);
        return list;
    }

    public static List<Entry> angmarOrcTraderSell() {
        List<Entry> list = new ArrayList<>();
            add(list, "minecraft:iron_ingot", 1, 3);
            add(list, "minecraft:string", 3, 1);
            add(list, "minecraft:wheat", 2, 1);
            add(list, "minecraft:bone", 1, 1);
        return list;
    }

    public static List<Entry> dolGuldurOrcTraderBuy() {
        List<Entry> list = new ArrayList<>();
            add(list, "lotr:dol_guldur_helmet", 1, 20);
            add(list, "lotr:dol_guldur_chestplate", 1, 30);
            add(list, "lotr:dol_guldur_leggings", 1, 26);
            add(list, "lotr:dol_guldur_boots", 1, 18);
            add(list, "lotr:dol_guldur_sword", 1, 14);
            add(list, "lotr:dol_guldur_spear", 1, 15);
            add(list, "lotr:dol_guldur_dagger", 1, 8);
            add(list, "lotr:dol_guldur_poisoned_dagger", 1, 10);
            add(list, "lotr:dol_guldur_battleaxe", 1, 18);
            add(list, "lotr:dol_guldur_pike", 1, 18);
            add(list, "lotr:orc_bow", 1, 16);
            add(list, "lotr:dol_guldur_pickaxe", 1, 14);
            add(list, "lotr:dol_guldur_axe", 1, 14);
            add(list, "lotr:dol_guldur_hammer", 1, 16);
            add(list, "lotr:maggoty_bread", 1, 4);
            add(list, "minecraft:string", 1, 2);
        return list;
    }

    public static List<Entry> dolGuldurOrcTraderSell() {
        List<Entry> list = new ArrayList<>();
            add(list, "minecraft:iron_ingot", 1, 3);
            add(list, "minecraft:wheat", 2, 1);
            add(list, "minecraft:bone", 1, 1);
        return list;
    }

}
