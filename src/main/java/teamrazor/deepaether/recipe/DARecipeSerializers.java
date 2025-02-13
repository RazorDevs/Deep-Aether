package teamrazor.deepaether.recipe;

import com.aetherteam.nitrogen.recipe.serializer.BlockStateRecipeSerializer;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.SimpleCraftingRecipeSerializer;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import teamrazor.deepaether.DeepAether;
import teamrazor.deepaether.recipe.combiner.CombinerRecipe;
import teamrazor.deepaether.recipe.poison.PoisonRecipe;
import teamrazor.deepaether.recipe.poison.PoisonRecipeSerializer;

public class DARecipeSerializers {
    public static final DeferredRegister<RecipeSerializer<?>> RECIPE_SERIALIZERS =
            DeferredRegister.create(ForgeRegistries.RECIPE_SERIALIZERS, DeepAether.MODID);

    public static final RegistryObject<PoisonRecipeSerializer<PoisonRecipe>> POISON_RECIPE =
            RECIPE_SERIALIZERS.register("poison_recipe", PoisonRecipe.Serializer::new);

    public static final RegistryObject<RecipeSerializer<CombinerRecipe>> COMBINER_RECIPE =
            RECIPE_SERIALIZERS.register("combining", CombinerRecipe.Serializer::new);

    public static final RegistryObject<BlockStateRecipeSerializer<GoldenSwetBallRecipe>> GOLDEN_SWET_BALL_RECIPE = RECIPE_SERIALIZERS.register("golden_swet_ball_recipe", GoldenSwetBallRecipe.Serializer::new);

    public static final RegistryObject<BlockStateRecipeSerializer<GlowingSporesRecipe>> GLOWING_SPORES_RECIPE =
            RECIPE_SERIALIZERS.register("glowing_spores_recipe", GlowingSporesRecipe.Serializer::new);

    public static final RegistryObject<RecipeSerializer<FloatyScarfColoring>> FLOATY_SCARF_COLORING =
            RECIPE_SERIALIZERS.register("floaty_scarf_coloring", () -> new SimpleCraftingRecipeSerializer<>(FloatyScarfColoring::new));
}
