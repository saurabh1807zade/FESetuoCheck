package com.hackathon.javatemplate.auth.constants;

public class ResponseErrors {

    public static final String INVALID_CREDENTIAL = "Provided credentials are invalid.";

    public static final String INVALID_ACCESS_TOKEN = "Provided access token is invalid.";

    public static final String EXPIRED_ACCESS_TOKEN = "Provided access token has expired. Please refresh token or login again.";

    public static final String INVALID_REFRESH_TOKEN = "Provided refresh token is invalid or expired.";

    public static final String INACTIVE_USER = "Inactive user. Please contact admin to activate account.";

    public static final String SAMPLE_ENTITY_NOT_EXIST = "Sample entity does not exist ";
}
