package teamrazor.deepaether.client.renderer;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.model.PlayerModel;
import net.minecraft.client.model.geom.ModelLayers;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.player.AbstractClientPlayer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.ItemRenderer;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.HumanoidArm;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import teamrazor.deepaether.item.dungeon.brass.WindShieldItem;
import teamrazor.deepaether.networking.DeepAetherPlayer;
import top.theillusivec4.curios.api.SlotContext;
import top.theillusivec4.curios.api.client.ICurioRenderer;

import java.util.Optional;

public class WindShieldRenderer implements ICurioRenderer {

    private final HumanoidModel<LivingEntity> shieldModel;
    public final HumanoidModel<LivingEntity> shieldModelArm;
    public final PlayerModel<LivingEntity> dummyArm;
    public final PlayerModel<LivingEntity> dummyArmSlim;

    public WindShieldRenderer() {
        this.shieldModel = new HumanoidModel<>(Minecraft.getInstance().getEntityModels().bakeLayer(DAModelLayers.WIND_SHIELD));
        this.shieldModelArm = new HumanoidModel<>(Minecraft.getInstance().getEntityModels().bakeLayer(DAModelLayers.WIND_SHIELD_ARM));
        this.dummyArm = new PlayerModel<>(Minecraft.getInstance().getEntityModels().bakeLayer(ModelLayers.PLAYER), false);
        this.dummyArmSlim = new PlayerModel<>(Minecraft.getInstance().getEntityModels().bakeLayer(ModelLayers.PLAYER_SLIM), true);
    }

    @Override
    public <T extends LivingEntity, M extends EntityModel<T>> void render(ItemStack stack, SlotContext reference, PoseStack poseStack, RenderLayerParent<T, M> renderLayerParent, MultiBufferSource buffer, int packedLight, float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch) {
        LivingEntity livingEntity = reference.entity();

        if (livingEntity instanceof Player player) {
            Optional<DeepAetherPlayer> aetherPlayerOptional = DeepAetherPlayer.get(player).resolve();
            if (aetherPlayerOptional.isPresent()) {
                if (aetherPlayerOptional.get().getWindShieldCooldown() > 0) {
                    return;
                }
            }
        }

        WindShieldItem shield = (WindShieldItem) stack.getItem();
        ResourceLocation texture = shield.getWindShieldTexture();
        HumanoidModel<LivingEntity> model = this.shieldModel;

        ICurioRenderer.followHeadRotations(reference.entity(), model.head);
        ICurioRenderer.followBodyRotations(reference.entity(), model);
        float f = (float)livingEntity.tickCount + partialTicks;
        VertexConsumer consumer = ItemRenderer.getArmorFoilBuffer(buffer, RenderType.energySwirl(texture, (f * 0.02F) % 1.0F, 0.0F), false, stack.isEnchanted());

        model.renderToBuffer(poseStack, consumer, packedLight, OverlayTexture.NO_OVERLAY, 1.0F, 1.0F, 1.0F, 1.0F);
    }

    public void renderFirstPerson(ItemStack stack, PoseStack poseStack, MultiBufferSource buffer, int packedLight, AbstractClientPlayer player, HumanoidArm arm) {

        Optional<DeepAetherPlayer> aetherPlayerOptional = DeepAetherPlayer.get(player).resolve();
        if (aetherPlayerOptional.isEmpty() || aetherPlayerOptional.get().getWindShieldCooldown() > 0) {
            return;
        }

        boolean isSlim = player.getModelName().equals("slim");
        if (!player.isInvisible()) {
            this.setupHand(isSlim ? this.dummyArmSlim : this.dummyArm, poseStack, buffer, packedLight, player, arm, isSlim);
        }
        this.setupShieldOnHand(stack, this.shieldModelArm, poseStack, buffer, packedLight, player, arm, isSlim);
    }

    private void setupShieldOnHand(ItemStack stack, HumanoidModel<LivingEntity> model, PoseStack poseStack, MultiBufferSource buffer, int packedLight, AbstractClientPlayer player, HumanoidArm arm, boolean isSlim) {
        this.setupModel(model, player);
        WindShieldItem shield = (WindShieldItem) stack.getItem();

        VertexConsumer consumer = ItemRenderer.getArmorFoilBuffer(buffer, RenderType.energySwirl(shield.getWindShieldTexture(), ((player.tickCount * 0.02F) % 1.0F), 0.0F), false, stack.isEnchanted());

        if (isSlim) {
            poseStack.translate((arm != HumanoidArm.LEFT ? 1.0F : -1.0F) * 0.05F, 0.0F, 0.0F);
        }

        if (arm == HumanoidArm.RIGHT) {
            this.renderShieldOnHand(model.rightArm, poseStack, packedLight, consumer);
        } else if (arm == HumanoidArm.LEFT) {
            this.renderShieldOnHand(model.leftArm, poseStack, packedLight, consumer);
        }
    }

    private void setupHand(PlayerModel<LivingEntity> model, PoseStack poseStack, MultiBufferSource buffer, int packedLight, AbstractClientPlayer player, HumanoidArm arm, boolean isSlim) {
        this.setupModel(model, player);


        VertexConsumer consumer = buffer.getBuffer(RenderType.entityTranslucent(player.getSkinTextureLocation()));
        if (isSlim) {
            poseStack.translate((arm != HumanoidArm.LEFT ? 1.0F : -1.0F) * -0.05F, 0.0F, 0.0F);
        }
        if (arm == HumanoidArm.RIGHT) {
            this.renderHand(model.rightArm, model.rightSleeve, poseStack, packedLight, consumer);
        } else if (arm == HumanoidArm.LEFT) {
            this.renderHand(model.leftArm, model.leftSleeve, poseStack, packedLight, consumer);
        }
    }

    private void setupModel(HumanoidModel<LivingEntity> model, AbstractClientPlayer player) {
        model.setAllVisible(false);
        model.attackTime = 0.0F;
        model.crouching = false;
        model.swimAmount = 0.0F;
        model.setupAnim(player, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F);
    }

    private void renderShieldOnHand(ModelPart shieldArm, PoseStack poseStack, int packedLight, VertexConsumer consumer) {
        shieldArm.visible = true;
        shieldArm.xRot = 0.0F;
        shieldArm.render(poseStack, consumer, packedLight, OverlayTexture.NO_OVERLAY, 1.0F, 1.0F, 1.0F, 1.0F);
    }

    private void renderHand(ModelPart dummyArm, ModelPart dummySleeve, PoseStack poseStack, int packedLight, VertexConsumer consumer) {
        dummyArm.visible = true;
        dummySleeve.visible = true;
        dummyArm.xRot = 0.0F;
        dummySleeve.xRot = 0.0F;
        dummyArm.render(poseStack, consumer, packedLight, OverlayTexture.NO_OVERLAY, 1.0F, 1.0F, 1.0F, 1.0F);
        dummySleeve.render(poseStack, consumer, packedLight, OverlayTexture.NO_OVERLAY, 1.0F, 1.0F, 1.0F, 1.0F);
    }
}