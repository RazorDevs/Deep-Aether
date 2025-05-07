package teamrazor.deepaether.recipe;

import net.minecraft.world.item.crafting.RecipeType;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import teamrazor.deepaether.DeepAether;
import teamrazor.deepaether.recipe.combiner.CombinerRecipe;
import teamrazor.deepaether.recipe.poison.PoisonRecipe;

public class DARecipeTypes {
    public static final DeferredRegister<RecipeType<?>> RECIPE_TYPES = DeferredRegister.create(ForgeRegistries.RECIPE_TYPES, DeepAether.MODID);
    public static final RegistryObject<RecipeType<PoisonRecipe>> POISON_RECIPE = RECIPE_TYPES.register("poison_recipe", () ->
            RecipeType.simple(DeepAether.getResource( "poison_recipe")));

    public static final RegistryObject<RecipeType<CombinerRecipe>> COMBINING = RECIPE_TYPES.register("combining", () ->
            RecipeType.simple(DeepAether.getResource( "combining")));

    public static final RegistryObject<RecipeType<GoldenSwetBallRecipe>> GOLDEN_SWET_BALL_RECIPE = RECIPE_TYPES.register("golden_swet_ball_recipe", () -> RecipeType.simple(DeepAether.getResource( "golden_swet_ball_recipe")));
    public static final RegistryObject<RecipeType<GlowingSporesRecipe>> GLOWING_SPORES_RECIPE = RECIPE_TYPES.register("glowing_spores_recipe", () -> RecipeType.simple(DeepAether.getResource( "glowing_spores_recipe")));
}
