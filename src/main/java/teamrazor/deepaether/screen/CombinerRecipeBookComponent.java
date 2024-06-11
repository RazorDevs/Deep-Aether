package teamrazor.deepaether.screen;

import net.minecraft.client.gui.components.WidgetSprites;
import net.minecraft.client.gui.screens.recipebook.RecipeBookComponent;
import net.minecraft.core.NonNullList;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.RecipeHolder;

import javax.annotation.Nullable;
import java.util.Iterator;
import java.util.List;

public class CombinerRecipeBookComponent extends RecipeBookComponent {
    private static final WidgetSprites FILTER_SPRITES = new WidgetSprites(
            new ResourceLocation("recipe_book/furnace_filter_enabled"),
            new ResourceLocation("recipe_book/furnace_filter_disabled"),
            new ResourceLocation("recipe_book/furnace_filter_enabled_highlighted"),
            new ResourceLocation("recipe_book/furnace_filter_disabled_highlighted")
    );

    protected void initFilterButtonTextures() {
        this.filterButton.initTextureValues(FILTER_SPRITES);
    }

    public void slotClicked(@Nullable Slot pSlot) {
        super.slotClicked(pSlot);
        if (pSlot != null && pSlot.index < this.menu.getSize()) {
            this.ghostRecipe.clear();
        }
    }

    public void setupGhostRecipe(RecipeHolder<?> pRecipe, List<Slot> pSlots) {
        ItemStack itemstack = pRecipe.value().getResultItem(this.minecraft.level.registryAccess());
        this.ghostRecipe.setRecipe(pRecipe);
        this.ghostRecipe.addIngredient(Ingredient.of(new ItemStack[]{itemstack}), ((Slot)pSlots.get(3)).x, ((Slot)pSlots.get(3)).y);
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
