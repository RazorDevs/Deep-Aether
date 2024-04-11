package teamrazor.deepaether.init;

import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import teamrazor.deepaether.DeepAetherMod;
import teamrazor.deepaether.entity.block.CombinerBlockEntity;
import teamrazor.deepaether.entity.block.DAHangingSignBlockEntity;
import teamrazor.deepaether.entity.block.DASignBlockEntity;

public class DABlockEntityTypes {
    public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITY_TYPES = DeferredRegister.create(ForgeRegistries.BLOCK_ENTITY_TYPES, DeepAetherMod.MODID);

    public static final RegistryObject<BlockEntityType<DASignBlockEntity>> SIGN = BLOCK_ENTITY_TYPES.register("sign", () -> BlockEntityType.Builder.of(DASignBlockEntity::new,
            DABlocks.YAGROOT_SIGN.get(), DABlocks.YAGROOT_WALL_SIGN.get(),
            DABlocks.CRUDEROOT_SIGN.get(), DABlocks.CRUDEROOT_WALL_SIGN.get(),
            DABlocks.ROSEROOT_SIGN.get(), DABlocks.ROSEROOT_WALL_SIGN.get(),
            DABlocks.CONBERRY_SIGN.get(), DABlocks.CONBERRY_WALL_SIGN.get(),
            DABlocks.SUNROOT_SIGN.get(), DABlocks.SUNROOT_WALL_SIGN.get()
    ).build(null));

    public static final RegistryObject<BlockEntityType<DAHangingSignBlockEntity>> HANGING_SIGN = BLOCK_ENTITY_TYPES.register("hanging_sign", () ->
            BlockEntityType.Builder.of(DAHangingSignBlockEntity::new,
                    DABlocks.YAGROOT_WALL_HANGING_SIGN.get(), DABlocks.YAGROOT_HANGING_SIGN.get(),
                    DABlocks.CRUDEROOT_WALL_HANGING_SIGN.get(), DABlocks.CRUDEROOT_HANGING_SIGN.get(),
                    DABlocks.ROSEROOT_WALL_HANGING_SIGN.get(), DABlocks.ROSEROOT_HANGING_SIGN.get(),
                    DABlocks.CONBERRY_WALL_HANGING_SIGN.get(), DABlocks.CONBERRY_HANGING_SIGN.get(),
                    DABlocks.SUNROOT_WALL_HANGING_SIGN.get(), DABlocks.SUNROOT_HANGING_SIGN.get()
            ).build(null));

    public static final RegistryObject<BlockEntityType<CombinerBlockEntity>> COMBINER_BE =
            BLOCK_ENTITY_TYPES.register("combiner_be", () ->
                    BlockEntityType.Builder.of(CombinerBlockEntity::new,
                            DABlocks.COMBINER.get()).build(null));
}