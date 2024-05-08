package teamrazor.deepaether.advancement;

import com.google.gson.JsonObject;
import net.minecraft.advancements.critereon.*;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.item.ItemStack;
import teamrazor.deepaether.DeepAether;

public class PoisonTrigger extends SimpleCriterionTrigger<PoisonTrigger.Instance> {
    private static final ResourceLocation ID = new ResourceLocation(DeepAether.MODID, "poison_trigger");
    public static final PoisonTrigger INSTANCE = new PoisonTrigger();

    @Override
    public ResourceLocation getId() {
        return ID;
    }

    @Override
    public PoisonTrigger.Instance createInstance(JsonObject json, ContextAwarePredicate predicate, DeserializationContext context) {
        ItemPredicate itemPredicate = ItemPredicate.fromJson(json.get("item"));
        return new PoisonTrigger.Instance(predicate, itemPredicate);
    }

    public void trigger(ServerPlayer player, ItemStack stack) {
        this.trigger(player, (instance) -> instance.test(stack));
    }

    public static class Instance extends AbstractCriterionTriggerInstance {
        private final ItemPredicate item;

        public Instance(ContextAwarePredicate predicate, ItemPredicate item) {
            super(PoisonTrigger.ID, predicate);
            this.item = item;
        }

        public boolean test(ItemStack stack) {
            return this.item.matches(stack);
        }

        @Override
        public JsonObject serializeToJson(SerializationContext context) {
            JsonObject jsonObject = super.serializeToJson(context);
            jsonObject.add("item", this.item.serializeToJson());
            return jsonObject;
        }
    }
}
