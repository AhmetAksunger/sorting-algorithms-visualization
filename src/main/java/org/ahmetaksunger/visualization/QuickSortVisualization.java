package org.ahmetaksunger.visualization;

import org.ahmetaksunger.frame.SortingVisualization;
import org.ahmetaksunger.model.Box;
import org.ahmetaksunger.util.BoxNotePlayer;

import java.awt.*;
import java.util.Collections;
import java.util.List;

public class QuickSortVisualization extends SortingVisualization {

    private static final int sleep = 100;
    private final List<Box> boxes = getBoxes();

    public QuickSortVisualization(List<Box> boxes, int boxSpacing, BoxNotePlayer boxNotePlayer) {
        super(boxes, boxSpacing, boxNotePlayer);
    }

    public QuickSortVisualization(List<Box> boxes, int boxSpacing) {
        super(boxes, boxSpacing);
    }

    @Override
    public void visualize() throws InterruptedException {
        setTitle(this.getClass().getSimpleName());
        setVisible(true);
        this.sort(0, boxes.size() - 1);
        sortingDoneAnimation();
    }

    public void sort(int low, int high) throws InterruptedException {

        if (low < high) {
            int pi = partition(low, high);

            sort(low, pi - 1);
            sort(pi + 1, high);
        }
    }

    private int partition(int low, int high) throws InterruptedException {

        BoxNotePlayer player = getBoxNotePlayer();

        Box pivot = boxes.get(high);
        pivot.setColor(Color.GREEN);
        super.repaint();

        int i = low - 1;

        for (int j = low; j < high; j++) {
            if (boxes.get(j).getHeight() <= pivot.getHeight()) {
                i++;
                var boxi = boxes.get(i);
                var boxj = boxes.get(j);
                boxi.setColor(Color.CYAN);
                boxj.setColor(Color.RED);
                super.repaint();
                player.playBox(boxi, 100, sleep);
                boxi.setColor(Color.WHITE);
                boxj.setColor(Color.WHITE);
                super.repaint();
                Collections.swap(boxes, i, j);
            }
        }
        Collections.swap(boxes, i + 1, high);
        pivot.setColor(Color.WHITE);
        return i + 1;
    }
}
