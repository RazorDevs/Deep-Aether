package teamrazor.deepaether.recipe.combiner;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.core.RegistryAccess;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.level.Level;
import teamrazor.deepaether.init.DABlocks;
import teamrazor.deepaether.recipe.DABookCategory;
import teamrazor.deepaether.recipe.DARecipeSerializers;
import teamrazor.deepaether.recipe.DARecipeTypes;

import java.util.ArrayList;
import java.util.List;

public class CombinerRecipe implements Recipe<SimpleContainer> {
    private final DABookCategory category;
    public final List<Ingredient> inputItems;
    private final ItemStack output;

    public CombinerRecipe(DABookCategory category, List<Ingredient> inputItems, ItemStack output) {
        this.inputItems = inputItems;
        this.output = output;
        this.category = category;
    }

    @Override
    public boolean matches(SimpleContainer pContainer, Level pLevel) {
        return testEachSlot(pContainer, inputItems.get(0))
                && testEachSlot(pContainer, inputItems.get(1))
                && testEachSlot(pContainer, inputItems.get(2));
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

    private boolean testEachSlot(SimpleContainer pContainer, Ingredient ingredient){
        return ingredient.test(pContainer.getItem(0))
                ^ ingredient.test(pContainer.getItem(1))
                ^ ingredient.test(pContainer.getItem(2));
    }

    @Override
    public ItemStack assemble(SimpleContainer pContainer, RegistryAccess pRegistryAccess) {
        return output.copy();
    }

    @Override
    public boolean canCraftInDimensions(int pWidth, int pHeight) {
        return true;
    }

    @Override
    public ItemStack getResultItem(RegistryAccess pRegistryAccess) {
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

        public final Codec<CombinerRecipe> codec = RecordCodecBuilder.create((instance) -> instance.group(
                DABookCategory.CODEC.fieldOf("category").forGetter(CombinerRecipe::daCategory),
                Ingredient.LIST_CODEC_NONEMPTY.fieldOf("ingredients").forGetter((recipe) -> recipe.inputItems),
                ItemStack.RESULT_CODEC.fieldOf("output").forGetter((recipe) -> recipe.output)
        ).apply(instance, CombinerRecipe::new));

        @Override
        public Codec<CombinerRecipe> codec() {
            return codec;
        }

        @Override
        public CombinerRecipe fromNetwork(FriendlyByteBuf buffer) {
            List<Ingredient> ingredients = new ArrayList<>();
            for(int i = 0; i < 3; i++) {
                ingredients.add(i, Ingredient.fromNetwork(buffer));
            }

            return new CombinerRecipe(buffer.readEnum(DABookCategory.class), ingredients, buffer.readItem());
        }

        @Override
        public void toNetwork(FriendlyByteBuf buffer, CombinerRecipe recipe) {
            for (Ingredient ingredient : recipe.inputItems)
                ingredient.toNetwork(buffer);
            buffer.writeEnum(recipe.daCategory());
            buffer.writeItem(recipe.getResult());
        }

    }
}

