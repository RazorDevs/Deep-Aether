package teamrazor.deepaether.client.model;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Entity;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;
import teamrazor.deepaether.DeepAether;
import teamrazor.deepaether.entity.living.boss.eots.EOTSSegment;


@OnlyIn(Dist.CLIENT)
public class EOTSSegmentModel extends EntityModel<EOTSSegment> {
    public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(new ResourceLocation(DeepAether.MODID, "eots_segment_head"), "main");
    private final ModelPart body;
    private final ModelPart head;
    private final ModelPart upperMouth;
    public EOTSSegmentModel(ModelPart root) {
        this.body = root.getChild("body");
        this.head = root.getChild("head");
        this.upperMouth = head.getChild("upperMouth");
    }

    public static LayerDefinition createBodyLayer() {
        MeshDefinition meshdefinition = new MeshDefinition();
        PartDefinition partdefinition = meshdefinition.getRoot();

        partdefinition.addOrReplaceChild("body", CubeListBuilder.create().texOffs(0, 0).addBox(-5.0F, -6.0F, 0.0F, 10.0F, 12.0F, 16.0F, new CubeDeformation(0.0F))
                .texOffs(35, 13).addBox(0.0F, -11.0F, 0.5F, 0.0F, 5.0F, 15.0F, new CubeDeformation(0.0F)), PartPose.offset(-2.0F, 18.0F, -8.0F));

        PartDefinition head =  partdefinition.addOrReplaceChild("head", CubeListBuilder.create().texOffs(0, 28).addBox(-5.5F, -7.0F, -12.0F, 11.0F, 14.0F, 12.0F, new CubeDeformation(0.0F))
                .texOffs(52, 12).addBox(0.0F, -12.0F, -11.5F, 0.0F, 5.0F, 11.0F, new CubeDeformation(0.0F))
                .texOffs(46, 33).addBox(-5.0F, 3.0F, -19.0F, 10.0F, 3.0F, 7.0F, new CubeDeformation(0.0F)), PartPose.offset(-2.0F, 18.0F, 8.0F));

        head.addOrReplaceChild("upperMouth", CubeListBuilder.create().texOffs(36, 0).addBox(-7.0F, -6.0F, -43.0F, 10.0F, 3.0F, 7.0F, new CubeDeformation(0.0F))
                .texOffs(0, 4).addBox(-1.0F, -8.0F, -41.0F, 2.0F, 2.0F, 2.0F, new CubeDeformation(0.0F))
                .texOffs(0, 4).addBox(-5.0F, -8.0F, -41.0F, 2.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(2.0F, 6.0F, 24.0F));

        return LayerDefinition.create(meshdefinition, 128, 128);
    }

    @Override
    public void setupAnim(EOTSSegment entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
        if(entity.isControllingSegment()) {
            body.visible = false;
            head.visible = true;
            upperMouth.visible = true;
        }
        else {
            body.visible = true;
            head.visible = false;
            upperMouth.visible = false;
        }

    }

    @Override
    public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
        body.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
        head.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
    }
}