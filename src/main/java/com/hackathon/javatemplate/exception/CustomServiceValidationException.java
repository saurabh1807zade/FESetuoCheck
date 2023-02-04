package com.hackathon.javatemplate.exception;

public class CustomServiceValidationException extends RuntimeException {

    public static final long serialVersionUID = 1L;

    public String message;

    public CustomServiceValidationException(String message) {
        this.message = message;
    }
}
