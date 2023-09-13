package teamrazor.deepaether.block;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.BonemealableBlock;
import net.minecraft.world.level.block.GrowingPlantBodyBlock;
import net.minecraft.world.level.block.GrowingPlantHeadBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.phys.BlockHitResult;
import org.jetbrains.annotations.NotNull;
import teamrazor.deepaether.block.Behaviors.GoldenVines;
import teamrazor.deepaether.datagen.tags.DATags;
import teamrazor.deepaether.init.DABlocks;
import teamrazor.deepaether.init.DAItems;

public class GoldenVinesPlantBlock extends GrowingPlantBodyBlock implements BonemealableBlock, GoldenVines {
    public GoldenVinesPlantBlock(BlockBehaviour.Properties p_153000_) {
        super(p_153000_, Direction.UP, SHAPE, false);
        this.registerDefaultState(this.stateDefinition.any().setValue(BERRIES, Boolean.valueOf(false)));
    }
    @NotNull
    protected GrowingPlantHeadBlock getHeadBlock() {
        return (GrowingPlantHeadBlock) DABlocks.GOLDEN_VINES.get();
    }
    @NotNull
    protected BlockState updateHeadAfterConvertedFromBody(BlockState value, BlockState p_153029_) {
        return p_153029_.setValue(BERRIES, value.getValue(BERRIES));
    }
    @NotNull
    public ItemStack getCloneItemStack(BlockGetter p_153007_, BlockPos p_153008_, BlockState p_153009_) {
        return new ItemStack(DAItems.GOLDEN_BERRIES.get());
    }

    @NotNull
    public InteractionResult use(BlockState blockState, Level level, BlockPos blockPos, Player player, InteractionHand p_153025_, BlockHitResult p_153026_) {
        return GoldenVines.use(player, blockState, level, blockPos);
    }

    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> stateBuilder) {
        stateBuilder.add(BERRIES);
    }

    public boolean isValidBonemealTarget(LevelReader p_255942_, BlockPos p_153012_, BlockState value, boolean p_153014_) {
        return !value.getValue(BERRIES);
    }

    public boolean isBonemealSuccess(Level p_220943_, RandomSource p_220944_, BlockPos p_220945_, BlockState p_220946_) {
        return true;
    }

    public void performBonemeal(ServerLevel serverLevel, RandomSource randomSource, BlockPos blockPos, BlockState p_220941_) {
        serverLevel.setBlock(blockPos, p_220941_.setValue(BERRIES, Boolean.valueOf(true)), 2);
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