package teamrazor.deepaether.init;

import com.aetherteam.aether.effect.AetherEffects;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.item.alchemy.Potion;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import teamrazor.deepaether.DeepAether;

public class DAPotions {
    public static final DeferredRegister<Potion> POTIONS = DeferredRegister.create(ForgeRegistries.POTIONS, DeepAether.MODID);

    public static final RegistryObject<Potion> REMEDY_POTION = POTIONS.register("remedy_potion",
            () -> new Potion(new MobEffectInstance(AetherEffects.REMEDY.get(), 200, 0)));

}
