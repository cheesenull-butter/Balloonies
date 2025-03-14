package cheesenull.balloonies.entity.custom;

import java.util.Arrays;
import java.util.Comparator;

public enum BalloonieVariant {

    BLUE(0),
    LIGHT_BLUE(1),
    WHITE(2),;

    private static final BalloonieVariant[] BY_ID = Arrays.stream(values()).sorted(Comparator.
            comparingInt(BalloonieVariant::getId)).toArray(BalloonieVariant[]::new);
    private final int id;

    BalloonieVariant(int id) {
        this.id = id;
    }

    public int getId() {
        return this.id;
    }

    public static BalloonieVariant byId(int id) {
        return BY_ID[id % BY_ID.length];
    }

}
