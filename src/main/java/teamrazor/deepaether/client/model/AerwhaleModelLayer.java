package teamrazor.deepaether.client.model;

import com.aetherteam.aether.client.renderer.entity.model.AerwhaleModel;
import com.aetherteam.aether.entity.passive.Aerwhale;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.RenderLayer;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Saddleable;
import teamrazor.deepaether.DeepAetherMod;

public class AerwhaleModelLayer extends RenderLayer<Aerwhale, AerwhaleModel> {

    RenderLayerParent<Aerwhale, AerwhaleModel> parent;
    private static final RenderType SLIDER_AWAKE_GLOW = RenderType.armorCutoutNoCull(new ResourceLocation(DeepAetherMod.MODID, "textures/entity/aerwhale_saddled.png"));

    public AerwhaleModelLayer(RenderLayerParent<Aerwhale, AerwhaleModel> parent) {
        super(parent);
        this.parent = parent;
    }

    @Override
    public void render(PoseStack poseStack, MultiBufferSource buffer, int packedLight, Aerwhale aerwhale, float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch) {
        if (((Saddleable) aerwhale).isSaddled() && !aerwhale.isInvisible()) {
            VertexConsumer consumer = buffer.getBuffer(SLIDER_AWAKE_GLOW);
            this.getParentModel().renderToBuffer(poseStack, consumer, packedLight, OverlayTexture.NO_OVERLAY, 1.0F, 1.0F, 1.0F, 1.0F);
        }
    }
}