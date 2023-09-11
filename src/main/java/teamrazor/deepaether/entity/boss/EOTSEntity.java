package teamrazor.deepaether.entity.boss;


import com.aetherteam.aether.entity.AetherBossMob;
import com.aetherteam.aether.entity.ai.controller.BlankMoveControl;
import com.aetherteam.aether.entity.monster.dungeon.boss.BossNameGenerator;
import com.aetherteam.aether.entity.monster.dungeon.boss.Slider;
import com.aetherteam.aether.entity.projectile.crystal.AbstractCrystal;
import com.aetherteam.aether.network.AetherPacketHandler;
import com.aetherteam.aether.network.packet.serverbound.BossInfoPacket;
import com.aetherteam.nitrogen.entity.BossRoomTracker;
import com.aetherteam.nitrogen.network.PacketRelay;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.server.level.ServerBossEvent;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.util.Mth;
import net.minecraft.world.BossEvent;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.ServerLevelAccessor;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.network.PlayMessages;
import org.jetbrains.annotations.Nullable;
import software.bernie.geckolib.animatable.GeoEntity;
import software.bernie.geckolib.core.animatable.instance.AnimatableInstanceCache;
import software.bernie.geckolib.core.animation.AnimatableManager;
import software.bernie.geckolib.util.GeckoLibUtil;
import teamrazor.deepaether.effect.PullEffect;
import teamrazor.deepaether.entity.WindCharge;
import teamrazor.deepaether.init.DAEffects;
import teamrazor.deepaether.init.DAEntities;
import teamrazor.deepaether.init.DASounds;

import javax.annotation.Nonnull;
import java.util.Collection;
import java.util.List;
import java.util.UUID;

@SuppressWarnings({"unchecked", "SameReturnValue"})
@Mod.EventBusSubscriber
public class EOTSEntity extends Monster implements GeoEntity, AetherBossMob<EOTSEntity> {
    public static final EntityDataAccessor<Component> DATA_BOSS_NAME_ID = SynchedEntityData.defineId(Slider.class, EntityDataSerializers.COMPONENT);

    private BossRoomTracker<EOTSEntity> brassdungeon;
    private final ServerBossEvent bossFight;
    protected double velocity;
    private Vec3 origin;

    private int xMax = 9;
    private int zMax = 9;

    private final AnimatableInstanceCache factory = GeckoLibUtil.createInstanceCache(this);


    public EOTSEntity(PlayMessages.SpawnEntity packet, Level world) {
        this(DAEntities.EOTS.get(), world);
    }

    public EOTSEntity(EntityType<EOTSEntity> type, Level world) {
        super(type, world);
        this.moveControl = new BlankMoveControl(this);
        this.bossFight = new ServerBossEvent(this.getBossName(), BossEvent.BossBarColor.RED, BossEvent.BossBarOverlay.PROGRESS);
        this.setBossFight(true);
        this.xpReward = XP_REWARD_BOSS;
        this.noPhysics = true;
        this.velocity =  1 - this.getHealth() / 700;
        this.origin = this.position();
        this.setPersistenceRequired();
    }

    @Override
    public SpawnGroupData finalizeSpawn(@Nonnull ServerLevelAccessor pLevel, @Nonnull DifficultyInstance pDifficulty, @Nonnull MobSpawnType pReason, @javax.annotation.Nullable SpawnGroupData pSpawnData, @javax.annotation.Nullable CompoundTag pDataTag) {
        SpawnGroupData data = super.finalizeSpawn(pLevel, pDifficulty, pReason, pSpawnData, pDataTag);
        this.setBossName(generateEOTSName());
        this.origin = this.position();
        Entity c1 = new EOTSCloud(DAEntities.EOTS_CLOUD.get(), level);
        Entity c2 = new EOTSCloud(DAEntities.EOTS_CLOUD.get(), level);
        Entity c3 = new EOTSCloud(DAEntities.EOTS_CLOUD.get(), level);

        c1.setPos(this.origin.x + 1, this.origin.y + 0.4, this.origin.z + 1);
        c2.setPos(this.origin.x, this.origin.y + 0.4, this.origin.z - 1.1);
        c3.setPos(this.origin.x - 1, this.origin.y + 0.4, this.origin.z +1);

        level.addFreshEntity(c1);
        level.addFreshEntity(c2);
        level.addFreshEntity(c3);
        return data;
    }

    protected void registerGoals() {
        this.goalSelector.addGoal(1, new ShootAirBall(this));
        this.goalSelector.addGoal(2, new ApplyStormEffectGoal(this));
        this.goalSelector.addGoal(2, new SummonTornadoGoal(this));
    }


    public static AttributeSupplier.Builder createAttributes() {
        return Mob.createMobAttributes()
                .add(Attributes.MAX_HEALTH, 200.0)
                .add(Attributes.KNOCKBACK_RESISTANCE, 10.0D);
    }


    @Override
    public void die(@Nonnull DamageSource damageSource) {
        this.setDeltaMovement(Vec3.ZERO);
        if (this.level instanceof ServerLevel) {
            this.bossFight.setProgress(this.getHealth() / this.getMaxHealth());
            if (this.getDungeon() != null) {
                this.getDungeon().grantAdvancements(damageSource);
                this.tearDownRoom();
            }
        }
        super.die(damageSource);
    }

    @Override
    public void startSeenByPlayer(@Nonnull ServerPlayer pPlayer) {
        super.startSeenByPlayer(pPlayer);
        PacketRelay.sendToPlayer(AetherPacketHandler.INSTANCE, new BossInfoPacket.Display(this.bossFight.getId()), pPlayer);
        if (this.getDungeon() == null || this.getDungeon().isPlayerTracked(pPlayer)) {
            this.bossFight.addPlayer(pPlayer);
        }
    }

    @Override
    public void stopSeenByPlayer(@Nonnull ServerPlayer pPlayer) {
        super.stopSeenByPlayer(pPlayer);
        PacketRelay.sendToPlayer(AetherPacketHandler.INSTANCE, new BossInfoPacket.Remove(this.bossFight.getId()), pPlayer);
        this.bossFight.removePlayer(pPlayer);
    }

    @SuppressWarnings("unchecked")
    @Override
    public void customServerAiStep() {
        super.customServerAiStep();
        this.bossFight.setProgress(this.getHealth() / this.getMaxHealth());
        this.trackDungeon();
    }

    @Override
    public void defineSynchedData() {
        super.defineSynchedData();
        this.entityData.define(DATA_BOSS_NAME_ID, Component.literal("Eye of the Storm"));
    }

    @Override
    public void tick() {
        this.setNoGravity(true);
        super.tick();
        if (this.getHealth() > 0) {
            double x = this.getX() + (this.random.nextFloat() - 0.5F) * this.random.nextFloat();
            double y = this.getBoundingBox().minY + this.random.nextFloat() - 0.5;
            double z = this.getZ() + (this.random.nextFloat() - 0.5F) * this.random.nextFloat();
            this.level.addParticle(ParticleTypes.FLAME, x, y, z, 0, -0.07500000298023224, 0);
        }
        this.setYRot(Mth.rotateIfNecessary(this.getYRot(), this.yHeadRot, 20));
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
        return this.entityData.get(DATA_BOSS_NAME_ID);
    }

    @Override
    public void setBossName(Component component) {
        this.entityData.set(DATA_BOSS_NAME_ID, component);
        this.bossFight.setName(component);
    }

    @Override
    public void setCustomName(@javax.annotation.Nullable Component name) {
        super.setCustomName(name);
        this.setBossName(name);
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
        return this.deathScore;
    }

    @Override
    public boolean isBossFight() {
        return this.bossFight.isVisible();
    }

    @Override
    public void setBossFight(boolean isFighting) {
        this.bossFight.setVisible(isFighting);
    }

    @Override
    public void trackDungeon() {
        AetherBossMob.super.trackDungeon();
    }

    @Override
    public void displayTooFarMessage(Player player) {
        AetherBossMob.super.displayTooFarMessage(player);
    }

    @Override
    public void onDungeonPlayerAdded(@Nullable Player player) {
        AetherBossMob.super.onDungeonPlayerAdded(player);
    }

    @Override
    public void onDungeonPlayerRemoved(@Nullable Player player) {
        AetherBossMob.super.onDungeonPlayerRemoved(player);
    }

    @Override
    public void reset() {

    }

    @Override
    public void tearDownRoom() {
        AetherBossMob.super.tearDownRoom();
    }

    @Override
    public void closeRoom() {
        AetherBossMob.super.closeRoom();
    }

    @Override
    public void openRoom() {
        AetherBossMob.super.openRoom();
    }

    @org.jetbrains.annotations.Nullable
    @Override
    public BlockState convertBlock(BlockState state) {
        return null;
    }

    @Override
    public void addBossSaveData(CompoundTag tag) {
        AetherBossMob.super.addBossSaveData(tag);
    }

    @Override
    public void readBossSaveData(CompoundTag tag) {
        AetherBossMob.super.readBossSaveData(tag);
    }

    @Override
    public boolean alwaysAccepts() {
        return super.alwaysAccepts();
    }

    @Override
    public LivingEntity self() {
        return super.self();
    }

    @Override
    public boolean canDisableShield() {
        return true;
    }

    public MutableComponent generateEOTSName() {
        MutableComponent result = BossNameGenerator.generateBossName(this.random);
        return result.append(Component.translatable("gui.aether.slider.title"));
    }


    public static class ShootAirBall extends Goal {
        private final EOTSEntity eots;
        private int shootInterval;

        public ShootAirBall(EOTSEntity eots) {
            this.eots = eots;
            this.shootInterval = (int) (55 + eots.getHealth() / 2);
        }

        @Override
        public boolean canUse() {
            return this.eots.isBossFight() && --this.shootInterval <= 0;
        }

        @Override
        public void start() {
            AbstractCrystal crystal;
            crystal = new WindCharge(this.eots.level, this.eots, WindCharge.AttackPatterns.FOUR);
            //crystal.setDeltaMovement(0, 0.05, 0);
            this.eots.level.addFreshEntity(crystal);
            this.shootInterval = (int) (15 + eots.getHealth() / 2);
        }

        @Override
        public boolean requiresUpdateEveryTick() {
            return true;
        }
    }

    public static class ApplyStormEffectGoal extends Goal {
        private final EOTSEntity eots;
        private int shootInterval;

        public ApplyStormEffectGoal(EOTSEntity eots) {
            this.eots = eots;
            this.shootInterval = (int) (55 + eots.getHealth() / 2);
        }

        @Override
        public boolean canUse() {
            return this.eots.isBossFight() && --this.shootInterval <= 0;
        }

        @Override
        public void start() {
            Player[] players  = this.eots.bossFight.getPlayers().toArray(Player[]::new);
            Level level = eots.getLevel();
            for(int i = 0; i < players.length; i++) {
                if(players[i] != null) {
                    PullEffect effect = DAEffects.PULL.get();
                    effect.setPos(this.eots);
                    players[i].addEffect(new MobEffectInstance(effect, 100), this.eots);
                }
            }


            level.addParticle(ParticleTypes.BUBBLE, eots.position().x, eots.position().y, eots.position().z, 1, 0.07, 10);



            this.shootInterval = (int) (15 + eots.getHealth() / 2);
        }

        @Override
        public boolean requiresUpdateEveryTick() {
            return true;
        }
    }

    public static class SummonTornadoGoal extends Goal {
        private final EOTSEntity eots;
        private int shootInterval;

        public SummonTornadoGoal(EOTSEntity eots) {
            this.eots = eots;
            this.shootInterval = (int) (1 + eots.getHealth() / 2);
        }

        @Override
        public boolean canUse() {
            return this.eots.isBossFight() && --this.shootInterval <= 0;
        }

        @Override
        public void start() {
            EOTSTornado tornado;
            tornado = new EOTSTornado(this.eots.level, this.eots.position().add(eots.random.nextInt(5), 0, eots.random.nextInt(5)));
            tornado.setEots(eots);
            tornado.setLifeLeft(1000);
            tornado.setInvisible(true);
            this.eots.level.addFreshEntity(tornado);
            this.shootInterval = (int) (10 + eots.getHealth() / 2);
        }

        @Override
        public boolean requiresUpdateEveryTick() {
            return true;
        }
    }
}