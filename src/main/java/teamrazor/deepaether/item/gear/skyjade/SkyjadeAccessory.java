package teamrazor.deepaether.item.gear.skyjade;

import net.minecraft.world.item.ItemStack;

public interface SkyjadeAccessory {

    static float handleMiningSpeed(float speed, ItemStack stack) {
        return speed * (((float) stack.getMaxDamage()) / (((float) stack.getDamageValue())));
    }
}
