package io.github.razordevs.deep_aether.item.component;

import com.jcraft.jorbis.Block;
import io.github.razordevs.deep_aether.DeepAether;
import net.minecraft.core.BlockPos;
import net.minecraft.core.component.DataComponentType;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

public class DADataComponentTypes {
    public static final DeferredRegister.DataComponents DATA_COMPONENT_TYPES = DeferredRegister.createDataComponents(
            Registries.DATA_COMPONENT_TYPE, DeepAether.MODID);

    public static final DeferredHolder<DataComponentType<?>, DataComponentType<ResourceLocation>> DUNGEON = DATA_COMPONENT_TYPES.registerComponentType(
            "dungeon",
            builder -> builder
                    .persistent(ResourceLocation.CODEC)
                    .networkSynchronized(ResourceLocation.STREAM_CODEC)
    );

    public static final DeferredHolder<DataComponentType<?>, DataComponentType<BlockPos>> DUNGEON_POS = DATA_COMPONENT_TYPES.registerComponentType(
            "dungeon_pos",
            builder -> builder
                    .persistent(BlockPos.CODEC)
                    .networkSynchronized(BlockPos.STREAM_CODEC)
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
