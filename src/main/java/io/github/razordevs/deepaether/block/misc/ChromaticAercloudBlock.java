package io.github.razordevs.deepaether.deepaether.block.misc;

import io.github.razordevs.deepaether.deepaether.item.gear.EquipmentUtil;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.HalfTransparentBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.EntityCollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import io.github.razordevs.deepaether.deepaether.block.behavior.ChromaticBlockFlight;
import io.github.razordevs.deepaether.deepaether.block.natural.SterlingAercloudBlock;

public class ChromaticAercloudBlock extends HalfTransparentBlock {

    @Override
    public VoxelShape getCollisionShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context) {
        if(context instanceof EntityCollisionContext collisionContext) {
            if(collisionContext.getEntity() instanceof LivingEntity entity) {
                if(EquipmentUtil.hasCloudNecklace(entity))
                    return SterlingAercloudBlock.FULL_COLLISION;
            }
        }
        return SterlingAercloudBlock.NO_COLLISION;
    }

    public ChromaticAercloudBlock(Properties properties) {
        super(properties);
    }

    @Override
    public boolean propagatesSkylightDown(BlockState state, BlockGetter level, BlockPos pos) {
        return false;
    }

    @SuppressWarnings("deprecation")
    @Override
    public VoxelShape getVisualShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context) {
        return Shapes.empty();
    }

    @SuppressWarnings("deprecation")
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
            ChromaticBlockFlight.handleFlight(player);
        else {
            entity.setDeltaMovement(entity.getDeltaMovement().multiply(1.0, 0.005, 1.0));
            entity.setOnGround(true);
        }
    }
}
