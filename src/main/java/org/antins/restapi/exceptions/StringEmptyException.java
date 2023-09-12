package org.antins.restapi.exceptions;

public class StringEmptyException extends RuntimeException {
    public StringEmptyException(String message) {
        super(message);
    }

    public StringEmptyException(String message, Throwable cause) {
        super(message, cause);
    }
}
