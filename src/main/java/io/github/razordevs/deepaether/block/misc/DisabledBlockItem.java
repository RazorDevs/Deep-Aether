package io.github.razordevs.deepaether.deepaether.block.misc;

import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.Style;
import net.minecraft.network.chat.TextColor;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.level.block.Block;
//Used to indicate a disabled compat item.
public class DisabledBlockItem extends BlockItem {

    public DisabledBlockItem(Block block, Properties properties) {
        super(block, properties);
    }

    @Override
    public Component getDescription() {
        return Component.translatable("deep_aether.item.disabled_item").withStyle(Style.EMPTY.withItalic(true)
                .withColor(TextColor.parseColor("#d1362b").result().get()));
    }
}