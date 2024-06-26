package org.ahmetaksunger;

import org.ahmetaksunger.frame.SortingVisualization;
import org.ahmetaksunger.model.Box;
import org.ahmetaksunger.util.BoxNotePlayer;
import org.ahmetaksunger.util.BoxUtils;

import java.awt.*;
import java.util.Collections;
import java.util.List;

/**
 * Represents a visualization of the selection sort algorithm using boxes.
 */
public class SelectionSortVisualization extends SortingVisualization {

    private static final long sleep = 200;

    private BoxNotePlayer player;

    /**
     * Constructs a SelectionSortVisualization with the specified list of boxes.
     *
     * @param boxes The list of boxes to visualize and sort.
     * @throws InterruptedException If the thread is interrupted while sleeping.
     */
    public SelectionSortVisualization(List<Box> boxes, int boxSpacing) throws InterruptedException {
        super(boxes, boxSpacing);
        this.player = new BoxNotePlayer(boxes, 40, 70, true);
        sort();
    }

    /**
     * Executes the selection sort algorithm and visualizes each step.
     *
     * @throws InterruptedException If the thread is interrupted while sleeping.
     */
    public void sort() throws InterruptedException {

        List<Box> boxes = getBoxes();
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

            boxes.get(minIndex).swapx(boxes.get(i));
            Collections.swap(boxes, i, minIndex);

        }

        sortingDoneAnimation();
    }

    public static void main(String[] args) throws InterruptedException {
        List<Box> defaultBoxes = BoxUtils.sorted(40, 600);
        BoxUtils.shuffleX(defaultBoxes);
        new SelectionSortVisualization(defaultBoxes, 30);

    }
}
