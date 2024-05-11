package teamrazor.deepaether.init;

import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.MenuType;
import net.neoforged.neoforge.network.IContainerFactory;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.neoforged.neoforge.registries.NeoForgeRegistries;
import teamrazor.deepaether.DeepAether;
import teamrazor.deepaether.screen.CombinerMenu;

public class DAMenuTypes {
    public static final DeferredRegister<MenuType<?>> MENUS =
            DeferredRegister.create(BuiltInRegistries.MENU, DeepAether.MODID);

    public static final DeferredHolder<MenuType<?>, MenuType<CombinerMenu>> COMBINER_MENU = MENUS.register("combiner_menu", () ->
            new MenuType<>(CombinerMenu::new, FeatureFlags.VANILLA_SET));
}
