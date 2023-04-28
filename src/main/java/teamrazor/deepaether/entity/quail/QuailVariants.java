package teamrazor.deepaether.entity.quail;

import java.util.Arrays;
import java.util.Comparator;

public enum QuailVariants {
    OLD_GREEN(0),
    PINK(1),
    PURPLE(2),
    TROPICAL_BLUE(3),
    FADED_YELLOW(4),
    LIGHT_BLUE(5),
    COPPER(6);

    private static final QuailVariants[] BY_ID = Arrays.stream(values()).sorted(Comparator.
            comparingInt(QuailVariants::getId)).toArray(QuailVariants[]::new);
    private final int id;

    QuailVariants(int id) {
        this.id = id;
    }

    public int getId() {
        return this.id;
    }

    public static QuailVariants byId(int id) {
        return BY_ID[id % BY_ID.length];
    }
}

