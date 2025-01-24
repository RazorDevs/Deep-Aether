package teamrazor.deepaether.client.model;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.world.entity.LivingEntity;

public class ScarfModel extends HumanoidModel<LivingEntity> {

    public final ModelPart head;
    public final ModelPart[] body = new ModelPart[4];

    public ScarfModel(ModelPart root) {
        super(root);
        this.head = root.getChild("head2");
        body[0] = root.getChild("body_0");
        body[1] = root.getChild("body_1");
        body[2] = root.getChild("body_2");
        body[3] = root.getChild("body_rot_3");

    }

    public static LayerDefinition createBodyLayer() {
        CubeDeformation cube = new CubeDeformation(0.6F);
        MeshDefinition meshdefinition = HumanoidModel.createMesh(cube, 0.0F);
        PartDefinition partdefinition = meshdefinition.getRoot();

        partdefinition.addOrReplaceChild("head2", CubeListBuilder.create().texOffs(0, 16).addBox(-2.0F, -1.5F, -1.0F, 4.0F, 4.0F, 4.0F, new CubeDeformation(0.0F))
                .texOffs(12, 0).addBox(-2.0F, 0.5F, -3.0F, 4.0F, 2.0F, 2.0F, new CubeDeformation(0.0F))
                .texOffs(16, 17).addBox(0.0F, -2.5F, 0.0F, 0.0F, 1.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offset(6.0F, -2.0F, -2.0F));

        PartDefinition body_1 = partdefinition.addOrReplaceChild("body_1", CubeListBuilder.create(), PartPose.offset(0.0F, -4.0F, -4.0F));

        PartDefinition body_rot_1 = body_1.addOrReplaceChild("body_rot_1", CubeListBuilder.create(), PartPose.offset(0.0F, 0.0F, 11.0F));

        body_rot_1.addOrReplaceChild("cube_r1", CubeListBuilder.create().texOffs(12, 4).addBox(-3.0F, -2.0F, -1.0F, 4.0F, 4.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(1.0F, 2.5F, 0.0F, 0.0F, -1.5708F, 0.0F));

        PartDefinition body_2 = partdefinition.addOrReplaceChild("body_2", CubeListBuilder.create(), PartPose.offset(-5.0F, -4.0F, -9.0F));

        PartDefinition body_rot_2 = body_2.addOrReplaceChild("body_rot_2", CubeListBuilder.create(), PartPose.offset(0.0F, 0.0F, 16.0F));

        body_rot_2.addOrReplaceChild("cube_r2", CubeListBuilder.create().texOffs(0, 8).addBox(-3.0F, -2.0F, 4.0F, 4.0F, 4.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(5.0F, 2.5F, 2.0F, 0.0F, -2.2253F, 0.0F));

        PartDefinition body_rot_3 = partdefinition.addOrReplaceChild("body_rot_3", CubeListBuilder.create(), PartPose.offset(0.0F, 21.0F, 8.0F));

        PartDefinition body_3 = body_rot_3.addOrReplaceChild("body_3", CubeListBuilder.create(), PartPose.offset(0.0F, 0.0F, -21.0F));

        body_3.addOrReplaceChild("cube_r3", CubeListBuilder.create().texOffs(0, 0).addBox(-3.0F, -2.0F, 9.0F, 4.0F, 4.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-7.0F, -22.5F, 24.0F, 0.0F, 3.1416F, 0.0F));

        PartDefinition body_0 = partdefinition.addOrReplaceChild("body_0", CubeListBuilder.create(), PartPose.offset(7.0F, -3.0F, 0.0F));

        PartDefinition body_rot_0 = body_0.addOrReplaceChild("body_rot_0", CubeListBuilder.create(), PartPose.offset(0.0F, 0.0F, 6.0F));

        body_rot_0.addOrReplaceChild("cube_r4", CubeListBuilder.create().texOffs(12, 12).addBox(-3.0F, -2.0F, -6.0F, 4.0F, 4.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-1.0F, 1.5F, -6.0F, 0.0F, 2.5307F, 0.0F));

        return LayerDefinition.create(meshdefinition, 32, 32);
    }

    @Override
    public void renderToBuffer(PoseStack p_102034_, VertexConsumer p_102035_, int p_102036_, int p_102037_, float p_102038_, float p_102039_, float p_102040_, float p_102041_) {
    }

    @Override
    public void setupAnim(LivingEntity entity, float limbSwing, float limbSwingAmount, float partialTicks, float netHeadYaw, float headPitch) {
    }
}
