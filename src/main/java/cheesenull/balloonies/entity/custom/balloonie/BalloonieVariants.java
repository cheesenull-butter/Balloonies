package cheesenull.balloonies.entity.custom.balloonie;

import java.util.Arrays;
import java.util.Comparator;

public enum BalloonieVariants {

    BLUE(0),
    GREEN(1),
    ORANGE(2),
    RED(3),
    WHITE(4);

    private static final BalloonieVariants[] BY_ID = Arrays.stream(values()).sorted(Comparator.
            comparingInt(BalloonieVariants::getId)).toArray(BalloonieVariants[]::new);
    private final int id;

    BalloonieVariants(int id) {
        this.id = id;
    }

    public int getId() {
        return this.id;
    }

    public static BalloonieVariants byId(int id) {
        return BY_ID[id % BY_ID.length];
    }

}
