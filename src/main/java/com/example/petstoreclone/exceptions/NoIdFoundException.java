package com.example.petstoreclone.exceptions;

public class NoIdFoundException extends RuntimeException {
    private static final String NO_SUCH_ID = "No such id found!";

    public NoIdFoundException() {
    }

    public NoIdFoundException(String message) {
        super(message);
    }

    public NoIdFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public NoIdFoundException(Throwable cause) {
        super(cause);
    }

    public NoIdFoundException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

    @Override
    public String getMessage() {
        return NO_SUCH_ID;
    }
}
