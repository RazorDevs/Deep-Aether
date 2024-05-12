package teamrazor.deepaether.client.model;

import com.aetherteam.aether.client.renderer.entity.model.AerwhaleModel;
import com.legacy.lost_aether.LostContentConfig;
import com.legacy.lost_aether.client.models.AerwhaleModelOverride;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
/**
 * I blame this on LC's code, okay?
 * Also, the name on the class totally makes sense {@link AerwhaleModelOverride}
 * For registration see {@link teamrazor.deepaether.event.DAClientModBusEvents}
 **/
//TODO: UPDATE WHEN LOST AETHER CONTENT HAS PORTED TO 1.20.4

public interface AerwhaleModelOverrideOverrideLCCompat {
    /*
    static LayerDefinition createOverrideLayerButWithChest()
    {
        if (!(Boolean) LostContentConfig.CLIENT.updatedAerwhaleAnimations.get()) {
            return AerwhaleModel.createBodyLayer();
        } else {
            MeshDefinition meshdefinition = new MeshDefinition();
            PartDefinition partdefinition = meshdefinition.getRoot();
            PartDefinition head = partdefinition.addOrReplaceChild("head", CubeListBuilder.create().texOffs(0, 0).addBox(-12.0F, -9.0F, -14.0F, 24.0F, 18.0F, 28.0F), PartPose.offset(0.0F, 14.0F, 0.0F));
            head.addOrReplaceChild("head_belly", CubeListBuilder.create().texOffs(104, 0).addBox(-13.0F, 4.0F, -15.0F, 26.0F, 6.0F, 30.0F), PartPose.offset(0.0F, 0.0F, 0.0F));
            head.addOrReplaceChild("right_fin", CubeListBuilder.create().texOffs(104, 94).mirror().addBox(-20.0F, -1.5F, -7.0F, 19.0F, 3.0F, 14.0F).mirror(false), PartPose.offset(-10.0F, 3.5F, 11.0F));
            head.addOrReplaceChild("left_fin", CubeListBuilder.create().texOffs(104, 94).addBox(1.5F, -1.5F, -7.0F, 19.0F, 3.0F, 14.0F), PartPose.offset(9.5F, 3.5F, 11.0F));
            PartDefinition bodyMiddle = head.addOrReplaceChild("middle_body", CubeListBuilder.create().texOffs(0, 46).addBox(-11.0F, -5.0F, -1.0F, 22.0F, 14.0F, 25.0F), PartPose.offset(0.0F, -1.0F, 14.0F));
            bodyMiddle.addOrReplaceChild("middle_belly", CubeListBuilder.create().texOffs(104, 36).addBox(-12.0F, 5.0F, -15.0F, 24.0F, 6.0F, 26.0F), PartPose.offset(0.0F, -1.0F, 14.0F));
            bodyMiddle.addOrReplaceChild("middle_fin", CubeListBuilder.create().texOffs(0, 46).addBox(-1.0F, -3.5F, -4.0F, 2.0F, 7.0F, 8.0F), PartPose.offset(0.0F, -6.5F, 12.0F));
            PartDefinition bodyBack = bodyMiddle.addOrReplaceChild("back_body", CubeListBuilder.create().texOffs(0, 85).addBox(-10.5F, -8.3F, -1.5F, 17.0F, 10.0F, 22.0F), PartPose.offset(2.0F, 4.0F, 24.0F));
            bodyBack.addOrReplaceChild("back_belly", CubeListBuilder.create().texOffs(104, 68).addBox(-11.5F, -1.6F, 13.7F, 19.0F, 5.0F, 21.0F), PartPose.offset(0.0F, 2.3F, -13.2F));
            bodyBack.addOrReplaceChild("back_right_fin", CubeListBuilder.create().texOffs(170, 84).mirror().addBox(-10.0F, -1.5F, -4.0F, 15.0F, 3.0F, 24.0F).mirror(false), PartPose.offset(-7.0F, 1.0F, 19.0F));
            bodyBack.addOrReplaceChild("back_left_fin", CubeListBuilder.create().texOffs(170, 84).addBox(-5.0F, -1.5F, -4.0F, 15.0F, 3.0F, 24.0F), PartPose.offset(3.0F, 1.0F, 19.0F));
            CubeListBuilder cubelistbuilder = CubeListBuilder.create().texOffs(47, 46).addBox(-4.0F, 0.0F, -2.0F, 8.0F, 8.0F, 3.0F, CubeDeformation.NONE);
            bodyMiddle.addOrReplaceChild("left_chest", cubelistbuilder, PartPose.offsetAndRotation(12.0F, -5.0F, 11.0F, 0.0F, (-(float)Math.PI / 2F), 0.0F));
            bodyMiddle.addOrReplaceChild("right_chest", cubelistbuilder, PartPose.offsetAndRotation(-12.0F, -5.0F, 11.0F, 0.0F, ((float)Math.PI / 2F), 0.0F));
            return LayerDefinition.create(meshdefinition, 256, 128);
        }
    }

     */
}
