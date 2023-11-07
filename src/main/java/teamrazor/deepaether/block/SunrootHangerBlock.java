package teamrazor.deepaether.block;

import com.aetherteam.aether.block.AetherBlockStateProperties;
import com.aetherteam.aether.block.natural.AetherLogBlock;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.RandomSource;
import net.minecraft.world.item.AxeItem;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.*;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.material.Fluids;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.minecraftforge.common.ToolAction;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import teamrazor.deepaether.block.Behaviors.DABlockStateProperties;
import teamrazor.deepaether.init.DABlocks;

public class SunrootHangerBlock extends Block {

    protected static final VoxelShape SHAPE_0 = Block.box(4.0D, 0.0D, 4.0D, 12.0D, 16.0D, 12.0D);
    protected static final VoxelShape SHAPE_1 = Block.box(6.0D, 0.0D, 6.0D, 10.0D, 16.0D, 10.0D);
    protected static final VoxelShape SHAPE_2 = Block.box(6.0D, 2.0D, 6.0D, 10.0D, 14.0D, 10.0D);


    public static final IntegerProperty THREE_SHAPES = DABlockStateProperties.THREE_SHAPES;
    public SunrootHangerBlock(Properties properties) {
        super(properties);
        this.registerDefaultState(this.stateDefinition.any().setValue(THREE_SHAPES, Integer.valueOf(0)));
    }

    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> p_261641_) {
        p_261641_.add(THREE_SHAPES);
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

        if(level.getBlockState(pos.below()).is(this)) {
            if(level.getBlockState(pos.above()).getBlock() != this) {
                return state.setValue(SunrootHangerBlock.THREE_SHAPES, 0);
            }
            else return state.setValue(SunrootHangerBlock.THREE_SHAPES, 1);
        }
        else if(level.getBlockState(pos.below()).getBlock() != this && level.getBlockState(pos.above()).getBlock() != this) {
            return state.setValue(SunrootHangerBlock.THREE_SHAPES, 0);
        }
        else return state.setValue(SunrootHangerBlock.THREE_SHAPES, 2);
    }

    @Override
    public BlockState getStateForPlacement(BlockPlaceContext context) {
        FluidState fluidstate = context.getLevel().getFluidState(context.getClickedPos());
        if (!fluidstate.isEmpty()) {
            return null;
        } else {
            BlockPos pos = context.getClickedPos();
            BlockState blockstate = context.getLevel().getBlockState(pos.above());

            if (blockstate.isFaceSturdy(context.getLevel(), pos, Direction.UP) || blockstate.is(BlockTags.LEAVES)) {
                return this.defaultBlockState().setValue(THREE_SHAPES, 0);
            } else if (blockstate.is(this)) {
                return this.defaultBlockState().setValue(THREE_SHAPES, 2);
            } else {
                return null;
            }
        }
    }

    @Override
    public void tick(BlockState state, ServerLevel level, BlockPos pos, RandomSource random) {
        if (!state.canSurvive(level, pos)) {
            level.destroyBlock(pos, true);
        }
    }

    @Override
    public VoxelShape getShape(BlockState state, BlockGetter getter, BlockPos pos, CollisionContext context) {
        if(state.getValue(THREE_SHAPES) == 0) {
            return SHAPE_0;
        }
        else if(state.getValue(THREE_SHAPES) == 1) {
            return SHAPE_1;
        }
        else return SHAPE_2;
    }

    @Override
    public int getLightEmission(BlockState state, BlockGetter level, BlockPos pos) {
        return 10;
    }

    @Override
    public void animateTick(BlockState p_221107_, Level p_221108_, BlockPos p_221109_, RandomSource p_221110_) {
        Direction direction = Direction.DOWN;
        double d0 = (double)p_221109_.getX() + 0.55D - (double)(p_221110_.nextFloat() * 0.1F);
        double d1 = (double)p_221109_.getY() + 0.55D - (double)(p_221110_.nextFloat() * 0.1F);
        double d2 = (double)p_221109_.getZ() + 0.55D - (double)(p_221110_.nextFloat() * 0.1F);
        double d3 = 0.4F - (p_221110_.nextFloat() + p_221110_.nextFloat()) * 0.4F;
        if (p_221110_.nextInt(20) == 0) {
            p_221108_.addParticle(ParticleTypes.END_ROD, d0 + (double)direction.getStepX() * d3, d1 + (double)direction.getStepY() * d3, d2 + (double)direction.getStepZ() * d3, p_221110_.nextGaussian() * 0.005D, p_221110_.nextGaussian() * 0.005D, p_221110_.nextGaussian() * 0.005D);
        }

    }
}