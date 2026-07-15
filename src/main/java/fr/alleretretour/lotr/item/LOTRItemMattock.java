package fr.alleretretour.lotr.item;

import fr.alleretretour.lotr.init.LOTRCreativeTabs;
import fr.alleretretour.lotr.init.LOTRItems;
import net.minecraft.block.BlockState;
import net.minecraft.block.material.Material;
import net.minecraft.item.ItemStack;
import net.minecraft.item.PickaxeItem;
import net.minecraft.item.ItemUseContext;
import net.minecraft.util.ActionResultType;
import net.minecraft.item.Items;

/**
 * Port de lotr.common.item.LOTRItemMattock : outil combine des Nains,
 * pioche + pelle + hache + peut labourer comme une houe.
 */
public class LOTRItemMattock extends PickaxeItem {

    private final float efficiency;

    public LOTRItemMattock(LOTRMaterial material) {
        super(material.toItemTier(), 1, -2.8f,
                new Properties().tab(LOTRCreativeTabs.TAB_TOOLS));
        this.efficiency = material.toItemTier().getSpeed();
    }

    @Override
    public float getDestroySpeed(ItemStack stack, BlockState state) {
        float f = super.getDestroySpeed(stack, state);
        Material m = state.getMaterial();
        if (f == 1.0f && (m == Material.WOOD || m == Material.PLANT
                || m == Material.REPLACEABLE_PLANT || m == Material.DIRT || m == Material.SAND)) {
            return efficiency;
        }
        return f;
    }

    @Override
    public ActionResultType useOn(ItemUseContext context) {
        // labourage comme une houe
        return Items.IRON_HOE.useOn(context);
    }
}
