package io.github.razordevs.deep_aether.item.misc;

import com.aetherteam.aether.effect.AetherEffects;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.food.FoodProperties;

public class DAFoods {
    public static final FoodProperties GOLDEN_BERRIES = (new FoodProperties.Builder()).nutrition(3).saturationMod(0.1F).fast().build();
    public static final FoodProperties ANTIDOTE = (new FoodProperties.Builder()).nutrition(1).saturationMod(0F).effect(new MobEffectInstance(AetherEffects.REMEDY.get(), 300, 0), 1.0F).fast().build();
    public static final FoodProperties ENCHANTED_ANTIDOTE = (new FoodProperties.Builder()).nutrition(1).saturationMod(0F).effect(new MobEffectInstance(AetherEffects.REMEDY.get(), 600, 0), 1.0F).fast().build();

}
