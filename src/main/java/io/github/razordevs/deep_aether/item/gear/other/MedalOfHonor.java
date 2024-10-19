package io.github.razordevs.deep_aether.item.gear.other;

import com.aetherteam.aether.item.accessories.pendant.PendantItem;
import io.github.razordevs.deep_aether.init.DAMobEffects;
import io.github.razordevs.deep_aether.init.DASounds;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.item.ItemStack;
import teamrazor.deepaether.DeepAether;
import top.theillusivec4.curios.api.SlotContext;

import java.util.List;

public class MedalOfHonor extends PendantItem implements FlawlessDrop {
    public MedalOfHonor(Properties properties) {
        super(ResourceLocation.fromNamespaceAndPath(DeepAether.MODID, "medal_of_honor"), DASounds.ITEM_ACCESSORY_EQUIP_MEDAL_OF_HONOR, properties);
    }
    public void curioTick(SlotContext slotContext, ItemStack stack) {
        slotContext.entity().addEffect(new MobEffectInstance(DAMobEffects.VALKYRIE_VALOR.get(), 5, 0, true, false, true));
    }

    @Override
    public void setRenderTexture(String modId, String registryName) {
        this.PENDANT_LOCATION = ResourceLocation.fromNamespaceAndPath(modId, "textures/models/accessory/pendant/" + registryName + "_accessory.png");
    }

    int i = 0;
    @Override
    public List<Component> getAttributesTooltip(List<Component> tagTooltips, ItemStack stack) {
        flawlessComponent(tagTooltips, i);
        i = i < 80 ? i + 1 : 0;
        return super.getAttributesTooltip(tagTooltips, stack);
    }
}
