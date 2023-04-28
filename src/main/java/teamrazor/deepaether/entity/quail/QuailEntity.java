package teamrazor.deepaether.entity.quail;

import net.minecraft.Util;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.animal.Chicken;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.ServerLevelAccessor;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.network.PlayMessages;
import software.bernie.geckolib.animatable.GeoEntity;
import software.bernie.geckolib.core.animatable.instance.AnimatableInstanceCache;
import software.bernie.geckolib.core.animation.*;
import software.bernie.geckolib.core.animation.AnimationState;
import software.bernie.geckolib.core.object.PlayState;
import software.bernie.geckolib.util.GeckoLibUtil;
import teamrazor.deepaether.init.DAEntities;
import teamrazor.deepaether.init.DAItems;

import javax.annotation.Nullable;

@SuppressWarnings({"unchecked", "SameReturnValue"})
@Mod.EventBusSubscriber
public class QuailEntity extends Chicken implements GeoEntity {

    private final AnimatableInstanceCache factory = GeckoLibUtil.createInstanceCache(this);

    private static final EntityDataAccessor<Integer> DATA_ID_TYPE_VARIANT =
            SynchedEntityData.defineId(QuailEntity.class, EntityDataSerializers.INT);

    @Override
    protected void defineSynchedData() {
        super.defineSynchedData();
        this.entityData.define(DATA_ID_TYPE_VARIANT, 0);
    }

    protected float getStandingEyeHeight(Pose p_28251_, EntityDimensions p_28252_) {
        return this.isBaby() ? p_28252_.height * 0.4F : p_28252_.height * 0.5F;
    }

    @Override
    public void addAdditionalSaveData(CompoundTag tag) {
        super.addAdditionalSaveData(tag);
        tag.putInt("Variant", this.getTypeVariant());
    }

    public SpawnGroupData finalizeSpawn(ServerLevelAccessor p_146746_, DifficultyInstance p_146747_,
                                        MobSpawnType p_146748_, @Nullable SpawnGroupData p_146749_,
                                        @Nullable CompoundTag p_146750_) {
        QuailVariants variant = Util.getRandom(QuailVariants.values(), this.random);
        setVariant(variant);
        return super.finalizeSpawn(p_146746_, p_146747_, p_146748_, p_146749_, p_146750_);
    }

    public QuailVariants getVariant() {
        return QuailVariants.byId(this.getTypeVariant() & 255);
    }

    private int getTypeVariant() {
        return this.entityData.get(DATA_ID_TYPE_VARIANT);
    }

    private void setVariant(QuailVariants variant) {
        this.entityData.set(DATA_ID_TYPE_VARIANT, variant.getId() & 255);
    }

    @Override
    public void readAdditionalSaveData(CompoundTag tag) {
        super.readAdditionalSaveData(tag);
        this.entityData.set(DATA_ID_TYPE_VARIANT, tag.getInt("Variant"));
    }

    public QuailEntity(PlayMessages.SpawnEntity packet, Level world) {
        this(DAEntities.QUAIL.get(), world);
    }

    public QuailEntity(EntityType<QuailEntity> type, Level world) {
        super(type, world);
    }

    public static AttributeSupplier.Builder createAttributes() {
        return Mob.createMobAttributes()
                .add(Attributes.MAX_HEALTH, 6.0D)
                .add(Attributes.MOVEMENT_SPEED, 0.25D);
    }

    public static void init() {
        SpawnPlacements.register(DAEntities.QUAIL.get(), SpawnPlacements.Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES,
                (entityType, world, reason, pos,
                 random) -> (world.getBlockState(pos.above()).is(Blocks.AIR)));
    }

    @Nullable
    @Override
    public Chicken getBreedOffspring(ServerLevel serverLevel, AgeableMob mob) {
        QuailEntity baby = DAEntities.QUAIL.get().create(serverLevel);
        QuailVariants variant = Util.getRandom(QuailVariants.values(), this.random);
        baby.setVariant(variant);
        return baby;
    }


    private PlayState predicate(AnimationState animationState) {

        if(animationState.isMoving()) {
            animationState.getController().setAnimation(RawAnimation.begin().thenPlay("animation.quail.walk"));
            return PlayState.CONTINUE;
        }

        animationState.getController().setAnimation(RawAnimation.begin().thenPlay("animation.quail.idle"));
        return PlayState.CONTINUE;

    }

    private PlayState flap(AnimationState animationState) {
        if(!this.onGround) {
            animationState.getController().setAnimation(RawAnimation.begin().thenPlayXTimes("animation.quail.flap_start", 1).then("animation.quail.flap", Animation.LoopType.LOOP));
            return PlayState.CONTINUE;
        } else {
            animationState.getController().setAnimation(RawAnimation.begin().thenPlay("animation.quail.idle"));
            return PlayState.CONTINUE;
        }
    }
    @Override
    public boolean isFood(ItemStack pStack) {
        return pStack.getItem() == DAItems.GOLDEN_GRASS_SEEDS.get();
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