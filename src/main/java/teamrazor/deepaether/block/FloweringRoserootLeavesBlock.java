package teamrazor.deepaether.block;

import com.aetherteam.aether.block.natural.AetherDoubleDropsLeaves;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.util.ParticleUtils;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import teamrazor.deepaether.init.DAParticles;

public class FloweringRoserootLeavesBlock extends AetherDoubleDropsLeaves {
    public FloweringRoserootLeavesBlock(Properties properties) {
        super(properties);
    }
    @Override
    public void animateTick(BlockState state, Level level, BlockPos pos, RandomSource random) {
        super.animateTick(state, level, pos, random);
        if (random.nextInt(10) == 0) {
            BlockPos blockpos = pos.below();
            BlockState blockstate = level.getBlockState(blockpos);
            if (!isFaceFull(blockstate.getCollisionShape(level, blockpos), Direction.UP)) {
                ParticleUtils.spawnParticleBelow(level, pos, random, DAParticles.FLOWERING_ROSEROOT_LEAVES.get());
            }
        }
    }
}
