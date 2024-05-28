package teamrazor.deepaether.world.feature.tree.decorators;

import com.mojang.serialization.Codec;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.level.levelgen.feature.treedecorators.TreeDecorator;
import net.minecraft.world.level.levelgen.feature.treedecorators.TreeDecoratorType;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;
import teamrazor.deepaether.DeepAether;

public class DADecoratorType<P extends TreeDecorator> extends TreeDecoratorType<P>{

    public static final DeferredRegister<TreeDecoratorType<?>> REGISTRY = DeferredRegister.create(BuiltInRegistries.TREE_DECORATOR_TYPE, DeepAether.MODID);

    public static final DeferredHolder<TreeDecoratorType<?>, TreeDecoratorType<FlowerDecorator>> FLOWERS = register("flowers", FlowerDecorator.CODEC);
    public static final DeferredHolder<TreeDecoratorType<?>, TreeDecoratorType<YagrootVineDecorator>> YAGVINES = register("yagvines", YagrootVineDecorator.CODEC);
    public static final DeferredHolder<TreeDecoratorType<?>, TreeDecoratorType<SunrootHangerDecorator>> SUNROOT_HANGER = register("sunroot_hanger", SunrootHangerDecorator.CODEC);
    public static final DeferredHolder<TreeDecoratorType<?>, TreeDecoratorType<GlowingVineDecorator>> GLOWING_VINE = register("glowing_vine", GlowingVineDecorator.CODEC);
    private final Codec<P> codec;

    private static <P extends TreeDecorator> DeferredHolder<TreeDecoratorType<?>, TreeDecoratorType<P>> register(String s, Codec<P> codec) {
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
