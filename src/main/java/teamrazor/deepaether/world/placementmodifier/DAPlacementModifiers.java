package teamrazor.deepaether.world.placementmodifier;

import com.mojang.serialization.Codec;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.levelgen.placement.PlacementModifier;
import net.minecraft.world.level.levelgen.placement.PlacementModifierType;
import teamrazor.deepaether.DeepAether;

public class DAPlacementModifiers {
    public static final PlacementModifierType<ImprovedRarityFilter> IMPROVED_RARITY_FILTER = register(new ResourceLocation(DeepAether.MODID, "dungeon_blacklist_filter"), ImprovedRarityFilter.CODEC);

    private static <P extends PlacementModifier> PlacementModifierType<P> register(ResourceLocation name, Codec<P> codec) {
        return Registry.register(BuiltInRegistries.PLACEMENT_MODIFIER_TYPE, name, () -> codec);
    }
}
