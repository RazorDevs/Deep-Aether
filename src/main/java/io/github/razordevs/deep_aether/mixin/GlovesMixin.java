package io.github.razordevs.deep_aether.mixin;

import com.aetherteam.aether.Aether;
import com.aetherteam.aether.item.accessories.gloves.GlovesItem;
import io.github.razordevs.deep_aether.DeepAether;
import io.github.razordevs.deep_aether.datagen.registry.DAEnchantments;
import io.wispforest.accessories.api.attributes.AccessoryAttributeBuilder;
import io.wispforest.accessories.api.slot.SlotReference;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(GlovesItem.class)
public abstract class GlovesMixin extends Item {

    @Unique
    private static final ResourceLocation EXTRA_BLOCK_REACH_ID = ResourceLocation.fromNamespaceAndPath(DeepAether.MODID, "extra_block_reach");


    public GlovesMixin(Properties pProperties) {
        super(pProperties);
    }

    @Inject(at = @At(value = "TAIL"), method = "getDynamicModifiers", remap = false)
    private void getAttributeModifiers(ItemStack stack, SlotReference reference, AccessoryAttributeBuilder builder, CallbackInfo ci) {
        builder.addStackable(Attributes.BLOCK_INTERACTION_RANGE, new AttributeModifier(EXTRA_BLOCK_REACH_ID, stack.getEnchantmentLevel(reference.entity().level().holderOrThrow(DAEnchantments.GLOVES_REACH)), AttributeModifier.Operation.ADD_VALUE));
    }
}
