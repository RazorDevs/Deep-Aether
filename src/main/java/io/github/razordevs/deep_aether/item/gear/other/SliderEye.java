package io.github.razordevs.deep_aether.item.gear.other;

import com.aetherteam.aether.item.EquipmentUtil;
import com.aetherteam.aether.item.accessories.AccessoryItem;
import com.aetherteam.nitrogen.attachment.INBTSynchable;
import io.github.razordevs.deep_aether.client.DeepAetherKeys;
import io.github.razordevs.deep_aether.datagen.tags.DATags;
import io.github.razordevs.deep_aether.init.DAItems;
import io.github.razordevs.deep_aether.init.DASounds;
import io.github.razordevs.deep_aether.networking.attachment.DAAttachments;
import io.github.razordevs.deep_aether.networking.attachment.DAPlayerAttachment;
import io.wispforest.accessories.api.slot.SlotReference;
import net.minecraft.core.Holder;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.network.protocol.game.ClientboundCooldownPacket;
import net.minecraft.network.protocol.game.ClientboundSetEntityMotionPacket;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.targeting.TargetingConditions;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;

import java.util.List;

public class SliderEye extends AccessoryItem {

    public SliderEye(Holder<SoundEvent> ringSound, Properties properties) {
        super(ringSound, properties);
    }

    private TargetingConditions targetingConditions(AABB aabb, Entity entity2) {
        return TargetingConditions.forCombat().selector((entity) -> !entity.is(entity2) && entity.level().getWorldBorder().isWithinBounds(aabb));
    }

    @Override
    public void tick(ItemStack stack, SlotReference reference) {
        Level level = reference.entity().level();

        if ((reference.entity() instanceof Player player)) {
            if(level.isClientSide()) {
                HandleClient(player, stack, level);
            }
            else HandleServer(player, level);
        }
    }

    private void HandleServer(Player player, Level level) {
        if (player.hasData(DAAttachments.PLAYER)) {
            DAPlayerAttachment attachment = player.getData(DAAttachments.PLAYER);
            if (attachment.isSliderSlamActivated()) {
                if (player.isSpectator()) {
                    attachment.setSynched(player.getId(), INBTSynchable.Direction.CLIENT, "setSliderSlamActivated", false);
                    return;
                }

                player.addDeltaMovement(new Vec3(0F, -0.3F, 0F));
                if (player instanceof ServerPlayer serverPlayer) {
                    serverPlayer.connection.send(new ClientboundSetEntityMotionPacket(serverPlayer));
                }

                if (player.onGround()) {
                    attachment.setSynched(player.getId(), INBTSynchable.Direction.CLIENT, "setSliderSlamActivated", false);

                    //Range of shockwave
                    AABB aabb = new AABB(player.position().add(-3, -1, -3), player.position().add(3, 4, 3));

                    List<LivingEntity> entities = level.getNearbyEntities(LivingEntity.class, targetingConditions(aabb, player), player, aabb);
                    float knockback = EquipmentUtil.getAccessories(player, DAItems.SLIDER_EYE.get()).size() == 2 ? 2.5F : 2F;

                    //Pushes all entities within range
                    for (LivingEntity target : entities) {
                        if (!target.getType().is(DATags.Entities.SLIDER_SLAM_BLACKLIST)) {
                            Vec3 push = target.position().vectorTo(player.position()).reverse().normalize().multiply(knockback, knockback, knockback);

                            if (push.y < 0)
                                push.multiply(1, -1, 1);

                            push.add(0F, 1, 0F);

                            target.addDeltaMovement(push);
                            target.hurt(level.damageSources().playerAttack(player), 1.0F);
                            if (target instanceof ServerPlayer serverPlayer) {
                                serverPlayer.connection.send(new ClientboundSetEntityMotionPacket(serverPlayer));
                            }
                        }
                    }
                    ((ServerLevel) level).sendParticles(ParticleTypes.EXPLOSION_EMITTER, player.getX(), player.getY(), player.getZ(), 1, 0.0, 0.0, 0.0, 0.0);
                    level.playSound(null, player.getX(), player.getY(), player.getZ(), SoundEvents.GENERIC_EXPLODE, SoundSource.PLAYERS, 1.0F, 1.0F);
                    //Allows Creative Players to use Slider Eye's ability without cooldown
                    if (player.isCreative()) {
                        if (player instanceof ServerPlayer serverPlayer) {
                            serverPlayer.connection.send(new ClientboundCooldownPacket(DAItems.SLIDER_EYE.get(), 0));
                        } else player.getCooldowns().addCooldown(DAItems.SLIDER_EYE.get(), 0);
                    }
                }
            }
        }
    }

    private void HandleClient(Player player, ItemStack stack, Level level) {
        if(player.isLocalPlayer() && player.hasData(DAAttachments.PLAYER)) {
            DAPlayerAttachment attachment = player.getData(DAAttachments.PLAYER);
            if (mayUse(stack, player)) {
                int cooldown = EquipmentUtil.getAccessories(player, DAItems.SLIDER_EYE.get()).size() == 2 ? 150 : 200;
                player.getCooldowns().addCooldown(stack.getItem(), cooldown);
                player.setDeltaMovement(0F, 0F, 0F);
                attachment.setSynched(player.getId(), INBTSynchable.Direction.SERVER, "setSliderSlamActivated", true);
                level.playSound(player, player.getOnPos(), DASounds.ITEM_ACCESSORY_ABILITY_SLIDER_EYE.get(), SoundSource.PLAYERS);
            }

            if (attachment.isSliderSlamActivated() && !player.getCooldowns().isOnCooldown(DAItems.SLIDER_EYE.get())) {
                attachment.setSynched(player.getId(), INBTSynchable.Direction.SERVER, "setSliderSlamActivated", false);
            }
        }
    }
    public boolean mayUse(ItemStack stack, Player player) {
        return DeepAetherKeys.SLIDER_EYE_SLAM_ABILITY.isDown() && !player.getCooldowns().isOnCooldown(stack.getItem()) && !player.onGround();
    }

    @Override
    public int getUseDuration(ItemStack stack, LivingEntity entity) {
        return 200;
    }
}
