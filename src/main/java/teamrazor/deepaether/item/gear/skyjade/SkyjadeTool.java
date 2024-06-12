package teamrazor.deepaether.item.gear.skyjade;
import net.minecraft.world.item.ItemStack;

public interface SkyjadeTool {
    default float decreaseSpeed(ItemStack stack, float speed) {
        return (float) (speed / (2.0 * ((double) stack.getDamageValue()) / ((double) stack.getMaxDamage()) + 0.5));
    }
}
