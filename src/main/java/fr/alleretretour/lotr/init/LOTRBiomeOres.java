package fr.alleretretour.lotr.init;

import net.minecraft.block.BlockState;
import net.minecraft.world.biome.BiomeGenerationSettings;
import net.minecraft.world.gen.GenerationStage;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.OreFeatureConfig;
import net.minecraft.world.gen.placement.Placement;
import net.minecraft.world.gen.placement.TopSolidRangeConfig;
import net.minecraftforge.registries.ForgeRegistries;

/**
 * PORT de LOTRBiomeDecorator.addDefaultOres() : minerais aux VALEURS DU LEGACY
 * (taille de veine, chance par chunk, altitudes min/max), multipliees par le
 * facteur du biome (biomeOreFactor / biomeGemFactor, defaut 1.0 / 0.5).
 *
 * Les minerais du mod sont resolus par nom ; tous sont desormais portes
 * (cuivre, etain, soufre, salpetre, sel, argent et les huit gemmes).
 */
public final class LOTRBiomeOres {

    private LOTRBiomeOres() {
    }

    public static void apply(String name, BiomeGenerationSettings.Builder b) {
        float oreFactor = 1.0f;
        float gemFactor = 0.5f;
        if (name.equals("misty_mountains")) { oreFactor = 1.0f; gemFactor = 1.0f; }
        if (name.equals("white_mountains")) { oreFactor = 1.0f; gemFactor = 1.0f; }
        if (name.equals("iron_hills")) { oreFactor = 1.0f; gemFactor = 0.75f; }
        if (name.equals("ettenmoors")) { oreFactor = 1.0f; gemFactor = 0.75f; }
        if (name.equals("grey_mountains")) { oreFactor = 1.0f; gemFactor = 1.0f; }
        if (name.equals("blue_mountains")) { oreFactor = 1.0f; gemFactor = 1.0f; }
        if (name.equals("angmar_mountains")) { oreFactor = 1.0f; gemFactor = 0.75f; }
        if (name.equals("coldfells")) { oreFactor = 1.0f; gemFactor = 0.75f; }
        if (name.equals("erebor")) { oreFactor = 1.0f; gemFactor = 1.0f; }
        if (name.equals("misty_mountains_foothills")) { oreFactor = 1.0f; gemFactor = 0.75f; }
        if (name.equals("grey_mountains_foothills")) { oreFactor = 1.0f; gemFactor = 0.75f; }
        if (name.equals("blue_mountains_foothills")) { oreFactor = 1.0f; gemFactor = 0.75f; }
        if (name.equals("white_mountains_foothills")) { oreFactor = 1.0f; gemFactor = 0.75f; }
        if (name.equals("harad_mountains")) { oreFactor = 1.0f; gemFactor = 1.0f; }
        if (name.equals("red_mountains")) { oreFactor = 2.0f; gemFactor = 1.5f; }
        if (name.equals("red_mountains_foothills")) { oreFactor = 1.0f; gemFactor = 1.0f; }
        if (name.equals("wind_mountains")) { oreFactor = 1.0f; gemFactor = 1.0f; }
        if (name.equals("wind_mountains_foothills")) { oreFactor = 1.0f; gemFactor = 0.75f; }
        if (name.equals("far_harad_jungle_mountains")) { oreFactor = 1.0f; gemFactor = 1.0f; }

        // terre et gravier (addSoil) - jamais multiplies par le facteur
        ore(b, "minecraft:dirt", 32, 40.0f, 0, 256);
        ore(b, "minecraft:gravel", 32, 20.0f, 0, 256);

        // minerais (addOre) x biomeOreFactor
        ore(b, "minecraft:coal_ore", 16, 40.0f * oreFactor, 0, 128);
        ore(b, "lotr:copper_ore", 8, 16.0f * oreFactor, 0, 128);
        ore(b, "lotr:tin_ore", 8, 16.0f * oreFactor, 0, 128);
        ore(b, "minecraft:iron_ore", 8, 20.0f * oreFactor, 0, 64);
        ore(b, "lotr:sulfur_ore", 8, 2.0f * oreFactor, 0, 64);
        ore(b, "lotr:saltpeter_ore", 8, 2.0f * oreFactor, 0, 64);
        ore(b, "lotr:salt_ore", 12, 2.0f * oreFactor, 0, 64);
        ore(b, "minecraft:gold_ore", 8, 2.0f * oreFactor, 0, 32);
        ore(b, "lotr:silver_ore", 8, 3.0f * oreFactor, 0, 32);

        // gemmes (addGem) x biomeGemFactor - PORT ligne a ligne du Legacy, ou
        // chaque meta de oreGem avait sa taille de veine, sa frequence et sa
        // profondeur propres : les gemmes rares descendent plus bas.
        ore(b, "lotr:amethyst_ore", 6, 2.0f * gemFactor, 0, 64);    // meta 1
        ore(b, "lotr:topaz_ore", 6, 2.0f * gemFactor, 0, 64);       // meta 0
        ore(b, "lotr:amber_ore", 5, 1.5f * gemFactor, 0, 48);       // meta 4
        ore(b, "lotr:opal_ore", 5, 1.5f * gemFactor, 0, 48);        // meta 6
        ore(b, "lotr:sapphire_ore", 4, 1.0f * gemFactor, 0, 32);    // meta 2
        ore(b, "lotr:ruby_ore", 4, 1.0f * gemFactor, 0, 32);        // meta 3
        ore(b, "lotr:lotr_emerald_ore", 4, 0.75f * gemFactor, 0, 24);  // meta 7
        ore(b, "lotr:lotr_diamond_ore", 4, 0.5f * gemFactor, 0, 16);   // meta 5
    }

    /** chancePerChunk du Legacy -> count vanilla (arrondi au superieur, min 1). */
    private static void ore(BiomeGenerationSettings.Builder b, String blockId,
                            int veinSize, float chancePerChunk, int minY, int maxY) {
        net.minecraft.block.Block block = ForgeRegistries.BLOCKS
                .getValue(new net.minecraft.util.ResourceLocation(blockId));
        if (block == null || block == net.minecraft.block.Blocks.AIR) {
            return;  // minerai pas encore porte : on ne genere rien
        }
        BlockState state = block.defaultBlockState();
        int count = Math.max(1, Math.round(chancePerChunk));
        b.addFeature(GenerationStage.Decoration.UNDERGROUND_ORES,
                Feature.ORE.configured(new OreFeatureConfig(
                        OreFeatureConfig.FillerBlockType.NATURAL_STONE, state, veinSize))
                        .decorated(Placement.RANGE.configured(
                                new TopSolidRangeConfig(minY, 0, maxY)))
                        .squared()
                        .count(count));
    }
}
