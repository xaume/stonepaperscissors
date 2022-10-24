package com.example.stonepaperscissors.common;

import java.util.Random;

public class Utils {
    private static final Random RANDOM = new Random();

    private Utils() {
        // not called
    }

    public static Shape randomShape() {
        Shape[] shapes = Shape.values();
        return shapes[RANDOM.nextInt(shapes.length)];
    }
}
