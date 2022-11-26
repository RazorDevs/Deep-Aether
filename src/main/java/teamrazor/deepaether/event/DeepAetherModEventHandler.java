package teamrazor.deepaether.event;




import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import teamrazor.deepaether.DeepAetherMod;


@Mod.EventBusSubscriber(modid = DeepAetherMod.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class DeepAetherModEventHandler {
    /*    @SubscribeEvent
        public static void init(final FMLCommonSetupEvent event) {
            event.enqueueWork(() -> {
                WoodType.register(DeepAetherModWoodTypes.YAGROOT);
                WoodType.register(DeepAetherModWoodTypes.ROSE);
                WoodType.register(DeepAetherModWoodTypes.CRUDEROOT);

            });
        }*/
}