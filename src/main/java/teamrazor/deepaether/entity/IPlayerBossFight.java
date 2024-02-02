package teamrazor.deepaether.entity;

import org.spongepowered.asm.mixin.Unique;

/**
 * Used to get and set a saved boss inside the player class through mixin. Used in the flawless boss check
 */
public interface IPlayerBossFight {

    @Unique
    boolean deep_Aether$getHasBeenHurt();

    @Unique
    void deep_Aether$setHasBeenHurt(boolean bool);
}
