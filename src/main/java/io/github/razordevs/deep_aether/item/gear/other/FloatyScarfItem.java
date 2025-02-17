package io.github.razordevs.deep_aether.item.gear.other;

import com.aetherteam.aether.item.accessories.pendant.PendantItem;
import com.aetherteam.nitrogen.attachment.INBTSynchable;
import io.github.razordevs.deep_aether.entity.living.GentleWind;
import io.github.razordevs.deep_aether.item.component.DADataComponentTypes;
import io.github.razordevs.deep_aether.item.component.FloatyScarf;
import io.github.razordevs.deep_aether.networking.attachment.DAAttachments;
import io.github.razordevs.deep_aether.networking.attachment.DAPlayerAttachment;
import io.wispforest.accessories.api.slot.SlotReference;
import net.minecraft.ChatFormatting;
import net.minecraft.core.Holder;
import net.minecraft.core.component.DataComponents;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class FloatyScarfItem extends PendantItem {
    public FloatyScarfItem(ResourceLocation pendantLocation, Holder<SoundEvent> pendantSound, Properties properties) {
        super(pendantLocation, pendantSound, properties);
    }

    @Override
    public void onEquip(ItemStack stack, SlotReference reference) {
        if(!stack.isEmpty()) {
            try {
                if(!reference.entity().level().isClientSide() && reference.entity().hasData(DAAttachments.PLAYER)) {
                    DAPlayerAttachment attachment  = reference.entity().getData(DAAttachments.PLAYER);
                    attachment.setSynched(reference.entity().getId(), INBTSynchable.Direction.CLIENT, "setFloatyScarfWrappedAroundNeck", false);
                }
                addGentleWind(stack, (Player) reference.entity());
            } catch (ClassCastException ignored) {}
        }
    }

    @Override
    public void tick(ItemStack stack, SlotReference reference) {
        if(!(reference.entity() instanceof Player player))
            return;

        FloatyScarf scarf = stack.get(DADataComponentTypes.FLOATY_SCARF);
        if(scarf == null)
            return;

        Entity entity = getGentleWind(stack, reference.entity().level());
        if(entity == null || !entity.isAlive()) {
            FloatyScarfItem.addGentleWind(stack, player);
        }
    }

    @Override
    public void onUnequip(ItemStack stack, SlotReference reference) {
        if(!stack.isEmpty()) {
            try {
                if(!reference.entity().level().isClientSide() && reference.entity().hasData(DAAttachments.PLAYER)) {
                    DAPlayerAttachment attachment  = reference.entity().getData(DAAttachments.PLAYER);
                    attachment.setSynched(reference.entity().getId(), INBTSynchable.Direction.CLIENT, "setFloatyScarfWrappedAroundNeck", false);
                }
            } catch (ClassCastException ignored) {}
            discardGentleWind(stack, reference.entity().level());
        }
    }

    public static void discardGentleWind(ItemStack stack, Level level) {
        Entity entity = getGentleWind(stack, level);
        if(entity != null)
            entity.discard();
    }

    public static void addGentleWind(ItemStack stack, Player player) {
        FloatyScarf scarf = stack.get(DADataComponentTypes.FLOATY_SCARF);
        if(scarf == null) {
            scarf = FloatyScarf.withDefaultColor(0);
        }
        GentleWind eots = new GentleWind(player.level(), player, scarf.colors());
        Component component = stack.get(DataComponents.CUSTOM_NAME);
        if (component != null) {
            eots.setCustomName(component);
        }
        stack.set(DADataComponentTypes.FLOATY_SCARF, new FloatyScarf(eots.getId(), scarf.colors(), scarf.currentModification()));
    }

    public static Entity getGentleWind(ItemStack stack, Level level) {
        FloatyScarf scarf = stack.get(DADataComponentTypes.FLOATY_SCARF);
        return scarf != null ? level.getEntity(scarf.uuid()) : null;
    }
    @Override
    public void appendHoverText(ItemStack stack, TooltipContext context, List<Component> tooltipComponents, TooltipFlag tooltipFlag) {
        if (!tooltipFlag.hasShiftDown()) {
            return;
        }

        chatFormat(tooltipComponents, 0, stack);
        chatFormat(tooltipComponents, 1, stack);
        chatFormat(tooltipComponents, 2, stack);
        chatFormat(tooltipComponents, 3, stack);
        chatFormat(tooltipComponents, 4, stack);
    }

    private void chatFormat(List<Component> tooltipComponents, int color, ItemStack stack) {
        FloatyScarf scarf = stack.get(DADataComponentTypes.FLOATY_SCARF);
        if (scarf != null) {
            if (color == scarf.currentModification())
                tooltipComponents.add(Component.literal("Color").withColor(scarf.colors().get(color)).withStyle(ChatFormatting.ITALIC));
            else tooltipComponents.add(Component.literal("Color").withColor(scarf.colors().get(color)));
        }
    }
}
