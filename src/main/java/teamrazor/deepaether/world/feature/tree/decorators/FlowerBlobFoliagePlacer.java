package teamrazor.deepaether.world.feature.tree.decorators;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.core.BlockPos;
import net.minecraft.util.RandomSource;
import net.minecraft.util.valueproviders.IntProvider;
import net.minecraft.world.level.LevelSimulatedReader;
import net.minecraft.world.level.block.LeavesBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.feature.TreeFeature;
import net.minecraft.world.level.levelgen.feature.configurations.TreeConfiguration;
import net.minecraft.world.level.levelgen.feature.foliageplacers.BlobFoliagePlacer;
import net.minecraft.world.level.levelgen.feature.foliageplacers.FoliagePlacerType;
import net.minecraft.world.level.levelgen.feature.foliageplacers.SpruceFoliagePlacer;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import teamrazor.deepaether.DeepAetherMod;

import java.util.function.BiConsumer;

public class FlowerBlobFoliagePlacer extends SpruceFoliagePlacer {

    public static final Codec<FlowerBlobFoliagePlacer> CODEC = RecordCodecBuilder.create((p_68735_) -> {
        return foliagePlacerParts(p_68735_).and(IntProvider.codec(0, 24).fieldOf("trunk_height").forGetter((p_161553_) -> {
            return p_161553_.trunkHeight;
        })).apply(p_68735_, FlowerBlobFoliagePlacer::new);
    });

    private final IntProvider trunkHeight;

    public FlowerBlobFoliagePlacer(IntProvider p_161539_, IntProvider p_161540_, IntProvider p_161541_) {
        super(p_161539_, p_161540_, p_161541_);
        this.trunkHeight = p_161541_;
    }

    public static final DeferredRegister<FoliagePlacerType<?>> REGISTRY = DeferredRegister.create(ForgeRegistries.FOLIAGE_PLACER_TYPES, DeepAetherMod.MODID);
    public static final RegistryObject<FoliagePlacerType<FlowerBlobFoliagePlacer>> BLOB_PLACER = REGISTRY.register("flowers2", () -> new FoliagePlacerType(FlowerBlobFoliagePlacer.CODEC));

    @Override
    protected FoliagePlacerType<?> type() {
        return BLOB_PLACER.get();
    }

    @Override
    protected void createFoliage(LevelSimulatedReader pLevel, BiConsumer<BlockPos, BlockState> pBlockSetter, RandomSource pRandom, TreeConfiguration pConfig, int pMaxFreeTreeHeight, FoliageAttachment pAttachment, int pFoliageHeight, int pFoliageRadius, int pOffset) {
        BlockPos blockpos = pAttachment.pos();
        int i = pRandom.nextInt(2);
        int j = 1;
        int k = 0;
        for(int l = pOffset; l >= -pFoliageHeight; --l) {
            this.placeLeavesRow(pLevel, pBlockSetter, pRandom, pConfig, blockpos, i, l, pAttachment.doubleTrunk());
            if (i >= j) {
                i = k;
                k = 1;
                j = Math.min(j + 1, pFoliageRadius + pAttachment.radiusOffset());
            } else {
                ++i;
            }
            BlockState core = pConfig.foliageProvider.getState(pRandom, pAttachment.pos());
            if (validTreePos(pLevel, pAttachment.pos())) {
                pBlockSetter.accept(pAttachment.pos(), core);
            }
        }
    }

    @Override
    protected void placeLeavesRow(LevelSimulatedReader p_161438_, BiConsumer<BlockPos, BlockState> p_161439_, RandomSource p_161440_, TreeConfiguration p_161441_, BlockPos p_161442_, int p_161443_, int p_161444_, boolean p_161445_) {
        int i = p_161445_ ? 1 : 0;
        BlockPos.MutableBlockPos blockpos$mutableblockpos = new BlockPos.MutableBlockPos();

        for (int j = -p_161443_; j <= p_161443_ + i; ++j) {
            for (int k = -p_161443_; k <= p_161443_ + i; ++k) {
                if (!shouldSkipLocationSigned(p_161440_, j, p_161444_, k, p_161443_, p_161445_)) {
                    blockpos$mutableblockpos.setWithOffset(p_161442_, j, p_161444_, k);
                    tryPlaceLeaf(p_161438_, p_161439_, p_161440_, p_161441_, blockpos$mutableblockpos);
                }
            }
        }
    }

    protected static void tryPlaceLeaf(LevelSimulatedReader p_161432_, BiConsumer<BlockPos, BlockState> p_161433_, RandomSource p_161434_, TreeConfiguration p_161435_, BlockPos p_161436_) {
        if (validTreePos(p_161432_, p_161436_)) {
            p_161433_.accept(p_161436_, p_161435_.foliageProvider.getState(p_161434_, p_161436_));
        }
    }

    public static boolean validTreePos(LevelSimulatedReader level, BlockPos pos) {
        return level.isStateAtPosition(pos, state -> {
            return !state.hasProperty(LeavesBlock.PERSISTENT) || !state.getValue(LeavesBlock.PERSISTENT);
        }) && TreeFeature.validTreePos(level, pos);
    }
}