package teamrazor.deepaether.integration.jei.categories;

import com.gildedgames.aether.recipe.BlockPropertyPair;
import com.gildedgames.aether.recipe.BlockStateIngredient;
import com.gildedgames.aether.recipe.recipes.block.SwetBallRecipe;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraftforge.fluids.FluidType;
import teamrazor.deepaether.DeepAetherMod;
import teamrazor.deepaether.fluids.DAFluidTypes;
/*
public class PoisonRecipeCategory implements IRecipeCategory<SwetBallRecipe> {
    public static final ResourceLocation UID = new ResourceLocation(DeepAetherMod.MODID, "swet_ball_convert");
    public static final ResourceLocation TEXTURE = new ResourceLocation(DeepAetherMod.MODID, "textures/gui/menu/jei_render.png");
    public static final RecipeType<SwetBallRecipe> RECIPE_TYPE = RecipeType.create(DeepAetherMod.MODID, "swet_ball_convert", SwetBallRecipe.class);

    private final IDrawable background;
    private final IDrawable icon;

    public PoisonRecipeCategory(IGuiHelper helper) {
        this.background = helper.createDrawable(TEXTURE, 49, 29, 84, 28);
        this.icon = helper.createDrawable(FluidType, new DAFluidTypes(DAFluidTypes.POISON_FLUID_TYPE.get()));
    }

    @Override
    public Component getTitle() {
        return Component.translatable("gui." + Aether.MODID + ".jei.swet_ball_convert");
    }

    @Override
    public IDrawable getBackground() {
        return this.background;
    }

    @Override
    public IDrawable getIcon() {
        return this.icon;
    }

    public ResourceLocation getUid() {
        return UID;
    }

    @Override
    public RecipeType<SwetBallRecipe> getRecipeType() {
        return RECIPE_TYPE;
    }

    @Override
    public void setRecipe(IRecipeLayoutBuilder builder, SwetBallRecipe recipe, IFocusGroup focusGroup) {
        BlockStateIngredient recipeIngredients = recipe.getIngredient();
        BlockPropertyPair recipeResult = recipe.getResult();

        BlockPropertyPair[] pairs = recipeIngredients.getPairs();

        int i = 0;
        for (BlockPropertyPair pair : pairs) {
            builder.addSlot(RecipeIngredientRole.INPUT, 8, 6 + i).addIngredients(Ingredient.of(pair.block()));
            i += 16;
        }
        builder.addSlot(RecipeIngredientRole.OUTPUT, 60, 6).addIngredients(Ingredient.of(recipeResult.block()));
    }

    @Override
    public void draw(SwetBallRecipe recipe, IRecipeSlotsView recipeSlotsView, PoseStack stack, double mouseX, double mouseY) {
    }
}*/