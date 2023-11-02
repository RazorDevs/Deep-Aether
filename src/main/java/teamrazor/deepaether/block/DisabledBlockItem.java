package teamrazor.deepaether.block;

import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.Style;
import net.minecraft.network.chat.TextColor;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.level.block.Block;

public class DisabledBlockItem extends BlockItem {

    public DisabledBlockItem(Block p_40565_, Properties p_40566_) {
        super(p_40565_, p_40566_);
    }

    @Override
    public Component getDescription() {
        return Component.translatable("deep_aether.item.disabled_item").withStyle(Style.EMPTY.withItalic(true).withColor(TextColor.parseColor("#d1362b")));
    }
}