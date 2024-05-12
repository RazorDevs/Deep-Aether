package teamrazor.deepaether.advancement;

import com.google.gson.JsonObject;
import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.advancements.Criterion;
import net.minecraft.advancements.critereon.*;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.util.ExtraCodecs;
import net.minecraft.world.item.ItemStack;
import teamrazor.deepaether.DeepAether;

import java.util.Optional;

public class PoisonTrigger extends SimpleCriterionTrigger<PoisonTrigger.Instance> {
    public static final PoisonTrigger INSTANCE = new PoisonTrigger();

    @Override
    public Codec<Instance> codec() {
        return Instance.CODEC;
    }

    public void trigger(ServerPlayer player, ItemStack stack) {
        this.trigger(player, (instance) -> instance.test(stack));
    }
    public record Instance(Optional<ContextAwarePredicate> player, Optional<ItemPredicate> item) implements SimpleInstance {
        public static final Codec<Instance> CODEC = RecordCodecBuilder.create(instance -> instance.group(
                        ExtraCodecs.strictOptionalField(EntityPredicate.ADVANCEMENT_CODEC, "player").forGetter(PoisonTrigger.Instance::player),
                        ExtraCodecs.strictOptionalField(ItemPredicate.CODEC, "item").forGetter(PoisonTrigger.Instance::item))
                .apply(instance, PoisonTrigger.Instance::new));

        public static Criterion<Instance> forItem(ItemPredicate item) {
            return DAAdvancementTriggers.POISON_TRIGGER.get().createCriterion(new PoisonTrigger.Instance(Optional.empty(), Optional.of(item)));
        }

        public boolean test(ItemStack stack) {
            return this.item.isEmpty() || this.item.get().matches(stack);
        }
    }
}
