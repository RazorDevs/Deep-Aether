package teamrazor.deepaether.recipe.poison;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.core.RegistryAccess;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.util.ExtraCodecs;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.RecipeType;
import net.neoforged.neoforge.common.crafting.CraftingHelper;
import teamrazor.deepaether.init.DAItems;
import teamrazor.deepaether.recipe.DARecipeSerializers;
import teamrazor.deepaether.recipe.DARecipeTypes;

import javax.annotation.Nullable;

public class PoisonRecipe extends AbstractPoisonRecipe {
    public PoisonRecipe(String group, Ingredient ingredient, ItemStack result) {
        super(DARecipeTypes.POISON_RECIPE.get(), group, ingredient, result);
    }

    public ItemStack getToastSymbol() {
        return new ItemStack(DAItems.PLACEABLE_POISON_BUCKET.get());
    }

    public RecipeSerializer<?> getSerializer() {
        return DARecipeSerializers.POISON_RECIPE.get();
    }

    public static class Serializer implements RecipeSerializer<PoisonRecipe> {
        private static final Codec<PoisonRecipe> CODEC = RecordCodecBuilder.create((instance) -> instance.group(
                ExtraCodecs.strictOptionalField(Codec.STRING, "group", "").forGetter(PoisonRecipe::getGroup),
                Ingredient.CODEC_NONEMPTY.fieldOf("ingredient").forGetter((recipe) -> recipe.ingredient),
                CraftingHelper.smeltingResultCodec().fieldOf("result").forGetter((recipe) -> recipe.result)
        ).apply(instance, PoisonRecipe::new));

        @Override
        public Codec<PoisonRecipe> codec() {
            return CODEC;
        }

        @Nullable
        @Override
        public PoisonRecipe fromNetwork(FriendlyByteBuf buffer) {
            String group = buffer.readUtf();
            Ingredient ingredient = Ingredient.fromNetwork(buffer);
            ItemStack result = buffer.readItem();
            return new PoisonRecipe(group, ingredient, result);
        }

        @Override
        public void toNetwork(FriendlyByteBuf buffer, PoisonRecipe recipe) {
            buffer.writeUtf(recipe.group);
            recipe.ingredient.toNetwork(buffer);
            buffer.writeItem(recipe.result);
        }
    }


    public Ingredient getIngredient() {
        return this.ingredient;
    }

    public RecipeType<?> getType() {
        return this.type;
    }

    public ItemStack getResultItem(RegistryAccess registryAccess) {
        return ItemStack.EMPTY;
    }
}
