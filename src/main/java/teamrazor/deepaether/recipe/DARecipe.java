package teamrazor.deepaether.recipe;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import teamrazor.deepaether.DeepAetherMod;

public class DARecipe {
    public static final DeferredRegister<RecipeType<?>> RECIPE_TYPES = DeferredRegister.create(ForgeRegistries.RECIPE_TYPES, DeepAetherMod.MODID);
    public static final RegistryObject<RecipeType<PoisonRecipe>> POISON_RECIPE = RECIPE_TYPES.register("poison_recipe", () ->
            RecipeType.simple(new ResourceLocation(DeepAetherMod.MODID, "poison_recipe")));

}
