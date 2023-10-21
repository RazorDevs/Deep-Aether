package teamrazor.deepaether.item.misc;

import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.Style;
import net.minecraft.network.chat.TextColor;
import net.minecraft.stats.Stats;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntitySelector;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.BoatItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.ClipContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.HitResult;
import net.minecraft.world.phys.Vec3;
import teamrazor.deepaether.entity.DABoatEntity;
import teamrazor.deepaether.entity.DAChestBoatEntity;

import java.util.List;
import java.util.function.Predicate;

public interface DACompatItem {


    default Component getDescription() {
        return Component.translatable("deep_aether.item.disabled_item").withStyle(Style.EMPTY.withItalic(true).withColor(TextColor.parseColor("#d1362b")));
    }
}
