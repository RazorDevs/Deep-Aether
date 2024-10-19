package io.github.razordevs.deep_aether.screen;

import com.mojang.blaze3d.systems.RenderSystem;
import io.github.razordevs.deep_aether.DeepAether;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;

public class SnapshotScreen extends Screen {
    private static final ResourceLocation LOGO_LOCATION = ResourceLocation.fromNamespaceAndPath(DeepAether.MODID, "textures/gui/deep_aether.png");
    private Screen parentScreen;

    public SnapshotScreen(Screen parentScreen) {
        super(Component.literal("Deep Aether"));
        this.parentScreen = parentScreen;
    }

    // Add widgets and precomputed values here
    @Override
    protected void init() {
        super.init();
        Button button = this.addRenderableWidget(Button.builder(Component.literal("Got it!"), (onpress) -> this.onClose()).build());
        button.setPosition(this.width / 2 - 75, 350);
    }

    @Override
    public void onClose() {
        this.minecraft.setScreen(this.parentScreen);
        // Call last in case it interferes with the override
        super.onClose();
    }

    @Override
    public void render(GuiGraphics graphics, int pMouseX, int pMouseY, float pPartialTick) {
        this.renderBackground(graphics, pMouseX, pMouseY, pPartialTick);
        graphics.drawCenteredString(this.font, "Welcome.", this.width / 2, 260, 16777215);
        graphics.drawCenteredString(this.font, "This is an incomplete build of Deep Aether,", this.width / 2, 280, 16777215);
        graphics.drawCenteredString(this.font, "so remember to backup your worlds!", this.width / 2, 300, 16719360);

        super.render(graphics, pMouseX, pMouseY, pPartialTick);
    }

    @Override
    public void renderBackground(GuiGraphics graphics, int pMouseX, int pMouseY, float pPartialTick) {
        super.renderBackground(graphics, pMouseX, pMouseY, pPartialTick);
        RenderSystem.setShaderTexture(0, LOGO_LOCATION);
        graphics.blit(LOGO_LOCATION, this.width / 2 - 150, 50, 0.0F, 0.0F, 909 / 3, 567 / 3, 909 / 3, 567 / 3);
    }
}
