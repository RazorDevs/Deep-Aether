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
import software.bernie.geckolib.event.GeoRenderEvent;
import teamrazor.deepaether.DeepAetherMod;
import teamrazor.deepaether.init.DABlocks;

import java.util.function.Predicate;

@Mod.EventBusSubscriber(modid = DeepAetherMod.MODID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class DAMissingEntries {

    public static final ResourceKey<Block> AMBERROOT_PLANKS = block("amberroot_planks");


    private static ResourceKey<Block> block(String name) {
        return ResourceKey.create(Registries.BLOCK, new ResourceLocation(DeepAetherMod.MODID, name));
    }


    @SubscribeEvent
    public static void missingMappings(MissingMappingsEvent missingMappingsEvent) {
        missingMappingsEvent.getAllMappings(Registries.BLOCK).get(1210).remap(DABlocks.CONBERRY_BUTTON.get());

        missingMappingsEvent.getAllMappings(Registries.BLOCK).get(1211).remap(DABlocks.CONBERRY_DOOR.get());
        missingMappingsEvent.getAllMappings(Registries.BLOCK).get(1207).remap(DABlocks.CONBERRY_FENCE.get());
        missingMappingsEvent.getAllMappings(Registries.BLOCK).get(1208).remap(DABlocks.CONBERRY_FENCE_GATE.get());
        missingMappingsEvent.getAllMappings(Registries.BLOCK).get(1213).remap(DABlocks.CONBERRY_LEAVES.get());
        missingMappingsEvent.getAllMappings(Registries.BLOCK).get(1199).remap(DABlocks.CONBERRY_LOG.get());
        missingMappingsEvent.getAllMappings(Registries.BLOCK).get(1204).remap(DABlocks.CONBERRY_PLANKS.get());
        missingMappingsEvent.getAllMappings(Registries.BLOCK).get(1209).remap(DABlocks.CONBERRY_PRESSURE_PLATE.get());

        missingMappingsEvent.getAllMappings(Registries.BLOCK).get(1214).remap(DABlocks.CONBERRY_SAPLING.get());
        missingMappingsEvent.getAllMappings(Registries.BLOCK).get(1216).remap(DABlocks.CONBERRY_SIGN.get());
        missingMappingsEvent.getAllMappings(Registries.BLOCK).get(1206).remap(DABlocks.CONBERRY_SLAB.get());
        missingMappingsEvent.getAllMappings(Registries.BLOCK).get(1205).remap(DABlocks.CONBERRY_STAIRS.get());
        missingMappingsEvent.getAllMappings(Registries.BLOCK).get(1212).remap(DABlocks.CONBERRY_TRAPDOOR.get());
        missingMappingsEvent.getAllMappings(Registries.BLOCK).get(1200).remap(DABlocks.CONBERRY_WALL.get());
        missingMappingsEvent.getAllMappings(Registries.BLOCK).get(1215).remap(DABlocks.CONBERRY_WALL_SIGN.get());
        missingMappingsEvent.getAllMappings(Registries.BLOCK).get(1198).remap(DABlocks.CONBERRY_WOOD.get());
        missingMappingsEvent.getAllMappings(Registries.BLOCK).get(1202).remap(DABlocks.STRIPPED_CONBERRY_LOG.get());
        missingMappingsEvent.getAllMappings(Registries.BLOCK).get(1203).remap(DABlocks.STRIPPED_CONBERRY_WALL.get());
        missingMappingsEvent.getAllMappings(Registries.BLOCK).get(1201).remap(DABlocks.STRIPPED_CONBERRY_WOOD.get());

    }
}
