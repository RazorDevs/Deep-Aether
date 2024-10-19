package io.github.razordevs.deepaether.init;

import com.google.common.base.Supplier;
import com.google.common.base.Suppliers;
import com.google.common.collect.ImmutableList;
import io.github.razordevs.deepaether.deepaether.recipe.DARecipeTypes;
import net.minecraft.client.RecipeBookCategories;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.neoforged.neoforge.client.event.RegisterRecipeBookCategoriesEvent;

public class DARecipeCategories {

    public static final Supplier<RecipeBookCategories> COMBINEABLE_SEARCH = Suppliers.memoize(() -> RecipeBookCategories.create("COMBINEABLE_SEARCH", new ItemStack(Items.COMPASS)));
    public static final Supplier<RecipeBookCategories> COMBINEABLE_FODDER = Suppliers.memoize(() -> RecipeBookCategories.create("COMBINEABLE_FODDER", new ItemStack(DAItems.FIRE_RES_FODDER.get())));
    public static final Supplier<RecipeBookCategories> COMBINEABLE_MISC = Suppliers.memoize(() -> RecipeBookCategories.create("COMBINEABLE_MISC", new ItemStack(DAItems.ANTIDOTE.get())));

    /**
     * Registers the mod's categories to be used in-game, along with functions to sort items.
     * To add sub-categories to be used by the search, use addAggregateCategories with the
     * search category as the first parameter.
     */
    public static void registerRecipeCategories(RegisterRecipeBookCategoriesEvent event) {
        // Combination
        event.registerBookCategories(DARecipeBookTypes.COMBINER, ImmutableList.of(COMBINEABLE_SEARCH.get(), COMBINEABLE_FODDER.get(), COMBINEABLE_MISC.get()));
        event.registerAggregateCategory(COMBINEABLE_SEARCH.get(), ImmutableList.of(COMBINEABLE_FODDER.get(), COMBINEABLE_MISC.get()));
        event.registerRecipeCategoryFinder(DARecipeTypes.COMBINING.get(), recipe -> COMBINEABLE_MISC.get());
    }
}
