package teamrazor.deepaether.item.gear.other;

import com.aetherteam.aether.capability.player.AetherPlayer;
import com.aetherteam.aether.item.accessories.ring.RingItem;
import com.aetherteam.nitrogen.capability.INBTSynchable;
import net.minecraft.ChatFormatting;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.network.chat.Component;
import net.minecraft.network.protocol.game.*;
import net.minecraft.network.protocol.handshake.ClientIntentionPacket;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.targeting.TargetingConditions;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;
import org.checkerframework.common.reflection.qual.ClassBound;
import org.jetbrains.annotations.NotNull;
import teamrazor.deepaether.DeepAetherMod;
import teamrazor.deepaether.client.keys.DeepAetherKeys;
import teamrazor.deepaether.init.DAItems;
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

    private int maxFallTime = 0;
    private static TargetingConditions targetingConditions(AABB aabb, Entity entity2) {
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
        if (maxFallTime > 0) {
            maxFallTime--;

            //Triggers the shockwave if the entity hits a block
            if (!level.isEmptyBlock(player.getOnPos())) {

                DeepAetherPlayer.get(player).ifPresent((deepAetherPlayer) ->
                        deepAetherPlayer.setSynched(INBTSynchable.Direction.CLIENT, "setSliderSlamActivated", false));

                maxFallTime = 0;

                //Range of shockwave
                AABB aabb = player.getBoundingBox().inflate(3.0F);

                List<LivingEntity> entities = level.getNearbyEntities(LivingEntity.class, targetingConditions(aabb, player), player, aabb);

                //Pushes all entities within range
                for (LivingEntity target : entities) {
                    target.hurt(level.damageSources().playerAttack(player), 1.4F);
                    float knockback = EquipmentUtil.getCurios(player, DAItems.SLIDER_EYE.get()).size() == 2 ? 2.5F : 2F;

                    Vec3 push = target.position().vectorTo(player.position()).reverse().normalize().multiply(knockback, knockback, knockback);

                    if (push.y < 0)
                        push.multiply(1, -1, 1);

                    push.add(0F, 1, 0F);

                    target.addDeltaMovement(push);
                }
            }
        }
    }

    private void HandleClient(Player player, ItemStack stack, Level level) {

        if(maxFallTime > 0) {
            maxFallTime--;

            player.addDeltaMovement(new Vec3(0F, -0.5F, 0F));
            if (player instanceof ServerPlayer serverPlayer) {
                serverPlayer.connection.send(new ClientboundSetEntityMotionPacket(serverPlayer));
            }

            DeepAetherPlayer.get(player).ifPresent((deepAetherPlayer) -> {
                if (!deepAetherPlayer.isSliderSlamActivated()) {
                    maxFallTime = 0;
                    level.playSound(player, player.getOnPos(), SoundEvents.GENERIC_EXPLODE, SoundSource.PLAYERS);
                }
            });
        }

        else if (mayUse(stack, player)) {
            player.getCooldowns().addCooldown(stack.getItem(), EquipmentUtil.getCurios(player, DAItems.SLIDER_EYE.get()).size() == 2 ? 150 : 200);
            player.setDeltaMovement(0F, 0F, 0F);
            if (player instanceof ServerPlayer serverPlayer) {
                serverPlayer.connection.send(new ClientboundSetEntityMotionPacket(serverPlayer));

                DeepAetherPlayer.get(player).ifPresent((deepAetherPlayer) -> {
                    deepAetherPlayer.setSynched(INBTSynchable.Direction.SERVER, "setSliderSlamActivated", true);
                });
            }
            maxFallTime = 200;
        }
    }

    public boolean mayUse(ItemStack stack, Player player) {
        return DeepAetherKeys.SLIDER_EYE_SLAM_ABILITY.isDown() && !player.getCooldowns().isOnCooldown(stack.getItem()) && !player.onGround();
    }

    @Override
    public int getUseDuration(@NotNull ItemStack stack) {
        return 200;
    }

    int i = 0;
    @Override
    public List<Component> getAttributesTooltip(List<Component> tagTooltips, ItemStack stack) {


        if(i > 70)
            tagTooltips.add(Component.translatable("gui.deep_aether.flawless_tier_1"));
        else if(i > 60)
            tagTooltips.add(Component.translatable("gui.deep_aether.flawless_tier_2"));
        else if(i > 50)
            tagTooltips.add(Component.translatable("gui.deep_aether.flawless_tier_3"));
        else if(i > 40)
            tagTooltips.add(Component.translatable("gui.deep_aether.flawless_tier_4"));
        else if(i > 30)
            tagTooltips.add(Component.translatable("gui.deep_aether.flawless_tier_5"));
        else if(i > 20)
            tagTooltips.add(Component.translatable("gui.deep_aether.flawless_tier_6"));
        else if(i > 10)
            tagTooltips.add(Component.translatable("gui.deep_aether.flawless_tier_7"));
        else
            tagTooltips.add(Component.translatable("gui.deep_aether.flawless_tier_8"));

        if(i < 80)
            i++;
        else i = 0;


        return super.getAttributesTooltip(tagTooltips, stack);

    }
}
