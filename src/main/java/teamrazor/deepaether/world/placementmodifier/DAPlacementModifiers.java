package teamrazor.deepaether.world.placementmodifier;

import com.mojang.serialization.Codec;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.levelgen.placement.PlacementModifier;
import net.minecraft.world.level.levelgen.placement.PlacementModifierType;
import teamrazor.deepaether.DeepAetherMod;

public class DAPlacementModifiers {

    public static final PlacementModifierType<ImprovedRarityFilter> IMPROVED_RARITY_FILTER = register(new ResourceLocation(DeepAetherMod.MODID, "dungeon_blacklist_filter"), ImprovedRarityFilter.CODEC);

    private static <P extends PlacementModifier> PlacementModifierType<P> register(ResourceLocation name, Codec<P> codec) {
        return Registry.register(Registry.PLACEMENT_MODIFIERS, name, () -> codec);
    }
}
