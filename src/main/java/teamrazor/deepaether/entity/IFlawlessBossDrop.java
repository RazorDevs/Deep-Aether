package teamrazor.deepaether.entity;

import org.spongepowered.asm.mixin.Unique;

/**
 * Used to get and set a boolean value to indicate if the player or players has been hurt during a boss fight.
 * Used in the flawless boss check
 */
public interface IFlawlessBossDrop {

    @Unique
    boolean deep_Aether$hasBeenHurt();

    @Unique
    void deep_Aether$setHasBeenHurt(boolean bool);
}
