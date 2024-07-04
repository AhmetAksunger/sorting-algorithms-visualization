package org.ahmetaksunger;

import org.ahmetaksunger.frame.SortingVisualization;
import org.ahmetaksunger.model.Box;
import org.ahmetaksunger.util.BoxNotePlayer;

import java.awt.*;
import java.util.Collections;
import java.util.List;

/**
 * Represents a visualization of the selection sort algorithm using boxes.
 */
public class SelectionSortVisualization extends SortingVisualization {

    private static final long sleep = 50;

    public SelectionSortVisualization(List<Box> boxes, int boxSpacing, BoxNotePlayer player) {
        super(boxes, boxSpacing, player);
    }

    /**
     * Constructs a SelectionSortVisualization with the specified list of boxes.
     *
     * @param boxes The list of boxes to visualize and sort.
     */
    public SelectionSortVisualization(List<Box> boxes, int boxSpacing) {
        super(boxes, boxSpacing);
    }

    @Override
    public void visualize() throws InterruptedException {
        setTitle(this.getClass().getSimpleName());
        setVisible(true);
        this.sort();
    }

    /**
     * Executes the selection sort algorithm and visualizes each step.
     *
     * @throws InterruptedException If the thread is interrupted while sleeping.
     */
    public void sort() throws InterruptedException {

        final List<Box> boxes = getBoxes();
        final BoxNotePlayer player = getBoxNotePlayer();

        for (int i = 0; i < getBoxes().size(); i++) {

            Thread.sleep(sleep);

            int minIndex = i;
            boxes.get(i).setColor(Color.GREEN);
            super.repaint();
            player.playBox(boxes.get(i), 100, sleep);

            for (int j = i + 1; j < boxes.size(); j++) {
                if (boxes.get(minIndex).getHeight() > boxes.get(j).getHeight()) {
                    minIndex = j;
                    boxes.get(j).setColor(Color.RED);
                    super.repaint();
                    player.playBox(boxes.get(j), 100, sleep);

                    boxes.get(j).setColor(Color.WHITE);
                    super.repaint();
                }
            }

            boxes.get(minIndex).setColor(Color.RED);
            super.repaint();
            player.playBox(boxes.get(minIndex), 100, sleep);

            boxes.get(i).setColor(Color.WHITE);
            boxes.get(minIndex).setColor(Color.GRAY);
            super.repaint();

            Collections.swap(boxes, i, minIndex);

        }

        sortingDoneAnimation();
    }
}
