package io.github.razordevs.deepaether.deepaether.world.placementmodifier;

import com.mojang.serialization.Codec;
import net.minecraft.core.BlockPos;
import net.minecraft.util.ExtraCodecs;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.levelgen.placement.PlacementContext;
import net.minecraft.world.level.levelgen.placement.PlacementFilter;
import net.minecraft.world.level.levelgen.placement.PlacementModifierType;
import org.jetbrains.annotations.NotNull;

public class ImprovedRarityFilter extends PlacementFilter {
    public static final Codec<ImprovedRarityFilter> CODEC = ExtraCodecs.POSITIVE_FLOAT.fieldOf("chance").xmap(ImprovedRarityFilter::new, (rarityFilter)
            -> rarityFilter.chance).codec();

    private final float chance;

    private ImprovedRarityFilter(float v) {
        this.chance = v;
    }

    public static ImprovedRarityFilter onAverageOnceEvery(float v) {
        return new ImprovedRarityFilter(v);
    }

    protected boolean shouldPlace(PlacementContext context, RandomSource v, BlockPos blockPos) {
        return v.nextFloat() < this.chance;
    }

    @NotNull
    public PlacementModifierType<?> type() {
        return DAPlacementModifiers.IMPROVED_RARITY_FILTER.get();
    }
}