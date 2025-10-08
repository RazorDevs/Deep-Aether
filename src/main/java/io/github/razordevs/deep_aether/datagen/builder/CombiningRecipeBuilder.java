package io.github.razordevs.deep_aether.datagen.builder;

import io.github.razordevs.deep_aether.item.component.DADataComponentTypes;
import io.github.razordevs.deep_aether.item.component.MoaFodder;
import io.github.razordevs.deep_aether.recipe.DABookCategory;
import io.github.razordevs.deep_aether.recipe.combiner.CombinerRecipe;
import net.minecraft.advancements.Advancement;
import net.minecraft.advancements.AdvancementRequirements;
import net.minecraft.advancements.AdvancementRewards;
import net.minecraft.advancements.Criterion;
import net.minecraft.advancements.critereon.RecipeUnlockedTrigger;
import net.minecraft.core.NonNullList;
import net.minecraft.data.recipes.RecipeBuilder;
import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.ItemLike;
import org.jetbrains.annotations.Nullable;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;

public class CombiningRecipeBuilder implements RecipeBuilder {
    private final DABookCategory category;
    private final ItemStack resultStack;
    private final NonNullList<Ingredient> ingredients;
    private final Map<String, Criterion<?>> criteria;
    @Nullable
    private String group;
    private final float experience;
    private final int processingTime;

    public CombiningRecipeBuilder(DABookCategory category, ItemLike itemLike, int count, float experience, int processingTime) {
        this(category, new ItemStack(itemLike, count), experience, processingTime);
    }

    public CombiningRecipeBuilder(DABookCategory category, ItemStack result, float experience, int processingTime) {
        this.ingredients = NonNullList.create();
        this.criteria = new LinkedHashMap<>();
        this.category = category;
        this.resultStack = result;
        this.experience = experience;
        this.processingTime = processingTime;
    }

    public static CombiningRecipeBuilder combining(DABookCategory category, ItemLike result, float experience, int processingTime) {
        return new CombiningRecipeBuilder(category, result, 1, experience, processingTime);
    }

    public static CombiningRecipeBuilder combining(DABookCategory category, ItemStack result, float experience, int processingTime) {
        return new CombiningRecipeBuilder(category, result, experience, processingTime);
    }

    @SuppressWarnings("unused")
    public CombiningRecipeBuilder requires(TagKey<Item> item) {
        return this.requires(Ingredient.of(item));
    }

    public CombiningRecipeBuilder requires(ItemLike item) {
        return this.requires(item, 1);
    }

    public CombiningRecipeBuilder requires(ItemLike item, int count) {
        for(int i = 0; i < count; ++i) {
            this.requires(Ingredient.of(item));
        }
        return this;
    }

    public CombiningRecipeBuilder requires(Ingredient item) {
        return this.requires(item, 1);
    }

    public CombiningRecipeBuilder requires(Ingredient item, int count) {
        for(int i = 0; i < count; ++i) {
            this.ingredients.add(item);
        }

        return this;
    }

    @Override
    public CombiningRecipeBuilder unlockedBy(String string, Criterion<?> criterion) {
        this.criteria.put(string, criterion);
        return this;
    }

    @Override
    public CombiningRecipeBuilder group(@Nullable String group) {
        this.group = group;
        return this;
    }

    @Override
    public Item getResult() {
        return resultStack.getItem();
    }

    public void save(RecipeOutput output, ResourceLocation location) {
        MoaFodder fodder = resultStack.get(DADataComponentTypes.MOA_FODDER);

        if(location.toString().contains("moa_fodder") && fodder != null)
                location = location.withSuffix("_" + fodder.effect().getDescriptionId().replaceAll("effect.", ""));

        this.ensureValid(location);
        Advancement.Builder advancement$builder = output.advancement().addCriterion("has_the_recipe", RecipeUnlockedTrigger.unlocked(location)).rewards(AdvancementRewards.Builder.recipe(location)).requirements(AdvancementRequirements.Strategy.OR);
        this.criteria.forEach(advancement$builder::addCriterion);
        CombinerRecipe recipe = new CombinerRecipe(Objects.requireNonNullElse(this.group, ""), this.category, this.ingredients, this.resultStack, this.experience, this.processingTime);
        output.accept(location, recipe, advancement$builder.build(location.withPrefix("recipe/")));
    }

    private void ensureValid(ResourceLocation location) {
        if (this.criteria.isEmpty()) {
            throw new IllegalStateException("No way of obtaining recipe " + location);
        }
    }
}
