package teamrazor.deepaether.entity;

import com.aetherteam.aether.entity.SkyrootBoatBehavior;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.ClientGamePacketListener;
import net.minecraft.network.protocol.game.ClientboundAddEntityPacket;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.vehicle.Boat;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.Level;
import teamrazor.deepaether.DeepAetherMod;
import teamrazor.deepaether.init.DAEntities;
import teamrazor.deepaether.init.DAItems;

import java.util.function.Supplier;

public class DABoatEntity extends Boat implements SkyrootBoatBehavior {
    private static final EntityDataAccessor<Integer> WOOD_TYPE = SynchedEntityData.defineId(DABoatEntity.class, EntityDataSerializers.INT);

    public DABoatEntity(EntityType<? extends Boat> type, Level level) {
        super(type, level);
        this.blocksBuilding = true;
    }

    public DABoatEntity(Level level, double x, double y, double z) {
        this(DAEntities.BOAT.get(), level);
        this.setPos(x, y, z);
        this.xo = x;
        this.yo = y;
        this.zo = z;
    }

    @Override
    protected void defineSynchedData() {
        super.defineSynchedData();
        this.entityData.define(WOOD_TYPE, 0);
    }

    @Override
    protected void readAdditionalSaveData(CompoundTag pCompound) {
        if (pCompound.contains("Type", 8)) {
            this.setWoodType(Type.byName(pCompound.getString("Type")));
        }
    }

    @Override
    protected void addAdditionalSaveData(CompoundTag pCompound) {
        super.addAdditionalSaveData(pCompound);
        pCompound.putString("Type", this.getWoodType().getName());
    }

    public Type getWoodType() {
        return Type.byId(this.entityData.get(WOOD_TYPE));
    }

    public void setWoodType(Type type) {
        this.entityData.set(WOOD_TYPE, type.ordinal());
    }

    @Override
    public Item getDropItem() {
        return this.getWoodType().getItem().get();
    }

    @Override
    public Packet<ClientGamePacketListener> getAddEntityPacket() {
        return new ClientboundAddEntityPacket(this);
    }

    public enum Type {
        ROSEROOT("roseroot", () -> DAItems.ROSEROOT_BOAT.get(), () -> DAItems.ROSEROOT_CHEST_BOAT.get()),
        CONBERRY("amberroot", () -> DAItems.CONBERRY_BOAT.get(), () -> DAItems.CONBERRY_CHEST_BOAT.get()),
        CRUDEROOT("cruderoot", () -> DAItems.CRUDEROOT_BOAT.get(), () -> DAItems.CRUDEROOT_CHEST_BOAT.get()),
        YAGROOT("yagroot", () -> DAItems.YAGROOT_BOAT.get(), () -> DAItems.YAGROOT_CHEST_BOAT.get());

        private final String name;
        private final Supplier<Item> item;
        private final Supplier<Item> chestItem;

        Type(String name, Supplier<Item> boatItem, Supplier<Item> chestBoatItem) {
            this.name = name;
            this.item = boatItem;
            this.chestItem = chestBoatItem;
        }

        public ResourceLocation getTexture(boolean hasChest) {
            if (hasChest) {
                return new ResourceLocation(DeepAetherMod.MODID, "textures/entity/chest_boat/" + name + ".png");
            }
            return new ResourceLocation(DeepAetherMod.MODID, "textures/entity/boat/" + name + ".png");
        }

        public String getModelLocation() {
            return "boat/" + name;
        }

        public String getChestModelLocation() {
            return "chest_boat/" + name;
        }

        public String getName() {
            return this.name;
        }

        public Supplier<Item> getItem() {
            return item;
        }

        public Supplier<Item> getChestItem() {
            return chestItem;
        }

        public static Type byId(int id) {
            Type[] values = values();
            if (id < 0 || id >= values.length) {
                id = 0;
            }

            return values[id];
        }

        public static Type byName(String name) {
            Type[] values = values();

            for(int i = 0; i < values.length; ++i) {
                if (values[i].getName().equals(name)) {
                    return values[i];
                }
            }

            return values[0];
        }
    }
}