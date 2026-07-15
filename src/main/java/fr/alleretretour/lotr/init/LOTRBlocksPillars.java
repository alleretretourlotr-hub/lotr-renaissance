package fr.alleretretour.lotr.init;

import fr.alleretretour.lotr.block.LOTRBlockPillar;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.SlabBlock;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraftforge.fml.RegistryObject;

import java.util.function.Supplier;

/** GENERE AUTOMATIQUEMENT - Phase 2 lot 7 : piliers segmentes + dalles de piliers. */
public class LOTRBlocksPillars {

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

    public static final RegistryObject<Block> DWARVEN_PILLAR = registerWithItem("dwarven_pillar",
            () -> new LOTRBlockPillar(STONE_PROPS));
    public static final RegistryObject<Block> GALADHRIM_PILLAR = registerWithItem("galadhrim_pillar",
            () -> new LOTRBlockPillar(STONE_PROPS));
    public static final RegistryObject<Block> GALADHRIM_CRACKED_PILLAR = registerWithItem("galadhrim_cracked_pillar",
            () -> new LOTRBlockPillar(STONE_PROPS));
    public static final RegistryObject<Block> BLUE_ROCK_PILLAR = registerWithItem("blue_rock_pillar",
            () -> new LOTRBlockPillar(STONE_PROPS));
    public static final RegistryObject<Block> RED_ROCK_PILLAR = registerWithItem("red_rock_pillar",
            () -> new LOTRBlockPillar(STONE_PROPS));
    public static final RegistryObject<Block> NEAR_HARAD_PILLAR = registerWithItem("near_harad_pillar",
            () -> new LOTRBlockPillar(STONE_PROPS));
    public static final RegistryObject<Block> GONDOR_PILLAR = registerWithItem("gondor_pillar",
            () -> new LOTRBlockPillar(STONE_PROPS));
    public static final RegistryObject<Block> MORDOR_PILLAR = registerWithItem("mordor_pillar",
            () -> new LOTRBlockPillar(STONE_PROPS));
    public static final RegistryObject<Block> ROHAN_PILLAR = registerWithItem("rohan_pillar",
            () -> new LOTRBlockPillar(STONE_PROPS));
    public static final RegistryObject<Block> BLACK_GONDOR_PILLAR = registerWithItem("black_gondor_pillar",
            () -> new LOTRBlockPillar(STONE_PROPS));
    public static final RegistryObject<Block> HIGH_ELVEN_PILLAR = registerWithItem("high_elven_pillar",
            () -> new LOTRBlockPillar(STONE_PROPS));
    public static final RegistryObject<Block> HIGH_ELVEN_CRACKED_PILLAR = registerWithItem("high_elven_cracked_pillar",
            () -> new LOTRBlockPillar(STONE_PROPS));
    public static final RegistryObject<Block> WOOD_ELVEN_PILLAR = registerWithItem("wood_elven_pillar",
            () -> new LOTRBlockPillar(STONE_PROPS));
    public static final RegistryObject<Block> WOOD_ELVEN_CRACKED_PILLAR = registerWithItem("wood_elven_cracked_pillar",
            () -> new LOTRBlockPillar(STONE_PROPS));
    public static final RegistryObject<Block> TAUREDAIN_PILLAR = registerWithItem("tauredain_pillar",
            () -> new LOTRBlockPillar(STONE_PROPS));
    public static final RegistryObject<Block> NEAR_HARAD_RED_PILLAR = registerWithItem("near_harad_red_pillar",
            () -> new LOTRBlockPillar(STONE_PROPS));
    public static final RegistryObject<Block> DWARVEN_CRACKED_PILLAR = registerWithItem("dwarven_cracked_pillar",
            () -> new LOTRBlockPillar(STONE_PROPS));
    public static final RegistryObject<Block> CHALK_PILLAR = registerWithItem("chalk_pillar",
            () -> new LOTRBlockPillar(STONE_PROPS));
    public static final RegistryObject<Block> STONE_PILLAR = registerWithItem("stone_pillar",
            () -> new LOTRBlockPillar(STONE_PROPS));
    public static final RegistryObject<Block> BRICK_PILLAR = registerWithItem("brick_pillar",
            () -> new LOTRBlockPillar(STONE_PROPS));
    public static final RegistryObject<Block> ANGMAR_PILLAR = registerWithItem("angmar_pillar",
            () -> new LOTRBlockPillar(STONE_PROPS));
    public static final RegistryObject<Block> DALE_PILLAR = registerWithItem("dale_pillar",
            () -> new LOTRBlockPillar(STONE_PROPS));
    public static final RegistryObject<Block> DORWINION_PILLAR = registerWithItem("dorwinion_pillar",
            () -> new LOTRBlockPillar(STONE_PROPS));
    public static final RegistryObject<Block> DORWINION_MOSSY_PILLAR = registerWithItem("dorwinion_mossy_pillar",
            () -> new LOTRBlockPillar(STONE_PROPS));
    public static final RegistryObject<Block> RHUN_PILLAR = registerWithItem("rhun_pillar",
            () -> new LOTRBlockPillar(STONE_PROPS));
    public static final RegistryObject<Block> RHUN_RED_PILLAR = registerWithItem("rhun_red_pillar",
            () -> new LOTRBlockPillar(STONE_PROPS));
    public static final RegistryObject<Block> UMBAR_PILLAR = registerWithItem("umbar_pillar",
            () -> new LOTRBlockPillar(STONE_PROPS));
    public static final RegistryObject<Block> TAUR_GOLD_PILLAR = registerWithItem("taur_gold_pillar",
            () -> new LOTRBlockPillar(STONE_PROPS));
    public static final RegistryObject<Block> TAUR_OBSIDIAN_PILLAR = registerWithItem("taur_obsidian_pillar",
            () -> new LOTRBlockPillar(STONE_PROPS));
    public static final RegistryObject<Block> ARNOR_PILLAR = registerWithItem("arnor_pillar",
            () -> new LOTRBlockPillar(STONE_PROPS));
    public static final RegistryObject<Block> ARNOR_CRACKED_PILLAR = registerWithItem("arnor_cracked_pillar",
            () -> new LOTRBlockPillar(STONE_PROPS));
    public static final RegistryObject<Block> URUK_PILLAR = registerWithItem("uruk_pillar",
            () -> new LOTRBlockPillar(STONE_PROPS));
    public static final RegistryObject<Block> DOL_GULDUR_PILLAR = registerWithItem("dol_guldur_pillar",
            () -> new LOTRBlockPillar(STONE_PROPS));
    public static final RegistryObject<Block> GALADHRIM_PILLAR_SLAB = registerWithItem("galadhrim_pillar_slab",
            () -> new SlabBlock(STONE_PROPS));
    public static final RegistryObject<Block> GALADHRIM_CRACKED_PILLAR_SLAB = registerWithItem("galadhrim_cracked_pillar_slab",
            () -> new SlabBlock(STONE_PROPS));
    public static final RegistryObject<Block> BLUE_ROCK_PILLAR_SLAB = registerWithItem("blue_rock_pillar_slab",
            () -> new SlabBlock(STONE_PROPS));
    public static final RegistryObject<Block> RED_ROCK_PILLAR_SLAB = registerWithItem("red_rock_pillar_slab",
            () -> new SlabBlock(STONE_PROPS));
    public static final RegistryObject<Block> NEAR_HARAD_PILLAR_SLAB = registerWithItem("near_harad_pillar_slab",
            () -> new SlabBlock(STONE_PROPS));
    public static final RegistryObject<Block> MORDOR_PILLAR_SLAB = registerWithItem("mordor_pillar_slab",
            () -> new SlabBlock(STONE_PROPS));
    public static final RegistryObject<Block> ROHAN_PILLAR_SLAB = registerWithItem("rohan_pillar_slab",
            () -> new SlabBlock(STONE_PROPS));
    public static final RegistryObject<Block> BLACK_GONDOR_PILLAR_SLAB = registerWithItem("black_gondor_pillar_slab",
            () -> new SlabBlock(STONE_PROPS));
    public static final RegistryObject<Block> HIGH_ELVEN_CRACKED_PILLAR_SLAB = registerWithItem("high_elven_cracked_pillar_slab",
            () -> new SlabBlock(STONE_PROPS));
    public static final RegistryObject<Block> WOOD_ELVEN_PILLAR_SLAB = registerWithItem("wood_elven_pillar_slab",
            () -> new SlabBlock(STONE_PROPS));
    public static final RegistryObject<Block> WOOD_ELVEN_CRACKED_PILLAR_SLAB = registerWithItem("wood_elven_cracked_pillar_slab",
            () -> new SlabBlock(STONE_PROPS));
    public static final RegistryObject<Block> NEAR_HARAD_RED_PILLAR_SLAB = registerWithItem("near_harad_red_pillar_slab",
            () -> new SlabBlock(STONE_PROPS));
    public static final RegistryObject<Block> DWARVEN_CRACKED_PILLAR_SLAB = registerWithItem("dwarven_cracked_pillar_slab",
            () -> new SlabBlock(STONE_PROPS));
    public static final RegistryObject<Block> TAUREDAIN_PILLAR_SLAB = registerWithItem("tauredain_pillar_slab",
            () -> new SlabBlock(STONE_PROPS));
    public static final RegistryObject<Block> CHALK_PILLAR_SLAB = registerWithItem("chalk_pillar_slab",
            () -> new SlabBlock(STONE_PROPS));
    public static final RegistryObject<Block> STONE_PILLAR_SLAB = registerWithItem("stone_pillar_slab",
            () -> new SlabBlock(STONE_PROPS));
    public static final RegistryObject<Block> BRICK_PILLAR_SLAB = registerWithItem("brick_pillar_slab",
            () -> new SlabBlock(STONE_PROPS));
    public static final RegistryObject<Block> ANGMAR_PILLAR_SLAB = registerWithItem("angmar_pillar_slab",
            () -> new SlabBlock(STONE_PROPS));
    public static final RegistryObject<Block> DORWINION_PILLAR_SLAB = registerWithItem("dorwinion_pillar_slab",
            () -> new SlabBlock(STONE_PROPS));
    public static final RegistryObject<Block> DORWINION_MOSSY_PILLAR_SLAB = registerWithItem("dorwinion_mossy_pillar_slab",
            () -> new SlabBlock(STONE_PROPS));
    public static final RegistryObject<Block> RHUN_PILLAR_SLAB = registerWithItem("rhun_pillar_slab",
            () -> new SlabBlock(STONE_PROPS));
    public static final RegistryObject<Block> RHUN_RED_PILLAR_SLAB = registerWithItem("rhun_red_pillar_slab",
            () -> new SlabBlock(STONE_PROPS));
    public static final RegistryObject<Block> UMBAR_PILLAR_SLAB = registerWithItem("umbar_pillar_slab",
            () -> new SlabBlock(STONE_PROPS));
    public static final RegistryObject<Block> TAUR_GOLD_PILLAR_SLAB = registerWithItem("taur_gold_pillar_slab",
            () -> new SlabBlock(STONE_PROPS));
    public static final RegistryObject<Block> TAUR_OBSIDIAN_PILLAR_SLAB = registerWithItem("taur_obsidian_pillar_slab",
            () -> new SlabBlock(STONE_PROPS));
    public static final RegistryObject<Block> ARNOR_PILLAR_SLAB = registerWithItem("arnor_pillar_slab",
            () -> new SlabBlock(STONE_PROPS));
    public static final RegistryObject<Block> URUK_PILLAR_SLAB = registerWithItem("uruk_pillar_slab",
            () -> new SlabBlock(STONE_PROPS));
    public static final RegistryObject<Block> DOL_GULDUR_PILLAR_SLAB = registerWithItem("dol_guldur_pillar_slab",
            () -> new SlabBlock(STONE_PROPS));
}
