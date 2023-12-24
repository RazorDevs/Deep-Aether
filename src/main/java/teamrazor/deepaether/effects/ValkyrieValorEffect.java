package teamrazor.deepaether.effects;

import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;

public class ValkyrieValorEffect extends MobEffect {
    public ValkyrieValorEffect() {
        super(MobEffectCategory.BENEFICIAL, 25525551);
    }

    @Override
    public boolean isDurationEffectTick(int duration, int amplifier) {
        return true;
    }
}
