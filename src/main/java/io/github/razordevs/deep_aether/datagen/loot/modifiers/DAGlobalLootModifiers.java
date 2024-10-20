package io.github.razordevs.deep_aether.datagen.loot.modifiers;

import com.mojang.serialization.MapCodec;
import io.github.razordevs.deep_aether.DeepAether;
import net.neoforged.neoforge.common.loot.IGlobalLootModifier;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.neoforged.neoforge.registries.NeoForgeRegistries;

public class DAGlobalLootModifiers {

    public static DeferredRegister<MapCodec<? extends IGlobalLootModifier>> LOOT_MODIFIERS = DeferredRegister.create(NeoForgeRegistries.Keys.GLOBAL_LOOT_MODIFIER_SERIALIZERS, DeepAether.MODID);

    public static DeferredHolder<MapCodec<? extends IGlobalLootModifier>, MapCodec<? extends IGlobalLootModifier>> AETHER_DUNGEON_LOOT_CODEC = LOOT_MODIFIERS.register("aether_dungeon_loot", DAAddDungeonLootModifier.CODEC);
}