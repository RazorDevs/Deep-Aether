package io.github.razordevs.deep_aether.screen;

import com.aetherteam.aether.client.gui.screen.inventory.AbstractRecipeBookScreen;
import io.github.razordevs.deep_aether.DeepAether;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;

public class CombinerScreen extends AbstractRecipeBookScreen<CombinerMenu, CombinerRecipeBookComponent> {
    //TODO: Make RecipeBook recipes work

    private static final ResourceLocation TEXTURE =
            ResourceLocation.fromNamespaceAndPath(DeepAether.MODID, "textures/gui/combiner_gui.png");

    public CombinerScreen(CombinerMenu menu, Inventory pPlayerInventory, Component pTitle) {
        super(menu, new CombinerRecipeBookComponent(), pPlayerInventory, pTitle);
    }

    @Override
    protected void init() {
        super.init();
        this.initScreen(20);
    }

    @Override
    protected void renderBg(GuiGraphics guiGraphics, float pPartialTick, int pMouseX, int pMouseY) {
        int left = this.getGuiLeft();
        int top = this.getGuiTop();

        guiGraphics.blit(TEXTURE, left, top, 0, 0, this.getXSize(), this.getYSize());

        renderProgressArrow(guiGraphics, left, top);
    }

    private void renderProgressArrow(GuiGraphics guiGraphics, int x, int y) {
        if(menu.isCrafting()) {
            guiGraphics.blit(TEXTURE, x + 63, y + 36, 176, 0, 51, menu.getScaledProgress());
        }
    }
}