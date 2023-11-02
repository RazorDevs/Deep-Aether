package teamrazor.deepaether.world.feature.tree.decorators;

import com.mojang.serialization.Codec;
import net.minecraft.world.level.levelgen.feature.treedecorators.TreeDecorator;
import net.minecraft.world.level.levelgen.feature.treedecorators.TreeDecoratorType;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import teamrazor.deepaether.DeepAetherMod;

public class DADecoratorType<P extends TreeDecorator> extends TreeDecoratorType<P>{

    public static final DeferredRegister<TreeDecoratorType<?>> REGISTRY = DeferredRegister.create(ForgeRegistries.TREE_DECORATOR_TYPES, DeepAetherMod.MODID);

    public static final RegistryObject<TreeDecoratorType<FlowerDecorator>> FLOWERS = register("flowers", FlowerDecorator.CODEC);
    public static final RegistryObject<TreeDecoratorType<YagrootVineDecorator>> YAGVINES = register("yagvines", YagrootVineDecorator.CODEC);
    public static final RegistryObject<TreeDecoratorType<SunrootHangerDecorator>> SUNROOT_HANGER = register("sunroot_hanger", SunrootHangerDecorator.CODEC);

    private final Codec<P> codec;

    private static <P extends TreeDecorator> RegistryObject<TreeDecoratorType<P>> register(String s, Codec<P> codec) {
        return REGISTRY.register(s, () -> new TreeDecoratorType<>(codec));
    }

    public DADecoratorType(Codec<P> codec) {
        super(codec);
        this.codec = codec;
    }

    public Codec<P> codec() {
        return this.codec;
    }
}
