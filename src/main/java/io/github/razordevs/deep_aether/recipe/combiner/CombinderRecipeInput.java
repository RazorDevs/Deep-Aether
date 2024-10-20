package io.github.razordevs.deep_aether.recipe.combiner;

import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.RecipeInput;

import java.util.List;

public record CombinderRecipeInput(List<ItemStack> items) implements RecipeInput {

    @Override
    public List<ItemStack> items() {
        return items;
    }

    @Override
    public ItemStack getItem(int index) {
        switch (index) {
            case 0 -> {return items.getFirst();}
            case 1 -> {return items.get(1);}
            case 2 ->  {return items.get(2);}
            default -> throw new IllegalArgumentException("Recipe does not contain slot " + index);
        }
    }

    @Override
    public int size() {
        return 3;
    }
}