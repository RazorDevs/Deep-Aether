package io.github.razordevs.deep_aether.item.compat;

import com.aetherteam.genesis.client.GenesisSoundEvents;
import com.aetherteam.genesis.entity.projectile.CogProjectile;
import io.github.razordevs.deep_aether.init.DAItems;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

public class MagneticStaffItem extends Item {
    public MagneticStaffItem(Properties properties) {
        super(properties);
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand interactionHand) {
        var item = player.getItemInHand(interactionHand);

        this.shootCog(player);
        item.hurtAndConvertOnBreak(1, DAItems.MAGNETIC_COG.asItem(), player, player.getEquipmentSlotForItem(item));
        player.getCooldowns().addCooldown(item.getItem(), 50);

        return InteractionResultHolder.success(item);
    }

    private void shootCog(Player player){
        CogProjectile cog = new CogProjectile(player.level(), player, false);

        cog.setPos(player.getEyePosition());
        var vec3 = player.getViewVector(1.0f);
        double x = vec3.x();
        double y = vec3.y();
        double z = vec3.z();
        float dist = (float)Math.sqrt(x * x + z * z);

        if (!player.level().isClientSide()) {
            float distance = dist * 0.075F;
            cog.shoot(x, y, z, distance * 5, 1.0F);

            player.playSound(GenesisSoundEvents.ENTITY_LABYRINTH_EYE_COG_LOSS.get(), 2.0F, 1.0F);
            player.level().addFreshEntity(cog);
        }
    }
}
