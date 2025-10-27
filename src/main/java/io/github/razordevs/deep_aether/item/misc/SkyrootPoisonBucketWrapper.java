package io.github.razordevs.deep_aether.item.misc;

import com.aetherteam.aether.item.miscellaneous.bucket.SkyrootBucketWrapper;
import io.github.razordevs.deep_aether.init.DAFluids;
import net.minecraft.world.item.ItemStack;
import net.neoforged.neoforge.fluids.FluidStack;
import net.neoforged.neoforge.fluids.FluidType;


public class SkyrootPoisonBucketWrapper extends SkyrootBucketWrapper {
    public SkyrootPoisonBucketWrapper(ItemStack container) {
        super(container);
    }

    @Override
    public FluidStack getFluid() {
        return new FluidStack(DAFluids.POISON_FLUID.get(), FluidType.BUCKET_VOLUME);
    }
}
