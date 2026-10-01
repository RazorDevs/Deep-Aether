package io.github.razordevs.deep_aether.entity.projectile;

import com.aetherteam.aether.AetherTags;
import com.aetherteam.aether.client.AetherSoundEvents;
import com.aetherteam.aether.data.resources.registries.AetherDamageTypes;
import com.aetherteam.aether.entity.projectile.crystal.AbstractCrystal;
import io.github.razordevs.deep_aether.DeepAetherConfig;
import io.github.razordevs.deep_aether.datagen.tags.DATags;
import io.github.razordevs.deep_aether.entity.living.GentleWind;
import io.github.razordevs.deep_aether.init.DAEntities;
import net.minecraft.core.Direction;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.damagesource.DamageTypes;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.HitResult;
import net.minecraft.world.phys.Vec3;
import net.neoforged.neoforge.event.EventHooks;

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
    private static final EntityDataAccessor<Boolean> IS_FRIENDLY = SynchedEntityData.defineId(WindCrystal.class, EntityDataSerializers.BOOLEAN);

    public WindCrystal(EntityType<WindCrystal> entityType, Level level) {
        super(entityType, level);
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

    public WindCrystal(Level level, Entity shooter, Vec3 direction, boolean friendly) {
            this(level, shooter, direction);
            this.setFriendly(friendly);
    }

    @Override
    protected void defineSynchedData(SynchedEntityData.Builder builder) {
        super.defineSynchedData(builder);
        builder.define(IS_FRIENDLY, false);
    }

    @Override
    protected void onHitEntity(EntityHitResult result) {
        Entity entity = result.getEntity();
        if (entity instanceof LivingEntity livingEntity) {

            if(this.getOwner() != null) {
                try {
                    Player player = ((GentleWind) this.getOwner()).getOwner();
                    if (player != null && !(((GentleWind) this.getOwner()).wantsToAttack(livingEntity, player))) {
                        return;
                    }
                }
                catch (ClassCastException ignore) {}

            }
            if (livingEntity.hurt(AetherDamageTypes.indirectEntityDamageSource(this.level(), DamageTypes.MOB_PROJECTILE, this, this.getOwner()), this.getDamage())) {
                this.level().playSound(null, this.getX(), this.getY(), this.getZ(), this.getImpactExplosionSoundEvent(), SoundSource.HOSTILE, 2.0F, this.random.nextFloat() - this.random.nextFloat() * 0.2F + 1.2F);
                this.discard();
            }
        }
    }

    private float getDamage() {
        if(this.isFriendly())
            return 6.0F;
        else {
            return (float) (10.0F + (10.0F * DeepAetherConfig.COMMON.eots_damage_multiplier.get() - 10.0F));
        }
    }

    @Override
    protected boolean canHitEntity(Entity pTarget) {
        if(this.getOwner() != null && pTarget.is(this.getOwner()))
            return false;
        if(pTarget instanceof LivingEntity livingEntity) {
            if(this.isFriendly()) {
                if (livingEntity.getType().is(DATags.Entities.FRIENDLY_WIND_CHARGE_BLACKLIST))
                    return false;
            } else if(livingEntity.getType().is(DATags.Entities.WIND_CHARGE_BLACKLIST))
                return false;
        }
        return super.canHitEntity(pTarget);
    }

    @Override
    protected void onHitBlock(BlockHitResult result) {
        this.markHurt();
        if(!this.isFriendly()) {
            if (result.getDirection() == Direction.UP) {
                float offset = (float) this.random.nextInt(200) / 1000;
                new WindCrystal(level(), this, baseSpeed + offset, 0, baseSpeed - offset);
                new WindCrystal(level(), this, -baseSpeed + offset, 0, baseSpeed + offset);
                new WindCrystal(level(), this, baseSpeed - offset, 0, -baseSpeed - offset);
                new WindCrystal(level(), this, -baseSpeed - offset, 0, -baseSpeed + offset);
            }
            this.level().playSound(this, result.getBlockPos(), AetherSoundEvents.ENTITY_ICE_CRYSTAL_EXPLODE.get(), SoundSource.HOSTILE, 1.0f, 1.0f);

            if (this.isBreakable(this.level().getBlockState(result.getBlockPos()))) {
                if (EventHooks.canEntityGrief(this.level(), this)) {
                    this.level().destroyBlock(result.getBlockPos(), true);
                }
            }
        }

        if (!this.level().isClientSide) {
            this.discard();
        }
    }

    @Override
    protected void onHit(HitResult pResult) {
        super.onHit(pResult);
        if (this.level().isClientSide) {
            this.level().addParticle(ParticleTypes.SNOWFLAKE, this.getX(), this.getY() + (random.nextFloat() - 0.5), this.getZ(), 0.0D, 0.0D, 0.0D);
            this.level().addParticle(ParticleTypes.SNOWFLAKE, this.getX(), this.getY() + (random.nextFloat() - 0.5), this.getZ(), 0.0D, 0.0D, 0.0D);
        }
    }

    private boolean isBreakable(BlockState blockState) {
        return !blockState.is(AetherTags.Blocks.VALKYRIE_QUEEN_UNBREAKABLE) && blockState.getBlock().defaultDestroyTime() >= 0.0F && blockState.getBlock().defaultDestroyTime() < 100.0F;
    }

    @Override
    @Nonnull
    protected SoundEvent getImpactExplosionSoundEvent() {
        return AetherSoundEvents.ENTITY_ICE_CRYSTAL_EXPLODE.get();
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
        tag.putBoolean("isFriendly", this.isFriendly());
    }

    @Override
    public void readAdditionalSaveData(@Nonnull CompoundTag tag) {
        super.readAdditionalSaveData(tag);
        this.xPower = tag.getDouble("XSpeed");
        this.yPower = tag.getDouble("YSpeed");
        this.zPower = tag.getDouble("ZSpeed");
        this.setFriendly(tag.getBoolean("isFriendly"));
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

    public void setFriendly(boolean friendly) {
        this.entityData.set(IS_FRIENDLY, friendly);
    }

    public boolean isFriendly() {
        return this.entityData.get(IS_FRIENDLY);
    }
}