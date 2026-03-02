package io.github.razordevs.deep_aether.item.misc;

import com.aetherteam.aether.data.resources.registries.AetherDimensions;
import com.mojang.datafixers.util.Pair;
import io.github.razordevs.deep_aether.DeepAether;
import io.github.razordevs.deep_aether.DeepAetherConfig;
import io.github.razordevs.deep_aether.item.component.DADataComponentTypes;
import io.github.razordevs.deep_aether.item.component.DungeonTrackerPos;
import io.github.razordevs.deep_aether.util.StructureUtil;
import net.minecraft.ChatFormatting;
import net.minecraft.core.BlockPos;
import net.minecraft.core.GlobalPos;
import net.minecraft.core.Holder;
import net.minecraft.core.HolderSet;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.Style;
import net.minecraft.network.chat.TextColor;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.levelgen.structure.Structure;

import java.util.List;
import java.util.Optional;

public class DungeonCompass extends Item {

    public DungeonCompass(Properties pProperties) {
        super(pProperties);
    }

    @Override
    public void inventoryTick(ItemStack stack, Level level, Entity entity, int i, boolean b) {
        super.inventoryTick(stack, level, entity, i, b);
        if(!stack.has(DADataComponentTypes.DUNGEON_TRACKER_POS)){
            stack.set(DADataComponentTypes.DUNGEON_TRACKER_POS, new DungeonTrackerPos(DeepAether.getResource("brass_dungeon"),Optional.empty(), false));
        }
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level worldIn, Player playerIn, InteractionHand hand) {
        ItemStack stack = playerIn.getItemInHand(hand);
        this.locateStructure(stack, playerIn);
        return super.use(worldIn, playerIn, hand);
    }

    /*
     * Locates nearby structures
     */
    private void locateStructure(ItemStack stack, Player player) {
        if (player.level().isClientSide) return;

        ServerLevel level = (ServerLevel) player.level();

        var dungeon = stack.get(DADataComponentTypes.DUNGEON_TRACKER_POS).dungeon();
        Component dungeonName = Component.translatable(String.format("%s.dungeon.%s", dungeon.getNamespace(), dungeon.getPath()));

        player.displayClientMessage(Component.translatable("deep_aether.structure.locating", dungeonName).withStyle(ChatFormatting.YELLOW), true);
        Registry<Structure> registry = level.registryAccess().registryOrThrow(Registries.STRUCTURE);
        HolderSet<Structure> featureHolderSet = registry.getHolder(dungeon).map(HolderSet::direct).orElse(null);
        if (featureHolderSet != null) {
            Pair<BlockPos, Holder<Structure>> pair = StructureUtil.findNearestMapStructure(level,
                    featureHolderSet, player.blockPosition(), 100, true);
            bindPosition(stack, player, pair, dungeon, dungeonName);
        }
    }

    private void bindPosition(ItemStack stack, Player player, Pair<BlockPos, Holder<Structure>> pair, ResourceLocation dungeon, Component dungeonName) {
        BlockPos structurePos = pair != null ? pair.getFirst() : null;

        if (structurePos == null) {
            stack.set(DADataComponentTypes.DUNGEON_TRACKER_POS, new DungeonTrackerPos(dungeon, Optional.empty(), false));

            int range = DeepAetherConfig.COMMON.dungeon_compass_range.get();
            player.displayClientMessage(Component.translatable("deep_aether.structure.failed", dungeonName, range).withStyle(ChatFormatting.RED), true);
        } else {
            stack.set(DADataComponentTypes.DUNGEON_TRACKER_POS, new DungeonTrackerPos(dungeon, Optional.of(GlobalPos.of(player.level().dimension(), pair.getFirst())), false));

            int distance = player.blockPosition().distManhattan(structurePos);
            player.displayClientMessage(Component.translatable("deep_aether.structure.found", dungeonName, distance).withStyle(ChatFormatting.GREEN), true);
        }

        player.getCooldowns().addCooldown(this, 1000);
    }

    @Override
    public void appendHoverText(ItemStack stack, TooltipContext context, List<Component> tooltip, TooltipFlag tooltipFlag) {
        Level level = context.level();

        if (stack.has(DADataComponentTypes.DUNGEON_TRACKER_POS)) {
            DungeonTrackerPos tracker = stack.get(DADataComponentTypes.DUNGEON_TRACKER_POS);
            Component dungeonName = Component.translatable(String.format("%s.dungeon.%s", tracker.dungeon().getNamespace(), tracker.dungeon().getPath()));

            if (tracker != null && tracker.found()) {
                if (level != null && tracker.target().isPresent() && level.dimension().equals(AetherDimensions.AETHER_LEVEL)) {
                    tooltip.add(Component.translatable("deep_aether.structure.found.tooltip", dungeonName).withStyle(ChatFormatting.GREEN));
                } else {
                    tooltip.add(Component.translatable("deep_aether.structure.wrong_dimension.tooltip", dungeonName).withStyle(ChatFormatting.RED));
                }
            } else {
                tooltip.add(Component.translatable("deep_aether.structure.failed.tooltip", dungeonName).withStyle(ChatFormatting.RED));
            }
        }
    }

    @Override
    public Component getDescription() {
        return Component.translatable("deep_aether.item.disabled_item").withStyle(Style.EMPTY.withItalic(true)
                .withColor(TextColor.parseColor("#d1362b").result().get()));
    }
}
