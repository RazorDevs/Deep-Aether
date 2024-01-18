package teamrazor.deepaether.block;

import com.aetherteam.aether.block.AetherBlocks;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LightningBolt;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.HalfTransparentBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.Vec3;
import teamrazor.deepaether.datagen.tags.DATags;

public class SterlingAercloudBlock extends HalfTransparentBlock {

    public SterlingAercloudBlock(Properties properties) {
        super(properties);
    }

    @Override
    public void entityInside(BlockState state, Level level, BlockPos pos, Entity entity) {
        if (entity instanceof LivingEntity && !entity.getType().is(DATags.Entities.STERLING_AERCLOUD_BLACKLIST)) {
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
    public boolean propagatesSkylightDown(BlockState blockState, BlockGetter blockGetter, BlockPos blockPos) {
        return true;
    }
}
