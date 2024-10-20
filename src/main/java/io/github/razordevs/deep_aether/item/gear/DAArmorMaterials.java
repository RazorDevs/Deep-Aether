package io.github.razordevs.deep_aether.item.gear;

import io.github.razordevs.deep_aether.DeepAether;
import io.github.razordevs.deep_aether.datagen.tags.DATags;
import io.github.razordevs.deep_aether.init.DASounds;
import net.minecraft.Util;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.crafting.Ingredient;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.EnumMap;
import java.util.List;

public class DAArmorMaterials {

    public static final DeferredRegister<ArmorMaterial> ARMOR_MATERIALS = DeferredRegister.create(BuiltInRegistries.ARMOR_MATERIAL, DeepAether.MODID);

    public static final DeferredHolder<ArmorMaterial, ArmorMaterial> STRATUS = ARMOR_MATERIALS.register("stratus", () -> new ArmorMaterial(
            Util.make(new EnumMap<>(ArmorItem.Type.class), map -> {
                map.put(ArmorItem.Type.BOOTS, 3);
                map.put(ArmorItem.Type.LEGGINGS, 6);
                map.put(ArmorItem.Type.CHESTPLATE, 8);
                map.put(ArmorItem.Type.HELMET, 3);
            }), 15, DASounds.ITEM_ARMOR_EQUIP_STRATUS, ()->Ingredient.of(DATags.Items.STRATUS_REPAIRING),
            List.of(new ArmorMaterial.Layer(ResourceLocation.fromNamespaceAndPath(DeepAether.MODID, "stratus")), new ArmorMaterial.Layer(ResourceLocation.fromNamespaceAndPath(DeepAether.MODID, "stratus"), "_overlay", false)),
            1.5F, 0.15F
    ));

    public static final DeferredHolder<ArmorMaterial, ArmorMaterial> STORMFORGED = ARMOR_MATERIALS.register("stormforged", () -> new ArmorMaterial(
            Util.make(new EnumMap<>(ArmorItem.Type.class), map -> {
                map.put(ArmorItem.Type.BOOTS, 3);
                map.put(ArmorItem.Type.LEGGINGS, 6);
                map.put(ArmorItem.Type.CHESTPLATE, 8);
                map.put(ArmorItem.Type.HELMET, 3);
            }), 10, DASounds.ITEM_ARMOR_EQUIP_STRATUS, ()->Ingredient.of(DATags.Items.STRATUS_REPAIRING),
            List.of(new ArmorMaterial.Layer(ResourceLocation.fromNamespaceAndPath(DeepAether.MODID, "stormforged")), new ArmorMaterial.Layer(ResourceLocation.fromNamespaceAndPath(DeepAether.MODID, "stormforged"), "_overlay", false)),
            1.5F, 0.15F
    ));

    public static final DeferredHolder<ArmorMaterial, ArmorMaterial> SKYJADE = ARMOR_MATERIALS.register("skyjade", () -> new ArmorMaterial(
            Util.make(new EnumMap<>(ArmorItem.Type.class), map -> {
                map.put(ArmorItem.Type.BOOTS, 3);
                map.put(ArmorItem.Type.LEGGINGS, 6);
                map.put(ArmorItem.Type.CHESTPLATE, 8);
                map.put(ArmorItem.Type.HELMET, 3);
            }), 10, DASounds.ITEM_ARMOR_EQUIP_SKYJADE, ()->Ingredient.of(DATags.Items.SKYJADE_REPAIRING),
            List.of(new ArmorMaterial.Layer(ResourceLocation.fromNamespaceAndPath(DeepAether.MODID, "skyjade")), new ArmorMaterial.Layer(ResourceLocation.fromNamespaceAndPath(DeepAether.MODID, "skyjade"), "_overlay", false)),
            0.0F, 0.0F
    ));
}
