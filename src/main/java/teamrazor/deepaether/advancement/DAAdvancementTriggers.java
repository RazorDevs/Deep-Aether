package teamrazor.deepaether.advancement;

import net.minecraft.advancements.CriteriaTriggers;
import net.minecraft.advancements.critereon.KilledTrigger;
import teamrazor.deepaether.DeepAether;

public class DAAdvancementTriggers {

    public static final KilledTrigger FLAWLESS = new KilledTrigger(DeepAether.getResource("flawless_boss_trigger"));
    public static void init() {
        CriteriaTriggers.register(PoisonTrigger.INSTANCE);
        CriteriaTriggers.register(FLAWLESS);
    }
}
