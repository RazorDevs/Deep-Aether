package io.github.razordevs.deep_aether.datagen;

import io.github.razordevs.deep_aether.DeepAether;
import io.github.razordevs.deep_aether.init.DASounds;
import net.minecraft.Util;
import net.minecraft.core.Holder;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.item.JukeboxSong;

public class DAJukeboxSongs {
    public static ResourceKey<JukeboxSong> NABOORU = create("nabooru");
    public static ResourceKey<JukeboxSong> A_MORNING_WISH = create("a_morning_wish");

    private static ResourceKey<JukeboxSong> create(String pName) {
        return ResourceKey.create(Registries.JUKEBOX_SONG, ResourceLocation.fromNamespaceAndPath(DeepAether.MODID, pName));
    }

    public static void bootstrap(BootstrapContext<JukeboxSong> context) {
        register(context, NABOORU, (Holder.Reference<SoundEvent>) DASounds.NABOORU.getDelegate(), 364, 7);
        register(context, A_MORNING_WISH, (Holder.Reference<SoundEvent>) DASounds.NABOORU.getDelegate(), 283, 8);
    }

    private static void register(BootstrapContext<JukeboxSong> context, ResourceKey<JukeboxSong> key, Holder.Reference<SoundEvent> soundEvent, int lengthInSeconds, int comparatorOutput) {
        context.register(key, new JukeboxSong(soundEvent, Component.translatable(Util.makeDescriptionId("jukebox_song", key.location())), (float) lengthInSeconds, comparatorOutput));
    }
}
