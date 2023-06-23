package teamrazor.deepaether.block;

import com.aetherteam.aether.block.AetherBlocks;
import com.aetherteam.aether.block.FrictionCapped;
import com.aetherteam.aether.block.natural.AercloudBlock;
import com.aetherteam.aether.block.natural.QuicksoilBlock;
import com.aetherteam.aether.effect.AetherEffects;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.RandomSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LightningBolt;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.HalfTransparentBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.Vec3;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import teamrazor.deepaether.init.DABlocks;

public class SterlingAercloudBlock extends HalfTransparentBlock {


    public SterlingAercloudBlock(Properties properties) {
        super(properties);
    }

    @Override
    public void entityInside(BlockState state, Level level, BlockPos pos, Entity entity) {
        LightningBolt lightningbolt = EntityType.LIGHTNING_BOLT.create(level);
        if (lightningbolt != null) {
            lightningbolt.moveTo(Vec3.atBottomCenterOf(pos));
            level.addFreshEntity(lightningbolt);
        }
        level.setBlockAndUpdate(pos, AetherBlocks.COLD_AERCLOUD.get().defaultBlockState());

        super.entityInside(state, level, pos, entity);
    }
}
