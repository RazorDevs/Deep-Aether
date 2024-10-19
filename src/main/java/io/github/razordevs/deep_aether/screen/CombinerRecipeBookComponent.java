package io.github.razordevs.deep_aether.screen;

import net.minecraft.client.gui.components.WidgetSprites;
import net.minecraft.client.gui.screens.recipebook.RecipeBookComponent;
import net.minecraft.core.NonNullList;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.RecipeHolder;

import javax.annotation.Nullable;
import java.util.Iterator;
import java.util.List;

public class CombinerRecipeBookComponent extends RecipeBookComponent {
    private static final Component FILTER_NAME = Component.translatable("gui.deep_aether.recipebook.toggleRecipes.combinable");
    private static final WidgetSprites FILTER_SPRITES = new WidgetSprites(
            ResourceLocation.fromNamespaceAndPath("recipe_book/furnace_filter_enabled"),
            ResourceLocation.fromNamespaceAndPath("recipe_book/furnace_filter_disabled"),
            ResourceLocation.fromNamespaceAndPath("recipe_book/furnace_filter_enabled_highlighted"),
            ResourceLocation.fromNamespaceAndPath("recipe_book/furnace_filter_disabled_highlighted"));

    @Override
    protected Component getRecipeFilterName() {
        return FILTER_NAME;
    }

    @Override
    protected void initFilterButtonTextures() {
        this.filterButton.initTextureValues(FILTER_SPRITES);
    }

    public void slotClicked(@Nullable Slot pSlot) {
        super.slotClicked(pSlot);
        if (pSlot != null && pSlot.index < this.menu.getSize()) {
            this.ghostRecipe.clear();
        }
    }

    /*@Override
    public void setupGhostRecipe(RecipeHolder<?> recipe, List<Slot> slots) {
        this.ghostRecipe.setRecipe(recipe);
        Slot outputSlot = slots.get(3);
        this.ghostRecipe.addIngredient(Ingredient.of(recipe.value().getResultItem(this.minecraft.level.registryAccess())), outputSlot.x, outputSlot.y);
        addIngredient(recipe, slots, 3);
        addIngredient(recipe, slots, 0);
        addIngredient(recipe, slots, 1);
        addIngredient(recipe, slots, 2);
    }

    private void addIngredient(RecipeHolder<?> recipe, List<Slot> slots, int i) {
        Ingredient ingredient = recipe.value().getIngredients().get(i);
        if (!ingredient.isEmpty()) {
            Slot ingredientSlot = slots.get(i);
            this.ghostRecipe.addIngredient(ingredient, ingredientSlot.x, ingredientSlot.y);
        }
    }*/

    public void setupGhostRecipe(RecipeHolder<?> pRecipe, List<Slot> pSlots) {
        ItemStack itemstack = pRecipe.value().getResultItem(this.minecraft.level.registryAccess());
        this.ghostRecipe.setRecipe(pRecipe);
        this.ghostRecipe.addIngredient(Ingredient.of(new ItemStack[]{itemstack}), ((Slot)pSlots.get(2)).x, ((Slot)pSlots.get(2)).y);
        NonNullList<Ingredient> nonnulllist = pRecipe.value().getIngredients();
        Iterator<Ingredient> iterator = nonnulllist.iterator();

        for(int i = 0; i < 2; ++i) {
            if (!iterator.hasNext()) {
                return;
            }

            Ingredient ingredient = (Ingredient)iterator.next();
            if (!ingredient.isEmpty()) {
                Slot slot1 = (Slot)pSlots.get(i);
                this.ghostRecipe.addIngredient(ingredient, slot1.x, slot1.y);
            }
        }

    }

}
