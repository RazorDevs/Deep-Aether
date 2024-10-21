package io.github.razordevs.deep_aether.world.structure.brass.processor;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.core.Holder;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructureProcessorList;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructureProcessorType;

public record BrassProcessorSettings(Holder<StructureProcessorList> bossSettings, Holder<StructureProcessorList> roomSettings) {
    public static final Codec<BrassProcessorSettings> CODEC = RecordCodecBuilder.create(builder -> builder.group(
            StructureProcessorType.LIST_CODEC.fieldOf("boss_room_processors").forGetter(BrassProcessorSettings::bossSettings),
            StructureProcessorType.LIST_CODEC.fieldOf("room_processors").forGetter(BrassProcessorSettings::bossSettings)
    ).apply(builder, BrassProcessorSettings::new));
}