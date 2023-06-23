package teamrazor.deepaether.world.placementmodifier;

import com.mojang.serialization.Codec;
import net.minecraft.core.BlockPos;
import net.minecraft.util.ExtraCodecs;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.levelgen.placement.PlacementContext;
import net.minecraft.world.level.levelgen.placement.PlacementFilter;
import net.minecraft.world.level.levelgen.placement.PlacementModifierType;
import teamrazor.deepaether.world.feature.DAPlacedFeatures;

public class ImprovedRarityFilter extends PlacementFilter {
    public static final Codec<ImprovedRarityFilter> CODEC = ExtraCodecs.POSITIVE_FLOAT.fieldOf("chance").xmap(ImprovedRarityFilter::new, (p_274876_)
            -> p_274876_.chance).codec();

    private final float chance;

    private ImprovedRarityFilter(float p_191899_) {
        this.chance = p_191899_;
    }

    public static ImprovedRarityFilter onAverageOnceEvery(float p_191901_) {
        return new ImprovedRarityFilter(p_191901_);
    }

    protected boolean shouldPlace(PlacementContext p_226397_, RandomSource p_226398_, BlockPos p_226399_) {
        return p_226398_.nextFloat() < this.chance;
    }

    public PlacementModifierType<?> type() {
        return DAPlacementModifiers.IMPROVED_RARITY_FILTER;
    }
}