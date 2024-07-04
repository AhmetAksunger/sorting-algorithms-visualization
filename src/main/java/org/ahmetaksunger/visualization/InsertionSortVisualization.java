package org.ahmetaksunger.visualization;

import org.ahmetaksunger.frame.SortingVisualization;
import org.ahmetaksunger.model.Box;
import org.ahmetaksunger.util.BoxNotePlayer;

import java.awt.*;
import java.util.List;

public class InsertionSortVisualization extends SortingVisualization {

    private static final long sleep = 50;

    public InsertionSortVisualization(List<Box> boxes, int boxSpacing, BoxNotePlayer player) {
        super(boxes, boxSpacing, player);
    }

    /**
     * Constructs an InsertionSortVisualization with the specified list of boxes and box spacing.
     *
     * @param boxes      The list of boxes to visualize and sort.
     * @param boxSpacing The horizontal spacing between boxes.
     */
    public InsertionSortVisualization(List<Box> boxes, int boxSpacing) {
        super(boxes, boxSpacing);
    }

    @Override
    public void visualize() throws InterruptedException {
        setTitle(this.getClass().getSimpleName());
        setVisible(true);
        this.sort();
    }

    /**
     * Sorts the list of boxes using the Insertion Sort algorithm and visualizes each step.
     *
     * @throws InterruptedException If the thread is interrupted while sleeping.
     */
    public void sort() throws InterruptedException {
        final List<Box> boxes = getBoxes();
        final BoxNotePlayer player = getBoxNotePlayer();

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
}
