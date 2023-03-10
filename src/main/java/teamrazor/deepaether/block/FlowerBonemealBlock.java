package teamrazor.deepaether.block;

import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.BonemealableBlock;
import net.minecraft.world.level.block.FlowerBlock;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.Nullable;
import teamrazor.deepaether.init.DABlocks;

public class FlowerBonemealBlock extends FlowerBlock implements BonemealableBlock {



    public FlowerBonemealBlock(java.util.function.Supplier<MobEffect> p_53512_, int p_53513_, Properties p_53514_) {
        super(p_53512_, p_53513_, p_53514_);
    }

    public FlowerBonemealBlock(MobEffect p_53512_, int p_53513_, Properties p_53514_) {
        super(p_53512_, p_53513_, p_53514_);
    }

    @Override
    public boolean isValidBonemealTarget(LevelReader p_256559_, BlockPos p_50898_, BlockState p_50899_, boolean p_50900_) {
        return true;
    }

    @Override
    public boolean isBonemealSuccess(Level p_220878_, RandomSource p_220879_, BlockPos p_220880_, BlockState p_220881_) {
        return true;
    }

    @Nullable
    @Override
    public void performBonemeal(ServerLevel level, RandomSource source, BlockPos pos, BlockState state) {
        if (state.is(DABlocks.AERLAVENDER.get())) {
            level.setBlock(pos, DABlocks.TALL_AERLAVENDER.get().defaultBlockState(), 4);
        }
        if (state.is(DABlocks.AETHER_CATTAILS.get())) {
            level.setBlock(pos, DABlocks.TALL_AETHER_CATTAILS.get().defaultBlockState(), 4);
        }
    }
}
