package teamrazor.deepaether.event;

import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.fml.common.Mod;
import teamrazor.deepaether.DeepAetherMod;

@Mod.EventBusSubscriber(modid = DeepAetherMod.MODID,  value = Dist.DEDICATED_SERVER, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class DAServerEvents {
    /*@SubscribeEvent
    public static void squashRandomizer(BlockEvent.CropGrowEvent event) {
        if (event.getState().is(DABlocks.ATTACHED_SQUASH_STEM.get())) {
            if (event.getLevel().getBlockState(event.getPos().north()).is(DABlocks.BLUE_SQUASH.get())) {
                if(new Random().nextBoolean()) event.getLevel().setBlock(event.getPos().north(), DABlocks.GREEN_SQUASH.get().defaultBlockState(), 1);
            }
            if (event.getLevel().getBlockState(event.getPos().south()).is(DABlocks.BLUE_SQUASH.get())) {
                if(new Random().nextBoolean()) event.getLevel().setBlock(event.getPos().south(), DABlocks.GREEN_SQUASH.get().defaultBlockState(), 1);
            }
            if (event.getLevel().getBlockState(event.getPos().east()).is(DABlocks.BLUE_SQUASH.get())) {
                if(new Random().nextBoolean()) event.getLevel().setBlock(event.getPos().east(), DABlocks.GREEN_SQUASH.get().defaultBlockState(), 1);
            }
            if (event.getLevel().getBlockState(event.getPos().west()).is(DABlocks.BLUE_SQUASH.get())) {
                if(new Random().nextBoolean()) event.getLevel().setBlock(event.getPos().west(), DABlocks.GREEN_SQUASH.get().defaultBlockState(), 1);
            }
        }
    }*/
}
