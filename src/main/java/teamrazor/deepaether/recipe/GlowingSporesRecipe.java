package teamrazor.deepaether.recipe;


import com.aetherteam.aether.recipe.recipes.block.MatchEventRecipe;
import com.aetherteam.nitrogen.recipe.BlockPropertyPair;
import com.aetherteam.nitrogen.recipe.BlockStateIngredient;
import com.aetherteam.nitrogen.recipe.recipes.AbstractBlockStateRecipe;
import com.aetherteam.nitrogen.recipe.serializer.BlockStateRecipeSerializer;
import net.minecraft.commands.CommandFunction;
import net.minecraft.core.BlockPos;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;

public class GlowingSporesRecipe extends AbstractBlockStateRecipe implements MatchEventRecipe {

    public GlowingSporesRecipe(ResourceLocation id, BlockStateIngredient ingredient, BlockPropertyPair result, CommandFunction.CacheableFunction function) {
        super(DARecipeTypes.GLOWING_SPORES_RECIPE.get(), id, ingredient, result, function);
    }

    @Override
    public boolean matches(Player player, Level level, BlockPos pos, ItemStack stack, BlockState oldState, BlockState newState, RecipeType recipeType) {
        return this.matches(level, pos, oldState) && MatchEventRecipe.super.matches(player, level, pos, stack, oldState, newState, recipeType);
    }

    @Override
    public RecipeSerializer<?> getSerializer() {
        return DARecipeSerializers.GLOWING_SPORES_RECIPE.get();
    }

    public static class Serializer extends BlockStateRecipeSerializer<GlowingSporesRecipe> {
        public Serializer() {
            super(GlowingSporesRecipe::new);
        }
    }
}