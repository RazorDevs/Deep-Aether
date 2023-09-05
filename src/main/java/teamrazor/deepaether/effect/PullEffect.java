package teamrazor.deepaether.effect;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.MoverType;
import net.minecraft.world.phys.Vec3;

public class PullEffect extends MobEffect {
    public PullEffect(MobEffectCategory mobEffectCategory, int color) {
        super(mobEffectCategory, color);

    }


    public void setPos(Entity entity) {
        this.entity = entity;
    }


    public Entity entity;
    @Override
    public void applyEffectTick(LivingEntity player, int pAmplifier) {



        if(player.isOnGround() && entity != null) {
            Vec3 pos = entity.position();
            pos = pos.subtract( player.position());
            double divide = Math.abs(pos.x) + Math.abs(pos.z);
            Vec3 vec = new Vec3(pos.x/divide, 0, pos.z/divide);
            vec = vec.multiply(0.1, 0, 0.1);
            player.setDeltaMovement(player.getDeltaMovement().add(vec));
            super.applyEffectTick(player, pAmplifier);
        }
    }

    @Override
    public boolean isDurationEffectTick(int pDuration, int pAmplifier) {
        return true;
    }
}
