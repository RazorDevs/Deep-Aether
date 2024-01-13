package teamrazor.deepaether.item.misc;

import net.minecraft.network.chat.Component;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.Nullable;
import top.theillusivec4.curios.api.type.capability.ICurioItem;

import java.util.List;

public class SunCore extends Item implements ICurioItem {
    public SunCore(Properties properties) {
        super(properties);
    }

    int i = 0;
    @Override
    public void appendHoverText(ItemStack itemStack, @Nullable Level level, List<Component> components, TooltipFlag tooltipFlag) {

        if(i > 70)
            components.add(Component.translatable("gui.deep_aether.flawless_tier_1"));
        else if(i > 60)
            components.add(Component.translatable("gui.deep_aether.flawless_tier_2"));
        else if(i > 50)
            components.add(Component.translatable("gui.deep_aether.flawless_tier_3"));
        else if(i > 40)
            components.add(Component.translatable("gui.deep_aether.flawless_tier_4"));
        else if(i > 30)
            components.add(Component.translatable("gui.deep_aether.flawless_tier_5"));
        else if(i > 20)
            components.add(Component.translatable("gui.deep_aether.flawless_tier_6"));
        else if(i > 10)
            components.add(Component.translatable("gui.deep_aether.flawless_tier_7"));
        else
            components.add(Component.translatable("gui.deep_aether.flawless_tier_8"));

        if(i < 80)
            i++;
        else i = 0;


        super.appendHoverText(itemStack, level, components, tooltipFlag);
    }
}
