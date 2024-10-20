package io.github.razordevs.deep_aether.datagen.loot;

import io.github.razordevs.deep_aether.DeepAether;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.storage.loot.LootTable;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public class DALoot {
    private static final Set<ResourceKey<LootTable>> LOOT_TABLES = new HashSet<>();
    public static final Set<ResourceKey<LootTable>> IMMUTABLE_LOOT_TABLES = Collections.unmodifiableSet(LOOT_TABLES);

    public static final ResourceKey<LootTable> BRASS_DUNGEON = register("chests/dungeon/brass/brass_dungeon");
    public static final ResourceKey<LootTable> BRASS_DUNGEON_LOOT = register("chests/dungeon/brass/brass_dungeon_loot");
    //public static final ResourceLocation BRASS_DUNGEON_LIBRARY_LOOT = register("chests/dungeon/brass/brass_dungeon_library_loot");
    //public static final ResourceLocation BRASS_DUNGEON_COMBINDER_LOOT = register("chests/dungeon/brass/brass_dungeon_combinder_loot");
    public static final ResourceKey<LootTable> BRASS_DUNGEON_TRASH = register("chests/dungeon/brass/brass_dungeon_trash");
    public static final ResourceKey<LootTable> BRASS_DUNGEON_DISC = register("chests/dungeon/brass/brass_dungeon_disc");
    public static final ResourceKey<LootTable> BRASS_DUNGEON_REWARD = register("chests/dungeon/brass/brass_dungeon_reward");
    public static final ResourceKey<LootTable> BRASS_DUNGEON_TREASURE = register("chests/dungeon/brass/brass_dungeon_treasure");
    public static final ResourceKey<LootTable> BRASS_DUNGEON_STORM_FORGED = register("chests/dungeon/brass/brass_dungeon_stormforged");
    public static final ResourceKey<LootTable> BRASS_DUNGEON_GUMMIES = register("chests/dungeon/brass/brass_dungeon_gummies");

    public static final ResourceKey<LootTable> ALTAR_CAMP = register("chests/dungeon/altar_camp");

    private static ResourceKey<LootTable> register(String id) {
        return register(ResourceKey.create(Registries.LOOT_TABLE, ResourceLocation.fromNamespaceAndPath(DeepAether.MODID, id)));
    }

    private static ResourceKey<LootTable> register(ResourceKey<LootTable> id) {
        if (LOOT_TABLES.add(id)) {
            return id;
        } else {
            throw new IllegalArgumentException(id + " is already a registered built-in loot table");
        }
    }
}

