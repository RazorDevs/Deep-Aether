package teamrazor.deepaether.block;

import com.aetherteam.aether.effect.AetherEffects;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.RandomSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.BlockAndTintGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.LiquidBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.FlowingFluid;
import net.minecraft.world.level.material.FluidState;
import teamrazor.deepaether.advancement.PoisonTrigger;
import teamrazor.deepaether.fluids.DAFluidInteraction;
import teamrazor.deepaether.init.DAParticles;
import teamrazor.deepaether.recipe.DARecipe;
import teamrazor.deepaether.recipe.PoisonRecipe;

import java.util.function.Supplier;

public class PoisonBlock extends LiquidBlock {

    public PoisonBlock(Supplier<? extends FlowingFluid> supplier, Properties properties) {
        super(supplier, properties);
    }

    @Override
    public void stepOn(Level level, BlockPos blockPos, BlockState blockState, Entity entity) {
        if (entity instanceof LivingEntity) {
            ((LivingEntity) entity).addEffect(new MobEffectInstance(AetherEffects.INEBRIATION.get(), 100, 0, false, false));
        }
    }

    @Override
    public boolean shouldDisplayFluidOverlay(BlockState state, BlockAndTintGetter level, BlockPos pos, FluidState fluidState) {
        return true;
    }

    @Override
    public void animateTick(BlockState blockState, Level level, BlockPos blockPos, RandomSource randomSource) {
        double d0 = blockPos.getX();
        double d1 = blockPos.getY();
        double d2 = blockPos.getZ();
        level.addAlwaysVisibleParticle(DAParticles.POISON_BUBBLES.get(), d0 + (double) randomSource.nextFloat(), d1 + (double) randomSource.nextFloat(), d2 + (double) randomSource.nextFloat(), 0.0D, 0.04D, 0.0D);
        if (randomSource.nextInt(10) == 0) {
            level.playLocalSound(d0, d1, d2, SoundEvents.BUBBLE_COLUMN_BUBBLE_POP, SoundSource.BLOCKS, 0.2F + randomSource.nextFloat() * 0.2F, 0.9F + randomSource.nextFloat() * 0.15F, false);
        }
        super.animateTick(blockState, level, blockPos, randomSource);
    }



    //Used as a timer, to indicate when the position recipe is finished.
    boolean COUNT = false;
    int TIME = 0;
    public void randomTick(BlockState state, ServerLevel level, BlockPos pos, RandomSource random) {
        if (COUNT && TIME < 200) {
            TIME += 1;
        } else {
            TIME = 0;
            COUNT = false;
        }
    }
    public boolean isRandomlyTicking(BlockState state) {
        return true;
    }

    /**
     * Used to apply inebriation effect to entities and convert items if they have a recipe
     * See {@link DARecipe} for poison recipe serializer
     */
    @Override
    public void entityInside(BlockState blockState, Level level, BlockPos pos, Entity entity) {

        //Applies inebriation effect to living entities
        if (entity instanceof LivingEntity) {
            ((LivingEntity) entity).addEffect(new MobEffectInstance(AetherEffects.INEBRIATION.get(), 100, 0, false, false));
        }

        //Poison recipe code
        else if (entity instanceof ItemEntity itemEntity) {

            //Temporary initialization for the result item of the poison recipe
            Item TRANSFORM_ITEM = null;

            int count = itemEntity.getItem().getCount();

            //Checks if any poison recipe matches the ingredient
            if (!level.isClientSide()) {
                for (PoisonRecipe recipe : level.getRecipeManager().getAllRecipesFor(DARecipe.POISON_RECIPE.get())) {
                    if (recipe.getIngredients().get(0).getItems()[0].getItem() == itemEntity.getItem().getItem()) {
                        TRANSFORM_ITEM = recipe.getResult().getItem();

                        //Starts the timer in the randomTick function.
                        COUNT = true;
                    }
                }
            }

            //No poison recipe was found, so we cancel the code.
            if (TRANSFORM_ITEM == null)
                return;

            //We spawn particles around the ingredient to indicate that the ingredient is getting converted.
            if (!level.isClientSide && itemEntity.isAlive()) {
                BlockPos itemPos = itemEntity.getOnPos();
                ServerLevel serverlevel = (ServerLevel) level;
                serverlevel.sendParticles(DAParticles.POISON_BUBBLES.get(), (double) itemPos.getX() + level.random.nextDouble(), pos.getY() + 1, (double) itemPos.getZ() + level.random.nextDouble(), 1, 0.0D, 0.0D, 0.2D, 0.3D);
                if (level.random.nextInt(25) == 0) {
                    serverlevel.playSound(itemEntity, itemPos, SoundEvents.LAVA_EXTINGUISH, SoundSource.BLOCKS, 0.2F + level.random.nextFloat() * 0.2F, 0.9F + level.random.nextFloat() * 0.15F);
                }
            }

            //Converts the ingredient when enough time has passed and the entity still is alive.
            if ((TIME > 2) && itemEntity.isAlive()) {

                //Stops the timer
                COUNT = false;
                //Grants the "Purple Magic" advancement.
                if (itemEntity.getOwner() instanceof ServerPlayer player) {
                    PoisonTrigger.INSTANCE.trigger(player, itemEntity.getItem());
                }

                //Removes the ingredient item and spawns the result item
                itemEntity.discard();
                entity.spawnAtLocation(new ItemStack(TRANSFORM_ITEM, count), 0);
                entity.setNoGravity(true);
            }
        }
    }

    public void onPlace(BlockState blockState, Level level, BlockPos blockPos, BlockState state, boolean b) {
        if (!DAFluidInteraction.canInteract(level, blockPos)) {
            level.scheduleTick(blockPos, blockState.getFluidState().getType(), this.getFluid().getTickDelay(level));
        }
    }

    public void neighborChanged(BlockState blockState, Level level, BlockPos blockPos, Block block, BlockPos blockPos1, boolean b) {
        if (!DAFluidInteraction.canInteract(level, blockPos)) {
            level.scheduleTick(blockPos, blockState.getFluidState().getType(), this.getFluid().getTickDelay(level));
        }
    }
}

