package teamrazor.deepaether.item.dungeon.brass;

import com.aetherteam.aether.item.accessories.pendant.PendantItem;
import com.aetherteam.nitrogen.capability.INBTSynchable;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.NotNull;
import teamrazor.deepaether.entity.GentleWind;
import teamrazor.deepaether.networking.DeepAetherPlayer;
import top.theillusivec4.curios.api.SlotContext;

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
                    Optional<DeepAetherPlayer> deepAetherPlayer = DeepAetherPlayer.get((Player) slotContext.entity()).resolve();
                    deepAetherPlayer.ifPresent(aetherPlayer -> aetherPlayer.setSynched(INBTSynchable.Direction.CLIENT, "setFloatyScarfWrappedAroundNeck", false));
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

        GentleWind eots = new GentleWind(player.level(), player);

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
}
