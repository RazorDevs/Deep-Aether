package io.github.razordevs.deep_aether.item.component;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import io.netty.buffer.ByteBuf;
import net.minecraft.core.BlockPos;
import net.minecraft.core.GlobalPos;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.ai.village.poi.PoiTypes;

import java.util.Optional;

public record DungeonTracker(Optional<GlobalPos> target, boolean found) {
    public static final Codec<DungeonTracker> CODEC = RecordCodecBuilder.create(
            p_337949_ -> p_337949_.group(
                            GlobalPos.CODEC.optionalFieldOf("target").forGetter(DungeonTracker::target),
                            Codec.BOOL.optionalFieldOf("tracked", Boolean.TRUE).forGetter(DungeonTracker::found)
                    )
                    .apply(p_337949_, DungeonTracker::new)
    );
    public static final StreamCodec<ByteBuf, DungeonTracker> STREAM_CODEC = StreamCodec.composite(
            GlobalPos.STREAM_CODEC.apply(ByteBufCodecs::optional), DungeonTracker::target, ByteBufCodecs.BOOL, DungeonTracker::found, DungeonTracker::new
    );
}