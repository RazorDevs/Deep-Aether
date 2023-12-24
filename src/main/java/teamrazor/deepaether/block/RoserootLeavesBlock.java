package teamrazor.deepaether.block;

import com.aetherteam.aether.block.natural.AetherDoubleDropsLeaves;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;

public class RoserootLeavesBlock extends AetherDoubleDropsLeaves {
    public RoserootLeavesBlock(Properties properties) {
        super(properties);
    }

    @Override
    public void animateTick(BlockState blockState, Level level, BlockPos blockPos, RandomSource randomSource) {
        super.animateTick(blockState, level, blockPos, randomSource);
        if (randomSource.nextInt(10) == 0) {
            BlockPos blockpos = blockPos.below();
            BlockState blockstate = level.getBlockState(blockpos);
            if (!isFaceFull(blockstate.getCollisionShape(level, blockpos), Direction.UP)) {
                //ParticleUtils.spawnParticleOnFace(level, blockPos, randomSource, DAParticles.ROSEROOT_LEAVES.get());
            }
        }
    }
}
