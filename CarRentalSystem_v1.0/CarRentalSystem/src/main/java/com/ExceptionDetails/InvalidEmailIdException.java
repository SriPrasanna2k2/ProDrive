package com.ExceptionDetails;

public class InvalidEmailIdException extends Exception {
    public InvalidEmailIdException(String message) {
        super(message);
    }
}