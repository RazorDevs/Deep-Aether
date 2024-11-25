package teamrazor.deepaether.screen;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.Container;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.player.StackedContents;
import net.minecraft.world.inventory.*;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.level.Level;
import teamrazor.deepaether.init.DAMenuTypes;
import teamrazor.deepaether.recipe.DARecipeBookTypes;
import teamrazor.deepaether.recipe.combiner.CombinerRecipe;

import java.util.ArrayList;
import java.util.List;

public class CombinerMenu extends RecipeBookMenu<Container> {
    private final Level level;
    private final ContainerData data;
    private final Container container;

    public CombinerMenu(int containerId, Inventory playerInventory) {
        this(containerId, playerInventory, new SimpleContainer(4), new SimpleContainerData(2));
    }

//    @SuppressWarnings("unchecked")
//    @Override
//    public void handlePlacement(boolean flag, Recipe<?> holder, ServerPlayer serverPlayer) {
//        this.beginPlacingRecipe();
//
//        try {
//            new CombinerServerPlaceRecipe(this).recipeClicked(serverPlayer, (Recipe<CombinerRecipe>) holder, flag);
//        } finally {
//            this.finishPlacingRecipe((Recipe<CombinerRecipe>)holder);
//        }
//    }

    public CombinerMenu(int pContainerId, Inventory inv, Container container, ContainerData data) {
        super(DAMenuTypes.COMBINER_MENU.get(), pContainerId);

        checkContainerSize(container, 4);
        checkContainerDataCount(data, 2);
        this.level = inv.player.level();
        this.data = data;
        this.container = container;

        this.addSlot(new Slot(container, 0, 57, 17));
        this.addSlot(new Slot(container, 1, 80, 17));
        this.addSlot(new Slot(container, 2, 103, 17));
        this.addSlot(new FurnaceResultSlot(inv.player, container, 3, 80, 53));

        addPlayerInventory(inv);
        addPlayerHotbar(inv);

        addDataSlots(data);
    }

    public CombinerMenu(int containerId, Inventory playerInventory, FriendlyByteBuf friendlyByteBuf) {
        this(containerId, playerInventory, new SimpleContainer(4), new SimpleContainerData(2));
    }

    public boolean isCrafting() {
        return data.get(0) > 0;
    }

    public int getScaledProgress() {
        int progress = this.data.get(0);
        int maxProgress = this.data.get(1);  // Max Progress
        int progressArrowSize = 14; // This is the height in pixels of your arrow

        return maxProgress != 0 && progress != 0 ? progress * progressArrowSize / maxProgress : 0;
    }

    @Override
    public boolean stillValid(Player pPlayer) {
        return this.container.stillValid(pPlayer);
    }

    private void addPlayerInventory(Inventory playerInventory) {
        for (int i = 0; i < 3; ++i) {
            for (int l = 0; l < 9; ++l) {
                this.addSlot(new Slot(playerInventory, l + i * 9 + 9, 8 + l * 18, 84 + i * 18));
            }
        }
    }

    private void addPlayerHotbar(Inventory playerInventory) {
        for (int i = 0; i < 9; ++i) {
            this.addSlot(new Slot(playerInventory, i, 8 + i * 18, 142));
        }
    }

    @Override
    public void fillCraftSlotsStackedContents(StackedContents stackedContents) {
        if (this.container instanceof StackedContentsCompatible stackedContentsCompatible) {
            stackedContentsCompatible.fillStackedContents(stackedContents);
        }
    }

    @Override
    public void clearCraftingContent() {
        this.getSlot(0).set(ItemStack.EMPTY);
        this.getSlot(1).set(ItemStack.EMPTY);
        this.getSlot(2).set(ItemStack.EMPTY);
    }

    @Override
    public boolean recipeMatches(Recipe<? super Container> recipeHolder) {
        return recipeHolder.matches(this.container, this.level);
    }

    private List<ItemStack> getIngredients(){
        List<ItemStack> stacks = new ArrayList<>();
        stacks.add(this.container.getItem(0));
        stacks.add(this.container.getItem(1));
        stacks.add(this.container.getItem(2));
        return stacks;
    }

    @Override
    public int getResultSlotIndex() {
        return 3;
    }

    @Override
    public int getGridWidth() {
        return 1;
    }

    @Override
    public int getGridHeight() {
        return 1;
    }

    @Override
    public int getSize() {
        return 4;
    }

    @Override
    public RecipeBookType getRecipeBookType() {
        return DARecipeBookTypes.COMBINER;
    }

    @Override
    public boolean shouldMoveToInventory(int slotIndex) {
        return slotIndex != 3;
    }

    @Override
    public ItemStack quickMoveStack(Player player, int index) {
        ItemStack itemStack = ItemStack.EMPTY;
        Slot slot = this.slots.get(index);
        if (slot.hasItem()) {
            ItemStack itemStack1 = slot.getItem();
            itemStack = itemStack1.copy();
            if (index == 3) {
                if (!this.moveItemStackTo(itemStack1, 4, 40, true)) {
                    return ItemStack.EMPTY;
                }

                slot.onQuickCraft(itemStack1, itemStack);
            } else if (index > 3) {
                if (!this.moveItemStackTo(itemStack1, 0, 3, false)) {
                    return ItemStack.EMPTY;
                }
            } else if (!this.moveItemStackTo(itemStack1, 4, 40, false)) {
                return ItemStack.EMPTY;
            }

            if (itemStack1.isEmpty()) {
                slot.setByPlayer(ItemStack.EMPTY);
            } else {
                slot.setChanged();
            }

            if (itemStack1.getCount() == itemStack.getCount()) {
                return ItemStack.EMPTY;
            }

            slot.onTake(player, itemStack1);
        }

        return itemStack;
    }
}