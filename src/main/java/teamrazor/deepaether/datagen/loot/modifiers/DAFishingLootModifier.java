package teamrazor.deepaether.datagen.loot.modifiers;

import com.aetherteam.aether.data.resources.registries.AetherDimensions;
import com.google.common.base.Suppliers;
import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import net.minecraft.util.random.WeightedEntry;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.storage.loot.LootContext;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;
import net.minecraftforge.common.loot.IGlobalLootModifier;
import net.minecraftforge.common.loot.LootModifier;
import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.function.Supplier;

public class DAFishingLootModifier extends LootModifier {

    public static final Supplier<Codec<DAFishingLootModifier>> CODEC = Suppliers.memoize(() -> RecordCodecBuilder.create(inst -> codecStart(inst)
            .and(WeightedEntry.Wrapper.codec(ItemStack.CODEC).listOf().fieldOf("items").forGetter(m -> m.items))
            .and(Codec.INT.fieldOf("totalWeight").forGetter(m -> m.totalWeight))
            .and(Codec.FLOAT.fieldOf("chanceToSpawn").forGetter(m -> m.chance))
            .apply(inst, DAFishingLootModifier::new)));

    public final List<WeightedEntry.Wrapper<ItemStack>> items;
    public final int totalWeight;
    public final float chance;

    public DAFishingLootModifier(final LootItemCondition[] conditionsIn, List<WeightedEntry.Wrapper<ItemStack>> items, int totalWeight, float chance) {
        super(conditionsIn);
        this.items = items.stream().map(wrapper -> WeightedEntry.wrap(wrapper.getData().copy(), wrapper.getWeight().asInt())).toList();
        this.totalWeight = totalWeight;
        this.chance = chance;
    }

    @Override
    protected @NotNull ObjectArrayList<ItemStack> doApply(ObjectArrayList<ItemStack> generatedLoot, LootContext context) {
        if(context.getLevel().dimension() == AetherDimensions.AETHER_LEVEL)
            if(context.getRandom().nextFloat() > chance) {

                int itemNum = context.getRandom().nextInt(totalWeight);
                ItemStack modifiedStack = null;

                for (WeightedEntry.Wrapper<ItemStack> stack : items) {
                    if (stack.getWeight().asInt() >= itemNum) {
                        modifiedStack = stack.getData();
                        break;
                    }
                }

                if(modifiedStack != null)
                    generatedLoot.set(0, modifiedStack);

            }
        return generatedLoot;
    }

    @Override
    public Codec<? extends IGlobalLootModifier> codec() {
        return CODEC.get();
    }
}