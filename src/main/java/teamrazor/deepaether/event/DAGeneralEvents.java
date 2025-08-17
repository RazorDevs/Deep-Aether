package teamrazor.deepaether.event;

import com.aetherteam.aether.entity.AetherBossMob;
import com.aetherteam.aether.entity.AetherEntityTypes;
import com.aetherteam.aether.entity.monster.AechorPlant;
import com.aetherteam.aether.entity.passive.Moa;
import com.aetherteam.aether.event.BossFightEvent;
import com.aetherteam.nitrogen.capability.INBTSynchable;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.network.protocol.game.ClientboundSetEntityMotionPacket;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.stats.Stats;
import net.minecraft.tags.DamageTypeTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.MobType;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.*;
import net.minecraft.world.level.Level;
import net.minecraftforge.event.entity.living.LivingDamageEvent;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.event.entity.living.MobEffectEvent;
import net.minecraftforge.event.entity.living.ShieldBlockEvent;
import net.minecraftforge.event.entity.player.ItemTooltipEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.ModList;
import net.minecraftforge.fml.common.Mod;
import teamrazor.deepaether.DeepAether;
import teamrazor.deepaether.advancement.DAAdvancementTriggers;
import teamrazor.deepaether.datagen.tags.DATags;
import teamrazor.deepaether.entity.GentleWind;
import teamrazor.deepaether.entity.IPlayerBossFight;
import teamrazor.deepaether.entity.MoaBonusJump;
import teamrazor.deepaether.init.DAItems;
import teamrazor.deepaether.init.DAMobEffects;
import teamrazor.deepaether.item.dungeon.brass.FloatyScarfItem;
import teamrazor.deepaether.item.gear.EquipmentUtil;
import teamrazor.deepaether.networking.DeepAetherPlayer;
import top.theillusivec4.curios.api.SlotResult;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Mod.EventBusSubscriber(modid = DeepAether.MODID)
public class DAGeneralEvents {

    @SubscribeEvent
    public static void onDungeonPlayerAdded(BossFightEvent.AddPlayer event) {
        ((IPlayerBossFight) event.getPlayer()).deep_Aether$setHasBeenHurt(false);
    }

    @SubscribeEvent
    public static void onLivingEntityDeath(LivingDeathEvent event) {
        LivingEntity entity = event.getEntity();
        if (entity.getType() == AetherEntityTypes.SLIDER.get() && DeepAether.IsHalloweenContentEnabled()) {
            entity.spawnAtLocation(new ItemStack(DAItems.SPOOKY_RING.get(), 1));
        }

        //For flawless boss drop system
        if(entity instanceof AetherBossMob<?> bossMob) {
            Level level = entity.level();

            //Checks if boss has been defeated
            if (!event.getEntity().isAlive() && !level.isClientSide() && bossMob.getDungeon() != null) {
                List<UUID> uuids = bossMob.getDungeon().dungeonPlayers();

                List<Player> players = new ArrayList<>();

                //Checks if any player has taken damage
                for (UUID uuid : uuids) {
                    Player player = level.getPlayerByUUID(uuid);
                    if (player != null) {
                        if (((IPlayerBossFight) player).deep_Aether$getHasBeenHurt())
                            return;
                        players.add(player);
                    }
                }



                //For advancement
                for (Player player : players) {
                    DAAdvancementTriggers.FLAWLESS.trigger((ServerPlayer) player, entity, event.getSource());
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
    public static void onShieldBlock(ShieldBlockEvent event)
    {
        var blocker = event.getEntity();
        DamageSource source = event.getDamageSource();
        if(ModList.get().isLoaded(DeepAether.LOST_AETHER_CONTENT)) {
            if (blocker.getUseItem().is(TagKey.create(Registries.ITEM, new ResourceLocation(DeepAether.LOST_AETHER_CONTENT, "aether_shields")))) {
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
        if(entity instanceof Moa moa && effect.equals(DAMobEffects.MOA_BONUS_JUMPS.get())) {
            MoaBonusJump moaBonusJump = (MoaBonusJump) moa;
            moaBonusJump.deep_Aether$setBonusJumps(0);
        }
    }


    @SubscribeEvent
    public static void applyValkyrieValorRes(LivingDamageEvent event){
        if(event.getSource().getEntity() instanceof LivingEntity undead) {
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
     * See {@link IPlayerBossFight}
     */
    public static HashMap<EntityType<?>, Item> FLAWLESS_BOSS_DROP = new HashMap<>();

    @SubscribeEvent
    public static void onLivingEntityHurt(LivingHurtEvent event) {
        if(event.getEntity() instanceof ServerPlayer player && !event.getEntity().isDamageSourceBlocked(event.getSource())) {
            ((IPlayerBossFight) player).deep_Aether$setHasBeenHurt(true);
        }
        if (event.getSource().getDirectEntity() != null && event.getSource().getDirectEntity() instanceof LivingEntity target) {
            if (EquipmentUtil.hasFullStormForgedSet(event.getEntity())) {
                target.knockback(0.5F, event.getEntity().getX() - target.getX(), event.getEntity().getZ() - target.getZ());
                if (target instanceof ServerPlayer serverPlayer) {
                    serverPlayer.connection.send(new ClientboundSetEntityMotionPacket(serverPlayer));
                }
            }
        }

        if (event.getEntity() instanceof Player player) {
            DeepAetherPlayer.get(player).ifPresent((daPlayer) -> {

                Optional<SlotResult> stack = EquipmentUtil.getWindShield(player);
                if (stack.isPresent() && !event.getSource().is(DamageTypeTags.BYPASSES_INVULNERABILITY) && daPlayer.getWindShieldCooldown() <= 0) {
                    daPlayer.setSynched(INBTSynchable.Direction.CLIENT, "setWindShieldCooldown", 1200);
                    player.level().playSound(null, player.getX(), player.getY(), player.getZ(), SoundEvents.SHIELD_BLOCK, SoundSource.PLAYERS, 1.0F, 1.0F);

                    if (!player.level().isClientSide()) {
                        stack.get().stack().hurtAndBreak(1, player, item -> {});
                        player.invulnerableTime = 20;
                    }
                    event.setCanceled(true);
                }
            });
        }
    }


    @SubscribeEvent
    public static void playerLoggedOutEvent(PlayerEvent.PlayerLoggedOutEvent event) {
        Player player = event.getEntity();
        Optional<SlotResult> reference = EquipmentUtil.getFloatyScarf(player);

        reference.ifPresent(slotResult -> FloatyScarfItem.discardGentleWind(slotResult.stack(), player.level()));
    }

    private static int i = 0;

    @SubscribeEvent(priority = EventPriority.LOWEST)
    public static void onTooltipAdd(ItemTooltipEvent event) {
        ItemStack stack = event.getItemStack();
        TooltipFlag flag = event.getFlags();
        List<Component> itemTooltips = event.getToolTip();

        if (flag.isCreative()) {
            int position = itemTooltips.size();
            Component itemName = stack.getItem().getName(stack);
            for (int i = 0; i < position; i++) {
                Component component = itemTooltips.get(i);
                if (component.getString().equals(itemName.getString())) {
                    position = i + 1;
                    break;
                }
            }
            if (stack.is(DATags.Items.BRASS_DUNGEON_LOOT)) {
                itemTooltips.add(position, DAItems.BRASS_DUNGEON_TOOLTIP);
            }
        }
        if (stack.is(DATags.Items.FLAWLESS_ITEMS)) {
            flawlessComponent(itemTooltips, i);
            i = i < 80 ? i + 1 : 0;
        }
    }

    /**
     * Mimics behavior of {@link AechorPlant#mobInteract(Player player, InteractionHand hand)} for vanilla Buckets
     */
    @SubscribeEvent
    public static void mobInteract(PlayerInteractEvent.EntityInteractSpecific event) {
        if (event.getTarget().getType() == AetherEntityTypes.AECHOR_PLANT.get()) {
            AechorPlant aechorPlant = ((AechorPlant) event.getTarget());
            Player player = event.getEntity();

            ItemStack itemStack = player.getItemInHand(event.getHand());
            if (itemStack.is(Items.BUCKET) && aechorPlant.getPoisonRemaining() > 0) {
                aechorPlant.setPoisonRemaining(aechorPlant.getPoisonRemaining() - 1);
                ItemStack itemStack1 = ItemUtils.createFilledResult(itemStack, player, DAItems.PLACEABLE_POISON_BUCKET.get().getDefaultInstance());
                player.setItemInHand(event.getHand(), itemStack1);
                player.swing(event.getHand());
                event.setCancellationResult(InteractionResult.SUCCESS);
            }
        }
    }

    @SubscribeEvent
    public static void onPlayerLogin(PlayerEvent.PlayerLoggedInEvent event) {
        syncGentleWind(event.getEntity());
    }

    @SubscribeEvent
    public static void onPlayerChangedDimension(PlayerEvent.PlayerChangedDimensionEvent event) {
        syncGentleWind(event.getEntity());
    }


    private static void syncGentleWind(Player player) {
        if (!player.level().isClientSide()) {
            Optional<SlotResult> result = EquipmentUtil.getFloatyScarf(player);

            //Sync the new player's floaty scarf with the other players
            if (result.isPresent()) {
                try {
                    GentleWind gentleWind = (GentleWind) FloatyScarfItem.getGentleWind(result.get().stack(), player.level());

                    if (gentleWind == null || !gentleWind.isAlive()) {
                        Optional<DeepAetherPlayer> deepAetherPlayer = DeepAetherPlayer.get(player).resolve();

                        deepAetherPlayer.ifPresent(aetherPlayer ->
                                aetherPlayer.setSynched(INBTSynchable.Direction.CLIENT, "setFloatyScarfWrappedAroundNeck", true));
                    }
                } catch (ClassCastException ignore) {
                }
            }

            //Sync the new player with the other players' floaty scarf
            List<? extends Player> players = player.level().players();
            for (Player serverPlayer : players) {
                if (!serverPlayer.getUUID().equals(player.getUUID())) {
                    DeepAetherPlayer.get(serverPlayer).ifPresent((aetherPlayer) ->
                            aetherPlayer.setSynched(INBTSynchable.Direction.PLAYER, "setFloatyScarfWrappedAroundNeck", aetherPlayer.isFloatyScarfWrappedAroundNeck(), player));
                }
            }
        }
    }

    private static void flawlessComponent(List<Component> tagTooltips, int i) {
        if(i > 70)
            printComponent(tagTooltips, 1);
        else if(i > 60)
            printComponent(tagTooltips, 2);
        else if(i > 50)
            printComponent(tagTooltips, 3);
        else if(i > 40)
            printComponent(tagTooltips, 4);
        else if(i > 30)
            printComponent(tagTooltips, 5);
        else if(i > 20)
            printComponent(tagTooltips, 6);
        else if(i > 10)
            printComponent(tagTooltips, 7);
        else
            printComponent(tagTooltips, 8);
    }
    private static void printComponent(List<Component> tagTooltips, int i){
        tagTooltips.add(1, Component.translatable("gui.deep_aether.flawless_tier_" + i));
    }
}