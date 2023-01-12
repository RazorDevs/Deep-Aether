package teamrazor.deepaether.block;

import com.gildedgames.aether.effect.AetherEffects;
import net.minecraft.core.BlockPos;
import net.minecraft.world.Difficulty;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;

public class DAEffectBlock extends DALeavesBlock {

    protected static final VoxelShape COLLISION_SHAPE = Block.box(1.0D, 0.0D, 1.0D, 15.9D, 16.0D, 15.9D);
    //protected static final VoxelShape OUTLINE_SHAPE = Block.box(1.0D, 0.0D, 1.0D, 15.0D, 16.0D, 15.0D);



    public void stepOn(Level p_153777_, BlockPos p_153778_, BlockState p_153779_, Entity pEntity) {
        if (!p_153777_.isClientSide && p_153777_.getDifficulty() != Difficulty.PEACEFUL) {

            if (pEntity.horizontalCollision  && pEntity instanceof LivingEntity /*&& !EnchantmentHelper.hasFrostWalker((LivingEntity)pEntity)*/) {
                LivingEntity pLivingentity = (LivingEntity)pEntity;
                pLivingentity.addEffect(new MobEffectInstance(AetherEffects.INEBRIATION.get(), 60, 1));
            }
        }
        super.stepOn(p_153777_, p_153778_, p_153779_, pEntity);
    }
    public void entityInside(BlockState p_51148_, Level p_51149_, BlockPos p_51150_, Entity pEntity) {
        if (!p_51149_.isClientSide && p_51149_.getDifficulty() != Difficulty.PEACEFUL) {
            LivingEntity pLivingentity = (LivingEntity) pEntity;
            pLivingentity.addEffect(new MobEffectInstance(AetherEffects.INEBRIATION.get(), 60, 1));
        }
    }


    public VoxelShape getCollisionShape(BlockState p_51176_, BlockGetter p_51177_, BlockPos p_51178_, CollisionContext p_51179_) {
        return COLLISION_SHAPE;
    }

    /*public VoxelShape getShape(BlockState p_51171_, BlockGetter p_51172_, BlockPos p_51173_, CollisionContext p_51174_) {
        return OUTLINE_SHAPE;
    }*/
}
