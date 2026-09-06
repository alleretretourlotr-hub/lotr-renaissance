package fr.alleretretour.lotr.init;

import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraftforge.fml.RegistryObject;

import java.util.function.Supplier;

/**
 * PORT des blocs FONCTIONNELS utilises par les structures du Legacy.
 *
 * Valeurs reprises telles quelles :
 *   hearth        : durete 1.0, resistance 8.0, son de pierre - l'atre des
 *                   foyers (obelisque du Harad, campements, sanctuaires) ;
 *   guldurilBrick : durete 3.0, resistance 10.0, LUMIERE 12 (0.75 x 16 dans
 *                   le Legacy) - la brique luisante de Dol Guldur ;
 *   beacon        : durete 0.0, resistance 5.0, son de bois - le feu d'alarme
 *                   du Gondor (l'allumage en chaine viendra avec sa
 *                   TileEntity, ici c'est le bloc) ;
 *   angmarTable   : table d'artisanat d'Angmar (LOTRBlockCraftingTable) ;
 *   chestBasket   : le panier a butin des campements.
 *
 * Ces cinq blocs manquaient a l'appel : les structures deja portees les
 * resolvent par nom et retombaient jusqu'ici sur des blocs vanilla.
 */
public final class LOTRBlocksStructure {

    private static final AbstractBlock.Properties HEARTH_PROPS =
            AbstractBlock.Properties.of(Material.STONE).requiresCorrectToolForDrops()
                    .strength(1.0f, 8.0f).sound(SoundType.STONE);

    private static final AbstractBlock.Properties GULDURIL_PROPS =
            AbstractBlock.Properties.of(Material.STONE).requiresCorrectToolForDrops()
                    .strength(3.0f, 10.0f).sound(SoundType.STONE)
                    .lightLevel(state -> 12);

    private static final AbstractBlock.Properties BEACON_PROPS =
            AbstractBlock.Properties.of(Material.WOOD).strength(0.0f, 5.0f)
                    .sound(SoundType.WOOD);

    private static final AbstractBlock.Properties WOOD_PROPS =
            AbstractBlock.Properties.of(Material.WOOD).strength(2.5f).sound(SoundType.WOOD);

    private LOTRBlocksStructure() {
    }

    public static void init() {
    }

    private static RegistryObject<Block> reg(String name, Supplier<Block> block) {
        RegistryObject<Block> ro = LOTRBlocks.BLOCKS.register(name, block);
        LOTRItems.ITEMS.register(name, () -> new BlockItem(ro.get(),
                new Item.Properties().tab(LOTRCreativeTabs.TAB_UTIL)));
        return ro;
    }

    /** PORT de LOTRBlockHearth : l'atre sur lequel brule le feu. */
    public static final RegistryObject<Block> HEARTH =
            reg("hearth", () -> new Block(HEARTH_PROPS));

    /** PORT de LOTRBlockGuldurilBrick : brique luisante de Dol Guldur. */
    public static final RegistryObject<Block> GULDURIL_BRICK =
            reg("gulduril_brick", () -> new Block(GULDURIL_PROPS));

    /** PORT de LOTRBlockBeacon : le feu d'alarme du Gondor. */
    public static final RegistryObject<Block> BEACON =
            reg("beacon", () -> new Block(BEACON_PROPS));

    /** PORT de LOTRBlockAngmarTable : table d'artisanat d'Angmar. */
    public static final RegistryObject<Block> ANGMAR_TABLE =
            reg("angmar_table", () -> new Block(WOOD_PROPS));

    /** PORT de LOTRBlockChestBasket : le panier a butin des campements. */
    public static final RegistryObject<Block> CHEST_BASKET =
            reg("chest_basket", () -> new Block(
                    AbstractBlock.Properties.of(Material.WOOD).strength(2.5f)
                            .sound(SoundType.GRASS)));
}
