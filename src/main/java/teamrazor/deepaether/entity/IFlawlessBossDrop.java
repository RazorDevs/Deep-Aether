package teamrazor.deepaether.entity;

import org.spongepowered.asm.mixin.Unique;


public interface IFlawlessBossDrop {

    @Unique
    boolean deep_Aether$hasBeenHurt();

    @Unique
    void deep_Aether$setHasBeenHurt(boolean bool);
}
