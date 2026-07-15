package fr.alleretretour.lotr.block;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.state.EnumProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.util.Direction;
import net.minecraft.util.IStringSerializable;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IWorld;

/**
 * Port de lotr.common.block.LOTRBlockPillarBase.
 * Pilier segmente : la texture laterale depend des voisins verticaux
 * (base, fut, chapiteau, ou colonne isolee), exactement comme l'original.
 * L'etat SEGMENT est recalcule a la pose et quand un voisin change.
 */
public class LOTRBlockPillar extends Block {

    public enum Segment implements IStringSerializable {
        SINGLE("single"), BOTTOM("bottom"), MIDDLE("middle"), TOP("top");

        private final String name;

        Segment(String name) {
            this.name = name;
        }

        @Override
        public String getSerializedName() {
            return name;
        }
    }

    public static final EnumProperty<Segment> SEGMENT = EnumProperty.create("segment", Segment.class);

    public LOTRBlockPillar(Properties props) {
        super(props);
        registerDefaultState(stateDefinition.any().setValue(SEGMENT, Segment.SINGLE));
    }

    @Override
    protected void createBlockStateDefinition(StateContainer.Builder<Block, BlockState> builder) {
        builder.add(SEGMENT);
    }

    private Segment computeSegment(IWorld world, BlockPos pos) {
        boolean above = world.getBlockState(pos.above()).getBlock() == this;
        boolean below = world.getBlockState(pos.below()).getBlock() == this;
        if (above && below) {
            return Segment.MIDDLE;
        }
        if (above) {
            return Segment.BOTTOM;
        }
        if (below) {
            return Segment.TOP;
        }
        return Segment.SINGLE;
    }

    @Override
    public BlockState getStateForPlacement(BlockItemUseContext context) {
        return defaultBlockState().setValue(SEGMENT,
                computeSegment(context.getLevel(), context.getClickedPos()));
    }

    @Override
    public BlockState updateShape(BlockState state, Direction dir, BlockState neighbor,
                                  IWorld world, BlockPos pos, BlockPos neighborPos) {
        if (dir.getAxis() == Direction.Axis.Y) {
            return state.setValue(SEGMENT, computeSegment(world, pos));
        }
        return state;
    }
}
