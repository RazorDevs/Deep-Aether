package teamrazor.deepaether.entity.boats;

import com.gildedgames.aether.entity.AetherEntityTypes;
import com.gildedgames.aether.entity.SkyrootBoatBehavior;
import net.minecraft.core.BlockPos;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.ClientGamePacketListener;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.vehicle.ChestBoat;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.network.NetworkHooks;
import teamrazor.deepaether.init.DeepAetherModEntities;
import teamrazor.deepaether.init.DeepAetherModItems;

import javax.annotation.Nonnull;

public class YagrootChestBoat extends ChestBoat implements SkyrootBoatBehavior {
    public YagrootChestBoat(EntityType<? extends YagrootChestBoat> entityType, Level level) {
        super(entityType, level);
    }

    public YagrootChestBoat(Level level, double x, double y, double z) {
        this(DeepAetherModEntities.YAGROOT_CHEST_BOAT.get(), level);
        this.setPos(x, y, z);
        this.xo = x;
        this.yo = y;
        this.zo = z;
    }

    @Nonnull
    @Override
    public Item getDropItem() {
        return DeepAetherModItems.YAGROOT_CHEST_BOAT.get();
    }

    @Override
    protected void checkFallDamage(double y, boolean onGround, @Nonnull BlockState state, @Nonnull BlockPos pos) {
        this.fall(this, y, onGround);
    }

    @Nonnull
    @Override
    public Packet<ClientGamePacketListener> getAddEntityPacket() {
        return NetworkHooks.getEntitySpawningPacket(this);
    }
}
