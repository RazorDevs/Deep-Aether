package teamrazor.deepaether.item.gear.other;

import net.minecraft.network.chat.Component;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.SimpleFoiledItem;
import top.theillusivec4.curios.api.type.capability.ICurioItem;

import java.util.List;

public class SunCore extends SimpleFoiledItem implements ICurioItem {
    public SunCore(Properties properties) {
        super(properties);
    }



    int i = 0;
    @Override
    public List<Component> getAttributesTooltip(List<Component> tagTooltips, ItemStack stack) {
        if(i > 70)
            tagTooltips.add(Component.translatable("gui.deep_aether.flawless_tier_1"));
        else if(i > 60)
            tagTooltips.add(Component.translatable("gui.deep_aether.flawless_tier_2"));
        else if(i > 50)
            tagTooltips.add(Component.translatable("gui.deep_aether.flawless_tier_3"));
        else if(i > 40)
            tagTooltips.add(Component.translatable("gui.deep_aether.flawless_tier_4"));
        else if(i > 30)
            tagTooltips.add(Component.translatable("gui.deep_aether.flawless_tier_5"));
        else if(i > 20)
            tagTooltips.add(Component.translatable("gui.deep_aether.flawless_tier_6"));
        else if(i > 10)
            tagTooltips.add(Component.translatable("gui.deep_aether.flawless_tier_7"));
        else
            tagTooltips.add(Component.translatable("gui.deep_aether.flawless_tier_8"));

        if(i < 80)
            i++;
        else i = 0;


        return ICurioItem.super.getAttributesTooltip(tagTooltips, stack);
    }
}
