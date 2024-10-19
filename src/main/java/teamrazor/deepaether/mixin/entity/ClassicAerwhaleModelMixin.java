package teamrazor.deepaether.mixin.entity;

import com.aetherteam.aether.client.renderer.entity.model.ClassicAerwhaleModel;
import com.aetherteam.aether.entity.passive.Aerwhale;
import com.llamalad7.mixinextras.sugar.Local;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.CubeDeformation;
import net.minecraft.client.model.geom.builders.CubeListBuilder;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraft.client.model.geom.builders.PartDefinition;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import teamrazor.deepaether.entity.AerwhaleSaddleable;

@Mixin(ClassicAerwhaleModel.class)
public abstract class ClassicAerwhaleModelMixin extends EntityModel<Aerwhale> {
    @Unique
    ModelPart deep_Aether$leftChest;
    @Unique
    ModelPart deep_Aether$rightChest;

    @Inject(at = @At("TAIL"), remap = false, method = "<init>")
    private void AerwhaleModel(ModelPart root, CallbackInfo ci) {
        deep_Aether$leftChest = root.getChild("left_chest");
        deep_Aether$rightChest = root.getChild("right_chest");
    }

    @Inject(at = @At("RETURN"), method = "createBodyLayer", remap = false)
    private static void AerwhaleRenderer(CallbackInfoReturnable<LayerDefinition> cir, @Local(name = "partDefinition") PartDefinition partDefinition) {

        CubeListBuilder cubelistbuilder = CubeListBuilder.create().texOffs(38, 30).addBox(-4.0F, 0.0F, -2.0F, 8.0F, 8.0F, 3.0F, CubeDeformation.NONE);
        partDefinition.addOrReplaceChild("left_chest", cubelistbuilder, PartPose.offsetAndRotation(10.0F, -8.0F, 11.0F, 0.0F, (-(float)Math.PI / 2F), 0.0F));
        partDefinition.addOrReplaceChild("right_chest", cubelistbuilder, PartPose.offsetAndRotation(-13.0F, -8.0F, 11.0F, 0.0F, ((float)Math.PI / 2F), 0.0F));
    }

    @Inject(at = @At("TAIL"), method = "setupAnim(Lcom/aetherteam/aether/entity/passive/Aerwhale;FFFFF)V", remap = false)
    public void setupAnim(Aerwhale aerwhale, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, CallbackInfo ci) {
        if (((AerwhaleSaddleable)aerwhale).isSaddled() && !aerwhale.isInvisible()) {
            deep_Aether$leftChest.visible = true;
            deep_Aether$rightChest.visible = true;
        } else {
            deep_Aether$leftChest.visible = false;
            deep_Aether$rightChest.visible = false;
        }
    }

    @Inject(at = @At("TAIL"), method = "renderToBuffer")
    public void renderToBuffer(PoseStack poseStack, VertexConsumer consumer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha, CallbackInfo ci) {
        this.deep_Aether$leftChest.render(poseStack, consumer, packedLight, packedOverlay, red, green, blue, alpha);
        this.deep_Aether$rightChest.render(poseStack, consumer, packedLight, packedOverlay, red, green, blue, alpha);
    }
}
