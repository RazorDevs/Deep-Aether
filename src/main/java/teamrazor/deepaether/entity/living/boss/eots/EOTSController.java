package teamrazor.deepaether.entity.living.boss.eots;

import com.aetherteam.aether.block.AetherBlocks;
import com.aetherteam.aether.client.AetherSoundEvents;
import com.aetherteam.aether.entity.AetherBossMob;
import com.aetherteam.aether.entity.ai.controller.BlankMoveControl;
import com.aetherteam.aether.entity.ai.goal.MostDamageTargetGoal;
import com.aetherteam.aether.entity.monster.dungeon.boss.BossNameGenerator;
import com.aetherteam.aether.event.AetherEventDispatch;
import com.aetherteam.aether.network.packet.clientbound.BossInfoPacket;
import com.aetherteam.nitrogen.entity.BossRoomTracker;
import com.aetherteam.nitrogen.network.PacketRelay;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.chat.Component;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerBossEvent;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.Mth;
import net.minecraft.world.BossEvent;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.entity.monster.Enemy;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Explosion;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.ServerLevelAccessor;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.HorizontalDirectionalBlock;
import net.minecraft.world.level.block.TrapDoorBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.Vec3;
import org.apache.commons.lang3.tuple.Pair;
import org.jetbrains.annotations.Nullable;
import software.bernie.geckolib.animatable.GeoEntity;
import software.bernie.geckolib.core.animatable.instance.AnimatableInstanceCache;
import software.bernie.geckolib.core.animation.AnimatableManager;
import software.bernie.geckolib.util.GeckoLibUtil;
import teamrazor.deepaether.init.DABlocks;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class EOTSController extends Mob implements GeoEntity, AetherBossMob<EOTSController>, Enemy {
    protected List<EOTSSegment> segments = new ArrayList();
    protected List<EOTSSegment> controllingSegments = new ArrayList();
    private final int SEGMENT_COUNT = 20;
    private final AnimatableInstanceCache factory = GeckoLibUtil.createInstanceCache(this);
    private static final EntityDataAccessor<Boolean> DATA_AWAKE_ID;
    private static final EntityDataAccessor<Component> DATA_BOSS_NAME_ID;
    private static final EntityDataAccessor<Float> DATA_HURT_ANGLE_ID;
    private static final EntityDataAccessor<Float> DATA_HURT_ANGLE_X_ID;
    private static final EntityDataAccessor<Float> DATA_HURT_ANGLE_Z_ID;
    private MostDamageTargetGoal mostDamageTargetGoal;
    private final ServerBossEvent bossFight;
    protected @Nullable BossRoomTracker<EOTSController> brassDungeon;

    public EOTSController(EntityType<? extends EOTSController> type, Level level) {
        super(type, level);
        this.moveControl = new BlankMoveControl(this);
        this.bossFight = new ServerBossEvent(this.getBossName(), BossEvent.BossBarColor.RED, BossEvent.BossBarOverlay.PROGRESS);
        this.setBossFight(false);
        this.xpReward = 50;
        this.setRot(0.0F, 0.0F);
        this.setPersistenceRequired();
    }

    public SpawnGroupData finalizeSpawn(ServerLevelAccessor level, DifficultyInstance difficulty, MobSpawnType reason, @Nullable SpawnGroupData spawnData, @Nullable CompoundTag tag) {
        this.setBossName(BossNameGenerator.generateBossName(this.getRandom()));
        this.moveTo((double) Mth.floor(this.getX()), this.getY(), (double)Mth.floor(this.getZ()));
        return spawnData;
    }

    public static AttributeSupplier.Builder createMobAttributes() {
        return Mob.createMobAttributes().add(Attributes.MAX_HEALTH, 400.0).add(Attributes.FOLLOW_RANGE, 64.0);
    }

    protected void registerGoals() {
        this.mostDamageTargetGoal = new MostDamageTargetGoal(this);
        this.targetSelector.addGoal(2, new selectControllingSegmentGoal(this));
        this.targetSelector.addGoal(1, this.mostDamageTargetGoal);
    }

    public void defineSynchedData() {
        super.defineSynchedData();
        this.getEntityData().define(DATA_AWAKE_ID, false);
        this.getEntityData().define(DATA_BOSS_NAME_ID, Component.literal("EOTS"));
        this.getEntityData().define(DATA_HURT_ANGLE_ID, 0.0F);
        this.getEntityData().define(DATA_HURT_ANGLE_X_ID, 0.0F);
        this.getEntityData().define(DATA_HURT_ANGLE_Z_ID, 0.0F);
    }

    public void tick() {
        label16: {
            super.tick();
            if (this.isAwake()) {
                LivingEntity var2 = this.getTarget();
                if (!(var2 instanceof Player)) {
                    break label16;
                }

                Player player = (Player)var2;
                if (!player.isCreative() && !player.isSpectator()) {
                    break label16;
                }
            }

            this.setTarget((LivingEntity)null);
        }

        this.evaporate();
    }

    private void evaporate() {
        Pair<BlockPos, BlockPos> minMax = this.getDefaultBounds(this);
        AetherBossMob.super.evaporate(this, minMax.getLeft(), minMax.getRight(), (blockState) -> {
            return true;
        });
    }

    public void customServerAiStep() {
        super.customServerAiStep();
        this.bossFight.setProgress(this.getHealth() / this.getMaxHealth());
        this.trackDungeon();
    }

    public boolean hurt(DamageSource source, float amount) {
        return true;
    }

    private void start() {
        if (this.getAwakenSound() != null) {
            this.playSound(this.getAwakenSound(), 2.5F, 1.0F / (this.getRandom().nextFloat() * 0.2F + 0.9F));
        }

        this.setAwake(true);
        this.setBossFight(true);
        if (this.getDungeon() != null) {
            this.closeRoom();
        }

        this.spawnSegments();
        AetherEventDispatch.onBossFightStart(this, this.getDungeon());
    }

    public void reset() {
        this.setDeltaMovement(Vec3.ZERO);
        this.setAwake(false);
        this.setBossFight(false);
        this.setTarget((LivingEntity)null);
        this.setHealth(this.getMaxHealth());
        if (this.getDungeon() != null) {
            this.setPos(this.getDungeon().originCoordinates());
            this.openRoom();
        }

        this.removeAllSegments();
        AetherEventDispatch.onBossFightStop(this, this.getDungeon());
    }

    public void die(DamageSource source) {
        this.setDeltaMovement(Vec3.ZERO);
        if (this.level() instanceof ServerLevel) {
            this.bossFight.setProgress(this.getHealth() / this.getMaxHealth());
            if (this.getDungeon() != null) {
                this.getDungeon().grantAdvancements(source);
                this.tearDownRoom();
            }
        }

        this.removeAllSegments();
        super.die(source);
    }

    private void spawnSegments() {
    }

    public void removeSegment(EOTSSegment segment) {
        this.segments.remove(segment);
    }

    public void setControllingSegment(EOTSSegment segment) {
        this.controllingSegments.add(segment);
    }

    public void removeControllingSegment(EOTSSegment segment) {
        this.controllingSegments.remove(segment);
    }

    private void removeAllSegments() {
        Iterator var1 = this.segments.iterator();

        while(var1.hasNext()) {
            EOTSSegment segment = (EOTSSegment)var1.next();
            segment.remove(RemovalReason.DISCARDED);
        }

    }

    public void knockback(double strength, double x, double z) {
    }

    public void push(double x, double y, double z) {
    }

    public void checkDespawn() {
    }

    public @Nullable BlockState convertBlock(BlockState state) {
        if (state.is((Block) DABlocks.LOCKED_NIMBUS_STONE.get())) {
            return ((Block)DABlocks.NIMBUS_STONE.get()).defaultBlockState();
        } else if (state.is((Block)DABlocks.LOCKED_LIGHT_NIMBUS_STONE.get())) {
            return ((Block)DABlocks.LIGHT_NIMBUS_STONE.get()).defaultBlockState();
        } else if (state.is((Block)DABlocks.BOSS_DOORWAY_NIMBUS_STONE.get())) {
            return Blocks.AIR.defaultBlockState();
        } else {
            return state.is((Block)DABlocks.TREASURE_DOORWAY_NIMBUS_STONE.get()) ? (BlockState)((TrapDoorBlock) AetherBlocks.SKYROOT_TRAPDOOR.get()).defaultBlockState().setValue(HorizontalDirectionalBlock.FACING, (Direction)state.getValue(HorizontalDirectionalBlock.FACING)) : null;
        }
    }

    public void startSeenByPlayer(ServerPlayer player) {
        super.startSeenByPlayer(player);
        PacketRelay.sendToPlayer(new BossInfoPacket.Display(this.bossFight.getId(), this.getId()), player);
        if (this.getDungeon() == null || this.getDungeon().isPlayerTracked(player)) {
            this.bossFight.addPlayer(player);
            AetherEventDispatch.onBossFightPlayerAdd(this, this.getDungeon(), player);
        }

    }

    public void stopSeenByPlayer(ServerPlayer player) {
        super.stopSeenByPlayer(player);
        PacketRelay.sendToPlayer(new BossInfoPacket.Remove(this.bossFight.getId(), this.getId()), player);
        this.bossFight.removePlayer(player);
        AetherEventDispatch.onBossFightPlayerRemove(this, this.getDungeon(), player);
    }

    public void onDungeonPlayerAdded(@Nullable Player player) {
        if (player instanceof ServerPlayer serverPlayer) {
            this.bossFight.addPlayer(serverPlayer);
            AetherEventDispatch.onBossFightPlayerAdd(this, this.getDungeon(), serverPlayer);
        }

    }

    public void onDungeonPlayerRemoved(@Nullable Player player) {
        if (player instanceof ServerPlayer serverPlayer) {
            this.bossFight.removePlayer(serverPlayer);
            AetherEventDispatch.onBossFightPlayerRemove(this, this.getDungeon(), serverPlayer);
        }

    }

    public boolean isAwake() {
        return (Boolean)this.getEntityData().get(DATA_AWAKE_ID);
    }

    public void setAwake(boolean awake) {
        this.getEntityData().set(DATA_AWAKE_ID, awake);
    }

    public Component getBossName() {
        return (Component)this.getEntityData().get(DATA_BOSS_NAME_ID);
    }

    public void setBossName(Component component) {
        this.getEntityData().set(DATA_BOSS_NAME_ID, component);
        this.bossFight.setName(component);
    }

    public @Nullable BossRoomTracker<EOTSController> getDungeon() {
        return this.brassDungeon;
    }

    public void setDungeon(@Nullable BossRoomTracker<EOTSController> dungeon) {
        this.brassDungeon = dungeon;
    }

    public boolean isBossFight() {
        return this.bossFight.isVisible();
    }

    public void setBossFight(boolean isFighting) {
        this.bossFight.setVisible(isFighting);
    }

    public @Nullable ResourceLocation getBossBarTexture() {
        return new ResourceLocation("deep_aether", "boss_bar/EOTS");
    }

    public @Nullable ResourceLocation getBossBarBackgroundTexture() {
        return new ResourceLocation("deep_aether", "boss_bar/EOTS_background");
    }

    public int getDeathScore() {
        return this.deathScore;
    }

    public void setCustomName(@Nullable Component name) {
        super.setCustomName(name);
        this.setBossName(name);
    }

    protected SoundEvent getAwakenSound() {
        return (SoundEvent) AetherSoundEvents.ENTITY_SLIDER_AWAKEN.get();
    }

    protected @Nullable SoundEvent getAmbientSound() {
        return (SoundEvent)AetherSoundEvents.ENTITY_SLIDER_AMBIENT.get();
    }

    protected SoundEvent getDeathSound() {
        return (SoundEvent)AetherSoundEvents.ENTITY_SLIDER_DEATH.get();
    }

    public SoundSource getSoundSource() {
        return SoundSource.HOSTILE;
    }

    public boolean canAttack(LivingEntity target) {
        return false;
    }

    public boolean ignoreExplosion(Explosion explosion) {
        return true;
    }

    public float getYRot() {
        return 0.0F;
    }

    protected boolean canRide(Entity vehicle) {
        return false;
    }

    public boolean canBeCollidedWith() {
        return false;
    }

    public boolean isPushable() {
        return false;
    }

    public boolean isNoGravity() {
        return true;
    }

    public boolean shouldDiscardFriction() {
        return true;
    }

    protected boolean isAffectedByFluids() {
        return false;
    }

    public boolean displayFireAnimation() {
        return false;
    }

    public boolean isFullyFrozen() {
        return false;
    }

    protected Entity.MovementEmission getMovementEmission() {
        return MovementEmission.EVENTS;
    }

    public void addAdditionalSaveData(CompoundTag tag) {
        super.addAdditionalSaveData(tag);
        this.addBossSaveData(tag);
        tag.putBoolean("Awake", this.isAwake());
    }

    public void readAdditionalSaveData(CompoundTag tag) {
        super.readAdditionalSaveData(tag);
        this.readBossSaveData(tag);
        if (tag.contains("Awake")) {
            this.setAwake(tag.getBoolean("Awake"));
        }

    }

    public void writeSpawnData(FriendlyByteBuf buffer) {
        CompoundTag tag = new CompoundTag();
        this.addBossSaveData(tag);
        buffer.writeNbt(tag);
    }

    public void readSpawnData(FriendlyByteBuf additionalData) {
        CompoundTag tag = additionalData.readNbt();
        if (tag != null) {
            this.readBossSaveData(tag);
        }

    }

    public void registerControllers(AnimatableManager.ControllerRegistrar controllerRegistrar) {
    }

    public AnimatableInstanceCache getAnimatableInstanceCache() {
        return this.factory;
    }

    static {
        DATA_AWAKE_ID = SynchedEntityData.defineId(EOTSController.class, EntityDataSerializers.BOOLEAN);
        DATA_BOSS_NAME_ID = SynchedEntityData.defineId(EOTSController.class, EntityDataSerializers.COMPONENT);
        DATA_HURT_ANGLE_ID = SynchedEntityData.defineId(EOTSController.class, EntityDataSerializers.FLOAT);
        DATA_HURT_ANGLE_X_ID = SynchedEntityData.defineId(EOTSController.class, EntityDataSerializers.FLOAT);
        DATA_HURT_ANGLE_Z_ID = SynchedEntityData.defineId(EOTSController.class, EntityDataSerializers.FLOAT);
    }

    public static class selectControllingSegmentGoal extends Goal {
        int timer = 200;
        EOTSController controller;

        private selectControllingSegmentGoal(EOTSController controller) {
            this.controller = controller;
        }

        public boolean canUse() {
            if (this.timer <= 0) {
                return true;
            } else {
                --this.timer;
                return false;
            }
        }

        public void start() {
            this.timer = 200;
            this.controller.controllingSegments.get(this.controller.level().getRandom().nextInt(this.controller.controllingSegments.size())).setGoToMiddle(this.controller.blockPosition().above(2));
        }
    }
}
