package io.github.razordevs.deep_aether.client.model;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import io.github.razordevs.deep_aether.entity.living.boss.eots.EOTSSegment;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.util.Mth;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;


@OnlyIn(Dist.CLIENT)
public class EOTSSegmentModel extends EntityModel<EOTSSegment> {

    private final ModelPart body;
    private final ModelPart head;
    private final ModelPart bb_main;
    private final ModelPart bb_segment;
    private final ModelPart upperMouth;
    private final ModelPart lowerMouth;

    public EOTSSegmentModel(ModelPart root) {
        this.body = root.getChild("body");
        this.head = root.getChild("head");
        this.bb_main = root.getChild("bb_main");
        this.bb_segment = root.getChild("bb_segment");
        this.upperMouth = head.getChild("upperMouth");
        this.lowerMouth = head.getChild("lowerMouth");
    }

    public static LayerDefinition createBodyLayer() {
        MeshDefinition meshdefinition = new MeshDefinition();
        PartDefinition partdefinition = meshdefinition.getRoot();

        partdefinition.addOrReplaceChild("body", CubeListBuilder.create().texOffs(0, 0).addBox(-5.0F, -8.0F, 0.0F, 14.0F, 12.0F, 16.0F,
                new CubeDeformation(0.0F)), PartPose.offset(-2.0F, 18.0F, -8.0F));

        partdefinition.addOrReplaceChild("bb_main", CubeListBuilder.create().texOffs(26, 41).addBox(-8.0F, -13.0F, 0.0F, 3.0F, 10.0F, 10.0F, new CubeDeformation(0.0F))
                .texOffs(0, 38).addBox(5.0F, -13.0F, 0.0F, 3.0F, 10.0F, 10.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 24.0F, 0.0F));

        partdefinition.addOrReplaceChild("bb_segment", CubeListBuilder.create().texOffs(28, 28).addBox(-9.0F, -13.0F, -6.0F, 2.0F, 10.0F, 12.0F, new CubeDeformation(0.0F))
                .texOffs(0, 28).addBox(7.0F, -13.0F, -6.0F, 2.0F, 10.0F, 12.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 24.0F, 0.0F));

        PartDefinition head =  partdefinition.addOrReplaceChild("head", CubeListBuilder.create().texOffs(0, 0).addBox(-3.5F, -9.0F, -10.0F, 11.0F, 13.0F, 13.0F, new CubeDeformation(0.0F))
                .texOffs(42, 30).addBox(2.0F, -13.0F, -9.5F, 0.0F, 4.0F, 11.0F, new CubeDeformation(0.0F)), PartPose.offset(-2.0F, 18.0F, 8.0F));

        head.addOrReplaceChild("upperMouth", CubeListBuilder.create().texOffs(0, 26).addBox(-5.0F, -3.0F, -9.0F, 10.0F, 3.0F, 9.0F, new CubeDeformation(0.0F))
                .texOffs(0, 0).addBox(1.0F, -5.0F, -7.0F, 2.0F, 2.0F, 2.0F, new CubeDeformation(0.0F))
                .texOffs(0, 4).addBox(-3.0F, -5.0F, -7.0F, 2.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(2.0F, 1.0F, -10.0F));

        head.addOrReplaceChild("lowerMouth", CubeListBuilder.create().texOffs(29, 29).addBox(-3.0F, 0.0F, -9.0F, 10.0F, 3.0F, 9.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 1.0F, -10.0F));

        return LayerDefinition.create(meshdefinition, 128, 128);
    }

    @Override
    public void setupAnim(EOTSSegment entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
        if(entity.isControllingSegment()) {
            body.visible = false;
            bb_segment.visible = false;
            head.visible = true;
            bb_main.visible = true;
            upperMouth.visible = true;
            if(entity.isMouthOpen()) {
                if(upperMouth.xRot > -0.3F) {
                    float mouthRotation = Mth.lerp(ageInTicks * 0.01F,  upperMouth.xRot, -0.3F);
                    upperMouth.xRot = mouthRotation;
                    lowerMouth.xRot = -mouthRotation;
                }
            }
            else if(upperMouth.xRot < 0.0F) {
                float mouthRotation = Mth.lerp(ageInTicks * 0.01F,  upperMouth.xRot, 0.0F);
                upperMouth.xRot = mouthRotation;
                lowerMouth.xRot = -mouthRotation;
            }
        }
        else {
            body.visible = true;
            bb_segment.visible = true;
            head.visible = false;
            bb_main.visible = false;
            upperMouth.visible = false;
        }
    }

    @Override
    public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, int color) {
        body.render(poseStack, vertexConsumer, packedLight, packedOverlay, color);
        head.render(poseStack, vertexConsumer, packedLight, packedOverlay, color);
        bb_main.render(poseStack, vertexConsumer, packedLight, packedOverlay, color);
        bb_segment.render(poseStack, vertexConsumer, packedLight, packedOverlay, color);
    }
}