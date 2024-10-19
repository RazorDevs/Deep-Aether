package io.github.razordevs.deepaether.deepaether.mixin.entity;

import com.aetherteam.aether.AetherConfig;
import com.aetherteam.aether.attachment.AetherDataAttachments;
import com.aetherteam.aether.entity.EntityUtil;
import com.aetherteam.aether.entity.passive.Aerwhale;
import com.aetherteam.aether.item.AetherItems;
import io.github.razordevs.deepaether.deepaether.entity.AerwhaleSaddleable;
import net.minecraft.core.NonNullList;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.Mth;
import net.minecraft.world.Containers;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.monster.piglin.PiglinAi;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.vehicle.ContainerEntity;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.ChestMenu;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.phys.Vec3;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import io.github.razordevs.deepaether.deepaether.init.DAItems;

import java.util.List;

@Mixin(value = Aerwhale.class)
public abstract class AerwhaleMixin extends FlyingMob implements AerwhaleSaddleable, ContainerEntity, HasCustomInventoryScreen {
    @Shadow(remap = false) public abstract void setYRotData(float rot);

    @Unique
    private static final EntityDataAccessor<Boolean> DATA_STILL_ID = SynchedEntityData.defineId(Aerwhale.class, EntityDataSerializers.BOOLEAN);
    protected AerwhaleMixin(EntityType<? extends FlyingMob> p_20806_, Level p_20807_) {
        super(p_20806_, p_20807_);
    }
    @Unique
    private static final EntityDataAccessor<Boolean> DATA_SADDLE_ID = SynchedEntityData.defineId(Aerwhale.class, EntityDataSerializers.BOOLEAN);

    @Inject(at = @At("TAIL"), method = "defineSynchedData")
    protected void defineSynchedData(CallbackInfo ci) {
        this.getEntityData().define(DATA_SADDLE_ID, false);
        this.getEntityData().define(DATA_STILL_ID, false);
    }

    @Unique
    public boolean deep_Aether$isStill() {
        return this.getEntityData().get(DATA_STILL_ID);
    }

    @Unique
    public void deep_Aether$setStill(boolean isStill) {
        this.getEntityData().set(DATA_STILL_ID, isStill);
    }


    /**
     * @author TunefulTurnip
     * @reason needed to overwrite the function to correct the rotation. Might come up with a better solution later
     */
    @Overwrite
    public void travel(@NotNull Vec3 vector) {
        if(deep_Aether$isStill()) {
            this.setDeltaMovement(new Vec3(0.0, 0.0, 0.0));
        }
        else if (this.isEffectiveAi() || this.isControlledByLocalInstance()) {
            List<Entity> passengers = this.getPassengers();
            if (!passengers.isEmpty()) {
                Entity entity = passengers.get(0);
                if (entity instanceof Player player) {
                    this.setYRot(player.getYRot() + 90.0F);
                    this.yRotO = player.getYHeadRot();
                    this.setXRot(-player.getXRot());
                    this.xRotO = player.getXRot() * 0.5F;
                    this.setYHeadRot(player.getYHeadRot());
                    this.yBodyRotO = player.getYHeadRot();
                    this.setYRotData(this.getYRot() - 90.0F);

                    float yRot = Mth.wrapDegrees(this.getYRot() + 90.0F);
                    yRot = Mth.approachDegrees(yRot, player.getYRot(), 0.00001F);
                    this.setYBodyRot(yRot);

                    vector = new Vec3(player.xxa, 0.0, player.zza <= 0.0F ? (double)(player.zza * 0.25F) : (double)player.zza);
                    double d0;
                    double d1;
                    if (player.getData(AetherDataAttachments.AETHER_PLAYER).isJumping()) {
                        this.setDeltaMovement(new Vec3(0.0, 0.0, 0.0));
                    } else {
                        d0 = Math.toRadians(this.getYRot());
                        d1 = Math.toRadians(-player.getXRot());
                        double d2 = Math.cos(d1);
                        this.setDeltaMovement(0.98 * (this.getDeltaMovement().x() + 0.05 * Math.cos(d0) * d2), 0.98 * (this.getDeltaMovement().y() + 0.02 * Math.sin(d1)), 0.98 * (this.getDeltaMovement().z() + 0.05 * Math.sin(d0) * d2));
                    }

                    if (!this.level().isClientSide()) {
                        super.travel(vector);
                    }

                    d0 = this.getX() - this.xo;
                    d1 = this.getZ() - this.zo;
                    float f4 = 4.0F * Mth.sqrt((float)(d0 * d0 + d1 * d1));
                    if (f4 > 1.0F) {
                        f4 = 1.0F;
                    }

                    this.walkAnimation.update(f4, 0.4F);
                }
            } else {
                super.travel(vector);
            }
        }

    }

    /**
     * Allows the player to open the aerwhale's chest if the player is sneaking or if the passenger count is larger than the allowed passenger count.
     * Allows the player to mount the aerwhale if the aerwhale has a saddle.
     * See chestInteract()
     */

    @Inject(at = @At("HEAD"), cancellable = true, method = "mobInteract")
    protected void mobInteract(Player player, InteractionHand hand, CallbackInfoReturnable<InteractionResult> cir) {
        ItemStack itemStack = player.getItemInHand(hand);
        if (this.isSaddleable() && itemStack.is(AetherItems.NATURE_STAFF.get())) {
            itemStack.hurtAndBreak(1, player, (p) -> {
                p.broadcastBreakEvent(hand);
            });
            this.deep_Aether$setStill(!this.deep_Aether$isStill());
            for (int i = 0; i < 20; ++i) {
                EntityUtil.spawnMovementExplosionParticles(this);
            }

            cir.setReturnValue(InteractionResult.sidedSuccess(this.level().isClientSide()));
        }

        else if(getPassengers().size() > 1)
            cir.setReturnValue(chestInteract(player));

        else if(player.isSecondaryUseActive()) {
            cir.setReturnValue(chestInteract(player));
        }
        else if (this.isSaddled()) {
            player.startRiding(this);
            cir.setReturnValue(InteractionResult.sidedSuccess(this.level().isClientSide()));
        }
    }

    /**
     * Code Copied from {@link net.minecraft.world.entity.vehicle.ChestBoat}
     */
    @Unique
    private InteractionResult chestInteract(Player player) {
        InteractionResult interactionresult = this.interactWithContainerVehicle(player);
        if (interactionresult.consumesAction()) {
            this.gameEvent(GameEvent.CONTAINER_OPEN, player);
            PiglinAi.angerNearbyPiglins(player, true);
        }

        return interactionresult;
    }

    /**
     * We want the Aerwhale to be able to have two passengers or less
     */
    @Override
    protected boolean canAddPassenger(Entity entity) {
        return getPassengers().size() < 2;
    }

    /**
     * Copied from {@link net.minecraft.world.entity.animal.horse.Llama}
     * offsets the players correctly from the center of the aerwhale. Allows multiple players to ride the aerwhale.
     */

    @Override
    protected void positionRider(Entity entity, MoveFunction moveFunction) {
        int i = this.getPassengers().indexOf(entity);
        if (i >= 0) {
            boolean flag = i == 0;
            float f = 0.7F;
            float f1 = (float) (this.isRemoved() ? (double) 0.01F : this.getBbHeight() * 0.75D);
            float f2 = 0;

            if(level().isClientSide && AetherConfig.CLIENT.legacy_models.get()) {
                f1+=1.7F;
                f-=1.9F;
                f2 = 0.1F;
            }

            if (this.getPassengers().size() > 1) {
                if (!flag) {
                    f = -1.2F;
                }
            }

            if(i == 0) {
                f1 += 0.3F;
                f-=0.1F;
            }
            else f = -2.1F;

            Vec3 vec3 = (new Vec3(0.0D, 0.0D, f)).yRot(-this.yBodyRot * ((float) Math.PI / 180F));
            moveFunction.accept(entity, this.getX() + vec3.x, this.getY() + (double) f1, this.getZ() + vec3.z+f2);
            this.deep_Aether$clampRotation(entity);
        }
    }

    /**
     * Copied from {@link net.minecraft.world.entity.animal.horse.Llama}
     */
    @Unique
    private void deep_Aether$clampRotation(Entity p_252070_) {
        p_252070_.setYBodyRot(this.getYRot());
        float f = p_252070_.getYRot();
        float f1 = Mth.wrapDegrees(f - this.getYRot());
        float f2 = Mth.clamp(f1, -160.0F, 160.0F);
        p_252070_.yRotO += f2 - f1;
        float f3 = f + f2 - f1;
        p_252070_.setYRot(f3);
        p_252070_.setYHeadRot(f3);
    }


    /**
     * Saddle Code
     */
    @Override
    public boolean isSaddleable() {
        return this.isAlive();
    }


    @Override
    public void equipSaddle(@Nullable SoundSource source) {
        this.deep_Aether$setSaddled(true);
    }

    @Override
    public boolean isSaddled() {
        return this.getEntityData().get(DATA_SADDLE_ID);
    }
    @Unique
    public void deep_Aether$setSaddled(boolean isSaddled) {
        this.getEntityData().set(DATA_SADDLE_ID, isSaddled);
    }
    /**
     * Save Data Code
     */

    @Override
    public void addAdditionalSaveData(CompoundTag tag) {
        super.addAdditionalSaveData(tag);
        this.addChestVehicleSaveData(tag);
        tag.putBoolean("isSaddled", isSaddled());
        tag.putBoolean("isStill", deep_Aether$isStill());
    }

    @Override
    public void readAdditionalSaveData(CompoundTag tag) {
        super.readAdditionalSaveData(tag);
        this.readChestVehicleSaveData(tag);
        if(tag.contains("isSaddled"))
            this.deep_Aether$setSaddled(tag.getBoolean("isSaddled"));
        if(tag.contains("isStill"))
            this.deep_Aether$setStill(tag.getBoolean("isStill"));

    }

    /**
     * Chest Code
     * Copied from {@link net.minecraft.world.entity.vehicle.ChestBoat}
     */
    @Unique
    private NonNullList<ItemStack> deep_Aether$itemStacks = NonNullList.withSize(this.getContainerSize(), ItemStack.EMPTY);
    @Unique
    @Nullable
    private ResourceLocation deep_Aether$lootTable;
    @Unique
    private long deep_Aether$lootTableSeed;

    @Override
    public void die(@NotNull DamageSource damageSource) {
        super.die(damageSource);
        this.chestVehicleDestroyed(damageSource, this.level(), this);
    }

    @Override
    public void remove(Entity.@NotNull RemovalReason reason) {
        if (!this.level().isClientSide && reason.shouldDestroy()) {
            Containers.dropContents(this.level(), this, this);
        }

        super.remove(reason);
    }

    @Override
    public void openCustomInventoryScreen(Player player) {
        player.openMenu(this);
        if (!player.level().isClientSide) {
            this.gameEvent(GameEvent.CONTAINER_OPEN, player);
            PiglinAi.angerNearbyPiglins(player, true);
        }
    }

    @Override
    public NonNullList<ItemStack> getItemStacks() {
        return this.deep_Aether$itemStacks;
    }

    @Override
    public void clearItemStacks() {
        this.deep_Aether$itemStacks = NonNullList.withSize(this.getContainerSize(), ItemStack.EMPTY);
    }

    @Override
    public int getContainerSize() {
        return 27;
    }

    @Override
    public @NotNull ItemStack getItem(int a) {
        return this.getChestVehicleItem(a);
    }

    @Override
    public @NotNull ItemStack removeItem(int a, int b) {
        return this.removeChestVehicleItem(a, b);
    }

    @Override
    public @NotNull ItemStack removeItemNoUpdate(int a) {
        return this.removeChestVehicleItemNoUpdate(a);
    }

    @Override
    public void setItem(int a, @NotNull ItemStack b) {
        this.setChestVehicleItem(a, b);
    }

    @Override
    public void setChanged() {
    }

    @Override
    public boolean stillValid(Player player) {
        return this.isChestVehicleStillValid(player);
    }

    @Override
    public void clearContent() {
        this.clearChestVehicleContent();
    }

    @Nullable
    @Override
    public AbstractContainerMenu createMenu(int a, Inventory inventory, Player player) {

        if (this.deep_Aether$lootTable != null && player.isSpectator()) {
            return null;
        } else {
            this.deep_Aether$unpackLootTable(inventory.player);
            return ChestMenu.threeRows(a, inventory, this);
        }
    }
    @Unique
    public void deep_Aether$unpackLootTable(@javax.annotation.Nullable Player player) {
        this.unpackChestVehicleLootTable(player);
    }

    @Override
    public @NotNull SlotAccess getSlot(int a) {
        return this.getChestVehicleSlot(a);
    }

    public void setLootTable(@Nullable ResourceLocation lootTable) {
        this.deep_Aether$lootTable = lootTable;
    }

    public long getLootTableSeed() {
        return this.deep_Aether$lootTableSeed;
    }

    public void setLootTableSeed(long seed) {
        this.deep_Aether$lootTableSeed = seed;
    }

    @Override
    public ResourceLocation getLootTable() {
        return this.deep_Aether$lootTable;
    }

    /**
     * Makes the Aerwhale drop a saddle on death if a saddle is equipped
     */

    @Override
    protected void dropEquipment() {
        super.dropEquipment();
        if (this.isSaddled()) {
            if (!this.level().isClientSide) {
                this.spawnAtLocation(DAItems.AERWHALE_SADDLE.get());
            }
            this.deep_Aether$setSaddled(false);
        }
    }
}
