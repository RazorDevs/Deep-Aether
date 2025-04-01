package teamrazor.deepaether.recipe.jei;

import mezz.jei.api.ingredients.subtypes.IIngredientSubtypeInterpreter;
import mezz.jei.api.ingredients.subtypes.UidContext;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.item.ItemStack;
import teamrazor.deepaether.item.moa_food.FodderItem;

public class MoaFodderSubtypeInterpreter implements IIngredientSubtypeInterpreter<ItemStack> {
    public static final MoaFodderSubtypeInterpreter INSTANCE = new MoaFodderSubtypeInterpreter();

    @Override
    public String apply(ItemStack itemStack, UidContext context) {
        if (!itemStack.hasTag()) {
            return IIngredientSubtypeInterpreter.NONE;
        }

        StringBuilder stringBuilder = new StringBuilder(itemStack.getDisplayName().getString());
        MobEffectInstance effect = FodderItem.getMobEffect(itemStack);
        stringBuilder.append(";").append(effect);

        return stringBuilder.toString();
    }
}