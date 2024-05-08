package teamrazor.deepaether.init;

import net.minecraft.world.effect.MobEffect;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import teamrazor.deepaether.DeepAether;
import teamrazor.deepaether.effects.MoaBonusJumpEffect;
import teamrazor.deepaether.effects.ValkyrieValorEffect;

public class DAMobEffects {
    public static final DeferredRegister<MobEffect> EFFECTS = DeferredRegister.create(ForgeRegistries.MOB_EFFECTS, DeepAether.MODID);
    public static final RegistryObject<MobEffect> MOA_BONUS_JUMPS = EFFECTS.register("moa_bonus_jumps", MoaBonusJumpEffect::new);
    public static final RegistryObject<MobEffect> VALKYRIE_VALOR = EFFECTS.register("valkyrie_valor", ValkyrieValorEffect::new);
}
