package teamrazor.deepaether.recipe.combiner;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import net.minecraft.core.NonNullList;
import net.minecraft.core.RegistryAccess;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.GsonHelper;
import net.minecraft.world.Container;
import net.minecraft.world.Container;
import net.minecraft.world.Container;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.*;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.Nullable;
import teamrazor.deepaether.init.DABlocks;
import teamrazor.deepaether.recipe.DABookCategory;
import teamrazor.deepaether.recipe.DARecipeSerializers;
import teamrazor.deepaether.recipe.DARecipeTypes;

import java.util.List;

public class CombinerRecipe implements Recipe<Container> {
    private final ResourceLocation id;
    private final String group;
    private final DABookCategory category;
    public final NonNullList<Ingredient> inputItems = NonNullList.create();
    public final ItemStack output;
    protected final float experience;
    protected final int processingTime;

    public CombinerRecipe(ResourceLocation id, String group, DABookCategory category, List<Ingredient> inputItems, ItemStack output, float experience, int processingTime) {
        this.id = id;
        this.group = group;
        this.inputItems.addAll(inputItems);
        this.output = output;
        this.category = category;
        this.experience = experience;
        this.processingTime = processingTime;
    }

    @Override
    public ItemStack assemble(Container worldlyContainer, RegistryAccess registryAccess) {
        return this.output.copy();
    }

    @Override
    public boolean canCraftInDimensions(int width, int height) {
        return true;
    }

    @Override
    public NonNullList<Ingredient> getIngredients() {
        return inputItems;
    }

    @Override
    public ItemStack getResultItem(RegistryAccess registryAccess) {
        return this.output;
    }

    public ItemStack getResult(){
        return output;
    }

    @Override
    public String getGroup() {
        return this.group;
    }

    public float getExperience() {
        return this.experience;
    }

    public int getProcessingTime() {
        return this.processingTime;
    }

    public DABookCategory daCategory() {
        return this.category;
    }

    @Override
    public ItemStack getToastSymbol() {
        return new ItemStack(DABlocks.COMBINER.get());
    }

    @Override
    public ResourceLocation getId() {
        return this.id;
    }

    @Override
    public RecipeType<?> getType() {
        return DARecipeTypes.COMBINING.get();
    }

    @Override
    public RecipeSerializer<?> getSerializer() {
        return DARecipeSerializers.COMBINER_RECIPE.get();
    }

    @Override
    public boolean matches(Container pContainer, Level pLevel) {
        if(pLevel.isClientSide())
            return false;

        return testEachSlot(pContainer, inputItems.get(0))
                && testEachSlot(pContainer, inputItems.get(1))
                && testEachSlot(pContainer, inputItems.get(2));
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

    public static class Type implements RecipeType<CombinerRecipe> {
        public static final Type INSTANCE = new Type();
    }

    public static class Serializer implements RecipeSerializer<CombinerRecipe> {
        public static final Serializer INSTANCE = new Serializer();

        @Override
        public CombinerRecipe fromJson(ResourceLocation pRecipeId, JsonObject pSerializedRecipe) {
            String group = GsonHelper.getAsString(pSerializedRecipe, "group", "");

            DABookCategory category = DABookCategory.CODEC.byName(GsonHelper.getAsString(pSerializedRecipe, "category", null), DABookCategory.UNKNOWN);

            JsonArray ingredients = GsonHelper.getAsJsonArray(pSerializedRecipe, "ingredients");
            NonNullList<Ingredient> inputs = NonNullList.withSize(3, Ingredient.EMPTY);

            for(int i = 0; i < inputs.size(); i++) {
                inputs.set(i, Ingredient.fromJson(ingredients.get(i)));
            }

            ItemStack output = ShapedRecipe.itemStackFromJson(GsonHelper.getAsJsonObject(pSerializedRecipe, "output"));

            float experience = GsonHelper.getAsFloat(pSerializedRecipe, "experience", 0.0f);
            int processingTime = GsonHelper.getAsInt(pSerializedRecipe, "processing_time", 200);

            int amplifier = GsonHelper.getAsInt(pSerializedRecipe, "amplifier", 0);
            int time = GsonHelper.getAsInt(pSerializedRecipe, "time", 14400);
            String effect = GsonHelper.getAsString(pSerializedRecipe, "effect", "");

            CompoundTag tag = new CompoundTag();
            tag.putInt("amplifier", amplifier);
            tag.putInt("time", time);
            tag.putString("effect", effect);

            output.setTag(tag);

            return new CombinerRecipe(pRecipeId, group, category, inputs, output, experience, processingTime);
        }

        @Override
        public @Nullable CombinerRecipe fromNetwork(ResourceLocation pRecipeId, FriendlyByteBuf buffer) {
            String group = buffer.readUtf();
            DABookCategory daBookCategory = buffer.readEnum(DABookCategory.class);

            NonNullList<Ingredient> inputs = NonNullList.withSize(buffer.readInt(), Ingredient.EMPTY);
            inputs.replaceAll(ignored -> Ingredient.fromNetwork(buffer));

            ItemStack output = buffer.readItem();

            float experience = buffer.readFloat();
            int processingTime = buffer.readInt();

            return new CombinerRecipe(pRecipeId, group, daBookCategory, inputs, output, experience, processingTime);
        }

        @Override
        public void toNetwork(FriendlyByteBuf buffer, CombinerRecipe recipe) {
            buffer.writeUtf(recipe.getGroup());
            buffer.writeEnum(recipe.daCategory());

            buffer.writeInt(recipe.inputItems.size());
            for (Ingredient ingredient : recipe.getIngredients()) {
                ingredient.toNetwork(buffer);
            }

            buffer.writeItemStack(recipe.getResultItem(null), false);
            buffer.writeFloat(recipe.getExperience());
            buffer.writeInt(recipe.getProcessingTime());
        }
    }
}
