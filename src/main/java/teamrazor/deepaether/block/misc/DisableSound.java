package teamrazor.deepaether.block.misc;

import org.spongepowered.asm.mixin.Unique;

public interface DisableSound {

    @Unique
    void deep_Aether$disableSound(boolean disableSound);
}
