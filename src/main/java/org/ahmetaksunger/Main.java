package org.ahmetaksunger;

import org.ahmetaksunger.frame.SortingVisualization;
import org.ahmetaksunger.model.Box;
import org.ahmetaksunger.util.BoxNotePlayer;
import org.ahmetaksunger.util.BoxUtils;

import java.util.List;

public class Main {
    public static void main(String[] args) throws InterruptedException {

        List<Box> boxes = BoxUtils.sorted(60, 600);
        BoxUtils.shuffleX(boxes);
        SortingVisualization bubbleSort = new BubbleSortVisualization(boxes, 10);
        BoxUtils.shuffleX(boxes);
        SortingVisualization insertionSort = new InsertionSortVisualization(boxes, 30);
        BoxUtils.shuffleX(boxes);
        SortingVisualization selectionSort = new SelectionSortVisualization(boxes, 30);

        selectionSort.setBoxNotePlayer(new BoxNotePlayer(selectionSort.getBoxes(), 30, 90, true));
        selectionSort.visualize();

        bubbleSort.visualize();
        insertionSort.visualize();
    }
}
