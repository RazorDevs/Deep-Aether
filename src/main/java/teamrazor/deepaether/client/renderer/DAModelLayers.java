package teamrazor.deepaether.client.renderer;

import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.resources.ResourceLocation;
import teamrazor.deepaether.DeepAetherMod;

public class DAModelLayers {


    public static final ModelLayerLocation AERGLOW_FISH = register("aerglow_fish");
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
