package teamrazor.deepaether.datagen.loot.modifiers;

import com.google.common.base.Suppliers;
import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.storage.loot.LootContext;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;
import net.neoforged.neoforge.common.loot.IGlobalLootModifier;
import net.neoforged.neoforge.common.loot.LootModifier;
import org.jetbrains.annotations.NotNull;

import java.util.function.Supplier;

//Guarantees the item to always be placed in the chest.
public class DAAddFlatDungeonLootModifier extends LootModifier {

    public static final Supplier<Codec<DAAddFlatDungeonLootModifier>> CODEC = Suppliers.memoize(() -> RecordCodecBuilder.create(inst -> codecStart(inst)
            .and(ItemStack.CODEC.fieldOf("items").forGetter(m -> m.items))
            .and(Codec.FLOAT.fieldOf("chanceToSpawn").forGetter(m -> m.chance))
            .apply(inst, DAAddFlatDungeonLootModifier::new)));

    public final ItemStack items;
    public final float chance;

    public DAAddFlatDungeonLootModifier(final LootItemCondition[] conditionsIn, ItemStack items, float chance) {
        super(conditionsIn);
        this.items = items;
        this.chance = chance;
    }

    @Override
    protected @NotNull ObjectArrayList<ItemStack> doApply(ObjectArrayList<ItemStack> generatedLoot, LootContext context) {

        // size of the loots
        int size = generatedLoot.size();

        // is the loot full?
        boolean isFull = size == 27;

        if (isFull) {
            generatedLoot.remove(context.getRandom().nextInt(27));
            generatedLoot.add(items);
        } else generatedLoot.add(items);

        return generatedLoot;
    }

    @Override
    public Codec<? extends IGlobalLootModifier> codec() {
        return CODEC.get();
    }
}