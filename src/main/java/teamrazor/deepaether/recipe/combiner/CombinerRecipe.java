package teamrazor.deepaether.recipe.combiner;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.core.NonNullList;
import net.minecraft.core.RegistryAccess;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.Nullable;
import teamrazor.deepaether.DeepAether;
import teamrazor.deepaether.init.DABlocks;
import teamrazor.deepaether.recipe.DABookCategory;

import java.util.List;

public class CombinerRecipe implements Recipe<SimpleContainer> {
    private final DABookCategory category;
    public final List<Ingredient> inputItems;
    public final ItemStack output;

    public CombinerRecipe(DABookCategory daBookCategory, List<Ingredient> inputItems, ItemStack output) {
        this.inputItems = inputItems;
        this.output = output;
        this.category = daBookCategory;
    }

    @Override
    public boolean matches(SimpleContainer pContainer, Level pLevel) {
        if(pLevel.isClientSide())
            return false;

        return testEachSlot(pContainer, inputItems.get(0))
                && testEachSlot(pContainer, inputItems.get(1))
                && testEachSlot(pContainer, inputItems.get(2));
    }

    public DABookCategory daCategory() {
        return this.category;
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
        return Serializer.INSTANCE;
    }

    @Override
    public RecipeType<?> getType() {
        return Type.INSTANCE;
    }

    @Override
    public ItemStack getToastSymbol() {
        return new ItemStack(DABlocks.COMBINER.get());
    }

    public static class Type implements RecipeType<CombinerRecipe> {
        public static final Type INSTANCE = new Type();
        public static final String ID = "combining";
    }

    public static class Serializer implements RecipeSerializer<CombinerRecipe> {
        public static final Serializer INSTANCE = new Serializer();
        public static final ResourceLocation ID = new ResourceLocation(DeepAether.MODID, "combining");

        private static final Codec<CombinerRecipe> CODEC = RecordCodecBuilder.create((instance) -> instance.group(
                DABookCategory.CODEC.fieldOf("category").forGetter(CombinerRecipe::daCategory),
                Ingredient.LIST_CODEC_NONEMPTY.fieldOf("ingredients").forGetter((recipe) -> recipe.inputItems),
                ItemStack.RESULT_CODEC.fieldOf("output").forGetter((recipe) -> recipe.output)
        ).apply(instance, CombinerRecipe::new));

        @Override
        public Codec<CombinerRecipe> codec() {
            return CODEC;
        }

        @Nullable
        @Override
        public CombinerRecipe fromNetwork(FriendlyByteBuf buffer) {
            DABookCategory daBookCategory = buffer.readEnum(DABookCategory.class);
            NonNullList<Ingredient> inputs = NonNullList.withSize(buffer.readInt(), Ingredient.EMPTY);

            for(int i = 0; i < inputs.size(); i++) {
                inputs.set(i, Ingredient.fromNetwork(buffer));
            }

            ItemStack output = buffer.readItem();
            return new CombinerRecipe(daBookCategory, inputs, output);
        }

        @Override
        public void toNetwork(FriendlyByteBuf buffer, CombinerRecipe recipe) {
            buffer.writeEnum(recipe.daCategory());
            buffer.writeInt(recipe.inputItems.size());

            for (Ingredient ingredient : recipe.getIngredients()) {
                ingredient.toNetwork(buffer);
            }
            buffer.writeItem(recipe.getResultItem(null));
        }
    }
}

