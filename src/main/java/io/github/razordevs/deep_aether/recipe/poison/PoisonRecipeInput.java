package io.github.razordevs.deep_aether.recipe.poison;

import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.RecipeInput;

import java.util.List;

public record PoisonRecipeInput(ItemStack item) implements RecipeInput {

    @Override
    public ItemStack item() {
        return item;
    }

    @Override
    public ItemStack getItem(int index) {
        if (index == 0) {
            return item;
        }
        else throw new IllegalArgumentException("Recipe does not contain slot " + index);
    }

    @Override
    public int size() {
        return 1;
    }
}