package teamrazor.deepaether.entity;

import com.aetherteam.aether.client.AetherSoundEvents;
import com.aetherteam.aether.data.resources.AetherDamageTypes;
import com.aetherteam.aether.entity.projectile.crystal.AbstractCrystal;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.EntityHitResult;
import teamrazor.deepaether.init.DAEntities;

import javax.annotation.Nonnull;

public class WindCharge extends AbstractCrystal {
    public double xPower = 0;
    public double yPower;
    public double zPower = 0;

    public WindCharge(EntityType<WindCharge> entityType, Level level) {
        super(entityType, level);
    }
    public WindCharge(Level level, Entity shooter) {
        this(DAEntities.WIND_CHARGE.get(), level);
        this.setOwner(shooter);
        this.setPos(shooter.getX(), shooter.getY(), shooter.getZ());
        this.yPower = -0.15;
        this.setDeltaMovement(this.xPower, this.yPower, this.zPower);
    }

    protected void onHitEntity(EntityHitResult result) {
        Entity entity = result.getEntity();
        if (entity instanceof LivingEntity livingEntity) {
            if (livingEntity.hurt(AetherDamageTypes.indirectEntityDamageSource(this.level, AetherDamageTypes.FIRE_CRYSTAL, this, this.getOwner()), 20.0F)) {
                this.level.playSound((Player)null, this.getX(), this.getY(), this.getZ(), this.getImpactExplosionSoundEvent(), SoundSource.HOSTILE, 2.0F, this.random.nextFloat() - this.random.nextFloat() * 0.2F + 1.2F);
                this.discard();
            }
        }
    }

    protected void onHitBlock(BlockHitResult result) {
        this.markHurt();
        switch (result.getDirection()) {
            case UP:
            case DOWN:
                Entity c1 = new WindCharge(level, this);
                Entity c2 = new WindCharge(level, this);
                Entity c3 = new WindCharge(level, this);
                Entity c4 = new WindCharge(level, this);

                c1.setDeltaMovement(0.2, 0, 0.2);
                c2.setDeltaMovement(-0.2, 0, 0.2);
                c3.setDeltaMovement(0.2, 0, -0.2);
                c4.setDeltaMovement(-0.2, 0, -0.2);

                level.addFreshEntity(c1);
                level.addFreshEntity(c2);
                level.addFreshEntity(c3);
                level.addFreshEntity(c4);
                break;
        }

        this.discard();
    }

    protected SoundEvent getImpactExplosionSoundEvent() {
        return (SoundEvent) AetherSoundEvents.ENTITY_FIRE_CRYSTAL_EXPLODE.get();
    }

    public boolean isPickable() {
        return true;
    }

    protected ParticleOptions getExplosionParticle() {
        return ParticleTypes.CLOUD;
    }

    public void addAdditionalSaveData(@Nonnull CompoundTag tag) {
        super.addAdditionalSaveData(tag);
        tag.putDouble("XSpeed", this.xPower);
        tag.putDouble("YSpeed", this.yPower);
        tag.putDouble("ZSpeed", this.zPower);
    }

    public void readAdditionalSaveData(@Nonnull CompoundTag tag) {
        super.readAdditionalSaveData(tag);
        this.xPower = tag.getDouble("XSpeed");
        this.yPower = tag.getDouble("YSpeed");
        this.zPower = tag.getDouble("ZSpeed");
    }
}
