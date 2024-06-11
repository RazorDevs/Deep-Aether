package teamrazor.deepaether.datagen.tags;

import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.material.Fluid;
import teamrazor.deepaether.DeepAether;

public class DATags {
    public class Items {
        public static final TagKey<Item> ROSEROOT_LOGS = tag("roseroot_logs");
        public static final TagKey<Item> YAGROOT_LOGS = tag("yagroot_logs");
        public static final TagKey<Item> CRUDEROOT_LOGS = tag("cruderoot_logs");
        public static final TagKey<Item> CONBERRY_LOGS = tag("conberry_logs");
        public static final TagKey<Item> SUNROOT_LOGS = tag("sunroot_logs");

        public static final TagKey<Item> CRAFTS_ROSEROOT_PLANKS = tag("crafts_roseroot_planks");
        public static final TagKey<Item> CRAFTS_YAGROOT_PLANKS = tag("crafts_yagroot_planks");
        public static final TagKey<Item> CRAFTS_CRUDEROOT_PLANKS = tag("crafts_cruderoot_planks");
        public static final TagKey<Item> CRAFTS_CONBERRY_PLANKS = tag("crafts_conberry_planks");
        public static final TagKey<Item> CRAFTS_SUNROOT_PLANKS = tag("crafts_sunroot_planks");
        public static final TagKey<Item> EGGS = tag("eggs");
        public static final TagKey<Item> MILK_BUCKETS = tag("milk_buckets");
        public static final TagKey<Item> STRATUS_REPAIRING = tag("stratus_repairing");
        public static final TagKey<Item> SKYJADE_REPAIRING = tag("skyjade_repairing");
        public static final TagKey<Item> IS_GOLDEN_SWET_BALL = tag("is_golden_swet_ball");

        private static TagKey<Item> tag(String name) {
            return TagKey.create(Registries.ITEM, new ResourceLocation(DeepAether.MODID, name));
        }
    }
    public static class Blocks {

        public static final TagKey<Block> ROSEROOT_LOGS = tag("roseroot_logs");
        public static final TagKey<Block> YAGROOT_LOGS = tag("yagroot_logs");
        public static final TagKey<Block> CRUDEROOT_LOGS = tag("cruderoot_logs");
        public static final TagKey<Block> CONBERRY_LOGS = tag("conberry_logs");
        public static final TagKey<Block> SUNROOT_LOGS = tag("sunroot_logs");

        public static final TagKey<Block> CAN_GOLDEN_VINES_SURVIVE_ON = tag("can_golden_vines_survive_on");
        private static TagKey<Block> tag(String name) {
            return TagKey.create(Registries.BLOCK, new ResourceLocation(DeepAether.MODID, name));
        }
    }

    public static class Entities {
        public static final TagKey<EntityType<?>> STERLING_AERCLOUD_BLACKLIST = tag("sterling_aercloud_blacklist");
        private static TagKey<EntityType<?>> tag(String name) {
            return TagKey.create(Registries.ENTITY_TYPE, new ResourceLocation(DeepAether.MODID, name));
        }
    }
    public static class Biomes {

        public static final TagKey<Biome> IS_NOT_SWAMP = tag("is_not_swamp");
        public static final TagKey<Biome> CAN_QUAIL_SPAWN = tag("can_quail_spawn");
        private static TagKey<Biome> tag(String name) {
            return TagKey.create(Registries.BIOME, new ResourceLocation(DeepAether.MODID, name));
        }

    }

    public static class Fluids {
        public static final TagKey<Fluid> POISON = tag("poison");

        private static TagKey<Fluid> tag(String name) {
            return TagKey.create(Registries.FLUID, new ResourceLocation(DeepAether.MODID, name));
        }
    }
}
