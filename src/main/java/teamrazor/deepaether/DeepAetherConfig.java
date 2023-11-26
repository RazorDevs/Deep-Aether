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
        public final ConfigValue<Boolean> disable_roseroot_forest_biomes;
        public final ConfigValue<Boolean> disable_yagroot_swap_biomes;
        public final ConfigValue<Boolean> disable_golden_heights_biomes;
        public final ConfigValue<Boolean> disable_aerlavenender_field_biomes;
        public final ConfigValue<Boolean> use_aether_tabs;


        public Common(ForgeConfigSpec.Builder builder) {
            builder.push("Gameplay");
            skyjade_enchant = builder
                    .comment("Skyjade tools will be enchantable")
                    .translation("config.deep_aether.common.gameplay.skyjade_enchant")
                    .define("Skyjade Enchant", false);
            builder.pop();

            builder.push("Gameplay");
            always_enable_halloween_content = builder
                    .comment("Always Enable halloween content, excluding the halloween slider")
                    .translation("config.deep_aether.common.gameplay.always_enable_halloween_content")
                    .define("Always Enable Halloween Content", false);
            builder.pop();

            builder.push("Gameplay");
            use_aether_tabs = builder
                    .comment("Enabling this removes Deep Aether's creative mode tab, and adds Deep Aether's items to Aether's tabs")
                    .translation("config.deep_aether.common.gameplay.use_aether_tabs")
                    .define("Use Aether's Creative Mode Tabs", false);
            builder.pop();

            builder.push("Biomes");
            deep_aether_biome_weight = builder
                    .comment("The weighting of Deep Aether regions in the aether")
                    .translation("config.deep_aether.common.gameplay.deep_aether_biome_weight")
                    .define("Deep Aether Biome Weight", 20);
            builder.pop();

            builder.push("Biomes");
            disable_roseroot_forest_biomes = builder
                    .comment("Disables Roseroot Forest biomes from generating. Might make some content unobtainable")
                    .translation("config.deep_aether.common.gameplay.disable_roseroot_forest_biomes")
                    .define("Disable Roseroot Forest Biomes", false);
            builder.pop();

            builder.push("Biomes");
            disable_yagroot_swap_biomes = builder
                    .comment("Disables Yagroot Swamp biomes from generating. Might make some content unobtainable")
                    .translation("config.deep_aether.common.gameplay.disable_yagroot_swap_biomes")
                    .define("Disable Yagroot Swamp Biomes", false);
            builder.pop();

            builder.push("Biomes");
            disable_golden_heights_biomes = builder
                    .comment("Disables Golden Heights biomes from generating. Might make some content unobtainable")
                    .translation("config.deep_aether.common.gameplay.disable_golden_heights_biomes")
                    .define("Disable Golden Heights Biomes", false);
            builder.pop();

            builder.push("Biomes");
            disable_aerlavenender_field_biomes = builder
                    .comment("Disables Aerlavender Fields biomes from generating. Might make some content unobtainable")
                    .translation("config.deep_aether.common.gameplay.disable_aerlavenender_field_biomes")
                    .define("Disable Aerlavender Fields Biomes", false);
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
