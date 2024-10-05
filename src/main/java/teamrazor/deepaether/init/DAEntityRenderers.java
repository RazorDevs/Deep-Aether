package teamrazor.deepaether.init;

import net.minecraft.client.model.BoatModel;
import net.minecraft.client.model.ChestBoatModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.renderer.blockentity.HangingSignRenderer;
import net.minecraft.client.renderer.blockentity.SignRenderer;
import net.minecraft.client.renderer.entity.ThrownItemRenderer;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.client.event.EntityRenderersEvent;
import teamrazor.deepaether.DeepAether;
import teamrazor.deepaether.client.model.*;
import teamrazor.deepaether.client.renderer.DAModelLayers;
import teamrazor.deepaether.client.renderer.entity.*;
import teamrazor.deepaether.entity.DABoatEntity;


@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class DAEntityRenderers {

	@SubscribeEvent
	public static void registerEntityRenderers(EntityRenderersEvent.RegisterRenderers event) {
		event.registerEntityRenderer(DAEntities.AETHER_FISH.get(), AetherFishRenderer::new);
		event.registerEntityRenderer(DAEntities.QUAIL.get(), QuailRenderer::new);
		event.registerEntityRenderer(DAEntities.VENOMITE.get(), VenomiteRenderer::new);
		event.registerEntityRenderer(DAEntities.WINDFLY.get(), WindflyRenderer::new);
		event.registerEntityRenderer(DAEntities.EOTS_CONTROLLER.get(), EOTSRenderer::new);
		event.registerEntityRenderer(DAEntities.EOTS_SEGMENT.get(), EOTSSegmentRenderer::new);
		event.registerEntityRenderer(DAEntities.WINDFLY.get(), WindflyRenderer::new);
		event.registerBlockEntityRenderer(DABlockEntityTypes.SIGN.get(), SignRenderer::new);
		event.registerBlockEntityRenderer(DABlockEntityTypes.HANGING_SIGN.get(), HangingSignRenderer::new);

		event.registerEntityRenderer(DAEntities.BOAT.get(), context -> new DABoatRenderer<>(context, false));
		event.registerEntityRenderer(DAEntities.CHEST_BOAT.get(), context -> new DABoatRenderer<>(context, true));
		event.registerEntityRenderer(DAEntities.QUAIL_EGG.get(), ThrownItemRenderer::new);

		event.registerEntityRenderer(DAEntities.FIRE_PROJECTILE.get(), FireProjectileRenderer::new);
		event.registerEntityRenderer(DAEntities.VENOMITE_BUBBLE.get(), VenomiteBubbleRenderer::new);
		event.registerEntityRenderer(DAEntities.WIND_CRYSTAL.get(), WindCrystalRenderer::new);
		event.registerEntityRenderer(DAEntities.STORM_ARROW.get(), StormArrowRenderer::new);
		event.registerEntityRenderer(DAEntities.BABY_ZEPHYR.get(), BabyZephyrRenderer::new);
	}

	@SubscribeEvent
	public static void registerLayerDefinitions(EntityRenderersEvent.RegisterLayerDefinitions event) {
		event.registerLayerDefinition(DAModelLayers.AERGLOW_FISH, AerglowFishModel::createBodyLayer);
		event.registerLayerDefinition(DAModelLayers.EOTS_CONTROLLER, EOTSModel::createBodyLayer);
		event.registerLayerDefinition(DAModelLayers.EOTS_SEGMENT, EOTSSegmentModel::createBodyLayer);
		event.registerLayerDefinition(DAModelLayers.QUAIL, QuailModel::createBodyLayer);
		event.registerLayerDefinition(DAModelLayers.VENOMITE_BUBBLE, VenomiteBubbleModel::createBodyLayer);
		event.registerLayerDefinition(DAModelLayers.VENOMITE, VenomiteModel::createBodyLayer);
		event.registerLayerDefinition(DAModelLayers.WINDFLY, WindflyModel::createBodyLayer);
		event.registerLayerDefinition(DAModelLayers.BABY_ZEPHYR, BabyZephyrModel::createBodyLayer);

		for (DABoatEntity.Type type : DABoatEntity.Type.values()) {
			event.registerLayerDefinition(new ModelLayerLocation(new ResourceLocation(DeepAether.MODID, type.getModelLocation()), "main"), BoatModel::createBodyModel);
			event.registerLayerDefinition(new ModelLayerLocation(new ResourceLocation(DeepAether.MODID, type.getChestModelLocation()), "main"), ChestBoatModel::createBodyModel);
		}
	}
}