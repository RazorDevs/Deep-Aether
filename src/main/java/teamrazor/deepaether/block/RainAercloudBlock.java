package teamrazor.deepaether.block;

import com.aetherteam.aether.block.FrictionCapped;
import com.aetherteam.aether.block.natural.AercloudBlock;
import com.aetherteam.aether.entity.monster.Swet;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.Nullable;
import teamrazor.deepaether.entity.swet.AercloudSwet;
import teamrazor.deepaether.init.DAEntities;

public class RainAercloudBlock extends AercloudBlock {
    public RainAercloudBlock(Properties properties) {
        super(properties);
    }


    @Override
    public void entityInside(BlockState state, Level level, BlockPos pos, Entity entity) {
        entity.resetFallDistance();
        if (entity.getDeltaMovement().y < 0.0) {
            entity.setDeltaMovement(entity.getDeltaMovement().multiply(1.0, 0.005, 1.0));
        }
        if (entity instanceof LivingEntity livingEntity && (!(livingEntity instanceof Player player) || !player.getAbilities().flying)) {
            entity.setOnGround(true);
        }
    }

    //From WerSpongeBlock
    @Override
    public void animateTick(BlockState state, Level level, BlockPos pos, RandomSource random) {
        Direction direction = Direction.getRandom(random);
        if (direction != Direction.UP) {
            BlockPos blockpos = pos.relative(direction);
            BlockState blockstate = level.getBlockState(blockpos);
            if (!state.canOcclude() || !blockstate.isFaceSturdy(level, blockpos, direction.getOpposite())) {
                double d0 = pos.getX();
                double d1 = pos.getY();
                double d2 = pos.getZ();
                if (direction == Direction.DOWN) {
                    d1 -= 0.05D;
                    d0 += random.nextDouble();
                    d2 += random.nextDouble();
                } else {
                    d1 += random.nextDouble() * 0.8D;
                    if (direction.getAxis() == Direction.Axis.X) {
                        d2 += random.nextDouble();
                        if (direction == Direction.EAST) {
                            ++d0;
                        } else {
                            d0 += 0.05D;
                        }
                    } else {
                        d0 += random.nextDouble();
                        if (direction == Direction.SOUTH) {
                            ++d2;
                        } else {
                            d2 += 0.05D;
                        }
                    }
                }

                level.addParticle(ParticleTypes.DRIPPING_WATER, d0, d1, d2, 0.0D, 0.0D, 0.0D);
            }
        }
    }
}
