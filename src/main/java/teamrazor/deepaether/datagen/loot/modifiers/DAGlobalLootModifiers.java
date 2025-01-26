package teamrazor.deepaether.datagen.loot.modifiers;

import com.mojang.serialization.Codec;
import net.minecraftforge.common.loot.IGlobalLootModifier;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import teamrazor.deepaether.DeepAether;

public class DAGlobalLootModifiers {

    public static DeferredRegister<Codec<? extends IGlobalLootModifier>> LOOT_MODIFIERS = DeferredRegister.create(ForgeRegistries.Keys.GLOBAL_LOOT_MODIFIER_SERIALIZERS, DeepAether.MODID);

    public static final RegistryObject<Codec<? extends IGlobalLootModifier>> AETHER_DUNGEON_LOOT_CODEC = LOOT_MODIFIERS.register("aether_dungeon_loot", DAAddDungeonLootModifier.CODEC);
    public static final RegistryObject<Codec<? extends IGlobalLootModifier>> AETHER_FISH_LOOT_CODEC = LOOT_MODIFIERS.register("aether_fish_loot", DAFishingLootModifier.CODEC);
}