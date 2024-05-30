package teamrazor.deepaether.item.compat.lost_content;

import net.minecraft.world.item.ItemStack;
import teamrazor.deepaether.DeepAetherConfig;

public class SkyjadeShieldItem extends LCDAShieldItem {
    public SkyjadeShieldItem(Properties pProperties) {
        super(pProperties);
    }

    @Override
    public boolean isEnchantable(ItemStack itemStack) {
        return DeepAetherConfig.COMMON.skyjade_enchant.get();
    }

    @Override
    public boolean isBookEnchantable(ItemStack stack, ItemStack book) {
        return DeepAetherConfig.COMMON.skyjade_enchant.get();
    }
}
