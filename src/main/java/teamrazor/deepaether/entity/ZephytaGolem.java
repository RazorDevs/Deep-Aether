package teamrazor.deepaether.entity;

import com.aetherteam.aether.client.AetherSoundEvents;
import com.aetherteam.aether.entity.projectile.ZephyrSnowball;
import net.minecraft.core.BlockPos;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.control.MoveControl;
import net.minecraft.world.entity.ai.goal.FollowOwnerGoal;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.ai.goal.target.OwnerHurtByTargetGoal;
import net.minecraft.world.entity.ai.goal.target.OwnerHurtTargetGoal;
import net.minecraft.world.entity.animal.FlyingAnimal;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;
import org.jetbrains.annotations.Nullable;
import software.bernie.geckolib.core.animatable.GeoAnimatable;
import software.bernie.geckolib.core.animatable.instance.AnimatableInstanceCache;
import software.bernie.geckolib.core.animation.AnimatableManager;

import javax.annotation.Nonnull;
import java.util.EnumSet;

public class ZephytaGolem extends TamableAnimal implements FlyingAnimal, GeoAnimatable {

    public static final EntityDataAccessor<Integer> DATA_ATTACK_CHARGE_ID = SynchedEntityData.defineId(ZephytaGolem.class, EntityDataSerializers.INT);
    public int scale;
    public int scaleAdd;
    public float tailRot;
    public float tailRotAdd;

    public ZephytaGolem(EntityType<? extends ZephytaGolem> type, Level level) {
        super(type, level);
        this.moveControl = new ZephytaGolem.MoveHelperController(this);
        this.xpReward = 5;
    }

    @Override
    protected void registerGoals() {
        this.goalSelector.addGoal(2, new SitWhenOrderedToGoal(this));
        this.goalSelector.addGoal(5, new FollowOwnerGoal(this, 1.0D, 10.0F, 2.0F, false));
        this.goalSelector.addGoal(6, new ZephytaGolem.RandomFlyGoal(this));
        this.goalSelector.addGoal(7, new ZephytaGolem.LookAroundGoal(this));
        this.goalSelector.addGoal(5, new ZephytaGolem.SnowballAttackGoal(this));
        this.targetSelector.addGoal(1, new NearestAttackableTargetGoal<>(this, Mob.class, true, false));
        this.targetSelector.addGoal(1, new OwnerHurtByTargetGoal(this));
        this.targetSelector.addGoal(2, new OwnerHurtTargetGoal(this));
        this.targetSelector.addGoal(3, (new HurtByTargetGoal(this)).setAlertOthers());
    }

    @Nonnull
    public static AttributeSupplier.Builder createMobAttributes() {
        return FlyingMob.createMobAttributes()
                .add(Attributes.MAX_HEALTH, 10.0)
                .add(Attributes.FOLLOW_RANGE, 30.0);
    }

    @Nullable
    @Override
    public AgeableMob getBreedOffspring(ServerLevel p_146743_, AgeableMob p_146744_) {
        return null;
    }

    @Override
    protected void defineSynchedData() {
        super.defineSynchedData();
        this.entityData.define(DATA_ATTACK_CHARGE_ID, 0);
    }

    public static boolean checkZephyrSpawnRules(EntityType<? extends ZephytaGolem> zephyr, LevelAccessor level, MobSpawnType reason, BlockPos pos, RandomSource random) {
        return Mob.checkMobSpawnRules(zephyr, level, reason, pos, random) && (reason != MobSpawnType.NATURAL || random.nextInt(11) == 0) && level.canSeeSky(pos);
    }

    @Override
    public void aiStep() {
        super.aiStep();
        if (this.getY() < this.level.getMinBuildHeight() - 2 || this.getY() > this.level.getMaxBuildHeight()) {
            this.discard();
        }
        this.scale += this.scaleAdd;
        this.tailRot += this.tailRotAdd;
        if (this.getAttackCharge() > 0 && this.scale < 40) {
            this.scaleAdd = 1;
        } else {
            this.scaleAdd = 0;
            this.scale = 0;
        }
        this.tailRotAdd = 0.015F;
        if (this.tailRot >= Mth.TWO_PI) {
            this.tailRot -= Mth.TWO_PI;
        }
    }

    @Override
    public boolean removeWhenFarAway(double distanceToClosestPlayer) {
        return true;
    }

    public int getAttackCharge() {
        return this.entityData.get(DATA_ATTACK_CHARGE_ID);
    }

    /**
     * Sets the value of the attack charge for the purposes of rendering on the
     * client. This only sets the value if it's above 0 because that's when the
     * zephyr begins to wind up for an attack.
     */
    public void setAttackCharge(int attackTimer) {
        this.entityData.set(DATA_ATTACK_CHARGE_ID, Math.max(attackTimer, 0));
    }

    @Override
    protected float getSoundVolume() {
        return 3.0F;
    }

    @Override
    protected SoundEvent getAmbientSound() {
        return AetherSoundEvents.ENTITY_ZEPHYR_AMBIENT.get();
    }

    @Override
    protected SoundEvent getHurtSound(@Nonnull DamageSource damageSource) {
        return AetherSoundEvents.ENTITY_ZEPHYR_HURT.get();
    }

    @Override
    protected SoundEvent getDeathSound() {
        return AetherSoundEvents.ENTITY_ZEPHYR_DEATH.get();
    }

    @Override
    protected boolean shouldDespawnInPeaceful() {
        return true;
    }

    @Override
    public boolean isFlying() {
        return true;
    }

    @Override
    public void registerControllers(AnimatableManager.ControllerRegistrar controllerRegistrar) {

    }

    @Override
    public AnimatableInstanceCache getAnimatableInstanceCache() {
        return null;
    }

    @Override
    public double getTick(Object o) {
        return 0;
    }

    static class SnowballAttackGoal extends Goal {
        private final ZephytaGolem parentEntity;
        public int attackTimer;

        public SnowballAttackGoal(ZephytaGolem zephyr) {
            this.parentEntity = zephyr;
        }

        /**
         * Returns whether execution should begin. You can also read and cache
         * any state necessary for execution in this method as well.
         */
        @Override
        public boolean canUse() {
            return parentEntity.getTarget() != null;
        }

        /**
         * Execute a one shot task or start executing a continuous task
         */
        @Override
        public void start() {
            this.attackTimer = 0;
        }

        /**
         * Reset the task's internal state. Called when this task is interrupted
         * by another one
         */
        @Override
        public void stop() {
            this.parentEntity.setAttackCharge(0);
        }

        /**
         * Keep ticking a continuous task that has already been started
         */
        @Override
        public void tick() {
            LivingEntity target = this.parentEntity.getTarget();
            if (target.distanceToSqr(this.parentEntity) < 40 * 40 && this.parentEntity.hasLineOfSight(target)) {
                Level level = this.parentEntity.level;
                ++this.attackTimer;
                if (this.attackTimer == 10) {
                    this.parentEntity.playSound(this.parentEntity.getAmbientSound(), 3.0F, (level.random.nextFloat() - level.random.nextFloat()) * 0.2F + 1.0F);
                } else if (this.attackTimer == 20) {
                    Vec3 look = this.parentEntity.getViewVector(1.0F);
                    double accelX = target.getX() - (this.parentEntity.getX() + look.x * 4.0);
                    double accelY = target.getY(0.5) - (0.5 + this.parentEntity.getY(0.5));
                    double accelZ = target.getZ() - (this.parentEntity.getZ() + look.z * 4.0);
                    this.parentEntity.playSound(AetherSoundEvents.ENTITY_ZEPHYR_SHOOT.get(), 3.0F, (level.random.nextFloat() - level.random.nextFloat()) * 0.2F + 1.0F);
                    ZephyrSnowball snowball = new ZephyrSnowball(level, this.parentEntity, accelX, accelY, accelZ);
                    snowball.setPos(this.parentEntity.getX() + look.x * 4.0, this.parentEntity.getY(0.5) + 0.5, this.parentEntity.getZ() + look.z * 4.0);
                    level.addFreshEntity(snowball);
                    this.attackTimer = -40;
                }
            } else if (this.attackTimer > 0) {
                this.attackTimer--;
            }
            this.parentEntity.setAttackCharge(this.attackTimer);
        }
    }

    static class RandomFlyGoal extends Goal {
        private final ZephytaGolem parentEntity;

        public RandomFlyGoal(ZephytaGolem entity) {
            this.parentEntity = entity;
            this.setFlags(EnumSet.of(Goal.Flag.MOVE));
        }

        /**
         * Returns whether execution should begin. You can also read and cache any state necessary for execution in this
         * method as well.
         */
        @Override
        public boolean canUse() {
            MoveControl moveControl = this.parentEntity.getMoveControl();
            if (!moveControl.hasWanted()) {
                return true;
            } else {
                double d0 = moveControl.getWantedX() - this.parentEntity.getX();
                double d1 = moveControl.getWantedY() - this.parentEntity.getY();
                double d2 = moveControl.getWantedZ() - this.parentEntity.getZ();
                double d3 = d0 * d0 + d1 * d1 + d2 * d2;
                return d3 < 1.0 || d3 > 3600.0;
            }
        }

        /**
         * Returns whether an in-progress EntityAIBase should continue executing
         */
        @Override
        public boolean canContinueToUse() {
            return false;
        }

        /**
         * Execute a one shot task or start executing a continuous task
         */
        @Override
        public void start() {
            RandomSource random = this.parentEntity.getRandom();
            double d0 = this.parentEntity.getX() + (random.nextFloat() * 2.0F - 1.0F) * 16.0F;
            double d1 = this.parentEntity.getY() + (random.nextFloat() * 2.0F - 1.0F) * 16.0F;
            double d2 = this.parentEntity.getZ() + (random.nextFloat() * 2.0F - 1.0F) * 16.0F;
            this.parentEntity.getMoveControl().setWantedPosition(d0, d1, d2, 1.0);
        }
    }

    static class MoveHelperController extends MoveControl {
        private final ZephytaGolem parentEntity;
        private int courseChangeCooldown;

        public MoveHelperController(ZephytaGolem zephyr) {
            super(zephyr);
            this.parentEntity = zephyr;
        }

        @Override
        public void tick() {
            if (this.operation == MoveControl.Operation.MOVE_TO) {
                if (this.courseChangeCooldown-- <= 0) {
                    this.courseChangeCooldown += this.parentEntity.getRandom().nextInt(5) + 2;
                    Vec3 vec3d = new Vec3(this.wantedX - this.parentEntity.getX(), this.wantedY - this.parentEntity.getY(), this.wantedZ - this.parentEntity.getZ());
                    double d0 = vec3d.length();
                    vec3d = vec3d.normalize();
                    if (this.isNotColliding(vec3d, Mth.ceil(d0))) {
                        this.parentEntity.setDeltaMovement(this.parentEntity.getDeltaMovement().add(vec3d.scale(0.1)));
                    } else {
                        this.operation = MoveControl.Operation.WAIT;
                    }
                }
            }
        }

        /**
         * Checks if entity bounding box is not colliding with terrain
         */
        private boolean isNotColliding(Vec3 pos, int distance) {
            AABB axisalignedbb = this.parentEntity.getBoundingBox();
            for (int i = 1; i < distance; ++i) {
                axisalignedbb = axisalignedbb.move(pos);
                if (!this.parentEntity.level.noCollision(this.parentEntity, axisalignedbb)) {
                    return false;
                }
            }
            return true;
        }
    }

    static class LookAroundGoal extends Goal {
        private final ZephytaGolem parentEntity;

        public LookAroundGoal(ZephytaGolem zephyr) {
            this.parentEntity = zephyr;
            this.setFlags(EnumSet.of(Goal.Flag.LOOK));
        }

        /**
         * Returns whether execution should begin. You can also read and cache any state necessary for execution in this
         * method as well.
         */
        @Override
        public boolean canUse() {
            return true;
        }

        /**
         * Keep ticking a continuous task that has already been started
         */
        @Override
        public void tick() {
            if (this.parentEntity.getTarget() == null) {
                Vec3 vec3d = this.parentEntity.getDeltaMovement();
                this.parentEntity.setYRot(-((float) Mth.atan2(vec3d.x, vec3d.z)) * (180.0F / (float) Math.PI));
                this.parentEntity.yBodyRot = this.parentEntity.getYRot();
            } else {
                LivingEntity livingEntity = this.parentEntity.getTarget();
                if (livingEntity.distanceToSqr(this.parentEntity) < 64 * 64) {
                    double x = livingEntity.getX() - this.parentEntity.getX();
                    double z = livingEntity.getZ() - this.parentEntity.getZ();
                    this.parentEntity.setYRot(-((float) Mth.atan2(x, z)) * (180.0F / (float) Math.PI));
                    this.parentEntity.yBodyRot = this.parentEntity.getYRot();
                }
            }
        }
    }

    class SitWhenOrderedToGoal extends Goal {
        private final ZephytaGolem mob;

        public SitWhenOrderedToGoal(ZephytaGolem p_25898_) {
            this.mob = p_25898_;
            this.setFlags(EnumSet.of(Goal.Flag.JUMP, Goal.Flag.MOVE));
        }

        public boolean canContinueToUse() {
            return this.mob.isOrderedToSit();
        }

        public boolean canUse() {
            if (this.mob.isInWaterOrBubble()) {
                return false;
            } else {
                LivingEntity livingentity = this.mob.getOwner();
                if (livingentity == null) {
                    return true;
                } else {
                    return this.mob.distanceToSqr(livingentity) < 144.0D && livingentity.getLastHurtByMob() != null ? false : this.mob.isOrderedToSit();
                }
            }
        }

        public void start() {
            this.mob.getNavigation().stop();
            this.mob.setInSittingPose(true);
        }

        public void stop() {
            this.mob.setInSittingPose(false);
        }
    }
}
