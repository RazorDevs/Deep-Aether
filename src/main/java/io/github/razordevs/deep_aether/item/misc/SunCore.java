package io.github.razordevs.deep_aether.item.misc;

import net.minecraft.network.chat.Component;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import io.github.razordevs.deep_aether.item.gear.other.FlawlessDrop;
import top.theillusivec4.curios.api.type.capability.ICurioItem;

import java.util.List;

public class SunCore extends Item implements ICurioItem, FlawlessDrop {
    public SunCore(Properties properties) {
        super(properties);
    }

    int i = 0;
    @Override
    public void appendHoverText(@NotNull ItemStack itemStack, @Nullable Level level, @NotNull List<Component> components, @NotNull TooltipFlag tooltipFlag) {
        flawlessComponent(components, i);
        i = i < 80 ? i + 1 : 0;
        super.appendHoverText(itemStack, level, components, tooltipFlag);
    }
}
