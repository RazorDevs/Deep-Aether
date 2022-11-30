package teamrazor.deepaether.item.gear.cloudium;


import com.gildedgames.aether.item.accessories.AccessoryItem;

import net.minecraft.client.KeyMapping;
import net.minecraft.client.Minecraft;
import net.minecraft.client.Options;
import net.minecraft.network.protocol.game.ClientboundSetEntityMotionPacket;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;

import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;

import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;

import net.minecraft.world.item.Items;
import net.minecraftforge.client.event.InputEvent;
import top.theillusivec4.curios.api.SlotContext;

import javax.swing.text.JTextComponent;

public class CloudiumRing extends AccessoryItem {
    public CloudiumRing(Properties properties) {
        super(properties);

    }
    float pTime;
    @Override
    public void curioTick(SlotContext slotContext, ItemStack stack) {
        pTime += 0.02;
        LivingEntity livingEntity = slotContext.entity();
        if (!livingEntity.isOnGround()) {
            if(pTime >= 1.5)
                if(livingEntity instanceof Player player) {
                    if(!player.getItemBySlot(EquipmentSlot.CHEST).is(Items.ELYTRA) && Minecraft.getInstance().options.keyUp.isDown()) {
                        Double x = player.getLookAngle().x * 0.01;
                        Double z = player.getLookAngle().z * 0.01;
                        player.push(x, 0, z);
                        if (player instanceof ServerPlayer serverPlayer) {
                            serverPlayer.connection.send(new ClientboundSetEntityMotionPacket(serverPlayer));
                        }
                    }
                }
                //livingEntity.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SPEED, 4, 2, false, false));
        }
        else {
            pTime = 0;
        }
    }
}