package teamrazor.deepaether.recipe;

import com.aetherteam.aether.block.AetherBlocks;
import com.aetherteam.aether.recipe.AetherRecipeSerializers;
import com.aetherteam.nitrogen.recipe.BlockStateIngredient;
import net.minecraft.core.RegistryAccess;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.Container;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.biome.Biome;

import javax.annotation.Nullable;

public class PoisonRecipe extends AbstractPoisonRecipe {
    public PoisonRecipe(ResourceLocation id, String group, Ingredient ingredient, ItemStack result) {
        super(DARecipe.POISON_RECIPE.get(), id, group, ingredient, result);
    }

    public ItemStack getToastSymbol() {
        return new ItemStack(AetherBlocks.FREEZER.get());
    }

    public RecipeSerializer<?> getSerializer() {
        return AetherRecipeSerializers.FREEZING.get();
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
