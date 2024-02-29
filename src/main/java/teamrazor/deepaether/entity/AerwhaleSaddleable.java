package teamrazor.deepaether.entity;

import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.Saddleable;
import org.jetbrains.annotations.NotNull;

import javax.annotation.Nullable;

public interface AerwhaleSaddleable {
    boolean isSaddleable();

    void equipSaddle(@Nullable SoundSource p_21748_);
    boolean isSaddled();
}
