package teamrazor.deepaether.block;

import com.gildedgames.aether.effect.AetherEffects;
import com.mojang.blaze3d.shaders.Effect;
import net.minecraft.client.renderer.EffectInstance;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.tags.FluidTags;
import net.minecraft.util.RandomSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.alchemy.Potion;
import net.minecraft.world.level.*;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.LiquidBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.material.*;
import teamrazor.deepaether.init.DeepAetherModFluids;
import teamrazor.deepaether.init.DeepAetherModItems;
import teamrazor.deepaether.init.DeepAetherModParticles;

import javax.annotation.Nullable;
import java.io.IOException;
import java.util.Optional;
import java.util.Random;
import java.util.function.Supplier;

public class PoisonBlock extends LiquidBlock {

    public PoisonBlock(Supplier<? extends FlowingFluid> p_54694_, Properties p_54695_) {
        super(p_54694_, p_54695_);
    }

    @Override
    public void stepOn(Level p_152431_, BlockPos p_152432_, BlockState p_152433_, Entity entity) {
        if(entity instanceof LivingEntity) {
            ((LivingEntity) entity).addEffect(new MobEffectInstance(AetherEffects.INEBRIATION.get(), 500, 0, false, false));
        }
    }

    @Override
    public void animateTick(BlockState blockState, Level level, BlockPos blockPos, RandomSource randomSource) {
        double d0 = (double)blockPos.getX();
        double d1 = (double)blockPos.getY();
        double d2 = (double)blockPos.getZ();
        //level.addAlwaysVisibleParticle(DeepAetherModParticles.POISON_BUBBLES.get(), d0 + 0.5D, d1, d2 + 0.5D, 0.0D, 0.04D, 0.0D);
        level.addAlwaysVisibleParticle(DeepAetherModParticles.POISON_BUBBLES.get(), d0 + (double)randomSource.nextFloat(), d1 + (double)randomSource.nextFloat(), d2 + (double)randomSource.nextFloat(), 0.0D, 0.04D, 0.0D);
        super.animateTick(blockState, level, blockPos, randomSource);
    }

    @Override
    public void entityInside(BlockState p_60495_, Level level, BlockPos blockPos, Entity entity) {
        if(entity instanceof LivingEntity){
            ((LivingEntity) entity).addEffect(new MobEffectInstance(AetherEffects.INEBRIATION.get(), 500, 0, false, false));
        }
    }
}