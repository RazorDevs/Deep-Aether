package teamrazor.deepaether.init;

import com.gildedgames.aether.Aether;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.state.properties.WoodType;
import teamrazor.deepaether.DeepAetherMod;

public class DeepAetherModWoodTypes {
    public static final WoodType ROSEROOT = WoodType.create(new ResourceLocation(DeepAetherMod.MODID, "roseroot").toString());
    public static final WoodType CRUDEROOT = WoodType.create(new ResourceLocation(DeepAetherMod.MODID, "yagroot").toString());
    public static final WoodType YAGROOT = WoodType.create(new ResourceLocation(DeepAetherMod.MODID, "cruderoot").toString());
}
