package io.github.razordevs.deep_aether.mixin.entity;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import com.llamalad7.mixinextras.sugar.Local;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import io.github.razordevs.deep_aether.networking.attachment.DAAttachments;
import io.github.razordevs.deep_aether.networking.attachment.DAPlayerAttachment;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.LivingEntityRenderer;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.world.entity.LivingEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(LivingEntityRenderer.class)
public abstract class LivingEntityRendererMixin <T extends LivingEntity, M extends EntityModel<T>> extends EntityRenderer<T> implements RenderLayerParent<T, M> {

    protected LivingEntityRendererMixin(EntityRendererProvider.Context pContext) {
        super(pContext);
    }

    @WrapOperation(method = "render(Lnet/minecraft/world/entity/LivingEntity;FFLcom/mojang/blaze3d/vertex/PoseStack;Lnet/minecraft/client/renderer/MultiBufferSource;I)V",
            at = @At(value = "INVOKE", target = "Lnet/minecraft/client/model/EntityModel;renderToBuffer(Lcom/mojang/blaze3d/vertex/PoseStack;Lcom/mojang/blaze3d/vertex/VertexConsumer;IIFFFF)V"))
    private void renderToBuffer(EntityModel<T> instance, PoseStack poseStack, VertexConsumer vertexConsumer, int pPackedLight, int pPackedOverlay, float pRed, float pGreen, float pBlue, float pAlpha, Operation<Void> original, @Local(argsOnly = true) T pEntity, @Local(argsOnly = true) MultiBufferSource pBuffer) {
        if(!pEntity.isInvisible() && pEntity.hasData(DAAttachments.PLAYER)) {
            DAPlayerAttachment attachment = pEntity.getData(DAAttachments.PLAYER.get());
            if (attachment.hasSkyjadeSet() && attachment.isSkyjadeAbilityActivated()) {
                instance.renderToBuffer(poseStack, pBuffer.getBuffer(RenderType.entityTranslucent(this.getTextureLocation(pEntity))),
                        pPackedLight, pPackedOverlay);
            }
            else original.call(instance, poseStack, vertexConsumer, pPackedLight, pPackedOverlay, pRed, pBlue, pGreen, pAlpha);
        }
        else original.call(instance, poseStack, vertexConsumer, pPackedLight, pPackedOverlay, pRed, pBlue, pGreen, pAlpha);
    }
}
