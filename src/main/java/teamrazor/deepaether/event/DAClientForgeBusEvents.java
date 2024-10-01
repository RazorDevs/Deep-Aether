package teamrazor.deepaether.event;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.client.gui.screens.TitleScreen;
import net.minecraft.client.player.AbstractClientPlayer;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.LivingEntityRenderer;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Vec3i;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.block.state.BlockState;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.EventPriority;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.client.event.RenderPlayerEvent;
import net.neoforged.neoforge.client.event.ScreenEvent;
import net.neoforged.neoforge.client.event.ViewportEvent;
import teamrazor.deepaether.DeepAether;
import teamrazor.deepaether.init.DABlocks;
import teamrazor.deepaether.screen.SnapshotScreen;

@Mod.EventBusSubscriber(modid = DeepAether.MODID,  value = Dist.CLIENT, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class DAClientForgeBusEvents {
    private static boolean hasShownScreen = false;

    // Fog effect to mimic PowderSnow behaviour
    @SubscribeEvent
    public static void fogDensityEvent(ViewportEvent.RenderFog event) {
        Minecraft mc = Minecraft.getInstance();
        Player player = mc.player;
        if (player != null) {
            if (mc.level != null) {
                BlockState state = mc.level.getBlockState(new BlockPos(new Vec3i(player.getBlockX(), (int) player.getEyeY(), player.getBlockZ())));
                if (state.is(DABlocks.VIRULENT_QUICKSAND.get())) {
                    event.setNearPlaneDistance(0.5f);
                    event.setFarPlaneDistance(1.8f);
                    event.setCanceled(true);
                }
                if (state.is(DABlocks.POISON_BLOCK.get())) {
                    event.setNearPlaneDistance(0.5f);
                    event.setFarPlaneDistance(1.8f);
                    event.setCanceled(true);
                }
            }
        }
    }

    @SubscribeEvent(priority = EventPriority.LOW)
    public static void onGuiOpen(ScreenEvent.Opening event) {
        if(DeepAether.MOD_VERSION.contains("snapshot")){
            Screen screen = event.getScreen();
            if (screen instanceof TitleScreen title && !hasShownScreen) {
                event.setNewScreen(new SnapshotScreen(title));
                hasShownScreen = true;
            }
        }
    }
}
