package org.ahmetaksunger.frame;


import org.ahmetaksunger.exception.UnequalBoxWidthsException;
import org.ahmetaksunger.model.Box;
import org.ahmetaksunger.util.BoxNotePlayer;

import javax.swing.*;
import java.awt.*;
import java.util.List;

/**
 * This is a base class for different sorting visualizations. It extends {@link JFrame} and
 * provides a graphical visualization of a list of boxes.
 */
public abstract class SortingVisualization extends JFrame {

    private final List<Box> boxes;
    private final int boxSpacing;
    private BoxNotePlayer boxNotePlayer;

    public SortingVisualization(List<Box> boxes, int boxSpacing, BoxNotePlayer boxNotePlayer) {
        this.boxes = boxes;
        this.boxSpacing = boxSpacing;
        this.boxNotePlayer = boxNotePlayer;
        validateBoxWidths();
        setFrame();
    }

    public SortingVisualization(List<Box> boxes, int boxSpacing) {
        this(boxes, boxSpacing, new BoxNotePlayer(boxes, 50, 80, true));
    }

    public abstract void visualize() throws InterruptedException;

    /**
     * Overrides the paint method to draw each box in the list using its specified color,
     * position, width, and height.
     *
     * @param g The Graphics context used for painting.
     */
    @Override
    public void paint(Graphics g) {
        super.paint(g);
        int x = 0;
        for (Box box : boxes) {
            g.setColor(box.getColor());
            g.fillRect(x, box.getY(), box.getWidth(), box.getHeight());
            x += boxSpacing;
        }
    }

    /**
     * Animates the sorting done state by changing each box's color to WHITE and then
     * to GRAY, with a short delay between changes.
     *
     * @throws InterruptedException If the thread is interrupted while sleeping.
     */
    public void sortingDoneAnimation() throws InterruptedException {
        for (Box box : boxes) {
            box.setColor(Color.WHITE);
            super.repaint();
            boxNotePlayer.playBox(box, 100, 50);
            box.setColor(Color.GRAY);
            super.repaint();
        }
    }

    /**
     * Getter for the property List of boxes
     *
     * @return List of boxes
     */
    public List<Box> getBoxes() {
        return boxes;
    }

    public BoxNotePlayer getBoxNotePlayer() {
        return boxNotePlayer;
    }

    public void setBoxNotePlayer(BoxNotePlayer boxNotePlayer) {
        this.boxNotePlayer = boxNotePlayer;
    }

    /**
     * Private method to set up the frame.
     */
    private void setFrame() {
        setTitle("Black Frame");

        int n = boxes.size();
        int width = (n * boxes.get(0).getWidth()) + ((n - 1) * (boxSpacing - boxes.get(0).getWidth()));

        setSize(width, 600);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        getContentPane().setBackground(Color.BLACK);
        setLocationRelativeTo(null);
    }

    private void validateBoxWidths() {

        int boxWidth = boxes.get(0).getWidth();

        if (!boxes.stream().allMatch(box -> box.getWidth() == boxWidth))
            throw new UnequalBoxWidthsException("Not all boxes have the same width");
    }
}

