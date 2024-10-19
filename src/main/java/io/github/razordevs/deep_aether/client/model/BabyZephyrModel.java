package io.github.razordevs.deep_aether.client.model;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import io.github.razordevs.deep_aether.entity.living.BabyZephyr;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.util.Mth;

public class BabyZephyrModel extends EntityModel<BabyZephyr> {
    private final ModelPart body;
    private final ModelPart tail;

    public BabyZephyrModel(ModelPart root) {
        this.body = root.getChild("body");
        this.tail = body.getChild("tail");
    }

    public static LayerDefinition createBodyLayer() {
        MeshDefinition meshdefinition = new MeshDefinition();
        PartDefinition partdefinition = meshdefinition.getRoot();

        PartDefinition Body = partdefinition.addOrReplaceChild("body", CubeListBuilder.create().texOffs(0, 0).addBox(-6.0F, -4.5F, -8.0F, 12.0F, 9.0F, 16.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 17.5F, 0.0F, 0.0F, 3.1416F, 0.0F));

        Body.addOrReplaceChild("bb_main", CubeListBuilder.create().texOffs(28, 40).addBox(-8.0F, -10.0F, -6.0F, 2.0F, 7.0F, 12.0F, new CubeDeformation(0.0F))
                .texOffs(0, 38).addBox(6.0F, -10.0F, -6.0F, 2.0F, 7.0F, 12.0F, new CubeDeformation(0.0F))
                .texOffs(16, 38).addBox(-3.0F, -9.0F, -10.0F, 6.0F, 5.0F, 2.0F, new CubeDeformation(0.0F))
                .texOffs(33, 27).addBox(-4.0F, -2.0F, -6.0F, 8.0F, 2.0F, 11.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 6.5F, 0.0F));

        Body.addOrReplaceChild("tail", CubeListBuilder.create().texOffs(40, 0).addBox(-3.0F, -2.5F, -6.0F, 6.0F, 5.0F, 6.0F, new CubeDeformation(0.0F))
                .texOffs(59, 6).addBox(-2.0F, -1.5F, -11.0F, 4.0F, 3.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, -13.0F));

        return LayerDefinition.create(meshdefinition, 128, 128);
    }

    @Override
    public void setupAnim(BabyZephyr zephyr, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
        this.tail.yRot = Mth.sin(ageInTicks/2.5F)/2.0F;
    }

    @Override
    public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
        poseStack.scale(0.3F, 0.3F, 0.3F);
        poseStack.translate(0.0F, 1.667F, 0.0F);
        body.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
    }
}