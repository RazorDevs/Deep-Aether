package teamrazor.deepaether.entity.boss;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.level.Level;
import net.minecraftforge.network.PlayMessages;
import software.bernie.geckolib.animatable.GeoEntity;
import software.bernie.geckolib.core.animatable.instance.AnimatableInstanceCache;
import software.bernie.geckolib.core.animation.AnimatableManager;
import software.bernie.geckolib.util.GeckoLibUtil;
import teamrazor.deepaether.init.DAEntities;

public class EOTSCloud extends Entity implements GeoEntity{

    private final AnimatableInstanceCache factory = GeckoLibUtil.createInstanceCache(this);

    public EOTSCloud(PlayMessages.SpawnEntity packet, Level world) {
        super(DAEntities.EOTS_CLOUD.get(), world);
    }

    public EOTSCloud(EntityType<EOTSCloud> eotsEntity, Level world) {
        super(eotsEntity, world);
    }


    @Override
    protected void defineSynchedData() {

    }

    @Override
    protected void readAdditionalSaveData(CompoundTag p_20052_) {

    }

    @Override
    protected void addAdditionalSaveData(CompoundTag p_20139_) {

    }

    @Override
    public boolean isInvulnerable() {
        return true;
    }

    @Override
    public void registerControllers(AnimatableManager.ControllerRegistrar controllerRegistrar) {

    }

    @Override
    public AnimatableInstanceCache getAnimatableInstanceCache() {
        return factory;
    }
}
