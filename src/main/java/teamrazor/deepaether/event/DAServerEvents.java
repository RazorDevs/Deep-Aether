package teamrazor.deepaether.event;

import com.aetherteam.aether.event.BossFightEvent;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import teamrazor.deepaether.DeepAether;
import teamrazor.deepaether.entity.IPlayerBossFight;

@Mod.EventBusSubscriber(modid = DeepAether.MODID,  value = Dist.DEDICATED_SERVER, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class DAServerEvents {
    @SubscribeEvent
    public static void onDungeonPlayerAdded(BossFightEvent.AddPlayer event) {
        ((IPlayerBossFight) event.getPlayer()).deep_Aether$setHasBeenHurt(false);
    }
}
