package teamrazor.deepaether.init;

import net.minecraft.tags.BlockTags;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraftforge.common.ForgeTier;

public class DeepAetherModTiers {
    public static final ForgeTier SKYJADE = new ForgeTier(2, 150, 6.0F, 2.0F, 22,
            BlockTags.NEEDS_IRON_TOOL, () -> Ingredient.of(new ItemStack(DeepAetherModItems.SKYJADE.get())));
}
