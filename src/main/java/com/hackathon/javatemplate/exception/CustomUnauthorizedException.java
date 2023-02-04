package com.hackathon.javatemplate.exception;

public class CustomUnauthorizedException extends RuntimeException {
    public static final long serialVersionUID = 1L;

    public String message;

    public CustomUnauthorizedException(String message) {
        this.message = message;
    }

}
