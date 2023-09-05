package teamrazor.deepaether.init;

import net.minecraft.core.BlockPos;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import teamrazor.deepaether.DeepAetherMod;
import teamrazor.deepaether.effect.BlessingEffect;
import teamrazor.deepaether.effect.PullEffect;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public class DAEffects {
    public static final DeferredRegister<MobEffect> MOB_EFFECTS = DeferredRegister.create(ForgeRegistries.MOB_EFFECTS, DeepAetherMod.MODID);

    public static final RegistryObject<MobEffect> BLESSING = MOB_EFFECTS.register("blessing",
            () -> new BlessingEffect(MobEffectCategory.BENEFICIAL, 3124687));

    public static final RegistryObject<PullEffect> PULL = MOB_EFFECTS.register("pull",
            () -> new PullEffect(MobEffectCategory.HARMFUL, 3124687));
}
