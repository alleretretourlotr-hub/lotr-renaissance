package fr.alleretretour.lotr.init;

import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraftforge.fml.RegistryObject;

import java.util.function.Supplier;

/** PORT de blockOreStorage (Legacy, metas 2/3) : blocs de bronze et d'argent.
 *  Utilises comme socles de protection des bannieres (portees 8 / 16). */
public class LOTRBlocksStorage {

    static final AbstractBlock.Properties METAL_PROPS =
            AbstractBlock.Properties.of(Material.METAL).requiresCorrectToolForDrops()
                    .strength(5.0f, 6.0f).sound(SoundType.METAL);

    public static void init() {
    }

    private static RegistryObject<Block> registerWithItem(String name, Supplier<Block> block) {
        RegistryObject<Block> ro = LOTRBlocks.BLOCKS.register(name, block);
        LOTRItems.ITEMS.register(name, () -> new BlockItem(ro.get(),
                new Item.Properties().tab(LOTRCreativeTabs.TAB_BLOCKS)));
        return ro;
    }

    public static final RegistryObject<Block> BRONZE_BLOCK = registerWithItem("bronze_block",
            () -> new Block(METAL_PROPS));
    public static final RegistryObject<Block> SILVER_BLOCK = registerWithItem("silver_block",
            () -> new Block(METAL_PROPS));
}
