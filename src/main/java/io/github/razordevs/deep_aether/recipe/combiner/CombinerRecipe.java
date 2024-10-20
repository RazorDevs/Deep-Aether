package io.github.razordevs.deep_aether.recipe.combiner;

import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import io.github.razordevs.deep_aether.init.DABlocks;
import io.github.razordevs.deep_aether.recipe.DABookCategory;
import io.github.razordevs.deep_aether.recipe.DARecipeSerializers;
import io.github.razordevs.deep_aether.recipe.DARecipeTypes;
import net.minecraft.core.HolderLookup;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.world.Container;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.*;
import net.minecraft.world.level.Level;

import java.util.ArrayList;
import java.util.List;

public class CombinerRecipe implements Recipe<CombinderRecipeInput> {

    private final String group;
    private final DABookCategory category;
    public final List<Ingredient> inputItems;
    public final ItemStack output;

    public CombinerRecipe(String group, DABookCategory category, List<Ingredient> inputItems, ItemStack output) {
        this.group = group;
        this.inputItems = inputItems;
        this.output = output;
        this.category = category;
    }

    @Override
    public boolean matches(CombinderRecipeInput input, Level pLevel) {
        return inputItems.get(0).test(input.items().getFirst())
                && inputItems.get(1).test(input.items().get(1))
                && inputItems.get(2).test(input.items().get(2));
    }

    public DABookCategory daCategory() {
        return this.category;
    }

    public ItemStack getResult(){
        return output;
    }

    /**
     * Method that checks if the passed ingredient is present in only one of the 3
     * slots using the XOR operator. This enables "shapeless" recipes in the combiner.
     */

    private boolean testEachSlot(Container pContainer, Ingredient ingredient){
        return ingredient.test(pContainer.getItem(0))
                ^ ingredient.test(pContainer.getItem(1))
                ^ ingredient.test(pContainer.getItem(2));
    }

    @Override
    public ItemStack assemble(CombinderRecipeInput input, HolderLookup.Provider registries) {
        return output.copy();
    }

    @Override
    public boolean canCraftInDimensions(int pWidth, int pHeight) {
        return true;
    }

    @Override
    public ItemStack getResultItem(HolderLookup.Provider registries) {
        return output.copy();
    }

    @Override
    public RecipeSerializer<?> getSerializer() {
        return DARecipeSerializers.COMBINING.get();
    }

    @Override
    public RecipeType<?> getType() {
        return DARecipeTypes.COMBINING.get();
    }

    @Override
    public ItemStack getToastSymbol() {
        return new ItemStack(DABlocks.COMBINER.get());
    }

    public static class Serializer implements RecipeSerializer<CombinerRecipe> {

        public final MapCodec<CombinerRecipe> CODEC = RecordCodecBuilder.mapCodec((instance) -> instance.group(
                Codec.STRING.optionalFieldOf("group", "").forGetter(CombinerRecipe::getGroup),
                DABookCategory.CODEC.fieldOf("category").forGetter(CombinerRecipe::daCategory),
                Ingredient.CODEC_NONEMPTY
                        .listOf()
                        .fieldOf("ingredients")
                        .forGetter(recipe -> recipe.inputItems),
                ItemStack.CODEC.fieldOf("output").forGetter(recipe -> recipe.output)
        ).apply(instance, CombinerRecipe::new));


        public static final StreamCodec<RegistryFriendlyByteBuf, CombinerRecipe> STREAM_CODEC = StreamCodec.of(
                CombinerRecipe.Serializer::toNetwork, CombinerRecipe.Serializer::fromNetwork
        );

        public static CombinerRecipe fromNetwork(RegistryFriendlyByteBuf buffer) {
            String group = buffer.readUtf();
            DABookCategory daBookCategory = buffer.readEnum(DABookCategory.class);
            List<Ingredient> ingredients = new ArrayList<>();
            for(int i = 0; i < 3; i++) {
                ingredients.add(Ingredient.CONTENTS_STREAM_CODEC.decode(buffer));
            }

            ItemStack itemstack = ItemStack.STREAM_CODEC.decode(buffer);
            return new CombinerRecipe(group, daBookCategory, ingredients, itemstack);
        }

        public static void toNetwork(RegistryFriendlyByteBuf buffer, CombinerRecipe recipe) {
            buffer.writeUtf(recipe.getGroup());
            buffer.writeEnum(recipe.daCategory());

            for (Ingredient ingredient : recipe.inputItems)
                Ingredient.CONTENTS_STREAM_CODEC.encode(buffer, ingredient);

            ItemStack.STREAM_CODEC.encode(buffer, recipe.getResult());
        }

        @Override
        public MapCodec<CombinerRecipe> codec() {
            return CODEC;
        }

        @Override
        public StreamCodec<RegistryFriendlyByteBuf, CombinerRecipe> streamCodec() {
            return STREAM_CODEC;
        }
    }
}

