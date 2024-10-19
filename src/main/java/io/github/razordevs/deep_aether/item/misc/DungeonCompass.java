package io.github.razordevs.deep_aether.item.misc;

import com.aetherteam.aether.Aether;
import com.mojang.datafixers.util.Pair;
import io.github.razordevs.deep_aether.util.StructureUtil;
import net.minecraft.ChatFormatting;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Holder;
import net.minecraft.core.HolderSet;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.Registries;
import net.minecraft.nbt.CompoundTag;
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

import javax.annotation.Nullable;
import java.util.List;

public class DungeonCompass extends Item {
    private String location;
    private String dungeonName;

    public DungeonCompass(Properties pProperties, String location, String dungeonName) {
        super(pProperties);
        this.location = Aether.MODID + ":" + location;
        this.dungeonName = dungeonName;
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level worldIn, Player playerIn, InteractionHand hand) {
        ItemStack stack = playerIn.getItemInHand(hand);
        CompoundTag tag = stack.getOrCreateTag();
        tag.putString("deep_aether:structureName", location);
        stack.setTag(tag);
        locateStructure(stack, playerIn);

        return super.use(worldIn, playerIn, hand);
    }

    /*
     * Locates nearby structures
     */
    private void locateStructure(ItemStack stack, Player player) {
        if (!player.level().isClientSide) {
            if (stack.hasTag() && stack.getTag().contains("deep_aether:structureName")) {
                ServerLevel level = (ServerLevel) player.level();
                CompoundTag tag = stack.getTag();

                String boundStructure = tag.getString("deep_aether:structureName");
                ResourceLocation structureLocation = ResourceLocation.tryParse(boundStructure);

                if (structureLocation != null) {
                    player.sendSystemMessage(Component.translatable("deep_aether.structure.locating", dungeonName).withStyle(ChatFormatting.YELLOW));
                    Registry<Structure> registry = level.registryAccess().registryOrThrow(Registries.STRUCTURE);
                    ResourceKey<Structure> structureKey = ResourceKey.create(Registries.STRUCTURE, structureLocation);
                    HolderSet<Structure> featureHolderSet = registry.getHolder(structureKey).map((holders) -> HolderSet.direct(holders)).orElse(null);
                    if (featureHolderSet != null) {
                            Pair<BlockPos, Holder<Structure>> pair = StructureUtil.findNearestMapStructure(level,
                                    featureHolderSet, player.blockPosition(), 100, true);
                            bindPosition(stack, tag, dungeonName, player, level, pair);
                    }
                } else {
                    player.sendSystemMessage(Component.translatable("deep_aether.locate.fail").withStyle(ChatFormatting.RED));
                }
            } else {
                player.sendSystemMessage(Component.translatable("deep_aether.structure.unset.tooltip").withStyle(ChatFormatting.YELLOW));
            }
        }
    }

    private void bindPosition(ItemStack stack, CompoundTag tag, String boundStructure, Player player, Level level, Pair<BlockPos, Holder<Structure>> pair) {
        BlockPos structurePos = pair != null ? pair.getFirst() : null;
        if (structurePos == null) {
            tag.putBoolean("deep_aether:structureFound", false);
            tag.remove("deep_aether:structureLocation");
            int range = 5000;
            player.sendSystemMessage(Component.translatable("deep_aether.structure.failed", boundStructure, range).withStyle(ChatFormatting.RED));
        } else {
            tag.putBoolean("deep_aether:structureFound", true);
            tag.putLong("deep_aether:structureLocation", structurePos.asLong());
            int distance = player.blockPosition().distManhattan(structurePos);
            player.sendSystemMessage(Component.translatable("deep_aether.structure.found", boundStructure, distance).withStyle(ChatFormatting.GREEN));
        }

        stack.setTag(tag);
        player.getCooldowns().addCooldown(this, 1000);
    }

    @Override
    public void appendHoverText(ItemStack stack, @Nullable Level level, List<Component> tooltip, TooltipFlag flagIn) {
        if (stack.hasTag()) {
            CompoundTag tag = stack.getTag();
            final boolean structureFound = tag.getBoolean("deep_aether:structureFound");
            if (structureFound) {
                if (level != null && level.dimension().location().toString().equals("aether:the_aether")) {
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
