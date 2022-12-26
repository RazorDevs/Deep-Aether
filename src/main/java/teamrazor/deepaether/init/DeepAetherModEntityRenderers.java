package teamrazor.deepaether.init;

import net.minecraft.client.model.BoatModel;
import net.minecraft.client.model.ChestBoatModel;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.api.distmarker.Dist;
import teamrazor.deepaether.client.renderer.DeepAetherModelLayers;


@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class DeepAetherModEntityRenderers {
	@SubscribeEvent
	public static void registerEntityRenderers(EntityRenderersEvent.RegisterRenderers event) {
		//event.registerEntityRenderer(DeepAetherModEntities.AETHER_FISH.get(), AetherFishRenderer::new);
		//event.registerEntityRenderer(DeepAetherModEntities.QUAIL.get(), QuailRenderer::new);

	}
	@SubscribeEvent
	public static void registerLayerDefinitions(EntityRenderersEvent.RegisterLayerDefinitions event) {
		event.registerLayerDefinition(DeepAetherModelLayers.ROSEROOT_BOAT, BoatModel::createBodyModel);
		event.registerLayerDefinition(DeepAetherModelLayers.ROSEROOT_CHEST_BOAT, ChestBoatModel::createBodyModel);
		event.registerLayerDefinition(DeepAetherModelLayers.YAGROOT_BOAT, BoatModel::createBodyModel);
		event.registerLayerDefinition(DeepAetherModelLayers.YAGROOT_CHEST_BOAT, ChestBoatModel::createBodyModel);
		event.registerLayerDefinition(DeepAetherModelLayers.CRUDEROOT_BOAT, BoatModel::createBodyModel);
		event.registerLayerDefinition(DeepAetherModelLayers.CRUDEROOT_CHEST_BOAT, ChestBoatModel::createBodyModel);
	}
}