package teamrazor.deepaether.init;

import com.aetherteam.aether.effect.AetherEffects;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.item.alchemy.Potion;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;
import teamrazor.deepaether.DeepAether;

public class DAPotions {
    public static final DeferredRegister<Potion> POTIONS = DeferredRegister.create(BuiltInRegistries.POTION, DeepAether.MODID);

    public static final DeferredHolder<Potion, Potion> REMEDY_POTION = POTIONS.register("remedy_potion",
            () -> new Potion(new MobEffectInstance(AetherEffects.REMEDY.get(), 200, 0)));

}
