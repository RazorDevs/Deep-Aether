package teamrazor.deepaether.entity;

import com.aetherteam.aether.effect.AetherEffects;
import com.aetherteam.aether.entity.passive.AetherAnimal;
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
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.network.PlayMessages;
import org.jetbrains.annotations.Nullable;
import software.bernie.geckolib.animatable.GeoEntity;
import software.bernie.geckolib.core.animatable.instance.AnimatableInstanceCache;
import software.bernie.geckolib.core.animation.AnimatableManager;
import software.bernie.geckolib.core.animation.AnimationController;
import software.bernie.geckolib.core.animation.AnimationState;
import software.bernie.geckolib.core.animation.RawAnimation;
import software.bernie.geckolib.core.object.PlayState;
import software.bernie.geckolib.util.GeckoLibUtil;
import teamrazor.deepaether.entity.goals.FollowPlayerGoal;
import teamrazor.deepaether.init.DAEntities;
import teamrazor.deepaether.init.DASounds;

import java.util.UUID;

@Mod.EventBusSubscriber
public class Venomite extends AetherAnimal implements GeoEntity, NeutralMob, FlyingAnimal {

    private final AnimatableInstanceCache factory = GeckoLibUtil.createInstanceCache(this);
    public static final int TICKS_PER_FLAP = Mth.ceil(1.4959966F);
    private static final EntityDataAccessor<Byte> DATA_FLAGS_ID = SynchedEntityData.defineId(Venomite.class, EntityDataSerializers.BYTE);
    private static final EntityDataAccessor<Integer> DATA_REMAINING_ANGER_TIME = SynchedEntityData.defineId(Venomite.class, EntityDataSerializers.INT);
    private int underWaterTicks;
    private float rollAmount;
    private float rollAmountO;
    private static final UniformInt PERSISTENT_ANGER_TIME = TimeUtil.rangeOfSeconds(20, 39);
    @javax.annotation.Nullable
    private UUID persistentAngerTarget;

    public Venomite(PlayMessages.SpawnEntity spawnEntity, Level level) {
        super(DAEntities.VENOMITE.get(), level);
        this.moveControl = new FlyingMoveControl(this, 20, true);
        this.xpReward = 3;
    }

    public Venomite(EntityType<? extends Animal> type, Level level) {
        super(type, level);
        this.moveControl = new FlyingMoveControl(this, 20, true);
        this.xpReward = 3;
    }

    public static AttributeSupplier.Builder createAttributes() {
        return Mob.createMobAttributes().add(Attributes.MAX_HEALTH, 10.0D)
                .add(Attributes.FLYING_SPEED, (double)0.6F)
                .add(Attributes.MOVEMENT_SPEED, (double)0.3F)
                .add(Attributes.ATTACK_DAMAGE, 2.0D)
                .add(Attributes.FOLLOW_RANGE, 48.0D);
    }

    public static void init() {
        SpawnPlacements.register(DAEntities.VENOMITE.get(), SpawnPlacements.Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES,
                (entityType, world, reason, pos,
                 random) -> (world.getBlockState(pos.above()).is(Blocks.AIR)));
    }

    protected void defineSynchedData() {
        super.defineSynchedData();
        this.entityData.define(DATA_FLAGS_ID, (byte)0);
        this.entityData.define(DATA_REMAINING_ANGER_TIME, 0);
    }

    public float getWalkTargetValue(BlockPos p_27788_, LevelReader blockState) {
        return blockState.getBlockState(p_27788_).isAir() ? 10.0F : 0.0F;
    }

    protected void registerGoals() {
        this.goalSelector.addGoal(0, new VenomiteAttackGoal(this, (double)1.4F, true));
        this.goalSelector.addGoal(1, new FollowPlayerGoal(this, 1.0, false));
        this.goalSelector.addGoal(2, new WaterAvoidingRandomFlyingGoal(this, 1.0D));
        this.goalSelector.addGoal(3, new FloatGoal(this));
        this.targetSelector.addGoal(1, (new VenomiteHurtByOtherGoal(this)).setAlertOthers(new Class[0]));
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

    public MobType getMobType() {
        return MobType.ARTHROPOD;
    }

    protected PathNavigation createNavigation(Level level) {
        FlyingPathNavigation flyingpathnavigation = new FlyingPathNavigation(this, level) {
            public boolean isStableDestination(BlockPos blockPos) {
                return !this.level.getBlockState(blockPos.below()).isAir();
            }
        };

        flyingpathnavigation.setCanOpenDoors(false);
        flyingpathnavigation.setCanFloat(false);
        flyingpathnavigation.setCanPassDoors(true);
        return flyingpathnavigation;
    }

    @Override
    public boolean canBeAffected(MobEffectInstance effectInstance) {
        return !effectInstance.getEffect().equals(AetherEffects.INEBRIATION.get());
    }

    private boolean getFlag(int p_27922_) {
        return (this.entityData.get(DATA_FLAGS_ID) & p_27922_) != 0;
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

    protected float getStandingEyeHeight(Pose pose, EntityDimensions entityDimensions) {
        return entityDimensions.height * 0.5F;
    }

    private void setFlag(int i, boolean b) {
        if (b) {
            this.entityData.set(DATA_FLAGS_ID, (byte)(this.entityData.get(DATA_FLAGS_ID) | i));
        } else {
            this.entityData.set(DATA_FLAGS_ID, (byte)(this.entityData.get(DATA_FLAGS_ID) & ~i));
        }
    }

    public void tick() {
        super.tick();
        this.updateRollAmount();
    }

    public float getRollAmount(float p_27936_) {
        return Mth.lerp(p_27936_, this.rollAmountO, this.rollAmount);
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


    private PlayState predicate(AnimationState animationState) {
        animationState.getController().setAnimation(RawAnimation.begin().thenPlay("animation.venomite.flying"));
        return PlayState.CONTINUE;
    }

    @Override
    public void registerControllers(AnimatableManager.ControllerRegistrar controllers) {
        controllers.add(new AnimationController<>(this, "controller",
                0, this::predicate));
    }

    @Override
    public AnimatableInstanceCache getAnimatableInstanceCache() {
        return factory;
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
