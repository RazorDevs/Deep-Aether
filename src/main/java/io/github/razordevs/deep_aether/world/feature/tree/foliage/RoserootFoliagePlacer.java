package io.github.razordevs.deep_aether.world.feature.tree.foliage;

import java.util.function.Consumer;

import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.core.BlockPos;
import net.minecraft.util.RandomSource;
import net.minecraft.util.valueproviders.IntProvider;
import net.minecraft.world.level.LevelSimulatedReader;
import net.minecraft.world.level.levelgen.feature.configurations.TreeConfiguration;
import net.minecraft.world.level.levelgen.feature.foliageplacers.FoliagePlacer;
import net.minecraft.world.level.levelgen.feature.foliageplacers.FoliagePlacerType;

public class RoserootFoliagePlacer extends FoliagePlacer {
	public static final MapCodec<RoserootFoliagePlacer> CODEC = RecordCodecBuilder.mapCodec(
		builder -> foliagePlacerParts(builder)
			.and(IntProvider.codec(0, 24).fieldOf("trunk_height").forGetter(f -> f.trunkHeight))
			.apply(builder, RoserootFoliagePlacer::new)
	);

	private final IntProvider trunkHeight;

	public RoserootFoliagePlacer(IntProvider radius, IntProvider offset, IntProvider height) {
		super(radius, offset);
		this.trunkHeight = height;
	}

	protected FoliagePlacerType<?> type() {
		return DAFoliagePlacers.ROSEROOT_FOLIAGE_PLACER.get();
	}

	@Override
	protected void createFoliage(
		LevelSimulatedReader reader,
		FoliageSetter setter,
		RandomSource rand,
		TreeConfiguration cfg,
		int i1,
		FoliagePlacer.FoliageAttachment attachment,
		int foliageMaxHeight,
		int i2,
		int i3
	) {
		var pos = attachment.pos();

		for(int l = rand.nextInt(1, 4); l >= 0; --l) {
			this.placeLeavesRow(reader, setter, rand, cfg, pos.above(1+l), 0, 1, attachment.doubleTrunk());
		}
		int a1 = ((foliageMaxHeight/3)*2) + rand.nextInt(-1,1);
		int a2 = ((foliageMaxHeight/3)) + rand.nextInt(-1,1);

		for(int l = foliageMaxHeight; l >= 0; --l) {
			if (l == 0) {
				this.placeLeavesRow(reader, setter, rand, cfg, pos, 1, 1, attachment.doubleTrunk());
				pos = pos.below(1);
			}
			else if(l >= a1) {
				placeSmallCircle(reader, setter, rand, cfg, pos, attachment);
				pos = pos.below(1);
			}
			else if (l >= a2) {
				this.placeSquare(reader, setter, rand, cfg, pos);
				pos = pos.below(1);
			}
			else {
				placeBiggerCircle(reader, setter, rand, cfg, pos, attachment);
				pos = pos.below(1);
			}
		}

	}

	public void placeSquare(
		LevelSimulatedReader reader,
		FoliageSetter setter,
		RandomSource rand,
		TreeConfiguration cfg,
		BlockPos blockPos
	) {
		var pos = blockPos.above(2);
		Consumer<BlockPos> placeLeaf = p -> {
			if(rand.nextInt(4) == 1) tryPlaceLeaf(reader, setter, rand, cfg, p);
		};

		for (int i = 0; i < 3; ++i) {
			for (int ii = 0; ii < 3; ++ii) {
				placeLeaf.accept(pos.north(i-1).east(ii-1));
			}
			placeLeaf.accept(pos.north(0).east(2));
			placeLeaf.accept(pos.north(0).east(-2));
			placeLeaf.accept(pos.north(2).east(0));
			placeLeaf.accept(pos.north(-2).east(0));

			placeLeaf.accept(pos.north(1).east(2));
			placeLeaf.accept(pos.north(1).east(-2));
			placeLeaf.accept(pos.north(-1).east(2));
			placeLeaf.accept(pos.north(-1).east(-2));
			placeLeaf.accept(pos.north(2).east(1));
			placeLeaf.accept(pos.north(-2).east(1));
			placeLeaf.accept(pos.north(2).east(-1));
			placeLeaf.accept(pos.north(-2).east(-1));
		}
	}

	public void placeSmallCircle(
		LevelSimulatedReader reader,
		FoliageSetter setter,
		RandomSource rand,
		TreeConfiguration cfg,
		BlockPos blockPos,
		FoliagePlacer.FoliageAttachment attachment
	) {
		this.placeLeavesRow(reader, setter, rand, cfg, blockPos, 1, 2, attachment.doubleTrunk());

		var pos = blockPos.above(2);
		Consumer<BlockPos> placeLeaf = p -> {
			if(rand.nextInt(4) == 1) tryPlaceLeaf(reader, setter, rand, cfg, p);
		};

		placeLeaf.accept(pos.north(-1).east(-1));
		placeLeaf.accept(pos.north(1).east(1));
		placeLeaf.accept(pos.north(0).east(2));
		placeLeaf.accept(pos.north(0).east(-2));
		placeLeaf.accept(pos.north(2).east(0));
		placeLeaf.accept(pos.north(-2).east(0));
		placeLeaf.accept(pos.north(-1).east(1));
		placeLeaf.accept(pos.north(1).east(-1));
	}

	public void placeBigCircle(
		LevelSimulatedReader reader,
		FoliageSetter setter,
		RandomSource rand,
		TreeConfiguration cfg,
		BlockPos blockPos,
		FoliagePlacer.FoliageAttachment attachment
	) {
		this.placeLeavesRow(reader, setter, rand, cfg, blockPos, 2, 2, attachment.doubleTrunk());

		var pos = blockPos.above(2);
		Consumer<BlockPos> placeLeaf = p -> {
			if(rand.nextInt(4) == 1) tryPlaceLeaf(reader, setter, rand, cfg, p);
		};

		placeLeaf.accept(pos.north(0).east(3));
		placeLeaf.accept(pos.north(0).east(-3));
		placeLeaf.accept(pos.north(3).east(0));
		placeLeaf.accept(pos.north(-3).east(0));
		placeLeaf.accept(pos.north(2).east(2));
		placeLeaf.accept(pos.north(-2).east(-2));
		placeLeaf.accept(pos.north(2).east(-2));
		placeLeaf.accept(pos.north(-2).east(2));
	}

	public void placeBiggerCircle(
		LevelSimulatedReader reader,
		FoliageSetter setter,
		RandomSource rand,
		TreeConfiguration cfg,
		BlockPos blockPos,
		FoliagePlacer.FoliageAttachment attachment
	) {
		this.placeLeavesRow(reader, setter, rand, cfg, blockPos, 2, 2, attachment.doubleTrunk());

		var pos = blockPos.above(2);
		Consumer<BlockPos> placeLeaf = p -> {
			if(rand.nextInt(4) == 1) tryPlaceLeaf(reader, setter, rand, cfg, p);
		};

		placeLeaf.accept(pos.north(0).east(3));
		placeLeaf.accept(pos.north(0).east(-3));
		placeLeaf.accept(pos.north(3).east(0));
		placeLeaf.accept(pos.north(-3).east(0));
		placeLeaf.accept(pos.north(2).east(2));
		placeLeaf.accept(pos.north(-2).east(-2));
		placeLeaf.accept(pos.north(2).east(-2));
		placeLeaf.accept(pos.north(-2).east(2));
		placeLeaf.accept(pos.north(1).east(3));
		placeLeaf.accept(pos.north(-1).east(3));
		placeLeaf.accept(pos.north(1).east(-3));
		placeLeaf.accept(pos.north(-1).east(-3));
		placeLeaf.accept(pos.north(3).east(1));
		placeLeaf.accept(pos.north(3).east(-1));
		placeLeaf.accept(pos.north(-3).east(1));
		placeLeaf.accept(pos.north(-3).east(-1));
	}

	public int foliageHeight(RandomSource rand, int i, TreeConfiguration cfg) {
		return Math.max(4, i - this.trunkHeight.sample(rand));
	}

	protected boolean shouldSkipLocation(RandomSource rand, int a, int b, int c, int d, boolean b1) {
		return a == d && c == d && d > 0;
	}

	@Override
	protected void placeLeavesRow(
		LevelSimulatedReader reader,
		FoliageSetter setter,
		RandomSource rand,
		TreeConfiguration cfg,
		BlockPos blockPos,
		int i1,
		int i2,
		boolean b
	) {
		int i = b ? 1 : 0;
		var pos = new BlockPos.MutableBlockPos();

		for (int j = -i1; j <= i1 + i; ++j) {
			for (int k = -i1; k <= i1 + i; ++k) {
				if (shouldSkipLocationSigned(rand, j, i2, k, i1, b)) continue;

				pos.setWithOffset(blockPos, j, i2, k);
				tryPlaceLeaf(reader, setter, rand, cfg, pos);
			}
		}
	}
}
