package io.github.razordevs.deep_aether.event;

import com.aetherteam.aether.entity.AetherBossMob;
import com.aetherteam.aether.entity.AetherEntityTypes;
import com.aetherteam.aether.entity.passive.Moa;
import com.aetherteam.aether.event.BossFightEvent;
import com.aetherteam.nitrogen.attachment.INBTSynchable;
import io.github.razordevs.deep_aether.DeepAether;
import io.github.razordevs.deep_aether.advancement.DAAdvancementTriggers;
import io.github.razordevs.deep_aether.entity.MoaBonusJump;
import io.github.razordevs.deep_aether.init.DAItems;
import io.github.razordevs.deep_aether.init.DAMobEffects;
import io.github.razordevs.deep_aether.item.gear.EquipmentUtil;
import io.github.razordevs.deep_aether.networking.attachment.DAAttachments;
import io.github.razordevs.deep_aether.networking.attachment.DAPlayerAttachment;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.protocol.game.ClientboundSetEntityMotionPacket;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.stats.Stats;
import net.minecraft.tags.DamageTypeTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.MobType;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.ModList;
import net.neoforged.neoforge.event.entity.EntityJoinLevelEvent;
import net.neoforged.neoforge.event.entity.living.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

@EventBusSubscriber(modid = DeepAether.MODID)
public class DAGeneralEvents {

    @SubscribeEvent
    public static void onEntityJoin(EntityJoinLevelEvent event) {
        if (event.getEntity() instanceof Moa moa) {
            moa.getData(DAAttachments.MOA_EFFECT).onJoinLevel(moa);
        }
    }

    @SubscribeEvent
    public static void onDungeonPlayerAdded(BossFightEvent.AddPlayer event) {
        event.getPlayer().setData(DAAttachments.PLAYER_BOSS_FIGHT.get(), false);
    }

    @SubscribeEvent
    public static void onLivingEntityDeath(LivingDeathEvent event) {
        LivingEntity entity = event.getEntity();
        if (entity.getType() == AetherEntityTypes.SLIDER.get() && DeepAether.IsHalloweenContentEnabled()) {
            entity.spawnAtLocation(new ItemStack(DAItems.SPOOKY_RING.get(), 1));
        }

        //For flawless boss drop system
        if (entity instanceof AetherBossMob<?> bossMob) {
            Level level = entity.level();

            //Checks if boss has been defeated
            if (!event.getEntity().isAlive() && !level.isClientSide() && bossMob.getDungeon() != null) {
                List<UUID> uuids = bossMob.getDungeon().dungeonPlayers();

                List<Player> players = new ArrayList<>();

                //Checks if any player has taken damage
                for (UUID uuid : uuids) {
                    Player player = level.getPlayerByUUID(uuid);
                    if (player != null) {
                        if (player.getData(DAAttachments.PLAYER_BOSS_FIGHT))
                            return;
                        players.add(player);
                    }
                }

                //For advancement
                for (Player player : players) {
                    DAAdvancementTriggers.FLAWLESS_TRIGGER.get().trigger((ServerPlayer) player, entity, event.getSource());
                }

                //Checks if flawless boss drop has been disabled
                if (FLAWLESS_BOSS_DROP.get(entity.getType()) != null) {


                    //Spawns the flawless boss drop
                    ItemStack stack = new ItemStack(FLAWLESS_BOSS_DROP.get(entity.getType()));
                    ItemEntity itementity = new ItemEntity(level, entity.getX(), entity.getY() + 0.0, entity.getZ(), stack);
                    itementity.setDefaultPickUpDelay();
                    level.addFreshEntity(itementity);
                }
            }
        }
    }

    @SubscribeEvent
    public static void onShieldBlock(ShieldBlockEvent event) {
        var blocker = event.getEntity();
        DamageSource source = event.getDamageSource();
        if (ModList.get().isLoaded(DeepAether.LOST_AETHER_CONTENT)) {
            if (blocker.getUseItem().is(TagKey.create(Registries.ITEM, ResourceLocation.fromNamespaceAndPath(DeepAether.LOST_AETHER_CONTENT, "aether_shields")))) {
                blocker.level().playSound(null, blocker.blockPosition(), SoundEvents.ZOMBIE_ATTACK_IRON_DOOR, blocker.getSoundSource(), 0.4F, 0.8F + blocker.level().random.nextFloat() * 0.4F);

                if (blocker.getUseItem().getItem() == DAItems.STRATUS_SHIELD.get() && source.getDirectEntity() instanceof LivingEntity attacker) {
                    attacker.knockback(1.5F, blocker.getX() - attacker.getX(), blocker.getZ() - attacker.getZ());
                    attacker.setPos(attacker.getX(), attacker.getY() + 1D, attacker.getZ());
                    attacker.hasImpulse = true;
                }
            }
        }
    }

    @SubscribeEvent
    public static void onEffectRemoved(MobEffectEvent.Remove effectEvent) {
        LivingEntity entity = effectEvent.getEntity();
        MobEffect effect = effectEvent.getEffect();
        if (entity instanceof Moa moa && effect.equals(DAMobEffects.MOA_BONUS_JUMPS.get())) {
            MoaBonusJump moaBonusJump = (MoaBonusJump) moa;
            moaBonusJump.deep_Aether$setBonusJumps(0);
        }
    }


    @SubscribeEvent
    public static void applyValkyrieValorRes(LivingDamageEvent event) {
        if (event.getSource().getEntity() instanceof LivingEntity undead) {
            if (event.getEntity().hasEffect(DAMobEffects.VALKYRIE_VALOR.get()) && !event.getSource().is(DamageTypeTags.BYPASSES_RESISTANCE) && undead.getMobType() == MobType.UNDEAD) {
                int j = 10;
                float f = event.getAmount() * (float) j;
                float f1 = event.getAmount();
                event.setAmount(Math.max(f / 25.0F, 0.0F));
                float f2 = f1 - event.getAmount();
                if (f2 > 0.0F && f2 < 3.4028235E37F) {
                    if (event.getEntity() instanceof ServerPlayer player) {
                        player.awardStat(Stats.CUSTOM.get(Stats.DAMAGE_RESISTED), Math.round(f2 * 10.0F));
                    } else if (event.getSource().getEntity() instanceof ServerPlayer player) {
                        player.awardStat(Stats.CUSTOM.get(Stats.DAMAGE_DEALT_RESISTED), Math.round(f2 * 10.0F));
                    }
                }
            }
        }
    }

    /**
     * Used to check if a player has been hurt during a boss fight
     * See {@link DAAttachments#PLAYER_BOSS_FIGHT}
     */

    public static HashMap<EntityType<?>, Item> FLAWLESS_BOSS_DROP = new HashMap<>();

    @SubscribeEvent
    public static void onLivingEntityHurt(LivingHurtEvent event) {
        if (event.getEntity() instanceof ServerPlayer player && !event.getEntity().isDamageSourceBlocked(event.getSource())) {
            player.setData(DAAttachments.PLAYER_BOSS_FIGHT, true);
        }
        if (event.getSource().getDirectEntity() != null && event.getSource().getDirectEntity() instanceof LivingEntity target) {
            if (EquipmentUtil.hasFullStormsteelSet(event.getEntity())) {
                target.knockback(0.5F, event.getEntity().getX() - target.getX(), event.getEntity().getZ() - target.getZ());
                if (target instanceof ServerPlayer serverPlayer) {
                    serverPlayer.connection.send(new ClientboundSetEntityMotionPacket(serverPlayer));
                }
            }
        }
    }

    @SubscribeEvent
    public static void onEquip(LivingEquipmentChangeEvent event) {
        if(event.getEntity() instanceof Player player) {
            DAPlayerAttachment attachment = player.getData(DAAttachments.PLAYER);
            boolean skyjade = EquipmentUtil.hasFullSkyjadeSet(player);
            boolean enabled = attachment.isSkyjadeAbilityActivated();
            attachment.setSynched(player.getId(), INBTSynchable.Direction.CLIENT, "hasSkyjadeSet", skyjade);

            EquipmentUtil.updateSkyjadeBehavior(player, skyjade && enabled);
        }
    }

    @SubscribeEvent
    public static void livingVisibilityModification(LivingEvent.LivingVisibilityEvent event) {
        if(event.getLookingEntity() instanceof LivingEntity living) {
            boolean enabled = true;
            if(living instanceof Player player) {
                enabled = player.getData(DAAttachments.PLAYER).isSkyjadeAbilityActivated();
            }
            if(enabled && EquipmentUtil.hasFullSkyjadeSet(living))
                event.modifyVisibility(event.getVisibilityModifier() * 0.5F);
        }
    }
}
