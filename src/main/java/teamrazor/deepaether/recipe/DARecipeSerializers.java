package teamrazor.deepaether.recipe;

import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import teamrazor.deepaether.DeepAetherMod;

public class DARecipeSerializers {
    public static final DeferredRegister<RecipeSerializer<?>> RECIPE_SERIALIZERS =
            DeferredRegister.create(ForgeRegistries.RECIPE_SERIALIZERS, DeepAetherMod.MODID);

    public static final RegistryObject<PoisonRecipeSerializer<PoisonRecipe>> POISON_RECIPE =
            RECIPE_SERIALIZERS.register("poison_recipe", PoisonRecipe.Serializer::new);


}
