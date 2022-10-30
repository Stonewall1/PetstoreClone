package com.example.petstoreclone.exceptions;

public class NoTokenFoundException extends RuntimeException {
    private static final String NO_TOKEN_FOUND = "No token found!";

    public NoTokenFoundException() {
    }

    public NoTokenFoundException(String message) {
        super(message);
    }

    public NoTokenFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public NoTokenFoundException(Throwable cause) {
        super(cause);
    }

    public NoTokenFoundException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

    @Override
    public String getMessage() {
        return NO_TOKEN_FOUND;
    }
}
