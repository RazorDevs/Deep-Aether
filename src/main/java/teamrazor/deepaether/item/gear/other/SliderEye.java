package teamrazor.deepaether.item.gear.other;

import com.aetherteam.aether.item.accessories.ring.RingItem;
import com.aetherteam.nitrogen.capability.INBTSynchable;
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
import org.jetbrains.annotations.NotNull;
import teamrazor.deepaether.client.keys.DeepAetherKeys;
import teamrazor.deepaether.datagen.tags.DATags;
import teamrazor.deepaether.init.DAItems;
import teamrazor.deepaether.init.DASounds;
import teamrazor.deepaether.item.gear.EquipmentUtil;
import teamrazor.deepaether.networking.DeepAetherPlayer;
import top.theillusivec4.curios.api.SlotContext;

import java.util.List;
import java.util.Optional;
import java.util.function.Supplier;

public class SliderEye extends RingItem {

    public SliderEye(Supplier<? extends SoundEvent> ringSound, Properties properties) {
        super(ringSound, properties);
    }

    private TargetingConditions targetingConditions(AABB aabb, Entity entity2) {
        return TargetingConditions.forCombat().selector((entity) -> !entity.is(entity2) && entity.level().getWorldBorder().isWithinBounds(aabb));
    }
    @Override
    public void curioTick(SlotContext slotContext, ItemStack stack) {

        Level level = slotContext.entity().level();

        if ((slotContext.entity() instanceof Player player)) {
            if(level.isClientSide()) {
                HandleClient(player, stack, level);
            }
            else HandleServer(player, level);
        }
    }

    private void HandleServer(Player player, Level level) {
        Optional<DeepAetherPlayer> deepAetherPlayer = DeepAetherPlayer.get(player).resolve();
        if (deepAetherPlayer.isEmpty() || !deepAetherPlayer.get().isSliderSlamActivated()) {
            return;
        }

        if (deepAetherPlayer.get().isSliderSlamActivated()) {
            player.addDeltaMovement(new Vec3(0F, -0.3F, 0F));
            if (player instanceof ServerPlayer serverPlayer) {
                serverPlayer.connection.send(new ClientboundSetEntityMotionPacket(serverPlayer));
            }

            if (player.onGround()) {
                deepAetherPlayer.get().setSynched(INBTSynchable.Direction.CLIENT, "setSliderSlamActivated", false);

                //Range of shockwave
                AABB aabb = new AABB(player.position().add(-3, -1, -3), player.position().add(3, 4, 3));

                List<LivingEntity> entities = level.getNearbyEntities(LivingEntity.class, targetingConditions(aabb, player), player, aabb);
                float knockback = EquipmentUtil.getCurios(player, DAItems.SLIDER_EYE.get()).size() == 2 ? 2.5F : 2F;

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
                if(player.isCreative()) {
                    if (player instanceof ServerPlayer serverPlayer) {
                        serverPlayer.connection.send(new ClientboundCooldownPacket(DAItems.SLIDER_EYE.get(), 0));
                    }
                    else player.getCooldowns().addCooldown(DAItems.SLIDER_EYE.get(), 0);
                }
            }
        }

    }

    private void HandleClient(Player player, ItemStack stack, Level level) {
        Optional<DeepAetherPlayer> deepAetherPlayer = DeepAetherPlayer.get(player).resolve();
        if(deepAetherPlayer.isEmpty() || !player.isLocalPlayer()) {
            return;
        }
        if (mayUse(stack, player)) {
            int cooldown = EquipmentUtil.getCurios(player, DAItems.SLIDER_EYE.get()).size() == 2 ? 150 : 200;
            player.getCooldowns().addCooldown(stack.getItem(), cooldown);
            player.setDeltaMovement(0F, 0F, 0F);
            DeepAetherPlayer.get(player).ifPresent((aetherPlayer) -> aetherPlayer.setSynched(INBTSynchable.Direction.SERVER, "setSliderSlamActivated", true));
            level.playSound(player, player.getOnPos(), DASounds.ITEM_ACCESSORY_ABILITY_SLIDER_EYE.get(), SoundSource.PLAYERS);
        }

        if (deepAetherPlayer.get().isSliderSlamActivated() && !player.getCooldowns().isOnCooldown(DAItems.SLIDER_EYE.get())) {
            deepAetherPlayer.get().setSynched(INBTSynchable.Direction.SERVER, "setSliderSlamActivated", false);
        }
    }

    public boolean mayUse(ItemStack stack, Player player) {
        return DeepAetherKeys.SLIDER_EYE_SLAM_ABILITY.isDown() && !player.getCooldowns().isOnCooldown(stack.getItem()) && !player.onGround();
    }

    @Override
    public int getUseDuration(@NotNull ItemStack stack) {
        return 200;
    }
}
