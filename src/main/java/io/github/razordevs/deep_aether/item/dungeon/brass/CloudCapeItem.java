package io.github.razordevs.deep_aether.item.dungeon.brass;

import com.aetherteam.aether.item.accessories.cape.CapeItem;
import net.minecraft.client.Minecraft;
import net.minecraft.network.protocol.game.ClientboundSetEntityMotionPacket;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.item.ItemStack;
import top.theillusivec4.curios.api.SlotContext;

public class CloudCapeItem extends CapeItem {
    public CloudCapeItem(ResourceLocation capeLocation, Properties properties) {
        super(capeLocation, properties);
    }

    boolean hasDoubleJumped = false;
    boolean canJump = false;

    @Override
    public void curioTick(SlotContext slotContext, ItemStack stack) {
        if(slotContext.entity().level().isClientSide()) {
            if (slotContext.entity().onGround()) {
                hasDoubleJumped = false;
                canJump = false;
            }
            else if(!canJump) {
                canJump = !Minecraft.getInstance().options.keyJump.isDown();
            }
            else if (!hasDoubleJumped && Minecraft.getInstance().options.keyJump.isDown()) {
                hasDoubleJumped = true;
                slotContext.entity().setDeltaMovement(slotContext.entity().getDeltaMovement().x(), 0.42, slotContext.entity().getDeltaMovement().z());
                slotContext.entity().resetFallDistance();
                if (slotContext.entity() instanceof ServerPlayer serverPlayer) {
                    serverPlayer.connection.send(new ClientboundSetEntityMotionPacket(serverPlayer));
                }
            }
        }
    }
}
