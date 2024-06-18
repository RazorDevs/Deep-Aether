package teamrazor.deepaether.recipe;

import net.minecraft.util.StringRepresentable;

public enum DABookCategory implements StringRepresentable {
    COMBINEABLE_MISC("combinable_misc");

    /**
     * Warning for "deprecation" is suppressed because using {@link StringRepresentable.EnumCodec} is necessary.
     */
    @SuppressWarnings("deprecation")
    public static final StringRepresentable.EnumCodec<DABookCategory> CODEC = StringRepresentable.fromEnum(DABookCategory::values);
    private final String name;

    DABookCategory(String name) {
        this.name = name;
    }

    public String getSerializedName() {
        return this.name;
    }
}
