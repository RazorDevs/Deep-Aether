package teamrazor.deepaether.block;

import com.aetherteam.aether.block.AetherBlockStateProperties;
import com.aetherteam.aether.block.natural.AetherDoubleDropBlock;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Registry;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.BonemealableBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import teamrazor.deepaether.world.feature.DAConfiguredFeatures;

public class DAMossBlock extends AetherDoubleDropBlock implements BonemealableBlock {
    public DAMossBlock(BlockBehaviour.Properties p_153790_) {
        super(p_153790_);
        this.registerDefaultState(this.defaultBlockState().setValue(AetherBlockStateProperties.DOUBLE_DROPS, false));
    }

    @Override
    public boolean isValidBonemealTarget(BlockGetter blockGetter, BlockPos blockPos, BlockState blockState, boolean b) {
        return blockGetter.getBlockState(blockPos.above()).isAir();
    }

    @Override
    public boolean isBonemealSuccess(Level pLevel, RandomSource pRandom, BlockPos pPos, BlockState pState) {
        return true;
    }

    @Override
    public void performBonemeal(ServerLevel pLevel, RandomSource pRanfom, BlockPos pPos, BlockState pState) {
        pLevel.registryAccess().registry(Registry.CONFIGURED_FEATURE_REGISTRY).flatMap((registry) ->
                registry.getHolder(DAConfiguredFeatures.AETHER_MOSS_PATCH_BONEMEAL)).ifPresent((value) ->
                value.value().place(pLevel, pLevel.getChunkSource().getGenerator(), pRanfom, pPos.above()));
    }
}