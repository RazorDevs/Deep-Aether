package teamrazor.deepaether.block.Behaviors;

import com.gildedgames.aether.block.AetherBlocks;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.stats.Stats;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.alchemy.PotionUtils;
import net.minecraft.world.item.alchemy.Potions;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import teamrazor.deepaether.DeepAetherMod;
import teamrazor.deepaether.init.DABlocks;

@Mod.EventBusSubscriber(modid = DeepAetherMod.MODID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class DABlockInteractionBehavior {


    @SubscribeEvent
    public static void onRightClick(PlayerInteractEvent.RightClickBlock event) {
        ItemStack itemstack = event.getItemStack();
        BlockPos pos = event.getPos();
        Level world = event.getLevel();
        BlockState state = world.getBlockState(pos);
        if ((event.getFace() != Direction.DOWN && PotionUtils.getPotion(itemstack) == Potions.WATER)) {
            if (state.getBlock() == AetherBlocks.AETHER_DIRT.get()) {
                BlockState newState = DABlocks.AETHER_MUD.get().defaultBlockState();

                world.setBlockAndUpdate(pos, newState);

                Player player = event.getEntity();


                player.awardStat(Stats.ITEM_USED.get(itemstack.getItem()));
                if (!world.isClientSide) {
                    ServerLevel serverlevel = (ServerLevel) world;
                    for (int i = 0; i < 5; ++i) {
                        serverlevel.sendParticles(ParticleTypes.SPLASH, (double) pos.getX() + world.random.nextDouble(), (double) (pos.getY() + 1), (double) pos.getZ() + world.random.nextDouble(), 1, 0.0D, 0.0D, 0.0D, 1.0D);
                    }
                }
                world.playSound(player, pos, SoundEvents.BOTTLE_EMPTY, SoundSource.PLAYERS, 0.5F, 1F);
                event.setCancellationResult(InteractionResult.SUCCESS);
                event.setCanceled(true);
            }
        }
    }
}
