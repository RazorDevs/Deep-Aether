package teamrazor.deepaether.init;

import com.google.common.base.Supplier;
import com.google.common.base.Suppliers;
import com.google.common.collect.ImmutableList;
import net.minecraft.client.RecipeBookCategories;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.neoforged.neoforge.client.event.RegisterRecipeBookCategoriesEvent;
import teamrazor.deepaether.recipe.DARecipe;

public class DARecipeCategories {

    public static final Supplier<RecipeBookCategories> COMBINATION_SEARCH = Suppliers.memoize(() -> RecipeBookCategories.create("COMBINATION_SEARCH", new ItemStack(Items.COMPASS)));
    public static final Supplier<RecipeBookCategories> COMBINATION_MISC = Suppliers.memoize(() -> RecipeBookCategories.create("COMBINATION_MISC", new ItemStack(DAItems.ANTIDOTE.get())));

    /**
     * Registers the mod's categories to be used in-game, along with functions to sort items.
     * To add sub-categories to be used by the search, use addAggregateCategories with the
     * search category as the first parameter.
     *
     * @see AetherClient#eventSetup()
     */
    public static void registerRecipeCategories(RegisterRecipeBookCategoriesEvent event) {
        // Combination
        event.registerBookCategories(DARecipeBookTypes.COMBINER, ImmutableList.of(COMBINATION_SEARCH.get(), COMBINATION_MISC.get()));
        event.registerAggregateCategory(COMBINATION_SEARCH.get(), ImmutableList.of(COMBINATION_MISC.get()));
        event.registerRecipeCategoryFinder(DARecipe.COMBINER_RECIPE.get(), recipe -> COMBINATION_MISC.get());
    }
}
