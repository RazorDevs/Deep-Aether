package teamrazor.deepaether.entity;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.Mth;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.control.LookControl;
import net.minecraft.world.entity.ai.control.MoveControl;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.entity.ai.goal.target.TargetGoal;
import net.minecraft.world.entity.ai.targeting.TargetingConditions;
import net.minecraft.world.entity.animal.Wolf;
import net.minecraft.world.entity.animal.horse.AbstractHorse;
import net.minecraft.world.entity.decoration.ArmorStand;
import net.minecraft.world.entity.monster.Creeper;
import net.minecraft.world.entity.monster.Ghast;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;
import teamrazor.deepaether.init.DAEntities;
import teamrazor.deepaether.init.DASounds;
import teamrazor.deepaether.item.gear.EquipmentUtil;
import top.theillusivec4.curios.api.SlotResult;

import javax.annotation.Nullable;
import java.util.EnumSet;
import java.util.Optional;

public class GentleWind extends FlyingMob {
    private static final int RIDE_COOLDOWN = 300;
    private int rideCooldownCounter;

    private static final EntityDataAccessor<Integer> DATA_OWNER_ID = SynchedEntityData.defineId(GentleWind.class, EntityDataSerializers.INT);
    private static final EntityDataAccessor<Boolean> IS_ON_NECK = SynchedEntityData.defineId(GentleWind.class, EntityDataSerializers.BOOLEAN);

    public GentleWind(EntityType<? extends FlyingMob> type, Level level) {
        super(type, level);
        this.moveControl = new GentleWindMoveControl(this);
        this.lookControl = new GentleWindLookControl(this);
    }

    public GentleWind(Level level, Player owner) {
        this(DAEntities.GENTLE_WIND.get(), level);
        this.setOwner(owner);
        this.setPos(owner.position());
        this.setEntityAroundNeck();

        level.addFreshEntity(this);
    }

    @Override
    public boolean canBeHitByProjectile() {
        return false;
    }

    public static AttributeSupplier.Builder createMobAttributes() {
        return FlyingMob.createMobAttributes().add(Attributes.MAX_HEALTH, 1.0).add(Attributes.MOVEMENT_SPEED, 10.0);
    }

    @Override
    protected void defineSynchedData() {
        super.defineSynchedData();
        this.entityData.define(DATA_OWNER_ID, 0);
        this.entityData.define(IS_ON_NECK, false);
    }

    @Override
    protected void registerGoals() {
        this.targetSelector.addGoal(1, new GentleWindOwnerHurtByTargetGoal(this));
        this.targetSelector.addGoal(2, new GentleWindOwnerHurtTargetGoal(this));
        this.goalSelector.addGoal(2, new GentleWindAirChargeGoal(this));
        this.goalSelector.addGoal(3, new WrapAroundPlayerGoal(this));
        this.goalSelector.addGoal(4, new FollowPlayerGoal(this));
    }

    @Override
    public boolean canBeSeenAsEnemy() {
        return false;
    }

    protected void pushEntities() {
    }

    protected boolean canRide(Entity vehicle) {
        return false;
    }

    public boolean hurt(DamageSource source, float damage) {
        return false;
    }

    @Nullable
    public Player getOwner() {
        return (Player) this.level().getEntity(this.getEntityData().get(DATA_OWNER_ID));
    }

    @Nullable
    public ServerPlayer getServerOwner() {
        return (ServerPlayer) this.level().getEntity(this.getEntityData().get(DATA_OWNER_ID));
    }

    public void setOwner(Player entity) {
        this.getEntityData().set(DATA_OWNER_ID, entity.getId());
    }

    private void followOwner() {
        Player player = this.getOwner();
        if(player != null) {
            if(this.distanceTo(player) > 40)
                this.setPos(player.position().add(0, 2,0));

            this.moveControl.setWantedPosition(player.getX(), player.getY() + 2, player.getZ(), 1.0F);
        }
    }

    public boolean wantsToAttack(LivingEntity target, LivingEntity owner) {
        if (target instanceof Creeper || target instanceof Ghast || target instanceof ArmorStand || target instanceof GentleWind) {
            return false;
        } else if (target instanceof Wolf wolf) {
            return !wolf.isTame() || wolf.getOwner() != owner;
        } else {
            if (target instanceof Player player && owner instanceof Player player1 && !player1.canHarmPlayer(player)) {
                return false;
            }

            if (target instanceof AbstractHorse abstracthorse && abstracthorse.isTamed()) {
                return false;
            }

            return !(target instanceof TamableAnimal tamableanimal) || !tamableanimal.isTame();
        }
    }

    @Override
    protected void processPortalCooldown() {
    }

    @Override
    public void tick() {
        if(this.isWrappedAroundNeck() && this.getOwner() != null) {
            this.setPos(getOwner().getX(), getOwner().getY() + 50, getOwner().getZ());
            this.getNavigation().stop();
        }
        ++rideCooldownCounter;
        super.tick();
    }

    @Override
    public boolean displayFireAnimation() {
        return false;
    }

    public boolean isWrappedAroundNeck() {
        return this.entityData.get(IS_ON_NECK);
    }

    public boolean setEntityAroundNeck() {
        if(isWrappedAroundNeck()) return false;
        this.entityData.set(IS_ON_NECK, true);
        return true;
    }

    public boolean removeEntityAroundNeck() {
        if(!isWrappedAroundNeck()) return false;
        this.rideCooldownCounter = 0;
        this.entityData.set(IS_ON_NECK, false);
        this.setPos(getOwner().getX(), getOwner().getY() + 1.2, getOwner().getZ());
        return true;
    }

    public static class WrapAroundPlayerGoal extends Goal {
        private final GentleWind eots;
        private ServerPlayer owner;

        public WrapAroundPlayerGoal(GentleWind eots) {
            this.eots = eots;
            this.setFlags(EnumSet.of(Flag.JUMP, Flag.MOVE));
        }

        @Override
        public boolean canContinueToUse() {
            return this.eots.isWrappedAroundNeck() && this.eots.getTarget() == null;
        }

        public boolean canUse() {
            ServerPlayer serverplayer = this.eots.getServerOwner();
            if(serverplayer == null) return false;

            boolean flag = !serverplayer.isSpectator() && !serverplayer.isInWater() && !serverplayer.isInPowderSnow;
            return flag && !this.eots.isWrappedAroundNeck() && this.eots.rideCooldownCounter > RIDE_COOLDOWN && this.eots.getTarget() == null;
        }

        public void start() {
            this.owner = this.eots.getServerOwner();
        }

        @Override
        public void stop() {
            this.eots.removeEntityAroundNeck();
        }

        public void tick() {
            if (!this.eots.isWrappedAroundNeck() && this.eots.getBoundingBox().intersects(this.owner.getBoundingBox().inflate(0, 2, 0))) {
                this.eots.setEntityAroundNeck();
            }
        }
    }

    public static class FollowPlayerGoal extends Goal {
        private final GentleWind eots;

        public FollowPlayerGoal(GentleWind eots) {
            this.eots = eots;
            this.setFlags(EnumSet.of(Flag.TARGET));
        }

        @Override
        public boolean canUse() {
            LivingEntity livingentity = this.eots.getOwner();
            if (livingentity == null) {
                this.eots.discard();
                return false;
            }

            Optional<SlotResult> reference = EquipmentUtil.getFloatyScarf(livingentity);
            if(reference.isEmpty()) {
                this.eots.discard();
                return false;
            }

            CompoundTag scarf = reference.get().stack().getTag();
            if(scarf == null || scarf.getInt("UUID") != this.eots.getId()) {
                this.eots.discard();
                return false;
            }

            return !this.eots.isWrappedAroundNeck();
        }

        @Override
        public boolean canContinueToUse() {
            return false;
        }

        @Override
        public void start() {
            this.eots.followOwner();
        }
    }

    public static class GentleWindOwnerHurtByTargetGoal extends TargetGoal {
        private final GentleWind gentleWind;
        private LivingEntity ownerLastHurtBy;
        private int timestamp;

        public GentleWindOwnerHurtByTargetGoal(GentleWind gentleWind) {
            super(gentleWind, false);
            this.gentleWind = gentleWind;
            this.setFlags(EnumSet.of(Flag.TARGET));
        }

        @Override
        public boolean canUse() {
            LivingEntity livingentity = this.gentleWind.getOwner();
            if (livingentity == null) {
                return false;
            } else {
                this.ownerLastHurtBy = livingentity.getLastHurtByMob();
                int i = livingentity.getLastHurtByMobTimestamp();
                return i != this.timestamp
                        && this.canAttack(this.ownerLastHurtBy, TargetingConditions.DEFAULT)
                        && this.gentleWind.wantsToAttack(this.ownerLastHurtBy, livingentity);
            }
        }

        @Override
        public void start() {
            this.gentleWind.removeEntityAroundNeck();
            this.mob.setTarget(this.ownerLastHurtBy);
            LivingEntity livingentity = this.gentleWind.getOwner();
            if (livingentity != null) {
                this.timestamp = livingentity.getLastHurtByMobTimestamp();
            }

            super.start();
        }
    }

    public static class GentleWindOwnerHurtTargetGoal extends TargetGoal {
        private final GentleWind gentleWind;
        private LivingEntity ownerLastHurt;
        private int timestamp;

        public GentleWindOwnerHurtTargetGoal(GentleWind gentleWind) {
            super(gentleWind, false);
            this.gentleWind = gentleWind;
            this.setFlags(EnumSet.of(Flag.TARGET));
        }

        @Override
        public boolean canUse() {
            LivingEntity livingentity = this.gentleWind.getOwner();
            if (livingentity == null) {
                return false;
            } else {
                this.ownerLastHurt = livingentity.getLastHurtMob();
                int i = livingentity.getLastHurtMobTimestamp();
                return i != this.timestamp
                        && this.canAttack(this.ownerLastHurt, TargetingConditions.DEFAULT)
                        && this.gentleWind.wantsToAttack(this.ownerLastHurt, livingentity);
            }
        }

        @Override
        public void start() {
            this.gentleWind.removeEntityAroundNeck();
            this.mob.setTarget(this.ownerLastHurt);
            LivingEntity livingentity = this.gentleWind.getOwner();
            if (livingentity != null) {
                this.timestamp = livingentity.getLastHurtMobTimestamp();
            }

            super.start();
        }
    }

    protected static class GentleWindAirChargeGoal extends Goal {
        GentleWind gentleWind;
        private int attackTimer = 10; //Delay between attacks
        private int attackDelay = 10; //Gives the segment time to rotate against the player before attacking
        private int numberOfAttacks = 0;

        public GentleWindAirChargeGoal(GentleWind gentleWind) {
            this.gentleWind = gentleWind;
        }

        @Override
        public boolean canUse() {
            if(this.gentleWind.getTarget() == null || !this.gentleWind.getTarget().isAlive() || !this.gentleWind.hasLineOfSight(this.gentleWind.getTarget()))
                return false;
            else if (this.attackTimer > 0) {
                --this.attackTimer;
                return false;
            } else {
                this.attackTimer = 2 + this.gentleWind.getRandom().nextInt(10);
                return true;
            }
        }


        @Override
        public boolean canContinueToUse() {
            if(attackDelay < -2) {
                return false;
            }
            LivingEntity livingentity = this.gentleWind.getTarget();
            return livingentity != null && livingentity.isAlive();
        }

        @Override
        public void start() {
            this.gentleWind.removeEntityAroundNeck();
            this.attackDelay = 10;
            this.numberOfAttacks = this.gentleWind.random.nextInt(2);
            super.start();
        }


        @Override
        public void tick() {
            if(this.gentleWind.getTarget() != null) {
                this.lookAt(this.gentleWind.getTarget());
                if(attackDelay <= 0) {
                    new WindCrystal(this.gentleWind.level(), this.gentleWind, this.gentleWind.getLookAngle().multiply(0.7F,0.7F,0.7F).offsetRandom(this.gentleWind.random, 0.15F), true);
                    this.gentleWind.level().playSound(null, this.gentleWind.getX(), this.gentleWind.getY(), this.gentleWind.getZ(), DASounds.EOTS_SHOOT.get(), SoundSource.HOSTILE, 1.0F, 2.0F);
                    if(numberOfAttacks > 0) {
                        numberOfAttacks--;
                        attackDelay = 9;
                    }
                    else attackDelay = -3;
                }
                else attackDelay--;
            }
        }

        private void lookAt(LivingEntity target) {
            double d0 = target.getX() - this.gentleWind.getX();
            double d1 = target.getEyeY() - this.gentleWind.getEyeY();
            double d2 = target.getZ() - this.gentleWind.getZ();

            double d3 = Math.sqrt(d0 * d0 + d2 * d2);
            this.gentleWind.setXRot(Mth.wrapDegrees((float)(-(Mth.atan2(d1, d3) * 180.0F / (float)Math.PI))));
            this.gentleWind.setYRot(Mth.wrapDegrees((float)(Mth.atan2(d2, d0) * 180.0F / (float)Math.PI) - 90.0F));

            this.gentleWind.setYHeadRot(this.gentleWind.getYRot());
            this.gentleWind.xRotO = this.gentleWind.getXRot();
            this.gentleWind.yRotO = this.gentleWind.getYRot();
        }
    }

    public static class GentleWindMoveControl extends MoveControl {
        private float speed = 0.1F;

        private final GentleWind gentleWind;

        public GentleWindMoveControl(GentleWind gentleWind) {
            super(gentleWind);
            this.gentleWind = gentleWind;
        }

        @Override
        public void tick() {
            if (this.gentleWind.horizontalCollision) {
                this.gentleWind.setYRot(this.gentleWind.getYRot() + 180.0F);
                this.speed = 0.1F;
            }

            double d0 = this.getWantedX() - this.gentleWind.getX();
            double d1 = this.getWantedY() - this.gentleWind.getY();
            double d2 = this.getWantedZ() - this.gentleWind.getZ();
            double d3 = Math.sqrt(d0 * d0 + d2 * d2);

            if (Math.abs(d3) > 1.0E-5F) {
                double d4 = 1.0 - Math.abs(d1 * 0.7F) / d3;
                d0 *= d4;
                d2 *= d4;
                d3 = Math.sqrt(d0 * d0 + d2 * d2);
                double d5 = Math.sqrt(d0 * d0 + d2 * d2 + d1 * d1);
                float f = this.gentleWind.getYRot();
                float f1 = (float)Mth.atan2(d2, d0);
                float f2 = Mth.wrapDegrees(this.gentleWind.getYRot() + 90.0F);
                float f3 = Mth.wrapDegrees(f1 * (180.0F / (float)Math.PI));
                this.gentleWind.setYRot(Mth.approachDegrees(f2, f3, 4.0F) - 90.0F);
                this.gentleWind.yBodyRot = this.gentleWind.getYRot();
                if (Mth.degreesDifferenceAbs(f, this.gentleWind.getYRot()) < 3.0F) {
                    this.speed = Mth.approach(this.speed, 1.8F, 0.005F * (1.8F / this.speed));
                } else {
                    this.speed = Mth.approach(this.speed, 0.2F, 0.025F);
                }

                float f4 = (float)(-(Mth.atan2(-d1, d3) * 180.0F / (float)Math.PI));
                this.gentleWind.setXRot(f4);
                float f5 = this.gentleWind.getYRot() + 90.0F;
                double d6 = (double)(this.speed * Mth.cos(f5 * (float) (Math.PI / 180.0))) * Math.abs(d0 / d5);
                double d7 = (double)(this.speed * Mth.sin(f5 * (float) (Math.PI / 180.0))) * Math.abs(d2 / d5);
                double d8 = (double)(this.speed * Mth.sin(f4 * (float) (Math.PI / 180.0))) * Math.abs(d1 / d5);
                Vec3 vec3 = this.gentleWind.getDeltaMovement();
                this.gentleWind.setDeltaMovement(vec3.add(new Vec3(d6, d8, d7).subtract(vec3).scale(0.2)));
            }
        }
    }


    protected static class GentleWindLookControl extends LookControl {
        public GentleWindLookControl(GentleWind gentleWind) {
            super(gentleWind);
        }

        /**
         * Updates look
         */
        @Override
        public void tick() {
        }
    }
}
