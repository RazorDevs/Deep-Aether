package teamrazor.deepaether.deepaether.client.model;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.client.model.geom.builders.PartDefinition;
import net.minecraft.client.model.geom.builders.MeshDefinition;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraft.client.model.geom.builders.CubeListBuilder;
import net.minecraft.client.model.geom.builders.CubeDeformation;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.EntityModel;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.blaze3d.vertex.PoseStack;
import teamrazor.deepaether.DeepAetherMod;
import teamrazor.deepaether.entity.QuailEntity;

public class QuailModel<Type extends QuailEntity> extends EntityModel<Type> {
    // This layer location should be baked with EntityRendererProvider.Context in the entity renderer and passed into this model's constructor
    public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(new ResourceLocation(DeepAetherMod.MODID, "quail"), "main");
    private final ModelPart bone;

    public QuailModel(ModelPart root) {
        this.bone = root.getChild("bone");
    }

    public static LayerDefinition createBodyLayer() {
        MeshDefinition meshdefinition = new MeshDefinition();
        PartDefinition partdefinition = meshdefinition.getRoot();

        PartDefinition bone = partdefinition.addOrReplaceChild("bone", CubeListBuilder.create(), PartPose.offset(0.0F, 24.0F, 0.0F));

        bone.addOrReplaceChild("head", CubeListBuilder.create().texOffs(0, 0).addBox(0.0F, 2.0F, 1.0F, 3.0F, 3.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -9.0F, -4.0F));

        bone.addOrReplaceChild("bill", CubeListBuilder.create().texOffs(14, 0).addBox(0.0F, 4.0F, 0.0F, 3.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -9.0F, -4.0F));

        bone.addOrReplaceChild("chin", CubeListBuilder.create(), PartPose.offset(0.0F, -9.0F, -4.0F));

        bone.addOrReplaceChild("body", CubeListBuilder.create().texOffs(0, 9).addBox(0.0F, -2.0F, -6.0F, 3.0F, 5.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -8.0F, 0.0F, 1.5708F, 0.0F, 0.0F));

        bone.addOrReplaceChild("left_wing", CubeListBuilder.create().texOffs(24, 13).addBox(0.0F, 0.0F, -1.0F, 1.0F, 2.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offset(3.0F, -5.0F, 0.0F));

        bone.addOrReplaceChild("right_wing", CubeListBuilder.create().texOffs(24, 13).addBox(-1.0F, 0.0F, -1.0F, 1.0F, 2.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -5.0F, 0.0F));

        bone.addOrReplaceChild("left_leg", CubeListBuilder.create().texOffs(26, 0).addBox(3.0F, 3.0F, -3.0F, 2.0F, 2.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offset(-1.0F, -5.0F, 1.0F));

        bone.addOrReplaceChild("right_leg", CubeListBuilder.create().texOffs(22, 7).addBox(-3.0F, 3.0F, -3.0F, 2.0F, 2.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offset(2.0F, -5.0F, 1.0F));

        return LayerDefinition.create(meshdefinition, 64, 32);
    }

    public void setupAnim(Type entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) { }

    @Override
    public void renderToBuffer(PoseStack poseStack, VertexConsumer buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
        this.bone.render(poseStack, buffer, packedLight, packedOverlay);
    }
}
