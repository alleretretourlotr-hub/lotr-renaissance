package fr.alleretretour.lotr.init;

import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.DoorBlock;
import net.minecraft.block.SoundType;
import net.minecraft.block.TrapDoorBlock;
import net.minecraft.block.material.Material;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.TallBlockItem;
import net.minecraftforge.fml.RegistryObject;

import java.util.function.Supplier;

/**
 * GENERE AUTOMATIQUEMENT - Phase 2 lot 3 : portes et trappes.
 * Les sous-classes anonymes exposent les constructeurs proteges de vanilla.
 */
public class LOTRBlocksDoors {

    static final AbstractBlock.Properties DOOR_PROPS =
            AbstractBlock.Properties.of(Material.WOOD).strength(3.0f).sound(SoundType.WOOD).noOcclusion();

    public static void init() {
    }

    private static RegistryObject<Block> registerWithItem(String name, Supplier<Block> block) {
        RegistryObject<Block> ro = LOTRBlocks.BLOCKS.register(name, block);
        LOTRItems.ITEMS.register(name, () -> new BlockItem(ro.get(),
                new Item.Properties().tab(LOTRCreativeTabs.TAB_BLOCKS)));
        return ro;
    }

    private static RegistryObject<Block> registerDoor(String name, Supplier<Block> block) {
        RegistryObject<Block> ro = LOTRBlocks.BLOCKS.register(name, block);
        LOTRItems.ITEMS.register(name, () -> new TallBlockItem(ro.get(),
                new Item.Properties().tab(LOTRCreativeTabs.TAB_BLOCKS)));
        return ro;
    }

    public static final RegistryObject<Block> SPRUCE_DOOR = registerDoor("spruce_door",
            () -> new DoorBlock(DOOR_PROPS) {});
    public static final RegistryObject<Block> BIRCH_DOOR = registerDoor("birch_door",
            () -> new DoorBlock(DOOR_PROPS) {});
    public static final RegistryObject<Block> JUNGLE_DOOR = registerDoor("jungle_door",
            () -> new DoorBlock(DOOR_PROPS) {});
    public static final RegistryObject<Block> ACACIA_DOOR = registerDoor("acacia_door",
            () -> new DoorBlock(DOOR_PROPS) {});
    public static final RegistryObject<Block> DARK_OAK_DOOR = registerDoor("dark_oak_door",
            () -> new DoorBlock(DOOR_PROPS) {});
    public static final RegistryObject<Block> SHIRE_PINE_DOOR = registerDoor("shire_pine_door",
            () -> new DoorBlock(DOOR_PROPS) {});
    public static final RegistryObject<Block> MALLORN_DOOR = registerDoor("mallorn_door",
            () -> new DoorBlock(DOOR_PROPS) {});
    public static final RegistryObject<Block> MIRK_OAK_DOOR = registerDoor("mirk_oak_door",
            () -> new DoorBlock(DOOR_PROPS) {});
    public static final RegistryObject<Block> CHARRED_DOOR = registerDoor("charred_door",
            () -> new DoorBlock(DOOR_PROPS) {});
    public static final RegistryObject<Block> APPLE_DOOR = registerDoor("apple_door",
            () -> new DoorBlock(DOOR_PROPS) {});
    public static final RegistryObject<Block> PEAR_DOOR = registerDoor("pear_door",
            () -> new DoorBlock(DOOR_PROPS) {});
    public static final RegistryObject<Block> CHERRY_DOOR = registerDoor("cherry_door",
            () -> new DoorBlock(DOOR_PROPS) {});
    public static final RegistryObject<Block> MANGO_DOOR = registerDoor("mango_door",
            () -> new DoorBlock(DOOR_PROPS) {});
    public static final RegistryObject<Block> LEBETHRON_DOOR = registerDoor("lebethron_door",
            () -> new DoorBlock(DOOR_PROPS) {});
    public static final RegistryObject<Block> BEECH_DOOR = registerDoor("beech_door",
            () -> new DoorBlock(DOOR_PROPS) {});
    public static final RegistryObject<Block> HOLLY_DOOR = registerDoor("holly_door",
            () -> new DoorBlock(DOOR_PROPS) {});
    public static final RegistryObject<Block> BANANA_DOOR = registerDoor("banana_door",
            () -> new DoorBlock(DOOR_PROPS) {});
    public static final RegistryObject<Block> MAPLE_DOOR = registerDoor("maple_door",
            () -> new DoorBlock(DOOR_PROPS) {});
    public static final RegistryObject<Block> LARCH_DOOR = registerDoor("larch_door",
            () -> new DoorBlock(DOOR_PROPS) {});
    public static final RegistryObject<Block> DATE_PALM_DOOR = registerDoor("date_palm_door",
            () -> new DoorBlock(DOOR_PROPS) {});
    public static final RegistryObject<Block> MANGROVE_DOOR = registerDoor("mangrove_door",
            () -> new DoorBlock(DOOR_PROPS) {});
    public static final RegistryObject<Block> CHESTNUT_DOOR = registerDoor("chestnut_door",
            () -> new DoorBlock(DOOR_PROPS) {});
    public static final RegistryObject<Block> BAOBAB_DOOR = registerDoor("baobab_door",
            () -> new DoorBlock(DOOR_PROPS) {});
    public static final RegistryObject<Block> CEDAR_DOOR = registerDoor("cedar_door",
            () -> new DoorBlock(DOOR_PROPS) {});
    public static final RegistryObject<Block> FIR_DOOR = registerDoor("fir_door",
            () -> new DoorBlock(DOOR_PROPS) {});
    public static final RegistryObject<Block> PINE_DOOR = registerDoor("pine_door",
            () -> new DoorBlock(DOOR_PROPS) {});
    public static final RegistryObject<Block> LEMON_DOOR = registerDoor("lemon_door",
            () -> new DoorBlock(DOOR_PROPS) {});
    public static final RegistryObject<Block> ORANGE_DOOR = registerDoor("orange_door",
            () -> new DoorBlock(DOOR_PROPS) {});
    public static final RegistryObject<Block> LIME_DOOR = registerDoor("lime_door",
            () -> new DoorBlock(DOOR_PROPS) {});
    public static final RegistryObject<Block> MAHOGANY_DOOR = registerDoor("mahogany_door",
            () -> new DoorBlock(DOOR_PROPS) {});
    public static final RegistryObject<Block> WILLOW_DOOR = registerDoor("willow_door",
            () -> new DoorBlock(DOOR_PROPS) {});
    public static final RegistryObject<Block> CYPRESS_DOOR = registerDoor("cypress_door",
            () -> new DoorBlock(DOOR_PROPS) {});
    public static final RegistryObject<Block> OLIVE_DOOR = registerDoor("olive_door",
            () -> new DoorBlock(DOOR_PROPS) {});
    public static final RegistryObject<Block> ASPEN_DOOR = registerDoor("aspen_door",
            () -> new DoorBlock(DOOR_PROPS) {});
    public static final RegistryObject<Block> GREEN_OAK_DOOR = registerDoor("green_oak_door",
            () -> new DoorBlock(DOOR_PROPS) {});
    public static final RegistryObject<Block> LAIRELOSSE_DOOR = registerDoor("lairelosse_door",
            () -> new DoorBlock(DOOR_PROPS) {});
    public static final RegistryObject<Block> ALMOND_DOOR = registerDoor("almond_door",
            () -> new DoorBlock(DOOR_PROPS) {});
    public static final RegistryObject<Block> PLUM_DOOR = registerDoor("plum_door",
            () -> new DoorBlock(DOOR_PROPS) {});
    public static final RegistryObject<Block> REDWOOD_DOOR = registerDoor("redwood_door",
            () -> new DoorBlock(DOOR_PROPS) {});
    public static final RegistryObject<Block> POMEGRANATE_DOOR = registerDoor("pomegranate_door",
            () -> new DoorBlock(DOOR_PROPS) {});
    public static final RegistryObject<Block> PALM_DOOR = registerDoor("palm_door",
            () -> new DoorBlock(DOOR_PROPS) {});
    public static final RegistryObject<Block> DRAGON_DOOR = registerDoor("dragon_door",
            () -> new DoorBlock(DOOR_PROPS) {});
    public static final RegistryObject<Block> ROTTEN_DOOR = registerDoor("rotten_door",
            () -> new DoorBlock(DOOR_PROPS) {});
    public static final RegistryObject<Block> KANUKA_DOOR = registerDoor("kanuka_door",
            () -> new DoorBlock(DOOR_PROPS) {});
    public static final RegistryObject<Block> SPRUCE_TRAPDOOR = registerWithItem("spruce_trapdoor",
            () -> new TrapDoorBlock(DOOR_PROPS) {});
    public static final RegistryObject<Block> BIRCH_TRAPDOOR = registerWithItem("birch_trapdoor",
            () -> new TrapDoorBlock(DOOR_PROPS) {});
    public static final RegistryObject<Block> JUNGLE_TRAPDOOR = registerWithItem("jungle_trapdoor",
            () -> new TrapDoorBlock(DOOR_PROPS) {});
    public static final RegistryObject<Block> ACACIA_TRAPDOOR = registerWithItem("acacia_trapdoor",
            () -> new TrapDoorBlock(DOOR_PROPS) {});
    public static final RegistryObject<Block> DARK_OAK_TRAPDOOR = registerWithItem("dark_oak_trapdoor",
            () -> new TrapDoorBlock(DOOR_PROPS) {});
    public static final RegistryObject<Block> SHIRE_PINE_TRAPDOOR = registerWithItem("shire_pine_trapdoor",
            () -> new TrapDoorBlock(DOOR_PROPS) {});
    public static final RegistryObject<Block> MALLORN_TRAPDOOR = registerWithItem("mallorn_trapdoor",
            () -> new TrapDoorBlock(DOOR_PROPS) {});
    public static final RegistryObject<Block> MIRK_OAK_TRAPDOOR = registerWithItem("mirk_oak_trapdoor",
            () -> new TrapDoorBlock(DOOR_PROPS) {});
    public static final RegistryObject<Block> CHARRED_TRAPDOOR = registerWithItem("charred_trapdoor",
            () -> new TrapDoorBlock(DOOR_PROPS) {});
    public static final RegistryObject<Block> APPLE_TRAPDOOR = registerWithItem("apple_trapdoor",
            () -> new TrapDoorBlock(DOOR_PROPS) {});
    public static final RegistryObject<Block> PEAR_TRAPDOOR = registerWithItem("pear_trapdoor",
            () -> new TrapDoorBlock(DOOR_PROPS) {});
    public static final RegistryObject<Block> CHERRY_TRAPDOOR = registerWithItem("cherry_trapdoor",
            () -> new TrapDoorBlock(DOOR_PROPS) {});
    public static final RegistryObject<Block> MANGO_TRAPDOOR = registerWithItem("mango_trapdoor",
            () -> new TrapDoorBlock(DOOR_PROPS) {});
    public static final RegistryObject<Block> LEBETHRON_TRAPDOOR = registerWithItem("lebethron_trapdoor",
            () -> new TrapDoorBlock(DOOR_PROPS) {});
    public static final RegistryObject<Block> BEECH_TRAPDOOR = registerWithItem("beech_trapdoor",
            () -> new TrapDoorBlock(DOOR_PROPS) {});
    public static final RegistryObject<Block> HOLLY_TRAPDOOR = registerWithItem("holly_trapdoor",
            () -> new TrapDoorBlock(DOOR_PROPS) {});
    public static final RegistryObject<Block> BANANA_TRAPDOOR = registerWithItem("banana_trapdoor",
            () -> new TrapDoorBlock(DOOR_PROPS) {});
    public static final RegistryObject<Block> MAPLE_TRAPDOOR = registerWithItem("maple_trapdoor",
            () -> new TrapDoorBlock(DOOR_PROPS) {});
    public static final RegistryObject<Block> LARCH_TRAPDOOR = registerWithItem("larch_trapdoor",
            () -> new TrapDoorBlock(DOOR_PROPS) {});
    public static final RegistryObject<Block> DATE_PALM_TRAPDOOR = registerWithItem("date_palm_trapdoor",
            () -> new TrapDoorBlock(DOOR_PROPS) {});
    public static final RegistryObject<Block> MANGROVE_TRAPDOOR = registerWithItem("mangrove_trapdoor",
            () -> new TrapDoorBlock(DOOR_PROPS) {});
    public static final RegistryObject<Block> CHESTNUT_TRAPDOOR = registerWithItem("chestnut_trapdoor",
            () -> new TrapDoorBlock(DOOR_PROPS) {});
    public static final RegistryObject<Block> BAOBAB_TRAPDOOR = registerWithItem("baobab_trapdoor",
            () -> new TrapDoorBlock(DOOR_PROPS) {});
    public static final RegistryObject<Block> CEDAR_TRAPDOOR = registerWithItem("cedar_trapdoor",
            () -> new TrapDoorBlock(DOOR_PROPS) {});
    public static final RegistryObject<Block> FIR_TRAPDOOR = registerWithItem("fir_trapdoor",
            () -> new TrapDoorBlock(DOOR_PROPS) {});
    public static final RegistryObject<Block> PINE_TRAPDOOR = registerWithItem("pine_trapdoor",
            () -> new TrapDoorBlock(DOOR_PROPS) {});
    public static final RegistryObject<Block> LEMON_TRAPDOOR = registerWithItem("lemon_trapdoor",
            () -> new TrapDoorBlock(DOOR_PROPS) {});
    public static final RegistryObject<Block> ORANGE_TRAPDOOR = registerWithItem("orange_trapdoor",
            () -> new TrapDoorBlock(DOOR_PROPS) {});
    public static final RegistryObject<Block> LIME_TRAPDOOR = registerWithItem("lime_trapdoor",
            () -> new TrapDoorBlock(DOOR_PROPS) {});
    public static final RegistryObject<Block> MAHOGANY_TRAPDOOR = registerWithItem("mahogany_trapdoor",
            () -> new TrapDoorBlock(DOOR_PROPS) {});
    public static final RegistryObject<Block> WILLOW_TRAPDOOR = registerWithItem("willow_trapdoor",
            () -> new TrapDoorBlock(DOOR_PROPS) {});
    public static final RegistryObject<Block> CYPRESS_TRAPDOOR = registerWithItem("cypress_trapdoor",
            () -> new TrapDoorBlock(DOOR_PROPS) {});
    public static final RegistryObject<Block> OLIVE_TRAPDOOR = registerWithItem("olive_trapdoor",
            () -> new TrapDoorBlock(DOOR_PROPS) {});
    public static final RegistryObject<Block> ASPEN_TRAPDOOR = registerWithItem("aspen_trapdoor",
            () -> new TrapDoorBlock(DOOR_PROPS) {});
    public static final RegistryObject<Block> GREEN_OAK_TRAPDOOR = registerWithItem("green_oak_trapdoor",
            () -> new TrapDoorBlock(DOOR_PROPS) {});
    public static final RegistryObject<Block> LAIRELOSSE_TRAPDOOR = registerWithItem("lairelosse_trapdoor",
            () -> new TrapDoorBlock(DOOR_PROPS) {});
    public static final RegistryObject<Block> ALMOND_TRAPDOOR = registerWithItem("almond_trapdoor",
            () -> new TrapDoorBlock(DOOR_PROPS) {});
    public static final RegistryObject<Block> PLUM_TRAPDOOR = registerWithItem("plum_trapdoor",
            () -> new TrapDoorBlock(DOOR_PROPS) {});
    public static final RegistryObject<Block> REDWOOD_TRAPDOOR = registerWithItem("redwood_trapdoor",
            () -> new TrapDoorBlock(DOOR_PROPS) {});
    public static final RegistryObject<Block> POMEGRANATE_TRAPDOOR = registerWithItem("pomegranate_trapdoor",
            () -> new TrapDoorBlock(DOOR_PROPS) {});
    public static final RegistryObject<Block> PALM_TRAPDOOR = registerWithItem("palm_trapdoor",
            () -> new TrapDoorBlock(DOOR_PROPS) {});
    public static final RegistryObject<Block> DRAGON_TRAPDOOR = registerWithItem("dragon_trapdoor",
            () -> new TrapDoorBlock(DOOR_PROPS) {});
    public static final RegistryObject<Block> KANUKA_TRAPDOOR = registerWithItem("kanuka_trapdoor",
            () -> new TrapDoorBlock(DOOR_PROPS) {});
    public static final RegistryObject<Block> ROTTEN_TRAPDOOR = registerWithItem("rotten_trapdoor",
            () -> new TrapDoorBlock(DOOR_PROPS) {});
}
