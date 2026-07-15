package fr.alleretretour.lotr.block;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.IWorld;
import net.minecraft.world.IWorldReader;

/**
 * Port de lotr.common.block.LOTRBlockHithlainRope : corde elfique suspendue.
 * Tient si le bloc au-dessus est solide OU est une autre corde (chaine pendante).
 * Escaladable via le tag minecraft:climbable.
 */
public class LOTRBlockRope extends Block {

    private static final VoxelShape SHAPE = Block.box(6.0, 0.0, 6.0, 10.0, 16.0, 10.0);

    public LOTRBlockRope(Properties props) {
        super(props);
    }

    @Override
    public VoxelShape getShape(BlockState state, IBlockReader world, BlockPos pos, ISelectionContext ctx) {
        return SHAPE;
    }

    @Override
    public boolean canSurvive(BlockState state, IWorldReader world, BlockPos pos) {
        BlockPos above = pos.above();
        BlockState up = world.getBlockState(above);
        return up.getBlock() == this || up.isFaceSturdy(world, above, Direction.DOWN);
    }

    @Override
    public BlockState updateShape(BlockState state, Direction dir, BlockState neighbor,
                                  IWorld world, BlockPos pos, BlockPos neighborPos) {
        if (dir == Direction.UP && !canSurvive(state, world, pos)) {
            return net.minecraft.block.Blocks.AIR.defaultBlockState();
        }
        return state;
    }
}
