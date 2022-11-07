package teamrazor.deepaether.tags;

import com.gildedgames.aether.entity.projectile.PoisonNeedle;
import com.gildedgames.aether.entity.projectile.dart.EnchantedDart;
import com.gildedgames.aether.entity.projectile.dart.GoldenDart;
import com.gildedgames.aether.entity.projectile.dart.PoisonDart;
import com.gildedgames.aether.item.accessories.gloves.GlovesItem;
import com.gildedgames.aether.block.AetherBlocks;
import com.gildedgames.aether.item.AetherItems;
import com.gildedgames.aether.loot.AetherLoot;
import com.gildedgames.aether.AetherTags;
import com.gildedgames.aether.data.resources.AetherDimensions;
import com.gildedgames.aether.AetherConfig;
import com.gildedgames.aether.capability.arrow.PhoenixArrow;
import com.gildedgames.aether.capability.lightning.LightningTracker;
import com.gildedgames.aether.capability.player.AetherPlayer;
import com.gildedgames.aether.loot.AetherLootContexts;
import com.gildedgames.aether.util.EquipmentUtil;
import com.google.common.collect.ImmutableMap;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.storage.loot.LootContext;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.parameters.LootContextParams;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.HitResult;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.common.ToolAction;
import net.minecraftforge.common.ToolActions;
import net.minecraftforge.event.entity.EntityStruckByLightningEvent;
import teamrazor.deepaether.init.DeepAetherModBlocks;

import java.util.List;
import java.util.Map;

public class AbilityHooks {
    public static class AccessoryHooks {
    }

    public static class ArmorHooks {
    }


    public static class ToolHooks {
        public static final Map<Block, Block> STRIPPABLES = (new ImmutableMap.Builder<Block, Block>())
                .put(DeepAetherModBlocks.ROSE_LOG.get(), DeepAetherModBlocks.STRIPPED_ROSE_LOG.get())
                .put(DeepAetherModBlocks.ROSE_WOOD.get(), DeepAetherModBlocks.STRIPPED_ROSE_WOOD.get())
                .put(DeepAetherModBlocks.YAGROOT_LOG.get(), DeepAetherModBlocks.STRIPPED_YAGROOT_LOG.get())
                .put(DeepAetherModBlocks.YAGROOT_WOOD.get(), DeepAetherModBlocks.STRIPPED_YAGROOT_WOOD.get())
                .build();

        public static BlockState setupToolActions(LevelAccessor accessor, BlockPos pos, BlockState old, ToolAction action) {
            Block oldBlock = old.getBlock();
            if (action == ToolActions.AXE_STRIP) {
                if (STRIPPABLES.containsKey(oldBlock)) {
                    return STRIPPABLES.get(oldBlock).withPropertiesOf(old);
                }
            }
            return old;
        }

        public static float handleSkyjadeToolAbility(ItemStack stack, float speed) {
            if (stack.getItem() instanceof SkyjadeTool skyjadeTool) {
                return skyjadeTool.decreaseSpeed(stack, speed);
            }
            return speed;
        }

        public static float increaseToolEffectiveness(Level level, BlockState state, ItemStack stack, float amount) {
            if (AetherConfig.COMMON.tools_debuff.get()) {
                if (level.dimension() == AetherDimensions.AETHER_LEVEL) {
                    if (!stack.isEmpty()
                            && !stack.is(AetherTags.Items.TREATED_AS_AETHER_ITEM)
                            && stack.isCorrectToolForDrops(state)) {
                        amount = (float) Math.pow(amount, -0.2);
                    }
                }
            }
            return amount;
        }
    }
}
