package teamrazor.deepaether.entity.block;

import com.aetherteam.aether.recipe.AetherRecipeTypes;
import it.unimi.dsi.fastutil.objects.Object2IntOpenHashMap;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.NonNullList;
import net.minecraft.core.RegistryAccess;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.*;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.player.StackedContents;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.ContainerData;
import net.minecraft.world.inventory.RecipeHolder;
import net.minecraft.world.inventory.StackedContentsCompatible;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.item.crafting.RecipeManager;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BaseContainerBlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.Nullable;
import teamrazor.deepaether.block.CombinerBlock;
import teamrazor.deepaether.init.DABlockEntityTypes;
import teamrazor.deepaether.recipe.DARecipeTypes;
import teamrazor.deepaether.recipe.combiner.CombinerRecipe;
import teamrazor.deepaether.screen.CombinerMenu;

import java.util.ArrayList;
import java.util.List;

public class CombinerBlockEntity extends BaseContainerBlockEntity implements WorldlyContainer, RecipeHolder, StackedContentsCompatible {
    private static final int FIRST_SLOT = 0;
    private static final int SECOND_SLOT = 1;
    private static final int THIRD_SLOT = 2;
    private static final int OUTPUT_SLOT = 3;

    protected final ContainerData data;
    protected NonNullList<ItemStack> items = NonNullList.withSize(4, ItemStack.EMPTY);
    int processingProgress = 0;
    int processingTotalTime = 78;
    boolean combining;
    int combiningDuration;

    private final Object2IntOpenHashMap<ResourceLocation> recipesUsed = new Object2IntOpenHashMap<>();
    private final RecipeManager.CachedCheck<Container, CombinerRecipe> quickCheck;

    public CombinerBlockEntity(BlockPos pPos, BlockState pBlockState) {
        this(DABlockEntityTypes.COMBINER.get(), pPos, pBlockState);
    }

    public CombinerBlockEntity(BlockEntityType<?> type, BlockPos pos, BlockState state) {
        super(type, pos, state);
        this.quickCheck = RecipeManager.createCheck(DARecipeTypes.COMBINING.get());
        this.data = new ContainerData() {
            @Override
            public int get(int pIndex) {
                return switch (pIndex) {
                    case 0 -> CombinerBlockEntity.this.processingProgress;
                    case 1 -> CombinerBlockEntity.this.processingTotalTime;
                    default -> 0;
                };
            }

            @Override
            public void set(int pIndex, int pValue) {
                switch (pIndex) {
                    case 0 -> CombinerBlockEntity.this.processingProgress = pValue;
                    case 1 -> CombinerBlockEntity.this.processingTotalTime = pValue;
                }
            }

            @Override
            public int getCount() {
                return 2;
            }
        };
    }

    @Override
    public Component getDisplayName() {
        return Component.translatable("block.deep_aether.combiner");
    }

    @Override
    protected Component getDefaultName() {
        return Component.translatable("block.deep_aether.combiner");
    }

    @Override
    protected AbstractContainerMenu createMenu(int i, Inventory inventory) {
        return new CombinerMenu(i, inventory, this, this.data);
    }

    @Override
    public void load(CompoundTag tag) {
        super.load(tag);
        this.items = NonNullList.withSize(this.getContainerSize(), ItemStack.EMPTY);
        ContainerHelper.loadAllItems(tag, this.items);
        this.processingProgress = tag.getInt("ProcessingTime");
        this.processingTotalTime = tag.getInt("ProcessingTimeTotal");
        CompoundTag recipesUsedTag = tag.getCompound("RecipesUsed");
        for (String key : recipesUsedTag.getAllKeys()) {
            this.recipesUsed.put(ResourceLocation.tryParse(key), recipesUsedTag.getInt(key));
        }
    }

    @Override
    protected void saveAdditional(CompoundTag tag) {
        super.saveAdditional(tag);
        tag.putInt("ProcessingTime", this.processingProgress);
        tag.putInt("ProcessingTimeTotal", this.processingTotalTime);
        ContainerHelper.saveAllItems(tag, this.items);
        CompoundTag recipesUsedTag = new CompoundTag();
        this.recipesUsed.forEach((location, integer) -> recipesUsedTag.putInt(location.toString(), integer));
        tag.put("RecipesUsed", recipesUsedTag);
    }

    public NonNullList<ItemStack> getIngredients() {
        ArrayList<ItemStack> ingredients = new ArrayList<>();

        ingredients.add(items.get(0));
        ingredients.add(items.get(1));
        ingredients.add(items.get(2));

        return (NonNullList<ItemStack>) List.copyOf(ingredients);
    }

    public static void serverTick(Level level, BlockPos pos, BlockState state, CombinerBlockEntity blockEntity) {
        boolean changed = false;

        Recipe<Container> recipeHolder = blockEntity.quickCheck.getRecipeFor(blockEntity, level).orElse(null);
        int i = blockEntity.getMaxStackSize();
        boolean isCharging = false;

        if (blockEntity.canProcess(level.registryAccess(), recipeHolder, blockEntity.items, i)) {
            changed = true;
            ++blockEntity.processingProgress;
            isCharging = true;
            if (blockEntity.processingProgress == blockEntity.processingTotalTime) {
                blockEntity.processingProgress = 0;
                blockEntity.processingTotalTime = getTotalProcessingTime(level, blockEntity);
                if (blockEntity.process(level.registryAccess(), recipeHolder, blockEntity.items, i)) {
                    blockEntity.setRecipeUsed(recipeHolder);
                }
                isCharging = false;
                blockEntity.combining = true;
                blockEntity.combiningDuration = 15;
            }
        } else {
            blockEntity.processingProgress = 0;
        }

        if (blockEntity.combiningDuration <= 0) {
            blockEntity.combining = false;
        }

        if (state.getValue(CombinerBlock.CHARGING) != isCharging) {
            changed = true;
            state = state.setValue(CombinerBlock.CHARGING, isCharging);
            level.setBlock(pos, state, 1 | 2);
        }

        if (state.getValue(CombinerBlock.COMBINING) != blockEntity.combining) {
            changed = true;
            state = state.setValue(CombinerBlock.COMBINING, blockEntity.combining);
            level.setBlock(pos, state, 1 | 2);
        }

        if (changed) {
            setChanged(level, pos, state);
        }
    }

    private boolean canProcess(RegistryAccess registryAccess, @Nullable Recipe<Container> recipeHolder, NonNullList<ItemStack> stacks, int maxStackSize) {
        ItemStack left = stacks.get(FIRST_SLOT);
        ItemStack middle = stacks.get(SECOND_SLOT);
        ItemStack right = stacks.get(THIRD_SLOT);
        if (!left.isEmpty() && !middle.isEmpty() && !right.isEmpty() && recipeHolder != null) {
            ItemStack result = recipeHolder.assemble(this, registryAccess);
            if (result.isEmpty()) {
                return false;
            } else {
                ItemStack inResultSlot = stacks.get(OUTPUT_SLOT);
                if (inResultSlot.isEmpty()) {
                    return true;
                } else if (!ItemStack.isSameItemSameTags(inResultSlot, result)) {
                    return false;
                } else if (inResultSlot.getCount() + result.getCount() <= maxStackSize && inResultSlot.getCount() + result.getCount() <= inResultSlot.getMaxStackSize()) {
                    return true;
                } else {
                    return inResultSlot.getCount() + result.getCount() <= result.getMaxStackSize();
                }
            }
        } else {
            return false;
        }
    }

    private boolean process(RegistryAccess registryAccess, @Nullable Recipe<Container> recipeHolder, NonNullList<ItemStack> stacks, int maxStackSize) {
        if (recipeHolder != null && this.canProcess(registryAccess, recipeHolder, stacks, maxStackSize)) {
            ItemStack left = stacks.get(FIRST_SLOT);
            ItemStack middle = stacks.get(SECOND_SLOT);
            ItemStack right = stacks.get(THIRD_SLOT);
            ItemStack result = recipeHolder.assemble(this, registryAccess);
            ItemStack output = stacks.get(OUTPUT_SLOT);

            if (output.isEmpty()) {
                stacks.set(OUTPUT_SLOT, result.copy());
            } else if (output.is(result.getItem())) {
                output.grow(result.getCount());
            }

            left.shrink(1);
            middle.shrink(1);
            right.shrink(1);

            return true;
        } else {
            return false;
        }
    }


    private static int getTotalProcessingTime(Level level, CombinerBlockEntity blockEntity) {
        return blockEntity.quickCheck.getRecipeFor(blockEntity, level).map(CombinerRecipe::getProcessingTime).orElse(200);
    }

    public void drops() {
        if(this.level != null)
            Containers.dropContents(this.level, this.worldPosition, this.items);
    }

    @Override
    public int[] getSlotsForFace(Direction side) {
        if (side == Direction.DOWN) {
            return new int[]{OUTPUT_SLOT};
        } else {
            return new int[]{FIRST_SLOT, SECOND_SLOT, THIRD_SLOT};
        }
    }

    @Override
    public boolean canPlaceItemThroughFace(int i, ItemStack itemStack, @Nullable Direction direction) {
        return this.canPlaceItem(i, itemStack);
    }

    @Override
    public boolean canTakeItemThroughFace(int i, ItemStack itemStack, Direction direction) {
        return direction != Direction.DOWN;
    }

    protected NonNullList<ItemStack> getItems() {
        return this.items;
    }

    protected void setItems(NonNullList<ItemStack> pItems) {
        this.items = pItems;
    }

    @Override
    public int getContainerSize() {
        return this.items.size();
    }

    @Override
    public boolean isEmpty() {
        for (ItemStack itemstack : this.items) {
            if (!itemstack.isEmpty()) {
                return false;
            }
        }
        return true;
    }

    @Override
    public ItemStack getItem(int index) {
        return this.items.get(index);
    }

    @Override
    public ItemStack removeItem(int index, int count) {
        return ContainerHelper.removeItem(this.items, index, count);
    }

    @Override
    public ItemStack removeItemNoUpdate(int index) {
        return ContainerHelper.takeItem(this.items, index);
    }

    @Override
    public void setItem(int index, ItemStack stack) {
        ItemStack itemstack = this.items.get(index);
        boolean continueProcess = !stack.isEmpty() && ItemStack.isSameItemSameTags(itemstack, stack);
        this.items.set(index, stack);
        if (stack.getCount() > this.getMaxStackSize()) {
            stack.setCount(this.getMaxStackSize());
        }

        if (index == 0 && !continueProcess) {
            this.processingTotalTime = getTotalProcessingTime(this.level, this);
            this.processingProgress = 0;
            this.setChanged();
        }
    }

    @Override
    public boolean stillValid(Player player) {
        return Container.stillValidBlockEntity(this, player);
    }

    @Override
    public void clearContent() {
        this.items.clear();
    }

    @Override
    public void setRecipeUsed(@Nullable Recipe<?> recipeHolder) {
        if (recipeHolder != null) {
            ResourceLocation location = recipeHolder.getId();
            this.recipesUsed.addTo(location, 1);
        }
    }

    @Override
    public Recipe<?> getRecipeUsed() {
        return null;
    }

    @Override
    public void awardUsedRecipes(Player player, List<ItemStack> items) {
    }

    @Override
    public void fillStackedContents(StackedContents stackedContents) {
        for (ItemStack itemstack : this.items) {
            stackedContents.accountStack(itemstack);
        }
    }
}