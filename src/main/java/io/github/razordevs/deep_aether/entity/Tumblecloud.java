package io.github.razordevs.deep_aether.entity;

import com.aetherteam.aether.client.AetherSoundEvents;
import io.github.razordevs.deep_aether.init.DAEntities;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MoverType;
import net.minecraft.world.level.Level;

public class Tumblecloud extends Entity {
    private static final int MAX_LIFE = 100;

    private final double xPower;
    private final double zPower;

    private int life;

    public Tumblecloud(EntityType<Tumblecloud> type, Level level) {
        super(type, level);
        this.life = 0;
        var vec3 = getDeltaMovement();
        double strength = 0.003; //+ (this.random.nextDouble() * 0.1);
        this.xPower = strength * (this.random.nextBoolean() ? 1 : -1);
        this.zPower = strength * (this.random.nextBoolean() ? 1 : -1);

        this.setDeltaMovement(this.xPower, vec3.y(), this.zPower);
    }

    public Tumblecloud(Level level, double x, double y, double z) {
        this(DAEntities.TUMBLECLOUD.get(), level);
        this.setPos(x, y, z);
    }

    @Override
    public boolean shouldRenderAtSqrDistance(double distance) {
        double d0 = this.getBoundingBox().getSize() * (double)4.0F;
        if (Double.isNaN(d0)) {
            d0 = 4.0F;
        }

        d0 *= 64.0F;
        return distance < d0 * d0;
    }

    @Override
    protected void readAdditionalSaveData(CompoundTag compoundTag) {

    }

    @Override
    protected void addAdditionalSaveData(CompoundTag compoundTag) {

    }

    @Override
    protected void defineSynchedData(SynchedEntityData.Builder builder) {

    }

    @Override
    public void tick() {
        super.tick();

        if(!this.level().isClientSide()) {
            ++this.life;
            if (this.life > MAX_LIFE) {
                this.playSound(AetherSoundEvents.ENTITY_WHIRLWIND_DROP.get(), 1.0F, 1.0F);
                this.discard();
                return;
            }
        }

        this.applyGravity();
        var vec3 = this.getDeltaMovement();
        this.move(MoverType.SELF, vec3);

        var currentVec = this.getDeltaMovement();

        currentVec = currentVec.add(this.xPower, 0, this.zPower);

        var d0 = currentVec.x();
        var d1 = currentVec.y();
        var d2 = currentVec.z();

        if (this.onGround()) {
            d1 = 0.25D; // Gives a bouncing effect
            this.level().addParticle(ParticleTypes.SMOKE, d1, d2, 0.0D, 0.0D, 0.0D, 0D);
        }

        if (this.horizontalCollision) {
            this.playSound(AetherSoundEvents.ENTITY_WHIRLWIND_DROP.get(), 1.0F, 1.0F);
            this.discard();
            return;
        }

        this.setDeltaMovement(d0, d1, d2);
    }

    //    @Override
//    public HumanoidArm getMainArm() {
//        return HumanoidArm.RIGHT;
//    }

    public boolean isNoGravity() {
        return false;
    }

    protected double getDefaultGravity() {
        return 0.03F;
    }

//    @Override
//    protected int calculateFallDamage(float p_21237_, float p_21238_) {
//        return 0;
//    }
//
//    @Override
//    public Iterable<ItemStack> getArmorSlots() {
//        return new ArrayList<>();
//    }
//
//    @Override
//    public ItemStack getItemBySlot(EquipmentSlot equipmentSlot) {
//        return ItemStack.EMPTY;
//    }
//
//    @Override
//    public void setItemSlot(EquipmentSlot equipmentSlot, ItemStack itemStack) {
//
//    }

    @Override
    public boolean isAttackable() {
        return false;
    }
}
