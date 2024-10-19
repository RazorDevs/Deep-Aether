package io.github.razordevs.deepaether.deepaether.datagen.tags;

import io.github.razordevs.deepaether.deepaether.init.DAFluids;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.FluidTagsProvider;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import org.jetbrains.annotations.Nullable;
import teamrazor.deepaether.DeepAether;

import javax.annotation.Nonnull;
import java.util.concurrent.CompletableFuture;

public class DAFluidTagData extends FluidTagsProvider {

    public DAFluidTagData(PackOutput output, CompletableFuture<HolderLookup.Provider> registries, @Nullable ExistingFileHelper helper) {
        super(output, registries, DeepAether.MODID, helper);
    }

    @Nonnull
    @Override
    public String getName() {
        return "Deep Aether Fluid Tags";
    }

    @SuppressWarnings("unchecked")
    @Override
    protected void addTags(HolderLookup.Provider p_256380_) {
        tag(DATags.Fluids.POISON).add(
                DAFluids.POISON_FLUID.get()
        );
    }
}
