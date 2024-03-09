package teamrazor.deepaether.item.gear.other;

import com.aetherteam.aether.client.AetherSoundEvents;
import com.aetherteam.aether.item.accessories.ring.RingItem;
import com.aetherteam.nitrogen.capability.INBTSynchable;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.network.chat.Component;
import net.minecraft.network.protocol.game.ClientboundSetEntityMotionPacket;
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
import teamrazor.deepaether.init.DAItems;
import teamrazor.deepaether.item.gear.EquipmentUtil;
import teamrazor.deepaether.networking.DeepAetherPlayer;
import top.theillusivec4.curios.api.SlotContext;

import java.util.List;
import java.util.function.Supplier;

public class SliderEye extends RingItem {

    public SliderEye(Supplier<? extends SoundEvent> ringSound, Properties properties) {
        super(ringSound, properties);
    }

    public int maxFallTime = 0;
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
        DeepAetherPlayer.get(player).ifPresent((aetherPlayer) -> {
            if(aetherPlayer.isSliderSlamActivated()) {
                maxFallTime = 200;
                aetherPlayer.setSynched(INBTSynchable.Direction.CLIENT, "setSliderSlamActivated", false);
            }
        });


        if (maxFallTime > 0) {

            maxFallTime--;

            //Triggers the shockwave if the entity hits a block
            if (player.onGround()) {
                if(!(player instanceof ServerPlayer)) {
                    maxFallTime = 0;
                }

                //Range of shockwave
                AABB aabb = new AABB(player.position().add(-3,-1,-3), player.position().add(3,4,3));

                List<LivingEntity> entities = level.getNearbyEntities(LivingEntity.class, targetingConditions(aabb, player), player, aabb);
                float knockback = EquipmentUtil.getCurios(player, DAItems.SLIDER_EYE.get()).size() == 2 ? 2.5F : 2F;


                //Pushes all entities within range
                for (LivingEntity target : entities) {
                    target.hurt(level.damageSources().playerAttack(player), 1.4F);


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
        if (mayUse(stack, player)) {
            if(!player.isCreative())
                player.getCooldowns().addCooldown(stack.getItem(), EquipmentUtil.getCurios(player, DAItems.SLIDER_EYE.get()).size() == 2 ? 150 : 200);
            player.setDeltaMovement(0F, 0F, 0F);
            if (player instanceof ServerPlayer serverPlayer) {
                serverPlayer.connection.send(new ClientboundSetEntityMotionPacket(serverPlayer));
            }

            DeepAetherPlayer.get(player).ifPresent((aetherPlayer) -> aetherPlayer.setSynched(INBTSynchable.Direction.SERVER, "setSliderSlamActivated", true));

            level.playSound(player, player.getOnPos(), AetherSoundEvents.ENTITY_SLIDER_MOVE.get(), SoundSource.PLAYERS);
            maxFallTime = 200;
        }

        if (maxFallTime > 0) {
            maxFallTime--;

            player.addDeltaMovement(new Vec3(0F, -0.3F, 0F));
            if (player instanceof ServerPlayer serverPlayer) {
                serverPlayer.connection.send(new ClientboundSetEntityMotionPacket(serverPlayer));
            }

            if (player.onGround()) {
                maxFallTime = 0;
                level.playSound(player, player.getOnPos(), SoundEvents.GENERIC_EXPLODE, SoundSource.PLAYERS);

                //Adds explosion
                level.addParticle(ParticleTypes.EXPLOSION_EMITTER, player.getX(), player.getY(), player.getZ(), 1.0D, 0.0D, 0.0D);
            }
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
