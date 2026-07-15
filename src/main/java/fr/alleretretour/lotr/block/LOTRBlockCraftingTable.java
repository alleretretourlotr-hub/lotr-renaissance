package fr.alleretretour.lotr.block;

import fr.alleretretour.lotr.inventory.LOTRContainerFactionCrafting;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.inventory.container.SimpleNamedContainerProvider;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.util.IWorldPosCallable;
import net.minecraft.world.World;

/**
 * Table de craft de faction : comme l'etabli vanilla (pas de TileEntity),
 * mais le container ne resout que les recettes de sa faction.
 */
public class LOTRBlockCraftingTable extends Block {

    private final String faction;

    public LOTRBlockCraftingTable(String faction, Properties props) {
        super(props);
        this.faction = faction;
    }

    public String getFaction() {
        return faction;
    }

    @Override
    public ActionResultType use(BlockState state, World world, BlockPos pos,
                                PlayerEntity player, Hand hand, BlockRayTraceResult hit) {
        if (world.isClientSide) {
            return ActionResultType.SUCCESS;
        }
        player.openMenu(new SimpleNamedContainerProvider(
                (id, inv, p) -> new LOTRContainerFactionCrafting(id, inv,
                        IWorldPosCallable.create(world, pos), faction, this),
                new TranslationTextComponent(getDescriptionId())));
        return ActionResultType.CONSUME;
    }
}
