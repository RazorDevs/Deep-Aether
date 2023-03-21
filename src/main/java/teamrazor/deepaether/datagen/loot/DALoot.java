package teamrazor.deepaether.datagen.loot;

import net.minecraft.resources.ResourceLocation;
import teamrazor.deepaether.DeepAetherMod;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public class DALoot {
    private static final Set<ResourceLocation> LOOT_TABLES = new HashSet<>();
    public static final Set<ResourceLocation> IMMUTABLE_LOOT_TABLES = Collections.unmodifiableSet(LOOT_TABLES);



    private static ResourceLocation register(String id) {
        return register(new ResourceLocation(DeepAetherMod.MODID, id));
    }

    private static ResourceLocation register(ResourceLocation id) {
        if (LOOT_TABLES.add(id)) {
            return id;
        } else {
            throw new IllegalArgumentException(id + " is already a registered built-in loot table");
        }
    }
}

