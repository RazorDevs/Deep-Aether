package teamrazor.deepaether.mixin;

import net.minecraft.core.BlockPos;
import net.minecraft.world.WorldlyContainer;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BaseContainerBlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.entity.BrewingStandBlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import teamrazor.deepaether.init.DAItems;

@Mixin(value = BrewingStandBlockEntity.class)
public abstract class BrewingFuelMixin extends BaseContainerBlockEntity implements WorldlyContainer {

    public BrewingFuelMixin(BlockEntityType<?> entityType, BlockPos blockPos, BlockState blockState) {
        super(entityType, blockPos, blockState);
    }

    @Inject(at = @At("HEAD"), method = "Lnet/minecraft/world/level/block/entity/BrewingStandBlockEntity;serverTick(Lnet/minecraft/world/level/Level;Lnet/minecraft/core/BlockPos;Lnet/minecraft/world/level/block/state/BlockState;Lnet/minecraft/world/level/block/entity/BrewingStandBlockEntity;)V")
    private static void serverTick(Level level, BlockPos blockPos, BlockState blockState, BrewingStandBlockEntity blockEntity, CallbackInfo ci){
        ItemStack itemstack = blockEntity.items.get(4);
        if (blockEntity.fuel <= 0 && itemstack.is(DAItems.BIO_CRYSTAL.get())) {
            blockEntity.fuel = 20;
            itemstack.shrink(1);
            setChanged(level, blockPos, blockState);
        }
    }
}
