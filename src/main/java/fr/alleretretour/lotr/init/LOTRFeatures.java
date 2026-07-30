package fr.alleretretour.lotr.init;

import fr.alleretretour.lotr.LOTRMod;
import fr.alleretretour.lotr.world.feature.LOTRFeatureRoads;
import net.minecraft.block.Blocks;
import net.minecraft.util.registry.Registry;
import net.minecraft.util.registry.WorldGenRegistries;
import net.minecraft.world.gen.GenerationStage;
import net.minecraft.world.gen.feature.ConfiguredFeature;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.IFeatureConfig;
import net.minecraft.world.gen.feature.NoFeatureConfig;
import net.minecraft.world.gen.placement.IPlacementConfig;
import net.minecraft.world.gen.placement.Placement;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

/** Features du mod (routes pour l'instant). */
public final class LOTRFeatures {

    public static final DeferredRegister<Feature<?>> FEATURES =
            DeferredRegister.create(ForgeRegistries.FEATURES, LOTRMod.MOD_ID);

    public static final RegistryObject<Feature<NoFeatureConfig>> ROADS =
            FEATURES.register("roads", () -> new LOTRFeatureRoads(NoFeatureConfig.CODEC));

    public static final RegistryObject<Feature<NoFeatureConfig>> FIR =
            FEATURES.register("fir", () -> new fr.alleretretour.lotr.world.feature.LOTRFeatureConifer(
                    NoFeatureConfig.CODEC,
                    fr.alleretretour.lotr.world.feature.LOTRFeatureConifer.Kind.FIR));

    public static final RegistryObject<Feature<NoFeatureConfig>> DUNLENDING_CAMPFIRE =
            FEATURES.register("dunlending_campfire", () -> new fr.alleretretour.lotr.world.structure.LOTRStructureDunlendingCampfire(
                    NoFeatureConfig.CODEC));

    public static final RegistryObject<Feature<NoFeatureConfig>> HARAD_OBELISK =
            FEATURES.register("harad_obelisk", () -> new fr.alleretretour.lotr.world.structure.LOTRStructureHaradObelisk(
                    NoFeatureConfig.CODEC));

    public static final RegistryObject<Feature<NoFeatureConfig>> BEECH_FANGORN =
            FEATURES.register("beech_fangorn", () -> new fr.alleretretour.lotr.world.feature.LOTRFeatureFangornTree(
                    NoFeatureConfig.CODEC, "beech", true, Blocks.OAK_LOG, Blocks.OAK_LEAVES));

    public static final RegistryObject<Feature<NoFeatureConfig>> BEECH_FANGORN_DEAD =
            FEATURES.register("beech_fangorn_dead", () -> new fr.alleretretour.lotr.world.feature.LOTRFeatureFangornTree(
                    NoFeatureConfig.CODEC, "beech", false, Blocks.OAK_LOG, Blocks.OAK_LEAVES));

    public static final RegistryObject<Feature<NoFeatureConfig>> BIRCH_FANGORN =
            FEATURES.register("birch_fangorn", () -> new fr.alleretretour.lotr.world.feature.LOTRFeatureFangornTree(
                    NoFeatureConfig.CODEC, "birch", true, Blocks.BIRCH_LOG, Blocks.BIRCH_LEAVES));

    public static final RegistryObject<Feature<NoFeatureConfig>> CHARRED_FANGORN =
            FEATURES.register("charred_fangorn", () -> new fr.alleretretour.lotr.world.feature.LOTRFeatureFangornTree(
                    NoFeatureConfig.CODEC, "charred", false, Blocks.DARK_OAK_LOG, Blocks.DARK_OAK_LEAVES));

    public static final RegistryObject<Feature<NoFeatureConfig>> OAK_DEAD =
            FEATURES.register("oak_dead", () -> new fr.alleretretour.lotr.world.feature.LOTRFeatureDeadTree(
                    NoFeatureConfig.CODEC, "oak", Blocks.OAK_LOG));

    public static final RegistryObject<Feature<NoFeatureConfig>> BIRCH_DEAD =
            FEATURES.register("birch_dead", () -> new fr.alleretretour.lotr.world.feature.LOTRFeatureDeadTree(
                    NoFeatureConfig.CODEC, "birch", Blocks.BIRCH_LOG));

    public static final RegistryObject<Feature<NoFeatureConfig>> SPRUCE_DEAD =
            FEATURES.register("spruce_dead", () -> new fr.alleretretour.lotr.world.feature.LOTRFeatureDeadTree(
                    NoFeatureConfig.CODEC, "spruce", Blocks.SPRUCE_LOG));

    public static final RegistryObject<Feature<NoFeatureConfig>> BEECH_DEAD =
            FEATURES.register("beech_dead", () -> new fr.alleretretour.lotr.world.feature.LOTRFeatureDeadTree(
                    NoFeatureConfig.CODEC, "beech", Blocks.OAK_LOG));

    public static final RegistryObject<Feature<NoFeatureConfig>> MIRK_OAK_DEAD =
            FEATURES.register("mirk_oak_dead", () -> new fr.alleretretour.lotr.world.feature.LOTRFeatureDeadTree(
                    NoFeatureConfig.CODEC, "mirk_oak", Blocks.DARK_OAK_LOG));

    public static final RegistryObject<Feature<NoFeatureConfig>> LEBETHRON_DEAD =
            FEATURES.register("lebethron_dead", () -> new fr.alleretretour.lotr.world.feature.LOTRFeatureDeadTree(
                    NoFeatureConfig.CODEC, "lebethron", Blocks.OAK_LOG));

    public static final RegistryObject<Feature<NoFeatureConfig>> ACACIA_DEAD =
            FEATURES.register("acacia_dead", () -> new fr.alleretretour.lotr.world.feature.LOTRFeatureDeadTree(
                    NoFeatureConfig.CODEC, "acacia", Blocks.ACACIA_LOG));

    public static final RegistryObject<Feature<NoFeatureConfig>> MALLORN_EXTREME =
            FEATURES.register("mallorn_extreme", () -> new fr.alleretretour.lotr.world.feature.LOTRFeatureMallornExtreme(
                    NoFeatureConfig.CODEC));

    public static final RegistryObject<Feature<NoFeatureConfig>> MIRK_OAK =
            FEATURES.register("mirk_oak", () -> new fr.alleretretour.lotr.world.feature.LOTRFeatureMirkOak(
                    NoFeatureConfig.CODEC, 4, 7, 0, "mirk_oak"));

    public static final RegistryObject<Feature<NoFeatureConfig>> MIRK_OAK_LARGE =
            FEATURES.register("mirk_oak_large", () -> new fr.alleretretour.lotr.world.feature.LOTRFeatureMirkOak(
                    NoFeatureConfig.CODEC, 12, 16, 1, "mirk_oak"));

    public static final RegistryObject<Feature<NoFeatureConfig>> RED_OAK =
            FEATURES.register("red_oak", () -> new fr.alleretretour.lotr.world.feature.LOTRFeatureMirkOak(
                    NoFeatureConfig.CODEC, 6, 9, 0, "red_oak"));

    public static final RegistryObject<Feature<NoFeatureConfig>> RED_OAK_LARGE =
            FEATURES.register("red_oak_large", () -> new fr.alleretretour.lotr.world.feature.LOTRFeatureMirkOak(
                    NoFeatureConfig.CODEC, 12, 17, 1, "red_oak"));

    public static final RegistryObject<Feature<NoFeatureConfig>> CHARRED =
            FEATURES.register("charred", () -> new fr.alleretretour.lotr.world.feature.LOTRFeatureCharred(
                    NoFeatureConfig.CODEC));

    public static final RegistryObject<Feature<NoFeatureConfig>> CEDAR =
            FEATURES.register("cedar", () -> new fr.alleretretour.lotr.world.feature.LOTRFeatureCedar(
                    NoFeatureConfig.CODEC, 10, 16));

    public static final RegistryObject<Feature<NoFeatureConfig>> CEDAR_LARGE =
            FEATURES.register("cedar_large", () -> new fr.alleretretour.lotr.world.feature.LOTRFeatureCedar(
                    NoFeatureConfig.CODEC, 15, 30));

    public static final RegistryObject<Feature<NoFeatureConfig>> MALLORN_BOUGHS =
            FEATURES.register("mallorn_boughs", () -> new fr.alleretretour.lotr.world.feature.LOTRFeatureMallorn(
                    NoFeatureConfig.CODEC, 10, 14));

    public static final RegistryObject<Feature<NoFeatureConfig>> MANGROVE =
            FEATURES.register("mangrove", () -> new fr.alleretretour.lotr.world.feature.LOTRFeatureMangrove(
                    NoFeatureConfig.CODEC));

    public static final RegistryObject<Feature<NoFeatureConfig>> BANANA =
            FEATURES.register("banana", () -> new fr.alleretretour.lotr.world.feature.LOTRFeatureBanana(
                    NoFeatureConfig.CODEC));

    public static final RegistryObject<Feature<NoFeatureConfig>> DATE_PALM =
            FEATURES.register("date_palm", () -> new fr.alleretretour.lotr.world.feature.LOTRFeaturePalm(
                    NoFeatureConfig.CODEC));

    public static final RegistryObject<Feature<NoFeatureConfig>> HOLLY =
            FEATURES.register("holly", () -> new fr.alleretretour.lotr.world.feature.LOTRFeatureHolly(
                    NoFeatureConfig.CODEC, false));

    public static final RegistryObject<Feature<NoFeatureConfig>> HOLLY_LARGE =
            FEATURES.register("holly_large", () -> new fr.alleretretour.lotr.world.feature.LOTRFeatureHolly(
                    NoFeatureConfig.CODEC, true));

    public static final RegistryObject<Feature<NoFeatureConfig>> BAOBAB =
            FEATURES.register("baobab", () -> new fr.alleretretour.lotr.world.feature.LOTRFeatureBaobab(
                    NoFeatureConfig.CODEC));

    public static final RegistryObject<Feature<NoFeatureConfig>> LARCH =
            FEATURES.register("larch", () -> new fr.alleretretour.lotr.world.feature.LOTRFeatureLarch(
                    NoFeatureConfig.CODEC));

    public static final RegistryObject<Feature<NoFeatureConfig>> SHIRE_PINE =
            FEATURES.register("shire_pine", () -> new fr.alleretretour.lotr.world.feature.LOTRFeatureConifer(
                    NoFeatureConfig.CODEC,
                    fr.alleretretour.lotr.world.feature.LOTRFeatureConifer.Kind.SHIRE_PINE));

    private static ConfiguredFeature<?, ?> configuredRoads;
    private static ConfiguredFeature<?, ?> configuredFir;
    private static ConfiguredFeature<?, ?> configuredShirePine;
    private static ConfiguredFeature<?, ?> configuredLarch;
    private static ConfiguredFeature<?, ?> configuredBaobab;
    private static ConfiguredFeature<?, ?> configuredHolly;
    private static ConfiguredFeature<?, ?> configuredHollyLarge;
    private static ConfiguredFeature<?, ?> configuredDatePalm;
    private static ConfiguredFeature<?, ?> configuredBanana;
    private static ConfiguredFeature<?, ?> configuredMangrove;
    private static ConfiguredFeature<?, ?> configuredMallornBoughs;
    private static ConfiguredFeature<?, ?> configuredCedar;
    private static ConfiguredFeature<?, ?> configuredCedarLarge;
    private static ConfiguredFeature<?, ?> configuredCharred;
    private static ConfiguredFeature<?, ?> configuredMallornExtreme;
    private static ConfiguredFeature<?, ?> configuredHaradObelisk;
    private static ConfiguredFeature<?, ?> configuredDunlendingCampfire;
    private static ConfiguredFeature<?, ?> configuredBeechFangorn;
    private static ConfiguredFeature<?, ?> configuredBeechFangornDead;
    private static ConfiguredFeature<?, ?> configuredBirchFangorn;
    private static ConfiguredFeature<?, ?> configuredCharredFangorn;

    private static ConfiguredFeature<?, ?> configuredOakDead;
    private static ConfiguredFeature<?, ?> configuredBirchDead;
    private static ConfiguredFeature<?, ?> configuredSpruceDead;
    private static ConfiguredFeature<?, ?> configuredBeechDead;
    private static ConfiguredFeature<?, ?> configuredMirkOakDead;
    private static ConfiguredFeature<?, ?> configuredLebethronDead;
    private static ConfiguredFeature<?, ?> configuredAcaciaDead;

    private static ConfiguredFeature<?, ?> configuredMirkOak;
    private static ConfiguredFeature<?, ?> configuredMirkOakLarge;
    private static ConfiguredFeature<?, ?> configuredRedOak;
    private static ConfiguredFeature<?, ?> configuredRedOakLarge;


    private LOTRFeatures() {
    }

    public static void register(IEventBus modBus) {
        FEATURES.register(modBus);
    }

    /** A appeler au FMLCommonSetupEvent (enqueueWork). */
    public static void registerConfigured() {
        configuredRoads = ROADS.get().configured(IFeatureConfig.NONE)
                .decorated(Placement.NOPE.configured(IPlacementConfig.NONE));
        Registry.register(WorldGenRegistries.CONFIGURED_FEATURE,
                new net.minecraft.util.ResourceLocation("lotr", "roads"), configuredRoads);

        configuredFir = FIR.get().configured(IFeatureConfig.NONE);
        Registry.register(WorldGenRegistries.CONFIGURED_FEATURE,
                new net.minecraft.util.ResourceLocation("lotr", "fir"), configuredFir);
        configuredShirePine = SHIRE_PINE.get().configured(IFeatureConfig.NONE);
        Registry.register(WorldGenRegistries.CONFIGURED_FEATURE,
                new net.minecraft.util.ResourceLocation("lotr", "shire_pine"), configuredShirePine);
        configuredLarch = LARCH.get().configured(IFeatureConfig.NONE);
        Registry.register(WorldGenRegistries.CONFIGURED_FEATURE,
                new net.minecraft.util.ResourceLocation("lotr", "larch"), configuredLarch);
        configuredBaobab = BAOBAB.get().configured(IFeatureConfig.NONE);
        Registry.register(WorldGenRegistries.CONFIGURED_FEATURE,
                new net.minecraft.util.ResourceLocation("lotr", "baobab"), configuredBaobab);
        configuredHolly = HOLLY.get().configured(IFeatureConfig.NONE);
        Registry.register(WorldGenRegistries.CONFIGURED_FEATURE,
                new net.minecraft.util.ResourceLocation("lotr", "holly"), configuredHolly);
        configuredHollyLarge = HOLLY_LARGE.get().configured(IFeatureConfig.NONE);
        Registry.register(WorldGenRegistries.CONFIGURED_FEATURE,
                new net.minecraft.util.ResourceLocation("lotr", "holly_large"), configuredHollyLarge);
        configuredDatePalm = DATE_PALM.get().configured(IFeatureConfig.NONE);
        Registry.register(WorldGenRegistries.CONFIGURED_FEATURE,
                new net.minecraft.util.ResourceLocation("lotr", "date_palm"), configuredDatePalm);
        configuredBanana = BANANA.get().configured(IFeatureConfig.NONE);
        Registry.register(WorldGenRegistries.CONFIGURED_FEATURE,
                new net.minecraft.util.ResourceLocation("lotr", "banana"), configuredBanana);
        configuredMangrove = MANGROVE.get().configured(IFeatureConfig.NONE);
        Registry.register(WorldGenRegistries.CONFIGURED_FEATURE,
                new net.minecraft.util.ResourceLocation("lotr", "mangrove"), configuredMangrove);
        configuredMallornBoughs = MALLORN_BOUGHS.get().configured(IFeatureConfig.NONE);
        Registry.register(WorldGenRegistries.CONFIGURED_FEATURE,
                new net.minecraft.util.ResourceLocation("lotr", "mallorn_boughs"), configuredMallornBoughs);
        configuredCedar = CEDAR.get().configured(IFeatureConfig.NONE);
        Registry.register(WorldGenRegistries.CONFIGURED_FEATURE,
                new net.minecraft.util.ResourceLocation("lotr", "cedar"), configuredCedar);
        configuredCedarLarge = CEDAR_LARGE.get().configured(IFeatureConfig.NONE);
        Registry.register(WorldGenRegistries.CONFIGURED_FEATURE,
                new net.minecraft.util.ResourceLocation("lotr", "cedar_large"), configuredCedarLarge);
        configuredCharred = CHARRED.get().configured(IFeatureConfig.NONE);
        Registry.register(WorldGenRegistries.CONFIGURED_FEATURE,
                new net.minecraft.util.ResourceLocation("lotr", "charred"), configuredCharred);
        configuredMallornExtreme = MALLORN_EXTREME.get().configured(IFeatureConfig.NONE);
        Registry.register(WorldGenRegistries.CONFIGURED_FEATURE,
                new net.minecraft.util.ResourceLocation("lotr", "mallorn_extreme"), configuredMallornExtreme);
        configuredHaradObelisk = HARAD_OBELISK.get().configured(IFeatureConfig.NONE);
        Registry.register(WorldGenRegistries.CONFIGURED_FEATURE,
                new net.minecraft.util.ResourceLocation("lotr", "harad_obelisk"), configuredHaradObelisk);
        configuredDunlendingCampfire = DUNLENDING_CAMPFIRE.get().configured(IFeatureConfig.NONE);
        Registry.register(WorldGenRegistries.CONFIGURED_FEATURE,
                new net.minecraft.util.ResourceLocation("lotr", "dunlending_campfire"), configuredDunlendingCampfire);
        configuredBeechFangorn = BEECH_FANGORN.get().configured(IFeatureConfig.NONE);
        Registry.register(WorldGenRegistries.CONFIGURED_FEATURE,
                new net.minecraft.util.ResourceLocation("lotr", "beech_fangorn"), configuredBeechFangorn);
        configuredBeechFangornDead = BEECH_FANGORN_DEAD.get().configured(IFeatureConfig.NONE);
        Registry.register(WorldGenRegistries.CONFIGURED_FEATURE,
                new net.minecraft.util.ResourceLocation("lotr", "beech_fangorn_dead"), configuredBeechFangornDead);
        configuredBirchFangorn = BIRCH_FANGORN.get().configured(IFeatureConfig.NONE);
        Registry.register(WorldGenRegistries.CONFIGURED_FEATURE,
                new net.minecraft.util.ResourceLocation("lotr", "birch_fangorn"), configuredBirchFangorn);
        configuredCharredFangorn = CHARRED_FANGORN.get().configured(IFeatureConfig.NONE);
        Registry.register(WorldGenRegistries.CONFIGURED_FEATURE,
                new net.minecraft.util.ResourceLocation("lotr", "charred_fangorn"), configuredCharredFangorn);

        configuredOakDead = OAK_DEAD.get().configured(IFeatureConfig.NONE);
        Registry.register(WorldGenRegistries.CONFIGURED_FEATURE,
                new net.minecraft.util.ResourceLocation("lotr", "oak_dead"), configuredOakDead);
        configuredBirchDead = BIRCH_DEAD.get().configured(IFeatureConfig.NONE);
        Registry.register(WorldGenRegistries.CONFIGURED_FEATURE,
                new net.minecraft.util.ResourceLocation("lotr", "birch_dead"), configuredBirchDead);
        configuredSpruceDead = SPRUCE_DEAD.get().configured(IFeatureConfig.NONE);
        Registry.register(WorldGenRegistries.CONFIGURED_FEATURE,
                new net.minecraft.util.ResourceLocation("lotr", "spruce_dead"), configuredSpruceDead);
        configuredBeechDead = BEECH_DEAD.get().configured(IFeatureConfig.NONE);
        Registry.register(WorldGenRegistries.CONFIGURED_FEATURE,
                new net.minecraft.util.ResourceLocation("lotr", "beech_dead"), configuredBeechDead);
        configuredMirkOakDead = MIRK_OAK_DEAD.get().configured(IFeatureConfig.NONE);
        Registry.register(WorldGenRegistries.CONFIGURED_FEATURE,
                new net.minecraft.util.ResourceLocation("lotr", "mirk_oak_dead"), configuredMirkOakDead);
        configuredLebethronDead = LEBETHRON_DEAD.get().configured(IFeatureConfig.NONE);
        Registry.register(WorldGenRegistries.CONFIGURED_FEATURE,
                new net.minecraft.util.ResourceLocation("lotr", "lebethron_dead"), configuredLebethronDead);
        configuredAcaciaDead = ACACIA_DEAD.get().configured(IFeatureConfig.NONE);
        Registry.register(WorldGenRegistries.CONFIGURED_FEATURE,
                new net.minecraft.util.ResourceLocation("lotr", "acacia_dead"), configuredAcaciaDead);

        configuredMirkOak = MIRK_OAK.get().configured(IFeatureConfig.NONE);
        Registry.register(WorldGenRegistries.CONFIGURED_FEATURE,
                new net.minecraft.util.ResourceLocation("lotr", "mirk_oak"), configuredMirkOak);
        configuredMirkOakLarge = MIRK_OAK_LARGE.get().configured(IFeatureConfig.NONE);
        Registry.register(WorldGenRegistries.CONFIGURED_FEATURE,
                new net.minecraft.util.ResourceLocation("lotr", "mirk_oak_large"), configuredMirkOakLarge);
        configuredRedOak = RED_OAK.get().configured(IFeatureConfig.NONE);
        Registry.register(WorldGenRegistries.CONFIGURED_FEATURE,
                new net.minecraft.util.ResourceLocation("lotr", "red_oak"), configuredRedOak);
        configuredRedOakLarge = RED_OAK_LARGE.get().configured(IFeatureConfig.NONE);
        Registry.register(WorldGenRegistries.CONFIGURED_FEATURE,
                new net.minecraft.util.ResourceLocation("lotr", "red_oak_large"), configuredRedOakLarge);

    }

    public static ConfiguredFeature<?, ?> roads() {
        return configuredRoads;
    }

    public static ConfiguredFeature<?, ?> fir() {
        return configuredFir;
    }

    public static ConfiguredFeature<?, ?> shirePine() {
        return configuredShirePine;
    }

    public static ConfiguredFeature<?, ?> larch() {
        return configuredLarch;
    }

    public static ConfiguredFeature<?, ?> baobab() {
        return configuredBaobab;
    }

    public static ConfiguredFeature<?, ?> holly() {
        return configuredHolly;
    }

    public static ConfiguredFeature<?, ?> hollyLarge() {
        return configuredHollyLarge;
    }

    public static ConfiguredFeature<?, ?> datePalm() {
        return configuredDatePalm;
    }

    public static ConfiguredFeature<?, ?> banana() {
        return configuredBanana;
    }

    public static ConfiguredFeature<?, ?> mangrove() {
        return configuredMangrove;
    }

    public static ConfiguredFeature<?, ?> mallornBoughs() {
        return configuredMallornBoughs;
    }

    public static ConfiguredFeature<?, ?> cedar() {
        return configuredCedar;
    }

    public static ConfiguredFeature<?, ?> cedarLarge() {
        return configuredCedarLarge;
    }

    public static ConfiguredFeature<?, ?> charred() {
        return configuredCharred;
    }

    public static ConfiguredFeature<?, ?> mallornExtreme() {
        return configuredMallornExtreme;
    }

    public static ConfiguredFeature<?, ?> haradObelisk() {
        return configuredHaradObelisk;
    }

    public static ConfiguredFeature<?, ?> dunlendingCampfire() {
        return configuredDunlendingCampfire;
    }

    public static ConfiguredFeature<?, ?> beechFangorn() {
        return configuredBeechFangorn;
    }

    public static ConfiguredFeature<?, ?> beechFangornDead() {
        return configuredBeechFangornDead;
    }

    public static ConfiguredFeature<?, ?> birchFangorn() {
        return configuredBirchFangorn;
    }

    public static ConfiguredFeature<?, ?> charredFangorn() {
        return configuredCharredFangorn;
    }


    public static ConfiguredFeature<?, ?> oakDead() {
        return configuredOakDead;
    }

    public static ConfiguredFeature<?, ?> birchDead() {
        return configuredBirchDead;
    }

    public static ConfiguredFeature<?, ?> spruceDead() {
        return configuredSpruceDead;
    }

    public static ConfiguredFeature<?, ?> beechDead() {
        return configuredBeechDead;
    }

    public static ConfiguredFeature<?, ?> mirkOakDead() {
        return configuredMirkOakDead;
    }

    public static ConfiguredFeature<?, ?> lebethronDead() {
        return configuredLebethronDead;
    }

    public static ConfiguredFeature<?, ?> acaciaDead() {
        return configuredAcaciaDead;
    }


    public static ConfiguredFeature<?, ?> mirkOak() {
        return configuredMirkOak;
    }

    public static ConfiguredFeature<?, ?> mirkOakLarge() {
        return configuredMirkOakLarge;
    }

    public static ConfiguredFeature<?, ?> redOak() {
        return configuredRedOak;
    }

    public static ConfiguredFeature<?, ?> redOakLarge() {
        return configuredRedOakLarge;
    }

}
