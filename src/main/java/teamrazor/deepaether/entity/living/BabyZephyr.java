package teamrazor.deepaether.entity.living;

import com.aetherteam.aether.client.AetherSoundEvents;
import com.aetherteam.aether.entity.monster.Zephyr;
import com.aetherteam.aether.entity.projectile.ZephyrSnowball;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.EntitySelector;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.control.MoveControl;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;

import java.util.EnumSet;

public class BabyZephyr extends Zephyr {
    public BabyZephyr(EntityType<? extends Zephyr> type, Level level) {
        super(type, level);
        this.moveControl = new BabyZephyrMoveControl(this);
    }

    @Override
    protected void registerGoals() {
        this.goalSelector.addGoal(5, new BabyZephyr.RandomFloatAroundGoal(this));
        this.goalSelector.addGoal(7, new ZephyrLookGoal(this));
        this.goalSelector.addGoal(4, new BabyZephyr.BabyZephyrMeeleGoal(this));
        this.goalSelector.addGoal(7, new BabyZephyr.ZephyrShootSnowballGoal(this));
        this.targetSelector.addGoal(1, new NearestAttackableTargetGoal<>(this, Player.class, true, false));
    }

    protected void updateRandomPos() {
        RandomSource random = this.getRandom();
        double d0 = this.getX() + (random.nextFloat() * 2.0F - 1.0F) * 4.0F;
        double d1 = this.getY() + (random.nextFloat() * 2.0F - 1.0F) * 4.0F;
        double d2 = this.getZ() + (random.nextFloat() * 2.0F - 1.0F) * 4.0F;
        this.getMoveControl().setWantedPosition(d0, d1, d2, 1.5);
    }

    public static class BabyZephyrMeeleGoal extends Goal {
        int cooldown = 20;
        int time = 0;
        private final BabyZephyr zephyr;
        private boolean hasAttacked = false;

        public BabyZephyrMeeleGoal(BabyZephyr zephyr) {
            this.zephyr = zephyr;
        }


        @Override
        public boolean canUse() {
            if(zephyr.getTarget() == null || !this.zephyr.hasLineOfSight(zephyr.getTarget()))
                return false;
            else if(cooldown > 0) {
                cooldown--;
                return false;
            }
            else {
                cooldown = zephyr.random.nextInt(20) + 20;
                return true;
            }
        }

        @Override
        public boolean canContinueToUse() {
            if(hasAttacked) {
                zephyr.updateRandomPos();
                return false;
            }
            else if(time > 200)
                return false;
            LivingEntity livingentity = this.zephyr.getTarget();
            if (livingentity == null) {
                return false;
            } else if (!livingentity.isAlive()) {
                return false;
            }
            else if(this.zephyr.hurtTime > 0) {
                zephyr.updateRandomPos();
                return false;
            }
            else return this.zephyr.isWithinRestriction(livingentity.blockPosition());
        }

        public void start() {
            time = 0;
            hasAttacked = false;
            if(zephyr.getTarget() != null) {
                Vec3 target = this.zephyr.getTarget().position().add(0, 2.0F,0);
                this.zephyr.getMoveControl().setWantedPosition(target.x(), target.y(), target.z(), 2.0);
            }
            this.zephyr.setAggressive(true);
        }

        /**
         * Reset the task's internal state. Called when this task is interrupted by another one
         */
        @Override
        public void stop() {
            LivingEntity livingentity = this.zephyr.getTarget();
            if (!EntitySelector.NO_CREATIVE_OR_SPECTATOR.test(livingentity)) {
                this.zephyr.setTarget(null);
            }

            this.zephyr.setAggressive(false);
            this.zephyr.updateRandomPos();
        }

        @Override
        public void tick() {
            time++;
            LivingEntity living = zephyr.getTarget();
            if(living != null) {
                Vec3 target = this.zephyr.getTarget().position().add(0, 2.0F,0);
                this.zephyr.getMoveControl().setWantedPosition(target.x(), target.y(), target.z(), 1.5 * (1.0+ (time / 100.0)));
                if (this.zephyr.position().distanceToSqr(target) < 1.3F) {
                    living.hurt(this.zephyr.level().damageSources().mobAttack(this.zephyr), 4.0F);
                    this.hasAttacked = true;
                }
            }
        }
    }

    protected static class ZephyrShootSnowballGoal extends Goal {
        private final BabyZephyr zephyr;

        public ZephyrShootSnowballGoal(BabyZephyr zephyr) {
            this.zephyr = zephyr;
        }

        @Override
        public boolean canUse() {
            if(this.zephyr.isAggressive())
                return false;
            return zephyr.getTarget() != null;
        }

        @Override
        public void start() {
            this.zephyr.setChargeTime(0);
        }

        @Override
        public void stop() {
            this.zephyr.setChargeTime(0);
        }

        @Override
        public void tick() {
            LivingEntity livingEntity = this.zephyr.getTarget();
            if (livingEntity != null) {
                if (livingEntity.distanceToSqr(this.zephyr) < 1600.0 && this.zephyr.hasLineOfSight(livingEntity)) {
                    Level level = this.zephyr.level();
                    this.zephyr.setChargeTime(this.zephyr.getChargeTime() + 1);
                    if (this.zephyr.getChargeTime() == 2) {
                        if (this.zephyr.getAmbientSound() != null) {
                            this.zephyr.playSound(this.zephyr.getAmbientSound(), this.zephyr.getSoundVolume(), (level.getRandom().nextFloat() - level.getRandom().nextFloat()) * 0.2F + 1.0F);
                        }
                    } else if (this.zephyr.getChargeTime() == 12) {
                        Vec3 look = this.zephyr.getViewVector(1.0F);
                        double accelX = livingEntity.getX() - (this.zephyr.getX() + look.x() * 4.0);
                        double accelY = livingEntity.getY(0.5) - (0.5 + this.zephyr.getY(0.5));
                        double accelZ = livingEntity.getZ() - (this.zephyr.getZ() + look.z() * 4.0);
                        this.zephyr.playSound(AetherSoundEvents.ENTITY_ZEPHYR_SHOOT.get(), this.zephyr.getSoundVolume(), (level.getRandom().nextFloat() - level.getRandom().nextFloat()) * 0.2F + 1.0F);
                        ZephyrSnowball snowball = new ZephyrSnowball(level, this.zephyr, accelX * 2, accelY * 2, accelZ * 2);
                        snowball.setPos(this.zephyr.getX() + look.x() * 4.0, this.zephyr.getY(0.5) + 0.5, this.zephyr.getZ() + look.z() * 4.0);
                        level.addFreshEntity(snowball);
                        this.zephyr.setChargeTime(-1);
                    }
                } else if (this.zephyr.getChargeTime() > 0) {
                    this.zephyr.setChargeTime(this.zephyr.getChargeTime() - 1);
                }
            }
        }
    }

    protected static class RandomFloatAroundGoal extends Goal {
        private final BabyZephyr zephyr;

        public RandomFloatAroundGoal(BabyZephyr zephyr) {
            this.zephyr = zephyr;
            this.setFlags(EnumSet.of(Goal.Flag.MOVE));
        }

        @Override
        public boolean canUse() {
            MoveControl moveControl = this.zephyr.getMoveControl();
            if (!moveControl.hasWanted()) {
                return true;
            } else {
                double d0 = moveControl.getWantedX() - this.zephyr.getX();
                double d1 = moveControl.getWantedY() - this.zephyr.getY();
                double d2 = moveControl.getWantedZ() - this.zephyr.getZ();
                double d3 = d0 * d0 + d1 * d1 + d2 * d2;
                return d3 < 1.0 || d3 > 3600.0;
            }
        }

        @Override
        public boolean canContinueToUse() {
            return false;
        }

        @Override
        public void start() {
            this.zephyr.updateRandomPos();
        }
    }

    protected static class BabyZephyrMoveControl extends MoveControl {
        private final BabyZephyr zephyr;
        private int floatDuration;

        public BabyZephyrMoveControl(BabyZephyr zephyr) {
            super(zephyr);
            this.zephyr = zephyr;
        }

        @Override
        public void tick() {
            if (this.operation == MoveControl.Operation.MOVE_TO) {
                if (this.floatDuration-- <= 0) {
                    this.floatDuration += this.zephyr.getRandom().nextInt(5) + 2;
                    Vec3 vec3d = new Vec3(this.wantedX - this.zephyr.getX(), this.wantedY - this.zephyr.getY(), this.wantedZ - this.zephyr.getZ());
                    double d0 = vec3d.length();
                    vec3d = vec3d.normalize();
                    if (this.canReach(vec3d, Mth.ceil(d0))) {
                        this.zephyr.setDeltaMovement(this.zephyr.getDeltaMovement().add(vec3d.scale(0.1 * speedModifier)));
                    } else {
                        this.operation = MoveControl.Operation.WAIT;
                    }
                }
            }
        }

        private boolean canReach(Vec3 pos, int distance) {
            AABB axisalignedbb = this.zephyr.getBoundingBox();
            for (int i = 1; i < distance; ++i) {
                axisalignedbb = axisalignedbb.move(pos);
                if (!this.zephyr.level().noCollision(this.zephyr, axisalignedbb)) {
                    return false;
                }
            }
            return true;
        }
    }
}
