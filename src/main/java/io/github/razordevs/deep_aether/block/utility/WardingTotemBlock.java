package io.github.razordevs.deep_aether.block.utility;

import com.aetherteam.aether.data.resources.registries.AetherDimensions;
import com.aetherteam.aether.item.AetherItems;
import com.mojang.serialization.MapCodec;
import io.github.razordevs.deep_aether.block.building.TotemBlock;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.ItemInteractionResult;
import net.minecraft.world.entity.ai.targeting.TargetingConditions;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.HorizontalDirectionalBlock;
import net.minecraft.world.level.block.Mirror;
import net.minecraft.world.level.block.Rotation;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.properties.DirectionProperty;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.BlockHitResult;

public class WardingTotemBlock extends TotemBlock {
    public static final MapCodec<WardingTotemBlock> CODEC = simpleCodec(WardingTotemBlock::new);
    public static final DirectionProperty FACING = BlockStateProperties.HORIZONTAL_FACING;
    public static final BooleanProperty ACTIVE = BooleanProperty.create("active");

    public WardingTotemBlock(Properties properties) {
        super(properties);
        this.registerDefaultState(this.stateDefinition.any().setValue(FACING, Direction.NORTH).setValue(ACTIVE, false));
    }

    @Override
    protected MapCodec<? extends HorizontalDirectionalBlock> codec() {
        return CODEC;
    }

    @Override
    protected ItemInteractionResult useItemOn(ItemStack itemStack, BlockState blockState, Level level, BlockPos blockPos, Player player, InteractionHand hand, BlockHitResult hitResult) {
        if(!itemStack.is(AetherItems.GOLDEN_AMBER.get())) {
            return super.useItemOn(itemStack, blockState, level, blockPos, player, hand, hitResult);
        }
        blockState.setValue(ACTIVE, true);
        return ItemInteractionResult.SUCCESS;
    }

    @Override
    protected InteractionResult useWithoutItem(BlockState blockState, Level level, BlockPos blockPos, Player player, BlockHitResult hitResult) {
        if(!blockState.getValue(ACTIVE)) {
            return super.useWithoutItem(blockState, level, blockPos, player, hitResult);
        }
        blockState.setValue(ACTIVE, false);
        return InteractionResult.SUCCESS_NO_ITEM_USED;
    }

    @Override
    protected void tick(BlockState blockState, ServerLevel serverLevel, BlockPos blockPos, RandomSource randomSource) {
        if(blockState.getValue(ACTIVE) && serverLevel.dimension().equals(AetherDimensions.AETHER_LEVEL)) {
            var bound = new AABB(blockPos).expandTowards(15d, 15d, 15d);
            var entity = serverLevel.getNearestEntity(Monster.class, TargetingConditions.DEFAULT, null, blockPos.getX(), blockPos.getY(), blockPos.getZ(), bound);

            if(entity != null && serverLevel.getGameTime() % 40 == 0) {
                serverLevel.sendParticles(ParticleTypes.LARGE_SMOKE, entity.getX(), entity.getY() + 1, entity.getZ(), 1, 0d, 1d, 0d, 0.5d);
                entity.hurt(entity.damageSources().onFire(), 2.0f);
            }
        }
        super.tick(blockState, serverLevel, blockPos, randomSource);
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(FACING, ACTIVE);
    }

    @Override
    public BlockState getStateForPlacement(BlockPlaceContext pContext) {
        return this.defaultBlockState().setValue(FACING, pContext.getHorizontalDirection().getOpposite());
    }

    @Override
    public BlockState rotate(BlockState pState, Rotation pRotation) {
        return pState.setValue(FACING, pRotation.rotate(pState.getValue(FACING)));
    }

    @Override
    public BlockState mirror(BlockState pState, Mirror pMirror) {
        return this.rotate(pState, pMirror.getRotation(pState.getValue(FACING)));
    }
}