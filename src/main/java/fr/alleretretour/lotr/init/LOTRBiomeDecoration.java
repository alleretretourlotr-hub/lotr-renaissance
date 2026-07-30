package fr.alleretretour.lotr.init;

import net.minecraft.world.biome.BiomeGenerationSettings;
import net.minecraft.world.gen.GenerationStage;
import net.minecraft.world.gen.blockstateprovider.SimpleBlockStateProvider;
import net.minecraft.world.gen.feature.BlockClusterFeatureConfig;
import net.minecraft.world.gen.feature.ConfiguredFeature;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.Features;
import net.minecraft.world.gen.feature.IFeatureConfig;
import net.minecraft.world.gen.placement.AtSurfaceWithExtraConfig;
import net.minecraft.world.gen.placement.Placement;

/**
 * PORT de LOTRBiomeDecorator : herbe, fleurs, arbres... aux quantites exactes
 * du Legacy, biome par biome.
 * Les essences propres au mod (mallorn, lebethron, cedre, charred...) attendent
 * le portage des blocs de bois : elles sont listees en fin de fichier avec leur
 * poids d'origine, aucune substitution n'a ete inventee.
 */
public final class LOTRBiomeDecoration {

    private LOTRBiomeDecoration() {
    }

    /** Applique la decoration Legacy du biome nomme. */
    public static void apply(String name, BiomeGenerationSettings.Builder b) {
        if (name.equals("rohan")) {
            grass(b, 15); tallGrass(b, 5); flowers(b, 4);
        }
        if (name.equals("misty_mountains")) {
            grass(b, 3); tallGrass(b, 1); flowers(b, 1);
        }
        if (name.equals("shire")) {
            grass(b, 6); flowers(b, 3); tallFlowers(b, 1);
        }
        if (name.equals("shire_woodlands")) {
            grass(b, 10); tallGrass(b, 2); flowers(b, 6); tallFlowers(b, 2); tree(b, Features.BIRCH, 0.714f);
        }
        if (name.equals("mordor")) {
            grass(b, 1);
        }
        if (name.equals("gondor")) {
            grass(b, 10); tallGrass(b, 1); flowers(b, 1);
        }
        if (name.equals("white_mountains")) {
            grass(b, 8); tallGrass(b, 2); flowers(b, 2); tree(b, Features.OAK, 0.055f); tree(b, Features.FANCY_OAK, 0.028f); tree(b, Features.BIRCH, 0.011f); tree(b, Features.SPRUCE, 0.166f); tree(b, Features.PINE, 0.276f);
        }
        if (name.equals("lothlorien")) {
            grass(b, 8); tallGrass(b, 2); flowers(b, 6); tree(b, Features.OAK, 0.481f); tree(b, Features.FANCY_OAK, 0.08f);
        }
        if (name.equals("celebrant")) {
            grass(b, 12); tallGrass(b, 3); flowers(b, 16); tallFlowers(b, 3);
        }
        if (name.equals("iron_hills")) {
            grass(b, 8); tallGrass(b, 1); flowers(b, 1);
        }
        if (name.equals("dead_marshes")) {
            grass(b, 8); tallGrass(b, 8); sugarCane(b, 10);
        }
        if (name.equals("trollshaws")) {
            grass(b, 6); tallGrass(b, 2); flowers(b, 3); tallFlowers(b, 2); tree(b, Features.OAK, 0.209f); tree(b, Features.FANCY_OAK, 0.084f); tree(b, Features.SPRUCE, 0.042f); tree(b, Features.PINE, 0.042f);
        }
        if (name.equals("woodland_realm")) {
            grass(b, 4); tallGrass(b, 1); flowers(b, 3); tallFlowers(b, 1); tree(b, Features.OAK, 0.029f); tree(b, Features.FANCY_OAK, 0.057f); tree(b, Features.SPRUCE, 0.057f); tree(b, Features.PINE, 0.114f);
        }
        if (name.equals("mirkwood_corrupted")) {
            grass(b, 12); tallGrass(b, 6); mushrooms(b, 4); tree(b, Features.FANCY_OAK, 1.143f); tree(b, Features.SPRUCE, 0.762f); tree(b, Features.PINE, 1.524f);
        }
        if (name.equals("rohan_uruk_highlands")) {
            grass(b, 6); tallGrass(b, 1); flowers(b, 1);
        }
        if (name.equals("emyn_muil")) {
            grass(b, 10); tallGrass(b, 2); flowers(b, 1);
        }
        if (name.equals("ithilien")) {
            grass(b, 10); tallGrass(b, 4); flowers(b, 4); tallFlowers(b, 4); waterlily(b, 2);
        }
        if (name.equals("pelargir")) {
            grass(b, 8); tallGrass(b, 4); flowers(b, 4);
        }
        if (name.equals("lone_lands")) {
            grass(b, 10); tallGrass(b, 6); flowers(b, 3);
        }
        if (name.equals("dunland")) {
            grass(b, 6); tallGrass(b, 1);
        }
        if (name.equals("fangorn")) {
            grass(b, 12); tallGrass(b, 6); flowers(b, 6); tallFlowers(b, 1); tree(b, Features.DARK_OAK, 0.585f); tree(b, Features.OAK, 0.146f); tree(b, Features.FANCY_OAK, 0.146f); tree(b, Features.BIRCH, 0.029f); tree(b, Features.SUPER_BIRCH_BEES_0002, 0.029f); tree(b, Features.BIRCH, 2.923f); tree(b, Features.SUPER_BIRCH_BEES_0002, 2.923f);
        }
        if (name.equals("ettenmoors")) {
            grass(b, 4); tallGrass(b, 2); flowers(b, 1);
        }
        if (name.equals("old_forest")) {
            grass(b, 12); tallGrass(b, 5); flowers(b, 1); mushrooms(b, 2); tree(b, Features.OAK, 1.905f); tree(b, Features.FANCY_OAK, 1.905f); tree(b, Features.DARK_OAK, 3.81f); tree(b, Features.PINE, 1.905f);
        }
        if (name.equals("harondor")) {
            grass(b, 8); tallGrass(b, 1); flowers(b, 4); deadBush(b, 1);
        }
        if (name.equals("eriador")) {
            grass(b, 9); tallGrass(b, 4);
        }
        if (name.equals("eriador_downs")) {
            grass(b, 5); tallGrass(b, 1);
        }
        if (name.equals("eryn_vorn")) {
            tallGrass(b, 2); flowers(b, 4); tallFlowers(b, 1); tree(b, Features.PINE, 7.143f); tree(b, Features.SPRUCE, 1.429f);
        }
        if (name.equals("midgewater")) {
            grass(b, 8); tallGrass(b, 8); waterlily(b, 3); sugarCane(b, 10); mushrooms(b, 3);
        }
        if (name.equals("brown_lands")) {
            grass(b, 2);
        }
        if (name.equals("ocean")) {
            grass(b, 8); tallGrass(b, 1); flowers(b, 2); tallFlowers(b, 1); tree(b, Features.OAK, 0.787f); tree(b, Features.FANCY_OAK, 0.079f); tree(b, Features.BIRCH, 0.079f);
        }
        if (name.equals("anduin_hills")) {
            grass(b, 4); tallGrass(b, 1); flowers(b, 2); tallFlowers(b, 1);
        }
        if (name.equals("meneltarma")) {
            grass(b, 6); tallGrass(b, 2); flowers(b, 5); tallFlowers(b, 1);
        }
        if (name.equals("gladden_fields")) {
            grass(b, 8); tallGrass(b, 8); flowers(b, 2); tallFlowers(b, 10); waterlily(b, 4); sugarCane(b, 10);
        }
        if (name.equals("lothlorien_edge")) {
            grass(b, 8); tallGrass(b, 1); flowers(b, 2); tree(b, Features.OAK, 0.952f); tree(b, Features.FANCY_OAK, 0.159f);
        }
        if (name.equals("enedwaith")) {
            grass(b, 8); tallGrass(b, 4); flowers(b, 1);
        }
        if (name.equals("angmar")) {
            grass(b, 4); tallGrass(b, 1);
        }
        if (name.equals("eregion")) {
            grass(b, 6); tallGrass(b, 3); flowers(b, 3); tallFlowers(b, 1); tree(b, Features.OAK, 0.224f); tree(b, Features.FANCY_OAK, 0.045f); tree(b, Features.BIRCH, 0.045f);
        }
        if (name.equals("lindon")) {
            grass(b, 8); tallGrass(b, 1); flowers(b, 3);
        }
        if (name.equals("lindon_woodlands")) {
            grass(b, 10); tallGrass(b, 3); flowers(b, 4); tallFlowers(b, 1);
        }
        if (name.equals("east_bight")) {
            grass(b, 10); tallGrass(b, 6); flowers(b, 2);
        }
        if (name.equals("blue_mountains")) {
            grass(b, 6); tallGrass(b, 1); flowers(b, 1); tree(b, Features.OAK, 0.13f); tree(b, Features.FANCY_OAK, 0.043f); tree(b, Features.SPRUCE, 0.217f); tree(b, Features.BIRCH, 0.174f); tree(b, Features.PINE, 0.217f);
        }
        if (name.equals("mirkwood_mountains")) {
            tree(b, Features.SPRUCE, 0.45f); tree(b, Features.PINE, 0.45f);
        }
        if (name.equals("wilderland")) {
            grass(b, 14); tallGrass(b, 8); flowers(b, 3); tallFlowers(b, 1);
        }
        if (name.equals("nurn")) {
            grass(b, 8);
        }
        if (name.equals("nurn_marshes")) {
            grass(b, 10); tallGrass(b, 10); sugarCane(b, 10);
        }
        if (name.equals("adornland")) {
            grass(b, 12); tallGrass(b, 3); flowers(b, 2);
        }
        if (name.equals("anduin_mouth")) {
            grass(b, 10); tallGrass(b, 10); flowers(b, 5); waterlily(b, 5); sugarCane(b, 10);
        }
        if (name.equals("entwash_mouth")) {
            grass(b, 10); tallGrass(b, 10); flowers(b, 3); waterlily(b, 2); sugarCane(b, 10);
        }
        if (name.equals("dor_en_ernil")) {
            grass(b, 8); tallGrass(b, 4); flowers(b, 4);
        }
        if (name.equals("fangorn_wasteland")) {
            grass(b, 5); tallGrass(b, 3); flowers(b, 1);
        }
        if (name.equals("rohan_woodlands")) {
            grass(b, 10); tallGrass(b, 3); flowers(b, 3); tallFlowers(b, 1); tree(b, Features.BIRCH, 5.0f);
        }
        if (name.equals("gondor_woodlands")) {
            grass(b, 10); tallGrass(b, 4); flowers(b, 4); tallFlowers(b, 1);
        }
        if (name.equals("barrow_downs")) {
            grass(b, 6); tallGrass(b, 6);
        }
        if (name.equals("long_marshes")) {
            grass(b, 10); tallGrass(b, 10); flowers(b, 2); waterlily(b, 4); sugarCane(b, 10);
        }
        if (name.equals("fangorn_clearing")) {
            grass(b, 10); tallGrass(b, 8); flowers(b, 4);
        }
        if (name.equals("ithilien_hills")) {
            grass(b, 8); flowers(b, 2);
        }
        if (name.equals("ithilien_wasteland")) {
            grass(b, 10); tallGrass(b, 2); flowers(b, 1);
        }
        if (name.equals("nindalf")) {
            grass(b, 12); tallGrass(b, 8); waterlily(b, 3); sugarCane(b, 10);
        }
        if (name.equals("coldfells")) {
            grass(b, 6); tallGrass(b, 2); flowers(b, 2); tallFlowers(b, 1); tree(b, Features.PINE, 0.446f); tree(b, Features.SPRUCE, 0.357f); tree(b, Features.SPRUCE, 0.179f); tree(b, Features.OAK, 0.179f); tree(b, Features.FANCY_OAK, 0.027f);
        }
        if (name.equals("nan_curunir")) {
            grass(b, 4); tallGrass(b, 1); flowers(b, 1);
        }
        if (name.equals("swanfleet")) {
            grass(b, 10); tallGrass(b, 8); flowers(b, 4); waterlily(b, 4); sugarCane(b, 10);
        }
        if (name.equals("pelennor")) {
            grass(b, 6); tallGrass(b, 1); flowers(b, 6);
        }
        if (name.equals("minhiriath")) {
            grass(b, 5); tallGrass(b, 3);
        }
        if (name.equals("erebor")) {
            grass(b, 6); tallGrass(b, 2); flowers(b, 1); tree(b, Features.OAK, 0.062f); tree(b, Features.FANCY_OAK, 0.062f); tree(b, Features.SPRUCE, 0.062f); tree(b, Features.PINE, 0.25f);
        }
        if (name.equals("mirkwood_north")) {
            grass(b, 8); tallGrass(b, 6); flowers(b, 2); tallFlowers(b, 1); tree(b, Features.OAK, 0.251f); tree(b, Features.FANCY_OAK, 0.501f); tree(b, Features.SPRUCE, 0.501f); tree(b, Features.SPRUCE, 0.251f); tree(b, Features.PINE, 2.004f);
        }
        if (name.equals("woodland_realm_hills")) {
            grass(b, 10);
        }
        if (name.equals("pinnath_gelin")) {
            grass(b, 10); tallGrass(b, 3); flowers(b, 3); tallFlowers(b, 1);
        }
        if (name.equals("island")) {
            grass(b, 8); tallGrass(b, 1); flowers(b, 2); tallFlowers(b, 1); tree(b, Features.OAK, 0.787f); tree(b, Features.FANCY_OAK, 0.079f); tree(b, Features.BIRCH, 0.079f);
        }
        if (name.equals("tundra")) {
            grass(b, 4); tallGrass(b, 1); flowers(b, 2);
        }
        if (name.equals("taiga")) {
            grass(b, 8); tallGrass(b, 2); flowers(b, 2); tree(b, Features.SPRUCE, 0.533f); tree(b, Features.SPRUCE, 0.267f); tree(b, Features.PINE, 0.533f);
        }
        if (name.equals("breeland")) {
            grass(b, 8); tallGrass(b, 1); flowers(b, 2); tallFlowers(b, 1);
        }
        if (name.equals("chetwood")) {
            grass(b, 6); tallGrass(b, 1); flowers(b, 4); tallFlowers(b, 2);
        }
        if (name.equals("far_harad")) {
            grass(b, 10); tallGrass(b, 12); flowers(b, 3); tallFlowers(b, 1);
        }
        if (name.equals("umbar")) {
            grass(b, 6); tallGrass(b, 1); flowers(b, 3); tallFlowers(b, 1);
        }
        if (name.equals("far_harad_jungle")) {
            grass(b, 15); tallGrass(b, 10); flowers(b, 4); tallFlowers(b, 4); sugarCane(b, 5); tree(b, Features.JUNGLE_TREE, 13.029f); tree(b, Features.MEGA_JUNGLE_TREE, 6.515f); tree(b, Features.JUNGLE_BUSH, 13.029f);
        }
        if (name.equals("umbar_hills")) {
            grass(b, 6); tallGrass(b, 1); flowers(b, 3); tallFlowers(b, 1);
        }
        if (name.equals("lostladen")) {
            grass(b, 3); tallGrass(b, 1); flowers(b, 1); deadBush(b, 2);
        }
        if (name.equals("far_harad_forest")) {
            grass(b, 8); flowers(b, 4); tallFlowers(b, 3);
        }
        if (name.equals("near_harad_fertile")) {
            grass(b, 10); tallGrass(b, 2); flowers(b, 3); tallFlowers(b, 1); deadBush(b, 1);
        }
        if (name.equals("pertorogwaith")) {
            grass(b, 10); tallGrass(b, 4); sugarCane(b, 10);
        }
        if (name.equals("umbar_forest")) {
            grass(b, 12); tallGrass(b, 4); flowers(b, 4); tallFlowers(b, 1);
        }
        if (name.equals("far_harad_jungle_edge")) {
            tree(b, Features.JUNGLE_TREE, 0.613f); tree(b, Features.MEGA_JUNGLE_TREE, 0.153f); tree(b, Features.JUNGLE_BUSH, 3.065f);
        }
        if (name.equals("tauredain_clearing")) {
            grass(b, 10); tallGrass(b, 6);
        }
        if (name.equals("gulf_harad")) {
            grass(b, 8); tallGrass(b, 2); flowers(b, 1); tallFlowers(b, 1); deadBush(b, 1);
        }
        if (name.equals("dorwinion_hills")) {
            grass(b, 10); tallGrass(b, 5); flowers(b, 3);
        }
        if (name.equals("tolfalas")) {
            grass(b, 10); tallGrass(b, 6);
        }
        if (name.equals("lebennin")) {
            grass(b, 12); tallGrass(b, 6); flowers(b, 3);
        }
        if (name.equals("rhun")) {
            grass(b, 12); tallGrass(b, 8); flowers(b, 1);
        }
        if (name.equals("rhun_forest")) {
            grass(b, 10); tallGrass(b, 2); flowers(b, 4); tallFlowers(b, 1); tree(b, Features.FANCY_OAK, 7.619f);
        }
        if (name.equals("red_mountains")) {
            grass(b, 8); tallGrass(b, 1); flowers(b, 1); tree(b, Features.OAK, 0.12f); tree(b, Features.FANCY_OAK, 0.02f); tree(b, Features.SPRUCE, 0.2f); tree(b, Features.PINE, 0.2f);
        }
        if (name.equals("dol_guldur")) {
            grass(b, 6); tallGrass(b, 1);
        }
        if (name.equals("near_harad_semi_desert")) {
            grass(b, 5); deadBush(b, 1);
        }
        if (name.equals("far_harad_arid")) {
            flowers(b, 1); tallFlowers(b, 1);
        }
        if (name.equals("far_harad_arid_hills")) {
            flowers(b, 1); tallFlowers(b, 1);
        }
        if (name.equals("far_harad_swamp")) {
            grass(b, 10); tallGrass(b, 8); waterlily(b, 3); sugarCane(b, 10); mushrooms(b, 3);
        }
        if (name.equals("far_harad_cloud_forest")) {
            grass(b, 15); tallGrass(b, 10); flowers(b, 4); tallFlowers(b, 4); tree(b, Features.JUNGLE_TREE, 0.906f); tree(b, Features.JUNGLE_BUSH, 1.812f);
        }
        if (name.equals("far_harad_bushland")) {
            grass(b, 16); tallGrass(b, 10);
        }
        if (name.equals("far_harad_bushland_hills")) {
            grass(b, 16); tallGrass(b, 10);
        }
        if (name.equals("far_harad_mangrove")) {
            grass(b, 8); waterlily(b, 3); tree(b, Features.ACACIA, 0.049f);
        }
        if (name.equals("anduin_vale")) {
            grass(b, 10); tallGrass(b, 3); flowers(b, 5); tallFlowers(b, 2);
        }
        if (name.equals("wold")) {
            grass(b, 6); tallGrass(b, 1); flowers(b, 1);
        }
        if (name.equals("shire_moors")) {
            grass(b, 16); tallGrass(b, 1); flowers(b, 16);
        }
        if (name.equals("shire_marshes")) {
            grass(b, 8); tallGrass(b, 6); flowers(b, 4); waterlily(b, 4); sugarCane(b, 10);
        }
        if (name.equals("morgul_vale")) {
            grass(b, 3); flowers(b, 1);
        }
        if (name.equals("eastern_desolation")) {
            grass(b, 3);
        }
        if (name.equals("dale")) {
            grass(b, 10); tallGrass(b, 5); flowers(b, 2); tallFlowers(b, 1);
        }
        if (name.equals("dorwinion")) {
            grass(b, 8); tallGrass(b, 2); flowers(b, 6); tallFlowers(b, 1);
        }
        if (name.equals("wilderland_north")) {
            grass(b, 10); tallGrass(b, 5); flowers(b, 2);
        }
        if (name.equals("near_harad_riverbank")) {
            grass(b, 10); tallGrass(b, 3); flowers(b, 1); tallFlowers(b, 1);
        }
        if (name.equals("lossarnach")) {
            grass(b, 6); tallGrass(b, 2); flowers(b, 12); tallFlowers(b, 4);
        }
        if (name.equals("imloth_melui")) {
            grass(b, 8); tallGrass(b, 3); flowers(b, 20); tallFlowers(b, 12);
        }
        if (name.equals("near_harad_oasis")) {
            grass(b, 10); tallGrass(b, 4); flowers(b, 5); tallFlowers(b, 2);
        }
        if (name.equals("harnedor")) {
            grass(b, 8); tallGrass(b, 1); flowers(b, 3); deadBush(b, 1);
        }
        if (name.equals("lamedon")) {
            grass(b, 6); tallGrass(b, 1); flowers(b, 3); tallFlowers(b, 1);
        }
        if (name.equals("blackroot_vale")) {
            grass(b, 12); tallGrass(b, 3); flowers(b, 8); tallFlowers(b, 2);
        }
        if (name.equals("andrast")) {
            grass(b, 12); tallGrass(b, 4); flowers(b, 3); tallFlowers(b, 1);
        }
        if (name.equals("pukel")) {
            grass(b, 14); tallGrass(b, 6); flowers(b, 3); tallFlowers(b, 1); tree(b, Features.OAK, 0.145f); tree(b, Features.FANCY_OAK, 0.073f); tree(b, Features.BIRCH, 0.018f); tree(b, Features.DARK_OAK, 0.181f); tree(b, Features.PINE, 0.073f); tree(b, Features.SPRUCE, 0.073f);
        }
        if (name.equals("rhun_land")) {
            grass(b, 8); tallGrass(b, 4); flowers(b, 6); tallFlowers(b, 1);
        }
        if (name.equals("rhun_land_steppe")) {
            grass(b, 12); tallGrass(b, 8); flowers(b, 3);
        }
        if (name.equals("rhun_land_hills")) {
            grass(b, 5); tallGrass(b, 1); flowers(b, 2);
        }
        if (name.equals("rhun_red_forest")) {
            grass(b, 8); tallGrass(b, 2); flowers(b, 4); tallFlowers(b, 1);
        }
        if (name.equals("wind_mountains")) {
            grass(b, 4); tallGrass(b, 1); flowers(b, 1);
        }
        if (name.equals("rivendell")) {
            grass(b, 6); tallGrass(b, 1); flowers(b, 5);
        }
        if (name.equals("rivendell_hills")) {
            grass(b, 10); tallGrass(b, 2); flowers(b, 2); tree(b, Features.PINE, 1.765f); tree(b, Features.SPRUCE, 0.176f); tree(b, Features.OAK, 0.176f); tree(b, Features.FANCY_OAK, 0.088f);
        }
        if (name.equals("half_troll_forest")) {
            grass(b, 10); tallGrass(b, 10); flowers(b, 1); tallFlowers(b, 1); tree(b, Features.ACACIA, 1.475f);
        }
        if (name.equals("far_harad_kanuka")) {
            grass(b, 4); tallGrass(b, 1); flowers(b, 3); tallFlowers(b, 1);
        }
    }

    private static void tree(BiomeGenerationSettings.Builder b,
                             ConfiguredFeature<?, ?> tree, float perChunk) {
        int whole = (int) perChunk;
        float chance = perChunk - whole;
        b.addFeature(GenerationStage.Decoration.VEGETAL_DECORATION,
                tree.decorated(Placement.COUNT_EXTRA.configured(
                        new AtSurfaceWithExtraConfig(whole, chance, 1))));
    }

    private static void grass(BiomeGenerationSettings.Builder b, int count) {
        repeat(b, Features.PATCH_GRASS_PLAIN, count);
    }

    private static void tallGrass(BiomeGenerationSettings.Builder b, int count) {
        repeat(b, Features.PATCH_TALL_GRASS, count);
    }

    private static void flowers(BiomeGenerationSettings.Builder b, int count) {
        repeat(b, Features.FLOWER_DEFAULT, count);
    }

    private static void tallFlowers(BiomeGenerationSettings.Builder b, int count) {
        repeat(b, Features.FOREST_FLOWER_VEGETATION, count);
    }

    private static void deadBush(BiomeGenerationSettings.Builder b, int count) {
        repeat(b, Features.PATCH_DEAD_BUSH, count);
    }

    private static void waterlily(BiomeGenerationSettings.Builder b, int count) {
        repeat(b, Features.PATCH_WATERLILLY, count);
    }

    private static void sugarCane(BiomeGenerationSettings.Builder b, int count) {
        repeat(b, Features.PATCH_SUGAR_CANE, count);
    }

    private static void mushrooms(BiomeGenerationSettings.Builder b, int count) {
        repeat(b, Features.BROWN_MUSHROOM_NORMAL, count);
        repeat(b, Features.RED_MUSHROOM_NORMAL, count);
    }

    private static void repeat(BiomeGenerationSettings.Builder b,
                               ConfiguredFeature<?, ?> feature, int count) {
        for (int i = 0; i < count; i++) {
            b.addFeature(GenerationStage.Decoration.VEGETAL_DECORATION, feature);
        }
    }
}

/* TODO - essences du mod a activer quand les bois seront portes :
    // rohan : essences du mod non portees -> BIRCH_LARGE(10), BEECH(20), BEECH_LARGE(10), APPLE(2), PEAR(2)
    // misty_mountains : essences du mod non portees -> LARCH(300), SPRUCE_MEGA(100), SPRUCE_MEGA_THIN(20), FIR(500)
    // shire : essences du mod non portees -> OAK_PARTY(10), CHESTNUT(250), CHESTNUT_LARGE(100), BIRCH_LARGE(10), ASPEN(50), ASPEN_LARGE(10), APPLE(5), PEAR(5)...
    // shire_woodlands : essences du mod non portees -> SHIRE_PINE(2500), ASPEN(300), ASPEN_LARGE(100)
    // mordor : essences du mod non portees -> CHARRED(1000)
    // gondor : essences du mod non portees -> BIRCH_LARGE(20), BEECH(50), BEECH_LARGE(20), APPLE(5), PEAR(5), PLUM(5), OLIVE(1), ALMOND(1)
    // white_mountains : essences du mod non portees -> BIRCH_LARGE(5), BEECH(20), BEECH_LARGE(5), LARCH(300), FIR(500), APPLE(5), PEAR(5)
    // lothlorien : essences du mod non portees -> LARCH(200), BEECH(100), BEECH_LARGE(20), MALLORN(300), MALLORN_BOUGHS(600), MALLORN_PARTY(100), MALLORN_EXTREME(30), ASPEN(100)...
    // celebrant : essences du mod non portees -> BIRCH_LARGE(100), BEECH(500), BEECH_LARGE(100), LARCH(700), CHESTNUT(200), CHESTNUT_LARGE(40), APPLE(5), PEAR(5)
    // iron_hills : essences du mod non portees -> SPRUCE_MEGA(200), SPRUCE_MEGA_THIN(50), FIR(400)
    // dead_marshes : essences du mod non portees -> OAK_DEAD(1000)
    // trollshaws : essences du mod non portees -> OAK_TALL(500), BEECH(500), BEECH_LARGE(200), FIR(100), MAPLE(50), MAPLE_LARGE(20), ASPEN(100), ASPEN_LARGE(20)
    // woodland_realm : essences du mod non portees -> GREEN_OAK(500), GREEN_OAK_LARGE(50), GREEN_OAK_EXTREME(80), RED_OAK(40), RED_OAK_LARGE(20), CHESTNUT(50), CHESTNUT_LARGE(50), BEECH(50)...
    // mirkwood_corrupted : essences du mod non portees -> MIRK_OAK_LARGE(1000), FIR(200)
    // ithilien : essences du mod non portees -> OAK_TALL(100), LEBETHRON(100), LEBETHRON_LARGE(50), BIRCH_LARGE(50), CEDAR(200), CHESTNUT(100), CHESTNUT_LARGE(50), CYPRESS(100)...
    // pelargir : essences du mod non portees -> BIRCH_LARGE(50), CYPRESS(500), CYPRESS_LARGE(100), CEDAR(400), CEDAR_LARGE(50), OLIVE(10), OLIVE_LARGE(10)
    // lone_lands : essences du mod non portees -> BEECH(100), BEECH_LARGE(50), BIRCH_LARGE(5), ASPEN(50), ASPEN_LARGE(10), APPLE(1), PEAR(1)
    // dunland : essences du mod non portees -> OAK_TALL(200), CHESTNUT(50), CHESTNUT_LARGE(10), FIR(500)
    // fangorn : essences du mod non portees -> OAK_TALL(200), OAK_TALLER(200), BIRCH_LARGE(10), BEECH(20), BEECH_LARGE(10), OAK_FANGORN(50), BEECH_FANGORN(20), ASPEN(50)...
    // angle : essences du mod non portees -> OAK_SHRUB(800)
    // ettenmoors : essences du mod non portees -> FIR(400), SPRUCE_DEAD(200), SPRUCE_MEGA(100)
    // old_forest : essences du mod non portees -> OAK_TALL(1000), OAK_TALLER(200), FIR(500)
    // harondor : essences du mod non portees -> OAK_DESERT(1000), CEDAR(250), LEMON(5), ORANGE(5), LIME(5), OLIVE(5), OLIVE_LARGE(5), ALMOND(5)...
    // eriador : essences du mod non portees -> BIRCH_LARGE(10), BEECH(20), BEECH_LARGE(2), CHESTNUT(100), CHESTNUT_LARGE(10), ASPEN(50), ASPEN_LARGE(5), APPLE(2)...
    // eryn_vorn : essences du mod non portees -> FIR(200)
    // grey_mountains : essences du mod non portees -> SPRUCE_MEGA(50), SPRUCE_MEGA_THIN(10), LARCH(500), FIR(500)
    // midgewater : essences du mod non portees -> OAK_TALL(500)
    // brown_lands : essences du mod non portees -> OAK_DEAD(1000)
    // ocean : essences du mod non portees -> BIRCH_LARGE(10), BEECH(50), BEECH_LARGE(5), APPLE(3), PEAR(3)
    // anduin_hills : essences du mod non portees -> BIRCH_LARGE(15), LARCH(150), CHESTNUT(100), CHESTNUT_LARGE(10), FIR(100), APPLE(2), PEAR(2)
    // meneltarma : essences du mod non portees -> CEDAR(1000), CEDAR_LARGE(500), OAK_TALL(200), BIRCH_LARGE(400), BEECH(200), BEECH_LARGE(400)
    // lothlorien_edge : essences du mod non portees -> LARCH(200), BEECH(100), BEECH_LARGE(20), MALLORN(100), MALLORN_BOUGHS(50), MALLORN_PARTY(5), ASPEN(100), ASPEN_LARGE(20)
    // enedwaith : essences du mod non portees -> OAK_TALL(300), CHESTNUT(1000)
    // angmar : essences du mod non portees -> SPRUCE_DEAD(150), CHARRED(150), FIR(100)
    // eregion : essences du mod non portees -> CHESTNUT(100), CHESTNUT_LARGE(50), HOLLY(1000), HOLLY_LARGE(100), BIRCH_LARGE(50), LARCH(200), ASPEN(20), ASPEN_LARGE(5)...
    // lindon : essences du mod non portees -> BIRCH_LARGE(200), BIRCH_PARTY(50), BEECH(100), BEECH_LARGE(25), CHESTNUT(40), CHESTNUT_LARGE(10), ASPEN(300), ASPEN_LARGE(100)...
    // east_bight : essences du mod non portees -> OAK_DEAD(500), SPRUCE_DEAD(500), BEECH_DEAD(500)
    // blue_mountains : essences du mod non portees -> FIR(500)
    // mirkwood_mountains : essences du mod non portees -> MIRK_OAK(200), MIRK_OAK_LARGE(200), FIR(1000)
    // wilderland : essences du mod non portees -> OAK_DEAD(500), SPRUCE_DEAD(100), CHESTNUT(100), CHESTNUT_LARGE(10)
    // dagorlad : essences du mod non portees -> CHARRED(1000)
    // nurn : essences du mod non portees -> OAK_DESERT(500), CEDAR(100), OAK_DEAD(200), CHARRED(200)
    // adornland : essences du mod non portees -> BIRCH_LARGE(10), BEECH(20), BEECH_LARGE(10), CHESTNUT(50), CHESTNUT_LARGE(10), FIR(300), APPLE(2), PEAR(2)
    // anduin_mouth : essences du mod non portees -> BIRCH_LARGE(100)
    // entwash_mouth : essences du mod non portees -> OAK_TALL(100)
    // dor_en_ernil : essences du mod non portees -> BIRCH_LARGE(200), OAK_TALL(100), CEDAR(100), CYPRESS(50), CYPRESS_LARGE(10), APPLE(5), PEAR(5), LEMON(5)...
    // fangorn_wasteland : essences du mod non portees -> CHARRED(500), OAK_DEAD(300), BEECH_DEAD(100), BIRCH_DEAD(20), CHARRED_FANGORN(50), OAK_FANGORN_DEAD(30), BEECH_FANGORN_DEAD(10)
    // barrow_downs : essences du mod non portees -> OAK_DEAD(1500)
    // long_marshes : essences du mod non portees -> GREEN_OAK(200)
    // ithilien_wasteland : essences du mod non portees -> OAK_DEAD(1000), LEBETHRON_DEAD(200), BIRCH_DEAD(50)
    // nindalf : essences du mod non portees -> OAK_DEAD(500), SPRUCE_DEAD(500)
    // coldfells : essences du mod non portees -> FIR(500), LARCH(300), MAPLE(100), MAPLE_LARGE(10)
    // nan_curunir : essences du mod non portees -> OAK_TALL(100), OAK_DEAD(200), SPRUCE_DEAD(100), CHARRED(300)
    // swanfleet : essences du mod non portees -> OAK_TALL(500), BIRCH_LARGE(100)
    // minhiriath : essences du mod non portees -> OAK_DEAD(1000), SPRUCE_DEAD(300), BEECH_DEAD(100), BIRCH_DEAD(50)
    // erebor : essences du mod non portees -> OAK_DEAD(500), FIR(400)
    // mirkwood_north : essences du mod non portees -> GREEN_OAK(1000), GREEN_OAK_LARGE(50), RED_OAK(15), RED_OAK_LARGE(10), MIRK_OAK(50), SPRUCE_MEGA(20), SPRUCE_MEGA_THIN(20), CHESTNUT(20)...
    // woodland_realm_hills : essences du mod non portees -> GREEN_OAK_EXTREME(500)
    // pinnath_gelin : essences du mod non portees -> ASPEN(500), BIRCH_LARGE(50), CHESTNUT(100), CHESTNUT_LARGE(50), APPLE(5), PEAR(5), PLUM(5), OLIVE(1)...
    // island : essences du mod non portees -> BIRCH_LARGE(10), BEECH(50), BEECH_LARGE(5), APPLE(3), PEAR(3)
    // tundra : essences du mod non portees -> SPRUCE_DEAD(100), FIR(100), MAPLE(10), BEECH(10)
    // taiga : essences du mod non portees -> SPRUCE_DEAD(50), FIR(200)
    // breeland : essences du mod non portees -> BEECH(300), BEECH_LARGE(75), MAPLE(200), MAPLE_LARGE(50), CHESTNUT(300), CHESTNUT_LARGE(75), BIRCH_LARGE(20), ASPEN(50)...
    // near_harad : essences du mod non portees -> OAK_DEAD(800), OAK_DESERT(200)
    // umbar : essences du mod non portees -> OAK_DESERT(1000), CEDAR(300), CYPRESS(500), CYPRESS_LARGE(50), PALM(100), DATE_PALM(5), LEMON(2), ORANGE(2)...
    // far_harad_jungle : essences du mod non portees -> MAHOGANY(500), MANGO(20), BANANA(50)
    // umbar_hills : essences du mod non portees -> OAK_DESERT(1000), CEDAR(300), CYPRESS(500), CYPRESS_LARGE(50), PALM(100), DATE_PALM(5), LEMON(2), ORANGE(2)...
    // lostladen : essences du mod non portees -> OAK_DESERT(1000), OAK_DEAD(200)
    // near_harad_fertile : essences du mod non portees -> CEDAR(800), OAK_DESERT(500), DATE_PALM(50), CYPRESS(400), CYPRESS_LARGE(50), PALM(100), LEMON(5), ORANGE(5)...
    // pertorogwaith : essences du mod non portees -> OAK_DESERT(50), OAK_DEAD(100), ACACIA_DEAD(200), BAOBAB(10)
    // far_harad_jungle_edge : essences du mod non portees -> MAHOGANY(50), MANGO(5)
    // gulf_harad : essences du mod non portees -> PALM(500), OAK_DESERT(400), DRAGONBLOOD(200), DRAGONBLOOD_LARGE(10), DATE_PALM(50), LEMON(5), ORANGE(5), LIME(5)...
    // tolfalas : essences du mod non portees -> OAK_DEAD(2000)
    // lebennin : essences du mod non portees -> BIRCH_LARGE(100), BEECH(150), BEECH_LARGE(50), MAPLE(50), APPLE(5), PEAR(5), OLIVE(5), ALMOND(5)...
    // rhun : essences du mod non portees -> PINE_SHRUB(4000), CHESTNUT(500), CHESTNUT_LARGE(20), ASPEN(100), ASPEN_LARGE(20), MAPLE(50), MAPLE_LARGE(20)
    // rhun_forest : essences du mod non portees -> OAK_PARTY(100)
    // red_mountains : essences du mod non portees -> LARCH(300), MAPLE(300), MAPLE_LARGE(50), FIR(500)
    // dol_guldur : essences du mod non portees -> MIRK_OAK(200), MIRK_OAK_DEAD(1000)
    // near_harad_semi_desert : essences du mod non portees -> OAK_DEAD(500), OAK_DESERT(500)
    // far_harad_cloud_forest : essences du mod non portees -> JUNGLE_CLOUD(4000), MANGO(20)
    // far_harad_bushland : essences du mod non portees -> BIRCH_LARGE(25)
    // far_harad_bushland_hills : essences du mod non portees -> BIRCH_LARGE(25)
    // far_harad_mangrove : essences du mod non portees -> MANGROVE(1000), OAK_DESERT(5)
    // near_harad_fertile_forest : essences du mod non portees -> CEDAR(6000), CEDAR_LARGE(1500)
    // wold : essences du mod non portees -> OAK_DEAD(400), BEECH_DEAD(400)
    // shire_moors : essences du mod non portees -> CHESTNUT_LARGE(2000)
    // far_harad_volcano : essences du mod non portees -> OAK_DEAD(100), ACACIA_DEAD(200), CHARRED(500)
    // morgul_vale : essences du mod non portees -> OAK_DESERT(500), OAK_DEAD(500), CHARRED(500)
    // dale : essences du mod non portees -> OAK_DEAD(50), SPRUCE_DEAD(50), CHESTNUT(100), CHESTNUT_LARGE(20), MAPLE(50), FIR(200), APPLE(5), PEAR(5)...
    // dorwinion : essences du mod non portees -> BIRCH_LARGE(50), BEECH(20), BEECH_LARGE(20), CYPRESS(500), CYPRESS_LARGE(50), OAK_SHRUB(800), APPLE(5), PEAR(5)...
    // gulf_harad_forest : essences du mod non portees -> DRAGONBLOOD(1000), DRAGONBLOOD_LARGE(400)
    // wilderland_north : essences du mod non portees -> OAK_DEAD(500), SPRUCE_DEAD(100), FIR(200)
    // far_harad_coast : essences du mod non portees -> PALM(4000)
    // lossarnach : essences du mod non portees -> BIRCH_LARGE(100), BEECH(50), BEECH_LARGE(10), MAPLE(50), MAPLE_LARGE(10), CHESTNUT(50), CHESTNUT_LARGE(10), APPLE(40)...
    // imloth_melui : essences du mod non portees -> MAPLE(500), MAPLE_LARGE(100), BEECH(500), BEECH_LARGE(100)
    // near_harad_oasis : essences du mod non portees -> DATE_PALM(2000), OLIVE(500), OLIVE_LARGE(200), OAK_SHRUB(3000)
    // harnedor : essences du mod non portees -> OAK_DESERT(1000), CEDAR(250), LEMON(5), ORANGE(5), LIME(5), OLIVE(5), OLIVE_LARGE(5), ALMOND(5)...
    // lamedon : essences du mod non portees -> BIRCH_LARGE(10), BEECH(50), BEECH_LARGE(10), CHESTNUT(200), CHESTNUT_LARGE(50), LARCH(300), ASPEN(300), APPLE(5)...
    // blackroot_vale : essences du mod non portees -> FIR(300), LARCH(300), ASPEN(100), BIRCH_LARGE(10), CHESTNUT(200), CHESTNUT_LARGE(50), APPLE(5), PEAR(5)...
    // andrast : essences du mod non portees -> BIRCH_LARGE(20), BEECH(50), BEECH_LARGE(20), FIR(200), LARCH(200), APPLE(5), PEAR(5), PLUM(5)...
    // pukel : essences du mod non portees -> OAK_PARTY(50), BIRCH_LARGE(20), BEECH(50), BEECH_LARGE(20), CHESTNUT(200), CHESTNUT_LARGE(50), DARK_OAK_PARTY(100), FIR(200)...
    // rhun_land : essences du mod non portees -> BIRCH_LARGE(20), BEECH(50), BEECH_LARGE(10), MAPLE(50), MAPLE_LARGE(10), CYPRESS(400), CYPRESS_LARGE(50), OAK_SHRUB(600)...
    // rhun_land_steppe : essences du mod non portees -> PINE_SHRUB(2000)
    // rhun_red_forest : essences du mod non portees -> REDWOOD(10000), REDWOOD_2(10000), REDWOOD_3(5000), REDWOOD_4(5000), REDWOOD_5(2000)
    // last_desert : essences du mod non portees -> OAK_DEAD(1000)
    // wind_mountains : essences du mod non portees -> SPRUCE_MEGA(50), SPRUCE_MEGA_THIN(10), LARCH(500), FIR(500), MAPLE(300)
    // rivendell : essences du mod non portees -> BEECH(500), BEECH_LARGE(200), BIRCH_LARGE(50), CHESTNUT(50), CHESTNUT_LARGE(10), ASPEN(50), ASPEN_LARGE(20), APPLE(2)...
    // rivendell_hills : essences du mod non portees -> PINE_SHRUB(200), FIR(100), ASPEN(100), ASPEN_LARGE(50)
    // half_troll_forest : essences du mod non portees -> OAK_DESERT(200), BAOBAB(20), ACACIA_DEAD(300), OAK_DEAD(100)
    // far_harad_kanuka : essences du mod non portees -> KANUKA(100)
*/
