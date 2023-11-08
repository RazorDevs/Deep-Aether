package teamrazor.deepaether.recipe;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonSyntaxException;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.GsonHelper;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.ShapedRecipe;
import net.minecraft.world.level.ItemLike;
import net.minecraftforge.registries.ForgeRegistries;

import javax.annotation.Nullable;

public class PoisonRecipeSerializer<T extends AbstractPoisonRecipe> implements RecipeSerializer<T> {
    private final PoisonRecipeSerializer.CookieBaker<T> factory;

    public PoisonRecipeSerializer(PoisonRecipeSerializer.CookieBaker<T> factory) {
        this.factory = factory;
    }

    public T fromJson(ResourceLocation id, JsonObject json) {
        String group = GsonHelper.getAsString(json, "group", "");
        JsonElement ingredientJson = GsonHelper.isArrayNode(json, "ingredient") ? GsonHelper.getAsJsonArray(json, "ingredient") : GsonHelper.getAsJsonObject(json, "ingredient");
        Ingredient ingredient = Ingredient.fromJson(ingredientJson);
        if (!json.has("result")) {
            throw new JsonSyntaxException("Missing result, expected to find a string or object");
        } else {
            ItemStack result;
            if (json.get("result").isJsonObject()) {
                result = ShapedRecipe.itemStackFromJson(GsonHelper.getAsJsonObject(json, "result"));
            } else {
                String resultString = GsonHelper.getAsString(json, "result");
                ResourceLocation resultLocation = new ResourceLocation(resultString);
                result = new ItemStack((ItemLike) ForgeRegistries.ITEMS.getValue(resultLocation));
                if (result.isEmpty()) {
                    throw new IllegalStateException("Item: " + resultString + " does not exist");
                }
            }
            return this.factory.create(id, group, ingredient, result);
        }
    }

    @Nullable
    public T fromNetwork(ResourceLocation id, FriendlyByteBuf buffer) {
        String group = buffer.readUtf();
        Ingredient ingredient = Ingredient.fromNetwork(buffer);
        ItemStack result = buffer.readItem();
        return this.factory.create(id, group, ingredient, result);
    }

    public void toNetwork(FriendlyByteBuf buffer, T recipe) {
        buffer.writeUtf(recipe.getGroup());
        recipe.getIngredients().get(0).toNetwork(buffer);
        buffer.writeItem(recipe.getResult());
    }

    public interface CookieBaker<T extends AbstractPoisonRecipe> {
        T create(ResourceLocation var1, String var2, Ingredient var4, ItemStack var5);
    }
}
