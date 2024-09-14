package teamrazor.deepaether.screen;

import net.minecraft.client.gui.components.WidgetSprites;
import net.minecraft.client.gui.screens.recipebook.RecipeBookComponent;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.RecipeHolder;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;

import javax.annotation.Nullable;
import java.util.List;

@OnlyIn(Dist.CLIENT)
public class CombinerRecipeBookComponent extends RecipeBookComponent {
    private static final Component FILTER_NAME = Component.translatable("gui.deep_aether.recipebook.toggleRecipes.combinable");

    private static final WidgetSprites FILTER_SPRITES = new WidgetSprites(
            new ResourceLocation("recipe_book/furnace_filter_enabled"),
            new ResourceLocation("recipe_book/furnace_filter_disabled"),
            new ResourceLocation("recipe_book/furnace_filter_enabled_highlighted"),
            new ResourceLocation("recipe_book/furnace_filter_disabled_highlighted"));

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

    @Override
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
    }

}