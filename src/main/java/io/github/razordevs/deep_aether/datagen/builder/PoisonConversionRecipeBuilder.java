package io.github.razordevs.deep_aether.datagen.builder;

import io.github.razordevs.deep_aether.recipe.poison.PoisonRecipe;
import net.minecraft.advancements.Advancement;
import net.minecraft.advancements.AdvancementRequirements;
import net.minecraft.advancements.AdvancementRewards;
import net.minecraft.advancements.Criterion;
import net.minecraft.advancements.critereon.RecipeUnlockedTrigger;
import net.minecraft.data.recipes.RecipeBuilder;
import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.ItemLike;
import org.jetbrains.annotations.Nullable;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;

public class PoisonConversionRecipeBuilder implements RecipeBuilder {
    private final ItemStack resultStack;
    private final Ingredient ingredient;
    private final Map<String, Criterion<?>> criteria;
    @Nullable
    private String group;

    public PoisonConversionRecipeBuilder(Ingredient ingredient, ItemStack resultStack) {
        this.resultStack = resultStack;
        this.ingredient = ingredient;
        this.criteria = new LinkedHashMap<>();
    }

    @Override
    public RecipeBuilder unlockedBy(String s, Criterion<?> criterion) {
        this.criteria.put(s, criterion);
        return this;
    }

    @Override
    public RecipeBuilder group(@Nullable String group) {
        this.group = group;
        return this;
    }

    @Override
    public Item getResult() {
        return resultStack.getItem();
    }

    public static PoisonConversionRecipeBuilder conversion(ItemLike ingredient, ItemLike result) {
        return conversion(Ingredient.of(ingredient), new ItemStack(result.asItem()));
    }

    public static PoisonConversionRecipeBuilder conversion(Ingredient ingredient, ItemStack result) {
        return new PoisonConversionRecipeBuilder(ingredient, result);
    }

    @Override
    public void save(RecipeOutput output, ResourceLocation location) {
        this.ensureValid(location);
        Advancement.Builder advancement$builder = output.advancement().addCriterion("has_the_recipe", RecipeUnlockedTrigger.unlocked(location)).rewards(AdvancementRewards.Builder.recipe(location)).requirements(AdvancementRequirements.Strategy.OR);
        this.criteria.forEach(advancement$builder::addCriterion);
        PoisonRecipe recipe = new PoisonRecipe(Objects.requireNonNullElse(this.group, ""), this.ingredient, this.resultStack);
        output.accept(location, recipe, advancement$builder.build(location.withPrefix("recipe/")));
    }

    private void ensureValid(ResourceLocation location) {
        if (this.criteria.isEmpty()) {
            throw new IllegalStateException("No way of obtaining recipe " + location);
        }
    }
}
