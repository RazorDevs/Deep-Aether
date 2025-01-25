package teamrazor.deepaether.recipe.combiner;

import net.minecraft.recipebook.ServerPlaceRecipe;
import net.minecraft.world.Container;
import net.minecraft.world.inventory.RecipeBookMenu;
import net.minecraft.world.item.crafting.Recipe;

import java.util.Iterator;
public class CombinerServerPlaceRecipe<C extends Container> extends ServerPlaceRecipe<C> {
    public CombinerServerPlaceRecipe(RecipeBookMenu<C> recipeBookMenu) {
        super(recipeBookMenu);
    }

    @Override
    public void placeRecipe(int width, int height, int result, Recipe<?> p_301225_, Iterator<Integer> iterator, int idk) {
        int k1 = 0;
        if (k1 == result) {
            k1++;
        }
        for (int slot = 0; slot < 3; slot++) {
            if (!iterator.hasNext()) {
                return;
            }
            this.addItemToSlot(iterator, k1, idk, slot, 0);
            k1++;
        }
    }
}