package teamrazor.deepaether.advancement;

import com.google.gson.JsonObject;
import net.minecraft.advancements.critereon.*;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.item.ItemStack;
import teamrazor.deepaether.DeepAetherMod;

public class FlawlessBossTrigger extends SimpleCriterionTrigger<FlawlessBossTrigger.Instance> {
    private static final ResourceLocation ID = new ResourceLocation(DeepAetherMod.MODID, "flawless_boss_trigger");
    public static final FlawlessBossTrigger INSTANCE = new FlawlessBossTrigger();

    @Override
    public ResourceLocation getId() {
        return ID;
    }

    @Override
    public FlawlessBossTrigger.Instance createInstance(JsonObject json, ContextAwarePredicate predicate, DeserializationContext context) {
        EntityTypePredicate entityPredicate = EntityTypePredicate.fromJson(json.get("entity"));
        return new FlawlessBossTrigger.Instance(predicate, entityPredicate);
    }

    public void trigger(ServerPlayer player, EntityType<?> entity) {
        this.trigger(player, (instance) -> instance.test(entity));
    }

    public static class Instance extends AbstractCriterionTriggerInstance {
        private final EntityTypePredicate entity;

        public Instance(ContextAwarePredicate predicate, EntityTypePredicate entity) {
            super(FlawlessBossTrigger.ID, predicate);
            this.entity = entity;
        }

        public static FlawlessBossTrigger.Instance forEntity(EntityTypePredicate entity) {
            return new FlawlessBossTrigger.Instance(ContextAwarePredicate.ANY, entity);
        }

        public boolean test(EntityType<?> type) {
            return this.entity.matches(type);
        }

        @Override
        public JsonObject serializeToJson(SerializationContext context) {
            JsonObject jsonObject = super.serializeToJson(context);
            jsonObject.add("entity", this.entity.serializeToJson());
            return jsonObject;
        }
    }
}
