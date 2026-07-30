package fr.alleretretour.lotr.world;

import fr.alleretretour.lotr.init.LOTRFeatures;
import fr.alleretretour.lotr.init.LOTRTreeFeatures;
import net.minecraft.world.gen.GenerationStage;
import net.minecraft.world.gen.feature.ConfiguredFeature;
import net.minecraft.world.gen.placement.AtSurfaceWithExtraConfig;
import net.minecraft.world.gen.placement.Placement;
import net.minecraftforge.event.world.BiomeLoadingEvent;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

/**
 * Ajouts au chargement des biomes : routes et arbres du mod.
 * (Les features configurees n'existent qu'apres le common setup, on ne peut
 * donc pas les declarer a l'enregistrement des biomes.)
 */
@Mod.EventBusSubscriber(modid = "lotr")   // bus FORGE : BiomeLoadingEvent y est publie
public final class LOTRBiomeEvents {

    private LOTRBiomeEvents() {
    }

    @SubscribeEvent(priority = EventPriority.HIGH)
    public static void onBiomeLoading(BiomeLoadingEvent event) {
        if (event.getName() == null || !"lotr".equals(event.getName().getNamespace())) {
            return;
        }
        String name = event.getName().getPath();

        if (LOTRFeatures.roads() != null) {
            event.getGeneration().getFeatures(GenerationStage.Decoration.RAW_GENERATION)
                    .add(() -> LOTRFeatures.roads());
        }

        // structures (frequences du Legacy : 1 chance sur N par chunk)
        if (name.equals("adornland")) {
            structure(event, LOTRFeatures.dunlendingCampfire(), 200);
        }
        if (name.equals("dunland")) {
            structure(event, LOTRFeatures.dunlendingCampfire(), 40);
        }
        if (name.equals("gulf_harad")) {
            structure(event, LOTRFeatures.haradObelisk(), 3000);
        }
        if (name.equals("near_harad")) {
            structure(event, LOTRFeatures.haradObelisk(), 3000);
        }
        if (name.equals("near_harad_fertile")) {
            structure(event, LOTRFeatures.haradObelisk(), 3000);
        }
        if (name.equals("near_harad_riverbank")) {
            structure(event, LOTRFeatures.haradObelisk(), 3000);
        }
        if (name.equals("near_harad_semi_desert")) {
            structure(event, LOTRFeatures.haradObelisk(), 2000);
        }

        // coniferes (generateurs propres du Legacy)
        if (name.equals("fangorn")) {
            conifer(event, LOTRFeatures.beechFangorn(), 0.029f); conifer(event, LOTRFeatures.birchFangorn(), 1.462f);
        }
        if (name.equals("fangorn_wasteland")) {
            conifer(event, LOTRFeatures.charredFangorn(), 0.05f); conifer(event, LOTRFeatures.beechFangornDead(), 0.01f);
        }
        if (name.equals("dol_guldur")) {
            conifer(event, LOTRFeatures.mirkOakDead(), 0.833f);
        }
        if (name.equals("erebor")) {
            conifer(event, LOTRFeatures.oakDead(), 0.312f);
        }
        if (name.equals("fangorn_wasteland")) {
            conifer(event, LOTRFeatures.oakDead(), 0.297f); conifer(event, LOTRFeatures.beechDead(), 0.099f); conifer(event, LOTRFeatures.birchDead(), 0.02f);
        }
        if (name.equals("half_troll_forest")) {
            conifer(event, LOTRFeatures.acaciaDead(), 0.738f); conifer(event, LOTRFeatures.oakDead(), 0.246f);
        }
        if (name.equals("taiga")) {
            conifer(event, LOTRFeatures.spruceDead(), 0.133f);
        }
        if (name.equals("tolfalas")) {
            conifer(event, LOTRFeatures.oakDead(), 1.0f);
        }
        if (name.equals("lothlorien")) {
            conifer(event, LOTRFeatures.mallornExtreme(), 0.048f);
        }
        if (name.equals("dol_guldur")) {
            conifer(event, LOTRFeatures.mirkOak(), 0.167f);
        }
        if (name.equals("mirkwood_corrupted")) {
            conifer(event, LOTRFeatures.mirkOakLarge(), 3.81f);
        }
        if (name.equals("mirkwood_mountains")) {
            conifer(event, LOTRFeatures.mirkOak(), 0.3f); conifer(event, LOTRFeatures.mirkOakLarge(), 0.3f);
        }
        if (name.equals("mirkwood_north")) {
            conifer(event, LOTRFeatures.redOak(), 0.075f); conifer(event, LOTRFeatures.redOakLarge(), 0.05f); conifer(event, LOTRFeatures.mirkOak(), 0.251f);
        }
        if (name.equals("woodland_realm")) {
            conifer(event, LOTRFeatures.redOak(), 0.023f); conifer(event, LOTRFeatures.redOakLarge(), 0.011f);
        }
        if (name.equals("fangorn_wasteland")) {
            conifer(event, LOTRFeatures.charred(), 0.495f);
        }
        if (name.equals("near_harad_fertile_forest")) {
            conifer(event, LOTRFeatures.cedar(), 4.8f);
            conifer(event, LOTRFeatures.cedarLarge(), 1.2f);
        }
        if (name.equals("lothlorien")) {
            conifer(event, LOTRFeatures.mallornBoughs(), 0.963f);
        }
        if (name.equals("lothlorien_edge")) {
            conifer(event, LOTRFeatures.mallornBoughs(), 0.159f);
        }
        if (name.equals("far_harad_mangrove")) {
            conifer(event, LOTRFeatures.mangrove(), 4.926f);
        }
        if (name.equals("far_harad_jungle")) {
            conifer(event, LOTRFeatures.banana(), 0.651f);
        }
        if (name.equals("near_harad_oasis")) {
            conifer(event, LOTRFeatures.datePalm(), 1.053f);
        }
        if (name.equals("eregion")) {
            conifer(event, LOTRFeatures.holly(), 0.447f); conifer(event, LOTRFeatures.hollyLarge(), 0.045f);
        }
        if (name.equals("half_troll_forest")) {
            conifer(event, LOTRFeatures.baobab(), 0.049f);
        }
        if (name.equals("coldfells")) {
            conifer(event, LOTRFeatures.larch(), 0.268f);
        }
        if (name.equals("eregion")) {
            conifer(event, LOTRFeatures.larch(), 0.089f);
        }
        if (name.equals("lothlorien")) {
            conifer(event, LOTRFeatures.larch(), 0.321f);
        }
        if (name.equals("lothlorien_edge")) {
            conifer(event, LOTRFeatures.larch(), 0.635f);
        }
        if (name.equals("mirkwood_north")) {
            conifer(event, LOTRFeatures.larch(), 1.002f);
        }
        if (name.equals("pukel")) {
            conifer(event, LOTRFeatures.larch(), 0.073f);
        }
        if (name.equals("red_mountains")) {
            conifer(event, LOTRFeatures.larch(), 0.12f);
        }
        if (name.equals("white_mountains")) {
            conifer(event, LOTRFeatures.larch(), 0.166f);
        }
        if (name.equals("woodland_realm")) {
            conifer(event, LOTRFeatures.larch(), 0.057f);
        }
        if (name.equals("blue_mountains")) {
            conifer(event, LOTRFeatures.fir(), 0.217f);
        }
        if (name.equals("coldfells")) {
            conifer(event, LOTRFeatures.fir(), 0.446f);
        }
        if (name.equals("erebor")) {
            conifer(event, LOTRFeatures.fir(), 0.25f);
        }
        if (name.equals("eryn_vorn")) {
            conifer(event, LOTRFeatures.fir(), 1.429f);
        }
        if (name.equals("mirkwood_corrupted")) {
            conifer(event, LOTRFeatures.fir(), 0.762f);
        }
        if (name.equals("mirkwood_mountains")) {
            conifer(event, LOTRFeatures.fir(), 1.5f);
        }
        if (name.equals("mirkwood_north")) {
            conifer(event, LOTRFeatures.fir(), 1.002f);
        }
        if (name.equals("old_forest")) {
            conifer(event, LOTRFeatures.fir(), 1.905f);
        }
        if (name.equals("pukel")) {
            conifer(event, LOTRFeatures.fir(), 0.073f);
        }
        if (name.equals("red_mountains")) {
            conifer(event, LOTRFeatures.fir(), 0.2f);
        }
        if (name.equals("rivendell_hills")) {
            conifer(event, LOTRFeatures.fir(), 0.176f);
        }
        if (name.equals("shire_woodlands")) {
            conifer(event, LOTRFeatures.shirePine(), 7.143f);
        }
        if (name.equals("taiga")) {
            conifer(event, LOTRFeatures.fir(), 0.533f);
        }
        if (name.equals("trollshaws")) {
            conifer(event, LOTRFeatures.fir(), 0.042f);
        }
        if (name.equals("white_mountains")) {
            conifer(event, LOTRFeatures.fir(), 0.276f);
        }
        if (name.equals("woodland_realm")) {
            conifer(event, LOTRFeatures.fir(), 0.114f);
        }

        // arbres du mod - frequences exactes du Legacy
        // (poids de l'essence / total du biome x treesPerChunk)
        if (name.equals("coldfells")) {
            tree(event, LOTRTreeFeatures.MAPLE, 0.089f); tree(event, LOTRTreeFeatures.MAPLE_LARGE, 0.009f);
        }
        if (name.equals("dol_guldur")) {
            tree(event, LOTRTreeFeatures.MIRK_OAK, 0.167f);
        }
        if (name.equals("eregion")) {
            tree(event, LOTRTreeFeatures.CHESTNUT, 0.045f); tree(event, LOTRTreeFeatures.CHESTNUT_LARGE, 0.022f);
        }
        if (name.equals("fangorn")) {
            tree(event, LOTRTreeFeatures.BEECH, 0.029f); tree(event, LOTRTreeFeatures.BEECH_LARGE, 0.015f);
        }
        if (name.equals("island")) {
            tree(event, LOTRTreeFeatures.BEECH, 0.039f);
        }
        if (name.equals("lothlorien")) {
            tree(event, LOTRTreeFeatures.BEECH, 0.16f); tree(event, LOTRTreeFeatures.BEECH_LARGE, 0.032f); tree(event, LOTRTreeFeatures.MALLORN, 0.481f);
        }
        if (name.equals("lothlorien_edge")) {
            tree(event, LOTRTreeFeatures.BEECH, 0.317f); tree(event, LOTRTreeFeatures.BEECH_LARGE, 0.063f); tree(event, LOTRTreeFeatures.MALLORN, 0.317f);
        }
        if (name.equals("mirkwood_corrupted")) {
            tree(event, LOTRTreeFeatures.MIRK_OAK_LARGE, 3.81f);
        }
        if (name.equals("mirkwood_mountains")) {
            tree(event, LOTRTreeFeatures.MIRK_OAK, 0.3f); tree(event, LOTRTreeFeatures.MIRK_OAK_LARGE, 0.3f);
        }
        if (name.equals("mirkwood_north")) {
            tree(event, LOTRTreeFeatures.MIRK_OAK, 0.251f); tree(event, LOTRTreeFeatures.CHESTNUT, 0.1f); tree(event, LOTRTreeFeatures.CHESTNUT_LARGE, 0.251f);
        }
        if (name.equals("ocean")) {
            tree(event, LOTRTreeFeatures.BEECH, 0.039f);
        }
        if (name.equals("pukel")) {
            tree(event, LOTRTreeFeatures.BEECH, 0.018f); tree(event, LOTRTreeFeatures.BEECH_LARGE, 0.007f); tree(event, LOTRTreeFeatures.CHESTNUT, 0.073f); tree(event, LOTRTreeFeatures.CHESTNUT_LARGE, 0.018f);
        }
        if (name.equals("red_mountains")) {
            tree(event, LOTRTreeFeatures.MAPLE, 0.12f); tree(event, LOTRTreeFeatures.MAPLE_LARGE, 0.02f);
        }
        if (name.equals("trollshaws")) {
            tree(event, LOTRTreeFeatures.BEECH, 0.209f); tree(event, LOTRTreeFeatures.BEECH_LARGE, 0.084f); tree(event, LOTRTreeFeatures.MAPLE, 0.021f); tree(event, LOTRTreeFeatures.MAPLE_LARGE, 0.008f);
        }
        if (name.equals("white_mountains")) {
            tree(event, LOTRTreeFeatures.BEECH, 0.011f);
        }
        if (name.equals("woodland_realm")) {
            tree(event, LOTRTreeFeatures.CHESTNUT, 0.029f); tree(event, LOTRTreeFeatures.CHESTNUT_LARGE, 0.029f); tree(event, LOTRTreeFeatures.BEECH, 0.029f); tree(event, LOTRTreeFeatures.BEECH_LARGE, 0.057f);
        }
    }

    /**
     * Structure aleatoire : le Legacy tire 1 chance sur chunkChance par chunk
     * (LOTRBiomeDecorator.addRandomStructure).
     */
    private static void structure(BiomeLoadingEvent event, ConfiguredFeature<?, ?> feature,
                                  int chunkChance) {
        if (feature == null) {
            return;
        }
        // PORT : 1 chance sur chunkChance par chunk
        ConfiguredFeature<?, ?> placed = feature.chance(chunkChance);
        event.getGeneration().getFeatures(GenerationStage.Decoration.SURFACE_STRUCTURES)
                .add(() -> placed);
    }

    private static void conifer(BiomeLoadingEvent event, ConfiguredFeature<?, ?> feature,
                                float perChunk) {
        tree(event, feature, perChunk);
    }

    private static void tree(BiomeLoadingEvent event, ConfiguredFeature<?, ?> feature,
                             float perChunk) {
        if (feature == null) {
            return;
        }
        int whole = (int) perChunk;
        float chance = perChunk - whole;
        ConfiguredFeature<?, ?> placed = feature.decorated(Placement.COUNT_EXTRA.configured(
                new AtSurfaceWithExtraConfig(whole, chance, 1)));
        event.getGeneration().getFeatures(GenerationStage.Decoration.VEGETAL_DECORATION)
                .add(() -> placed);
    }
}

/* Toutes les essences du Legacy sont desormais portees.
   Restent liees au portage des STRUCTURES :
     - plateformes des Elfes sylvains dans les grands chenes (LOTRWorldGenWoodElfPlatform)
     - maisons elfiques dans les mallorns geants (HOUSE_CHANCE / HOUSE_ELFLORD_CHANCE)
*/
