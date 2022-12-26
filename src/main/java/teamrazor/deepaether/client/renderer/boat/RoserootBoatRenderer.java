package teamrazor.deepaether.client.renderer.boat;

import com.gildedgames.aether.Aether;
import teamrazor.deepaether.client.renderer.DeepAetherModelLayers;
import com.mojang.datafixers.util.Pair;
import net.minecraft.client.model.BoatModel;
import net.minecraft.client.model.ChestBoatModel;
import net.minecraft.client.model.ListModel;
import net.minecraft.client.renderer.entity.BoatRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.vehicle.Boat;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import teamrazor.deepaether.DeepAetherMod;

import javax.annotation.Nonnull;

@OnlyIn(Dist.CLIENT)
public class RoserootBoatRenderer extends BoatRenderer {
    private static final ResourceLocation ROSEROOT_BOAT = new ResourceLocation(DeepAetherMod.MODID, "textures/entity/miscellaneous/boat/roseroot.png");
    private static final ResourceLocation ROSEROOT_CHEST_BOAT = new ResourceLocation(DeepAetherMod.MODID, "textures/entity/miscellaneous/chest_boat/roseroot.png");
    private final Pair<ResourceLocation, ListModel<Boat>> roserootBoatResource;

    public RoserootBoatRenderer(EntityRendererProvider.Context context, boolean chest) {
        super(context, chest);
        this.roserootBoatResource = Pair.of(chest ? ROSEROOT_CHEST_BOAT : ROSEROOT_BOAT, chest ? new ChestBoatModel(context.bakeLayer(DeepAetherModelLayers.ROSEROOT_CHEST_BOAT)) : new BoatModel(context.bakeLayer(DeepAetherModelLayers.ROSEROOT_BOAT)));
    }

    @Nonnull
    @Override
    public Pair<ResourceLocation, ListModel<Boat>> getModelWithLocation(@Nonnull Boat boat) {
        return this.roserootBoatResource;
    }
}
