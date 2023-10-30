package teamrazor.deepaether.datagen;

import com.aetherteam.aether.AetherTags;
import com.aetherteam.aether.block.AetherBlocks;
import com.aetherteam.aether.data.providers.AetherRecipeProvider;
import com.aetherteam.aether.item.AetherItems;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.*;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraftforge.common.Tags;
import teamrazor.deepaether.DeepAetherMod;
import teamrazor.deepaether.datagen.tags.DATags;
import teamrazor.deepaether.init.DABlocks;
import teamrazor.deepaether.init.DAItems;

import java.util.function.Consumer;
import java.util.function.Supplier;

public class DARecipeData extends AetherRecipeProvider {
    public DARecipeData(PackOutput output) {
        super(output, DeepAetherMod.MODID);
    }

    @Override
    protected void buildRecipes(Consumer<FinishedRecipe> consumer) {
        woodFromLogs(consumer, DABlocks.ROSEROOT_WOOD.get(), DABlocks.ROSEROOT_LOG.get());
        woodFromLogs(consumer, DABlocks.STRIPPED_ROSEROOT_WOOD.get(), DABlocks.STRIPPED_ROSEROOT_LOG.get());
        planksFromLogs(consumer, DABlocks.ROSEROOT_PLANKS.get(), DATags.Items.CRAFTS_ROSEROOT_PLANKS, 4);
        stairs(DABlocks.ROSEROOT_STAIRS, DABlocks.ROSEROOT_PLANKS).group("wooden_stairs").save(consumer);

        slabBuilder(RecipeCategory.BUILDING_BLOCKS, DABlocks.ROSEROOT_SLAB.get(), Ingredient.of(DABlocks.ROSEROOT_PLANKS.get()))
                .group("wooden_slab")
                .unlockedBy(getHasName(DABlocks.ROSEROOT_PLANKS.get()), has(DABlocks.ROSEROOT_PLANKS.get()))
                .save(consumer);

        fence(DABlocks.ROSEROOT_FENCE, DABlocks.ROSEROOT_PLANKS).save(consumer);
        fenceGate(DABlocks.ROSEROOT_FENCE_GATE, DABlocks.ROSEROOT_PLANKS).save(consumer);

        doorBuilder(DABlocks.ROSEROOT_DOOR.get(), Ingredient.of(DABlocks.ROSEROOT_PLANKS.get())).unlockedBy(getHasName(DABlocks.ROSEROOT_PLANKS.get()), has(DABlocks.ROSEROOT_PLANKS.get())).group("wooden_door").save(consumer);
        trapdoorBuilder(DABlocks.ROSEROOT_TRAPDOOR.get(), Ingredient.of(DABlocks.ROSEROOT_PLANKS.get())).unlockedBy(getHasName(DABlocks.ROSEROOT_PLANKS.get()), has(DABlocks.ROSEROOT_PLANKS.get())).group("wooden_trapdoor").save(consumer);
        pressurePlateBuilder(RecipeCategory.REDSTONE, DABlocks.ROSEROOT_PRESSURE_PLATE.get(), Ingredient.of(DABlocks.ROSEROOT_PLANKS.get())).unlockedBy(getHasName(DABlocks.ROSEROOT_PLANKS.get()), has(DABlocks.ROSEROOT_PLANKS.get())).group("wooden_pressure_plate").save(consumer);
        buttonBuilder(DABlocks.ROSEROOT_BUTTON.get(), Ingredient.of(DABlocks.ROSEROOT_PLANKS.get())).unlockedBy(getHasName(DABlocks.ROSEROOT_PLANKS.get()), has(DABlocks.ROSEROOT_PLANKS.get())).group("wooden_button").save(consumer);
        wall(consumer, RecipeCategory.DECORATIONS, DABlocks.ROSEROOT_WALL.get(), DABlocks.ROSEROOT_LOG.get());
        wall(consumer, RecipeCategory.DECORATIONS, DABlocks.STRIPPED_ROSEROOT_WALL.get(), DABlocks.STRIPPED_ROSEROOT_LOG.get());

        //wall(consumer, RecipeCategory.DECORATIONS, DABlocks.SKYROOT_WALL.get(), AetherBlocks.SKYROOT_LOG.get());
        //wall(consumer, RecipeCategory.DECORATIONS, DABlocks.STRIPPED_SKYROOT_WALL.get(), AetherBlocks.SKYROOT_LOG.get());

        ShapedRecipeBuilder.shaped(RecipeCategory.DECORATIONS, DABlocks.ROSEROOT_SIGN.get(), 3)
                .group("wooden_sign")
                .define('P', DABlocks.ROSEROOT_PLANKS.get().asItem())
                .define('/', Tags.Items.RODS_WOODEN)
                .pattern("PPP")
                .pattern("PPP")
                .pattern(" / ")
                .unlockedBy(getHasName(DABlocks.ROSEROOT_PLANKS.get()), has(DABlocks.ROSEROOT_PLANKS.get()))
                .save(consumer, name("roseroot_sign"));

        woodFromLogs(consumer, DABlocks.YAGROOT_WOOD.get(), DABlocks.YAGROOT_LOG.get());
        woodFromLogs(consumer, DABlocks.STRIPPED_YAGROOT_WOOD.get(), DABlocks.STRIPPED_YAGROOT_LOG.get());
        planksFromLogs(consumer, DABlocks.YAGROOT_PLANKS.get(), DATags.Items.CRAFTS_YAGROOT_PLANKS, 4);
        stairs(DABlocks.YAGROOT_STAIRS, DABlocks.YAGROOT_PLANKS).group("wooden_stairs").save(consumer);

        slabBuilder(RecipeCategory.BUILDING_BLOCKS, DABlocks.YAGROOT_SLAB.get(), Ingredient.of(DABlocks.YAGROOT_PLANKS.get()))
                .group("wooden_slab")
                .unlockedBy(getHasName(DABlocks.YAGROOT_PLANKS.get()), has(DABlocks.YAGROOT_PLANKS.get()))
                .save(consumer);

        fence(DABlocks.YAGROOT_FENCE, DABlocks.YAGROOT_PLANKS).save(consumer);
        fenceGate(DABlocks.YAGROOT_FENCE_GATE, DABlocks.YAGROOT_PLANKS).save(consumer);

        doorBuilder(DABlocks.YAGROOT_DOOR.get(), Ingredient.of(DABlocks.YAGROOT_PLANKS.get())).unlockedBy(getHasName(DABlocks.YAGROOT_PLANKS.get()), has(DABlocks.YAGROOT_PLANKS.get())).group("wooden_door").save(consumer);
        trapdoorBuilder(DABlocks.YAGROOT_TRAPDOOR.get(), Ingredient.of(DABlocks.YAGROOT_PLANKS.get())).unlockedBy(getHasName(DABlocks.YAGROOT_PLANKS.get()), has(DABlocks.YAGROOT_PLANKS.get())).group("wooden_trapdoor").save(consumer);
        pressurePlateBuilder(RecipeCategory.REDSTONE, DABlocks.YAGROOT_PRESSURE_PLATE.get(), Ingredient.of(DABlocks.YAGROOT_PLANKS.get())).unlockedBy(getHasName(DABlocks.YAGROOT_PLANKS.get()), has(DABlocks.YAGROOT_PLANKS.get())).group("wooden_pressure_plate").save(consumer);
        buttonBuilder(DABlocks.YAGROOT_BUTTON.get(), Ingredient.of(DABlocks.YAGROOT_PLANKS.get())).unlockedBy(getHasName(DABlocks.YAGROOT_PLANKS.get()), has(DABlocks.YAGROOT_PLANKS.get())).group("wooden_button").save(consumer);
        wall(consumer, RecipeCategory.DECORATIONS, DABlocks.YAGROOT_WALL.get(), DABlocks.YAGROOT_LOG.get());
        wall(consumer, RecipeCategory.DECORATIONS, DABlocks.STRIPPED_YAGROOT_WALL.get(), DABlocks.STRIPPED_YAGROOT_LOG.get());

        ShapedRecipeBuilder.shaped(RecipeCategory.DECORATIONS, DABlocks.YAGROOT_SIGN.get(), 3)
                .group("wooden_sign")
                .define('P', DABlocks.YAGROOT_PLANKS.get().asItem())
                .define('/', Tags.Items.RODS_WOODEN)
                .pattern("PPP")
                .pattern("PPP")
                .pattern(" / ")
                .unlockedBy(getHasName(DABlocks.YAGROOT_PLANKS.get()), has(DABlocks.YAGROOT_PLANKS.get()))
                .save(consumer, name("yagroot_sign"));

        woodFromLogs(consumer, DABlocks.CRUDEROOT_WOOD.get(), DABlocks.CRUDEROOT_LOG.get());
        woodFromLogs(consumer, DABlocks.STRIPPED_CRUDEROOT_WOOD.get(), DABlocks.STRIPPED_CRUDEROOT_LOG.get());
        planksFromLogs(consumer, DABlocks.CRUDEROOT_PLANKS.get(), DATags.Items.CRAFTS_CRUDEROOT_PLANKS, 4);
        stairs(DABlocks.CRUDEROOT_STAIRS, DABlocks.CRUDEROOT_PLANKS).group("wooden_stairs").save(consumer);

        slabBuilder(RecipeCategory.BUILDING_BLOCKS, DABlocks.CRUDEROOT_SLAB.get(), Ingredient.of(DABlocks.CRUDEROOT_PLANKS.get()))
                .group("wooden_slab")
                .unlockedBy(getHasName(DABlocks.CRUDEROOT_PLANKS.get()), has(DABlocks.CRUDEROOT_PLANKS.get()))
                .save(consumer);

        fence(DABlocks.CRUDEROOT_FENCE, DABlocks.CRUDEROOT_PLANKS).save(consumer);
        fenceGate(DABlocks.CRUDEROOT_FENCE_GATE, DABlocks.CRUDEROOT_PLANKS).save(consumer);

        doorBuilder(DABlocks.CRUDEROOT_DOOR.get(), Ingredient.of(DABlocks.CRUDEROOT_PLANKS.get())).unlockedBy(getHasName(DABlocks.CRUDEROOT_PLANKS.get()), has(DABlocks.CRUDEROOT_PLANKS.get())).group("wooden_door").save(consumer);
        trapdoorBuilder(DABlocks.CRUDEROOT_TRAPDOOR.get(), Ingredient.of(DABlocks.CRUDEROOT_PLANKS.get())).unlockedBy(getHasName(DABlocks.CRUDEROOT_PLANKS.get()), has(DABlocks.CRUDEROOT_PLANKS.get())).group("wooden_trapdoor").save(consumer);
        pressurePlateBuilder(RecipeCategory.REDSTONE, DABlocks.CRUDEROOT_PRESSURE_PLATE.get(), Ingredient.of(DABlocks.CRUDEROOT_PLANKS.get())).unlockedBy(getHasName(DABlocks.CRUDEROOT_PLANKS.get()), has(DABlocks.CRUDEROOT_PLANKS.get())).group("wooden_pressure_plate").save(consumer);
        buttonBuilder(DABlocks.CRUDEROOT_BUTTON.get(), Ingredient.of(DABlocks.CRUDEROOT_PLANKS.get())).unlockedBy(getHasName(DABlocks.CRUDEROOT_PLANKS.get()), has(DABlocks.CRUDEROOT_PLANKS.get())).group("wooden_button").save(consumer);
        wall(consumer, RecipeCategory.DECORATIONS, DABlocks.CRUDEROOT_WALL.get(), DABlocks.CRUDEROOT_LOG.get());
        wall(consumer, RecipeCategory.DECORATIONS, DABlocks.STRIPPED_CRUDEROOT_WALL.get(), DABlocks.STRIPPED_CRUDEROOT_LOG.get());

        ShapedRecipeBuilder.shaped(RecipeCategory.DECORATIONS, DABlocks.CRUDEROOT_SIGN.get(), 3)
                .group("wooden_sign")
                .define('P', DABlocks.CRUDEROOT_PLANKS.get().asItem())
                .define('/', Tags.Items.RODS_WOODEN)
                .pattern("PPP")
                .pattern("PPP")
                .pattern(" / ")
                .unlockedBy(getHasName(DABlocks.CRUDEROOT_PLANKS.get()), has(DABlocks.CRUDEROOT_PLANKS.get()))
                .save(consumer, name("cruderoot_sign"));

        ShapedRecipeBuilder.shaped(RecipeCategory.DECORATIONS, Blocks.CRAFTING_TABLE, 1)
                .group("minecraft:crafting_table")
                .define('P', AetherTags.Items.PLANKS_CRAFTING)
                .pattern("PP")
                .pattern("PP")
                .unlockedBy(getHasName(AetherBlocks.SKYROOT_PLANKS.get()), has(AetherBlocks.SKYROOT_PLANKS.get()))
                .save(consumer, name("skyroot_crafting_table"));

        woodFromLogs(consumer, DABlocks.CONBERRY_WOOD.get(), DABlocks.CONBERRY_LOG.get());
        woodFromLogs(consumer, DABlocks.STRIPPED_CONBERRY_WOOD.get(), DABlocks.STRIPPED_CONBERRY_LOG.get());
        planksFromLogs(consumer, DABlocks.CONBERRY_PLANKS.get(), DATags.Items.CRAFTS_CONBERRY_PLANKS, 4);
        stairs(DABlocks.CONBERRY_STAIRS, DABlocks.CONBERRY_PLANKS).group("wooden_stairs").save(consumer);

        slabBuilder(RecipeCategory.BUILDING_BLOCKS, DABlocks.CONBERRY_SLAB.get(), Ingredient.of(DABlocks.CONBERRY_PLANKS.get()))
                .group("wooden_slab")
                .unlockedBy(getHasName(DABlocks.CONBERRY_PLANKS.get()), has(DABlocks.CONBERRY_PLANKS.get()))
                .save(consumer);

        fence(DABlocks.CONBERRY_FENCE, DABlocks.CONBERRY_PLANKS).save(consumer);
        fenceGate(DABlocks.CONBERRY_FENCE_GATE, DABlocks.CONBERRY_PLANKS).save(consumer);

        doorBuilder(DABlocks.CONBERRY_DOOR.get(), Ingredient.of(DABlocks.CONBERRY_PLANKS.get())).unlockedBy(getHasName(DABlocks.CONBERRY_PLANKS.get()), has(DABlocks.CONBERRY_PLANKS.get())).group("wooden_door").save(consumer);
        trapdoorBuilder(DABlocks.CONBERRY_TRAPDOOR.get(), Ingredient.of(DABlocks.CONBERRY_PLANKS.get())).unlockedBy(getHasName(DABlocks.CONBERRY_PLANKS.get()), has(DABlocks.CONBERRY_PLANKS.get())).group("wooden_trapdoor").save(consumer);
        pressurePlateBuilder(RecipeCategory.REDSTONE, DABlocks.CONBERRY_PRESSURE_PLATE.get(), Ingredient.of(DABlocks.CONBERRY_PLANKS.get())).unlockedBy(getHasName(DABlocks.CONBERRY_PLANKS.get()), has(DABlocks.CONBERRY_PLANKS.get())).group("wooden_pressure_plate").save(consumer);
        buttonBuilder(DABlocks.CONBERRY_BUTTON.get(), Ingredient.of(DABlocks.CONBERRY_PLANKS.get())).unlockedBy(getHasName(DABlocks.CONBERRY_PLANKS.get()), has(DABlocks.CONBERRY_PLANKS.get())).group("wooden_button").save(consumer);
        wall(consumer, RecipeCategory.DECORATIONS, DABlocks.CONBERRY_WALL.get(), DABlocks.CONBERRY_LOG.get());
        wall(consumer, RecipeCategory.DECORATIONS, DABlocks.STRIPPED_CONBERRY_WALL.get(), DABlocks.STRIPPED_CONBERRY_LOG.get());

        ShapedRecipeBuilder.shaped(RecipeCategory.DECORATIONS, DABlocks.CONBERRY_SIGN.get(), 3)
                .group("wooden_sign")
                .define('P', DABlocks.CONBERRY_PLANKS.get().asItem())
                .define('/', Tags.Items.RODS_WOODEN)
                .pattern("PPP")
                .pattern("PPP")
                .pattern(" / ")
                .unlockedBy(getHasName(DABlocks.CONBERRY_PLANKS.get()), has(DABlocks.CONBERRY_PLANKS.get()))
                .save(consumer, name("conberry_sign"));

        woodFromLogs(consumer, DABlocks.SUNROOT_WOOD.get(), DABlocks.SUNROOT_LOG.get());
        woodFromLogs(consumer, DABlocks.STRIPPED_SUNROOT_WOOD.get(), DABlocks.STRIPPED_SUNROOT_LOG.get());
        planksFromLogs(consumer, DABlocks.SUNROOT_PLANKS.get(), DATags.Items.CRAFTS_SUNROOT_PLANKS, 4);
        stairs(DABlocks.SUNROOT_STAIRS, DABlocks.SUNROOT_PLANKS).group("wooden_stairs").save(consumer);

        slabBuilder(RecipeCategory.BUILDING_BLOCKS, DABlocks.SUNROOT_SLAB.get(), Ingredient.of(DABlocks.SUNROOT_PLANKS.get()))
                .group("wooden_slab")
                .unlockedBy(getHasName(DABlocks.SUNROOT_PLANKS.get()), has(DABlocks.SUNROOT_PLANKS.get()))
                .save(consumer);

        fence(DABlocks.SUNROOT_FENCE, DABlocks.SUNROOT_PLANKS).save(consumer);
        fenceGate(DABlocks.SUNROOT_FENCE_GATE, DABlocks.SUNROOT_PLANKS).save(consumer);

        doorBuilder(DABlocks.SUNROOT_DOOR.get(), Ingredient.of(DABlocks.SUNROOT_PLANKS.get())).unlockedBy(getHasName(DABlocks.SUNROOT_PLANKS.get()), has(DABlocks.SUNROOT_PLANKS.get())).group("wooden_door").save(consumer);
        trapdoorBuilder(DABlocks.SUNROOT_TRAPDOOR.get(), Ingredient.of(DABlocks.SUNROOT_PLANKS.get())).unlockedBy(getHasName(DABlocks.SUNROOT_PLANKS.get()), has(DABlocks.SUNROOT_PLANKS.get())).group("wooden_trapdoor").save(consumer);
        pressurePlateBuilder(RecipeCategory.REDSTONE, DABlocks.SUNROOT_PRESSURE_PLATE.get(), Ingredient.of(DABlocks.SUNROOT_PLANKS.get())).unlockedBy(getHasName(DABlocks.SUNROOT_PLANKS.get()), has(DABlocks.SUNROOT_PLANKS.get())).group("wooden_pressure_plate").save(consumer);
        buttonBuilder(DABlocks.SUNROOT_BUTTON.get(), Ingredient.of(DABlocks.SUNROOT_PLANKS.get())).unlockedBy(getHasName(DABlocks.SUNROOT_PLANKS.get()), has(DABlocks.SUNROOT_PLANKS.get())).group("wooden_button").save(consumer);
        wall(consumer, RecipeCategory.DECORATIONS, DABlocks.SUNROOT_WALL.get(), DABlocks.SUNROOT_LOG.get());
        wall(consumer, RecipeCategory.DECORATIONS, DABlocks.STRIPPED_SUNROOT_WALL.get(), DABlocks.STRIPPED_SUNROOT_LOG.get());

        ShapedRecipeBuilder.shaped(RecipeCategory.DECORATIONS, DABlocks.SUNROOT_SIGN.get(), 3)
                .group("wooden_sign")
                .define('P', DABlocks.SUNROOT_PLANKS.get().asItem())
                .define('/', Tags.Items.RODS_WOODEN)
                .pattern("PPP")
                .pattern("PPP")
                .pattern(" / ")
                .unlockedBy(getHasName(DABlocks.SUNROOT_PLANKS.get()), has(DABlocks.SUNROOT_PLANKS.get()))
                .save(consumer, name("sunroot_sign"));


        ShapedRecipeBuilder.shaped(RecipeCategory.DECORATIONS, DABlocks.AERGLOW_BLOSSOM_BLOCK.get(), 1)
                .define('A', DAItems.AERGLOW_BLOSSOM.get())
                .pattern("AA")
                .pattern("AA")
                .unlockedBy(getHasName(DAItems.AERGLOW_BLOSSOM.get()), has(DAItems.AERGLOW_BLOSSOM.get()))
                .save(consumer);

        ShapedRecipeBuilder.shaped(RecipeCategory.DECORATIONS, DABlocks.AETHER_MOSS_CARPET.get(), 3)
                .define('A', DABlocks.AETHER_MOSS_BLOCK.get().asItem())
                .pattern("AAA")
                .unlockedBy(getHasName(DABlocks.AETHER_MOSS_BLOCK.get()), has(DABlocks.AETHER_MOSS_BLOCK.get()))
                .save(consumer);

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

        ShapedRecipeBuilder.shaped(RecipeCategory.DECORATIONS, DABlocks.AETHER_MUD_BRICKS.get(), 4)
                .define('A', DABlocks.PACKED_AETHER_MUD.get().asItem())
                .pattern("AA")
                .pattern("AA")
                .unlockedBy(getHasName(DABlocks.PACKED_AETHER_MUD.get()), has(DABlocks.PACKED_AETHER_MUD.get()))
                .save(consumer);
        stairs(DABlocks.AETHER_MUD_BRICKS_STAIRS, DABlocks.AETHER_MUD_BRICKS).save(consumer);

        slabBuilder(RecipeCategory.BUILDING_BLOCKS, DABlocks.AETHER_MUD_BRICKS_SLAB.get(), Ingredient.of(DABlocks.AETHER_MUD_BRICKS.get()))
                .unlockedBy(getHasName(DABlocks.AETHER_MUD_BRICKS.get()), has(DABlocks.AETHER_MUD_BRICKS.get()))
                .save(consumer);
        wall(consumer, RecipeCategory.DECORATIONS, DABlocks.AETHER_MUD_BRICKS_WALL.get(), DABlocks.AETHER_MUD_BRICKS.get());

        stonecuttingRecipe(consumer, RecipeCategory.BUILDING_BLOCKS, DABlocks.ASETERITE_STAIRS.get(), DABlocks.ASETERITE.get());
        stonecuttingRecipe(consumer, RecipeCategory.BUILDING_BLOCKS, DABlocks.ASETERITE_SLAB.get(), DABlocks.ASETERITE.get(), 2);
        stonecuttingRecipe(consumer, RecipeCategory.BUILDING_BLOCKS, DABlocks.ASETERITE_WALL.get(), DABlocks.ASETERITE.get());

        stonecuttingRecipe(consumer, RecipeCategory.BUILDING_BLOCKS, DABlocks.POLISHED_ASETERITE.get(), DABlocks.ASETERITE.get());
        stonecuttingRecipe(consumer, RecipeCategory.BUILDING_BLOCKS, DABlocks.POLISHED_ASETERITE_STAIRS.get(), DABlocks.ASETERITE.get());
        stonecuttingRecipe(consumer, RecipeCategory.BUILDING_BLOCKS, DABlocks.POLISHED_ASETERITE_SLAB.get(), DABlocks.ASETERITE.get(), 2);

        stonecuttingRecipe(consumer, RecipeCategory.BUILDING_BLOCKS, DABlocks.COBBLED_ASETERITE_STAIRS.get(), DABlocks.COBBLED_ASETERITE.get());
        stonecuttingRecipe(consumer, RecipeCategory.BUILDING_BLOCKS, DABlocks.COBBLED_ASETERITE_SLAB.get(), DABlocks.COBBLED_ASETERITE.get(), 2);
        stonecuttingRecipe(consumer, RecipeCategory.BUILDING_BLOCKS, DABlocks.COBBLED_ASETERITE_WALL.get(), DABlocks.COBBLED_ASETERITE.get());

        stonecuttingRecipe(consumer, RecipeCategory.BUILDING_BLOCKS, DABlocks.POLISHED_ASETERITE_STAIRS.get(), DABlocks.POLISHED_ASETERITE.get());
        stonecuttingRecipe(consumer, RecipeCategory.BUILDING_BLOCKS, DABlocks.POLISHED_ASETERITE_SLAB.get(), DABlocks.POLISHED_ASETERITE.get(), 2);


        stonecuttingRecipe(consumer, RecipeCategory.BUILDING_BLOCKS, DABlocks.ASETERITE_BRICKS.get(), DABlocks.ASETERITE.get());
        stonecuttingRecipe(consumer, RecipeCategory.BUILDING_BLOCKS, DABlocks.ASETERITE_BRICKS.get(), DABlocks.POLISHED_ASETERITE.get());

        stonecuttingRecipe(consumer, RecipeCategory.BUILDING_BLOCKS, DABlocks.ASETERITE_BRICKS_STAIRS.get(), DABlocks.ASETERITE.get());
        stonecuttingRecipe(consumer, RecipeCategory.BUILDING_BLOCKS, DABlocks.ASETERITE_BRICKS_SLAB.get(), DABlocks.ASETERITE.get(), 2);
        stonecuttingRecipe(consumer, RecipeCategory.BUILDING_BLOCKS, DABlocks.ASETERITE_BRICKS_WALL.get(), DABlocks.ASETERITE.get());

        stonecuttingRecipe(consumer, RecipeCategory.BUILDING_BLOCKS, DABlocks.ASETERITE_BRICKS_STAIRS.get(), DABlocks.POLISHED_ASETERITE.get());
        stonecuttingRecipe(consumer, RecipeCategory.BUILDING_BLOCKS, DABlocks.ASETERITE_BRICKS_SLAB.get(), DABlocks.POLISHED_ASETERITE.get(), 2);
        stonecuttingRecipe(consumer, RecipeCategory.BUILDING_BLOCKS, DABlocks.ASETERITE_BRICKS_WALL.get(), DABlocks.POLISHED_ASETERITE.get());

        stonecuttingRecipe(consumer, RecipeCategory.BUILDING_BLOCKS, DABlocks.ASETERITE_BRICKS_STAIRS.get(), DABlocks.ASETERITE_BRICKS.get());
        stonecuttingRecipe(consumer, RecipeCategory.BUILDING_BLOCKS, DABlocks.ASETERITE_BRICKS_SLAB.get(), DABlocks.ASETERITE_BRICKS.get(), 2);
        stonecuttingRecipe(consumer, RecipeCategory.BUILDING_BLOCKS, DABlocks.ASETERITE_BRICKS_WALL.get(), DABlocks.ASETERITE_BRICKS.get());

        stairs(DABlocks.ASETERITE_STAIRS, DABlocks.ASETERITE).save(consumer);
        slab(consumer, RecipeCategory.BUILDING_BLOCKS, DABlocks.ASETERITE_SLAB.get(), DABlocks.ASETERITE.get());
        wall(consumer, RecipeCategory.DECORATIONS, DABlocks.ASETERITE_WALL.get(), DABlocks.ASETERITE.get());

        stairs(DABlocks.COBBLED_ASETERITE_STAIRS, DABlocks.COBBLED_ASETERITE).save(consumer);
        slab(consumer, RecipeCategory.BUILDING_BLOCKS, DABlocks.COBBLED_ASETERITE_SLAB.get(), DABlocks.COBBLED_ASETERITE.get());
        wall(consumer, RecipeCategory.DECORATIONS, DABlocks.COBBLED_ASETERITE_WALL.get(), DABlocks.COBBLED_ASETERITE.get());

        stairs(DABlocks.ASETERITE_BRICKS_STAIRS, DABlocks.ASETERITE_BRICKS).save(consumer);
        slab(consumer, RecipeCategory.BUILDING_BLOCKS, DABlocks.ASETERITE_BRICKS_SLAB.get(), DABlocks.ASETERITE_BRICKS.get());
        wall(consumer, RecipeCategory.DECORATIONS, DABlocks.ASETERITE_BRICKS_WALL.get(), DABlocks.ASETERITE_BRICKS.get());

        ShapedRecipeBuilder.shaped(RecipeCategory.DECORATIONS, DABlocks.POLISHED_ASETERITE.get(), 4)
                .define('A', DABlocks.ASETERITE.get().asItem())
                .pattern("AA")
                .pattern("AA")
                .unlockedBy(getHasName(DABlocks.ASETERITE.get()), has(DABlocks.ASETERITE.get()))
                .save(consumer);

        ShapedRecipeBuilder.shaped(RecipeCategory.DECORATIONS, DABlocks.ASETERITE_BRICKS.get(), 4)
                .define('A', DABlocks.POLISHED_ASETERITE.get().asItem())
                .pattern("AA")
                .pattern("AA")
                .unlockedBy(getHasName(DABlocks.ASETERITE.get()), has(DABlocks.ASETERITE.get()))
                .save(consumer);

        stairs(DABlocks.POLISHED_ASETERITE_STAIRS, DABlocks.POLISHED_ASETERITE).save(consumer);
        slab(consumer, RecipeCategory.BUILDING_BLOCKS, DABlocks.POLISHED_ASETERITE_SLAB.get(), DABlocks.POLISHED_ASETERITE.get());

        smeltingBlockRecipe(DABlocks.ASETERITE.get(), DABlocks.COBBLED_ASETERITE.get(), 0.1F);

        stonecuttingRecipe(consumer, RecipeCategory.BUILDING_BLOCKS, DABlocks.CLORITE_STAIRS.get(), DABlocks.CLORITE.get());
        stonecuttingRecipe(consumer, RecipeCategory.BUILDING_BLOCKS, DABlocks.CLORITE_SLAB.get(), DABlocks.CLORITE.get(), 2);
        stonecuttingRecipe(consumer, RecipeCategory.BUILDING_BLOCKS, DABlocks.CLORITE_WALL.get(), DABlocks.CLORITE.get());

        stonecuttingRecipe(consumer, RecipeCategory.BUILDING_BLOCKS, DABlocks.RAW_CLORITE_STAIRS.get(), DABlocks.RAW_CLORITE.get());
        stonecuttingRecipe(consumer, RecipeCategory.BUILDING_BLOCKS, DABlocks.RAW_CLORITE_SLAB.get(), DABlocks.RAW_CLORITE.get(), 2);
        stonecuttingRecipe(consumer, RecipeCategory.BUILDING_BLOCKS, DABlocks.RAW_CLORITE_WALL.get(), DABlocks.RAW_CLORITE.get());

        stonecuttingRecipe(consumer, RecipeCategory.BUILDING_BLOCKS, DABlocks.POLISHED_CLORITE.get(), DABlocks.CLORITE.get());
        stonecuttingRecipe(consumer, RecipeCategory.BUILDING_BLOCKS, DABlocks.POLISHED_CLORITE_STAIRS.get(), DABlocks.CLORITE.get());
        stonecuttingRecipe(consumer, RecipeCategory.BUILDING_BLOCKS, DABlocks.POLISHED_CLORITE_SLAB.get(), DABlocks.CLORITE.get(), 2);
        stonecuttingRecipe(consumer, RecipeCategory.BUILDING_BLOCKS, DABlocks.POLISHED_CLORITE_STAIRS.get(), DABlocks.POLISHED_CLORITE.get());
        stonecuttingRecipe(consumer, RecipeCategory.BUILDING_BLOCKS, DABlocks.POLISHED_CLORITE_SLAB.get(), DABlocks.POLISHED_CLORITE.get(), 2);
        stonecuttingRecipe(consumer, RecipeCategory.BUILDING_BLOCKS, DABlocks.CLORITE_PILLAR.get(), DABlocks.CLORITE.get());

        enchantingRecipe(RecipeCategory.MISC, DABlocks.CLORITE.get(), DABlocks.RAW_CLORITE.get(), 0.15F, 50).save(consumer, this.name("raw_clorite_enchanting"));
        ShapedRecipeBuilder.shaped(RecipeCategory.DECORATIONS, DABlocks.RAW_CLORITE.get(), 4)
                .define('A', DABlocks.ASETERITE.get().asItem())
                .define('B', AetherBlocks.HOLYSTONE.get())
                .pattern("AB")
                .pattern("BA")
                .unlockedBy(getHasName(DABlocks.ASETERITE.get()), has(DABlocks.ASETERITE.get()))
                .save(consumer);

        stairs(DABlocks.RAW_CLORITE_STAIRS, DABlocks.RAW_CLORITE).save(consumer);
        slab(consumer, RecipeCategory.BUILDING_BLOCKS, DABlocks.RAW_CLORITE_SLAB.get(), DABlocks.RAW_CLORITE.get());

        stairs(DABlocks.CLORITE_STAIRS, DABlocks.CLORITE).save(consumer);
        slab(consumer, RecipeCategory.BUILDING_BLOCKS, DABlocks.CLORITE_SLAB.get(), DABlocks.CLORITE.get());
        wall(consumer, RecipeCategory.DECORATIONS, DABlocks.CLORITE_WALL.get(), DABlocks.CLORITE.get());
        wall(consumer, RecipeCategory.DECORATIONS, DABlocks.RAW_CLORITE_WALL.get(), DABlocks.RAW_CLORITE.get());

        ShapedRecipeBuilder.shaped(RecipeCategory.DECORATIONS, DABlocks.POLISHED_CLORITE.get(), 4)
                .define('A', DABlocks.CLORITE.get().asItem())
                .pattern("AA")
                .pattern("AA")
                .unlockedBy(getHasName(DABlocks.CLORITE.get()), has(DABlocks.CLORITE.get()))
                .save(consumer);

        ShapedRecipeBuilder.shaped(RecipeCategory.DECORATIONS, DABlocks.CLORITE_PILLAR.get(), 1)
                .define('A', DABlocks.CLORITE_SLAB.get().asItem())
                .pattern("A")
                .pattern("A")
                .unlockedBy(getHasName(DABlocks.CLORITE.get()), has(DABlocks.CLORITE.get()))
                .save(consumer);

        stairs(DABlocks.POLISHED_CLORITE_STAIRS, DABlocks.POLISHED_CLORITE).save(consumer);
        slab(consumer, RecipeCategory.BUILDING_BLOCKS, DABlocks.POLISHED_CLORITE_SLAB.get(), DABlocks.POLISHED_CLORITE.get());


        stonecuttingRecipe(consumer, RecipeCategory.BUILDING_BLOCKS, DABlocks.BIG_HOLYSTONE_BRICKS.get(), AetherBlocks.HOLYSTONE.get());
        stonecuttingRecipe(consumer, RecipeCategory.BUILDING_BLOCKS, DABlocks.BIG_HOLYSTONE_BRICKS_WALL.get(), AetherBlocks.HOLYSTONE.get());
        stonecuttingRecipe(consumer, RecipeCategory.BUILDING_BLOCKS, DABlocks.BIG_HOLYSTONE_BRICKS_STAIRS.get(), AetherBlocks.HOLYSTONE.get());
        stonecuttingRecipe(consumer, RecipeCategory.BUILDING_BLOCKS, DABlocks.BIG_HOLYSTONE_BRICKS_SLAB.get(), AetherBlocks.HOLYSTONE.get());
        stonecuttingRecipe(consumer, RecipeCategory.BUILDING_BLOCKS, DABlocks.BIG_HOLYSTONE_BRICKS_WALL.get(), DABlocks.BIG_HOLYSTONE_BRICKS.get());
        stonecuttingRecipe(consumer, RecipeCategory.BUILDING_BLOCKS, DABlocks.BIG_HOLYSTONE_BRICKS_STAIRS.get(), DABlocks.BIG_HOLYSTONE_BRICKS.get());
        stonecuttingRecipe(consumer, RecipeCategory.BUILDING_BLOCKS, DABlocks.BIG_HOLYSTONE_BRICKS_SLAB.get(), DABlocks.BIG_HOLYSTONE_BRICKS.get());


        stonecuttingRecipe(consumer, RecipeCategory.BUILDING_BLOCKS, DABlocks.HOLYSTONE_PILLAR.get(), AetherBlocks.HOLYSTONE.get());
        stonecuttingRecipe(consumer, RecipeCategory.BUILDING_BLOCKS, DABlocks.HOLYSTONE_PILLAR_UP.get(), AetherBlocks.HOLYSTONE.get());
        stonecuttingRecipe(consumer, RecipeCategory.BUILDING_BLOCKS, DABlocks.HOLYSTONE_PILLAR_DOWN.get(), AetherBlocks.HOLYSTONE.get());
        stonecuttingRecipe(consumer, RecipeCategory.BUILDING_BLOCKS, DABlocks.CHISELED_HOLYSTONE.get(), AetherBlocks.HOLYSTONE.get());

        ShapedRecipeBuilder.shaped(RecipeCategory.DECORATIONS, DABlocks.BIG_HOLYSTONE_BRICKS.get(), 4)
                .define('A', DABlocks.HOLYSTONE_TILES.get().asItem())
                .pattern("AA")
                .pattern("AA")
                .unlockedBy(getHasName(DABlocks.BIG_HOLYSTONE_BRICKS.get()), has(DABlocks.BIG_HOLYSTONE_BRICKS.get()))
                .save(consumer);

        stairs(DABlocks.BIG_HOLYSTONE_BRICKS_STAIRS, DABlocks.BIG_HOLYSTONE_BRICKS).save(consumer);
        slab(consumer, RecipeCategory.BUILDING_BLOCKS, DABlocks.BIG_HOLYSTONE_BRICKS_SLAB.get(), DABlocks.BIG_HOLYSTONE_BRICKS.get());
        wall(consumer, RecipeCategory.DECORATIONS, DABlocks.BIG_HOLYSTONE_BRICKS_WALL.get(), DABlocks.BIG_HOLYSTONE_BRICKS.get());

        ShapedRecipeBuilder.shaped(RecipeCategory.DECORATIONS, DABlocks.HOLYSTONE_PILLAR.get(), 2)
                .define('A', AetherBlocks.HOLYSTONE.get().asItem())
                .pattern("A")
                .pattern("A")
                .unlockedBy(getHasName(DABlocks.HOLYSTONE_PILLAR.get()), has(DABlocks.HOLYSTONE_PILLAR.get()))
                .save(consumer);

        ShapedRecipeBuilder.shaped(RecipeCategory.DECORATIONS, DABlocks.HOLYSTONE_PILLAR_UP.get(), 2)
                .define('A', DABlocks.HOLYSTONE_PILLAR.get().asItem())
                .pattern("A")
                .pattern("A")
                .unlockedBy(getHasName(DABlocks.HOLYSTONE_PILLAR_UP.get()), has(DABlocks.HOLYSTONE_PILLAR_UP.get()))
                .save(consumer);

        ShapedRecipeBuilder.shaped(RecipeCategory.DECORATIONS, DABlocks.HOLYSTONE_PILLAR_DOWN.get(), 2)
                .define('A', DABlocks.HOLYSTONE_PILLAR_UP.get().asItem())
                .pattern("A")
                .pattern("A")
                .unlockedBy(getHasName(DABlocks.HOLYSTONE_PILLAR_DOWN.get()), has(DABlocks.HOLYSTONE_PILLAR_DOWN.get()))
                .save(consumer);

        ShapedRecipeBuilder.shaped(RecipeCategory.DECORATIONS, DABlocks.CHISELED_HOLYSTONE.get(), 1)
                .define('A', AetherBlocks.HOLYSTONE_SLAB.get().asItem())
                .pattern("A")
                .pattern("A")
                .unlockedBy(getHasName(DABlocks.CHISELED_HOLYSTONE.get()), has(DABlocks.CHISELED_HOLYSTONE.get()))
                .save(consumer);


        stonecuttingRecipe(consumer, RecipeCategory.BUILDING_BLOCKS, DABlocks.HOLYSTONE_TILE_STAIRS.get(), DABlocks.HOLYSTONE_TILES.get());
        stonecuttingRecipe(consumer, RecipeCategory.BUILDING_BLOCKS, DABlocks.HOLYSTONE_TILE_SLAB.get(), DABlocks.HOLYSTONE_TILES.get(), 2);
        stonecuttingRecipe(consumer, RecipeCategory.BUILDING_BLOCKS, DABlocks.HOLYSTONE_TILE_WALL.get(), DABlocks.HOLYSTONE_TILES.get());

        stonecuttingRecipe(consumer, RecipeCategory.BUILDING_BLOCKS, DABlocks.HOLYSTONE_TILES.get(), AetherBlocks.HOLYSTONE.get());
        stonecuttingRecipe(consumer, RecipeCategory.BUILDING_BLOCKS, DABlocks.HOLYSTONE_TILE_STAIRS.get(), AetherBlocks.HOLYSTONE.get());
        stonecuttingRecipe(consumer, RecipeCategory.BUILDING_BLOCKS, DABlocks.HOLYSTONE_TILE_SLAB.get(), AetherBlocks.HOLYSTONE.get(), 2);
        stonecuttingRecipe(consumer, RecipeCategory.BUILDING_BLOCKS, DABlocks.HOLYSTONE_TILE_WALL.get(), AetherBlocks.HOLYSTONE.get());

        stonecuttingRecipe(consumer, RecipeCategory.BUILDING_BLOCKS, DABlocks.HOLYSTONE_TILES.get(), AetherBlocks.HOLYSTONE_BRICKS.get());
        stonecuttingRecipe(consumer, RecipeCategory.BUILDING_BLOCKS, DABlocks.HOLYSTONE_TILE_STAIRS.get(), AetherBlocks.HOLYSTONE_BRICKS.get());
        stonecuttingRecipe(consumer, RecipeCategory.BUILDING_BLOCKS, DABlocks.HOLYSTONE_TILE_SLAB.get(), AetherBlocks.HOLYSTONE_BRICKS.get(), 2);
        stonecuttingRecipe(consumer, RecipeCategory.BUILDING_BLOCKS, DABlocks.HOLYSTONE_TILE_WALL.get(), AetherBlocks.HOLYSTONE_BRICKS.get());


        stairs(DABlocks.HOLYSTONE_TILE_STAIRS, DABlocks.HOLYSTONE_TILES).save(consumer);
        slab(consumer, RecipeCategory.BUILDING_BLOCKS, DABlocks.HOLYSTONE_TILE_SLAB.get(), DABlocks.HOLYSTONE_TILES.get());
        wall(consumer, RecipeCategory.DECORATIONS, DABlocks.HOLYSTONE_TILE_WALL.get(), DABlocks.HOLYSTONE_TILES.get());

        ShapedRecipeBuilder.shaped(RecipeCategory.DECORATIONS, DABlocks.HOLYSTONE_TILES.get(), 4)
                .define('A', AetherBlocks.HOLYSTONE_BRICKS.get().asItem())
                .pattern("AA")
                .pattern("AA")
                .unlockedBy(getHasName(DABlocks.HOLYSTONE_TILES.get()), has(DABlocks.HOLYSTONE_TILES.get()))
                .save(consumer);


        stonecuttingRecipe(consumer, RecipeCategory.BUILDING_BLOCKS, DABlocks.MOSSY_HOLYSTONE_BRICK_STAIRS.get(), DABlocks.MOSSY_HOLYSTONE_BRICKS.get());
        stonecuttingRecipe(consumer, RecipeCategory.BUILDING_BLOCKS, DABlocks.MOSSY_HOLYSTONE_BRICK_SLAB.get(), DABlocks.MOSSY_HOLYSTONE_BRICKS.get(), 2);
        stonecuttingRecipe(consumer, RecipeCategory.BUILDING_BLOCKS, DABlocks.MOSSY_HOLYSTONE_BRICK_WALL.get(), DABlocks.MOSSY_HOLYSTONE_BRICKS.get());

        stonecuttingRecipe(consumer, RecipeCategory.BUILDING_BLOCKS, DABlocks.MOSSY_HOLYSTONE_BRICKS.get(), AetherBlocks.MOSSY_HOLYSTONE.get());
        stonecuttingRecipe(consumer, RecipeCategory.BUILDING_BLOCKS, DABlocks.MOSSY_HOLYSTONE_BRICK_STAIRS.get(), AetherBlocks.MOSSY_HOLYSTONE.get());
        stonecuttingRecipe(consumer, RecipeCategory.BUILDING_BLOCKS, DABlocks.MOSSY_HOLYSTONE_BRICK_SLAB.get(), AetherBlocks.MOSSY_HOLYSTONE.get(), 2);
        stonecuttingRecipe(consumer, RecipeCategory.BUILDING_BLOCKS, DABlocks.MOSSY_HOLYSTONE_BRICK_WALL.get(), AetherBlocks.MOSSY_HOLYSTONE.get());


        stairs(DABlocks.MOSSY_HOLYSTONE_BRICK_STAIRS, DABlocks.MOSSY_HOLYSTONE_BRICKS).save(consumer);
        slab(consumer, RecipeCategory.BUILDING_BLOCKS, DABlocks.MOSSY_HOLYSTONE_BRICK_SLAB.get(), DABlocks.MOSSY_HOLYSTONE_BRICKS.get());
        wall(consumer, RecipeCategory.DECORATIONS, DABlocks.MOSSY_HOLYSTONE_BRICK_WALL.get(), DABlocks.MOSSY_HOLYSTONE_BRICKS.get());


        ShapelessRecipeBuilder.shapeless(RecipeCategory.DECORATIONS, DABlocks.MOSSY_HOLYSTONE_BRICKS.get(), 1)
                .group("mossy_holystone_bricks")
                .requires(AetherBlocks.HOLYSTONE_BRICKS.get().asItem())
                .requires(Blocks.MOSS_BLOCK.asItem())
                .unlockedBy(getHasName(AetherBlocks.HOLYSTONE_BRICKS.get()), has(AetherBlocks.HOLYSTONE_BRICKS.get()))
                .save(consumer, name("mossy_holystone_bricks_from_moss"));

        ShapelessRecipeBuilder.shapeless(RecipeCategory.DECORATIONS, DABlocks.MOSSY_HOLYSTONE_BRICKS.get(), 1)
                .group("mossy_holystone_bricks")
                .requires(AetherBlocks.HOLYSTONE_BRICKS.get().asItem())
                .requires(DABlocks.AETHER_MOSS_BLOCK.get().asItem())
                .unlockedBy(getHasName(AetherBlocks.HOLYSTONE_BRICKS.get()), has(AetherBlocks.HOLYSTONE_BRICKS.get()))
                .save(consumer, name("mossy_holystone_bricks_from_aether_moss"));

        ShapelessRecipeBuilder.shapeless(RecipeCategory.DECORATIONS, DABlocks.MOSSY_HOLYSTONE_BRICKS.get(), 1)
                .group("mossy_holystone_bricks")
                .requires(AetherBlocks.HOLYSTONE_BRICKS.get().asItem())
                .requires(Blocks.VINE.asItem())
                .unlockedBy(getHasName(AetherBlocks.HOLYSTONE_BRICKS.get()), has(AetherBlocks.HOLYSTONE_BRICKS.get()))
                .save(consumer, name("mossy_holystone_bricks_from_vines"));

        ShapedRecipeBuilder.shaped(RecipeCategory.DECORATIONS, DABlocks.MOSSY_HOLYSTONE_BRICKS.get(), 4)
                .define('A', AetherBlocks.MOSSY_HOLYSTONE.get().asItem())
                .pattern("AA")
                .pattern("AA")
                .unlockedBy(getHasName(AetherBlocks.HOLYSTONE_BRICKS.get()), has(AetherBlocks.HOLYSTONE_BRICKS.get()))
                .save(consumer);


        //stonecuttingRecipe(consumer, RecipeCategory.BUILDING_BLOCKS, DABlocks.BLIGHTMOSS_HOLYSTONE_BRICK_STAIRS.get(), DABlocks.BLIGHTMOSS_HOLYSTONE_BRICKS.get());
        //stonecuttingRecipe(consumer, RecipeCategory.BUILDING_BLOCKS, DABlocks.BLIGHTMOSS_HOLYSTONE_BRICK_SLAB.get(), DABlocks.BLIGHTMOSS_HOLYSTONE_BRICKS.get(), 2);
        //stonecuttingRecipe(consumer, RecipeCategory.BUILDING_BLOCKS, DABlocks.BLIGHTMOSS_HOLYSTONE_BRICK_WALL.get(), DABlocks.BLIGHTMOSS_HOLYSTONE_BRICKS.get());
        //stonecuttingRecipe(consumer, RecipeCategory.BUILDING_BLOCKS, DABlocks.BLIGHTMOSS_HOLYSTONE_BRICKS.get(), ReduxBlocks.BLIGHTMOSS_HOLYSTONE.get());
        //stonecuttingRecipe(consumer, RecipeCategory.BUILDING_BLOCKS, DABlocks.BLIGHTMOSS_HOLYSTONE_BRICK_STAIRS.get(), ReduxBlocks.BLIGHTMOSS_HOLYSTONE.get());
        //stonecuttingRecipe(consumer, RecipeCategory.BUILDING_BLOCKS, DABlocks.BLIGHTMOSS_HOLYSTONE_BRICK_SLAB.get(), ReduxBlocks.BLIGHTMOSS_HOLYSTONE.get(), 2);
        //stonecuttingRecipe(consumer, RecipeCategory.BUILDING_BLOCKS, DABlocks.BLIGHTMOSS_HOLYSTONE_BRICK_WALL.get(), ReduxBlocks.BLIGHTMOSS_HOLYSTONE.get());
//
        //stairs(DABlocks.BLIGHTMOSS_HOLYSTONE_BRICK_STAIRS, DABlocks.BLIGHTMOSS_HOLYSTONE_BRICKS).save(consumer);
        //slab(consumer, RecipeCategory.BUILDING_BLOCKS, DABlocks.BLIGHTMOSS_HOLYSTONE_BRICK_SLAB.get(), DABlocks.BLIGHTMOSS_HOLYSTONE_BRICKS.get());
        //wall(consumer, RecipeCategory.DECORATIONS, DABlocks.BLIGHTMOSS_HOLYSTONE_BRICK_WALL.get(), DABlocks.BLIGHTMOSS_HOLYSTONE_BRICKS.get());
//
//
        //ShapedRecipeBuilder.shaped(RecipeCategory.DECORATIONS, DABlocks.BLIGHTMOSS_HOLYSTONE_BRICKS.get(), 4)
        //        .define('A', ReduxBlocks.BLIGHTMOSS_HOLYSTONE.get().asItem())
        //        .pattern("AA")
        //        .pattern("AA")
        //        .unlockedBy(getHasName(AetherBlocks.HOLYSTONE_BRICKS.get()), has(AetherBlocks.HOLYSTONE_BRICKS.get()))
        //        .save(consumer);

        //stonecuttingRecipe(consumer, RecipeCategory.BUILDING_BLOCKS, DABlocks.GILDED_HOLYSTONE_BRICK_STAIRS.get(), DABlocks.GILDED_HOLYSTONE_BRICKS.get());
        //stonecuttingRecipe(consumer, RecipeCategory.BUILDING_BLOCKS, DABlocks.GILDED_HOLYSTONE_BRICK_SLAB.get(), DABlocks.GILDED_HOLYSTONE_BRICKS.get(), 2);
        //stonecuttingRecipe(consumer, RecipeCategory.BUILDING_BLOCKS, DABlocks.GILDED_HOLYSTONE_BRICK_WALL.get(), DABlocks.GILDED_HOLYSTONE_BRICKS.get());

        //stonecuttingRecipe(consumer, RecipeCategory.BUILDING_BLOCKS, DABlocks.GILDED_HOLYSTONE_BRICKS.get(), ReduxBlocks.GILDED_HOLYSTONE.get());
        //stonecuttingRecipe(consumer, RecipeCategory.BUILDING_BLOCKS, DABlocks.GILDED_HOLYSTONE_BRICK_STAIRS.get(), ReduxBlocks.GILDED_HOLYSTONE.get());
        //stonecuttingRecipe(consumer, RecipeCategory.BUILDING_BLOCKS, DABlocks.GILDED_HOLYSTONE_BRICK_SLAB.get(), ReduxBlocks.GILDED_HOLYSTONE.get(), 2);
        //stonecuttingRecipe(consumer, RecipeCategory.BUILDING_BLOCKS, DABlocks.GILDED_HOLYSTONE_BRICK_WALL.get(), ReduxBlocks.GILDED_HOLYSTONE.get());

        //stairs(DABlocks.GILDED_HOLYSTONE_BRICK_STAIRS, DABlocks.GILDED_HOLYSTONE_BRICKS).save(consumer);
        //slab(consumer, RecipeCategory.BUILDING_BLOCKS, DABlocks.GILDED_HOLYSTONE_BRICK_SLAB.get(), DABlocks.GILDED_HOLYSTONE_BRICKS.get());
        //wall(consumer, RecipeCategory.DECORATIONS, DABlocks.GILDED_HOLYSTONE_BRICK_WALL.get(), DABlocks.GILDED_HOLYSTONE_BRICKS.get());


        //ShapedRecipeBuilder.shaped(RecipeCategory.DECORATIONS, DABlocks.GILDED_HOLYSTONE_BRICKS.get(), 4)
        //        .define('A', ReduxBlocks.GILDED_HOLYSTONE.get().asItem())
        //        .pattern("AA")
        //        .pattern("AA")
        //        .unlockedBy(getHasName(AetherBlocks.HOLYSTONE_BRICKS.get()), has(AetherBlocks.HOLYSTONE_BRICKS.get()))
        //        .save(consumer);

        //stonecuttingRecipe(consumer, RecipeCategory.BUILDING_BLOCKS, DABlocks.FROSTED_HOLYSTONE_BRICK_STAIRS.get(), DABlocks.FROSTED_HOLYSTONE_BRICKS.get());
        //stonecuttingRecipe(consumer, RecipeCategory.BUILDING_BLOCKS, DABlocks.FROSTED_HOLYSTONE_BRICK_SLAB.get(), DABlocks.FROSTED_HOLYSTONE_BRICKS.get(), 2);
        //stonecuttingRecipe(consumer, RecipeCategory.BUILDING_BLOCKS, DABlocks.FROSTED_HOLYSTONE_BRICK_WALL.get(), DABlocks.FROSTED_HOLYSTONE_BRICKS.get());

        //stonecuttingRecipe(consumer, RecipeCategory.BUILDING_BLOCKS, DABlocks.FROSTED_HOLYSTONE_BRICKS.get(), ReduxBlocks.FROSTED_HOLYSTONE.get());
        //stonecuttingRecipe(consumer, RecipeCategory.BUILDING_BLOCKS, DABlocks.FROSTED_HOLYSTONE_BRICK_STAIRS.get(), ReduxBlocks.FROSTED_HOLYSTONE.get());
        //stonecuttingRecipe(consumer, RecipeCategory.BUILDING_BLOCKS, DABlocks.FROSTED_HOLYSTONE_BRICK_SLAB.get(), ReduxBlocks.FROSTED_HOLYSTONE.get(), 2);
        //stonecuttingRecipe(consumer, RecipeCategory.BUILDING_BLOCKS, DABlocks.FROSTED_HOLYSTONE_BRICK_WALL.get(), ReduxBlocks.FROSTED_HOLYSTONE.get());

        //stairs(DABlocks.FROSTED_HOLYSTONE_BRICK_STAIRS, DABlocks.FROSTED_HOLYSTONE_BRICKS).save(consumer);
        //slab(consumer, RecipeCategory.BUILDING_BLOCKS, DABlocks.FROSTED_HOLYSTONE_BRICK_SLAB.get(), DABlocks.FROSTED_HOLYSTONE_BRICKS.get());
        //wall(consumer, RecipeCategory.DECORATIONS, DABlocks.FROSTED_HOLYSTONE_BRICK_WALL.get(), DABlocks.FROSTED_HOLYSTONE_BRICKS.get());


        //ShapedRecipeBuilder.shaped(RecipeCategory.DECORATIONS, DABlocks.FROSTED_HOLYSTONE_BRICKS.get(), 4)
        //        .define('A', ReduxBlocks.FROSTED_HOLYSTONE.get().asItem())
        //        .pattern("AA")
        //        .pattern("AA")
        //        .unlockedBy(getHasName(AetherBlocks.HOLYSTONE_BRICKS.get()), has(AetherBlocks.HOLYSTONE_BRICKS.get()))
        //        .save(consumer);


        ShapedRecipeBuilder.shaped(RecipeCategory.DECORATIONS, AetherBlocks.MOSSY_HOLYSTONE.get(), 1)
                .group("mossy_holystone")
                .define('A', AetherBlocks.HOLYSTONE.get().asItem())
                .define('B', DABlocks.AETHER_MOSS_BLOCK.get().asItem())
                .pattern("AB")
                .unlockedBy(getHasName(AetherBlocks.HOLYSTONE.get()), has(AetherBlocks.HOLYSTONE.get().asItem()))
                .save(consumer, name("mossy_holystone_from_aether_moss"));

        ShapedRecipeBuilder.shaped(RecipeCategory.DECORATIONS, DABlocks.SKYJADE_BLOCK.get())
                .define('A', DAItems.SKYJADE.get())
                .pattern("AAA")
                .pattern("AAA")
                .pattern("AAA")
                .unlockedBy(getHasName(DAItems.SKYJADE.get()), has(DAItems.SKYJADE.get()))
                .save(consumer);


        ShapedRecipeBuilder.shaped(RecipeCategory.DECORATIONS, DABlocks.STRATUS_BLOCK.get())
                .define('A', DAItems.STRATUS_INGOT.get())
                .pattern("AAA")
                .pattern("AAA")
                .pattern("AAA")
                .unlockedBy(getHasName(DAItems.STRATUS_INGOT.get()), has(DAItems.STRATUS_INGOT.get()))
                .save(consumer);

        ShapelessRecipeBuilder.shapeless(RecipeCategory.DECORATIONS, DAItems.STRATUS_INGOT.get(), 9)
                .requires(DABlocks.STRATUS_BLOCK.get())
                .unlockedBy(getHasName(DAItems.STRATUS_INGOT.get()), has(DAItems.STRATUS_INGOT.get()))
                .save(consumer, name("stratus_ingot_from_stratus_block"));

        ShapelessRecipeBuilder.shapeless(RecipeCategory.DECORATIONS, DAItems.SKYJADE.get(), 9)
                .requires(DABlocks.SKYJADE_BLOCK.get())
                .unlockedBy(getHasName(DAItems.SKYJADE.get()), has(DAItems.SKYJADE.get()))
                .save(consumer, name("skyjade_from_skyjade_block"));


        smeltingOreRecipe(DAItems.SKYJADE.get(), DABlocks.SKYJADE_ORE.get(), 1F).save(consumer);
        blastingOreRecipe(DAItems.SKYJADE.get(), DABlocks.SKYJADE_ORE.get(), 0.5F).save(consumer, name("skjyade_from_blasting"));

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

        makeRing(DAItems.GRAVITITE_RING, AetherBlocks.ENCHANTED_GRAVITITE.get().asItem()).save(consumer);

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
        stratusSmithingRecipe(consumer, DAItems.GRAVITITE_RING.get(), RecipeCategory.COMBAT, DAItems.STRATUS_RING.get());
        stratusSmithingRecipe(consumer, AetherItems.GRAVITITE_GLOVES.get(), RecipeCategory.COMBAT, DAItems.STRATUS_GLOVES.get());

        copyTemplate(consumer, DAItems.STRATUS_SMITHING_TEMPLATE.get(), AetherBlocks.HOLYSTONE.get());
        copyTemplateGravitite(consumer, DAItems.STRATUS_SMITHING_TEMPLATE.get(), AetherBlocks.HOLYSTONE.get());


        enchantingRecipe(RecipeCategory.BUILDING_BLOCKS, DABlocks.CHROMATIC_AERCLOUD.get(), DABlocks.STERLING_AERCLOUD.get(), 2.0F, 2000).save(consumer, name("stratus_enchanting"));

        ShapelessRecipeBuilder.shapeless(RecipeCategory.DECORATIONS, DAItems.STRATUS_INGOT.get())
                .requires(DABlocks.CHROMATIC_AERCLOUD.get(), 5)
                .requires(AetherBlocks.ENCHANTED_GRAVITITE.get())
                .requires(AetherItems.ZANITE_GEMSTONE.get())
                .requires(AetherItems.AMBROSIUM_SHARD.get())
                .requires(DAItems.SKYJADE.get())
                .unlockedBy(getHasName(DABlocks.STERLING_AERCLOUD.get()), has(DABlocks.STERLING_AERCLOUD.get()))
                .save(consumer);

        smeltingFoodRecipe(DAItems.COOKED_QUAIL.get(), DAItems.RAW_QUAIL.get(), 0.35F).save(consumer);
        smeltingFoodRecipe(DAItems.COOKED_AERGLOW_FISH.get(), DAItems.RAW_AERGLOW_FISH.get(), 0.35F).save(consumer);

        SmokingFoodRecipe(DAItems.COOKED_QUAIL.get(), DAItems.RAW_QUAIL.get(), 0.35F).save(consumer, name("cooked_quail_from_smoker"));
        SmokingFoodRecipe(DAItems.COOKED_AERGLOW_FISH.get(), DAItems.RAW_AERGLOW_FISH.get(), 0.35F).save(consumer, name("cooked_aerglow_fish_from_smoker"));

        makeBoat(DAItems.ROSEROOT_BOAT, DABlocks.ROSEROOT_PLANKS.get()).save(consumer);
        makeBoat(DAItems.YAGROOT_BOAT, DABlocks.YAGROOT_PLANKS.get()).save(consumer);
        makeBoat(DAItems.CRUDEROOT_BOAT, DABlocks.CRUDEROOT_PLANKS.get()).save(consumer);
        makeBoat(DAItems.CONBERRY_BOAT, DABlocks.CONBERRY_PLANKS.get()).save(consumer);
        makeBoat(DAItems.SUNROOT_BOAT, DABlocks.SUNROOT_PLANKS.get()).save(consumer);

        makeChestBoat(DAItems.ROSEROOT_CHEST_BOAT.get()).save(consumer);
        makeChestBoat(DAItems.YAGROOT_CHEST_BOAT.get()).save(consumer);
        makeChestBoat(DAItems.CRUDEROOT_CHEST_BOAT.get()).save(consumer);
        makeChestBoat(DAItems.CONBERRY_CHEST_BOAT.get()).save(consumer);
        makeChestBoat(DAItems.SUNROOT_CHEST_BOAT.get()).save(consumer);

        makeHangingSign(consumer, DAItems.ROSEROOT_HANGING_SIGN.get(), DABlocks.STRIPPED_ROSEROOT_LOG.get());
        makeHangingSign(consumer, DAItems.YAGROOT_HANGING_SIGN.get(), DABlocks.STRIPPED_YAGROOT_LOG.get());
        makeHangingSign(consumer, DAItems.CRUDEROOT_HANGING_SIGN.get(), DABlocks.STRIPPED_CRUDEROOT_LOG.get());
        makeHangingSign(consumer, DAItems.CONBERRY_HANGING_SIGN.get(), DABlocks.STRIPPED_CONBERRY_LOG.get());
        makeHangingSign(consumer, DAItems.SUNROOT_HANGING_SIGN.get(), DABlocks.STRIPPED_SUNROOT_LOG.get());

        hiddenEnchantingRecipe(RecipeCategory.MISC, DAItems.MUSIC_DISC_A_MORNING_WISH.get(), Items.MUSIC_DISC_OTHERSIDE, 2.0F, 2500).save(consumer, name("a_moring_wish_enchanting"));
        hiddenEnchantingRecipe(RecipeCategory.MISC, DAItems.MUSIC_DISC_NABOORU.get(), Items.MUSIC_DISC_PIGSTEP, 1.0F, 2500).save(consumer, name("nabooru_enchanting"));

        ShapelessRecipeBuilder.shapeless(RecipeCategory.DECORATIONS, Items.CYAN_DYE)
                .requires(DABlocks.AETHER_CATTAILS.get(), 1)
                .unlockedBy(getHasName(DABlocks.AETHER_CATTAILS.get()), has(DABlocks.AETHER_CATTAILS.get()))
                .save(consumer, name("cyan_dye_from_aether_cattails"));

        ShapelessRecipeBuilder.shapeless(RecipeCategory.DECORATIONS, Items.CYAN_DYE, 2)
                .requires(DABlocks.TALL_AETHER_CATTAILS.get(), 1)
                .unlockedBy(getHasName(DABlocks.AETHER_CATTAILS.get()), has(DABlocks.AETHER_CATTAILS.get()))
                .save(consumer, name("cyan_dye_from_tall_aether_cattails"));

        ShapelessRecipeBuilder.shapeless(RecipeCategory.DECORATIONS, Items.PINK_DYE)
                .requires(DABlocks.AERLAVENDER.get(), 1)
                .unlockedBy(getHasName(DABlocks.AERLAVENDER.get()), has(DABlocks.AERLAVENDER.get()))
                .save(consumer, name("cyan_dye_from_aerlavender"));

        ShapelessRecipeBuilder.shapeless(RecipeCategory.DECORATIONS, Items.PINK_DYE, 2)
                .requires(DABlocks.TALL_AERLAVENDER.get(), 1)
                .unlockedBy(getHasName(DABlocks.AERLAVENDER.get()), has(DABlocks.AERLAVENDER.get()))
                .save(consumer, name("cyan_dye_from_tall_aerlavender"));

        ShapelessRecipeBuilder.shapeless(RecipeCategory.DECORATIONS, Items.WHITE_DYE)
                .requires(DABlocks.RADIANT_ORCHID.get(), 1)
                .unlockedBy(getHasName(DABlocks.RADIANT_ORCHID.get()), has(DABlocks.RADIANT_ORCHID.get()))
                .save(consumer, name("white_dye_from_radiant_orchid"));


        ShapelessRecipeBuilder.shapeless(RecipeCategory.DECORATIONS, Items.ORANGE_DYE)
                .requires(DABlocks.GOLDEN_FLOWER.get(), 1)
                .unlockedBy(getHasName(DABlocks.GOLDEN_FLOWER.get()), has(DABlocks.GOLDEN_FLOWER.get()))
                .save(consumer, name("orange_dye_from_golden_flower"));

        ShapelessRecipeBuilder.shapeless(RecipeCategory.DECORATIONS, Items.WHITE_DYE)
                .requires(DABlocks.ENCHANTED_BLOSSOM.get(), 1)
                .unlockedBy(getHasName(DABlocks.ENCHANTED_BLOSSOM.get()), has(DABlocks.ENCHANTED_BLOSSOM.get()))
                .save(consumer, name("white_dye_from_enchanted_blossom"));

        ShapelessRecipeBuilder.shapeless(RecipeCategory.DECORATIONS, Items.RED_DYE)
                .requires(DABlocks.SKY_TULIPS.get(), 2)
                .unlockedBy(getHasName(DABlocks.SKY_TULIPS.get()), has(DABlocks.SKY_TULIPS.get()))
                .save(consumer, name("red_dye_from_sky_tulips"));

        ShapelessRecipeBuilder.shapeless(RecipeCategory.DECORATIONS, Items.BLUE_DYE)
                .requires(DABlocks.IASPOVE.get(), 1)
                .unlockedBy(getHasName(DABlocks.IASPOVE.get()), has(DABlocks.IASPOVE.get()))
                .save(consumer, name("blue_dye_from_iaspove"));

        ShapelessRecipeBuilder.shapeless(RecipeCategory.DECORATIONS, Items.ORANGE_DYE)
                .requires(DABlocks.GOLDEN_ASPESS.get(), 1)
                .unlockedBy(getHasName(DABlocks.GOLDEN_ASPESS.get()), has(DABlocks.GOLDEN_ASPESS.get()))
                .save(consumer, name("orange_dye_from_golden_aspess"));

        ShapelessRecipeBuilder.shapeless(RecipeCategory.DECORATIONS, Items.PURPLE_DYE)
                .requires(DABlocks.ECHAISY.get(), 2)
                .unlockedBy(getHasName(DABlocks.ECHAISY.get()), has(DABlocks.ECHAISY.get()))
                .save(consumer, name("purple_dye_from_echaisy"));

        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, DAItems.GOLDEN_SWET_BALL.get())
                .requires(DAItems.GOLDEN_GRASS_SEEDS.get())
                .requires(AetherItems.SWET_BALL.get())
                .unlockedBy(getHasName(DAItems.GOLDEN_GRASS_SEEDS.get()), has(DAItems.GOLDEN_GRASS_SEEDS.get()))
                .save(consumer);

        ShapelessRecipeBuilder.shapeless(RecipeCategory.FOOD, Items.PUMPKIN_PIE)
                .group("minecraft:pumpkin_pie")
                .requires(DATags.Items.EGGS)
                .requires(Items.PUMPKIN)
                .requires(Items.SUGAR)
                .unlockedBy(getHasName(DAItems.QUAIL_EGG.get()), has(DATags.Items.EGGS))
                .save(consumer, name("pumpkin_pie"));


        ShapedRecipeBuilder.shaped(RecipeCategory.FOOD, Items.CAKE)
                .group("minecraft:cake")
                .define('U', Items.MILK_BUCKET)
                .define('S', Items.SUGAR)
                .define('Y', Items.WHEAT)
                .define('O', DAItems.QUAIL_EGG.get())
                .pattern("UUU")
                .pattern("SOS")
                .pattern("YYY")
                .unlockedBy(getHasName(DAItems.QUAIL_EGG.get()), has(DATags.Items.EGGS))
                .save(consumer, name("cake"));

        ShapedRecipeBuilder.shaped(RecipeCategory.FOOD, Blocks.CAKE)
                .group("minecraft:cake")
                .define('A', AetherItems.SKYROOT_MILK_BUCKET.get())
                .define('B', Items.SUGAR)
                .define('C', Items.WHEAT)
                .define('E', DAItems.QUAIL_EGG.get())
                .pattern("AAA")
                .pattern("BEB")
                .pattern("CCC")
                .unlockedBy(getHasName(DAItems.QUAIL_EGG.get()), has(DATags.Items.EGGS))
                .save(consumer, name("skyroot_milk_bucket_cake"));

        //LOST CONTENT
        //ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, DAItems.SKYJADE_SHIELD.get(), 1)
        //        .group("minecraft:shield")
        //        .define('G', DAItems.SKYJADE.get())
        //        .define('R', Tags.Items.RODS_WOODEN)
        //        .pattern("GRG")
        //        .pattern("GGG")
        //        .pattern(" G ")
        //        .unlockedBy("has_skyjade_gemstone", has(DAItems.SKYJADE.get()))
        //        .save(consumer, packLostContent("skyjade_shield"));
//
        //repairingRecipe(RecipeCategory.COMBAT, DAItems.SKYJADE_SHIELD.get(), 2250).group("altar_shield_repair").save(consumer, packLostContent("skyjade_shield_repairing"));
        //repairingRecipe(RecipeCategory.COMBAT, DAItems.STRATUS_SHIELD.get(), 5500).group("altar_shield_repair").save(consumer, packLostContent("stratus_shield_repairing"));
    }

    protected void copyTemplate(Consumer<FinishedRecipe> p_266734_, ItemLike p_267133_, ItemLike p_267023_) {
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, p_267133_, 2).define('#', Items.DIAMOND)
                .define('C', p_267023_)
                .define('S', p_267133_)
                .pattern("#S#")
                .pattern("#C#")
                .pattern("###")
                .unlockedBy(getHasName(p_267133_), has(p_267133_)).save(p_266734_);
    }

    protected void copyTemplateGravitite(Consumer<FinishedRecipe> p_266734_, ItemLike p_267133_, ItemLike p_267023_) {
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, p_267133_, 2).define('#', AetherBlocks.ENCHANTED_GRAVITITE.get())
                .define('C', p_267023_)
                .define('S', p_267133_)
                .pattern("#S#")
                .pattern("#C#")
                .pattern("###")
                .unlockedBy(getHasName(p_267133_), has(p_267133_)).save(p_266734_, name(p_267133_ + "_from_gravitite"));
    }

    protected void stonecuttingRecipe(Consumer<FinishedRecipe> consumer, RecipeCategory category, ItemLike item, ItemLike ingredient) {
        stonecuttingRecipe(consumer, category, item, ingredient, 1);
    }

    protected void stonecuttingRecipe(Consumer<FinishedRecipe> consumer, RecipeCategory category, ItemLike item, ItemLike ingredient, int count) {
        SingleItemRecipeBuilder.stonecutting(Ingredient.of(ingredient), category, item, count).unlockedBy(getHasName(ingredient), has(ingredient)).save(consumer, name(getConversionRecipeName(item, ingredient) + "_stonecutting"));
    }

    protected void stratusSmithingRecipe(Consumer<FinishedRecipe> consumer, Item ingredient, RecipeCategory category, Item item) {
        SmithingTransformRecipeBuilder.smithing(Ingredient.of(DAItems.STRATUS_SMITHING_TEMPLATE.get()), Ingredient.of(ingredient), Ingredient.of(DAItems.STRATUS_INGOT.get()), category, item).unlocks("has_stratus_ingot", has(DAItems.STRATUS_INGOT.get())).save(consumer, name(getItemName(item)) + "_smithing");
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

    protected ShapelessRecipeBuilder makeChestBoat(Item boat) {
        return ShapelessRecipeBuilder.shapeless(RecipeCategory.TRANSPORTATION, boat)
                .requires(boat)
                .requires(Tags.Items.CHESTS_WOODEN)
                .unlockedBy(getHasName(boat), has(boat));
    }

    protected void makeHangingSign(Consumer<FinishedRecipe> consumer, Item sign, Block log) {
        ShapedRecipeBuilder.shaped(RecipeCategory.DECORATIONS, sign, 6)
                .group("hanging_sign")
                .define('#', log)
                .define('X', Items.CHAIN)
                .pattern("X X")
                .pattern("###")
                .pattern("###")
                .unlockedBy("has_stripped_logs", has(log))
                .save(consumer, name(sign.toString()));
    }

    protected ResourceLocation name(String name) {
        return new ResourceLocation(DeepAetherMod.MODID, name);
    }
    protected ResourceLocation packName(String name) {
        return packNameSpace(name, "pack");
    }
    protected ResourceLocation packNameSpace(String name, String pack) {
        return new ResourceLocation(DeepAetherMod.MODID, pack+"/"+name);
    }
}
