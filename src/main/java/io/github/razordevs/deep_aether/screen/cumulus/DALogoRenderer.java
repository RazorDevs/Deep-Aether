package io.github.razordevs.deep_aether.screen.cumulus;

import com.aetherteam.aether.client.gui.screen.menu.logo.AetherLogoRenderer;
import com.mojang.blaze3d.systems.RenderSystem;
import io.github.razordevs.deep_aether.DeepAether;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.resources.ResourceLocation;

public class DALogoRenderer extends AetherLogoRenderer {
    private static final ResourceLocation DA_LOGO = DeepAether.getResource("textures/gui/cumulus/deep_aether.png");
    private final boolean keepLogoThroughFade;
    private final boolean alignedLeft;

    public DALogoRenderer(boolean keepLogoThroughFade, boolean alignedLeft) {
        super(keepLogoThroughFade, alignedLeft);
        this.keepLogoThroughFade = keepLogoThroughFade;
        this.alignedLeft = alignedLeft;
    }

    public void renderLogo(GuiGraphics guiGraphics, int screenWidth, float transparency, int height) {
        guiGraphics.setColor(1.0F, 1.0F, 1.0F, this.keepLogoThroughFade ? 1.0F : transparency);
        RenderSystem.enableBlend();
        int logoX = this.alignedLeft ? 28 : (int) ((screenWidth / 2.0F - (190.0F / 2.0F)));
        int logoY = this.alignedLeft ? 25 : 36;
        guiGraphics.blit(DA_LOGO, logoX, logoY, 0, 0, 190, 38, 190, 38);
        guiGraphics.setColor(1.0F, 1.0F, 1.0F, 1.0F);
        RenderSystem.disableBlend();
    }
}
