package io.github.razordevs.deep_aether.entity.living.boss.eots;

import com.aetherteam.aether.Aether;
import com.aetherteam.aether.block.AetherBlocks;
import com.aetherteam.aether.block.dungeon.DoorwayBlock;
import com.aetherteam.aether.client.AetherSoundEvents;
import com.aetherteam.aether.entity.AetherBossMob;
import com.aetherteam.aether.entity.ai.controller.BlankMoveControl;
import com.aetherteam.aether.entity.monster.dungeon.boss.BossNameGenerator;
import com.aetherteam.aether.event.AetherEventDispatch;
import com.aetherteam.aether.network.packet.clientbound.BossInfoPacket;
import com.aetherteam.nitrogen.entity.BossRoomTracker;
import com.aetherteam.nitrogen.network.PacketRelay;
import io.github.razordevs.deep_aether.block.building.DoorwayPillarBlock;
import io.github.razordevs.deep_aether.init.DABlocks;
import io.github.razordevs.deep_aether.init.DAEntities;
import io.github.razordevs.deep_aether.init.DAParticles;
import io.github.razordevs.deep_aether.init.DASounds;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerBossEvent;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundSource;
import net.minecraft.tags.DamageTypeTags;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.world.BossEvent;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.AttributeInstance;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.monster.Enemy;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Explosion;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.ServerLevelAccessor;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.HorizontalDirectionalBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;
import net.neoforged.neoforge.entity.IEntityWithComplexSpawn;
import org.apache.commons.lang3.tuple.Pair;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class EOTSController extends Mob implements AetherBossMob<EOTSController>, Enemy, IEntityWithComplexSpawn {
    protected List<EOTSSegment> controllingSegments = new ArrayList<>();
    protected List<UUID> segmentUUIDs = new ArrayList<>();
    public static final int SEGMENT_COUNT = 15;
    public static final int EXTRA_SEGMENT = 5;
    private static final EntityDataAccessor<Boolean> DATA_AWAKE_ID = SynchedEntityData.defineId(EOTSController.class, EntityDataSerializers.BOOLEAN);
    private static final EntityDataAccessor<Component> DATA_BOSS_NAME_ID = SynchedEntityData.defineId(EOTSController.class, EntityDataSerializers.COMPONENT);
    private final ServerBossEvent bossFight;
    private boolean hasBeenContactedBySegment = false;
    protected @Nullable BossRoomTracker<EOTSController> brassDungeon;
    private int chatCooldown;
    private int soundCooldown;

    public EOTSController(EntityType<? extends EOTSController> type, Level level) {
        super(type, level);
        this.moveControl = new BlankMoveControl(this);
        this.bossFight = new ServerBossEvent(this.getBossName(), BossEvent.BossBarColor.RED, BossEvent.BossBarOverlay.PROGRESS);
        this.setBossFight(false);
        this.xpReward = 50;
        this.setRot(0.0F, 0.0F);
        this.noPhysics = true;
        this.setPersistenceRequired();
        this.soundCooldown = 0;
    }

    @SuppressWarnings("deprecation")
    public SpawnGroupData finalizeSpawn(@NotNull ServerLevelAccessor level, @NotNull DifficultyInstance difficulty, @NotNull MobSpawnType reason, @Nullable SpawnGroupData spawnData, @Nullable CompoundTag tag) {
        this.setBossName(generateEOTSName(this.getRandom()));
        this.moveTo(Mth.floor(this.getX()), this.getY(), Mth.floor(this.getZ()));
        return spawnData;
    }

    /**
     * Generates a name for the EOTS boss.
     */
    public static MutableComponent generateEOTSName(RandomSource random) {
        MutableComponent result = BossNameGenerator.generateBossName(random);
        return result.append(Component.translatable("gui.deep_aether.eots.title"));
    }

    @NotNull
    public static AttributeSupplier.Builder createMobAttributes() {
        return Mob.createMobAttributes().add(Attributes.MAX_HEALTH, 225.1).add(Attributes.FOLLOW_RANGE, 96.0);
    }

    @Override
    protected void registerGoals() {
        this.targetSelector.addGoal(0, new NearestAttackableTargetGoal<>(this, Player.class, false));
    }

    @Override
    public void defineSynchedData() {
        super.defineSynchedData();
        this.getEntityData().define(DATA_AWAKE_ID, false);
        this.getEntityData().define(DATA_BOSS_NAME_ID, Component.literal("EOTS"));
    }

    @Override
    public void tick() {
        super.tick();

        if(this.level().isClientSide()) {
            if(!this.bossFight.isVisible()){
                this.spawnParticles();
            }
        }

        if (!this.isAwake() || (this.getTarget() instanceof Player player && (player.isCreative() || player.isSpectator()))) {
            this.setTarget(null);
            this.playBlowingSound();
        }
        this.evaporate();

        if(this.hasBeenContactedBySegment && this.isAwake() && segmentUUIDs.isEmpty()) {
            this.hurt(this.level().damageSources().mobAttack(this), 255.1F);
        }

        if (this.getChatCooldown() > 0) {
            --this.chatCooldown;
        }
    }

    private void playBlowingSound() {
        if(this.soundCooldown != 0){
            this.soundCooldown--;
        }else{
            this.level().playLocalSound(this, DASounds.EOTS_BLOWING.get(), SoundSource.HOSTILE, 1.0f, 1.0f);
            this.soundCooldown = 50;
        }
    }

    @Override
    public boolean isWithinMeleeAttackRange(LivingEntity pEntity) {
        return false;
    }

    @Override
    public boolean attackable() {
        return !this.bossFight.isVisible();
    }

    private void evaporate() {
        Pair<BlockPos, BlockPos> minMax = this.getDefaultBounds(this);
        AetherBossMob.super.evaporate(this, minMax.getLeft(), minMax.getRight(), (blockState) -> true);
    }

    public void customServerAiStep() {
        super.customServerAiStep();
        this.bossFight.setProgress(this.getHealth() / this.getMaxHealth());

        if (this.getDungeon() != null) {
            this.getDungeon().trackPlayers();
            if (this.isBossFight() && (this.getDungeon().dungeonPlayers().isEmpty() || !this.getDungeon().isBossWithinRoom())) {
                this.reset();
            }
        }

        this.trackDungeon();
    }

    @Override
    public boolean hurt(DamageSource source, float amount) {
        if (source.is(DamageTypeTags.BYPASSES_INVULNERABILITY))
            return super.hurt(source, amount);
        else if (!this.isBossFight() && source.getDirectEntity() != null && source.getDirectEntity().getType() == EntityType.PLAYER) {
            this.start();
            return false;
        }

        if (source.getDirectEntity() != null && source.getDirectEntity().getType() == DAEntities.EOTS_SEGMENT.get()) {
            this.invulnerableTime = 0;
            return super.hurt(source, amount);
        }
        else if (source.getEntity() != null && source.getEntity().is(this) && this.isBossFight()) {
            this.invulnerableTime = 0;
            return super.hurt(source, amount);
        }
        else return false;
    }

    @Override
    public boolean fireImmune() {
        return true;
    }

    private void start() {
        this.getAwakenSound();
        this.playSound(this.getAwakenSound(), 2.5F, 1.0F / (this.getRandom().nextFloat() * 0.2F + 0.9F));

        this.setAwake(true);
        this.setBossFight(true);
        this.closeRoom();
        this.setPos(this.position().subtract(0, 6.0,0));

        this.spawnSegments();
        this.setInvisible(true);
        this.setHasBeenContactedBySegment();
        AetherEventDispatch.onBossFightStart(this, this.getDungeon());
    }

    @Override
    public void closeRoom() {
        if(this.getDungeon() != null) {
            this.getDungeon().modifyRoom(state -> {
                if (state.getBlock() instanceof DoorwayBlock || state.getBlock() instanceof DoorwayPillarBlock) {
                    return state.setValue(DoorwayBlock.INVISIBLE, false);
                } else {
                    return null;
                }
            });
        }
    }

    @Override
    public void reset() {
        this.setDeltaMovement(Vec3.ZERO);
        this.setAwake(false);
        this.setBossFight(false);
        this.setInvisible(false);
        this.setTarget(null);
        this.setHealth(this.getMaxHealth());
        if (this.getDungeon() != null) {
            this.setPos(this.getDungeon().originCoordinates());
            this.openRoom();
        }

        this.setInvisible(false);
        this.removeAllSegments();
        AetherEventDispatch.onBossFightStop(this, this.getDungeon());
    }

    @Override
    public void die(@NotNull DamageSource source) {
        this.setDeltaMovement(Vec3.ZERO);
        this.setPos(this.position().add(0, 8, 0));
        if (this.level() instanceof ServerLevel) {
            this.removeAllSegments();
            this.bossFight.setProgress(this.getHealth() / this.getMaxHealth());
            if (this.getDungeon() != null) {
                this.getDungeon().grantAdvancements(source);
                this.tearDownRoom();
            }
        }

        super.die(source);
    }

    @Override
    public void tearDownRoom() {
        if(this.getDungeon() != null) {
            AABB aabb = this.getDungeon().roomBounds();
            for (BlockPos pos : BlockPos.betweenClosed((int) aabb.minX - 10, (int) aabb.minY, (int) aabb.minZ - 10, (int) aabb.maxX + 10, (int) aabb.maxY, (int) aabb.maxZ +10)) {
                BlockState state = this.level().getBlockState(pos);
                BlockState newState = this.convertBlock(state);
                if (newState != null) {
                    this.level().setBlock(pos, newState, 1 | 2);
                }
            }
        }
    }

    private static final UUID HEALTH_UUID = UUID.fromString("385fead7-a5d3-49c7-8c8f-532403c5a7e9");
    protected void spawnSegments() {
        EOTSSegment oldSegment = new EOTSSegment(this.level(), this);
        this.segmentUUIDs.add(oldSegment.getUUID());
        int extra = (this.bossFight.getPlayers().size() - 1) * EXTRA_SEGMENT;
        AttributeInstance instance = this.getAttribute(Attributes.MAX_HEALTH);
        if(instance != null) {
            instance.removeModifier(HEALTH_UUID);
            instance.addTransientModifier(new AttributeModifier(HEALTH_UUID,"eots_health_multiplayer", extra*15.0F, AttributeModifier.Operation.ADDITION));
            oldSegment.setHealth(this.getMaxHealth());
        }

        for (int i = 0; i < SEGMENT_COUNT-1 + extra; i++) {
            oldSegment = new EOTSSegment(this.level(), oldSegment, this);
        }
    }

    private void removeAllSegments() {
        for (UUID segmentUUID : this.segmentUUIDs) {
            EOTSSegment segment = (EOTSSegment)((ServerLevel)this.level()).getEntity(segmentUUID);
            if(segment != null)
                segment.remove(RemovalReason.DISCARDED);
        }
        this.segmentUUIDs = new ArrayList<>();
        this.controllingSegments = new ArrayList<>();
        this.hasBeenContactedBySegment = false;
    }

    public void spawnParticles() {
        for (int i = 0; i < 2; ++i) {
            this.level().addParticle(DAParticles.EOTS_PRE_FIGHT.get(), this.getX() - 1, this.getY() + 0.25 + (random.nextFloat() * 2), this.getZ(), 0, 0.001 + (random.nextFloat() * 0.002), 0);
        }
    }

    @Override
    protected InteractionResult mobInteract(Player pPlayer, InteractionHand pHand) {
        if (!this.level().isClientSide()) {
            if (this.getChatCooldown() <= 0) {
                pPlayer.sendSystemMessage(Component.translatable("gui.deep_aether.eots.message.interact"));
                this.setChatCooldown(15);
            }
        }
        return super.mobInteract(pPlayer, pHand);
    }

    public int getChatCooldown() {
        return this.chatCooldown;
    }

    public void setChatCooldown(int cooldown) {
        this.chatCooldown = cooldown;
    }

    @Override
    public void knockback(double strength, double x, double z) {
    }

    @Override
    public void push(double x, double y, double z) {
    }

    @Override
    public void checkDespawn() {
    }

    @Nullable
    @Override
    public BlockState convertBlock(BlockState state) {
        if (state.is(DABlocks.LOCKED_NIMBUS_STONE.get())) {
            return DABlocks.NIMBUS_STONE.get().defaultBlockState();
        } else if (state.is(DABlocks.LOCKED_LIGHT_NIMBUS_STONE.get())) {
            return DABlocks.LIGHT_NIMBUS_STONE.get().defaultBlockState();
        }
        else if (state.is(DABlocks.LOCKED_NIMBUS_PILLAR.get())) {
            return DABlocks.NIMBUS_PILLAR.get().defaultBlockState();
        } else if (state.is(DABlocks.LOCKED_LIGHT_NIMBUS_PILLAR.get())) {
            return DABlocks.LIGHT_NIMBUS_PILLAR.get().defaultBlockState();
        } else if (state.is(DABlocks.BOSS_DOORWAY_NIMBUS_STONE.get()) || state.is(DABlocks.BOSS_DOORWAY_NIMBUS_PILLAR)) {
            return Blocks.AIR.defaultBlockState();
        } else {
            return state.is(DABlocks.TREASURE_DOORWAY_NIMBUS_STONE.get()) ? AetherBlocks.SKYROOT_TRAPDOOR.get().defaultBlockState().setValue(HorizontalDirectionalBlock.FACING, state.getValue(HorizontalDirectionalBlock.FACING)) : null;
        }
    }

    @Override
    public void startSeenByPlayer(@NotNull ServerPlayer player) {
        super.startSeenByPlayer(player);
        PacketRelay.sendToPlayer(new BossInfoPacket.Display(this.bossFight.getId(), this.getId()), player);
        if (this.getDungeon() == null || this.getDungeon().isPlayerTracked(player)) {
            this.bossFight.addPlayer(player);
            AetherEventDispatch.onBossFightPlayerAdd(this, this.getDungeon(), player);
        }
    }

    @Override
    public void stopSeenByPlayer(@NotNull ServerPlayer player) {
        super.stopSeenByPlayer(player);
        PacketRelay.sendToPlayer(new BossInfoPacket.Remove(this.bossFight.getId(), this.getId()), player);
        this.bossFight.removePlayer(player);
        AetherEventDispatch.onBossFightPlayerRemove(this, this.getDungeon(), player);
    }

    @Override
    public void onDungeonPlayerAdded(@Nullable Player player) {
        if (player instanceof ServerPlayer serverPlayer) {
            this.bossFight.addPlayer(serverPlayer);
            AetherEventDispatch.onBossFightPlayerAdd(this, this.getDungeon(), serverPlayer);
        }

    }

    @Override
    public void onDungeonPlayerRemoved(@Nullable Player player) {
        if (player instanceof ServerPlayer serverPlayer) {
            this.bossFight.removePlayer(serverPlayer);
            AetherEventDispatch.onBossFightPlayerRemove(this, this.getDungeon(), serverPlayer);
        }

    }

    protected void setHasBeenContactedBySegment() {
        this.hasBeenContactedBySegment = true;
        this.setInvisible(true);
    }


    public boolean isAwake() {
        return this.getEntityData().get(DATA_AWAKE_ID);
    }

    public void setAwake(boolean awake) {
        this.getEntityData().set(DATA_AWAKE_ID, awake);
    }

    public Component getBossName() {
        return this.getEntityData().get(DATA_BOSS_NAME_ID);
    }

    public void setBossName(Component component) {
        this.getEntityData().set(DATA_BOSS_NAME_ID, component);
        this.bossFight.setName(component);
    }

    @Nullable
    @Override
    public BossRoomTracker<EOTSController> getDungeon() {
        return this.brassDungeon;
    }

    @Override
    public void setDungeon(@Nullable BossRoomTracker<EOTSController> dungeon) {
        this.brassDungeon = dungeon;
    }

    @Override
    public boolean isBossFight() {
        return this.bossFight.isVisible();
    }

    @Override
    public void setBossFight(boolean isFighting) {
        this.bossFight.setVisible(isFighting);
    }


    /**
     * Temporary
     * @return The {@link ResourceLocation} for this boss's health bar.
     */
    @Nullable
    @Override
    public ResourceLocation getBossBarTexture() {
        return ResourceLocation.fromNamespaceAndPath(Aether.MODID, "boss_bar/slider");
    }

    /**
     * Temporary
     * @return The {@link ResourceLocation} for this boss's health bar background.
     */
    @Nullable
    @Override
    public ResourceLocation getBossBarBackgroundTexture() {
        return ResourceLocation.fromNamespaceAndPath(Aether.MODID, "boss_bar/slider_background");
    }
    @Override
    public int getDeathScore() {
        return this.deathScore;
    }

    @Override
    public void setCustomName(@Nullable Component name) {
        super.setCustomName(name);
        this.setBossName(name);
    }

    @NotNull
    protected SoundEvent getAwakenSound() {
        return AetherSoundEvents.ENTITY_SLIDER_AWAKEN.get();
    }

    /*
    @Override
    @Nullable
    protected SoundEvent getAmbientSound() {
        return AetherSoundEvents.ENTITY_ZEPHYR_SHOOT.get();
    }
     */

    protected SoundEvent getDeathSound() {
        return null;
    }

    @Override
    @NotNull
    public SoundSource getSoundSource() {
        return SoundSource.HOSTILE;
    }

    @Override
    public boolean canAttack(@NotNull LivingEntity target) {
        return false;
    }

    @Override
    public boolean ignoreExplosion(@NotNull Explosion explosion) {
        return true;
    }

    @Override
    public float getYRot() {
        return 0.0F;
    }

    @Override
    protected boolean canRide(@NotNull Entity vehicle) {
        return false;
    }

    @Override
    public boolean isPushable() {
        return false;
    }

    @Override
    public boolean isNoGravity() {
        return true;
    }

    @Override
    public boolean shouldDiscardFriction() {
        return true;
    }

    @Override
    protected boolean isAffectedByFluids() {
        return false;
    }

    @Override
    public boolean displayFireAnimation() {
        return false;
    }

    @Override
    public boolean isFullyFrozen() {
        return false;
    }

    @Override
    @NotNull
    protected Entity.MovementEmission getMovementEmission() {
        return MovementEmission.EVENTS;
    }

    @Override
    public void addAdditionalSaveData(@NotNull CompoundTag tag) {
        super.addAdditionalSaveData(tag);
        this.addBossSaveData(tag);
        tag.putBoolean("Awake", this.isAwake());
    }

    @Override
    public void readAdditionalSaveData(@NotNull CompoundTag tag) {
        super.readAdditionalSaveData(tag);
        this.readBossSaveData(tag);
        if (tag.contains("Awake")) {
            this.setAwake(tag.getBoolean("Awake"));
        }

    }

    @Override
    public void writeSpawnData(FriendlyByteBuf buffer) {
        CompoundTag tag = new CompoundTag();
        this.addBossSaveData(tag);
        buffer.writeNbt(tag);
    }

    @Override
    public void readSpawnData(FriendlyByteBuf additionalData) {
        CompoundTag tag = additionalData.readNbt();
        if (tag != null) {
            this.readBossSaveData(tag);
        }

    }
}
