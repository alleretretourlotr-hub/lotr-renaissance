package fr.alleretretour.lotr.init;

import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.FlowerBlock;
import net.minecraft.block.LadderBlock;
import net.minecraft.block.SoundType;
import net.minecraft.block.TorchBlock;
import net.minecraft.block.WallTorchBlock;
import net.minecraft.block.material.Material;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.WallOrFloorItem;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.potion.Effects;
import net.minecraftforge.fml.RegistryObject;

import java.util.function.Supplier;

/** GENERE AUTOMATIQUEMENT - Phase 2 lot 10 : fleurs, torches de faction, echelles. */
public class LOTRBlocksFlora {

    static final AbstractBlock.Properties FLOWER_PROPS =
            AbstractBlock.Properties.of(Material.PLANT).noCollission().instabreak()
                    .sound(SoundType.GRASS);
    static final AbstractBlock.Properties LADDER_PROPS =
            AbstractBlock.Properties.of(Material.DECORATION).strength(0.4f)
                    .sound(SoundType.LADDER).noOcclusion();

    static AbstractBlock.Properties torchProps(int light) {
        return AbstractBlock.Properties.of(Material.DECORATION).noCollission().instabreak()
                .lightLevel(s -> light).sound(SoundType.WOOD);
    }

    public static void init() {
    }

    private static RegistryObject<Block> registerWithItem(String name, Supplier<Block> block) {
        RegistryObject<Block> ro = LOTRBlocks.BLOCKS.register(name, block);
        LOTRItems.ITEMS.register(name, () -> new BlockItem(ro.get(),
                new Item.Properties().tab(LOTRCreativeTabs.TAB_DECO)));
        return ro;
    }

    public static final RegistryObject<Block> FLOWER_SIMBELMYNE = registerWithItem("simbelmyne",
            () -> new FlowerBlock(Effects.REGENERATION, 8, FLOWER_PROPS));
    public static final RegistryObject<Block> FLOWER_SHIRE_HEATHER = registerWithItem("shire_heather",
            () -> new FlowerBlock(Effects.REGENERATION, 8, FLOWER_PROPS));
    public static final RegistryObject<Block> FLOWER_ELANOR = registerWithItem("elanor",
            () -> new FlowerBlock(Effects.REGENERATION, 8, FLOWER_PROPS));
    public static final RegistryObject<Block> FLOWER_NIPHREDIL = registerWithItem("niphredil",
            () -> new FlowerBlock(Effects.REGENERATION, 8, FLOWER_PROPS));
    public static final RegistryObject<Block> FLOWER_ATHELAS = registerWithItem("athelas",
            () -> new FlowerBlock(Effects.REGENERATION, 8, FLOWER_PROPS));
    public static final RegistryObject<Block> FLOWER_BLUEBELL = registerWithItem("bluebell",
            () -> new FlowerBlock(Effects.REGENERATION, 8, FLOWER_PROPS));
    public static final RegistryObject<Block> FLOWER_ASPHODEL = registerWithItem("asphodel",
            () -> new FlowerBlock(Effects.REGENERATION, 8, FLOWER_PROPS));
    public static final RegistryObject<Block> FLOWER_DWARF_HERB = registerWithItem("dwarf_herb",
            () -> new FlowerBlock(Effects.REGENERATION, 8, FLOWER_PROPS));
    public static final RegistryObject<Block> FLOWER_FLAX_PLANT = registerWithItem("flax_plant",
            () -> new FlowerBlock(Effects.REGENERATION, 8, FLOWER_PROPS));
    public static final RegistryObject<Block> FLOWER_MARIGOLD = registerWithItem("marigold",
            () -> new FlowerBlock(Effects.REGENERATION, 8, FLOWER_PROPS));
    public static final RegistryObject<Block> FLOWER_LAVENDER = registerWithItem("lavender",
            () -> new FlowerBlock(Effects.REGENERATION, 8, FLOWER_PROPS));
    public static final RegistryObject<Block> MALLORN_TORCH = LOTRBlocks.BLOCKS.register("mallorn_torch",
            () -> new TorchBlock(torchProps(14), ParticleTypes.SOUL_FIRE_FLAME));
    public static final RegistryObject<Block> MALLORN_TORCH_WALL = LOTRBlocks.BLOCKS.register("mallorn_torch_wall",
            () -> new WallTorchBlock(torchProps(14), ParticleTypes.SOUL_FIRE_FLAME));
    public static final RegistryObject<Item> MALLORN_TORCH_ITEM = LOTRItems.ITEMS.register("mallorn_torch",
            () -> new WallOrFloorItem(MALLORN_TORCH.get(), MALLORN_TORCH_WALL.get(),
                    new Item.Properties().tab(LOTRCreativeTabs.TAB_DECO)));
    public static final RegistryObject<Block> MALLORN_TORCH_BLUE = LOTRBlocks.BLOCKS.register("mallorn_torch_blue",
            () -> new TorchBlock(torchProps(14), ParticleTypes.SOUL_FIRE_FLAME));
    public static final RegistryObject<Block> MALLORN_TORCH_BLUE_WALL = LOTRBlocks.BLOCKS.register("mallorn_torch_blue_wall",
            () -> new WallTorchBlock(torchProps(14), ParticleTypes.SOUL_FIRE_FLAME));
    public static final RegistryObject<Item> MALLORN_TORCH_BLUE_ITEM = LOTRItems.ITEMS.register("mallorn_torch_blue",
            () -> new WallOrFloorItem(MALLORN_TORCH_BLUE.get(), MALLORN_TORCH_BLUE_WALL.get(),
                    new Item.Properties().tab(LOTRCreativeTabs.TAB_DECO)));
    public static final RegistryObject<Block> MALLORN_TORCH_GOLD = LOTRBlocks.BLOCKS.register("mallorn_torch_gold",
            () -> new TorchBlock(torchProps(14), ParticleTypes.FLAME));
    public static final RegistryObject<Block> MALLORN_TORCH_GOLD_WALL = LOTRBlocks.BLOCKS.register("mallorn_torch_gold_wall",
            () -> new WallTorchBlock(torchProps(14), ParticleTypes.FLAME));
    public static final RegistryObject<Item> MALLORN_TORCH_GOLD_ITEM = LOTRItems.ITEMS.register("mallorn_torch_gold",
            () -> new WallOrFloorItem(MALLORN_TORCH_GOLD.get(), MALLORN_TORCH_GOLD_WALL.get(),
                    new Item.Properties().tab(LOTRCreativeTabs.TAB_DECO)));
    public static final RegistryObject<Block> MALLORN_TORCH_GREEN = LOTRBlocks.BLOCKS.register("mallorn_torch_green",
            () -> new TorchBlock(torchProps(14), ParticleTypes.FLAME));
    public static final RegistryObject<Block> MALLORN_TORCH_GREEN_WALL = LOTRBlocks.BLOCKS.register("mallorn_torch_green_wall",
            () -> new WallTorchBlock(torchProps(14), ParticleTypes.FLAME));
    public static final RegistryObject<Item> MALLORN_TORCH_GREEN_ITEM = LOTRItems.ITEMS.register("mallorn_torch_green",
            () -> new WallOrFloorItem(MALLORN_TORCH_GREEN.get(), MALLORN_TORCH_GREEN_WALL.get(),
                    new Item.Properties().tab(LOTRCreativeTabs.TAB_DECO)));
    public static final RegistryObject<Block> WOOD_ELVEN_TORCH = LOTRBlocks.BLOCKS.register("wood_elven_torch",
            () -> new TorchBlock(torchProps(14), ParticleTypes.SOUL_FIRE_FLAME));
    public static final RegistryObject<Block> WOOD_ELVEN_TORCH_WALL = LOTRBlocks.BLOCKS.register("wood_elven_torch_wall",
            () -> new WallTorchBlock(torchProps(14), ParticleTypes.SOUL_FIRE_FLAME));
    public static final RegistryObject<Item> WOOD_ELVEN_TORCH_ITEM = LOTRItems.ITEMS.register("wood_elven_torch",
            () -> new WallOrFloorItem(WOOD_ELVEN_TORCH.get(), WOOD_ELVEN_TORCH_WALL.get(),
                    new Item.Properties().tab(LOTRCreativeTabs.TAB_DECO)));
    public static final RegistryObject<Block> HIGH_ELVEN_TORCH = LOTRBlocks.BLOCKS.register("high_elven_torch",
            () -> new TorchBlock(torchProps(13), ParticleTypes.SOUL_FIRE_FLAME));
    public static final RegistryObject<Block> HIGH_ELVEN_TORCH_WALL = LOTRBlocks.BLOCKS.register("high_elven_torch_wall",
            () -> new WallTorchBlock(torchProps(13), ParticleTypes.SOUL_FIRE_FLAME));
    public static final RegistryObject<Item> HIGH_ELVEN_TORCH_ITEM = LOTRItems.ITEMS.register("high_elven_torch",
            () -> new WallOrFloorItem(HIGH_ELVEN_TORCH.get(), HIGH_ELVEN_TORCH_WALL.get(),
                    new Item.Properties().tab(LOTRCreativeTabs.TAB_DECO)));
    public static final RegistryObject<Block> MORGUL_TORCH = LOTRBlocks.BLOCKS.register("morgul_torch",
            () -> new TorchBlock(torchProps(13), ParticleTypes.SOUL_FIRE_FLAME));
    public static final RegistryObject<Block> MORGUL_TORCH_WALL = LOTRBlocks.BLOCKS.register("morgul_torch_wall",
            () -> new WallTorchBlock(torchProps(13), ParticleTypes.SOUL_FIRE_FLAME));
    public static final RegistryObject<Item> MORGUL_TORCH_ITEM = LOTRItems.ITEMS.register("morgul_torch",
            () -> new WallOrFloorItem(MORGUL_TORCH.get(), MORGUL_TORCH_WALL.get(),
                    new Item.Properties().tab(LOTRCreativeTabs.TAB_DECO)));
    public static final RegistryObject<Block> MALLORN_LADDER = registerWithItem("mallorn_ladder",
            () -> new LadderBlock(LADDER_PROPS) {});
    public static final RegistryObject<Block> HITHLAIN_LADDER = registerWithItem("hithlain_ladder",
            () -> new fr.alleretretour.lotr.block.LOTRBlockRope(LADDER_PROPS));
}
