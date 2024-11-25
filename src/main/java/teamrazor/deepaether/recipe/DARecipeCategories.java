package teamrazor.deepaether.recipe;

import com.google.common.base.Suppliers;
import com.google.common.collect.ImmutableList;
import net.minecraft.client.RecipeBookCategories;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.RegisterRecipeBookCategoriesEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import teamrazor.deepaether.DeepAether;
import teamrazor.deepaether.init.DAItems;
import teamrazor.deepaether.recipe.combiner.CombinerRecipe;

import java.util.function.Supplier;

@Mod.EventBusSubscriber(modid = DeepAether.MODID, value = Dist.CLIENT, bus = Mod.EventBusSubscriber.Bus.MOD)
public class DARecipeCategories {
    public static final Supplier<RecipeBookCategories> COMBINEABLE_SEARCH = Suppliers.memoize(() -> RecipeBookCategories.create("COMBINEABLE_SEARCH", new ItemStack(Items.COMPASS)));
    public static final Supplier<RecipeBookCategories> COMBINEABLE_FODDER = Suppliers.memoize(() -> RecipeBookCategories.create("COMBINEABLE_FODDER", new ItemStack(DAItems.MOA_FODDER.get())));
    public static final Supplier<RecipeBookCategories> COMBINEABLE_MISC = Suppliers.memoize(() -> RecipeBookCategories.create("COMBINEABLE_MISC", new ItemStack(DAItems.ANTIDOTE.get())));

    /**
     * Registers the mod's categories to be used in-game, along with functions to sort items.
     * To add sub-categories to be used by the search, use addAggregateCategories with the
     * search category as the first parameter.
     */
    @SubscribeEvent
    public static void registerRecipeCategories(RegisterRecipeBookCategoriesEvent event) {
        // Combination
        event.registerBookCategories(DARecipeBookTypes.COMBINER, ImmutableList.of(COMBINEABLE_SEARCH.get(), COMBINEABLE_FODDER.get(), COMBINEABLE_MISC.get()));
        event.registerAggregateCategory(COMBINEABLE_SEARCH.get(), ImmutableList.of(COMBINEABLE_FODDER.get(), COMBINEABLE_MISC.get()));

        event.registerRecipeCategoryFinder(DARecipeTypes.COMBINER_RECIPE.get(), recipe -> {
            if(recipe instanceof CombinerRecipe value){
                return value.daCategory() == DABookCategory.COMBINEABLE_FODDER ?
                        COMBINEABLE_FODDER.get() :
                        COMBINEABLE_MISC.get();
            }
            return COMBINEABLE_MISC.get();
        });
    }
}
