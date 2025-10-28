package io.github.razordevs.deep_aether.datagen.loot.modifiers;

import com.aetherteam.aether.data.resources.registries.AetherDimensions;
import com.google.common.base.Suppliers;
import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import io.github.razordevs.deep_aether.DeepAether;
import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import net.minecraft.util.random.WeightedEntry;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.storage.loot.LootContext;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;
import net.neoforged.neoforge.common.loot.IGlobalLootModifier;
import net.neoforged.neoforge.common.loot.LootModifier;

import java.util.List;
import java.util.function.Supplier;

public class DAHalloweenLootModifier extends LootModifier {

    public static final Supplier<MapCodec<DAHalloweenLootModifier>> CODEC = Suppliers.memoize(() -> RecordCodecBuilder.mapCodec(inst -> codecStart(inst)
            .and(WeightedEntry.Wrapper.codec(ItemStack.CODEC).listOf().fieldOf("items").forGetter(m -> m.items))
            .and(Codec.INT.fieldOf("totalWeight").forGetter(m -> m.totalWeight))
            .and(Codec.FLOAT.fieldOf("chanceToSpawn").forGetter(m -> m.chance))
            .apply(inst, DAHalloweenLootModifier::new)));


    public final List<WeightedEntry.Wrapper<ItemStack>> items;
    public final int totalWeight;
    public final float chance;

    protected DAHalloweenLootModifier(final LootItemCondition[] conditionsIn, List<WeightedEntry.Wrapper<ItemStack>> items, int totalWeight, float chance) {
        super(conditionsIn);
        this.items = items.stream().map(wrapper -> WeightedEntry.wrap(wrapper.data().copy(), wrapper.getWeight().asInt())).toList();
        this.totalWeight = totalWeight;
        this.chance = chance;
    }

    @Override
    protected ObjectArrayList<ItemStack> doApply(ObjectArrayList<ItemStack> generatedLoot, LootContext context) {
        if(context.getLevel().dimension() == AetherDimensions.AETHER_LEVEL && DeepAether.IS_HALLOWEEN){
            if(context.getRandom().nextFloat() > chance){
                for (WeightedEntry.Wrapper<ItemStack> stack : items) {
                    stack.data().setCount(context.getRandom().nextInt(5));
                    generatedLoot.add(stack.data());
                }
            }

        }
        return generatedLoot;
    }

    @Override
    public MapCodec<? extends IGlobalLootModifier> codec() {
        return CODEC.get();
    }
}
