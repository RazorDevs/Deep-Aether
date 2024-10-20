package io.github.razordevs.deep_aether.datagen;

import com.aetherteam.aether.AetherTags;
import com.aetherteam.aether.inventory.AetherAccessorySlots;
import io.github.razordevs.deep_aether.DeepAether;
import net.minecraft.core.HolderGetter;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EquipmentSlotGroup;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentEffectComponents;
import net.minecraft.world.item.enchantment.LevelBasedValue;
import net.minecraft.world.item.enchantment.effects.EnchantmentAttributeEffect;

public class DAEnchantments {
    public static final ResourceKey<Enchantment> GLOVES_REACH = registerKey("gloves_reach");

    private static ResourceKey<Enchantment> registerKey(String name) {
        return ResourceKey.create(Registries.ENCHANTMENT, ResourceLocation.fromNamespaceAndPath(DeepAether.MODID, name));
    }


    public static void bootstrap(BootstrapContext<Enchantment> context) {
        HolderGetter<Item> items = context.lookup(Registries.ITEM);
        register(context, GLOVES_REACH, new Enchantment.Builder(Enchantment.definition(
                items.getOrThrow(AetherTags.Items.ACCESSORIES_GLOVES),
                1,
                3,
                Enchantment.dynamicCost(25, 25),
                Enchantment.dynamicCost(75, 25),
                8,
                EquipmentSlotGroup.ANY))
                .withEffect(
                        EnchantmentEffectComponents.ATTRIBUTES,
                        new EnchantmentAttributeEffect(
                                ResourceLocation.fromNamespaceAndPath(DeepAether.MODID, "Gloves Reach Bonus"),
                                Attributes.BLOCK_INTERACTION_RANGE,
                                LevelBasedValue.perLevel(1.0F),
                                AttributeModifier.Operation.ADD_VALUE
                        )
                ));

    }

    private static void register(BootstrapContext<Enchantment> context, ResourceKey<Enchantment> key, Enchantment.Builder builder) {
        context.register(key, builder.build(key.location()));
    }
}