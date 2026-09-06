package fr.alleretretour.lotr.init;

import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.FallingBlock;
import net.minecraft.block.OreBlock;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.material.MaterialColor;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.util.math.MathHelper;
import net.minecraftforge.fml.RegistryObject;

import java.util.Random;
import java.util.function.Supplier;

/**
 * PORT des blocs de TERRAIN du Legacy (lotr.common.block).
 *
 * Les blocs a metadonnees du 1.7.10 deviennent un bloc par variante :
 *   - LOTRBlockDirtPath (metas dirt / mud) -> dirt_path, mud_path ;
 *   - LOTRBlockOreGem (8 gemmes)          -> topaz_ore ... emerald_ore.
 *
 * Duretes, resistances et sons repris tels quels du Legacy :
 *   mordorDirt / mud / dirtPath / thatch / whiteSand : 0.5
 *   mordorGravel : 0.6   mudGrass : 0.6   clayTile : 1.25
 *   oreGem : 3.0 / 5.0   oreSalt : 3.0 / 5.0
 *
 * Ces blocs sont ceux que la generation du monde resout par nom
 * (LOTRBiomes, LOTRBiomeOres, LOTRRoadGenerator) : des qu'ils existent, les
 * surfaces du Mordor, les mangroves, les routes et les gemmes apparaissent.
 */
public final class LOTRBlocksTerrain {

    // ---- proprietes, valeurs exactes du Legacy ----
    private static final AbstractBlock.Properties DIRT_PROPS =
            AbstractBlock.Properties.of(Material.DIRT).strength(0.5f).sound(SoundType.GRAVEL);
    private static final AbstractBlock.Properties GRAVEL_PROPS =
            AbstractBlock.Properties.of(Material.SAND).strength(0.6f).sound(SoundType.GRAVEL);
    private static final AbstractBlock.Properties SAND_PROPS =
            AbstractBlock.Properties.of(Material.SAND, MaterialColor.SAND)
                    .strength(0.5f).sound(SoundType.SAND);
    private static final AbstractBlock.Properties GRASS_PROPS =
            AbstractBlock.Properties.of(Material.GRASS).strength(0.6f).sound(SoundType.GRASS);
    private static final AbstractBlock.Properties THATCH_PROPS =
            AbstractBlock.Properties.of(Material.GRASS).strength(0.5f).sound(SoundType.GRASS);
    private static final AbstractBlock.Properties TILE_PROPS =
            AbstractBlock.Properties.of(Material.STONE).requiresCorrectToolForDrops()
                    .strength(1.25f).sound(SoundType.STONE);
    private static final AbstractBlock.Properties ORE_PROPS =
            AbstractBlock.Properties.of(Material.STONE).requiresCorrectToolForDrops()
                    .strength(3.0f, 5.0f).sound(SoundType.STONE);

    private LOTRBlocksTerrain() {
    }

    public static void init() {
    }

    private static RegistryObject<Block> reg(String name, Supplier<Block> block) {
        RegistryObject<Block> ro = LOTRBlocks.BLOCKS.register(name, block);
        LOTRItems.ITEMS.register(name, () -> new BlockItem(ro.get(),
                new Item.Properties().tab(LOTRCreativeTabs.TAB_BLOCKS)));
        return ro;
    }

    // ================== sols ==================

    /** PORT de LOTRBlockMordorDirt : la terre stérile du Mordor. */
    public static final RegistryObject<Block> MORDOR_DIRT =
            reg("mordor_dirt", () -> new Block(DIRT_PROPS));

    /** PORT de LOTRBlockGravel (mordorGravel) : tombe comme le gravier. */
    public static final RegistryObject<Block> MORDOR_GRAVEL =
            reg("mordor_gravel", () -> new FallingBlock(GRAVEL_PROPS) {
                @Override
                public int getDustColor(net.minecraft.block.BlockState state,
                                        net.minecraft.world.IBlockReader reader,
                                        net.minecraft.util.math.BlockPos pos) {
                    return 0x4C4C4C;
                }
            });

    /** PORT de LOTRBlockSand (whiteSand) : le sable blanc du Harad. */
    public static final RegistryObject<Block> WHITE_SAND =
            reg("white_sand", () -> new FallingBlock(SAND_PROPS) {
                @Override
                public int getDustColor(net.minecraft.block.BlockState state,
                                        net.minecraft.world.IBlockReader reader,
                                        net.minecraft.util.math.BlockPos pos) {
                    return 0xE8E4D8;
                }
            });

    /** PORT de LOTRBlockMud : la vase des marais. */
    public static final RegistryObject<Block> MUD =
            reg("mud", () -> new Block(DIRT_PROPS));

    /** PORT de LOTRBlockMudGrass : vase herbue, surface des mangroves. */
    public static final RegistryObject<Block> MUD_GRASS =
            reg("mud_grass", () -> new Block(GRASS_PROPS));

    /** PORT de LOTRBlockDirtPath, meta "dirt" : le chemin des routes. */
    public static final RegistryObject<Block> DIRT_PATH =
            reg("dirt_path", () -> new Block(DIRT_PROPS));

    /** PORT de LOTRBlockDirtPath, meta "mud". */
    public static final RegistryObject<Block> MUD_PATH =
            reg("mud_path", () -> new Block(DIRT_PROPS));

    /**
     * PORT de LOTRBlockQuagmire : le bourbier. Dans le Legacy il ralentit et
     * enlise ; ici on reprend sa faible durete et son absence de collision
     * pleine (l'enlisement demandera un bloc dedie, note pour plus tard).
     */
    public static final RegistryObject<Block> QUAGMIRE =
            reg("quagmire", () -> new Block(
                    AbstractBlock.Properties.of(Material.DIRT).strength(1.0f)
                            .sound(SoundType.GRAVEL).speedFactor(0.4f)));

    /** PORT de LOTRBlockThatch : le chaume des toitures. */
    public static final RegistryObject<Block> THATCH =
            reg("thatch", () -> new Block(THATCH_PROPS));

    /** PORT de LOTRBlockClayTile : la tuile de terre cuite. */
    public static final RegistryObject<Block> CLAY_TILE =
            reg("clay_tile", () -> new Block(TILE_PROPS));

    // ================== minerais ==================
    // PORT de LOTRBlockOreGem : un bloc par gemme (metas 0-7 du Legacy).

    public static final RegistryObject<Block> TOPAZ_ORE =
            reg("topaz_ore", () -> gemOre(2, 5));
    public static final RegistryObject<Block> AMETHYST_ORE =
            reg("amethyst_ore", () -> gemOre(2, 5));
    public static final RegistryObject<Block> SAPPHIRE_ORE =
            reg("sapphire_ore", () -> gemOre(2, 5));
    public static final RegistryObject<Block> RUBY_ORE =
            reg("ruby_ore", () -> gemOre(3, 7));
    public static final RegistryObject<Block> AMBER_ORE =
            reg("amber_ore", () -> gemOre(2, 5));
    public static final RegistryObject<Block> DIAMOND_ORE =
            reg("lotr_diamond_ore", () -> gemOre(3, 7));
    public static final RegistryObject<Block> OPAL_ORE =
            reg("opal_ore", () -> gemOre(2, 5));
    public static final RegistryObject<Block> LOTR_EMERALD_ORE =
            reg("lotr_emerald_ore", () -> gemOre(3, 7));

    /** PORT de LOTRBlockOre (oreSalt). */
    public static final RegistryObject<Block> SALT_ORE =
            reg("salt_ore", () -> new OreBlock(ORE_PROPS));

    /** Minerai de gemme : experience laachee entre min et max, comme le Legacy. */
    private static OreBlock gemOre(int xpMin, int xpMax) {
        return new OreBlock(ORE_PROPS) {
            @Override
            protected int xpOnDrop(Random random) {
                return MathHelper.nextInt(random, xpMin, xpMax);
            }
        };
    }
}
