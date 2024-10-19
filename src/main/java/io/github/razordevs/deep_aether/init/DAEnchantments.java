package io.github.razordevs.deep_aether.init;

import io.github.razordevs.deep_aether.enchantments.GlovesReachEnchantment;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentCategory;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;
import teamrazor.deepaether.DeepAether;

public class DAEnchantments {
    public static final DeferredRegister<Enchantment> ENCHANTMENTS =
            DeferredRegister.create(BuiltInRegistries.ENCHANTMENT, DeepAether.MODID);

    public static final DeferredHolder<Enchantment, Enchantment> GLOVES_REACH = ENCHANTMENTS.register("gloves_reach", () ->
            new GlovesReachEnchantment(Enchantment.Rarity.RARE, EnchantmentCategory.create("GLOVES",
                    (item -> item instanceof ArmorItem armoritem && armoritem.getEquipmentSlot() == EquipmentSlot.FEET)),
                    new EquipmentSlot[]{EquipmentSlot.MAINHAND}));
}
