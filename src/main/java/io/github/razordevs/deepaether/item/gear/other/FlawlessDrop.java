package io.github.razordevs.deepaether.deepaether.item.gear.other;

import net.minecraft.network.chat.Component;
import net.minecraft.world.item.ItemStack;

import java.util.List;

public interface FlawlessDrop {
    default void flawlessComponent(List<Component> tagTooltips, int i) {
        if(i > 70)
            printComponent(tagTooltips, 1);
        else if(i > 60)
            printComponent(tagTooltips, 2);
        else if(i > 50)
            printComponent(tagTooltips, 3);
        else if(i > 40)
            printComponent(tagTooltips, 4);
        else if(i > 30)
            printComponent(tagTooltips, 5);
        else if(i > 20)
            printComponent(tagTooltips, 6);
        else if(i > 10)
            printComponent(tagTooltips, 7);
        else
            printComponent(tagTooltips, 8);
    }

    private void printComponent(List<Component> tagTooltips, int i){
        tagTooltips.add(Component.translatable("gui.deep_aether.flawless_tier_" + i));
    }
}
