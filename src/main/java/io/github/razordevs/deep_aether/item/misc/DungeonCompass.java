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
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
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
        if(!stack.has(DADataComponentTypes.DUNGEON)){
            stack.set(DADataComponentTypes.DUNGEON, DeepAether.getResource("brass_dungeon"));
        }
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand hand) {
        ItemStack stack = player.getItemInHand(hand);
        if(!level.dimension().equals(AetherDimensions.AETHER_LEVEL)){
            player.displayClientMessage(Component.translatable("deep_aether.structure.wrong_dimension.tooltip").withStyle(ChatFormatting.RED).withStyle(ChatFormatting.ITALIC), true);
            return InteractionResultHolder.fail(stack);
        }
        this.locateStructure(stack, player);
        return super.use(level, player, hand);
    }

    /*
     * Locates nearby structures
     */
    private void locateStructure(ItemStack stack, Player player) {
        if (player.level().isClientSide) return;

        ServerLevel level = (ServerLevel) player.level();
        if (!stack.has(DADataComponentTypes.DUNGEON)) {
            stack.set(DADataComponentTypes.DUNGEON, DeepAether.getResource("brass_dungeon"));
        }

        var dungeon = stack.get(DADataComponentTypes.DUNGEON);
        if (dungeon == null) return;

        var dungeonName = StructureUtil.getDungeonAetherLocation(dungeon);

        player.displayClientMessage(Component.translatable("deep_aether.structure.locating", dungeonName).withStyle(ChatFormatting.YELLOW), true);
        Registry<Structure> registry = level.registryAccess().registryOrThrow(Registries.STRUCTURE);
        HolderSet<Structure> featureHolderSet = registry.getHolder(dungeon).map(HolderSet::direct).orElse(null);
        if (featureHolderSet != null) {
            Pair<BlockPos, Holder<Structure>> pair = StructureUtil.findNearestMapStructure(level,
                    featureHolderSet, player.blockPosition(), 100, false);
            bindPosition(stack, player, pair, dungeonName);
        }
    }

    private void bindPosition(ItemStack stack, Player player, Pair<BlockPos, Holder<Structure>> pair, Component dungeonName) {
        BlockPos structurePos = pair != null ? pair.getFirst() : null;

        if (structurePos == null) {
            stack.remove(DADataComponentTypes.DUNGEON_POS);

            int range = DeepAetherConfig.COMMON.dungeon_compass_range.get();
            player.displayClientMessage(Component.translatable("deep_aether.structure.failed", dungeonName, range).withStyle(ChatFormatting.RED), true);
        } else {
            stack.set(DADataComponentTypes.DUNGEON_POS, structurePos);

            int distance = player.blockPosition().distManhattan(structurePos);
            player.displayClientMessage(Component.translatable("deep_aether.structure.found", dungeonName, distance).withStyle(ChatFormatting.GREEN), true);
            player.playNotifySound(SoundEvents.LODESTONE_COMPASS_LOCK, SoundSource.PLAYERS, 1f, 1f);
        }

        player.getCooldowns().addCooldown(this, 1000);
    }

    @Override
    public void appendHoverText(ItemStack stack, TooltipContext context, List<Component> tooltip, TooltipFlag tooltipFlag) {
        Level level = context.level();

        if (stack.has(DADataComponentTypes.DUNGEON)) {
            final ResourceLocation structureLocation = stack.get(DADataComponentTypes.DUNGEON);
            Component dungeonName = StructureUtil.getDungeonAetherLocation(structureLocation);
            tooltip.add(dungeonName.copy().withStyle(ChatFormatting.GRAY).withStyle(ChatFormatting.ITALIC));

            if(!level.dimension().equals(AetherDimensions.AETHER_LEVEL)){
                tooltip.add(Component.translatable("deep_aether.structure.wrong_dimension.tooltip").withStyle(ChatFormatting.RED).withStyle(ChatFormatting.ITALIC));
            }

            if (stack.has(DADataComponentTypes.DUNGEON_POS)) {
                var tracker = stack.get(DADataComponentTypes.DUNGEON_POS);
                if (level != null && tracker != null && level.dimension().equals(AetherDimensions.AETHER_LEVEL)) {
                    tooltip.add(Component.translatable("deep_aether.structure.found.tooltip", tracker.getX(), tracker.getZ()).withStyle(ChatFormatting.GREEN).withStyle(ChatFormatting.ITALIC));
                }
            }
        }
    }
}
