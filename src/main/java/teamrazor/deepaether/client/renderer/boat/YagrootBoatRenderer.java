package teamrazor.deepaether.client.renderer.boat;

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
import teamrazor.deepaether.client.renderer.DeepAetherModelLayers;

import javax.annotation.Nonnull;

@OnlyIn(Dist.CLIENT)
public class YagrootBoatRenderer extends BoatRenderer {
    private static final ResourceLocation YAGROOT_BOAT = new ResourceLocation(DeepAetherMod.MODID, "textures/entity/boat/yagroot.png");
    private static final ResourceLocation YAGROOT_CHEST_BOAT = new ResourceLocation(DeepAetherMod.MODID, "textures/entity/chest_boat/yagroot.png");
    private final Pair<ResourceLocation, ListModel<Boat>> yagrootBoatResource;

    public YagrootBoatRenderer(EntityRendererProvider.Context context, boolean chest) {
        super(context, chest);
        this.yagrootBoatResource = Pair.of(chest ? YAGROOT_CHEST_BOAT : YAGROOT_BOAT, chest ? new ChestBoatModel(context.bakeLayer(DeepAetherModelLayers.YAGROOT_CHEST_BOAT)) : new BoatModel(context.bakeLayer(DeepAetherModelLayers.YAGROOT_BOAT)));
    }

    @Nonnull
    @Override
    public Pair<ResourceLocation, ListModel<Boat>> getModelWithLocation(@Nonnull Boat boat) {
        return this.yagrootBoatResource;
    }
}
