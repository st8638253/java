package com.mycompany.app;

// викликайте власне виключення (InvalidInputException)
public class InvalidInputException extends Exception {
    public InvalidInputException(String message) {
        super(message);
    }
}
