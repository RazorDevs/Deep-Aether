package teamrazor.deepaether.advancement;

import net.minecraft.advancements.CriteriaTriggers;
import net.minecraft.advancements.CriterionTrigger;
import net.minecraft.advancements.critereon.KilledTrigger;
import net.minecraft.resources.ResourceLocation;
import teamrazor.deepaether.DeepAetherMod;

public class DAAdvancementTriggers {

    public static final KilledTrigger FLAWLESS = new KilledTrigger(new ResourceLocation(DeepAetherMod.MODID, "flawless_boss_trigger"));
    public static void init() {
        CriteriaTriggers.register(PoisonTrigger.INSTANCE);
        CriteriaTriggers.register(FLAWLESS);
    }
}
