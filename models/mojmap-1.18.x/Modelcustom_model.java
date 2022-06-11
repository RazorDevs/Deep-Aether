// Made with Blockbench 4.2.4
// Exported for Minecraft version 1.17 - 1.18 with Mojang mappings
// Paste this class into your mod and generate all required imports

public class Modelcustom_model<T extends Entity> extends EntityModel<T> {
	// This layer location should be baked with EntityRendererProvider.Context in
	// the entity renderer and passed into this model's constructor
	public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(
			new ResourceLocation("modid", "custom_model"), "main");
	private final ModelPart body;
	private final ModelPart head;
	private final ModelPart nose;
	private final ModelPart fin_left;
	private final ModelPart fin_right;
	private final ModelPart fin_back;
	private final ModelPart tail;

	public Modelcustom_model(ModelPart root) {
		this.body = root.getChild("body");
		this.head = root.getChild("head");
		this.nose = root.getChild("nose");
		this.fin_left = root.getChild("fin_left");
		this.fin_right = root.getChild("fin_right");
		this.fin_back = root.getChild("fin_back");
		this.tail = root.getChild("tail");
	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition body = partdefinition.addOrReplaceChild("body", CubeListBuilder.create().texOffs(0, 0).addBox(
				-1.0F, -2.0F, 0.0F, 2.0F, 4.0F, 9.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 22.0F, 0.0F));

		PartDefinition right_fin = body.addOrReplaceChild("right_fin",
				CubeListBuilder.create().texOffs(24, 1).addBox(-2.0F, 0.0F, -1.0F, 2.0F, 0.0F, 2.0F,
						new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(-1.0F, 1.0F, 0.0F, 0.0F, 0.0F, -0.7854F));

		PartDefinition left_fin = body
				.addOrReplaceChild("left_fin",
						CubeListBuilder.create().texOffs(24, 4).addBox(0.0F, 0.0F, -1.0F, 2.0F, 0.0F, 2.0F,
								new CubeDeformation(0.0F)),
						PartPose.offsetAndRotation(1.0F, 1.0F, 0.0F, 0.0F, 0.0F, 0.7854F));

		PartDefinition head = partdefinition.addOrReplaceChild("head", CubeListBuilder.create().texOffs(13, 0).addBox(
				-1.0F, -2.0F, -3.0F, 2.0F, 4.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 22.0F, 0.0F));

		PartDefinition nose = partdefinition.addOrReplaceChild("nose", CubeListBuilder.create().texOffs(0, 0).addBox(
				-1.0F, -2.0F, -1.0F, 2.0F, 3.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 22.0F, -3.0F));

		PartDefinition fin_left = partdefinition.addOrReplaceChild("fin_left", CubeListBuilder.create(),
				PartPose.offset(0.0F, 24.0F, 0.0F));

		PartDefinition fin_right = partdefinition.addOrReplaceChild("fin_right", CubeListBuilder.create(),
				PartPose.offset(0.0F, 24.0F, 0.0F));

		PartDefinition fin_back = partdefinition.addOrReplaceChild("fin_back", CubeListBuilder.create().texOffs(20, -6)
				.addBox(0.0F, -2.0F, -1.0F, 0.0F, 2.0F, 6.0F, new CubeDeformation(0.0F)),
				PartPose.offset(0.0F, 20.0F, 0.0F));

		PartDefinition tail = partdefinition.addOrReplaceChild("tail", CubeListBuilder.create(),
				PartPose.offset(0.0F, 22.0F, 7.0F));

		PartDefinition tail_sub_0 = tail.addOrReplaceChild("tail_sub_0",
				CubeListBuilder.create().texOffs(22, 3).mirror()
						.addBox(0.0F, -4.0F, 9.0F, 0.0F, 4.0F, 5.0F, new CubeDeformation(0.0F)).mirror(false),
				PartPose.offset(0.0F, 2.0F, -7.0F));

		return LayerDefinition.create(meshdefinition, 64, 64);
	}

	@Override
	public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay,
			float red, float green, float blue, float alpha) {
		body.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
		head.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
		nose.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
		fin_left.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
		fin_right.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
		fin_back.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
		tail.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
	}

	public void setupAnim(T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw,
			float headPitch) {
		this.head.xRot = ageInTicks;
		this.nose.xRot = ageInTicks;
		this.fin_left.xRot = ageInTicks;
		this.tail.xRot = ageInTicks;
		this.fin_right.xRot = ageInTicks;
		this.body.xRot = ageInTicks;
		this.fin_back.xRot = ageInTicks;
	}
}