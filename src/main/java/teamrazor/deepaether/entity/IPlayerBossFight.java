package teamrazor.deepaether.entity;

import net.minecraft.world.entity.Entity;
import org.spongepowered.asm.mixin.Unique;

import javax.annotation.Nullable;

/**
 * Used to get and set a saved boss inside the player class through mixin. Used in the flawless boss check
 */
public interface IPlayerBossFight {

    @Unique
    boolean deep_Aether$getHasBeenHurt();

    @Unique
    void deep_Aether$setHasBeenHurt(boolean bool);

    @Nullable
    Entity deep_Aether$getBoss();

    void deep_Aether$setBoss(@Nullable Entity entity);
}
