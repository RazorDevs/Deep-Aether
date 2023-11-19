package teamrazor.deepaether.mixin.flawless;

import com.aetherteam.aether.entity.projectile.crystal.AbstractCrystal;
import com.aetherteam.aether.entity.projectile.crystal.FireCrystal;
import com.aetherteam.aether.entity.projectile.crystal.IceCrystal;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.level.Level;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import teamrazor.deepaether.entity.IFlawlessBossDrop;

@Mixin(value = FireCrystal.class, remap = false)
public abstract class FireCrystalMixin extends AbstractCrystal implements IFlawlessBossDrop {

    protected FireCrystalMixin(EntityType<? extends AbstractCrystal> entityType, Level level) {
        super(entityType, level);
    }

    @Unique
    @Override
    public void deep_Aether$setHasBeenHurt(boolean bool) {
        if(this.getOwner() instanceof IFlawlessBossDrop boss) {
            boss.deep_Aether$setHasBeenHurt(bool);
        }
    }

}
