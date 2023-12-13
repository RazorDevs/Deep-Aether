package teamrazor.deepaether.entity;

import net.minecraft.world.entity.Entity;
import org.spongepowered.asm.mixin.Unique;


public interface IPlayerBossFight {

    @Unique
    Entity deep_Aether$getBoss();

    @Unique
    void deep_Aether$setBoss(Entity boss);
}
