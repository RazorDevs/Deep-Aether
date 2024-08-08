package teamrazor.deepaether.client.renderer;

import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.client.event.RegisterGuiOverlaysEvent;
import teamrazor.deepaether.DeepAether;
import teamrazor.deepaether.DeepAetherConfig;
import teamrazor.deepaether.item.gear.stratus.StratusAbility;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class DAOverlays {

    public static final ResourceLocation STRATUS_COOLDOWN_1 = new ResourceLocation(DeepAether.MODID, "hud/stratus/stratus_cooldown_1");
    public static final ResourceLocation STRATUS_COOLDOWN_2 = new ResourceLocation(DeepAether.MODID, "hud/stratus/stratus_cooldown_2");
    public static final ResourceLocation STRATUS_COOLDOWN_3 = new ResourceLocation(DeepAether.MODID, "hud/stratus/stratus_cooldown_3");

    @SubscribeEvent
    public static void registerOverlays(RegisterGuiOverlaysEvent event) {
        event.registerAboveAll(new ResourceLocation(DeepAether.MODID, "stratus"), (gui, guiGraphics, partialTick, screenWidth, screenHeight) -> {
            Minecraft minecraft = Minecraft.getInstance();
            LocalPlayer player = minecraft.player;
            if (player != null && !player.isSpectator() && DeepAetherConfig.COMMON.stratus_dash_cooldown.get() > 0) {
                if (StratusAbility.coolDown > 0)
                    renderStratusCooldown(guiGraphics, screenWidth, screenHeight);
            }
        });
    }

    private static void renderStratusCooldown(GuiGraphics guiGraphics, int screenWidth, int screenHeight) {
        RenderSystem.enableBlend();
        stratusCooldown(guiGraphics, screenWidth - DeepAetherConfig.CLIENT.stratus_cooldown_indicator_x_position.get(),screenHeight - DeepAetherConfig.CLIENT.stratus_colldown_indicator_y_position.get());
        RenderSystem.disableBlend();
    }

    private static void stratusCooldown(GuiGraphics guiGraphics, int pX, int pY) {
        float m =  (StratusAbility.coolDown / DeepAetherConfig.COMMON.stratus_dash_cooldown.get());
        ResourceLocation sprite;
        if(m > 0.667)
            sprite = STRATUS_COOLDOWN_1;
        else if(m > 0.333)
            sprite = STRATUS_COOLDOWN_2;
        else sprite = STRATUS_COOLDOWN_3;

        guiGraphics.blitSprite(sprite, pX, pY, 16, 16);
    }
}
