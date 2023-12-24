package teamrazor.deepaether.advancement;

import com.google.gson.JsonObject;
import net.minecraft.advancements.critereon.*;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.item.ItemStack;
import teamrazor.deepaether.DeepAetherMod;

public class PoisonTrigger extends SimpleCriterionTrigger<PoisonTrigger.Instance> {
    private static final ResourceLocation ID = new ResourceLocation(DeepAetherMod.MODID, "poison_trigger");
    public static final PoisonTrigger INSTANCE = new PoisonTrigger();

    @Override
    public ResourceLocation getId() {
        return ID;
    }

    @Override
    protected Instance createInstance(JsonObject json, EntityPredicate.Composite predicate, DeserializationContext context) {
        ItemPredicate itemPredicate = ItemPredicate.fromJson(json.get("item"));
        return new PoisonTrigger.Instance(predicate, itemPredicate);
    }

    public void trigger(ServerPlayer player, ItemStack stack) {
        this.trigger(player, (instance) -> instance.test(stack));
    }

    public static class Instance extends AbstractCriterionTriggerInstance {
        private final ItemPredicate item;

        public Instance(EntityPredicate.Composite predicate, ItemPredicate item) {
            super(PoisonTrigger.ID, predicate);
            this.item = item;
        }

        public static PoisonTrigger.Instance forItem(ItemPredicate item) {
            return new PoisonTrigger.Instance(EntityPredicate.Composite.ANY, item);
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
