package teamrazor.deepaether.mixin;

import com.aetherteam.aether.client.renderer.level.AetherSkyRenderEffects;
import net.minecraft.resources.ResourceLocation;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Mutable;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin(value = AetherSkyRenderEffects.class, remap = false)
public interface AetherSkyRenderEffectsAccessor {
    @Mutable
    @Accessor
    static void setMOON_LOCATION(ResourceLocation MOON_LOCATION) {
        throw new UnsupportedOperationException();
    }
}
