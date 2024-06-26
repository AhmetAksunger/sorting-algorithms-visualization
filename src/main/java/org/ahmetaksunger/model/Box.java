package org.ahmetaksunger.model;

import java.awt.*;

/**
 * The Box class represents a rectangular box with specified dimensions, position, and color.
 */
public class Box {

    private static final int DEFAULT_WIDTH = 20;

    private static final Color DEFAULT_COLOR = Color.WHITE;

    private int height;

    private int width;

    private Color color;

    private int x;

    private int y;

    /**
     * Constructs a Box object with the specified height, width, position (x, y), and color.
     *
     * @param height The height of the box.
     * @param width  The width of the box.
     * @param x      The x-coordinate of the box's top-left corner.
     * @param y      The y-coordinate of the box's top-left corner.
     * @param color  The color of the box.
     */
    public Box(int height, int width, int x, int y, Color color) {
        this.height = height;
        this.width = width;
        this.color = color;
        this.x = x;
        this.y = y;
    }

    /**
     * Constructs a Box object with the specified width, height, position (x, y),
     * and the default color (white).
     *
     * @param width  The width of the box.
     * @param height The height of the box.
     * @param x      The x-coordinate of the box's top-left corner.
     * @param y      The y-coordinate of the box's top-left corner.
     */
    public Box(int width, int height, int x, int y) {
        this(height, width, x, y, DEFAULT_COLOR);
    }

    /**
     * Constructs a Box object with the specified height, default width, position (x, y),
     * and color.
     *
     * @param height The height of the box.
     * @param x      The x-coordinate of the box's top-left corner.
     * @param y      The y-coordinate of the box's top-left corner.
     * @param color  The color of the box.
     */
    public Box(int height, int x, int y, Color color) {
        this(height, DEFAULT_WIDTH, x, y, color);
    }


    /**
     * Constructs a Box object with the specified height, default width, position (x, y),
     * and the default color (white).
     *
     * @param height The height of the box.
     * @param x      The x-coordinate of the box's top-left corner.
     * @param y      The y-coordinate of the box's top-left corner.
     */
    public Box(int height, int x, int y) {
        this(height, DEFAULT_WIDTH, x, y, DEFAULT_COLOR);
    }

    /**
     * Swaps the x-coordinate of this box with another box.
     *
     * @param other The other Box object to swap coordinates with.
     */
    @Deprecated(forRemoval = false)
    public void swapx(Box other) {
        var tempx = this.x;
        this.x = other.x;
        other.x = tempx;
    }

    // Getters & Setters
    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }
}
