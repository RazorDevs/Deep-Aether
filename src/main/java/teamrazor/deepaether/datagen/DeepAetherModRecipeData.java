package teamrazor.deepaether.datagen;

import com.gildedgames.aether.Aether;
import com.gildedgames.aether.AetherTags;
import com.gildedgames.aether.block.AetherBlocks;
import com.gildedgames.aether.item.AetherItems;
import net.minecraft.data.recipes.*;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.Block;
import teamrazor.deepaether.init.*;
import net.minecraftforge.common.Tags;
import teamrazor.deepaether.init.DeepAetherModBlocks;
import teamrazor.deepaether.init.DeepAetherModBlocks;
import teamrazor.deepaether.init.DeepAetherModBlocks;
import teamrazor.deepaether.init.DeepAetherModBlocks;
import com.gildedgames.aether.data.providers.AetherRecipeProvider;
import net.minecraft.data.PackOutput;
import net.minecraft.world.item.crafting.Ingredient;
import teamrazor.deepaether.DeepAetherMod;
import teamrazor.deepaether.datagen.tags.DeepAetherModTags;
import teamrazor.deepaether.init.DeepAetherModBlocks;

import java.util.function.Consumer;
import java.util.function.Supplier;

public class DeepAetherModRecipeData extends AetherRecipeProvider {
    public DeepAetherModRecipeData(PackOutput output) {
        super(output, DeepAetherMod.MODID);
    }

    @Override
    protected void buildRecipes(Consumer<FinishedRecipe> consumer) {
        woodFromLogs(consumer, DeepAetherModBlocks.ROSE_WOOD.get(), DeepAetherModBlocks.ROSE_LOG.get());
        woodFromLogs(consumer, DeepAetherModBlocks.STRIPPED_ROSE_WOOD.get(), DeepAetherModBlocks.STRIPPED_ROSE_LOG.get());
        planksFromLogs(consumer, DeepAetherModBlocks.ROSE_PLANKS.get(), DeepAetherModTags.Items.CRAFTS_ROSEROOT_PLANKS, 4);
        stairs(DeepAetherModBlocks.ROSE_STAIRS, DeepAetherModBlocks.ROSE_STAIRS).group("wooden_stairs").save(consumer);

        slabBuilder(RecipeCategory.BUILDING_BLOCKS, DeepAetherModBlocks.ROSE_SLAB.get(), Ingredient.of(DeepAetherModBlocks.ROSE_PLANKS.get()))
                .group("wooden_slab")
                .unlockedBy(getHasName(DeepAetherModBlocks.ROSE_PLANKS.get()), has(DeepAetherModBlocks.ROSE_PLANKS.get()))
                .save(consumer);

        fence(DeepAetherModBlocks.ROSE_FENCE, DeepAetherModBlocks.ROSE_PLANKS).save(consumer);
        fenceGate(DeepAetherModBlocks.ROSE_FENCE_GATE, DeepAetherModBlocks.ROSE_PLANKS).save(consumer);

        doorBuilder(DeepAetherModBlocks.ROSE_DOOR.get(), Ingredient.of(DeepAetherModBlocks.ROSE_PLANKS.get())).unlockedBy(getHasName(DeepAetherModBlocks.ROSE_PLANKS.get()), has(DeepAetherModBlocks.ROSE_PLANKS.get())).group("wooden_door").save(consumer);
        trapdoorBuilder(DeepAetherModBlocks.ROSE_TRAPDOOR.get(), Ingredient.of(DeepAetherModBlocks.ROSE_PLANKS.get())).unlockedBy(getHasName(DeepAetherModBlocks.ROSE_PLANKS.get()), has(DeepAetherModBlocks.ROSE_PLANKS.get())).group("wooden_trapdoor").save(consumer);
        pressurePlateBuilder(RecipeCategory.REDSTONE, DeepAetherModBlocks.ROSE_PRESSURE_PLATE.get(), Ingredient.of(DeepAetherModBlocks.ROSE_PLANKS.get())).unlockedBy(getHasName(DeepAetherModBlocks.ROSE_PLANKS.get()), has(DeepAetherModBlocks.ROSE_PLANKS.get())).group("wooden_pressure_plate").save(consumer);
        buttonBuilder(DeepAetherModBlocks.ROSE_BUTTON.get(), Ingredient.of(DeepAetherModBlocks.ROSE_PLANKS.get())).unlockedBy(getHasName(DeepAetherModBlocks.ROSE_PLANKS.get()), has(DeepAetherModBlocks.ROSE_PLANKS.get())).group("wooden_button").save(consumer);
        wall(consumer, RecipeCategory.DECORATIONS, DeepAetherModBlocks.ROSE_WALL.get(), DeepAetherModBlocks.ROSE_LOG.get());
        wall(consumer, RecipeCategory.DECORATIONS, DeepAetherModBlocks.STRIPPED_ROSE_WALL.get(), DeepAetherModBlocks.STRIPPED_ROSE_LOG.get());

        ShapedRecipeBuilder.shaped(RecipeCategory.DECORATIONS, DeepAetherModBlocks.ROSEROOT_SIGN.get(), 3)
                .group("wooden_sign")
                .define('P', DeepAetherModBlocks.ROSE_PLANKS.get().asItem())
                .define('/', Tags.Items.RODS_WOODEN)
                .pattern("PPP")
                .pattern("PPP")
                .pattern(" / ")
                .unlockedBy(getHasName(DeepAetherModBlocks.ROSE_PLANKS.get()), has(DeepAetherModBlocks.ROSE_PLANKS.get()))
                .save(consumer);

        woodFromLogs(consumer, DeepAetherModBlocks.YAGROOT_WOOD.get(), DeepAetherModBlocks.YAGROOT_LOG.get());
        woodFromLogs(consumer, DeepAetherModBlocks.STRIPPED_YAGROOT_WOOD.get(), DeepAetherModBlocks.STRIPPED_YAGROOT_LOG.get());
        planksFromLogs(consumer, DeepAetherModBlocks.YAGROOT_PLANKS.get(), DeepAetherModTags.Items.CRAFTS_YAGROOT_PLANKS, 4);
        stairs(DeepAetherModBlocks.YAGROOT_STAIRS, DeepAetherModBlocks.YAGROOT_STAIRS).group("wooden_stairs").save(consumer);

        slabBuilder(RecipeCategory.BUILDING_BLOCKS, DeepAetherModBlocks.YAGROOT_SLAB.get(), Ingredient.of(DeepAetherModBlocks.YAGROOT_PLANKS.get()))
                .group("wooden_slab")
                .unlockedBy(getHasName(DeepAetherModBlocks.YAGROOT_PLANKS.get()), has(DeepAetherModBlocks.YAGROOT_PLANKS.get()))
                .save(consumer);

        fence(DeepAetherModBlocks.YAGROOT_FENCE, DeepAetherModBlocks.YAGROOT_PLANKS).save(consumer);
        fenceGate(DeepAetherModBlocks.YAGROOT_FENCE_GATE, DeepAetherModBlocks.YAGROOT_PLANKS).save(consumer);

        doorBuilder(DeepAetherModBlocks.YAGROOT_DOOR.get(), Ingredient.of(DeepAetherModBlocks.YAGROOT_PLANKS.get())).unlockedBy(getHasName(DeepAetherModBlocks.YAGROOT_PLANKS.get()), has(DeepAetherModBlocks.YAGROOT_PLANKS.get())).group("wooden_door").save(consumer);
        trapdoorBuilder(DeepAetherModBlocks.YAGROOT_TRAPDOOR.get(), Ingredient.of(DeepAetherModBlocks.YAGROOT_PLANKS.get())).unlockedBy(getHasName(DeepAetherModBlocks.YAGROOT_PLANKS.get()), has(DeepAetherModBlocks.YAGROOT_PLANKS.get())).group("wooden_trapdoor").save(consumer);
        pressurePlateBuilder(RecipeCategory.REDSTONE, DeepAetherModBlocks.YAGROOT_PRESSURE_PLATE.get(), Ingredient.of(DeepAetherModBlocks.YAGROOT_PLANKS.get())).unlockedBy(getHasName(DeepAetherModBlocks.YAGROOT_PLANKS.get()), has(DeepAetherModBlocks.YAGROOT_PLANKS.get())).group("wooden_pressure_plate").save(consumer);
        buttonBuilder(DeepAetherModBlocks.YAGROOT_BUTTON.get(), Ingredient.of(DeepAetherModBlocks.YAGROOT_PLANKS.get())).unlockedBy(getHasName(DeepAetherModBlocks.YAGROOT_PLANKS.get()), has(DeepAetherModBlocks.YAGROOT_PLANKS.get())).group("wooden_button").save(consumer);
        wall(consumer, RecipeCategory.DECORATIONS, DeepAetherModBlocks.YAGROOT_WALL.get(), DeepAetherModBlocks.YAGROOT_LOG.get());
        wall(consumer, RecipeCategory.DECORATIONS, DeepAetherModBlocks.STRIPPED_YAGROOT_WALL.get(), DeepAetherModBlocks.STRIPPED_YAGROOT_LOG.get());

        ShapedRecipeBuilder.shaped(RecipeCategory.DECORATIONS, DeepAetherModBlocks.YAGROOT_SIGN.get(), 3)
                .group("wooden_sign")
                .define('P', DeepAetherModBlocks.YAGROOT_PLANKS.get().asItem())
                .define('/', Tags.Items.RODS_WOODEN)
                .pattern("PPP")
                .pattern("PPP")
                .pattern(" / ")
                .unlockedBy(getHasName(DeepAetherModBlocks.YAGROOT_PLANKS.get()), has(DeepAetherModBlocks.YAGROOT_PLANKS.get()))
                .save(consumer);

        woodFromLogs(consumer, DeepAetherModBlocks.CRUDEROOT_WOOD.get(), DeepAetherModBlocks.CRUDEROOT_LOG.get());
        woodFromLogs(consumer, DeepAetherModBlocks.STRIPPED_CRUDEROOT_WOOD.get(), DeepAetherModBlocks.STRIPPED_CRUDEROOT_LOG.get());
        planksFromLogs(consumer, DeepAetherModBlocks.CRUDEROOT_PLANKS.get(), DeepAetherModTags.Items.CRAFTS_CRUDEROOT_PLANKS, 4);
        stairs(DeepAetherModBlocks.CRUDEROOT_STAIRS, DeepAetherModBlocks.CRUDEROOT_STAIRS).group("wooden_stairs").save(consumer);

        slabBuilder(RecipeCategory.BUILDING_BLOCKS, DeepAetherModBlocks.CRUDEROOT_SLAB.get(), Ingredient.of(DeepAetherModBlocks.CRUDEROOT_PLANKS.get()))
                .group("wooden_slab")
                .unlockedBy(getHasName(DeepAetherModBlocks.CRUDEROOT_PLANKS.get()), has(DeepAetherModBlocks.CRUDEROOT_PLANKS.get()))
                .save(consumer);

        fence(DeepAetherModBlocks.CRUDEROOT_FENCE, DeepAetherModBlocks.CRUDEROOT_PLANKS).save(consumer);
        fenceGate(DeepAetherModBlocks.CRUDEROOT_FENCE_GATE, DeepAetherModBlocks.CRUDEROOT_PLANKS).save(consumer);

        doorBuilder(DeepAetherModBlocks.CRUDEROOT_DOOR.get(), Ingredient.of(DeepAetherModBlocks.CRUDEROOT_PLANKS.get())).unlockedBy(getHasName(DeepAetherModBlocks.CRUDEROOT_PLANKS.get()), has(DeepAetherModBlocks.CRUDEROOT_PLANKS.get())).group("wooden_door").save(consumer);
        trapdoorBuilder(DeepAetherModBlocks.CRUDEROOT_TRAPDOOR.get(), Ingredient.of(DeepAetherModBlocks.CRUDEROOT_PLANKS.get())).unlockedBy(getHasName(DeepAetherModBlocks.CRUDEROOT_PLANKS.get()), has(DeepAetherModBlocks.CRUDEROOT_PLANKS.get())).group("wooden_trapdoor").save(consumer);
        pressurePlateBuilder(RecipeCategory.REDSTONE, DeepAetherModBlocks.CRUDEROOT_PRESSURE_PLATE.get(), Ingredient.of(DeepAetherModBlocks.CRUDEROOT_PLANKS.get())).unlockedBy(getHasName(DeepAetherModBlocks.CRUDEROOT_PLANKS.get()), has(DeepAetherModBlocks.CRUDEROOT_PLANKS.get())).group("wooden_pressure_plate").save(consumer);
        buttonBuilder(DeepAetherModBlocks.CRUDEROOT_BUTTON.get(), Ingredient.of(DeepAetherModBlocks.CRUDEROOT_PLANKS.get())).unlockedBy(getHasName(DeepAetherModBlocks.CRUDEROOT_PLANKS.get()), has(DeepAetherModBlocks.CRUDEROOT_PLANKS.get())).group("wooden_button").save(consumer);
        wall(consumer, RecipeCategory.DECORATIONS, DeepAetherModBlocks.CRUDEROOT_WALL.get(), DeepAetherModBlocks.CRUDEROOT_LOG.get());
        wall(consumer, RecipeCategory.DECORATIONS, DeepAetherModBlocks.STRIPPED_CRUDEROOT_WALL.get(), DeepAetherModBlocks.STRIPPED_CRUDEROOT_LOG.get());

        ShapedRecipeBuilder.shaped(RecipeCategory.DECORATIONS, DeepAetherModBlocks.CRUDEROOT_SIGN.get(), 3)
                .group("wooden_sign")
                .define('P', DeepAetherModBlocks.CRUDEROOT_PLANKS.get().asItem())
                .define('/', Tags.Items.RODS_WOODEN)
                .pattern("PPP")
                .pattern("PPP")
                .pattern(" / ")
                .unlockedBy(getHasName(DeepAetherModBlocks.CRUDEROOT_PLANKS.get()), has(DeepAetherModBlocks.CRUDEROOT_PLANKS.get()))
                .save(consumer);

        ShapedRecipeBuilder.shaped(RecipeCategory.DECORATIONS, DeepAetherModBlocks.AERGLOW_PETAL_BLOCK.get(), 1)
                .define('A', DeepAetherModItems.AERGLOW_PETAL.get())
                .pattern("AA")
                .pattern("AA")
                .unlockedBy(getHasName(DeepAetherModItems.AERGLOW_PETAL.get()), has(DeepAetherModItems.AERGLOW_PETAL.get()))
                .save(consumer);

        ShapedRecipeBuilder.shaped(RecipeCategory.DECORATIONS, DeepAetherModBlocks.AETHER_MOSS_CARPET.get(), 3)
                .define('A', DeepAetherModBlocks.AETHER_MOSS_BLOCK.get().asItem())
                .pattern("AAA")
                .unlockedBy(getHasName(DeepAetherModBlocks.AETHER_MOSS_BLOCK.get()), has(DeepAetherModBlocks.AETHER_MOSS_BLOCK.get()))
                .save(consumer);

        ShapelessRecipeBuilder.shapeless(RecipeCategory.DECORATIONS, DeepAetherModBlocks.PACKED_AETHER_MUD.get())
                .requires(DeepAetherModBlocks.AETHER_MUD.get())
                .requires(Items.WHEAT)
                .unlockedBy(getHasName(DeepAetherModBlocks.AETHER_MUD.get()), has(DeepAetherModBlocks.AETHER_MUD.get()))
                .save(consumer);

        ShapelessRecipeBuilder.shapeless(RecipeCategory.DECORATIONS, DeepAetherModBlocks.MUDDY_YAGROOT_ROOTS.get())
                .requires(DeepAetherModBlocks.AETHER_MUD.get())
                .requires(DeepAetherModBlocks.MUDDY_YAGROOT_ROOTS.get())
                .unlockedBy(getHasName(DeepAetherModBlocks.AETHER_MUD.get()), has(DeepAetherModBlocks.AETHER_MUD.get()))
                .save(consumer);

        ShapedRecipeBuilder.shaped(RecipeCategory.DECORATIONS, DeepAetherModBlocks.AETHER_MUD_BRICKS.get(), 4)
                .define('A', DeepAetherModBlocks.PACKED_AETHER_MUD.get().asItem())
                .pattern("AA")
                .pattern("AA")
                .unlockedBy(getHasName(DeepAetherModBlocks.PACKED_AETHER_MUD.get()), has(DeepAetherModBlocks.PACKED_AETHER_MUD.get()))
                .save(consumer);


        stonecuttingRecipe(consumer, RecipeCategory.BUILDING_BLOCKS, DeepAetherModBlocks.ASETERITE_STAIRS.get(), DeepAetherModBlocks.ASETERITE.get());
        stonecuttingRecipe(consumer, RecipeCategory.BUILDING_BLOCKS, DeepAetherModBlocks.ASETERITE_SLAB.get(), DeepAetherModBlocks.ASETERITE.get(),2);
        stonecuttingRecipe(consumer, RecipeCategory.BUILDING_BLOCKS, DeepAetherModBlocks.ASETERITE_WALL.get(), DeepAetherModBlocks.ASETERITE.get());

        stonecuttingRecipe(consumer, RecipeCategory.BUILDING_BLOCKS, DeepAetherModBlocks.POLISHED_ASETERITE.get(), DeepAetherModBlocks.ASETERITE.get());
        stonecuttingRecipe(consumer, RecipeCategory.BUILDING_BLOCKS, DeepAetherModBlocks.POLISHED_ASETERITE_STAIRS.get(), DeepAetherModBlocks.ASETERITE.get());
        stonecuttingRecipe(consumer, RecipeCategory.BUILDING_BLOCKS, DeepAetherModBlocks.POLISHED_ASETERITE_SLAB.get(), DeepAetherModBlocks.ASETERITE.get(),2);
        stonecuttingRecipe(consumer, RecipeCategory.BUILDING_BLOCKS, DeepAetherModBlocks.POLISHED_ASETERITE_STAIRS.get(), DeepAetherModBlocks.POLISHED_ASETERITE.get());
        stonecuttingRecipe(consumer, RecipeCategory.BUILDING_BLOCKS, DeepAetherModBlocks.POLISHED_ASETERITE_SLAB.get(), DeepAetherModBlocks.POLISHED_ASETERITE.get(),2);

        stairs(DeepAetherModBlocks.ASETERITE_STAIRS, DeepAetherModBlocks.ASETERITE).save(consumer);
        slab(consumer, RecipeCategory.BUILDING_BLOCKS, DeepAetherModBlocks.ASETERITE_SLAB.get(), DeepAetherModBlocks.ASETERITE.get());
        wall(consumer, RecipeCategory.DECORATIONS, DeepAetherModBlocks.ASETERITE_WALL.get(), DeepAetherModBlocks.ASETERITE.get());

        ShapedRecipeBuilder.shaped(RecipeCategory.DECORATIONS, DeepAetherModBlocks.POLISHED_ASETERITE.get(), 4)
                .define('A', DeepAetherModBlocks.ASETERITE.get().asItem())
                .pattern("AA")
                .pattern("AA")
                .unlockedBy(getHasName(DeepAetherModBlocks.ASETERITE.get()), has(DeepAetherModBlocks.ASETERITE.get()))
                .save(consumer);

        stairs(DeepAetherModBlocks.POLISHED_ASETERITE_STAIRS, DeepAetherModBlocks.POLISHED_ASETERITE).save(consumer);
        slab(consumer, RecipeCategory.BUILDING_BLOCKS, DeepAetherModBlocks.POLISHED_ASETERITE_SLAB.get(), DeepAetherModBlocks.POLISHED_ASETERITE.get());

        stonecuttingRecipe(consumer, RecipeCategory.BUILDING_BLOCKS, DeepAetherModBlocks.CLORITE_STAIRS.get(), DeepAetherModBlocks.CLORITE.get());
        stonecuttingRecipe(consumer, RecipeCategory.BUILDING_BLOCKS, DeepAetherModBlocks.CLORITE_SLAB.get(), DeepAetherModBlocks.CLORITE.get(),2);
        stonecuttingRecipe(consumer, RecipeCategory.BUILDING_BLOCKS, DeepAetherModBlocks.CLORITE_WALL.get(), DeepAetherModBlocks.CLORITE.get());

        stonecuttingRecipe(consumer, RecipeCategory.BUILDING_BLOCKS, DeepAetherModBlocks.POLISHED_CLORITE.get(), DeepAetherModBlocks.CLORITE.get());
        stonecuttingRecipe(consumer, RecipeCategory.BUILDING_BLOCKS, DeepAetherModBlocks.POLISHED_CLORITE_STAIRS.get(), DeepAetherModBlocks.CLORITE.get());
        stonecuttingRecipe(consumer, RecipeCategory.BUILDING_BLOCKS, DeepAetherModBlocks.POLISHED_CLORITE_SLAB.get(), DeepAetherModBlocks.CLORITE.get(),2);
        stonecuttingRecipe(consumer, RecipeCategory.BUILDING_BLOCKS, DeepAetherModBlocks.POLISHED_CLORITE_STAIRS.get(), DeepAetherModBlocks.POLISHED_CLORITE.get());
        stonecuttingRecipe(consumer, RecipeCategory.BUILDING_BLOCKS, DeepAetherModBlocks.POLISHED_CLORITE_SLAB.get(), DeepAetherModBlocks.POLISHED_CLORITE.get(),2);

        stairs(DeepAetherModBlocks.CLORITE_STAIRS, DeepAetherModBlocks.CLORITE).save(consumer);
        slab(consumer, RecipeCategory.BUILDING_BLOCKS, DeepAetherModBlocks.CLORITE_SLAB.get(), DeepAetherModBlocks.CLORITE.get());
        wall(consumer, RecipeCategory.DECORATIONS, DeepAetherModBlocks.CLORITE_WALL.get(), DeepAetherModBlocks.CLORITE.get());

        ShapedRecipeBuilder.shaped(RecipeCategory.DECORATIONS, DeepAetherModBlocks.POLISHED_CLORITE.get(), 4)
                .define('A', DeepAetherModBlocks.CLORITE.get().asItem())
                .pattern("AA")
                .pattern("AA")
                .unlockedBy(getHasName(DeepAetherModBlocks.CLORITE.get()), has(DeepAetherModBlocks.CLORITE.get()))
                .save(consumer);

        stairs(DeepAetherModBlocks.POLISHED_CLORITE_STAIRS, DeepAetherModBlocks.POLISHED_CLORITE).save(consumer);
        slab(consumer, RecipeCategory.BUILDING_BLOCKS, DeepAetherModBlocks.POLISHED_CLORITE_SLAB.get(), DeepAetherModBlocks.POLISHED_CLORITE.get());

        stonecuttingRecipe(consumer, RecipeCategory.BUILDING_BLOCKS, DeepAetherModBlocks.JARINITE_STAIRS.get(), DeepAetherModBlocks.JARINITE.get());
        stonecuttingRecipe(consumer, RecipeCategory.BUILDING_BLOCKS, DeepAetherModBlocks.JARINITE_SLAB.get(), DeepAetherModBlocks.JARINITE.get(),2);
        stonecuttingRecipe(consumer, RecipeCategory.BUILDING_BLOCKS, DeepAetherModBlocks.JARINITE_WALL.get(), DeepAetherModBlocks.JARINITE.get());

        stonecuttingRecipe(consumer, RecipeCategory.BUILDING_BLOCKS, DeepAetherModBlocks.POLISHED_JARINITE.get(), DeepAetherModBlocks.JARINITE.get());
        stonecuttingRecipe(consumer, RecipeCategory.BUILDING_BLOCKS, DeepAetherModBlocks.POLISHED_JARINITE_STAIRS.get(), DeepAetherModBlocks.JARINITE.get());
        stonecuttingRecipe(consumer, RecipeCategory.BUILDING_BLOCKS, DeepAetherModBlocks.POLISHED_JARINITE_SLAB.get(), DeepAetherModBlocks.JARINITE.get(),2);
        stonecuttingRecipe(consumer, RecipeCategory.BUILDING_BLOCKS, DeepAetherModBlocks.POLISHED_JARINITE_STAIRS.get(), DeepAetherModBlocks.POLISHED_JARINITE.get());
        stonecuttingRecipe(consumer, RecipeCategory.BUILDING_BLOCKS, DeepAetherModBlocks.POLISHED_JARINITE_SLAB.get(), DeepAetherModBlocks.POLISHED_JARINITE.get(),2);

        stairs(DeepAetherModBlocks.JARINITE_STAIRS, DeepAetherModBlocks.JARINITE).save(consumer);
        slab(consumer, RecipeCategory.BUILDING_BLOCKS, DeepAetherModBlocks.JARINITE_SLAB.get(), DeepAetherModBlocks.JARINITE.get());
        wall(consumer, RecipeCategory.DECORATIONS, DeepAetherModBlocks.JARINITE_WALL.get(), DeepAetherModBlocks.JARINITE.get());

        ShapedRecipeBuilder.shaped(RecipeCategory.DECORATIONS, DeepAetherModBlocks.POLISHED_JARINITE.get(), 4)
                .define('A', DeepAetherModBlocks.JARINITE.get().asItem())
                .pattern("AA")
                .pattern("AA")
                .unlockedBy(getHasName(DeepAetherModBlocks.JARINITE.get()), has(DeepAetherModBlocks.JARINITE.get()))
                .save(consumer);

        stairs(DeepAetherModBlocks.POLISHED_JARINITE_STAIRS, DeepAetherModBlocks.POLISHED_JARINITE).save(consumer);
        slab(consumer, RecipeCategory.BUILDING_BLOCKS, DeepAetherModBlocks.POLISHED_JARINITE_SLAB.get(), DeepAetherModBlocks.POLISHED_JARINITE.get());

        stonecuttingRecipe(consumer, RecipeCategory.BUILDING_BLOCKS, DeepAetherModBlocks.YALLESITE_STAIRS.get(), DeepAetherModBlocks.YALLESITE.get());
        stonecuttingRecipe(consumer, RecipeCategory.BUILDING_BLOCKS, DeepAetherModBlocks.YALLESITE_SLAB.get(), DeepAetherModBlocks.YALLESITE.get(),2);
        stonecuttingRecipe(consumer, RecipeCategory.BUILDING_BLOCKS, DeepAetherModBlocks.YALLESITE_WALL.get(), DeepAetherModBlocks.YALLESITE.get());

        stonecuttingRecipe(consumer, RecipeCategory.BUILDING_BLOCKS, DeepAetherModBlocks.POLISHED_YALLESITE.get(), DeepAetherModBlocks.YALLESITE.get());
        stonecuttingRecipe(consumer, RecipeCategory.BUILDING_BLOCKS, DeepAetherModBlocks.POLISHED_YALLESITE_STAIRS.get(), DeepAetherModBlocks.YALLESITE.get());
        stonecuttingRecipe(consumer, RecipeCategory.BUILDING_BLOCKS, DeepAetherModBlocks.POLISHED_YALLESITE_SLAB.get(), DeepAetherModBlocks.YALLESITE.get(),2);
        stonecuttingRecipe(consumer, RecipeCategory.BUILDING_BLOCKS, DeepAetherModBlocks.POLISHED_YALLESITE_STAIRS.get(), DeepAetherModBlocks.POLISHED_YALLESITE.get());
        stonecuttingRecipe(consumer, RecipeCategory.BUILDING_BLOCKS, DeepAetherModBlocks.POLISHED_YALLESITE_SLAB.get(), DeepAetherModBlocks.POLISHED_YALLESITE.get(),2);

        stairs(DeepAetherModBlocks.YALLESITE_STAIRS, DeepAetherModBlocks.YALLESITE).save(consumer);
        slab(consumer, RecipeCategory.BUILDING_BLOCKS, DeepAetherModBlocks.YALLESITE_SLAB.get(), DeepAetherModBlocks.YALLESITE.get());
        wall(consumer, RecipeCategory.DECORATIONS, DeepAetherModBlocks.YALLESITE_WALL.get(), DeepAetherModBlocks.YALLESITE.get());

        ShapedRecipeBuilder.shaped(RecipeCategory.DECORATIONS, DeepAetherModBlocks.POLISHED_YALLESITE.get(), 4)
                .define('A', DeepAetherModBlocks.YALLESITE.get().asItem())
                .pattern("AA")
                .pattern("AA")
                .unlockedBy(getHasName(DeepAetherModBlocks.YALLESITE.get()), has(DeepAetherModBlocks.YALLESITE.get()))
                .save(consumer);

        stairs(DeepAetherModBlocks.POLISHED_YALLESITE_STAIRS, DeepAetherModBlocks.POLISHED_YALLESITE).save(consumer);
        slab(consumer, RecipeCategory.BUILDING_BLOCKS, DeepAetherModBlocks.POLISHED_YALLESITE_SLAB.get(), DeepAetherModBlocks.POLISHED_YALLESITE.get());

        stonecuttingRecipe(consumer, RecipeCategory.BUILDING_BLOCKS, DeepAetherModBlocks.DARKERITE_STAIRS.get(), DeepAetherModBlocks.DARKERITE.get());
        stonecuttingRecipe(consumer, RecipeCategory.BUILDING_BLOCKS, DeepAetherModBlocks.DARKERITE_SLAB.get(), DeepAetherModBlocks.DARKERITE.get(),2);
        stonecuttingRecipe(consumer, RecipeCategory.BUILDING_BLOCKS, DeepAetherModBlocks.DARKERITE_WALL.get(), DeepAetherModBlocks.DARKERITE.get());

        stonecuttingRecipe(consumer, RecipeCategory.BUILDING_BLOCKS, DeepAetherModBlocks.POLISHED_DARKERITE.get(), DeepAetherModBlocks.DARKERITE.get());
        stonecuttingRecipe(consumer, RecipeCategory.BUILDING_BLOCKS, DeepAetherModBlocks.POLISHED_DARKERITE_STAIRS.get(), DeepAetherModBlocks.DARKERITE.get());
        stonecuttingRecipe(consumer, RecipeCategory.BUILDING_BLOCKS, DeepAetherModBlocks.POLISHED_DARKERITE_SLAB.get(), DeepAetherModBlocks.DARKERITE.get(),2);
        stonecuttingRecipe(consumer, RecipeCategory.BUILDING_BLOCKS, DeepAetherModBlocks.POLISHED_DARKERITE_STAIRS.get(), DeepAetherModBlocks.POLISHED_DARKERITE.get());
        stonecuttingRecipe(consumer, RecipeCategory.BUILDING_BLOCKS, DeepAetherModBlocks.POLISHED_DARKERITE_SLAB.get(), DeepAetherModBlocks.POLISHED_DARKERITE.get(),2);

        stairs(DeepAetherModBlocks.DARKERITE_STAIRS, DeepAetherModBlocks.DARKERITE).save(consumer);
        slab(consumer, RecipeCategory.BUILDING_BLOCKS, DeepAetherModBlocks.DARKERITE_SLAB.get(), DeepAetherModBlocks.DARKERITE.get());
        wall(consumer, RecipeCategory.DECORATIONS, DeepAetherModBlocks.DARKERITE_WALL.get(), DeepAetherModBlocks.DARKERITE.get());

        ShapedRecipeBuilder.shaped(RecipeCategory.DECORATIONS, DeepAetherModBlocks.POLISHED_DARKERITE.get(), 4)
                .define('A', DeepAetherModBlocks.DARKERITE.get().asItem())
                .pattern("AA")
                .pattern("AA")
                .unlockedBy(getHasName(DeepAetherModBlocks.DARKERITE.get()), has(DeepAetherModBlocks.DARKERITE.get()))
                .save(consumer);

        stairs(DeepAetherModBlocks.POLISHED_DARKERITE_STAIRS, DeepAetherModBlocks.POLISHED_DARKERITE).save(consumer);
        slab(consumer, RecipeCategory.BUILDING_BLOCKS, DeepAetherModBlocks.POLISHED_DARKERITE_SLAB.get(), DeepAetherModBlocks.POLISHED_DARKERITE.get());

        stonecuttingRecipe(consumer, RecipeCategory.BUILDING_BLOCKS, DeepAetherModBlocks.GREOTITE_STAIRS.get(), DeepAetherModBlocks.GREOTITE.get());
        stonecuttingRecipe(consumer, RecipeCategory.BUILDING_BLOCKS, DeepAetherModBlocks.GREOTITE_SLAB.get(), DeepAetherModBlocks.GREOTITE.get(),2);
        stonecuttingRecipe(consumer, RecipeCategory.BUILDING_BLOCKS, DeepAetherModBlocks.GREOTITE_WALL.get(), DeepAetherModBlocks.GREOTITE.get());

        stonecuttingRecipe(consumer, RecipeCategory.BUILDING_BLOCKS, DeepAetherModBlocks.POLISHED_GREOTITE.get(), DeepAetherModBlocks.GREOTITE.get());
        stonecuttingRecipe(consumer, RecipeCategory.BUILDING_BLOCKS, DeepAetherModBlocks.POLISHED_GREOTITE_STAIRS.get(), DeepAetherModBlocks.GREOTITE.get());
        stonecuttingRecipe(consumer, RecipeCategory.BUILDING_BLOCKS, DeepAetherModBlocks.POLISHED_GREOTITE_SLAB.get(), DeepAetherModBlocks.GREOTITE.get(),2);
        stonecuttingRecipe(consumer, RecipeCategory.BUILDING_BLOCKS, DeepAetherModBlocks.POLISHED_GREOTITE_STAIRS.get(), DeepAetherModBlocks.POLISHED_GREOTITE.get());
        stonecuttingRecipe(consumer, RecipeCategory.BUILDING_BLOCKS, DeepAetherModBlocks.POLISHED_GREOTITE_SLAB.get(), DeepAetherModBlocks.POLISHED_GREOTITE.get(),2);

        stairs(DeepAetherModBlocks.GREOTITE_STAIRS, DeepAetherModBlocks.GREOTITE).save(consumer);
        slab(consumer, RecipeCategory.BUILDING_BLOCKS, DeepAetherModBlocks.GREOTITE_SLAB.get(), DeepAetherModBlocks.GREOTITE.get());
        wall(consumer, RecipeCategory.DECORATIONS, DeepAetherModBlocks.GREOTITE_WALL.get(), DeepAetherModBlocks.GREOTITE.get());

        ShapedRecipeBuilder.shaped(RecipeCategory.DECORATIONS, DeepAetherModBlocks.POLISHED_GREOTITE.get(), 4)
                .define('A', DeepAetherModBlocks.GREOTITE.get().asItem())
                .pattern("AA")
                .pattern("AA")
                .unlockedBy(getHasName(DeepAetherModBlocks.GREOTITE.get()), has(DeepAetherModBlocks.GREOTITE.get()))
                .save(consumer);

        stairs(DeepAetherModBlocks.POLISHED_GREOTITE_STAIRS, DeepAetherModBlocks.POLISHED_GREOTITE).save(consumer);
        slab(consumer, RecipeCategory.BUILDING_BLOCKS, DeepAetherModBlocks.POLISHED_GREOTITE_SLAB.get(), DeepAetherModBlocks.POLISHED_GREOTITE.get());

        ShapedRecipeBuilder.shaped(RecipeCategory.DECORATIONS, DeepAetherModBlocks.SKYJADE_BLOCK.get())
                .define('A', DeepAetherModItems.SKYJADE.get())
                .pattern("AAA")
                .pattern("AAA")
                .pattern("AAA")
                .unlockedBy(getHasName(DeepAetherModItems.SKYJADE.get()), has(DeepAetherModItems.SKYJADE.get()))
                .save(consumer);


        ShapedRecipeBuilder.shaped(RecipeCategory.DECORATIONS, DeepAetherModBlocks.CLOUDIUM_BLOCK.get())
                .define('A', DeepAetherModItems.CLOUDIUM_INGOT.get())
                .pattern("AAA")
                .pattern("AAA")
                .pattern("AAA")
                .unlockedBy(getHasName(DeepAetherModItems.CLOUDIUM_INGOT.get()), has(DeepAetherModItems.CLOUDIUM_INGOT.get()))
                .save(consumer);

        stonecuttingRecipe(consumer, RecipeCategory.BUILDING_BLOCKS, DeepAetherModBlocks.HOLYSTONE_BRICKS.get(), AetherBlocks.HOLYSTONE.get());
        stonecuttingRecipe(consumer, RecipeCategory.BUILDING_BLOCKS, DeepAetherModBlocks.HOLYSTONE_BRICKS_STAIRS.get(), AetherBlocks.HOLYSTONE.get());
        stonecuttingRecipe(consumer, RecipeCategory.BUILDING_BLOCKS, DeepAetherModBlocks.HOLYSTONE_BRICKS_SLAB.get(), AetherBlocks.HOLYSTONE.get(),2);
        stonecuttingRecipe(consumer, RecipeCategory.BUILDING_BLOCKS, DeepAetherModBlocks.HOLYSTONE_BRICKS_STAIRS.get(), DeepAetherModBlocks.POLISHED_GREOTITE.get());
        stonecuttingRecipe(consumer, RecipeCategory.BUILDING_BLOCKS, DeepAetherModBlocks.HOLYSTONE_BRICKS_SLAB.get(), DeepAetherModBlocks.POLISHED_GREOTITE.get(),2);

        ShapedRecipeBuilder.shaped(RecipeCategory.DECORATIONS, DeepAetherModBlocks.HOLYSTONE_BRICKS.get(), 4)
                .define('A', AetherBlocks.HOLYSTONE.get().asItem())
                .pattern("AA")
                .pattern("AA")
                .unlockedBy(getHasName(AetherBlocks.HOLYSTONE.get()), has(AetherBlocks.HOLYSTONE.get()))
                .save(consumer);
        stairs(DeepAetherModBlocks.HOLYSTONE_BRICKS_STAIRS, DeepAetherModBlocks.HOLYSTONE_BRICKS).save(consumer);
        slab(consumer, RecipeCategory.BUILDING_BLOCKS, DeepAetherModBlocks.HOLYSTONE_BRICKS_SLAB.get(), DeepAetherModBlocks.HOLYSTONE_BRICKS.get());

        smeltingOreRecipe(DeepAetherModItems.SKYJADE.get(), DeepAetherModBlocks.SKYJADE_ORE.get(), 1F).save(consumer);

        repairingRecipe(RecipeCategory.COMBAT, DeepAetherModItems.SKYJADE_TOOLS_SWORD.get(), 7000).group("altar_sword_repair").save(consumer, name("skyjade_sword_repairing"));
        repairingRecipe(RecipeCategory.COMBAT, DeepAetherModItems.SKYJADE_TOOLS_AXE.get(), 7000).group("altar_sword_repair").save(consumer, name("skyjade_axe_repairing"));
        repairingRecipe(RecipeCategory.COMBAT, DeepAetherModItems.SKYJADE_TOOLS_PICKAXE.get(), 7000).group("altar_sword_repair").save(consumer, name("skyjade_pickaxe_repairing"));
        repairingRecipe(RecipeCategory.COMBAT, DeepAetherModItems.SKYJADE_TOOLS_SHOVEL.get(), 7000).group("altar_sword_repair").save(consumer, name("skyjade_shovel_repairing"));
        repairingRecipe(RecipeCategory.COMBAT, DeepAetherModItems.SKYJADE_TOOLS_HOE.get(), 7000).group("altar_sword_repair").save(consumer, name("skyjade_hoe_repairing"));
        repairingRecipe(RecipeCategory.COMBAT, DeepAetherModItems.SKYJADE_ARMOR_BOOTS.get(), 7000).group("altar_sword_repair").save(consumer, name("skyjade_boots_repairing"));
        repairingRecipe(RecipeCategory.COMBAT, DeepAetherModItems.SKYJADE_ARMOR_LEGGINGS.get(), 7000).group("altar_sword_repair").save(consumer, name("skyjade_leggings_repairing"));
        repairingRecipe(RecipeCategory.COMBAT, DeepAetherModItems.SKYJADE_ARMOR_CHESTPLATE.get(), 7000).group("altar_sword_repair").save(consumer, name("skyjade_chestplate_repairing"));
        repairingRecipe(RecipeCategory.COMBAT, DeepAetherModItems.SKYJADE_ARMOR_HELMET.get(), 7000).group("altar_sword_repair").save(consumer, name("skyjade_helmet_repairing"));
        repairingRecipe(RecipeCategory.COMBAT, DeepAetherModItems.SKYJADE_GLOVES.get(), 7000).group("altar_sword_repair").save(consumer, name("skyjade_gloves_repairing"));

        makeSword(DeepAetherModItems.SKYJADE_TOOLS_SWORD, DeepAetherModItems.SKYJADE).save(consumer);
        makeAxe(DeepAetherModItems.SKYJADE_TOOLS_AXE, DeepAetherModItems.SKYJADE).save(consumer);
        makePickaxe(DeepAetherModItems.SKYJADE_TOOLS_PICKAXE, DeepAetherModItems.SKYJADE).save(consumer);
        makeShovel(DeepAetherModItems.SKYJADE_TOOLS_SHOVEL, DeepAetherModItems.SKYJADE).save(consumer);
        makeHoe(DeepAetherModItems.SKYJADE_TOOLS_HOE, DeepAetherModItems.SKYJADE).save(consumer);

        makeBoots(DeepAetherModItems.SKYJADE_ARMOR_BOOTS, DeepAetherModItems.SKYJADE).save(consumer);
        makeLeggings(DeepAetherModItems.SKYJADE_ARMOR_LEGGINGS, DeepAetherModItems.SKYJADE).save(consumer);
        makeChestplate(DeepAetherModItems.SKYJADE_ARMOR_CHESTPLATE, DeepAetherModItems.SKYJADE).save(consumer);
        makeHelmet(DeepAetherModItems.SKYJADE_ARMOR_HELMET, DeepAetherModItems.SKYJADE).save(consumer);
        makeRing(DeepAetherModItems.SKYJADE_RING, DeepAetherModItems.SKYJADE.get()).save(consumer);
        makeGloves(DeepAetherModItems.SKYJADE_GLOVES, DeepAetherModItems.SKYJADE).save(consumer);

        makeRing(DeepAetherModItems.GRAVITIE_RING, AetherBlocks.ENCHANTED_GRAVITITE.get().asItem()).save(consumer);

        repairingRecipe(RecipeCategory.COMBAT, DeepAetherModItems.CLOUDIUM_SWORD.get(), 16000).group("altar_sword_repair").save(consumer, name("cloudium_sword_repairing"));
        repairingRecipe(RecipeCategory.COMBAT, DeepAetherModItems.CLOUDIUM_AXE.get(), 16000).group("altar_sword_repair").save(consumer, name("cloudium_axe_repairing"));
        repairingRecipe(RecipeCategory.COMBAT, DeepAetherModItems.CLOUDIUM_PICKAXE.get(), 16000).group("altar_sword_repair").save(consumer, name("cloudium_pickaxe_repairing"));
        repairingRecipe(RecipeCategory.COMBAT, DeepAetherModItems.CLOUDIUM_SHOVEL.get(), 16000).group("altar_sword_repair").save(consumer, name("cloudium_shovel_repairing"));
        repairingRecipe(RecipeCategory.COMBAT, DeepAetherModItems.CLOUDIUM_HOE.get(), 16000).group("altar_sword_repair").save(consumer, name("cloudium_hoe_repairing"));
        repairingRecipe(RecipeCategory.COMBAT, DeepAetherModItems.CLOUDIUM_BOOTS.get(), 16000).group("altar_sword_repair").save(consumer, name("cloudium_boots_repairing"));
        repairingRecipe(RecipeCategory.COMBAT, DeepAetherModItems.CLOUDIUM_LEGGINGS.get(), 16000).group("altar_sword_repair").save(consumer, name("cloudium_leggings_repairing"));
        repairingRecipe(RecipeCategory.COMBAT, DeepAetherModItems.CLOUDIUM_CHESTPLATE.get(), 16000).group("altar_sword_repair").save(consumer, name("cloudium_chestplate_repairing"));
        repairingRecipe(RecipeCategory.COMBAT, DeepAetherModItems.CLOUDIUM_HELMET.get(), 16000).group("altar_sword_repair").save(consumer, name("cloudium_helmet_repairing"));
        repairingRecipe(RecipeCategory.COMBAT, DeepAetherModItems.CLOUDIUM_GLOVES.get(), 16000).group("altar_sword_repair").save(consumer, name("cloudium_gloves_repairing"));


        cloudiumSmithingRecipe(consumer, AetherItems.GRAVITITE_SWORD.get(), RecipeCategory.COMBAT, DeepAetherModItems.CLOUDIUM_SWORD.get());
        cloudiumSmithingRecipe(consumer, AetherItems.GRAVITITE_AXE.get(), RecipeCategory.COMBAT, DeepAetherModItems.CLOUDIUM_AXE.get());
        cloudiumSmithingRecipe(consumer, AetherItems.GRAVITITE_PICKAXE.get(), RecipeCategory.COMBAT, DeepAetherModItems.CLOUDIUM_PICKAXE.get());
        cloudiumSmithingRecipe(consumer, AetherItems.GRAVITITE_SHOVEL.get(), RecipeCategory.COMBAT, DeepAetherModItems.CLOUDIUM_SHOVEL.get());
        cloudiumSmithingRecipe(consumer, AetherItems.GRAVITITE_HOE.get(), RecipeCategory.COMBAT, DeepAetherModItems.CLOUDIUM_HOE.get());

        cloudiumSmithingRecipe(consumer, AetherItems.GRAVITITE_BOOTS.get(), RecipeCategory.COMBAT, DeepAetherModItems.CLOUDIUM_BOOTS.get());
        cloudiumSmithingRecipe(consumer, AetherItems.GRAVITITE_LEGGINGS.get(), RecipeCategory.COMBAT, DeepAetherModItems.CLOUDIUM_LEGGINGS.get());
        cloudiumSmithingRecipe(consumer, AetherItems.GRAVITITE_CHESTPLATE.get(), RecipeCategory.COMBAT, DeepAetherModItems.CLOUDIUM_CHESTPLATE.get());
        cloudiumSmithingRecipe(consumer, AetherItems.GRAVITITE_HELMET.get(), RecipeCategory.COMBAT, DeepAetherModItems.CLOUDIUM_HELMET.get());
        cloudiumSmithingRecipe(consumer, DeepAetherModItems.GRAVITIE_RING.get(), RecipeCategory.COMBAT, DeepAetherModItems.CLOUDIUM_RING.get());
        cloudiumSmithingRecipe(consumer, AetherItems.GRAVITITE_GLOVES.get(), RecipeCategory.COMBAT, DeepAetherModItems.CLOUDIUM_GLOVES.get());

        enchantingRecipe(RecipeCategory.BUILDING_BLOCKS, DeepAetherModItems.CLOUDIUM_SCRAP.get(), DeepAetherModBlocks.CLOUDIUM_DEBRIS.get(), 2.0F, 2000).save(consumer, name("cloudium_enchanting"));

        ShapelessRecipeBuilder.shapeless(RecipeCategory.DECORATIONS, DeepAetherModItems.CLOUDIUM_INGOT.get())
                .requires(DeepAetherModItems.CLOUDIUM_SCRAP.get(),4)
                .requires(AetherBlocks.ENCHANTED_GRAVITITE.get(),4)
                .unlockedBy(getHasName(DeepAetherModBlocks.CLOUDIUM_DEBRIS.get()), has(DeepAetherModBlocks.CLOUDIUM_DEBRIS.get()))
                .save(consumer);

        smeltingFoodRecipe(DeepAetherModItems.COOKED_QUAIL.get(), DeepAetherModItems.RAW_QUAIL.get(), 0.35F).save(consumer);
        smeltingFoodRecipe(DeepAetherModItems.COOKED_AERGLOW_FISH.get(), DeepAetherModItems.RAW_AERGLOW_FISH.get(), 0.35F).save(consumer);

        makeBoat(DeepAetherModItems.ROSEROOT_BOAT, DeepAetherModBlocks.ROSE_PLANKS.get()).save(consumer);
        makeBoat(DeepAetherModItems.YAGROOT_BOAT, DeepAetherModBlocks.YAGROOT_PLANKS.get()).save(consumer);
        makeBoat(DeepAetherModItems.CRUDEROOT_BOAT, DeepAetherModBlocks.CRUDEROOT_PLANKS.get()).save(consumer);

        makeChestBoat(DeepAetherModItems.ROSEROOT_CHEST_BOAT.get()).save(consumer);
        makeChestBoat(DeepAetherModItems.YAGROOT_CHEST_BOAT.get()).save(consumer);
        makeChestBoat(DeepAetherModItems.CRUDEROOT_CHEST_BOAT.get()).save(consumer);
        hiddenEnchantingRecipe(RecipeCategory.MISC, DeepAetherModItems.MUSIC_DISC_A_MORNING_WISH.get(), Items.MUSIC_DISC_OTHERSIDE, 2.0F, 2500).save(consumer, name("a_moring_wish_enchanting"));
        hiddenEnchantingRecipe(RecipeCategory.MISC, DeepAetherModItems.MUSIC_DISC_NABOORU.get(), Items.MUSIC_DISC_PIGSTEP, 1.0F, 2500).save(consumer, name("nabooru_enchanting"));

        makeskyrootSticks(DeepAetherModBlocks.ROSE_PLANKS.get()).save(consumer, name("skyroot_sticks_from_roseroot_planks"));
        makeskyrootSticks(DeepAetherModBlocks.YAGROOT_PLANKS.get()).save(consumer, name("skyroot_sticks_from_yagroot_planks"));
        makeskyrootSticks(DeepAetherModBlocks.CRUDEROOT_PLANKS.get()).save(consumer, name("skyroot_sticks_from_cruderoot_planks"));


    }



    protected static void cloudiumSmithingRecipe(Consumer<FinishedRecipe> consumer, Item ingredient, RecipeCategory category, Item item) {
        UpgradeRecipeBuilder.smithing(Ingredient.of(ingredient), Ingredient.of(DeepAetherModItems.CLOUDIUM_INGOT.get()), category, item).unlocks("has_cloudium_ingot", has(DeepAetherModItems.CLOUDIUM_INGOT.get())).save(consumer, name(getItemName(item) + "_smithing"));
    }
    protected static SimpleCookingRecipeBuilder smeltingFoodRecipe(ItemLike result, ItemLike ingredient, float experience) {
        return SimpleCookingRecipeBuilder.smelting(Ingredient.of(ingredient), RecipeCategory.FOOD, result, experience, 200)
                .unlockedBy(getHasName(ingredient), has(ingredient));
    }

    protected static ShapedRecipeBuilder makeBoat(Supplier<? extends Item> boat, Block material) {
        return ShapedRecipeBuilder.shaped(RecipeCategory.TRANSPORTATION, boat.get())
                .define('#', material)
                .pattern("# #")
                .pattern("###")
                .unlockedBy(getHasName(material), has(material));
    }
    protected static ShapedRecipeBuilder makeskyrootSticks(Block material) {
        return ShapedRecipeBuilder.shaped(RecipeCategory.MISC, AetherItems.SKYROOT_STICK.get(),4)
                .group("sticks")
                .define('#', material)
                .pattern("#")
                .pattern("#")
                .unlockedBy(getHasName(material), has(material));
    }
    protected static ShapelessRecipeBuilder makeChestBoat(Item boat) {
        return ShapelessRecipeBuilder.shapeless(RecipeCategory.TRANSPORTATION, boat)
                .requires(boat)
                .requires(Tags.Items.RODS_WOODEN)
                .unlockedBy(getHasName(boat), has(boat));
    }
}
