package teamrazor.deepaether;

import net.minecraftforge.common.ForgeConfig;
import net.minecraft.world.level.Level;
import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.common.ForgeConfigSpec.ConfigValue;
import org.apache.commons.lang3.tuple.Pair;

public class DeepAetherConfig {

    public static class Common {
        public final ConfigValue<Boolean> skyjade_enchant;
        public final ConfigValue<Integer> deep_aether_biome_weight;
        public final ConfigValue<Boolean> always_enable_halloween_content;

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
                    .translation("config.deep_aether.common.gameplay.deep_aether_region_weight")
                    .define("Deep Aether Region Weight", 3);
            builder.pop();

            builder.push("Gameplay");
            always_enable_halloween_content = builder
                    .comment("Always Enable halloween content, excluding the halloween slider")
                    .translation("config.deep_aether.common.gameplay.always_enable_halloween_content")
                    .define("Always Enable Halloween Content", false);
            builder.pop();
        }
    }
    public static class Client {
        public final ConfigValue<Boolean> always_enable_halloween_slider;
        public final ConfigValue<Boolean> never_enable_halloween_slider;
        public Client(ForgeConfigSpec.Builder builder) {
            builder.push("Rendering");
            always_enable_halloween_slider = builder
                    .comment("Always Enables the halloween Slider")
                    .translation("config.deep_aether.common.gameplay.always_enable_halloween_slider")
                    .define("Always Enable Halloween Slider", false);
            builder.pop();

            builder.push("Rendering");
            never_enable_halloween_slider = builder
                    .comment("Never Enables the halloween Slider, not even during halloween")
                    .translation("config.deep_aether.common.gameplay.never_enable_halloween_slider")
                    .define("Never Enable Halloween Slider", false);
            builder.pop();
        }
    }


    public static final ForgeConfigSpec COMMON_SPEC;
    public static final Common COMMON;

    public static final ForgeConfigSpec CLIENT_SPEC;
    public static final Client CLIENT;

    static {
        final Pair<Common, ForgeConfigSpec> commonSpecPair = new ForgeConfigSpec.Builder().configure(Common::new);
        COMMON_SPEC = commonSpecPair.getRight();
        COMMON = commonSpecPair.getLeft();

        final Pair<Client, ForgeConfigSpec> clientSpecPair = new ForgeConfigSpec.Builder().configure(Client::new);
        CLIENT_SPEC = clientSpecPair.getRight();
        CLIENT = clientSpecPair.getLeft();
    }
}
