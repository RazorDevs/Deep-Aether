package teamrazor.deepaether.screen;

import com.aetherteam.aether.client.gui.screen.inventory.AbstractRecipeBookScreen;
import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;
import teamrazor.deepaether.DeepAether;

public class CombinerScreen extends AbstractRecipeBookScreen<CombinerMenu, CombinerRecipeBookComponent> {
    private static final ResourceLocation TEXTURE =
            new ResourceLocation(DeepAether.MODID, "textures/gui/combiner_gui.png");

    public CombinerScreen(CombinerMenu menu, Inventory pPlayerInventory, Component pTitle) {
        super(menu, new CombinerRecipeBookComponent(), pPlayerInventory, pTitle);
    }

    @Override
    protected void init() {
        super.init();
        this.initScreen(0);
    }

    @Override
    protected void renderBg(GuiGraphics guiGraphics, float pPartialTick, int pMouseX, int pMouseY) {
        RenderSystem.setShader(GameRenderer::getPositionTexShader);
        RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
        RenderSystem.setShaderTexture(0, TEXTURE);
        int x = (width - imageWidth) / 2;
        int y = (height - imageHeight) / 2;

        guiGraphics.blit(TEXTURE, x, y, 0, 0, imageWidth, imageHeight);

        renderProgressArrow(guiGraphics, x, y);
    }

    private void renderProgressArrow(GuiGraphics guiGraphics, int x, int y) {
        if(menu.isCrafting()) {
            guiGraphics.blit(TEXTURE, x + 63, y + 36, 176, 0, 51, menu.getScaledProgress());
        }
    }

    @Override
    public void render(GuiGraphics guiGraphics, int mouseX, int mouseY, float delta) {
        renderBackground(guiGraphics, mouseX, mouseY, delta);
        super.render(guiGraphics, mouseX, mouseY, delta);
        renderTooltip(guiGraphics, mouseX, mouseY);
    }
}