package teamrazor.deepaether.item.gear.other;

import com.aetherteam.aether.item.accessories.cape.CapeItem;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.ItemStack;

import java.util.List;

public class FloatyScarf extends CapeItem implements FlawlessDrop {
    public FloatyScarf(String capeLocation, Properties properties) {
        super(capeLocation, properties);
    }

    int i = 0;
    @Override
    public List<Component> getAttributesTooltip(List<Component> tagTooltips, ItemStack stack) {
        flawlessComponent(tagTooltips, i);
        i = i < 80 ? i + 1 : 0;
        return super.getAttributesTooltip(tagTooltips, stack);
    }
}
