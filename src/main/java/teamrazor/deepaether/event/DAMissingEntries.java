package teamrazor.deepaether.event;

import com.mojang.datafixers.optics.profunctors.Mapping;
import net.minecraft.core.registries.Registries;

import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;

import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.*;
import org.spongepowered.asm.mixin.Interface;
import software.bernie.geckolib.event.GeoRenderEvent;
import teamrazor.deepaether.DeepAetherMod;
import teamrazor.deepaether.init.DABlocks;
import teamrazor.deepaether.init.DAItems;

import java.util.function.Predicate;

@Mod.EventBusSubscriber(modid = DeepAetherMod.MODID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class DAMissingEntries {
    @SubscribeEvent
    public static void missingMappings(MissingMappingsEvent event) {
        event.getMappings(ForgeRegistries.Keys.BLOCKS, DeepAetherMod.MODID).stream()
                .filter(mapping -> mapping.getKey().getPath().contains("amberroot_planks")).forEach(blockMapping -> blockMapping.remap(DABlocks.CONBERRY_PLANKS.get()));
        event.getMappings(ForgeRegistries.Keys.BLOCKS, DeepAetherMod.MODID).stream()
                .filter(mapping -> mapping.getKey().getPath().contains("amberroot_button")).forEach(blockMapping -> blockMapping.remap(DABlocks.CONBERRY_BUTTON.get()));
        event.getMappings(ForgeRegistries.Keys.BLOCKS, DeepAetherMod.MODID).stream()
                .filter(mapping -> mapping.getKey().getPath().contains("amberroot_fence")).forEach(blockMapping -> blockMapping.remap(DABlocks.CONBERRY_FENCE.get()));
        event.getMappings(ForgeRegistries.Keys.BLOCKS, DeepAetherMod.MODID).stream()
                .filter(mapping -> mapping.getKey().getPath().contains("amberroot_fence_gate")).forEach(blockMapping -> blockMapping.remap(DABlocks.CONBERRY_FENCE_GATE.get()));
        event.getMappings(ForgeRegistries.Keys.BLOCKS, DeepAetherMod.MODID).stream()
                .filter(mapping -> mapping.getKey().getPath().contains("amberroot_leaves")).forEach(blockMapping -> blockMapping.remap(DABlocks.CONBERRY_LEAVES.get()));
        event.getMappings(ForgeRegistries.Keys.BLOCKS, DeepAetherMod.MODID).stream()
                .filter(mapping -> mapping.getKey().getPath().contains("amberroot_log")).forEach(blockMapping -> blockMapping.remap(DABlocks.CONBERRY_LOG.get()));
        event.getMappings(ForgeRegistries.Keys.BLOCKS, DeepAetherMod.MODID).stream()
                .filter(mapping -> mapping.getKey().getPath().contains("amberroot_pressure_plate")).forEach(blockMapping -> blockMapping.remap(DABlocks.CONBERRY_PRESSURE_PLATE.get()));
        event.getMappings(ForgeRegistries.Keys.BLOCKS, DeepAetherMod.MODID).stream()
                .filter(mapping -> mapping.getKey().getPath().contains("amberroot_sapling")).forEach(blockMapping -> blockMapping.remap(DABlocks.CONBERRY_SAPLING.get()));
        event.getMappings(ForgeRegistries.Keys.BLOCKS, DeepAetherMod.MODID).stream()
                .filter(mapping -> mapping.getKey().getPath().contains("amberroot_sign")).forEach(blockMapping -> blockMapping.remap(DABlocks.CONBERRY_SIGN.get()));
        event.getMappings(ForgeRegistries.Keys.BLOCKS, DeepAetherMod.MODID).stream()
                .filter(mapping -> mapping.getKey().getPath().contains("amberroot_slab")).forEach(blockMapping -> blockMapping.remap(DABlocks.CONBERRY_SLAB.get()));
        event.getMappings(ForgeRegistries.Keys.BLOCKS, DeepAetherMod.MODID).stream()
                .filter(mapping -> mapping.getKey().getPath().contains("amberroot_stairs")).forEach(blockMapping -> blockMapping.remap(DABlocks.CONBERRY_STAIRS.get()));
        event.getMappings(ForgeRegistries.Keys.BLOCKS, DeepAetherMod.MODID).stream()
                .filter(mapping -> mapping.getKey().getPath().contains("amberroot_trapdoor")).forEach(blockMapping -> blockMapping.remap(DABlocks.CONBERRY_TRAPDOOR.get()));
        event.getMappings(ForgeRegistries.Keys.BLOCKS, DeepAetherMod.MODID).stream()
                .filter(mapping -> mapping.getKey().getPath().contains("amberroot_door")).forEach(blockMapping -> blockMapping.remap(DABlocks.CONBERRY_DOOR.get()));
        event.getMappings(ForgeRegistries.Keys.BLOCKS, DeepAetherMod.MODID).stream()
                .filter(mapping -> mapping.getKey().getPath().contains("amberroot_wall")).forEach(blockMapping -> blockMapping.remap(DABlocks.CONBERRY_WALL.get()));
        event.getMappings(ForgeRegistries.Keys.BLOCKS, DeepAetherMod.MODID).stream()
                .filter(mapping -> mapping.getKey().getPath().contains("amberroot_wall_sign")).forEach(blockMapping -> blockMapping.remap(DABlocks.CONBERRY_WALL_SIGN.get()));
        event.getMappings(ForgeRegistries.Keys.BLOCKS, DeepAetherMod.MODID).stream()
                .filter(mapping -> mapping.getKey().getPath().contains("amberroot_wood")).forEach(blockMapping -> blockMapping.remap(DABlocks.CONBERRY_WOOD.get()));
        event.getMappings(ForgeRegistries.Keys.BLOCKS, DeepAetherMod.MODID).stream()
                .filter(mapping -> mapping.getKey().getPath().contains("potted_amberroot_sapling")).forEach(blockMapping -> blockMapping.remap(DABlocks.POTTED_CONBERRY_SAPLING.get()));
        event.getMappings(ForgeRegistries.Keys.BLOCKS, DeepAetherMod.MODID).stream()
                .filter(mapping -> mapping.getKey().getPath().contains("stripped_amberroot_log")).forEach(blockMapping -> blockMapping.remap(DABlocks.STRIPPED_CONBERRY_LOG.get()));
        event.getMappings(ForgeRegistries.Keys.BLOCKS, DeepAetherMod.MODID).stream()
                .filter(mapping -> mapping.getKey().getPath().contains("stripped_amberroot_wall")).forEach(blockMapping -> blockMapping.remap(DABlocks.STRIPPED_CONBERRY_WALL.get()));
        event.getMappings(ForgeRegistries.Keys.BLOCKS, DeepAetherMod.MODID).stream()
                .filter(mapping -> mapping.getKey().getPath().contains("stripped_amberroot_wood")).forEach(blockMapping -> blockMapping.remap(DABlocks.STRIPPED_CONBERRY_WALL.get()));

        event.getMappings(ForgeRegistries.Keys.ITEMS, DeepAetherMod.MODID).stream().filter(mapping -> mapping.getKey().getPath().contains("cloudium_debris")).forEach(blockMapping -> blockMapping.remap(DABlocks.STRATUS_DEBRIS.get().asItem()));
        event.getMappings(ForgeRegistries.Keys.ITEMS, DeepAetherMod.MODID).stream().filter(mapping -> mapping.getKey().getPath().contains("cloudium_block")).forEach(blockMapping -> blockMapping.remap(DABlocks.STRATUS_BLOCK.get().asItem()));

        event.getMappings(ForgeRegistries.Keys.ITEMS, DeepAetherMod.MODID).stream().filter(mapping -> mapping.getKey().getPath().contains("cloudium_axe")).forEach(blockMapping -> blockMapping.remap(DAItems.STRATUS_AXE.get()));
        event.getMappings(ForgeRegistries.Keys.BLOCKS, DeepAetherMod.MODID).stream().filter(mapping -> mapping.getKey().getPath().contains("cloudium_block")).forEach(blockMapping -> blockMapping.remap(DABlocks.STRATUS_BLOCK.get()));
        event.getMappings(ForgeRegistries.Keys.ITEMS, DeepAetherMod.MODID).stream().filter(mapping -> mapping.getKey().getPath().contains("cloudium_boots")).forEach(blockMapping -> blockMapping.remap(DAItems.STRATUS_BOOTS.get()));
        event.getMappings(ForgeRegistries.Keys.ITEMS, DeepAetherMod.MODID).stream().filter(mapping -> mapping.getKey().getPath().contains("cloudium_chestplate")).forEach(blockMapping -> blockMapping.remap(DAItems.STRATUS_CHESTPLATE.get()));
        event.getMappings(ForgeRegistries.Keys.BLOCKS, DeepAetherMod.MODID).stream().filter(mapping -> mapping.getKey().getPath().contains("cloudium_debris")).forEach(blockMapping -> blockMapping.remap(DABlocks.STRATUS_DEBRIS.get()));
        event.getMappings(ForgeRegistries.Keys.ITEMS, DeepAetherMod.MODID).stream().filter(mapping -> mapping.getKey().getPath().contains("cloudium_gloves")).forEach(blockMapping -> blockMapping.remap(DAItems.STRATUS_GLOVES.get()));
        event.getMappings(ForgeRegistries.Keys.ITEMS, DeepAetherMod.MODID).stream().filter(mapping -> mapping.getKey().getPath().contains("cloudium_helmet")).forEach(blockMapping -> blockMapping.remap(DAItems.STRATUS_HELMET.get()));
        event.getMappings(ForgeRegistries.Keys.ITEMS, DeepAetherMod.MODID).stream().filter(mapping -> mapping.getKey().getPath().contains("cloudium_hoe")).forEach(blockMapping -> blockMapping.remap(DAItems.STRATUS_HOE.get()));
        event.getMappings(ForgeRegistries.Keys.ITEMS, DeepAetherMod.MODID).stream().filter(mapping -> mapping.getKey().getPath().contains("cloudium_ingot")).forEach(blockMapping -> blockMapping.remap(DAItems.STRATUS_INGOT.get()));
        event.getMappings(ForgeRegistries.Keys.ITEMS, DeepAetherMod.MODID).stream().filter(mapping -> mapping.getKey().getPath().contains("cloudium_leggings")).forEach(blockMapping -> blockMapping.remap(DAItems.STRATUS_LEGGINGS.get()));
        event.getMappings(ForgeRegistries.Keys.ITEMS, DeepAetherMod.MODID).stream().filter(mapping -> mapping.getKey().getPath().contains("cloudium_pickaxe")).forEach(blockMapping -> blockMapping.remap(DAItems.STRATUS_PICKAXE.get()));
        event.getMappings(ForgeRegistries.Keys.ITEMS, DeepAetherMod.MODID).stream().filter(mapping -> mapping.getKey().getPath().contains("cloudium_ring")).forEach(blockMapping -> blockMapping.remap(DAItems.STRATUS_RING.get()));
        event.getMappings(ForgeRegistries.Keys.ITEMS, DeepAetherMod.MODID).stream().filter(mapping -> mapping.getKey().getPath().contains("cloudium_scrap")).forEach(blockMapping -> blockMapping.remap(DAItems.STRATUS_SCRAP.get()));
        event.getMappings(ForgeRegistries.Keys.ITEMS, DeepAetherMod.MODID).stream().filter(mapping -> mapping.getKey().getPath().contains("cloudium_shield")).forEach(blockMapping -> blockMapping.remap(DAItems.STRATUS_SHIELD.get()));
        event.getMappings(ForgeRegistries.Keys.ITEMS, DeepAetherMod.MODID).stream().filter(mapping -> mapping.getKey().getPath().contains("cloudium_shovel")).forEach(blockMapping -> blockMapping.remap(DAItems.STRATUS_SHOVEL.get()));
        event.getMappings(ForgeRegistries.Keys.ITEMS, DeepAetherMod.MODID).stream().filter(mapping -> mapping.getKey().getPath().contains("cloudium_sword")).forEach(blockMapping -> blockMapping.remap(DAItems.STRATUS_SWORD.get()));

    }
}
