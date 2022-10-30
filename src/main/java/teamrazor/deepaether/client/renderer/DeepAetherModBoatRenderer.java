package teamrazor.deepaether.client.renderer;


import com.google.common.collect.Maps;
import com.mojang.datafixers.util.Pair;
import net.minecraft.client.model.BoatModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.renderer.entity.BoatRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.vehicle.Boat;
import teamrazor.deepaether.DeepAetherMod;
import teamrazor.deepaether.entity.DeepAetherModBoat;

import java.util.Map;


public class DeepAetherModBoatRenderer extends BoatRenderer {
    private final Map<DeepAetherModBoat.Type, Pair<ResourceLocation, BoatModel>> boatResources = Maps.newHashMap();

    public DeepAetherModBoatRenderer(EntityRendererProvider.Context context, boolean hasChest) {
        super(context, hasChest);
        for(DeepAetherModBoat.Type type : DeepAetherModBoat.Type.values()) {
            boatResources.put(type, Pair.of(type.getTexture(hasChest), new BoatModel(context.bakeLayer(new ModelLayerLocation(new ResourceLocation(DeepAetherMod.MODID, hasChest ? type.getChestModelLocation() : type.getModelLocation()), "main")), hasChest)));
        }
    }

    @Override
    public Pair<ResourceLocation, BoatModel> getModelWithLocation(Boat boat) {
        return boatResources.get(((DeepAetherModBoat)boat).getDeepAetherModBoatType());
    }

    private static String getTextureLocation(DeepAetherModBoat.Type p_234566_, boolean p_234567_) {
        return p_234567_ ? "textures/entity/chest_boat/" + p_234566_.getName() + ".png" : "textures/entity/boat/" + p_234566_.getName() + ".png";
    }
}