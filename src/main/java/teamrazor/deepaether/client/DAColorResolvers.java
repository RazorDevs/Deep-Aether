package teamrazor.deepaether.client;


import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.fml.common.Mod;
import teamrazor.deepaether.DeepAetherMod;

@Mod.EventBusSubscriber(value = Dist.CLIENT, modid = DeepAetherMod.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class DAColorResolvers {
    /*
    private static final ColorResolver GOLDEN_GRASS = (biome, x, z) -> 16575076;

    @SubscribeEvent
    static void registerColorResolver(RegisterColorHandlersEvent.ColorResolvers event) {

        event.register(GOLDEN_GRASS);
    }

    @SubscribeEvent
    static void registerBlockColor(RegisterColorHandlersEvent.Block event) {
        Map<Block, BlockColor> map = new HashMap<>();
        Map<Holder.Reference<Block>, BlockColor> blockColors = ((BlockColorsAccessor) event.getBlockColors()).aether$getBlockColors();
        map.put(Blocks.GRASS, blockColors.get(ForgeRegistries.BLOCKS.getDelegateOrThrow(Blocks.GRASS)));
        map.put(Blocks.FERN, blockColors.get(ForgeRegistries.BLOCKS.getDelegateOrThrow(Blocks.FERN)));
        map.put(Blocks.TALL_GRASS, blockColors.get(ForgeRegistries.BLOCKS.getDelegateOrThrow(Blocks.TALL_GRASS)));
        map.put(Blocks.LARGE_FERN, blockColors.get(ForgeRegistries.BLOCKS.getDelegateOrThrow(Blocks.LARGE_FERN)));

        for (Map.Entry<Block, BlockColor> entry : map.entrySet()) {
            event.register(((state, level, pos, tintIndex) -> {
                if (level != null && pos != null) {
                    BlockPos newPos = state.hasProperty(DoublePlantBlock.HALF) ? (state.getValue(DoublePlantBlock.HALF) == DoubleBlockHalf.UPPER ? pos.below() : pos) : pos;
                    if (level.getBlockState(newPos.below()).is(DABlocks.GOLDEN_GRASS_BLOCK.get())) {
                        return level.getBlockTint(newPos, GOLDEN_GRASS);
                    }
                }
                return entry.getValue().getColor(state, level, pos, tintIndex);
            }), entry.getKey());
        }
    }
    */
}