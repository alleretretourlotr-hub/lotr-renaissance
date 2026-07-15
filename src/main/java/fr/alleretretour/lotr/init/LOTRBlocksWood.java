package fr.alleretretour.lotr.init;

import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.FenceBlock;
import net.minecraft.block.FenceGateBlock;
import net.minecraft.block.LeavesBlock;
import net.minecraft.block.RotatedPillarBlock;
import net.minecraft.block.SlabBlock;
import net.minecraft.block.SoundType;
import net.minecraft.block.StairsBlock;
import net.minecraft.block.material.Material;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraftforge.fml.RegistryObject;

import java.util.function.Supplier;

/**
 * GENERE AUTOMATIQUEMENT - Phase 2 lots 1+2 : famille bois complete
 * (planches, troncs, feuilles, escaliers, dalles, barrieres, portillons).
 */
public class LOTRBlocksWood {

    static final AbstractBlock.Properties WOOD_PROPS =
            AbstractBlock.Properties.of(Material.WOOD).strength(2.0f, 5.0f).sound(SoundType.WOOD);
    static final AbstractBlock.Properties LEAVES_PROPS =
            AbstractBlock.Properties.of(Material.LEAVES).strength(0.2f).sound(SoundType.GRASS)
                    .noOcclusion().randomTicks();

    public static void init() {
    }

    private static RegistryObject<Block> registerWithItem(String name, Supplier<Block> block) {
        RegistryObject<Block> ro = LOTRBlocks.BLOCKS.register(name, block);
        LOTRItems.ITEMS.register(name, () -> new BlockItem(ro.get(),
                new Item.Properties().tab(LOTRCreativeTabs.TAB_BLOCKS)));
        return ro;
    }

    public static final RegistryObject<Block> SHIRE_PINE_PLANKS = registerWithItem("shire_pine_planks",
            () -> new Block(WOOD_PROPS));
    public static final RegistryObject<Block> MALLORN_PLANKS = registerWithItem("mallorn_planks",
            () -> new Block(WOOD_PROPS));
    public static final RegistryObject<Block> MIRK_OAK_PLANKS = registerWithItem("mirk_oak_planks",
            () -> new Block(WOOD_PROPS));
    public static final RegistryObject<Block> CHARRED_PLANKS = registerWithItem("charred_planks",
            () -> new Block(WOOD_PROPS));
    public static final RegistryObject<Block> APPLE_PLANKS = registerWithItem("apple_planks",
            () -> new Block(WOOD_PROPS));
    public static final RegistryObject<Block> PEAR_PLANKS = registerWithItem("pear_planks",
            () -> new Block(WOOD_PROPS));
    public static final RegistryObject<Block> CHERRY_PLANKS = registerWithItem("cherry_planks",
            () -> new Block(WOOD_PROPS));
    public static final RegistryObject<Block> MANGO_PLANKS = registerWithItem("mango_planks",
            () -> new Block(WOOD_PROPS));
    public static final RegistryObject<Block> LEBETHRON_PLANKS = registerWithItem("lebethron_planks",
            () -> new Block(WOOD_PROPS));
    public static final RegistryObject<Block> BEECH_PLANKS = registerWithItem("beech_planks",
            () -> new Block(WOOD_PROPS));
    public static final RegistryObject<Block> HOLLY_PLANKS = registerWithItem("holly_planks",
            () -> new Block(WOOD_PROPS));
    public static final RegistryObject<Block> BANANA_PLANKS = registerWithItem("banana_planks",
            () -> new Block(WOOD_PROPS));
    public static final RegistryObject<Block> MAPLE_PLANKS = registerWithItem("maple_planks",
            () -> new Block(WOOD_PROPS));
    public static final RegistryObject<Block> LARCH_PLANKS = registerWithItem("larch_planks",
            () -> new Block(WOOD_PROPS));
    public static final RegistryObject<Block> DATE_PALM_PLANKS = registerWithItem("date_palm_planks",
            () -> new Block(WOOD_PROPS));
    public static final RegistryObject<Block> MANGROVE_PLANKS = registerWithItem("mangrove_planks",
            () -> new Block(WOOD_PROPS));
    public static final RegistryObject<Block> CHESTNUT_PLANKS = registerWithItem("chestnut_planks",
            () -> new Block(WOOD_PROPS));
    public static final RegistryObject<Block> BAOBAB_PLANKS = registerWithItem("baobab_planks",
            () -> new Block(WOOD_PROPS));
    public static final RegistryObject<Block> CEDAR_PLANKS = registerWithItem("cedar_planks",
            () -> new Block(WOOD_PROPS));
    public static final RegistryObject<Block> FIR_PLANKS = registerWithItem("fir_planks",
            () -> new Block(WOOD_PROPS));
    public static final RegistryObject<Block> PINE_PLANKS = registerWithItem("pine_planks",
            () -> new Block(WOOD_PROPS));
    public static final RegistryObject<Block> LEMON_PLANKS = registerWithItem("lemon_planks",
            () -> new Block(WOOD_PROPS));
    public static final RegistryObject<Block> ORANGE_PLANKS = registerWithItem("orange_planks",
            () -> new Block(WOOD_PROPS));
    public static final RegistryObject<Block> LIME_PLANKS = registerWithItem("lime_planks",
            () -> new Block(WOOD_PROPS));
    public static final RegistryObject<Block> MAHOGANY_PLANKS = registerWithItem("mahogany_planks",
            () -> new Block(WOOD_PROPS));
    public static final RegistryObject<Block> WILLOW_PLANKS = registerWithItem("willow_planks",
            () -> new Block(WOOD_PROPS));
    public static final RegistryObject<Block> CYPRESS_PLANKS = registerWithItem("cypress_planks",
            () -> new Block(WOOD_PROPS));
    public static final RegistryObject<Block> OLIVE_PLANKS = registerWithItem("olive_planks",
            () -> new Block(WOOD_PROPS));
    public static final RegistryObject<Block> ASPEN_PLANKS = registerWithItem("aspen_planks",
            () -> new Block(WOOD_PROPS));
    public static final RegistryObject<Block> GREEN_OAK_PLANKS = registerWithItem("green_oak_planks",
            () -> new Block(WOOD_PROPS));
    public static final RegistryObject<Block> LAIRELOSSE_PLANKS = registerWithItem("lairelosse_planks",
            () -> new Block(WOOD_PROPS));
    public static final RegistryObject<Block> ALMOND_PLANKS = registerWithItem("almond_planks",
            () -> new Block(WOOD_PROPS));
    public static final RegistryObject<Block> PLUM_PLANKS = registerWithItem("plum_planks",
            () -> new Block(WOOD_PROPS));
    public static final RegistryObject<Block> REDWOOD_PLANKS = registerWithItem("redwood_planks",
            () -> new Block(WOOD_PROPS));
    public static final RegistryObject<Block> POMEGRANATE_PLANKS = registerWithItem("pomegranate_planks",
            () -> new Block(WOOD_PROPS));
    public static final RegistryObject<Block> PALM_PLANKS = registerWithItem("palm_planks",
            () -> new Block(WOOD_PROPS));
    public static final RegistryObject<Block> DRAGON_PLANKS = registerWithItem("dragon_planks",
            () -> new Block(WOOD_PROPS));
    public static final RegistryObject<Block> KANUKA_PLANKS = registerWithItem("kanuka_planks",
            () -> new Block(WOOD_PROPS));
    public static final RegistryObject<Block> ROTTEN_PLANKS = registerWithItem("rotten_planks",
            () -> new Block(WOOD_PROPS));
    public static final RegistryObject<Block> SHIRE_PINE_LOG = registerWithItem("shire_pine_log",
            () -> new RotatedPillarBlock(WOOD_PROPS));
    public static final RegistryObject<Block> MALLORN_LOG = registerWithItem("mallorn_log",
            () -> new RotatedPillarBlock(WOOD_PROPS));
    public static final RegistryObject<Block> MIRK_OAK_LOG = registerWithItem("mirk_oak_log",
            () -> new RotatedPillarBlock(WOOD_PROPS));
    public static final RegistryObject<Block> CHARRED_LOG = registerWithItem("charred_log",
            () -> new RotatedPillarBlock(WOOD_PROPS));
    public static final RegistryObject<Block> LEBETHRON_LOG = registerWithItem("lebethron_log",
            () -> new RotatedPillarBlock(WOOD_PROPS));
    public static final RegistryObject<Block> BEECH_LOG = registerWithItem("beech_log",
            () -> new RotatedPillarBlock(WOOD_PROPS));
    public static final RegistryObject<Block> HOLLY_LOG = registerWithItem("holly_log",
            () -> new RotatedPillarBlock(WOOD_PROPS));
    public static final RegistryObject<Block> BANANA_LOG = registerWithItem("banana_log",
            () -> new RotatedPillarBlock(WOOD_PROPS));
    public static final RegistryObject<Block> MAPLE_LOG = registerWithItem("maple_log",
            () -> new RotatedPillarBlock(WOOD_PROPS));
    public static final RegistryObject<Block> LARCH_LOG = registerWithItem("larch_log",
            () -> new RotatedPillarBlock(WOOD_PROPS));
    public static final RegistryObject<Block> DATE_PALM_LOG = registerWithItem("date_palm_log",
            () -> new RotatedPillarBlock(WOOD_PROPS));
    public static final RegistryObject<Block> MANGROVE_LOG = registerWithItem("mangrove_log",
            () -> new RotatedPillarBlock(WOOD_PROPS));
    public static final RegistryObject<Block> CHESTNUT_LOG = registerWithItem("chestnut_log",
            () -> new RotatedPillarBlock(WOOD_PROPS));
    public static final RegistryObject<Block> BAOBAB_LOG = registerWithItem("baobab_log",
            () -> new RotatedPillarBlock(WOOD_PROPS));
    public static final RegistryObject<Block> CEDAR_LOG = registerWithItem("cedar_log",
            () -> new RotatedPillarBlock(WOOD_PROPS));
    public static final RegistryObject<Block> FIR_LOG = registerWithItem("fir_log",
            () -> new RotatedPillarBlock(WOOD_PROPS));
    public static final RegistryObject<Block> SHIRE_PINE_LEAVES = registerWithItem("shire_pine_leaves",
            () -> new LeavesBlock(LEAVES_PROPS));
    public static final RegistryObject<Block> MALLORN_LEAVES = registerWithItem("mallorn_leaves",
            () -> new LeavesBlock(LEAVES_PROPS));
    public static final RegistryObject<Block> MIRK_OAK_LEAVES = registerWithItem("mirk_oak_leaves",
            () -> new LeavesBlock(LEAVES_PROPS));
    public static final RegistryObject<Block> MIRK_OAK_RED_LEAVES = registerWithItem("mirk_oak_red_leaves",
            () -> new LeavesBlock(LEAVES_PROPS));
    public static final RegistryObject<Block> LEBETHRON_LEAVES = registerWithItem("lebethron_leaves",
            () -> new LeavesBlock(LEAVES_PROPS));
    public static final RegistryObject<Block> BEECH_LEAVES = registerWithItem("beech_leaves",
            () -> new LeavesBlock(LEAVES_PROPS));
    public static final RegistryObject<Block> HOLLY_LEAVES = registerWithItem("holly_leaves",
            () -> new LeavesBlock(LEAVES_PROPS));
    public static final RegistryObject<Block> BANANA_LEAVES = registerWithItem("banana_leaves",
            () -> new LeavesBlock(LEAVES_PROPS));
    public static final RegistryObject<Block> MAPLE_LEAVES = registerWithItem("maple_leaves",
            () -> new LeavesBlock(LEAVES_PROPS));
    public static final RegistryObject<Block> LARCH_LEAVES = registerWithItem("larch_leaves",
            () -> new LeavesBlock(LEAVES_PROPS));
    public static final RegistryObject<Block> DATE_PALM_LEAVES = registerWithItem("date_palm_leaves",
            () -> new LeavesBlock(LEAVES_PROPS));
    public static final RegistryObject<Block> MANGROVE_LEAVES = registerWithItem("mangrove_leaves",
            () -> new LeavesBlock(LEAVES_PROPS));
    public static final RegistryObject<Block> CHESTNUT_LEAVES = registerWithItem("chestnut_leaves",
            () -> new LeavesBlock(LEAVES_PROPS));
    public static final RegistryObject<Block> BAOBAB_LEAVES = registerWithItem("baobab_leaves",
            () -> new LeavesBlock(LEAVES_PROPS));
    public static final RegistryObject<Block> CEDAR_LEAVES = registerWithItem("cedar_leaves",
            () -> new LeavesBlock(LEAVES_PROPS));
    public static final RegistryObject<Block> FIR_LEAVES = registerWithItem("fir_leaves",
            () -> new LeavesBlock(LEAVES_PROPS));
    public static final RegistryObject<Block> SHIRE_PINE_STAIRS = registerWithItem("shire_pine_stairs",
            () -> new StairsBlock(() -> SHIRE_PINE_PLANKS.get().defaultBlockState(), WOOD_PROPS));
    public static final RegistryObject<Block> MALLORN_STAIRS = registerWithItem("mallorn_stairs",
            () -> new StairsBlock(() -> MALLORN_PLANKS.get().defaultBlockState(), WOOD_PROPS));
    public static final RegistryObject<Block> APPLE_STAIRS = registerWithItem("apple_stairs",
            () -> new StairsBlock(() -> APPLE_PLANKS.get().defaultBlockState(), WOOD_PROPS));
    public static final RegistryObject<Block> PEAR_STAIRS = registerWithItem("pear_stairs",
            () -> new StairsBlock(() -> PEAR_PLANKS.get().defaultBlockState(), WOOD_PROPS));
    public static final RegistryObject<Block> CHERRY_STAIRS = registerWithItem("cherry_stairs",
            () -> new StairsBlock(() -> CHERRY_PLANKS.get().defaultBlockState(), WOOD_PROPS));
    public static final RegistryObject<Block> MIRK_OAK_STAIRS = registerWithItem("mirk_oak_stairs",
            () -> new StairsBlock(() -> MIRK_OAK_PLANKS.get().defaultBlockState(), WOOD_PROPS));
    public static final RegistryObject<Block> CHARRED_STAIRS = registerWithItem("charred_stairs",
            () -> new StairsBlock(() -> CHARRED_PLANKS.get().defaultBlockState(), WOOD_PROPS));
    public static final RegistryObject<Block> LEBETHRON_STAIRS = registerWithItem("lebethron_stairs",
            () -> new StairsBlock(() -> LEBETHRON_PLANKS.get().defaultBlockState(), WOOD_PROPS));
    public static final RegistryObject<Block> BEECH_STAIRS = registerWithItem("beech_stairs",
            () -> new StairsBlock(() -> BEECH_PLANKS.get().defaultBlockState(), WOOD_PROPS));
    public static final RegistryObject<Block> HOLLY_STAIRS = registerWithItem("holly_stairs",
            () -> new StairsBlock(() -> HOLLY_PLANKS.get().defaultBlockState(), WOOD_PROPS));
    public static final RegistryObject<Block> MANGO_STAIRS = registerWithItem("mango_stairs",
            () -> new StairsBlock(() -> MANGO_PLANKS.get().defaultBlockState(), WOOD_PROPS));
    public static final RegistryObject<Block> BANANA_STAIRS = registerWithItem("banana_stairs",
            () -> new StairsBlock(() -> BANANA_PLANKS.get().defaultBlockState(), WOOD_PROPS));
    public static final RegistryObject<Block> MAPLE_STAIRS = registerWithItem("maple_stairs",
            () -> new StairsBlock(() -> MAPLE_PLANKS.get().defaultBlockState(), WOOD_PROPS));
    public static final RegistryObject<Block> LARCH_STAIRS = registerWithItem("larch_stairs",
            () -> new StairsBlock(() -> LARCH_PLANKS.get().defaultBlockState(), WOOD_PROPS));
    public static final RegistryObject<Block> DATE_PALM_STAIRS = registerWithItem("date_palm_stairs",
            () -> new StairsBlock(() -> DATE_PALM_PLANKS.get().defaultBlockState(), WOOD_PROPS));
    public static final RegistryObject<Block> MANGROVE_STAIRS = registerWithItem("mangrove_stairs",
            () -> new StairsBlock(() -> MANGROVE_PLANKS.get().defaultBlockState(), WOOD_PROPS));
    public static final RegistryObject<Block> CHESTNUT_STAIRS = registerWithItem("chestnut_stairs",
            () -> new StairsBlock(() -> CHESTNUT_PLANKS.get().defaultBlockState(), WOOD_PROPS));
    public static final RegistryObject<Block> BAOBAB_STAIRS = registerWithItem("baobab_stairs",
            () -> new StairsBlock(() -> BAOBAB_PLANKS.get().defaultBlockState(), WOOD_PROPS));
    public static final RegistryObject<Block> CEDAR_STAIRS = registerWithItem("cedar_stairs",
            () -> new StairsBlock(() -> CEDAR_PLANKS.get().defaultBlockState(), WOOD_PROPS));
    public static final RegistryObject<Block> FIR_STAIRS = registerWithItem("fir_stairs",
            () -> new StairsBlock(() -> FIR_PLANKS.get().defaultBlockState(), WOOD_PROPS));
    public static final RegistryObject<Block> PINE_STAIRS = registerWithItem("pine_stairs",
            () -> new StairsBlock(() -> PINE_PLANKS.get().defaultBlockState(), WOOD_PROPS));
    public static final RegistryObject<Block> LEMON_STAIRS = registerWithItem("lemon_stairs",
            () -> new StairsBlock(() -> LEMON_PLANKS.get().defaultBlockState(), WOOD_PROPS));
    public static final RegistryObject<Block> ORANGE_STAIRS = registerWithItem("orange_stairs",
            () -> new StairsBlock(() -> ORANGE_PLANKS.get().defaultBlockState(), WOOD_PROPS));
    public static final RegistryObject<Block> LIME_STAIRS = registerWithItem("lime_stairs",
            () -> new StairsBlock(() -> LIME_PLANKS.get().defaultBlockState(), WOOD_PROPS));
    public static final RegistryObject<Block> MAHOGANY_STAIRS = registerWithItem("mahogany_stairs",
            () -> new StairsBlock(() -> MAHOGANY_PLANKS.get().defaultBlockState(), WOOD_PROPS));
    public static final RegistryObject<Block> WILLOW_STAIRS = registerWithItem("willow_stairs",
            () -> new StairsBlock(() -> WILLOW_PLANKS.get().defaultBlockState(), WOOD_PROPS));
    public static final RegistryObject<Block> CYPRESS_STAIRS = registerWithItem("cypress_stairs",
            () -> new StairsBlock(() -> CYPRESS_PLANKS.get().defaultBlockState(), WOOD_PROPS));
    public static final RegistryObject<Block> OLIVE_STAIRS = registerWithItem("olive_stairs",
            () -> new StairsBlock(() -> OLIVE_PLANKS.get().defaultBlockState(), WOOD_PROPS));
    public static final RegistryObject<Block> ASPEN_STAIRS = registerWithItem("aspen_stairs",
            () -> new StairsBlock(() -> ASPEN_PLANKS.get().defaultBlockState(), WOOD_PROPS));
    public static final RegistryObject<Block> GREEN_OAK_STAIRS = registerWithItem("green_oak_stairs",
            () -> new StairsBlock(() -> GREEN_OAK_PLANKS.get().defaultBlockState(), WOOD_PROPS));
    public static final RegistryObject<Block> LAIRELOSSE_STAIRS = registerWithItem("lairelosse_stairs",
            () -> new StairsBlock(() -> LAIRELOSSE_PLANKS.get().defaultBlockState(), WOOD_PROPS));
    public static final RegistryObject<Block> ALMOND_STAIRS = registerWithItem("almond_stairs",
            () -> new StairsBlock(() -> ALMOND_PLANKS.get().defaultBlockState(), WOOD_PROPS));
    public static final RegistryObject<Block> PLUM_STAIRS = registerWithItem("plum_stairs",
            () -> new StairsBlock(() -> PLUM_PLANKS.get().defaultBlockState(), WOOD_PROPS));
    public static final RegistryObject<Block> REDWOOD_STAIRS = registerWithItem("redwood_stairs",
            () -> new StairsBlock(() -> REDWOOD_PLANKS.get().defaultBlockState(), WOOD_PROPS));
    public static final RegistryObject<Block> POMEGRANATE_STAIRS = registerWithItem("pomegranate_stairs",
            () -> new StairsBlock(() -> POMEGRANATE_PLANKS.get().defaultBlockState(), WOOD_PROPS));
    public static final RegistryObject<Block> PALM_STAIRS = registerWithItem("palm_stairs",
            () -> new StairsBlock(() -> PALM_PLANKS.get().defaultBlockState(), WOOD_PROPS));
    public static final RegistryObject<Block> DRAGON_STAIRS = registerWithItem("dragon_stairs",
            () -> new StairsBlock(() -> DRAGON_PLANKS.get().defaultBlockState(), WOOD_PROPS));
    public static final RegistryObject<Block> KANUKA_STAIRS = registerWithItem("kanuka_stairs",
            () -> new StairsBlock(() -> KANUKA_PLANKS.get().defaultBlockState(), WOOD_PROPS));
    public static final RegistryObject<Block> SHIRE_PINE_SLAB = registerWithItem("shire_pine_slab",
            () -> new SlabBlock(WOOD_PROPS));
    public static final RegistryObject<Block> MALLORN_SLAB = registerWithItem("mallorn_slab",
            () -> new SlabBlock(WOOD_PROPS));
    public static final RegistryObject<Block> MIRK_OAK_SLAB = registerWithItem("mirk_oak_slab",
            () -> new SlabBlock(WOOD_PROPS));
    public static final RegistryObject<Block> CHARRED_SLAB = registerWithItem("charred_slab",
            () -> new SlabBlock(WOOD_PROPS));
    public static final RegistryObject<Block> APPLE_SLAB = registerWithItem("apple_slab",
            () -> new SlabBlock(WOOD_PROPS));
    public static final RegistryObject<Block> PEAR_SLAB = registerWithItem("pear_slab",
            () -> new SlabBlock(WOOD_PROPS));
    public static final RegistryObject<Block> CHERRY_SLAB = registerWithItem("cherry_slab",
            () -> new SlabBlock(WOOD_PROPS));
    public static final RegistryObject<Block> MANGO_SLAB = registerWithItem("mango_slab",
            () -> new SlabBlock(WOOD_PROPS));
    public static final RegistryObject<Block> LEBETHRON_SLAB = registerWithItem("lebethron_slab",
            () -> new SlabBlock(WOOD_PROPS));
    public static final RegistryObject<Block> BEECH_SLAB = registerWithItem("beech_slab",
            () -> new SlabBlock(WOOD_PROPS));
    public static final RegistryObject<Block> HOLLY_SLAB = registerWithItem("holly_slab",
            () -> new SlabBlock(WOOD_PROPS));
    public static final RegistryObject<Block> BANANA_SLAB = registerWithItem("banana_slab",
            () -> new SlabBlock(WOOD_PROPS));
    public static final RegistryObject<Block> MAPLE_SLAB = registerWithItem("maple_slab",
            () -> new SlabBlock(WOOD_PROPS));
    public static final RegistryObject<Block> LARCH_SLAB = registerWithItem("larch_slab",
            () -> new SlabBlock(WOOD_PROPS));
    public static final RegistryObject<Block> DATE_PALM_SLAB = registerWithItem("date_palm_slab",
            () -> new SlabBlock(WOOD_PROPS));
    public static final RegistryObject<Block> MANGROVE_SLAB = registerWithItem("mangrove_slab",
            () -> new SlabBlock(WOOD_PROPS));
    public static final RegistryObject<Block> CHESTNUT_SLAB = registerWithItem("chestnut_slab",
            () -> new SlabBlock(WOOD_PROPS));
    public static final RegistryObject<Block> BAOBAB_SLAB = registerWithItem("baobab_slab",
            () -> new SlabBlock(WOOD_PROPS));
    public static final RegistryObject<Block> CEDAR_SLAB = registerWithItem("cedar_slab",
            () -> new SlabBlock(WOOD_PROPS));
    public static final RegistryObject<Block> FIR_SLAB = registerWithItem("fir_slab",
            () -> new SlabBlock(WOOD_PROPS));
    public static final RegistryObject<Block> PINE_SLAB = registerWithItem("pine_slab",
            () -> new SlabBlock(WOOD_PROPS));
    public static final RegistryObject<Block> LEMON_SLAB = registerWithItem("lemon_slab",
            () -> new SlabBlock(WOOD_PROPS));
    public static final RegistryObject<Block> ORANGE_SLAB = registerWithItem("orange_slab",
            () -> new SlabBlock(WOOD_PROPS));
    public static final RegistryObject<Block> LIME_SLAB = registerWithItem("lime_slab",
            () -> new SlabBlock(WOOD_PROPS));
    public static final RegistryObject<Block> MAHOGANY_SLAB = registerWithItem("mahogany_slab",
            () -> new SlabBlock(WOOD_PROPS));
    public static final RegistryObject<Block> WILLOW_SLAB = registerWithItem("willow_slab",
            () -> new SlabBlock(WOOD_PROPS));
    public static final RegistryObject<Block> CYPRESS_SLAB = registerWithItem("cypress_slab",
            () -> new SlabBlock(WOOD_PROPS));
    public static final RegistryObject<Block> OLIVE_SLAB = registerWithItem("olive_slab",
            () -> new SlabBlock(WOOD_PROPS));
    public static final RegistryObject<Block> ASPEN_SLAB = registerWithItem("aspen_slab",
            () -> new SlabBlock(WOOD_PROPS));
    public static final RegistryObject<Block> GREEN_OAK_SLAB = registerWithItem("green_oak_slab",
            () -> new SlabBlock(WOOD_PROPS));
    public static final RegistryObject<Block> LAIRELOSSE_SLAB = registerWithItem("lairelosse_slab",
            () -> new SlabBlock(WOOD_PROPS));
    public static final RegistryObject<Block> ALMOND_SLAB = registerWithItem("almond_slab",
            () -> new SlabBlock(WOOD_PROPS));
    public static final RegistryObject<Block> PLUM_SLAB = registerWithItem("plum_slab",
            () -> new SlabBlock(WOOD_PROPS));
    public static final RegistryObject<Block> REDWOOD_SLAB = registerWithItem("redwood_slab",
            () -> new SlabBlock(WOOD_PROPS));
    public static final RegistryObject<Block> POMEGRANATE_SLAB = registerWithItem("pomegranate_slab",
            () -> new SlabBlock(WOOD_PROPS));
    public static final RegistryObject<Block> PALM_SLAB = registerWithItem("palm_slab",
            () -> new SlabBlock(WOOD_PROPS));
    public static final RegistryObject<Block> DRAGON_SLAB = registerWithItem("dragon_slab",
            () -> new SlabBlock(WOOD_PROPS));
    public static final RegistryObject<Block> KANUKA_SLAB = registerWithItem("kanuka_slab",
            () -> new SlabBlock(WOOD_PROPS));
    public static final RegistryObject<Block> ROTTEN_SLAB = registerWithItem("rotten_slab",
            () -> new SlabBlock(WOOD_PROPS));
    public static final RegistryObject<Block> SHIRE_PINE_FENCE = registerWithItem("shire_pine_fence",
            () -> new FenceBlock(WOOD_PROPS));
    public static final RegistryObject<Block> MALLORN_FENCE = registerWithItem("mallorn_fence",
            () -> new FenceBlock(WOOD_PROPS));
    public static final RegistryObject<Block> MIRK_OAK_FENCE = registerWithItem("mirk_oak_fence",
            () -> new FenceBlock(WOOD_PROPS));
    public static final RegistryObject<Block> CHARRED_FENCE = registerWithItem("charred_fence",
            () -> new FenceBlock(WOOD_PROPS));
    public static final RegistryObject<Block> APPLE_FENCE = registerWithItem("apple_fence",
            () -> new FenceBlock(WOOD_PROPS));
    public static final RegistryObject<Block> PEAR_FENCE = registerWithItem("pear_fence",
            () -> new FenceBlock(WOOD_PROPS));
    public static final RegistryObject<Block> CHERRY_FENCE = registerWithItem("cherry_fence",
            () -> new FenceBlock(WOOD_PROPS));
    public static final RegistryObject<Block> MANGO_FENCE = registerWithItem("mango_fence",
            () -> new FenceBlock(WOOD_PROPS));
    public static final RegistryObject<Block> LEBETHRON_FENCE = registerWithItem("lebethron_fence",
            () -> new FenceBlock(WOOD_PROPS));
    public static final RegistryObject<Block> BEECH_FENCE = registerWithItem("beech_fence",
            () -> new FenceBlock(WOOD_PROPS));
    public static final RegistryObject<Block> HOLLY_FENCE = registerWithItem("holly_fence",
            () -> new FenceBlock(WOOD_PROPS));
    public static final RegistryObject<Block> BANANA_FENCE = registerWithItem("banana_fence",
            () -> new FenceBlock(WOOD_PROPS));
    public static final RegistryObject<Block> MAPLE_FENCE = registerWithItem("maple_fence",
            () -> new FenceBlock(WOOD_PROPS));
    public static final RegistryObject<Block> LARCH_FENCE = registerWithItem("larch_fence",
            () -> new FenceBlock(WOOD_PROPS));
    public static final RegistryObject<Block> DATE_PALM_FENCE = registerWithItem("date_palm_fence",
            () -> new FenceBlock(WOOD_PROPS));
    public static final RegistryObject<Block> MANGROVE_FENCE = registerWithItem("mangrove_fence",
            () -> new FenceBlock(WOOD_PROPS));
    public static final RegistryObject<Block> CHESTNUT_FENCE = registerWithItem("chestnut_fence",
            () -> new FenceBlock(WOOD_PROPS));
    public static final RegistryObject<Block> BAOBAB_FENCE = registerWithItem("baobab_fence",
            () -> new FenceBlock(WOOD_PROPS));
    public static final RegistryObject<Block> CEDAR_FENCE = registerWithItem("cedar_fence",
            () -> new FenceBlock(WOOD_PROPS));
    public static final RegistryObject<Block> FIR_FENCE = registerWithItem("fir_fence",
            () -> new FenceBlock(WOOD_PROPS));
    public static final RegistryObject<Block> PINE_FENCE = registerWithItem("pine_fence",
            () -> new FenceBlock(WOOD_PROPS));
    public static final RegistryObject<Block> LEMON_FENCE = registerWithItem("lemon_fence",
            () -> new FenceBlock(WOOD_PROPS));
    public static final RegistryObject<Block> ORANGE_FENCE = registerWithItem("orange_fence",
            () -> new FenceBlock(WOOD_PROPS));
    public static final RegistryObject<Block> LIME_FENCE = registerWithItem("lime_fence",
            () -> new FenceBlock(WOOD_PROPS));
    public static final RegistryObject<Block> MAHOGANY_FENCE = registerWithItem("mahogany_fence",
            () -> new FenceBlock(WOOD_PROPS));
    public static final RegistryObject<Block> WILLOW_FENCE = registerWithItem("willow_fence",
            () -> new FenceBlock(WOOD_PROPS));
    public static final RegistryObject<Block> CYPRESS_FENCE = registerWithItem("cypress_fence",
            () -> new FenceBlock(WOOD_PROPS));
    public static final RegistryObject<Block> OLIVE_FENCE = registerWithItem("olive_fence",
            () -> new FenceBlock(WOOD_PROPS));
    public static final RegistryObject<Block> ASPEN_FENCE = registerWithItem("aspen_fence",
            () -> new FenceBlock(WOOD_PROPS));
    public static final RegistryObject<Block> GREEN_OAK_FENCE = registerWithItem("green_oak_fence",
            () -> new FenceBlock(WOOD_PROPS));
    public static final RegistryObject<Block> LAIRELOSSE_FENCE = registerWithItem("lairelosse_fence",
            () -> new FenceBlock(WOOD_PROPS));
    public static final RegistryObject<Block> ALMOND_FENCE = registerWithItem("almond_fence",
            () -> new FenceBlock(WOOD_PROPS));
    public static final RegistryObject<Block> PLUM_FENCE = registerWithItem("plum_fence",
            () -> new FenceBlock(WOOD_PROPS));
    public static final RegistryObject<Block> REDWOOD_FENCE = registerWithItem("redwood_fence",
            () -> new FenceBlock(WOOD_PROPS));
    public static final RegistryObject<Block> POMEGRANATE_FENCE = registerWithItem("pomegranate_fence",
            () -> new FenceBlock(WOOD_PROPS));
    public static final RegistryObject<Block> PALM_FENCE = registerWithItem("palm_fence",
            () -> new FenceBlock(WOOD_PROPS));
    public static final RegistryObject<Block> DRAGON_FENCE = registerWithItem("dragon_fence",
            () -> new FenceBlock(WOOD_PROPS));
    public static final RegistryObject<Block> KANUKA_FENCE = registerWithItem("kanuka_fence",
            () -> new FenceBlock(WOOD_PROPS));
    public static final RegistryObject<Block> ROTTEN_FENCE = registerWithItem("rotten_fence",
            () -> new FenceBlock(WOOD_PROPS));
    public static final RegistryObject<Block> SHIRE_PINE_FENCE_GATE = registerWithItem("shire_pine_fence_gate",
            () -> new FenceGateBlock(WOOD_PROPS));
    public static final RegistryObject<Block> MALLORN_FENCE_GATE = registerWithItem("mallorn_fence_gate",
            () -> new FenceGateBlock(WOOD_PROPS));
    public static final RegistryObject<Block> MIRK_OAK_FENCE_GATE = registerWithItem("mirk_oak_fence_gate",
            () -> new FenceGateBlock(WOOD_PROPS));
    public static final RegistryObject<Block> CHARRED_FENCE_GATE = registerWithItem("charred_fence_gate",
            () -> new FenceGateBlock(WOOD_PROPS));
    public static final RegistryObject<Block> APPLE_FENCE_GATE = registerWithItem("apple_fence_gate",
            () -> new FenceGateBlock(WOOD_PROPS));
    public static final RegistryObject<Block> PEAR_FENCE_GATE = registerWithItem("pear_fence_gate",
            () -> new FenceGateBlock(WOOD_PROPS));
    public static final RegistryObject<Block> CHERRY_FENCE_GATE = registerWithItem("cherry_fence_gate",
            () -> new FenceGateBlock(WOOD_PROPS));
    public static final RegistryObject<Block> MANGO_FENCE_GATE = registerWithItem("mango_fence_gate",
            () -> new FenceGateBlock(WOOD_PROPS));
    public static final RegistryObject<Block> LEBETHRON_FENCE_GATE = registerWithItem("lebethron_fence_gate",
            () -> new FenceGateBlock(WOOD_PROPS));
    public static final RegistryObject<Block> BEECH_FENCE_GATE = registerWithItem("beech_fence_gate",
            () -> new FenceGateBlock(WOOD_PROPS));
    public static final RegistryObject<Block> HOLLY_FENCE_GATE = registerWithItem("holly_fence_gate",
            () -> new FenceGateBlock(WOOD_PROPS));
    public static final RegistryObject<Block> BANANA_FENCE_GATE = registerWithItem("banana_fence_gate",
            () -> new FenceGateBlock(WOOD_PROPS));
    public static final RegistryObject<Block> MAPLE_FENCE_GATE = registerWithItem("maple_fence_gate",
            () -> new FenceGateBlock(WOOD_PROPS));
    public static final RegistryObject<Block> LARCH_FENCE_GATE = registerWithItem("larch_fence_gate",
            () -> new FenceGateBlock(WOOD_PROPS));
    public static final RegistryObject<Block> DATE_PALM_FENCE_GATE = registerWithItem("date_palm_fence_gate",
            () -> new FenceGateBlock(WOOD_PROPS));
    public static final RegistryObject<Block> MANGROVE_FENCE_GATE = registerWithItem("mangrove_fence_gate",
            () -> new FenceGateBlock(WOOD_PROPS));
    public static final RegistryObject<Block> CHESTNUT_FENCE_GATE = registerWithItem("chestnut_fence_gate",
            () -> new FenceGateBlock(WOOD_PROPS));
    public static final RegistryObject<Block> BAOBAB_FENCE_GATE = registerWithItem("baobab_fence_gate",
            () -> new FenceGateBlock(WOOD_PROPS));
    public static final RegistryObject<Block> CEDAR_FENCE_GATE = registerWithItem("cedar_fence_gate",
            () -> new FenceGateBlock(WOOD_PROPS));
    public static final RegistryObject<Block> FIR_FENCE_GATE = registerWithItem("fir_fence_gate",
            () -> new FenceGateBlock(WOOD_PROPS));
    public static final RegistryObject<Block> PINE_FENCE_GATE = registerWithItem("pine_fence_gate",
            () -> new FenceGateBlock(WOOD_PROPS));
    public static final RegistryObject<Block> LEMON_FENCE_GATE = registerWithItem("lemon_fence_gate",
            () -> new FenceGateBlock(WOOD_PROPS));
    public static final RegistryObject<Block> ORANGE_FENCE_GATE = registerWithItem("orange_fence_gate",
            () -> new FenceGateBlock(WOOD_PROPS));
    public static final RegistryObject<Block> LIME_FENCE_GATE = registerWithItem("lime_fence_gate",
            () -> new FenceGateBlock(WOOD_PROPS));
    public static final RegistryObject<Block> MAHOGANY_FENCE_GATE = registerWithItem("mahogany_fence_gate",
            () -> new FenceGateBlock(WOOD_PROPS));
    public static final RegistryObject<Block> WILLOW_FENCE_GATE = registerWithItem("willow_fence_gate",
            () -> new FenceGateBlock(WOOD_PROPS));
    public static final RegistryObject<Block> CYPRESS_FENCE_GATE = registerWithItem("cypress_fence_gate",
            () -> new FenceGateBlock(WOOD_PROPS));
    public static final RegistryObject<Block> OLIVE_FENCE_GATE = registerWithItem("olive_fence_gate",
            () -> new FenceGateBlock(WOOD_PROPS));
    public static final RegistryObject<Block> ASPEN_FENCE_GATE = registerWithItem("aspen_fence_gate",
            () -> new FenceGateBlock(WOOD_PROPS));
    public static final RegistryObject<Block> GREEN_OAK_FENCE_GATE = registerWithItem("green_oak_fence_gate",
            () -> new FenceGateBlock(WOOD_PROPS));
    public static final RegistryObject<Block> LAIRELOSSE_FENCE_GATE = registerWithItem("lairelosse_fence_gate",
            () -> new FenceGateBlock(WOOD_PROPS));
    public static final RegistryObject<Block> ALMOND_FENCE_GATE = registerWithItem("almond_fence_gate",
            () -> new FenceGateBlock(WOOD_PROPS));
    public static final RegistryObject<Block> ROTTEN_FENCE_GATE = registerWithItem("rotten_fence_gate",
            () -> new FenceGateBlock(WOOD_PROPS));
    public static final RegistryObject<Block> PLUM_FENCE_GATE = registerWithItem("plum_fence_gate",
            () -> new FenceGateBlock(WOOD_PROPS));
    public static final RegistryObject<Block> REDWOOD_FENCE_GATE = registerWithItem("redwood_fence_gate",
            () -> new FenceGateBlock(WOOD_PROPS));
    public static final RegistryObject<Block> POMEGRANATE_FENCE_GATE = registerWithItem("pomegranate_fence_gate",
            () -> new FenceGateBlock(WOOD_PROPS));
    public static final RegistryObject<Block> PALM_FENCE_GATE = registerWithItem("palm_fence_gate",
            () -> new FenceGateBlock(WOOD_PROPS));
    public static final RegistryObject<Block> DRAGON_FENCE_GATE = registerWithItem("dragon_fence_gate",
            () -> new FenceGateBlock(WOOD_PROPS));
    public static final RegistryObject<Block> KANUKA_FENCE_GATE = registerWithItem("kanuka_fence_gate",
            () -> new FenceGateBlock(WOOD_PROPS));
}
