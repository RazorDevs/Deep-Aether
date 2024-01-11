package teamrazor.deepaether.mixin;

import com.aetherteam.aether.client.renderer.entity.model.AerwhaleModel;
import com.aetherteam.aether.entity.passive.Aerwhale;
import com.llamalad7.mixinextras.sugar.Local;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.CubeListBuilder;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraft.client.model.geom.builders.PartDefinition;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(AerwhaleModel.class)
public abstract class AerwhaleModelMixin extends EntityModel<Aerwhale> {
    /*
    @Shadow(remap = false)
    @Final
    public ModelPart head;
    @Unique
    private final ModelPart leftChest = this.head.getChild("left_chest");
    @Unique
    private final ModelPart rightChest = this.head.getChild("right_chest");

     */

    @Inject(at = @At(value = "RETURN"), method = "createBodyLayer", remap = false)
    private static void createBodyLayer(CallbackInfoReturnable<LayerDefinition> cir, @Local(ordinal = 1) PartDefinition head) {
        CubeListBuilder chestBuilder = CubeListBuilder.create().texOffs(47, 49).addBox(-4.0F, 0.0F, -2.0F, 8.0F, 8.0F, 3.0F);
        head.addOrReplaceChild("left_chest", chestBuilder, PartPose.offsetAndRotation(6.0F, -8.0F, 0.0F, 0.0F, (-(float)Math.PI / 2F), 0.0F));
        head.addOrReplaceChild("right_chest", chestBuilder, PartPose.offsetAndRotation(-6.0F, -8.0F, 0.0F, 0.0F, ((float)Math.PI / 2F), 0.0F));
    }
}