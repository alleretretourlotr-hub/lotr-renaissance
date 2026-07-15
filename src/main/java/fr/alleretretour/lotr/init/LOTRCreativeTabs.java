package fr.alleretretour.lotr.init;

import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.RegistryObject;

import java.util.function.Supplier;

/**
 * Port de lotr.common.LOTRCreativeTabs : les onglets creatifs du mod.
 * Les icones referencent des items deja enregistres (suppliers laziness).
 * tabStory et tabSpawn arriveront avec les Phases 3-4.
 */
public class LOTRCreativeTabs {

    public static final ItemGroup TAB_BLOCKS = make("lotr_blocks", () -> LOTRBlocksRock.GONDOR_BRICK);
    public static final ItemGroup TAB_DECO = make("lotr_decorations", () -> itemRO("mallorn_torch"));
    public static final ItemGroup TAB_UTIL = make("lotr_util", () -> LOTRBlocksBarrel.BARREL_ITEM);
    public static final ItemGroup TAB_FOOD = make("lotr_food", () -> itemRO("lembas"));
    public static final ItemGroup TAB_MATERIALS = make("lotr_materials", () -> itemRO("bronze"));
    public static final ItemGroup TAB_MISC = make("lotr_misc", () -> itemRO("naurite_gem"));
    public static final ItemGroup TAB_TOOLS = make("lotr_tools", () -> itemRO("dwarven_mattock"));
    public static final ItemGroup TAB_COMBAT = make("lotr_combat", () -> itemRO("gondor_sword"));

    private static Supplier<net.minecraft.item.Item> itemRO(String id) {
        return () -> net.minecraftforge.registries.ForgeRegistries.ITEMS.getValue(
                new net.minecraft.util.ResourceLocation("lotr", id));
    }

    @SuppressWarnings("unchecked")
    private static ItemGroup make(String label, Supplier<Object> icon) {
        return new ItemGroup(label) {
            @Override
            public ItemStack makeIcon() {
                Object o = icon.get();
                if (o instanceof RegistryObject) {
                    Object v = ((RegistryObject<?>) o).get();
                    if (v instanceof net.minecraft.block.Block) {
                        return new ItemStack(((net.minecraft.block.Block) v).asItem());
                    }
                    return new ItemStack((Item) v);
                }
                if (o instanceof Supplier) {
                    Item it = ((Supplier<Item>) o).get();
                    return new ItemStack(it != null ? it : net.minecraft.item.Items.BARRIER);
                }
                return ItemStack.EMPTY;
            }
        };
    }
}
