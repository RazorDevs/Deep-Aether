package teamrazor.deepaether.client.model;
/*
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
import teamrazor.deepaether.entity.AetherFishEntity;

	public class Modelcustom_model<Type extends AetherFishEntity> extends EntityModel<Type> {
		// This layer location should be baked with EntityRendererProvider.Context in the entity renderer and passed into this model's constructor
		public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(new ResourceLocation(DeepAetherMod.MODID, "aerglow_fish"), "main");
		private final ModelPart bone;

		public Modelcustom_model(ModelPart root) {
			this.bone = root.getChild("bone");
		}

		public static LayerDefinition createBodyLayer() {
			MeshDefinition meshdefinition = new MeshDefinition();
			PartDefinition partdefinition = meshdefinition.getRoot();

			PartDefinition bone = partdefinition.addOrReplaceChild("bone", CubeListBuilder.create(), PartPose.offset(0.0F, 22.0F, 4.0F));

			PartDefinition body = bone.addOrReplaceChild("body", CubeListBuilder.create().texOffs(0, 0).addBox(-1.0F, -2.0F, -4.0F, 2.0F, 4.0F, 9.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));

			body.addOrReplaceChild("right_fin", CubeListBuilder.create().texOffs(24, 1).addBox(-2.0F, 0.0F, -1.0F, 2.0F, 0.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-1.0F, 1.0F, -4.0F, 0.0F, 0.0F, -0.7854F));

			body.addOrReplaceChild("left_fin", CubeListBuilder.create().texOffs(24, 4).addBox(0.0F, 0.0F, -1.0F, 2.0F, 0.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(1.0F, 1.0F, -4.0F, 0.0F, 0.0F, 0.7854F));

			bone.addOrReplaceChild("head", CubeListBuilder.create().texOffs(13, 0).addBox(-1.0F, -2.0F, -3.0F, 2.0F, 4.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, -4.0F));

			bone.addOrReplaceChild("nose", CubeListBuilder.create().texOffs(0, 0).addBox(-1.0F, -2.0F, -1.0F, 2.0F, 3.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, -7.0F));

			bone.addOrReplaceChild("fin_left", CubeListBuilder.create(), PartPose.offset(1.0F, 1.0F, -4.0F));

			bone.addOrReplaceChild("fin_right", CubeListBuilder.create(), PartPose.offset(-1.0F, 1.0F, -4.0F));

			bone.addOrReplaceChild("fin_back", CubeListBuilder.create().texOffs(20, -6).addBox(0.0F, -2.0F, -3.0F, 0.0F, 2.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -2.0F, -2.0F));

			PartDefinition tail = bone.addOrReplaceChild("tail", CubeListBuilder.create(), PartPose.offset(0.0F, 0.0F, 5.0F));

			tail.addOrReplaceChild("tail_sub_0", CubeListBuilder.create().texOffs(22, 3).mirror().addBox(0.0F, -2.0F, 0.0F, 0.0F, 4.0F, 5.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offset(0.0F, 0.0F, 0.0F));

			return LayerDefinition.create(meshdefinition, 64, 64);
		}

		public void setupAnim(Type entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) { }

			@Override
			public void renderToBuffer(PoseStack poseStack, VertexConsumer buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
				this.bone.render(poseStack, buffer, packedLight, packedOverlay);
			}
		}

 */