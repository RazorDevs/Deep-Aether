package teamrazor.deepaether.entity.quail;

import net.minecraft.Util;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.util.Mth;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.*;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.ServerLevelAccessor;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraft.world.level.pathfinder.BlockPathTypes;
import net.minecraft.world.phys.Vec3;
import net.neoforged.fml.common.Mod;
import software.bernie.geckolib.animatable.GeoEntity;
import software.bernie.geckolib.core.animatable.instance.AnimatableInstanceCache;
import software.bernie.geckolib.core.animation.AnimationState;
import software.bernie.geckolib.core.animation.*;
import software.bernie.geckolib.core.object.PlayState;
import software.bernie.geckolib.util.GeckoLibUtil;
import teamrazor.deepaether.init.DAEntities;
import teamrazor.deepaether.init.DAItems;
import teamrazor.deepaether.init.DASounds;

import javax.annotation.Nullable;

@SuppressWarnings({"unchecked", "SameReturnValue"})
public class Quail extends SittingAetherAnimal implements GeoEntity {

    private final AnimatableInstanceCache factory = GeckoLibUtil.createInstanceCache(this);
    private static final EntityDataAccessor<Integer> DATA_ID_TYPE_VARIANT =
            SynchedEntityData.defineId(Quail.class, EntityDataSerializers.INT);
    private static final Ingredient FOOD_ITEMS = Ingredient.of(
            Items.WHEAT_SEEDS, Items.TORCHFLOWER_SEEDS
    );

    public float flap;
    public float flapSpeed;
    public float oFlapSpeed;
    public float oFlap;
    public float flapping = 1.0F;
    private float nextFlap = 1.0F;
    public int eggTime = this.random.nextInt(6000) + 6000;


    // Initialization

    public Quail(EntityType<Quail> type, Level world) {
        super(type, world);
        this.setPathfindingMalus(BlockPathTypes.WATER, 0.0F);
    }

    protected void registerGoals() {
        this.goalSelector.addGoal(0, new FloatGoal(this));
        this.goalSelector.addGoal(1, new PanicGoal(this, 1.4D));
        this.goalSelector.addGoal(2, new BreedGoal(this, 1.0D));
        this.goalSelector.addGoal(3, new TemptGoal(this, 1.0D, FOOD_ITEMS, false));
        this.goalSelector.addGoal(4, new FollowParentGoal(this, 1.1D));
        this.goalSelector.addGoal(5, new WaterAvoidingRandomStrollGoal(this, 1.0D));
        this.goalSelector.addGoal(6, new LookAtPlayerGoal(this, Player.class, 6.0F));
        this.goalSelector.addGoal(7, new RandomLookAroundGoal(this));
        this.goalSelector.addGoal(8, new RandomSittingGoal(this));
    }

    public static AttributeSupplier.Builder createAttributes() {
        return Mob.createMobAttributes()
                .add(Attributes.MAX_HEALTH, 6.0D)
                .add(Attributes.MOVEMENT_SPEED, 0.25D);
    }


    // Spawn Handling
    public static void init() {
        SpawnPlacements.register(DAEntities.QUAIL.get(), SpawnPlacements.Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES,
                (entityType, world, reason, pos,
                 random) -> (world.getBlockState(pos.above()).is(Blocks.AIR)));
    }

    public SpawnGroupData finalizeSpawn(ServerLevelAccessor levelAccessor, DifficultyInstance difficultyInstance,
                                        MobSpawnType spawnType, @Nullable SpawnGroupData spawnGroupData,
                                        @Nullable CompoundTag compoundTag) {
        QuailVariants variant = Util.getRandom(QuailVariants.values(), this.random);
        setVariant(variant);
        return super.finalizeSpawn(levelAccessor, difficultyInstance, spawnType, spawnGroupData, compoundTag);
    }


    // Overall Mob definition

    public void aiStep() {
        super.aiStep();
        this.oFlap = this.flap;
        this.oFlapSpeed = this.flapSpeed;
        this.flapSpeed += (this.onGround() ? -1.0F : 4.0F) * 0.3F;
        this.flapSpeed = Mth.clamp(this.flapSpeed, 0.0F, 1.0F);
        if (!this.onGround() && this.flapping < 1.0F) {
            this.flapping = 1.0F;
        }

        this.flapping *= 0.9F;
        Vec3 vec3 = this.getDeltaMovement();
        if (!this.onGround() && vec3.y < 0.0D) {
            this.setDeltaMovement(vec3.multiply(1.0D, 0.6D, 1.0D));
        }

        this.flap += this.flapping * 2.0F;
        if (!this.level().isClientSide && this.isAlive() && !this.isBaby() && --this.eggTime <= 0) {
            this.playSound(SoundEvents.CHICKEN_EGG, 1.0F, (this.random.nextFloat() - this.random.nextFloat()) * 0.2F + 1.0F);
            this.spawnAtLocation(DAItems.QUAIL_EGG.get());
            this.gameEvent(GameEvent.ENTITY_PLACE);
            this.eggTime = this.random.nextInt(6000) + 6000;
        }
    }

    @Nullable
    @Override
    public Quail getBreedOffspring(ServerLevel serverLevel, AgeableMob mob) {
        Quail baby = DAEntities.QUAIL.get().create(serverLevel);
        QuailVariants variant = Util.getRandom(QuailVariants.values(), this.random);
        baby.setVariant(variant);
        return baby;
    }

    @Override
    public boolean isFood(ItemStack pStack) {
        return pStack.getItem() == DAItems.GOLDEN_GRASS_SEEDS.get();
    }

    protected float getStandingEyeHeight(Pose pose, EntityDimensions entityDimensions) {
        return this.isBaby() ? entityDimensions.height * 0.5F : entityDimensions.height * 0.8F;
    }

    protected boolean isFlapping() {
        return this.flyDist > this.nextFlap;
    }

    protected void onFlap() {
        this.nextFlap = this.flyDist + this.flapSpeed / 2.0F;
    }

    protected SoundEvent getAmbientSound() {
        return DASounds.QUAIL_AMBIENT.get();
    }

    protected SoundEvent getHurtSound(DamageSource p_28262_) {
        return DASounds.QUAIL_HURT.get();
    }

    protected SoundEvent getDeathSound() {
        return DASounds.QUAIL_DEATH.get();
    }

    protected void playStepSound(BlockPos p_28254_, BlockState p_28255_) {
        this.playSound(SoundEvents.CHICKEN_STEP, 0.15F, 1.0F);
    }

    @Override
    protected void positionRider(Entity entity, Entity.MoveFunction moveFunction) {
        float f = Mth.sin(this.yBodyRot * ((float)Math.PI / 180F));
        float f1 = Mth.cos(this.yBodyRot * ((float)Math.PI / 180F));
        float f2 = 0.1F;
        float f3 = 0.0F;
        entity.setPos(this.getX() + (double)(0.1F * f), this.getY(0.5D) + entity.getMyRidingOffset(entity) + 0.0D, this.getZ() - (double)(0.1F * f1));
        if (entity instanceof LivingEntity) {
            ((LivingEntity)entity).yBodyRot = this.yBodyRot;
        }
    }

    // Data handling
    @Override
    protected void defineSynchedData() {
        super.defineSynchedData();
        this.entityData.define(DATA_ID_TYPE_VARIANT, 0);
    }

    @Override
    public void addAdditionalSaveData(CompoundTag tag) {
        super.addAdditionalSaveData(tag);
        tag.putInt("EggLayTime", this.eggTime);
        tag.putInt("Variant", this.getTypeVariant());
    }

    public QuailVariants getVariant() {
        return QuailVariants.byId(this.getTypeVariant() & 255);
    }

    private int getTypeVariant() {
        return this.entityData.get(DATA_ID_TYPE_VARIANT);
    }

    public void setVariant(QuailVariants variant) {
        this.entityData.set(DATA_ID_TYPE_VARIANT, variant.getId() & 255);
    }

    @Override
    public void readAdditionalSaveData(CompoundTag tag) {
        super.readAdditionalSaveData(tag);
        if (tag.contains("EggLayTime")) {
            this.eggTime = tag.getInt("EggLayTime");
        }
        this.entityData.set(DATA_ID_TYPE_VARIANT, tag.getInt("Variant"));
    }


    // Animation handling
    private PlayState predicate(AnimationState animationState) {

        if(animationState.isMoving()) {
            animationState.getController().setAnimation(RawAnimation.begin().thenPlay("animation.quail.walk"));
            return PlayState.CONTINUE;
        }

        animationState.getController().setAnimation(RawAnimation.begin().thenPlay("animation.quail.idle"));
        return PlayState.CONTINUE;

    }

    private PlayState flap(AnimationState animationState) {
        if(!this.onGround()) {
            animationState.getController().setAnimation(RawAnimation.begin().thenPlayXTimes("animation.quail.flap_start", 1).then("animation.quail.flap", Animation.LoopType.LOOP));
            return PlayState.CONTINUE;
        } else {
            animationState.getController().setAnimation(RawAnimation.begin().thenPlay("animation.quail.idle"));
            return PlayState.CONTINUE;
        }
    }

    @Override
    public void registerControllers(AnimatableManager.ControllerRegistrar controllers) {
        controllers.add(new AnimationController(this, "controller",
                0, this::predicate));
        controllers.add(new AnimationController(this, "flap_controller",
                0, this::flap));
    }

    @Override
    public AnimatableInstanceCache getAnimatableInstanceCache() {
        return factory;
    }
}