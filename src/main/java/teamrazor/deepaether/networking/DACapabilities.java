package teamrazor.deepaether.networking;


import com.aetherteam.nitrogen.capability.CapabilityProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Entity;
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
    public void registerCapabilities(RegisterCapabilitiesEvent event) {
        event.register(DeepAetherPlayer.class);
    }

    @Mod.EventBusSubscriber(modid = DeepAetherMod.MODID)
    public static class Registration {
        @SubscribeEvent
        public static void attachPlayerCapabilities(AttachCapabilitiesEvent<Entity> event) {
            if(event.getObject() instanceof Player player)
                event.addCapability(new ResourceLocation(DeepAetherMod.MODID, "deep_aether_player"), new CapabilityProvider(DACapabilities.DEEP_AETHER_PLAYER_CAPABILITY, new DAPlayerCapability(player)));
        }
    }
}
