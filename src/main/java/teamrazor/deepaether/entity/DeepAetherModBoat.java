package teamrazor.deepaether.entity;


import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.ClientboundAddEntityPacket;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.vehicle.Boat;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.registries.RegistryObject;
import teamrazor.deepaether.DeepAetherMod;
import teamrazor.deepaether.init.DeepAetherModBlocks;
import teamrazor.deepaether.init.DeepAetherModEntities;
import teamrazor.deepaether.init.DeepAetherModItems;

import javax.annotation.Nonnull;


public class DeepAetherModBoat extends Boat {
    private static final EntityDataAccessor<Integer> DATA_ID_TYPE = SynchedEntityData.defineId(DeepAetherModBoat.class, EntityDataSerializers.INT);


    public DeepAetherModBoat(EntityType<? extends Boat> p_38290_, Level p_38291_) {
        super(p_38290_, p_38291_);
        this.blocksBuilding = true;
    }

    public DeepAetherModBoat(Level p_38293_, double p_38294_, double p_38295_, double p_38296_) {
        this(DeepAetherModEntities.BOAT.get(), p_38293_);
        this.setPos(p_38294_, p_38295_, p_38296_);
        this.xo = p_38294_;
        this.yo = p_38295_;
        this.zo = p_38296_;
    }






    @Override
    protected void defineSynchedData() {
        super.defineSynchedData();
        this.entityData.define(DATA_ID_TYPE, 0);
    }
    @Override
    protected void addAdditionalSaveData(CompoundTag p_38359_) {
        p_38359_.putString("Type", this.getDeepAetherModBoatType().getName());
    }
    @Override
    protected void readAdditionalSaveData(CompoundTag p_38338_) {
        if (p_38338_.contains("Type", 8)) {
            this.setDeepAetherModBoatType(DeepAetherModBoat.Type.byName(p_38338_.getString("Type")));
        }
    }

    public DeepAetherModBoat.Type getDeepAetherModBoatType() {
        return DeepAetherModBoat.Type.byId(this.entityData.get(DATA_ID_TYPE));
    }


    public void setDeepAetherModBoatType(DeepAetherModBoat.Type type) {
        this.entityData.set(DATA_ID_TYPE, type.ordinal());
    }


    @Nonnull
    @Override
    public Item getDropItem() {
        switch (this.getDeepAetherModBoatType()) {
            case YAGROOT:
            default:
                return DeepAetherModItems.YAGROOT_BOAT.get();
            case ROSE:
                return DeepAetherModItems.ROSE_BOAT.get();
            case CRUDEROOT:
                return DeepAetherModItems.CRUDEROOT_BOAT.get();
        }
    }

    @Override
    public Packet<?> getAddEntityPacket() {
        return new ClientboundAddEntityPacket(this);
    }


    public enum Type {
        YAGROOT(DeepAetherModBlocks.YAGROOT_PLANKS, "yagroot"),
        ROSE(DeepAetherModBlocks.ROSE_PLANKS, "rose"),
        CRUDEROOT(DeepAetherModBlocks.CRUDEROOT_PLANKS, "cruderoot");



        private final String name;
        private final RegistryObject<Block> planks;

        Type(RegistryObject<Block> p_38427_, String p_38428_) {
            this.name = p_38428_;
            this.planks = p_38427_;
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

        public RegistryObject<Block> getPlanks() {
            return this.planks;
        }

        public String toString() {
            return this.name;
        }


        public static DeepAetherModBoat.Type byId(int p_38431_) {
            DeepAetherModBoat.Type[] aboat$type = values();
            if (p_38431_ < 0 || p_38431_ >= aboat$type.length) {
                p_38431_ = 0;
            }

            return aboat$type[p_38431_];
        }

        public static DeepAetherModBoat.Type byName(String p_38433_) {
            DeepAetherModBoat.Type[] aboat$type = values();

            for(int i = 0; i < aboat$type.length; ++i) {
                if (aboat$type[i].getName().equals(p_38433_)) {
                    return aboat$type[i];
                }
            }
            return aboat$type[0];
        }
    }
}

