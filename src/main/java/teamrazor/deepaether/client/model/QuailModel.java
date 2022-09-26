package teamrazor.deepaether.client.model;

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
import net.minecraft.util.Mth;
import net.minecraft.world.entity.LivingEntity;
import org.jetbrains.annotations.Nullable;
import software.bernie.geckolib3.core.event.predicate.AnimationEvent;
import software.bernie.geckolib3.core.processor.IBone;
import software.bernie.geckolib3.model.AnimatedGeoModel;
import software.bernie.geckolib3.model.provider.data.EntityModelData;
import teamrazor.deepaether.DeepAetherMod;
import teamrazor.deepaether.entity.QuailEntity;

public class QuailModel extends AnimatedGeoModel<QuailEntity> {

    @Override
    public ResourceLocation getModelResource(QuailEntity object) {
        return new ResourceLocation(DeepAetherMod.MODID, "geo/quail.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(QuailEntity object) {
        return new ResourceLocation(DeepAetherMod.MODID, "textures/entities/quail.png");
    }

    @Override
    public ResourceLocation getAnimationResource(QuailEntity animatable) {
        return new ResourceLocation(DeepAetherMod.MODID, "animations/quail.anim.json");
    }

    @Override
    public void setLivingAnimations(QuailEntity entity, Integer uniqueID, @Nullable AnimationEvent customPredicate) {
        super.setLivingAnimations(entity, uniqueID, customPredicate);

        IBone head = getAnimationProcessor().getBone("head");

        EntityModelData entityData = (EntityModelData)customPredicate.getExtraDataOfType(EntityModelData.class).get(0);
        head.setRotationX(entityData.headPitch * Mth.DEG_TO_RAD);
        head.setRotationY(entityData.netHeadYaw * Mth.DEG_TO_RAD);
    }
}