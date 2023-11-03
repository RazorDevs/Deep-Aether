package teamrazor.deepaether.world.feature.tree.foliage;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.util.RandomSource;
import net.minecraft.util.valueproviders.IntProvider;
import net.minecraft.world.level.LevelSimulatedReader;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.feature.configurations.TreeConfiguration;
import net.minecraft.world.level.levelgen.feature.foliageplacers.FoliagePlacer;
import net.minecraft.world.level.levelgen.feature.foliageplacers.FoliagePlacerType;

import java.util.function.BiConsumer;

public class ConberryFoliagePlacer extends FoliagePlacer {
    public static final Codec<ConberryFoliagePlacer> CODEC = RecordCodecBuilder.create((p_273246_) -> {
        return foliagePlacerParts(p_273246_).and(p_273246_.group(IntProvider.codec(4, 16).fieldOf("height").forGetter((p_273527_) -> {
            return p_273527_.height;
        }), Codec.floatRange(0.0F, 1.0F).fieldOf("wide_bottom_layer_hole_chance").forGetter((p_273760_) -> {
            return p_273760_.wideBottomLayerHoleChance;
        }), Codec.floatRange(0.0F, 1.0F).fieldOf("corner_hole_chance").forGetter((p_273020_) -> {
            return p_273020_.wideBottomLayerHoleChance;
        }), Codec.floatRange(0.0F, 1.0F).fieldOf("hanging_leaves_chance").forGetter((p_273148_) -> {
            return p_273148_.hangingLeavesChance;
        }), Codec.floatRange(0.0F, 1.0F).fieldOf("hanging_leaves_extension_chance").forGetter((p_273098_) -> {
            return p_273098_.hangingLeavesExtensionChance;
        }))).apply(p_273246_, ConberryFoliagePlacer::new);
    });
    private final IntProvider height;
    private final float wideBottomLayerHoleChance;
    private final float cornerHoleChance;
    private final float hangingLeavesChance;
    private final float hangingLeavesExtensionChance;

    public ConberryFoliagePlacer(IntProvider intProvider, IntProvider intProvider1, IntProvider intProvider2, float v, float v1, float v2, float v3) {
        super(intProvider, intProvider1);
        this.height = intProvider2;
        this.wideBottomLayerHoleChance = v;
        this.cornerHoleChance = v1;
        this.hangingLeavesChance = v2;
        this.hangingLeavesExtensionChance = v3;
    }

    protected FoliagePlacerType<?> type() {
        return DAFoliagePlacers.CONBERRY_FOLIAGE_PLACER.get();
    }

    @Override
    public void createFoliage(LevelSimulatedReader levelSimulatedReader, BiConsumer<BlockPos, BlockState> foliageSetter, RandomSource randomSource, TreeConfiguration treeConfiguration, int i1, FoliageAttachment foliageAttachment, int i2, int i3, int i4) {
        boolean flag = foliageAttachment.doubleTrunk();
        BlockPos blockpos = foliageAttachment.pos().above(i4);
        int i = i3 + foliageAttachment.radiusOffset() - 1;
        this.placeLeavesRow(levelSimulatedReader, foliageSetter, randomSource, treeConfiguration, blockpos, i - 2, i2 - 3, flag);
        this.placeLeavesRow(levelSimulatedReader, foliageSetter, randomSource, treeConfiguration, blockpos, i - 1, i2 - 4, flag);

        for(int j = i2 - 5; j >= 0; --j) {
            this.placeLeavesRow(levelSimulatedReader, foliageSetter, randomSource, treeConfiguration, blockpos, i, j, flag);
        }

        this.placeLeavesRowWithHangingLeavesBelow(levelSimulatedReader, foliageSetter, randomSource, treeConfiguration, blockpos, i, -1, flag, this.hangingLeavesChance, this.hangingLeavesExtensionChance);
        this.placeLeavesRowWithHangingLeavesBelow(levelSimulatedReader, foliageSetter, randomSource, treeConfiguration, blockpos, i - 1, -2, flag, this.hangingLeavesChance, this.hangingLeavesExtensionChance);
    }

    public int foliageHeight(RandomSource p_273679_, int p_273336_, TreeConfiguration p_273643_) {
        return this.height.sample(p_273679_);
    }

    protected boolean shouldSkipLocation(RandomSource p_273294_, int p_273380_, int p_272865_, int p_272853_, int p_272631_, boolean p_273432_) {
        if (p_272865_ == -1 && (p_273380_ == p_272631_ || p_272853_ == p_272631_) && p_273294_.nextFloat() < this.wideBottomLayerHoleChance) {
            return true;
        } else {
            boolean flag = p_273380_ == p_272631_ && p_272853_ == p_272631_;
            boolean flag1 = p_272631_ > 2;
            if (flag1) {
                return flag || p_273380_ + p_272853_ > p_272631_ * 2 - 2 && p_273294_.nextFloat() < this.cornerHoleChance;
            } else {
                return flag && p_273294_.nextFloat() < this.cornerHoleChance;
            }
        }
    }

    protected final void placeLeavesRowWithHangingLeavesBelow(LevelSimulatedReader p_273087_, BiConsumer<BlockPos, BlockState> p_273225_, RandomSource p_272629_, TreeConfiguration p_272885_, BlockPos p_273412_, int p_272712_, int p_272656_, boolean p_272689_, float p_273464_, float p_273068_) {
        this.placeLeavesRow(p_273087_, p_273225_, p_272629_, p_272885_, p_273412_, p_272712_, p_272656_, p_272689_);
        int i = p_272689_ ? 1 : 0;
        BlockPos blockpos = p_273412_.below();
        BlockPos.MutableBlockPos blockpos$mutableblockpos = new BlockPos.MutableBlockPos();

        for(Direction direction : Direction.Plane.HORIZONTAL) {
            Direction direction1 = direction.getClockWise();
            int j = direction1.getAxisDirection() == Direction.AxisDirection.POSITIVE ? p_272712_ + i : p_272712_;
            blockpos$mutableblockpos.setWithOffset(p_273412_, 0, p_272656_ - 1, 0).move(direction1, j).move(direction, -p_272712_);
            int k = -p_272712_;
        }
    }
}