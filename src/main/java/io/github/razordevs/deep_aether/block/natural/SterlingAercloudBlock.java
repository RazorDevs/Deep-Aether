package io.github.razordevs.deep_aether.block.natural;

import com.aetherteam.aether.block.AetherBlocks;
import io.github.razordevs.deep_aether.datagen.tags.DATags;
import io.github.razordevs.deep_aether.item.gear.EquipmentUtil;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LightningBolt;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.HalfTransparentBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.Vec3;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.EntityCollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;

public class SterlingAercloudBlock extends HalfTransparentBlock {

    public SterlingAercloudBlock(Properties properties) {
        super(properties);
    }
    public static final VoxelShape FULL_COLLISION = Block.box(0.0, 0.0, 0.0, 16.0, 16.0, 16.0);
    public static final VoxelShape NO_COLLISION = Block.box(0.0, 0.0, 0.0, 0.0, 0.0, 0.0);

    @Override
    public VoxelShape getCollisionShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context) {
        if(context instanceof EntityCollisionContext collisionContext) {
            if(collisionContext.getEntity() instanceof LivingEntity entity) {
                if(EquipmentUtil.hasCloudNecklace(entity))
                    return FULL_COLLISION;
            }
        }
        return NO_COLLISION;
    }

    @Override
    public void entityInside(BlockState state, Level level, BlockPos pos, Entity entity) {
        if (entity instanceof LivingEntity && !entity.getType().is(DATags.Entities.STERLING_AERCLOUD_BLACKLIST)) {
            if(EquipmentUtil.hasCloudNecklace((LivingEntity) entity))
                return;
            LightningBolt lightningbolt = EntityType.LIGHTNING_BOLT.create(level);
            if (lightningbolt != null) {
                lightningbolt.moveTo(Vec3.atBottomCenterOf(pos));
                level.addFreshEntity(lightningbolt);
            }
            level.setBlockAndUpdate(pos, AetherBlocks.COLD_AERCLOUD.get().defaultBlockState());
        }
        super.entityInside(state, level, pos, entity);
    }

    @Override
    public boolean propagatesSkylightDown(BlockState p_49928_, BlockGetter p_49929_, BlockPos p_49930_) {
        return true;
    }
}
