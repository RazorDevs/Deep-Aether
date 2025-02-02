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
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Recipe;
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
    //Used as a timer, to indicate when the position recipe is finished.
    boolean doCount = false;
    int conversionTime = 0;

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
    public boolean isRandomlyTicking(BlockState state) {
        return true;
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

    @Override
    public void randomTick(BlockState state, ServerLevel level, BlockPos pos, RandomSource random) {
        if (doCount && conversionTime < 200) {
            conversionTime++;
        }else {
            conversionTime = 0;
            doCount = false;
        }
    }

    @Override
    public void entityInside(BlockState blockState, Level level, BlockPos pos, Entity entity) {
        if (level.isClientSide()) return;

        //If we're not dealing with an ItemEntity, apply Inebriation and return.
        if (!(entity instanceof ItemEntity itemEntity)) {
            ((LivingEntity) entity).addEffect(new MobEffectInstance(AetherEffects.INEBRIATION.get(), 100, 0, false, false));
            return;
        }

        //Poison recipe code
        //Temporary initialization for the result item of the poison recipe
        ItemStack TRANSFORM_ITEM = ItemStack.EMPTY;

        //Checks if any poison recipe matches the ingredient
        for (Recipe<?> recipe : level.getRecipeManager().getAllRecipesFor(DARecipe.POISON_RECIPE.get())) {
            if(recipe instanceof PoisonRecipe poisonRecipe) {
                if (poisonRecipe.getIngredients().get(0).getItems()[0].is(itemEntity.getItem().getItem())) {
                    TRANSFORM_ITEM = poisonRecipe.getResult();

                    //Starts the timer in the randomTick function.
                    this.doCount = true;
                }
            }
        }

        if(TRANSFORM_ITEM.isEmpty() || !itemEntity.isAlive()) return;

        TRANSFORM_ITEM.setCount(itemEntity.getItem().getCount());

        //We spawn particles around the ingredient to indicate that the ingredient is getting converted.
        BlockPos itemPos = itemEntity.getOnPos();
        ServerLevel serverlevel = (ServerLevel) level;

        serverlevel.sendParticles(DAParticles.POISON_BUBBLES.get(), (double) itemPos.getX() + level.random.nextDouble(), pos.getY() + 1, (double) itemPos.getZ() + level.random.nextDouble(), 1, 0.0D, 0.0D, 0.2D, 0.3D);
        if (level.random.nextInt(25) == 0)
            serverlevel.playSound(null, itemPos, SoundEvents.LAVA_EXTINGUISH, SoundSource.BLOCKS, 0.2F + level.random.nextFloat() * 0.2F, 0.9F + level.random.nextFloat() * 0.15F);

        //Converts the ingredient when enough time has passed and the entity still is alive.
        if (conversionTime > 2) {

            //Stops the timer
            this.doCount = false;
            //Grants the "Purple Magic" advancement.
            if(itemEntity.getThrower() != null && level.getPlayerByUUID(itemEntity.getThrower()) instanceof ServerPlayer player) {
                PoisonTrigger.INSTANCE.trigger(player, itemEntity.getItem());
            }

            //Removes the ingredient item and spawns the result item
            itemEntity.discard();
            entity.spawnAtLocation(TRANSFORM_ITEM, 0);
            entity.setNoGravity(true);
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

