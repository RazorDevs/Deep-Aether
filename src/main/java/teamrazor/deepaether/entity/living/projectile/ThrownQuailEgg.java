package teamrazor.deepaether.entity.living.projectile;

import net.minecraft.Util;
import net.minecraft.core.particles.ItemParticleOption;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.ThrowableItemProjectile;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.HitResult;
import teamrazor.deepaether.entity.living.quail.Quail;
import teamrazor.deepaether.entity.living.quail.QuailVariants;
import teamrazor.deepaether.init.DAEntities;
import teamrazor.deepaether.init.DAItems;

public class ThrownQuailEgg extends ThrowableItemProjectile {

    public ThrownQuailEgg(EntityType<ThrownQuailEgg> entityType, Level level) {
        super(entityType, level);
    }

    public ThrownQuailEgg(Level p_37481_, LivingEntity p_37482_) {
        super(DAEntities.QUAIL_EGG.get(), p_37482_, p_37481_);
    }

    public void handleEntityEvent(byte b) {
        if (b == 3) {
            double d0 = 0.08D;

            for(int i = 0; i < 8; ++i) {
                this.level().addParticle(new ItemParticleOption(ParticleTypes.ITEM, this.getItem()), this.getX(), this.getY(), this.getZ(), ((double)this.random.nextFloat() - 0.5D) * 0.08D, ((double)this.random.nextFloat() - 0.5D) * 0.08D, ((double)this.random.nextFloat() - 0.5D) * 0.08D);
            }
        }

    }

    protected void onHitEntity(EntityHitResult hitResult) {
        super.onHitEntity(hitResult);
        hitResult.getEntity().hurt(this.damageSources().thrown(this, this.getOwner()), 0.0F);
    }

    protected void onHit(HitResult hitResult) {
        super.onHit(hitResult);
        if (!this.level().isClientSide) {
            if (this.random.nextInt(8) == 0) {
                int i = 1;
                if (this.random.nextInt(32) == 0) {
                    i = 4;
                }

                for(int j = 0; j < i; ++j) {
                    Quail quail = DAEntities.QUAIL.get().create(this.level());
                    QuailVariants variant = Util.getRandom(QuailVariants.values(), this.random);
                    quail.setVariant(variant);
                    if (quail != null) {
                        quail.setAge(-24000);
                        quail.moveTo(this.getX(), this.getY(), this.getZ(), this.getYRot(), 0.0F);
                        this.level().addFreshEntity(quail);
                    }
                }
            }

            this.level().broadcastEntityEvent(this, (byte)3);
            this.discard();
        }

    }

    @Override
    protected Item getDefaultItem() {
        return DAItems.QUAIL_EGG.get();
    }
}
