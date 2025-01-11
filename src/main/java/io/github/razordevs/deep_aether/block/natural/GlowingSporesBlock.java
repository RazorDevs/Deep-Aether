package io.github.razordevs.deep_aether.block.natural;

import com.aetherteam.aether.item.materials.behavior.ItemUseConversion;
import io.github.razordevs.deep_aether.recipe.DARecipeTypes;
import io.github.razordevs.deep_aether.recipe.GlowingSporesRecipe;
import net.minecraft.core.BlockPos;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.ItemInteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.RecipeHolder;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.DoublePlantBlock;
import net.minecraft.world.level.block.PinkPetalsBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.DoubleBlockHalf;
import net.minecraft.world.phys.BlockHitResult;

public class GlowingSporesBlock extends PinkPetalsBlock implements ItemUseConversion<GlowingSporesRecipe> {
    public GlowingSporesBlock(Properties properties) {
        super(properties);
    }

    public ItemInteractionResult convertBlock(RecipeType<GlowingSporesRecipe> recipeType, Player player, Level level, BlockPos pos, ItemStack stack, BlockState oldBlockState) {
        for (RecipeHolder<GlowingSporesRecipe> glowingSporesRecipeRecipeHolder : level.getRecipeManager().getAllRecipesFor(recipeType)) {
            if (glowingSporesRecipeRecipeHolder != null) {
                BlockState newState = glowingSporesRecipeRecipeHolder.value().getResultState(oldBlockState);
                if (glowingSporesRecipeRecipeHolder.value().matches(player, level, pos, stack, oldBlockState, newState, recipeType)) {
                    if ( this.convertNoUpdate(level, pos, newState)) {
                        if (player != null && !player.getAbilities().instabuild) {
                            stack.shrink(1);
                        }

                        return ItemInteractionResult.SKIP_DEFAULT_BLOCK_INTERACTION;
                    }
                }
            }
        }

        return ItemInteractionResult.PASS_TO_DEFAULT_BLOCK_INTERACTION;
    }

    private boolean convertNoUpdate(Level level, BlockPos pos, BlockState newState) {
        level.setBlock(pos, newState, 18);
        return true;
    }

    @Override
    protected ItemInteractionResult useItemOn(ItemStack stack, BlockState state, Level level, BlockPos pos, Player player, InteractionHand hand, BlockHitResult result) {
        ItemInteractionResult interactionResult;

        if (state.hasProperty(DoublePlantBlock.HALF)) {
            interactionResult = this.convertBlock(DARecipeTypes.GLOWING_SPORES_RECIPE.get(), player, level, pos, stack, state);
            if (state.getValue(DoublePlantBlock.HALF).equals(DoubleBlockHalf.LOWER)) {
                this.convertBlock(DARecipeTypes.GLOWING_SPORES_RECIPE.get(), player, level, pos.above(), stack, state);
            } else
                this.convertBlock(DARecipeTypes.GLOWING_SPORES_RECIPE.get(), player, level, pos.below(), stack, state);
        } else interactionResult = this.convertBlock(DARecipeTypes.GLOWING_SPORES_RECIPE.get(), player, level, pos, stack, state);
        return interactionResult;
    }
}
