package teamrazor.deepaether.client.renderer;

import com.gildedgames.aether.Aether;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.resources.ResourceLocation;
import teamrazor.deepaether.DeepAetherMod;

public class DeepAetherModelLayers {
    public static final ModelLayerLocation ROSEROOT_BOAT = register("roseroot_boat");
    public static final ModelLayerLocation ROSEROOT_CHEST_BOAT = register("roseroot_chest_boat");
    public static final ModelLayerLocation YAGROOT_BOAT = register("yagroot_boat");
    public static final ModelLayerLocation YAGROOT_CHEST_BOAT = register("yagroot_chest_boat");
    public static final ModelLayerLocation CRUDEROOT_BOAT = register("cruderoot_boat");
    public static final ModelLayerLocation CRUDEROOT_CHEST_BOAT = register("cruderoot_chest_boat");
    public static final ModelLayerLocation AERGLOW_FISH = register("aether_fish");
    private static ModelLayerLocation register(String name) {
        return register(name, "main");
    }

    private static ModelLayerLocation register(String name, String type) {
        return register(new ResourceLocation(DeepAetherMod.MODID, name), type);
    }

    private static ModelLayerLocation register(ResourceLocation identifier, String type) {
        return new ModelLayerLocation(identifier, type);
    }
}
