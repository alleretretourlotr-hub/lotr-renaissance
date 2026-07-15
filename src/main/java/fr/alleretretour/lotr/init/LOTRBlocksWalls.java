package fr.alleretretour.lotr.init;

import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.SlabBlock;
import net.minecraft.block.SoundType;
import net.minecraft.block.WallBlock;
import net.minecraft.block.material.Material;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraftforge.fml.RegistryObject;

import java.util.function.Supplier;

/** GENERE AUTOMATIQUEMENT - Phase 2 lot 6 : murets et dalles de pierre. */
public class LOTRBlocksWalls {

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

    public static final RegistryObject<Block> MORDOR_ROCK_WALL = registerWithItem("mordor_rock_wall",
            () -> new WallBlock(STONE_PROPS));
    public static final RegistryObject<Block> MORDOR_BRICK_WALL = registerWithItem("mordor_brick_wall",
            () -> new WallBlock(STONE_PROPS));
    public static final RegistryObject<Block> GONDOR_ROCK_WALL = registerWithItem("gondor_rock_wall",
            () -> new WallBlock(STONE_PROPS));
    public static final RegistryObject<Block> GONDOR_BRICK_WALL = registerWithItem("gondor_brick_wall",
            () -> new WallBlock(STONE_PROPS));
    public static final RegistryObject<Block> GONDOR_MOSSY_BRICK_WALL = registerWithItem("gondor_mossy_brick_wall",
            () -> new WallBlock(STONE_PROPS));
    public static final RegistryObject<Block> GONDOR_CRACKED_BRICK_WALL = registerWithItem("gondor_cracked_brick_wall",
            () -> new WallBlock(STONE_PROPS));
    public static final RegistryObject<Block> ROHAN_BRICK_WALL = registerWithItem("rohan_brick_wall",
            () -> new WallBlock(STONE_PROPS));
    public static final RegistryObject<Block> DWARVEN_BRICK_WALL = registerWithItem("dwarven_brick_wall",
            () -> new WallBlock(STONE_PROPS));
    public static final RegistryObject<Block> ROHAN_ROCK_WALL = registerWithItem("rohan_rock_wall",
            () -> new WallBlock(STONE_PROPS));
    public static final RegistryObject<Block> MORDOR_CRACKED_BRICK_WALL = registerWithItem("mordor_cracked_brick_wall",
            () -> new WallBlock(STONE_PROPS));
    public static final RegistryObject<Block> GALADHRIM_BRICK_WALL = registerWithItem("galadhrim_brick_wall",
            () -> new WallBlock(STONE_PROPS));
    public static final RegistryObject<Block> GALADHRIM_MOSSY_BRICK_WALL = registerWithItem("galadhrim_mossy_brick_wall",
            () -> new WallBlock(STONE_PROPS));
    public static final RegistryObject<Block> GALADHRIM_CRACKED_BRICK_WALL = registerWithItem("galadhrim_cracked_brick_wall",
            () -> new WallBlock(STONE_PROPS));
    public static final RegistryObject<Block> BLUE_ROCK_WALL = registerWithItem("blue_rock_wall",
            () -> new WallBlock(STONE_PROPS));
    public static final RegistryObject<Block> BLUE_ROCK_BRICK_WALL = registerWithItem("blue_rock_brick_wall",
            () -> new WallBlock(STONE_PROPS));
    public static final RegistryObject<Block> NEAR_HARAD_BRICK_WALL = registerWithItem("near_harad_brick_wall",
            () -> new WallBlock(STONE_PROPS));
    public static final RegistryObject<Block> ANGMAR_BRICK_WALL = registerWithItem("angmar_brick_wall",
            () -> new WallBlock(STONE_PROPS));
    public static final RegistryObject<Block> ANGMAR_CRACKED_BRICK_WALL = registerWithItem("angmar_cracked_brick_wall",
            () -> new WallBlock(STONE_PROPS));
    public static final RegistryObject<Block> RED_ROCK_WALL = registerWithItem("red_rock_wall",
            () -> new WallBlock(STONE_PROPS));
    public static final RegistryObject<Block> RED_ROCK_BRICK_WALL = registerWithItem("red_rock_brick_wall",
            () -> new WallBlock(STONE_PROPS));
    public static final RegistryObject<Block> ARNOR_BRICK_WALL = registerWithItem("arnor_brick_wall",
            () -> new WallBlock(STONE_PROPS));
    public static final RegistryObject<Block> ARNOR_MOSSY_BRICK_WALL = registerWithItem("arnor_mossy_brick_wall",
            () -> new WallBlock(STONE_PROPS));
    public static final RegistryObject<Block> ARNOR_CRACKED_BRICK_WALL = registerWithItem("arnor_cracked_brick_wall",
            () -> new WallBlock(STONE_PROPS));
    public static final RegistryObject<Block> URUK_BRICK_WALL = registerWithItem("uruk_brick_wall",
            () -> new WallBlock(STONE_PROPS));
    public static final RegistryObject<Block> DOL_GULDUR_BRICK_WALL = registerWithItem("dol_guldur_brick_wall",
            () -> new WallBlock(STONE_PROPS));
    public static final RegistryObject<Block> DOL_GULDUR_CRACKED_BRICK_WALL = registerWithItem("dol_guldur_cracked_brick_wall",
            () -> new WallBlock(STONE_PROPS));
    public static final RegistryObject<Block> BLACK_GONDOR_BRICK_WALL = registerWithItem("black_gondor_brick_wall",
            () -> new WallBlock(STONE_PROPS));
    public static final RegistryObject<Block> HIGH_ELVEN_BRICK_WALL = registerWithItem("high_elven_brick_wall",
            () -> new WallBlock(STONE_PROPS));
    public static final RegistryObject<Block> HIGH_ELVEN_MOSSY_BRICK_WALL = registerWithItem("high_elven_mossy_brick_wall",
            () -> new WallBlock(STONE_PROPS));
    public static final RegistryObject<Block> HIGH_ELVEN_CRACKED_BRICK_WALL = registerWithItem("high_elven_cracked_brick_wall",
            () -> new WallBlock(STONE_PROPS));
    public static final RegistryObject<Block> DOL_AMROTH_BRICK_WALL = registerWithItem("dol_amroth_brick_wall",
            () -> new WallBlock(STONE_PROPS));
    public static final RegistryObject<Block> MOREDAIN_BRICK_WALL = registerWithItem("moredain_brick_wall",
            () -> new WallBlock(STONE_PROPS));
    public static final RegistryObject<Block> WOOD_ELVEN_BRICK_WALL = registerWithItem("wood_elven_brick_wall",
            () -> new WallBlock(STONE_PROPS));
    public static final RegistryObject<Block> WOOD_ELVEN_MOSSY_BRICK_WALL = registerWithItem("wood_elven_mossy_brick_wall",
            () -> new WallBlock(STONE_PROPS));
    public static final RegistryObject<Block> WOOD_ELVEN_CRACKED_BRICK_WALL = registerWithItem("wood_elven_cracked_brick_wall",
            () -> new WallBlock(STONE_PROPS));
    public static final RegistryObject<Block> NEAR_HARAD_CRACKED_BRICK_WALL = registerWithItem("near_harad_cracked_brick_wall",
            () -> new WallBlock(STONE_PROPS));
    public static final RegistryObject<Block> NEAR_HARAD_RED_BRICK_WALL = registerWithItem("near_harad_red_brick_wall",
            () -> new WallBlock(STONE_PROPS));
    public static final RegistryObject<Block> NEAR_HARAD_RED_CRACKED_BRICK_WALL = registerWithItem("near_harad_red_cracked_brick_wall",
            () -> new WallBlock(STONE_PROPS));
    public static final RegistryObject<Block> CHALK_ROCK_WALL = registerWithItem("chalk_rock_wall",
            () -> new WallBlock(STONE_PROPS));
    public static final RegistryObject<Block> CHALK_BRICK_WALL = registerWithItem("chalk_brick_wall",
            () -> new WallBlock(STONE_PROPS));
    public static final RegistryObject<Block> MUD_BRICK_WALL = registerWithItem("mud_brick_wall",
            () -> new WallBlock(STONE_PROPS));
    public static final RegistryObject<Block> DALE_BRICK_WALL = registerWithItem("dale_brick_wall",
            () -> new WallBlock(STONE_PROPS));
    public static final RegistryObject<Block> DORWINION_BRICK_WALL = registerWithItem("dorwinion_brick_wall",
            () -> new WallBlock(STONE_PROPS));
    public static final RegistryObject<Block> DORWINION_MOSSY_BRICK_WALL = registerWithItem("dorwinion_mossy_brick_wall",
            () -> new WallBlock(STONE_PROPS));
    public static final RegistryObject<Block> DORWINION_CRACKED_BRICK_WALL = registerWithItem("dorwinion_cracked_brick_wall",
            () -> new WallBlock(STONE_PROPS));
    public static final RegistryObject<Block> DORWINION_FLOWERS_BRICK_WALL = registerWithItem("dorwinion_flowers_brick_wall",
            () -> new WallBlock(STONE_PROPS));
    public static final RegistryObject<Block> RHUN_BRICK_WALL = registerWithItem("rhun_brick_wall",
            () -> new WallBlock(STONE_PROPS));
    public static final RegistryObject<Block> TAUREDAIN_BRICK_WALL = registerWithItem("tauredain_brick_wall",
            () -> new WallBlock(STONE_PROPS));
    public static final RegistryObject<Block> TAUREDAIN_MOSSY_BRICK_WALL = registerWithItem("tauredain_mossy_brick_wall",
            () -> new WallBlock(STONE_PROPS));
    public static final RegistryObject<Block> TAUREDAIN_CRACKED_BRICK_WALL = registerWithItem("tauredain_cracked_brick_wall",
            () -> new WallBlock(STONE_PROPS));
    public static final RegistryObject<Block> TAUREDAIN_GOLD_BRICK_WALL = registerWithItem("tauredain_gold_brick_wall",
            () -> new WallBlock(STONE_PROPS));
    public static final RegistryObject<Block> TAUREDAIN_OBSIDIAN_BRICK_WALL = registerWithItem("tauredain_obsidian_brick_wall",
            () -> new WallBlock(STONE_PROPS));
    public static final RegistryObject<Block> DWARVEN_CRACKED_BRICK_WALL = registerWithItem("dwarven_cracked_brick_wall",
            () -> new WallBlock(STONE_PROPS));
    public static final RegistryObject<Block> DWARVEN_OBSIDIAN_BRICK_WALL = registerWithItem("dwarven_obsidian_brick_wall",
            () -> new WallBlock(STONE_PROPS));
    public static final RegistryObject<Block> GONDOR_RUSTIC_BRICK_WALL = registerWithItem("gondor_rustic_brick_wall",
            () -> new WallBlock(STONE_PROPS));
    public static final RegistryObject<Block> GONDOR_RUSTIC_MOSSY_BRICK_WALL = registerWithItem("gondor_rustic_mossy_brick_wall",
            () -> new WallBlock(STONE_PROPS));
    public static final RegistryObject<Block> GONDOR_RUSTIC_CRACKED_BRICK_WALL = registerWithItem("gondor_rustic_cracked_brick_wall",
            () -> new WallBlock(STONE_PROPS));
    public static final RegistryObject<Block> RHUN_MOSSY_BRICK_WALL = registerWithItem("rhun_mossy_brick_wall",
            () -> new WallBlock(STONE_PROPS));
    public static final RegistryObject<Block> RHUN_CRACKED_BRICK_WALL = registerWithItem("rhun_cracked_brick_wall",
            () -> new WallBlock(STONE_PROPS));
    public static final RegistryObject<Block> RHUN_FLOWERS_BRICK_WALL = registerWithItem("rhun_flowers_brick_wall",
            () -> new WallBlock(STONE_PROPS));
    public static final RegistryObject<Block> RHUN_RED_BRICK_WALL = registerWithItem("rhun_red_brick_wall",
            () -> new WallBlock(STONE_PROPS));
    public static final RegistryObject<Block> DALE_MOSSY_BRICK_WALL = registerWithItem("dale_mossy_brick_wall",
            () -> new WallBlock(STONE_PROPS));
    public static final RegistryObject<Block> DALE_CRACKED_BRICK_WALL = registerWithItem("dale_cracked_brick_wall",
            () -> new WallBlock(STONE_PROPS));
    public static final RegistryObject<Block> UMBAR_BRICK_WALL = registerWithItem("umbar_brick_wall",
            () -> new WallBlock(STONE_PROPS));
    public static final RegistryObject<Block> UMBAR_CRACKED_BRICK_WALL = registerWithItem("umbar_cracked_brick_wall",
            () -> new WallBlock(STONE_PROPS));
    public static final RegistryObject<Block> ANGMAR_SNOW_BRICK_WALL = registerWithItem("angmar_snow_brick_wall",
            () -> new WallBlock(STONE_PROPS));
    public static final RegistryObject<Block> DOL_GULDUR_MOSSY_BRICK_WALL = registerWithItem("dol_guldur_mossy_brick_wall",
            () -> new WallBlock(STONE_PROPS));
    public static final RegistryObject<Block> MORWAITH_CRACKED_BRICK_WALL = registerWithItem("morwaith_cracked_brick_wall",
            () -> new WallBlock(STONE_PROPS));
    public static final RegistryObject<Block> MORDOR_BRICK_SLAB = registerWithItem("mordor_brick_slab",
            () -> new SlabBlock(STONE_PROPS));
    public static final RegistryObject<Block> GONDOR_BRICK_SLAB = registerWithItem("gondor_brick_slab",
            () -> new SlabBlock(STONE_PROPS));
    public static final RegistryObject<Block> GONDOR_MOSSY_BRICK_SLAB = registerWithItem("gondor_mossy_brick_slab",
            () -> new SlabBlock(STONE_PROPS));
    public static final RegistryObject<Block> GONDOR_CRACKED_BRICK_SLAB = registerWithItem("gondor_cracked_brick_slab",
            () -> new SlabBlock(STONE_PROPS));
    public static final RegistryObject<Block> ROHAN_BRICK_SLAB = registerWithItem("rohan_brick_slab",
            () -> new SlabBlock(STONE_PROPS));
    public static final RegistryObject<Block> DWARVEN_BRICK_SLAB = registerWithItem("dwarven_brick_slab",
            () -> new SlabBlock(STONE_PROPS));
    public static final RegistryObject<Block> MORDOR_CRACKED_BRICK_SLAB = registerWithItem("mordor_cracked_brick_slab",
            () -> new SlabBlock(STONE_PROPS));
    public static final RegistryObject<Block> GALADHRIM_BRICK_SLAB = registerWithItem("galadhrim_brick_slab",
            () -> new SlabBlock(STONE_PROPS));
    public static final RegistryObject<Block> GALADHRIM_MOSSY_BRICK_SLAB = registerWithItem("galadhrim_mossy_brick_slab",
            () -> new SlabBlock(STONE_PROPS));
    public static final RegistryObject<Block> GALADHRIM_CRACKED_BRICK_SLAB = registerWithItem("galadhrim_cracked_brick_slab",
            () -> new SlabBlock(STONE_PROPS));
    public static final RegistryObject<Block> BLUE_ROCK_BRICK_SLAB = registerWithItem("blue_rock_brick_slab",
            () -> new SlabBlock(STONE_PROPS));
    public static final RegistryObject<Block> ANGMAR_BRICK_SLAB = registerWithItem("angmar_brick_slab",
            () -> new SlabBlock(STONE_PROPS));
    public static final RegistryObject<Block> ANGMAR_CRACKED_BRICK_SLAB = registerWithItem("angmar_cracked_brick_slab",
            () -> new SlabBlock(STONE_PROPS));
    public static final RegistryObject<Block> RED_ROCK_BRICK_SLAB = registerWithItem("red_rock_brick_slab",
            () -> new SlabBlock(STONE_PROPS));
    public static final RegistryObject<Block> ARNOR_BRICK_SLAB = registerWithItem("arnor_brick_slab",
            () -> new SlabBlock(STONE_PROPS));
    public static final RegistryObject<Block> ARNOR_MOSSY_BRICK_SLAB = registerWithItem("arnor_mossy_brick_slab",
            () -> new SlabBlock(STONE_PROPS));
    public static final RegistryObject<Block> ARNOR_CRACKED_BRICK_SLAB = registerWithItem("arnor_cracked_brick_slab",
            () -> new SlabBlock(STONE_PROPS));
    public static final RegistryObject<Block> URUK_BRICK_SLAB = registerWithItem("uruk_brick_slab",
            () -> new SlabBlock(STONE_PROPS));
    public static final RegistryObject<Block> DOL_GULDUR_BRICK_SLAB = registerWithItem("dol_guldur_brick_slab",
            () -> new SlabBlock(STONE_PROPS));
    public static final RegistryObject<Block> DOL_GULDUR_CRACKED_BRICK_SLAB = registerWithItem("dol_guldur_cracked_brick_slab",
            () -> new SlabBlock(STONE_PROPS));
    public static final RegistryObject<Block> BLACK_GONDOR_BRICK_SLAB = registerWithItem("black_gondor_brick_slab",
            () -> new SlabBlock(STONE_PROPS));
    public static final RegistryObject<Block> HIGH_ELVEN_BRICK_SLAB = registerWithItem("high_elven_brick_slab",
            () -> new SlabBlock(STONE_PROPS));
    public static final RegistryObject<Block> HIGH_ELVEN_MOSSY_BRICK_SLAB = registerWithItem("high_elven_mossy_brick_slab",
            () -> new SlabBlock(STONE_PROPS));
    public static final RegistryObject<Block> HIGH_ELVEN_CRACKED_BRICK_SLAB = registerWithItem("high_elven_cracked_brick_slab",
            () -> new SlabBlock(STONE_PROPS));
    public static final RegistryObject<Block> WOOD_ELVEN_BRICK_SLAB = registerWithItem("wood_elven_brick_slab",
            () -> new SlabBlock(STONE_PROPS));
    public static final RegistryObject<Block> WOOD_ELVEN_MOSSY_BRICK_SLAB = registerWithItem("wood_elven_mossy_brick_slab",
            () -> new SlabBlock(STONE_PROPS));
    public static final RegistryObject<Block> WOOD_ELVEN_CRACKED_BRICK_SLAB = registerWithItem("wood_elven_cracked_brick_slab",
            () -> new SlabBlock(STONE_PROPS));
    public static final RegistryObject<Block> DOL_AMROTH_BRICK_SLAB = registerWithItem("dol_amroth_brick_slab",
            () -> new SlabBlock(STONE_PROPS));
    public static final RegistryObject<Block> NEAR_HARAD_CRACKED_BRICK_SLAB = registerWithItem("near_harad_cracked_brick_slab",
            () -> new SlabBlock(STONE_PROPS));
    public static final RegistryObject<Block> NEAR_HARAD_RED_BRICK_SLAB = registerWithItem("near_harad_red_brick_slab",
            () -> new SlabBlock(STONE_PROPS));
    public static final RegistryObject<Block> NEAR_HARAD_RED_CRACKED_BRICK_SLAB = registerWithItem("near_harad_red_cracked_brick_slab",
            () -> new SlabBlock(STONE_PROPS));
    public static final RegistryObject<Block> DWARVEN_CRACKED_BRICK_SLAB = registerWithItem("dwarven_cracked_brick_slab",
            () -> new SlabBlock(STONE_PROPS));
    public static final RegistryObject<Block> TAUREDAIN_MOSSY_BRICK_SLAB = registerWithItem("tauredain_mossy_brick_slab",
            () -> new SlabBlock(STONE_PROPS));
    public static final RegistryObject<Block> TAUREDAIN_CRACKED_BRICK_SLAB = registerWithItem("tauredain_cracked_brick_slab",
            () -> new SlabBlock(STONE_PROPS));
    public static final RegistryObject<Block> TAUREDAIN_GOLD_BRICK_SLAB = registerWithItem("tauredain_gold_brick_slab",
            () -> new SlabBlock(STONE_PROPS));
    public static final RegistryObject<Block> TAUREDAIN_OBSIDIAN_BRICK_SLAB = registerWithItem("tauredain_obsidian_brick_slab",
            () -> new SlabBlock(STONE_PROPS));
    public static final RegistryObject<Block> DWARVEN_OBSIDIAN_BRICK_SLAB = registerWithItem("dwarven_obsidian_brick_slab",
            () -> new SlabBlock(STONE_PROPS));
    public static final RegistryObject<Block> MUD_BRICK_SLAB = registerWithItem("mud_brick_slab",
            () -> new SlabBlock(STONE_PROPS));
    public static final RegistryObject<Block> DALE_BRICK_SLAB = registerWithItem("dale_brick_slab",
            () -> new SlabBlock(STONE_PROPS));
    public static final RegistryObject<Block> DORWINION_BRICK_SLAB = registerWithItem("dorwinion_brick_slab",
            () -> new SlabBlock(STONE_PROPS));
    public static final RegistryObject<Block> DORWINION_MOSSY_BRICK_SLAB = registerWithItem("dorwinion_mossy_brick_slab",
            () -> new SlabBlock(STONE_PROPS));
    public static final RegistryObject<Block> DORWINION_CRACKED_BRICK_SLAB = registerWithItem("dorwinion_cracked_brick_slab",
            () -> new SlabBlock(STONE_PROPS));
    public static final RegistryObject<Block> DORWINION_FLOWERS_BRICK_SLAB = registerWithItem("dorwinion_flowers_brick_slab",
            () -> new SlabBlock(STONE_PROPS));
    public static final RegistryObject<Block> MORDOR_ROCK_SLAB = registerWithItem("mordor_rock_slab",
            () -> new SlabBlock(STONE_PROPS));
    public static final RegistryObject<Block> GONDOR_RUSTIC_MOSSY_BRICK_SLAB = registerWithItem("gondor_rustic_mossy_brick_slab",
            () -> new SlabBlock(STONE_PROPS));
    public static final RegistryObject<Block> GONDOR_RUSTIC_CRACKED_BRICK_SLAB = registerWithItem("gondor_rustic_cracked_brick_slab",
            () -> new SlabBlock(STONE_PROPS));
    public static final RegistryObject<Block> GONDOR_ROCK_SLAB = registerWithItem("gondor_rock_slab",
            () -> new SlabBlock(STONE_PROPS));
    public static final RegistryObject<Block> ROHAN_ROCK_SLAB = registerWithItem("rohan_rock_slab",
            () -> new SlabBlock(STONE_PROPS));
    public static final RegistryObject<Block> BLUE_ROCK_SLAB = registerWithItem("blue_rock_slab",
            () -> new SlabBlock(STONE_PROPS));
    public static final RegistryObject<Block> RED_ROCK_SLAB = registerWithItem("red_rock_slab",
            () -> new SlabBlock(STONE_PROPS));
    public static final RegistryObject<Block> CHALK_ROCK_SLAB = registerWithItem("chalk_rock_slab",
            () -> new SlabBlock(STONE_PROPS));
    public static final RegistryObject<Block> RHUN_MOSSY_BRICK_SLAB = registerWithItem("rhun_mossy_brick_slab",
            () -> new SlabBlock(STONE_PROPS));
    public static final RegistryObject<Block> RHUN_CRACKED_BRICK_SLAB = registerWithItem("rhun_cracked_brick_slab",
            () -> new SlabBlock(STONE_PROPS));
    public static final RegistryObject<Block> RHUN_FLOWERS_BRICK_SLAB = registerWithItem("rhun_flowers_brick_slab",
            () -> new SlabBlock(STONE_PROPS));
    public static final RegistryObject<Block> RHUN_RED_BRICK_SLAB = registerWithItem("rhun_red_brick_slab",
            () -> new SlabBlock(STONE_PROPS));
    public static final RegistryObject<Block> ANGMAR_SNOW_BRICK_SLAB = registerWithItem("angmar_snow_brick_slab",
            () -> new SlabBlock(STONE_PROPS));
    public static final RegistryObject<Block> DALE_CRACKED_BRICK_SLAB = registerWithItem("dale_cracked_brick_slab",
            () -> new SlabBlock(STONE_PROPS));
    public static final RegistryObject<Block> UMBAR_BRICK_SLAB = registerWithItem("umbar_brick_slab",
            () -> new SlabBlock(STONE_PROPS));
    public static final RegistryObject<Block> UMBAR_CRACKED_BRICK_SLAB = registerWithItem("umbar_cracked_brick_slab",
            () -> new SlabBlock(STONE_PROPS));
    public static final RegistryObject<Block> DOL_GULDUR_MOSSY_BRICK_SLAB = registerWithItem("dol_guldur_mossy_brick_slab",
            () -> new SlabBlock(STONE_PROPS));
    public static final RegistryObject<Block> MORWAITH_CRACKED_BRICK_SLAB = registerWithItem("morwaith_cracked_brick_slab",
            () -> new SlabBlock(STONE_PROPS));
}
