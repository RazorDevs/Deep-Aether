
/*
 *    MCreator note: This file will be REGENERATED on each build.
 */
package teamrazor.deepaether.init;

import teamrazor.deepaether.client.renderer.AetherFishRenderer;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.api.distmarker.Dist;
import teamrazor.deepaether.client.renderer.QuailRenderer;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class DeepAetherModEntityRenderers {
	@SubscribeEvent
	public static void registerEntityRenderers(EntityRenderersEvent.RegisterRenderers event) {
		event.registerEntityRenderer(DeepAetherModEntities.AETHER_FISH.get(), AetherFishRenderer::new);
		event.registerEntityRenderer(DeepAetherModEntities.QUAIL.get(), QuailRenderer::new);
	}
}
