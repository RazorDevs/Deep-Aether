package teamrazor.deepaether.init;

import net.minecraft.client.model.BoatModel;
import net.minecraft.client.model.ChestBoatModel;
import net.minecraft.client.model.PlayerModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.builders.CubeDeformation;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraft.client.renderer.blockentity.HangingSignRenderer;
import net.minecraft.client.renderer.blockentity.SignRenderer;
import net.minecraft.client.renderer.entity.ThrownItemRenderer;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import teamrazor.deepaether.DeepAether;
import teamrazor.deepaether.client.model.*;
import teamrazor.deepaether.client.renderer.*;
import teamrazor.deepaether.entity.DABoatEntity;


@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class DAEntityRenderers {
	@SubscribeEvent
	public static void registerEntityRenderers(EntityRenderersEvent.RegisterRenderers event) {
		event.registerEntityRenderer(DAEntities.AETHER_FISH.get(), AetherFishRenderer::new);
		event.registerEntityRenderer(DAEntities.QUAIL.get(), QuailRenderer::new);
		event.registerEntityRenderer(DAEntities.VENOMITE.get(), VenomiteRenderer::new);
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
		event.registerLayerDefinition(DAModelLayers.EOTS_SEGMENT_CLASSIC, ClassicEOTSSegmentModel::createBodyLayer);
		event.registerLayerDefinition(DAModelLayers.BABY_EOTS, BabyEotsModel::createBodyLayer);
		event.registerLayerDefinition(DAModelLayers.VENOMITE_BUBBLE, VenomiteBubbleModel::createBodyLayer);
		event.registerLayerDefinition(DAModelLayers.BABY_ZEPHYR, BabyZephyrModel::createBodyLayer);

		event.registerLayerDefinition(DAModelLayers.WIND_SHIELD, () -> LayerDefinition.create(PlayerModel.createMesh(new CubeDeformation(1.1F), false), 64, 64));
		event.registerLayerDefinition(DAModelLayers.WIND_SHIELD_SLIM, () -> LayerDefinition.create(PlayerModel.createMesh(new CubeDeformation(1.15F), true), 64, 64));
		event.registerLayerDefinition(DAModelLayers.WIND_SHIELD_ARM, () -> LayerDefinition.create(PlayerModel.createMesh(new CubeDeformation(0.4F), false), 64, 64));

		event.registerLayerDefinition(DAModelLayers.SCARF, ScarfModel::createBodyLayer);

		for (DABoatEntity.Type type : DABoatEntity.Type.values()) {
			event.registerLayerDefinition(new ModelLayerLocation(new ResourceLocation(DeepAether.MODID, type.getModelLocation()), "main"), BoatModel::createBodyModel);
			event.registerLayerDefinition(new ModelLayerLocation(new ResourceLocation(DeepAether.MODID, type.getChestModelLocation()), "main"), ChestBoatModel::createBodyModel);
		}

	}
}