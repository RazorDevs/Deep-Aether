package teamrazor.deepaether.datagen.tags;

import com.aetherteam.aether.entity.AetherEntityTypes;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.EntityTypeTagsProvider;
import net.minecraft.tags.EntityTypeTags;
import net.minecraftforge.common.data.ExistingFileHelper;
import org.jetbrains.annotations.Nullable;
import teamrazor.deepaether.DeepAether;
import teamrazor.deepaether.init.DAEntities;

import javax.annotation.Nonnull;
import java.util.concurrent.CompletableFuture;

public class DAEntityTagData extends EntityTypeTagsProvider {
    public DAEntityTagData(PackOutput output, CompletableFuture<HolderLookup.Provider> registries, @Nullable ExistingFileHelper helper) {
        super(output, registries, DeepAether.MODID, helper);
    }

    @Nonnull
    @Override
    public String getName() {
        return "Deep Aether EntityType Tags";
    }

    @SuppressWarnings("unchecked")
    @Override
    protected void addTags(HolderLookup.Provider p_256380_) {
        tag(EntityTypeTags.FALL_DAMAGE_IMMUNE).add(
                DAEntities.QUAIL.get()
        );

        tag(DATags.Entities.STERLING_AERCLOUD_BLACKLIST).add(
                AetherEntityTypes.AERWHALE.get(),
                AetherEntityTypes.ZEPHYR.get()
        );
    }
}
