package teamrazor.deepaether.item.mods.lost_content;

import com.legacy.lost_aether.item.LCShieldItem;
import net.minecraft.world.item.ItemStack;
import teamrazor.deepaether.init.DAItems;

public class LCDAShieldItem extends LCShieldItem {
    public LCDAShieldItem(Properties pProperties) {
        super(pProperties);
    }

    @Override
    public boolean isValidRepairItem(ItemStack pStack, ItemStack pRepairCandidate) {
        if (this.equals(DAItems.SKYJADE_SHIELD.get())) {
            return pRepairCandidate.is(DAItems.SKYJADE.get());
        }
        if (this.equals(DAItems.CLOUDIUM_SHIELD.get())) {
            return pRepairCandidate.is(DAItems.CLOUDIUM_INGOT.get());
        }
        return super.isValidRepairItem(pStack, pRepairCandidate);
    }
}
