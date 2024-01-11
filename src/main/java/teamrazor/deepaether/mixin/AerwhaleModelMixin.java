package teamrazor.deepaether.mixin;

import com.aetherteam.aether.client.renderer.entity.model.AerwhaleModel;
import com.aetherteam.aether.entity.passive.Aerwhale;
import com.llamalad7.mixinextras.sugar.Local;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.world.entity.Saddleable;
import org.checkerframework.checker.units.qual.A;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(AerwhaleModel.class)
public abstract class AerwhaleModelMixin extends EntityModel<Aerwhale> {

    @Shadow @Final public ModelPart head;

    @Unique
    ModelPart deep_Aether$leftChest;
    @Unique
    ModelPart deep_Aether$rightChest;

    @Inject(at = @At("TAIL"), remap = false, method = "<init>")
    private void AerwhaleModel(ModelPart root, CallbackInfo ci) {
        deep_Aether$leftChest = this.head.getChild("middle_top").getChild("left_chest");
        deep_Aether$rightChest = this.head.getChild("middle_top").getChild("right_chest");

    }

    @Inject(at = @At("RETURN"), method = "createBodyLayer", remap = false)
    private static void AerwhaleRenderer(CallbackInfoReturnable<LayerDefinition> cir, @Local(name = "head") PartDefinition head) {

        CubeListBuilder cubelistbuilder = CubeListBuilder.create().texOffs(47, 46).addBox(-4.0F, 0.0F, -2.0F, 8.0F, 8.0F, 3.0F, CubeDeformation.NONE);
        head.getChild("middle_top").addOrReplaceChild("left_chest", cubelistbuilder, PartPose.offsetAndRotation(24.0F, 2.0F, 11.0F, 0.0F, (-(float)Math.PI / 2F), 0.0F));
        head.getChild("middle_top").addOrReplaceChild("right_chest", cubelistbuilder, PartPose.offsetAndRotation(0.0F, 2.0F, 11.0F, 0.0F, ((float)Math.PI / 2F), 0.0F));
    }

    @Override
    public void setupAnim(Aerwhale aerwhale, float p_102619_, float p_102620_, float p_102621_, float p_102622_, float p_102623_) {
        if (((Saddleable)aerwhale).isSaddled() && !aerwhale.isInvisible()) {


            deep_Aether$leftChest.visible = true;
            deep_Aether$rightChest.visible = true;
        } else {
            deep_Aether$leftChest.visible = false;
            deep_Aether$rightChest.visible = false;
        }
    }
}
