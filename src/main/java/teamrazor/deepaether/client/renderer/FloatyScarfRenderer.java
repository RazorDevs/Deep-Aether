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
import teamrazor.deepaether.entity.GentleWind;
import teamrazor.deepaether.item.dungeon.brass.FloatyScarfItem;
import top.theillusivec4.curios.api.SlotContext;
import top.theillusivec4.curios.api.client.ICurioRenderer;

public class FloatyScarfRenderer implements ICurioRenderer {
    private final ScarfModel scarfModel;

    public FloatyScarfRenderer() {
        this.scarfModel = new ScarfModel(Minecraft.getInstance().getEntityModels().bakeLayer(DAModelLayers.SCARF));
    }

    @Override
    public <T extends LivingEntity, M extends EntityModel<T>> void render(ItemStack stack, SlotContext reference, PoseStack poseStack, RenderLayerParent<T, M> renderLayerParent, MultiBufferSource buffer, int packedLight, float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch) {
        GentleWind gentleWind = (GentleWind) FloatyScarfItem.getGentleWind(stack, reference.entity().level());

        if(gentleWind == null || !gentleWind.isWrappedAroundNeck()) return;

        ICurioRenderer.followBodyRotations(reference.entity(), this.scarfModel);
        VertexConsumer vertexConsumer = buffer.getBuffer(RenderType.entityTranslucent(new ResourceLocation(DeepAether.MODID, "textures/models/accessory/pendant/scarf.png")));

        Player owner = gentleWind.getOwner();
        if(owner != null && owner.isCrouching()) {
            poseStack.translate(0, 0.23, 0);
        }

        this.scarfModel.head.render(poseStack, vertexConsumer, packedLight, OverlayTexture.NO_OVERLAY);
        this.scarfModel.body[0].render(poseStack, vertexConsumer, packedLight, OverlayTexture.NO_OVERLAY);
        this.scarfModel.body[1].render(poseStack, vertexConsumer, packedLight, OverlayTexture.NO_OVERLAY);
        this.scarfModel.body[2].render(poseStack, vertexConsumer, packedLight, OverlayTexture.NO_OVERLAY);
        this.scarfModel.body[3].render(poseStack, vertexConsumer, packedLight, OverlayTexture.NO_OVERLAY);
    }
}
