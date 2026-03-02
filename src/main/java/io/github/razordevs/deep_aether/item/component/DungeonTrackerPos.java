package io.github.razordevs.deep_aether.item.component;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import io.netty.buffer.ByteBuf;
import net.minecraft.core.GlobalPos;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.resources.ResourceLocation;

import java.util.Optional;

public record DungeonTrackerPos(ResourceLocation dungeon, Optional<GlobalPos> target, boolean found) {
    public static final Codec<DungeonTrackerPos> CODEC = RecordCodecBuilder.create(
            record -> record.group(
                            ResourceLocation.CODEC.fieldOf("dungeon").forGetter(DungeonTrackerPos::dungeon),
                            GlobalPos.CODEC.optionalFieldOf("target").forGetter(DungeonTrackerPos::target),
                            Codec.BOOL.optionalFieldOf("tracked", Boolean.TRUE).forGetter(DungeonTrackerPos::found)
                    )
                    .apply(record, DungeonTrackerPos::new)
    );
    public static final StreamCodec<ByteBuf, DungeonTrackerPos> STREAM_CODEC = StreamCodec.composite(
            ResourceLocation.STREAM_CODEC, DungeonTrackerPos::dungeon,
            GlobalPos.STREAM_CODEC.apply(ByteBufCodecs::optional),
            DungeonTrackerPos::target, ByteBufCodecs.BOOL, DungeonTrackerPos::found, DungeonTrackerPos::new
    );
}