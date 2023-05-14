package teamrazor.deepaether.client.renderer;

import com.aetherteam.aether.Aether;
import com.aetherteam.aether.client.renderer.AetherModelLayers;
import com.aetherteam.aether.client.renderer.entity.model.ZephyrModel;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.renderer.GeoEntityRenderer;
import teamrazor.deepaether.client.model.ZephytaModel;
import teamrazor.deepaether.entity.ZephytaGolem;

public class ZephytaGolemRenderer extends GeoEntityRenderer<ZephytaGolem> {
    private static final ResourceLocation ZEPHYR_TEXTURE = new ResourceLocation(Aether.MODID, "textures/entity/mobs/zephyr/zephyr.png");

    private final ZephyrModel defaultModel;
    public ZephytaGolemRenderer(EntityRendererProvider.Context renderManager) {
        super(renderManager, new ZephytaModel());
        this.defaultModel = new ZephyrModel(renderManager.bakeLayer(AetherModelLayers.ZEPHYR));
    }
}
