package teamrazor.deepaether.item.gear.other;

import com.aetherteam.aether.client.AetherSoundEvents;
import com.aetherteam.aether.item.accessories.pendant.PendantItem;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.item.ItemStack;
import teamrazor.deepaether.DeepAetherMod;
import teamrazor.deepaether.init.DAMobEffects;
import top.theillusivec4.curios.api.SlotContext;

import java.util.List;

public class MedalOfHonor extends PendantItem {
    public MedalOfHonor(Properties properties) {
        super(new ResourceLocation(DeepAetherMod.MODID, "medal_of_honor"), AetherSoundEvents.ITEM_ACCESSORY_EQUIP_GOLD_PENDANT, properties);
    }
    public void curioTick(SlotContext slotContext, ItemStack stack) {
        slotContext.entity().addEffect(new MobEffectInstance(DAMobEffects.VALKYRIE_VALOR.get(), 5, 0, true, false, true));
    }

    @Override
    public void setRenderTexture(String modId, String registryName) {
        this.PENDANT_LOCATION = new ResourceLocation(modId, "textures/models/accessory/pendant/" + registryName + "_accessory.png");
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

        return super.getAttributesTooltip(tagTooltips, stack);
    }
}
