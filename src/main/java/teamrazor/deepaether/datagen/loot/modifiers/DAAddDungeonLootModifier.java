package teamrazor.deepaether.datagen.loot.modifiers;

import com.google.common.base.Suppliers;
import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import net.minecraft.util.random.WeightedEntry;
import net.minecraft.util.random.WeightedRandom;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.storage.loot.LootContext;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;
import net.minecraftforge.common.loot.IGlobalLootModifier;
import net.minecraftforge.common.loot.LootModifier;
import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.function.Supplier;
//Code from: https://github.com/Mrthomas20121-Mods/Aether-Gravitation/blob/1.19.4/src/main/java/mrthomas20121/gravitation/data/loot/AetherDungeonLootModifiers.java
public class DAAddDungeonLootModifier extends LootModifier {

    public static final Supplier<Codec<DAAddDungeonLootModifier>> CODEC = Suppliers.memoize(() -> RecordCodecBuilder.create(inst -> codecStart(inst)
            .and(WeightedEntry.Wrapper.codec(ItemStack.CODEC).listOf().fieldOf("items").forGetter(m -> m.items))
            .and(Codec.INT.fieldOf("totalWeight").forGetter(m -> m.totalWeight))
            .and(Codec.FLOAT.fieldOf("chanceToSpawn").forGetter(m -> m.chance))
            .apply(inst, DAAddDungeonLootModifier::new)));

    public final List<WeightedEntry.Wrapper<ItemStack>> items;
    public final int totalWeight;
    public final float chance;

    public DAAddDungeonLootModifier(final LootItemCondition[] conditionsIn, List<WeightedEntry.Wrapper<ItemStack>> items, int totalWeight, float chance) {
        super(conditionsIn);
        this.items = items.stream().map(wrapper -> WeightedEntry.wrap(wrapper.getData().copy(), wrapper.getWeight().asInt())).toList();
        this.totalWeight = totalWeight;
        this.chance = chance;
    }

    @Override
    protected @NotNull ObjectArrayList<ItemStack> doApply(ObjectArrayList<ItemStack> generatedLoot, LootContext context) {

        ObjectArrayList<ItemStack> list = new ObjectArrayList<>();

        // size of the loots
        int size = generatedLoot.size();

        // is the loot full?
        boolean isFull = size == 27;

        // size diff for when it's not full
        int sizeDiff = 27-size;

        for(ItemStack stack: generatedLoot) {
            // chest is full => x chance to replace the item by one of my item
            // chest is not full => we don't replace any loot
            if(isFull) {
                if(context.getRandom().nextFloat() > chance) {
                    WeightedRandom.getRandomItem(context.getRandom(), this.items, totalWeight).ifPresent(e -> list.add(e.getData()));
                }
            }
            else {
                list.add(stack);
            }
        }

        // if the loot is not full, for each slot remaining, have x chance to add one of our item in the empty slots
        if(!isFull) {
            for(int i = 0; i<= sizeDiff; i++) {
                if(context.getRandom().nextFloat() > chance) {
                    WeightedRandom.getRandomItem(context.getRandom(), this.items, totalWeight).ifPresent(e -> list.add(e.getData()));
                }
            }
        }

        return list;
    }

    @Override
    public Codec<? extends IGlobalLootModifier> codec() {
        return CODEC.get();
    }
}