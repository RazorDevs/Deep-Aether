package teamrazor.deepaether.init;

import com.aetherteam.aether.effect.AetherEffects;
import net.minecraft.core.particles.ParticleType;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.item.alchemy.Potion;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import teamrazor.deepaether.DeepAetherMod;

public class DAPotions {
    public static final DeferredRegister<Potion> POTIONS = DeferredRegister.create(ForgeRegistries.POTIONS, DeepAetherMod.MODID);

    public static final RegistryObject<Potion> REMEDY_POTION = POTIONS.register("remedy_potion",
            () -> new Potion(new MobEffectInstance(AetherEffects.REMEDY.get(), 200, 0)));

}
