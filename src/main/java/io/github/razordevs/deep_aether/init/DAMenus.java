package io.github.razordevs.deep_aether.init;

import com.aetherteam.cumulus.api.CumulusEntrypoint;
import com.aetherteam.cumulus.api.Menu;
import com.aetherteam.cumulus.api.MenuInitializer;
import com.aetherteam.cumulus.api.MenuRegisterCallback;
import io.github.razordevs.deep_aether.DeepAether;
import io.github.razordevs.deep_aether.screen.cumulus.DATitleScreen;
import net.minecraft.client.renderer.CubeMap;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;

@CumulusEntrypoint
public class DAMenus implements MenuInitializer {
    // Icons
    private static final ResourceLocation DEEP_AETHER_ICON = ResourceLocation.fromNamespaceAndPath(DeepAether.MODID, "textures/gui/cumulus/deep_aether_icon.png");

    // Names
    private static final Component DA_NAME = Component.translatable("deep_aether.menu_title.deep_aether");
    private static final Component DA_LEFT_NAME = Component.translatable("deep_aether.menu_title.deep_aether_left");

    // Menus
    public static final Menu DEEP_AETHER = new Menu(DEEP_AETHER_ICON, DA_NAME, new DATitleScreen(false), new Menu.Properties().music(DATitleScreen.MENU).panorama(new CubeMap(DeepAether.getResource("textures/gui/cumulus/panorama"))));
    public static final Menu DEEP_AETHER_LEFT = new Menu(DEEP_AETHER_ICON, DA_LEFT_NAME, new DATitleScreen(true), new Menu.Properties().music(DATitleScreen.MENU).panorama(new CubeMap(DeepAether.getResource("textures/gui/cumulus/panorama"))));

    @Override
    public void registerMenus(MenuRegisterCallback menuRegisterCallback) {
        menuRegisterCallback.registerMenu(ResourceLocation.fromNamespaceAndPath(DeepAether.MODID, "deep_aether"), DEEP_AETHER);
        menuRegisterCallback.registerMenu(ResourceLocation.fromNamespaceAndPath(DeepAether.MODID, "deep_aether_left"), DEEP_AETHER_LEFT);
    }
}
