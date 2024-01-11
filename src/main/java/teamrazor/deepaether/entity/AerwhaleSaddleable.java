package teamrazor.deepaether.entity;

import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import org.jetbrains.annotations.NotNull;

import javax.annotation.Nullable;

public interface AerwhaleSaddleable {
    boolean isSaddleable();

    void equipSaddle(@Nullable SoundSource p_21748_);

    default @NotNull SoundEvent getSaddleSoundEvent() {
        return SoundEvents.HORSE_SADDLE;
    }

    boolean isSaddled();
}
