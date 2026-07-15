package fr.alleretretour.lotr.init;

import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.StairsBlock;
import net.minecraft.block.material.Material;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraftforge.fml.RegistryObject;

import java.util.function.Supplier;

/** GENERE AUTOMATIQUEMENT - Phase 2 lot 5 : escaliers de pierre. */
public class LOTRBlocksStoneStairs {

    static final AbstractBlock.Properties STONE_PROPS =
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

    public static final RegistryObject<Block> MORDOR_BRICK_STAIRS = registerWithItem("mordor_brick_stairs",
            () -> new StairsBlock(() -> LOTRBlocksRock.MORDOR_BRICK.get().defaultBlockState(), STONE_PROPS));
    public static final RegistryObject<Block> GONDOR_BRICK_STAIRS = registerWithItem("gondor_brick_stairs",
            () -> new StairsBlock(() -> LOTRBlocksRock.GONDOR_BRICK.get().defaultBlockState(), STONE_PROPS));
    public static final RegistryObject<Block> GONDOR_MOSSY_BRICK_STAIRS = registerWithItem("gondor_mossy_brick_stairs",
            () -> new StairsBlock(() -> LOTRBlocksRock.GONDOR_MOSSY_BRICK.get().defaultBlockState(), STONE_PROPS));
    public static final RegistryObject<Block> GONDOR_CRACKED_BRICK_STAIRS = registerWithItem("gondor_cracked_brick_stairs",
            () -> new StairsBlock(() -> LOTRBlocksRock.GONDOR_CRACKED_BRICK.get().defaultBlockState(), STONE_PROPS));
    public static final RegistryObject<Block> ROHAN_BRICK_STAIRS = registerWithItem("rohan_brick_stairs",
            () -> new StairsBlock(() -> LOTRBlocksRock.ROHAN_BRICK.get().defaultBlockState(), STONE_PROPS));
    public static final RegistryObject<Block> DWARVEN_BRICK_STAIRS = registerWithItem("dwarven_brick_stairs",
            () -> new StairsBlock(() -> LOTRBlocksRock.DWARVEN_BRICK.get().defaultBlockState(), STONE_PROPS));
    public static final RegistryObject<Block> MORDOR_CRACKED_BRICK_STAIRS = registerWithItem("mordor_cracked_brick_stairs",
            () -> new StairsBlock(() -> LOTRBlocksRock.MORDOR_CRACKED_BRICK.get().defaultBlockState(), STONE_PROPS));
    public static final RegistryObject<Block> GALADHRIM_BRICK_STAIRS = registerWithItem("galadhrim_brick_stairs",
            () -> new StairsBlock(() -> LOTRBlocksRock.GALADHRIM_BRICK.get().defaultBlockState(), STONE_PROPS));
    public static final RegistryObject<Block> GALADHRIM_MOSSY_BRICK_STAIRS = registerWithItem("galadhrim_mossy_brick_stairs",
            () -> new StairsBlock(() -> LOTRBlocksRock.GALADHRIM_MOSSY_BRICK.get().defaultBlockState(), STONE_PROPS));
    public static final RegistryObject<Block> GALADHRIM_CRACKED_BRICK_STAIRS = registerWithItem("galadhrim_cracked_brick_stairs",
            () -> new StairsBlock(() -> LOTRBlocksRock.GALADHRIM_CRACKED_BRICK.get().defaultBlockState(), STONE_PROPS));
    public static final RegistryObject<Block> BLUE_ROCK_BRICK_STAIRS = registerWithItem("blue_rock_brick_stairs",
            () -> new StairsBlock(() -> LOTRBlocksRock.BLUE_ROCK_BRICK.get().defaultBlockState(), STONE_PROPS));
    public static final RegistryObject<Block> ANGMAR_BRICK_STAIRS = registerWithItem("angmar_brick_stairs",
            () -> new StairsBlock(() -> LOTRBlocksRock.ANGMAR_BRICK.get().defaultBlockState(), STONE_PROPS));
    public static final RegistryObject<Block> ANGMAR_CRACKED_BRICK_STAIRS = registerWithItem("angmar_cracked_brick_stairs",
            () -> new StairsBlock(() -> LOTRBlocksRock.ANGMAR_CRACKED_BRICK.get().defaultBlockState(), STONE_PROPS));
    public static final RegistryObject<Block> RED_ROCK_BRICK_STAIRS = registerWithItem("red_rock_brick_stairs",
            () -> new StairsBlock(() -> LOTRBlocksRock.RED_ROCK_BRICK.get().defaultBlockState(), STONE_PROPS));
    public static final RegistryObject<Block> NEAR_HARAD_BRICK_STAIRS = registerWithItem("near_harad_brick_stairs",
            () -> new StairsBlock(() -> LOTRBlocksRock.NEAR_HARAD_BRICK.get().defaultBlockState(), STONE_PROPS));
    public static final RegistryObject<Block> ARNOR_BRICK_STAIRS = registerWithItem("arnor_brick_stairs",
            () -> new StairsBlock(() -> LOTRBlocksRock.ARNOR_BRICK.get().defaultBlockState(), STONE_PROPS));
    public static final RegistryObject<Block> ARNOR_MOSSY_BRICK_STAIRS = registerWithItem("arnor_mossy_brick_stairs",
            () -> new StairsBlock(() -> LOTRBlocksRock.ARNOR_MOSSY_BRICK.get().defaultBlockState(), STONE_PROPS));
    public static final RegistryObject<Block> ARNOR_CRACKED_BRICK_STAIRS = registerWithItem("arnor_cracked_brick_stairs",
            () -> new StairsBlock(() -> LOTRBlocksRock.ARNOR_CRACKED_BRICK.get().defaultBlockState(), STONE_PROPS));
    public static final RegistryObject<Block> URUK_BRICK_STAIRS = registerWithItem("uruk_brick_stairs",
            () -> new StairsBlock(() -> LOTRBlocksRock.URUK_BRICK.get().defaultBlockState(), STONE_PROPS));
    public static final RegistryObject<Block> DOL_GULDUR_BRICK_STAIRS = registerWithItem("dol_guldur_brick_stairs",
            () -> new StairsBlock(() -> LOTRBlocksRock.DOL_GULDUR_BRICK.get().defaultBlockState(), STONE_PROPS));
    public static final RegistryObject<Block> DOL_GULDUR_CRACKED_BRICK_STAIRS = registerWithItem("dol_guldur_cracked_brick_stairs",
            () -> new StairsBlock(() -> LOTRBlocksRock.DOL_GULDUR_CRACKED_BRICK.get().defaultBlockState(), STONE_PROPS));
    public static final RegistryObject<Block> BLACK_GONDOR_BRICK_STAIRS = registerWithItem("black_gondor_brick_stairs",
            () -> new StairsBlock(() -> LOTRBlocksRock.BLACK_GONDOR_BRICK.get().defaultBlockState(), STONE_PROPS));
    public static final RegistryObject<Block> HIGH_ELVEN_BRICK_STAIRS = registerWithItem("high_elven_brick_stairs",
            () -> new StairsBlock(() -> LOTRBlocksRock.HIGH_ELVEN_BRICK.get().defaultBlockState(), STONE_PROPS));
    public static final RegistryObject<Block> HIGH_ELVEN_MOSSY_BRICK_STAIRS = registerWithItem("high_elven_mossy_brick_stairs",
            () -> new StairsBlock(() -> LOTRBlocksRock.HIGH_ELVEN_MOSSY_BRICK.get().defaultBlockState(), STONE_PROPS));
    public static final RegistryObject<Block> HIGH_ELVEN_CRACKED_BRICK_STAIRS = registerWithItem("high_elven_cracked_brick_stairs",
            () -> new StairsBlock(() -> LOTRBlocksRock.HIGH_ELVEN_CRACKED_BRICK.get().defaultBlockState(), STONE_PROPS));
    public static final RegistryObject<Block> WOOD_ELVEN_BRICK_STAIRS = registerWithItem("wood_elven_brick_stairs",
            () -> new StairsBlock(() -> LOTRBlocksRock.WOOD_ELVEN_BRICK.get().defaultBlockState(), STONE_PROPS));
    public static final RegistryObject<Block> WOOD_ELVEN_MOSSY_BRICK_STAIRS = registerWithItem("wood_elven_mossy_brick_stairs",
            () -> new StairsBlock(() -> LOTRBlocksRock.WOOD_ELVEN_MOSSY_BRICK.get().defaultBlockState(), STONE_PROPS));
    public static final RegistryObject<Block> WOOD_ELVEN_CRACKED_BRICK_STAIRS = registerWithItem("wood_elven_cracked_brick_stairs",
            () -> new StairsBlock(() -> LOTRBlocksRock.WOOD_ELVEN_CRACKED_BRICK.get().defaultBlockState(), STONE_PROPS));
    public static final RegistryObject<Block> DOL_AMROTH_BRICK_STAIRS = registerWithItem("dol_amroth_brick_stairs",
            () -> new StairsBlock(() -> LOTRBlocksRock.DOL_AMROTH_BRICK.get().defaultBlockState(), STONE_PROPS));
    public static final RegistryObject<Block> MOREDAIN_BRICK_STAIRS = registerWithItem("moredain_brick_stairs",
            () -> new StairsBlock(() -> LOTRBlocksRock.MOREDAIN_BRICK.get().defaultBlockState(), STONE_PROPS));
    public static final RegistryObject<Block> NEAR_HARAD_CRACKED_BRICK_STAIRS = registerWithItem("near_harad_cracked_brick_stairs",
            () -> new StairsBlock(() -> LOTRBlocksRock.NEAR_HARAD_CRACKED_BRICK.get().defaultBlockState(), STONE_PROPS));
    public static final RegistryObject<Block> TAUREDAIN_BRICK_STAIRS = registerWithItem("tauredain_brick_stairs",
            () -> new StairsBlock(() -> LOTRBlocksRock.TAUREDAIN_BRICK.get().defaultBlockState(), STONE_PROPS));
    public static final RegistryObject<Block> TAUREDAIN_MOSSY_BRICK_STAIRS = registerWithItem("tauredain_mossy_brick_stairs",
            () -> new StairsBlock(() -> LOTRBlocksRock.TAUREDAIN_MOSSY_BRICK.get().defaultBlockState(), STONE_PROPS));
    public static final RegistryObject<Block> TAUREDAIN_CRACKED_BRICK_STAIRS = registerWithItem("tauredain_cracked_brick_stairs",
            () -> new StairsBlock(() -> LOTRBlocksRock.TAUREDAIN_CRACKED_BRICK.get().defaultBlockState(), STONE_PROPS));
    public static final RegistryObject<Block> TAUREDAIN_GOLD_BRICK_STAIRS = registerWithItem("tauredain_gold_brick_stairs",
            () -> new StairsBlock(() -> LOTRBlocksRock.TAUREDAIN_GOLD_BRICK.get().defaultBlockState(), STONE_PROPS));
    public static final RegistryObject<Block> TAUREDAIN_OBSIDIAN_BRICK_STAIRS = registerWithItem("tauredain_obsidian_brick_stairs",
            () -> new StairsBlock(() -> LOTRBlocksRock.TAUREDAIN_OBSIDIAN_BRICK.get().defaultBlockState(), STONE_PROPS));
    public static final RegistryObject<Block> NEAR_HARAD_RED_BRICK_STAIRS = registerWithItem("near_harad_red_brick_stairs",
            () -> new StairsBlock(() -> LOTRBlocksRock.NEAR_HARAD_RED_BRICK.get().defaultBlockState(), STONE_PROPS));
    public static final RegistryObject<Block> NEAR_HARAD_RED_CRACKED_BRICK_STAIRS = registerWithItem("near_harad_red_cracked_brick_stairs",
            () -> new StairsBlock(() -> LOTRBlocksRock.NEAR_HARAD_RED_CRACKED_BRICK.get().defaultBlockState(), STONE_PROPS));
    public static final RegistryObject<Block> DWARVEN_CRACKED_BRICK_STAIRS = registerWithItem("dwarven_cracked_brick_stairs",
            () -> new StairsBlock(() -> LOTRBlocksRock.DWARVEN_CRACKED_BRICK.get().defaultBlockState(), STONE_PROPS));
    public static final RegistryObject<Block> DWARVEN_OBSIDIAN_BRICK_STAIRS = registerWithItem("dwarven_obsidian_brick_stairs",
            () -> new StairsBlock(() -> LOTRBlocksRock.DWARVEN_OBSIDIAN_BRICK.get().defaultBlockState(), STONE_PROPS));
    public static final RegistryObject<Block> CHALK_BRICK_STAIRS = registerWithItem("chalk_brick_stairs",
            () -> new StairsBlock(() -> LOTRBlocksRock.CHALK_BRICK.get().defaultBlockState(), STONE_PROPS));
    public static final RegistryObject<Block> MORDOR_ROCK_STAIRS = registerWithItem("mordor_rock_stairs",
            () -> new StairsBlock(() -> LOTRBlocksRock.MORDOR_ROCK.get().defaultBlockState(), STONE_PROPS));
    public static final RegistryObject<Block> GONDOR_ROCK_STAIRS = registerWithItem("gondor_rock_stairs",
            () -> new StairsBlock(() -> LOTRBlocksRock.GONDOR_ROCK.get().defaultBlockState(), STONE_PROPS));
    public static final RegistryObject<Block> ROHAN_ROCK_STAIRS = registerWithItem("rohan_rock_stairs",
            () -> new StairsBlock(() -> LOTRBlocksRock.ROHAN_ROCK.get().defaultBlockState(), STONE_PROPS));
    public static final RegistryObject<Block> BLUE_ROCK_STAIRS = registerWithItem("blue_rock_stairs",
            () -> new StairsBlock(() -> LOTRBlocksRock.BLUE_ROCK.get().defaultBlockState(), STONE_PROPS));
    public static final RegistryObject<Block> RED_ROCK_STAIRS = registerWithItem("red_rock_stairs",
            () -> new StairsBlock(() -> LOTRBlocksRock.RED_ROCK.get().defaultBlockState(), STONE_PROPS));
    public static final RegistryObject<Block> CHALK_ROCK_STAIRS = registerWithItem("chalk_rock_stairs",
            () -> new StairsBlock(() -> LOTRBlocksRock.CHALK_ROCK.get().defaultBlockState(), STONE_PROPS));
    public static final RegistryObject<Block> MUD_BRICK_STAIRS = registerWithItem("mud_brick_stairs",
            () -> new StairsBlock(() -> LOTRBlocksRock.MUD_BRICK.get().defaultBlockState(), STONE_PROPS));
    public static final RegistryObject<Block> DALE_BRICK_STAIRS = registerWithItem("dale_brick_stairs",
            () -> new StairsBlock(() -> LOTRBlocksRock.DALE_BRICK.get().defaultBlockState(), STONE_PROPS));
    public static final RegistryObject<Block> DORWINION_BRICK_STAIRS = registerWithItem("dorwinion_brick_stairs",
            () -> new StairsBlock(() -> LOTRBlocksRock.DORWINION_BRICK.get().defaultBlockState(), STONE_PROPS));
    public static final RegistryObject<Block> DORWINION_MOSSY_BRICK_STAIRS = registerWithItem("dorwinion_mossy_brick_stairs",
            () -> new StairsBlock(() -> LOTRBlocksRock.DORWINION_MOSSY_BRICK.get().defaultBlockState(), STONE_PROPS));
    public static final RegistryObject<Block> DORWINION_CRACKED_BRICK_STAIRS = registerWithItem("dorwinion_cracked_brick_stairs",
            () -> new StairsBlock(() -> LOTRBlocksRock.DORWINION_CRACKED_BRICK.get().defaultBlockState(), STONE_PROPS));
    public static final RegistryObject<Block> DORWINION_FLOWERS_BRICK_STAIRS = registerWithItem("dorwinion_flowers_brick_stairs",
            () -> new StairsBlock(() -> LOTRBlocksRock.DORWINION_FLOWERS_BRICK.get().defaultBlockState(), STONE_PROPS));
    public static final RegistryObject<Block> GONDOR_RUSTIC_BRICK_STAIRS = registerWithItem("gondor_rustic_brick_stairs",
            () -> new StairsBlock(() -> LOTRBlocksRock.GONDOR_RUSTIC_BRICK.get().defaultBlockState(), STONE_PROPS));
    public static final RegistryObject<Block> GONDOR_RUSTIC_MOSSY_BRICK_STAIRS = registerWithItem("gondor_rustic_mossy_brick_stairs",
            () -> new StairsBlock(() -> LOTRBlocksRock.GONDOR_RUSTIC_MOSSY_BRICK.get().defaultBlockState(), STONE_PROPS));
    public static final RegistryObject<Block> GONDOR_RUSTIC_CRACKED_BRICK_STAIRS = registerWithItem("gondor_rustic_cracked_brick_stairs",
            () -> new StairsBlock(() -> LOTRBlocksRock.GONDOR_RUSTIC_CRACKED_BRICK.get().defaultBlockState(), STONE_PROPS));
    public static final RegistryObject<Block> RHUN_BRICK_STAIRS = registerWithItem("rhun_brick_stairs",
            () -> new StairsBlock(() -> LOTRBlocksRock.RHUN_BRICK.get().defaultBlockState(), STONE_PROPS));
    public static final RegistryObject<Block> RHUN_MOSSY_BRICK_STAIRS = registerWithItem("rhun_mossy_brick_stairs",
            () -> new StairsBlock(() -> LOTRBlocksRock.RHUN_MOSSY_BRICK.get().defaultBlockState(), STONE_PROPS));
    public static final RegistryObject<Block> RHUN_CRACKED_BRICK_STAIRS = registerWithItem("rhun_cracked_brick_stairs",
            () -> new StairsBlock(() -> LOTRBlocksRock.RHUN_CRACKED_BRICK.get().defaultBlockState(), STONE_PROPS));
    public static final RegistryObject<Block> RHUN_FLOWERS_BRICK_STAIRS = registerWithItem("rhun_flowers_brick_stairs",
            () -> new StairsBlock(() -> LOTRBlocksRock.RHUN_FLOWERS_BRICK.get().defaultBlockState(), STONE_PROPS));
    public static final RegistryObject<Block> RHUN_RED_BRICK_STAIRS = registerWithItem("rhun_red_brick_stairs",
            () -> new StairsBlock(() -> LOTRBlocksRock.RHUN_RED_BRICK.get().defaultBlockState(), STONE_PROPS));
    public static final RegistryObject<Block> DALE_MOSSY_BRICK_STAIRS = registerWithItem("dale_mossy_brick_stairs",
            () -> new StairsBlock(() -> LOTRBlocksRock.DALE_MOSSY_BRICK.get().defaultBlockState(), STONE_PROPS));
    public static final RegistryObject<Block> DALE_CRACKED_BRICK_STAIRS = registerWithItem("dale_cracked_brick_stairs",
            () -> new StairsBlock(() -> LOTRBlocksRock.DALE_CRACKED_BRICK.get().defaultBlockState(), STONE_PROPS));
    public static final RegistryObject<Block> UMBAR_BRICK_STAIRS = registerWithItem("umbar_brick_stairs",
            () -> new StairsBlock(() -> LOTRBlocksRock.UMBAR_BRICK.get().defaultBlockState(), STONE_PROPS));
    public static final RegistryObject<Block> UMBAR_CRACKED_BRICK_STAIRS = registerWithItem("umbar_cracked_brick_stairs",
            () -> new StairsBlock(() -> LOTRBlocksRock.UMBAR_CRACKED_BRICK.get().defaultBlockState(), STONE_PROPS));
    public static final RegistryObject<Block> ANGMAR_SNOW_BRICK_STAIRS = registerWithItem("angmar_snow_brick_stairs",
            () -> new StairsBlock(() -> LOTRBlocksRock.ANGMAR_SNOW_BRICK.get().defaultBlockState(), STONE_PROPS));
    public static final RegistryObject<Block> DOL_GULDUR_MOSSY_BRICK_STAIRS = registerWithItem("dol_guldur_mossy_brick_stairs",
            () -> new StairsBlock(() -> LOTRBlocksRock.DOL_GULDUR_MOSSY_BRICK.get().defaultBlockState(), STONE_PROPS));
    public static final RegistryObject<Block> MORWAITH_CRACKED_BRICK_STAIRS = registerWithItem("morwaith_cracked_brick_stairs",
            () -> new StairsBlock(() -> LOTRBlocksRock.MORWAITH_CRACKED_BRICK.get().defaultBlockState(), STONE_PROPS));
}
