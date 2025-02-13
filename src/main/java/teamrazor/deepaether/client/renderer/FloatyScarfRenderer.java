package teamrazor.deepaether.client.renderer;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import teamrazor.deepaether.DeepAether;
import teamrazor.deepaether.client.model.ScarfModel;
import teamrazor.deepaether.networking.DeepAetherPlayer;
import top.theillusivec4.curios.api.SlotContext;
import top.theillusivec4.curios.api.client.ICurioRenderer;

import java.util.Optional;

public class FloatyScarfRenderer implements ICurioRenderer {
    private final ScarfModel scarfModel;

    public FloatyScarfRenderer() {
        this.scarfModel = new ScarfModel(Minecraft.getInstance().getEntityModels().bakeLayer(DAModelLayers.SCARF));
    }

    @Override
    public <T extends LivingEntity, M extends EntityModel<T>> void render(ItemStack stack, SlotContext reference, PoseStack poseStack, RenderLayerParent<T, M> renderLayerParent, MultiBufferSource buffer, int packedLight, float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch) {
        try {
            Player owner = (Player) reference.entity();
            Optional<DeepAetherPlayer> deepAetherPlayer = DeepAetherPlayer.get(owner).resolve();
            if(deepAetherPlayer.isPresent()) {
                if (deepAetherPlayer.get().isFloatyScarfWrappedAroundNeck()) {

                    ICurioRenderer.followBodyRotations(reference.entity(), this.scarfModel);
                    VertexConsumer vertexConsumer = buffer.getBuffer(RenderType.entityTranslucent(new ResourceLocation(DeepAether.MODID, "textures/models/accessory/pendant/scarf.png")));

                    if (owner.isCrouching()) {
                        poseStack.translate(0, 0.23, 0);
                    }

                    GentleWindRenderer.renderModel(this.scarfModel.head, poseStack, vertexConsumer, packedLight, OverlayTexture.NO_OVERLAY, deepAetherPlayer.get().getFloatyScarfColor0());
                    GentleWindRenderer.renderModel(this.scarfModel.body[0], poseStack, vertexConsumer, packedLight, OverlayTexture.NO_OVERLAY, deepAetherPlayer.get().getFloatyScarfColor1());
                    GentleWindRenderer.renderModel(this.scarfModel.body[1], poseStack, vertexConsumer, packedLight, OverlayTexture.NO_OVERLAY, deepAetherPlayer.get().getFloatyScarfColor2());
                    GentleWindRenderer.renderModel(this.scarfModel.body[2], poseStack, vertexConsumer, packedLight, OverlayTexture.NO_OVERLAY, deepAetherPlayer.get().getFloatyScarfColor3());
                    GentleWindRenderer.renderModel(this.scarfModel.body[3], poseStack, vertexConsumer, packedLight, OverlayTexture.NO_OVERLAY, deepAetherPlayer.get().getFloatyScarfColor4());
                }
            }
        } catch (ClassCastException ignored) {}
    }
}
