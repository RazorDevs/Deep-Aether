package teamrazor.deepaether.item.misc;

import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.Style;
import net.minecraft.network.chat.TextColor;

public interface DACompatItem {


    default Component getDescription() {
        return Component.translatable("deep_aether.item.disabled_item").withStyle(Style.EMPTY.withItalic(true).withColor(TextColor.parseColor("#d1362b")));
    }
}
