package teamrazor.deepaether.recipe;

import net.minecraft.core.RegistryAccess;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.RecipeType;
import teamrazor.deepaether.init.DAItems;

public class PoisonRecipe extends AbstractPoisonRecipe {
    public PoisonRecipe(ResourceLocation id, String group, Ingredient ingredient, ItemStack result) {
        super(DARecipe.POISON_RECIPE.get(), id, group, ingredient, result);
    }

    public ItemStack getToastSymbol() {
        return new ItemStack(DAItems.PLACEABLE_POISON_BUCKET.get());
    }

    public RecipeSerializer<?> getSerializer() {
        return DARecipeSerializers.POISON_RECIPE.get();
    }

    public static class Serializer extends PoisonRecipeSerializer<PoisonRecipe> {
        public Serializer() {
            super(PoisonRecipe::new);
        }
    }



    public Ingredient getIngredient() {
        return this.ingredient;
    }

    public RecipeType<?> getType() {
        return this.type;
    }

    public ResourceLocation getId() {
        return this.id;
    }

    public ItemStack getResultItem(RegistryAccess registryAccess) {
        return ItemStack.EMPTY;
    }
}
