package org.ahmetaksunger.util;

import org.ahmetaksunger.model.Box;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

/**
 * Utility class for handling operations related to Box objects.
 */
public class BoxUtils {

    private BoxUtils() {
    }

    private static final Random RANDOM = new Random();

    /**
     * Generates a list of Box objects sorted by increasing height.
     *
     * @param amount    The number of boxes to generate.
     * @param maxHeight The maximum height of the tallest box.
     * @return A list of Box objects sorted by increasing height.
     */
    public static List<Box> sorted(int amount, int maxHeight) {

        List<Box> boxes = new ArrayList<>();

        int increase = maxHeight / amount;
        int x = 0;
        int y = maxHeight - increase;
        int height = increase;
        for (int i = 0; i < amount; i++) {
            boxes.add(new Box(height, x, y));
            x += 30;
            y -= increase;
            height += increase;
        }

        return boxes;
    }


    /**
     * Shuffles the x-coordinates of the boxes randomly.
     *
     * @param boxes The list of boxes to shuffle.
     */
    public static void shuffleX(List<Box> boxes) {
        for (int i = boxes.size() - 1; i >= 0; i--) {
            int index = RANDOM.nextInt(i + 1);
            int tempx = boxes.get(index).getX();
            boxes.get(index).setX(boxes.get(i).getX());
            boxes.get(i).setX(tempx);
            Collections.swap(boxes, index, i);
        }
    }

}
