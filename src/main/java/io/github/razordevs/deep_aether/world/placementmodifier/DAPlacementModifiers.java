package io.github.razordevs.deep_aether.world.placementmodifier;

import io.github.razordevs.deep_aether.DeepAether;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.level.levelgen.placement.PlacementModifierType;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

public class DAPlacementModifiers {

    public static final DeferredRegister<PlacementModifierType<?>> PLACEMENT_MODIFIERS = DeferredRegister.create(BuiltInRegistries.PLACEMENT_MODIFIER_TYPE, DeepAether.MODID);
    public static final DeferredHolder<PlacementModifierType<?>, PlacementModifierType<ImprovedRarityFilter>> IMPROVED_RARITY_FILTER = PLACEMENT_MODIFIERS.register("dungeon_blacklist_filter", () -> () -> ImprovedRarityFilter.CODEC);
}
