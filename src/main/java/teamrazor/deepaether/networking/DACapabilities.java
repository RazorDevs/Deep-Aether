package teamrazor.deepaether.networking;


import com.aetherteam.nitrogen.capability.CapabilityProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityManager;
import net.minecraftforge.common.capabilities.CapabilityToken;
import net.minecraftforge.common.capabilities.RegisterCapabilitiesEvent;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import teamrazor.deepaether.DeepAetherMod;

@Mod.EventBusSubscriber(modid = DeepAetherMod.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class DACapabilities {
    public static final Capability<DeepAetherPlayer> DEEP_AETHER_PLAYER_CAPABILITY = CapabilityManager.get(new CapabilityToken<>() {});

    @SubscribeEvent
    public static void register(RegisterCapabilitiesEvent event) {
        event.register(DeepAetherPlayer.class);
    }

    public static class Registration {
        public Registration() {
        }

        @SubscribeEvent
        public static void attachEntityCapabilities(AttachCapabilitiesEvent<Entity> event) {
            Object var2 = event.getObject();
            if (var2 instanceof LivingEntity livingEntity) {
                if (livingEntity instanceof Player player) {
                    event.addCapability(new ResourceLocation(DeepAetherMod.MODID, "deep_aether_player"), new CapabilityProvider(DACapabilities.DEEP_AETHER_PLAYER_CAPABILITY, new DAPlayerCapability(player)));
                }
            }
        }
    }
}
