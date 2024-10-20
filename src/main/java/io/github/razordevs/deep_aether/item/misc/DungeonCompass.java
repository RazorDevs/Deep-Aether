package io.github.razordevs.deep_aether.item.misc;

import com.mojang.datafixers.util.Pair;
import io.github.razordevs.deep_aether.item.component.DADataComponentTypes;
import io.github.razordevs.deep_aether.item.component.DungeonTracker;
import io.github.razordevs.deep_aether.util.StructureUtil;
import net.minecraft.ChatFormatting;
import net.minecraft.core.*;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.levelgen.structure.Structure;

import java.util.List;
import java.util.Optional;

public class DungeonCompass extends Item {
    private final ResourceKey<Structure> dungeon;
    private final String dungeonName;

    public DungeonCompass(Properties pProperties, ResourceKey<Structure> dungeon, String dungeonName) {
        super(pProperties);
        this.dungeon = dungeon;
        this.dungeonName = dungeonName;

    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level worldIn, Player playerIn, InteractionHand hand) {
        ItemStack stack = playerIn.getItemInHand(hand);
        locateStructure(stack, playerIn);
        return super.use(worldIn, playerIn, hand);
    }

    /*
     * Locates nearby structures
     */
    private void locateStructure(ItemStack stack, Player player) {
        if (!player.level().isClientSide) {
            ServerLevel level = (ServerLevel) player.level();
            player.sendSystemMessage(Component.translatable("deep_aether.structure.locating", dungeon).withStyle(ChatFormatting.YELLOW));
            Registry<Structure> registry = level.registryAccess().registryOrThrow(Registries.STRUCTURE);
            HolderSet<Structure> featureHolderSet = registry.getHolder(dungeon).map(HolderSet::direct).orElse(null);
            if (featureHolderSet != null) {
                Pair<BlockPos, Holder<Structure>> pair = StructureUtil.findNearestMapStructure(level,
                        featureHolderSet, player.blockPosition(), 100, true);
                bindPosition(stack, player, level, pair);
            }
        }
    }

    private void bindPosition(ItemStack stack, Player player, Level level, Pair<BlockPos, Holder<Structure>> pair) {
        BlockPos structurePos = pair != null ? pair.getFirst() : null;
        if (structurePos == null) {
            stack.set(DADataComponentTypes.DUNGEON_TRACKER, new DungeonTracker(Optional.empty(), false));

            int range = 5000;
            player.sendSystemMessage(Component.translatable("deep_aether.structure.failed", dungeonName, range).withStyle(ChatFormatting.RED));
        } else {
            stack.set(DADataComponentTypes.DUNGEON_TRACKER, new DungeonTracker(Optional.of(GlobalPos.of(level.dimension(), pair.getFirst())), false));

            int distance = player.blockPosition().distManhattan(structurePos);
            player.sendSystemMessage(Component.translatable("deep_aether.structure.found", dungeonName, distance).withStyle(ChatFormatting.GREEN));
        }

        player.getCooldowns().addCooldown(this, 1000);
    }

    @Override
    public void appendHoverText(ItemStack stack, TooltipContext context, List<Component> tooltip, TooltipFlag tooltipFlag) {
        Level level = context.level();

        if (stack.has(DADataComponentTypes.DUNGEON_TRACKER)) {
            DungeonTracker tracker = stack.get(DADataComponentTypes.DUNGEON_TRACKER);
            if (tracker != null && tracker.found()) {
                if (level != null && tracker.target().isPresent() && tracker.target().get().dimension().equals(level.dimension())) {
                    tooltip.add(Component.translatable("deep_aether.structure.found.tooltip", dungeonName).withStyle(ChatFormatting.GREEN));
                } else {
                    tooltip.add(Component.translatable("deep_aether.structure.wrong_dimension.tooltip", dungeonName).withStyle(ChatFormatting.RED));
                }
            } else {
                tooltip.add(Component.translatable("deep_aether.structure.failed.tooltip", dungeonName).withStyle(ChatFormatting.RED));
            }
        } else {
            tooltip.add(Component.translatable("deep_aether.structure.unset.tooltip").withStyle(ChatFormatting.GOLD));
        }
    }
}
