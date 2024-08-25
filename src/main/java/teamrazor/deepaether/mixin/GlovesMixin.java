package teamrazor.deepaether.mixin;

import com.aetherteam.aether.item.accessories.gloves.GlovesItem;
import com.google.common.collect.Multimap;
import com.llamalad7.mixinextras.sugar.Local;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.neoforged.neoforge.common.NeoForgeMod;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import teamrazor.deepaether.init.DAEnchantments;
import top.theillusivec4.curios.api.SlotContext;

import java.util.UUID;

@Mixin(GlovesItem.class)
public abstract class GlovesMixin extends Item {
    public GlovesMixin(Properties pProperties) {
        super(pProperties);
    }

    @Inject(at = @At(value = "INVOKE", target = "Lcom/google/common/collect/Multimap;put(Ljava/lang/Object;Ljava/lang/Object;)Z"), method = "getAttributeModifiers")
    private void getAttributeModifiers(SlotContext slotContext, UUID uuid, ItemStack stack, CallbackInfoReturnable<Multimap<Attribute, AttributeModifier>> cir, @Local Multimap<Attribute, AttributeModifier> attributes) {
        attributes.put(NeoForgeMod.BLOCK_REACH.value(), new AttributeModifier(uuid, "Gloves Damage Bonus", stack.getEnchantmentLevel(DAEnchantments.GLOVES_REACH.get()), AttributeModifier.Operation.ADDITION));
    }
}
