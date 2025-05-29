package io.github.razordevs.deep_aether.item;

import io.github.razordevs.deep_aether.DeepAether;
import io.github.razordevs.deep_aether.init.DAItems;
import net.minecraft.Util;
import net.minecraft.core.Holder;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.Style;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.armortrim.TrimMaterial;

import java.util.Map;

public class DATrimMaterials {
    public static final ResourceKey<TrimMaterial> STORMFORGED = createKey("stormforged");

    private static ResourceKey<TrimMaterial> createKey(String name) {
        return ResourceKey.create(Registries.TRIM_MATERIAL, DeepAether.getResource(name));
    }

    public static void bootstrap(BootstrapContext<TrimMaterial> context) {
        register(context, STORMFORGED, DAItems.SQUALL_PLATE.get(), Style.EMPTY.withColor(0xc8ddde), 0.9F);
    }

    private static void register(BootstrapContext<TrimMaterial> context, ResourceKey<TrimMaterial> materialKey, Item ingredient, Style style, float itemModelIndex) {
        register(context, materialKey, ingredient, style, itemModelIndex, Map.of());
    }

    private static void register(BootstrapContext<TrimMaterial> context, ResourceKey<TrimMaterial> materialKey, Item ingredient, Style style, float itemModelIndex, Map<Holder<ArmorMaterial>, String> overrideArmorMaterials) {
        TrimMaterial trimMaterial = TrimMaterial.create(materialKey.location().getPath(), ingredient, itemModelIndex, Component.translatable(Util.makeDescriptionId("trim_material", materialKey.location())).withStyle(style), overrideArmorMaterials);
        context.register(materialKey, trimMaterial);
    }
}
