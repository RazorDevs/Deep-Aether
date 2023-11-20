package teamrazor.deepaether.block;

import com.aetherteam.aether.block.AetherBlocks;
import com.aetherteam.aether.effect.AetherEffects;
import com.aetherteam.aether.item.AetherItems;
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
import net.minecraft.world.entity.player.Player;
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
import teamrazor.deepaether.init.DABlocks;
import teamrazor.deepaether.init.DAParticles;
import teamrazor.deepaether.recipe.DARecipe;
import teamrazor.deepaether.recipe.PoisonRecipe;

import java.util.function.Supplier;

public class PoisonBlock extends LiquidBlock {

    public PoisonBlock(Supplier<? extends FlowingFluid> supplier, Properties properties) {
        super(supplier, properties);
    }

    boolean COUNT = false;
    float TIME = 0;
    Item TRANSFORM_ITEM;
    boolean CAN_TRANSFORM = false;

    @Override
    public void stepOn(Level level, BlockPos blockPos, BlockState blockState, Entity entity) {
        if (entity instanceof LivingEntity) {
            ((LivingEntity) entity).addEffect(new MobEffectInstance(AetherEffects.INEBRIATION.get(), 250, 0, false, false));
        }
    }

    @Override
    public boolean shouldDisplayFluidOverlay(BlockState state, BlockAndTintGetter level, BlockPos pos, FluidState fluidState) {
        return true;
    }


    @Override
        public void animateTick (BlockState blockState, Level level, BlockPos blockPos, RandomSource randomSource){
            double d0 = blockPos.getX();
            double d1 = blockPos.getY();
            double d2 = blockPos.getZ();
            level.addAlwaysVisibleParticle(DAParticles.POISON_BUBBLES.get(), d0 + (double) randomSource.nextFloat(), d1 + (double) randomSource.nextFloat(), d2 + (double) randomSource.nextFloat(), 0.0D, 0.04D, 0.0D);
            if (randomSource.nextInt(10) == 0) {
                level.playLocalSound(d0, d1, d2, SoundEvents.BUBBLE_COLUMN_BUBBLE_POP, SoundSource.BLOCKS, 0.2F + randomSource.nextFloat() * 0.2F, 0.9F + randomSource.nextFloat() * 0.15F, false);
            }
            super.animateTick(blockState, level, blockPos, randomSource);


            if (COUNT && TIME < 100) {
                TIME += 0.05F;
            } else {
                TIME = 0;
                COUNT = false;
            }
        }

        @Override
        public void entityInside (BlockState blockState, Level level, BlockPos pos, Entity entity) {
            if (entity instanceof LivingEntity) {
                ((LivingEntity) entity).addEffect(new MobEffectInstance(AetherEffects.INEBRIATION.get(), 250, 0, false, false));
            } else if (entity instanceof ItemEntity itemEntity) {

                ItemEntity TRANSFORMED_ITEM_ENTITY = (new ItemEntity(level, pos.getX(), pos.getY(), pos.getZ(), new ItemStack(Items.DIRT, 1)));
                int count = itemEntity.getItem().getCount();

                CAN_TRANSFORM = false;

                if (!level.isClientSide()) {
                    for (Recipe<?> recipe : level.getRecipeManager().getAllRecipesFor(DARecipe.POISON_RECIPE.get())) {
                        if (recipe instanceof PoisonRecipe poisonRecipe) {
                            if (poisonRecipe.getIngredients().get(0).getItems()[0].getItem() == itemEntity.getItem().getItem()) {
                                TRANSFORM_ITEM = poisonRecipe.getResult().getItem();
                                CAN_TRANSFORM = true;
                                COUNT = true;
                            }
                        }
                    }
                }
                System.out.println(TRANSFORM_ITEM);
                //if (itemEntity.getItem().getItem() == AetherItems.ENCHANTED_DART.get()) {
                //    TRANSFORM_ITEM = AetherItems.GOLDEN_DART.get();
                //    CAN_TRANSFORM = true;
                //}

                if (!level.isClientSide && CAN_TRANSFORM) {
                    if ((TRANSFORMED_ITEM_ENTITY.getFeetBlockState().getBlock() == this || level.getBlockState(TRANSFORMED_ITEM_ENTITY.getOnPos().below(1)).getBlock() == this) && TRANSFORMED_ITEM_ENTITY.isAlive()) {
                        BlockPos itemPos = TRANSFORMED_ITEM_ENTITY.getOnPos();
                        ServerLevel serverlevel = (ServerLevel) level;
                        serverlevel.sendParticles(DAParticles.POISON_BUBBLES.get(), (double) itemPos.getX() + level.random.nextDouble(), pos.getY() + 1, (double) itemPos.getZ() + level.random.nextDouble(), 1, 0.0D, 0.0D, 0.2D, 0.3D);
                        if (level.random.nextInt(25) == 0) {
                            serverlevel.playSound(null, itemPos, SoundEvents.LAVA_EXTINGUISH, SoundSource.BLOCKS, 0.2F + level.random.nextFloat() * 0.2F, 0.9F + level.random.nextFloat() * 0.15F);
                        }
                    }
                }

                if ((TIME > 5) && itemEntity.isAlive() && CAN_TRANSFORM) {
                    CAN_TRANSFORM = false;
                    COUNT = false;
                    if(itemEntity.getOwner() instanceof ServerPlayer player) {
                        PoisonTrigger.INSTANCE.trigger(player, itemEntity.getItem());
                    }

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

