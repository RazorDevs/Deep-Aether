package io.github.razordevs.deep_aether.init;

import com.google.common.base.Supplier;
import com.google.common.base.Suppliers;
import com.google.common.collect.ImmutableList;
import io.github.razordevs.deep_aether.recipe.DARecipeTypes;
import net.minecraft.client.RecipeBookCategories;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.neoforged.fml.common.asm.enumextension.EnumProxy;
import net.neoforged.neoforge.client.event.RegisterRecipeBookCategoriesEvent;

import java.util.ArrayList;
import java.util.List;

public class DARecipeCategories {

    public static final EnumProxy<RecipeBookCategories> DEEP_AETHER_COMBINEABLE_SEARCH = new EnumProxy<>(
            RecipeBookCategories.class, (Supplier<List<ItemStack>>) ()->new ArrayList<>() {{
        new ItemStack(Items.COMPASS);
    }});
    public static final EnumProxy<RecipeBookCategories> DEEP_AETHER_COMBINEABLE_FODDER = new EnumProxy<>(
            RecipeBookCategories.class, (Supplier<List<ItemStack>>) ()->new ArrayList<>() {{
        new ItemStack(DAItems.FIRE_RES_FODDER.get());
    }});

    public static final EnumProxy<RecipeBookCategories> DEEP_AETHER_COMBINEABLE_MISC = new EnumProxy<>(
            RecipeBookCategories.class, (Supplier<List<ItemStack>>) ()->new ArrayList<>() {{
        new ItemStack(DAItems.ANTIDOTE.get());
    }});

    /**
     * Registers the mod's categories to be used in-game, along with functions to sort items.
     * To add sub-categories to be used by the search, use addAggregateCategories with the
     * search category as the first parameter.
     */
    public static void registerRecipeCategories(RegisterRecipeBookCategoriesEvent event) {
        // Combination
        event.registerBookCategories(DARecipeBookTypes.DEEP_AETHER_COMBINER.getValue(), ImmutableList.of(DEEP_AETHER_COMBINEABLE_SEARCH.getValue(), DEEP_AETHER_COMBINEABLE_FODDER.getValue(), DEEP_AETHER_COMBINEABLE_MISC.getValue()));
        event.registerAggregateCategory(DEEP_AETHER_COMBINEABLE_SEARCH.getValue(), ImmutableList.of(DEEP_AETHER_COMBINEABLE_FODDER.getValue(), DEEP_AETHER_COMBINEABLE_MISC.getValue()));
        event.registerRecipeCategoryFinder(DARecipeTypes.COMBINING.get(), recipe -> DEEP_AETHER_COMBINEABLE_MISC.getValue());
    }
}
