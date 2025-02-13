package teamrazor.deepaether.client.renderer;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.math.Axis;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import teamrazor.deepaether.DeepAether;
import teamrazor.deepaether.client.model.GentleWindModel;
import teamrazor.deepaether.entity.GentleWind;

public class GentleWindRenderer extends MobRenderer<GentleWind, GentleWindModel> {
    public GentleWindRenderer(EntityRendererProvider.Context context) {
        super(context, new GentleWindModel(context.bakeLayer(DAModelLayers.BABY_EOTS)), 0.5F);
    }

    @Override
    public ResourceLocation getTextureLocation(GentleWind pEntity) {
        return new ResourceLocation(DeepAether.MODID, "textures/entity/gentle_wind.png");
    }

    @Override
    public void render(GentleWind eots, float entityYaw, float partialTicks, PoseStack matrixStack, MultiBufferSource buffer, int packedLight) {
        matrixStack.pushPose();

        boolean flag = eots.isWrappedAroundNeck();
        eots.setInvisible(flag);
        this.model.root.visible = !flag;
        this.model.head.visible = !flag;
        for(ModelPart part : this.model.body) part.visible = !flag;

        this.model.attackTime = this.getAttackAnim(eots, partialTicks);
        float f = Mth.rotLerp(partialTicks, eots.yBodyRotO, eots.yBodyRot);
        float f1 = Mth.rotLerp(partialTicks, eots.yHeadRotO, eots.yHeadRot);
        float f2 = f1 - f;

        float f6 = Mth.lerp(partialTicks, eots.xRotO, eots.getXRot());
        if (isEntityUpsideDown(eots)) {
            f6 *= -1.0F;
            f2 *= -1.0F;
        }

        f2 = Mth.wrapDegrees(f2);

        float f8 = eots.getScale();
        matrixStack.scale(f8, f8, f8);
        float f9 = this.getBob(eots, partialTicks);
        this.setupRotations(eots, matrixStack, f9, f, partialTicks);
        matrixStack.scale(-1.0F, -1.0F, 1.0F);
        this.scale(eots, matrixStack, partialTicks);
        matrixStack.translate(0.0F, -1.501F, 0.0F);
        float f4 = 0.0F;
        float f5 = 0.0F;
        if (eots.isAlive()) {
            f4 = eots.walkAnimation.speed(partialTicks);
            f5 = eots.walkAnimation.position(partialTicks);

            if (f4 > 1.0F) {
                f4 = 1.0F;
            }
        }

        this.model.prepareMobModel(eots, f5, f4, partialTicks);
        this.model.setupAnim(eots, f5, f4, f9, f2, f6);
        RenderType rendertype = this.model.renderType(this.getTextureLocation(eots));
        VertexConsumer vertexconsumer = buffer.getBuffer(rendertype);
        int i = getOverlayCoords(eots, this.getWhiteOverlayProgress(eots, partialTicks));

        renderModel(this.model.head, matrixStack, vertexconsumer, packedLight, i, eots.getColor(0));
        renderModel(this.model.body[0], matrixStack, vertexconsumer, packedLight, i, eots.getColor(1));
        renderModel(this.model.body[1], matrixStack, vertexconsumer, packedLight, i, eots.getColor(2));
        renderModel(this.model.body[2], matrixStack, vertexconsumer, packedLight, i, eots.getColor(3));
        renderModel(this.model.body[3], matrixStack, vertexconsumer, packedLight, i, eots.getColor(4));

        matrixStack.popPose();

        if (this.shouldShowName(eots) && eots.hasCustomName() && !eots.isWrappedAroundNeck()) {
            this.renderNameTag(eots, eots.getDisplayName(), matrixStack, buffer, packedLight);
        }
    }

    public static void renderModel(ModelPart part, PoseStack matrixStack, VertexConsumer vertexConsumer, int packedLight, int i, int color) {
        float red = (float)(color >> 16 & 255) / 255.0F;
        float green = (float)(color >> 8 & 255) / 255.0F;
        float blue = (float)(color & 255) / 255.0F;
        part.render(matrixStack, vertexConsumer, packedLight, i, red, green, blue, 1.0F);
    }

    @Override
    protected void setupRotations(GentleWind pEntityLiving, PoseStack pPoseStack, float pAgeInTicks, float pRotationYaw, float pPartialTicks) {
        super.setupRotations(pEntityLiving, pPoseStack, pAgeInTicks, pRotationYaw, pPartialTicks);
        pPoseStack.mulPose(Axis.XP.rotationDegrees(Mth.lerp(pAgeInTicks, pEntityLiving.xRotO, pEntityLiving.getXRot())));
    }

    @Override
    protected float getBob(GentleWind eots, float partialTicks) {
        return partialTicks;
    }
}
