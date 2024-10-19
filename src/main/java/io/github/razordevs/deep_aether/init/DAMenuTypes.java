package io.github.razordevs.deep_aether.init;

import io.github.razordevs.deep_aether.screen.CombinerMenu;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.inventory.MenuType;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;
import teamrazor.deepaether.DeepAether;

public class DAMenuTypes {
    public static final DeferredRegister<MenuType<?>> MENUS =
            DeferredRegister.create(BuiltInRegistries.MENU, DeepAether.MODID);

    public static final DeferredHolder<MenuType<?>, MenuType<CombinerMenu>> COMBINER_MENU = MENUS.register("combiner_menu", () ->
            new MenuType<>(CombinerMenu::new, FeatureFlags.VANILLA_SET));
}
