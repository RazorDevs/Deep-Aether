package teamrazor.deepaether.block;

import com.gildedgames.aether.block.AetherBlockStateProperties;
import com.gildedgames.aether.block.natural.AetherDoubleDropsLeaves;
import com.gildedgames.aether.effect.AetherEffects;
import net.minecraft.core.BlockPos;
import net.minecraft.world.Difficulty;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;

public class DAEffectBlock extends AetherDoubleDropsLeaves {

    //protected static final VoxelShape COLLISION_SHAPE = Block.box(1.0D, 0.0D, 1.0D, 15.9D, 16.0D, 15.9D);
    public DAEffectBlock(Properties properties) {
        super(properties);
        this.registerDefaultState(this.defaultBlockState().setValue(AetherBlockStateProperties.DOUBLE_DROPS, false));
    }


    public void stepOn(Level level, BlockPos pos, BlockState blockstate, Entity entity) {
        if (!level.isClientSide && level.getDifficulty() != Difficulty.PEACEFUL && entity instanceof LivingEntity livingEntity) {
            livingEntity.addEffect(new MobEffectInstance(AetherEffects.INEBRIATION.get(), 30, 1));
        }
        super.stepOn(level, pos, blockstate, entity);
    }
    /*public void entityInside(BlockState blockState, Level level, BlockPos pos, Entity entity) {
        if (!level.isClientSide && level.getDifficulty() != Difficulty.PEACEFUL && entity instanceof LivingEntity pLivingentity) {
            pLivingentity.addEffect(new MobEffectInstance(AetherEffects.INEBRIATION.get(), 60, 1));
        }
    }*/


    /*public VoxelShape getCollisionShape(BlockState p_51176_, BlockGetter p_51177_, BlockPos p_51178_, CollisionContext p_51179_) {
        return COLLISION_SHAPE;
    }*/
}
