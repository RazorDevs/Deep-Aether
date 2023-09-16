package teamrazor.deepaether.client.renderer;

import com.google.common.collect.ImmutableMap;
import com.mojang.datafixers.util.Pair;
import net.minecraft.client.model.BoatModel;
import net.minecraft.client.model.ListModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.renderer.entity.BoatRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.vehicle.Boat;
import teamrazor.deepaether.DeepAetherMod;
import teamrazor.deepaether.entity.DABoatEntity;

import java.util.Map;
import java.util.stream.Stream;

public class DABoatRenderer extends BoatRenderer {
    private final Map<DABoatEntity.Type, Pair<ResourceLocation, ListModel<Boat>>> boatResources;

    public DABoatRenderer(EntityRendererProvider.Context renderer, boolean hasChest) {
        super(renderer, hasChest);
        this.shadowRadius = 0.8f;
        this.boatResources = Stream.of(DABoatEntity.Type.values()).collect(ImmutableMap.toImmutableMap(type -> type, type ->
                Pair.of(type.getTexture(hasChest), this.createBoatModel(renderer, type, hasChest))));
    }

    private ListModel<Boat> createBoatModel(EntityRendererProvider.Context renderer, DABoatEntity.Type type, boolean hasChest) {
        ModelLayerLocation modelLayerLocation = hasChest ?
                new ModelLayerLocation(new ResourceLocation(DeepAetherMod.MODID, type.getChestModelLocation()), "main")
                : new ModelLayerLocation(new ResourceLocation(DeepAetherMod.MODID, type.getModelLocation()), "main");
        ModelPart modelPart = renderer.bakeLayer(modelLayerLocation);
        return hasChest ? new BoatModel(modelPart, true) : new BoatModel(modelPart, false);
    }

    @Override
    public Pair<ResourceLocation, BoatModel> getModelWithLocation(Boat boat) {
        return (Pair<ResourceLocation, BoatModel>) this.boatResources;
    }
}