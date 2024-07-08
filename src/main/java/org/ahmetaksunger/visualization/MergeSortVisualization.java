package org.ahmetaksunger.visualization;

import org.ahmetaksunger.frame.SortingVisualization;
import org.ahmetaksunger.model.Box;
import org.ahmetaksunger.util.BoxNotePlayer;

import java.awt.*;
import java.util.List;

public class MergeSortVisualization extends SortingVisualization {

    private static final int sleep = 50;
    private final List<Box> boxes = getBoxes();
    private final BoxNotePlayer player = getBoxNotePlayer();

    public MergeSortVisualization(List<Box> boxes, int boxSpacing, BoxNotePlayer boxNotePlayer) {
        super(boxes, boxSpacing, boxNotePlayer);
    }

    public MergeSortVisualization(List<Box> boxes, int boxSpacing) {
        super(boxes, boxSpacing);
    }

    @Override
    public void visualize() throws InterruptedException {
        setTitle(this.getClass().getSimpleName());
        setVisible(true);
        this.sort(0, boxes.size() - 1);
        sortingDoneAnimation();
    }

    // subarray1 --> l...m
    // subarray2 --> m+1...r
    private void merge(int l, int m, int r) throws InterruptedException {

        final int n1 = m - l + 1;
        final int n2 = r - m;

        Box[] L = new Box[n1];
        Box[] R = new Box[n1];

        for (int i = 0; i < n1; ++i) {
            Box box = boxes.get(l + i);
            L[i] = box;
        }

        for (int i = 0; i < n2; ++i) {
            Box box = boxes.get(m + 1 + i);
            R[i] = box;
        }

        int i = 0, j = 0;
        int k = l;

        while (i < n1 && j < n2) {
            if (L[i].getHeight() <= R[j].getHeight()) {
                boxes.set(k, L[i]);
                setColorAndPlayBox(L[i], Color.GREEN);
                i++;
            } else {
                boxes.set(k, R[j]);
                setColorAndPlayBox(R[j], Color.RED);
                j++;
            }
            k++;
        }

        while (i < n1) {
            boxes.set(k, L[i]);
            setColorAndPlayBox(L[i], Color.GREEN);
            i++;
            k++;
        }

        while (j < n2) {
            boxes.set(k, R[j]);
            setColorAndPlayBox(R[j], Color.RED);
            j++;
            k++;
        }

    }

    private void sort(int l, int r) throws InterruptedException {
        if (l < r) {

            int m = l + (r - l) / 2;

            sort(l, m);
            sort(m + 1, r);

            merge(l, m, r);
        }
    }

    private void setColorAndPlayBox(final Box box, final Color color) throws InterruptedException {
        box.setColor(color);
        super.repaint();
        player.playBox(box, 100, sleep);
        box.setColor(Color.WHITE);
    }
}
