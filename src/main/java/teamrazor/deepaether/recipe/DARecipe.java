package teamrazor.deepaether.recipe;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import teamrazor.deepaether.DeepAether;
import teamrazor.deepaether.recipe.poison.PoisonRecipe;

public class DARecipe {
    public static final DeferredRegister<RecipeType<?>> RECIPE_TYPES = DeferredRegister.create(ForgeRegistries.RECIPE_TYPES, DeepAether.MODID);
    public static final RegistryObject<RecipeType<PoisonRecipe>> POISON_RECIPE = RECIPE_TYPES.register("poison_recipe", () ->
            RecipeType.simple(new ResourceLocation(DeepAether.MODID, "poison_recipe")));

    public static final RegistryObject<RecipeType<GoldenSwetBallRecipe>> GOLDEN_SWET_BALL_RECIPE = RECIPE_TYPES.register("golden_swet_ball_recipe", () -> RecipeType.simple(new ResourceLocation(DeepAether.MODID, "golden_swet_ball_recipe")));
}
