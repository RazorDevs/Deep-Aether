package teamrazor.deepaether.recipe;

import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.crafting.RecipeType;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;
import teamrazor.deepaether.DeepAether;
import teamrazor.deepaether.recipe.combiner.CombinerRecipe;
import teamrazor.deepaether.recipe.poison.PoisonRecipe;

public class DARecipe {
    public static final DeferredRegister<RecipeType<?>> RECIPE_TYPES = DeferredRegister.create(BuiltInRegistries.RECIPE_TYPE, DeepAether.MODID);
    public static final DeferredHolder<RecipeType<?>, RecipeType<PoisonRecipe>> POISON_RECIPE = RECIPE_TYPES.register("poison_recipe", () ->
            RecipeType.simple(new ResourceLocation(DeepAether.MODID, "poison_recipe")));

    public static final DeferredHolder<RecipeType<?>,RecipeType<CombinerRecipe>> COMBINER_RECIPE = RECIPE_TYPES.register("combiner_recipe", () ->
            RecipeType.simple(new ResourceLocation(DeepAether.MODID, "combiner_recipe")));

    public static final DeferredHolder<RecipeType<?>, RecipeType<GoldenSwetBallRecipe>> GOLDEN_SWET_BALL_RECIPE = RECIPE_TYPES.register("golden_swet_ball_recipe", () -> RecipeType.simple(new ResourceLocation(DeepAether.MODID, "golden_swet_ball_recipe")));
}
