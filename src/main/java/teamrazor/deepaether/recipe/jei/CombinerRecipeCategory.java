package teamrazor.deepaether.recipe.jei;

import com.aetherteam.nitrogen.integration.jei.categories.AbstractRecipeCategory;
import mezz.jei.api.constants.VanillaTypes;
import mezz.jei.api.gui.builder.IRecipeLayoutBuilder;
import mezz.jei.api.gui.drawable.IDrawableAnimated;
import mezz.jei.api.gui.ingredient.IRecipeSlotsView;
import mezz.jei.api.helpers.IGuiHelper;
import mezz.jei.api.recipe.IFocusGroup;
import mezz.jei.api.recipe.RecipeIngredientRole;
import mezz.jei.api.recipe.RecipeType;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Font;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import teamrazor.deepaether.DeepAether;
import teamrazor.deepaether.init.DABlocks;
import teamrazor.deepaether.recipe.combiner.CombinerRecipe;

public class CombinerRecipeCategory extends AbstractRecipeCategory<CombinerRecipe> {
    public static final ResourceLocation UID = DeepAether.getResource( "combiner_recipe");
    public static final ResourceLocation TEXTURE = DeepAether.getResource( "textures/gui/combiner_gui_jei.png");
    public static final ResourceLocation ARROW_TEXTURE = DeepAether.getResource( "textures/gui/sprites/combiner_arrow.png");
    public static final RecipeType<CombinerRecipe> RECIPE_TYPE = RecipeType.create(DeepAether.MODID, "combiner_recipe", CombinerRecipe.class);

    private IDrawableAnimated animatedProgressArrow;

    public CombinerRecipeCategory(IGuiHelper guiHelper) {
        super("combiner_recipe", UID,
                guiHelper.createDrawable(TEXTURE, 0, 0, 68, 58),
                guiHelper.createDrawableIngredient(VanillaTypes.ITEM_STACK, new ItemStack(DABlocks.COMBINER.get())),
                RECIPE_TYPE);

        this.animatedProgressArrow = guiHelper.createAnimatedDrawable(guiHelper.drawableBuilder(ARROW_TEXTURE, 0, 0, 51, 14).setTextureSize(51, 14).build(), 100, IDrawableAnimated.StartDirection.TOP, false);
    }

    @Override
    public Component getTitle() {
        return Component.translatable("gui.deep_aether.jei." + this.id);
    }

    @Override
    public void setRecipe(IRecipeLayoutBuilder builder, CombinerRecipe recipe, IFocusGroup focuses) {
        builder.addSlot(RecipeIngredientRole.INPUT, 3, 3).addIngredients(recipe.getIngredients().get(0));
        builder.addSlot(RecipeIngredientRole.INPUT, 26, 3).addIngredients(recipe.getIngredients().get(1));
        builder.addSlot(RecipeIngredientRole.INPUT, 49, 3).addIngredients(recipe.getIngredients().get(2));
        builder.addSlot(RecipeIngredientRole.OUTPUT, 26, 39).addItemStack(recipe.getResult());
    }

    @Override
    public void draw(CombinerRecipe recipe, IRecipeSlotsView recipeSlotsView, GuiGraphics guiGraphics, double mouseX, double mouseY) {
        this.animatedProgressArrow.draw(guiGraphics, 9, 22);
        this.drawCookingTime(guiGraphics, 50, 44, recipe.getProcessingTime());
    }

    private void drawCookingTime(GuiGraphics guiGraphics, int x, int y, int time) {
        if (time > 0) {
            int cookTimeSeconds = time / 20;
            Component timeString = Component.translatable("gui.jei.category.smelting.time.seconds", cookTimeSeconds);
            Font fontRenderer = Minecraft.getInstance().font;
            guiGraphics.drawString(fontRenderer, timeString, x, y, 0xFF808080, false);
        }
    }
}
