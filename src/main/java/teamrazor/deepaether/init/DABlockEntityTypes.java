package teamrazor.deepaether.init;

import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import teamrazor.deepaether.DeepAetherMod;
import teamrazor.deepaether.entity.DASignBlockEntity;

public class DABlockEntityTypes {
    public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITY_TYPES = DeferredRegister.create(ForgeRegistries.BLOCK_ENTITY_TYPES, DeepAetherMod.MODID);

    public static final RegistryObject<BlockEntityType<DASignBlockEntity>> SIGN = BLOCK_ENTITY_TYPES.register("sign", () -> BlockEntityType.Builder.of(DASignBlockEntity::new,
            DABlocks.YAGROOT_SIGN.get(), DABlocks.YAGROOT_WALL_SIGN.get(),
            DABlocks.CRUDEROOT_SIGN.get(), DABlocks.CRUDEROOT_WALL_SIGN.get(),
            DABlocks.ROSEROOT_SIGN.get(), DABlocks.ROSEROOT_WALL_SIGN.get(),
            DABlocks.AMBERROOT_SIGN.get(), DABlocks.AMBERROOT_WALL_SIGN.get()

    ).build(null));
}