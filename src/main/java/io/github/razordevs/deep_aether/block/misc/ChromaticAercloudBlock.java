package io.github.razordevs.deep_aether.block.misc;

import com.aetherteam.aether.attachment.AetherDataAttachments;
import com.aetherteam.aether.mixin.mixins.common.accessor.ServerGamePacketListenerImplAccessor;
import io.github.razordevs.deep_aether.block.natural.SterlingAercloudBlock;
import io.github.razordevs.deep_aether.item.gear.DAEquipmentUtil;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.HalfTransparentBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.Vec3;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.EntityCollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;

public class ChromaticAercloudBlock extends HalfTransparentBlock {
    public ChromaticAercloudBlock(Properties properties) {
        super(properties);
    }

    @Override
    public VoxelShape getCollisionShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context) {
        if(context instanceof EntityCollisionContext collisionContext) {
            if(collisionContext.getEntity() instanceof LivingEntity entity) {
                if(DAEquipmentUtil.hasCloudNecklace(entity))
                    return SterlingAercloudBlock.FULL_COLLISION;
            }
        }
        return SterlingAercloudBlock.NO_COLLISION;
    }

    @Override
    public boolean propagatesSkylightDown(BlockState state, BlockGetter level, BlockPos pos) {
        return false;
    }

    @Override
    public VoxelShape getVisualShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context) {
        return Shapes.empty();
    }

    @Override
    public float getShadeBrightness(BlockState state, BlockGetter level, BlockPos pos) {
        return 0.25F;
    }

    @Override
    public void fallOn(Level level, BlockState state, BlockPos pos, Entity entity, float fallDistance) { }

    //Allows players to fly if entity they're inside the Chromatic Aercloud Block. Floats other Entities upwards.
    @Override
    public void entityInside(BlockState state, Level level, BlockPos pos, Entity entity) {
        entity.resetFallDistance();
        if (entity instanceof Player player)
            handleFlight(player);
        else {
            entity.setDeltaMovement(entity.getDeltaMovement().multiply(1.0, 0.005, 1.0));
            entity.setOnGround(true);
        }
    }

    static void handleFlight(LivingEntity entity) {
        if (entity instanceof Player player && !player.getAbilities().flying) { // The player can't have creative flight enabled, otherwise it causes issues.
            var data = player.getData(AetherDataAttachments.AETHER_PLAYER);
            Vec3 deltaMovement = player.getDeltaMovement();
            // Updates the flight modifier and timer values.
            if (data.isJumping() && !player.onGround()) { // Checks if the player is off the ground and holding the jump key (space bar by default).
                if (data.getFlightModifier() >= data.getFlightModifierMax()) { // Limits the flight modifier to a maximum value.
                    data.setFlightModifier(data.getFlightModifierMax());
                }
                if (data.getFlightTimer() > 2) { // Starts allowing the player to fly after a 2 tick delay of being off the ground.
                    if (data.getFlightTimer() < data.getFlightTimerMax()) { // Allows the player to fly until the maximum value is hit.
                        data.setFlightModifier(data.getFlightModifier() + 0.25F);
                        data.setFlightTimer(data.getFlightTimer() + 1);
                    }
                } else {
                    data.setFlightTimer(data.getFlightTimer() + 1);
                }
            } else if (!data.isJumping()) {
                // Resets only the modifier if the player stops holding the jump key midair. The timer doesn't reset though and remains frozen, and will continue where it left off when the key is held again, preventing infinite flight.
                data.setFlightModifier(1.0F);
            }
            if (player.onGround()) { // Resets both timer and modifier if the player is on the ground.
                data.setFlightTimer(0);
                data.setFlightModifier(1.0F);
            }
            // Modifies the player's upwards movement based on the set flight modifier and timer values.
            if (data.isJumping() && !player.onGround() && data.getFlightTimer() > 2 && data.getFlightModifier() > 1.0F) {
                player.setDeltaMovement(deltaMovement.x(), 0.025F * data.getFlightModifier(), deltaMovement.z());
            }
            if (player instanceof ServerPlayer serverPlayer) { // Prevents the player from being kicked for flying.
                ServerGamePacketListenerImplAccessor serverGamePacketListenerImplAccessor = (ServerGamePacketListenerImplAccessor) serverPlayer.connection;
                serverGamePacketListenerImplAccessor.aether$setAboveGroundTickCount(0);
            }
        }
    }
}
