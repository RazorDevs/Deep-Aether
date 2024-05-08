package teamrazor.deepaether.client.keys;

import net.minecraft.client.KeyMapping;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.client.event.RegisterKeyMappingsEvent;
import org.lwjgl.glfw.GLFW;
import teamrazor.deepaether.DeepAether;

@Mod.EventBusSubscriber(modid = DeepAether.MODID, value = Dist.CLIENT, bus = Mod.EventBusSubscriber.Bus.MOD)
public class DeepAetherKeys {
    public final static KeyMapping STRATUS_DASH_ABILITY = new KeyMapping("key.deep_aether.stratus_dash_ability.desc", GLFW.GLFW_KEY_R, "key.aether.category");
    public final static KeyMapping SLIDER_EYE_SLAM_ABILITY = new KeyMapping("key.deep_aether.slider_eye_ability", GLFW.GLFW_KEY_LEFT_ALT, "key.aether.category");

    @SubscribeEvent
    public static void registerKeyMappings(RegisterKeyMappingsEvent event) {
        event.register(STRATUS_DASH_ABILITY);
        event.register(SLIDER_EYE_SLAM_ABILITY);
    }
}
