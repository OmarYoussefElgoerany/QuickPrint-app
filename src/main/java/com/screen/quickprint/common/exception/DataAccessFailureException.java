package com.screen.quickprint.common.exception;

public class DataAccessFailureException extends RuntimeException {
    public DataAccessFailureException(String message) {
        super(message);
    }

    public DataAccessFailureException(String message, Throwable cause) {
        super(message, cause);
    }
}
