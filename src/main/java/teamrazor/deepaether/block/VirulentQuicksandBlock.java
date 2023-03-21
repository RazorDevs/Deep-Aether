package teamrazor.deepaether.block;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.particles.BlockParticleOption;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.item.FallingBlockEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.Fallable;
import net.minecraft.world.level.block.PowderSnowBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.Material;
import net.minecraft.world.phys.Vec3;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.EntityCollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.jetbrains.annotations.NotNull;
import teamrazor.deepaether.init.DAItems;

import java.util.Optional;

public class VirulentQuicksandBlock extends PowderSnowBlock implements Fallable {
    private static final float HORIZONTAL_PARTICLE_MOMENTUM_FACTOR = 0.083333336F;
    private static final float IN_BLOCK_HORIZONTAL_SPEED_MULTIPLIER = 0.9F;
    private static final float IN_BLOCK_VERTICAL_SPEED_MULTIPLIER = 1.5F;
    private static final float NUM_BLOCKS_TO_FALL_INTO_BLOCK = 2.5F;
    private static final VoxelShape FALLING_COLLISION_SHAPE = Shapes.box(0.0D, 0.0D, 0.0D, 1.0D, (double)0.9F, 1.0D);
    private static final double MINIMUM_FALL_DISTANCE_FOR_SOUND = 4.0D;
    private static final double MINIMUM_FALL_DISTANCE_FOR_BIG_SOUND = 7.0D;

    public VirulentQuicksandBlock(Properties properties) {
        super(properties);
    }

    public void onPlace(BlockState p_53233_, Level p_53234_, BlockPos p_53235_, BlockState p_53236_, boolean p_53237_) {
        p_53234_.scheduleTick(p_53235_, this, this.getDelayAfterPlace());
    }

    public BlockState updateShape(BlockState p_53226_, Direction p_53227_, BlockState p_53228_, LevelAccessor p_53229_, BlockPos p_53230_, BlockPos p_53231_) {
        p_53229_.scheduleTick(p_53230_, this, this.getDelayAfterPlace());
        return super.updateShape(p_53226_, p_53227_, p_53228_, p_53229_, p_53230_, p_53231_);
    }

    public void tick(BlockState blockState, ServerLevel serverLevel, BlockPos blockPos, RandomSource randomSource) {
        if (isFree(serverLevel.getBlockState(blockPos.below())) && blockPos.getY() >= serverLevel.getMinBuildHeight()) {
            FallingBlockEntity fallingblockentity = FallingBlockEntity.fall(serverLevel, blockPos, blockState);
            this.falling(fallingblockentity);
        }
    }

    protected void falling(FallingBlockEntity blockEntity) {
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
                level.addParticle(new BlockParticleOption(ParticleTypes.FALLING_DUST, blockState), d0, d1, d2, 0.0D, 0.0D, 0.0D);
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
            int h = (int) entity.getEyeHeight();
            if((entity.getY()+h) == pos.getY() && entity instanceof LivingEntity) {
                ((LivingEntity) entity).addEffect(new MobEffectInstance(MobEffects.BLINDNESS, 20, 4, false, false, false));
                entity.hurt(DamageSource.IN_WALL, 1.5f);
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

    public @NotNull VoxelShape getCollisionShape(@NotNull BlockState blockState, @NotNull BlockGetter blockGetter, @NotNull BlockPos blockPos, @NotNull CollisionContext context) {
        if (context instanceof EntityCollisionContext entitycollisioncontext) {
            Entity entity = entitycollisioncontext.getEntity();
            if (entity != null) {
                if (entity.fallDistance > 2.5F) {
                    return FALLING_COLLISION_SHAPE;
                }

                boolean flag = entity instanceof FallingBlockEntity;
                if (flag || canEntityWalkOnPowderSnow(entity) && context.isAbove(Shapes.block(), blockPos, false) && !context.isDescending()) {
                    return super.getCollisionShape(blockState, blockGetter, blockPos, context);
                }
            }
        }

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
