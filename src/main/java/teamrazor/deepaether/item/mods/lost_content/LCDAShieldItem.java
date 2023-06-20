package teamrazor.deepaether.item.mods.lost_content;

import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.ShieldItem;
import teamrazor.deepaether.init.DAItems;

public class LCDAShieldItem extends ShieldItem {
    public LCDAShieldItem(Properties pProperties) {
        super(pProperties);
    }

    @Override
    public boolean isValidRepairItem(ItemStack pStack, ItemStack pRepairCandidate) {
        if (this.equals(DAItems.SKYJADE_SHIELD.get())) {
            return pRepairCandidate.is(DAItems.SKYJADE.get());
        }
        if (this.equals(DAItems.STRATUS_SHIELD.get())) {
            return pRepairCandidate.is(DAItems.STRATUS_INGOT.get());
        }
        return super.isValidRepairItem(pStack, pRepairCandidate);
    }
}
