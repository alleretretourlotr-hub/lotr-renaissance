package fr.alleretretour.lotr.init;

import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.PaneBlock;
import net.minecraft.block.PressurePlateBlock;
import net.minecraft.block.SoundType;
import net.minecraft.block.StoneButtonBlock;
import net.minecraft.block.material.Material;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraftforge.fml.RegistryObject;

import java.util.function.Supplier;

/** GENERE AUTOMATIQUEMENT - Phase 2 lot 12 : barreaux, boutons, plaques de pression. */
public class LOTRBlocksMisc {

    static final AbstractBlock.Properties BARS_PROPS =
            AbstractBlock.Properties.of(Material.METAL).requiresCorrectToolForDrops()
                    .strength(5.0f, 6.0f).sound(SoundType.METAL).noOcclusion();
    static final AbstractBlock.Properties WOOD_BARS_PROPS =
            AbstractBlock.Properties.of(Material.WOOD).strength(2.0f, 3.0f)
                    .sound(SoundType.WOOD).noOcclusion();
    static final AbstractBlock.Properties PLATE_PROPS =
            AbstractBlock.Properties.of(Material.STONE).requiresCorrectToolForDrops()
                    .noCollission().strength(0.5f).sound(SoundType.STONE);
    static final AbstractBlock.Properties BUTTON_PROPS =
            AbstractBlock.Properties.of(Material.DECORATION).noCollission()
                    .strength(0.5f).sound(SoundType.STONE);

    public static void init() {
    }

    private static RegistryObject<Block> registerWithItem(String name, Supplier<Block> block) {
        RegistryObject<Block> ro = LOTRBlocks.BLOCKS.register(name, block);
        LOTRItems.ITEMS.register(name, () -> new BlockItem(ro.get(),
                new Item.Properties().tab(LOTRCreativeTabs.TAB_BLOCKS)));
        return ro;
    }

    public static final RegistryObject<Block> ORC_STEEL_BARS = registerWithItem("orc_steel_bars",
            () -> new PaneBlock(BARS_PROPS) {});
    public static final RegistryObject<Block> BRONZE_BARS = registerWithItem("bronze_bars",
            () -> new PaneBlock(BARS_PROPS) {});
    public static final RegistryObject<Block> GOLD_BARS = registerWithItem("gold_bars",
            () -> new PaneBlock(BARS_PROPS) {});
    public static final RegistryObject<Block> SILVER_BARS = registerWithItem("silver_bars",
            () -> new PaneBlock(BARS_PROPS) {});
    public static final RegistryObject<Block> MITHRIL_BARS = registerWithItem("mithril_bars",
            () -> new PaneBlock(BARS_PROPS) {});
    public static final RegistryObject<Block> URUK_BARS = registerWithItem("uruk_bars",
            () -> new PaneBlock(BARS_PROPS) {});
    public static final RegistryObject<Block> HIGH_ELF_BARS = registerWithItem("high_elf_bars",
            () -> new PaneBlock(BARS_PROPS) {});
    public static final RegistryObject<Block> GALADHRIM_BARS = registerWithItem("galadhrim_bars",
            () -> new PaneBlock(BARS_PROPS) {});
    public static final RegistryObject<Block> WOOD_ELF_BARS = registerWithItem("wood_elf_bars",
            () -> new PaneBlock(WOOD_BARS_PROPS) {});
    public static final RegistryObject<Block> DWARF_BARS = registerWithItem("dwarf_bars",
            () -> new PaneBlock(BARS_PROPS) {});
    public static final RegistryObject<Block> BLUE_DWARF_BARS = registerWithItem("blue_dwarf_bars",
            () -> new PaneBlock(BARS_PROPS) {});
    public static final RegistryObject<Block> HIGH_ELF_WOOD_BARS = registerWithItem("high_elf_wood_bars",
            () -> new PaneBlock(WOOD_BARS_PROPS) {});
    public static final RegistryObject<Block> GALADHRIM_WOOD_BARS = registerWithItem("galadhrim_wood_bars",
            () -> new PaneBlock(WOOD_BARS_PROPS) {});
    public static final RegistryObject<Block> WOOD_ELF_WOOD_BARS = registerWithItem("wood_elf_wood_bars",
            () -> new PaneBlock(WOOD_BARS_PROPS) {});
    public static final RegistryObject<Block> PRESSURE_PLATE_MORDOR_ROCK = registerWithItem("pressure_plate_mordor_rock",
            () -> new PressurePlateBlock(PressurePlateBlock.Sensitivity.MOBS, PLATE_PROPS) {});
    public static final RegistryObject<Block> PRESSURE_PLATE_GONDOR_ROCK = registerWithItem("pressure_plate_gondor_rock",
            () -> new PressurePlateBlock(PressurePlateBlock.Sensitivity.MOBS, PLATE_PROPS) {});
    public static final RegistryObject<Block> PRESSURE_PLATE_ROHAN_ROCK = registerWithItem("pressure_plate_rohan_rock",
            () -> new PressurePlateBlock(PressurePlateBlock.Sensitivity.MOBS, PLATE_PROPS) {});
    public static final RegistryObject<Block> PRESSURE_PLATE_BLUE_ROCK = registerWithItem("pressure_plate_blue_rock",
            () -> new PressurePlateBlock(PressurePlateBlock.Sensitivity.MOBS, PLATE_PROPS) {});
    public static final RegistryObject<Block> PRESSURE_PLATE_RED_ROCK = registerWithItem("pressure_plate_red_rock",
            () -> new PressurePlateBlock(PressurePlateBlock.Sensitivity.MOBS, PLATE_PROPS) {});
    public static final RegistryObject<Block> PRESSURE_PLATE_CHALK = registerWithItem("pressure_plate_chalk",
            () -> new PressurePlateBlock(PressurePlateBlock.Sensitivity.MOBS, PLATE_PROPS) {});
    public static final RegistryObject<Block> BUTTON_MORDOR_ROCK = registerWithItem("button_mordor_rock",
            () -> new StoneButtonBlock(BUTTON_PROPS) {});
    public static final RegistryObject<Block> BUTTON_GONDOR_ROCK = registerWithItem("button_gondor_rock",
            () -> new StoneButtonBlock(BUTTON_PROPS) {});
    public static final RegistryObject<Block> BUTTON_ROHAN_ROCK = registerWithItem("button_rohan_rock",
            () -> new StoneButtonBlock(BUTTON_PROPS) {});
    public static final RegistryObject<Block> BUTTON_BLUE_ROCK = registerWithItem("button_blue_rock",
            () -> new StoneButtonBlock(BUTTON_PROPS) {});
    public static final RegistryObject<Block> BUTTON_RED_ROCK = registerWithItem("button_red_rock",
            () -> new StoneButtonBlock(BUTTON_PROPS) {});
    public static final RegistryObject<Block> BUTTON_CHALK = registerWithItem("button_chalk",
            () -> new StoneButtonBlock(BUTTON_PROPS) {});
}
