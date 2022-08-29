package teamrazor.deepaether.world;

import net.minecraftforge.event.world.BiomeLoadingEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import teamrazor.deepaether.DeepAetherMod;
import teamrazor.deepaether.world.Gen.DeepAetherModOreGeneration;
import teamrazor.deepaether.world.Gen.ModTreeGeneration;

@Mod.EventBusSubscriber(modid = DeepAetherMod.MODID)
public class DeepAetherModWorldEvents {
    @SubscribeEvent
    public static void biomeLoadingEvent(final BiomeLoadingEvent event) {
        DeepAetherModOreGeneration.generateOres(event);
        ModTreeGeneration.generateTrees(event);
    }
}