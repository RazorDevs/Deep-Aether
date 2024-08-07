package teamrazor.deepaether.entity.living.boss.eots;


import com.aetherteam.aether.AetherTags;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.control.LookControl;
import net.minecraft.world.entity.ai.control.MoveControl;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.ai.targeting.TargetingConditions;
import net.minecraft.world.entity.monster.Enemy;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.ServerLevelAccessor;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.Vec3;
import net.neoforged.neoforge.event.EventHooks;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import teamrazor.deepaether.entity.living.projectile.WindCrystal;
import teamrazor.deepaether.init.DAEntities;

import java.util.EnumSet;
import java.util.UUID;

public class EOTSSegment extends FlyingMob implements Enemy {
    /*
    private boolean shouldGoToMiddle = false; //Not yet implemented
    private BlockPos middle; //Not yet implemented
    */
    private boolean hasContactedControllerOnLoad = false;

    @Nullable
    protected EOTSSegment parent;
    @Nullable
    private UUID parentUUID;
    @Nullable
    protected EOTSController controller; //Not yet implemented
    @Nullable
    private UUID controllerUUID; //Not yet implemented
    private boolean shouldMove = true;
    private static final EntityDataAccessor<Boolean> DATA_HEAD_ID = SynchedEntityData.defineId(EOTSSegment.class, EntityDataSerializers.BOOLEAN);
    public static final EntityDataAccessor<String> PARENT_DATA = SynchedEntityData.defineId(EOTSSegment.class, EntityDataSerializers.STRING);

    public EOTSSegment(EntityType<? extends EOTSSegment> pEntityType, Level pLevel) {
        super(pEntityType, pLevel);
        this.moveControl = new EotsSegmentMoveControl(this);
        this.lookControl = new EotsLookControl(this);
        this.noPhysics = true;
    }

    /**
     * Used to spawn multiple segments at once
     * {@link EOTSController#SEGMENT_COUNT}
     */
    protected EOTSSegment(Level level, EOTSSegment parent, int length) {
        this(DAEntities.EOTS_SEGMENT.get(), level);
        this.setPos(parent.getOnPos().getCenter());
        level.addFreshEntity(this);
        this.setParent(parent);
        if (length < EOTSController.SEGMENT_COUNT) {
            new EOTSSegment(level, this, length + 1);
        }
        this.hasContactedControllerOnLoad = true;
    }

    public EOTSSegment(Level level, EOTSSegment parent, EOTSController controller) {
        this(DAEntities.EOTS_SEGMENT.get(), level);
        this.setPos(parent.getOnPos().getCenter());
        level.addFreshEntity(this);
        this.setParent(parent);
        this.setController(controller);
        if(this.getController() != null) {
            this.getController().segmentUUIDs.add(this.uuid);
        }
        this.hasContactedControllerOnLoad = true;
    }

    public EOTSSegment(Level level, EOTSController controller) {
        this(DAEntities.EOTS_SEGMENT.get(), level);
        this.setPos(controller.getOnPos().getCenter());
        level.addFreshEntity(this);
        this.setController(controller);
        this.hasContactedControllerOnLoad = true;
    }

    /**
     * Max Health should be equal to the controller's health divided by the numbers of segments
     */
    @NotNull
    public static AttributeSupplier.Builder createMobAttributes() {
        return Mob.createMobAttributes().add(Attributes.MAX_HEALTH, 20.0).add(Attributes.FOLLOW_RANGE, 128.0).add(Attributes.ATTACK_DAMAGE, 15.0);
    }

    @Override
    protected void defineSynchedData() {
        super.defineSynchedData();
        this.getEntityData().define(DATA_HEAD_ID, true);
        this.getEntityData().define(PARENT_DATA, this.getParent() != null ? this.getParentUUID().toString() : this.getStringUUID());
    }
    @Nullable
    @SuppressWarnings("deprecation")
    public SpawnGroupData finalizeSpawn(@NotNull ServerLevelAccessor pLevel, @NotNull DifficultyInstance pDifficulty, @NotNull MobSpawnType pReason, @Nullable SpawnGroupData pSpawnData, @Nullable CompoundTag pDataTag) {
        new EOTSSegment(this.level(), this, 0); //Ensures multiple segments spawns in if spawn command is used
        return super.finalizeSpawn(pLevel, pDifficulty, pReason, pSpawnData, pDataTag);
    }

    @Override
    protected void registerGoals() {
        this.goalSelector.addGoal(0, new EotsAirChargeGoal(this));
        this.goalSelector.addGoal(1, new EotsAttackGoal(this));
        this.goalSelector.addGoal(2, new RandomFloatAroundGoal(this));
        this.targetSelector.addGoal(3, new NearestAttackableTargetGoal<>(this, Player.class, false));
    }

    @Override
    public void checkDespawn() {
    }

    /**
     * Used to rotate and position a body segment.
     * Converts a body segment into a head segment if the parent (head) segment is missing
     */
    @Override
    public void tick() {
        if(!this.hasContactedControllerOnLoad) {
            if(this.getControllerUUID() == null) {
                this.hasContactedControllerOnLoad = true;
            }
            else if (this.getController() != null) {
                this.getController().segmentUUIDs.add(this.uuid);
                if (this.isControllingSegment()) {
                    this.getController().controllingSegments.add(this);
                    this.getController().setInvisible(true);
                }
                this.hasContactedControllerOnLoad = true;
                this.getController().setHasBeenContactedBySegment();
            }
        }
        super.tick();

        if (!this.isControllingSegment()) {
            EOTSSegment parent = this.getParent();
            if (parent != null) {
                float yRot = this.getYRot();
                float yParentRot = parent.getYRot();
                float newYRot;
                if (Math.abs(yRot - yParentRot) < 0.1F) {
                    newYRot = yRot;
                } else {
                    newYRot = Mth.lerp(0.15F, yRot, yParentRot);
                }

                float xRot = this.getXRot();
                float xParentRot = parent.getXRot();
                float newXRot;
                if (Math.abs(xRot - xParentRot) < 0.1F) {
                    newXRot = xRot;
                } else {
                    newXRot = Mth.lerp(0.15F, xRot, xParentRot);
                }

                //Rotates the segment towards the parent segment
                this.setRot(newYRot, newXRot);

                //Positions the segment behind the parent segment
                this.setPos((parent.position().subtract(parent.getLookAngle().multiply(1.8F, 0F, 1.8F))).subtract(parent.getLookAngle().reverse().multiply(0F, 1.8F, 0F)));
            } else if (this.level() instanceof ServerLevel) {

                //Converts a body segment into a head segment
                this.setControllingSegment(true);
                if (this.getController() != null)

                    //Adds itself to the list of controlling Segments
                    this.getController().controllingSegments.add(this);
            }

            if (this.getTarget() != null) {
                if (this.getTarget().distanceToSqr(this) < 1.0F) {
                    this.doHurtTarget(this.getTarget());
                }
            }
        }
    }

    @Override
    public boolean canDisableShield() {
        return true;
    }

    @Override
    protected void blockedByShield(@NotNull LivingEntity pDefender) {
        super.blockedByShield(pDefender);
        if(pDefender instanceof Player player) {
            player.getCooldowns().addCooldown(player.getUseItem().getItem(), 1000);
            player.invulnerableTime = 20;
        }
    }

    @Override
    public boolean hurt(@NotNull DamageSource pSource, float pAmount) {
        float health = this.getHealth();
        boolean doHurt = super.hurt(pSource, pAmount);
        if(this.getController() != null) {
            this.getController().hurt(createControllerDamageSource(pSource.getEntity()), Math.min(pAmount, health));
        }
        return doHurt;
    }

    private DamageSource createControllerDamageSource(@Nullable Entity trueSource) {
        return new DamageSource(this.level().damageSources().generic().typeHolder(), this, trueSource);
    }

    @Override
    public boolean fireImmune() {
        return true;
    }

    @Override
    public void die(@NotNull DamageSource pDamageSource) {
        if(this.getController() != null) {
            this.getController().segmentUUIDs.remove(this.uuid);
            if(this.isControllingSegment())
                this.getController().controllingSegments.remove(this);
            this.getController().deathPos = this.position();
        }
        super.die(pDamageSource);
    }

    public boolean canCollideWith(@NotNull Entity pEntity) {
        return pEntity.getType() != DAEntities.EOTS_SEGMENT.get();
    }

    @Override
    public boolean canAttackType(@NotNull EntityType<?> pType) {
        return true;
    }

    /**
     * @return whenever the segment is a head (controlling segment) or not
     * Used in {@link teamrazor.deepaether.client.model.EOTSSegmentModel} to renderer the correct model
     */
    public boolean isControllingSegment() {
        return this.getEntityData().get(DATA_HEAD_ID);
    }

    public void setControllingSegment(boolean head) {
        this.getEntityData().set(DATA_HEAD_ID, head);
    }

    /**
     * @return the parent's UUID as a fallback if {@link #parent} is missing
     */
    private @Nullable UUID getParentUUID() {
        return this.parentUUID;
    }

    /**
     * Checks if the parent is present, and uses the parent's UUID as a fallback
     * @return the parent object
     */
    @Nullable
    public EOTSSegment getParent() {
        if (this.parent != null && !this.parent.isRemoved()) {
            return this.parent;
        } else if(this.parentUUID == null) {
            return null;
        } else if (this.parent == null && this.level() instanceof ServerLevel) {
            this.parent = (EOTSSegment)((ServerLevel)this.level()).getEntity(this.parentUUID);
            return this.parent;
        } else {
            return null;
        }
    }

    private void setParentUUID(@Nullable UUID uuid) {
        this.parentUUID = uuid;
    }

    /**
     * Saves the parent object and the parent's UUID
     */
    public void setParent(@Nullable EOTSSegment parent) {
        this.setControllingSegment(parent == null);
        this.parent = parent;
        if (parent == null) {
            this.setParentUUID(null);
        } else {
            this.setParentUUID(parent.getUUID());
        }

    }

    private void setControllerUUID(@Nullable UUID uuid) {
        this.controllerUUID = uuid;
    }

    @Nullable
    private UUID getControllerUUID() {
        return this.controllerUUID;
    }

    public void setController(@Nullable EOTSController controller) {
        this.controller = controller;
        if (controller == null) {
            this.setControllerUUID(null);
        } else {
            this.setControllerUUID(controller.getUUID());
        }
    }


    /**
     * Checks if an EOTSController is present, and uses the EOTSController's UUID as a fallback
     * @return the EOTSController object
     */
    @Nullable
    public EOTSController getController() {
        if (this.controller != null && !this.controller.isRemoved()) {
            return this.controller;
        } else if(this.controllerUUID == null) {
            return null;
        } else if (this.controller == null && this.level() instanceof ServerLevel) {
            this.controller = (EOTSController) ((ServerLevel)this.level()).getEntity(this.getControllerUUID());
            return this.controller;
        } else {
            return null;
        }
    }

    /**
     * Not yet implemented
     * @param middle The position the controlling segment should move towards
     */
    public void setGoToMiddle(BlockPos middle) {
        //this.shouldGoToMiddle = true;
        //this.middle = middle;
    }

    private float getIdleYPos() {
        if(this.getController() != null)
            return  (float) (this.getController().position().y + 15.0F);
        else if(this.getTarget() != null)
            return (float) (this.getTarget().position().y + 15.0F);
        else return 255.0F;
    }

    private float getGlobalSpeedModifier() {
        EOTSController controller = this.getController();
        if(controller != null) {
            return Mth.lerp(Mth.abs(controller.getHealth()/controller.getMaxHealth()), 2.0F, 1.0F);
        }
        else return 1.0F;
    }

    /**
     * Used to check if EOTS can perform or start a new attack
     * @return If the EOTS segments is close to its idle pos, assuming it's not above its idle pos.
     */
    public boolean isAroundIdlePos() {
        return this.getY() > this.getIdleYPos() - 3;
    }

    private void goToIdlePos() {
        float speed = 1.75F;
        if(this.isAroundIdlePos())
            speed = 1.0F;

        float floatAroundHeight = this.getIdleYPos();
        if(this.getTarget() == null) {
            RandomSource random = this.getRandom();
            double d0 = this.getX() + (double) ((random.nextFloat() * 2.0F - 1.0F) * 8.0F);
            double d1 = floatAroundHeight + random.nextInt(1);
            double d2 = this.getZ() + (double) ((random.nextFloat() * 2.0F - 1.0F) * 8.0F);
            this.getMoveControl().setWantedPosition(d0, d1, d2, speed);
        }
        else {
            RandomSource random = this.getRandom();
            double d0 = this.getTarget().getX() + (double) ((random.nextFloat() * 2.0F - 1.0F) * 8.0F);
            double d1 = floatAroundHeight + random.nextInt(1);
            double d2 = this.getTarget().getZ() + (double) ((random.nextFloat() * 2.0F - 1.0F) * 8.0F);
            this.getMoveControl().setWantedPosition(d0, d1, d2, speed);
        }
    }



    @Override
    public void readAdditionalSaveData(@NotNull CompoundTag tag) {
        super.readAdditionalSaveData(tag);
        if(tag.hasUUID("Controller"))
            this.setControllerUUID(tag.getUUID("Controller"));
        if (tag.hasUUID("Parent")) {
            this.setParentUUID(tag.getUUID("Parent"));
            this.setControllingSegment(false);
        }
    }

    public void addAdditionalSaveData(@NotNull CompoundTag tag) {
        super.addAdditionalSaveData(tag);
        if(this.getControllerUUID() != null)
            tag.putUUID("Controller", this.getControllerUUID());
        if (this.getParentUUID() != null) {
            tag.putUUID("Parent", this.getParentUUID());
        }
    }


    protected static class EotsLookControl extends LookControl {
        EOTSSegment segment;
        public EotsLookControl(EOTSSegment segment) {
            super(segment);
            this.segment = segment;
        }

        /**
         * Updates look
         */
        @Override
        public void tick() {
        }
    }

    /**
     * Modified version of @link net.minecraft.world.entity.monster.Phantom#PhantomMoveControl
     * Handles the rotation and movement of the controlling segment
     */
    protected static class EotsSegmentMoveControl extends MoveControl {
        private final EOTSSegment segment;
        private float baseSpeed = 0.1F;

        public EotsSegmentMoveControl(EOTSSegment segment) {
            super(segment);
            this.segment = segment;
        }

        @Override
        public void tick() {
            if (this.segment.isControllingSegment()) {
                double d0 = this.segment.moveControl.getWantedX() - this.segment.getX();
                double d1 = this.segment.moveControl.getWantedY() - this.segment.getY();
                double d2 = this.segment.moveControl.getWantedZ() - this.segment.getZ();
                double d3 = Math.sqrt(d0 * d0 + d2 * d2);
                if (Math.abs(d3) > 9.999999747378752E-6) {
                    double d4 = 1.0 - Math.abs(d1 * 0.699999988079071) / d3;
                    d0 *= d4;
                    d2 *= d4;
                    d3 = Math.sqrt(d0 * d0 + d2 * d2);
                    double d5 = Math.sqrt(d0 * d0 + d2 * d2 + d1 * d1);
                    float f = this.segment.getYRot();
                    float f1 = (float) Mth.atan2(d2, d0);
                    float f2 = Mth.wrapDegrees(this.segment.getYRot() + 90.0F);
                    float f3 = Mth.wrapDegrees(f1 * 57.295776F);
                    this.segment.setYRot(Mth.approachDegrees(f2, f3, 4.0F) - 90.0F);
                    this.segment.yBodyRot = this.segment.getYRot();
                    if (Mth.degreesDifferenceAbs(f, this.segment.getYRot()) < 3.0F) {
                        this.baseSpeed = Mth.approach(this.baseSpeed, 1.8F, 0.005F * (1.8F / this.baseSpeed));
                    } else {
                        this.baseSpeed = Mth.approach(this.baseSpeed, 0.2F, 0.025F);
                    }

                    // Introduce a vertical wave motion using sine function
                    double waveFrequency = 0.05; // Frequency of the wave
                    double waveAmplitude = 0.8; // Amplitude of the wave
                    double time = this.segment.tickCount;
                    EOTSController controller = this.segment.getController();
                    if(controller != null)
                        time += controller.controllingSegments.indexOf(this); // Ensure different segments are out of phase
                    double verticalWave = Math.sin(time * waveFrequency) * waveAmplitude;

                    float f4 = (float) (-(Mth.atan2(-d1 + verticalWave, d3) * 180.0 / 3.1415927410125732));
                    this.segment.setXRot(f4);
                    float f5 = this.segment.getYRot() + 90.0F;
                    double d6 = (double) (this.getSpeed() * Mth.cos(f5 * 0.017453292F)) * Math.abs(d0 / d5);
                    double d7 = (double) (this.getSpeed() * Mth.sin(f5 * 0.017453292F)) * Math.abs(d2 / d5);
                    double d8 = (double) (this.getSpeed() * Mth.sin(f4 * 0.017453292F)) * Math.abs((d1 + verticalWave) / d5);
                    Vec3 vec3 = this.segment.getDeltaMovement();
                    this.segment.setDeltaMovement(vec3.add((new Vec3(d6, d8, d7)).subtract(vec3).scale(0.2)));
                }
            }
        }

        private float getSpeed() {
            return baseSpeed * (float) this.segment.moveControl.getSpeedModifier() * this.segment.getGlobalSpeedModifier();
        }
    }

    /**
     * Makes the segment randomly float around when no other goals are active
     * Should be updated release
     */
    protected static class RandomFloatAroundGoal extends Goal {
        private final EOTSSegment segment;

        public RandomFloatAroundGoal(EOTSSegment segment) {
            this.segment = segment;
            this.setFlags(EnumSet.of(Flag.MOVE));
        }

        public boolean canUse() {
            if (!this.segment.isControllingSegment() || !this.segment.shouldMove) {
                return false;
            } else {
                MoveControl moveControl = this.segment.getMoveControl();
                if (!moveControl.hasWanted()) {
                    return true;
                } else {
                    double d0 = moveControl.getWantedX() - this.segment.getX();
                    double d1 = moveControl.getWantedY() - this.segment.getY();
                    double d2 = moveControl.getWantedZ() - this.segment.getZ();
                    double d3 = d0 * d0 + d1 * d1 + d2 * d2;
                    return d3 < 1.0 || d3 > 3600.0;
                }
            }
        }

        public boolean canContinueToUse() {
            return false;
        }

        public void start() {
            this.segment.goToIdlePos();
        }
    }

    protected static class EotsAttackGoal extends Goal {
        EOTSSegment segment;
        private int nextScanTick = 150;
        private boolean hasAttacked = false; //Used for sweeping attack pattern
        int maxFollowingTimer = 150; //Used for following attack pattern
        Vec3 targetStartPos;
        private enum AttackType {
            FOLLOWING, //Follows the player for a certain amount of time
            SWEEPING //Sweeps down one time to then fly up again
        }
        private AttackType attackType = AttackType.SWEEPING;
        public EotsAttackGoal(EOTSSegment segment) {
            this.segment = segment;
        }

        @Override
        public boolean canUse() {
            if(!this.segment.isAroundIdlePos() || !this.segment.isControllingSegment() || !this.segment.shouldMove || this.segment.getTarget() == null)
                return false;
            else if (this.nextScanTick > 0) {
                --this.nextScanTick;
                return false;
            } else {
                this.hasAttacked = false;
                this.nextScanTick = (int) (((float) (50 + this.segment.random.nextInt(-25,40)))/this.segment.getGlobalSpeedModifier());
                return true;
            }
        }

        @Override
        public void start() {
            if(segment.getRandom().nextBoolean())
                attackType = AttackType.SWEEPING;
            else attackType = AttackType.FOLLOWING;
            maxFollowingTimer = 150;
            if(this.segment.getTarget() != null)
                this.targetStartPos = this.segment.getTarget().position();
        }

        @Override
        public boolean canContinueToUse() {
            if(this.segment.hurtTime > 0) {
                this.segment.goToIdlePos(); //Forces the segment to go to its idle pos if it takes damage
                return false;
            }
            if(this.hasAttacked || this.maxFollowingTimer <= 0)
                return false;
            LivingEntity livingentity = this.segment.getTarget();
            return livingentity != null && this.segment.canAttack(livingentity, TargetingConditions.DEFAULT);
        }

        @Override
        public void tick() {
            if(this.segment.getTarget() != null && this.segment.isControllingSegment()) {
                if(this.targetStartPos == null) {
                    hasAttacked = true;
                }
                else if(this.attackType == AttackType.SWEEPING) {
                    Vec3 pos = this.segment.getTarget().position();
                    this.segment.moveControl.setWantedPosition(pos.x(), pos.y(), pos.z(), 1.75F);
                   if (this.segment.position().y < this.targetStartPos.y || this.segment.position().y < this.segment.getTarget().position().y)
                       this.hasAttacked = true;
                }
                else {
                    Vec3 pos = this.segment.getTarget().position().add(0.0D,1.0D,0.0D);
                    this.segment.moveControl.setWantedPosition(pos.x(), pos.y(), pos.z(), 1.5F);
                    this.maxFollowingTimer--;
                }

                if (this.segment.getBoundingBox().inflate(0.2F).intersects(this.segment.getTarget().getBoundingBox())) {
                    this.segment.doHurtTarget(this.segment.getTarget());
                    this.segment.getTarget().setDeltaMovement(this.segment.getLookAngle().multiply(2.0F,2.0F,2.0F));
                    this.hasAttacked = true;
                }
                //BlockState state = this.segment.level().getBlockState(this.segment.getOnPos());
                //if(!state.isAir())
                //    tryBreakBlock(state, this.segment.getOnPos());

            }
        }

        private void tryBreakBlock(BlockState state, BlockPos pos) {
            if (!this.segment.level().isClientSide()) {
                if (EventHooks.getMobGriefingEvent(this.segment.level(), this.segment)) {
                    for (int i = 0; i < 2; i++) {
                        if (this.isBreakable(state)) {
                            EOTSController controller = this.segment.getController();
                            if (controller != null) {
                                if (controller.getDungeon() != null) {
                                    if (controller.getDungeon().roomBounds() != null) {
                                        if (controller.getDungeon().roomBounds().contains(pos.getCenter())) {
                                            this.segment.level().destroyBlock(pos, true);
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }

        private boolean isBreakable(BlockState blockState) {
            return !blockState.is(AetherTags.Blocks.VALKYRIE_QUEEN_UNBREAKABLE) && blockState.getBlock().defaultDestroyTime() >= 0.0F && blockState.getBlock().defaultDestroyTime() < 100.0F;
        }

    }

    protected static class EotsAirChargeGoal extends Goal {
        EOTSSegment segment;
        private int attackTimer = 150; //Delay between attacks
        private int attackDelay = 9; //Gives the segment time to rotate against the player before attacking
        private int numberOfAttacks = 0;
        public EotsAirChargeGoal(EOTSSegment segment) {
            this.segment = segment;
        }

        @Override
        public boolean canUse() {
            if(!this.segment.isAroundIdlePos())
                return false;
            else if(!this.segment.isControllingSegment())
                return false;
            else if (this.attackTimer > 0) {
                --this.attackTimer;
                return false;
            } else {
                this.attackTimer = (int) (((float) (75 + this.segment.random.nextInt(-50,50)))/this.segment.getGlobalSpeedModifier());
                return true;
            }
        }

        @Override
        public boolean canContinueToUse() {
            if(attackDelay < -2) {
                return false;
            }
            LivingEntity livingentity = this.segment.getTarget();
            return livingentity != null && this.segment.canAttack(livingentity, TargetingConditions.DEFAULT);
        }

        @Override
        public void start() {
            this.attackDelay = 19;
            this.numberOfAttacks = (int) (this.segment.random.nextInt(0,3) * this.segment.getGlobalSpeedModifier());
            this.segment.shouldMove = false;
            super.start();
        }

        @Override
        public void stop() {
            this.segment.shouldMove = true;
            super.stop();
        }

        @Override
        public void tick() {
            if(this.segment.getTarget() != null) {
                this.lookAt(this.segment.getTarget());
                if(attackDelay <= 0) {
                    new WindCrystal(this.segment.level(), this.segment, this.segment.getLookAngle().multiply(0.7F,0.7F,0.7F).offsetRandom(this.segment.random, 0.3F));
                    if(numberOfAttacks > 0) {
                        numberOfAttacks--;
                        attackDelay = 9;
                    }
                    else attackDelay = -3;
                }
            }
            attackDelay--;
        }

        private void lookAt(LivingEntity target) {
            double d0 = target.getX() - this.segment.getX();
            double d1 = target.getEyeY() - this.segment.getEyeY();
            double d2 = target.getZ() - this.segment.getZ();

            double d3 = Math.sqrt(d0 * d0 + d2 * d2);
            this.segment.setXRot(Mth.wrapDegrees((float)(-(Mth.atan2(d1, d3) * 180.0F / (float)Math.PI))));
            this.segment.setYRot(Mth.wrapDegrees((float)(Mth.atan2(d2, d0) * 180.0F / (float)Math.PI) - 90.0F));

            this.segment.setYHeadRot(this.segment.getYRot());
            this.segment.xRotO = this.segment.getXRot();
            this.segment.yRotO = this.segment.getYRot();
        }
    }
}
