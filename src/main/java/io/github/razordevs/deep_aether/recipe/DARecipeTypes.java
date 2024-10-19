package io.github.razordevs.deep_aether.recipe;

import io.github.razordevs.deep_aether.DeepAether;
import io.github.razordevs.deep_aether.recipe.combiner.CombinerRecipe;
import io.github.razordevs.deep_aether.recipe.poison.PoisonRecipe;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.crafting.RecipeType;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

public class DARecipeTypes {
    public static final DeferredRegister<RecipeType<?>> RECIPE_TYPES = DeferredRegister.create(BuiltInRegistries.RECIPE_TYPE, DeepAether.MODID);
    public static final DeferredHolder<RecipeType<?>, RecipeType<PoisonRecipe>> POISON_RECIPE = RECIPE_TYPES.register("poison_recipe", () ->
            RecipeType.simple(ResourceLocation.fromNamespaceAndPath(DeepAether.MODID, "poison_recipe")));

    public static final DeferredHolder<RecipeType<?>,RecipeType<CombinerRecipe>> COMBINING = RECIPE_TYPES.register("combining", () ->
            RecipeType.simple(ResourceLocation.fromNamespaceAndPath(DeepAether.MODID, "combining")));

    public static final DeferredHolder<RecipeType<?>, RecipeType<GoldenSwetBallRecipe>> GOLDEN_SWET_BALL_RECIPE = RECIPE_TYPES.register("golden_swet_ball_recipe", () -> RecipeType.simple(ResourceLocation.fromNamespaceAndPath(DeepAether.MODID, "golden_swet_ball_recipe")));
    public static final DeferredHolder<RecipeType<?>, RecipeType<GlowingSporesRecipe>> GLOWING_SPORES_RECIPE = RECIPE_TYPES.register("glowing_spores_recipe", () -> RecipeType.simple(ResourceLocation.fromNamespaceAndPath(DeepAether.MODID, "glowing_spores_recipe")));

}
