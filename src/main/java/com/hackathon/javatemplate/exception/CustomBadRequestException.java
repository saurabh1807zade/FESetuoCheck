package com.hackathon.javatemplate.exception;

public class CustomBadRequestException extends RuntimeException {

    public static final long serialVersionUID = 1L;

    public String message;

    public CustomBadRequestException(String message) {
        this.message = message;
    }

}
