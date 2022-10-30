package teamrazor.deepaether.event;


import net.minecraft.world.level.block.state.properties.WoodType;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import teamrazor.deepaether.DeepAetherMod;
import teamrazor.deepaether.blockentity.DeepAetherModWoodTypes;

@Mod.EventBusSubscriber(modid = DeepAetherMod.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class DeepAetherModEventHandler {
    @SubscribeEvent
    public static void init(final FMLCommonSetupEvent event) {
        event.enqueueWork(() -> {
            WoodType.register(DeepAetherModWoodTypes.YAGROOT);
            WoodType.register(DeepAetherModWoodTypes.ROSE);
            WoodType.register(DeepAetherModWoodTypes.CRUDEROOT);

        });
    }
}