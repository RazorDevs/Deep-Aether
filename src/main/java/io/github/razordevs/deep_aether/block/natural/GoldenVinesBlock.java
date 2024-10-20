package io.github.razordevs.deep_aether.block.natural;

import com.mojang.serialization.MapCodec;
import io.github.razordevs.deep_aether.block.behavior.GoldenVines;
import io.github.razordevs.deep_aether.datagen.tags.DATags;
import io.github.razordevs.deep_aether.init.DABlocks;
import io.github.razordevs.deep_aether.init.DAItems;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.BonemealableBlock;
import net.minecraft.world.level.block.GrowingPlantHeadBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.HitResult;
import net.neoforged.neoforge.common.CommonHooks;

public class GoldenVinesBlock extends GrowingPlantHeadBlock implements BonemealableBlock, GoldenVines {
    public static final MapCodec<GoldenVinesBlock> CODEC = simpleCodec(GoldenVinesBlock::new);

    public GoldenVinesBlock(BlockBehaviour.Properties properties) {
        super(properties, Direction.UP, SHAPE, false, 0.02D);
        this.registerDefaultState(this.stateDefinition.any().setValue(AGE, 0).setValue(BERRIES, Boolean.FALSE));
    }

    @Override
    protected int getBlocksToGrowWhenBonemealed(RandomSource random) {
        return 1;
    }

    @Override
    protected boolean canGrowInto(BlockState state) {
        return state.isAir();
    }

    @Override
    protected Block getBodyBlock() {
        return DABlocks.GOLDEN_VINES_PLANT.get();
    }

    @Override
    protected BlockState updateBodyAfterConvertedFromHead(BlockState state, BlockState state2) {
        return state2.setValue(BERRIES, state.getValue(BERRIES));
    }

    @Override
    protected BlockState getGrowIntoState(BlockState state, RandomSource random) {
        return super.getGrowIntoState(state, random).setValue(BERRIES, random.nextFloat() < 0.5F);
    }

    @Override
    public ItemStack getCloneItemStack(BlockState state, HitResult target, LevelReader level, BlockPos pos, Player player) {
        return new ItemStack(DAItems.GOLDEN_BERRIES.get());
    }


    @Override
    protected InteractionResult useWithoutItem(BlockState state, Level level, BlockPos pos, Player player, BlockHitResult hitResult) {
        return GoldenVines.use(player, state, level, pos);
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> state) {
        super.createBlockStateDefinition(state);
        state.add(BERRIES);
    }

    @Override
    public boolean isValidBonemealTarget(LevelReader level, BlockPos pos, BlockState state) {
        return !state.getValue(BERRIES);
    }

    @Override
    public boolean isBonemealSuccess(Level level, RandomSource random, BlockPos pos, BlockState state) {
        return true;
    }

    @Override
    public void performBonemeal(ServerLevel p_220923_, RandomSource random, BlockPos pos, BlockState p_220926_) {
        p_220923_.setBlock(pos, p_220926_.setValue(BERRIES, Boolean.TRUE), 2);
    }

    @Override
    protected MapCodec<? extends GrowingPlantHeadBlock> codec() {
        return CODEC;
    }

    @Override
    public void randomTick(BlockState state, ServerLevel level, BlockPos pos, RandomSource random) {
        if (state.getValue(AGE) < 25 && CommonHooks.canCropGrow(level, pos.relative(this.growthDirection), level.getBlockState(pos.relative(this.growthDirection)),random.nextDouble() < 0.02D) && this.canSurvive(state, level, pos.above(1))) {
            BlockPos blockpos = pos.relative(this.growthDirection);
            if (this.canGrowInto(level.getBlockState(blockpos))) {
                level.setBlockAndUpdate(blockpos, this.getGrowIntoState(state, level.random));
                CommonHooks.fireCropGrowPost(level, blockpos, level.getBlockState(blockpos));
            }
        }

    }

    @Override
    public boolean canSurvive(BlockState state, LevelReader level, BlockPos pos) {
        boolean HasValidHightAndBlock = false;
        Block block;
        for (int i = 1; i < 5; i++) {
            block = level.getBlockState(pos.below(i)).getBlock();
            if (block.defaultBlockState().is(DATags.Blocks.CAN_GOLDEN_VINES_SURVIVE_ON)) {
                HasValidHightAndBlock = true;
            }
        }

        BlockPos blockpos = pos.relative(this.growthDirection.getOpposite());
        BlockState blockstate = level.getBlockState(blockpos);
        if (!this.canAttachTo(blockstate) || !HasValidHightAndBlock) {
            return false;
        } else {
            return blockstate.is(this.getHeadBlock()) || blockstate.is(this.getBodyBlock()) || blockstate.isFaceSturdy(level, blockpos, this.growthDirection);
        }
    }
}