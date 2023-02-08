package teamrazor.deepaether.block;

import com.gildedgames.aether.effect.AetherEffects;
import net.minecraft.core.BlockPos;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.AbstractCauldronBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import teamrazor.deepaether.block.Behaviors.DaCauldronInteraction;


public class PoisonCauldronBlock extends AbstractCauldronBlock {


    public PoisonCauldronBlock(BlockBehaviour.Properties p_153498_) {
        super(p_153498_, DaCauldronInteraction.POISON);
    }

    protected double getContentHeight(BlockState p_153500_) {
        return 0.9375D;
    }

    public boolean isFull(BlockState p_153511_) {
        return true;
    }

    public void entityInside(BlockState p_153506_, Level p_153507_, BlockPos p_153508_, Entity entity) {
        if (this.isEntityInsideContent(p_153506_, p_153508_, entity)) {
            if (entity instanceof LivingEntity livingEntity)
                livingEntity.addEffect(new MobEffectInstance(AetherEffects.INEBRIATION.get(), 600, 0, false, false));
        }

    }

    public int getAnalogOutputSignal(BlockState p_153502_, Level p_153503_, BlockPos p_153504_) {
        return 14;
    }





}
