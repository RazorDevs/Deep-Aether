package teamrazor.deepaether.entity.living.projectile;

import com.aetherteam.aether.client.AetherSoundEvents;
import com.aetherteam.aether.data.resources.registries.AetherDamageTypes;
import com.aetherteam.aether.entity.projectile.crystal.AbstractCrystal;
import net.minecraft.core.Direction;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.damagesource.DamageTypes;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.Vec3;
import teamrazor.deepaether.datagen.tags.DATags;
import teamrazor.deepaether.init.DAEntities;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.UUID;

public class WindCrystal extends AbstractCrystal {

    @Nullable
    private UUID ownerUUID;
    @Nullable
    private Entity cachedOwner;
    public double xPower = 0;
    public double yPower;
    public double zPower = 0;
    private static final double baseSpeed = 0.3;



    public WindCrystal(EntityType<WindCrystal> entityType, Level level) {
        super(entityType, level);
    }
    private WindCrystal(Level level, Entity shooter) {
        this(DAEntities.WIND_CRYSTAL.get(), level);
        this.setOwner(shooter);
        this.setPos(shooter.getX(), shooter.getY(), shooter.getZ());
        this.yPower = -0.15;
        this.setDeltaMovement(this.xPower, this.yPower, this.zPower);
        level().addFreshEntity(this);
    }

    public WindCrystal(Level level, Entity shooter, Vec3 direction) {
        this(DAEntities.WIND_CRYSTAL.get(), level);
        this.setOwner(shooter);
        this.setPos(shooter.getX(), shooter.getY(), shooter.getZ());
        this.yPower = -0.15;
        this.setDeltaMovement(direction);
        level().addFreshEntity(this);
    }

    public WindCrystal(Level level, Entity shooter, double x, double y, double z) {
        this(level, shooter, new Vec3(x,y,z));
    }


    @Override
    protected void onHitEntity(EntityHitResult result) {
        Entity entity = result.getEntity();
        if (entity instanceof LivingEntity livingEntity) {
            if(!entity.getType().is(DATags.Entities.WIND_CHARGE_BLACKLIST)) {
                if (livingEntity.hurt(AetherDamageTypes.indirectEntityDamageSource(this.level(), DamageTypes.MOB_PROJECTILE, this, this.getOwner()), 20.0F)) {
                    this.level().playSound(null, this.getX(), this.getY(), this.getZ(), this.getImpactExplosionSoundEvent(), SoundSource.HOSTILE, 2.0F, this.random.nextFloat() - this.random.nextFloat() * 0.2F + 1.2F);
                    this.discard();
                }
            }
        }
    }

    @Override
    protected boolean canHitEntity(Entity pTarget) {
        if(pTarget instanceof LivingEntity livingEntity) {
            if(livingEntity.getType().is(DATags.Entities.WIND_CHARGE_BLACKLIST))
                return false;
        }
        return super.canHitEntity(pTarget);
    }


    @Override
    protected void onHitBlock(BlockHitResult result) {
        this.markHurt();
        if(result.getDirection() == Direction.UP) {
            float offset = (float) this.random.nextInt(200) / 1000;
            new WindCrystal(level(), this, baseSpeed + offset, 0, baseSpeed - offset);
            new WindCrystal(level(), this, -baseSpeed + offset, 0, baseSpeed + offset);
            new WindCrystal(level(), this, baseSpeed - offset, 0, -baseSpeed - offset);
            new WindCrystal(level(), this, -baseSpeed - offset, 0, -baseSpeed + offset);
        }
        this.level().explode(this, this.getX(), this.getY(), this.getZ(), 0, false, Level.ExplosionInteraction.MOB);

        if (!this.level().isClientSide) {
            this.discard();
        }
    }

    @Override
    @Nonnull
    protected SoundEvent getImpactExplosionSoundEvent() {
        return AetherSoundEvents.ENTITY_FIRE_CRYSTAL_EXPLODE.get();
    }

    @Override
    public boolean isPickable() {
        return true;
    }

    @Override
    protected ParticleOptions getExplosionParticle() {
        return ParticleTypes.CLOUD;
    }

    @Override
    public void addAdditionalSaveData(@Nonnull CompoundTag tag) {
        super.addAdditionalSaveData(tag);
        tag.putDouble("XSpeed", this.xPower);
        tag.putDouble("YSpeed", this.yPower);
        tag.putDouble("ZSpeed", this.zPower);
    }

    @Override
    public void readAdditionalSaveData(@Nonnull CompoundTag tag) {
        super.readAdditionalSaveData(tag);
        this.xPower = tag.getDouble("XSpeed");
        this.yPower = tag.getDouble("YSpeed");
        this.zPower = tag.getDouble("ZSpeed");
    }


    @Override
    public void setOwner(@Nullable Entity owner) {
        if (owner != null) {
            this.ownerUUID = owner.getUUID();
            this.cachedOwner = owner;
        }
    }

    @Override
    @Nullable
    public Entity getOwner() {
        if (this.cachedOwner != null && !this.cachedOwner.isRemoved()) {
            return this.cachedOwner;
        } else if (this.ownerUUID != null && this.level() instanceof ServerLevel) {
            this.cachedOwner = ((ServerLevel)this.level()).getEntity(this.ownerUUID);
            return this.cachedOwner;
        } else {
            return null;
        }
    }
}