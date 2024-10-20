package io.github.razordevs.deep_aether.client.renderer.curios;

import com.aetherteam.aether.client.renderer.AetherModelLayers;
import com.aetherteam.aether.client.renderer.accessory.GlovesRenderer;
import com.aetherteam.aether.client.renderer.accessory.model.GlovesModel;
import com.aetherteam.aether.item.accessories.gloves.GlovesItem;
import com.aetherteam.aether.mixin.mixins.client.accessor.PlayerModelAccessor;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import io.github.razordevs.deep_aether.networking.attachment.DAAttachments;
import io.github.razordevs.deep_aether.networking.attachment.DAPlayerAttachment;
import io.wispforest.accessories.api.client.AccessoryRenderer;
import io.wispforest.accessories.api.slot.SlotReference;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.model.PlayerModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.player.AbstractClientPlayer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.Sheets;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.client.renderer.texture.TextureAtlas;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.core.component.DataComponents;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.ItemTags;
import net.minecraft.util.FastColor;
import net.minecraft.world.entity.HumanoidArm;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.armortrim.ArmorTrim;
import net.minecraft.world.item.component.DyedItemColor;

public class SkyjadeGlovesRenderer extends GlovesRenderer {
    private final GlovesModel glovesModel;
    private final GlovesModel glovesTrimModel;
    private final GlovesModel glovesModelSlim;
    private final GlovesModel glovesTrimModelSlim;
    private final GlovesModel glovesFirstPerson;
    private final GlovesModel glovesTrimFirstPerson;
    private final TextureAtlas armorTrimAtlas;

    public SkyjadeGlovesRenderer() {
        this.glovesModel = new GlovesModel(Minecraft.getInstance().getEntityModels().bakeLayer(AetherModelLayers.GLOVES));
        this.glovesTrimModel = new GlovesModel(Minecraft.getInstance().getEntityModels().bakeLayer(AetherModelLayers.GLOVES_TRIM));
        this.glovesModelSlim = new GlovesModel(Minecraft.getInstance().getEntityModels().bakeLayer(AetherModelLayers.GLOVES_SLIM));
        this.glovesTrimModelSlim = new GlovesModel(Minecraft.getInstance().getEntityModels().bakeLayer(AetherModelLayers.GLOVES_TRIM_SLIM));
        this.glovesFirstPerson = new GlovesModel(Minecraft.getInstance().getEntityModels().bakeLayer(AetherModelLayers.GLOVES_FIRST_PERSON));
        this.glovesTrimFirstPerson = new GlovesModel(Minecraft.getInstance().getEntityModels().bakeLayer(AetherModelLayers.GLOVES_TRIM_FIRST_PERSON));
        this.armorTrimAtlas = Minecraft.getInstance().getModelManager().getAtlas(Sheets.ARMOR_TRIMS_SHEET);
    }


    /**
     * Modified version of {@link GlovesRenderer#render(ItemStack, SlotReference, PoseStack, EntityModel, MultiBufferSource, int, float, float, float, float, float, float)} 
     */
    @Override
    public <M extends LivingEntity> void render(ItemStack stack, SlotReference reference, PoseStack poseStack, EntityModel<M> entityModel, MultiBufferSource buffer, int packedLight, float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch) {
        GlovesItem glovesItem = (GlovesItem) stack.getItem();
        GlovesModel model = this.glovesModel;
        GlovesModel trimModel = this.glovesTrimModel;
        ResourceLocation texture = glovesItem.getGlovesTexture();

        if (entityModel instanceof PlayerModel<?> playerModel) {
            PlayerModelAccessor playerModelAccessor = (PlayerModelAccessor) playerModel;
            model = playerModelAccessor.aether$getSlim() ? this.glovesModelSlim : this.glovesModel;
            trimModel = playerModelAccessor.aether$getSlim() ? this.glovesTrimModelSlim : this.glovesTrimModel;
        }

        this.align(stack, reference, model, poseStack);
        this.align(stack, reference, trimModel, poseStack);

        //Added code
        /*if(livingEntity.hasData(DAAttachments.PLAYER)) {
            DAPlayerAttachment attachment = livingEntity.getData(DAAttachments.PLAYER);

            if(attachment.isSkyjadeAbilityActivated() && attachment.hasSkyjadeSet()) {
                VertexConsumer vertexConsumer = buffer.getBuffer(RenderType.entityTranslucent(texture));
                model.renderToBuffer(poseStack, vertexConsumer, packedLight, OverlayTexture.NO_OVERLAY, -1, 0.3F);

                GlovesModel finalTrimModel = trimModel;
                ArmorTrim.getTrim(livingEntity.level().registryAccess(), stack, true).ifPresent((trim) -> {
                    TextureAtlasSprite textureAtlasSprite = this.armorTrimAtlas.getSprite(trim.outerTexture(glovesItem.getMaterial()));
                    VertexConsumer trimConsumer = textureAtlasSprite.wrap(buffer.getBuffer(Sheets.armorTrimsSheet(trim.pattern().value().decal())));
                    finalTrimModel.renderToBuffer(poseStack, trimConsumer, packedLight, OverlayTexture.NO_OVERLAY, -1, 0.3F);
                });
                return;
            }
        }*/
        
        VertexConsumer vertexConsumer = buffer.getBuffer(RenderType.armorCutoutNoCull(texture));
        model.renderToBuffer(poseStack, vertexConsumer, packedLight, OverlayTexture.NO_OVERLAY);

        GlovesModel finalTrimModel = trimModel;

        ArmorTrim trim = stack.get(DataComponents.TRIM);
        if (trim != null) {
            TextureAtlasSprite textureAtlasSprite = this.armorTrimAtlas.getSprite(trim.outerTexture(glovesItem.getMaterial()));
            VertexConsumer trimConsumer = textureAtlasSprite.wrap(buffer.getBuffer(Sheets.armorTrimsSheet(trim.pattern().value().decal())));
            finalTrimModel.renderToBuffer(poseStack, trimConsumer, packedLight, OverlayTexture.NO_OVERLAY);
        }

        if (stack.hasFoil()) {
            model.renderToBuffer(poseStack, buffer.getBuffer(RenderType.armorEntityGlint()), packedLight, OverlayTexture.NO_OVERLAY);
        }
    }

    @Override
    public <M extends LivingEntity> void align(ItemStack stack, SlotReference reference, EntityModel<M> model, PoseStack poseStack) {
        if (model instanceof HumanoidModel<? extends LivingEntity> humanoidModel) {
            AccessoryRenderer.followBodyRotations(reference.entity(), (HumanoidModel<LivingEntity>) humanoidModel);
        }
    }
    /**
     * Modified version of {@link GlovesRenderer#renderFirstPerson(ItemStack, PoseStack, MultiBufferSource, int, AbstractClientPlayer, HumanoidArm)}
     */
    @Override
    public void renderFirstPerson(ItemStack stack, PoseStack poseStack, MultiBufferSource buffer, int packedLight, AbstractClientPlayer player, HumanoidArm arm) {
        GlovesModel model = this.glovesFirstPerson;
        GlovesModel trimModel = this.glovesTrimFirstPerson;

        GlovesItem glovesItem = (GlovesItem) stack.getItem();

        int color = -1;

        model.setAllVisible(false);
        model.attackTime = 0.0F;
        model.crouching = false;
        model.swimAmount = 0.0F;
        model.setupAnim(player, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F);

        ModelPart gloveArm = arm == HumanoidArm.RIGHT ? model.rightArm : model.leftArm;
        gloveArm.visible = true;
        gloveArm.xRot = 0.0F;
        //Added code
        /*if(player.hasData(DAAttachments.PLAYER)) {
            DAPlayerAttachment attachment = player.getData(DAAttachments.PLAYER);
            VertexConsumer consumer = buffer.getBuffer(RenderType.entityTranslucent(glovesItem.getGlovesTexture(), true));
            if (attachment.isSkyjadeAbilityActivated() && attachment.hasSkyjadeSet()) {
                poseStack.translate(0.0F,0.0F,-0.0001F); //Needed in order to avoid pixel collision
                gloveArm.render(poseStack, consumer, packedLight, OverlayTexture.NO_OVERLAY);

                ArmorTrim.getTrim(player.level().registryAccess(), stack, true).ifPresent((trim) -> {
                    trimModel.setAllVisible(false);
                    trimModel.attackTime = 0.0F;
                    trimModel.crouching = false;
                    trimModel.swimAmount = 0.0F;
                    trimModel.setupAnim(player, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F);

                    ModelPart gloveTrimArm = arm == HumanoidArm.RIGHT ? trimModel.rightArm : trimModel.leftArm;
                    gloveTrimArm.visible = true;
                    gloveTrimArm.xRot = 0.0F;

                    TextureAtlasSprite textureAtlasSprite = this.armorTrimAtlas.getSprite(trim.outerTexture(glovesItem.getMaterial()));
                    VertexConsumer trimConsumer = textureAtlasSprite.wrap(buffer.getBuffer(Sheets.armorTrimsSheet(trim.pattern().value().decal())));
                    gloveTrimArm.render(poseStack, trimConsumer, packedLight, OverlayTexture.NO_OVERLAY);
                });
                return;
            }
        }*/

        VertexConsumer consumer = buffer.getBuffer(RenderType.armorCutoutNoCull(glovesItem.getGlovesTexture()));
        gloveArm.render(poseStack, consumer, packedLight, OverlayTexture.NO_OVERLAY, color);

        ArmorTrim trim = stack.get(DataComponents.TRIM);
        if (trim != null) {
            trimModel.setAllVisible(false);
            trimModel.attackTime = 0.0F;
            trimModel.crouching = false;
            trimModel.swimAmount = 0.0F;
            trimModel.setupAnim(player, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F);

            ModelPart gloveTrimArm = arm == HumanoidArm.RIGHT ? trimModel.rightArm : trimModel.leftArm;
            gloveTrimArm.visible = true;
            gloveTrimArm.xRot = 0.0F;

            TextureAtlasSprite textureAtlasSprite = this.armorTrimAtlas.getSprite(trim.outerTexture(glovesItem.getMaterial()));
            VertexConsumer trimConsumer = textureAtlasSprite.wrap(buffer.getBuffer(Sheets.armorTrimsSheet(trim.pattern().value().decal())));
            gloveTrimArm.render(poseStack, trimConsumer, packedLight, OverlayTexture.NO_OVERLAY);
        }
        if (stack.hasFoil()) {
            gloveArm.render(poseStack, buffer.getBuffer(RenderType.armorEntityGlint()), packedLight, OverlayTexture.NO_OVERLAY);
        }
    }
}
