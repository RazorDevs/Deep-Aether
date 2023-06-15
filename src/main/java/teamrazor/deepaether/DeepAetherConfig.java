package teamrazor.deepaether;

import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.common.ForgeConfigSpec.ConfigValue;
import org.apache.commons.lang3.tuple.Pair;

public class DeepAetherConfig {

    public static class Common {
        /*public final ConfigValue<Boolean> cloudium_spawn;
        public final ConfigValue<Boolean> skyjade_spawn;

        public final ConfigValue<Boolean> aerlavender_fields_spawn;
        public final ConfigValue<Boolean> aerglow_forest_spawn;
        public final ConfigValue<Boolean> blue_aerglow_forest_spawn;
        public final ConfigValue<Boolean> mystic_aerglow_forest_spawn;
        public final ConfigValue<Boolean> yagroot_swamp_spawn;
        public final ConfigValue<Boolean> golden_heights_spawn;*/

        public final ConfigValue<Boolean> skyjade_enchant;
        public final ConfigValue<Integer> deep_aether_biome_weight;

        public Common(ForgeConfigSpec.Builder builder) {

            /*builder.push("World Generation");
            cloudium_spawn = builder
                    .comment("Allows Cloudium spawning in the world")
                    .translation("config.deep_aether.common.gameplay.cloudium_spawn")
                    .define("Cloudium Spawn", false);
            skyjade_spawn = builder
                    .comment("Allows Skyjade spawning in the world")
                    .translation("config.deep_aether.common.gameplay.skyjade_spawn")
                    .define("Skyjade Spawn", false);

            aerlavender_fields_spawn = builder
                    .comment("Allows the generation of the Aerlavender Fields biome")
                    .translation("config.deep_aether.common.gameplay.aerlavender_fields_spawn")
                    .define("Aerlavender Fields Gen", true);
            aerglow_forest_spawn = builder
                    .comment("Allows the generation of the Aerglow Forest biome")
                    .translation("config.deep_aether.common.gameplay.aerglow_forest_spawn")
                    .define("Aerglow Forest Gen", true);
            blue_aerglow_forest_spawn = builder
                    .comment("Allows the generation of the Blue Aerglow Forest biome")
                    .translation("config.deep_aether.common.gameplay.blue_aerglow_forest_spawn")
                    .define("Blue Aerglow Forest Gen", true);
            mystic_aerglow_forest_spawn = builder
                    .comment("Allows the generation of the Mystic Aerglow Forest biome")
                    .translation("config.deep_aether.common.gameplay.mystic_aerglow_forest_spawn")
                    .define("Mystic Aerglow Forest Gen", true);
            yagroot_swamp_spawn = builder
                    .comment("Allows the generation of the Yagroot Swamp biome")
                    .translation("config.deep_aether.common.gameplay.yagroot_swamp_spawn")
                    .define("Yagroot Swamp Gen", true);
            golden_heights_spawn = builder
                    .comment("Allows the generation of the Golden Heights biome")
                    .translation("config.deep_aether.common.gameplay.golden_heights_spawn")
                    .define("Golden Heights Gen", true);

            builder.pop();*/


            builder.push("Gameplay");
            skyjade_enchant = builder
                    .comment("Skyjade tools will be enchantable")
                    .translation("config.deep_aether.common.gameplay.skyjade_enchant")
                    .define("Skyjade Enchant", false);
            builder.pop();

            builder.push("Gameplay");
            deep_aether_biome_weight = builder
                    .comment("The weighting of Deep Aether regions in the aether")
                    .translation("config.deep_aether.common.gameplay.deep_aether_region_weight")
                    .define("Deep Aether Region Weight", 2);
            builder.pop();
        }
    }


    public static final ForgeConfigSpec COMMON_SPEC;
    public static final Common COMMON;

    static {
        final Pair<Common, ForgeConfigSpec> commonSpecPair = new ForgeConfigSpec.Builder().configure(Common::new);
        COMMON_SPEC = commonSpecPair.getRight();
        COMMON = commonSpecPair.getLeft();
    }
}
