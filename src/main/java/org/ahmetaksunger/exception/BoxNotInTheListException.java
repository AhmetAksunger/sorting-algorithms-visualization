package org.ahmetaksunger.exception;

/**
 * A custom exception class indicating that a specified box is not in the list of boxes.
 */
public class BoxNotInTheListException extends RuntimeException {

    public BoxNotInTheListException(String message) {
        super(message);
    }
}
