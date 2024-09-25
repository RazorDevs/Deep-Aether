package teamrazor.deepaether.recipe;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.core.NonNullList;
import net.minecraft.core.RegistryAccess;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.util.ExtraCodecs;
import net.minecraft.world.inventory.CraftingContainer;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.*;
import net.minecraft.world.level.Level;
import teamrazor.deepaether.networking.attachment.DAAttachments;

import java.util.List;
/*
public class ConditionalShapedRecipe implements CraftingRecipe, net.neoforged.neoforge.common.crafting.IShapedRecipe<net.minecraft.world.inventory.CraftingContainer> {
    public final ShapedRecipePattern pattern;
    final ItemStack result;
    final String group;
    final CraftingBookCategory category;
    final boolean showNotification;
    final String condition;

    public ConditionalShapedRecipe(String pGroup, CraftingBookCategory pCategory, ShapedRecipePattern pPattern, ItemStack pResult, boolean pShowNotification, String condition) {
        this.group = pGroup;
        this.category = pCategory;
        this.pattern = pPattern;
        this.result = pResult;
        this.showNotification = pShowNotification;
        this.condition = condition;
    }

    public ConditionalShapedRecipe(String pGroup, CraftingBookCategory pCategory, ShapedRecipePattern pPattern, ItemStack pResult, String condition) {
        this(pGroup, pCategory, pPattern, pResult, true, condition);
    }

    @Override
    public RecipeSerializer<?> getSerializer() {
        return null;
        //return DARecipeSerializers.CONDITIONAL_SHAPED.get();
    }

    @Override
    public String getGroup() {
        return this.group;
    }

    @Override
    public int getRecipeWidth() {
        return getWidth();
    }

    @Override
    public CraftingBookCategory category() {
        return this.category;
    }

    @Override
    public int getRecipeHeight() {
        return getHeight();
    }

    @Override
    public ItemStack getResultItem(RegistryAccess pRegistryAccess) {
        return this.result;
    }

    @Override
    public NonNullList<Ingredient> getIngredients() {
        return this.pattern.ingredients();
    }

    @Override
    public boolean showNotification() {
        return this.showNotification;
    }

    @Override
    public boolean canCraftInDimensions(int pWidth, int pHeight) {
        return pWidth >= this.pattern.width() && pHeight >= this.pattern.height();
    }

    public ItemStack assemble(CraftingContainer pContainer, RegistryAccess pRegistryAccess) {
        return this.getResultItem(pRegistryAccess).copy();
    }

    public int getWidth() {
        return this.pattern.width();
    }

    public int getHeight() {
        return this.pattern.height();
    }

    @Override
    public boolean isIncomplete() {
        NonNullList<Ingredient> nonnulllist = this.getIngredients();
        return nonnulllist.isEmpty() || nonnulllist.stream().filter(p_151277_ -> !p_151277_.isEmpty()).anyMatch(net.neoforged.neoforge.common.CommonHooks::hasNoElements);
    }

    public String getCondition() {
        return condition;
    }

    @Override
    public boolean matches(CraftingContainer pInv, Level pLevel) {
        if(pLevel.isClientSide()) {
            LocalPlayer player = Minecraft.getInstance().player;
            if(Minecraft.getInstance().player != null) {
                if(player.hasData(DAAttachments.PLAYER_FLAWLESS)) {
                    if (player.getData(DAAttachments.PLAYER_FLAWLESS).contains(condition)) {
                        return this.pattern.matches(pInv);
                    } else return false;
                }
                else return false;
            }
            else return false;
        }
        else return this.pattern.matches(pInv);
    }

    public static class Serializer implements RecipeSerializer<ConditionalShapedRecipe> {
        public static final Codec<ConditionalShapedRecipe> CODEC = RecordCodecBuilder.create(
                p_311728_ -> p_311728_.group(
                                ExtraCodecs.strictOptionalField(Codec.STRING, "group", "").forGetter(p_311729_ -> p_311729_.group),
                                CraftingBookCategory.CODEC.fieldOf("category").orElse(CraftingBookCategory.MISC).forGetter(p_311732_ -> p_311732_.category),
                                ShapedRecipePattern.MAP_CODEC.forGetter(p_311733_ -> p_311733_.pattern),
                                ItemStack.ITEM_WITH_COUNT_CODEC.fieldOf("result").forGetter(p_311730_ -> p_311730_.result),
                                ExtraCodecs.strictOptionalField(Codec.BOOL, "show_notification", true).forGetter(p_311731_ -> p_311731_.showNotification),
                                ExtraCodecs.strictOptionalField(Codec.STRING, "condition", "").forGetter(p_311729_ -> p_311729_.condition)
                                )
                        .apply(p_311728_, ConditionalShapedRecipe::new)
        );

        @Override
        public Codec<ConditionalShapedRecipe> codec() {
            return CODEC;
        }

        public ConditionalShapedRecipe fromNetwork(FriendlyByteBuf pBuffer) {
            String s = pBuffer.readUtf();
            CraftingBookCategory craftingbookcategory = pBuffer.readEnum(CraftingBookCategory.class);
            ShapedRecipePattern shapedrecipepattern = ShapedRecipePattern.fromNetwork(pBuffer);
            ItemStack itemstack = pBuffer.readItem();
            boolean flag = pBuffer.readBoolean();
            String condition = pBuffer.readUtf();
            return new ConditionalShapedRecipe(s, craftingbookcategory, shapedrecipepattern, itemstack, condition);
        }

        public void toNetwork(FriendlyByteBuf pBuffer, ConditionalShapedRecipe pRecipe) {
            pBuffer.writeUtf(pRecipe.getGroup());
            pBuffer.writeEnum(pRecipe.category());
            pRecipe.pattern.toNetwork(pBuffer);
            pBuffer.writeItem(pRecipe.getResultItem(null));
            pBuffer.writeBoolean(pRecipe.showNotification());
            pBuffer.writeUtf(pRecipe.getCondition());
        }
    }
}

*/