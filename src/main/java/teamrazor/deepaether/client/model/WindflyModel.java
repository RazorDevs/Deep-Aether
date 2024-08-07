package teamrazor.deepaether.client.model;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.util.Mth;
import teamrazor.deepaether.entity.living.Windfly;

public class WindflyModel extends EntityModel<Windfly> {
    private final ModelPart body;
    private final ModelPart leftWing;
    private final ModelPart rightWing;
    private final ModelPart bb_main;

    public WindflyModel(ModelPart root) {
        this.body = root.getChild("body");
        this.leftWing = root.getChild("leftWing");
        this.rightWing = root.getChild("rightWing");
        this.bb_main = root.getChild("bb_main");
    }

    public static LayerDefinition createBodyLayer() {
        MeshDefinition meshdefinition = new MeshDefinition();
        PartDefinition partdefinition = meshdefinition.getRoot();

        partdefinition.addOrReplaceChild("body", CubeListBuilder.create().texOffs(0, 0).addBox(-1.0F, -1.25F, -6.0F, 3.0F, 3.0F, 11.0F, new CubeDeformation(0.0F)), PartPose.offset(-0.5F, 20.0F, 1.0F));

        partdefinition.addOrReplaceChild("leftWing", CubeListBuilder.create().texOffs(-34, 27).addBox(-1.25F, 0.0F, -8.25F, 23.0F, 0.0F, 34.0F, new CubeDeformation(0.0F)), PartPose.offset(1.5F, 19.0F, -0.75F));

        partdefinition.addOrReplaceChild("rightWing", CubeListBuilder.create().texOffs(-34, 27).mirror().addBox(-21.75F, 0.0F, -8.25F, 23.0F, 0.0F, 34.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offset(-1.5F, 19.0F, -0.75F));

        partdefinition.addOrReplaceChild("bb_main", CubeListBuilder.create().texOffs(28, 0).addBox(-1.5F, -2.25F, -5.0F, 3.0F, 3.0F, 11.0F, new CubeDeformation(0.0F))
                .texOffs(0, 14).addBox(-1.5F, -7.0F, -7.0F, 3.0F, 3.0F, 4.0F, new CubeDeformation(0.1F)), PartPose.offset(0.0F, 24.0F, 0.0F));

        return LayerDefinition.create(meshdefinition, 64, 64);
    }

    @Override
    public void setupAnim(Windfly entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
        float a = 1.0F;
        if(ageInTicks % 20.0F >= 10.0F) a = -1.0F;
        float move = Mth.lerp((ageInTicks % 10.0F) / 10.0F, -0.5F, 0.5F) * a;

        rightWing.zRot = move;
        leftWing.zRot = -move;
    }

    @Override
    public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
        body.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
        leftWing.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
        rightWing.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
        bb_main.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
    }
}
