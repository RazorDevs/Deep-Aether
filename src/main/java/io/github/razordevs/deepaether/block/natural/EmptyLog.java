package io.github.razordevs.deepaether.deepaether.block.natural;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.material.Fluids;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;

import javax.annotation.Nonnull;

public class EmptyLog extends DALogBlock {
    public static final BooleanProperty WATERLOGGED = BlockStateProperties.WATERLOGGED;
    public EmptyLog(Properties properties) {
        super(properties);
        registerDefaultState(defaultBlockState().setValue(WATERLOGGED, false));
    }

    private static final VoxelShape SHAPE_BOTTOM = box(0F, 0F, 0F, 16F, 4F, 16F);
    private static final VoxelShape SHAPE_TOP = box(0F, 12F, 0F, 16F, 16F, 16F);
    private static final VoxelShape SHAPE_NORTH = box(0F, 0F, 0F, 4F, 16F, 16F);
    private static final VoxelShape SHAPE_SOUTH = box(12F, 0F, 0F, 16F, 16F, 16F);
    private static final VoxelShape SHAPE_EAST = box(0F, 0F, 0F, 16F, 16F, 4F);
    private static final VoxelShape SHAPE_WEST = box(0F, 0F, 12F, 16F, 16F, 16F);

    private static final VoxelShape SHAPE_X = Shapes.or(SHAPE_BOTTOM, SHAPE_TOP, SHAPE_EAST, SHAPE_WEST);
    private static final VoxelShape SHAPE_Y = Shapes.or(SHAPE_NORTH, SHAPE_SOUTH, SHAPE_EAST, SHAPE_WEST);
    private static final VoxelShape SHAPE_Z = Shapes.or(SHAPE_BOTTOM, SHAPE_TOP, SHAPE_NORTH, SHAPE_SOUTH);


    @Override
    public VoxelShape getShape(BlockState state, BlockGetter world, BlockPos pos, CollisionContext ctx) {
        return switch (state.getValue(AXIS)) {
            case X -> SHAPE_X;
            case Y -> SHAPE_Y;
            case Z -> SHAPE_Z;
        };
    }

    @Override
    public BlockState getStateForPlacement(BlockPlaceContext ctx) {
        return super.getStateForPlacement(ctx).setValue(WATERLOGGED, ctx.getLevel().getFluidState(ctx.getClickedPos()).getType() == Fluids.WATER);
    }

    @Override
    public boolean propagatesSkylightDown(BlockState state, @Nonnull BlockGetter reader, @Nonnull BlockPos pos) {
        return !state.getValue(WATERLOGGED) && state.getValue(AXIS) == Direction.Axis.Y;
    }

    @Nonnull
    @Override
    public FluidState getFluidState(BlockState state) {
        return state.getValue(WATERLOGGED) ? Fluids.WATER.getSource(false) : super.getFluidState(state);
    }

    @Nonnull
    @Override
    public BlockState updateShape(BlockState state, @Nonnull Direction facing, @Nonnull BlockState facingState, @Nonnull LevelAccessor level, @Nonnull BlockPos pos, @Nonnull BlockPos facingPos) {
        if (state.getValue(WATERLOGGED)) {
            level.scheduleTick(pos, Fluids.WATER, Fluids.WATER.getTickDelay(level));
        }

        return super.updateShape(state, facing, facingState, level, pos, facingPos);
    }

    @Override
    public boolean useShapeForLightOcclusion(BlockState p_56967_) {
        return true;
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> def) {
        super.createBlockStateDefinition(def);
        def.add(WATERLOGGED);
    }

    @Override
    public boolean hasDynamicShape() {
        return true;
    }
}
