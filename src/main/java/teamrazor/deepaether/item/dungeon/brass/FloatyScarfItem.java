package teamrazor.deepaether.item.dungeon.brass;

import com.aetherteam.aether.item.accessories.pendant.PendantItem;
import com.aetherteam.nitrogen.capability.INBTSynchable;
import net.minecraft.ChatFormatting;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.network.chat.Style;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.LayeredCauldronBlock;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import teamrazor.deepaether.entity.GentleWind;
import teamrazor.deepaether.networking.DeepAetherPlayer;
import top.theillusivec4.curios.api.SlotContext;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.function.Supplier;

public class FloatyScarfItem extends PendantItem {
    public FloatyScarfItem(ResourceLocation pendantLocation, Supplier<? extends SoundEvent> pendantSound, Properties properties) {
        super(pendantLocation, pendantSound, properties);
    }

    @Override
    public void onEquip(SlotContext slotContext, ItemStack prevStack, ItemStack stack) {
        if(!stack.isEmpty()) {
            try {
                if(!slotContext.entity().level().isClientSide()) {
                    DeepAetherPlayer.get((Player) slotContext.entity()).ifPresent(aetherPlayer ->
                    {
                        aetherPlayer.setSynched(INBTSynchable.Direction.CLIENT, "setFloatyScarfWrappedAroundNeck", true);

                        CompoundTag tag = stack.getOrCreateTag();
                        if (tag.contains("Colors")) {

                            int[] colors = tag.getIntArray("Colors");
                            aetherPlayer.setSynched(INBTSynchable.Direction.CLIENT, "setFloatyScarfColor0", colors[0]);
                            aetherPlayer.setSynched(INBTSynchable.Direction.CLIENT, "setFloatyScarfColor1", colors[1]);
                            aetherPlayer.setSynched(INBTSynchable.Direction.CLIENT, "setFloatyScarfColor2", colors[2]);
                            aetherPlayer.setSynched(INBTSynchable.Direction.CLIENT, "setFloatyScarfColor3", colors[3]);
                            aetherPlayer.setSynched(INBTSynchable.Direction.CLIENT, "setFloatyScarfColor4", colors[4]);

                        }
                    });
                }
                addGentleWind(stack, (Player) slotContext.entity());
            } catch (ClassCastException ignored) {}
        }
    }

    @Override
    public void curioTick(SlotContext slotContext, ItemStack stack) {
        if(stack.isEmpty())
            return;

        if(!(slotContext.entity() instanceof Player player))
            return;

        CompoundTag scarf = stack.getTag();
        if(scarf != null)
            return;

        Entity entity = getGentleWind(stack, slotContext.entity().level());
        if(entity == null || !entity.isAlive()) {
            FloatyScarfItem.addGentleWind(stack, player);
        }
    }

    @Override
    public void onUnequip(SlotContext slotContext, ItemStack newStack, ItemStack stack) {
        if(!stack.isEmpty()) {
            try {
                if(!slotContext.entity().level().isClientSide()) {
                    Optional<DeepAetherPlayer> deepAetherPlayer = DeepAetherPlayer.get((Player) slotContext.entity()).resolve();
                    deepAetherPlayer.ifPresent(aetherPlayer -> aetherPlayer.setSynched(INBTSynchable.Direction.CLIENT, "setFloatyScarfWrappedAroundNeck", false));
                }
                } catch (ClassCastException ignored) {}
            discardGentleWind(stack, slotContext.entity().level());
        }
    }

    public static void discardGentleWind(@NotNull ItemStack stack, Level level) {
        Entity entity = getGentleWind(stack, level);
        if(entity != null)
            entity.discard();
    }

    public static void addGentleWind(@NotNull ItemStack stack, Player player) {
        CompoundTag scarf = stack.getOrCreateTag();
        GentleWind eots;
        if (scarf.contains("Colors")) {
            eots = new GentleWind(player.level(), player, scarf.getIntArray("Colors"));
        } else {
            eots = new GentleWind(player.level(), player);
        }

        MutableComponent mutablecomponent = Component.empty().append(stack.getHoverName());
        if (stack.hasCustomHoverName()) {
            eots.setCustomName(mutablecomponent);
        }

        scarf.putInt("UUID", eots.getId());
        stack.setTag(scarf);
    }

    public static Entity getGentleWind(ItemStack stack, Level level){
        CompoundTag scarf = stack.getTag();
        return scarf != null ? level.getEntity(scarf.getInt("UUID")) : null;
    }

    @Override
    public void appendHoverText(ItemStack stack, @Nullable Level level, List<Component> tooltipComponents, TooltipFlag tooltipFlag) {
        CompoundTag tag = stack.getOrCreateTag();
        if (tag.contains("Colors")) {
            int[] colors = tag.getIntArray("Colors");
            this.chatFormat(tooltipComponents, colors[0], 0, tag);
            this.chatFormat(tooltipComponents, colors[1], 1, tag);
            this.chatFormat(tooltipComponents, colors[2], 2, tag);
            this.chatFormat(tooltipComponents, colors[3], 3, tag);
            this.chatFormat(tooltipComponents, colors[4], 4, tag);
        }
    }

    @Override
    public InteractionResult useOn(UseOnContext context) {
        BlockState state = context.getLevel().getBlockState(context.getClickedPos());
        if(state.is(Blocks.WATER_CAULDRON)) {
            if (context.getPlayer() != null && context.getPlayer().isShiftKeyDown())
                return InteractionResult.PASS;

            CompoundTag tag = context.getItemInHand().getOrCreateTag();

            if(tag.contains("Colors")) {
                int[] colors = tag.getIntArray("Colors");

                if(Arrays.stream(colors).anyMatch( value -> value != -1)) {
                    LayeredCauldronBlock.lowerFillLevel(state, context.getLevel(), context.getClickedPos());
                    tag.putIntArray("Colors", new int[]{-1, -1, -1, -1, -1});
                    return InteractionResult.SUCCESS;
                }
            }
        }
        return InteractionResult.PASS;
    }

    private void chatFormat(List<Component> tooltipComponents, int color, int mod, CompoundTag tag) {
        if (tag.getInt("currentModification") == mod) {
            tooltipComponents.add(Component.literal("Color").withStyle(Style.EMPTY.withColor(color)).withStyle(ChatFormatting.ITALIC));
        } else {
            tooltipComponents.add(Component.literal("Color").withStyle(Style.EMPTY.withColor(color)));
        }
    }
}
