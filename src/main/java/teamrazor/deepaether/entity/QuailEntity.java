package teamrazor.deepaether.entity;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.entity.SpawnPlacements;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.animal.Chicken;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.biome.MobSpawnSettings;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraftforge.common.ForgeMod;
import net.minecraftforge.event.world.BiomeLoadingEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.network.PlayMessages;
import teamrazor.deepaether.init.DeepAetherModEntities;

import java.util.Set;

@Mod.EventBusSubscriber
public class QuailEntity extends Chicken {
    private static final Set<ResourceLocation> SPAWN_BIOMES = Set.of(new ResourceLocation("aether:skyroot_grove"),
            new ResourceLocation("aether:skyroot_forest"), new ResourceLocation("aether:skyroot_thicket"),
            new ResourceLocation("aether:golden_forest"));

    @SubscribeEvent
    public static void addLivingEntityToBiomes(BiomeLoadingEvent event) {
        if (SPAWN_BIOMES.contains(event.getName()))
            event.getSpawns().getSpawner(MobCategory.CREATURE)
                    .add(new MobSpawnSettings.SpawnerData(DeepAetherModEntities.QUAIL.get(), 10, 2, 4));
    }

    public QuailEntity(PlayMessages.SpawnEntity packet, Level world) {
        this(DeepAetherModEntities.QUAIL.get(), world);
    }

    public QuailEntity(EntityType<QuailEntity> type, Level world) {
        super(type, world);
    }

    public static AttributeSupplier.Builder createAttributes() {
        return Mob.createMobAttributes()
                .add(Attributes.MOVEMENT_SPEED, 0.5)
                .add(Attributes.MAX_HEALTH, 4)
                .add(ForgeMod.SWIM_SPEED.get(), 1.2);
    }

    public static void init() {
        SpawnPlacements.register(DeepAetherModEntities.QUAIL.get(), SpawnPlacements.Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES,
                (entityType, world, reason, pos,
                 random) -> (world.getBlockState(pos.above()).is(Blocks.AIR)));
    }

}
