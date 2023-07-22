package teamrazor.deepaether.client.renderer;

import com.aetherteam.aether.capability.player.AetherPlayer;
import com.mojang.blaze3d.platform.Window;
import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.*;
import net.minecraft.client.Minecraft;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.RegisterGuiOverlaysEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import teamrazor.deepaether.DeepAetherMod;
import teamrazor.deepaether.init.DAEffects;

@Mod.EventBusSubscriber(modid = DeepAetherMod.MODID, value = Dist.CLIENT, bus = Mod.EventBusSubscriber.Bus.MOD)
public class DAOverlays {
    private static final ResourceLocation TEXTURE_BLESSING_OVERLAY = new ResourceLocation(DeepAetherMod.MODID, "textures/overlays/blessing_overlay.png");

    @SubscribeEvent
    public static void registerOverlays(RegisterGuiOverlaysEvent event) {
        event.registerAboveAll("blessing_overlay", (gui, pStack, partialTicks, screenWidth, screenHeight) -> {
            Minecraft minecraft = Minecraft.getInstance();
            Window window = minecraft.getWindow();
            LocalPlayer player = minecraft.player;
            if (player != null) {
                AetherPlayer.get(player).ifPresent(handler -> renderBlessingOverlay(pStack, minecraft, window, handler));
            }
        });
    }


    private static void renderBlessingOverlay(PoseStack poseStack, Minecraft minecraft, Window window, AetherPlayer handler) {
        Player player = handler.getPlayer();
        MobEffectInstance blessing = player.getEffect(DAEffects.BLESSING.get());
        double effectScale = minecraft.options.screenEffectScale().get();
        if (blessing != null) {
            float blessingdur = (float) (blessing.getDuration() % 50) / 50;
            float alpha = (blessingdur * blessingdur) / 5.0F + 0.4F;
            renderVignette(poseStack, window, effectScale, alpha, TEXTURE_BLESSING_OVERLAY);
        }
    }



    private static void renderVignette(PoseStack poseStack, Window window, double effectScale, float alpha, ResourceLocation resource) {
        poseStack.pushPose();
        alpha *= Math.sqrt(effectScale);
        RenderSystem.disableDepthTest();
        RenderSystem.depthMask(false);
        RenderSystem.enableBlend();
        RenderSystem.defaultBlendFunc();
        RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, alpha);
        RenderSystem.setShader(GameRenderer::getPositionTexShader);
        RenderSystem.setShaderTexture(0, resource);
        Tesselator tessellator = Tesselator.getInstance();
        BufferBuilder bufferbuilder = tessellator.getBuilder();
        bufferbuilder.begin(VertexFormat.Mode.QUADS, DefaultVertexFormat.POSITION_TEX);
        bufferbuilder.vertex(0.0, window.getGuiScaledHeight(), -90.0).uv(0.0F, 1.0F).endVertex();
        bufferbuilder.vertex(window.getGuiScaledWidth(), window.getGuiScaledHeight(), -90.0).uv(1.0F, 1.0F).endVertex();
        bufferbuilder.vertex(window.getGuiScaledWidth(), 0.0, -90.0).uv(1.0F, 0.0F).endVertex();
        bufferbuilder.vertex(0.0, 0.0, -90.0).uv(0.0F, 0.0F).endVertex();
        tessellator.end();
        RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
        RenderSystem.disableBlend();
        RenderSystem.depthMask(true);
        RenderSystem.enableDepthTest();
        poseStack.popPose();
    }
}
