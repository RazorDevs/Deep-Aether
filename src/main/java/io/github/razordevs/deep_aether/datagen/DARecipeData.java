package io.github.razordevs.deep_aether.datagen;

import com.aetherteam.aether.AetherTags;
import com.aetherteam.aether.block.AetherBlocks;
import com.aetherteam.aether.data.providers.AetherRecipeProvider;
import com.aetherteam.aether.item.AetherItems;
import com.aetherteam.nitrogen.recipe.BlockStateIngredient;
import com.aetherteam.nitrogen.recipe.builder.BlockStateRecipeBuilder;
import io.github.razordevs.deep_aether.DeepAether;
import io.github.razordevs.deep_aether.datagen.builder.CombiningRecipeBuilder;
import io.github.razordevs.deep_aether.datagen.builder.PoisonConversionRecipeBuilder;
import io.github.razordevs.deep_aether.datagen.tags.DATags;
import io.github.razordevs.deep_aether.init.DABlocks;
import io.github.razordevs.deep_aether.init.DAItems;
import io.github.razordevs.deep_aether.init.DAMobEffects;
import io.github.razordevs.deep_aether.item.component.DADataComponentTypes;
import io.github.razordevs.deep_aether.item.component.MoaFodder;
import io.github.razordevs.deep_aether.recipe.DABookCategory;
import io.github.razordevs.deep_aether.recipe.FloatyScarfColoring;
import io.github.razordevs.deep_aether.recipe.GlowingSporesRecipe;
import io.github.razordevs.deep_aether.recipe.GoldenSwetBallRecipe;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.RecipeBuilder;
import net.minecraft.data.recipes.RecipeCategory;
import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.data.recipes.ShapedRecipeBuilder;
import net.minecraft.data.recipes.ShapelessRecipeBuilder;
import net.minecraft.data.recipes.SimpleCookingRecipeBuilder;
import net.minecraft.data.recipes.SingleItemRecipeBuilder;
import net.minecraft.data.recipes.SmithingTransformRecipeBuilder;
import net.minecraft.data.recipes.SpecialRecipeBuilder;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.neoforged.neoforge.common.Tags;

import java.util.concurrent.CompletableFuture;
import java.util.function.Supplier;

@SuppressWarnings("SameParameterValue")
public class DARecipeData extends AetherRecipeProvider {
    public DARecipeData(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider) {
        super(output, lookupProvider, DeepAether.MODID);
    }

    @Override
    protected void buildRecipes(RecipeOutput consumer) {

        //Wood Types
        woodFromLogs(consumer, DABlocks.ROSEROOT_WOOD.get(), DABlocks.ROSEROOT_LOG.get());
        woodFromLogs(consumer, DABlocks.STRIPPED_ROSEROOT_WOOD.get(), DABlocks.STRIPPED_ROSEROOT_LOG.get());
        planksFromLogs(consumer, DABlocks.ROSEROOT_PLANKS.get(), DATags.Items.CRAFTS_ROSEROOT_PLANKS, 4);
        stairs(DABlocks.ROSEROOT_STAIRS, DABlocks.ROSEROOT_PLANKS).group("wooden_stairs").save(consumer);
        slab(DABlocks.ROSEROOT_SLAB.get(), DABlocks.ROSEROOT_PLANKS).group("wooden_slab").save(consumer);
        fence(DABlocks.ROSEROOT_FENCE, DABlocks.ROSEROOT_PLANKS).save(consumer);
        fenceGate(DABlocks.ROSEROOT_FENCE_GATE, DABlocks.ROSEROOT_PLANKS).save(consumer);
        doorBuilder(DABlocks.ROSEROOT_DOOR.get(), Ingredient.of(DABlocks.ROSEROOT_PLANKS.get())).unlockedBy(getHasName(DABlocks.ROSEROOT_PLANKS.get()), has(DABlocks.ROSEROOT_PLANKS.get())).group("wooden_door").save(consumer);
        trapdoorBuilder(DABlocks.ROSEROOT_TRAPDOOR.get(), Ingredient.of(DABlocks.ROSEROOT_PLANKS.get())).unlockedBy(getHasName(DABlocks.ROSEROOT_PLANKS.get()), has(DABlocks.ROSEROOT_PLANKS.get())).group("wooden_trapdoor").save(consumer);
        pressurePlateBuilder(RecipeCategory.REDSTONE, DABlocks.ROSEROOT_PRESSURE_PLATE.get(), Ingredient.of(DABlocks.ROSEROOT_PLANKS.get())).unlockedBy(getHasName(DABlocks.ROSEROOT_PLANKS.get()), has(DABlocks.ROSEROOT_PLANKS.get())).group("wooden_pressure_plate").save(consumer);
        buttonBuilder(DABlocks.ROSEROOT_BUTTON.get(), Ingredient.of(DABlocks.ROSEROOT_PLANKS.get())).unlockedBy(getHasName(DABlocks.ROSEROOT_PLANKS.get()), has(DABlocks.ROSEROOT_PLANKS.get())).group("wooden_button").save(consumer);
        sign(consumer, DAItems.ROSEROOT_SIGN.get(), DABlocks.ROSEROOT_PLANKS.get());
        makeHangingSign(consumer, DAItems.ROSEROOT_HANGING_SIGN.get(), DABlocks.STRIPPED_ROSEROOT_LOG.get());
        makeBoat(DAItems.ROSEROOT_BOAT, DABlocks.ROSEROOT_PLANKS.get()).save(consumer);
        makeChestBoat(DAItems.ROSEROOT_CHEST_BOAT.get(), DAItems.ROSEROOT_BOAT.get()).save(consumer);

        woodFromLogs(consumer, DABlocks.YAGROOT_WOOD.get(), DABlocks.YAGROOT_LOG.get());
        woodFromLogs(consumer, DABlocks.STRIPPED_YAGROOT_WOOD.get(), DABlocks.STRIPPED_YAGROOT_LOG.get());
        planksFromLogs(consumer, DABlocks.YAGROOT_PLANKS.get(), DATags.Items.CRAFTS_YAGROOT_PLANKS, 4);
        stairs(DABlocks.YAGROOT_STAIRS, DABlocks.YAGROOT_PLANKS).group("wooden_stairs").save(consumer);
        slab(DABlocks.YAGROOT_SLAB.get(), DABlocks.YAGROOT_PLANKS).group("wooden_slab").save(consumer);
        fence(DABlocks.YAGROOT_FENCE, DABlocks.YAGROOT_PLANKS).save(consumer);
        fenceGate(DABlocks.YAGROOT_FENCE_GATE, DABlocks.YAGROOT_PLANKS).save(consumer);
        doorBuilder(DABlocks.YAGROOT_DOOR.get(), Ingredient.of(DABlocks.YAGROOT_PLANKS.get())).unlockedBy(getHasName(DABlocks.YAGROOT_PLANKS.get()), has(DABlocks.YAGROOT_PLANKS.get())).group("wooden_door").save(consumer);
        trapdoorBuilder(DABlocks.YAGROOT_TRAPDOOR.get(), Ingredient.of(DABlocks.YAGROOT_PLANKS.get())).unlockedBy(getHasName(DABlocks.YAGROOT_PLANKS.get()), has(DABlocks.YAGROOT_PLANKS.get())).group("wooden_trapdoor").save(consumer);
        pressurePlateBuilder(RecipeCategory.REDSTONE, DABlocks.YAGROOT_PRESSURE_PLATE.get(), Ingredient.of(DABlocks.YAGROOT_PLANKS.get())).unlockedBy(getHasName(DABlocks.YAGROOT_PLANKS.get()), has(DABlocks.YAGROOT_PLANKS.get())).group("wooden_pressure_plate").save(consumer);
        buttonBuilder(DABlocks.YAGROOT_BUTTON.get(), Ingredient.of(DABlocks.YAGROOT_PLANKS.get())).unlockedBy(getHasName(DABlocks.YAGROOT_PLANKS.get()), has(DABlocks.YAGROOT_PLANKS.get())).group("wooden_button").save(consumer);
        sign(consumer, DAItems.YAGROOT_SIGN.get(), DABlocks.YAGROOT_PLANKS.get());
        makeHangingSign(consumer, DAItems.YAGROOT_HANGING_SIGN.get(), DABlocks.STRIPPED_YAGROOT_LOG.get());
        makeBoat(DAItems.YAGROOT_BOAT, DABlocks.YAGROOT_PLANKS.get()).save(consumer);
        makeChestBoat(DAItems.YAGROOT_CHEST_BOAT.get(), DAItems.YAGROOT_BOAT.get()).save(consumer);

        woodFromLogs(consumer, DABlocks.CRUDEROOT_WOOD.get(), DABlocks.CRUDEROOT_LOG.get());
        woodFromLogs(consumer, DABlocks.STRIPPED_CRUDEROOT_WOOD.get(), DABlocks.STRIPPED_CRUDEROOT_LOG.get());
        planksFromLogs(consumer, DABlocks.CRUDEROOT_PLANKS.get(), DATags.Items.CRAFTS_CRUDEROOT_PLANKS, 4);
        stairs(DABlocks.CRUDEROOT_STAIRS, DABlocks.CRUDEROOT_PLANKS).group("wooden_stairs").save(consumer);
        slab(DABlocks.CRUDEROOT_SLAB.get(), DABlocks.CRUDEROOT_PLANKS).group("wooden_slab").save(consumer);
        fence(DABlocks.CRUDEROOT_FENCE, DABlocks.CRUDEROOT_PLANKS).save(consumer);
        fenceGate(DABlocks.CRUDEROOT_FENCE_GATE, DABlocks.CRUDEROOT_PLANKS).save(consumer);
        doorBuilder(DABlocks.CRUDEROOT_DOOR.get(), Ingredient.of(DABlocks.CRUDEROOT_PLANKS.get())).unlockedBy(getHasName(DABlocks.CRUDEROOT_PLANKS.get()), has(DABlocks.CRUDEROOT_PLANKS.get())).group("wooden_door").save(consumer);
        trapdoorBuilder(DABlocks.CRUDEROOT_TRAPDOOR.get(), Ingredient.of(DABlocks.CRUDEROOT_PLANKS.get())).unlockedBy(getHasName(DABlocks.CRUDEROOT_PLANKS.get()), has(DABlocks.CRUDEROOT_PLANKS.get())).group("wooden_trapdoor").save(consumer);
        pressurePlateBuilder(RecipeCategory.REDSTONE, DABlocks.CRUDEROOT_PRESSURE_PLATE.get(), Ingredient.of(DABlocks.CRUDEROOT_PLANKS.get())).unlockedBy(getHasName(DABlocks.CRUDEROOT_PLANKS.get()), has(DABlocks.CRUDEROOT_PLANKS.get())).group("wooden_pressure_plate").save(consumer);
        buttonBuilder(DABlocks.CRUDEROOT_BUTTON.get(), Ingredient.of(DABlocks.CRUDEROOT_PLANKS.get())).unlockedBy(getHasName(DABlocks.CRUDEROOT_PLANKS.get()), has(DABlocks.CRUDEROOT_PLANKS.get())).group("wooden_button").save(consumer);
        sign(consumer, DAItems.CRUDEROOT_SIGN.get(), DABlocks.CRUDEROOT_PLANKS.get());
        makeHangingSign(consumer, DAItems.CRUDEROOT_HANGING_SIGN.get(), DABlocks.STRIPPED_CRUDEROOT_LOG.get());
        makeBoat(DAItems.CRUDEROOT_BOAT, DABlocks.CRUDEROOT_PLANKS.get()).save(consumer);
        makeChestBoat(DAItems.CRUDEROOT_CHEST_BOAT.get(), DAItems.CRUDEROOT_BOAT.get()).save(consumer);

        woodFromLogs(consumer, DABlocks.CONBERRY_WOOD.get(), DABlocks.CONBERRY_LOG.get());
        woodFromLogs(consumer, DABlocks.STRIPPED_CONBERRY_WOOD.get(), DABlocks.STRIPPED_CONBERRY_LOG.get());
        planksFromLogs(consumer, DABlocks.CONBERRY_PLANKS.get(), DATags.Items.CRAFTS_CONBERRY_PLANKS, 4);
        stairs(DABlocks.CONBERRY_STAIRS, DABlocks.CONBERRY_PLANKS).group("wooden_stairs").save(consumer);
        slab(DABlocks.CONBERRY_SLAB.get(), DABlocks.CONBERRY_PLANKS).group("wooden_slab").save(consumer);
        fence(DABlocks.CONBERRY_FENCE, DABlocks.CONBERRY_PLANKS).save(consumer);
        fenceGate(DABlocks.CONBERRY_FENCE_GATE, DABlocks.CONBERRY_PLANKS).save(consumer);
        doorBuilder(DABlocks.CONBERRY_DOOR.get(), Ingredient.of(DABlocks.CONBERRY_PLANKS.get())).unlockedBy(getHasName(DABlocks.CONBERRY_PLANKS.get()), has(DABlocks.CONBERRY_PLANKS.get())).group("wooden_door").save(consumer);
        trapdoorBuilder(DABlocks.CONBERRY_TRAPDOOR.get(), Ingredient.of(DABlocks.CONBERRY_PLANKS.get())).unlockedBy(getHasName(DABlocks.CONBERRY_PLANKS.get()), has(DABlocks.CONBERRY_PLANKS.get())).group("wooden_trapdoor").save(consumer);
        pressurePlateBuilder(RecipeCategory.REDSTONE, DABlocks.CONBERRY_PRESSURE_PLATE.get(), Ingredient.of(DABlocks.CONBERRY_PLANKS.get())).unlockedBy(getHasName(DABlocks.CONBERRY_PLANKS.get()), has(DABlocks.CONBERRY_PLANKS.get())).group("wooden_pressure_plate").save(consumer);
        buttonBuilder(DABlocks.CONBERRY_BUTTON.get(), Ingredient.of(DABlocks.CONBERRY_PLANKS.get())).unlockedBy(getHasName(DABlocks.CONBERRY_PLANKS.get()), has(DABlocks.CONBERRY_PLANKS.get())).group("wooden_button").save(consumer);
        sign(consumer, DAItems.CONBERRY_SIGN.get(), DABlocks.CONBERRY_PLANKS.get());
        makeHangingSign(consumer, DAItems.CONBERRY_HANGING_SIGN.get(), DABlocks.STRIPPED_CONBERRY_LOG.get());
        makeBoat(DAItems.CONBERRY_BOAT, DABlocks.CONBERRY_PLANKS.get()).save(consumer);
        makeChestBoat(DAItems.CONBERRY_CHEST_BOAT.get(), DAItems.CONBERRY_BOAT.get()).save(consumer);

        woodFromLogs(consumer, DABlocks.SUNROOT_WOOD.get(), DABlocks.SUNROOT_LOG.get());
        woodFromLogs(consumer, DABlocks.STRIPPED_SUNROOT_WOOD.get(), DABlocks.STRIPPED_SUNROOT_LOG.get());
        planksFromLogs(consumer, DABlocks.SUNROOT_PLANKS.get(), DATags.Items.CRAFTS_SUNROOT_PLANKS, 4);
        stairs(DABlocks.SUNROOT_STAIRS, DABlocks.SUNROOT_PLANKS).group("wooden_stairs").save(consumer);
        slab(DABlocks.SUNROOT_SLAB.get(), DABlocks.SUNROOT_PLANKS).group("wooden_slab").save(consumer);
        fence(DABlocks.SUNROOT_FENCE, DABlocks.SUNROOT_PLANKS).save(consumer);
        fenceGate(DABlocks.SUNROOT_FENCE_GATE, DABlocks.SUNROOT_PLANKS).save(consumer);
        doorBuilder(DABlocks.SUNROOT_DOOR.get(), Ingredient.of(DABlocks.SUNROOT_PLANKS.get())).unlockedBy(getHasName(DABlocks.SUNROOT_PLANKS.get()), has(DABlocks.SUNROOT_PLANKS.get())).group("wooden_door").save(consumer);
        trapdoorBuilder(DABlocks.SUNROOT_TRAPDOOR.get(), Ingredient.of(DABlocks.SUNROOT_PLANKS.get())).unlockedBy(getHasName(DABlocks.SUNROOT_PLANKS.get()), has(DABlocks.SUNROOT_PLANKS.get())).group("wooden_trapdoor").save(consumer);
        pressurePlateBuilder(RecipeCategory.REDSTONE, DABlocks.SUNROOT_PRESSURE_PLATE.get(), Ingredient.of(DABlocks.SUNROOT_PLANKS.get())).unlockedBy(getHasName(DABlocks.SUNROOT_PLANKS.get()), has(DABlocks.SUNROOT_PLANKS.get())).group("wooden_pressure_plate").save(consumer);
        buttonBuilder(DABlocks.SUNROOT_BUTTON.get(), Ingredient.of(DABlocks.SUNROOT_PLANKS.get())).unlockedBy(getHasName(DABlocks.SUNROOT_PLANKS.get()), has(DABlocks.SUNROOT_PLANKS.get())).group("wooden_button").save(consumer);
        sign(consumer, DAItems.SUNROOT_SIGN.get(), DABlocks.SUNROOT_PLANKS.get());
        makeHangingSign(consumer, DAItems.SUNROOT_HANGING_SIGN.get(), DABlocks.STRIPPED_SUNROOT_LOG.get());
        makeBoat(DAItems.SUNROOT_BOAT, DABlocks.SUNROOT_PLANKS.get()).save(consumer);
        makeChestBoat(DAItems.SUNROOT_CHEST_BOAT.get(), DAItems.SUNROOT_BOAT.get()).save(consumer);

        //Mud
        ShapelessRecipeBuilder.shapeless(RecipeCategory.DECORATIONS, DABlocks.PACKED_AETHER_MUD.get())
                .requires(DABlocks.AETHER_MUD.get())
                .requires(DAItems.AERGLOW_BLOSSOM.get())
                .unlockedBy(getHasName(DABlocks.AETHER_MUD.get()), has(DABlocks.AETHER_MUD.get()))
                .save(consumer);
        ShapelessRecipeBuilder.shapeless(RecipeCategory.DECORATIONS, DABlocks.MUDDY_YAGROOT_ROOTS.get())
                .requires(DABlocks.AETHER_MUD.get())
                .requires(DABlocks.YAGROOT_ROOTS.get())
                .unlockedBy(getHasName(DABlocks.AETHER_MUD.get()), has(DABlocks.AETHER_MUD.get()))
                .save(consumer);

        stonecuttingRecipe(consumer, RecipeCategory.DECORATIONS, DABlocks.AETHER_MUD_BRICKS_WALL.get(), DABlocks.AETHER_MUD_BRICKS.get());
        stonecuttingRecipe(consumer, RecipeCategory.BUILDING_BLOCKS, DABlocks.AETHER_MUD_BRICKS_STAIRS.get(), DABlocks.AETHER_MUD_BRICKS.get());
        stonecuttingRecipe(consumer, RecipeCategory.BUILDING_BLOCKS, DABlocks.AETHER_MUD_BRICKS_SLAB.get(), DABlocks.AETHER_MUD_BRICKS.get(), 2);

        brick(consumer, DABlocks.AETHER_MUD_BRICKS.get(), DABlocks.PACKED_AETHER_MUD.get());
        stairs(DABlocks.AETHER_MUD_BRICKS_STAIRS, DABlocks.AETHER_MUD_BRICKS).save(consumer);
        slab(consumer, RecipeCategory.BUILDING_BLOCKS, DABlocks.AETHER_MUD_BRICKS_SLAB.get(), DABlocks.AETHER_MUD_BRICKS.get());
        wall(consumer, RecipeCategory.DECORATIONS, DABlocks.AETHER_MUD_BRICKS_WALL.get(), DABlocks.AETHER_MUD_BRICKS.get());

        //Aseterite
        stonecuttingRecipe(consumer, RecipeCategory.BUILDING_BLOCKS, DABlocks.COBBLED_ASETERITE_STAIRS.get(), DABlocks.COBBLED_ASETERITE.get());
        stonecuttingRecipe(consumer, RecipeCategory.BUILDING_BLOCKS, DABlocks.COBBLED_ASETERITE_SLAB.get(), DABlocks.COBBLED_ASETERITE.get(), 2);
        stonecuttingRecipe(consumer, RecipeCategory.BUILDING_BLOCKS, DABlocks.COBBLED_ASETERITE_WALL.get(), DABlocks.COBBLED_ASETERITE.get());

        stonecuttingRecipe(consumer, RecipeCategory.BUILDING_BLOCKS, DABlocks.ASETERITE_STAIRS.get(), DABlocks.ASETERITE.get());
        stonecuttingRecipe(consumer, RecipeCategory.BUILDING_BLOCKS, DABlocks.ASETERITE_SLAB.get(), DABlocks.ASETERITE.get(), 2);
        stonecuttingRecipe(consumer, RecipeCategory.BUILDING_BLOCKS, DABlocks.ASETERITE_WALL.get(), DABlocks.ASETERITE.get());

        stonecuttingRecipe(consumer, RecipeCategory.BUILDING_BLOCKS, DABlocks.POLISHED_ASETERITE.get(), DABlocks.ASETERITE.get());
        stonecuttingRecipe(consumer, RecipeCategory.BUILDING_BLOCKS, DABlocks.POLISHED_ASETERITE_STAIRS.get(), DABlocks.ASETERITE.get());
        stonecuttingRecipe(consumer, RecipeCategory.BUILDING_BLOCKS, DABlocks.POLISHED_ASETERITE_STAIRS.get(), DABlocks.POLISHED_ASETERITE.get());
        stonecuttingRecipe(consumer, RecipeCategory.BUILDING_BLOCKS, DABlocks.POLISHED_ASETERITE_SLAB.get(), DABlocks.ASETERITE.get(), 2);
        stonecuttingRecipe(consumer, RecipeCategory.BUILDING_BLOCKS, DABlocks.POLISHED_ASETERITE_SLAB.get(), DABlocks.POLISHED_ASETERITE.get(), 2);
        stonecuttingRecipe(consumer, RecipeCategory.BUILDING_BLOCKS, DABlocks.POLISHED_ASETERITE_WALL.get(), DABlocks.ASETERITE.get());
        stonecuttingRecipe(consumer, RecipeCategory.BUILDING_BLOCKS, DABlocks.POLISHED_ASETERITE_WALL.get(), DABlocks.POLISHED_ASETERITE.get());

        stonecuttingRecipe(consumer, RecipeCategory.BUILDING_BLOCKS, DABlocks.ASETERITE_BRICKS.get(), DABlocks.ASETERITE.get());
        stonecuttingRecipe(consumer, RecipeCategory.BUILDING_BLOCKS, DABlocks.ASETERITE_BRICKS.get(), DABlocks.POLISHED_ASETERITE.get());
        stonecuttingRecipe(consumer, RecipeCategory.BUILDING_BLOCKS, DABlocks.ASETERITE_BRICKS_STAIRS.get(), DABlocks.ASETERITE.get());
        stonecuttingRecipe(consumer, RecipeCategory.BUILDING_BLOCKS, DABlocks.ASETERITE_BRICKS_STAIRS.get(), DABlocks.POLISHED_ASETERITE.get());
        stonecuttingRecipe(consumer, RecipeCategory.BUILDING_BLOCKS, DABlocks.ASETERITE_BRICKS_STAIRS.get(), DABlocks.ASETERITE_BRICKS.get());
        stonecuttingRecipe(consumer, RecipeCategory.BUILDING_BLOCKS, DABlocks.ASETERITE_BRICKS_SLAB.get(), DABlocks.ASETERITE.get(), 2);
        stonecuttingRecipe(consumer, RecipeCategory.BUILDING_BLOCKS, DABlocks.ASETERITE_BRICKS_SLAB.get(), DABlocks.POLISHED_ASETERITE.get(), 2);
        stonecuttingRecipe(consumer, RecipeCategory.BUILDING_BLOCKS, DABlocks.ASETERITE_BRICKS_SLAB.get(), DABlocks.ASETERITE_BRICKS.get(), 2);
        stonecuttingRecipe(consumer, RecipeCategory.BUILDING_BLOCKS, DABlocks.ASETERITE_BRICKS_WALL.get(), DABlocks.ASETERITE.get());
        stonecuttingRecipe(consumer, RecipeCategory.BUILDING_BLOCKS, DABlocks.ASETERITE_BRICKS_WALL.get(), DABlocks.POLISHED_ASETERITE.get());
        stonecuttingRecipe(consumer, RecipeCategory.BUILDING_BLOCKS, DABlocks.ASETERITE_BRICKS_WALL.get(), DABlocks.ASETERITE_BRICKS.get());

        stairs(DABlocks.COBBLED_ASETERITE_STAIRS, DABlocks.COBBLED_ASETERITE).save(consumer);
        slab(consumer, RecipeCategory.BUILDING_BLOCKS, DABlocks.COBBLED_ASETERITE_SLAB.get(), DABlocks.COBBLED_ASETERITE.get());
        wall(consumer, RecipeCategory.DECORATIONS, DABlocks.COBBLED_ASETERITE_WALL.get(), DABlocks.COBBLED_ASETERITE.get());

        smeltingBlockRecipe(DABlocks.ASETERITE.get(), DABlocks.COBBLED_ASETERITE.get(), 0.1F).save(consumer);
        stairs(DABlocks.ASETERITE_STAIRS, DABlocks.ASETERITE).save(consumer);
        slab(consumer, RecipeCategory.BUILDING_BLOCKS, DABlocks.ASETERITE_SLAB.get(), DABlocks.ASETERITE.get());
        wall(consumer, RecipeCategory.DECORATIONS, DABlocks.ASETERITE_WALL.get(), DABlocks.ASETERITE.get());

        brick(consumer, DABlocks.POLISHED_ASETERITE.get(), DABlocks.ASETERITE.get());
        stairs(DABlocks.POLISHED_ASETERITE_STAIRS, DABlocks.POLISHED_ASETERITE).save(consumer);
        slab(consumer, RecipeCategory.BUILDING_BLOCKS, DABlocks.POLISHED_ASETERITE_SLAB.get(), DABlocks.POLISHED_ASETERITE.get());
        wall(consumer, RecipeCategory.DECORATIONS, DABlocks.POLISHED_ASETERITE_WALL.get(), DABlocks.POLISHED_ASETERITE.get());

        brick(consumer, DABlocks.ASETERITE_BRICKS.get(), DABlocks.POLISHED_ASETERITE.get());
        stairs(DABlocks.ASETERITE_BRICKS_STAIRS, DABlocks.ASETERITE_BRICKS).save(consumer);
        slab(consumer, RecipeCategory.BUILDING_BLOCKS, DABlocks.ASETERITE_BRICKS_SLAB.get(), DABlocks.ASETERITE_BRICKS.get());
        wall(consumer, RecipeCategory.DECORATIONS, DABlocks.ASETERITE_BRICKS_WALL.get(), DABlocks.ASETERITE_BRICKS.get());

        //Clorite
        ShapedRecipeBuilder.shaped(RecipeCategory.DECORATIONS, DABlocks.RAW_CLORITE.get(), 4)
                .define('A', DABlocks.ASETERITE.get())
                .define('B', AetherBlocks.HOLYSTONE.get())
                .pattern("AB")
                .pattern("BA")
                .unlockedBy(getHasName(DABlocks.ASETERITE.get()), has(DABlocks.ASETERITE.get()))
                .save(consumer);
        ShapedRecipeBuilder.shaped(RecipeCategory.DECORATIONS, DABlocks.CLORITE_PILLAR.get(), 1)
                .define('A', DABlocks.CLORITE_SLAB.get())
                .pattern("A")
                .pattern("A")
                .unlockedBy(getHasName(DABlocks.CLORITE.get()), has(DABlocks.CLORITE.get()))
                .save(consumer);

        stonecuttingRecipe(consumer, RecipeCategory.BUILDING_BLOCKS, DABlocks.RAW_CLORITE_STAIRS.get(), DABlocks.RAW_CLORITE.get());
        stonecuttingRecipe(consumer, RecipeCategory.BUILDING_BLOCKS, DABlocks.RAW_CLORITE_SLAB.get(), DABlocks.RAW_CLORITE.get(), 2);
        stonecuttingRecipe(consumer, RecipeCategory.BUILDING_BLOCKS, DABlocks.RAW_CLORITE_WALL.get(), DABlocks.RAW_CLORITE.get());

        stonecuttingRecipe(consumer, RecipeCategory.BUILDING_BLOCKS, DABlocks.CLORITE_STAIRS.get(), DABlocks.CLORITE.get());
        stonecuttingRecipe(consumer, RecipeCategory.BUILDING_BLOCKS, DABlocks.CLORITE_SLAB.get(), DABlocks.CLORITE.get(), 2);
        stonecuttingRecipe(consumer, RecipeCategory.BUILDING_BLOCKS, DABlocks.CLORITE_WALL.get(), DABlocks.CLORITE.get());
        stonecuttingRecipe(consumer, RecipeCategory.BUILDING_BLOCKS, DABlocks.CLORITE_PILLAR.get(), DABlocks.CLORITE.get());

        stonecuttingRecipe(consumer, RecipeCategory.BUILDING_BLOCKS, DABlocks.POLISHED_CLORITE.get(), DABlocks.CLORITE.get());
        stonecuttingRecipe(consumer, RecipeCategory.BUILDING_BLOCKS, DABlocks.POLISHED_CLORITE_STAIRS.get(), DABlocks.CLORITE.get());
        stonecuttingRecipe(consumer, RecipeCategory.BUILDING_BLOCKS, DABlocks.POLISHED_CLORITE_STAIRS.get(), DABlocks.POLISHED_CLORITE.get());
        stonecuttingRecipe(consumer, RecipeCategory.BUILDING_BLOCKS, DABlocks.POLISHED_CLORITE_SLAB.get(), DABlocks.CLORITE.get(), 2);
        stonecuttingRecipe(consumer, RecipeCategory.BUILDING_BLOCKS, DABlocks.POLISHED_CLORITE_SLAB.get(), DABlocks.POLISHED_CLORITE.get(), 2);
        stonecuttingRecipe(consumer, RecipeCategory.BUILDING_BLOCKS, DABlocks.POLISHED_CLORITE_WALL.get(), DABlocks.CLORITE.get());
        stonecuttingRecipe(consumer, RecipeCategory.BUILDING_BLOCKS, DABlocks.POLISHED_CLORITE_WALL.get(), DABlocks.POLISHED_CLORITE.get());

        stairs(DABlocks.RAW_CLORITE_STAIRS, DABlocks.RAW_CLORITE).save(consumer);
        slab(consumer, RecipeCategory.BUILDING_BLOCKS, DABlocks.RAW_CLORITE_SLAB.get(), DABlocks.RAW_CLORITE.get());
        wall(consumer, RecipeCategory.DECORATIONS, DABlocks.RAW_CLORITE_WALL.get(), DABlocks.RAW_CLORITE.get());

        stairs(DABlocks.CLORITE_STAIRS, DABlocks.CLORITE).save(consumer);
        slab(consumer, RecipeCategory.BUILDING_BLOCKS, DABlocks.CLORITE_SLAB.get(), DABlocks.CLORITE.get());
        wall(consumer, RecipeCategory.DECORATIONS, DABlocks.CLORITE_WALL.get(), DABlocks.CLORITE.get());

        brick(consumer, DABlocks.POLISHED_CLORITE.get(), DABlocks.CLORITE.get());
        stairs(DABlocks.POLISHED_CLORITE_STAIRS, DABlocks.POLISHED_CLORITE).save(consumer);
        slab(consumer, RecipeCategory.BUILDING_BLOCKS, DABlocks.POLISHED_CLORITE_SLAB.get(), DABlocks.POLISHED_CLORITE.get());
        wall(consumer, RecipeCategory.DECORATIONS, DABlocks.POLISHED_CLORITE_WALL.get(), DABlocks.POLISHED_CLORITE.get());

        // Big Holystone Bricks
        stonecuttingRecipe(consumer, RecipeCategory.BUILDING_BLOCKS, DABlocks.BIG_HOLYSTONE_BRICKS.get(), AetherBlocks.HOLYSTONE.get());
        stonecuttingRecipe(consumer, RecipeCategory.BUILDING_BLOCKS, DABlocks.BIG_HOLYSTONE_BRICKS.get(), AetherBlocks.HOLYSTONE_BRICKS.get());
        stonecuttingRecipe(consumer, RecipeCategory.BUILDING_BLOCKS, DABlocks.BIG_HOLYSTONE_BRICKS.get(), DABlocks.HOLYSTONE_TILES.get());
        stonecuttingRecipe(consumer, RecipeCategory.BUILDING_BLOCKS, DABlocks.BIG_HOLYSTONE_BRICKS_STAIRS.get(), AetherBlocks.HOLYSTONE.get());
        stonecuttingRecipe(consumer, RecipeCategory.BUILDING_BLOCKS, DABlocks.BIG_HOLYSTONE_BRICKS_STAIRS.get(), AetherBlocks.HOLYSTONE_BRICKS.get());
        stonecuttingRecipe(consumer, RecipeCategory.BUILDING_BLOCKS, DABlocks.BIG_HOLYSTONE_BRICKS_STAIRS.get(), DABlocks.HOLYSTONE_TILES.get());
        stonecuttingRecipe(consumer, RecipeCategory.BUILDING_BLOCKS, DABlocks.BIG_HOLYSTONE_BRICKS_STAIRS.get(), DABlocks.BIG_HOLYSTONE_BRICKS.get());
        stonecuttingRecipe(consumer, RecipeCategory.BUILDING_BLOCKS, DABlocks.BIG_HOLYSTONE_BRICKS_SLAB.get(), AetherBlocks.HOLYSTONE.get());
        stonecuttingRecipe(consumer, RecipeCategory.BUILDING_BLOCKS, DABlocks.BIG_HOLYSTONE_BRICKS_SLAB.get(), AetherBlocks.HOLYSTONE_BRICKS.get());
        stonecuttingRecipe(consumer, RecipeCategory.BUILDING_BLOCKS, DABlocks.BIG_HOLYSTONE_BRICKS_SLAB.get(), DABlocks.HOLYSTONE_TILES.get());
        stonecuttingRecipe(consumer, RecipeCategory.BUILDING_BLOCKS, DABlocks.BIG_HOLYSTONE_BRICKS_SLAB.get(), DABlocks.BIG_HOLYSTONE_BRICKS.get());
        stonecuttingRecipe(consumer, RecipeCategory.BUILDING_BLOCKS, DABlocks.BIG_HOLYSTONE_BRICKS_WALL.get(), AetherBlocks.HOLYSTONE.get());
        stonecuttingRecipe(consumer, RecipeCategory.BUILDING_BLOCKS, DABlocks.BIG_HOLYSTONE_BRICKS_WALL.get(), AetherBlocks.HOLYSTONE_BRICKS.get());
        stonecuttingRecipe(consumer, RecipeCategory.BUILDING_BLOCKS, DABlocks.BIG_HOLYSTONE_BRICKS_WALL.get(), DABlocks.HOLYSTONE_TILES.get());
        stonecuttingRecipe(consumer, RecipeCategory.BUILDING_BLOCKS, DABlocks.BIG_HOLYSTONE_BRICKS_WALL.get(), DABlocks.BIG_HOLYSTONE_BRICKS.get());

        brick(consumer, DABlocks.BIG_HOLYSTONE_BRICKS.get(), DABlocks.HOLYSTONE_TILES.get());
        stairs(DABlocks.BIG_HOLYSTONE_BRICKS_STAIRS, DABlocks.BIG_HOLYSTONE_BRICKS).save(consumer);
        slab(consumer, RecipeCategory.BUILDING_BLOCKS, DABlocks.BIG_HOLYSTONE_BRICKS_SLAB.get(), DABlocks.BIG_HOLYSTONE_BRICKS.get());
        wall(consumer, RecipeCategory.DECORATIONS, DABlocks.BIG_HOLYSTONE_BRICKS_WALL.get(), DABlocks.BIG_HOLYSTONE_BRICKS.get());

        // Holystone Pillar
        stonecuttingRecipe(consumer, RecipeCategory.BUILDING_BLOCKS, DABlocks.HOLYSTONE_PILLAR.get(), AetherBlocks.HOLYSTONE.get());
        stonecuttingRecipe(consumer, RecipeCategory.BUILDING_BLOCKS, DABlocks.HOLYSTONE_PILLAR_UP.get(), AetherBlocks.HOLYSTONE.get());
        stonecuttingRecipe(consumer, RecipeCategory.BUILDING_BLOCKS, DABlocks.HOLYSTONE_PILLAR_DOWN.get(), AetherBlocks.HOLYSTONE.get());

        ShapedRecipeBuilder.shaped(RecipeCategory.DECORATIONS, DABlocks.HOLYSTONE_PILLAR.get(), 2)
                .define('A', AetherBlocks.HOLYSTONE.get())
                .pattern("A")
                .pattern("A")
                .unlockedBy(getHasName(DABlocks.HOLYSTONE_PILLAR.get()), has(DABlocks.HOLYSTONE_PILLAR.get()))
                .save(consumer);

        ShapedRecipeBuilder.shaped(RecipeCategory.DECORATIONS, DABlocks.HOLYSTONE_PILLAR_UP.get(), 2)
                .define('A', DABlocks.HOLYSTONE_PILLAR.get())
                .pattern("A")
                .pattern("A")
                .unlockedBy(getHasName(DABlocks.HOLYSTONE_PILLAR_UP.get()), has(DABlocks.HOLYSTONE_PILLAR_UP.get()))
                .save(consumer);

        ShapedRecipeBuilder.shaped(RecipeCategory.DECORATIONS, DABlocks.HOLYSTONE_PILLAR_DOWN.get(), 2)
                .define('A', DABlocks.HOLYSTONE_PILLAR_UP.get())
                .pattern("A")
                .pattern("A")
                .unlockedBy(getHasName(DABlocks.HOLYSTONE_PILLAR_DOWN.get()), has(DABlocks.HOLYSTONE_PILLAR_DOWN.get()))
                .save(consumer);

        // Chiseled Holystone
        ShapedRecipeBuilder.shaped(RecipeCategory.DECORATIONS, DABlocks.CHISELED_HOLYSTONE.get(), 1)
                .define('A', AetherBlocks.HOLYSTONE_SLAB.get())
                .pattern("A")
                .pattern("A")
                .unlockedBy(getHasName(DABlocks.CHISELED_HOLYSTONE.get()), has(DABlocks.CHISELED_HOLYSTONE.get()))
                .save(consumer);

        stonecuttingRecipe(consumer, RecipeCategory.BUILDING_BLOCKS, DABlocks.CHISELED_HOLYSTONE.get(), AetherBlocks.HOLYSTONE.get());

        // Mossy Holystone Bricks
        ShapelessRecipeBuilder.shapeless(RecipeCategory.DECORATIONS, DABlocks.MOSSY_HOLYSTONE_BRICKS.get(), 1)
                .group("mossy_holystone_bricks")
                .requires(AetherBlocks.HOLYSTONE_BRICKS.get())
                .requires(DATags.Items.CRAFTS_MOSSY_BLOCKS)
                .unlockedBy(getHasName(AetherBlocks.HOLYSTONE_BRICKS.get()), has(AetherBlocks.HOLYSTONE_BRICKS.get()))
                .save(consumer, name("mossy_holystone_bricks_from_mossy"));

        stonecuttingRecipe(consumer, RecipeCategory.BUILDING_BLOCKS, DABlocks.MOSSY_HOLYSTONE_BRICKS.get(), AetherBlocks.MOSSY_HOLYSTONE.get());
        stonecuttingRecipe(consumer, RecipeCategory.BUILDING_BLOCKS, DABlocks.MOSSY_HOLYSTONE_BRICK_STAIRS.get(), AetherBlocks.MOSSY_HOLYSTONE.get());
        stonecuttingRecipe(consumer, RecipeCategory.BUILDING_BLOCKS, DABlocks.MOSSY_HOLYSTONE_BRICK_STAIRS.get(), DABlocks.MOSSY_HOLYSTONE_BRICKS.get());
        stonecuttingRecipe(consumer, RecipeCategory.BUILDING_BLOCKS, DABlocks.MOSSY_HOLYSTONE_BRICK_SLAB.get(), AetherBlocks.MOSSY_HOLYSTONE.get(), 2);
        stonecuttingRecipe(consumer, RecipeCategory.BUILDING_BLOCKS, DABlocks.MOSSY_HOLYSTONE_BRICK_SLAB.get(), DABlocks.MOSSY_HOLYSTONE_BRICKS.get(), 2);
        stonecuttingRecipe(consumer, RecipeCategory.BUILDING_BLOCKS, DABlocks.MOSSY_HOLYSTONE_BRICK_WALL.get(), AetherBlocks.MOSSY_HOLYSTONE.get());
        stonecuttingRecipe(consumer, RecipeCategory.BUILDING_BLOCKS, DABlocks.MOSSY_HOLYSTONE_BRICK_WALL.get(), DABlocks.MOSSY_HOLYSTONE_BRICKS.get());

        brick(consumer, DABlocks.MOSSY_HOLYSTONE_BRICKS.get(), AetherBlocks.MOSSY_HOLYSTONE.get());
        stairs(DABlocks.MOSSY_HOLYSTONE_BRICK_STAIRS, DABlocks.MOSSY_HOLYSTONE_BRICKS).save(consumer);
        slab(consumer, RecipeCategory.BUILDING_BLOCKS, DABlocks.MOSSY_HOLYSTONE_BRICK_SLAB.get(), DABlocks.MOSSY_HOLYSTONE_BRICKS.get());
        wall(consumer, RecipeCategory.DECORATIONS, DABlocks.MOSSY_HOLYSTONE_BRICK_WALL.get(), DABlocks.MOSSY_HOLYSTONE_BRICKS.get());

        // Holystone Tiles
        stonecuttingRecipe(consumer, RecipeCategory.BUILDING_BLOCKS, DABlocks.HOLYSTONE_TILES.get(), AetherBlocks.HOLYSTONE.get());
        stonecuttingRecipe(consumer, RecipeCategory.BUILDING_BLOCKS, DABlocks.HOLYSTONE_TILES.get(), AetherBlocks.HOLYSTONE_BRICKS.get());
        stonecuttingRecipe(consumer, RecipeCategory.BUILDING_BLOCKS, DABlocks.HOLYSTONE_TILE_STAIRS.get(), AetherBlocks.HOLYSTONE.get());
        stonecuttingRecipe(consumer, RecipeCategory.BUILDING_BLOCKS, DABlocks.HOLYSTONE_TILE_STAIRS.get(), AetherBlocks.HOLYSTONE_BRICKS.get());
        stonecuttingRecipe(consumer, RecipeCategory.BUILDING_BLOCKS, DABlocks.HOLYSTONE_TILE_STAIRS.get(), DABlocks.HOLYSTONE_TILES.get());
        stonecuttingRecipe(consumer, RecipeCategory.BUILDING_BLOCKS, DABlocks.HOLYSTONE_TILE_SLAB.get(), AetherBlocks.HOLYSTONE.get(), 2);
        stonecuttingRecipe(consumer, RecipeCategory.BUILDING_BLOCKS, DABlocks.HOLYSTONE_TILE_SLAB.get(), AetherBlocks.HOLYSTONE_BRICKS.get(), 2);
        stonecuttingRecipe(consumer, RecipeCategory.BUILDING_BLOCKS, DABlocks.HOLYSTONE_TILE_SLAB.get(), DABlocks.HOLYSTONE_TILES.get(), 2);
        stonecuttingRecipe(consumer, RecipeCategory.BUILDING_BLOCKS, DABlocks.HOLYSTONE_TILE_WALL.get(), AetherBlocks.HOLYSTONE.get());
        stonecuttingRecipe(consumer, RecipeCategory.BUILDING_BLOCKS, DABlocks.HOLYSTONE_TILE_WALL.get(), AetherBlocks.HOLYSTONE_BRICKS.get());
        stonecuttingRecipe(consumer, RecipeCategory.BUILDING_BLOCKS, DABlocks.HOLYSTONE_TILE_WALL.get(), DABlocks.HOLYSTONE_TILES.get());

        brick(consumer, DABlocks.HOLYSTONE_TILES.get(), AetherBlocks.HOLYSTONE_BRICKS.get());
        stairs(DABlocks.HOLYSTONE_TILE_STAIRS, DABlocks.HOLYSTONE_TILES).save(consumer);
        slab(consumer, RecipeCategory.BUILDING_BLOCKS, DABlocks.HOLYSTONE_TILE_SLAB.get(), DABlocks.HOLYSTONE_TILES.get());
        wall(consumer, RecipeCategory.DECORATIONS, DABlocks.HOLYSTONE_TILE_WALL.get(), DABlocks.HOLYSTONE_TILES.get());

        // Mossy Holystone Tiles
        ShapelessRecipeBuilder.shapeless(RecipeCategory.DECORATIONS, DABlocks.MOSSY_HOLYSTONE_TILES.get(), 1)
                .group("mossy_holystone_tiles")
                .requires(DABlocks.HOLYSTONE_TILES.get())
                .requires(DATags.Items.CRAFTS_MOSSY_BLOCKS)
                .unlockedBy(getHasName(DABlocks.HOLYSTONE_TILES.get()), has(DABlocks.HOLYSTONE_TILES.get()))
                .save(consumer, name("mossy_holystone_tiles_from_mossy"));

        stonecuttingRecipe(consumer, RecipeCategory.BUILDING_BLOCKS, DABlocks.MOSSY_HOLYSTONE_TILES.get(), AetherBlocks.MOSSY_HOLYSTONE.get());
        stonecuttingRecipe(consumer, RecipeCategory.BUILDING_BLOCKS, DABlocks.MOSSY_HOLYSTONE_TILES.get(), DABlocks.MOSSY_HOLYSTONE_BRICKS.get());
        stonecuttingRecipe(consumer, RecipeCategory.BUILDING_BLOCKS, DABlocks.MOSSY_HOLYSTONE_TILE_STAIRS.get(), AetherBlocks.MOSSY_HOLYSTONE.get());
        stonecuttingRecipe(consumer, RecipeCategory.BUILDING_BLOCKS, DABlocks.MOSSY_HOLYSTONE_TILE_STAIRS.get(), DABlocks.MOSSY_HOLYSTONE_BRICKS.get());
        stonecuttingRecipe(consumer, RecipeCategory.BUILDING_BLOCKS, DABlocks.MOSSY_HOLYSTONE_TILE_STAIRS.get(), DABlocks.MOSSY_HOLYSTONE_TILES.get());
        stonecuttingRecipe(consumer, RecipeCategory.BUILDING_BLOCKS, DABlocks.MOSSY_HOLYSTONE_TILE_SLAB.get(), AetherBlocks.MOSSY_HOLYSTONE.get(), 2);
        stonecuttingRecipe(consumer, RecipeCategory.BUILDING_BLOCKS, DABlocks.MOSSY_HOLYSTONE_TILE_SLAB.get(), DABlocks.MOSSY_HOLYSTONE_BRICKS.get(), 2);
        stonecuttingRecipe(consumer, RecipeCategory.BUILDING_BLOCKS, DABlocks.MOSSY_HOLYSTONE_TILE_SLAB.get(), DABlocks.MOSSY_HOLYSTONE_TILES.get(), 2);
        stonecuttingRecipe(consumer, RecipeCategory.BUILDING_BLOCKS, DABlocks.MOSSY_HOLYSTONE_TILE_WALL.get(), AetherBlocks.MOSSY_HOLYSTONE.get());
        stonecuttingRecipe(consumer, RecipeCategory.BUILDING_BLOCKS, DABlocks.MOSSY_HOLYSTONE_TILE_WALL.get(), DABlocks.MOSSY_HOLYSTONE_BRICKS.get());
        stonecuttingRecipe(consumer, RecipeCategory.BUILDING_BLOCKS, DABlocks.MOSSY_HOLYSTONE_TILE_WALL.get(), DABlocks.MOSSY_HOLYSTONE_TILES.get());

        brick(consumer, DABlocks.MOSSY_HOLYSTONE_TILES.get(), DABlocks.MOSSY_HOLYSTONE_BRICKS.get());
        stairs(DABlocks.MOSSY_HOLYSTONE_TILE_STAIRS, DABlocks.MOSSY_HOLYSTONE_TILES).save(consumer);
        slab(consumer, RecipeCategory.BUILDING_BLOCKS, DABlocks.MOSSY_HOLYSTONE_TILE_SLAB.get(), DABlocks.MOSSY_HOLYSTONE_TILES.get());
        wall(consumer, RecipeCategory.DECORATIONS, DABlocks.MOSSY_HOLYSTONE_TILE_WALL.get(), DABlocks.MOSSY_HOLYSTONE_TILES.get());

        // Mossy Blocks
        ShapelessRecipeBuilder.shapeless(RecipeCategory.DECORATIONS, AetherBlocks.MOSSY_HOLYSTONE.get(), 1)
                .group("mossy_holystone")
                .requires(AetherBlocks.HOLYSTONE.get())
                .requires(DATags.Items.CRAFTS_MOSSY_BLOCKS)
                .unlockedBy(getHasName(AetherBlocks.HOLYSTONE.get()), has(AetherBlocks.HOLYSTONE.get()))
                .save(consumer, name("mossy_holystone"));

        ShapelessRecipeBuilder.shapeless(RecipeCategory.DECORATIONS, Blocks.MOSSY_COBBLESTONE, 1)
                .group("mossy_holystone")
                .requires(Blocks.COBBLESTONE)
                .requires(DATags.Items.CRAFTS_MOSSY_BLOCKS)
                .unlockedBy(getHasName(Blocks.COBBLESTONE), has(Blocks.COBBLESTONE))
                .save(consumer, name("mossy_cobblestone"));

        ShapelessRecipeBuilder.shapeless(RecipeCategory.DECORATIONS, Blocks.MOSSY_STONE_BRICKS, 1)
                .group("mossy_holystone")
                .requires(Blocks.STONE_BRICKS)
                .requires(DATags.Items.CRAFTS_MOSSY_BLOCKS)
                .unlockedBy(getHasName(Blocks.STONE_BRICKS), has(Blocks.STONE_BRICKS))
                .save(consumer, name("mossy_stone_bricks"));

        // Nimbus Stone
        stairs(DABlocks.NIMBUS_STAIRS, DABlocks.NIMBUS_STONE).save(consumer);
        slab(DABlocks.NIMBUS_SLAB.get(), DABlocks.NIMBUS_STONE).save(consumer);
        wall(consumer, RecipeCategory.DECORATIONS, DABlocks.NIMBUS_WALL.get(), DABlocks.NIMBUS_STONE.get());

        stonecuttingRecipe(consumer, RecipeCategory.DECORATIONS, DABlocks.NIMBUS_WALL.get(), DABlocks.NIMBUS_STONE.get());
        stonecuttingRecipe(consumer, RecipeCategory.BUILDING_BLOCKS, DABlocks.NIMBUS_STAIRS.get(), DABlocks.NIMBUS_STONE.get());
        stonecuttingRecipe(consumer, RecipeCategory.BUILDING_BLOCKS, DABlocks.NIMBUS_SLAB.get(), DABlocks.NIMBUS_STONE.get(), 2);

        stonecuttingRecipe(consumer, RecipeCategory.BUILDING_BLOCKS, DABlocks.NIMBUS_STONE.get(), DABlocks.NIMBUS_PILLAR.get());
        stonecuttingRecipe(consumer, RecipeCategory.BUILDING_BLOCKS, DABlocks.NIMBUS_STONE.get(), DABlocks.LIGHT_NIMBUS_STONE.get());
        stonecuttingRecipe(consumer, RecipeCategory.BUILDING_BLOCKS, DABlocks.NIMBUS_STONE.get(), DABlocks.LIGHT_NIMBUS_PILLAR.get());
        stonecuttingRecipe(consumer, RecipeCategory.BUILDING_BLOCKS, DABlocks.NIMBUS_PILLAR.get(), DABlocks.NIMBUS_STONE.get());
        stonecuttingRecipe(consumer, RecipeCategory.BUILDING_BLOCKS, DABlocks.NIMBUS_PILLAR.get(), DABlocks.LIGHT_NIMBUS_STONE.get());
        stonecuttingRecipe(consumer, RecipeCategory.BUILDING_BLOCKS, DABlocks.NIMBUS_PILLAR.get(), DABlocks.LIGHT_NIMBUS_PILLAR.get());
        stonecuttingRecipe(consumer, RecipeCategory.BUILDING_BLOCKS, DABlocks.LIGHT_NIMBUS_STONE.get(), DABlocks.NIMBUS_STONE.get());
        stonecuttingRecipe(consumer, RecipeCategory.BUILDING_BLOCKS, DABlocks.LIGHT_NIMBUS_STONE.get(), DABlocks.NIMBUS_PILLAR.get());
        stonecuttingRecipe(consumer, RecipeCategory.BUILDING_BLOCKS, DABlocks.LIGHT_NIMBUS_STONE.get(), DABlocks.LIGHT_NIMBUS_PILLAR.get());
        stonecuttingRecipe(consumer, RecipeCategory.BUILDING_BLOCKS, DABlocks.LIGHT_NIMBUS_PILLAR.get(), DABlocks.NIMBUS_STONE.get());
        stonecuttingRecipe(consumer, RecipeCategory.BUILDING_BLOCKS, DABlocks.LIGHT_NIMBUS_PILLAR.get(), DABlocks.NIMBUS_PILLAR.get());
        stonecuttingRecipe(consumer, RecipeCategory.BUILDING_BLOCKS, DABlocks.LIGHT_NIMBUS_PILLAR.get(), DABlocks.LIGHT_NIMBUS_STONE.get());

        // Skyjade
        smeltingOreRecipe(DAItems.SKYJADE.get(), DABlocks.SKYJADE_ORE.get(), 1F).save(consumer);
        blastingOreRecipe(DAItems.SKYJADE.get(), DABlocks.SKYJADE_ORE.get(), 0.5F).save(consumer, name("skjyade_from_blasting"));

        makeFullBlock(DAItems.SKYJADE.get(), DABlocks.SKYJADE_BLOCK.get()).save(consumer, name("skyjade_block_from_skyjade"));
        materialFromBlock(DABlocks.SKYJADE_BLOCK.get(), DAItems.SKYJADE.get()).save(consumer, name("skyjade_from_skyjade_block"));

        ShapelessRecipeBuilder.shapeless(RecipeCategory.DECORATIONS, DAItems.SKYJADE_NUGGET.get(), 9)
                .requires(DAItems.SKYJADE.get())
                .unlockedBy(getHasName(DAItems.SKYJADE.get()), has(DAItems.SKYJADE.get()))
                .save(consumer);

        ShapedRecipeBuilder.shaped(RecipeCategory.DECORATIONS, DAItems.SKYJADE.get())
                .define('A', DAItems.SKYJADE_NUGGET.get())
                .pattern("AAA")
                .pattern("AAA")
                .pattern("AAA")
                .unlockedBy(getHasName(DAItems.SKYJADE_NUGGET.get()), has(DAItems.SKYJADE_NUGGET.get()))
                .save(consumer, name("skyjade_from_skyjade_nuggets"));

        ShapedRecipeBuilder.shaped(RecipeCategory.DECORATIONS, DABlocks.SKYJADE_LANTERN.get())
                .define('A', DAItems.SKYJADE_NUGGET.get())
                .define('B', AetherBlocks.AMBROSIUM_TORCH.get())
                .pattern("AAA")
                .pattern("ABA")
                .pattern("AAA")
                .unlockedBy(getHasName(DAItems.SKYJADE_NUGGET.get()), has(DAItems.SKYJADE_NUGGET.get()))
                .save(consumer, name("skyjade_lantern_from_skyjade_nuggets"));

        ShapedRecipeBuilder.shaped(RecipeCategory.DECORATIONS, DABlocks.AMBROSIUM_TIKI_TORCH.get())
                .define('A', AetherItems.AMBROSIUM_SHARD.get())
                .define('B', AetherItems.SKYROOT_STICK.get())
                .define('C', DAItems.CLOUDBLOOM_BOUQUET.get())
                .pattern(" CA")
                .pattern(" BC")
                .pattern("B  ")
                .unlockedBy(getHasName(AetherItems.AMBROSIUM_SHARD.get()), has(AetherItems.AMBROSIUM_SHARD.get()))
                .save(consumer, name("ambrosium_tiki_torch"));

        ShapedRecipeBuilder.shaped(RecipeCategory.DECORATIONS, DABlocks.SKYJADE_CHAIN.get())
                .define('A', DAItems.SKYJADE_NUGGET.get())
                .define('B', AetherItems.SKYROOT_STICK.get())
                .pattern("A")
                .pattern("B")
                .pattern("A")
                .unlockedBy(getHasName(DAItems.SKYJADE_NUGGET.get()), has(DAItems.SKYJADE_NUGGET.get()))
                .save(consumer, name("skyjade_chain_from_skyjade_nuggets"));

        repairingRecipe(RecipeCategory.COMBAT, DAItems.SKYJADE_TOOLS_SWORD.get(), 750).group("altar_sword_repair").save(consumer, name("skyjade_sword_repairing"));
        repairingRecipe(RecipeCategory.COMBAT, DAItems.SKYJADE_TOOLS_AXE.get(), 750).group("altar_sword_repair").save(consumer, name("skyjade_axe_repairing"));
        repairingRecipe(RecipeCategory.COMBAT, DAItems.SKYJADE_TOOLS_PICKAXE.get(), 750).group("altar_sword_repair").save(consumer, name("skyjade_pickaxe_repairing"));
        repairingRecipe(RecipeCategory.COMBAT, DAItems.SKYJADE_TOOLS_SHOVEL.get(), 750).group("altar_sword_repair").save(consumer, name("skyjade_shovel_repairing"));
        repairingRecipe(RecipeCategory.COMBAT, DAItems.SKYJADE_TOOLS_HOE.get(), 750).group("altar_sword_repair").save(consumer, name("skyjade_hoe_repairing"));
        repairingRecipe(RecipeCategory.COMBAT, DAItems.SKYJADE_BOOTS.get(), 750).group("altar_sword_repair").save(consumer, name("skyjade_boots_repairing"));
        repairingRecipe(RecipeCategory.COMBAT, DAItems.SKYJADE_LEGGINGS.get(), 750).group("altar_sword_repair").save(consumer, name("skyjade_leggings_repairing"));
        repairingRecipe(RecipeCategory.COMBAT, DAItems.SKYJADE_CHESTPLATE.get(), 750).group("altar_sword_repair").save(consumer, name("skyjade_chestplate_repairing"));
        repairingRecipe(RecipeCategory.COMBAT, DAItems.SKYJADE_HELMET.get(), 750).group("altar_sword_repair").save(consumer, name("skyjade_helmet_repairing"));
        repairingRecipe(RecipeCategory.COMBAT, DAItems.SKYJADE_GLOVES.get(), 750).group("altar_sword_repair").save(consumer, name("skyjade_gloves_repairing"));

        makeSword(DAItems.SKYJADE_TOOLS_SWORD, DAItems.SKYJADE).save(consumer);
        makeAxe(DAItems.SKYJADE_TOOLS_AXE, DAItems.SKYJADE).save(consumer);
        makePickaxe(DAItems.SKYJADE_TOOLS_PICKAXE, DAItems.SKYJADE).save(consumer);
        makeShovel(DAItems.SKYJADE_TOOLS_SHOVEL, DAItems.SKYJADE).save(consumer);
        makeHoe(DAItems.SKYJADE_TOOLS_HOE, DAItems.SKYJADE).save(consumer);

        makeBoots(DAItems.SKYJADE_BOOTS, DAItems.SKYJADE).save(consumer);
        makeLeggings(DAItems.SKYJADE_LEGGINGS, DAItems.SKYJADE).save(consumer);
        makeChestplate(DAItems.SKYJADE_CHESTPLATE, DAItems.SKYJADE).save(consumer);
        makeHelmet(DAItems.SKYJADE_HELMET, DAItems.SKYJADE).save(consumer);
        makeRing(DAItems.SKYJADE_RING, DAItems.SKYJADE.get()).save(consumer);
        makeGloves(DAItems.SKYJADE_GLOVES, DAItems.SKYJADE).save(consumer);

        // Stratus
        ShapelessRecipeBuilder.shapeless(RecipeCategory.DECORATIONS, DAItems.STRATUS_INGOT.get())
                .requires(DABlocks.CHROMATIC_AERCLOUD.get(), 5)
                .requires(AetherTags.Items.PROCESSED_GRAVITITE)
                .requires(AetherItems.ZANITE_GEMSTONE.get())
                .requires(AetherItems.AMBROSIUM_SHARD.get())
                .requires(DAItems.SKYJADE.get())
                .unlockedBy(getHasName(DABlocks.STERLING_AERCLOUD.get()), has(DABlocks.STERLING_AERCLOUD.get()))
                .save(consumer);

        makeFullBlock(DAItems.STRATUS_INGOT.get(), DABlocks.STRATUS_BLOCK.get()).save(consumer, name("stratus_block_from_stratus"));
        materialFromBlock(DABlocks.STRATUS_BLOCK.get(), DAItems.STRATUS_INGOT.get()).save(consumer, name("stratus_from_stratus_block"));

        repairingRecipe(RecipeCategory.COMBAT, DAItems.STRATUS_SWORD.get(), 1500).group("altar_sword_repair").save(consumer, name("stratus_sword_repairing"));
        repairingRecipe(RecipeCategory.COMBAT, DAItems.STRATUS_AXE.get(), 1500).group("altar_sword_repair").save(consumer, name("stratus_axe_repairing"));
        repairingRecipe(RecipeCategory.COMBAT, DAItems.STRATUS_PICKAXE.get(), 1500).group("altar_sword_repair").save(consumer, name("stratus_pickaxe_repairing"));
        repairingRecipe(RecipeCategory.COMBAT, DAItems.STRATUS_SHOVEL.get(), 1500).group("altar_sword_repair").save(consumer, name("stratus_shovel_repairing"));
        repairingRecipe(RecipeCategory.COMBAT, DAItems.STRATUS_HOE.get(), 1500).group("altar_sword_repair").save(consumer, name("stratus_hoe_repairing"));
        repairingRecipe(RecipeCategory.COMBAT, DAItems.STRATUS_BOOTS.get(), 1500).group("altar_sword_repair").save(consumer, name("stratus_boots_repairing"));
        repairingRecipe(RecipeCategory.COMBAT, DAItems.STRATUS_LEGGINGS.get(), 1500).group("altar_sword_repair").save(consumer, name("stratus_leggings_repairing"));
        repairingRecipe(RecipeCategory.COMBAT, DAItems.STRATUS_CHESTPLATE.get(), 1500).group("altar_sword_repair").save(consumer, name("stratus_chestplate_repairing"));
        repairingRecipe(RecipeCategory.COMBAT, DAItems.STRATUS_HELMET.get(), 1500).group("altar_sword_repair").save(consumer, name("stratus_helmet_repairing"));
        repairingRecipe(RecipeCategory.COMBAT, DAItems.STRATUS_GLOVES.get(), 1500).group("altar_sword_repair").save(consumer, name("stratus_gloves_repairing"));

        stratusSmithingRecipe(consumer, AetherItems.GRAVITITE_SWORD.get(), RecipeCategory.COMBAT, DAItems.STRATUS_SWORD.get());
        stratusSmithingRecipe(consumer, AetherItems.GRAVITITE_AXE.get(), RecipeCategory.COMBAT, DAItems.STRATUS_AXE.get());
        stratusSmithingRecipe(consumer, AetherItems.GRAVITITE_PICKAXE.get(), RecipeCategory.COMBAT, DAItems.STRATUS_PICKAXE.get());
        stratusSmithingRecipe(consumer, AetherItems.GRAVITITE_SHOVEL.get(), RecipeCategory.COMBAT, DAItems.STRATUS_SHOVEL.get());
        stratusSmithingRecipe(consumer, AetherItems.GRAVITITE_HOE.get(), RecipeCategory.COMBAT, DAItems.STRATUS_HOE.get());

        stratusSmithingRecipe(consumer, AetherItems.GRAVITITE_BOOTS.get(), RecipeCategory.COMBAT, DAItems.STRATUS_BOOTS.get());
        stratusSmithingRecipe(consumer, AetherItems.GRAVITITE_LEGGINGS.get(), RecipeCategory.COMBAT, DAItems.STRATUS_LEGGINGS.get());
        stratusSmithingRecipe(consumer, AetherItems.GRAVITITE_CHESTPLATE.get(), RecipeCategory.COMBAT, DAItems.STRATUS_CHESTPLATE.get());
        stratusSmithingRecipe(consumer, AetherItems.GRAVITITE_HELMET.get(), RecipeCategory.COMBAT, DAItems.STRATUS_HELMET.get());
        stratusSmithingRecipe(consumer, AetherItems.GRAVITITE_GLOVES.get(), RecipeCategory.COMBAT, DAItems.STRATUS_GLOVES.get());
        stratusSmithingRecipe(consumer, DAItems.GRAVITITE_RING.get(), RecipeCategory.COMBAT, DAItems.STRATUS_RING.get());

        // Food Recipes
        smeltingFoodRecipe(DAItems.COOKED_QUAIL.get(), DAItems.RAW_QUAIL.get(), 0.35F).save(consumer);
        smeltingFoodRecipe(DAItems.COOKED_AERGLOW_FISH.get(), DAItems.RAW_AERGLOW_FISH.get(), 0.35F).save(consumer);

        SmokingFoodRecipe(DAItems.COOKED_QUAIL.get(), DAItems.RAW_QUAIL.get(), 0.35F).save(consumer, name("cooked_quail_from_smoker"));
        SmokingFoodRecipe(DAItems.COOKED_AERGLOW_FISH.get(), DAItems.RAW_AERGLOW_FISH.get(), 0.35F).save(consumer, name("cooked_aerglow_fish_from_smoker"));

        ShapelessRecipeBuilder.shapeless(RecipeCategory.FOOD, DAItems.BLUE_SQUASH_SLICE.get(), 4)
                .requires(DABlocks.BLUE_SQUASH.get(), 1)
                .unlockedBy(getHasName(DABlocks.BLUE_SQUASH.get()), has(DABlocks.BLUE_SQUASH.get()))
                .save(consumer, name("slice_from_blue_squash"));

        ShapelessRecipeBuilder.shapeless(RecipeCategory.FOOD, DAItems.GREEN_SQUASH_SLICE.get(), 4)
                .requires(DABlocks.GREEN_SQUASH.get(), 1)
                .unlockedBy(getHasName(DABlocks.GREEN_SQUASH.get()), has(DABlocks.GREEN_SQUASH.get()))
                .save(consumer, name("slice_from_green_squash"));

        ShapelessRecipeBuilder.shapeless(RecipeCategory.FOOD, DAItems.PURPLE_SQUASH_SLICE.get(), 4)
                .requires(DABlocks.PURPLE_SQUASH.get(), 1)
                .unlockedBy(getHasName(DABlocks.PURPLE_SQUASH.get()), has(DABlocks.PURPLE_SQUASH.get()))
                .save(consumer, name("slice_from_purple_squash"));

        ShapelessRecipeBuilder.shapeless(RecipeCategory.FOOD, DAItems.SQUASH_SEEDS.get(), 1)
                .requires(Ingredient.of(DATags.Items.SQUASH_SLICE), 1)
                .unlockedBy(getHasName(DATags.Items.SQUASH_SLICE), has(DATags.Items.SQUASH_SLICE))
                .save(consumer, name("seeds_from_squash_slice"));

        ShapedRecipeBuilder.shaped(RecipeCategory.FOOD, DABlocks.BLUE_SQUASH.get(), 1)
                .define('V', DAItems.BLUE_SQUASH_SLICE.get())
                .pattern("VV")
                .pattern("VV")
                .unlockedBy(getHasName(DABlocks.BLUE_SQUASH.get()), has(DABlocks.BLUE_SQUASH.get()))
                .save(consumer, name("blue_squash_from_slices"));

        ShapedRecipeBuilder.shaped(RecipeCategory.FOOD, DABlocks.GREEN_SQUASH.get(), 1)
                .define('V', DAItems.GREEN_SQUASH_SLICE.get())
                .pattern("VV")
                .pattern("VV")
                .unlockedBy(getHasName(DABlocks.GREEN_SQUASH.get()), has(DABlocks.GREEN_SQUASH.get()))
                .save(consumer, name("green_squash_from_slices"));

        ShapedRecipeBuilder.shaped(RecipeCategory.FOOD, DABlocks.PURPLE_SQUASH.get(), 1)
                .define('V', DAItems.PURPLE_SQUASH_SLICE.get())
                .pattern("VV")
                .pattern("VV")
                .unlockedBy(getHasName(DABlocks.PURPLE_SQUASH.get()), has(DABlocks.PURPLE_SQUASH.get()))
                .save(consumer, name("purple_squash_from_slices"));

        ShapedRecipeBuilder.shaped(RecipeCategory.FOOD, Items.CAKE)
                .group("minecraft:cake")
                .define('U', Tags.Items.BUCKETS_MILK)
                .define('S', Items.SUGAR)
                .define('Y', Items.WHEAT)
                .define('O', Tags.Items.EGGS)
                .pattern("UUU")
                .pattern("SOS")
                .pattern("YYY")
                .unlockedBy(getHasName(DAItems.QUAIL_EGG.get()), has(Tags.Items.EGGS))
                .save(consumer, name("cake"));

        ShapelessRecipeBuilder.shapeless(RecipeCategory.FOOD, Items.PUMPKIN_PIE)
                .group("minecraft:pumpkin_pie")
                .requires(Tags.Items.EGGS)
                .requires(Items.PUMPKIN)
                .requires(Items.SUGAR)
                .unlockedBy(getHasName(DAItems.QUAIL_EGG.get()), has(Tags.Items.EGGS))
                .save(consumer, name("pumpkin_pie"));

        // Dyes
        dye(consumer, Items.CYAN_DYE, DABlocks.AETHER_CATTAILS.get());
        dye(consumer, Items.CYAN_DYE, DABlocks.TALL_AETHER_CATTAILS.get(), 2);
        dye(consumer, Items.PINK_DYE, DABlocks.AERLAVENDER.get());
        dye(consumer, Items.PINK_DYE, DABlocks.TALL_AERLAVENDER.get(), 2);
        dye(consumer, Items.PURPLE_DYE, DABlocks.RADIANT_ORCHID.get());
        dye(consumer, Items.ORANGE_DYE, DABlocks.GOLDEN_FLOWER.get(), 2);
        dye(consumer, Items.WHITE_DYE, DABlocks.ENCHANTED_BLOSSOM.get());
        dye(consumer, Items.RED_DYE, DABlocks.SKY_TULIPS.get());
        dye(consumer, Items.BLUE_DYE, DABlocks.IASPOVE.get());
        dye(consumer, Items.ORANGE_DYE, DABlocks.GOLDEN_ASPESS.get());
        dye(consumer, Items.PURPLE_DYE, DABlocks.ECHAISY.get());

        SpecialRecipeBuilder.special(FloatyScarfColoring::new).save(consumer, name("floaty_scarf_coloring"));

        // Treasure Reforging Compat
        copyTemplate(consumer, DAItems.STORMFORGED_SMITHING_TEMPLATE.get(), DABlocks.NIMBUS_STONE.get());
        copyTemplateGravitite(consumer, DAItems.STORMFORGED_SMITHING_TEMPLATE.get(), DABlocks.NIMBUS_STONE.get());

        makeFullBlock(DAItems.SQUALL_PLATE.get(), DABlocks.SQUALL_BLOCK.get()).save(consumer, name("squall_block_from_squall"));
        materialFromBlock(DABlocks.SQUALL_BLOCK.get(), DAItems.SQUALL_PLATE.get()).save(consumer, name("squall_from_squall_block"));

        SimpleCookingRecipeBuilder.smelting(
                        Ingredient.of(DAItems.STORMFORGED_BOOTS.get(), DAItems.STORMFORGED_LEGGINGS.get(), DAItems.STORMFORGED_CHESTPLATE.get(), DAItems.STORMFORGED_HELMET.get(), DAItems.STORMFORGED_GLOVES.get()),
                        RecipeCategory.MISC,
                        DAItems.SQUALL_PLATE.get(),
                        0.1F,
                        200)
                .unlockedBy("has_boots", has(DAItems.STORMFORGED_BOOTS.get()))
                .unlockedBy("has_leggings", has(DAItems.STORMFORGED_LEGGINGS.get()))
                .unlockedBy("has_chestplate", has(DAItems.STORMFORGED_CHESTPLATE.get()))
                .unlockedBy("has_helmet", has(DAItems.STORMFORGED_HELMET.get()))
                .unlockedBy("has_gloves", has(DAItems.STORMFORGED_GLOVES.get()))
                .group(getSmeltingRecipeName(DAItems.SQUALL_PLATE.get()))
                .save(consumer, name(getSmeltingRecipeName(DAItems.SQUALL_PLATE.get())));

        stormSmithingRecipe(consumer, DAItems.SKYJADE_BOOTS.get(), RecipeCategory.COMBAT, DAItems.STORMFORGED_BOOTS.get());
        stormSmithingRecipe(consumer, DAItems.SKYJADE_LEGGINGS.get(), RecipeCategory.COMBAT, DAItems.STORMFORGED_LEGGINGS.get());
        stormSmithingRecipe(consumer, DAItems.SKYJADE_CHESTPLATE.get(), RecipeCategory.COMBAT, DAItems.STORMFORGED_CHESTPLATE.get());
        stormSmithingRecipe(consumer, DAItems.SKYJADE_HELMET.get(), RecipeCategory.COMBAT, DAItems.STORMFORGED_HELMET.get());
        stormSmithingRecipe(consumer, DAItems.SKYJADE_GLOVES.get(), RecipeCategory.COMBAT, DAItems.STORMFORGED_GLOVES.get());
        stormSmithingRecipe(consumer, DAItems.SKYJADE_TOOLS_SWORD.get(), RecipeCategory.COMBAT, DAItems.STORM_SWORD.get());
        stormSmithingRecipe(consumer, Items.BOW, RecipeCategory.COMBAT, DAItems.STORM_BOW.get());

        //Misc
        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, DABlocks.AETHER_COARSE_DIRT.get(), 4)
                .define('D', AetherBlocks.AETHER_DIRT.get().asItem())
                .define('G', Blocks.GRAVEL)
                .pattern("DG")
                .pattern("GD")
                .unlockedBy(getHasName(AetherBlocks.AETHER_DIRT.get()), has(AetherBlocks.AETHER_DIRT.get()))
                .save(consumer, name("aether_coarse_dirt"));

        ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, DAItems.AFTERBURNER.get(), 1)
                .define('O', DAItems.SUN_CORE.get())
                .define('P', Blocks.OBSIDIAN)
                .pattern(" P ")
                .pattern("POP")
                .pattern(" P ")
                .unlockedBy(getHasName(DAItems.SUN_CORE.get()), has(DAItems.SUN_CORE.get()))
                .save(consumer, name("afterburner"));

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, DABlocks.COMBINER.get(), 1)
                .define('O', DAItems.SKYJADE.get())
                .define('W', AetherBlocks.SKYROOT_PLANKS.get())
                .define('H', AetherBlocks.HOLYSTONE.get())
                .define('A', AetherItems.AMBROSIUM_SHARD.get())
                .pattern("WHW")
                .pattern("OAO")
                .pattern("HOH")
                .unlockedBy(getHasName(DAItems.SKYJADE.get()), has(DAItems.SKYJADE.get()))
                .save(consumer, name("combiner"));

        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, DAItems.GOLDEN_SWET_BALL.get())
                .requires(DAItems.GOLDEN_GRASS_SEEDS.get())
                .requires(AetherItems.SWET_BALL.get())
                .unlockedBy(getHasName(DAItems.GOLDEN_GRASS_SEEDS.get()), has(DAItems.GOLDEN_GRASS_SEEDS.get()))
                .save(consumer);

        ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, AetherItems.POISON_DART.get(), 8)
                .define('D', AetherItems.GOLDEN_DART.get())
                .define('B', DATags.Items.POISON_BUCKET)
                .pattern("DDD")
                .pattern("DBD")
                .pattern("DDD")
                .unlockedBy(getHasName(AetherItems.GOLDEN_DART.get()), has(AetherItems.GOLDEN_DART.get()))
                .unlockedBy(getHasName(DATags.Items.POISON_BUCKET), has(DATags.Items.POISON_BUCKET))
                .save(consumer, name("poison_dart"));

        ShapedRecipeBuilder.shaped(RecipeCategory.DECORATIONS, Blocks.CRAFTING_TABLE, 1)
                .group("minecraft:crafting_table")
                .define('P', AetherTags.Items.PLANKS_CRAFTING)
                .pattern("PP")
                .pattern("PP")
                .unlockedBy(getHasName(AetherBlocks.SKYROOT_PLANKS.get()), has(AetherBlocks.SKYROOT_PLANKS.get()))
                .save(consumer, name("skyroot_crafting_table"));

        ShapedRecipeBuilder.shaped(RecipeCategory.DECORATIONS, DABlocks.AERGLOW_BLOSSOM_BLOCK.get(), 1)
                .define('A', DAItems.AERGLOW_BLOSSOM.get())
                .pattern("AA")
                .pattern("AA")
                .unlockedBy(getHasName(DAItems.AERGLOW_BLOSSOM.get()), has(DAItems.AERGLOW_BLOSSOM.get()))
                .save(consumer);

        ShapedRecipeBuilder.shaped(RecipeCategory.DECORATIONS, DABlocks.AETHER_MOSS_CARPET.get(), 3)
                .define('A', DABlocks.AETHER_MOSS_BLOCK.get())
                .pattern("AA")
                .unlockedBy(getHasName(DABlocks.AETHER_MOSS_BLOCK.get()), has(DABlocks.AETHER_MOSS_BLOCK.get()))
                .save(consumer);

        ShapedRecipeBuilder.shaped(RecipeCategory.DECORATIONS, DABlocks.CLOUDBLOOM_CARPET.get(), 1)
                .define('A', DAItems.CLOUDBLOOM_BOUQUET.get())
                .pattern("AA")
                .unlockedBy(getHasName(DAItems.CLOUDBLOOM_BOUQUET.get()), has(DAItems.CLOUDBLOOM_BOUQUET.get()))
                .save(consumer);

        ShapedRecipeBuilder.shaped(RecipeCategory.DECORATIONS, Blocks.BREWING_STAND, 1)
                .group("minecraft:brewing_stand")
                .define('I', DAItems.BIO_CRYSTAL.get())
                .define('D', AetherBlocks.HOLYSTONE.get())
                .pattern(" I ")
                .pattern("DDD")
                .unlockedBy(getHasName(DAItems.BIO_CRYSTAL.get()), has(DAItems.BIO_CRYSTAL.get()))
                .save(consumer, name("crystal_brewing_stand"));

        copyTemplate(consumer, DAItems.STRATUS_SMITHING_TEMPLATE.get(), AetherBlocks.HOLYSTONE.get());
        copyTemplateGravitite(consumer, DAItems.STRATUS_SMITHING_TEMPLATE.get(), AetherBlocks.HOLYSTONE.get());

        makeRing(DAItems.GRAVITITE_RING, AetherTags.Items.PROCESSED_GRAVITITE).save(consumer);

        // Swet Recipes
        goldBallRecipe(DABlocks.GOLDEN_GRASS_BLOCK.get(), AetherBlocks.AETHER_DIRT.get()).save(consumer, name("golden_grass_block_from_aether_dirt"));
        goldBallRecipe(DABlocks.GOLDEN_GRASS_BLOCK.get(), AetherBlocks.AETHER_GRASS_BLOCK.get()).save(consumer, name("golden_grass_block_from_aether_grass_block"));

        // Spore Recipes
        glowingSporesRecipe(DABlocks.TALL_GLOWING_GRASS.get(), Blocks.TALL_GRASS).save(consumer, name("glowing_tall_grass_from_grass"));
        glowingSporesRecipe(DABlocks.GLOWING_VINE.get(), Blocks.VINE).save(consumer, name("glowing_vine_from_vine"));

        // Freezer Recipes
        freezingRecipe(RecipeCategory.MISC, DAItems.FROZEN_GOLDEN_BERRIES.get(), DAItems.GOLDEN_BERRIES.get(), 1, 50).save(consumer, name("golden_berries_freezing"));

        // Altar Recipes
        enchantingRecipe(RecipeCategory.MISC, DAItems.ENCHANTED_ANTIDOTE.get(), DAItems.ANTIDOTE.get(), 1, 750).save(consumer, name("antidote_enchanting"));
        enchantingRecipe(RecipeCategory.MISC, Items.WHEAT_SEEDS, DABlocks.GLOWING_SPORES.get(), 1, 50).save(consumer, name("glowing_spores"));
        enchantingRecipe(RecipeCategory.MISC, DAItems.SQUASH_SEEDS.get(), Items.PUMPKIN_SEEDS, 5, 50).save(consumer, name("squash_seeds_enchanting"));
        enchantingRecipe(RecipeCategory.MISC, DABlocks.CLORITE.get(), DABlocks.RAW_CLORITE.get(), 0.15F, 50).save(consumer, name("raw_clorite_enchanting"));
        enchantingRecipe(RecipeCategory.MISC, AetherBlocks.COLD_AERCLOUD.get(), DABlocks.AERCLOUD_ROOTS.get(), 1, 50).save(consumer, name("cold_aercloud_from_aercloud_roots"));
        enchantingRecipe(RecipeCategory.MISC, AetherBlocks.BLUE_AERCLOUD.get(), DABlocks.BLUE_AERCLOUD_MUSHROOM_BLOCK.get(), 1, 250).save(consumer, name("blue_aercloud_from_blue_aercloud_mushroom_blocks"));
        enchantingRecipe(RecipeCategory.MISC, AetherBlocks.GOLDEN_AERCLOUD.get(), DABlocks.PINK_AERCLOUD_MUSHROOM_BLOCK.get(), 1, 500).save(consumer, name("golden_aercloud_from_pink_aercloud_mushroom_blocks"));
        enchantingRecipe(RecipeCategory.MISC, DAItems.REMEDY_BUCKET.get(), DAItems.PLACEABLE_POISON_BUCKET.get(), 0.35F, 500).save(consumer, name("iron_remedy_bucket_enchanting"));
        enchantingRecipe(RecipeCategory.BUILDING_BLOCKS, DABlocks.CHROMATIC_AERCLOUD.get(), DABlocks.STERLING_AERCLOUD.get(), 2.0F, 2000).save(consumer, name("stratus_enchanting"));

        hiddenEnchantingRecipe(RecipeCategory.MISC, DAItems.MUSIC_DISC_A_MORNING_WISH.get(), Items.MUSIC_DISC_OTHERSIDE, 2.0F, 500).save(consumer, name("a_moring_wish_enchanting"));
        hiddenEnchantingRecipe(RecipeCategory.MISC, DAItems.MUSIC_DISC_NABOORU.get(), Items.MUSIC_DISC_PIGSTEP, 1.0F, 500).save(consumer, name("nabooru_enchanting"));

        // Combiner Recipes
        CombiningRecipeBuilder.combining(DABookCategory.COMBINEABLE_MISC, DAItems.ANTIDOTE.get(), 0.1f, 100)
                .requires(AetherItems.GOLDEN_AMBER.get())
                .requires(DAItems.FROZEN_GOLDEN_BERRIES.get())
                .requires(DAItems.BIO_CRYSTAL.get())
                .unlockedBy(getHasName(DAItems.BIO_CRYSTAL.get()), has(DAItems.BIO_CRYSTAL.get()))
                .save(consumer);

        ItemStack fodder = new ItemStack(DAItems.MOA_FODDER.get(), 1);

        fodder.set(DADataComponentTypes.MOA_FODDER, new MoaFodder(new MobEffectInstance(DAMobEffects.MOA_BONUS_JUMPS, 14400, 1)));
        CombiningRecipeBuilder.combining(DABookCategory.COMBINEABLE_FODDER, fodder,
                        0.1f, 100)
                .requires(AetherItems.GOLDEN_AMBER.get())
                .requires(DAItems.FROZEN_GOLDEN_BERRIES.get())
                .requires(DAItems.QUAIL_EGG.get())
                .unlockedBy(getHasName(DAItems.QUAIL_EGG.get()), has(DAItems.QUAIL_EGG.get()))
                .save(consumer);

        fodder.set(DADataComponentTypes.MOA_FODDER, new MoaFodder(new MobEffectInstance(MobEffects.FIRE_RESISTANCE, 14400, 1)));
        CombiningRecipeBuilder.combining(DABookCategory.COMBINEABLE_FODDER, fodder,
                        0.1f, 100)
                .requires(AetherItems.GOLDEN_AMBER.get())
                .requires(DAItems.FROZEN_GOLDEN_BERRIES.get())
                .requires(AetherBlocks.ICESTONE.get())
                .unlockedBy(getHasName(AetherBlocks.ICESTONE.get()), has(AetherBlocks.ICESTONE.get()))
                .save(consumer);

        fodder.set(DADataComponentTypes.MOA_FODDER, new MoaFodder(new MobEffectInstance(MobEffects.JUMP, 14400, 1)));
        CombiningRecipeBuilder.combining(DABookCategory.COMBINEABLE_FODDER, fodder,
                        0.1f, 100)
                .requires(AetherItems.GOLDEN_AMBER.get())
                .requires(DAItems.FROZEN_GOLDEN_BERRIES.get())
                .requires(AetherItems.BLUE_BERRY.get())
                .unlockedBy(getHasName(AetherItems.BLUE_BERRY.get()), has(AetherItems.BLUE_BERRY.get()))
                .save(consumer);

        //Poison Recipes
        PoisonConversionRecipeBuilder.conversion(DABlocks.GREEN_SQUASH.asItem(), DABlocks.PURPLE_SQUASH.asItem())
                .unlockedBy(getHasName(DABlocks.GREEN_SQUASH.get()), has(DABlocks.GREEN_SQUASH.get()))
                .group("poison_squash")
                .save(consumer, name("purple_squash_from_green_squash"));

        PoisonConversionRecipeBuilder.conversion(DABlocks.BLUE_SQUASH.asItem(), DABlocks.PURPLE_SQUASH.asItem())
                .unlockedBy(getHasName(DABlocks.BLUE_SQUASH.get()), has(DABlocks.BLUE_SQUASH.get()))
                .group("poison_squash")
                .save(consumer, name("purple_squash_from_blue_squash"));

        PoisonConversionRecipeBuilder.conversion(AetherBlocks.QUICKSOIL_GLASS.asItem(), AetherBlocks.QUICKSOIL.get())
                .unlockedBy(getHasName(AetherBlocks.QUICKSOIL_GLASS.get()), has(AetherBlocks.QUICKSOIL_GLASS.get()))
                .save(consumer, name("quicksoil_from_poison"));

        PoisonConversionRecipeBuilder.conversion(AetherItems.ENCHANTED_DART.asItem(), AetherItems.GOLDEN_DART.get())
                .unlockedBy(getHasName(AetherItems.ENCHANTED_DART.get()), has(AetherItems.ENCHANTED_DART.get()))
                .save(consumer, name("golden_dart_from_poison"));

        PoisonConversionRecipeBuilder.conversion(AetherItems.ENCHANTED_DART_SHOOTER.asItem(), AetherItems.GOLDEN_DART_SHOOTER.get())
                .unlockedBy(getHasName(AetherItems.ENCHANTED_DART_SHOOTER.get()), has(AetherItems.ENCHANTED_DART_SHOOTER.get()))
                .save(consumer, name("golden_dart_shooter_from_poison"));

        PoisonConversionRecipeBuilder.conversion(AetherItems.SKYROOT_REMEDY_BUCKET.asItem(), AetherItems.SKYROOT_POISON_BUCKET.get())
                .unlockedBy(getHasName(AetherItems.SKYROOT_REMEDY_BUCKET.get()), has(AetherItems.SKYROOT_REMEDY_BUCKET.get()))
                .save(consumer, name("skyroot_poison_bucket_from_poison"));

        PoisonConversionRecipeBuilder.conversion(DAItems.REMEDY_BUCKET.asItem(), DAItems.PLACEABLE_POISON_BUCKET.get())
                .unlockedBy(getHasName(DAItems.REMEDY_BUCKET.get()), has(DAItems.REMEDY_BUCKET.get()))
                .save(consumer, name("poison_bucket_from_poison"));

        PoisonConversionRecipeBuilder.conversion(AetherItems.ENCHANTED_BERRY.asItem(), AetherItems.BLUE_BERRY.get())
                .unlockedBy(getHasName(AetherItems.ENCHANTED_BERRY.get()), has(AetherItems.ENCHANTED_BERRY.get()))
                .save(consumer, name("blueberry_from_poison"));

        PoisonConversionRecipeBuilder.conversion(AetherBlocks.GOLDEN_AERCLOUD.asItem(), AetherBlocks.COLD_AERCLOUD.get())
                .unlockedBy(getHasName(AetherBlocks.GOLDEN_AERCLOUD.get()), has(AetherBlocks.GOLDEN_AERCLOUD.get()))
                .save(consumer, name("cold_aercloud_from_golden_aercloud"));

        PoisonConversionRecipeBuilder.conversion(DABlocks.CLORITE.asItem(), DABlocks.RAW_CLORITE.get())
                .unlockedBy(getHasName(DABlocks.CLORITE.get()), has(DABlocks.CLORITE.get()))
                .save(consumer, name("raw_clorite_from_poison"));

        PoisonConversionRecipeBuilder.conversion(AetherItems.HEALING_STONE.asItem(), AetherBlocks.HOLYSTONE.get())
                .unlockedBy(getHasName(AetherItems.HEALING_STONE.get()), has(AetherItems.HEALING_STONE.get()))
                .save(consumer, name("holystone_from_poison"));

        PoisonConversionRecipeBuilder.conversion(AetherBlocks.ENCHANTED_GRAVITITE.asItem(), AetherBlocks.GRAVITITE_ORE.get())
                .unlockedBy(getHasName(AetherBlocks.ENCHANTED_GRAVITITE.get()), has(AetherBlocks.ENCHANTED_GRAVITITE.get()))
                .save(consumer, name("gravitite_ore_from_poison"));

        PoisonConversionRecipeBuilder.conversion(Items.POTATO, Items.POISONOUS_POTATO)
                .unlockedBy(getHasName(Items.POTATO), has(Items.POTATO))
                .save(consumer, name("poisonous_poison_from_poison"));
    }

    protected ShapedRecipeBuilder makeRing(Supplier<? extends Item> ring, TagKey<Item> material) {
        return ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, ring.get())
                .define('#', material)
                .pattern(" # ")
                .pattern("# #")
                .pattern(" # ")
                .unlockedBy(getHasName(material), has(material));
    }

    protected static String getHasName(TagKey<Item> pItemLike) {
        return "has_" + pItemLike.location().getPath();
    }
    protected void copyTemplate(RecipeOutput p_266734_, ItemLike p_267133_, ItemLike p_267023_) {
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, p_267133_, 2).define('#', Items.DIAMOND)
                .define('C', p_267023_)
                .define('S', p_267133_)
                .pattern("#S#")
                .pattern("#C#")
                .pattern("###")
                .unlockedBy(getHasName(p_267133_), has(p_267133_)).save(p_266734_);
    }

    protected void copyTemplateGravitite(RecipeOutput p_266734_, ItemLike p_267133_, ItemLike p_267023_) {
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, p_267133_, 2).define('#', AetherTags.Items.PROCESSED_GRAVITITE)
                .define('C', p_267023_)
                .define('S', p_267133_)
                .pattern("#S#")
                .pattern("#C#")
                .pattern("###")
                .unlockedBy(getHasName(p_267133_), has(p_267133_)).save(p_266734_, name(getItemName(p_267133_) + "_from_gravitite"));
    }

    protected void stonecuttingRecipe(RecipeOutput consumer, RecipeCategory category, ItemLike item, ItemLike ingredient) {
        stonecuttingRecipe(consumer, category, item, ingredient, 1);
    }

    protected void stonecuttingRecipe(RecipeOutput consumer, RecipeCategory category, ItemLike item, ItemLike ingredient, int count) {
        SingleItemRecipeBuilder.stonecutting(Ingredient.of(ingredient), category, item, count).unlockedBy(getHasName(ingredient), has(ingredient)).save(consumer, name(getConversionRecipeName(item, ingredient) + "_stonecutting"));
    }

    protected void stratusSmithingRecipe(RecipeOutput consumer, Item ingredient, RecipeCategory category, Item item) {
        SmithingTransformRecipeBuilder.smithing(Ingredient.of(DAItems.STRATUS_SMITHING_TEMPLATE.get()), Ingredient.of(ingredient), Ingredient.of(DAItems.STRATUS_INGOT.get()), category, item).unlocks("has_stratus_ingot", has(DAItems.STRATUS_INGOT.get())).save(consumer, name(getItemName(item)) + "_smithing");
    }

    protected void stormSmithingRecipe(RecipeOutput consumer, Item ingredient, RecipeCategory category, Item item) {
        SmithingTransformRecipeBuilder.smithing(Ingredient.of(DAItems.STORMFORGED_SMITHING_TEMPLATE.get()), Ingredient.of(ingredient), Ingredient.of(DAItems.SQUALL_PLATE.get()), category, item).unlocks("has_squall_plate", has(DAItems.SQUALL_PLATE.get())).save(consumer, name(getItemName(item)) + "_smithing");
    }

    protected SimpleCookingRecipeBuilder smeltingFoodRecipe(ItemLike result, ItemLike ingredient, float experience) {
        return SimpleCookingRecipeBuilder.smelting(Ingredient.of(ingredient), RecipeCategory.FOOD, result, experience, 200)
                .unlockedBy(getHasName(ingredient), has(ingredient));
    }

    protected SimpleCookingRecipeBuilder smeltingBlockRecipe(ItemLike result, ItemLike ingredient, float experience) {
        return SimpleCookingRecipeBuilder.smelting(Ingredient.of(ingredient), RecipeCategory.BUILDING_BLOCKS, result, experience, 200)
                .unlockedBy(getHasName(ingredient), has(ingredient));
    }

    protected SimpleCookingRecipeBuilder SmokingFoodRecipe(ItemLike result, ItemLike ingredient, float experience) {
        return SimpleCookingRecipeBuilder.smoking(Ingredient.of(ingredient), RecipeCategory.FOOD, result, experience, 100)
                .unlockedBy(getHasName(ingredient), has(ingredient));
    }

    protected ShapedRecipeBuilder makeFullBlock(Item material, Block result) {
        return ShapedRecipeBuilder.shaped(RecipeCategory.DECORATIONS, result)
                .define('#', material)
                .pattern("###")
                .pattern("###")
                .pattern("###")
                .unlockedBy(getHasName(material), has(material));
    }

    protected ShapelessRecipeBuilder materialFromBlock(Block material, Item result) {
        return ShapelessRecipeBuilder.shapeless(RecipeCategory.DECORATIONS, result, 9)
                .requires(material)
                .unlockedBy(getHasName(material), has(material));
    }

    protected ShapedRecipeBuilder makeBoat(Supplier<? extends Item> boat, Block material) {
        return ShapedRecipeBuilder.shaped(RecipeCategory.TRANSPORTATION, boat.get())
                .define('#', material)
                .pattern("# #")
                .pattern("###")
                .unlockedBy(getHasName(material), has(material));
    }

    protected ShapelessRecipeBuilder makeChestBoat(Item chestBoat, Item boat) {
        return ShapelessRecipeBuilder.shapeless(RecipeCategory.TRANSPORTATION, chestBoat)
                .requires(boat)
                .requires(Tags.Items.CHESTS_WOODEN)
                .unlockedBy(getHasName(chestBoat), has(chestBoat));
    }

    protected void makeHangingSign(RecipeOutput consumer, Item sign, Block log) {
        ShapedRecipeBuilder.shaped(RecipeCategory.DECORATIONS, sign, 6)
                .group("hanging_sign")
                .define('#', log)
                .define('X', Items.CHAIN)
                .pattern("X X")
                .pattern("###")
                .pattern("###")
                .unlockedBy("has_stripped_logs", has(log))
                .save(consumer);
    }
    protected void sign(RecipeOutput consumer, Item sign, Block planks) {
        ShapedRecipeBuilder.shaped(RecipeCategory.DECORATIONS, sign, 3)
                .group("wooden_sign")
                .define('#', planks)
                .define('/', Tags.Items.RODS_WOODEN)
                .pattern("###")
                .pattern("###")
                .pattern(" / ")
                .unlockedBy(getHasName(planks), has(planks))
                .save(consumer);
    }
    protected void brick(RecipeOutput consumer, Block brick, Block stone) {
        ShapedRecipeBuilder.shaped(RecipeCategory.DECORATIONS, brick, 4)
                .define('A', stone)
                .pattern("AA")
                .pattern("AA")
                .unlockedBy(getHasName(stone), has(stone))
                .save(consumer);
    }

    protected void dye(RecipeOutput consumer, Item dye, Block flower, int count) {
        ShapelessRecipeBuilder.shapeless(RecipeCategory.DECORATIONS, dye, count)
                .requires(flower)
                .unlockedBy(getHasName(flower), has(flower))
                .save(consumer, name(getItemName(dye) + "_from_" + getItemName(flower)));
    }
    protected void dye(RecipeOutput consumer, Item dye, Block flower) {
        dye(consumer, dye, flower, 1);
    }

    protected static BlockStateRecipeBuilder goldBallRecipe(Block result, Block ingredient) {
        return BlockStateRecipeBuilder.recipe(BlockStateIngredient.of(ingredient), result, GoldenSwetBallRecipe::new);
    }

    protected static BlockStateRecipeBuilder glowingSporesRecipe(Block result, Block ingredient) {
        return BlockStateRecipeBuilder.recipe(BlockStateIngredient.of(ingredient), result, GlowingSporesRecipe::new);
    }

    protected RecipeBuilder slab(Block slab, Supplier<? extends Block> material) {
        return slabBuilder(RecipeCategory.BUILDING_BLOCKS, slab, Ingredient.of(material.get())).unlockedBy(getHasName(material.get()), has(material.get()));
    }
    protected ResourceLocation name(String name) {
        return ResourceLocation.fromNamespaceAndPath(DeepAether.MODID, name);
    }
    protected ResourceLocation packName(String name) {
        return packNameSpace(name, "pack");
    }
    protected ResourceLocation packNameSpace(String name, String pack) {
        return ResourceLocation.fromNamespaceAndPath(DeepAether.MODID, pack+"/"+name);
    }
}
