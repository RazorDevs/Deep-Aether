package teamrazor.deepaether.event;

import net.minecraft.client.Minecraft;
import net.minecraft.core.BlockPos;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.tags.TagKey;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.ViewportEvent;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.event.entity.living.ShieldBlockEvent;
import net.minecraftforge.event.server.ServerStartingEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.ModList;
import net.minecraftforge.fml.common.Mod;
import teamrazor.deepaether.DeepAetherMod;
import teamrazor.deepaether.init.DABlocks;
import teamrazor.deepaether.init.DAItems;

@Mod.EventBusSubscriber(modid = DeepAetherMod.MODID,  value = Dist.CLIENT, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class DAEvents {

    // Fog effect to mimic PowderSnow behaviour
    @SubscribeEvent
    public static void fogDensityEvent(ViewportEvent.RenderFog event) {
        Minecraft mc = Minecraft.getInstance();
        Player player = mc.player;
        if (player != null) {
            if (mc.level != null) {
                BlockState state = mc.level.getBlockState(new BlockPos(player.blockPosition().above(1)));
                if (state.is(DABlocks.VIRULENT_QUICKSAND.get())) {
                    event.setNearPlaneDistance(0.5f);
                    event.setFarPlaneDistance(1.8f);
                    event.setCanceled(true);
                }
            }
        }
    }


    @SubscribeEvent
    public static void onShieldBlock(ShieldBlockEvent event)
    {
        var blocker = event.getEntity();
        DamageSource source = event.getDamageSource();
        if(ModList.get().isLoaded(DeepAetherMod.LOST_AETHER_CONTENT)) {
            if (blocker.getUseItem().is(TagKey.create(Registries.ITEM, new ResourceLocation(DeepAetherMod.LOST_AETHER_CONTENT, "aether_shields")))) {
                blocker.level.playSound(null, blocker.blockPosition(), SoundEvents.ZOMBIE_ATTACK_IRON_DOOR, blocker.getSoundSource(), 0.4F, 0.8F + blocker.level.random.nextFloat() * 0.4F);

                if (blocker.getUseItem().getItem() == DAItems.STRATUS_SHIELD.get() && source.getDirectEntity() instanceof LivingEntity attacker) {
                    attacker.knockback(1.5F, blocker.getX() - attacker.getX(), blocker.getZ() - attacker.getZ());
                    attacker.setPos(attacker.getX(), attacker.getY() + 1D, attacker.getZ());
                    attacker.hasImpulse = true;
                }
            }
        }
    }

    public boolean mayflyChromatic = false;
    @SubscribeEvent
    public void playerTickEvent(TickEvent.PlayerTickEvent event) {
        Player player = event.player;
        mayflyChromatic = player.getFeetBlockState().is(DABlocks.CHROMATIC_AERCLOUD.get());

        if(!isMayfly(player)) {
            player.getAbilities().flying = false;
        }
    }

    public boolean isMayfly(Player player) {
        if(player.getAbilities().mayfly) {
            return true;
        }
        else return mayflyChromatic;
    }


    static private void startFlying(Player player) {
        player.getAbilities().mayfly = true;
        player.onUpdateAbilities();
    }

    static private void stopFlying(Player player) {
        player.getAbilities().flying = false;
        player.getAbilities().mayfly = false;
        player.onUpdateAbilities();
    }
}
