package teamrazor.deepaether.entity.swet;

import com.aetherteam.aether.AetherTags;
import com.aetherteam.aether.entity.monster.Swet;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.world.Difficulty;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.common.ForgeMod;
import org.jetbrains.annotations.NotNull;

import javax.annotation.Nonnull;
import java.util.EnumSet;

public class AercloudSwet extends Swet {

    int dashTimer = 0;
    int gravityTimer = 0;
    Vec3 targetPos = new Vec3(0,0,0);

    public AercloudSwet(EntityType<? extends Swet> type, Level level) {
        super(type, level);
    }


    //@Override
    //public float getJumpPower() {
    //    return 0.75F;
    //}

    @Override
    public double getMountJumpStrength() {
        return 1.5;
    }

    @Override
    public int getJumpDelay() {
        return this.random.nextInt(5) + 5;
    }


    public static boolean checkSwetSpawnRules(EntityType<? extends Swet> swet, LevelAccessor level, MobSpawnType spawnReason, BlockPos pos, RandomSource random) {
        return level.getDifficulty() != Difficulty.PEACEFUL && level.getBlockState(pos.below()).is(AetherTags.Blocks.AERCLOUDS) && level.getRawBrightness(pos, 0) > 8;
    }

    @Override
    protected int calculateFallDamage(float p_21237_, float p_21238_) {
        if(this.isVehicle()) {
            return super.calculateFallDamage(p_21237_, p_21238_);
        }
        else return 0;
    }

    @Nonnull
    public static AttributeSupplier.Builder createMobAttributes() {
        return Mob.createMobAttributes()
                .add(Attributes.MAX_HEALTH, 12.0)
                .add(Attributes.MOVEMENT_SPEED, 0.4)
                .add(Attributes.FOLLOW_RANGE, 14.0)
                .add(Attributes.FOLLOW_RANGE, 48)
                .add(ForgeMod.ENTITY_GRAVITY.get(), 0.03D);
    }

    @Override
    protected void registerGoals() {
        this.goalSelector.addGoal(0, new ConsumeGoal(this));
        this.goalSelector.addGoal(1, new HuntAndDashGoal(this));
        this.goalSelector.addGoal(2, new SwetRandomDirectionGoal(this));
        this.goalSelector.addGoal(4, new SwetKeepOnJumpingGoal(this));
        this.targetSelector.addGoal(1, new NearestAttackableTargetGoal<>(this, Player.class, true, (target) -> !this.isFriendlyTowardEntity(target) && !(target.getRootVehicle() instanceof Swet)));
    }

    @Override
    public void tick() {
        super.tick();
        if (this.dashTimer > 0) {
            this.dashTimer--;
        }

        if (this.gravityTimer > 0) {
            this.gravityTimer--;
        }

        if (this.gravityTimer <= 0 || !this.getFeetBlockState().isAir() || this.hasPrey()) {
            this.setNoGravity(false);
            gravityTimer = 0;
        }

        if(this.isNoGravity()) {
            if (this.getTarget() != null) {
                this.setDeltaMovement(targetPos);
            }
        }
    }

    public BlockState getBlockStateFromPos(BlockPos pos) {
        return this.level.getBlockState(pos);
    }


    public static class HuntAndDashGoal extends Goal {
        private final AercloudSwet swet;
        boolean canUse = true;

        public HuntAndDashGoal(AercloudSwet swet) {
            this.swet = swet;
            this.setFlags(EnumSet.of(Goal.Flag.LOOK));
        }


        @Override
        public boolean canUse() {
            LivingEntity target = this.swet.getTarget();
            if (this.swet.hasPrey() || target == null || !target.isAlive() || this.swet.isFriendlyTowardEntity(target) || (target instanceof Player player && player.getAbilities().invulnerable)) {
                return false;
            } else {
                return this.swet.getMoveControl() instanceof SwetMoveControl;
            }
        }

        @Override
        public boolean canContinueToUse() {
            LivingEntity target = this.swet.getTarget();
            if (swet.hasPrey() || target == null || !target.isAlive()) {
                return false;
            } else if (target instanceof Player player && player.getAbilities().invulnerable) {
                return false;
            } else {
                return !this.swet.isFriendlyTowardEntity(target);
            }
        }

        @Override
        public void tick() {
            LivingEntity target = this.swet.getTarget();
            if (target != null) {
                this.swet.lookAt(target, 10.0F, 10.0F);
                ((SwetMoveControl) this.swet.getMoveControl()).setDirection(this.swet.getYRot(), true);


                if (swet.dashTimer <= 0) {
                    this.canUse = true;
                    for (int i = 0; i < 2; i++) {
                        BlockPos pos = this.swet.getOnPos().below(i);

                        if (!this.swet.getBlockStateFromPos(pos).isAir()) {
                            canUse = false;
                        }
                    }
                } else canUse = false;

                if (this.canUse) {
                    this.swet.setNoGravity(true);
                    swet.gravityTimer = 100;
                    swet.dashTimer = swet.random.nextInt(500);
                    Vec3 pos = target.position().subtract(swet.position());
                    swet.targetPos = pos.scale(0.15D).add(0, 0.02D, 0).offsetRandom(swet.random, 0.1F);

                    double d = (float) swet.getX() + (swet.random.nextFloat() - swet.random.nextFloat()) * 0.3F;
                    double d1 = (float) swet.getY() + swet.getBbHeight();
                    double d2 = (float) swet.getZ() + (swet.random.nextFloat() - swet.random.nextFloat()) * 0.3F;
                    swet.level.addParticle(ParticleTypes.POOF, d, d1 - 0.25, d2, 0.0, 0.0, 0.0);
                }

                if (this.swet.getBoundingBox().intersects(target.getBoundingBox())) {
                    this.swet.consumePassenger(target);
                }

            }
        }
    }
}