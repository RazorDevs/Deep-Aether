package teamrazor.deepaether.entity;

import com.aetherteam.aether.client.AetherSoundEvents;
import com.aetherteam.aether.data.resources.AetherDamageTypes;
import com.aetherteam.aether.entity.projectile.crystal.AbstractCrystal;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.EntityHitResult;
import teamrazor.deepaether.datagen.tags.DATags;
import teamrazor.deepaether.entity.boss.EOTSEntity;
import teamrazor.deepaether.init.DAEntities;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.UUID;

public class WindCharge extends AbstractCrystal {

    public enum AttackPatterns {
        FOUR,
        RAPID_RANDOM,
        RAPID_TARGET,
        EXPLOSION
    }

    @Nullable
    private UUID ownerUUID;
    @Nullable
    private EOTSEntity cachedOwner;
    public double xPower = 0;
    public double yPower;
    public double zPower = 0;
    public AttackPatterns pattern;



    public WindCharge(EntityType<WindCharge> entityType, Level level) {
        super(entityType, level);
    }
    public WindCharge(Level level, Entity shooter, AttackPatterns pattern) {
        this(DAEntities.WIND_CHARGE.get(), level);
        this.pattern = pattern;
        this.setOwner(shooter);
        this.setPos(shooter.getX(), shooter.getY(), shooter.getZ());
        this.yPower = -0.15;
        this.setDeltaMovement(this.xPower, this.yPower, this.zPower);
    }


    protected void onHitEntity(EntityHitResult result) {
        Entity entity = result.getEntity();
        if (entity instanceof LivingEntity livingEntity) {
            if(!livingEntity.getType().is(DATags.Entities.WIND_CHARGE_BLACKLIST)) {
                if (livingEntity.hurt(AetherDamageTypes.indirectEntityDamageSource(this.level, AetherDamageTypes.FIRE_CRYSTAL, this, this.getOwner()), 20.0F)) {
                    this.level.playSound(null, this.getX(), this.getY(), this.getZ(), this.getImpactExplosionSoundEvent(), SoundSource.HOSTILE, 2.0F, this.random.nextFloat() - this.random.nextFloat() * 0.2F + 1.2F);
                    this.discard();
                }
            }
        }
    }

    protected void onHitBlock(BlockHitResult result) {
        this.markHurt();
        switch (result.getDirection()) {
            case UP:
            case DOWN:
                if(this.pattern == AttackPatterns.FOUR) {
                    Entity c1 = new WindCharge(level, this, null);
                    Entity c2 = new WindCharge(level, this, null);
                    Entity c3 = new WindCharge(level, this, null);
                    Entity c4 = new WindCharge(level, this, null);

                    float offset = (float) random.nextInt(200) / 1000;
                    System.out.println(offset);
                    c1.setDeltaMovement(0.2 + offset, 0, 0.2 - offset);
                    c2.setDeltaMovement(-0.2 + offset, 0, 0.2 + offset);
                    c3.setDeltaMovement(0.2 - offset, 0, -0.2 - offset);
                    c4.setDeltaMovement(-0.2 - offset, 0, -0.2 + offset);

                    level.addFreshEntity(c1);
                    level.addFreshEntity(c2);
                    level.addFreshEntity(c3);
                    level.addFreshEntity(c4);
                }

                else if(this.pattern == AttackPatterns.RAPID_RANDOM) {
                    Entity c1 = new WindCharge(level, this, null);
                    float offset = (float) random.nextInt(-2, 3) /10;
                    float offset2 = 4.0F - Math.abs(offset);
                    if(random.nextBoolean())
                        offset2 = offset *-1;
                    c1.setDeltaMovement(offset, 0, offset2);

                    level.addFreshEntity(c1);
                }
                break;
        }
        this.level.explode(this, this.getX(), this.getY(), this.getZ(), 0, false, Level.ExplosionInteraction.MOB);

        if (!this.level.isClientSide) {
            this.discard();
        }
    }


    protected SoundEvent getImpactExplosionSoundEvent() {
        return AetherSoundEvents.ENTITY_FIRE_CRYSTAL_EXPLODE.get();
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



    public void setOwner(@Nullable EOTSEntity p_37263_) {
        if (p_37263_ != null) {
            this.ownerUUID = p_37263_.getUUID();
            this.cachedOwner = p_37263_;
        }

    }

    @Override
    @Nullable
    public EOTSEntity getOwner() {
        if (this.cachedOwner != null && !this.cachedOwner.isRemoved()) {
            return this.cachedOwner;
        } else if (this.ownerUUID != null && this.level instanceof ServerLevel) {
            this.cachedOwner = (EOTSEntity) ((ServerLevel)this.level).getEntity(this.ownerUUID);
            return this.cachedOwner;
        } else {
            return null;
        }
    }
}
