package teamrazor.deepaether.entity.boss;

import com.aetherteam.aether.api.BossRoomTracker;
import com.aetherteam.aether.entity.BossMob;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.util.Mth;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraft.world.level.pathfinder.BlockPathTypes;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.network.PlayMessages;
import org.jetbrains.annotations.Nullable;
import software.bernie.geckolib.animatable.GeoEntity;
import software.bernie.geckolib.core.animatable.instance.AnimatableInstanceCache;
import software.bernie.geckolib.core.animation.AnimatableManager;
import software.bernie.geckolib.util.GeckoLibUtil;
import teamrazor.deepaether.init.DAEntities;
import teamrazor.deepaether.init.DASounds;

@SuppressWarnings({"unchecked", "SameReturnValue"})
@Mod.EventBusSubscriber
public class EOTSEntity extends Monster implements GeoEntity, BossMob {

    private final AnimatableInstanceCache factory = GeckoLibUtil.createInstanceCache(this);
    private static final EntityDataAccessor<Integer> DATA_ID_TYPE_VARIANT =
            SynchedEntityData.defineId(EOTSEntity.class, EntityDataSerializers.INT);


    public EOTSEntity(PlayMessages.SpawnEntity packet, Level world) {
        this(DAEntities.EOTS.get(), world);
        this.setPathfindingMalus(BlockPathTypes.WATER, 0.0F);
    }

    public EOTSEntity(EntityType<EOTSEntity> type, Level world) {
        super(type, world);
        this.setPathfindingMalus(BlockPathTypes.WATER, 0.0F);
    }

    public static void init() {
        SpawnPlacements.register(DAEntities.EOTS.get(), SpawnPlacements.Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES,
                (entityType, world, reason, pos,
                 random) -> (world.getBlockState(pos.above()).is(Blocks.AIR)));
    }

    protected void registerGoals() {
    }


    public static AttributeSupplier.Builder createAttributes() {
        return Mob.createMobAttributes()
                .add(Attributes.MAX_HEALTH, 100.0D);
    }


    public void aiStep() {
        super.aiStep();
    }


    @Override
    protected void defineSynchedData() {
        super.defineSynchedData();
        this.entityData.define(DATA_ID_TYPE_VARIANT, 0);
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
    public boolean isInvulnerableTo(DamageSource p_20122_) {
        return super.isInvulnerableTo(p_20122_);
    }


    public void positionRider(Entity p_28269_) {
        super.positionRider(p_28269_);
        float f = Mth.sin(this.yBodyRot * ((float)Math.PI / 180F));
        float f1 = Mth.cos(this.yBodyRot * ((float)Math.PI / 180F));
        float f2 = 0.1F;
        float f3 = 0.0F;
        p_28269_.setPos(this.getX() + (double)(0.1F * f), this.getY(0.5D) + p_28269_.getMyRidingOffset() + 0.0D, this.getZ() - (double)(0.1F * f1));
        if (p_28269_ instanceof LivingEntity) {
            ((LivingEntity)p_28269_).yBodyRot = this.yBodyRot;
        }

    }

    @Override
    public void registerControllers(AnimatableManager.ControllerRegistrar controllerRegistrar) {

    }

    @Override
    public AnimatableInstanceCache getAnimatableInstanceCache() {
        return factory;
    }

    @Override
    public Component getBossName() {
        return null;
    }

    @Override
    public void setBossName(Component component) {

    }

    @Override
    public boolean isBossFight() {
        return false;
    }

    @Override
    public void setBossFight(boolean isFighting) {

    }

    @Override
    public BossRoomTracker getDungeon() {
        return null;
    }

    @Override
    public void setDungeon(BossRoomTracker dungeon) {

    }

    @Override
    public int getDeathScore() {
        return 0;
    }

    @Override
    public void trackDungeon() {
        BossMob.super.trackDungeon();
    }

    @Override
    public void displayTooFarMessage(Player player) {
        BossMob.super.displayTooFarMessage(player);
    }

    @Override
    public void onDungeonPlayerAdded(@Nullable Player player) {
        BossMob.super.onDungeonPlayerAdded(player);
    }

    @Override
    public void onDungeonPlayerRemoved(@Nullable Player player) {
        BossMob.super.onDungeonPlayerRemoved(player);
    }

    @Override
    public void reset() {

    }

    @Override
    public void tearDownRoom() {
        BossMob.super.tearDownRoom();
    }

    @Override
    public void closeRoom() {
        BossMob.super.closeRoom();
    }

    @Override
    public void openRoom() {
        BossMob.super.openRoom();
    }

    @org.jetbrains.annotations.Nullable
    @Override
    public BlockState convertBlock(BlockState state) {
        return null;
    }

    @Override
    public void addBossSaveData(CompoundTag tag) {
        BossMob.super.addBossSaveData(tag);
    }

    @Override
    public void readBossSaveData(CompoundTag tag) {
        BossMob.super.readBossSaveData(tag);
    }

    @Override
    public boolean alwaysAccepts() {
        return super.alwaysAccepts();
    }

    @Override
    public LivingEntity self() {
        return super.self();
    }
}