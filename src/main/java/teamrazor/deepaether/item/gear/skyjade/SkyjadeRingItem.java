package teamrazor.deepaether.item.gear.skyjade;

import com.aetherteam.aether.client.AetherSoundEvents;
import com.aetherteam.aether.item.accessories.ring.RingItem;
import net.minecraft.world.item.ItemStack;
import teamrazor.deepaether.DAConfig;
import teamrazor.deepaether.init.DAItems;


public class SkyjadeRingItem extends RingItem implements SkyjadeAccessory {
    public SkyjadeRingItem(Properties properties) {
        super(AetherSoundEvents.ITEM_ACCESSORY_EQUIP_ZANITE_RING, properties);
    }

    @Override
    public boolean isValidRepairItem(ItemStack repairItem, ItemStack repairMaterial) {
        return repairMaterial.is(DAItems.SKYJADE.get());
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
