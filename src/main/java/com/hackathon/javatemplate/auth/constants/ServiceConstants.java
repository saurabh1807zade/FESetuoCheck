package com.hackathon.javatemplate.auth.constants;

public class ServiceConstants {
    public static final String ID = "id";
    public static final String EMAIL_REGEX_PATTERN = "^(?=.{1,64}@)[A-Za-z0-9_-]+(\\.[A-Za-z0-9_-]+)*@[^-][A-Za-z0-9-]+(\\.[A-Za-z0-9-]+)*(\\.[A-Za-z]{2,})$";
    public static final String PHONE_NUMBER_REGEX_PATTERN = "^(\\+\\d{1,3}[- ]?)?\\d{10}$";
    public static final Integer DEFAULT_PAGE = 0;
    public static final Integer DEFAULT_PAGE_SIZE = 50;
}
