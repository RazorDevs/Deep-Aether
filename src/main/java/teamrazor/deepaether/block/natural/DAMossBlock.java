package teamrazor.deepaether.block.natural;

import com.aetherteam.aether.block.AetherBlockStateProperties;
import com.aetherteam.aether.block.natural.AetherDoubleDropBlock;
import net.minecraft.core.BlockPos;
import net.minecraft.core.registries.Registries;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelReader;
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
    public boolean isValidBonemealTarget(LevelReader p_256559_, BlockPos p_50898_, BlockState p_50899_) {
        return p_256559_.getBlockState(p_50898_.above()).isAir();
    }

    @Override
    public boolean isBonemealSuccess(Level pLevel, RandomSource pRandom, BlockPos pPos, BlockState pState) {
        return true;
    }

    @Override
    public void performBonemeal(ServerLevel pLevel, RandomSource pRanfom, BlockPos pPos, BlockState pState) {
        pLevel.registryAccess().registry(Registries.CONFIGURED_FEATURE).flatMap((p_258973_) -> {
            return p_258973_.getHolder(DAConfiguredFeatures.AETHER_MOSS_PATCH_BONEMEAL);
        }).ifPresent((p_255669_) -> {
            p_255669_.value().place(pLevel, pLevel.getChunkSource().getGenerator(), pRanfom, pPos.above());
        });
    }
}