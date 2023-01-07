package teamrazor.deepaether.item;

import net.minecraft.core.BlockPos;
import net.minecraft.stats.Stats;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntitySelector;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.vehicle.Boat;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.ClipContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.properties.WoodType;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.HitResult;
import net.minecraft.world.phys.Vec3;
import teamrazor.deepaether.entity.boats.*;
import teamrazor.deepaether.init.DeepAetherModWoodTypes;

import java.util.List;
import java.util.function.Predicate;
public class DeepAetherModBoatItem extends Item {
    private static final Predicate<Entity> ENTITY_PREDICATE = EntitySelector.NO_SPECTATORS.and(Entity::isPickable);
    private final boolean hasChest;
    private final WoodType woodType;

    public DeepAetherModBoatItem(boolean hasChest, Properties properties, WoodType woodType) {
        super(properties);
        this.hasChest = hasChest;
        this.woodType = woodType;
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand hand) {
        ItemStack heldStack = player.getItemInHand(hand);
        HitResult hitResult = getPlayerPOVHitResult(level, player, ClipContext.Fluid.ANY);
        if (hitResult.getType() == HitResult.Type.MISS) {
            return InteractionResultHolder.pass(heldStack);
        } else {
            Vec3 viewVector = player.getViewVector(1.0F);
            List<Entity> list = level.getEntities(player, player.getBoundingBox().expandTowards(viewVector.scale(5.0D)).inflate(1.0D), ENTITY_PREDICATE);
            if (!list.isEmpty()) {
                Vec3 eyePosition = player.getEyePosition();
                for(Entity entity : list) {
                    AABB aabb = entity.getBoundingBox().inflate(entity.getPickRadius());
                    if (aabb.contains(eyePosition)) {
                        return InteractionResultHolder.pass(heldStack);
                    }
                }
            }
            if (hitResult.getType() == HitResult.Type.BLOCK) {
                Boat boat = this.getBoat(level, hitResult);
                boat.setYRot(player.getYRot());
                if (!level.noCollision(boat, boat.getBoundingBox())) {
                    return InteractionResultHolder.fail(heldStack);
                } else {
                    if (!level.isClientSide()) {
                        level.addFreshEntity(boat);
                        level.gameEvent(player, GameEvent.ENTITY_PLACE, new BlockPos(hitResult.getLocation()));
                        if (!player.getAbilities().instabuild) {
                            heldStack.shrink(1);
                        }
                    }
                    player.awardStat(Stats.ITEM_USED.get(this));
                    return InteractionResultHolder.sidedSuccess(heldStack, level.isClientSide());
                }
            } else {
                return InteractionResultHolder.pass(heldStack);
            }
        }
    }

    private Boat getBoat(Level level, HitResult hitResult) {
        if(woodType == DeepAetherModWoodTypes.ROSEROOT)
            return this.hasChest ? new RoserootChestBoat(level, hitResult.getLocation().x, hitResult.getLocation().y, hitResult.getLocation().z) : new RoserootBoat(level, hitResult.getLocation().x, hitResult.getLocation().y, hitResult.getLocation().z);
        if(woodType == DeepAetherModWoodTypes.YAGROOT)
            return this.hasChest ? new YagrootChestBoat(level, hitResult.getLocation().x, hitResult.getLocation().y, hitResult.getLocation().z) : new YagrootBoat(level, hitResult.getLocation().x, hitResult.getLocation().y, hitResult.getLocation().z);

        return this.hasChest ? new CruderootChestBoat(level, hitResult.getLocation().x, hitResult.getLocation().y, hitResult.getLocation().z) : new CruderootBoat(level, hitResult.getLocation().x, hitResult.getLocation().y, hitResult.getLocation().z);
    }
}
