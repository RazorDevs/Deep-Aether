package io.github.razordevs.deep_aether.mixin;

import com.aetherteam.aether.client.renderer.level.AetherSkyRenderEffects;
import io.github.razordevs.deep_aether.item.gear.other.SpookyRing;
import net.minecraft.resources.ResourceLocation;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Mutable;
import org.spongepowered.asm.mixin.gen.Accessor;

/**
 * Used in an Easter egg to change the texture of the moon
 * See {@link SpookyRing} for the full easter egg
 */
@Mixin(value = AetherSkyRenderEffects.class, remap = false)
public interface AetherSkyRenderEffectsAccessor {
    @Mutable
    @Accessor
    static void setMOON_LOCATION(ResourceLocation MOON_LOCATION) {
        throw new UnsupportedOperationException();
    }
}
