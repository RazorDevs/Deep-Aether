package teamrazor.deepaether.block;

import com.aetherteam.aether.block.natural.AetherDoubleDropsLeaves;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import teamrazor.deepaether.init.DABlocks;

public class SunrootLeaves extends AetherDoubleDropsLeaves {
    public static final BooleanProperty BOTTOM = BlockStateProperties.BOTTOM;
    public SunrootLeaves(Properties properties) {
        super(properties);
        this.registerDefaultState(this.defaultBlockState().setValue(BlockStateProperties.BOTTOM, false));
    }


    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> state) {
        state.add(BOTTOM);
        super.createBlockStateDefinition(state);
    }


    @Override
    public BlockState updateShape(BlockState state, Direction direction, BlockState p_154149_, LevelAccessor level, BlockPos pos, BlockPos p_154152_) {
        return super.updateShape(state, direction, p_154149_, level, pos, p_154152_).setValue(BOTTOM, level.getBlockState(pos.below()).is(DABlocks.SUNROOT_HANGER.get()));
    }

    @Override
    public BlockState getStateForPlacement(BlockPlaceContext context) {

        return super.getStateForPlacement(context).setValue(BOTTOM, context.getLevel().getBlockState(context.getClickedPos().below()).is(DABlocks.SUNROOT_HANGER.get()));

    }
}
