package io.github.razordevs.deep_aether.entity.block;

import io.github.razordevs.deep_aether.init.DABlockEntityTypes;
import io.github.razordevs.deep_aether.init.DARecipeBookTypes;
import io.github.razordevs.deep_aether.recipe.DARecipeTypes;
import io.github.razordevs.deep_aether.recipe.combiner.CombinderRecipeInput;
import io.github.razordevs.deep_aether.recipe.combiner.CombinerRecipe;
import io.github.razordevs.deep_aether.screen.CombinerMenu;
import net.minecraft.core.BlockPos;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.NonNullList;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.world.Container;
import net.minecraft.world.Containers;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.ContainerData;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.RecipeHolder;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.neoforged.neoforge.items.ItemStackHandler;
import org.jetbrains.annotations.Nullable;

import java.util.Optional;

public class CombinerBlockEntity extends BlockEntity implements MenuProvider, Container {
    private final ItemStackHandler itemHandler = new ItemStackHandler(4);

    private static final int FIRST_SLOT = 0;
    private static final int SECOND_SLOT = 1;
    private static final int THIRD_SLOT = 2;
    private static final int OUTPUT_SLOT = 3;

    protected final ContainerData data;
    private int progress = 0;
    private int maxProgress = 78;

    public CombinerBlockEntity(BlockPos pPos, BlockState pBlockState) {
            super(DABlockEntityTypes.COMBINER.get(), pPos, pBlockState);
        this.data = new ContainerData() {
            @Override
            public int get(int pIndex) {
                return switch (pIndex) {
                    case 0 -> CombinerBlockEntity.this.progress;
                    case 1 -> CombinerBlockEntity.this.maxProgress;
                    default -> 0;
                };
            }

            @Override
            public void set(int pIndex, int pValue) {
                switch (pIndex) {
                    case 0 -> CombinerBlockEntity.this.progress = pValue;
                    case 1 -> CombinerBlockEntity.this.maxProgress = pValue;
                }
            }

            @Override
            public int getCount() {
                return 3;
            }
        };
    }

    public void drops() {
        SimpleContainer inventory = new SimpleContainer(itemHandler.getSlots());
        for(int i = 0; i < itemHandler.getSlots(); i++) {
            inventory.setItem(i, itemHandler.getStackInSlot(i));
        }
        Containers.dropContents(this.level, this.worldPosition, inventory);
    }

    @Override
    public Component getDisplayName() {
        return Component.translatable("block.deep_aether.combiner");
    }

    @Nullable
    @Override
    public AbstractContainerMenu createMenu(int pContainerId, Inventory pPlayerInventory, Player pPlayer) {
        return new CombinerMenu(pContainerId, DARecipeTypes.COMBINING.get(), DARecipeBookTypes.DEEP_AETHER_COMBINER.getValue(), pPlayerInventory, this, this.data);
    }

    @Override
    protected void saveAdditional(CompoundTag tag, HolderLookup.Provider registries) {
        tag.put("inventory", itemHandler.serializeNBT(registries));
        tag.putInt("combiner.progress", progress);
        super.saveAdditional(tag, registries);
    }

    @Override
    protected void loadAdditional(CompoundTag tag, HolderLookup.Provider registries) {
        super.loadAdditional(tag, registries);
        itemHandler.deserializeNBT(registries, tag.getCompound("inventory"));
        progress = tag.getInt("combiner.progress");
    }

    public void tick(Level pLevel, BlockPos pPos, BlockState pState) {
        if(hasRecipe()) {
            increaseCraftingProgress();
            setChanged(pLevel, pPos, pState);

            if(hasProgressFinished()) {
                craftItem();
                resetProgress();
            }
        } else {
            resetProgress();
        }
    }

    private void resetProgress() {
        progress = 0;
    }

    private void craftItem() {
        Optional<RecipeHolder<CombinerRecipe>> recipe = getCurrentRecipe();
        ItemStack result = recipe.get().value().getResultItem(getLevel().registryAccess());
        consumeIngredients();

        this.itemHandler.setStackInSlot(OUTPUT_SLOT, new ItemStack(result.getItem(),
                this.itemHandler.getStackInSlot(OUTPUT_SLOT).getCount() + result.getCount()));
    }


    private boolean hasRecipe() {
        Optional<RecipeHolder<CombinerRecipe>> recipe = getCurrentRecipe();

        if(recipe.isEmpty())
            return false;

        ItemStack result = recipe.get().value().getResultItem(getLevel().registryAccess());
        return canInsertAmountIntoOutputSlot(result.getCount()) && canInsertItemIntoOutputSlot(result.getItem());
    }


    private Optional<RecipeHolder<CombinerRecipe>> getCurrentRecipe() {

        NonNullList<ItemStack> items = NonNullList.withSize(3, ItemStack.EMPTY);

        for(int i = 0; i < itemHandler.getSlots(); i++)
            items.add(i, this.itemHandler.getStackInSlot(i));
        return this.level.getRecipeManager().getRecipeFor(DARecipeTypes.COMBINING.get(), new CombinderRecipeInput(items), level);
    }


    private boolean canInsertItemIntoOutputSlot(Item item) {
        return this.itemHandler.getStackInSlot(OUTPUT_SLOT).isEmpty() || this.itemHandler.getStackInSlot(OUTPUT_SLOT).is(item);
    }

    private boolean canInsertAmountIntoOutputSlot(int count) {
        return this.itemHandler.getStackInSlot(OUTPUT_SLOT).getCount() + count <= this.itemHandler.getStackInSlot(OUTPUT_SLOT).getMaxStackSize();
    }

    private boolean hasProgressFinished() {
        return progress >= maxProgress;
    }

    private void increaseCraftingProgress() {
        progress++;
    }

    private void consumeIngredients(){
        this.itemHandler.extractItem(FIRST_SLOT, 1, false);
        this.itemHandler.extractItem(SECOND_SLOT, 1, false);
        this.itemHandler.extractItem(THIRD_SLOT, 1, false);
    }

    @Override
    public int getContainerSize() {
        return 4;
    }

    @Override
    public boolean isEmpty() {
        for(int i = 0; i < itemHandler.getSlots(); i++)
            if(itemHandler.getStackInSlot(i) != ItemStack.EMPTY)
                return false;
        return true;
    }

    @Override
    public ItemStack getItem(int i) {
        return itemHandler.getStackInSlot(i);
    }

    @Override
    public ItemStack removeItem(int i, int i1) {
        return itemHandler.extractItem(i, i1, false);
    }

    @Override
    public ItemStack removeItemNoUpdate(int i) {
        return itemHandler.insertItem(i, ItemStack.EMPTY, false);
    }

    @Override
    public void setItem(int i, ItemStack itemStack) {
        itemHandler.setStackInSlot(i, itemStack);
    }

    @Override
    public boolean stillValid(Player player) {
        return true;
    }

    @Override
    public void clearContent() {
        for(int i = 0; i < itemHandler.getSlots(); i++)
            itemHandler.setStackInSlot(i, ItemStack.EMPTY);
    }
}