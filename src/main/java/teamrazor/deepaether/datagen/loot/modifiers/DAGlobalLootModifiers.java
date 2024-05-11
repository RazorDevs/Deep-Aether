package teamrazor.deepaether.datagen.loot.modifiers;

import com.mojang.serialization.Codec;
import net.minecraft.core.registries.BuiltInRegistries;
import net.neoforged.neoforge.common.loot.IGlobalLootModifier;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.neoforged.neoforge.registries.NeoForgeRegistries;
import teamrazor.deepaether.DeepAether;

public class DAGlobalLootModifiers {

    public static DeferredRegister<Codec<? extends IGlobalLootModifier>> LOOT_MODIFIERS = DeferredRegister.create(NeoForgeRegistries.Keys.GLOBAL_LOOT_MODIFIER_SERIALIZERS, DeepAether.MODID);

    public static DeferredHolder<Codec<? extends IGlobalLootModifier>, Codec<? extends IGlobalLootModifier>> AETHER_DUNGEON_LOOT_CODEC = LOOT_MODIFIERS.register("aether_dungeon_loot", DAAddDungeonLootModifier.CODEC);
    public static DeferredHolder<Codec<? extends IGlobalLootModifier>, Codec<? extends IGlobalLootModifier>> AETHER_FORCE_DUNGEON_LOOT_CODEC = LOOT_MODIFIERS.register("aether_force_dungeon_loot", DAAddFlatDungeonLootModifier.CODEC);
    public static DeferredHolder<Codec<? extends IGlobalLootModifier>, Codec<? extends IGlobalLootModifier>> AETHER_DUNGEON_LOOT_CODEC_NO_REPLACEMENTS = LOOT_MODIFIERS.register("aether_dungeon_loot_no_replacement", DAAddDungeonLootModifierNoReplacements.CODEC);

}