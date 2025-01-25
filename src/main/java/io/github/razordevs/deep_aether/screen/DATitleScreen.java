package io.github.razordevs.deep_aether.screen;

import com.aetherteam.aether.client.AetherSoundEvents;
import com.aetherteam.aether.client.gui.component.menu.AetherMenuButton;
import com.aetherteam.aether.client.gui.screen.menu.AetherTitleScreen;
import com.aetherteam.aether.client.gui.screen.menu.TitleScreenBehavior;
import com.aetherteam.aether.mixin.mixins.client.accessor.TitleScreenAccessor;
import com.aetherteam.cumulus.client.gui.screen.DynamicMenuButton;
import com.aetherteam.cumulus.mixin.mixins.client.accessor.SplashRendererAccessor;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
import io.github.razordevs.deep_aether.DeepAether;
import net.minecraft.Util;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.Renderable;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.Music;
import net.minecraft.util.Mth;
import net.neoforged.neoforge.client.ClientHooks;
import net.neoforged.neoforge.internal.BrandingControl;

public class DATitleScreen extends AetherTitleScreen implements TitleScreenBehavior {

    //TODO: for Winds and Storms part 2
    //TODO: Panorama pain

    private static final ResourceLocation PANORAMA_OVERLAY = ResourceLocation.withDefaultNamespace("textures/gui/title/background/panorama_overlay.png");
    private static final ResourceLocation DEEP_AETHER_LOGO = ResourceLocation.fromNamespaceAndPath(DeepAether.MODID, "textures/gui/title/deep_aether.png");
    public static final Music MENU = new Music(AetherSoundEvents.MUSIC_MENU, 20, 600, true);

    public DATitleScreen() {
        super();
    }

    public DATitleScreen(boolean alignedLeft) {
        super(alignedLeft);
    }

    @Override
    public void render(GuiGraphics guiGraphics, int mouseX, int mouseY, float partialTicks) {
        TitleScreenAccessor titleScreenAccessor = (TitleScreenAccessor) this;
        if (this.minecraft != null && titleScreenAccessor.aether$getSplash() == null) {
            titleScreenAccessor.aether$setSplash(this.minecraft.getSplashManager().getSplash());
        }
        float fadeAmount = handleFading(guiGraphics, this, titleScreenAccessor, PANORAMA, PANORAMA_OVERLAY, partialTicks);
        float scale = getScale(this, this.getMinecraft());
        this.setupLogo(guiGraphics, fadeAmount, scale);
        int roundedFadeAmount = Mth.ceil(fadeAmount * 255.0F) << 24;
        if ((roundedFadeAmount & -67108864) != 0) {
            ClientHooks.renderMainMenu(this, guiGraphics, this.font, this.width, this.height, roundedFadeAmount);
            if (titleScreenAccessor.aether$getSplash() != null) {
                SplashRendererAccessor splashRendererAccessor = (SplashRendererAccessor) titleScreenAccessor.aether$getSplash();
                if (splashRendererAccessor.cumulus$getSplash() != null && !splashRendererAccessor.cumulus$getSplash().isEmpty()) {
                    PoseStack poseStack = guiGraphics.pose();
                    float splashX = DATitleScreen.this.isAlignedLeft() ? 400.0F / scale : (float) DATitleScreen.this.width / 2 + (175 / scale);
                    float splashY = DATitleScreen.this.isAlignedLeft() ? 100.0F / scale : (int) (20 + (76 / scale));
                    poseStack.pushPose();
                    poseStack.translate(splashX, splashY, 0.0F);
                    poseStack.mulPose(Axis.ZP.rotationDegrees(-20.0F));
                    float textSize = 1.8F - Mth.abs(Mth.sin((float) (Util.getMillis() % 1000L) / 1000.0F * Mth.TWO_PI) * 0.1F);
                    textSize = textSize * (200.0F / scale) / (DATitleScreen.this.font.width(splashRendererAccessor.cumulus$getSplash()) + (64 / scale));
                    poseStack.scale(textSize, textSize, textSize);
                    guiGraphics.drawCenteredString(DATitleScreen.this.font, splashRendererAccessor.cumulus$getSplash(), 0, (int) (-16 / scale), 16776960 | roundedFadeAmount);
                    poseStack.popPose();
                }
            }

            if (this.isAlignedLeft()) {
                renderRightBranding(guiGraphics, this, this.font, roundedFadeAmount);
            } else {
                BrandingControl.forEachLine(true, true, (brandingLine, branding) ->
                        guiGraphics.drawString(this.font, branding, 2, this.height - (10 + brandingLine * (this.font.lineHeight + 1)), 16777215 | roundedFadeAmount)
                );
                BrandingControl.forEachAboveCopyrightLine((brandingLine, branding) ->
                        guiGraphics.drawString(this.font, branding, this.width - this.font.width(branding), this.height - (10 + (brandingLine + 1) * (this.font.lineHeight + 1)), 16777215 | roundedFadeAmount)
                );
            }
        }

        int xOffset = handleButtonVisibility(this, fadeAmount);
        for (Renderable renderable : this.renderables) {
            renderable.render(guiGraphics, mouseX, mouseY, partialTicks);
            if (renderable instanceof AetherMenuButton aetherButton) { // Smoothly shifts the Aether-styled buttons to the right slightly when hovered over.
                if (aetherButton.isMouseOver(mouseX, mouseY)) {
                    if (aetherButton.hoverOffset < 15) {
                        aetherButton.hoverOffset += 4;
                    }
                } else {
                    if (aetherButton.hoverOffset > 0) {
                        aetherButton.hoverOffset -= 4;
                    }
                }
            }
            if (renderable instanceof DynamicMenuButton dynamicMenuButton) {  // Increases the x-offset to the left for image buttons if there are menu buttons on the screen.
                if (dynamicMenuButton.enabled) {
                    xOffset -= 24;
                }
            }
        }
        handleImageButtons(this, xOffset);
    }

    private void setupLogo(GuiGraphics guiGraphics, float transparency, float scale) {
        int width = (int) (350 / scale);
        int height = (int) (76 / scale);
        int logoX = this.isAlignedLeft() ? (int) (10 + (18 / scale)) : (int) ((this.width / 2 - 175 / scale));
        int logoY = this.isAlignedLeft() ? (int) (15 + (10 / scale)) : (int) (25 + (10 / scale));
        guiGraphics.setColor(1.0F, 1.0F, 1.0F, transparency);
        guiGraphics.blit(DEEP_AETHER_LOGO, logoX, logoY, 0, 0, width, height, width, height);
        guiGraphics.setColor(1.0F, 1.0F, 1.0F, 1.0F);
    }
}
