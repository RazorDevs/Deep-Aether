package io.github.razordevs.deep_aether.client.model;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import io.github.razordevs.deep_aether.entity.living.Venomite;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.util.Mth;

public class VenomiteModel extends EntityModel<Venomite> {

    private final ModelPart head;
    private final ModelPart body;
    private final ModelPart wingsfront;
    private final ModelPart leftFront;
    private final ModelPart rightFront;
    private final ModelPart wingsback;
    private final ModelPart rightBack;
    private final ModelPart leftBack;
    private final ModelPart tail;

    public VenomiteModel(ModelPart root) {
        this.head = root.getChild("head");
        this.body = root.getChild("body");
        this.wingsfront = root.getChild("wingsfront");
        this.leftFront = wingsfront.getChild("left");
        this.rightFront = wingsfront.getChild("right");
        this.wingsback = root.getChild("wingsback");
        this.rightBack = wingsback.getChild("right2");
        this.leftBack = wingsback.getChild("left2");
        this.tail = body.getChild("tail");
    }

    public static LayerDefinition createBodyLayer() {
        MeshDefinition meshdefinition = new MeshDefinition();
        PartDefinition partdefinition = meshdefinition.getRoot();

        PartDefinition head = partdefinition.addOrReplaceChild("head", CubeListBuilder.create().texOffs(17, 19).addBox(-3.0F, -2.0F, -4.0F, 6.0F, 3.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 19.0F, -4.0F));

        head.addOrReplaceChild("head_r1", CubeListBuilder.create().texOffs(38, 16).addBox(1.0F, -12.0F, -10.5F, 0.0F, 4.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 7.0F, 4.0F, 0.0F, 0.3054F, 0.0F));

        head.addOrReplaceChild("head_r2", CubeListBuilder.create().texOffs(38, 16).addBox(-1.0F, -12.0F, -10.5F, 0.0F, 4.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 7.0F, 4.0F, 0.0F, -0.3054F, 0.0F));

        PartDefinition body = partdefinition.addOrReplaceChild("body", CubeListBuilder.create().texOffs(0, 22).addBox(-2.0F, -3.0F, -4.0F, 4.0F, 4.6F, 4.0F, new CubeDeformation(0.0F))
                .texOffs(16, 26).addBox(-2.0F, -2.3F, 0.0F, 4.0F, 3.5F, 3.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 19.0F, 0.0F));

        body.addOrReplaceChild("body_r1", CubeListBuilder.create().texOffs(0, 6).addBox(0.25F, -0.4F, -2.0F, 0.0F, 2.4F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-2.0F, 2.0F, -2.0F, 0.0F, 0.0F, -0.3491F));

        body.addOrReplaceChild("body_r2", CubeListBuilder.create().texOffs(0, 4).addBox(-0.25F, -0.4F, -2.0F, 0.0F, 2.4F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(2.0F, 2.0F, -2.0F, 0.0F, 0.0F, 0.3491F));

        body.addOrReplaceChild("tail", CubeListBuilder.create().texOffs(32, 27).addBox(0.0F, -6.0F, -1.0F, 0.0F, 8.0F, 13.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 3.0F));

        body.addOrReplaceChild("bubble", CubeListBuilder.create().texOffs(13, 8).addBox(-3.0F, -1.0F, -1.6F, 6.0F, 3.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 2.6F));

        PartDefinition wingsfront = partdefinition.addOrReplaceChild("wingsfront", CubeListBuilder.create(), PartPose.offset(0.0F, 17.0F, -2.0F));

        wingsfront.addOrReplaceChild("left", CubeListBuilder.create().texOffs(0, 0).addBox(0.0F, 0.0F, -2.0F, 12.0F, 0.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(2.0F, 0.0F, 0.0F));

        wingsfront.addOrReplaceChild("right", CubeListBuilder.create().texOffs(0, 4).addBox(-12.0F, 0.0F, -2.0F, 12.0F, 0.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(-2.0F, 0.0F, 0.0F));

        PartDefinition wingsback = partdefinition.addOrReplaceChild("wingsback", CubeListBuilder.create(), PartPose.offset(0.0F, 18.0F, 2.0F));

        wingsback.addOrReplaceChild("right2", CubeListBuilder.create().texOffs(0, 19).addBox(-9.0F, 0.0F, -2.0F, 9.0F, 0.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offset(-2.0F, 0.0F, 0.0F));

        wingsback.addOrReplaceChild("left2", CubeListBuilder.create().texOffs(19, 16).addBox(0.0F, 0.0F, -2.0F, 9.0F, 0.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offset(2.0F, 0.0F, 0.0F));

        return LayerDefinition.create(meshdefinition, 64, 64);
    }

    @Override
    public void setupAnim(Venomite entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
        this.head.xRot = headPitch * 0.017453292F;
        this.head.yRot = netHeadYaw * 0.017453292F;


        float a = 1.0F;
        if(ageInTicks % 10.0F >= 5.0F) a = -1.0F;
        float move = Mth.lerp((ageInTicks % 5.0F) / 5.0F, -1.0F, 1.0F) * a;

        this.rightFront.zRot = move;
        this.leftFront.zRot = -move;
        this.rightBack.zRot = -move;
        this.leftBack.zRot = move;

        a = (ageInTicks % 10.0F) / 10.0F;
        if(ageInTicks % 20.0F >= 10.0F)
            this.tail.xRot = Mth.lerp(a, -0.1F, 0.25F);
        else this.tail.xRot = Mth.lerp(a, 0.25F, -0.1F);
    }

    @Override
    public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
        head.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
        body.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
        wingsfront.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
        wingsback.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
    }
}
