package io.github.razordevs.deep_aether.init;

import net.minecraft.world.inventory.RecipeBookType;
import net.neoforged.fml.common.asm.enumextension.EnumProxy;

public class DARecipeBookTypes {
    public static final EnumProxy<RecipeBookType> DEEP_AETHER_COMBINER = new EnumProxy<>(
            RecipeBookType.class);
}
