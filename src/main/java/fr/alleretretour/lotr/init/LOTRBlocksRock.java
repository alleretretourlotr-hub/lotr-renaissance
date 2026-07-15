package fr.alleretretour.lotr.init;

import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.OreBlock;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraftforge.fml.RegistryObject;

import java.util.function.Supplier;

/**
 * GENERE AUTOMATIQUEMENT - Phase 2 lot 4 : roches, briques et minerais.
 */
public class LOTRBlocksRock {

    static final AbstractBlock.Properties ROCK_PROPS =
            AbstractBlock.Properties.of(Material.STONE).requiresCorrectToolForDrops()
                    .strength(1.5f, 10.0f).sound(SoundType.STONE);

    public static void init() {
    }

    private static RegistryObject<Block> registerWithItem(String name, Supplier<Block> block) {
        RegistryObject<Block> ro = LOTRBlocks.BLOCKS.register(name, block);
        LOTRItems.ITEMS.register(name, () -> new BlockItem(ro.get(),
                new Item.Properties().tab(LOTRCreativeTabs.TAB_BLOCKS)));
        return ro;
    }

    public static final RegistryObject<Block> MORDOR_ROCK = registerWithItem("mordor_rock",
            () -> new Block(ROCK_PROPS));
    public static final RegistryObject<Block> GONDOR_ROCK = registerWithItem("gondor_rock",
            () -> new Block(ROCK_PROPS));
    public static final RegistryObject<Block> ROHAN_ROCK = registerWithItem("rohan_rock",
            () -> new Block(ROCK_PROPS));
    public static final RegistryObject<Block> BLUE_ROCK = registerWithItem("blue_rock",
            () -> new Block(ROCK_PROPS));
    public static final RegistryObject<Block> RED_ROCK = registerWithItem("red_rock",
            () -> new Block(ROCK_PROPS));
    public static final RegistryObject<Block> CHALK_ROCK = registerWithItem("chalk_rock",
            () -> new Block(ROCK_PROPS));
    public static final RegistryObject<Block> MORDOR_BRICK = registerWithItem("mordor_brick",
            () -> new Block(ROCK_PROPS));
    public static final RegistryObject<Block> GONDOR_BRICK = registerWithItem("gondor_brick",
            () -> new Block(ROCK_PROPS));
    public static final RegistryObject<Block> GONDOR_MOSSY_BRICK = registerWithItem("gondor_mossy_brick",
            () -> new Block(ROCK_PROPS));
    public static final RegistryObject<Block> GONDOR_CRACKED_BRICK = registerWithItem("gondor_cracked_brick",
            () -> new Block(ROCK_PROPS));
    public static final RegistryObject<Block> ROHAN_BRICK = registerWithItem("rohan_brick",
            () -> new Block(ROCK_PROPS));
    public static final RegistryObject<Block> GONDOR_CARVED_BRICK = registerWithItem("gondor_carved_brick",
            () -> new Block(ROCK_PROPS));
    public static final RegistryObject<Block> DWARVEN_BRICK = registerWithItem("dwarven_brick",
            () -> new Block(ROCK_PROPS));
    public static final RegistryObject<Block> MORDOR_CRACKED_BRICK = registerWithItem("mordor_cracked_brick",
            () -> new Block(ROCK_PROPS));
    public static final RegistryObject<Block> DWARVEN_SILVER_BRICK = registerWithItem("dwarven_silver_brick",
            () -> new Block(ROCK_PROPS));
    public static final RegistryObject<Block> DWARVEN_GOLD_BRICK = registerWithItem("dwarven_gold_brick",
            () -> new Block(ROCK_PROPS));
    public static final RegistryObject<Block> DWARVEN_MITHRIL_BRICK = registerWithItem("dwarven_mithril_brick",
            () -> new Block(ROCK_PROPS));
    public static final RegistryObject<Block> GALADHRIM_BRICK = registerWithItem("galadhrim_brick",
            () -> new Block(ROCK_PROPS));
    public static final RegistryObject<Block> GALADHRIM_MOSSY_BRICK = registerWithItem("galadhrim_mossy_brick",
            () -> new Block(ROCK_PROPS));
    public static final RegistryObject<Block> GALADHRIM_CRACKED_BRICK = registerWithItem("galadhrim_cracked_brick",
            () -> new Block(ROCK_PROPS));
    public static final RegistryObject<Block> BLUE_ROCK_BRICK = registerWithItem("blue_rock_brick",
            () -> new Block(ROCK_PROPS));
    public static final RegistryObject<Block> NEAR_HARAD_BRICK = registerWithItem("near_harad_brick",
            () -> new Block(ROCK_PROPS));
    public static final RegistryObject<Block> ANGMAR_BRICK = registerWithItem("angmar_brick",
            () -> new Block(ROCK_PROPS));
    public static final RegistryObject<Block> ANGMAR_CRACKED_BRICK = registerWithItem("angmar_cracked_brick",
            () -> new Block(ROCK_PROPS));
    public static final RegistryObject<Block> RED_ROCK_BRICK = registerWithItem("red_rock_brick",
            () -> new Block(ROCK_PROPS));
    public static final RegistryObject<Block> ARNOR_BRICK = registerWithItem("arnor_brick",
            () -> new Block(ROCK_PROPS));
    public static final RegistryObject<Block> ARNOR_MOSSY_BRICK = registerWithItem("arnor_mossy_brick",
            () -> new Block(ROCK_PROPS));
    public static final RegistryObject<Block> ARNOR_CRACKED_BRICK = registerWithItem("arnor_cracked_brick",
            () -> new Block(ROCK_PROPS));
    public static final RegistryObject<Block> ARNOR_CARVED_BRICK = registerWithItem("arnor_carved_brick",
            () -> new Block(ROCK_PROPS));
    public static final RegistryObject<Block> URUK_BRICK = registerWithItem("uruk_brick",
            () -> new Block(ROCK_PROPS));
    public static final RegistryObject<Block> DOL_GULDUR_BRICK = registerWithItem("dol_guldur_brick",
            () -> new Block(ROCK_PROPS));
    public static final RegistryObject<Block> DOL_GULDUR_CRACKED_BRICK = registerWithItem("dol_guldur_cracked_brick",
            () -> new Block(ROCK_PROPS));
    public static final RegistryObject<Block> MORDOR_CARVED_BRICK = registerWithItem("mordor_carved_brick",
            () -> new Block(ROCK_PROPS));
    public static final RegistryObject<Block> BLACK_GONDOR_BRICK = registerWithItem("black_gondor_brick",
            () -> new Block(ROCK_PROPS));
    public static final RegistryObject<Block> DWARVEN_CARVED_BRICK = registerWithItem("dwarven_carved_brick",
            () -> new Block(ROCK_PROPS));
    public static final RegistryObject<Block> HIGH_ELVEN_CARVED_BRICK = registerWithItem("high_elven_carved_brick",
            () -> new Block(ROCK_PROPS));
    public static final RegistryObject<Block> WOOD_ELVEN_CARVED_BRICK = registerWithItem("wood_elven_carved_brick",
            () -> new Block(ROCK_PROPS));
    public static final RegistryObject<Block> GALADHRIM_CARVED_BRICK = registerWithItem("galadhrim_carved_brick",
            () -> new Block(ROCK_PROPS));
    public static final RegistryObject<Block> BLUE_CARVED_BRICK = registerWithItem("blue_carved_brick",
            () -> new Block(ROCK_PROPS));
    public static final RegistryObject<Block> RED_CARVED_BRICK = registerWithItem("red_carved_brick",
            () -> new Block(ROCK_PROPS));
    public static final RegistryObject<Block> HIGH_ELVEN_BRICK = registerWithItem("high_elven_brick",
            () -> new Block(ROCK_PROPS));
    public static final RegistryObject<Block> HIGH_ELVEN_MOSSY_BRICK = registerWithItem("high_elven_mossy_brick",
            () -> new Block(ROCK_PROPS));
    public static final RegistryObject<Block> HIGH_ELVEN_CRACKED_BRICK = registerWithItem("high_elven_cracked_brick",
            () -> new Block(ROCK_PROPS));
    public static final RegistryObject<Block> WOOD_ELVEN_BRICK = registerWithItem("wood_elven_brick",
            () -> new Block(ROCK_PROPS));
    public static final RegistryObject<Block> WOOD_ELVEN_MOSSY_BRICK = registerWithItem("wood_elven_mossy_brick",
            () -> new Block(ROCK_PROPS));
    public static final RegistryObject<Block> WOOD_ELVEN_CRACKED_BRICK = registerWithItem("wood_elven_cracked_brick",
            () -> new Block(ROCK_PROPS));
    public static final RegistryObject<Block> NEAR_HARAD_CARVED_BRICK = registerWithItem("near_harad_carved_brick",
            () -> new Block(ROCK_PROPS));
    public static final RegistryObject<Block> DOL_AMROTH_BRICK = registerWithItem("dol_amroth_brick",
            () -> new Block(ROCK_PROPS));
    public static final RegistryObject<Block> MOREDAIN_BRICK = registerWithItem("moredain_brick",
            () -> new Block(ROCK_PROPS));
    public static final RegistryObject<Block> NEAR_HARAD_CRACKED_BRICK = registerWithItem("near_harad_cracked_brick",
            () -> new Block(ROCK_PROPS));
    public static final RegistryObject<Block> DWARVEN_GLOWING_BRICK = registerWithItem("dwarven_glowing_brick",
            () -> new Block(ROCK_PROPS));
    public static final RegistryObject<Block> NEAR_HARAD_RED_BRICK = registerWithItem("near_harad_red_brick",
            () -> new Block(ROCK_PROPS));
    public static final RegistryObject<Block> NEAR_HARAD_RED_CRACKED_BRICK = registerWithItem("near_harad_red_cracked_brick",
            () -> new Block(ROCK_PROPS));
    public static final RegistryObject<Block> NEAR_HARAD_RED_CARVED_BRICK = registerWithItem("near_harad_red_carved_brick",
            () -> new Block(ROCK_PROPS));
    public static final RegistryObject<Block> TAUREDAIN_BRICK = registerWithItem("tauredain_brick",
            () -> new Block(ROCK_PROPS));
    public static final RegistryObject<Block> TAUREDAIN_MOSSY_BRICK = registerWithItem("tauredain_mossy_brick",
            () -> new Block(ROCK_PROPS));
    public static final RegistryObject<Block> TAUREDAIN_CRACKED_BRICK = registerWithItem("tauredain_cracked_brick",
            () -> new Block(ROCK_PROPS));
    public static final RegistryObject<Block> TAUREDAIN_GOLD_BRICK = registerWithItem("tauredain_gold_brick",
            () -> new Block(ROCK_PROPS));
    public static final RegistryObject<Block> TAUREDAIN_OBSIDIAN_BRICK = registerWithItem("tauredain_obsidian_brick",
            () -> new Block(ROCK_PROPS));
    public static final RegistryObject<Block> DWARVEN_CRACKED_BRICK = registerWithItem("dwarven_cracked_brick",
            () -> new Block(ROCK_PROPS));
    public static final RegistryObject<Block> BLACK_GONDOR_CARVED_BRICK = registerWithItem("black_gondor_carved_brick",
            () -> new Block(ROCK_PROPS));
    public static final RegistryObject<Block> NEAR_HARAD_LAPIS_BRICK = registerWithItem("near_harad_lapis_brick",
            () -> new Block(ROCK_PROPS));
    public static final RegistryObject<Block> HIGH_ELVEN_SILVER_BRICK = registerWithItem("high_elven_silver_brick",
            () -> new Block(ROCK_PROPS));
    public static final RegistryObject<Block> GALADHRIM_SILVER_BRICK = registerWithItem("galadhrim_silver_brick",
            () -> new Block(ROCK_PROPS));
    public static final RegistryObject<Block> WOOD_ELVEN_SILVER_BRICK = registerWithItem("wood_elven_silver_brick",
            () -> new Block(ROCK_PROPS));
    public static final RegistryObject<Block> HIGH_ELVEN_GOLD_BRICK = registerWithItem("high_elven_gold_brick",
            () -> new Block(ROCK_PROPS));
    public static final RegistryObject<Block> GALADHRIM_GOLD_BRICK = registerWithItem("galadhrim_gold_brick",
            () -> new Block(ROCK_PROPS));
    public static final RegistryObject<Block> WOOD_ELVEN_GOLD_BRICK = registerWithItem("wood_elven_gold_brick",
            () -> new Block(ROCK_PROPS));
    public static final RegistryObject<Block> DWARVEN_OBSIDIAN_BRICK = registerWithItem("dwarven_obsidian_brick",
            () -> new Block(ROCK_PROPS));
    public static final RegistryObject<Block> CHALK_BRICK = registerWithItem("chalk_brick",
            () -> new Block(ROCK_PROPS));
    public static final RegistryObject<Block> MUD_BRICK = registerWithItem("mud_brick",
            () -> new Block(ROCK_PROPS));
    public static final RegistryObject<Block> DALE_BRICK = registerWithItem("dale_brick",
            () -> new Block(ROCK_PROPS));
    public static final RegistryObject<Block> DORWINION_BRICK = registerWithItem("dorwinion_brick",
            () -> new Block(ROCK_PROPS));
    public static final RegistryObject<Block> ROHAN_CARVED_BRICK = registerWithItem("rohan_carved_brick",
            () -> new Block(ROCK_PROPS));
    public static final RegistryObject<Block> DORWINION_MOSSY_BRICK = registerWithItem("dorwinion_mossy_brick",
            () -> new Block(ROCK_PROPS));
    public static final RegistryObject<Block> DORWINION_CRACKED_BRICK = registerWithItem("dorwinion_cracked_brick",
            () -> new Block(ROCK_PROPS));
    public static final RegistryObject<Block> DORWINION_CARVED_BRICK = registerWithItem("dorwinion_carved_brick",
            () -> new Block(ROCK_PROPS));
    public static final RegistryObject<Block> DORWINION_FLOWERS_BRICK = registerWithItem("dorwinion_flowers_brick",
            () -> new Block(ROCK_PROPS));
    public static final RegistryObject<Block> GONDOR_RUSTIC_BRICK = registerWithItem("gondor_rustic_brick",
            () -> new Block(ROCK_PROPS));
    public static final RegistryObject<Block> GONDOR_RUSTIC_MOSSY_BRICK = registerWithItem("gondor_rustic_mossy_brick",
            () -> new Block(ROCK_PROPS));
    public static final RegistryObject<Block> GONDOR_RUSTIC_CRACKED_BRICK = registerWithItem("gondor_rustic_cracked_brick",
            () -> new Block(ROCK_PROPS));
    public static final RegistryObject<Block> RHUN_BRICK = registerWithItem("rhun_brick",
            () -> new Block(ROCK_PROPS));
    public static final RegistryObject<Block> RHUN_CARVED_BRICK = registerWithItem("rhun_carved_brick",
            () -> new Block(ROCK_PROPS));
    public static final RegistryObject<Block> RHUN_MOSSY_BRICK = registerWithItem("rhun_mossy_brick",
            () -> new Block(ROCK_PROPS));
    public static final RegistryObject<Block> RHUN_CRACKED_BRICK = registerWithItem("rhun_cracked_brick",
            () -> new Block(ROCK_PROPS));
    public static final RegistryObject<Block> RHUN_FLOWERS_BRICK = registerWithItem("rhun_flowers_brick",
            () -> new Block(ROCK_PROPS));
    public static final RegistryObject<Block> RHUN_GOLD_BRICK = registerWithItem("rhun_gold_brick",
            () -> new Block(ROCK_PROPS));
    public static final RegistryObject<Block> RHUN_RED_BRICK = registerWithItem("rhun_red_brick",
            () -> new Block(ROCK_PROPS));
    public static final RegistryObject<Block> RHUN_RED_CARVED_BRICK = registerWithItem("rhun_red_carved_brick",
            () -> new Block(ROCK_PROPS));
    public static final RegistryObject<Block> DALE_MOSSY_BRICK = registerWithItem("dale_mossy_brick",
            () -> new Block(ROCK_PROPS));
    public static final RegistryObject<Block> DALE_CRACKED_BRICK = registerWithItem("dale_cracked_brick",
            () -> new Block(ROCK_PROPS));
    public static final RegistryObject<Block> DALE_CARVED_BRICK = registerWithItem("dale_carved_brick",
            () -> new Block(ROCK_PROPS));
    public static final RegistryObject<Block> UMBAR_BRICK = registerWithItem("umbar_brick",
            () -> new Block(ROCK_PROPS));
    public static final RegistryObject<Block> UMBAR_CRACKED_BRICK = registerWithItem("umbar_cracked_brick",
            () -> new Block(ROCK_PROPS));
    public static final RegistryObject<Block> UMBAR_CARVED_BRICK = registerWithItem("umbar_carved_brick",
            () -> new Block(ROCK_PROPS));
    public static final RegistryObject<Block> BLACK_UMBAR_CARVED_BRICK = registerWithItem("black_umbar_carved_brick",
            () -> new Block(ROCK_PROPS));
    public static final RegistryObject<Block> ANGMAR_SNOW_BRICK = registerWithItem("angmar_snow_brick",
            () -> new Block(ROCK_PROPS));
    public static final RegistryObject<Block> DOL_GULDUR_MOSSY_BRICK = registerWithItem("dol_guldur_mossy_brick",
            () -> new Block(ROCK_PROPS));
    public static final RegistryObject<Block> DOL_GULDUR_CARVED_BRICK = registerWithItem("dol_guldur_carved_brick",
            () -> new Block(ROCK_PROPS));
    public static final RegistryObject<Block> MORWAITH_CRACKED_BRICK = registerWithItem("morwaith_cracked_brick",
            () -> new Block(ROCK_PROPS));
    public static final RegistryObject<Block> COPPER_ORE = registerWithItem("copper_ore",
            () -> new OreBlock(AbstractBlock.Properties.of(Material.STONE).requiresCorrectToolForDrops().strength(3.0f, 5.0f)));
    public static final RegistryObject<Block> TIN_ORE = registerWithItem("tin_ore",
            () -> new OreBlock(AbstractBlock.Properties.of(Material.STONE).requiresCorrectToolForDrops().strength(3.0f, 5.0f)));
    public static final RegistryObject<Block> SILVER_ORE = registerWithItem("silver_ore",
            () -> new OreBlock(AbstractBlock.Properties.of(Material.STONE).requiresCorrectToolForDrops().strength(3.0f, 5.0f)));
    public static final RegistryObject<Block> MITHRIL_ORE = registerWithItem("mithril_ore",
            () -> new OreBlock(AbstractBlock.Properties.of(Material.STONE).requiresCorrectToolForDrops().strength(3.0f, 5.0f).strength(4.0f, 10.0f)));
    public static final RegistryObject<Block> NAURITE_ORE = registerWithItem("naurite_ore",
            () -> new OreBlock(AbstractBlock.Properties.of(Material.STONE).requiresCorrectToolForDrops().strength(3.0f, 5.0f).lightLevel(s -> 7)));
    public static final RegistryObject<Block> QUENDITE_ORE = registerWithItem("quendite_ore",
            () -> new OreBlock(AbstractBlock.Properties.of(Material.STONE).requiresCorrectToolForDrops().strength(3.0f, 5.0f).lightLevel(s -> 11)));
    public static final RegistryObject<Block> GLOWSTONE_ORE = registerWithItem("glowstone_ore",
            () -> new OreBlock(AbstractBlock.Properties.of(Material.STONE).requiresCorrectToolForDrops().strength(3.0f, 5.0f).lightLevel(s -> 11)));
    public static final RegistryObject<Block> GULDURIL_ORE = registerWithItem("gulduril_ore",
            () -> new OreBlock(AbstractBlock.Properties.of(Material.STONE).requiresCorrectToolForDrops().strength(3.0f, 5.0f).lightLevel(s -> 11)));
    public static final RegistryObject<Block> MORGUL_IRON_ORE = registerWithItem("morgul_iron_ore",
            () -> new OreBlock(AbstractBlock.Properties.of(Material.STONE).requiresCorrectToolForDrops().strength(3.0f, 5.0f)));
    public static final RegistryObject<Block> SULFUR_ORE = registerWithItem("sulfur_ore",
            () -> new OreBlock(AbstractBlock.Properties.of(Material.STONE).requiresCorrectToolForDrops().strength(3.0f, 5.0f)));
    public static final RegistryObject<Block> SALTPETER_ORE = registerWithItem("saltpeter_ore",
            () -> new OreBlock(AbstractBlock.Properties.of(Material.STONE).requiresCorrectToolForDrops().strength(3.0f, 5.0f)));
}
