package org.ahmetaksunger.util;

import org.ahmetaksunger.exception.BoxNotInTheListException;
import org.ahmetaksunger.model.Box;

import javax.sound.midi.MidiChannel;
import javax.sound.midi.MidiSystem;
import javax.sound.midi.MidiUnavailableException;
import javax.sound.midi.Synthesizer;
import java.util.List;

/**
 * The BoxNotePlayer class provides functionality to play MIDI notes based on the heights of boxes.
 */
public class BoxNotePlayer {

    private static final Synthesizer synthesizer;

    static {
        try {
            synthesizer = MidiSystem.getSynthesizer();
        } catch (MidiUnavailableException e) {
            throw new RuntimeException(e);
        }
    }

    private int minHeight;
    private int maxHeight;
    private int minNote;
    private int maxNote;
    private boolean isInverted;
    private List<Box> boxes;

    /**
     * Constructs a BoxNotePlayer with the specified list of boxes, MIDI note range, and inversion flag.
     *
     * @param boxes      The list of boxes to map heights to MIDI notes.
     * @param minNote    The minimum MIDI note in the range.
     * @param maxNote    The maximum MIDI note in the range.
     * @param isInverted Whether to invert the mapping (true for inverted, false otherwise).
     */
    public BoxNotePlayer(List<Box> boxes, int minNote, int maxNote, boolean isInverted) {

        this.boxes = boxes;
        this.minNote = minNote;
        this.maxNote = maxNote;
        this.maxHeight = boxes.stream()
                .max((box, other) -> Math.max(box.getHeight(), other.getHeight()))
                .orElseThrow()
                .getHeight();
        this.minHeight = boxes.stream()
                .min((box, other) -> Math.min(box.getHeight(), other.getHeight()))
                .orElseThrow()
                .getHeight();
        this.isInverted = isInverted;
    }

    /**
     * Plays a MIDI note with the specified parameters.
     *
     * @param note     The MIDI note to play.
     * @param velocity The velocity (volume) of the note.
     * @param sleep    The duration to hold the note (in milliseconds).
     * @throws InterruptedException If the thread is interrupted while sleeping.
     */
    public void playNote(int note, int velocity, long sleep) throws InterruptedException {

        try {
            synthesizer.open();
            MidiChannel[] channels = synthesizer.getChannels();
            MidiChannel channel = channels[0];
            channel.noteOn(note, velocity);
            Thread.sleep(sleep);
            channel.noteOff(note);
            synthesizer.close();
        } catch (MidiUnavailableException e) {
            throw new RuntimeException(e);
        }

    }

    /**
     * Plays a MIDI note based on the height of the specified box.
     *
     * @param box      The box whose height determines the MIDI note to play.
     * @param velocity The velocity (volume) of the note.
     * @param sleep    The duration to hold the note (in milliseconds).
     * @throws InterruptedException     If the thread is interrupted while sleeping.
     * @throws BoxNotInTheListException If the specified box is not in the list of boxes.
     */
    public void playBox(Box box, int velocity, long sleep) throws InterruptedException {
        if (!boxes.contains(box))
            throw new BoxNotInTheListException("The specified box with the hash: " + box.hashCode() + " is not in the list");

        this.playNote(mapHeightToNote(box.getHeight()), velocity, sleep);
    }

    /**
     * Maps the height of a box to a MIDI note within the specified range.
     *
     * @param height The height of the box to map.
     * @return The MIDI note mapped from the height.
     */
    private int mapHeightToNote(int height) {

        double normalizedHeight = (double) (height - minHeight) / (maxHeight - minHeight);

        if (!isInverted)
            normalizedHeight = 1 - normalizedHeight;

        return (int) (minNote + (normalizedHeight * (maxNote - minNote)));
    }

    public List<Box> getBoxes() {
        return boxes;
    }

    public boolean isInverted() {
        return isInverted;
    }

    public int getMaxNote() {
        return maxNote;
    }

    public int getMinNote() {
        return minNote;
    }

    public int getMaxHeight() {
        return maxHeight;
    }

    public int getMinHeight() {
        return minHeight;
    }

    /**
     * A main method for testing purposes, demonstrating how to use BoxNotePlayer to play MIDI notes.
     *
     * @param args Command line arguments (not used in this context).
     * @throws InterruptedException If the thread is interrupted while sleeping.
     */
    public static void main(String[] args) throws InterruptedException {
        List<Box> sorted = BoxUtils.sorted(30, 600);
        BoxNotePlayer boxNotePlayer = new BoxNotePlayer(sorted, 60, 90, true);
        boxNotePlayer.playBox(sorted.get(3), 90, 1000);

        for (Box box : sorted) {
            boxNotePlayer.playBox(box, 90, 300);
        }
    }
}
