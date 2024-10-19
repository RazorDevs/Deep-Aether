package io.github.razordevs.deepaether.deepaether.block.natural;

import com.aetherteam.aether.block.natural.AetherDoubleDropsLeaves;
import io.github.razordevs.deepaether.deepaether.init.DAParticles;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.util.ParticleUtils;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;

public class RoserootLeavesBlock extends AetherDoubleDropsLeaves {
    public RoserootLeavesBlock(Properties properties) {
        super(properties);
    }

    @Override
    public void animateTick(BlockState state, Level level, BlockPos pos, RandomSource random) {
        super.animateTick(state, level, pos, random);
        if (random.nextInt(10) == 0) {
            BlockPos blockpos = pos.below();
            BlockState blockstate = level.getBlockState(blockpos);
            if (!isFaceFull(blockstate.getCollisionShape(level, blockpos), Direction.UP)) {
                ParticleUtils.spawnParticleBelow(level, pos, random, DAParticles.ROSEROOT_LEAVES.get());
            }
        }
    }
}
