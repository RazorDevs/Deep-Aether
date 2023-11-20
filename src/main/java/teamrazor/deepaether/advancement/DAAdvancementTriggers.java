package teamrazor.deepaether.advancement;

import net.minecraft.advancements.CriteriaTriggers;

public class DAAdvancementTriggers {
    public static void init() {
        CriteriaTriggers.register(PoisonTrigger.INSTANCE);
        CriteriaTriggers.register(FlawlessBossTrigger.INSTANCE);
    }
}
