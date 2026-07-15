package fr.alleretretour.lotr.init;

import fr.alleretretour.lotr.tileentity.LOTRTileEntityBarrel;

/**
 * GENERE AUTOMATIQUEMENT depuis LOTRBrewingRecipes.java (Legacy).
 * Chaque brassin : 6 ingredients + 3 seaux d'eau -> 16 portions, puissance
 * croissante avec le temps de fermentation.
 */
public class LOTRBrewingRecipesData {

    public static void init() {
        LOTRTileEntityBarrel.RECIPES.add(new LOTRTileEntityBarrel.BrewEntry("lotr:mug_ale", "minecraft:wheat", "minecraft:wheat", "minecraft:wheat", "minecraft:wheat", "minecraft:wheat", "minecraft:wheat"));
        LOTRTileEntityBarrel.RECIPES.add(new LOTRTileEntityBarrel.BrewEntry("lotr:mug_miruvor", "lotr:mallorn_nut", "lotr:mallorn_nut", "lotr:mallorn_nut", "lotr:elanor", "lotr:niphredil", "minecraft:sugar"));
        LOTRTileEntityBarrel.RECIPES.add(new LOTRTileEntityBarrel.BrewEntry("lotr:mug_mead", "minecraft:sugar", "minecraft:sugar", "minecraft:sugar", "minecraft:sugar", "minecraft:sugar", "minecraft:sugar"));
        LOTRTileEntityBarrel.RECIPES.add(new LOTRTileEntityBarrel.BrewEntry("lotr:mug_cider", "minecraft:apple", "minecraft:apple", "minecraft:apple", "minecraft:apple", "minecraft:apple", "minecraft:apple"));
        LOTRTileEntityBarrel.RECIPES.add(new LOTRTileEntityBarrel.BrewEntry("lotr:mug_perry", "lotr:pear", "lotr:pear", "lotr:pear", "lotr:pear", "lotr:pear", "lotr:pear"));
        LOTRTileEntityBarrel.RECIPES.add(new LOTRTileEntityBarrel.BrewEntry("lotr:mug_cherry_liqueur", "lotr:cherry", "lotr:cherry", "lotr:cherry", "lotr:cherry", "lotr:cherry", "lotr:cherry"));
        LOTRTileEntityBarrel.RECIPES.add(new LOTRTileEntityBarrel.BrewEntry("lotr:mug_rum", "minecraft:sugar_cane", "minecraft:sugar_cane", "minecraft:sugar_cane", "minecraft:sugar_cane", "minecraft:sugar_cane", "minecraft:sugar_cane"));
        LOTRTileEntityBarrel.RECIPES.add(new LOTRTileEntityBarrel.BrewEntry("lotr:mug_athelas_brew", "lotr:athelas", "lotr:athelas", "lotr:athelas", "lotr:athelas", "lotr:athelas", "lotr:athelas"));
        LOTRTileEntityBarrel.RECIPES.add(new LOTRTileEntityBarrel.BrewEntry("lotr:mug_vodka", "minecraft:potato", "minecraft:potato", "minecraft:potato", "minecraft:potato", "minecraft:potato", "minecraft:potato"));
        LOTRTileEntityBarrel.RECIPES.add(new LOTRTileEntityBarrel.BrewEntry("lotr:mug_maple_beer", "minecraft:wheat", "minecraft:wheat", "minecraft:wheat", "minecraft:wheat", "lotr:maple_syrup", "lotr:maple_syrup"));
        LOTRTileEntityBarrel.RECIPES.add(new LOTRTileEntityBarrel.BrewEntry("lotr:mug_carrot_wine", "minecraft:carrot", "minecraft:carrot", "minecraft:carrot", "minecraft:carrot", "minecraft:carrot", "minecraft:carrot"));
        LOTRTileEntityBarrel.RECIPES.add(new LOTRTileEntityBarrel.BrewEntry("lotr:mug_melon_liqueur", "minecraft:melon_slice", "minecraft:melon_slice", "minecraft:melon_slice", "minecraft:melon_slice", "minecraft:melon_slice", "minecraft:melon_slice"));
        LOTRTileEntityBarrel.RECIPES.add(new LOTRTileEntityBarrel.BrewEntry("lotr:mug_cactus_liqueur", "minecraft:cactus", "minecraft:cactus", "minecraft:cactus", "minecraft:cactus", "minecraft:cactus", "minecraft:cactus"));
        LOTRTileEntityBarrel.RECIPES.add(new LOTRTileEntityBarrel.BrewEntry("lotr:mug_lemon_liqueur", "lotr:lemon", "lotr:lemon", "lotr:lemon", "lotr:lemon", "lotr:lemon", "lotr:lemon"));
        LOTRTileEntityBarrel.RECIPES.add(new LOTRTileEntityBarrel.BrewEntry("lotr:mug_lime_liqueur", "lotr:lime", "lotr:lime", "lotr:lime", "lotr:lime", "lotr:lime", "lotr:lime"));
        LOTRTileEntityBarrel.RECIPES.add(new LOTRTileEntityBarrel.BrewEntry("lotr:mug_corn_liquor", "lotr:corn", "lotr:corn", "lotr:corn", "lotr:corn", "lotr:corn", "lotr:corn"));
        LOTRTileEntityBarrel.RECIPES.add(new LOTRTileEntityBarrel.BrewEntry("lotr:mug_red_wine", "lotr:grape_red", "lotr:grape_red", "lotr:grape_red", "lotr:grape_red", "lotr:grape_red", "lotr:grape_red"));
        LOTRTileEntityBarrel.RECIPES.add(new LOTRTileEntityBarrel.BrewEntry("lotr:mug_white_wine", "lotr:grape_white", "lotr:grape_white", "lotr:grape_white", "lotr:grape_white", "lotr:grape_white", "lotr:grape_white"));
        LOTRTileEntityBarrel.RECIPES.add(new LOTRTileEntityBarrel.BrewEntry("lotr:mug_plum_kvass", "minecraft:wheat", "minecraft:wheat", "minecraft:wheat", "lotr:plum", "lotr:plum", "lotr:plum"));
        LOTRTileEntityBarrel.RECIPES.add(new LOTRTileEntityBarrel.BrewEntry("lotr:mug_sour_milk", "minecraft:milk_bucket", "minecraft:milk_bucket", "minecraft:milk_bucket", "minecraft:milk_bucket", "minecraft:milk_bucket", "minecraft:milk_bucket"));
        LOTRTileEntityBarrel.RECIPES.add(new LOTRTileEntityBarrel.BrewEntry("lotr:mug_pomegranate_wine", "lotr:pomegranate", "lotr:pomegranate", "lotr:pomegranate", "lotr:pomegranate", "lotr:pomegranate", "lotr:pomegranate"));
    }
}
