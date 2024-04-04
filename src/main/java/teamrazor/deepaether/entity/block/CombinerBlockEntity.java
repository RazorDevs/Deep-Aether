package teamrazor.deepaether.entity.block;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.NonNullList;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.world.ContainerHelper;
import net.minecraft.world.Containers;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.WorldlyContainer;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.ContainerData;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BaseContainerBlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.common.brewing.BrewingRecipeRegistry;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.event.ForgeEventFactory;
import net.minecraftforge.items.IItemHandler;
import org.jetbrains.annotations.Nullable;

import java.util.Iterator;

public class CombinerBlockEntity extends BaseContainerBlockEntity implements WorldlyContainer, MenuProvider {
    private static final int INPUT1_SLOT = 0;
    private static final int INPUT2_SLOT = 1;
    private static final int INPUT3_SLOT = 2;
    private static final int OUTPUT_SLOT = 3;
    private static final int[] SLOTS_FOR_UP = new int[]{3};
    private static final int[] SLOTS_FOR_DOWN = new int[]{0, 1, 2, 3};
    private static final int[] SLOTS_FOR_SIDES = new int[]{0, 1, 2, 4};
    public static final int DATA_COMBINE_TIME = 0;
    public static final int NUM_DATA_VALUES = 2;
    public NonNullList<ItemStack> items;
    int combineTime;
    protected final ContainerData dataAccess;
    LazyOptional<? extends IItemHandler>[] handlers;
    public CombinerBlockEntity(BlockEntityType<?> blockEntityType, BlockPos blockPos, BlockState state) {
        super(blockEntityType, blockPos, state);
        this.items = NonNullList.withSize(5, ItemStack.EMPTY);
        this.dataAccess = new ContainerData() {
            public int get(int i) {
                switch (i) {
                    case 0:
                        return combineTime;
                    default:
                        return 0;
                }
            }

            public void set(int index, int value) {
                switch (index) {
                    case 0:
                        combineTime = value;
                        break;
                }

            }

            public int getCount() {
                return 1;
            }
        };
    }

    @Override
    public Component getDisplayName() {
        return null;
    }

    @Override
    protected Component getDefaultName() {
        return null;
    }

    @Nullable
    @Override
    public AbstractContainerMenu createMenu(int i, Inventory inventory, Player player) {
        return null;
    }

    @Override
    protected AbstractContainerMenu createMenu(int i, Inventory inventory) {
        return null;
    }

    @Override
    public int[] getSlotsForFace(Direction direction) {
        return new int[0];
    }

    @Override
    public boolean canPlaceItemThroughFace(int i, ItemStack itemStack, @Nullable Direction direction) {
        return false;
    }

    @Override
    public boolean canTakeItemThroughFace(int i, ItemStack itemStack, Direction direction) {
        return false;
    }

    public int getContainerSize() {
        return this.items.size();
    }

    public boolean isEmpty() {
        Iterator var1 = this.items.iterator();

        ItemStack itemstack;
        do {
            if (!var1.hasNext()) {
                return true;
            }

            itemstack = (ItemStack)var1.next();
        } while(itemstack.isEmpty());

        return false;
    }

    public static void serverTick(Level level, BlockPos blockPos, BlockState state, CombinerBlockEntity blockEntity) {
        ItemStack itemstack = (ItemStack)blockEntity.items.get(4);

        boolean flag = isBrewable(blockEntity.items);
        boolean flag1 = blockEntity.combineTime > 0;
        ItemStack itemstack1 = (ItemStack)blockEntity.items.get(3);
        if (flag1) {
            --blockEntity.combineTime;
            boolean flag2 = blockEntity.combineTime == 0;
            if (flag2 && flag) {
                doBrew(level, blockPos, blockEntity.items);
                setChanged(level, blockPos, state);
            } else if (!flag || !itemstack1.is(blockEntity.ingredient)) {
                blockEntity.combineTime = 0;
                setChanged(level, blockPos, state);
            }
        } else {
            blockEntity.combineTime = 400;
            blockEntity.ingredient = itemstack1.getItem();
            setChanged(level, blockPos, state);
        }
    }

    private static void doBrew(Level p_155291_, BlockPos p_155292_, NonNullList<ItemStack> p_155293_) {
        if (!ForgeEventFactory.onPotionAttemptBrew(p_155293_)) {
            ItemStack itemstack = (ItemStack)p_155293_.get(3);
            BrewingRecipeRegistry.brewPotions(p_155293_, itemstack, SLOTS_FOR_SIDES);
            ForgeEventFactory.onPotionBrewed(p_155293_);
            if (itemstack.hasCraftingRemainingItem()) {
                ItemStack itemstack1 = itemstack.getCraftingRemainingItem();
                itemstack.shrink(1);
                if (itemstack.isEmpty()) {
                    itemstack = itemstack1;
                } else {
                    Containers.dropItemStack(p_155291_, (double)p_155292_.getX(), (double)p_155292_.getY(), (double)p_155292_.getZ(), itemstack1);
                }
            } else {
                itemstack.shrink(1);
            }

            p_155293_.set(3, itemstack);
            p_155291_.levelEvent(1035, p_155292_, 0);
        }
    }

    public void load(CompoundTag compoundTag) {
        super.load(compoundTag);
        this.items = NonNullList.withSize(this.getContainerSize(), ItemStack.EMPTY);
        ContainerHelper.loadAllItems(compoundTag, this.items);
        this.combineTime = compoundTag.getShort("CombineTime");
    }

    protected void saveAdditional(CompoundTag compoundTag) {
        super.saveAdditional(compoundTag);
        compoundTag.putShort("CombineTime", (short)this.combineTime);
        ContainerHelper.saveAllItems(compoundTag, this.items);
    }

    @Override
    public ItemStack getItem(int i) {
        return null;
    }

    @Override
    public ItemStack removeItem(int i, int i1) {
        return null;
    }

    @Override
    public ItemStack removeItemNoUpdate(int i) {
        return null;
    }

    @Override
    public void setItem(int i, ItemStack itemStack) {

    }

    @Override
    public boolean stillValid(Player player) {
        return false;
    }

    @Override
    public void clearContent() {

    }
}
