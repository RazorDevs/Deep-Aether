package teamrazor.deepaether.item.gear.skyjade;

import com.aetherteam.aether.item.accessories.ring.RingItem;
import net.minecraft.world.item.ItemStack;
import teamrazor.deepaether.DeepAetherConfig;
import teamrazor.deepaether.init.DAItems;
import teamrazor.deepaether.init.DASounds;


public class SkyjadeRingItem extends RingItem implements SkyjadeAccessory {
    public SkyjadeRingItem(Properties properties) {
        super(DASounds.ITEM_ACCESSORY_EQUIP_SKYJADE_RING, properties);
    }

    @Override
    public boolean isValidRepairItem(ItemStack repairItem, ItemStack repairMaterial) {
        return repairMaterial.is(DAItems.SKYJADE.get());
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
