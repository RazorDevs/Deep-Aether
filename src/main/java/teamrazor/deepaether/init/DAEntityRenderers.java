package teamrazor.deepaether.init;

import net.minecraft.client.model.BoatModel;
import net.minecraft.client.model.ChestBoatModel;
import net.minecraft.client.renderer.blockentity.SignRenderer;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.api.distmarker.Dist;
import teamrazor.deepaether.client.model.AerglowFishModel;
import teamrazor.deepaether.client.renderer.AetherFishRenderer;
import teamrazor.deepaether.client.renderer.DeepAetherModelLayers;
import teamrazor.deepaether.client.renderer.QuailRenderer;


@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class DAEntityRenderers {
	@SubscribeEvent
	public static void registerEntityRenderers(EntityRenderersEvent.RegisterRenderers event) {
		event.registerEntityRenderer(DAEntities.AETHER_FISH.get(), AetherFishRenderer::new);
		event.registerEntityRenderer(DAEntities.QUAIL.get(), QuailRenderer::new);
		event.registerBlockEntityRenderer(DABlockEntityTypes.SIGN.get(), SignRenderer::new);

	}
	@SubscribeEvent
	public static void registerLayerDefinitions(EntityRenderersEvent.RegisterLayerDefinitions event) {
		event.registerLayerDefinition(DeepAetherModelLayers.AERGLOW_FISH, AerglowFishModel::createBodyLayer);
		event.registerLayerDefinition(DeepAetherModelLayers.ROSEROOT_BOAT, BoatModel::createBodyModel);
		event.registerLayerDefinition(DeepAetherModelLayers.ROSEROOT_CHEST_BOAT, ChestBoatModel::createBodyModel);
		event.registerLayerDefinition(DeepAetherModelLayers.YAGROOT_BOAT, BoatModel::createBodyModel);
		event.registerLayerDefinition(DeepAetherModelLayers.YAGROOT_CHEST_BOAT, ChestBoatModel::createBodyModel);
		event.registerLayerDefinition(DeepAetherModelLayers.CRUDEROOT_BOAT, BoatModel::createBodyModel);
		event.registerLayerDefinition(DeepAetherModelLayers.CRUDEROOT_CHEST_BOAT, ChestBoatModel::createBodyModel);
	}
}