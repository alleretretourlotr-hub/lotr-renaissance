package fr.alleretretour.lotr.init;

import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.RotatedPillarBlock;
import net.minecraft.block.SoundType;
import net.minecraft.block.StairsBlock;
import net.minecraft.block.material.Material;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraftforge.fml.RegistryObject;

import java.util.function.Supplier;

/**
 * PORT des blocs des RUINES : ceux qui manquaient aux maisons abandonnees.
 *
 * Valeurs du Legacy :
 *   rottenLog     : bois pourri, proprietes du bois (LOTRBlockWoodBase) ;
 *   stairsRotten  : escalier de planches pourries ;
 *   scorchedStone : pierre roussie, durete 2.0, resistance 10.0 - la pierre
 *                   des maisons brulees et des terres devastees.
 */
public final class LOTRBlocksRuins {

    private static final AbstractBlock.Properties WOOD_PROPS =
            AbstractBlock.Properties.of(Material.WOOD).strength(2.0f).sound(SoundType.WOOD);

    private static final AbstractBlock.Properties STONE_PROPS =
            AbstractBlock.Properties.of(Material.STONE).requiresCorrectToolForDrops()
                    .strength(2.0f, 10.0f).sound(SoundType.STONE);

    private LOTRBlocksRuins() {
    }

    public static void init() {
    }

    private static RegistryObject<Block> reg(String name, Supplier<Block> block) {
        RegistryObject<Block> ro = LOTRBlocks.BLOCKS.register(name, block);
        LOTRItems.ITEMS.register(name, () -> new BlockItem(ro.get(),
                new Item.Properties().tab(LOTRCreativeTabs.TAB_BLOCKS)));
        return ro;
    }

    /** PORT de LOTRBlockRottenLog : la buche pourrie. */
    public static final RegistryObject<Block> ROTTEN_LOG =
            reg("rotten_log", () -> new RotatedPillarBlock(WOOD_PROPS));

    /** PORT de stairsRotten : l'escalier de planches pourries. */
    public static final RegistryObject<Block> ROTTEN_STAIRS =
            reg("rotten_stairs", () -> new StairsBlock(
                    () -> LOTRBlocksWood.ROTTEN_PLANKS.get().defaultBlockState(), WOOD_PROPS));

    /** PORT de LOTRBlockScorchedStone : la pierre roussie. */
    public static final RegistryObject<Block> SCORCHED_STONE =
            reg("scorched_stone", () -> new Block(STONE_PROPS));
}
