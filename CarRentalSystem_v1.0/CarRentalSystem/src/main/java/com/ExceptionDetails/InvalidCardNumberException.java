package com.ExceptionDetails;

public class InvalidCardNumberException  extends Exception {
    public InvalidCardNumberException(String message) {
        super(message);
    }
}