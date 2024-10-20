package io.github.razordevs.deep_aether.entity.living;

import com.aetherteam.aether.effect.AetherEffects;
import com.aetherteam.aether.entity.passive.AetherAnimal;
import io.github.razordevs.deep_aether.init.DAEntities;
import io.github.razordevs.deep_aether.init.DASounds;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.util.Mth;
import net.minecraft.util.TimeUtil;
import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.control.FlyingMoveControl;
import net.minecraft.world.entity.ai.goal.FloatGoal;
import net.minecraft.world.entity.ai.goal.MeleeAttackGoal;
import net.minecraft.world.entity.ai.goal.WaterAvoidingRandomFlyingGoal;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.ai.goal.target.ResetUniversalAngerTargetGoal;
import net.minecraft.world.entity.ai.navigation.FlyingPathNavigation;
import net.minecraft.world.entity.ai.navigation.PathNavigation;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.entity.animal.FlyingAnimal;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.Nullable;
import io.github.razordevs.deep_aether.entity.goals.FollowPlayerGoal;
import io.github.razordevs.deep_aether.entity.living.projectile.VenomiteBubble;

import java.util.UUID;

public class Venomite extends AetherAnimal implements NeutralMob, FlyingAnimal {

    public static final int TICKS_PER_FLAP = Mth.ceil(1.4959966F);
    private static final EntityDataAccessor<Byte> DATA_FLAGS_ID = SynchedEntityData.defineId(Venomite.class, EntityDataSerializers.BYTE);
    private static final EntityDataAccessor<Integer> DATA_REMAINING_ANGER_TIME = SynchedEntityData.defineId(Venomite.class, EntityDataSerializers.INT);
    private int underWaterTicks;
    private float rollAmount;
    private float rollAmountO;
    private static final UniformInt PERSISTENT_ANGER_TIME = TimeUtil.rangeOfSeconds(20, 39);

    @Nullable
    private UUID persistentAngerTarget;

    public Venomite(EntityType<? extends Animal> type, Level level) {
        super(type, level);
        this.moveControl = new FlyingMoveControl(this, 20, true);
        this.xpReward = 3;
    }

    public static AttributeSupplier.Builder createAttributes() {
        return Mob.createMobAttributes().add(Attributes.MAX_HEALTH, 10.0D)
                .add(Attributes.FLYING_SPEED, 0.6F)
                .add(Attributes.MOVEMENT_SPEED, 0.3F)
                .add(Attributes.ATTACK_DAMAGE, 2.0D)
                .add(Attributes.FOLLOW_RANGE, 48.0D);
    }

    @Override
    protected void defineSynchedData(SynchedEntityData.Builder builder) {
        super.defineSynchedData(builder);
        builder.define(DATA_FLAGS_ID, (byte)0);
        builder.define(DATA_REMAINING_ANGER_TIME, 0);
    }

    public float getWalkTargetValue(BlockPos state, LevelReader blockState) {
        return blockState.getBlockState(state).isAir() ? 10.0F : 0.0F;
    }

    protected void registerGoals() {
        this.goalSelector.addGoal(0, new VenomiteAttackGoal(this, 1.4F, true));
        this.goalSelector.addGoal(1, new FollowPlayerGoal(this, 1.0, 1));
        this.goalSelector.addGoal(2, new WaterAvoidingRandomFlyingGoal(this, 1.0D));
        this.goalSelector.addGoal(3, new FloatGoal(this));

        this.targetSelector.addGoal(1, (new VenomiteHurtByOtherGoal(this)).setAlertOthers());
        this.targetSelector.addGoal(2, new VenomiteBecomeAngryTargetGoal(this));
        this.targetSelector.addGoal(3, new ResetUniversalAngerTargetGoal<>(this, true));
    }

    public void addAdditionalSaveData(CompoundTag compoundTag) {
        super.addAdditionalSaveData(compoundTag);
        this.addPersistentAngerSaveData(compoundTag);
    }

    public void readAdditionalSaveData(CompoundTag compoundTag) {
        super.readAdditionalSaveData(compoundTag);
        this.readPersistentAngerSaveData(this.level(), compoundTag);
    }

    @Override
    public boolean isFood(ItemStack stack) {
        return false;
    }

    protected void playStepSound(BlockPos blockPos, BlockState blockState) {
    }

    protected SoundEvent getAmbientSound() {
        return DASounds.VENOMITE_AMBIENT.get();
    }

    protected SoundEvent getHurtSound(DamageSource damageSource) {
        return DASounds.VENOMITE_HURT.get();
    }

    protected SoundEvent getDeathSound() {
        return DASounds.VENOMITE_DEATH.get();
    }

    @Override
    protected void checkFallDamage(double v, boolean b, BlockState blockState, BlockPos blockPos) {
    }

    @Override
    protected PathNavigation createNavigation(Level level) {
        FlyingPathNavigation flyingpathnavigation = new FlyingPathNavigation(this, level);
        flyingpathnavigation.setCanFloat(true);
        return flyingpathnavigation;
    }

    @SuppressWarnings("deprecation")
    @Override
    public boolean canBeAffected(MobEffectInstance effectInstance) {
        return !effectInstance.getEffect().equals(AetherEffects.INEBRIATION.get());
    }

    private boolean getFlag(int flag) {
        return (this.entityData.get(DATA_FLAGS_ID) & flag) != 0;
    }

    @Nullable
    @Override
    public AgeableMob getBreedOffspring(ServerLevel level, AgeableMob ageableMob) {
        return null;
    }

    public boolean isFlapping() {
        return this.isFlying() && this.tickCount % TICKS_PER_FLAP == 0;
    }

    public boolean isFlying() {
        return !this.onGround();
    }

    public int getRemainingPersistentAngerTime() {
        return this.entityData.get(DATA_REMAINING_ANGER_TIME);
    }

    public void setRemainingPersistentAngerTime(int set) {
        this.entityData.set(DATA_REMAINING_ANGER_TIME, set);
    }

    @javax.annotation.Nullable
    public UUID getPersistentAngerTarget() {
        return this.persistentAngerTarget;
    }

    public void setPersistentAngerTarget(@javax.annotation.Nullable UUID uuid) {
        this.persistentAngerTarget = uuid;
    }

    public void startPersistentAngerTimer() {
        this.setRemainingPersistentAngerTime(PERSISTENT_ANGER_TIME.sample(this.random));
    }

    @Override
    protected EntityDimensions getDefaultDimensions(Pose pose) {
        return super.getDefaultDimensions(pose).scale(1F, 1.5F);
    }

    private void setFlag(int i, boolean b) {
        if (b) {
            this.entityData.set(DATA_FLAGS_ID, (byte)(this.entityData.get(DATA_FLAGS_ID) | i));
        } else {
            this.entityData.set(DATA_FLAGS_ID, (byte)(this.entityData.get(DATA_FLAGS_ID) & ~i));
        }
    }

    @Override
    public void remove(RemovalReason removalReason) {
        if(!this.level().isClientSide && this.isDeadOrDying() && random.nextBoolean()) {
            VenomiteBubble bubble = new VenomiteBubble(DAEntities.VENOMITE_BUBBLE.get(), this.level());
            bubble.moveTo(this.getPosition(0));
            this.level().addFreshEntity(bubble);
        }
        super.remove(removalReason);
    }

    public void tick() {
        super.tick();
        this.updateRollAmount();
    }

    private void updateRollAmount() {
        this.rollAmountO = this.rollAmount;
        if (this.isRolling()) {
            this.rollAmount = Math.min(1.0F, this.rollAmount + 0.2F);
        } else {
            this.rollAmount = Math.max(0.0F, this.rollAmount - 0.24F);
        }

    }

    protected void customServerAiStep() {
        if (this.isInWaterOrBubble()) {
            ++this.underWaterTicks;
        } else {
            this.underWaterTicks = 0;
        }

        if (this.underWaterTicks > 20) {
            this.hurt(this.damageSources().drown(), 1.0F);
        }

        if (!this.level().isClientSide) {
            this.updatePersistentAnger((ServerLevel)this.level(), false);
        }

    }

    private boolean isRolling() {
        return this.getFlag(2);
    }

    private void setRolling(boolean p_27930_) {
        this.setFlag(2, p_27930_);
    }

    public void aiStep() {
        super.aiStep();
        if (!this.level().isClientSide) {
            boolean flag = this.isAngry() && this.getTarget() != null && this.getTarget().distanceToSqr(this) < 4.0D;
            this.setRolling(flag);
        }
    }

    class VenomiteAttackGoal extends MeleeAttackGoal {
        VenomiteAttackGoal(PathfinderMob mob, double v, boolean b) {
            super(mob, v, b);
        }

        public boolean canUse() {
            return super.canUse() && Venomite.this.isAngry();
        }

        public boolean canContinueToUse() {
            return super.canContinueToUse() && Venomite.this.isAngry();
        }
    }

    static class VenomiteBecomeAngryTargetGoal extends NearestAttackableTargetGoal<Player> {
        VenomiteBecomeAngryTargetGoal(Venomite venomite) {
            super(venomite, Player.class, 10, true, false, venomite::isAngryAt);
        }

        public boolean canUse() {
            return this.venomiteCanTarget() && super.canUse();
        }

        public boolean canContinueToUse() {
            boolean flag = this.venomiteCanTarget();
            if (flag && this.mob.getTarget() != null) {
                return super.canContinueToUse();
            } else {
                this.targetMob = null;
                return false;
            }
        }

        private boolean venomiteCanTarget() {
            Venomite venomite = (Venomite) this.mob;
            return venomite.isAngry();
        }
    }

    class VenomiteHurtByOtherGoal extends HurtByTargetGoal {
        VenomiteHurtByOtherGoal(Venomite venomite) {
            super(venomite);
        }

        public boolean canContinueToUse() {
            return Venomite.this.isAngry() && super.canContinueToUse();
        }

        protected void alertOther(Mob mob, LivingEntity livingEntity) {
            if (mob instanceof Venomite && this.mob.hasLineOfSight(livingEntity)) {
                mob.setTarget(livingEntity);
            }
        }
    }
}
