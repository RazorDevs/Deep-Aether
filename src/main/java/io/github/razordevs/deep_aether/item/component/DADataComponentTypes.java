package io.github.razordevs.deep_aether.item.component;

import io.github.razordevs.deep_aether.DeepAether;
import net.minecraft.core.component.DataComponentType;
import net.minecraft.core.registries.Registries;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

public class DADataComponentTypes {
    public static final DeferredRegister.DataComponents DATA_COMPONENT_TYPES = DeferredRegister.createDataComponents(
            Registries.DATA_COMPONENT_TYPE, DeepAether.MODID);

    public static final DeferredHolder<DataComponentType<?>, DataComponentType<DungeonTrackerPos>> DUNGEON_TRACKER_POS = DATA_COMPONENT_TYPES.registerComponentType(
            "dungeon_tracker_pos",
            builder -> builder
                    .persistent(DungeonTrackerPos.CODEC)
                    .networkSynchronized(DungeonTrackerPos.STREAM_CODEC)
    );

    public static final DeferredHolder<DataComponentType<?>, DataComponentType<MoaFodder>> MOA_FODDER = DATA_COMPONENT_TYPES.registerComponentType(
            "moa_fodder",
            builder -> builder
                    .persistent(MoaFodder.CODEC)
                    .networkSynchronized(MoaFodder.STREAM_CODEC)
    );

    public static final DeferredHolder<DataComponentType<?>, DataComponentType<FloatyScarf>> FLOATY_SCARF = DATA_COMPONENT_TYPES.registerComponentType(
            "floaty_scarf",
            builder -> builder
                    .persistent(FloatyScarf.CODEC)
                    .networkSynchronized(FloatyScarf.STREAM_CODEC)
    );
}
