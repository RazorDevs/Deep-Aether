package teamrazor.deepaether.item.dungeon.brass;

import com.aetherteam.aether.item.accessories.pendant.PendantItem;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.Nullable;
import teamrazor.deepaether.entity.GentleWind;
import top.theillusivec4.curios.api.SlotContext;

import java.util.function.Supplier;

public class FloatyScarfItem extends PendantItem {
    public FloatyScarfItem(ResourceLocation pendantLocation, Supplier<? extends SoundEvent> pendantSound, Properties properties) {
        super(pendantLocation, pendantSound, properties);
    }

    @Override
    public void onEquip(SlotContext slotContext, ItemStack prevStack, ItemStack stack) {
        if(slotContext.entity() instanceof Player player) {
            tryAddGentleWind(stack, player);
        }
    }

    @Override
    public void curioTick(SlotContext slotContext, ItemStack stack) {
        if(!(slotContext.entity() instanceof Player player))
            return;

        CompoundTag scarf = stack.getTag();
        if(scarf != null)
            return;

        Entity entity = getGentleWind(stack, slotContext.entity().level());
        if(entity == null || !entity.isAlive()) {
            FloatyScarfItem.tryAddGentleWind(stack, player);
        }
    }

    @Override
    public void onUnequip(SlotContext slotContext, ItemStack newStack, ItemStack stack) {
        tryDiscardGentleWind(stack, slotContext.entity().level());
    }

    public static void tryDiscardGentleWind(@Nullable ItemStack stack, Level level) {
        if(stack == null)
            return;
        Entity entity = getGentleWind(stack, level);
        if(entity != null)
            entity.discard();
    }

    public static void tryAddGentleWind(@Nullable ItemStack stack, Player player) {
        if(stack == null)
            return;

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

    @Nullable
    public GentleWind hasStoredGentleWind(Level level, ItemStack stack) {
        CompoundTag scarf = stack.getTag();
        if(scarf == null)
            return null;

        GentleWind entity = (GentleWind) level.getEntity(scarf.getInt("UUID"));
        if(entity == null || !entity.isAlive()) {
            return null;
        }
        return entity;
    }
}
