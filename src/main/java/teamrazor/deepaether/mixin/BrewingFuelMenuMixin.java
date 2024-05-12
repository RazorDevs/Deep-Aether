package teamrazor.deepaether.mixin;

import net.minecraft.world.Container;
import net.minecraft.world.inventory.BrewingStandMenu;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import teamrazor.deepaether.init.DAItems;

@Mixin(value = BrewingStandMenu.FuelSlot.class)
public abstract class BrewingFuelMenuMixin extends Slot {

    public BrewingFuelMenuMixin(Container container, int i, int i1, int i2) {
        super(container, i, i1, i2);
    }

    @Inject(at = @At("HEAD"), method = "mayPlaceItem", remap = false, cancellable = true)
    private static void shardCheck(ItemStack stack, CallbackInfoReturnable<Boolean> cir) {
        if (stack.is(DAItems.BIO_CRYSTAL.get())) {
            cir.setReturnValue(true);
        }
    }
}
