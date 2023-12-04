package teamrazor.deepaether.item.misc;

import com.aetherteam.aether.network.AetherPacketHandler;
import com.aetherteam.aether.network.packet.serverbound.HammerProjectileLaunchPacket;
import com.aetherteam.nitrogen.network.PacketRelay;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.ClientGamePacketListener;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.ThrowableProjectile;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.HitResult;
import net.minecraftforge.network.NetworkHooks;
import teamrazor.deepaether.init.DAEntities;

import java.util.Iterator;
import java.util.List;

public class FireProjectile extends ThrowableProjectile {
    private int ticksInAir = 0;

    public FireProjectile(EntityType<? extends FireProjectile> type, Level level) {
        super(type, level);
    }

    public FireProjectile(LivingEntity owner, Level level) {
        super((EntityType) DAEntities.FIRE_PROJECTILE.get(), owner, level);
    }

    public FireProjectile(Level level) {
        super((EntityType) DAEntities.FIRE_PROJECTILE.get(), level);
    }

    @Override
    protected void defineSynchedData() {
    }

    public void tick() {
        super.tick();
        if (!this.onGround()) {
            ++this.ticksInAir;
        }

        if (this.ticksInAir > 100 && !this.level().isClientSide()) {
            this.discard();
        }

        if (this.level().isClientSide()) {
            this.level().addParticle(ParticleTypes.FLAME, this.getX(), this.getY() + 0.2, this.getZ(), 0.0, 0.0, 0.0);
        }

    }

    public void shoot(float rotationPitch, float rotationYaw, float velocity, float inaccuracy) {
        float x = -Mth.sin(rotationYaw * 0.017453292F) * Mth.cos(rotationPitch * 0.017453292F);
        float y = -Mth.sin(rotationPitch * 0.017453292F);
        float z = Mth.cos(rotationYaw * 0.017453292F) * Mth.cos(rotationPitch * 0.017453292F);
        super.shoot((double) x, (double) y, (double) z, velocity, inaccuracy);
    }

    protected void onHit(HitResult result) {
        super.onHit(result);
        if (!this.level().isClientSide()) {
            this.level().broadcastEntityEvent(this, (byte) 3);
            this.discard();
        }

    }

    protected void onHitEntity(EntityHitResult result) {
        Entity target = result.getEntity();
        if (!this.level().isClientSide()) {
            this.launchTarget(target);
            this.level().broadcastEntityEvent(this, (byte) 70);
        } else {
            PacketRelay.sendToServer(AetherPacketHandler.INSTANCE, new HammerProjectileLaunchPacket(target.getId(), this.getId()));
            this.spawnParticles();
        }

    }

    protected void onHitBlock(BlockHitResult result) {
        super.onHitBlock(result);
        List<Entity> list = this.level().getEntities(this, this.getBoundingBox().inflate(5.0));
        Iterator var3 = list.iterator();

        while (var3.hasNext()) {
            Entity target = (Entity) var3.next();
            if (!this.level().isClientSide()) {
                this.launchTarget(target);
            } else {
                PacketRelay.sendToServer(AetherPacketHandler.INSTANCE, new HammerProjectileLaunchPacket(target.getId(), this.getId()));
            }
        }

        if (!this.level().isClientSide()) {
            this.level().broadcastEntityEvent(this, (byte) 70);
        } else {
            this.spawnParticles();
        }

    }

    private void spawnParticles() {
        for (int j = 0; j < 8; ++j) {
            this.level().addParticle(ParticleTypes.FLAME, this.getX(), this.getY(), this.getZ(), 0.0, 0.0, 0.0);
            this.level().addParticle(ParticleTypes.FLAME, this.getX(), this.getY(), this.getZ(), 0.0, 0.0, 0.0);
            this.level().addParticle(ParticleTypes.SMOKE, this.getX(), this.getY(), this.getZ(), 0.0, 0.0, 0.0);
            this.level().addParticle(ParticleTypes.FLAME, this.getX(), this.getY(), this.getZ(), 0.0, 0.0, 0.0);
        }

    }

    public void launchTarget(Entity target) {
        if (target != this.getOwner() && (this.getOwner() == null || target != this.getOwner().getVehicle()) && target instanceof LivingEntity livingEntity) {
            livingEntity.hurt(this.damageSources().thrown(this, this.getOwner()), 7.0F);
            livingEntity.push(this.getDeltaMovement().x(), 0.6, this.getDeltaMovement().z());
        }

    }

    protected float getGravity() {
        return 0.0F;
    }

    public void handleEntityEvent(byte id) {
        if (id == 70) {
            this.spawnParticles();
        } else {
            super.handleEntityEvent(id);
        }

    }

    public void addAdditionalSaveData(CompoundTag tag) {
        super.addAdditionalSaveData(tag);
        tag.putInt("TicksInAir", this.ticksInAir);
    }

    public void readAdditionalSaveData(CompoundTag tag) {
        super.readAdditionalSaveData(tag);
        if (tag.contains("TicksInAir")) {
            this.ticksInAir = tag.getInt("TicksInAir");
        }

    }

    public Packet<ClientGamePacketListener> getAddEntityPacket() {
        return NetworkHooks.getEntitySpawningPacket(this);
    }
}

