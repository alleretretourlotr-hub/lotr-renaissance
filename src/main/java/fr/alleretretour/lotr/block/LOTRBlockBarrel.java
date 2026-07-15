package fr.alleretretour.lotr.block;

import fr.alleretretour.lotr.item.LOTRItemMug;
import fr.alleretretour.lotr.tileentity.LOTRTileEntityBarrel;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.HorizontalBlock;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.item.ItemStack;
import net.minecraft.state.StateContainer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;

/**
 * Port de lotr.common.block.LOTRBlockBarrel : le tonneau de brassage.
 * Clic droit avec un recipient vide + boisson prete -> sert une chope.
 * Clic droit sinon -> ouvre le GUI de brassage.
 */
public class LOTRBlockBarrel extends HorizontalBlock {

    public LOTRBlockBarrel(Properties props) {
        super(props);
        registerDefaultState(stateDefinition.any().setValue(FACING, net.minecraft.util.Direction.NORTH));
    }

    @Override
    protected void createBlockStateDefinition(StateContainer.Builder<Block, BlockState> builder) {
        builder.add(FACING);
    }

    @Override
    public BlockState getStateForPlacement(BlockItemUseContext context) {
        return defaultBlockState().setValue(FACING, context.getHorizontalDirection().getOpposite());
    }

    private static final net.minecraft.util.math.shapes.VoxelShape SHAPE =
            Block.box(2.0, 0.0, 2.0, 14.0, 13.0, 14.0);

    @Override
    public net.minecraft.util.math.shapes.VoxelShape getShape(
            BlockState state, net.minecraft.world.IBlockReader world,
            BlockPos pos, net.minecraft.util.math.shapes.ISelectionContext ctx) {
        return SHAPE;
    }

    @Override
    public boolean hasTileEntity(BlockState state) {
        return true;
    }

    @Override
    public TileEntity createTileEntity(BlockState state, IBlockReader world) {
        return new LOTRTileEntityBarrel();
    }

    @Override
    public ActionResultType use(BlockState state, World world, BlockPos pos,
                                PlayerEntity player, Hand hand, BlockRayTraceResult hit) {
        if (world.isClientSide) {
            return ActionResultType.SUCCESS;
        }
        TileEntity te = world.getBlockEntity(pos);
        if (!(te instanceof LOTRTileEntityBarrel)) {
            return ActionResultType.PASS;
        }
        LOTRTileEntityBarrel barrel = (LOTRTileEntityBarrel) te;

        ItemStack held = player.getItemInHand(hand);
        if (barrel.hasDrink() && held.getItem() instanceof LOTRItemMug
                && !(((LOTRItemMug) held.getItem()).isBrewable())
                && held.getItem() instanceof fr.alleretretour.lotr.item.LOTRItemVessel) {
            ItemStack served = barrel.serve(held);
            if (!served.isEmpty()) {
                held.shrink(1);
                if (!player.inventory.add(served)) {
                    player.drop(served, false);
                }
                return ActionResultType.CONSUME;
            }
        }

        player.openMenu(barrel);
        return ActionResultType.CONSUME;
    }

    @Override
    public void onRemove(BlockState state, World world, BlockPos pos, BlockState newState, boolean moving) {
        if (state.getBlock() != newState.getBlock()) {
            TileEntity te = world.getBlockEntity(pos);
            if (te instanceof LOTRTileEntityBarrel) {
                net.minecraft.inventory.InventoryHelper.dropContents(world, pos, (LOTRTileEntityBarrel) te);
            }
            super.onRemove(state, world, pos, newState, moving);
        }
    }
}
