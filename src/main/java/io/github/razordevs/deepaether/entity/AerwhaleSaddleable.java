package io.github.razordevs.deepaether.deepaether.entity;

import net.minecraft.sounds.SoundSource;

import javax.annotation.Nullable;

public interface AerwhaleSaddleable {
    boolean isSaddleable();

    void equipSaddle(@Nullable SoundSource p_21748_);
    boolean isSaddled();
}
