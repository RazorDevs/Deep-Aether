package io.github.razordevs.deep_aether;

import net.neoforged.neoforge.common.ModConfigSpec;
import net.neoforged.neoforge.common.ModConfigSpec.ConfigValue;
import org.apache.commons.lang3.tuple.Pair;

public class DeepAetherConfig {

    public static class Common {
        public final ConfigValue<Boolean> skyjade_enchant;
        public final ConfigValue<Integer> deep_aether_biome_weight;
        public final ConfigValue<Integer> stratus_dash_cooldown;
        public final ConfigValue<Boolean> always_enable_halloween_content;
        public final ConfigValue<Boolean> disable_roseroot_forest_biomes;
        public final ConfigValue<Boolean> disable_yagroot_swap_biomes;
        public final ConfigValue<Boolean> disable_golden_heights_biomes;
        public final ConfigValue<Boolean> disable_aerlavenender_field_biomes;
        public final ConfigValue<Boolean> disable_sacred_lands_biomes;
        public final ConfigValue<Boolean> disable_storm_cloud_and_skyroot_rainforest_biomes;
        public final ConfigValue<Integer> storm_cloud_biome_weight;
        public final ConfigValue<String> slider_flawless_boss_drop;
        public final ConfigValue<String> valkyrie_queen_flawless_boss_drop;
        public final ConfigValue<String> sun_spirit_flawless_boss_drop;
        public final ConfigValue<String> eots_flawless_boss_drop;
        public final ConfigValue<String> aerwhale_king_flawless_boss_drop;

        public Common(ModConfigSpec.Builder builder) {
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
            slider_flawless_boss_drop = builder
                    .comment("The item dropped when the Slider is defeated flawlessly, set value to null (with quotation marks!) to disable flawless boss drops from the slider")
                    .translation("config.deep_aether.common.flawless.slider_flawless_boss_drop")
                    .define("Slider Flawless Boss Drop", "deep_aether:slider_eye");
            builder.pop();

            builder.push("Gameplay");
            valkyrie_queen_flawless_boss_drop = builder
                    .comment("The item dropped when the Valkyrie Queen is defeated flawlessly, set value to null (with quotation marks!) to disable flawless boss drops from the Valkyrie Queen")
                    .translation("config.deep_aether.common.flawless.valkyrie_queen_flawless_boss_drop")
                    .define("Valkyrie Queen Flawless Boss Drop", "deep_aether:medal_of_honor");
            builder.pop();

            builder.push("Gameplay");
            sun_spirit_flawless_boss_drop = builder
                    .comment("The item dropped when the Sun Spirit is defeated flawlessly, set value to null (with quotation marks!) to disable flawless boss drops from the Sun Spirit")
                    .translation("config.deep_aether.common.flawless.sun_spirit_flawless_boss_drop")
                    .define("Sun Spirit Flawless Boss Drop", "deep_aether:sun_core");
            builder.pop();

            builder.push("Gameplay");
            eots_flawless_boss_drop = builder
                    .comment("The item dropped when the Eye of the Storm is defeated flawlessly, set value to null (with quotation marks!) to disable flawless boss drops from the Eye of the Storm")
                    .translation("config.deep_aether.common.flawless.eots_flawless_boss_drop")
                    .define("Eye of the Storm Flawless Boss Drop", "deep_aether:sun_core");
            builder.pop();

            builder.push("Gameplay");
            aerwhale_king_flawless_boss_drop = builder
                    .comment("The item dropped when the Aerwhale King is defeated flawlessly, set value to null (with quotation marks!) to disable flawless boss drops from the Aerwhale King. This Config Does nothing if Aether Lost Content isn't installed.")
                    .translation("config.deep_aether.common.flawless.aerwhale_king_flawless_boss_drop")
                    .define("Aerwhale King Flawless Boss Drop", "deep_aether:aerwhale_saddle");
            builder.pop();

            builder.push("Gameplay");
            stratus_dash_cooldown = builder
                    .comment("The cooldown of the stratus dash. Set to a value below zero to disable the cooldown.")
                    .translation("config.deep_aether.common.stratus_dash_cooldown")
                    .define("Stratus dash cooldown", 5);
            builder.pop();

            builder.push("Biomes");
            deep_aether_biome_weight = builder
                    .comment("The weighting of Deep Aether regions in the aether")
                    .translation("config.deep_aether.common.gameplay.deep_aether_biome_weight")
                    .define("Deep Aether Biome Weight", 15);
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

            builder.push("Biomes");
            disable_sacred_lands_biomes = builder
                    .comment("Disables Sacred Lands biomes from generating. Might make some content unobtainable")
                    .translation("config.deep_aether.common.gameplay.disable_sacred_lands_biomes")
                    .define("Disable Sacred Lands Biomes", false);
            builder.pop();

            builder.push("Biomes");
            disable_storm_cloud_and_skyroot_rainforest_biomes = builder
                    .comment("Disables Storm Cloud and Skyroot Rainforest biomes from generating. Might make some content unobtainable")
                    .translation("config.deep_aether.common.gameplay.disable_storm_cloud_and_skyroot_rainforest_biomes")
                    .define("Disable Storm Cloud And Skyroot Rainforest Biomes", false);
            builder.pop();

            builder.push("Biomes");
            storm_cloud_biome_weight = builder
                    .comment("The weighting of Storm Cloud regions in the aether")
                    .translation("config.deep_aether.common.gameplay.storm_cloud_biome_weight")
                    .define("Storm Cloud Biome Weight", 1);
            builder.pop();
        }
    }
    public static class Client {
        public final ConfigValue<Boolean> always_enable_halloween_slider;
        public final ConfigValue<Boolean> never_enable_halloween_slider;

        public final ConfigValue<Integer> stratus_cooldown_indicator_x_position;
        public final ConfigValue<Integer> stratus_colldown_indicator_y_position;

        public Client(ModConfigSpec.Builder builder) {
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

            builder.push("Gui");
            stratus_cooldown_indicator_x_position = builder
                    .comment("The horizontal position of the stratus cooldown icon")
                    .translation("config.deep_aether.client.gui.stratus_cooldown_indicator_x_position")
                    .define("Stratus Cooldown Indicator Horizontal Position", 354);
            builder.pop();

            builder.push("Gui");
            stratus_colldown_indicator_y_position = builder
                    .comment("The vertical position of the stratus cooldown icon")
                    .translation("config.deep_aether.client.gui.stratus_cooldown_indicator_y_position")
                    .define("Stratus Cooldown Indicator Vertical Position", 17);
            builder.pop();
        }
    }


    public static final ModConfigSpec COMMON_SPEC;
    public static final Common COMMON;

    public static final ModConfigSpec CLIENT_SPEC;
    public static final Client CLIENT;

    static {
        final Pair<Common, ModConfigSpec> commonSpecPair = new ModConfigSpec.Builder().configure(Common::new);
        COMMON_SPEC = commonSpecPair.getRight();
        COMMON = commonSpecPair.getLeft();

        final Pair<Client, ModConfigSpec> clientSpecPair = new ModConfigSpec.Builder().configure(Client::new);
        CLIENT_SPEC = clientSpecPair.getRight();
        CLIENT = clientSpecPair.getLeft();
    }
}
