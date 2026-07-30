package fr.alleretretour.lotr.init;

import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.registry.Registry;
import net.minecraft.util.registry.WorldGenRegistries;
import net.minecraft.world.gen.blockstateprovider.SimpleBlockStateProvider;
import net.minecraft.world.gen.feature.BaseTreeFeatureConfig;
import net.minecraft.world.gen.feature.ConfiguredFeature;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.FeatureSpread;
import net.minecraft.world.gen.feature.TwoLayerFeature;
import net.minecraft.world.gen.foliageplacer.BlobFoliagePlacer;
import net.minecraft.world.gen.foliageplacer.FancyFoliagePlacer;
import net.minecraft.world.gen.trunkplacer.FancyTrunkPlacer;
import net.minecraft.world.gen.trunkplacer.StraightTrunkPlacer;
import net.minecraftforge.registries.ForgeRegistries;

/**
 * Arbres du mod, aux formes EXACTES du Legacy (LOTRTreeType) :
 *   - "simple"  = LOTRWorldGenSimpleTrees(min, max)  -> tronc droit, houppier rond
 *   - "big"     = LOTRWorldGenBigTrees               -> grand arbre ramifie
 * Les bois utilises sont ceux deja portes dans LOTRBlocksWood.
 *
 * Les essences a generateur specifique du Legacy (mallorn a ramures, houx,
 * meleze, palmier-dattier, palétuvier, baobab, cedre, sapin, bananier, pin du
 * Comte, arbres calcines) ont chacune leur propre algorithme : elles feront
 * l'objet d'un lot dedie, aucune forme n'a ete inventee ici.
 */
public final class LOTRTreeFeatures {

    public static ConfiguredFeature<?, ?> BEECH;
    public static ConfiguredFeature<?, ?> BEECH_LARGE;
    public static ConfiguredFeature<?, ?> CHESTNUT;
    public static ConfiguredFeature<?, ?> CHESTNUT_LARGE;
    public static ConfiguredFeature<?, ?> LEBETHRON;
    public static ConfiguredFeature<?, ?> LEBETHRON_LARGE;
    public static ConfiguredFeature<?, ?> MALLORN;
    public static ConfiguredFeature<?, ?> MAPLE;
    public static ConfiguredFeature<?, ?> MAPLE_LARGE;
    public static ConfiguredFeature<?, ?> MIRK_OAK;
    public static ConfiguredFeature<?, ?> MIRK_OAK_LARGE;

    private LOTRTreeFeatures() {
    }

    /** A appeler au FMLCommonSetupEvent (enqueueWork). */
    public static void register() {
        BEECH = register("beech", simple("beech", 5, 9));
        BEECH_LARGE = register("beech_large", big("beech"));
        CHESTNUT = register("chestnut", simple("chestnut", 5, 7));
        CHESTNUT_LARGE = register("chestnut_large", big("chestnut"));
        LEBETHRON = register("lebethron", simple("lebethron", 5, 9));
        LEBETHRON_LARGE = register("lebethron_large", big("lebethron"));
        MALLORN = register("mallorn", simple("mallorn", 6, 9));
        MAPLE = register("maple", simple("maple", 4, 8));
        MAPLE_LARGE = register("maple_large", big("maple"));
        MIRK_OAK = register("mirk_oak", simple("mirk_oak", 4, 7));
        MIRK_OAK_LARGE = register("mirk_oak_large", simple("mirk_oak", 12, 16));
    }

    private static ConfiguredFeature<?, ?> register(String name, ConfiguredFeature<?, ?> feature) {
        return Registry.register(WorldGenRegistries.CONFIGURED_FEATURE,
                new ResourceLocation("lotr", name), feature);
    }

    /** PORT de LOTRWorldGenSimpleTrees(flag, min, max, bois, feuilles). */
    private static ConfiguredFeature<?, ?> simple(String species, int min, int max) {
        return Feature.TREE.configured(new BaseTreeFeatureConfig.Builder(
                new SimpleBlockStateProvider(log(species)),
                new SimpleBlockStateProvider(leaves(species)),
                new BlobFoliagePlacer(FeatureSpread.fixed(2), FeatureSpread.fixed(0), 3),
                new StraightTrunkPlacer(min, max - min, 0),
                new TwoLayerFeature(1, 0, 1))
                .ignoreVines().build());
    }

    /** PORT de LOTRWorldGenBigTrees (grand arbre ramifie). */
    private static ConfiguredFeature<?, ?> big(String species) {
        return Feature.TREE.configured(new BaseTreeFeatureConfig.Builder(
                new SimpleBlockStateProvider(log(species)),
                new SimpleBlockStateProvider(leaves(species)),
                new FancyFoliagePlacer(FeatureSpread.fixed(2), FeatureSpread.fixed(4), 4),
                new FancyTrunkPlacer(3, 11, 0),
                new TwoLayerFeature(0, 0, 0, java.util.OptionalInt.of(4)))
                .ignoreVines().build());
    }

    private static net.minecraft.block.BlockState log(String species) {
        return state("lotr:" + species + "_log", Blocks.OAK_LOG);
    }

    private static net.minecraft.block.BlockState leaves(String species) {
        return state("lotr:" + species + "_leaves", Blocks.OAK_LEAVES);
    }

    private static net.minecraft.block.BlockState state(String id, Block fallback) {
        Block block = ForgeRegistries.BLOCKS.getValue(new ResourceLocation(id));
        return (block == null || block == Blocks.AIR ? fallback : block).defaultBlockState();
    }
}
