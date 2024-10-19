package io.github.razordevs.deep_aether.init;

import io.github.razordevs.deep_aether.entity.block.CombinerBlockEntity;
import io.github.razordevs.deep_aether.entity.block.DAHangingSignBlockEntity;
import io.github.razordevs.deep_aether.entity.block.DASignBlockEntity;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;
import teamrazor.deepaether.DeepAether;

public class DABlockEntityTypes {
    public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITY_TYPES = DeferredRegister.create( BuiltInRegistries.BLOCK_ENTITY_TYPE, DeepAether.MODID);

    public static final DeferredHolder<BlockEntityType<?>, BlockEntityType<DASignBlockEntity>> SIGN = BLOCK_ENTITY_TYPES.register("sign", () -> BlockEntityType.Builder.of(DASignBlockEntity::new,
            DABlocks.YAGROOT_SIGN.get(), DABlocks.YAGROOT_WALL_SIGN.get(),
            DABlocks.CRUDEROOT_SIGN.get(), DABlocks.CRUDEROOT_WALL_SIGN.get(),
            DABlocks.ROSEROOT_SIGN.get(), DABlocks.ROSEROOT_WALL_SIGN.get(),
            DABlocks.CONBERRY_SIGN.get(), DABlocks.CONBERRY_WALL_SIGN.get(),
            DABlocks.SUNROOT_SIGN.get(), DABlocks.SUNROOT_WALL_SIGN.get()
    ).build(null));

    public static final DeferredHolder<BlockEntityType<?>, BlockEntityType<DAHangingSignBlockEntity>> HANGING_SIGN = BLOCK_ENTITY_TYPES.register("hanging_sign", () ->
            BlockEntityType.Builder.of(DAHangingSignBlockEntity::new,
                    DABlocks.YAGROOT_WALL_HANGING_SIGN.get(), DABlocks.YAGROOT_HANGING_SIGN.get(),
                    DABlocks.CRUDEROOT_WALL_HANGING_SIGN.get(), DABlocks.CRUDEROOT_HANGING_SIGN.get(),
                    DABlocks.ROSEROOT_WALL_HANGING_SIGN.get(), DABlocks.ROSEROOT_HANGING_SIGN.get(),
                    DABlocks.CONBERRY_WALL_HANGING_SIGN.get(), DABlocks.CONBERRY_HANGING_SIGN.get(),
                    DABlocks.SUNROOT_WALL_HANGING_SIGN.get(), DABlocks.SUNROOT_HANGING_SIGN.get()
            ).build(null));

    public static final DeferredHolder<BlockEntityType<?>, BlockEntityType<CombinerBlockEntity>> COMBINER =
            BLOCK_ENTITY_TYPES.register("combiner", () ->
                    BlockEntityType.Builder.of(CombinerBlockEntity::new,
                            DABlocks.COMBINER.get()).build(null));
}