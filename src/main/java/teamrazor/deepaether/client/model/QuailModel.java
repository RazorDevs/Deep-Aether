package teamrazor.deepaether.client.model;

import com.google.common.collect.ImmutableList;
import net.minecraft.client.model.AgeableListModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.util.Mth;
import teamrazor.deepaether.entity.living.quail.Quail;

public class QuailModel extends AgeableListModel<Quail> {
    private final ModelPart head;
    private final ModelPart leftWing;
    private final ModelPart rightWing;
    private final ModelPart rightLeg;
    private final ModelPart leftLeg;
    private final ModelPart body;

    public QuailModel(ModelPart root) {
        this.head = root.getChild("head");
        this.leftWing = root.getChild("left_wing");
        this.rightWing = root.getChild("right_wing");
        this.leftLeg = root.getChild("left_leg");
        this.rightLeg = root.getChild("right_leg");
        this.body = root.getChild("body");
    }

    public static LayerDefinition createBodyLayer() {
        MeshDefinition meshdefinition = new MeshDefinition();
        PartDefinition partdefinition = meshdefinition.getRoot();


        PartDefinition head = partdefinition.addOrReplaceChild("head", CubeListBuilder.create().texOffs(0, 14).addBox(-2.0F, -7.0F, -4.0F, 4.0F, 4.0F, 5.0F, new CubeDeformation(0.0F))
                .texOffs(13, 14).addBox(-2.0F, -5.0F, -5.0F, 4.0F, 2.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 16.0F, -4.0F));

        head.addOrReplaceChild("head_r1", CubeListBuilder.create().texOffs(6, 20).mirror().addBox(0.0F, -2.0F, -1.0F, 0.0F, 4.0F, 3.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(-2.0F, -6.0F, 1.0F, 0.0F, -0.3927F, 0.0F));

        head.addOrReplaceChild("head_r2", CubeListBuilder.create().texOffs(6, 20).addBox(0.0F, -2.0F, -1.0F, 0.0F, 4.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(2.0F, -6.0F, 1.0F, 0.0F, 0.3927F, 0.0F));

        head.addOrReplaceChild("head_r3", CubeListBuilder.create().texOffs(18, 0).addBox(-1.0F, -13.0F, -2.5F, 2.0F, 4.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 8.0F, 4.0F, 0.2618F, 0.0F, 0.0F));

        partdefinition.addOrReplaceChild("left_wing", CubeListBuilder.create().texOffs(12, 17).addBox(-0.49F, -0.5F, -3.0F, 1.0F, 5.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offset(3.5F, 13.5F, 0.0F));

        partdefinition.addOrReplaceChild("right_wing", CubeListBuilder.create().texOffs(12, 17).addBox(-0.51F, -0.5F, -3.0F, 1.0F, 5.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offset(-3.5F, 13.5F, 0.0F));

        partdefinition.addOrReplaceChild("left_leg", CubeListBuilder.create().texOffs(20, 14).addBox(-1.0F, 0.0F, -3.0F, 3.0F, 5.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offset(1.0F, 19.0F, 1.0F));

        partdefinition.addOrReplaceChild("right_leg", CubeListBuilder.create().texOffs(20, 14).addBox(-1.0F, 0.0F, -3.0F, 3.0F, 5.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offset(-2.0F, 19.0F, 1.0F));

        PartDefinition body = partdefinition.addOrReplaceChild("body", CubeListBuilder.create(), PartPose.offset(0.0F, 24.0F, 0.0F));

        body.addOrReplaceChild("body_r1", CubeListBuilder.create().texOffs(0, 20).mirror().addBox(0.0F, -3.0F, -1.0F, 0.0F, 6.0F, 3.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(-3.0F, -9.0F, 4.0F, 0.0F, -0.6109F, 0.0F));

        body.addOrReplaceChild("body_r2", CubeListBuilder.create().texOffs(0, 20).addBox(0.0F, -3.0F, -1.0F, 0.0F, 6.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(3.0F, -9.0F, 4.0F, 0.0F, 0.6109F, 0.0F));

        body.addOrReplaceChild("body_r3", CubeListBuilder.create().texOffs(0, 0).addBox(-3.0F, -4.0F, 5.0F, 6.0F, 8.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 1.5708F, 0.0F, 0.0F));
        return LayerDefinition.create(meshdefinition, 32, 32);
    }


    @Override
    public void setupAnim(Quail entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
        this.head.xRot = headPitch * 0.017453292F;
        this.head.yRot = netHeadYaw * 0.017453292F;
        this.rightLeg.xRot = Mth.cos(limbSwing * 0.6662F) * 1.4F * limbSwingAmount;
        this.leftLeg.xRot = Mth.cos(limbSwing * 0.6662F + 3.1415927F) * 1.4F * limbSwingAmount;
        this.rightWing.zRot = ageInTicks;
        this.leftWing.zRot = -ageInTicks;
    }

    @Override
    protected Iterable<ModelPart> headParts() {
        return ImmutableList.of(this.head);
    }

    @Override
    protected Iterable<ModelPart> bodyParts() {
        return ImmutableList.of(this.leftWing, this.rightWing, this.rightLeg, this.leftLeg, this.body);
    }
}