package io.github.razordevs.deep_aether.recipe.jei;

import io.github.razordevs.deep_aether.DeepAether;
import io.github.razordevs.deep_aether.init.DABlocks;
import io.github.razordevs.deep_aether.init.DAItems;
import io.github.razordevs.deep_aether.recipe.DARecipeTypes;
import mezz.jei.api.IModPlugin;
import mezz.jei.api.JeiPlugin;
import mezz.jei.api.registration.IRecipeCatalystRegistration;
import mezz.jei.api.registration.IRecipeCategoryRegistration;
import mezz.jei.api.registration.IRecipeRegistration;
import net.minecraft.client.Minecraft;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.RecipeHolder;
import net.minecraft.world.item.crafting.RecipeManager;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;

@JeiPlugin
public class DAJEIPlugin implements IModPlugin {
    @Override
    public @NotNull ResourceLocation getPluginUid() {
        return ResourceLocation.fromNamespaceAndPath(DeepAether.MODID, "jei");
    }

    @Override
    public void registerCategories(IRecipeCategoryRegistration registration) {
        registration.addRecipeCategories(new PoisonRecipeCategory(registration.getJeiHelpers().getGuiHelper()));
        registration.addRecipeCategories(new CombinerRecipeCategory(registration.getJeiHelpers().getGuiHelper()));
    }

    @Override
    public void registerRecipes(IRecipeRegistration registration) {
        RecipeManager rm = Objects.requireNonNull(Minecraft.getInstance().level).getRecipeManager();
        registration.addRecipes(PoisonRecipeCategory.RECIPE_TYPE, rm.getAllRecipesFor(DARecipeTypes.POISON_RECIPE.get()).stream().map(RecipeHolder::value).toList());
        registration.addRecipes(CombinerRecipeCategory.RECIPE_TYPE, rm.getAllRecipesFor(DARecipeTypes.COMBINING.get()).stream().map(RecipeHolder::value).toList());
    }

    @Override
    public void registerRecipeCatalysts(IRecipeCatalystRegistration registration) {
        registration.addRecipeCatalyst(new ItemStack(DAItems.PLACEABLE_POISON_BUCKET.get()), PoisonRecipeCategory.RECIPE_TYPE);
        registration.addRecipeCatalyst(new ItemStack(DABlocks.COMBINER.get()), CombinerRecipeCategory.RECIPE_TYPE);
    }
}