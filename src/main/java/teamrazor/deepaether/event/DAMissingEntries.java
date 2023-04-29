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
    }
}
