package io.github.razordevs.deep_aether.recipe.jei;

import io.github.razordevs.deep_aether.item.misc.FodderItem;
import mezz.jei.api.ingredients.subtypes.ISubtypeInterpreter;
import mezz.jei.api.ingredients.subtypes.UidContext;
import net.minecraft.world.item.ItemStack;
import org.jetbrains.annotations.Nullable;

public class MoaFodderSubtypeInterpreter implements ISubtypeInterpreter<ItemStack> {
    public static final MoaFodderSubtypeInterpreter INSTANCE = new MoaFodderSubtypeInterpreter();

    @Override
    public @Nullable Object getSubtypeData(ItemStack itemStack, UidContext uidContext) {
        return FodderItem.getFodderEffect(itemStack);
    }

    @Override
    public String getLegacyStringSubtypeInfo(ItemStack itemStack, UidContext uidContext) {
        return itemStack.getHoverName().getString();
    }
}
