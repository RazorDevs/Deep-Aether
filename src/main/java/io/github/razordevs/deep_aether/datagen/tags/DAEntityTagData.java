package io.github.razordevs.deep_aether.datagen.tags;

import com.aetherteam.aether.AetherTags;
import com.aetherteam.aether.entity.AetherEntityTypes;
import io.github.razordevs.deep_aether.DeepAether;
import io.github.razordevs.deep_aether.init.DAEntities;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.EntityTypeTagsProvider;
import net.minecraft.tags.EntityTypeTags;
import net.neoforged.neoforge.common.Tags;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import org.jetbrains.annotations.Nullable;

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

    @Override
    protected void addTags(HolderLookup.Provider p_256380_) {
        tag(EntityTypeTags.FALL_DAMAGE_IMMUNE).add(
                DAEntities.QUAIL.get()
        );

        tag(DATags.Entities.STERLING_AERCLOUD_BLACKLIST).add(
                AetherEntityTypes.AERWHALE.get(),
                AetherEntityTypes.EVIL_WHIRLWIND.get(),
                AetherEntityTypes.WHIRLWIND.get(),
                AetherEntityTypes.ZEPHYR.get(),
                AetherEntityTypes.ZEPHYR_SNOWBALL.get(),
                DAEntities.EOTS_SEGMENT.get(),
                DAEntities.EOTS_CONTROLLER.get()
        );

        tag(DATags.Entities.FRIENDLY_WIND_CHARGE_BLACKLIST).add(
                AetherEntityTypes.AERWHALE.get(),
                AetherEntityTypes.EVIL_WHIRLWIND.get(),
                AetherEntityTypes.WHIRLWIND.get(),
                AetherEntityTypes.ZEPHYR.get(),
                AetherEntityTypes.ZEPHYR_SNOWBALL.get()
        );
        tag(DATags.Entities.WIND_CHARGE_BLACKLIST).add(
                DAEntities.EOTS_SEGMENT.get(),
                DAEntities.EOTS_CONTROLLER.get()
        ).addTag(DATags.Entities.FRIENDLY_WIND_CHARGE_BLACKLIST);

        tag(Tags.EntityTypes.BOSSES).add(
                DAEntities.EOTS_SEGMENT.get(),
                DAEntities.EOTS_CONTROLLER.get()
        );
        tag(EntityTypeTags.ARROWS).add(
                DAEntities.STORM_ARROW.get()
        );
        tag(DATags.Entities.SLIDER_SLAM_BLACKLIST).add(
                DAEntities.EOTS_CONTROLLER.get()
        );
        tag(AetherTags.Entities.UNHOOKABLE).add(
                DAEntities.EOTS_CONTROLLER.get(),
                DAEntities.EOTS_SEGMENT.get()
        );
        tag(AetherTags.Entities.UNLAUNCHABLE).add(
                DAEntities.EOTS_CONTROLLER.get()
        );
    }
}
