package teamrazor.deepaether.mixin;

import com.aetherteam.aether.client.renderer.level.AetherSkyRenderEffects;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.chunk.ChunkAccess;
import net.minecraft.world.level.levelgen.NoiseChunk;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Mutable;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.gen.Accessor;
import org.spongepowered.asm.mixin.gen.Invoker;

@Mixin(AetherSkyRenderEffects.class)
public interface AetherSkyRenderEffectsAccessor {
    @Mutable
    @Accessor
    static void setMOON_LOCATION(ResourceLocation MOON_LOCATION) {
        throw new UnsupportedOperationException();
    }
}
