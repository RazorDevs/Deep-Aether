package io.github.razordevs.deep_aether.block.natural;

import com.mojang.serialization.MapCodec;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.item.FallingBlockEntity;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Fallable;
import net.minecraft.world.level.block.SimpleWaterloggedBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.properties.DirectionProperty;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.material.Fluids;
import net.minecraft.world.phys.Vec3;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;

import javax.annotation.Nullable;

public class PointedHolystoneBlock extends Block implements Fallable, SimpleWaterloggedBlock {
    public static final MapCodec<PointedHolystoneBlock> CODEC = simpleCodec(PointedHolystoneBlock::new);
    public static final DirectionProperty TIP_DIRECTION = BlockStateProperties.VERTICAL_DIRECTION;
    public static final BooleanProperty WATERLOGGED = BlockStateProperties.WATERLOGGED;

    private static final VoxelShape TIP_SHAPE_UP = Block.box(5.0F, 0.0F, 5.0F, 11.0F, 11.0F, 11.0F);
    private static final VoxelShape TIP_SHAPE_DOWN = Block.box(5.0F, 5.0F, 5.0F, 11.0F, 16.0F, 11.0F);

    public PointedHolystoneBlock(Properties properties) {
        super(properties);
        this.registerDefaultState(this.stateDefinition.any().setValue(TIP_DIRECTION, Direction.UP).setValue(WATERLOGGED, false));
    }

    public MapCodec<PointedHolystoneBlock> codec() {
        return CODEC;
    }

    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(TIP_DIRECTION, WATERLOGGED);
    }

    protected boolean canSurvive(BlockState blockState, LevelReader levelReader, BlockPos blockPos) {
        return isValidPointedDripstonePlacement(levelReader, blockPos, blockState.getValue(TIP_DIRECTION));
    }

    private static boolean isValidPointedDripstonePlacement(LevelReader levelReader, BlockPos blockPos, Direction direction) {
        BlockPos blockpos = blockPos.relative(direction.getOpposite());
        BlockState blockState = levelReader.getBlockState(blockpos);
        return blockState.isFaceSturdy(levelReader, blockpos, direction);
    }


    @Nullable
    public BlockState getStateForPlacement(BlockPlaceContext placeContext) {
        LevelAccessor levelaccessor = placeContext.getLevel();
        BlockPos blockpos = placeContext.getClickedPos();
        Direction direction = placeContext.getNearestLookingVerticalDirection().getOpposite();
        Direction direction1 = calculateTipDirection(levelaccessor, blockpos, direction);
        if (direction1 == null) {
            return null;
        } else {
            return this.defaultBlockState().setValue(TIP_DIRECTION, direction1).setValue(WATERLOGGED, levelaccessor.getFluidState(blockpos).getType() == Fluids.WATER);
        }
    }

    @Nullable
    private static Direction calculateTipDirection(LevelReader levelReader, BlockPos blockPos, Direction direction1) {
        Direction direction;
        if (isValidPointedDripstonePlacement(levelReader, blockPos, direction1)) {
            direction = direction1;
        } else {
            if (!isValidPointedDripstonePlacement(levelReader, blockPos, direction1.getOpposite())) {
                return null;
            }
            direction = direction1.getOpposite();
        }
        return direction;
    }

    protected FluidState getFluidState(BlockState blockState) {
        return blockState.getValue(WATERLOGGED) ? Fluids.WATER.getSource(false) : super.getFluidState(blockState);
    }

    protected VoxelShape getOcclusionShape(BlockState blockState, BlockGetter blockGetter, BlockPos blockPos) {
        return Shapes.empty();
    }

    protected VoxelShape getShape(BlockState blockState, BlockGetter blockGetter, BlockPos blockPos, CollisionContext collisionContext) {
        VoxelShape voxelshape;

        if (blockState.getValue(TIP_DIRECTION) == Direction.DOWN) {
            voxelshape = TIP_SHAPE_DOWN;
        } else {
            voxelshape = TIP_SHAPE_UP;
        }

        Vec3 vec3 = blockState.getOffset(blockGetter, blockPos);
        return voxelshape.move(vec3.x, 0.0F, vec3.z);
    }

    protected boolean isCollisionShapeFullBlock(BlockState p_181235_, BlockGetter p_181236_, BlockPos p_181237_) {
        return false;
    }

    protected BlockState updateShape(BlockState blockState, Direction direction1, BlockState blockState1, LevelAccessor levelAccessor, BlockPos blockPos, BlockPos blockPos1) {
        if (blockState.getValue(WATERLOGGED)) {
            levelAccessor.scheduleTick(blockPos, Fluids.WATER, Fluids.WATER.getTickDelay(levelAccessor));
        }

        if (direction1 != Direction.UP && direction1 != Direction.DOWN) {
            return blockState;
        } else {
            Direction direction = blockState.getValue(TIP_DIRECTION);
            if (direction == Direction.DOWN && levelAccessor.getBlockTicks().hasScheduledTick(blockPos, this)) {
                return blockState;
            } else if (direction1 == direction.getOpposite() && !this.canSurvive(blockState, levelAccessor, blockPos)) {
                if (direction == Direction.DOWN) {
                    levelAccessor.scheduleTick(blockPos, this, 2);
                } else {
                    levelAccessor.scheduleTick(blockPos, this, 1);
                }

                return blockState;
            }
        }
        return blockState;
    }

    protected float getMaxHorizontalOffset() {
        return 0.125F;
    }

    public void onBrokenAfterFall(Level level, BlockPos blockPos, FallingBlockEntity blockEntity) {
        if (!blockEntity.isSilent()) {
            level.levelEvent(1045, blockPos, 0);
        }
    }

    public void fallOn(Level level, BlockState blockState, BlockPos blockPos, Entity entity, float fallDamage) {
        if (blockState.getValue(TIP_DIRECTION) == Direction.UP) {
            entity.causeFallDamage(fallDamage + 2.0F, 2.0F, level.damageSources().stalagmite());
        } else {
            super.fallOn(level, blockState, blockPos, entity, fallDamage);
        }
    }

    public DamageSource getFallDamageSource(Entity entity) {
        return entity.damageSources().fallingStalactite(entity);
    }

    protected void tick(BlockState blockState, ServerLevel serverLevel, BlockPos blockPos, RandomSource random) {
        if (!this.canSurvive(blockState, serverLevel, blockPos)) {
            serverLevel.destroyBlock(blockPos, true);
        } else {
            spawnFallingStalactite(blockState, serverLevel, blockPos);
        }
    }

    private static void spawnFallingStalactite(BlockState blockState, ServerLevel serverLevel, BlockPos blockPos) {
        BlockPos.MutableBlockPos blockpos$mutableblockpos = blockPos.mutable();

            FallingBlockEntity fallingblockentity = FallingBlockEntity.fall(serverLevel, blockpos$mutableblockpos, blockState);
            int i = Math.max(1 + blockPos.getY() - blockpos$mutableblockpos.getY(), 6);
            float f = (float) i;
            fallingblockentity.setHurtsEntities(f, 40);
    }
}
