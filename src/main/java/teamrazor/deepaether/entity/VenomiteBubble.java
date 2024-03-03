package teamrazor.deepaether.entity;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.ClientGamePacketListener;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.projectile.ThrowableProjectile;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.HitResult;
import net.minecraftforge.network.NetworkHooks;
import teamrazor.deepaether.init.DAEntities;

public class VenomiteBubble extends ThrowableProjectile {
    private int ticksInAir = 0;

    public VenomiteBubble(EntityType<? extends VenomiteBubble> type, Level level) {
        super(type, level);
    }

    public VenomiteBubble(Level level) {
        super(DAEntities.VENOMITE_BUBBLE.get(), level);
    }
    @Override
    protected void defineSynchedData() {
    }

    public void tick() {
        super.tick();
        if (!this.onGround())
            ++this.ticksInAir;

        if (this.ticksInAir > 300 && !this.level().isClientSide())
            this.discard();
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
            this.explode();
            this.level().broadcastEntityEvent(this, (byte) 70);
        }
    }

    protected void onHitBlock(BlockHitResult result) {
        super.onHitBlock(result);
        if (!this.level().isClientSide) {
            this.explode();
        }
    }

    private void explode(){
        level().explode(this, this.getX(),this.getY(),this.getZ(),1, Level.ExplosionInteraction.NONE);
    }


    protected float getGravity() {
        return 0.07F;
    }
    public void handleEntityEvent(byte id) {
        super.handleEntityEvent(id);
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

