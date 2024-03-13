package teamrazor.deepaether.datagen.loot.modifiers;

import com.mojang.serialization.Codec;
import net.minecraftforge.common.loot.IGlobalLootModifier;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import teamrazor.deepaether.DeepAetherMod;

public class DAGlobalLootModifiers {

    public static DeferredRegister<Codec<? extends IGlobalLootModifier>> LOOT_MODIFIERS = DeferredRegister.create(ForgeRegistries.Keys.GLOBAL_LOOT_MODIFIER_SERIALIZERS, DeepAetherMod.MODID);

    public static RegistryObject<Codec<? extends IGlobalLootModifier>> AETHER_DUNGEON_LOOT_CODEC = LOOT_MODIFIERS.register("aether_dungeon_loot", DAAddDungeonLootModifier.CODEC);
    public static RegistryObject<Codec<? extends IGlobalLootModifier>> AETHER_FORCE_DUNGEON_LOOT_CODEC = LOOT_MODIFIERS.register("aether_force_dungeon_loot", DAAddFlatDungeonLootModifier.CODEC);
    public static RegistryObject<Codec<? extends IGlobalLootModifier>> AETHER_DUNGEON_LOOT_CODEC_NO_REPLACEMENTS = LOOT_MODIFIERS.register("aether_dungeon_loot_no_replacement", DAAddDungeonLootModifierNoReplacements.CODEC);

}