package com.hackathon.javatemplate.auth.constants;

import com.hackathon.javatemplate.auth.enums.Role;

import java.util.Arrays;

public class ResponseSuccess {

    public static final String LOGIN_SUCCESS = "Logged in successfully.";

    public static final String REFRESH_TOKEN_SUCCESS = "Token refreshed successfully.";

    public static final String USER_REGISTER_SUCCESS = "User registered successfully.";

    public static final String USER_ACTIVATED_SUCCESS = "User activated successfully.";

    public static final String PASSWORD_UPDATED_SUCCESS = "Password updated successfully.";

    public static final String userType = Arrays.stream(Role.values()).map((role -> role.name)).toList().toString();

    public static final String INVALID_USER_TYPE = "User type is invalid. Available user types: " + userType;

    public static final String SAMPLE_ENTITY_CREATED = "Sample entity created successfully.";

    public static final String SAMPLE_ENTITY_UPDATED = "Sample entity updated successfully.";

    public static final String SAMPLE_ENTITY_FOUND = "Sample entity found successfully.";
}
