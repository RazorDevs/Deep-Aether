package io.github.razordevs.deep_aether;

import io.github.razordevs.deep_aether.init.DAItems;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;

import java.util.List;
import java.util.function.Supplier;

@SuppressWarnings("unused")
public class DAEnumExtensions {
    public static Object combiningSearchIcon(int idx, Class<?> type) {
        return type.cast(switch (idx) {
            case 0 -> (Supplier<List<ItemStack>>) () -> List.of(new ItemStack(Items.COMPASS));
            default -> throw new IllegalArgumentException("Unexpected parameter index: " + idx);
        });
    }

    public static Object combiningFoodIcon(int idx, Class<?> type) {
        return type.cast(switch (idx) {
            case 0 -> (Supplier<List<ItemStack>>) () -> List.of(new ItemStack(DAItems.MOA_FODDER.get()));
            default -> throw new IllegalArgumentException("Unexpected parameter index: " + idx);
        });
    }

    public static Object combiningMiscIcon(int idx, Class<?> type) {
        return type.cast(switch (idx) {
            case 0 -> (Supplier<List<ItemStack>>) () -> List.of(new ItemStack(DAItems.ANTIDOTE.get()));
            default -> throw new IllegalArgumentException("Unexpected parameter index: " + idx);
        });
    }

    private static String prefix(String id) {
        return DeepAether.MODID + ":" + id;
    }
}
