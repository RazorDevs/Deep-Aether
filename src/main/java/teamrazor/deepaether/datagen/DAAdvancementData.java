package teamrazor.deepaether.datagen;

import com.gildedgames.aether.Aether;
import com.gildedgames.aether.data.generators.AetherAdvancementData;
import com.gildedgames.aether.data.resources.registries.AetherBiomes;
import com.gildedgames.aether.data.resources.registries.AetherDimensions;
import com.gildedgames.aether.loot.AetherLoot;
import com.google.common.collect.ImmutableList;
import com.google.gson.annotations.JsonAdapter;
import net.minecraft.advancements.Advancement;
import net.minecraft.advancements.AdvancementRewards;
import net.minecraft.advancements.CriterionTriggerInstance;
import net.minecraft.advancements.FrameType;
import net.minecraft.advancements.critereon.*;
import net.minecraft.commands.CommandFunction;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.advancements.packs.VanillaAdvancementProvider;
import net.minecraft.data.advancements.packs.VanillaAdventureAdvancements;
import net.minecraft.data.advancements.packs.VanillaNetherAdvancements;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.Biomes;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.storage.loot.predicates.LocationCheck;
import net.minecraftforge.common.BiomeManager;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.common.data.ForgeAdvancementProvider;
import net.minecraftforge.fml.common.Mod;
import org.jetbrains.annotations.NonBlocking;
import org.openjdk.nashorn.internal.ir.annotations.Ignore;
import teamrazor.deepaether.DeepAetherMod;
import teamrazor.deepaether.init.DABlocks;
import teamrazor.deepaether.world.biomes.DABiomes;

import javax.annotation.CheckForNull;
import javax.annotation.Nonnull;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.function.Consumer;

public class DAAdvancementData extends ForgeAdvancementProvider {
    public DAAdvancementData(PackOutput output, CompletableFuture<HolderLookup.Provider> registries, ExistingFileHelper helper) {
        super(output, registries, helper, List.of(new DAAdvancements()));
    }


    private static final List<ResourceKey<Biome>> EXPLORABLE_BIOMES = ImmutableList.of(AetherBiomes.SKYROOT_GROVE, AetherBiomes.SKYROOT_FOREST, AetherBiomes.SKYROOT_MEADOW, AetherBiomes.SKYROOT_WOODLAND,
            DABiomes.AERGLOW_FOREST, DABiomes.BLUE_AERGLOW_FOREST, DABiomes.RARE_AERGLOW_FOREST, DABiomes.AETHER_PLAINS);

    public static class DAAdvancements implements AdvancementGenerator {
        @Override
        public void generate(HolderLookup.Provider provider, Consumer<Advancement> consumer, ExistingFileHelper existingFileHelper) {
            Advancement enterAether = addBiomes(Advancement.Builder.advancement(), EXPLORABLE_BIOMES)
                    .parent(new ResourceLocation("aether:enter_aether"))
                    .display(DABlocks.ROSEROOT_SAPLING.get().asItem(), Component.translatable("advancements.deep_aether.explore_aether.title"), Component.translatable("advancements.deep_aether.explore_aether.description"),
                            null, FrameType.CHALLENGE, true, true, false)
                    .rewards(AdvancementRewards.Builder.experience(500))
                    .save(consumer, new ResourceLocation(DeepAetherMod.MODID, "explore_aether"), existingFileHelper);




            //Advancement.Builder.advancement().parent(advancement9).display(Items.NETHERITE_CHESTPLATE, Component.translatable("advancements.nether.netherite_armor.title"), Component.translatable("advancements.nether.netherite_armor.description"), (ResourceLocation)null, FrameType.CHALLENGE, true, true, false).rewards(AdvancementRewards.Builder.experience(100)).addCriterion("netherite_armor", InventoryChangeTrigger.TriggerInstance.hasItems(Items.NETHERITE_HELMET, Items.NETHERITE_CHESTPLATE, Items.NETHERITE_LEGGINGS, Items.NETHERITE_BOOTS)).save(p_249760_, "nether/netherite_armor");

        }
    }


    private static Advancement.Builder addBiomes(Advancement.Builder p_249250_, List<ResourceKey<Biome>> p_251338_) {
        for(ResourceKey<Biome> resourcekey : p_251338_) {
            p_249250_.addCriterion(resourcekey.location().toString(), PlayerTrigger.TriggerInstance.located(LocationPredicate.inBiome(resourcekey)));
        }

        return p_249250_;
    }
}
