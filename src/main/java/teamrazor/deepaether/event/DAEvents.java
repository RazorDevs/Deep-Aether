package teamrazor.deepaether.event;

import net.minecraft.client.Minecraft;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.ViewportEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import teamrazor.deepaether.DeepAetherMod;
import teamrazor.deepaether.init.DABlocks;

@Mod.EventBusSubscriber(modid = DeepAetherMod.MODID,  value = Dist.CLIENT, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class DAEvents {

    @SubscribeEvent
    public static void fogDensityEvent(ViewportEvent.RenderFog event) {
        Minecraft mc = Minecraft.getInstance();
        // Reduce lava fog from players when they have the lava vision enchantment
        Player player = mc.player;
        if (player != null) {
            if (mc.level != null) {
                BlockState state = mc.level.getBlockState(new BlockPos(player.blockPosition().above(1)));
                if (state.is(DABlocks.VIRULENT_QUICKSAND.get())) {
                    event.setNearPlaneDistance(0.5f);
                    event.setFarPlaneDistance(1.8f);
                    event.setCanceled(true);
                }
            }
        }
    }

    /*@SubscribeEvent
    public static void fogColorEvent(ViewportEvent.ComputeFogColor event) {
        Minecraft mc = Minecraft.getInstance();
        // Reduce lava fog from players when they have the lava vision enchantment
        Player player = mc.player;
        if (player != null) {
            if (mc.level != null) {
                BlockState state = mc.level.getBlockState(new BlockPos(player.blockPosition().above(1)));
                if (state.is(DABlocks.VIRULENT_QUICKSAND.get())) {
                    event.setBlue(0.4f);
                    event.setCanceled(true);
                }
            }
        }
    }*/
}
