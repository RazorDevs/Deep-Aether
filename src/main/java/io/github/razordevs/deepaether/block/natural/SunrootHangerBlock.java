package io.github.razordevs.deepaether.deepaether.block.natural;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.RandomSource;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;

public class SunrootHangerBlock extends Block {
    protected static final VoxelShape SHAPE = Block.box(4.0D, 0.0D, 4.0D, 12.0D, 16.0D, 12.0D);
    public static final BooleanProperty BOTTOM = BlockStateProperties.BOTTOM;
    public SunrootHangerBlock(Properties properties) {
        super(properties);
        this.registerDefaultState(this.stateDefinition.any().setValue(BOTTOM, false));
    }

    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> state) {
        state.add(BOTTOM);
    }



    @Override
    public boolean canSurvive(BlockState state, LevelReader level, BlockPos pos) {
        BlockState blockState = level.getBlockState(pos.above());
        return blockState.is(this) || blockState.isFaceSturdy(level, pos, Direction.UP) || blockState.is(BlockTags.LEAVES);
    }

    @Override
    public BlockState updateShape(BlockState state, Direction direction, BlockState p_154149_, LevelAccessor level, BlockPos pos, BlockPos p_154152_) {
        if (!state.canSurvive(level, pos)) {
            level.scheduleTick(pos, this, 1);
        }

        if(level.getBlockState(pos.below()).is(this))
            return state.setValue(SunrootHangerBlock.BOTTOM, false);

        else return state.setValue(SunrootHangerBlock.BOTTOM, true);
    }

    @Override
    public BlockState getStateForPlacement(BlockPlaceContext context) {
        FluidState fluidstate = context.getLevel().getFluidState(context.getClickedPos());
        BlockPos pos = context.getClickedPos();
        BlockState blockstate = context.getLevel().getBlockState(pos.above());

        if (fluidstate.isEmpty() && (blockstate.isFaceSturdy(context.getLevel(), pos, Direction.UP) || blockstate.is(BlockTags.LEAVES) || blockstate.is(this)))
            return this.defaultBlockState().setValue(BOTTOM, true);
        else
            return null;
    }

    @Override
    public void tick(BlockState state, ServerLevel level, BlockPos pos, RandomSource random) {
        if (!state.canSurvive(level, pos)) {
            level.destroyBlock(pos, true);
        }
    }

    @Override
    public VoxelShape getShape(BlockState state, BlockGetter getter, BlockPos pos, CollisionContext context) {
        return SHAPE;
    }
    @Override
    public void animateTick(BlockState state, Level level, BlockPos pos, RandomSource random) {
        Direction direction = Direction.DOWN;
        double d0 = (double)pos.getX() + 0.55D - (double)(random.nextFloat() * 0.1F);
        double d1 = (double)pos.getY() + 0.55D - (double)(random.nextFloat() * 0.1F);
        double d2 = (double)pos.getZ() + 0.55D - (double)(random.nextFloat() * 0.1F);
        double d3 = 0.4F - (random.nextFloat() + random.nextFloat()) * 0.4F;
        if (random.nextInt(5) == 1) {
            level.addParticle(ParticleTypes.END_ROD, d0 + (double)direction.getStepX() * d3, d1 + (double)direction.getStepY() * d3, d2 + (double)direction.getStepZ() * d3, random.nextGaussian() * 0.005D, random.nextGaussian() * 0.005D, random.nextGaussian() * 0.005D);
        }
    }
}