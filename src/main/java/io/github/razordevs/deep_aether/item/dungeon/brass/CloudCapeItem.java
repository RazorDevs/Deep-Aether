package io.github.razordevs.deep_aether.item.dungeon.brass;

import com.aetherteam.aether.item.accessories.cape.CapeItem;
import io.wispforest.accessories.api.slot.SlotReference;
import net.minecraft.client.Minecraft;
import net.minecraft.network.protocol.game.ClientboundSetEntityMotionPacket;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.item.ItemStack;

public class CloudCapeItem extends CapeItem {
    public CloudCapeItem(ResourceLocation capeLocation, Properties properties) {
        super(capeLocation, properties);
    }

    boolean hasDoubleJumped = false;
    boolean canJump = false;

    @Override
    public void tick(ItemStack stack, SlotReference reference) {
        if(reference.entity().level().isClientSide()) {
            if (reference.entity().onGround()) {
                hasDoubleJumped = false;
                canJump = false;
            }
            else if(!canJump) {
                canJump = !Minecraft.getInstance().options.keyJump.isDown();
            }
            else if (!hasDoubleJumped && Minecraft.getInstance().options.keyJump.isDown()) {
                hasDoubleJumped = true;
                reference.entity().setDeltaMovement(reference.entity().getDeltaMovement().x(), 0.42, reference.entity().getDeltaMovement().z());
                reference.entity().resetFallDistance();
                if (reference.entity() instanceof ServerPlayer serverPlayer) {
                    serverPlayer.connection.send(new ClientboundSetEntityMotionPacket(serverPlayer));
                }
            }
        }
    }

}
