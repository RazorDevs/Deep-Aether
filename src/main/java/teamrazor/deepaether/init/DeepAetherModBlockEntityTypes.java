package teamrazor.deepaether.init;

import com.gildedgames.aether.block.AetherBlocks;

import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import teamrazor.deepaether.DeepAetherMod;
import teamrazor.deepaether.entity.DeepAetherModSignBlockEntity;

public class DeepAetherModBlockEntityTypes {
    public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITY_TYPES = DeferredRegister.create(ForgeRegistries.BLOCK_ENTITY_TYPES, DeepAetherMod.MODID);

    public static final RegistryObject<BlockEntityType<DeepAetherModSignBlockEntity>> SIGN = BLOCK_ENTITY_TYPES.register("sign", () -> BlockEntityType.Builder.of(DeepAetherModSignBlockEntity::new,
            DeepAetherModBlocks.YAGROOT_SIGN.get(), DeepAetherModBlocks.YAGROOT_WALL_SIGN.get(),
            DeepAetherModBlocks.CRUDEROOT_SIGN.get(), DeepAetherModBlocks.CRUDEROOT_WALL_SIGN.get(),
            DeepAetherModBlocks.ROSEROOT_SIGN.get(), DeepAetherModBlocks.ROSEROOT_WALL_SIGN.get()
    ).build(null));
}