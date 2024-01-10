package teamrazor.deepaether.mixin;

import com.aetherteam.aether.client.renderer.entity.model.AerwhaleModel;
import com.aetherteam.aether.entity.passive.Aerwhale;
import com.llamalad7.mixinextras.sugar.Local;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(AerwhaleModel.class)
public abstract class AerwhaleModelMixin extends EntityModel<Aerwhale> {

    @Inject(at = @At("RETURN"), method = "createBodyLayer", remap = false)
    private static void AerwhaleRenderer(CallbackInfoReturnable<LayerDefinition> cir, @Local(name = "head") PartDefinition head) {

        CubeListBuilder cubelistbuilder = CubeListBuilder.create().texOffs(47, 46).addBox(-4.0F, 0.0F, -2.0F, 8.0F, 8.0F, 3.0F, CubeDeformation.NONE);
        head.getChild("middle_top").addOrReplaceChild("left_chest", cubelistbuilder, PartPose.offsetAndRotation(24.0F, 2.0F, 11.0F, 0.0F, (-(float)Math.PI / 2F), 0.0F));
        head.getChild("middle_top").addOrReplaceChild("right_chest", cubelistbuilder, PartPose.offsetAndRotation(0.0F, 2.0F, 11.0F, 0.0F, ((float)Math.PI / 2F), 0.0F));
    }
}