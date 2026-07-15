package fr.alleretretour.lotr.init;

import fr.alleretretour.lotr.block.LOTRBlockBarrel;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraftforge.fml.RegistryObject;

/** Phase 2 lot 11 : le tonneau de brassage. */
public class LOTRBlocksBarrel {

    public static final RegistryObject<Block> BARREL = LOTRBlocks.BLOCKS.register("barrel",
            () -> new LOTRBlockBarrel(AbstractBlock.Properties.of(Material.WOOD)
                    .strength(2.5f).sound(SoundType.WOOD).noOcclusion()));

    public static final RegistryObject<Item> BARREL_ITEM = LOTRItems.ITEMS.register("barrel",
            () -> new BlockItem(BARREL.get(), new Item.Properties().tab(LOTRCreativeTabs.TAB_UTIL)));

    public static void init() {
    }
}
