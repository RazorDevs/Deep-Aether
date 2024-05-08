package teamrazor.deepaether.recipe.jei;

import com.aetherteam.aether.Aether;
import com.aetherteam.nitrogen.integration.jei.categories.AbstractRecipeCategory;
import mezz.jei.api.constants.VanillaTypes;
import mezz.jei.api.gui.builder.IRecipeLayoutBuilder;
import mezz.jei.api.helpers.IGuiHelper;
import mezz.jei.api.recipe.IFocusGroup;
import mezz.jei.api.recipe.RecipeIngredientRole;
import mezz.jei.api.recipe.RecipeType;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import teamrazor.deepaether.DeepAether;
import teamrazor.deepaether.recipe.poison.PoisonRecipe;

public class PoisonRecipeCategory extends AbstractRecipeCategory<PoisonRecipe> {
    public static final ResourceLocation UID = new ResourceLocation(DeepAether.MODID, "poison_recipe");
    public static final ResourceLocation TEXTURE = new ResourceLocation(Aether.MODID, "textures/gui/menu/jei_render.png");
    public static final RecipeType<PoisonRecipe> RECIPE_TYPE = RecipeType.create(DeepAether.MODID, "poison_recipe", PoisonRecipe.class);

    public PoisonRecipeCategory(IGuiHelper guiHelper) {
        super("poison_recipe", UID,
                guiHelper.createDrawable(TEXTURE, 0, 0, 84, 28),
                guiHelper.createDrawableIngredient(VanillaTypes.ITEM_STACK, new ItemStack(DAItems.PLACEABLE_POISON_BUCKET.get())),
                RECIPE_TYPE);
    }

    @Override
    public Component getTitle() {
        return Component.translatable("gui.deep_aether.jei." + this.id);
    }

    @Override
    public void setRecipe(IRecipeLayoutBuilder builder, PoisonRecipe recipe, IFocusGroup focuses) {
        builder.addSlot(RecipeIngredientRole.INPUT, 8, 6).addIngredients(recipe.getIngredient());
        builder.addSlot(RecipeIngredientRole.OUTPUT, 60, 6).addItemStack(recipe.getResult());
    }
}