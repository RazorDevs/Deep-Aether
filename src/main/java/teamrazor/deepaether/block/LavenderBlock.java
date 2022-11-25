package teamrazor.deepaether.block;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.item.AxeItem;
import net.minecraft.world.item.BoneMealItem;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.BonemealableBlock;
import net.minecraft.world.level.block.FlowerBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.common.ToolAction;
import net.minecraftforge.event.entity.player.BonemealEvent;
import org.jetbrains.annotations.Nullable;
import teamrazor.deepaether.init.DeepAetherModBlocks;

public class LavenderBlock extends FlowerBlock implements BonemealableBlock {


    public LavenderBlock(MobEffect p_53512_, int p_53513_, Properties p_53514_) {
        super(p_53512_, p_53513_, p_53514_);
    }

/*
    @Nullable
    @Override
    public BlockState getToolModifiedState(BlockState state, UseOnContext context, BonemealEvent bonemealEvent, boolean simulate) {
        if (context.getItemInHand().getItem() instanceof BoneMealItem) {
            if (state.is(DeepAetherModBlocks.AERLAVENDER.get())) {
                return DeepAetherModBlocks.TALL_AERLAVENDER.get().defaultBlockState();
            }
        }
        return super.getToolModifiedState(state, context, bonemealEvent, simulate);
    }
*/
    @Override
    public boolean isValidBonemealTarget(BlockGetter p_50897_, BlockPos p_50898_, BlockState p_50899_, boolean p_50900_) {
        return true;
    }

    @Override
    public boolean isBonemealSuccess(Level p_220878_, RandomSource p_220879_, BlockPos p_220880_, BlockState p_220881_) {
        return true;
    }

    @Nullable
    @Override
    public void performBonemeal(ServerLevel p_220874_, RandomSource p_220875_, BlockPos p_220876_, BlockState p_220877_) {
        if (p_220877_.is(DeepAetherModBlocks.AERLAVENDER.get())) {
            p_220874_.setBlock(p_220876_,DeepAetherModBlocks.TALL_AERLAVENDER.get().defaultBlockState(), 4);
        }
    }
}
