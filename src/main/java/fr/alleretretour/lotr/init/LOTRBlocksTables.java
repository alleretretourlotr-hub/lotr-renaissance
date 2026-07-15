package fr.alleretretour.lotr.init;

import fr.alleretretour.lotr.block.LOTRBlockCraftingTable;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraftforge.fml.RegistryObject;

import java.util.function.Supplier;

/** GENERE AUTOMATIQUEMENT - Phase 2 lot 8 : les 28 tables de craft de faction. */
public class LOTRBlocksTables {

    static final AbstractBlock.Properties TABLE_PROPS =
            AbstractBlock.Properties.of(Material.WOOD).strength(2.5f).sound(SoundType.WOOD);

    public static void init() {
    }

    private static RegistryObject<Block> registerWithItem(String name, Supplier<Block> block) {
        RegistryObject<Block> ro = LOTRBlocks.BLOCKS.register(name, block);
        LOTRItems.ITEMS.register(name, () -> new BlockItem(ro.get(),
                new Item.Properties().tab(LOTRCreativeTabs.TAB_UTIL)));
        return ro;
    }

    public static final RegistryObject<Block> MORGUL_CRAFTING_TABLE = registerWithItem("morgul_crafting_table",
            () -> new LOTRBlockCraftingTable("morgul", TABLE_PROPS));
    public static final RegistryObject<Block> ELVEN_CRAFTING_TABLE = registerWithItem("elven_crafting_table",
            () -> new LOTRBlockCraftingTable("elven", TABLE_PROPS));
    public static final RegistryObject<Block> DWARVEN_CRAFTING_TABLE = registerWithItem("dwarven_crafting_table",
            () -> new LOTRBlockCraftingTable("dwarven", TABLE_PROPS));
    public static final RegistryObject<Block> URUK_CRAFTING_TABLE = registerWithItem("uruk_crafting_table",
            () -> new LOTRBlockCraftingTable("uruk", TABLE_PROPS));
    public static final RegistryObject<Block> WOOD_ELVEN_CRAFTING_TABLE = registerWithItem("wood_elven_crafting_table",
            () -> new LOTRBlockCraftingTable("wood_elven", TABLE_PROPS));
    public static final RegistryObject<Block> GONDORIAN_CRAFTING_TABLE = registerWithItem("gondorian_crafting_table",
            () -> new LOTRBlockCraftingTable("gondorian", TABLE_PROPS));
    public static final RegistryObject<Block> ROHIRRIC_CRAFTING_TABLE = registerWithItem("rohirric_crafting_table",
            () -> new LOTRBlockCraftingTable("rohirric", TABLE_PROPS));
    public static final RegistryObject<Block> DUNLENDING_CRAFTING_TABLE = registerWithItem("dunlending_crafting_table",
            () -> new LOTRBlockCraftingTable("dunlending", TABLE_PROPS));
    public static final RegistryObject<Block> ANGMAR_CRAFTING_TABLE = registerWithItem("angmar_crafting_table",
            () -> new LOTRBlockCraftingTable("angmar", TABLE_PROPS));
    public static final RegistryObject<Block> NEAR_HARAD_CRAFTING_TABLE = registerWithItem("near_harad_crafting_table",
            () -> new LOTRBlockCraftingTable("near_harad", TABLE_PROPS));
    public static final RegistryObject<Block> HIGH_ELVEN_CRAFTING_TABLE = registerWithItem("high_elven_crafting_table",
            () -> new LOTRBlockCraftingTable("high_elven", TABLE_PROPS));
    public static final RegistryObject<Block> BLUE_DWARVEN_CRAFTING_TABLE = registerWithItem("blue_dwarven_crafting_table",
            () -> new LOTRBlockCraftingTable("blue_dwarven", TABLE_PROPS));
    public static final RegistryObject<Block> RANGER_CRAFTING_TABLE = registerWithItem("ranger_crafting_table",
            () -> new LOTRBlockCraftingTable("ranger", TABLE_PROPS));
    public static final RegistryObject<Block> DOL_GULDUR_CRAFTING_TABLE = registerWithItem("dol_guldur_crafting_table",
            () -> new LOTRBlockCraftingTable("dol_guldur", TABLE_PROPS));
    public static final RegistryObject<Block> GUNDABAD_CRAFTING_TABLE = registerWithItem("gundabad_crafting_table",
            () -> new LOTRBlockCraftingTable("gundabad", TABLE_PROPS));
    public static final RegistryObject<Block> HALF_TROLL_CRAFTING_TABLE = registerWithItem("half_troll_crafting_table",
            () -> new LOTRBlockCraftingTable("half_troll", TABLE_PROPS));
    public static final RegistryObject<Block> DOL_AMROTH_CRAFTING_TABLE = registerWithItem("dol_amroth_crafting_table",
            () -> new LOTRBlockCraftingTable("dol_amroth", TABLE_PROPS));
    public static final RegistryObject<Block> MOREDAIN_CRAFTING_TABLE = registerWithItem("moredain_crafting_table",
            () -> new LOTRBlockCraftingTable("moredain", TABLE_PROPS));
    public static final RegistryObject<Block> TAUREDAIN_CRAFTING_TABLE = registerWithItem("tauredain_crafting_table",
            () -> new LOTRBlockCraftingTable("tauredain", TABLE_PROPS));
    public static final RegistryObject<Block> DALE_CRAFTING_TABLE = registerWithItem("dale_crafting_table",
            () -> new LOTRBlockCraftingTable("dale", TABLE_PROPS));
    public static final RegistryObject<Block> DORWINION_CRAFTING_TABLE = registerWithItem("dorwinion_crafting_table",
            () -> new LOTRBlockCraftingTable("dorwinion", TABLE_PROPS));
    public static final RegistryObject<Block> HOBBIT_CRAFTING_TABLE = registerWithItem("hobbit_crafting_table",
            () -> new LOTRBlockCraftingTable("hobbit", TABLE_PROPS));
    public static final RegistryObject<Block> RHUN_CRAFTING_TABLE = registerWithItem("rhun_crafting_table",
            () -> new LOTRBlockCraftingTable("rhun", TABLE_PROPS));
    public static final RegistryObject<Block> RIVENDELL_CRAFTING_TABLE = registerWithItem("rivendell_crafting_table",
            () -> new LOTRBlockCraftingTable("rivendell", TABLE_PROPS));
    public static final RegistryObject<Block> UMBAR_CRAFTING_TABLE = registerWithItem("umbar_crafting_table",
            () -> new LOTRBlockCraftingTable("umbar", TABLE_PROPS));
    public static final RegistryObject<Block> GULF_CRAFTING_TABLE = registerWithItem("gulf_crafting_table",
            () -> new LOTRBlockCraftingTable("gulf", TABLE_PROPS));
    public static final RegistryObject<Block> BREE_CRAFTING_TABLE = registerWithItem("bree_crafting_table",
            () -> new LOTRBlockCraftingTable("bree", TABLE_PROPS));
}
