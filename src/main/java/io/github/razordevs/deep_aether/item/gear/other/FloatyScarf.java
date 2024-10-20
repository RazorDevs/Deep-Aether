package io.github.razordevs.deep_aether.item.gear.other;

import com.aetherteam.aether.item.accessories.cape.CapeItem;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;

import java.util.List;

public class FloatyScarf extends CapeItem implements FlawlessDrop {
    public FloatyScarf(String capeLocation, Properties properties) {
        super(capeLocation, properties);
    }


    int i = 0;
    @Override
    public void appendHoverText(ItemStack stack, TooltipContext context, List<Component> tooltipComponents, TooltipFlag tooltipFlag) {
        flawlessComponent(tooltipComponents, i);
        i = i < 80 ? i + 1 : 0;
    }
}
