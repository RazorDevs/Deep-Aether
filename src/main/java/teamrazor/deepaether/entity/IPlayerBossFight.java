package teamrazor.deepaether.entity;

import net.minecraft.world.entity.Entity;
import org.spongepowered.asm.mixin.Unique;

/**
 * Used to get and set a saved boss inside the player class through mixin. Used in the flawless boss check
 */
public interface IPlayerBossFight {

    @Unique
    Entity deep_Aether$getBoss();

    @Unique
    void deep_Aether$setBoss(Entity boss);
}
