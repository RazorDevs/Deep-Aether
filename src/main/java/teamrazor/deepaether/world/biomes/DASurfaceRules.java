package teamrazor.deepaether.world.biomes;


import com.aetherteam.aether.block.AetherBlockStateProperties;
import com.aetherteam.aether.block.AetherBlocks;
import com.aetherteam.aether.data.resources.AetherFeatureStates;
import com.aetherteam.aether.data.resources.registries.AetherDimensions;
import net.minecraft.core.Holder;
import net.minecraft.core.Registry;
import net.minecraft.core.RegistryAccess;
import net.minecraft.core.registries.Registries;
import net.minecraft.server.MinecraftServer;
import net.minecraft.world.level.chunk.ChunkGenerator;
import net.minecraft.world.level.dimension.LevelStem;
import net.minecraft.world.level.levelgen.*;
import net.minecraft.world.level.levelgen.placement.CaveSurface;
import net.minecraftforge.event.server.ServerAboutToStartEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import teamrazor.deepaether.DeepAetherMod;
import teamrazor.deepaether.init.DABlocks;
import teamrazor.deepaether.world.noise.DANoises;


import java.util.ArrayList;
import java.util.List;


@Mod.EventBusSubscriber(modid = DeepAetherMod.MODID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class DASurfaceRules {

    @SubscribeEvent
    public static void onServerAboutToStart(ServerAboutToStartEvent event) {
        MinecraftServer server = event.getServer();
        RegistryAccess registryAccess = server.registryAccess();
        Registry<LevelStem> levelStemRegistry = registryAccess.registryOrThrow(Registries.LEVEL_STEM);
        LevelStem levelStem = levelStemRegistry.get(AetherDimensions.AETHER_LEVEL_STEM);
        ChunkGenerator chunkGenerator = levelStem.generator();


        // Inject biomes to biome source
        if (chunkGenerator instanceof NoiseBasedChunkGenerator noiseGenerator) {
            NoiseGeneratorSettings noiseGeneratorSettings = noiseGenerator.settings.value();
            SurfaceRules.RuleSource currentRuleSource = noiseGeneratorSettings.surfaceRule();
            if (currentRuleSource instanceof SurfaceRules.SequenceRuleSource sequenceRuleSource) {
                List<SurfaceRules.RuleSource> surfaceRules = new ArrayList<>(sequenceRuleSource.sequence());
                surfaceRules.add(0, SurfaceRules.ifTrue(SurfaceRules.isBiome(DABiomes.YAGROOT_SWAMP),
                        SurfaceRules.ifTrue(SurfaceRules.ON_FLOOR, SurfaceRules.state(DABlocks.AETHER_MUD.get().defaultBlockState().setValue(AetherBlockStateProperties.DOUBLE_DROPS, true)))));
                surfaceRules.add(0, SurfaceRules.ifTrue(SurfaceRules.isBiome(DABiomes.YAGROOT_SWAMP),
                        SurfaceRules.ifTrue(SurfaceRules.stoneDepthCheck(0, true, 0, CaveSurface.FLOOR), SurfaceRules.state(DABlocks.AETHER_MUD.get().defaultBlockState().setValue(AetherBlockStateProperties.DOUBLE_DROPS, true)))));
                surfaceRules.add(1, SurfaceRules.ifTrue(SurfaceRules.isBiome(DABiomes.GOLDEN_HEIGHTS),
                        SurfaceRules.ifTrue(SurfaceRules.ON_FLOOR, SurfaceRules.state(DABlocks.GOLDEN_HEIGHTS_GRASS_BLOCK.get().defaultBlockState().setValue(AetherBlockStateProperties.DOUBLE_DROPS, true)))));

                surfaceRules.add(1, SurfaceRules.ifTrue(SurfaceRules.isBiome(DABiomes.GOLDEN_HEIGHTS),
                        SurfaceRules.ifTrue(SurfaceRules.ON_FLOOR, SurfaceRules.state(DABlocks.GOLDEN_HEIGHTS_GRASS_BLOCK.get().defaultBlockState().setValue(AetherBlockStateProperties.DOUBLE_DROPS, true)))));

                surfaceRules.add(0, SurfaceRules.ifTrue(SurfaceRules.isBiome(DABiomes.GOLDEN_HEIGHTS),
                        SurfaceRules.ifTrue(SurfaceRules.ON_FLOOR,
                                SurfaceRules.ifTrue(SurfaceRules.noiseCondition(Noises.SWAMP, 0.2D),
                                        SurfaceRules.state(AetherFeatureStates.QUICKSOIL)))));
                NoiseGeneratorSettings moddedNoiseGeneratorSettings = new NoiseGeneratorSettings(noiseGeneratorSettings.noiseSettings(),
                        noiseGeneratorSettings.defaultBlock(), noiseGeneratorSettings.defaultFluid(), noiseGeneratorSettings.noiseRouter(),
                        SurfaceRules.sequence(surfaceRules.toArray(SurfaceRules.RuleSource[]::new)), noiseGeneratorSettings.spawnTarget(),
                        noiseGeneratorSettings.seaLevel(), noiseGeneratorSettings.disableMobGeneration(),
                        noiseGeneratorSettings.aquifersEnabled(), noiseGeneratorSettings.oreVeinsEnabled(),
                        noiseGeneratorSettings.useLegacyRandomSource());
                noiseGenerator.settings = new Holder.Direct<>(moddedNoiseGeneratorSettings);
            }
        }
    }
}
