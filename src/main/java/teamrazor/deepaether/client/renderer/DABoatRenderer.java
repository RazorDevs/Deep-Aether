package teamrazor.deepaether.client.renderer;

import com.google.common.collect.ImmutableMap;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.datafixers.util.Pair;
import com.mojang.math.Quaternion;
import com.mojang.math.Vector3f;
import net.minecraft.client.model.BoatModel;
import net.minecraft.client.model.ListModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.vehicle.Boat;
import teamrazor.deepaether.DeepAetherMod;
import teamrazor.deepaether.entity.DABoatEntity;

import java.util.Map;
import java.util.stream.Stream;

public class DABoatRenderer<T extends DABoatEntity> extends EntityRenderer<T> {
    private final Map<DABoatEntity.Type, Pair<ResourceLocation, ListModel<Boat>>> boatResources;

    public DABoatRenderer(EntityRendererProvider.Context renderer, boolean hasChest) {
        super(renderer);
        this.shadowRadius = 0.8f;
        this.boatResources = Stream.of(DABoatEntity.Type.values()).collect(ImmutableMap.toImmutableMap(type -> type, type ->
                Pair.of(type.getTexture(hasChest), this.createBoatModel(renderer, type, hasChest))));
    }

    private ListModel<Boat> createBoatModel(EntityRendererProvider.Context renderer, DABoatEntity.Type type, boolean hasChest) {
        ModelLayerLocation modelLayerLocation = hasChest ?
                new ModelLayerLocation(new ResourceLocation(DeepAetherMod.MODID, type.getChestModelLocation()), "main")
                : new ModelLayerLocation(new ResourceLocation(DeepAetherMod.MODID, type.getModelLocation()), "main");
        ModelPart modelPart = renderer.bakeLayer(modelLayerLocation);
        return hasChest ? new BoatModel(modelPart, true) : new BoatModel(modelPart, false);
    }

    @Override
    public void render(T entity, float entityYaw, float partialTicks, PoseStack matrixStack, MultiBufferSource buffer, int packedLight) {
        matrixStack.pushPose();
        matrixStack.translate(0.0D, 0.375D, 0.0D);
        matrixStack.mulPose(Vector3f.YP.rotationDegrees(180.0F - entityYaw));
        float f = (float)entity.getHurtTime() - partialTicks;
        float f1 = entity.getDamage() - partialTicks;
        if (f1 < 0.0F) {
            f1 = 0.0F;
        }
        if (f > 0.0F) {
            matrixStack.mulPose(Vector3f.XP.rotationDegrees(Mth.sin(f) * f * f1 / 10.0F * (float)entity.getHurtDir()));
        }
        float f2 = entity.getBubbleAngle(partialTicks);
        if (!Mth.equal(f2, 0.0F)) {
            matrixStack.mulPose(new Quaternion(new Vector3f(1.0F, 0.0F, 1.0F), entity.getBubbleAngle(partialTicks), true));
        }

        Pair<ResourceLocation, ListModel<Boat>> pair = this.boatResources.get(entity.getWoodType());
        ResourceLocation resourcelocation = pair.getFirst();
        ListModel<Boat> boatmodel = pair.getSecond();
        matrixStack.scale(-1.0F, -1.0F, 1.0F);
        matrixStack.mulPose(Vector3f.YP.rotationDegrees(90.0F));
        boatmodel.setupAnim(entity, partialTicks, 0.0F, -0.1F, 0.0F, 0.0F);
        VertexConsumer vertexconsumer = buffer.getBuffer(boatmodel.renderType(resourcelocation));
        boatmodel.renderToBuffer(matrixStack, vertexconsumer, packedLight, OverlayTexture.NO_OVERLAY, 1.0F, 1.0F, 1.0F, 1.0F);
        if (!entity.isUnderWater()) {
            VertexConsumer vertexconsumer1 = buffer.getBuffer(RenderType.waterMask());
            if (boatmodel instanceof BoatModel) {
                ((BoatModel) boatmodel).waterPatch().render(matrixStack, vertexconsumer1, packedLight, OverlayTexture.NO_OVERLAY);
            }
        }
        matrixStack.popPose();
        super.render(entity, entityYaw, partialTicks, matrixStack, buffer, packedLight);
    }

    @Override
    public ResourceLocation getTextureLocation(T boat) {
        return boatResources.get(boat.getWoodType()).getFirst();
    }
}