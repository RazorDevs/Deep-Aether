package teamrazor.deepaether.world.feature.tree.decorators;
/*
import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.core.HolderSet;
import net.minecraft.core.Registry;
import net.minecraft.core.RegistryCodecs;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.levelgen.feature.stateproviders.BlockStateProvider;

public record YagrootRootPlacement(HolderSet<Block> canGrowThrough, HolderSet<Block> muddyRootsIn, BlockStateProvider muddyRootsProvider, int maxRootWidth, int maxRootLength, float randomSkewChance) {
    public static final Codec<net.minecraft.world.level.levelgen.feature.rootplacers.YagrootRootPlacement> CODEC = RecordCodecBuilder.create((p_225789_) -> {
        return p_225789_.group(RegistryCodecs.homogeneousList(Registry.BLOCK_REGISTRY).fieldOf("can_grow_through").forGetter((p_225808_) -> {
            return p_225808_.canGrowThrough;
        }), RegistryCodecs.homogeneousList(Registry.BLOCK_REGISTRY).fieldOf("muddy_roots_in").forGetter((p_225803_) -> {
            return p_225803_.muddyRootsIn;
        }), BlockStateProvider.CODEC.fieldOf("muddy_roots_provider").forGetter((p_225800_) -> {
            return p_225800_.muddyRootsProvider;
        }), Codec.intRange(1, 12).fieldOf("max_root_width").forGetter((p_225797_) -> {
            return p_225797_.maxRootWidth;
        }), Codec.intRange(1, 64).fieldOf("max_root_length").forGetter((p_225794_) -> {
            return p_225794_.maxRootLength;
        }), Codec.floatRange(0.0F, 1.0F).fieldOf("random_skew_chance").forGetter((p_225791_) -> {
            return p_225791_.randomSkewChance;
        })).apply(p_225789_, net.minecraft.world.level.levelgen.feature.rootplacers.YagrootRootPlacement::new);
    });
}
*/