package teamrazor.deepaether.item.mods.lost_content;

import net.minecraft.world.item.ItemStack;
import teamrazor.deepaether.DAConfig;

public class SkyjadeShieldItem extends LCDAShieldItem {
    public SkyjadeShieldItem(Properties pProperties) {
        super(pProperties);
    }

    @Override
    public boolean isEnchantable(ItemStack itemStack) {
        return DAConfig.COMMON.skyjade_enchant.get();
    }

    @Override
    public boolean isBookEnchantable(ItemStack stack, ItemStack book) {
        return DAConfig.COMMON.skyjade_enchant.get();
    }
}
