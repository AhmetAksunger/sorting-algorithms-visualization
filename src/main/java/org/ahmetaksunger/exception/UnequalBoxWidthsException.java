package org.ahmetaksunger.exception;

/**
 * A custom exception class indicating that not all the boxes in a list have the same widths.
 */
public class UnequalBoxWidthsException extends RuntimeException {

    public UnequalBoxWidthsException(String message) {
        super(message);
    }
}
