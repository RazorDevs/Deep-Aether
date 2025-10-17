package io.github.razordevs.deep_aether.item.misc;

import com.aetherteam.aether.item.materials.behavior.ItemUseConversion;
import io.github.razordevs.deep_aether.recipe.DARecipeTypes;
import io.github.razordevs.deep_aether.recipe.GlowingSporesRecipe;
import net.minecraft.core.BlockPos;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.item.crafting.RecipeHolder;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.DoublePlantBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.DoubleBlockHalf;
import net.minecraft.world.phys.BlockHitResult;

public class GlowingSporesItem extends BlockItem implements ItemUseConversion<GlowingSporesRecipe> {
    public GlowingSporesItem(Block block, Properties properties) {
        super(block, properties);
    }


    @Override
    public <T extends GlowingSporesRecipe> InteractionResult convertBlock(RecipeType<T> recipeType, UseOnContext context) {
        Player player = context.getPlayer();
        Level level = context.getLevel();
        BlockPos pos = context.getClickedPos();
        ItemStack stack = context.getItemInHand();
        BlockState oldBlockState = level.getBlockState(pos);

        for (RecipeHolder<T> glowingSporesRecipeRecipeHolder : level.getRecipeManager().getAllRecipesFor(recipeType)) {
            if (glowingSporesRecipeRecipeHolder != null) {
                BlockState newState = glowingSporesRecipeRecipeHolder.value().getResultState(oldBlockState);
                if (glowingSporesRecipeRecipeHolder.value().matches(player, level, pos, stack, oldBlockState, newState, recipeType)) {
                    if ( this.convertNoUpdate(level, pos, newState)) {
                        if (player != null && !player.getAbilities().instabuild) {
                            stack.shrink(1);
                        }

                        return InteractionResult.SUCCESS;
                    }
                }
            }
        }

        return InteractionResult.PASS;
    }

    public <T extends GlowingSporesRecipe> void convertBlockNoConsume(RecipeType<T> recipeType, UseOnContext context) {
        Player player = context.getPlayer();
        Level level = context.getLevel();
        BlockPos pos = context.getClickedPos();
        ItemStack stack = context.getItemInHand();
        BlockState oldBlockState = level.getBlockState(pos);

        for (RecipeHolder<T> glowingSporesRecipeRecipeHolder : level.getRecipeManager().getAllRecipesFor(recipeType)) {
            if (glowingSporesRecipeRecipeHolder != null) {
                BlockState newState = glowingSporesRecipeRecipeHolder.value().getResultState(oldBlockState);
                if (glowingSporesRecipeRecipeHolder.value().matches(player, level, pos, stack, oldBlockState, newState, recipeType)) {
                    this.convertNoUpdate(level, pos, newState);
                }
            }
        }
    }

    private boolean convertNoUpdate(Level level, BlockPos pos, BlockState newState) {
        level.setBlock(pos, newState, 18);
        return true;
    }

    @Override
    public InteractionResult useOn(UseOnContext context) {
        BlockPos pos = context.getClickedPos();
        Level level = context.getLevel();
        BlockState state = level.getBlockState(pos);

        InteractionResult interactionResult;

        if (state.hasProperty(DoublePlantBlock.HALF)) {
            interactionResult = this.convertBlock(DARecipeTypes.GLOWING_SPORES_RECIPE.get(), context);
            if(interactionResult == InteractionResult.SUCCESS) {
                if (state.getValue(DoublePlantBlock.HALF).equals(DoubleBlockHalf.LOWER)) {
                    System.out.println("a");
                    this.convertBlockNoConsume(DARecipeTypes.GLOWING_SPORES_RECIPE.get(), new UseOnContext(level, context.getPlayer(), context.getHand(), context.getItemInHand(), new BlockHitResult(context.getClickLocation(), context.getClickedFace(), pos.above(), context.isInside())));
                } else {
                    this.convertBlockNoConsume(DARecipeTypes.GLOWING_SPORES_RECIPE.get(), new UseOnContext(level, context.getPlayer(), context.getHand(), context.getItemInHand(), new BlockHitResult(context.getClickLocation(), context.getClickedFace(), pos.below(), context.isInside())));
                }
            }
        } else interactionResult = this.convertBlock(DARecipeTypes.GLOWING_SPORES_RECIPE.get(), context);

        if(interactionResult.consumesAction())
            return interactionResult;
        else return super.useOn(context);
    }
}