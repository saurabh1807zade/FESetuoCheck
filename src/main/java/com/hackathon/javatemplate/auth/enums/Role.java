package com.hackathon.javatemplate.auth.enums;

public enum Role {

    USER("user"),
    ADMIN("admin");

    public final String name;

    Role(String name) {
        this.name = name;
    }
}
