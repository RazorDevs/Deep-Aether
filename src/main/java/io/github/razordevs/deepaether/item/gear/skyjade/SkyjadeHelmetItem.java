package io.github.razordevs.deepaether.deepaether.item.gear.skyjade;

import com.aetherteam.nitrogen.attachment.INBTSynchable;
import io.github.razordevs.deepaether.deepaether.item.gear.EquipmentUtil;
import io.github.razordevs.deepaether.deepaether.networking.attachment.DAAttachments;
import io.github.razordevs.deepaether.deepaether.networking.attachment.DAPlayerAttachment;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import io.github.razordevs.deepaether.deepaether.client.keys.DeepAetherKeys;

public class SkyjadeHelmetItem extends SkyjadeArmorItem {
    public SkyjadeHelmetItem(ArmorMaterial pMaterial, Type pType, Properties pProperties) {
        super(pMaterial, pType, pProperties);
    }

    @Override
    public void inventoryTick(ItemStack pStack, Level pLevel, Entity pEntity, int pSlotId, boolean pIsSelected) {
        if(pSlotId == 39 && pEntity instanceof Player player) {
            DAPlayerAttachment attachment = player.getData(DAAttachments.PLAYER);
            if(pLevel.isClientSide() && attachment.isSkyjadeAbilityActivated() != DeepAetherKeys.TOGGLE_SKYJADE_TRANSPARENCY.isDown()) {
                attachment.setSynched(player.getId(), INBTSynchable.Direction.SERVER, "setSkyjadeAbilityActivated", DeepAetherKeys.TOGGLE_SKYJADE_TRANSPARENCY.isDown());
            }
            EquipmentUtil.updateSkyjadeBehavior(player, attachment.isSkyjadeAbilityActivated() && attachment.hasSkyjadeSet());
        }
    }
}
