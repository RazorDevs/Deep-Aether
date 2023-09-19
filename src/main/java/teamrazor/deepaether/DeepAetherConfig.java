package teamrazor.deepaether;

import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.common.ForgeConfigSpec.ConfigValue;
import org.apache.commons.lang3.tuple.Pair;

public class DeepAetherConfig {

    public static class Common {
        public final ConfigValue<Boolean> skyjade_enchant;
        public final ConfigValue<Integer> deep_aether_biome_weight;

        public Common(ForgeConfigSpec.Builder builder) {
            builder.push("Gameplay");
            skyjade_enchant = builder
                    .comment("Skyjade tools will be enchantable")
                    .translation("config.deep_aether.common.gameplay.skyjade_enchant")
                    .define("Skyjade Enchant", false);
            builder.pop();

            builder.push("Gameplay");
            deep_aether_biome_weight = builder
                    .comment("The weighting of Deep Aether regions in the aether")
                    .translation("config.deep_aether.common.gameplay.deep_aether_biome_weight")
                    .define("Deep Aether Biome Weight", 15);
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
