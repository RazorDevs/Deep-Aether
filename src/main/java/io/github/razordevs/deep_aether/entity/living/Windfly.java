package io.github.razordevs.deep_aether.entity.living;

import com.aetherteam.aether.entity.passive.Aerwhale;
import com.aetherteam.aether.entity.passive.AetherAnimal;
import com.aetherteam.aether.item.AetherItems;
import io.github.razordevs.deep_aether.init.DAEntities;
import io.github.razordevs.deep_aether.init.DASounds;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.util.Mth;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.AgeableMob;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.FlyingMob;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.control.MoveControl;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.entity.ai.navigation.FlyingPathNavigation;
import net.minecraft.world.entity.ai.navigation.PathNavigation;
import net.minecraft.world.entity.animal.FlyingAnimal;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.pathfinder.PathType;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;
import org.jetbrains.annotations.Nullable;

public class Windfly extends AetherAnimal implements FlyingAnimal {
    private static final EntityDataAccessor<Float> DATA_X_ROT_O_ID = SynchedEntityData.defineId(Windfly.class, EntityDataSerializers.FLOAT);
    private static final EntityDataAccessor<Float> DATA_X_ROT_ID = SynchedEntityData.defineId(Windfly.class, EntityDataSerializers.FLOAT);
    private static final EntityDataAccessor<Float> DATA_Y_ROT_ID = SynchedEntityData.defineId(Windfly.class, EntityDataSerializers.FLOAT);

    public Windfly(EntityType<Windfly> type, Level world) {
        super(type, world);
        this.lookControl = new Aerwhale.BlankLookControl(this);
        this.moveControl = new WindflyMoveControl(this);
        this.setPathfindingMalus(PathType.WATER, 0.0F);
    }

    protected void registerGoals() {
        this.goalSelector.addGoal(0, new Aerwhale.SetTravelCourseGoal(this));
    }

    public static AttributeSupplier.Builder createAttributes() {
        return FlyingMob.createMobAttributes()
                .add(Attributes.MAX_HEALTH, 6.0D)
                .add(Attributes.FLYING_SPEED, 0.15F)
                .add(Attributes.MOVEMENT_SPEED, 0.3F)
                .add(Attributes.ATTACK_DAMAGE, 2.0D);
    }

    @Override
    protected void defineSynchedData(SynchedEntityData.Builder builder) {
        super.defineSynchedData(builder);
        builder.define(DATA_X_ROT_O_ID, this.getXRot());
        builder.define(DATA_X_ROT_ID, this.getXRot());
        builder.define(DATA_Y_ROT_ID, this.getYRot());
    }

    public void aiStep() {
        super.aiStep();
        this.setXRot(this.getXRotData());
        this.setYRot(this.getYRotData());
        this.setYBodyRot(this.getYRotData());
        this.setYHeadRot(this.getYRotData());
    }

    @Override
    public boolean isFood(ItemStack stack) {
        return false;
    }

    public void tick() {
        this.setXRotOData(this.getXRotData());

        if(random.nextInt(90) == 0) {
            ItemEntity shard = new ItemEntity(this.level(), 0, 0, 0, new ItemStack(AetherItems.AMBROSIUM_SHARD.get()));
            shard.moveTo(this.position());
            this.level().addFreshEntity(shard);
        }

        if(random.nextInt(7) == 0)
            this.level().addParticle(ParticleTypes.END_ROD, this.getX() + random.nextFloat(), this.getY() + 0.3, this.getZ() - 0.5, 0.0, 0.0, 0.0);
        if(random.nextInt(7) == 0)
            this.level().addParticle(ParticleTypes.END_ROD, this.getX() - random.nextFloat(), this.getY() + 0.3, this.getZ() - 0.5, 0.0, 0.0, 0.0);

        super.tick();
    }

    public float getXRotOData() {
        return this.getEntityData().get(DATA_X_ROT_O_ID);
    }

    public void setXRotOData(float rot) {
        this.getEntityData().set(DATA_X_ROT_O_ID, Mth.wrapDegrees(rot));
    }

    public float getXRotData() {
        return this.getEntityData().get(DATA_X_ROT_ID);
    }

    public void setXRotData(float rot) {
        this.getEntityData().set(DATA_X_ROT_ID, Mth.wrapDegrees(rot));
    }

    public float getYRotData() {
        return this.getEntityData().get(DATA_Y_ROT_ID);
    }

    public void setYRotData(float rot) {
        this.getEntityData().set(DATA_Y_ROT_ID, Mth.wrapDegrees(rot));
    }

    protected PathNavigation createNavigation(Level level) {
        FlyingPathNavigation flyingpathnavigation = new FlyingPathNavigation(this, level);
        flyingpathnavigation.setCanOpenDoors(false);
        flyingpathnavigation.setCanFloat(true);
        flyingpathnavigation.setCanPassDoors(false);
        return flyingpathnavigation;
    }

    @Nullable
    @Override
    public AgeableMob getBreedOffspring(ServerLevel serverLevel, AgeableMob ageableMob) {
        return DAEntities.WINDFLY.get().create(serverLevel);
    }

    @Override
    public boolean causeFallDamage(float v, float v1, DamageSource damageSource) {
        return false;
    }

    public boolean isFlying() {
        return !this.onGround();
    }

    @Override
    protected SoundEvent getAmbientSound() {
        return DASounds.WINDFLY_AMBIENT.get();
    }
    
    @Override
    protected SoundEvent getHurtSound(DamageSource pDamageSource) {
        return DASounds.WINDFLY_HURT.get();
    }

    public static class WindflyMoveControl extends MoveControl {
        protected final Windfly mob;

        public WindflyMoveControl(Windfly pMob) {
            super(pMob);
            this.mob = pMob;
        }

        public void tick() {
            if (!this.mob.isVehicle()) {
                double x = this.getWantedX() - this.mob.getX();
                double y = this.getWantedY() - this.mob.getY();
                double z = this.getWantedZ() - this.mob.getZ();
                double distance = Math.sqrt(x * x + z * z);
                if (distance < 3.0 || this.isColliding((new Vec3(x, y, z)).normalize())) {
                    this.operation = Operation.WAIT;
                }

                float xRotTarget = (float)(Mth.atan2(y, distance) * 57.2957763671875);
                float xRot = Mth.wrapDegrees(this.mob.getXRot());
                xRot = Mth.approachDegrees(xRot, xRotTarget, 0.2F);
                this.mob.setXRot(xRot);
                this.mob.setXRotData(this.mob.getXRot());
                float yRotTarget = Mth.wrapDegrees((float)Mth.atan2(z, x) * 57.295776F);
                float yRot = Mth.wrapDegrees(this.mob.getYRot() + 90.0F);
                yRot = Mth.approachDegrees(yRot, yRotTarget, 0.5F);
                this.mob.setYRot(yRot - 90.0F);
                this.mob.setYRotData(this.mob.getYRot());
                this.mob.setYBodyRot(yRot);
                this.mob.setYHeadRot(yRot);
                x = this.mob.getAttributeValue(Attributes.FLYING_SPEED) * (double)Mth.cos(yRot * 0.017453292F);
                y = this.mob.getAttributeValue(Attributes.FLYING_SPEED) * (double)Mth.sin(xRot * 0.017453292F);
                z = this.mob.getAttributeValue(Attributes.FLYING_SPEED) * (double)Mth.sin(yRot * 0.017453292F);
                Vec3 motion = new Vec3(x, y, z);
                this.mob.setDeltaMovement(motion);
                Entity entity = this.mob.getLeashHolder();
                if (entity != null && entity.level() == this.mob.level()) {
                    this.mob.restrictTo(entity.blockPosition(), 5);
                    float f = this.mob.distanceTo(entity);
                    if (f > 10.0F) {
                        this.mob.dropLeash(true, true);
                        this.mob.goalSelector.disableControlFlag(Goal.Flag.MOVE);
                    } else if (f > 6.0F) {
                        double d0 = (entity.getX() - this.mob.getX()) / (double)f;
                        double d1 = (entity.getY() - this.mob.getY()) / (double)f;
                        double d2 = (entity.getZ() - this.mob.getZ()) / (double)f;
                        this.mob.setDeltaMovement(this.mob.getDeltaMovement().add(Math.copySign(d0 * d0 * 0.4, d0), Math.copySign(d1 * d1 * 0.4, d1), Math.copySign(d2 * d2 * 0.4, d2)));
                        this.mob.checkSlowFallDistance();
                    }
                }
            }
        }

        private boolean isColliding(Vec3 pos) {
            AABB axisalignedbb = this.mob.getBoundingBox();

            for(int i = 1; i < 7; ++i) {
                axisalignedbb = axisalignedbb.move(pos);
                if (!this.mob.level().noCollision(this.mob, axisalignedbb)) {
                    return true;
                }
            }

            return false;
        }
    }
}
