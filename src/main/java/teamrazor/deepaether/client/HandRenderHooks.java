package teamrazor.deepaether.client;

import com.aetherteam.aether.capability.player.AetherPlayer;
import com.aetherteam.aether.item.EquipmentUtil;
import com.aetherteam.aether.mixin.mixins.client.accessor.ItemInHandRendererAccessor;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
import net.minecraft.client.player.AbstractClientPlayer;
import net.minecraft.client.renderer.ItemInHandRenderer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.util.Mth;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.HumanoidArm;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import teamrazor.deepaether.client.renderer.WindShieldRenderer;
import teamrazor.deepaether.item.dungeon.brass.WindShieldItem;
import top.theillusivec4.curios.api.CuriosApi;
import top.theillusivec4.curios.api.client.CuriosRendererRegistry;
import top.theillusivec4.curios.api.client.ICurioRenderer;

import javax.annotation.Nullable;

/**
 * [CODE COPY] {@link com.aetherteam.aether.client.event.hooks.HandRenderHooks}
 */
public class HandRenderHooks {
    public static void renderWindShieldHandOverlay(ItemInHandRenderer itemInHandRenderer, @Nullable AbstractClientPlayer player, InteractionHand hand, float pitch, float swingProgress, float equippedProgress, PoseStack poseStack, MultiBufferSource buffer, int packedLight) {
        if (player != null) {
            AetherPlayer.get(player).ifPresent((aetherPlayer) -> {
                if (!aetherPlayer.isWearingInvisibilityCloak()) { // Check for Invisibility Cloak.
                    EquipmentUtil.findFirstCurio(player, (item) -> item.getItem() instanceof WindShieldItem).ifPresent((slotResult) -> {
                        String identifier = slotResult.slotContext().identifier();
                        int id = slotResult.slotContext().index();
                        ItemStack itemStack = slotResult.stack();
                        CuriosApi.getCuriosInventory(player).ifPresent(handler -> handler.getStacksHandler(identifier).ifPresent(stacksHandler -> {
                            if (stacksHandler.getRenders().get(id)) { // Check if Shield of Repulsion is visible.
                                CuriosRendererRegistry.getRenderer(itemStack.getItem()).ifPresent((renderer) -> {
                                    if (renderer instanceof WindShieldRenderer shieldRenderer) {
                                        ItemStack heldItem = hand == InteractionHand.MAIN_HAND ? ((ItemInHandRendererAccessor) itemInHandRenderer).aether$getMainHandItem() : ((ItemInHandRendererAccessor) itemInHandRenderer).aether$getOffHandItem();
                                        renderArmWithItem(itemInHandRenderer, shieldRenderer, itemStack, player, heldItem, hand, pitch, swingProgress, equippedProgress, poseStack, buffer, packedLight);
                                    }
                                });
                            }
                        }));
                    });
                }
            });
        }
    }

    public static void renderArmWithItem(ItemInHandRenderer itemInHandRenderer, ICurioRenderer renderer, ItemStack glovesStack, AbstractClientPlayer player, ItemStack heldItem, InteractionHand hand, float pitch, float swingProgress, float equippedProgress, PoseStack poseStack, MultiBufferSource buffer, int combinedLight) {
        if (!player.isScoping()) {
            boolean isMainHand = hand == InteractionHand.MAIN_HAND;
            HumanoidArm humanoidarm = isMainHand ? player.getMainArm() : player.getMainArm().getOpposite();
            poseStack.pushPose();
            if (heldItem.isEmpty()) {
                if (isMainHand) {
                    renderPlayerArm(renderer, glovesStack, player, poseStack, buffer, combinedLight, swingProgress, equippedProgress, humanoidarm);
                }
            } else if (heldItem.is(Items.FILLED_MAP)) {
                if (isMainHand && ((ItemInHandRendererAccessor) itemInHandRenderer).aether$getOffHandItem().isEmpty()) {
                    renderTwoHandedMap(itemInHandRenderer, renderer, glovesStack, player, heldItem, poseStack, buffer, combinedLight, swingProgress, equippedProgress, pitch);
                } else {
                    renderOneHandedMap(itemInHandRenderer, renderer, glovesStack, player, heldItem, poseStack, buffer, combinedLight, swingProgress, equippedProgress, humanoidarm);
                }
            }
            poseStack.popPose();
        }
    }

    public static void renderPlayerArm(ICurioRenderer renderer, ItemStack glovesStack, AbstractClientPlayer player, PoseStack poseStack, MultiBufferSource buffer, int combinedLight, float swingProgress, float equippedProgress, HumanoidArm arm) {
        boolean isSlim = player.getModelName().equals("slim");
        boolean flag = arm != HumanoidArm.LEFT;
        float f = flag ? 1.0F : -1.0F;
        float f1 = Mth.sqrt(swingProgress);
        float f2 = -0.3F * Mth.sin(f1 * Mth.PI);
        float f3 = 0.4F * Mth.sin(f1 * Mth.TWO_PI);
        float f4 = -0.4F * Mth.sin(swingProgress * Mth.PI);
        poseStack.translate(f * (f2 + 0.64F), f3 - 0.6F + equippedProgress * -0.6F, f4 - 0.72F);
        poseStack.mulPose(Axis.YP.rotationDegrees(f * 45.0F));
        float f5 = Mth.sin(swingProgress * swingProgress * Mth.PI);
        float f6 = Mth.sin(f1 * Mth.PI);
        poseStack.mulPose(Axis.YP.rotationDegrees(f * f6 * 70.0F));
        poseStack.mulPose(Axis.ZP.rotationDegrees(f * f5 * -20.0F));
        poseStack.translate(f * -1.0F, 3.6F, 3.5F);
        poseStack.mulPose(Axis.ZP.rotationDegrees(f * 120.0F));
        poseStack.mulPose(Axis.XP.rotationDegrees(200.0F));
        poseStack.mulPose(Axis.YP.rotationDegrees(f * -135.0F));
        float offset = 5.6F;
        if (isSlim) {
            offset = 5.65F;
        }
        poseStack.translate((f * offset) - 0.0025, 0.0025, -0.0025);
        ((WindShieldRenderer) renderer).renderFirstPerson(glovesStack, poseStack, buffer, combinedLight, player, arm);
    }

    public static void renderTwoHandedMap(ItemInHandRenderer itemInHandRenderer, ICurioRenderer renderer, ItemStack glovesStack, AbstractClientPlayer player, ItemStack heldItem, PoseStack poseStack, MultiBufferSource buffer, int combinedLight, float swingProgress, float equippedProgress, float pitch) {
        float f = Mth.sqrt(swingProgress);
        float f1 = -0.2F * Mth.sin(swingProgress * Mth.PI);
        float f2 = -0.4F * Mth.sin(f * Mth.PI);
        poseStack.translate(0.0F, -f1 / 2.0F, f2);
        float f3 = ((ItemInHandRendererAccessor) itemInHandRenderer).callCalculateMapTilt(pitch);
        poseStack.translate(0.0F, 0.04F + equippedProgress * -1.2F + f3 * -0.5F, -0.72F);
        poseStack.mulPose(Axis.XP.rotationDegrees(f3 * -85.0F));

        poseStack.pushPose();
        poseStack.mulPose(Axis.YP.rotationDegrees(90.0F));
        renderMapHand(renderer, glovesStack, player, poseStack, buffer, combinedLight, HumanoidArm.RIGHT);
        renderMapHand(renderer, glovesStack, player, poseStack, buffer, combinedLight, HumanoidArm.LEFT);
        poseStack.popPose();

        float f4 = Mth.sin(f * Mth.PI);
        poseStack.mulPose(Axis.XP.rotationDegrees(f4 * 20.0F));
        poseStack.scale(2.0F, 2.0F, 2.0F);
        ((ItemInHandRendererAccessor) itemInHandRenderer).callRenderMap(poseStack, buffer, combinedLight, heldItem);
    }

    public static void renderOneHandedMap(ItemInHandRenderer itemInHandRenderer, ICurioRenderer renderer, ItemStack glovesStack, AbstractClientPlayer player, ItemStack heldItem, PoseStack poseStack, MultiBufferSource buffer, int combinedLight, float swingProgress, float equippedProgress, HumanoidArm arm) {
        float f = arm == HumanoidArm.RIGHT ? 1.0F : -1.0F;
        poseStack.translate(f * 0.125F, -0.125F, 0.0F);

        poseStack.pushPose();
        poseStack.mulPose(Axis.ZP.rotationDegrees(f * 10.0F));
        renderPlayerArm(renderer, glovesStack, player, poseStack, buffer, combinedLight, swingProgress, equippedProgress, arm);
        poseStack.popPose();

        poseStack.pushPose();
        poseStack.translate(f * 0.51F, -0.08F + equippedProgress * -1.2F, -0.75F);
        float f1 = Mth.sqrt(swingProgress);
        float f2 = Mth.sin(f1 * Mth.PI);
        float f3 = -0.5F * f2;
        float f4 = 0.4F * Mth.sin(f1 * Mth.TWO_PI);
        float f5 = -0.3F * Mth.sin(swingProgress * Mth.PI);
        poseStack.translate(f * f3, f4 - 0.3F * f2, f5);
        poseStack.mulPose(Axis.XP.rotationDegrees(f2 * -45.0F));
        poseStack.mulPose(Axis.YP.rotationDegrees(f * f2 * -30.0F));
        ((ItemInHandRendererAccessor) itemInHandRenderer).callRenderMap(poseStack, buffer, combinedLight, heldItem);
        poseStack.popPose();
    }

    public static void renderMapHand(ICurioRenderer renderer, ItemStack glovesStack, AbstractClientPlayer player, PoseStack poseStack, MultiBufferSource buffer, int combinedLight, HumanoidArm arm) {
        poseStack.pushPose();
        float f = arm == HumanoidArm.RIGHT ? 1.0F : -1.0F;
        poseStack.mulPose(Axis.YP.rotationDegrees(92.0F));
        poseStack.mulPose(Axis.XP.rotationDegrees(45.0F));
        poseStack.mulPose(Axis.ZP.rotationDegrees(f * -41.0F));
        poseStack.translate(f * 0.3F, -1.1F, 0.45F);
        ((WindShieldRenderer) renderer).renderFirstPerson(glovesStack, poseStack, buffer, combinedLight, player, arm);
        poseStack.popPose();
    }
}