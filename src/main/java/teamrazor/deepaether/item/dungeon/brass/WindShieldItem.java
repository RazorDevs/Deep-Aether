package teamrazor.deepaether.item.dungeon.brass;

import com.aetherteam.aether.item.accessories.AccessoryItem;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import teamrazor.deepaether.DeepAether;
import teamrazor.deepaether.networking.DeepAetherPlayer;
import top.theillusivec4.curios.api.SlotContext;

public class WindShieldItem extends AccessoryItem {
    private static final ResourceLocation SHIELD_OF_REPULSION = new ResourceLocation(DeepAether.MODID, "textures/models/accessory/wind_shield/wind_shield_accessory.png");

    public WindShieldItem(Properties properties) {
        super(properties);
    }

    public ResourceLocation getWindShieldTexture() {
        return SHIELD_OF_REPULSION;
    }

    @Override
    public void curioTick(SlotContext slotContext, ItemStack stack) {
        if (slotContext.entity() instanceof Player player) {
            DeepAetherPlayer.get(player).ifPresent((aetherPlayer) -> {
                if (aetherPlayer.getWindShieldCooldown() > 0) {
                    aetherPlayer.setWindShieldCooldown(aetherPlayer.getWindShieldCooldown() - 1);
                }
            });
        }
    }
}
