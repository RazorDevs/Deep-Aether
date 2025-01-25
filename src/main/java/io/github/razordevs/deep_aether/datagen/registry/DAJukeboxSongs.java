package io.github.razordevs.deep_aether.datagen.registry;

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
    public static final ResourceKey<JukeboxSong> NABOORU = create("nabooru");
    public static final ResourceKey<JukeboxSong> A_MORNING_WISH = create("a_morning_wish");

    public static final ResourceKey<JukeboxSong> CYCLONE = create("cyclone");
    public static final ResourceKey<JukeboxSong> ABOVE_THE_RAIN = create("above_the_rain");

    public static final ResourceKey<JukeboxSong> ATTA = create("atta");
    public static final ResourceKey<JukeboxSong> FAENT = create("faent");
    public static final ResourceKey<JukeboxSong> HIMININN = create("himininn");

    private static ResourceKey<JukeboxSong> create(String pName) {
        return ResourceKey.create(Registries.JUKEBOX_SONG, ResourceLocation.fromNamespaceAndPath(DeepAether.MODID, pName));
    }

    public static void bootstrap(BootstrapContext<JukeboxSong> context) {
        register(context, NABOORU, (Holder.Reference<SoundEvent>) DASounds.NABOORU.getDelegate(), 364, 7);
        register(context, A_MORNING_WISH, (Holder.Reference<SoundEvent>) DASounds.A_MORNING_WISH.getDelegate(), 283, 8);

        register(context, CYCLONE, (Holder.Reference<SoundEvent>) DASounds.CYCLONE.getDelegate(), 364, 9);

        register(context, ATTA, (Holder.Reference<SoundEvent>) DASounds.ATTA.getDelegate(), 129, 10);
        register(context, FAENT, (Holder.Reference<SoundEvent>) DASounds.FAENT.getDelegate(), 223, 11);
        register(context, HIMININN, (Holder.Reference<SoundEvent>) DASounds.HIMININN.getDelegate(), 180, 12);

        register(context, ABOVE_THE_RAIN, (Holder.Reference<SoundEvent>) DASounds.ABOVE_THE_RAIN.getDelegate(), 249, 13);
    }

    private static void register(BootstrapContext<JukeboxSong> context, ResourceKey<JukeboxSong> key, Holder.Reference<SoundEvent> soundEvent, int lengthInSeconds, int comparatorOutput) {
        context.register(key, new JukeboxSong(soundEvent, Component.translatable(Util.makeDescriptionId("jukebox_song", key.location())), (float) lengthInSeconds, comparatorOutput));
    }
}
