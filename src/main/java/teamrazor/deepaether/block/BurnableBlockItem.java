package teamrazor.deepaether.block;

import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.level.block.Block;

import javax.annotation.Nullable;
public class BurnableBlockItem extends BlockItem {

    private final int BURN_TIME;
    public BurnableBlockItem(int burnTime, Block block, Item.Properties properties) {
        super(block, properties);
        this.BURN_TIME = burnTime;
    }

    public int getBurnTime(ItemStack itemStack, @Nullable RecipeType<?> recipeType) {
        return BURN_TIME;
    }
}
