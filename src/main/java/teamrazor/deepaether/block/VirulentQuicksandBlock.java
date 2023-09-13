package teamrazor.deepaether.block;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.PowderSnowBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.Material;
import net.minecraft.world.phys.Vec3;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.jetbrains.annotations.NotNull;
import teamrazor.deepaether.init.DAItems;

import java.util.Optional;

public class VirulentQuicksandBlock extends PowderSnowBlock {
    public VirulentQuicksandBlock(Properties properties) {
        super(properties);
    }

    public void onPlace(BlockState blockState, Level level, BlockPos blockPos, BlockState state, boolean b) {
        level.scheduleTick(blockPos, this, this.getDelayAfterPlace());
    }
    @NotNull
    public BlockState updateShape(BlockState blockState, Direction direction, BlockState blockState1, LevelAccessor levelAccessor, BlockPos blockPos, BlockPos blockPos1) {
        levelAccessor.scheduleTick(blockPos, this, this.getDelayAfterPlace());
        return super.updateShape(blockState, direction, blockState1, levelAccessor, blockPos, blockPos1);
    }

    @SuppressWarnings("SameReturnValue")
    protected int getDelayAfterPlace() {
        return 2;
    }

    public static boolean isFree(BlockState blockState) {
        Material material = blockState.getMaterial();
        return blockState.isAir() || blockState.is(BlockTags.FIRE) || material.isLiquid() || material.isReplaceable();
    }

    public void animateTick(@NotNull BlockState blockState, @NotNull Level level, @NotNull BlockPos blockPos, RandomSource randomSource) {
        if (randomSource.nextInt(16) == 0) {
            BlockPos blockpos = blockPos.below();
            if (isFree(level.getBlockState(blockpos))) {
                double d0 = (double)blockPos.getX() + randomSource.nextDouble();
                double d1 = (double)blockPos.getY() - 0.05D;
                double d2 = (double)blockPos.getZ() + randomSource.nextDouble();
                //level.addParticle(new BlockParticleOption(ParticleTypes.FALLING_DUST, blockState), d0, d1, d2, 0.0D, 0.0D, 0.0D);
                level.addParticle(ParticleTypes.WHITE_ASH, d0, d1, d2, 0.1, 0, 0.2);
                level.addParticle(ParticleTypes.WHITE_ASH, d0, d1, d2, 0.2, 0,  0.1);
            }
        }

    }


    @Override
    public void entityInside(BlockState blockState, Level level, BlockPos pos, Entity entity) {
        if (!(entity instanceof LivingEntity) || entity.getFeetBlockState().is(this)) {
            entity.makeStuckInBlock(blockState, new Vec3((double) 0.9F, 1.5D, (double) 0.9F));
            if (level.isClientSide) {
                RandomSource randomsource = level.getRandom();
                boolean flag = entity.xOld != entity.getX() || entity.zOld != entity.getZ();
                if (flag && randomsource.nextBoolean()) {
                    level.addParticle(ParticleTypes.ASH, entity.getX(), (double) (pos.getY() + 1), entity.getZ(), (double) (Mth.randomBetween(randomsource, -1.0F, 1.0F) * 0.083333336F), (double) 0.05F, (double) (Mth.randomBetween(randomsource, -1.0F, 1.0F) * 0.083333336F));
                }
            }
        }
    }

    @Override
    public ItemStack pickupBlock(LevelAccessor accessor, @NotNull BlockPos pos, @NotNull BlockState blockState) {
        accessor.setBlock(pos, Blocks.AIR.defaultBlockState(), 11);
        if (!accessor.isClientSide()) {
            accessor.levelEvent(2001, pos, Block.getId(blockState));
        }

        return new ItemStack(DAItems.VIRULENT_QUICKSAND_BUCKET.get());
    }

    public boolean skipRendering(@NotNull BlockState blockState, BlockState blockState1, @NotNull Direction direction) {
        return blockState1.is(this) || super.skipRendering(blockState, blockState1, direction);
    }

    public @NotNull VoxelShape getOcclusionShape(@NotNull BlockState blockState, @NotNull BlockGetter blockGetter, @NotNull BlockPos blockPos) {
        return Shapes.empty();
    }


    public @NotNull VoxelShape getVisualShape(@NotNull BlockState blockState, @NotNull BlockGetter blockGetter, @NotNull BlockPos blockPos, CollisionContext context) {
        return Shapes.empty();
    }

    @Override
    public @NotNull Optional<SoundEvent> getPickupSound() {
        return Optional.of(SoundEvents.SAND_BREAK);
    }

}
