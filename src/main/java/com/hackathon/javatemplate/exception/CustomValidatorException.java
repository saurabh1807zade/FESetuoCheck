package com.hackathon.javatemplate.exception;

public class CustomValidatorException extends RuntimeException{

    public static final long serialVersionUID = 1L;

    public String message;

    public CustomValidatorException(String message) {
        this.message = message;
    }
}
