package teamrazor.deepaether.item.gear.other;

import com.aetherteam.aether.item.accessories.ring.RingItem;
import com.google.common.collect.HashMultimap;
import com.google.common.collect.Multimap;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.item.ItemStack;
import top.theillusivec4.curios.api.SlotContext;

import java.util.List;
import java.util.UUID;
import java.util.function.Supplier;

public class SliderRing extends RingItem {
    public static final UUID SPOOKY_RING_UUID = UUID.fromString("c5a762b9-f032-408f-86d2-5197e784a061");
    private int Increase = 0;
    public SliderRing(Supplier<? extends SoundEvent> ringSound, Properties properties) {
        super(ringSound, properties);
    }

    Multimap<Attribute, AttributeModifier> attributes = HashMultimap.create();

    private int calculateIncrease(SlotContext context) {
        LivingEntity entity = context.entity();
        Increase = (int) (entity.getMaxHealth() / entity.getHealth()) - 1;

        return Increase;
    }


    @Override
    public void curioTick(SlotContext slotContext, ItemStack stack) {
        super.curioTick(slotContext, stack);
        AttributeModifier attribute = slotContext.entity().getAttribute(Attributes.MOVEMENT_SPEED).getModifier(SPOOKY_RING_UUID);
        if (attribute != null) {

            slotContext.entity().getAttribute(Attributes.ATTACK_DAMAGE).removeModifier(SPOOKY_RING_UUID);
            attributes.put(Attributes.MOVEMENT_SPEED, new AttributeModifier(SPOOKY_RING_UUID, "Slider Ring Speed Boost", this.calculateIncrease(slotContext), AttributeModifier.Operation.ADDITION));

        } else
            attributes.put(Attributes.MOVEMENT_SPEED, new AttributeModifier(SPOOKY_RING_UUID, "Slider Ring Speed Boost", this.calculateIncrease(slotContext), AttributeModifier.Operation.ADDITION));

    }

    @Override
    public List<Component> getAttributesTooltip(List<Component> tagTooltips, ItemStack stack) {
        tagTooltips.add(Component.translatable("gui.deep_aether.slider_ring").append(String.valueOf(Increase))


                .withStyle(ChatFormatting.DARK_BLUE));
        return super.getAttributesTooltip(tagTooltips, stack);
    }
}
