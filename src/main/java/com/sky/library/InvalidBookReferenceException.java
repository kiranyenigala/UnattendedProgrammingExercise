package com.sky.library;

public class InvalidBookReferenceException extends RuntimeException {

    public InvalidBookReferenceException(String message) {
        super(message);
    }
}
