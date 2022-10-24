package com.example.stonepaperscissors.common;

import java.util.Random;

public enum Shape {
    STONE,
    PAPER,
    SCISSORS;

    private static final Random RANDOM = new Random();

    public static Shape randomShape() {
        Shape[] shapes = values();
        return shapes[RANDOM.nextInt(shapes.length)];
    }
}
