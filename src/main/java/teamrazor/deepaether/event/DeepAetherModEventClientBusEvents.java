package teamrazor.deepaether.event;

import net.minecraft.client.renderer.entity.EntityRenderers;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import teamrazor.deepaether.DeepAetherMod;
import teamrazor.deepaether.client.renderer.AetherFishRenderer;
import teamrazor.deepaether.client.renderer.QuailRenderer;
import teamrazor.deepaether.init.DeepAetherModEntities;

@Mod.EventBusSubscriber(modid = DeepAetherMod.MODID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class DeepAetherModEventClientBusEvents {
    @SubscribeEvent
    public static void registerRenderers(EntityRenderersEvent.RegisterRenderers event) {
        EntityRenderers.register(DeepAetherModEntities.QUAIL.get(), QuailRenderer::new);
        EntityRenderers.register(DeepAetherModEntities.AETHER_FISH.get(), AetherFishRenderer::new);
    }
}



