package io.github.razordevs.deep_aether.client.keys;

import io.github.razordevs.deep_aether.DeepAether;
import net.minecraft.client.KeyMapping;
import net.minecraft.client.ToggleKeyMapping;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.neoforge.client.event.RegisterKeyMappingsEvent;
import org.lwjgl.glfw.GLFW;

@EventBusSubscriber(modid = DeepAether.MODID, value = Dist.CLIENT, bus = EventBusSubscriber.Bus.MOD)
public class DeepAetherKeys {

    public final static KeyMapping STRATUS_DASH_ABILITY = new KeyMapping("key.deep_aether.stratus_dash_ability.desc", GLFW.GLFW_KEY_R, "key.aether.category");
    public final static KeyMapping SLIDER_EYE_SLAM_ABILITY = new KeyMapping("key.deep_aether.slider_eye_ability", GLFW.GLFW_KEY_LEFT_ALT, "key.aether.category");
    public final static ToggleKeyMapping TOGGLE_SKYJADE_TRANSPARENCY = new ToggleKeyMapping("key.deep_aether.toggle_skyjade_transparency", GLFW.GLFW_KEY_V, "key.aether.category", () -> true);

    @SubscribeEvent
    public static void registerKeyMappings(RegisterKeyMappingsEvent event) {
        event.register(STRATUS_DASH_ABILITY);
        event.register(SLIDER_EYE_SLAM_ABILITY);
        event.register(TOGGLE_SKYJADE_TRANSPARENCY);
    }
}
