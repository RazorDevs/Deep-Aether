package teamrazor.deepaether.tags;
import net.minecraft.world.item.ItemStack;

public interface SkyjadeTool {
        static float decreaseSpeed(ItemStack stack, float speed) {
            if (stack.is(DeepAetherTags.Items.SKYJADE_TOOLS)) {
                speed /= (2.0F * ((float) stack.getDamageValue()) / ((float) stack.getMaxDamage()) + 0.5F);
            }
            return speed;
        }
    }
