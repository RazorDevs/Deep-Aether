package io.github.razordevs.deep_aether.mixin.entity;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import com.llamalad7.mixinextras.sugar.Local;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import io.github.razordevs.deep_aether.networking.attachment.DAAttachments;
import io.github.razordevs.deep_aether.networking.attachment.DAPlayerAttachment;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.model.Model;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.HumanoidArmorLayer;
import net.minecraft.client.renderer.entity.layers.RenderLayer;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ArmorItem;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(HumanoidArmorLayer.class)
public abstract class HumanoidArmorLayerMixin <T extends LivingEntity, M extends HumanoidModel<T>, A extends HumanoidModel<T>> extends RenderLayer<T, M> {

    public HumanoidArmorLayerMixin(RenderLayerParent<T, M> pRenderer) {
        super(pRenderer);
    }

    @WrapOperation(method = "renderArmorPiece", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/renderer/entity/layers/HumanoidArmorLayer;renderModel(Lcom/mojang/blaze3d/vertex/PoseStack;Lnet/minecraft/client/renderer/MultiBufferSource;ILnet/minecraft/client/model/Model;ILnet/minecraft/resources/ResourceLocation;)V"))
    private void renderModel(HumanoidArmorLayer<T, M, A> instance, PoseStack pose, MultiBufferSource buffer, int packedLight, Model model, int color, ResourceLocation armorResource, Operation<Void> original, @Local(argsOnly = true) T pLivingEntity) {
        if(!pLivingEntity.isInvisible() && pLivingEntity.hasData(DAAttachments.PLAYER)) {
            DAPlayerAttachment attachment = pLivingEntity.getData(DAAttachments.PLAYER.get());
            if (attachment.hasSkyjadeSet() && attachment.isSkyjadeAbilityActivated()) {
                VertexConsumer vertexconsumer = buffer.getBuffer(RenderType.entityTranslucent(armorResource));
                model.renderToBuffer(pose, vertexconsumer, packedLight, OverlayTexture.NO_OVERLAY);
            }
            else original.call(instance, pose, buffer, packedLight, model, color, armorResource);
        }
        else original.call(instance, pose, buffer, packedLight, model, color, armorResource);
    }
}
