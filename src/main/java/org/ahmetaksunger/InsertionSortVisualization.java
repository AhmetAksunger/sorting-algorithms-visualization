package org.ahmetaksunger;

import org.ahmetaksunger.frame.SortingVisualization;
import org.ahmetaksunger.model.Box;
import org.ahmetaksunger.util.BoxNotePlayer;
import org.ahmetaksunger.util.BoxUtils;

import java.awt.*;
import java.util.List;

public class InsertionSortVisualization extends SortingVisualization {

    private static final long sleep = 100;

    private BoxNotePlayer player;

    /**
     * Constructs an InsertionSortVisualization with the specified list of boxes and box spacing.
     *
     * @param boxes      The list of boxes to visualize and sort.
     * @param boxSpacing The horizontal spacing between boxes.
     * @throws InterruptedException If the thread is interrupted while sleeping.
     */
    public InsertionSortVisualization(List<Box> boxes, int boxSpacing) throws InterruptedException {
        super(boxes, boxSpacing);
        this.player = new BoxNotePlayer(boxes, 50, 80, true);
        sort();
    }

    /**
     * Sorts the list of boxes using the Insertion Sort algorithm and visualizes each step.
     *
     * @throws InterruptedException If the thread is interrupted while sleeping.
     */
    public void sort() throws InterruptedException {
        List<Box> boxes = getBoxes();
        for (int i = 1; i < boxes.size(); i++) {
            Box key = boxes.get(i);
            int j = i - 1;

            while (j >= 0 && boxes.get(j).getHeight() > key.getHeight()) {
                boxes.set(j + 1, boxes.get(j));
                boxes.get(j + 1).setColor(Color.GREEN);
                super.repaint();
                player.playBox(boxes.get(j + 1), 100, sleep);
                boxes.get(j + 1).setColor(Color.WHITE);
                j = j - 1;
            }
            boxes.set(j + 1, key);
            boxes.get(j + 1).setColor(Color.GREEN);
            super.repaint();
            player.playBox(boxes.get(j + 1), 100, sleep);
            boxes.get(j + 1).setColor(Color.WHITE);
        }

        sortingDoneAnimation();

    }

    public static void main(String[] args) throws InterruptedException {
        List<Box> defaultBoxes = BoxUtils.sorted(40, 600);
        BoxUtils.shuffleX(defaultBoxes);
        new InsertionSortVisualization(defaultBoxes, 30);
    }
}
