package teamrazor.deepaether.recipe;

import net.minecraft.core.RegistryAccess;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.FastColor;
import net.minecraft.world.inventory.CraftingContainer;
import net.minecraft.world.item.DyeItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.CraftingBookCategory;
import net.minecraft.world.item.crafting.CustomRecipe;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.NotNull;
import org.spongepowered.asm.mixin.Unique;
import teamrazor.deepaether.init.DAItems;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class FloatyScarfColoring extends CustomRecipe {
    public FloatyScarfColoring(ResourceLocation p_252125_, CraftingBookCategory p_249010_) {
        super(p_252125_, p_249010_);
    }

    @Override
    public boolean matches(CraftingContainer input, @NotNull Level level) {
        boolean containsScarf = false;
        boolean containsBucket = false;
        int colorCount = 0;

        for (int k = 0; k < input.getContainerSize(); k++) {
            ItemStack itemstack = input.getItem(k);
            if (itemstack.is(DAItems.FLOATY_SCARF.get())) {
                containsScarf = true;
            } else if (itemstack.getItem() instanceof DyeItem)
                colorCount++;
            else if(itemstack.is(Items.WATER_BUCKET)) {
                if(containsBucket)
                    return false;
                else containsBucket = true;
            }
            else if(!itemstack.isEmpty())
                return false;
        }
        if(containsBucket && colorCount > 0)
            return false;

        return containsScarf;
    }

    @Unique
    public final static HashMap<Integer, Integer> TEXTURE_DIFFUSE_COLOR = new HashMap<>();

    @Override
    @NotNull
    public ItemStack assemble(CraftingContainer input, RegistryAccess provider) {
        List<Integer> colors = new ArrayList<>();
        ItemStack scarf = new ItemStack(DAItems.FLOATY_SCARF.get());
        boolean bucket = false;

        for (int i = 0; i < input.getContainerSize(); i++) {
            ItemStack itemstack = input.getItem(i);
            if (itemstack.is(DAItems.FLOATY_SCARF.get())) {
                scarf = itemstack;
            } else if (itemstack.getItem() instanceof DyeItem item) {

                colors.add(TEXTURE_DIFFUSE_COLOR.get(item.getDyeColor().getId()));
            }
            else if(itemstack.is(Items.WATER_BUCKET)) {
                bucket = true;
                break;
            }
        }

        scarf = scarf.copy();

        CompoundTag tag = scarf.getOrCreateTag();
        int[] currentColors;
        byte mod;
        if (tag.contains("Colors"))
            currentColors = tag.getIntArray("Colors");
        else {
            currentColors = new int[]{-1, -1, -1, -1, -1};
        }

        if(tag.contains("currentModification")) {
            mod = tag.getByte("currentModification");
        }
        else mod = 0;



        if(bucket) {
            currentColors[mod] = -1;
            byte currentModification = (byte) (mod + 1);
            if(currentModification > 4)
                currentModification = 0;

            tag.putIntArray("Colors", currentColors);
            tag.putByte("currentModification", currentModification);
        }

        else if(colors.isEmpty()) {
            byte currentModification = (byte) (mod + 1);
            if(currentModification > 4)
                currentModification = 0;
            tag.putByte("currentModification", currentModification);
        }
        else {
            int applyDyes = applyDyes(currentColors[mod], colors);

            currentColors[mod] = applyDyes;
            byte currentModification = (byte) (mod + 1);
            if(currentModification > 4)
                currentModification = 0;

            tag.putIntArray("Colors", currentColors);
            tag.putByte("currentModification", currentModification);
        }

        return scarf.copy();
    }


    @Override
    public boolean canCraftInDimensions(int pWidth, int pHeight) {
        return pWidth * pHeight >= 2;
    }

    @Override
    @NotNull
    public RecipeSerializer<?> getSerializer() {
        return DARecipeSerializers.FLOATY_SCARF_COLORING.get();
    }

    public static int applyDyes(int color, List<Integer> dyes) {
        int i = 0;
        int j = 0;
        int k = 0;
        int l = 0;
        int i1 = 0;

        int j1 = FastColor.ARGB32.red(color);
        int k1 = FastColor.ARGB32.green(color);
        int l1 = FastColor.ARGB32.blue(color);
        l += Math.max(j1, Math.max(k1, l1));
        i += j1;
        j += k1;
        k += l1;
        i1++;


        for (int dye : dyes) {
            int i2 = FastColor.ARGB32.red(dye);
            int j2 = FastColor.ARGB32.green(dye);
            int k2 = FastColor.ARGB32.blue(dye);
            l += Math.max(i2, Math.max(j2, k2));
            i += i2;
            j += j2;
            k += k2;
            i1++;
        }

        int l2 = i / i1;
        int i3 = j / i1;
        int k3 = k / i1;
        float f = (float)l / (float)i1;
        float f1 = (float)Math.max(l2, Math.max(i3, k3));
        l2 = (int)((float)l2 * f / f1);
        i3 = (int)((float)i3 * f / f1);
        k3 = (int)((float)k3 * f / f1);
        return FastColor.ARGB32.color(0, l2, i3, k3);
    }
}