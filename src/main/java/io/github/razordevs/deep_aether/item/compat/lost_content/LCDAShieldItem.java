package io.github.razordevs.deep_aether.item.compat.lost_content;
/*
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.Style;
import net.minecraft.network.chat.TextColor;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.ShieldItem;
import net.neoforged.fml.ModList;
import io.github.razordevs.deep_aether.DeepAether;
import io.github.razordevs.deep_aether.init.DAItems;

public class LCDAShieldItem extends ShieldItem {
    public LCDAShieldItem(Properties pProperties) {
        super(pProperties);
    }

    @Override
    public boolean isValidRepairItem(ItemStack pStack, ItemStack pRepairCandidate) {
        if (this.equals(DAItems.SKYJADE_SHIELD.get())) {
            return pRepairCandidate.is(DAItems.SKYJADE.get());
        }
        if (this.equals(DAItems.STRATUS_SHIELD.get())) {
            return pRepairCandidate.is(DAItems.STRATUS_INGOT.get());
        }
        return super.isValidRepairItem(pStack, pRepairCandidate);
    }

    @Override
    public Component getDescription() {
        if(ModList.get().isLoaded(DeepAether.LOST_AETHER_CONTENT)) {
            return super.getDescription();
        }
        else return Component.translatable("deep_aether.item.disabled_item").withStyle(Style.EMPTY.withItalic(true).withColor(TextColor.parseColor("#d1362b").result().get()));

    }
}

 */
