package org.ahmetaksunger;

import org.ahmetaksunger.frame.SortingVisualization;
import org.ahmetaksunger.model.Box;
import org.ahmetaksunger.util.BoxNotePlayer;
import org.ahmetaksunger.util.BoxUtils;

import java.awt.*;
import java.util.Collections;
import java.util.List;

/**
 * A visualization of the Bubble Sort algorithm using graphical boxes.
 * Extends SortingVisualization to provide visualization capabilities.
 */
public class BubbleSortVisualization extends SortingVisualization {

    private static final long sleep = 100;

    private BoxNotePlayer player;

    /**
     * Constructs a BubbleSortVisualization with the specified list of boxes and box spacing.
     *
     * @param boxes      The list of boxes to visualize and sort.
     * @param boxSpacing The horizontal spacing between boxes.
     * @throws InterruptedException If the thread is interrupted while sleeping.
     */
    public BubbleSortVisualization(List<Box> boxes, int boxSpacing) throws InterruptedException {
        super(boxes, boxSpacing);
        this.player = new BoxNotePlayer(boxes, 50, 80, true);
        sort();
    }

    /**
     * Sorts the list of boxes using the Bubble Sort algorithm and visualizes each step.
     *
     * @throws InterruptedException If the thread is interrupted while sleeping.
     */
    public void sort() throws InterruptedException {

        List<Box> boxes = getBoxes();
        boolean swapped = false;
        for (int i = 0; i < boxes.size() - 1; i++) {
            for (int j = 0; j < boxes.size() - i - 1; j++) {
                if (boxes.get(j).getHeight() > boxes.get(j + 1).getHeight()) {
                    Collections.swap(boxes, j, j + 1);
                    boxes.get(j).setColor(Color.GREEN);
                    boxes.get(j+1).setColor(Color.RED);
                    super.repaint();
                    player.playBox(boxes.get(j), 100, sleep);
                    boxes.get(j).setColor(Color.WHITE);
                    boxes.get(j+1).setColor(Color.WHITE);
                    swapped = true;
                }
            }

            if (!swapped) {
                break;
            }
        }

        sortingDoneAnimation();
    }

    public static void main(String[] args) throws InterruptedException {
        List<Box> defaultBoxes = BoxUtils.sorted(40, 600);
        BoxUtils.shuffleX(defaultBoxes);
        new BubbleSortVisualization(defaultBoxes, 30);
    }
}
