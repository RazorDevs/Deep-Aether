package teamrazor.deepaether.datagen.loot.modifiers;

import com.google.common.base.Suppliers;
import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import net.minecraft.util.random.WeightedEntry;
import net.minecraft.util.random.WeightedRandom;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.storage.loot.LootContext;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;
import net.minecraftforge.common.loot.IGlobalLootModifier;
import net.minecraftforge.common.loot.LootModifier;
import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.function.Supplier;

//Guarantees the item to always be placed in the chest.
public class DAAddForceDungeonLootModifier extends LootModifier {

    public static final Supplier<Codec<DAAddForceDungeonLootModifier>> CODEC = Suppliers.memoize(() -> RecordCodecBuilder.create(inst -> codecStart(inst)
            .and(ItemStack.CODEC.fieldOf("items").forGetter(m -> m.items))
            .and(Codec.FLOAT.fieldOf("chanceToSpawn").forGetter(m -> m.chance))
            .apply(inst, DAAddForceDungeonLootModifier::new)));

    public final ItemStack items;
    public final float chance;

    public DAAddForceDungeonLootModifier(final LootItemCondition[] conditionsIn, ItemStack items, float chance) {
        super(conditionsIn);
        this.items = items;
        this.chance = chance;
    }

    @Override
    protected @NotNull ObjectArrayList<ItemStack> doApply(ObjectArrayList<ItemStack> generatedLoot, LootContext context) {

        ObjectArrayList<ItemStack> list = new ObjectArrayList<>();

        // size of the loots
        int size = generatedLoot.size();

        // is the loot full?
        boolean isFull = size == 27;

        for(ItemStack stack: generatedLoot) {
            // chest is full => x chance to replace the item by one of my item
            // chest is not full => we don't replace any loot
            if(isFull) {
                list.add(items);
            }
            else {
                list.add(stack);
            }
        }

        // if the loot is not full, for each slot remaining, have x chance to add one of our item in the empty slots
        if(!isFull) {
            list.add(items);
        }

        return list;
    }

    @Override
    public Codec<? extends IGlobalLootModifier> codec() {
        return CODEC.get();
    }
}