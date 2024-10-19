package io.github.razordevs.deep_aether.datagen.loot;

import io.github.razordevs.deep_aether.DeepAether;
import net.minecraft.resources.ResourceLocation;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public class DALoot {
    private static final Set<ResourceLocation> LOOT_TABLES = new HashSet<>();
    public static final Set<ResourceLocation> IMMUTABLE_LOOT_TABLES = Collections.unmodifiableSet(LOOT_TABLES);

    public static final ResourceLocation BRASS_DUNGEON = register("chests/dungeon/brass/brass_dungeon");
    public static final ResourceLocation BRASS_DUNGEON_LOOT = register("chests/dungeon/brass/brass_dungeon_loot");
    //public static final ResourceLocation BRASS_DUNGEON_LIBRARY_LOOT = register("chests/dungeon/brass/brass_dungeon_library_loot");
    //public static final ResourceLocation BRASS_DUNGEON_COMBINDER_LOOT = register("chests/dungeon/brass/brass_dungeon_combinder_loot");
    public static final ResourceLocation BRASS_DUNGEON_TRASH = register("chests/dungeon/brass/brass_dungeon_trash");
    public static final ResourceLocation BRASS_DUNGEON_DISC = register("chests/dungeon/brass/brass_dungeon_disc");
    public static final ResourceLocation BRASS_DUNGEON_REWARD = register("chests/dungeon/brass/brass_dungeon_reward");
    public static final ResourceLocation BRASS_DUNGEON_TREASURE = register("chests/dungeon/brass/brass_dungeon_treasure");
    public static final ResourceLocation BRASS_DUNGEON_STORM_FORGED = register("chests/dungeon/brass/brass_dungeon_stormforged");
    public static final ResourceLocation BRASS_DUNGEON_GUMMIES = register("chests/dungeon/brass/brass_dungeon_gummies");

    public static final ResourceLocation ALTAR_CAMP = register("chests/dungeon/altar_camp");
    
    private static ResourceLocation register(String id) {
        return register(ResourceLocation.fromNamespaceAndPath(DeepAether.MODID, id));
    }
    private static ResourceLocation register(ResourceLocation id) {
        if (LOOT_TABLES.add(id)) {
            return id;
        } else {
            throw new IllegalArgumentException(id + " is already a registered built-in loot table");
        }
    }
}

