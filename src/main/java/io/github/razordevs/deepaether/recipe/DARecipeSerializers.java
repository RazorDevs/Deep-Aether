package io.github.razordevs.deepaether.deepaether.recipe;

import com.aetherteam.nitrogen.recipe.serializer.BlockStateRecipeSerializer;
import io.github.razordevs.deepaether.deepaether.recipe.combiner.CombinerRecipe;
import io.github.razordevs.deepaether.deepaether.recipe.poison.PoisonRecipe;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;
import teamrazor.deepaether.DeepAether;

public class DARecipeSerializers {
    public static final DeferredRegister<RecipeSerializer<?>> RECIPE_SERIALIZERS =
            DeferredRegister.create(BuiltInRegistries.RECIPE_SERIALIZER, DeepAether.MODID);

    public static final DeferredHolder<RecipeSerializer<?>, RecipeSerializer<PoisonRecipe>> POISON_RECIPE =
            RECIPE_SERIALIZERS.register("poison_recipe", PoisonRecipe.Serializer::new);

    public static final DeferredHolder<RecipeSerializer<?>, RecipeSerializer<CombinerRecipe>> COMBINING =
            RECIPE_SERIALIZERS.register("combining", CombinerRecipe.Serializer::new);

    public static final DeferredHolder<RecipeSerializer<?>, BlockStateRecipeSerializer<GoldenSwetBallRecipe>> GOLDEN_SWET_BALL_RECIPE = RECIPE_SERIALIZERS.register("golden_swet_ball_recipe", GoldenSwetBallRecipe.Serializer::new);
    public static final DeferredHolder<RecipeSerializer<?>, BlockStateRecipeSerializer<GlowingSporesRecipe>> GLOWING_SPORES_RECIPE = RECIPE_SERIALIZERS.register("glowing_spores_recipe", GlowingSporesRecipe.Serializer::new);

}
