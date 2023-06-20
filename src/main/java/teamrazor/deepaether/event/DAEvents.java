package teamrazor.deepaether.event;

import com.legacy.lost_aether.data.LCTags;
import net.minecraft.client.Minecraft;
import net.minecraft.core.BlockPos;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.ViewportEvent;
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
        if(ModList.get().isLoaded("lost_aether_content")) {
            if (blocker.getUseItem().is(LCTags.Items.AETHER_SHIELDS)) {
                blocker.level.playSound(null, blocker.blockPosition(), SoundEvents.ZOMBIE_ATTACK_IRON_DOOR, blocker.getSoundSource(), 0.4F, 0.8F + blocker.level.random.nextFloat() * 0.4F);

                if (blocker.getUseItem().getItem() == DAItems.STRATUS_SHIELD.get() && source.getDirectEntity() instanceof LivingEntity attacker) {
                    attacker.knockback(1.5F, blocker.getX() - attacker.getX(), blocker.getZ() - attacker.getZ());
                    attacker.setPos(attacker.getX(), attacker.getY() + 1D, attacker.getZ());
                    attacker.hasImpulse = true;
                }
            }
        }
    }

    /*@SubscribeEvent
    public static void fogColorEvent(ViewportEvent.ComputeFogColor event) {
        Minecraft mc = Minecraft.getInstance();
        // Reduce lava fog from players when they have the lava vision enchantment
        Player player = mc.player;
        if (player != null) {
            if (mc.level != null) {
                BlockState state = mc.level.getBlockState(new BlockPos(player.blockPosition().above(1)));
                if (state.is(DABlocks.VIRULENT_QUICKSAND.get())) {
                    event.setBlue(0.4f);
                    event.setCanceled(true);
                }
            }
        }
    }*/
}
