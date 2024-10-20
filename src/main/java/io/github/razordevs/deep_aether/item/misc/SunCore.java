package io.github.razordevs.deep_aether.item.misc;

import io.wispforest.accessories.api.Accessory;
import io.wispforest.accessories.api.slot.SlotType;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import io.github.razordevs.deep_aether.item.gear.other.FlawlessDrop;

import java.util.List;

public class SunCore extends Item implements FlawlessDrop, Accessory {
    public SunCore(Properties properties) {
        super(properties);
    }

    int i = 0;
    @Override
    public void getAttributesTooltip(ItemStack stack, SlotType type, List<Component> tooltips, TooltipContext tooltipContext, TooltipFlag tooltipType) {
        flawlessComponent(tooltips, i);
        i = i < 80 ? i + 1 : 0;
    }
}
