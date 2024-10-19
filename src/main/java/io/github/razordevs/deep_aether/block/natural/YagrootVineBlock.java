package io.github.razordevs.deep_aether.block.natural;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.VineBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.BooleanProperty;

public class YagrootVineBlock extends VineBlock {
    public static final BooleanProperty BOTTOM = BlockStateProperties.BOTTOM;

    public YagrootVineBlock(Properties properties) {
        super(properties);
        this.registerDefaultState(this.stateDefinition.any().setValue(BOTTOM, false).setValue(UP, false).setValue(NORTH, false).setValue(EAST, false).setValue(SOUTH, false).setValue(WEST, false));
    }

    public BlockState updateShape(BlockState state, Direction direction, BlockState p_57877_, LevelAccessor level, BlockPos pos, BlockPos p_57880_) {
        boolean isBottom = !level.getBlockState(pos.below()).is(this);


        if (direction == Direction.DOWN) {
            return state.setValue(YagrootVineBlock.BOTTOM, isBottom);
        } else {
            BlockState blockstate = this.getUpdatedState(state, level, pos);
            return !this.hasFaces(blockstate) ? Blocks.AIR.defaultBlockState() : blockstate.setValue(YagrootVineBlock.BOTTOM, isBottom);
        }
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> state) {
        state.add(BOTTOM);
        super.createBlockStateDefinition(state);
    }

    @Override
    public BlockState getStateForPlacement(BlockPlaceContext context) {
        boolean isBottom = !context.getLevel().getBlockState(context.getClickedPos().below()).is(this);
        BlockState state = super.getStateForPlacement(context);
        if(state != null)
            return state.setValue(BOTTOM, isBottom);
        else return null;
    }

}
